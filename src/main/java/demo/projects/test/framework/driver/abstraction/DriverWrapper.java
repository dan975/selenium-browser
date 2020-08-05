package demo.projects.test.framework.driver.abstraction;

import demo.projects.test.framework.enums.DriverType;
import demo.projects.test.framework.exceptions.ElementPresentWhenNotExpectedException;
import demo.projects.test.framework.helpers.file.util.FileUtilHelper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract Wrapper class for Selenium driver.
 * To be extended by other classes implementing concrete drivers.
 */
public abstract class DriverWrapper {

    /**
     * Initial SUT Web page URL.
     */
    @Value("${initialBrowserPage}")
    private String initialBrowserPage;

    @Autowired
    private WebElementWrapperFactory webElementWrapperFactory;

    @Getter
    @Autowired
    private DriverType driverType;

    /**
     * Selenium driver.
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    @SuppressWarnings("checkstyle:VisibilityModifier")
    private WebDriver driver;

    /**
     * Single Actions class instance to be used for GUI interactions.
     */
    @Getter
    private Actions actions;

    /**
     * Default {@link WebDriverWait}.
     */
    @Getter
    @SuppressWarnings("checkstyle:VisibilityModifier")
    private WebDriverWait defaultWait;

    /**
     * Integer value of {@link DriverWrapper#defaultWait} timeout in seconds.
     * Can be used in other calls for default timeout, where {@link WebDriverWait} is not used.
     */
    @Getter
    @Value("${defaultWait}")
    @SuppressWarnings("checkstyle:VisibilityModifier")
    protected int defaultWaitValue;

    /**
     * Selenium driver wrapper initialization method.
     * Starts Selenium driver, sets {@link DriverWrapper#driver} field and other fields requiring Selenium driver.
     */
    public void initDriver() {
        initDriverHelper();

        this.defaultWait = new WebDriverWait(driver, defaultWaitValue);
        this.actions = new Actions(driver);
    }

    /**
     * Initializes concrete Selenium driver.
     */
    protected abstract void initDriverHelper();

    /**
     * Convenience method for {@link DriverWrapper#waitUntilVisible(By, WebDriverWait)}.
     * Uses {@link DriverWrapper#defaultWait}.
     * @param selector Selenium selector for element.
     * @return Wrapped Selenium element.
     */
    public WebElementWrapper waitUntilVisible(By selector) {
        return waitUntilVisible(selector, defaultWait);
    }

    /**
     * Waits until element identified by passed selector becomes visible and returns element.
     * @param selector Selenium selector for element.
     * @param wait Driver wait defining driver call timeout and sleep between calls.
     * @return Wrapped Selenium element.
     */
    public WebElementWrapper waitUntilVisible(By selector, WebDriverWait wait) {
        var rawElement = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return webElementWrapperFactory.getWebElementWrapper(rawElement);
    }

    /**
     * Convenience method for {@link DriverWrapper#waitUntilPresent(By, WebDriverWait)}.
     * @param selector Selenium selector for element.
     * @return Wrapped Selenium element.
     */
    public WebElementWrapper waitUntilPresent(By selector) {
        return waitUntilPresent(selector, defaultWait);
    }

    /**
     * Wait until element identified by passed selector is present on the page's DOM and return element.
     * @param selector Selenium selector for element.
     * @param wait Driver wait defining driver call timeout and sleep between calls.
     * @return Wrapped Selenium element.
     */
    public WebElementWrapper waitUntilPresent(By selector, WebDriverWait wait) {
        var rawElement = wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        return webElementWrapperFactory.getWebElementWrapper(rawElement);
    }

    /**
     * Convenience method for {@link DriverWrapper#waitUntilFirstVisibleAndFindAll(By, WebDriverWait)}.
     * Uses {@link DriverWrapper#defaultWait}.
     * @param selector Selenium selector for element.
     * @return Wrapped Selenium elements.
     */
    public List<WebElementWrapper> waitUntilFirstVisibleAndFindAll(By selector) {
        return waitUntilFirstVisibleAndFindAll(selector, defaultWait);
    }

    /**
     * Method waits until first element identified by passed selector becomes visible
     * and then finds all objects identified by that selector.
     * @param selector Selenium selector for element.
     * @param wait Driver wait defining driver call timeout and sleep between calls.
     * @return Wrapped Selenium elements.
     */
    public List<WebElementWrapper> waitUntilFirstVisibleAndFindAll(By selector, WebDriverWait wait) {
        waitUntilVisible(selector, wait);
        List<WebElement> rawElements = driver.findElements(selector);

        return wrapElements(rawElements);
    }

