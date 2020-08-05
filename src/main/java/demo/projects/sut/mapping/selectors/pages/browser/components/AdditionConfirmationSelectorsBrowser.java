package demo.projects.sut.mapping.selectors.pages.browser.components;

import demo.projects.sut.mapping.selectors.pages.base.components.AdditionConfirmationSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class AdditionConfirmationSelectorsBrowser extends AdditionConfirmationSelectors {
    public AdditionConfirmationSelectorsBrowser() {
        this.quantity = By.cssSelector(".ajax_cart_product_txt_s .ajax_cart_quantity");
        this.productTotalsValue = By.className("ajax_block_products_total");
        this.shippingTotalValue = By.className("ajax_cart_shipping_cost");
        this.totalCostValue = By.className("ajax_block_cart_total");
        this.image = By.cssSelector("img.layer_cart_img");

        this.continueBtn = By.className("continue");
        this.proceedBtn = By.cssSelector(".button-container .button-medium");
    }
}
