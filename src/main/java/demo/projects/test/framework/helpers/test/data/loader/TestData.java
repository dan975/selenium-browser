package demo.projects.test.framework.helpers.test.data.loader;

/**
 * Interface to be extended by test data objects that are meant to be loaded with {@link TestDataLoader}
 * and do not have images to be loaded.
 */
public interface TestData {

    /**
     * @return Id uniquely identifying test data object instance.
     */
    int getInternalId();
}
