package demo.projects.sut.mapping.selectors.pages.base.checkout;

import demo.projects.sut.mapping.selectors.BaseSelectors;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public abstract class PaymentSummaryPageSelectors extends BaseSelectors {
    protected By paymentTypeTitle;
    protected By paymentAmount;
    protected By paymentCurrency;
}
