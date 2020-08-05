package demo.projects.cucumber.step.def;

import demo.projects.sut.mapping.pages.AuthenticationPage;
import demo.projects.sut.mapping.test.data.UserAccount;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationPageSteps extends BaseSteps {

    @Autowired
    private AuthenticationPage authenticationPage;

    @Then("User is redirected to the Authentication page")
    public void userIsRedirectedToTheAuthenticationPage() {
        authenticationPage.getNewUserEmailInput();
    }

    @When("User inputs a random valid email address and clicks create account button")
    @SuppressWarnings("checkstyle:MagicNumber")
    public void userInputARandomValidEmailAddressAndClicksCreateAccountButton() {
        var randomStringGenerator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .build();
        var email = randomStringGenerator.generate(8) + "@selenium.demo";

        authenticationPage.getNewUserEmailInput().sendKeys(email);
        authenticationPage.getCreateAnAccountBtn().click();

        var userAccount = new UserAccount();
        userAccount.setEmail(email);

        getWorld().setCurrentlyTestedUserAccount(userAccount);
    }
}
