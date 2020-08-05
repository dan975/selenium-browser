package demo.projects.sut.mapping.selectors.pages.base;

import demo.projects.sut.mapping.selectors.BaseSelectors;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public abstract class SignUpPageSelectors extends BaseSelectors {
    protected By titleRadioBtn;
    protected By userFirstNameInput;
    protected By userLastNameInput;
    protected By emailInput;
    protected By password;
    protected By daysSelect;
    protected By monthsSelect;
    protected By yearsSelect;

    protected By addressFirstNameInput;
    protected By addressLastNameInput;
    protected By companyInput;
    protected By addressInput;
    protected By address2Input;
    protected By cityInput;
    protected By stateSelect;
    protected By zipInput;
    protected By countrySelect;
    protected By additionalInfoInput;
    protected By homePhoneInput;
    protected By mobilePhoneInput;
    protected By addressAliasInput;

    protected By registerBtn;
}
