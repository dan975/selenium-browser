package demo.projects.sut.mapping.pages.checkout;

import demo.projects.sut.mapping.element.wrappers.cart.items.CheckoutCartItemPayment;
import demo.projects.sut.mapping.pages.BasePageComponent;
import demo.projects.sut.mapping.selectors.pages.base.checkout.SummaryAndPaymentPageSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 05 Checkout page 1st payment page displaying cart item table and payment method selection links.
 */
@ThreadComponent
public class PaymentChoosePaymentPage extends BasePageComponent {

    @Autowired
    private SummaryAndPaymentPageSelectors summaryAndPaymentPageSelectors;

    /**
     * @return All cart item row elements.
     */
    public List<CheckoutCartItemPayment> getCartItems() {
        return getDriverWrapper().waitUntilFirstVisibleAndFindAll(summaryAndPaymentPageSelectors.getCartItem())
                .stream()
                .map(e -> getElementFactory().getCartItemPayment(e))
                .collect(Collectors.toList());
    }

    /**
     * @return Total product cost element.
     */
    public WebElementWrapper getProductCostTotal() {
        return getDriverWrapper().waitUntilVisible(summaryAndPaymentPageSelectors.getTotalProducts());
    }

    /**
     * @return Shipping cost element.
     */
    public WebElementWrapper getShippingCostTotal() {
        return getDriverWrapper().waitUntilVisible(summaryAndPaymentPageSelectors.getTotalShipping());
    }

    /**
     * @return Total tax element.
     */
    public WebElementWrapper getTotalTax() {
        return getDriverWrapper().waitUntilVisible(summaryAndPaymentPageSelectors.getTotalTax());
    }

    /**
     * @return Total cost (Total product cost + shipping cost + total tax) element.
     */
    public WebElementWrapper getTotalCost() {
        return getDriverWrapper().waitUntilVisible(summaryAndPaymentPageSelectors.getTotalCost());
    }

    /**
     * @return Bank wire payment link.
     */
    public WebElementWrapper getBankWireLink() {
        return getDriverWrapper().waitUntilVisible(summaryAndPaymentPageSelectors.getBankWire());
    }

    /**
     * @return Check payment link.
     */
    public WebElementWrapper getCheckLink() {
        return getDriverWrapper().waitUntilVisible(summaryAndPaymentPageSelectors.getCheck());
    }
}
