package demo.projects.cucumber.step.def;

import demo.projects.sut.mapping.pages.ProductSubCategoryPage;
import demo.projects.sut.mapping.test.data.ProductContent;
import demo.projects.sut.mapping.test.data.supporting.TestDataKeys;
import demo.projects.test.framework.helpers.test.data.loader.TestDataSubset;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class ProductSubCategoryPageSteps extends BaseSteps {

    @Autowired
    private ProductSubCategoryPage productSubCategoryPage;

    @When("User adds random product to cart in {string} sub category page")
    public void userAddsRandomProductToCartInSubCategoryPage(String subCategory) {
        var testDataKey = getTestDataKeyForSubCategory(subCategory);

        var productContents = getTestDataLoader().getTestData(testDataKey);
        var products = productSubCategoryPage.getProducts();
        assertEquals(productContents.size(), products.size());

        int randomIndex = getRandom().nextInt(products.size());
        var productContent = productContents.get(randomIndex);
        getWorld().setCurrentlyTestedProductContent(productContent);

        var product = products.get(randomIndex);
        product.assertTextContentAsExpected(productContent);

        product.getProductImageElem().moveToElement();
        product.getAddToCartBtn().click();
        getWorld().addToProductsInCart(productContent, 1);
    }

    @When("User opens quick view for random product in {string} sub category page")
    public void userOpensQuickViewForRandomProductInSubCategoryPage(String subCategory) {
        var testDataKey = getTestDataKeyForSubCategory(subCategory);

        var productContents = getTestDataLoader().getTestData(testDataKey);
        var products = productSubCategoryPage.getProducts();
        assertEquals(productContents.size(), products.size());

        int randomIndex = getRandom().nextInt(products.size());
        var productContent = productContents.get(randomIndex);
        getWorld().setCurrentlyTestedProductContent(productContent);

        var product = products.get(randomIndex);
        product.assertTextContentAsExpected(productContent);

        product.getProductImageElem().moveToElement();
        product.getQuickViewBtn().click();
    }

    private TestDataSubset<ProductContent> getTestDataKeyForSubCategory(String subCategory) {
        switch (subCategory) {
            case "tshirt":
                return TestDataKeys.SHIRTS;
            case "casualDresses":
                return TestDataKeys.CASUAL_DRESSES;
            case "summerDresses":
                return TestDataKeys.SUMMER_DRESSES;
            default:
                throw new RuntimeException("Not implemented for subCategory: " + subCategory);
        }
    }

}
