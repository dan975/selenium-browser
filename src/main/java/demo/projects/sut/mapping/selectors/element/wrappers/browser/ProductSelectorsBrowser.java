package demo.projects.sut.mapping.selectors.element.wrappers.browser;

import demo.projects.sut.mapping.selectors.element.wrappers.ProductSelectors;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class ProductSelectorsBrowser extends ProductSelectors {

    public ProductSelectorsBrowser() {
        this.price = By.cssSelector(".right-block .content_price");
        this.title = By.className("product-name");
        this.moreBtn = By.cssSelector(".button-container .lnk_view");
        this.addToCartBtn = By.cssSelector(".button-container .ajax_add_to_cart_button ");
        this.productImg = By.cssSelector("img");
        this.quickViewBtn = By.className("quick-view");
    }
}
