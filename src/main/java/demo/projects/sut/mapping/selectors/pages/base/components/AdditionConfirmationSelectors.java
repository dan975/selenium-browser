package demo.projects.sut.mapping.selectors.pages.base.components;

import demo.projects.sut.mapping.selectors.BaseSelectors;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public abstract class AdditionConfirmationSelectors extends BaseSelectors {
    protected By quantity;
    protected By productTotalsValue;
    protected By shippingTotalValue;
    protected By totalCostValue;
    protected By image;

    protected By continueBtn;
    protected By proceedBtn;
}
