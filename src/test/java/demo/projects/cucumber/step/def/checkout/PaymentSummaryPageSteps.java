package demo.projects.cucumber.step.def.checkout;

import demo.projects.cucumber.step.def.BaseSteps;
import demo.projects.sut.mapping.pages.checkout.PaymentSummaryPage;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class PaymentSummaryPageSteps extends BaseSteps {

    @Autowired
    private PaymentSummaryPage paymentSummaryPage;

    @Then("User sees the bank wire order summary with total cost of purchase {string}")
    public void userSeesTheBankWireOrderSummaryWithTotalCostOfPurchase(String expectedTotal) {
        paymentSummaryPage.assertBankWirePaymentText();

        var actualTotal = paymentSummaryPage.getAmount().getText();
        assertEquals(expectedTotal, actualTotal);
    }
}
