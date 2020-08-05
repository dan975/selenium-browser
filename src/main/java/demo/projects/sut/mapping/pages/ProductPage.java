package demo.projects.sut.mapping.pages;

import demo.projects.sut.mapping.selectors.pages.base.ProductPageSelectors;
import demo.projects.sut.mapping.test.data.ProductContent;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Product page object.
 * Page displayed to user after clicking on product "More" button.
 */
@ThreadComponent
public class ProductPage extends BasePageComponent {

    @Autowired
    private ProductPageSelectors productPageSelectors;

    /**
     * Returns image displayed to user after clicking on {@link ProductPage#getPrimaryImage()} element.
     * @return Product preview image element.
     */
    public WebElementWrapper getProductImagePreview() {
        return getDriverWrapper().waitUntilVisible(productPageSelectors.getProductImagePreview());
    }

    /**
     * @return Image preview close button.
     */
    public WebElementWrapper getProductImagePreviewCloseBtn() {
        return getDriverWrapper().waitUntilVisible(productPageSelectors.getProductImagePreviewCloseBtn());
    }

    /**
     * @return Thumbnail image elements.
     */
    public List<WebElementWrapper> getThumbnails() {
        return getDriverWrapper().waitUntilFirstVisibleAndFindAll(productPageSelectors.getThumbnail());
    }

    /**
     * Method compares image currently displayed in {@link ProductPage#getPrimaryImage()} element
     * with expected product content image for passed thumbnail id.
     * <p>
     * Method meant to be used to assert correct image is displayed to user after hovering on thumbnail
     * of one of products images.
     * @param productContent Expected product content containing all loaded product images.
     * @param thumbnailId Thumbnail id of previously hovered on product's image.
     * @return Comparison score, if images matched.
     */
    public double comparePrimaryImage(ProductContent productContent, String thumbnailId) {
        var expectedImage = productContent.getProductImageByThumbnailId(thumbnailId).getImage();
        var threshold = getImageComparisonThresholds().getProductLargeImage();
        return getPrimaryImage().compareImage(expectedImage, threshold);
    }

    /**
     * Enlarged product image element displayed above all product thumbnails.
     * @return Enlarged product image element.
     */
    public WebElementWrapper getPrimaryImage() {
        return getDriverWrapper().waitUntilVisible(productPageSelectors.getPrimaryImage());
    }
}
