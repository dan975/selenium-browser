package demo.projects.cucumber.test.infrastructure.world;

import demo.projects.sut.mapping.test.data.ProductContent;
import demo.projects.sut.mapping.test.data.UserAccount;
import demo.projects.test.framework.annotations.ThreadComponent;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Cucumber world object for shared SUT, test state during test run persistence between different Cucumber steps.
 */
@Slf4j
@ThreadComponent
public class World {

    /**
     * Currently tested expected product content.
     */
    private ProductContent currentlyTestedProductContent;

    /**
     * Currently being tested user account.
     */
    private UserAccount currentlyTestedUserAccount;

    /**
     * Currently present products and their quantities in User's cart.
     */
    private final WorldProductsInCartContainer productsInCart = new WorldProductsInCartContainer();

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public ProductContent getCurrentlyTestedProductContent() {
        log.info("retrieving from world current product content {}", currentlyTestedProductContent);
        return currentlyTestedProductContent;
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void setCurrentlyTestedProductContent(ProductContent currentlyTestedProductContent) {
        log.info("setting in world current product content {}", currentlyTestedProductContent);
        this.currentlyTestedProductContent = currentlyTestedProductContent;
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public UserAccount getCurrentlyTestedUserAccount() {
        log.info("Getting from world user account: {}", currentlyTestedUserAccount);
        return currentlyTestedUserAccount;
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void setCurrentlyTestedUserAccount(UserAccount currentlyTestedUserAccount) {
        log.info("Setting in world user account: {}", currentlyTestedUserAccount);
        this.currentlyTestedUserAccount = currentlyTestedUserAccount;
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void addToProductsInCart(ProductContent productContent, int quantity) {
        productsInCart.add(new WorldProductInCart(productContent, quantity));

        log.debug("Products in cart: {}", productsInCart);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void removeFromProductsInCart(ProductContent productContent) {
        productsInCart.remove(productContent);

        log.debug("Products in cart: {}", productsInCart);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public int getCountOfProductsInCart() {
        return productsInCart.getCountOfProducts();
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public int getSumOfProductsInCartInCents() {
        return productsInCart.getSumOfPurchaseInCents();
    }

    /**
     * @return Unique cart items with expected quantity.
         */
    public List<WorldProductInCart> getGroupedProductsInCart() {
        return productsInCart.getGroupedProducts();
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void reset() {
        log.debug("Resetting world contents");
        this.currentlyTestedProductContent = null;
        this.currentlyTestedUserAccount = null;
        this.productsInCart.clear();
    }

}
