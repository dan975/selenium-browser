package demo.projects.cucumber.step.def;

import demo.projects.sut.mapping.element.wrappers.ProductCard;
import demo.projects.sut.mapping.pages.SearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchPageSteps extends BaseSteps {

    @Autowired
    private SearchPage searchPage;

    @Then("User only sees products containing {string} in search page")
    public void userOnlySeesProductsContainingSearchInputInSearchPage(String searchInput) {
        for (ProductCard product : searchPage.getProducts()) {
            var expectedTitle = searchInput.toLowerCase();
            var actualTitle = product.getTitleElem().getText().toLowerCase();

            String baseMessage = "Expected product title to contain case insensitive text: \"%s\", "
                    + "actual title: \"%s\"";
            String assertionMessage = String.format(baseMessage, searchInput, actualTitle);

            assertTrue(assertionMessage, actualTitle.contains(expectedTitle));
        }
    }

    @And("User sees total of {int} in search page")
    public void userSeesTotalOfNumOfProductsInSearchPage(int expectedProductTotal) {
        int actualProductTotal = searchPage.getProducts().size();
        assertEquals(expectedProductTotal, actualProductTotal);
    }
}
