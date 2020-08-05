package demo.projects.sut.mapping.selectors.pages.browser.checkout;

import demo.projects.sut.mapping.selectors.pages.base.checkout.ShippingPageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class ShippingPageSelectorsBrowser extends ShippingPageSelectors {
    public ShippingPageSelectorsBrowser() {
        this.tosCheckBox = By.id("cgv");
    }
}
