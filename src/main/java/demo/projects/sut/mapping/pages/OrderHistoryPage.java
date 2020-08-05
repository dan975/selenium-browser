package demo.projects.sut.mapping.pages;

import demo.projects.sut.mapping.element.wrappers.OrderHistoryEntry;
import demo.projects.sut.mapping.selectors.pages.base.OrderHistoryPageSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Order history page object.
 * Page user sees after clicking "Order History and Details" button in {@link AccountManagementPage}.
 */
@ThreadComponent
public class OrderHistoryPage extends BasePageComponent {

    @Autowired
    private OrderHistoryPageSelectors orderHistoryPageSelectors;

    /**
     * @return Past order row elements.
     */
    public List<OrderHistoryEntry> getPastOrders() {
        return getDriverWrapper().waitUntilFirstVisibleAndFindAll(orderHistoryPageSelectors.getOrderEntry())
                .stream()
                .map(e -> getElementFactory().getOrderHistoryEntry(e))
                .collect(Collectors.toList());
    }
}
