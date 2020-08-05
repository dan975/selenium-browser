package demo.projects.cucumber.step.def;

import demo.projects.sut.mapping.pages.HomePage;
import demo.projects.sut.mapping.test.data.Ad;
import demo.projects.sut.mapping.test.data.ProductContent;
import demo.projects.sut.mapping.test.data.supporting.TestDataKeys;
import demo.projects.test.framework.driver.abstraction.Browser;
import demo.projects.test.framework.helpers.test.data.loader.TestDataSubset;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static demo.projects.Constants.ONE_SECOND_IN_MILLISECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
public class HomePageSteps extends BaseSteps {

    @Autowired
    private HomePage homePage;

    @Given("^User is on the homepage")
    public void userIsOnTheHomepage() {
        getDriverWrapper().loadInitialSUTPage();
        homePage.assertHomepageLoaded();
    }

    @Then("^User sees all popular items with expected content and expected images$")
    public void userSeesAllPopularItemsWithExpectedContent() {
        compareDisplayedProductContentAndImages(TestDataKeys.POPULAR);
    }

    @When("User clicks popular tab")
    public void userClicksPopularTab() {
        homePage.getPopularTabElem().click();
    }

    @When("User clicks best sellers tab")
    public void userClicksBestSellersTab() {
        homePage.getBestSellersTabElem().click();
    }

    @Then("User sees all best seller items with expected content and expected images")
    public void userSeesAllBestSellerItemsWithExpectedContentAndExpectedImages() {
        compareDisplayedProductContentAndImages(TestDataKeys.BEST_SELLERS);
    }

    private void compareDisplayedProductContentAndImages(TestDataSubset<ProductContent> testDataSubset) {
        if (getDriverWrapper().isDesktopBrowser() && ((Browser) getDriverWrapper()).isChrome()) {
            //Flakiness present when taking image screenshot,
            //since images are not always fully loaded when capturing images immediately.
            //Below wait resolves the issue.
            try {
                Thread.sleep(ONE_SECOND_IN_MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        var products = homePage.getAllDisplayedProducts();
        var productContentIterator = getTestDataLoader().getTestData(testDataSubset).listIterator();

        products.forEach(product -> {
            if (getDriverWrapper().isDesktopBrowser() && ((Browser) getDriverWrapper()).isChrome()) {
                //Moving mouse outside of element to not cause screenshot capture issues by accidental hover
                homePage.getProductContainer().moveToElement(0, 0);
            }

            ProductContent productContent = productContentIterator.next();
            product.assertTextContentAsExpected(productContent);
            product.compareImage(productContent);
        });
    }

    @When("User clicks on top {int} ad")
    public void userClicksOnTopNumAd(int num) {
        homePage.getTopStaticAds().get(num).click();
    }

    @SneakyThrows
    @Then("User is redirected to correct link for top {int} ad")
    public void userIsRedirectedToCorrectLinkForTopAd(int num) {
        var ad = getTestDataLoader().getTestData(TestDataKeys.TOP_ADS, Ad.class).get(num);

        getDriverWrapper().waitForUrlToContainExpectedText(ad.getRedirectLink());
    }

    @When("User clicks on bottom {int} ad")
    public void userClicksOnBottomNumOfAdAd(int num) {
        homePage.getBottomStaticAds().get(num).click();
    }

    @Then("User is redirected to correct link for bottom {int} ad")
    public void userIsRedirectedToCorrectLinkForBottomNumOfAdAd(int num) {
        var actualURL = getDriverWrapper().getDriver().getCurrentUrl();
        var ad = getTestDataLoader().getTestData(TestDataKeys.BOTTOM_ADS, Ad.class).get(num);

        assertTrue(actualURL.contains(ad.getRedirectLink()));
    }

    @When("User moves mouse over a random popular product and clicks the quick view button")
    public void userMovesMouseOverARandomPopularProduct() {
        var popularProductContents = getTestDataLoader().getTestData(TestDataKeys.POPULAR);
        int randomIndex = getRandom().nextInt(popularProductContents.size());

        var productContent = popularProductContents.get(randomIndex);
        getWorld().setCurrentlyTestedProductContent(productContent);

        var product = homePage.getAllDisplayedProducts().get(randomIndex);
        product.getProductImageElem().moveToElement();
        product.getQuickViewBtn().click();
    }

    @When("User adds random popular product to cart")
    public void userAddsRandomPopularProductToCart() {
        var testData = getTestDataLoader().getTestData(TestDataKeys.POPULAR);
        var products = homePage.getAllDisplayedProducts();
        assertEquals(testData.size(), products.size());

        int randomIndex = getRandom().nextInt(testData.size());
        var productContent = testData.get(randomIndex);
        var product = products.get(randomIndex);

        product.getProductImageElem().moveToElement();
        product.getAddToCartBtn().click();

        getWorld().addToProductsInCart(productContent, 1);
    }

    @Then("User sees {int} top ad loaded with correct image")
    public void userSeesNumOfAdAdLoadedWithCorrectImage(int numOfAd) {
        var expectedTopAds = getTestDataLoader().getTestData(TestDataKeys.TOP_ADS, Ad.class);
        var imageThreshold = getImageComparisonThresholds().getAd();

        homePage.getTopStaticAds()
                .get(numOfAd)
                .compareImage(expectedTopAds.get(numOfAd).getImage(), imageThreshold);
    }

    @Then("User sees {int} bottom ad loaded with correct image")
    public void userSeesNumOfAdBottomAdLoadedWithCorrectImage(int numOfAd) {
        var expectedBottomAds = getTestDataLoader().getTestData(TestDataKeys.BOTTOM_ADS, Ad.class);
        var imageThreshold = getImageComparisonThresholds().getAd();
        homePage.getBottomStaticAds()
                .get(numOfAd)
                .compareImage(expectedBottomAds.get(numOfAd).getImage(), imageThreshold);
    }

    @When("User adds popular product with index {int} to cart")
    public void userAddsPopularProductWithIndexToCart(int numOfProduct) {
        homePage.getPopularTabElem().click();

        var product = homePage.getAllDisplayedProducts().get(numOfProduct);
        product.getProductImageElem().moveToElement();
        product.getAddToCartBtn().click();
    }
}
