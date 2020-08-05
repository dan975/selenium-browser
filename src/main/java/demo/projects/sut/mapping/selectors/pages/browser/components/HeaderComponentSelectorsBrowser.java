package demo.projects.sut.mapping.selectors.pages.browser.components;

import demo.projects.sut.mapping.selectors.pages.base.components.HeaderComponentSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class HeaderComponentSelectorsBrowser extends HeaderComponentSelectors {
    public HeaderComponentSelectorsBrowser() {
        this.signInBtn = By.className("login");
        this.signOutBtn = By.className("logout");
        this.signedInUserName = By.cssSelector(".account span");
        this.productTab = By.cssSelector("#block_top_menu > ul > li");
        this.searchBox = By.id("search_query_top");
        this.searchBtn = By.cssSelector("#searchbox button");
    }
}
