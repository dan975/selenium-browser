package demo.projects.cucumber.step.def.checkout;

import demo.projects.cucumber.step.def.BaseSteps;
import demo.projects.sut.mapping.pages.checkout.PaymentChoosePaymentPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("checkstyle:MagicNumber")
public class PaymentChoosePaymentPageSteps extends BaseSteps {

    @Autowired
    private PaymentChoosePaymentPage paymentChoosePaymentPage;

    @When("User chooses bank wire payment method")
    public void userChoosesBankWirePaymentMethod() {
        paymentChoosePaymentPage.getBankWireLink().click();
    }

    @Then("User sees following products in checkout in payment page")
    public void userSeesFollowingProductsInCheckoutInPaymentPage(DataTable table) {
        var cartItems = paymentChoosePaymentPage.getCartItems().listIterator();
        table.cells()
                .stream()
                .skip(1)
                .forEach(expectedCartItem -> {
                    var actualCartItem = cartItems.next();

                    assertEquals(expectedCartItem.get(0), actualCartItem.getTitle().getText());
                    assertEquals(expectedCartItem.get(1), actualCartItem.getQuantity().getText());
                    assertEquals(expectedCartItem.get(2), actualCartItem.getTotalPrice().getText());
                });
    }

    @And("User sees purchase totals in payment page")
    public void userSeesPurchaseTotalsInPaymentPage(DataTable table) {
        var expectedPrices = table.cells().get(1);
        assertEquals(expectedPrices.get(0), paymentChoosePaymentPage.getProductCostTotal().getText());
        assertEquals(expectedPrices.get(1), paymentChoosePaymentPage.getShippingCostTotal().getText());
        assertEquals(expectedPrices.get(2), paymentChoosePaymentPage.getTotalTax().getText());
        assertEquals(expectedPrices.get(3), paymentChoosePaymentPage.getTotalCost().getText());
    }
}
