package demo.projects.cucumber.step.def.checkout;

import demo.projects.cucumber.step.def.BaseSteps;
import demo.projects.sut.mapping.pages.checkout.ShippingPage;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

public class ShippingPageSteps extends BaseSteps {

    @Autowired
    private ShippingPage shippingPage;

    @And("User checks Terms of Service checkbox")
    public void userChecksTermsOfServiceCheckbox() {
        shippingPage.getTosCheckBox().click();
    }
}
