package demo.projects.sut.mapping.selectors.pages.mobile.checkout;

import demo.projects.sut.mapping.selectors.pages.base.checkout.ShippingPageSelectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("androidApp")
public class ShippingPageSelectorsMobile extends ShippingPageSelectors {
}
