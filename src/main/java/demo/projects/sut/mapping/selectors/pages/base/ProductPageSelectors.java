package demo.projects.sut.mapping.selectors.pages.base;

import demo.projects.sut.mapping.selectors.BaseSelectors;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * Product page selectors encapsulate both {@link demo.projects.sut.mapping.pages.ProductPage} and
 * {@link demo.projects.sut.mapping.pages.ProductQuickViewPage} selectors.
 */
@Getter
public abstract class ProductPageSelectors extends BaseSelectors {
    protected By quickViewFrame;
    protected By quickViewLoadingSpinner;

    protected By primaryImage;
    protected By title;
    protected By priceContent;
    protected By description;
    protected By quantityInput;
    protected By addToCartBtn;
    protected By colorOption;
    protected By thumbnail;
    protected By closeQuickViewBtn;
    protected By productImagePreview;
    protected By productImagePreviewCloseBtn;
}
