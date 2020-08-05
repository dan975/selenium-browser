package demo.projects.cucumber.step.def.checkout;

import demo.projects.cucumber.step.def.BaseSteps;
import demo.projects.cucumber.test.infrastructure.world.WorldProductInCart;
import demo.projects.sut.mapping.element.wrappers.cart.items.CheckoutCartItemSummary;
import demo.projects.sut.mapping.pages.checkout.SummaryPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

@Slf4j
@SuppressWarnings("checkstyle:MagicNumber")
public class SummaryPageSteps extends BaseSteps {

    @Autowired
    private SummaryPage summaryPage;

    @Then("User sees all expected cart items in summary page with expected cost totals")
    public void userSeesAllExpectedCartItemsInSummaryPageWithExpectedCostTotals() {
        var groupedProductsInCart = getWorld().getGroupedProductsInCart();
        var cartItems = summaryPage.getCartItems();
        assertEquals(groupedProductsInCart.size(), cartItems.size());

        for (WorldProductInCart worldProductInCart : groupedProductsInCart) {
            log.info("Asserting expected product in cart details: {}", worldProductInCart);
            CheckoutCartItemSummary matchingCartItem = null;
            for (CheckoutCartItemSummary item : cartItems) {
                if (item.isItemOfProductContent(worldProductInCart.getProductContent())) {
                    matchingCartItem = item;
                    break;
                }
            }

            if (matchingCartItem == null) {
                throw new RuntimeException("Did not find matching cart item");
            }

            int actualQuantity = Integer.parseInt(matchingCartItem.getQuantityInput().getAttribute("value"));
            var actualPrice = matchingCartItem.getTotalPrice().getText();
            log.info("Matching actual product in cart quantity: {}, price: {}", actualQuantity, actualPrice);

            int expectedQuantity = worldProductInCart.getQuantity();
            assertEquals(expectedQuantity, actualQuantity);

            var expectedPrice = worldProductInCart.getExpectedPriceInCents() / 100.0;
            assertEquals(expectedPrice, Double.parseDouble(actualPrice.substring(1)), 0.001);
        }

        summaryPage.assertPartialPricingSummary(getWorld().getSumOfProductsInCartInCents());
    }

    @Then("User sees following products in checkout in summary page")
    public void userSeesFollowingProductsInCheckoutInSummaryPage(DataTable table) {
        var cartItems = summaryPage.getCartItems().listIterator();
        table.cells()
                .stream()
                .skip(1)
                .forEach(expectedCartItem -> {
                    var actualCartItem = cartItems.next();

                    assertEquals(expectedCartItem.get(0), actualCartItem.getTitle().getText());
                    assertEquals(expectedCartItem.get(1), actualCartItem.getQuantityInput().getAttribute("value"));
                    assertEquals(expectedCartItem.get(2), actualCartItem.getTotalPrice().getText());
                });
    }

    @Then("User sees purchase totals in summary page")
    public void userSeesPurchaseTotalsInSummaryPage(DataTable table) {
        var expectedPrices = table.cells().get(1);
        assertEquals(expectedPrices.get(0), summaryPage.getProductCostTotal().getText());
        assertEquals(expectedPrices.get(1), summaryPage.getShippingCostTotal().getText());
        assertEquals(expectedPrices.get(2), summaryPage.getTotalCostWithoutTax().getText());
        assertEquals(expectedPrices.get(3), summaryPage.getTotalTax().getText());
        assertEquals(expectedPrices.get(4), summaryPage.getTotalCost().getText());
    }
}
