package demo.projects.sut.mapping.selectors.pages.mobile;

import demo.projects.sut.mapping.selectors.pages.base.HomePageSelectors;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("androidApp")
public class HomePageSelectorsMobile extends HomePageSelectors {

    public HomePageSelectorsMobile() {
        this.topStaticAd = By.xpath("//*[@resource-id='htmlcontent_top']/android.widget.ListView/android.view.View");
        this.bottomStaticAd = By.xpath("//*[@resource-id='htmlcontent_home']/android.widget.ListView/android.view.View");
        this.product = By.xpath("//*[@resource-id='homefeatured' or @resource-id='blockbestsellers']/android.view.View/android.view.View");
        this.popularTab = MobileBy.AccessibilityId("POPULAR");
        this.bestSellersTab = MobileBy.AccessibilityId("BEST SELLERS");
    }
}