    /**
     * Wrap list of Selenium elements with {@link WebElementWrapper}.
     * @param rawElements Raw Selenium elements.
     * @return Wrapped selenium elements.
     */
    private List<WebElementWrapper> wrapElements(List<WebElement> rawElements) {
        return rawElements.stream()
                .map(rawElement -> webElementWrapperFactory.getWebElementWrapper(rawElement))
                .collect(Collectors.toList());
    }

    /**
     * Method creates directories for screenshot directory defined in filePathToSaveScreenshotTo,
     * if they don't exist. Then takes a screenshot and saves it under the passed file path.
     * @param filePathToSaveScreenshotTo Path of file to save the screenshot.
     */
    public void getScreenshot(String filePathToSaveScreenshotTo) {
        FileUtilHelper.createDirectoriesForFile(filePathToSaveScreenshotTo);
        getScreenshotHelper(filePathToSaveScreenshotTo);
    }

    /**
     * Method takes screenshot and saves under passed file path.
     * @param filePathToSaveScreenshotTo Path under which to save the screenshot.
     */
    @SneakyThrows
    private void getScreenshotHelper(String filePathToSaveScreenshotTo) {
        File fileToSaveTo = new File(filePathToSaveScreenshotTo + ".png");

        File screenshotAsFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(screenshotAsFile, fileToSaveTo);
    }

    /**
     * Load initial SUT page.
     */
    public void loadInitialSUTPage() {
        driver.get(initialBrowserPage);
    }

    /**
     * Assert that element identified by passed selector is not present.
     * @param selector Selenium selector for element.
     */
    public void assertElementNotPresent(By selector) {
        try {
            driver.findElement(selector);

            throw new ElementPresentWhenNotExpectedException();
        } catch (NoSuchElementException ignored) { }
    }

    /**
     * @return Current URL displayed in URL bar of browser client.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Convenience method for {@link DriverWrapper#waitForUrlToContainExpectedText(String, WebDriverWait)}.
     * @param expectedUrlContent Expected String to be displayed in URL bar.
     */
    public void waitForUrlToContainExpectedText(String expectedUrlContent) {
        waitForUrlToContainExpectedText(expectedUrlContent, defaultWait);
    }

    /**
     * Wait until the URL in the URL bar contains the passed expected URL content.
     * @param expectedUrlContent Expected String to be displayed in URL bar.
     * @param wait Driver wait defining driver call timeout and sleep between calls.
     */
    public void waitForUrlToContainExpectedText(String expectedUrlContent, WebDriverWait wait) {
        wait.until(new ExpectedCondition<Boolean>() {
            private String lastURl;

            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver driverIgnored) {
                this.lastURl = getCurrentUrl();
                return lastURl.contains(expectedUrlContent);
            }

            @Override
            public String toString() {
                var baseMessage = "current URL to contain text:\"%s\", last URL:\"%s\"";
                return String.format(baseMessage, expectedUrlContent, lastURl);
            }
        });
    }

    /**
     * @return True - if current driver wrapper instance is of type {@link Browser}.
     * False - if current driver wrapper instance is not of type {@link Browser}.
     */
    public boolean isDesktopBrowser() {
        return driverType.equals(DriverType.DESKTOP_BROWSER);
    }

    /**
     * @return True - if current driver wrapper instance is of type
     * {@link demo.projects.test.framework.driver.abstraction.mobile.AndroidApp}.
     * False - if current driver wrapper instance is not of type
     * {@link demo.projects.test.framework.driver.abstraction.mobile.AndroidApp}.
     */
    public boolean isAndroidApp() {
        return driverType.equals(DriverType.ANDROID_APP);
    }

    /**
     * @return True - if current driver wrapper instance is of type
     * {@link demo.projects.test.framework.driver.abstraction.mobile.AndroidBrowser}.
     * False - if current driver wrapper instance is not of type
     * {@link demo.projects.test.framework.driver.abstraction.mobile.AndroidBrowser}.
     */
    public boolean isAndroidBrowser() {
        return driverType.equals(DriverType.ANDROID_BROWSER);
    }
}
