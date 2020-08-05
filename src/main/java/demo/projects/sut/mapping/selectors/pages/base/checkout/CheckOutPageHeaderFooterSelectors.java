package demo.projects.sut.mapping.selectors.pages.base.checkout;

import demo.projects.sut.mapping.selectors.BaseSelectors;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public abstract class CheckOutPageHeaderFooterSelectors extends BaseSelectors {
    protected By orderStep;
    protected By backBtn;
    protected By forwardBtn;
}
