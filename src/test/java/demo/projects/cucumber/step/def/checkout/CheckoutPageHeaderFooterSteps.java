package demo.projects.cucumber.step.def.checkout;

import demo.projects.sut.mapping.pages.BasePageComponent;
import demo.projects.sut.mapping.pages.checkout.CheckOutPageHeaderFooter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckoutPageHeaderFooterSteps extends BasePageComponent {

    @Autowired
    private CheckOutPageHeaderFooter checkOutPageHeaderFooter;

    @When("User clicks proceed to checkout button in checkout")
    public void userClicksProceedToCheckoutButtonInCheckout() {
        checkOutPageHeaderFooter.getProceedBtn().click();
    }

    @And("User clicks button to confirm order")
    public void userClicksButtonToConfirmOrder() {
        checkOutPageHeaderFooter.getProceedBtn().click();
    }

    @And("User clicks button to go to order history page after purchase")
    public void userClicksButtonToGoToOrderHistoryPageAfterPurchase() {
        checkOutPageHeaderFooter.getBackBtn().clickByJSExecutor();
    }
}
