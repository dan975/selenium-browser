package demo.projects.sut.mapping.selectors.pages.base.checkout;

import demo.projects.sut.mapping.selectors.BaseSelectors;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public abstract class AddressPageSelectors extends BaseSelectors {
    protected By deliveryDetails;
}
