package demo.projects.sut.mapping.selectors.element.wrappers.browser;

import demo.projects.sut.mapping.selectors.element.wrappers.WomenProductTabSelectors;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class WomenProductTabSelectorsBrowser extends WomenProductTabSelectors {

    public WomenProductTabSelectorsBrowser() {
        this.sublist = By.xpath("//a[@class='sf-with-ul']/following-sibling::ul");
        this.sublistItem = By.cssSelector("li a");
        this.ad = By.className("imgm");
    }
}
