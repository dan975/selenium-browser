package demo.projects.sut.mapping.selectors.by.factory;

import org.openqa.selenium.By;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * By factory for Android application.
 */
@Configuration
@Profile("androidApp")
public class ByFactoryMobile extends ByFactory {

    @Override
    public By getById(String id) {
        String base = "//*[@resource-id='%s']";
        return By.xpath(String.format(base, id));
    }
}
