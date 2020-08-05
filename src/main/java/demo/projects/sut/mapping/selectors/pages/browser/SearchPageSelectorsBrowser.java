package demo.projects.sut.mapping.selectors.pages.browser;

import demo.projects.sut.mapping.selectors.pages.base.SearchPageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class SearchPageSelectorsBrowser extends SearchPageSelectors {

    public SearchPageSelectorsBrowser() {
        this.product = By.cssSelector("#center_column .product-container");
    }
}
