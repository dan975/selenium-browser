package demo.projects.sut.mapping.pages;

import demo.projects.sut.mapping.selectors.pages.base.AccountManagementPageSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Account management page object.
 * Page that's displayed to user when clicking on logged in user's full name in header component.
 */
@ThreadComponent
public class AccountManagementPage extends BasePageComponent {

    @Autowired
    private AccountManagementPageSelectors accountManagementPageSelectors;

    /**
     * @return "Order History and Details" button.
     */
    public WebElementWrapper getOrderHistoryBtn() {
        return getOptionBtns().get(0);
    }

    private List<WebElementWrapper> getOptionBtns() {
        return getDriverWrapper().waitUntilFirstVisibleAndFindAll(accountManagementPageSelectors.getAccountListItem());
    }
}
