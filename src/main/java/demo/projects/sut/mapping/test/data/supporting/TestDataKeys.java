package demo.projects.sut.mapping.test.data.supporting;

import demo.projects.sut.mapping.test.data.ProductContent;
import demo.projects.sut.mapping.test.data.UserAccount;
import demo.projects.test.framework.helpers.test.data.loader.TestDataSubset;

/**
 * Class contains test data keys for retrieving test data from resources.
 */
@SuppressWarnings("checkstyle:RegexpSingleline")
public final class TestDataKeys {
    //Product Content
    private static final String PRODUCTS = "products.csv";

    public static final TestDataSubset<ProductContent> POPULAR =
            new TestDataSubset<>("productContentPopular", PRODUCTS, ProductContent.class);
    public static final TestDataSubset<ProductContent> BEST_SELLERS =
            new TestDataSubset<>("productContentBestSellers", PRODUCTS, ProductContent.class);
    public static final TestDataSubset<ProductContent> TOPS =
            new TestDataSubset<>("productContentTops", PRODUCTS, ProductContent.class);
    public static final TestDataSubset<ProductContent> SHIRTS =
            new TestDataSubset<>("productContentShirts", PRODUCTS, ProductContent.class);
    public static final TestDataSubset<ProductContent> BLOUSES =
            new TestDataSubset<>("productContentBlouses", PRODUCTS, ProductContent.class);
    public static final TestDataSubset<ProductContent> DRESSES =
            new TestDataSubset<>("productContentDresses", PRODUCTS, ProductContent.class);
    public static final TestDataSubset<ProductContent> CASUAL_DRESSES =
            new TestDataSubset<>("productContentCasualDresses", PRODUCTS, ProductContent.class);
    public static final TestDataSubset<ProductContent> EVENING_DRESSES =
            new TestDataSubset<>("productContentEveningDresses", PRODUCTS, ProductContent.class);
    public static final TestDataSubset<ProductContent> SUMMER_DRESSES =
            new TestDataSubset<>("productContentSummerDresses", PRODUCTS, ProductContent.class);

    //User Account
    private static final String USER_ACCOUNTS = "userAccount.csv";
    public static final TestDataSubset<UserAccount> MAIN_ACCOUNT =
            new TestDataSubset<>("mainAccount", USER_ACCOUNTS, UserAccount.class);
    public static final TestDataSubset<UserAccount> INVOICE_ACCOUNT =
            new TestDataSubset<>("invoiceAccount", USER_ACCOUNTS, UserAccount.class);

    //Ad
    public static final String TOP_ADS = "topAdds.csv";
    public static final String BOTTOM_ADS = "bottomAdds.csv";

    private TestDataKeys() {
    }
}
