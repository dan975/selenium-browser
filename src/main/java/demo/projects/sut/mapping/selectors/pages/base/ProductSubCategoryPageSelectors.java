package demo.projects.sut.mapping.selectors.pages.base;

import demo.projects.sut.mapping.selectors.BaseSelectors;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public abstract class ProductSubCategoryPageSelectors extends BaseSelectors {
    protected By product;
}
