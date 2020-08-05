package demo.projects.sut.mapping.pages.checkout;

import demo.projects.sut.mapping.pages.BasePageComponent;
import demo.projects.sut.mapping.selectors.pages.base.checkout.AddressPageSelectors;
import demo.projects.sut.mapping.test.data.UserAccount;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.Assert.assertEquals;

/**
 * 03 Checkout page for choosing address page object.
 */
@ThreadComponent
public class AddressPage extends BasePageComponent {

    @Autowired
    private AddressPageSelectors addressPageSelectors;

    /**
     * Expected Address detail title.
     */
    @Value("${addressDetailsTitle}")
    private String addressDetailsTitle;

    /**
     * Method asserts default delivery details are set to user account's main address and
     * that title of address is as defined in {@link AddressPage#addressDetailsTitle}.
     * @param userAccount User account with expected main address details.
     */
    public void assertDeliveryDetails(UserAccount userAccount) {
        var expectedDeliveryDetails = new StringBuilder();

        expectedDeliveryDetails.append(addressDetailsTitle);
        expectedDeliveryDetails.append("\n");

        var expectedFullName = userAccount.getAddressFirstName() + " " + userAccount.getAddressLastName();
        expectedDeliveryDetails.append(expectedFullName);
        expectedDeliveryDetails.append("\n");

        if (!userAccount.getCompany().isEmpty()) {
            expectedDeliveryDetails.append(userAccount.getCompany());
            expectedDeliveryDetails.append("\n");
        }

        var expectedAddress = userAccount.getAddress()
                + (userAccount.getAddress2().isEmpty() ? ""
                : " " + userAccount.getAddress2());
        expectedDeliveryDetails.append(expectedAddress);
        expectedDeliveryDetails.append("\n");

        var expectedCityStateZip = String.format("%s, %s %s",
                userAccount.getCity(),
                userAccount.getState(),
                userAccount.getZip());
        expectedDeliveryDetails.append(expectedCityStateZip);
        expectedDeliveryDetails.append("\n");

        expectedDeliveryDetails.append(userAccount.getCountry());
        expectedDeliveryDetails.append("\n");

        expectedDeliveryDetails.append(userAccount.getHomePhone());

        if (!userAccount.getMobilePhone().isEmpty()) {
            expectedDeliveryDetails.append("\n");
            expectedDeliveryDetails.append(userAccount.getMobilePhone());
        }

        var expectedDeliveryDetailsFinal = expectedDeliveryDetails.toString();
        var actualDeliveryDetails = getDeliveryDetails().getText();
        actualDeliveryDetails = actualDeliveryDetails.substring(0, actualDeliveryDetails.lastIndexOf('\n'));

        assertEquals(expectedDeliveryDetailsFinal, actualDeliveryDetails);
    }

    private WebElementWrapper getDeliveryDetails() {
        return getDriverWrapper().waitUntilVisible(addressPageSelectors.getDeliveryDetails());
    }
}
