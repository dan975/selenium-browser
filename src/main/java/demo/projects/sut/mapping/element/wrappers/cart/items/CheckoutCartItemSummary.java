package demo.projects.sut.mapping.element.wrappers.cart.items;

import demo.projects.test.framework.driver.abstraction.WebElementWrapper;

/**
 * Summary page cart item table row wrapper element.
 */
public class CheckoutCartItemSummary extends CheckoutCartItem {

    /**
     * @param containerElem Summary page cart item table row element.
     */
    public CheckoutCartItemSummary(WebElementWrapper containerElem) {
        super(containerElem);
    }

    /**
     * @return Quantity input element.
     */
    public WebElementWrapper getQuantityInput() {
        return getContainerElem().findElement(getCartItemSelectors().getQuantityInput());
    }

    /**
     * @return Delete button.
     */
    public WebElementWrapper getDeleteBtn() {
        return getContainerElem().findElement(getCartItemSelectors().getDeleteBtn());
    }
}
