package demo.projects.sut.mapping.pages;

import demo.projects.sut.mapping.element.wrappers.ProductCard;
import demo.projects.sut.mapping.selectors.pages.base.HomePageSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Home page object.
 * First page user sees when going to main SUT website.
 */
@ThreadComponent
public class HomePage extends BasePageComponent {

    @Autowired
    private HomePageSelectors homePageSelectors;

    /**
     * Returns top static ads.
     * Ads present at the top of the interface that are next to ad slider.
     * @return Top static ads.
     */
    public List<WebElementWrapper> getTopStaticAds() {
        getDriverWrapper().waitUntilPresent(homePageSelectors.getTopStaticAd());
        return getDriverWrapper().getDriver()
                .findElements(homePageSelectors.getTopStaticAd())
                .stream()
                .map(e -> getWebElementWrapperFactory().getWebElementWrapper(e))
                .collect(Collectors.toList());
    }

    /**
     * @return Static ads below displayed product cards.
     */
    public List<WebElementWrapper> getBottomStaticAds() {
        return getDriverWrapper().waitUntilFirstVisibleAndFindAll(homePageSelectors.getBottomStaticAd());
    }

    /**
     * Method asserts that currently loaded page is the homepage.
     */
    public void assertHomepageLoaded() {
        getPopularTabElem();
    }

    /**
     * At the time of writing only used for Google Chrome automation
     * to circumvent issues with mouse hover for image comparison.
     * @return Displayed product card container element.
     */
    public WebElementWrapper getProductContainer() {
        return getDriverWrapper().waitUntilPresent(homePageSelectors.getProductContainer());
    }

    /**
     * @return All displayed product cards below popular, best sellers tabs.
     */
    public List<ProductCard> getAllDisplayedProducts() {
        var elementWrappers = getDriverWrapper().waitUntilFirstVisibleAndFindAll(homePageSelectors.getProduct());
        return elementWrappers.stream()
                .map(e -> getElementFactory().getProductCard(e))
                .collect(Collectors.toList());
    }

    /**
     * @return Popular tab element.
     */
    public WebElementWrapper getPopularTabElem() {
        return getDriverWrapper().waitUntilVisible(homePageSelectors.getPopularTab());
    }

    /**
     * @return Best sellers tab element.
     */
    public WebElementWrapper getBestSellersTabElem() {
        return getDriverWrapper().waitUntilVisible(homePageSelectors.getBestSellersTab());
    }
}
