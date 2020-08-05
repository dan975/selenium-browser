package demo.projects.sut.mapping.selectors.element.wrappers.browser;

import demo.projects.sut.mapping.selectors.element.wrappers.CartItemSelectors;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class CartItemSelectorsBrowser extends CartItemSelectors {

    public CartItemSelectorsBrowser() {
        this.title = By.className("product-name");
        this.quantityInPayment = By.className("cart_quantity");
        this.quantityInput = By.className("cart_quantity_input");
        this.totalPrice = By.cssSelector(".cart_total .price");
        this.deleteBtn = By.cssSelector(".cart_delete a");
    }
}
