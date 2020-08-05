package demo.projects.sut.mapping.selectors.element.wrappers.browser;

import demo.projects.sut.mapping.selectors.element.wrappers.OrderHistoryEntrySelectors;
import demo.projects.test.framework.exceptions.NotImplementedForBrowserTypeException;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OrderHistoryEntrySelectorsBrowser extends OrderHistoryEntrySelectors {

    @PostConstruct
    public void init() {
        switch (getBrowserType()) {
            case CHROME:
                this.invoiceDownloadLink = By.cssSelector(".history_invoice a");
                break;
            case FIREFOX:
                this.invoiceDownloadLink = By.className("history_invoice");
                break;
            default:
                throw new NotImplementedForBrowserTypeException(getBrowserType());
        }
    }
}
