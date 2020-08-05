package demo.projects.test.framework.helpers.test.data.loader;

import demo.projects.test.framework.helpers.image.comparison.NamedBufferedImage;

/**
 * Interface to be implemented by test data objects with not categorized images.
 */
public interface TestDataWithNotCategorizedImages extends TestDataWithImages {

    /**
     * Method defining how to store returned not categorized images.
     * @param namedImages Array of not categorized images.
     */
    void setNotCategorizedImagesForObject(NamedBufferedImage[] namedImages);
}
