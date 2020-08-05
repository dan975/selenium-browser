package demo.projects.sut.mapping.pages.checkout;

import demo.projects.sut.mapping.pages.BasePageComponent;
import demo.projects.sut.mapping.selectors.pages.base.checkout.CheckOutPageHeaderFooterSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Checkout page footer and header component encapsulation.
 */
@ThreadComponent
public class CheckOutPageHeaderFooter extends BasePageComponent {

    @Autowired
    private CheckOutPageHeaderFooterSelectors checkOutPageHeaderFooterSelectors;

    /**
     * @return Order step GUI nodes displayed in top of check out pages.
     */
    public List<WebElementWrapper> getOrderSteps() {
        return getDriverWrapper().waitUntilFirstVisibleAndFindAll(checkOutPageHeaderFooterSelectors.getOrderStep());
    }

    /**
     * @return Back button in check out page. Corresponds to "Continue shopping",
     * "Other payment methods" and "Back To Orders" buttons.
     */
    @SneakyThrows
    public WebElementWrapper getBackBtn() {
        return getDriverWrapper().waitUntilVisible(checkOutPageHeaderFooterSelectors.getBackBtn());
    }

    /**
     * @return Proceed button in check out page. Corresponds to "Proceed to checkout", "I confirm my order" buttons.
     */
    public WebElementWrapper getProceedBtn() {
        return getDriverWrapper().waitUntilVisible(checkOutPageHeaderFooterSelectors.getForwardBtn());
    }

}
