package demo.projects.cucumber.step.def;

import demo.projects.sut.mapping.pages.ProductQuickViewPage;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static demo.projects.Constants.ONE_SECOND_IN_MILLISECONDS;
import static org.junit.Assert.assertEquals;

@Slf4j
public class ProductQuickViewPageSteps extends BaseSteps {

    @Autowired
    private ProductQuickViewPage productQuickViewPage;

    private String selectedColor;

    @Then("User sees the quick view open for a product")
    public void userSeesTheQuickViewOpenForAProduct() {
        productQuickViewPage.waitUntilQuickViewFinishedLoading();
        productQuickViewPage.getPrimaryImage();
    }

    @And("User sees correct price, title, description for current product in Quick View")
    public void userSeesCorrectPriceTitleDescriptionForCurrentProductInQuickView() {
        var productContent = getWorld().getCurrentlyTestedProductContent();
        productQuickViewPage.assertTextContentIsAsExpected(productContent);
    }

    @And("User sees all the product images displayed full size when moving mouse over each thumbnail")
    @SuppressWarnings("checkstyle:LineLength")
    public void userSeesAllTheProductImagesDisplayedFullSizeWhenMovingMouseOverEachThumbnail() throws InterruptedException {
        var productContent = getWorld().getCurrentlyTestedProductContent();
        for (WebElementWrapper thumbnail : productQuickViewPage.getThumbnails()) {
            thumbnail.moveToElement();
            Thread.sleep(2 * ONE_SECOND_IN_MILLISECONDS);

            var thumbnailId = thumbnail.getAttribute("id");
            log.info("Asserting primary image is as expected for image with thumbnail id {}", thumbnailId);

            var expectedImage = productContent.getProductImageByThumbnailId(thumbnailId).getImage();
            var imageThreshold = getImageComparisonThresholds().getProductLargeImage();
            productQuickViewPage.getPrimaryImage().compareImage(expectedImage, imageThreshold);
        }
    }

    @When("User picks a random color of the product in quick view")
    public void userPicksARandomColorOfTheProductInQuickView() {
        var colorOptions = productQuickViewPage.getColorOptions();
        int randomColorOptionIndex = getRandom().nextInt(colorOptions.size());
        WebElementWrapper colorOption = colorOptions.get(randomColorOptionIndex);
        colorOption.click();

        String colorOptionName = colorOption.getAttribute("Name");
        log.info("Storing selected color option: {}", colorOptionName);
        selectedColor = colorOptionName;
    }

    @SneakyThrows
    @Then("User sees only images for the selected color in quick view")
    public void userSeesOnlyImagesForTheSelectedColorInQuickView() {
        var productContent = getWorld().getCurrentlyTestedProductContent();
        var imagesForColorSelected = productContent.getImagesForColor(selectedColor);

        var thumbnails = productQuickViewPage.getThumbnails();
        assertEquals(imagesForColorSelected.length, thumbnails.size());

        var comparisonThreshold = getImageComparisonThresholds().getProductLargeImage();
        for (var productImage : imagesForColorSelected) {
            productImage.getMatchingThumbnail(thumbnails).moveToElement();
            Thread.sleep(ONE_SECOND_IN_MILLISECONDS);

            productQuickViewPage.getPrimaryImage()
                    .compareImage(productImage.getImage(), comparisonThreshold);
        }
    }

    @When("User adds random quantity in range {int} to {int} of item to cart")
    public void userAddsRandomQuantityInRangeToOfItemToCart(int start, int end) {
        var productContent = getWorld().getCurrentlyTestedProductContent();

        int quantity = start + getRandom().nextInt(end - start + 1);
        var quantityInput = productQuickViewPage.getQuantityInput();
        quantityInput.getRawElement().clear();
        quantityInput.sendKeys(quantity + "");
        productQuickViewPage.clickAddToCartBtn();

        getWorld().addToProductsInCart(productContent, quantity);
    }
}
