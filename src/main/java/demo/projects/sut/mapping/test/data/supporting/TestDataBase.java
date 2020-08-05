package demo.projects.sut.mapping.test.data.supporting;

/**
 * Base abstract test data object.
 */
public abstract class TestDataBase {
    private static final double CENTS_IN_DOLLAR = 100.0;

    protected static double convertPriceFromCents(int priceInCents) {
        return priceInCents / CENTS_IN_DOLLAR;
    }
}
