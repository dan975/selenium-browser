package demo.projects.sut.mapping.pages;

import demo.projects.sut.mapping.element.wrappers.ProductCard;
import demo.projects.sut.mapping.selectors.pages.base.SearchPageSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Search page object.
 * Page displayed to user after passing input to search input field in header component.
 */
@ThreadComponent
public class SearchPage extends BasePageComponent {

    @Autowired
    private SearchPageSelectors searchPageSelectors;

    /**
     * @return Displayed product cards.
     */
    public List<ProductCard> getProducts() {
        return getDriverWrapper().waitUntilFirstVisibleAndFindAll(searchPageSelectors.getProduct())
                .stream()
                .map(e -> getElementFactory().getProductCard(e))
                .collect(Collectors.toList());
    }
}
