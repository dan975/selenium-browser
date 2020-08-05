package demo.projects.sut.mapping.element.wrappers.cart.items;

import demo.projects.sut.mapping.selectors.element.wrappers.CartItemSelectors;
import demo.projects.sut.mapping.test.data.ProductContent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract Summary, Payment page cart item table row element wrapper.
 */
abstract class CheckoutCartItem {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private CartItemSelectors cartItemSelectors;

    @Getter
    private final WebElementWrapper containerElem;

    /**
     * Element wrapper for cart item rows in Checkout Summary and Payment page tables.
     * @param containerElem Row element in Checkout Summary, Payment page cart item table.
     */
    protected CheckoutCartItem(WebElementWrapper containerElem) {
        this.containerElem = containerElem;
    }

    /**
     * @return Product title element.
     */
    public WebElementWrapper getTitle() {
        return containerElem.findElement(getCartItemSelectors().getTitle());
    }

    /**
     * @return Total price element.
     */
    public WebElementWrapper getTotalPrice() {
        return containerElem.findElement(getCartItemSelectors().getTotalPrice());
    }

    /**
     * Method checks if row element id matches that of product content internal id.
     * Product content id matches cart item id if cart item's id ends with an integer
     * greater by 1 than product content's id. (i.e. For product content with internal id - 2,
     * matching cart item id is "product_3".).
     * @param productContent Product content for which to check if cart item matches.
     * @return True - if cart item row id matches product content id. False - if ids do not match.
     */
    public boolean isItemOfProductContent(ProductContent productContent) {
        String cartItemId = containerElem.getAttribute("id");
        String productContentIdPattern = "product_" + (productContent.getInternalId() + 1);

        return cartItemId.startsWith(productContentIdPattern);
    }
}
