package demo.projects.sut.mapping.element.wrappers;

import demo.projects.sut.mapping.selectors.element.wrappers.OrderHistoryEntrySelectors;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Order history page's order table row wrapper element.
 */
public class OrderHistoryEntry {

    @Autowired
    private OrderHistoryEntrySelectors orderHistoryEntrySelectors;

    @Getter
    private final WebElementWrapper containerElem;

    /**
     * @param containerElem Order history table row element.
     */
    public OrderHistoryEntry(WebElementWrapper containerElem) {
        this.containerElem = containerElem;
    }

    /**
     * @return Invoice download link.
     */
    public WebElementWrapper getInvoiceDownloadLink() {
        return containerElem.findElement(orderHistoryEntrySelectors.getInvoiceDownloadLink());
    }
}
