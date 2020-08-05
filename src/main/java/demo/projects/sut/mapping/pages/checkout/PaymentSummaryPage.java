package demo.projects.sut.mapping.pages.checkout;

import demo.projects.sut.mapping.pages.BasePageComponent;
import demo.projects.sut.mapping.selectors.pages.base.checkout.PaymentSummaryPageSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.Assert.assertEquals;

/**
 * 05 Checkout page 2nd page showing payment type and amount and other relevant detail summary.
 */
@ThreadComponent
public class PaymentSummaryPage extends BasePageComponent {

    @Autowired
    private PaymentSummaryPageSelectors paymentSummaryPageSelectors;

    /**
     * Expected bank wire payment title.
     */
    @Value("${bankWirePaymentText}")
    private String bankWirePaymentText;

    /**
     * Assert bank wire payment title is as expected.
     */
    public void assertBankWirePaymentText() {
        var actualBankWireText = getPaymentTypeTitle().getText();
        assertEquals(bankWirePaymentText, actualBankWireText);
    }

    /**
     * @return Payment title element.
     */
    public WebElementWrapper getPaymentTypeTitle() {
        return getDriverWrapper().waitUntilVisible(paymentSummaryPageSelectors.getPaymentTypeTitle());
    }

    /**
     * @return Amount element.
     */
    public WebElementWrapper getAmount() {
        return getDriverWrapper().waitUntilVisible(paymentSummaryPageSelectors.getPaymentAmount());
    }

    /**
     * @return Payment currency element.
     */
    public WebElementWrapper getPaymentCurrency() {
        return getDriverWrapper().waitUntilVisible(paymentSummaryPageSelectors.getPaymentCurrency());
    }
}
