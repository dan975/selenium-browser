package demo.projects.sut.mapping.selectors.pages.browser;

import demo.projects.sut.mapping.selectors.pages.base.ProductPageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class ProductPageSelectorsBrowser extends ProductPageSelectors {

    public ProductPageSelectorsBrowser() {
        this.quickViewFrame = By.className("fancybox-iframe");
        this.quickViewLoadingSpinner = By.id("fancybox-loading");

        this.primaryImage = By.id("bigpic");
        this.priceContent = By.className("content_prices");
        this.title = By.cssSelector(".pb-center-column h1");
        this.description = By.id("short_description_content");
        this.quantityInput = By.id("quantity_wanted");
        this.addToCartBtn = By.cssSelector("#add_to_cart button");
        this.colorOption = By.cssSelector("#color_to_pick_list a");

        this.thumbnail = By.cssSelector("#thumbs_list_frame li");
        this.closeQuickViewBtn = By.className("fancybox-close");
        this.productImagePreview = By.className("fancybox-image");
        this.productImagePreviewCloseBtn = By.className("fancybox-close");
    }
}
