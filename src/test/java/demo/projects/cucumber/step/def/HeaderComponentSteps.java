package demo.projects.cucumber.step.def;

import demo.projects.sut.mapping.pages.components.HeaderComponent;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static demo.projects.Constants.ONE_SECOND_IN_MILLISECONDS;
import static org.junit.Assert.assertEquals;

public class HeaderComponentSteps extends BaseSteps {

    @Autowired
    private HeaderComponent headerComponent;

    @When("User clicks website logo")
    public void userClicksWebsiteLogo() {
        headerComponent.getLogo().click();
    }

    @When("User searches for products containing {string}")
    public void userSearchesForProductsContaining(String input) {
        headerComponent.getSearchBoxElem().sendKeys(input);
        headerComponent.getSearchBtn().click();
    }

    @Given("User is signed out")
    public void userIsSignedOut() {
        try {
            headerComponent.getSignOutBtn().clickByActionsClass();
        } catch (org.openqa.selenium.TimeoutException ignored) { }

        headerComponent.getSignInBtn();
    }

    @When("User clicks sign in button")
    public void userClicksSignInButton() {
        headerComponent.getSignInBtn().clickByActionsClass();
    }

    @Then("User is signed in")
    public void userIsSignedIn() {
        headerComponent.getSignOutBtn();
    }

    @And("User sees his full name next to sing out button")
    public void userSeesHisFullNameNextToSingOutButton() {
        var expectedFullName = getWorld().getCurrentlyTestedUserAccount().getFullName();
        var actualFullName = headerComponent.getSignedInUserName().getText();

        assertEquals(expectedFullName, actualFullName);
    }

    @When("User clicks t shirts under women tab in header")
    public void userClicksTShirtsUnderWomenTabInHeader() throws InterruptedException {
        var womenProductTab = headerComponent.getWomenProductTab();
        womenProductTab.moveToTabAndWaitForMenuToExpand();
        womenProductTab.getTopsLinkList().get(0).click();
        Thread.sleep(2 * ONE_SECOND_IN_MILLISECONDS);
    }

    @When("User clicks summer dresses under women tab in header")
    public void userClicksSummerDressesUnderWomenTabInHeader() throws InterruptedException {
        var womenProductTab = headerComponent.getWomenProductTab();
        womenProductTab.moveToTabAndWaitForMenuToExpand();
        womenProductTab.getDressesLinkList().get(2).click();
        Thread.sleep(2 * ONE_SECOND_IN_MILLISECONDS);
    }
}
