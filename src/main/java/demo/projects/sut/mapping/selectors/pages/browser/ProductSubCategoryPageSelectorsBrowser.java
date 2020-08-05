package demo.projects.sut.mapping.selectors.pages.browser;

import demo.projects.sut.mapping.selectors.pages.base.ProductSubCategoryPageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class ProductSubCategoryPageSelectorsBrowser extends ProductSubCategoryPageSelectors {

    public ProductSubCategoryPageSelectorsBrowser() {
        this.product = By.cssSelector(".product_list > li");
    }
}
