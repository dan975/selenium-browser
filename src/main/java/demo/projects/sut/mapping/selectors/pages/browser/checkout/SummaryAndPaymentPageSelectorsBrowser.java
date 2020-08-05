package demo.projects.sut.mapping.selectors.pages.browser.checkout;

import demo.projects.sut.mapping.selectors.pages.base.checkout.SummaryAndPaymentPageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class SummaryAndPaymentPageSelectorsBrowser extends SummaryAndPaymentPageSelectors {
    public SummaryAndPaymentPageSelectorsBrowser() {
        this.cartItem = By.className("cart_item");
        this.totalProducts = By.id("total_product");
        this.totalShipping = By.id("total_shipping");
        this.totalCostWithoutTax = By.id("total_price_without_tax");
        this.totalTax = By.id("total_tax");
        this.totalCost = By.id("total_price");
        this.bankWire = By.cssSelector("a.bankwire");
        this.check = By.cssSelector("a.cheque");
    }
}
