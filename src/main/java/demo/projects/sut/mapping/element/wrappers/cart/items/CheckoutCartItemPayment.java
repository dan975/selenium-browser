package demo.projects.sut.mapping.element.wrappers.cart.items;

import demo.projects.test.framework.driver.abstraction.WebElementWrapper;

/**
 * Payment page cart item table row wrapper element.
 */
public class CheckoutCartItemPayment extends CheckoutCartItem {

    /**
     * @param containerElem Payment page cart item table row element.
     */
    public CheckoutCartItemPayment(WebElementWrapper containerElem) {
        super(containerElem);
    }

    /**
     * @return Quantity element.
     */
    public WebElementWrapper getQuantity() {
        return getContainerElem().findElement(getCartItemSelectors().getQuantityInPayment());
    }
}
