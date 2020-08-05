package demo.projects.sut.mapping.selectors.pages.browser.checkout;

import demo.projects.sut.mapping.selectors.pages.base.checkout.PaymentSummaryPageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class PaymentSummaryPageSelectorsBrowser extends PaymentSummaryPageSelectors {

    public PaymentSummaryPageSelectorsBrowser() {
        this.paymentTypeTitle = By.className("page-subheading");
        this.paymentAmount = By.id("amount");
        this.paymentCurrency = By.cssSelector(".box.cheque-box b");
    }
}
