package demo.projects.sut.mapping.selectors.pages.browser;

import demo.projects.sut.mapping.selectors.pages.base.OrderHistoryPageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class OrderHistoryPageSelectorsBrowser extends OrderHistoryPageSelectors {

    public OrderHistoryPageSelectorsBrowser() {
        this.orderEntry = By.cssSelector("tbody tr");
    }
}
