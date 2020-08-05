package demo.projects.sut.mapping.pages;

import demo.projects.sut.mapping.element.wrappers.ElementFactory;
import demo.projects.sut.mapping.test.data.supporting.ImageComparisonThresholds;
import demo.projects.test.framework.driver.abstraction.DriverWrapper;
import demo.projects.test.framework.driver.abstraction.WebElementWrapperFactory;
import demo.projects.test.framework.helpers.image.comparison.ImageLoader;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.Assert.assertEquals;

/**
 * Base page object for shared page object component, functionality encapsulation.
 */
@Getter(AccessLevel.PROTECTED)
public abstract class BasePageComponent {

    @Autowired
    private DriverWrapper driverWrapper;

    @Autowired
    private ElementFactory elementFactory;

    @Autowired
    private WebElementWrapperFactory webElementWrapperFactory;

    @Value("${defaultShippingCostInCents}")
    private int defaultShippingCostInCents;

    @Autowired
    private ImageLoader imageLoader;

    @Autowired
    private ImageComparisonThresholds imageComparisonThresholds;

    /**
     * Method converts passed price in dollars to cents as an integer
     * and asserts that price text starts with dollar symbol, one and only one dot is present as cent value delimiter.
     * @param costWithCurrencySymbol Cost text value with dollar sign (e.g. "$5.00")
     * @return Price in cents.
     */
    protected int parseCostAndConvertToCents(String costWithCurrencySymbol) {
        assertEquals('$', costWithCurrencySymbol.charAt(0));

        String priceWithoutCurrencySymbol = costWithCurrencySymbol.substring(1);
        @SuppressWarnings("checkstyle:MagicNumber")
        char actualCharSecondFromEnd = priceWithoutCurrencySymbol.charAt(priceWithoutCurrencySymbol.length() - 3);
        assertEquals("Expected second character from end to contain dot", '.', actualCharSecondFromEnd);

        var cents = priceWithoutCurrencySymbol.replaceFirst("\\.", "");
        return Integer.parseInt(cents);
    }
}
