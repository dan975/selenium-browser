package demo.projects.cucumber.step.def;

import demo.projects.sut.mapping.pages.components.AdditionConfirmationPopup;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class AdditionConfirmationPopUpSteps extends BaseSteps {

    @Autowired
    private AdditionConfirmationPopup additionConfirmationPopup;

    @Then("User sees addition confirmation popup with all item quantity and sum totals")
    public void userSeesAdditionConfirmationPopupWithAllItemQuantityAndSumTotals() {
        int expectedQuantity = getWorld().getCountOfProductsInCart();

        var quantity = additionConfirmationPopup.getQuantity();
        int actualQuantity = Integer.parseInt(quantity.getAttribute("innerText"));
        assertEquals(expectedQuantity, actualQuantity);

        int expectedSumOfProductsInCents = getWorld().getSumOfProductsInCartInCents();
        additionConfirmationPopup.assertCostValuesAreAsExpected(expectedSumOfProductsInCents);
    }

    @When("User clicks continue shopping button in addition confirmation popup")
    public void userClicksContinueShoppingButtonInAdditionConfirmationPopup() {
        additionConfirmationPopup.getContinueShoppingBtn().clickByActionsClass();
    }

    @When("User clicks proceed to checkout button in addition confirmation popup")
    public void userClicksProceedToCheckoutButtonInAdditionConfirmationPopup() {
        additionConfirmationPopup.getProceedToCheckoutBtn().clickByActionsClass();
    }

    @Then("User sees addition confirmation popup with item quantity {int} and total price {string}")
    @SuppressWarnings("checkstyle:LineLength")
    public void userSeesAdditionConfirmationPopupWithItemQuantityAndTotalPrice(int expectedQuantity, String expectedTotal) {
        var actualQuantity = additionConfirmationPopup.getQuantity().getAttribute("innerText");
        assertEquals(expectedQuantity, Integer.parseInt(actualQuantity));

        var actualTotal = additionConfirmationPopup.getProductTotalsValue().getAttribute("innerText");
        assertEquals(expectedTotal, actualTotal);
    }
}
