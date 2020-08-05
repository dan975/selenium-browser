package demo.projects.test.framework.helpers.test.data.loader;

import demo.projects.test.framework.helpers.image.comparison.NamedBufferedImage;

import java.util.Map;

/**
 * Interface to be implemented by test data objects with categorized images.
 */
public interface TestDataWithCategorizedImages extends TestDataWithImages {

    /**
     * Method defining how to store returned categorized images.
     * @param categorizedImages Map of categorized images loaded from image file directory.
     */
    void setCategorizedImagesForObject(Map<String, NamedBufferedImage[]> categorizedImages);
}
