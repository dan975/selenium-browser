package demo.projects.sut.mapping.pages;

import demo.projects.sut.mapping.selectors.pages.base.ProductPageSelectors;
import demo.projects.sut.mapping.test.data.ProductContent;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static demo.projects.Constants.ONE_SECOND_IN_MILLISECONDS;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.Assert.assertEquals;

/**
 * Product quick view page.
 * Pop up displayed to user when clicking "Quick view" after hovering
 * on {@link demo.projects.sut.mapping.element.wrappers.ProductCard}'s image element.
 * <p>
 * Demo note:
 * Quick view pop up is displayed in an iframe.
 * Page object implements frame switching logic, where page has a flag for checking whether current quick view's
 * iframe is focused when retrieving the page's elements and switches to "Quick view"'s iframe or default content
 * as needed.
 * <p>
 * Since this is the only GUI node encapsulating object of the SUT that requires iframe switching
 * the provided solution suffices. However in real life case a more robust and easier to maintain logic of
 * switching iframes should be implemented as follows:
 * 1. Create Enum for currently focused frame, with an enum value for each SUT iframe that requires to be
 * switched to during test run and default content.
 * 2. Encapsulate logic for switching to each frame in a single class such as
 * {@link demo.projects.test.framework.driver.abstraction.DriverWrapper} or {@link BasePageComponent}
 * and store the enum value of currently focused on iframe, default content.
 * 3. Before call to Selenium driver server to retrieve {@link WebElement} check whether correct iframe
 * or default content is focused on for WebElement retrieval call.
 * 4. In after each test case hook reset the focused view enum value
 * in object defined in step 2. to default content view.
 * <p>
 * The check and switch defined in step 3. can be implemented by adding the method call
 * to each {@link WebElement} retrieval call as is currently implemented in this "Quick View"'s page object or
 * by AOP by creating an aspect with before advices where for different page object
 * different before advice would be applied.
 */
@Slf4j
@ThreadComponent
public class ProductQuickViewPage extends BasePageComponent {
    private static final int QUICK_VIEW_LOAD_TIME_OUT_IN_SECONDS = 9;
    private static final String ON_SALE_CONTENT_PRICE_TEXT_BASE = "$%.2f\n-%d%%\n$%.2f";
    private static final String REGULAR_CONTENT_PRICE_TEXT_BASE = "$%.2f";

    @Autowired
    private ProductPageSelectors productPageSelectors;

    @Setter
    private boolean isFocusedOnQuickViewFrame = false;

    /**
     * @return Image displayed above product image thumbnails element.
     */
    public WebElementWrapper getPrimaryImage() {
        switchToQuickViewFrameIfNotFocused();
        return getDriverWrapper().waitUntilVisible(productPageSelectors.getPrimaryImage());
    }

    /**
     * @return Product title element.
     */
    public WebElementWrapper getTitle() {
        switchToQuickViewFrameIfNotFocused();
        return getDriverWrapper().waitUntilVisible(productPageSelectors.getTitle());
    }

    /**
     * Method asserts product's title, price content, description are as expected.
     *
     * Demo note:
     * Product page contains more GUI elements with product specific text contents
     * (e.g. Model name, product condition). Same as with {@link ProductPage} text content assertion is partial.
     * @param productContent Expected product content.
     */
    public void assertTextContentIsAsExpected(ProductContent productContent) {
        log.info("asserting text content for product content {}", productContent);
        var expectedTitle = productContent.getTitle();
        var actualTitle = getTitle().getText();
        assertEquals(expectedTitle, actualTitle);

        var expectedPriceContent = getExpectedContentPriceText(productContent);
        String actualPriceContent = getPriceContent().getText();
        assertEquals(expectedPriceContent, actualPriceContent);

        var expectedDescription = productContent.getDescription();
        var actualDescription = getDescription().getText();
        assertEquals(expectedDescription, actualDescription);
    }

    private String getExpectedContentPriceText(ProductContent productContent) {
        if (productContent.isProductOnSale()) {
            return getSaleContentPriceText(productContent);
        } else {
            return getRegularContentPriceText(productContent);
        }
    }

    private String getSaleContentPriceText(ProductContent productContent) {
        var regularPrice = productContent.getRegularPrice();
        var salePrice = productContent.getSalePrice();
        var salePercentage = productContent.getSalePercentage();

        return String.format(ON_SALE_CONTENT_PRICE_TEXT_BASE, salePrice, salePercentage, regularPrice);
    }

