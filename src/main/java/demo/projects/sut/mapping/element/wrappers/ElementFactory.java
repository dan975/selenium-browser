package demo.projects.sut.mapping.element.wrappers;

import demo.projects.sut.mapping.element.wrappers.cart.items.CheckoutCartItemPayment;
import demo.projects.sut.mapping.element.wrappers.cart.items.CheckoutCartItemSummary;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Element wrapper bean factory.
 */
@Configuration
public class ElementFactory {

    /**
     * @param containerElem Product card container element.
     * @return Wrapped Product card element.
     */
    @Bean(autowireCandidate = false)
    @Scope("prototype")
    public ProductCard getProductCard(WebElementWrapper containerElem) {
        return new ProductCard(containerElem);
    }

    /**
     * @param containerElem Women tab element.
     * @return Wrapped Women tab element.
     */
    @Bean(autowireCandidate = false)
    @Scope("prototype")
    public WomenProductTab getWomenProductTab(WebElementWrapper containerElem) {
        return new WomenProductTab(containerElem);
    }

    /**
     * @param containerElem Summary page cart item table row element.
     * @return wrapped Summary page cart item table element.
     */
    @Bean(autowireCandidate = false)
    @Scope("prototype")
    public CheckoutCartItemSummary getCartItemSummary(WebElementWrapper containerElem) {
        return new CheckoutCartItemSummary(containerElem);
    }

    /**
     * @param containerElem Payment page cart item table row element.
     * @return Wrapped Payment page cart item table row element.
     */
    @Bean(autowireCandidate = false)
    @Scope("prototype")
    public CheckoutCartItemPayment getCartItemPayment(WebElementWrapper containerElem) {
        return new CheckoutCartItemPayment(containerElem);
    }

    /**
     * @param containerElem Order history table row element.
     * @return Wrapped Order history table row element.
     */
    @Bean(autowireCandidate = false)
    @Scope("prototype")
    public OrderHistoryEntry getOrderHistoryEntry(WebElementWrapper containerElem) {
        return new OrderHistoryEntry(containerElem);
    }
}
