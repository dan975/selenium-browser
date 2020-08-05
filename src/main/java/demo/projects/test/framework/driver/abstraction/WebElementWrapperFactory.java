package demo.projects.test.framework.driver.abstraction;

import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * {@link WebElementWrapper} bean factory.
 */
@Configuration
public class WebElementWrapperFactory {

    /**
     * Returns wrapped Selenium's {@link WebElement}.
     * @param rawElement Selenium's {@link WebElement}.
     * @return Wrapped element.
     */
    @Bean(autowireCandidate = false)
    @Scope("prototype")
    public WebElementWrapper getWebElementWrapper(WebElement rawElement) {
        return new WebElementWrapper(rawElement);
    }
}
