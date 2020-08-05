package demo.projects.test.framework.driver.abstraction.mobile;

import com.google.common.collect.ImmutableMap;
import demo.projects.test.framework.annotations.ThreadComponent;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Profile;

/**
 * Android browser driver wrapper.
 */
@ThreadComponent
@Profile("androidBrowser")
public class AndroidBrowser extends Android {

    @Override
    protected DesiredCapabilities getFinalDesiredCapabilities() {
        var capabilities = getSharedCapabilities();

        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
        capabilities.setCapability("browserName", "Chrome");

        return capabilities;
    }
}
