package demo.projects.sut.mapping.selectors.pages.browser.checkout;

import demo.projects.sut.mapping.selectors.pages.base.checkout.AddressPageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class AddressPageSelectorsBrowser extends AddressPageSelectors {
    public AddressPageSelectorsBrowser() {
        this.deliveryDetails = By.id("address_delivery");
    }
}
