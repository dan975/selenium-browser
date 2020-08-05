package demo.projects.sut.mapping.element.wrappers;

import demo.projects.sut.mapping.selectors.element.wrappers.ProductSelectors;
import demo.projects.sut.mapping.test.data.ProductContent;
import demo.projects.sut.mapping.test.data.supporting.ImageComparisonThresholds;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * Product card wrapper element.
 */
@Slf4j
public class ProductCard {
    private static final String ON_SALE_CONTENT_PRICE_TEXT_BASE = "$%.2f $%.2f -%d%%";
    private static final String REGULAR_CONTENT_PRICE_TEXT_BASE = "$%.2f";

    @Autowired
    private ImageComparisonThresholds imageComparisonThresholds;

    @Autowired
    private ProductSelectors productSelectors;

    @Getter
    private final WebElementWrapper containerElem;

    /**
     * @param containerElem Product card container element.
     */
    ProductCard(WebElementWrapper containerElem) {
        this.containerElem = containerElem;
    }

    /**
     * Return price content element.
     * If product has discount expected element text
     * to contain sale, regular prices and discount percentage.
     * @return Price content element.
     */
    public WebElementWrapper getPriceElem() {
        return containerElem.findElement(productSelectors.getPrice());
    }

    /**
     * @return Product title element.
     */
    public WebElementWrapper getTitleElem() {
        return containerElem.findElement(productSelectors.getTitle());
    }

    /**
     * @return Add to cart button.
     */
    public WebElementWrapper getAddToCartBtn() {
        return containerElem.findElement(productSelectors.getAddToCartBtn());
    }

    /**
     * @return More button.
     */
    public WebElementWrapper getMoreBtnElem() {
        return containerElem.findElement(productSelectors.getMoreBtn());
    }

    /**
     * Compares product image with main expected product image.
     * @param productContent Expected product content.
         * @return comparison score, if images matched.
     */
    public double compareImage(ProductContent productContent) {
        log.info("Comparing image for product content {}", productContent);
        var expectedMainImage = productContent.getMainImage().getImage();
        var comparisonThreshold = imageComparisonThresholds.getProductLargeImage();
        return getProductImageElem().compareImage(expectedMainImage, comparisonThreshold);
    }

    /**
     * @return Product image element.
     */
    public WebElementWrapper getProductImageElem() {
        return containerElem.findElement(productSelectors.getProductImg());
    }

    /**
     * @return Quick view button.
     */
    public WebElementWrapper getQuickViewBtn() {
        return containerElem.waitUntilVisible(productSelectors.getQuickViewBtn());
    }

    /**
     * Asserts title and price content of product card.
     * @param productContent Expected product content.
     */
    public void assertTextContentAsExpected(ProductContent productContent) {
        log.info("asserting text content for product content {}", productContent);
        assertTextContentAsExpected(
                productContent.getTitle(),
                getExpectedContentPriceText(productContent));
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

        return String.format(ON_SALE_CONTENT_PRICE_TEXT_BASE, salePrice, regularPrice, salePercentage);
    }

    private String getRegularContentPriceText(ProductContent productContent) {
        double regularPrice = productContent.getRegularPrice();

        return String.format(REGULAR_CONTENT_PRICE_TEXT_BASE, regularPrice);
    }

    /**
     * Asserts title and price content of product card.
     * @param expectedTitle Expected product title.
     * @param expectedPriceContent Expected price content.
     */
    public void assertTextContentAsExpected(String expectedTitle, String expectedPriceContent) {
        assertPriceIsAsExpected(expectedPriceContent);
        assertTitleIsAsExpected(expectedTitle);
    }

    private void assertPriceIsAsExpected(String expectedPriceContent) {
        var actualPriceContent = getPriceElem().getText();
        assertEquals(expectedPriceContent, actualPriceContent);
    }

    private void assertTitleIsAsExpected(String expectedTitle) {
        var actualTitle = getTitleElem().getText();
        assertEquals(expectedTitle, actualTitle);
    }
}
