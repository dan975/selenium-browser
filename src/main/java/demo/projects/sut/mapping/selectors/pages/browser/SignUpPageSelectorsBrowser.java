package demo.projects.sut.mapping.selectors.pages.browser;

import demo.projects.sut.mapping.selectors.pages.base.SignUpPageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class SignUpPageSelectorsBrowser extends SignUpPageSelectors {

    public SignUpPageSelectorsBrowser() {
        this.titleRadioBtn = By.className("radio");
        this.userFirstNameInput = By.id("customer_firstname");
        this.userLastNameInput = By.id("customer_lastname");
        this.emailInput = By.id("email");
        this.password = By.id("passwd");
        this.daysSelect = By.id("days");
        this.monthsSelect = By.id("months");
        this.yearsSelect = By.id("years");

        this.addressFirstNameInput = By.id("firstname");
        this.addressLastNameInput = By.id("lastname");
        this.companyInput = By.id("company");
        this.addressInput = By.id("address1");
        this.address2Input = By.id("address2");
        this.cityInput = By.id("city");
        this.stateSelect = By.id("id_state");
        this.zipInput = By.id("postcode");
        this.countrySelect = By.id("id_country");
        this.additionalInfoInput = By.id("other");
        this.homePhoneInput = By.id("phone");
        this.mobilePhoneInput = By.id("phone_mobile");
        this.addressAliasInput = By.id("alias");

        this.registerBtn = By.id("submitAccount");
    }
}
