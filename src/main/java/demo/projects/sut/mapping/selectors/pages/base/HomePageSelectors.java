package demo.projects.sut.mapping.selectors.pages.base;

import demo.projects.sut.mapping.selectors.BaseSelectors;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public abstract class HomePageSelectors extends BaseSelectors {
    protected By topStaticAd;
    protected By bottomStaticAd;

    protected By productContainer;
    protected By product;
    protected By popularTab;
    protected By bestSellersTab;
}
