package demo.projects.sut.mapping.pages.components;

import demo.projects.sut.mapping.pages.BasePageComponent;
import demo.projects.sut.mapping.selectors.pages.base.components.AdditionConfirmationSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * Addition confirmation page.
 * Displayed to user when user clicks "Add to cart" button in
 * {@link demo.projects.sut.mapping.pages.ProductQuickViewPage}.
 */
@ThreadComponent
public class AdditionConfirmationPopup extends BasePageComponent {

    @Autowired
    private AdditionConfirmationSelectors additionConfirmationSelectors;

    /**
     * @return Total product in cart quantity element.
     */
    public WebElementWrapper getQuantity() {
        getProductImage();
        return getDriverWrapper().waitUntilPresent(additionConfirmationSelectors.getQuantity());
    }

    /**
     * Assert total product cost, shipping cost and total cost with shipping is as expected.
     * @param expectedProductTotalCost Expected product total cost in cents.
     */
    public void assertCostValuesAreAsExpected(int expectedProductTotalCost) {
        int actualProductTotal = parseCostAndConvertToCents(getProductTotalsValue().getAttribute("innerText"));
        assertEquals(expectedProductTotalCost, actualProductTotal);

        int actualShippingCost = parseCostAndConvertToCents(getShippingCostValue().getAttribute("innerText"));
        assertEquals(getDefaultShippingCostInCents(), actualShippingCost);

        int expectedTotalCost = expectedProductTotalCost + getDefaultShippingCostInCents();
        int actualTotalCost = parseCostAndConvertToCents(getTotalCostValue().getAttribute("innerText"));
        assertEquals(expectedTotalCost, actualTotalCost);
    }

    /**
     * @return Product total cost element.
     */
    public WebElementWrapper getProductTotalsValue() {
        getProductImage();
        return getDriverWrapper().waitUntilPresent(additionConfirmationSelectors.getProductTotalsValue());
    }

    /**
     * @return Shipping cost element.
     */
    public WebElementWrapper getShippingCostValue() {
        return getDriverWrapper().waitUntilPresent(additionConfirmationSelectors.getShippingTotalValue());
    }

    /**
     * @return Total cost (Total product cost + shipping) element.
     */
    public WebElementWrapper getTotalCostValue() {
        return getDriverWrapper().waitUntilPresent(additionConfirmationSelectors.getTotalCostValue());
    }

    /**
     * @return Product image element.
     */
    public WebElementWrapper getProductImage() {
        return getDriverWrapper().waitUntilVisible(additionConfirmationSelectors.getImage());
    }

    /**
     * @return Continue shopping button.
     */
    public WebElementWrapper getContinueShoppingBtn() {
        return getDriverWrapper().waitUntilVisible(additionConfirmationSelectors.getContinueBtn());
    }

    /**
     * @return Proceed to checkout button.
     */
    public WebElementWrapper getProceedToCheckoutBtn() {
        return getDriverWrapper().waitUntilVisible(additionConfirmationSelectors.getProceedBtn());
    }
}
