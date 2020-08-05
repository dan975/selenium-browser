package demo.projects.sut.mapping.selectors.pages.browser;

import demo.projects.sut.mapping.selectors.pages.base.AccountManagementPageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class AccountManagementPageSelectorsBrowser extends AccountManagementPageSelectors {
    public AccountManagementPageSelectorsBrowser() {
        this.accountListItem = By.cssSelector(".myaccount-link-list li");
    }
}
