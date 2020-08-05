package demo.projects.sut.mapping.selectors.element.wrappers;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public abstract class ProductSelectors {
    protected By price;
    protected By title;
    protected By moreBtn;
    protected By addToCartBtn;
    protected By productImg;
    protected By quickViewBtn;
}
