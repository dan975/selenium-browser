package demo.projects.sut.mapping.selectors;

import demo.projects.sut.mapping.selectors.by.factory.ByFactory;
import demo.projects.test.framework.enums.BrowserType;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Base selector object.
 */
public abstract class BaseSelectors {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private ByFactory byFactory;

    @Getter(AccessLevel.PROTECTED)
    @Value("${browserType}")
    private BrowserType browserType;

}
