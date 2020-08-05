package demo.projects.test.framework.driver.abstraction;

import demo.projects.test.framework.exceptions.ElementPresentWhenNotExpectedException;
import demo.projects.test.framework.helpers.file.util.FileUtilHelper;
import demo.projects.test.framework.helpers.image.comparison.ImageComparator;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Selenium element wrapper.
 */
public class WebElementWrapper {

    @Autowired
    private DriverWrapper driverWrapper;

    @Autowired
    private ImageComparator imageComparator;

    @Autowired
    private WebElementWrapperFactory webElementWrapperFactory;

    /**
     * Raw Selenium element.
     */
    @Getter
    private final WebElement rawElement;

    /**
     * @param rawElement Selenium {@link WebElement} returned from {@link WebDriver}.
     */
    public WebElementWrapper(WebElement rawElement) {
        this.rawElement = rawElement;
    }

    /**
     * Type passed keys into element.
     * @param keys Keys to type into element.
     */
    public void sendKeys(CharSequence... keys) {
        rawElement.sendKeys(keys);
    }

    /**
     * Click element.
     */
    public void click() {
        rawElement.click();
    }

    /**
     * Click element explicitly by {@link JavascriptExecutor}.
     */
    public void clickByJSExecutor() {
        var jsExecutor = (JavascriptExecutor) driverWrapper.getDriver();
        jsExecutor.executeScript("arguments[0].click();", rawElement);
    }

    /**
     * Click element explicitly by Actions class.
     */
    public void clickByActionsClass() {
        scrollIntoView();
        driverWrapper.getActions().click(rawElement)
                .perform();
    }

    /**
     * Move mouse cursor to element starting from element's top left corner plus the passed coordinates.
     * @param xOffset X axis offset from element's top left corner.
     * @param yOffset Y axis offset from element's top left corner.
     */
    public void moveToElement(int xOffset, int yOffset) {
        scrollIntoView();
        driverWrapper.getActions().moveToElement(rawElement, xOffset, yOffset)
                .perform();
    }

    /**
     * Move mouse cursor to center of element.
     */
    public void moveToElement() {
        scrollIntoView();
        driverWrapper.getActions().moveToElement(rawElement)
                .perform();
    }

    /**
     * Scroll element into view.
     */
    public void scrollIntoView() {
        String jsScript = "arguments[0].scrollIntoView(true);";
        ((JavascriptExecutor) driverWrapper.getDriver()).executeScript(jsScript, rawElement);
    }

    /**
     * Get text of element.
     * @return Text of element.
     */
    public String getText() {
        return rawElement.getText();
    }

    /**
     * Return element's attribute value.
     * @param attribute Element's attribute.
     * @return Attribute value.
     */
    public String getAttribute(String attribute) {
        return rawElement.getAttribute(attribute);
    }

    /**
     * Assert child element identified by passed selector is not present in this element.
     * @param selector Selenium selector for child element.
     */
    public void assertChildElementNotPresent(By selector) {
        try {
            findElement(selector);

            throw new ElementPresentWhenNotExpectedException();
        } catch (NoSuchElementException ignored) { }
    }

    /**
     * Compare image of element with expected image.
     * @param baseImage Expected image.
     * @param comparisonThreshold Image threshold to pass to {@link ImageComparator}, which if met
     *                            asserts that displayed image is as expected.
     * @return Score of image comparison, if images matched.
     */
    public double compareImage(BufferedImage baseImage, double comparisonThreshold) {
        BufferedImage targetImage = getElementScreenshotAsBufferedImage();
        return imageComparator.compareImages(baseImage, targetImage, comparisonThreshold);
    }

    @SneakyThrows
    private BufferedImage getElementScreenshotAsBufferedImage() {
        if (driverWrapper.isDesktopBrowser()) {
            scrollIntoView();
        }

        return ImageIO.read(rawElement.getScreenshotAs(OutputType.FILE));
    }

    /**
     * Takes screenshot of element and stores it in the passed file path.
     * If directories for file do not exist, creates them.
     * @param filePathToSaveScreenshotTo File path for storing image.
     */
    public void getScreenshot(String filePathToSaveScreenshotTo) {
        FileUtilHelper.createDirectoriesForFile(filePathToSaveScreenshotTo);
        getScreenshotHelper(filePathToSaveScreenshotTo);
    }

    @SneakyThrows
    private void getScreenshotHelper(String filePathToSaveScreenshotTo) {
        File fileToSaveTo = new File(filePathToSaveScreenshotTo + ".png");

        File screenshotAsFile = rawElement.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotAsFile, fileToSaveTo);
    }

    /**
     * Find child element for passed selector.
     * @param selector Child element selector relative to current element.
     * @return Child element.
     */
    public WebElementWrapper findElement(By selector) {
        WebElement childElem = rawElement.findElement(selector);
        return webElementWrapperFactory.getWebElementWrapper(childElem);
    }

    /**
     * Find child elements for passed selector.
     * @param selector Child element selector relative to current element.
     * @return All elements matching passed selector.
     */
    public List<WebElementWrapper> findElements(By selector) {
        return rawElement.findElements(selector)
                .stream()
                .map(e -> webElementWrapperFactory.getWebElementWrapper(e))
                .collect(Collectors.toList());
    }

    /**
     * Convenience method for {@link WebElementWrapper#waitUntilVisible(By, WebDriverWait)}.
     * @param childElemSelector Child selector relative to current element.
     * @return Child element.
     */
    public WebElementWrapper waitUntilVisible(By childElemSelector) {
        return waitUntilVisible(childElemSelector, driverWrapper.getDefaultWait());
    }

    /**
     * Wait until child element identified by passed selector becomes visible.
     * @param childElemSelector Child selector relative to current element.
     * @param wait Driver wait defining driver call timeout and sleep between calls.
     * @return Child element.
     */
    public WebElementWrapper waitUntilVisible(By childElemSelector, WebDriverWait wait) {
        var childElement = wait.until(new ExpectedCondition<WebElement>() {
            @NullableDecl
            @Override
            public WebElement apply(@NullableDecl WebDriver driver) {
                WebElement childElem = rawElement.findElement(childElemSelector);
                return childElem.isDisplayed() ? childElem : null;
            }

            @Override
            public String toString() {
                return "child element to become visible";
            }
        });

        return webElementWrapperFactory.getWebElementWrapper(childElement);
    }
}
