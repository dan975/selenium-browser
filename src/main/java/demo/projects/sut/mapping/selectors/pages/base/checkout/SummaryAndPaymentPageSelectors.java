package demo.projects.sut.mapping.selectors.pages.base.checkout;

import demo.projects.sut.mapping.selectors.BaseSelectors;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public abstract class SummaryAndPaymentPageSelectors extends BaseSelectors {
    protected By cartItem;
    protected By totalProducts;
    protected By totalShipping;
    protected By totalCostWithoutTax;
    protected By totalTax;
    protected By totalCost;
    protected By bankWire;
    protected By check;
}
