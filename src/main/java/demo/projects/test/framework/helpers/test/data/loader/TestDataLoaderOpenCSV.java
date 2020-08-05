package demo.projects.test.framework.helpers.test.data.loader;

import com.google.common.collect.ImmutableList;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.MappingStrategy;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * OpenCSV implementation of {@link TestDataLoader}.
 */
@Component
public class TestDataLoaderOpenCSV extends TestDataLoader {

    @SneakyThrows
    @Override
    public <T> ImmutableList<T> loadAndParseObjectsFromCSV(String resourceName, Class<T> parsedObjectClass) {
        var csvParser = getCsvParser(resourceName, parsedObjectClass);
        return ImmutableList.copyOf(csvParser.parse());
    }

    private <T> CsvToBean<T> getCsvParser(String resourceName, Class<T> parsedObjectClass) {
        var csvReader = getCSVReaderForResource(resourceName);
        var strategy = getMappingStrategy(parsedObjectClass);

        return getCsvToBeanParser(csvReader, strategy);
    }

    @SneakyThrows
    private CSVReader getCSVReaderForResource(String resourceName) {
        var resourceFile = getResourceFile(resourceName);
        return new CSVReader(new BufferedReader(new FileReader(resourceFile)));
    }

    @SneakyThrows
    private File getResourceFile(String resourceName) {
        var resourcePath = "classpath:" + getTestDataRootDir() + resourceName;
        return ResourceUtils.getFile(resourcePath);
    }

    private <T> MappingStrategy<T> getMappingStrategy(Class<T> parsedObjectClass) {
        var strategy = new ColumnPositionMappingStrategy<T>();
        strategy.setType(parsedObjectClass);
        return strategy;
    }

    private <T> CsvToBean<T> getCsvToBeanParser(CSVReader csvReader, MappingStrategy<T> strategy) {
        return new CsvToBeanBuilder<T>(csvReader)
                .withMappingStrategy(strategy)
                .build();
    }
}
