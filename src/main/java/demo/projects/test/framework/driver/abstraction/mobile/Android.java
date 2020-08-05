package demo.projects.test.framework.driver.abstraction.mobile;

import demo.projects.test.framework.driver.abstraction.DriverWrapper;
import io.appium.java_client.android.AndroidDriver;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Abstract Appium's Android driver wrapper.
 */
abstract class Android extends DriverWrapper {
    protected abstract DesiredCapabilities getFinalDesiredCapabilities();

    protected DesiredCapabilities getSharedCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Test Device");
        capabilities.setCapability("automationName", "UiAutomator2");

        return capabilities;
    }

    @SneakyThrows
    @Override
    public void initDriverHelper() {
        var capabilities = getFinalDesiredCapabilities();
        setDriver(new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities));
    }
}
