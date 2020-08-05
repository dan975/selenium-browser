package demo.projects.sut.mapping.selectors.pages.browser.checkout;

import demo.projects.sut.mapping.selectors.pages.base.checkout.CheckOutPageHeaderFooterSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class CheckOutPageHeaderFooterSelectorsBrowser extends CheckOutPageHeaderFooterSelectors {

    public CheckOutPageHeaderFooterSelectorsBrowser() {
        this.orderStep = By.cssSelector("#order_step li");
        this.backBtn = By.cssSelector(".cart_navigation .button-exclusive");
        this.forwardBtn = By.cssSelector(".cart_navigation .button-medium");
    }


}
