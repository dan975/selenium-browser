package demo.projects.sut.mapping.pages.components;

import demo.projects.sut.mapping.element.wrappers.WomenProductTab;
import demo.projects.sut.mapping.pages.BasePageComponent;
import demo.projects.sut.mapping.selectors.pages.base.components.HeaderComponentSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static demo.projects.Constants.ONE_SECOND_IN_MILLISECONDS;

/**
 * SUT Page header component.
 * Containing search box, sign in, sign out button and other header elements.
 */
@ThreadComponent
public class HeaderComponent extends BasePageComponent {

    /**
     * Expected site logo image.
     */
    @Value("${siteLogoImage}")
    private String siteLogoImageFilePath;

    @Autowired
    private HeaderComponentSelectors headerComponentSelectors;

    /**
     * @return Sign in button.
     */
    @SneakyThrows
    public WebElementWrapper getSignInBtn() {
        //Clicking sign out, sign in buttons is flaky could implement Robot class for clicking different elements
        // and shell scripts for focusing the browser window when executing in parallel.
        var res = getDriverWrapper().waitUntilVisible(headerComponentSelectors.getSignInBtn());
        Thread.sleep(ONE_SECOND_IN_MILLISECONDS);
        return res;
    }

    /**
     * @return Sign out button.
     */
    @SneakyThrows
    public WebElementWrapper getSignOutBtn() {
        var res = getDriverWrapper().waitUntilVisible(headerComponentSelectors.getSignOutBtn());
        Thread.sleep(ONE_SECOND_IN_MILLISECONDS);
        return res;
    }

    /**
     * @return Signed in user's full name element.
     */
    public WebElementWrapper getSignedInUserName() {
        return getDriverWrapper().waitUntilVisible(headerComponentSelectors.getSignedInUserName());
    }

    /**
     * @return Site logo element.
     */
    public WebElementWrapper getLogo() {
        return getDriverWrapper().waitUntilVisible(headerComponentSelectors.getLogo());
    }

    /**
     * Assert site logo is as expected.
     */
    public void assertLogoImageIsAsExpected() {
        var expectedLogoImage = getImageLoader().getImage(siteLogoImageFilePath).getBufferedImage();
        var imageThreshold = getImageComparisonThresholds().getSiteLogo();

        getLogo().compareImage(expectedLogoImage, imageThreshold);
    }

    /**
     * @return Search box element.
     */
    public WebElementWrapper getSearchBoxElem() {
        return getDriverWrapper().waitUntilVisible(headerComponentSelectors.getSearchBox());
    }

    /**
     * Method returns search button. Can search by appending "Enter" key to end of search box input as well.
     * @return Search button.
     */
    public WebElementWrapper getSearchBtn() {
        return getDriverWrapper().waitUntilVisible(headerComponentSelectors.getSearchBtn());
    }

    /**
     * @return Women tab element.
     */
    public WomenProductTab getWomenProductTab() {
        return getElementFactory().getWomenProductTab(getProductTabs().get(0));
    }

    private List<WebElementWrapper> getProductTabs() {
        return getDriverWrapper().waitUntilFirstVisibleAndFindAll(headerComponentSelectors.getProductTab());
    }
}
