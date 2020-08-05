package demo.projects.test.framework.helpers.test.data.loader;

/**
 * Interface for test data objects with images.
 */
interface TestDataWithImages extends TestData {

    /**
     * @return Path to image directory under resource image directory.
     */
    String getImagePath();
}