    private String getRegularContentPriceText(ProductContent productContent) {
        double regularPrice = productContent.getRegularPrice();

        return String.format(REGULAR_CONTENT_PRICE_TEXT_BASE, regularPrice);
    }

    /**
     * @return Price content element.
     */
    public WebElementWrapper getPriceContent() {
        switchToQuickViewFrameIfNotFocused();
        return getDriverWrapper().waitUntilVisible(productPageSelectors.getPriceContent());
    }

    /**
     * @return Description element.
     */
    public WebElementWrapper getDescription() {
        switchToQuickViewFrameIfNotFocused();
        return getDriverWrapper().waitUntilVisible(productPageSelectors.getDescription());
    }

    /**
     * @return Quantity input element.
     */
    public WebElementWrapper getQuantityInput() {
        switchToQuickViewFrameIfNotFocused();
        return getDriverWrapper().waitUntilVisible(productPageSelectors.getQuantityInput());
    }

    /**
     * Method clicks add to cart button.
     * <p>
     * Clicking encapsulated in separate method so as to ensure switching back to default content.
     */
    public void clickAddToCartBtn() {
        getAddToCartBtn().click();
        switchToDefaultContentIfNotFocused();
    }

    private WebElementWrapper getAddToCartBtn() {
        switchToQuickViewFrameIfNotFocused();
        return getDriverWrapper().waitUntilVisible(productPageSelectors.getAddToCartBtn());
    }

    /**
     * @return Color selection option elements.
     */
    public List<WebElementWrapper> getColorOptions() {
        switchToQuickViewFrameIfNotFocused();
        return getDriverWrapper().waitUntilFirstVisibleAndFindAll(productPageSelectors.getColorOption());
    }

    /**
     * @return Thumbnail elements.
     */
    public List<WebElementWrapper> getThumbnails() {
        switchToQuickViewFrameIfNotFocused();
        var res = getDriverWrapper().getDriver().findElements(productPageSelectors.getThumbnail());

        return res.stream()
                .filter(WebElement::isDisplayed)
                .map(e -> getWebElementWrapperFactory().getWebElementWrapper(e))
                .collect(Collectors.toList());
    }

    /**
     * @return Close quick view button.
     */
    public WebElementWrapper getCloseQuickViewBtn() {
        switchToDefaultContentIfNotFocused();
        return getDriverWrapper().waitUntilVisible(productPageSelectors.getCloseQuickViewBtn());
    }

    private void switchToDefaultContentIfNotFocused() {
        log.debug("isFocusedOnQuickViewFrame = {}.", isFocusedOnQuickViewFrame);
        if (isFocusedOnQuickViewFrame) {
            log.debug("switching frames");
            getDriverWrapper().getDriver().switchTo().defaultContent();
            log.debug("switched to default view");
            isFocusedOnQuickViewFrame = false;
        }
    }

    private void switchToQuickViewFrameIfNotFocused() {
        log.debug("isFocusedOnQuickViewFrame = {}.", isFocusedOnQuickViewFrame);
        if (!isFocusedOnQuickViewFrame) {
            log.debug("switched frames");
            switchToQuickViewFrame();

            isFocusedOnQuickViewFrame = true;
        }
    }

    @SneakyThrows
    private void switchToQuickViewFrame() {
        waitUntilQuickViewFinishedLoading();

        var quickViewFrame = getQuickViewFrame();
        getDriverWrapper().getDriver().switchTo().frame(quickViewFrame);
        Thread.sleep(ONE_SECOND_IN_MILLISECONDS);
    }

    private WebElement getQuickViewFrame() {
        return getDriverWrapper().getDriver().findElement(productPageSelectors.getQuickViewFrame());
    }

    /**
     * Method waits until quick view loading spinner disappears indicating "Quick view" pop up loaded.
     */
    public void waitUntilQuickViewFinishedLoading() {
        FluentWait<WebDriver> waitForSpinner = new FluentWait<>(getDriverWrapper().getDriver())
                .withTimeout(Duration.of(QUICK_VIEW_LOAD_TIME_OUT_IN_SECONDS, SECONDS))
                .pollingEvery(Duration.of(1, SECONDS))
                .ignoring(NoSuchElementException.class);

        By spinnerSelector = productPageSelectors.getQuickViewLoadingSpinner();
        waitForSpinner.until((ExpectedCondition<Boolean>) driver -> {
            log.trace("waiting for spinner to disappear");
            return driver.findElements(spinnerSelector).size() == 0;
        });
    }
}
