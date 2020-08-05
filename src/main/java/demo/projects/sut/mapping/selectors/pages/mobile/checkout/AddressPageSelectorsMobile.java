package demo.projects.sut.mapping.selectors.pages.mobile.checkout;

import demo.projects.sut.mapping.selectors.pages.base.checkout.AddressPageSelectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("androidApp")
public class AddressPageSelectorsMobile extends AddressPageSelectors {
}
