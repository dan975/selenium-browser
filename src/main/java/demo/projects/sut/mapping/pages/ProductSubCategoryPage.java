package demo.projects.sut.mapping.pages;

import demo.projects.sut.mapping.element.wrappers.ProductCard;
import demo.projects.sut.mapping.selectors.pages.base.ProductSubCategoryPageSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Product sub category page.
 * Page displayed to user after clicking on different product category tabs in header component.
 */
@ThreadComponent
public class ProductSubCategoryPage extends BasePageComponent {

    @Autowired
    private ProductSubCategoryPageSelectors productSubCategoryPageSelectors;

    /**
     * @return Displayed product category product cards.
     */
    public List<ProductCard> getProducts() {
        return getDriverWrapper().waitUntilFirstVisibleAndFindAll(productSubCategoryPageSelectors.getProduct())
                .stream()
                .map(e -> getElementFactory().getProductCard(e))
                .collect(Collectors.toList());
    }
}
