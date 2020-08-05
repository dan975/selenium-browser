package demo.projects.sut.mapping.selectors.by.factory;

import org.openqa.selenium.By;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * By factory for desktop and mobile browser clients.
 */
@Configuration
@Profile({"desktopBrowser", "androidBrowser"})
public class ByFactoryBrowser extends ByFactory {

    @Override
    public By getById(String id) {
        return By.id(id);
    }
}
