package demo.projects.sut.mapping.selectors.pages.browser.checkout;

import demo.projects.sut.mapping.selectors.pages.base.checkout.PaymentConfirmationPageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class PaymentConfirmationPageSelectorsBrowser extends PaymentConfirmationPageSelectors {
    public PaymentConfirmationPageSelectorsBrowser() {
        this.amount = By.xpath("//span[@class='price']");
    }
}
