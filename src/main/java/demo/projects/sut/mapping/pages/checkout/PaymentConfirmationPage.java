package demo.projects.sut.mapping.pages.checkout;

import demo.projects.sut.mapping.pages.BasePageComponent;
import demo.projects.sut.mapping.selectors.pages.base.checkout.PaymentConfirmationPageSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 05 Checkout page 3rd page showing confirmation of made purchase.
 */
@ThreadComponent
public class PaymentConfirmationPage extends BasePageComponent {

    @Autowired
    private PaymentConfirmationPageSelectors paymentConfirmationPageSelectors;

    /**
     * @return Amount element.
     */
    public WebElementWrapper getAmount() {
        return getDriverWrapper().waitUntilVisible(paymentConfirmationPageSelectors.getAmount());
    }
}
