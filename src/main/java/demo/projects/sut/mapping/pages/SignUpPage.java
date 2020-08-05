package demo.projects.sut.mapping.pages;

import demo.projects.sut.mapping.selectors.pages.base.SignUpPageSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Sign up page object.
 * Page displayed to user after clicking "Create an account" button in {@link AuthenticationPage}.
 */
@ThreadComponent
public class SignUpPage extends BasePageComponent {

    @Autowired
    private SignUpPageSelectors signUpPageSelectors;

    /**
     * @return User preferred title radio buttons.
     */
    public List<WebElementWrapper> getTitleRadioBtns() {
        return getDriverWrapper().waitUntilFirstVisibleAndFindAll(signUpPageSelectors.getTitleRadioBtn());
    }

    /**
     * @return User's first name input.
     */
    public WebElementWrapper getUserFirstNameInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getUserFirstNameInput());
    }

    /**
     * @return User's last name input.
     */
    public WebElementWrapper getUserLastNameInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getUserLastNameInput());
    }

    /**
     * @return Email input.
     */
    public WebElementWrapper getEmailInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getEmailInput());
    }

    /**
     * @return Password input.
     */
    public WebElementWrapper getPasswordInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getPassword());
    }

    /**
     * @return Day drop down element.
     */
    public Select getDaySelect() {
        var daysElem = getDriverWrapper().getDriver().findElement(signUpPageSelectors.getDaysSelect());
        return new Select(daysElem);
    }

    /**
     * @return Month drop down element.
     */
    public Select getMonthSelect() {
        var monthElem = getDriverWrapper().getDriver().findElement(signUpPageSelectors.getMonthsSelect());
        return new Select(monthElem);
    }

    /**
     * @return year drop down element.
     */
    public Select getYearSelect() {
        var yearElem = getDriverWrapper().getDriver().findElement(signUpPageSelectors.getYearsSelect());
        return new Select(yearElem);
    }

    /**
     * @return Address first name input.
     */
    public WebElementWrapper getAddressFirstNameInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getAddressFirstNameInput());
    }

    /**
     * @return Address last name input.
     */
    public WebElementWrapper getAddressLastNameInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getAddressLastNameInput());
    }

    /**
     * @return Company input.
     */
    public WebElementWrapper getCompanyInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getCompanyInput());
    }

    /**
     * @return First address input.
     */
    public WebElementWrapper getAddressInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getAddressInput());
    }

    /**
     * @return Second address input.
     */
    public WebElementWrapper getAddress2Input() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getAddress2Input());
    }

    /**
     * @return City input.
     */
    public WebElementWrapper getCityInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getCityInput());
    }

    /**
     * @return State drop down element.
     */
    public Select getStateSelect() {
        var stateElem = getDriverWrapper().getDriver().findElement(signUpPageSelectors.getStateSelect());
        return new Select(stateElem);
    }

    /**
     * @return Zip input.
     */
    public WebElementWrapper getZipInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getZipInput());
    }

    /**
     * @return Country drop down element.
     */
    public Select getCountrySelect() {
        var countryElem = getDriverWrapper().getDriver().findElement(signUpPageSelectors.getCountrySelect());
        return new Select(countryElem);
    }

    /**
     * @return Additional information input.
     */
    public WebElementWrapper getAdditionalInfoInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getAdditionalInfoInput());
    }

    /**
     * @return Home phone input.
     */
    public WebElementWrapper getHomePhoneInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getHomePhoneInput());
    }

    /**
     * @return Mobile phone input.
     */
    public WebElementWrapper getMobilePhoneInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getMobilePhoneInput());
    }

    /**
     * @return Address alias input.
     */
    public WebElementWrapper getAddressAliasInput() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getAddressAliasInput());
    }

    /**
     * @return Register button.
     */
    public WebElementWrapper getRegisterBtn() {
        return getDriverWrapper().waitUntilVisible(signUpPageSelectors.getRegisterBtn());
    }
}
