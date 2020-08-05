package demo.projects.sut.mapping.selectors.by.factory;

import org.openqa.selenium.By;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * Abstract by factory for getting Selenium selector for different driver wrappers.
 */
public abstract class ByFactory {

    /**
     * Method returns selector for element's "id" attribute value in browser client.
     * @param id In browser client "id" attribute value.
     * @return Driver wrapper specific selector.
     */
    @Bean
    @Scope("prototype")
    public abstract By getById(String id);
}
