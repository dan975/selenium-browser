package demo.projects.cucumber.test.infrastructure.world;

import demo.projects.sut.mapping.test.data.ProductContent;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Product in cart selection object for {@link World} object.
 */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class WorldProductInCart {
    private final ProductContent productContent;

    @Setter
    private int quantity;

    /**
     * Gets expected product price from product content and multiples by quantity.
     * @return Expected cart item price for chosen quantity.
     */
    public int getExpectedPriceInCents() {
        return productContent.getPriceInCents() * quantity;
    }
}
