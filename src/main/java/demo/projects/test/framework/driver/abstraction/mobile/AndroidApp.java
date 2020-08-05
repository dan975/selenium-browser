package demo.projects.test.framework.driver.abstraction.mobile;

import demo.projects.test.framework.annotations.ThreadComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Profile;

/**
 * Android application driver wrapper.
 *
 * Demo note:
 * Simulating Product mobile app client testing.
 * In actuality testing Google Chrome browser app.
 */
@ThreadComponent
@Profile("androidApp")
public class AndroidApp extends Android {
    private static final By URL_BAR = By.id("com.android.chrome:id/url_bar");

    @Override
    protected DesiredCapabilities getFinalDesiredCapabilities() {
        var capabilities = getSharedCapabilities();

        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");

        return capabilities;
    }

    @Override
    public String getCurrentUrl() {
        return waitUntilVisible(URL_BAR).getText();
    }
}
