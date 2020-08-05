package demo.projects.cucumber.step.def.checkout;

import demo.projects.cucumber.step.def.BaseSteps;
import demo.projects.sut.mapping.pages.checkout.AddressPage;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressPageSteps extends BaseSteps {

    @Autowired
    private AddressPage addressPage;

    @Then("User sees correct address details for main address in checkout address page")
    public void userSeesCorrectAddressDetailsForMainAddressInCheckoutAddressPage() {
        addressPage.assertDeliveryDetails(getMainAccount());
    }
}
