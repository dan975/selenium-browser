package demo.projects.sut.mapping.pages.checkout;

import demo.projects.sut.mapping.pages.BasePageComponent;
import demo.projects.sut.mapping.selectors.pages.base.checkout.ShippingPageSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 04 Checkout page for choosing shipping and Terms Of Service confirmation.
 */
@ThreadComponent
public class ShippingPage extends BasePageComponent {

    @Autowired
    private ShippingPageSelectors shippingPageSelectors;

    /**
     * @return Terms Of Service checkbox.
     */
    public WebElementWrapper getTosCheckBox() {
        return getDriverWrapper().waitUntilPresent(shippingPageSelectors.getTosCheckBox());
    }
}
