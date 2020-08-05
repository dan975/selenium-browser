package demo.projects.sut.mapping.selectors.pages.mobile.components;

import demo.projects.sut.mapping.selectors.pages.base.components.HeaderComponentSelectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("androidApp")
public class HeaderComponentSelectorsMobile extends HeaderComponentSelectors {
}
