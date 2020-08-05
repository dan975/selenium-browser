package demo.projects.sut.mapping.selectors.element.wrappers;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public abstract class CartItemSelectors {
    protected By title;
    protected By quantityInPayment;
    protected By quantityInput;
    protected By totalPrice;
    protected By deleteBtn;
}
