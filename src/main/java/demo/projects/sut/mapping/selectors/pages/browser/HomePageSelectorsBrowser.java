package demo.projects.sut.mapping.selectors.pages.browser;

import demo.projects.sut.mapping.selectors.pages.base.HomePageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class HomePageSelectorsBrowser extends HomePageSelectors {

    public HomePageSelectorsBrowser() {
        this.topStaticAd = By.cssSelector("#top_column img.item-img");
        this.bottomStaticAd = By.cssSelector("#center_column img.item-img");
        this.productContainer = By.cssSelector(".tab-content .active");
        this.product = By.cssSelector(".active .product-container");
        this.popularTab = By.cssSelector("a.homefeatured");
        this.bestSellersTab = By.cssSelector("a.blockbestsellers");
    }
}
