package demo.projects.sut.mapping.selectors.pages.mobile;

import demo.projects.sut.mapping.selectors.pages.base.OrderHistoryPageSelectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("androidApp")
public class OrderHistoryPageSelectorsMobile extends OrderHistoryPageSelectors {
}
