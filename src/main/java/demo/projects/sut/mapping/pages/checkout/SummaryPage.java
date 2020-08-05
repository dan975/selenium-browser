package demo.projects.sut.mapping.pages.checkout;

import demo.projects.sut.mapping.element.wrappers.cart.items.CheckoutCartItemSummary;
import demo.projects.sut.mapping.pages.BasePageComponent;
import demo.projects.sut.mapping.selectors.pages.base.checkout.SummaryAndPaymentPageSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * 01 Checkout page containing purchase summary.
 */
@ThreadComponent
public class SummaryPage extends BasePageComponent {

    @Autowired
    private SummaryAndPaymentPageSelectors summaryAndPaymentPageSelectors;

    /**
     * @return All cart item table row elements.
     */
    public List<CheckoutCartItemSummary> getCartItems() {
        return getDriverWrapper().waitUntilFirstVisibleAndFindAll(summaryAndPaymentPageSelectors.getCartItem())
                .stream()
                .map(e -> getElementFactory().getCartItemSummary(e))
                .collect(Collectors.toList());
    }

    /**
     * Partial pricing total summary assertion.
     * Asserts Product total, default shipping cost and total without tax is as expected.
     * @param expectedTotalProductCostInCents Expected product total cost.
     */
    public void assertPartialPricingSummary(int expectedTotalProductCostInCents) {
        int actualProductTotal = parseCostAndConvertToCents(getProductCostTotal().getText());
        assertEquals(expectedTotalProductCostInCents, actualProductTotal);

        int actualShippingTotal = parseCostAndConvertToCents(getShippingCostTotal().getText());
        assertEquals(getDefaultShippingCostInCents(), actualShippingTotal);

        int actualTotalWithoutTax = parseCostAndConvertToCents(getTotalCostWithoutTax().getText());
        int expectedTotalWithoutTax = expectedTotalProductCostInCents + getDefaultShippingCostInCents();
        assertEquals(expectedTotalWithoutTax, actualTotalWithoutTax);
    }

    /**
     * @return Product cost total cost element.
     */
    public WebElementWrapper getProductCostTotal() {
        return getDriverWrapper().waitUntilVisible(summaryAndPaymentPageSelectors.getTotalProducts());
    }

    /**
     * @return Default shipping cost total element.
     */
    public WebElementWrapper getShippingCostTotal() {
        return getDriverWrapper().waitUntilVisible(summaryAndPaymentPageSelectors.getTotalShipping());
    }

    /**
     * @return Total cost without tax (total product cost + shipping).
     */
    public WebElementWrapper getTotalCostWithoutTax() {
        return getDriverWrapper().waitUntilVisible(summaryAndPaymentPageSelectors.getTotalCostWithoutTax());
    }

    /**
     * @return Total tax cost element.
     */
    public WebElementWrapper getTotalTax() {
        return getDriverWrapper().waitUntilVisible(summaryAndPaymentPageSelectors.getTotalTax());
    }

    /**
     * @return Total price (total product cost + shipping + total tax) element.
     */
    public WebElementWrapper getTotalCost() {
        return getDriverWrapper().waitUntilVisible(summaryAndPaymentPageSelectors.getTotalCost());
    }

}
