package demo.projects.cucumber.step.def.mixed;

import demo.projects.cucumber.step.def.BaseSteps;
import demo.projects.sut.mapping.pages.AccountManagementPage;
import demo.projects.sut.mapping.pages.AuthenticationPage;
import demo.projects.sut.mapping.pages.components.HeaderComponent;
import demo.projects.sut.mapping.test.data.UserAccount;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MixedPageSteps extends BaseSteps {

    @Autowired
    private HeaderComponent headerComponent;

    @Autowired
    private AuthenticationPage authenticationPage;

    @Autowired
    private AccountManagementPage accountManagementPage;

    @Given("User is signed in with main account")
    public void userIsSignedInWithMainAccount() {
        signOutAndSignIn(getMainAccount());
        getWorld().setCurrentlyTestedUserAccount(getMainAccount());
    }

    @Given("User is signed in with invoice account")
    public void userIsSignedInWithInvoiceAccount() {
        signOutAndSignIn(getInvoiceAccount());
        getWorld().setCurrentlyTestedUserAccount(getInvoiceAccount());
    }

    private void signOutAndSignIn(UserAccount userAccount) {
        try {
            headerComponent.getSignOutBtn().click();
        } catch (Throwable ignored) { }

        headerComponent.getSignInBtn().click();

        authenticationPage.getRegisteredUserEmailInput().sendKeys(userAccount.getEmail());
        authenticationPage.getPasswordInput().sendKeys(userAccount.getPassword());
        authenticationPage.getSignInBtn().click();
    }

    @When("User goes to Order history page")
    public void userGoesToOrderHistoryPage() {
        headerComponent.getSignedInUserName().click();
        accountManagementPage.getOrderHistoryBtn().click();
    }
}
