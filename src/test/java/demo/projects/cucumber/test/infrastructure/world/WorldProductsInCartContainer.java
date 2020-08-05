package demo.projects.cucumber.test.infrastructure.world;

import demo.projects.sut.mapping.test.data.ProductContent;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * User's Products in cart selection for current test scenario.
 *
 * Object does not take into account different item size, color selection.
 */
@Slf4j
public class WorldProductsInCartContainer {
    private final List<WorldProductInCart> container = new ArrayList<>();

    /**
     * Add new product content with selected quantity.
     * @param worldProductInCart Product content with selected quantity added to cart.
     */
    public void add(WorldProductInCart worldProductInCart) {
        log.info("Adding to products in cart, quantity: {}, product content: {}",
                worldProductInCart.getQuantity(), worldProductInCart.getProductContent());
        container.add(worldProductInCart);
    }

    /**
     * Removes all entries of product content from container.
     * @param productContent Product to remove product content.
     */
    public void remove(ProductContent productContent) {
        log.info("Removing from products in cart all entries of product content {}", productContent);
        boolean isProductPresent;
        do {
            isProductPresent = false;

            for (WorldProductInCart worldProductInCart : container) {
                if (worldProductInCart.getProductContent().equals(productContent)) {
                    log.info("Removing product in cart, {}", worldProductInCart);
                    container.remove(worldProductInCart);

                    isProductPresent = true;
                    break;
                }
            }
        } while (isProductPresent);
    }

    /**
     * @return Total count of products in cart.
     */
    public int getCountOfProducts() {
        int res = 0;
        for (WorldProductInCart worldProductInCart : container) {
            res += worldProductInCart.getQuantity();
        }

        log.info("Getting count of products {}", res);
        return res;
    }

    /**
     * @return Sum of purchase in cents.
     */
    public int getSumOfPurchaseInCents() {
        int res = 0;
        for (WorldProductInCart worldProductInCart : container) {
            int productPriceInCents;
            if (worldProductInCart.getProductContent().isProductOnSale()) {
                productPriceInCents = worldProductInCart.getProductContent().getSalePriceInCents();
            } else {
                productPriceInCents = worldProductInCart.getProductContent().getRegularPriceInCents();
            }

            int sumOfCartAddition = productPriceInCents * worldProductInCart.getQuantity();
            res += sumOfCartAddition;
        }

        log.info("Getting sum of purchase in cents: {}", res);
        return res;
    }

    /**
     * Iterates over products in cart stored in container and returns unique cart items
     * with sum quantity of all cart items that are matching the same cart item.
     * @return List of unique cart item selection with quantity for each selection.
     */
    public List<WorldProductInCart> getGroupedProducts() {
        List<WorldProductInCart> res = new ArrayList<>();
        for (WorldProductInCart worldProductInCart : container) {
            var productInCartList = getProductInCartList(res, worldProductInCart.getProductContent());

            if (productInCartList != null) {
                productInCartList.setQuantity(productInCartList.getQuantity() + worldProductInCart.getQuantity());
            } else {
                var productContent = worldProductInCart.getProductContent();
                var quantity = worldProductInCart.getQuantity();
                res.add(new WorldProductInCart(productContent, quantity));
            }
        }

        log.info("Getting grouped products: {}", res);
        return res;
    }

    @SuppressWarnings("checkstyle:LineLength")
    private WorldProductInCart getProductInCartList(List<WorldProductInCart> productsInCart, ProductContent productContent) {
        for (WorldProductInCart worldProductInCart : productsInCart) {
            if (worldProductInCart.getProductContent().equals(productContent)) {
                return worldProductInCart;
            }
        }

        return null;
    }

    /**
     * Clear all products in container.
     */
    public void clear() {
        container.clear();
    }
}
