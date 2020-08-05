package demo.projects.sut.mapping.element.wrappers;

import demo.projects.sut.mapping.selectors.element.wrappers.WomenProductTabSelectors;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Women tab in header component element wrapper.
 */
public class WomenProductTab {

    @Autowired
    private WomenProductTabSelectors womenProductTabSelectors;

    @Getter
    private final WebElementWrapper containerElem;

    /**
     * @param containerElem Women tab element.
     */
    public WomenProductTab(WebElementWrapper containerElem) {
        this.containerElem = containerElem;
    }

    /**
     * @return Tops links: T-shirts, Blouses.
     */
    public List<WebElementWrapper> getTopsLinkList() {
        return getSubLists().get(1).findElements(womenProductTabSelectors.getSublistItem());
    }

    /**
     * @return Dress links: Casual, Evening, Summer dresses.
     */
    public List<WebElementWrapper> getDressesLinkList() {
        return getSubLists().get(2).findElements(womenProductTabSelectors.getSublistItem());
    }

    private List<WebElementWrapper> getSubLists() {
        return containerElem.findElements(womenProductTabSelectors.getSublist());
    }

    /**
     * Method moves mouse cursor to Women tab element and waits until the tab's menu expands.
     */
    public void moveToTabAndWaitForMenuToExpand() {
        containerElem.moveToElement();
        containerElem.waitUntilVisible(womenProductTabSelectors.getAd());
    }

    /**
     * @return Women tab menu ad elements.
     */
    public List<WebElementWrapper> getAds() {
        return containerElem.findElements(womenProductTabSelectors.getAd());
    }
}
