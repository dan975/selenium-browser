package demo.projects.test.framework.helpers.test.data.loader;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Object encapsulates details for loading specific subset of test data stored in a resource file.
 * @param <T> Generic test data object implementing {@link TestData} interface type.
 */
@AllArgsConstructor
@Getter(AccessLevel.PACKAGE)
public class TestDataSubset<T> {
    /**
     * Key of subset defined in testDataSubsets.properties file.
     * Value of key should be expressed as internal object ids returned by {@link TestData#getInternalId()}
     * separated by commas (e.g. "1,3,2").
     */
    private final String subsetKey;

    /**
     * Resource file path stored under current environment's resource directory.
     */
    private final String resourceFilePath;

    /**
     * Class of test data object.
     */
    private final Class<T> parsedObjectClass;
}
