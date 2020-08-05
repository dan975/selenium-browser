package demo.projects.test.framework.helpers.test.data.loader;

import com.google.common.collect.ImmutableList;
import demo.projects.test.framework.helpers.image.comparison.ImageLoader;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * CSV Test data loader.
 * <p>
 * Loads and parses test data from CSV afterwards loads images for parsed object, if test data object implements
 * an interface extending {@link TestDataWithImages}.
 */
@Slf4j
public abstract class TestDataLoader {

    @Autowired
    private ImageLoader imageLoader;

    /**
     * Directory of test data files stored for given environment.
     */
    @javax.annotation.Resource(name = "testDataPath")
    @Getter(AccessLevel.PROTECTED)
    private String testDataRootDir;

    /**
     * Map of list of test objects already loaded into memory.
     * Key - resource param value passed to {@link TestDataLoader#getTestData(String, Class)}.
     * Value - list of parsed test objects from the resource file.
     */
    @SuppressWarnings("rawtypes")
    private final Map<String, ImmutableList> loadedData = Collections.synchronizedMap(new HashMap<>());

    private Properties testDataSubsetProperties;

    @SneakyThrows
    @PostConstruct
    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void initEnvProps() {
        String testDataPath = getTestDataRootDir() + "testDataSubsets.properties";
        Resource resource = new ClassPathResource(testDataPath);
        testDataSubsetProperties = PropertiesLoaderUtils.loadProperties(resource);
    }

    protected abstract <T> ImmutableList<T> loadAndParseObjectsFromCSV(String resourceName, Class<T> parsedObjectClass);

    /**
     * Get test data for test data subset key.
     * @param testDataSubset Test data subset key.
     * @param <T> Generic Test data object implementing {@link TestData} interface type.
     * @return Test data objects for subset key.
     */
    public synchronized <T extends TestData> List<T> getTestData(TestDataSubset<T> testDataSubset) {
        if (!loadedData.containsKey(testDataSubset.getResourceFilePath())) {
            getTestData(testDataSubset.getResourceFilePath(), testDataSubset.getParsedObjectClass());
        }

        List<T> res = new ArrayList<>();

        ImmutableList<T> allTestData = getLoadedTestData(testDataSubset.getResourceFilePath());
        var subsetIds = getSubsetIds(testDataSubset.getSubsetKey());

        // O(n^2) implementation that could significantly slowdown test execution with larger
        // test object counts. Alternatively can duplicate test data object content defining
        // resource files instead of using test data subset implementation.
        for (Integer subsetId : subsetIds) {
            for (T testDatum : allTestData) {
                int internalId = testDatum.getInternalId();
                if (subsetId == internalId) {
                    res.add(testDatum);
                    break;
                }
            }
        }

        return res;
    }

    private List<Integer> getSubsetIds(String subsetKey) {
        log.trace("Retrieving test data subset for key = {}.", subsetKey);
        String[] rawIndexes = ((String) testDataSubsetProperties.get(subsetKey)).split(",");

        List<Integer> res = new LinkedList<>();
        for (String rawIndex : rawIndexes) {
            res.add(Integer.parseInt(rawIndex));
        }

        return res;
    }

    /**
     * Load test data objects stored in CSV file and their images for current environment.
     * @param resource Path of file storing test data for current environment
     *                 (e.g. Relative path for project of resource:
     *                 "src\main\resources\env.dependent\dev\testObjects.csv", where
     *                 "src\main\resources\" path to resources, "dev" - environment,
     *                 "testObjects.csv" - <strong>resource</strong> param)
     * @param parsedObjectClass Class of test object.
     * @param <T> Generic value for class of test object.
     * @return List of test objects stored under passed resource param directory.
     */
    public synchronized <T extends TestData> List<T> getTestData(String resource, Class<T> parsedObjectClass) {
        if (isTestDataLoaded(resource)) {
            return getLoadedTestData(resource);
        } else {
            return loadTestDataAndAddToLoadedContainer(resource, parsedObjectClass);
        }
    }

    private boolean isTestDataLoaded(String resource) {
        return loadedData.containsKey(resource);
    }

    private <T extends TestData> ImmutableList<T> getLoadedTestData(String resource) {
        //noinspection unchecked
        return loadedData.get(resource);
    }

    private <T> ImmutableList<T> loadTestDataAndAddToLoadedContainer(String resource, Class<T> parsedObjectClass) {
        ImmutableList<T> res = loadTestData(resource, parsedObjectClass);
        addDataToLoadedContainer(resource, res);

        return res;
    }

    private <T> ImmutableList<T> loadTestData(String resource, Class<T> parsedObjectClass) {
        ImmutableList<T> res = loadAndParseObjectsFromCSV(resource, parsedObjectClass);
        loadTestDataImages(parsedObjectClass, res);
        return res;
    }

    private <T> void loadTestDataImages(Class<T> parsedObjectClass, ImmutableList<T> res) {
        if (isTestDataWithImages(parsedObjectClass)) {
            if (isWithCategorizedImages(res)) {
                loadCategorizedImages(res);
            } else {
                loadNotCategorizedImages(res);
            }
        }
    }

    private <T> boolean isTestDataWithImages(Class<T> parsedObjectClass) {
        return TestDataWithImages.class.isAssignableFrom(parsedObjectClass);
    }
    private <T> boolean isWithCategorizedImages(ImmutableList<T> res) {
        return res.get(0) instanceof TestDataWithCategorizedImages;
    }

    private <T> void loadCategorizedImages(ImmutableList<T> res) {
        for (T resInstance : res) {
            var castResInstance = (TestDataWithCategorizedImages) resInstance;
            var categorizedImages = imageLoader.getCategorizedImages(castResInstance.getImagePath());

            castResInstance.setCategorizedImagesForObject(categorizedImages);
        }
    }

    private <T> void loadNotCategorizedImages(ImmutableList<T> res) {
        for (T resInstance : res) {
            var castResInstance = (TestDataWithNotCategorizedImages) resInstance;
            var namedBufferedImages = imageLoader.getNotCategorizedImages(castResInstance.getImagePath());

            castResInstance.setNotCategorizedImagesForObject(namedBufferedImages);
        }
    }

    private <T> void addDataToLoadedContainer(String resource, ImmutableList<T> res) {
        loadedData.put(resource, res);
    }
}
