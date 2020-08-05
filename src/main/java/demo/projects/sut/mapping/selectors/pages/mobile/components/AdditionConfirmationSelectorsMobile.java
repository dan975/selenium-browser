package demo.projects.sut.mapping.selectors.pages.mobile.components;

import demo.projects.sut.mapping.selectors.pages.base.components.AdditionConfirmationSelectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("androidApp")
public class AdditionConfirmationSelectorsMobile extends AdditionConfirmationSelectors {
}
