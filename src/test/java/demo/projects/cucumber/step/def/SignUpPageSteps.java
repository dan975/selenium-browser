package demo.projects.cucumber.step.def;

import demo.projects.sut.mapping.pages.SignUpPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.Assert.assertEquals;

public class SignUpPageSteps extends BaseSteps {

    @Autowired
    private SignUpPage signUpPage;

    @Value("${userTitles}")
    private String[] userTitles;


    @Then("User is redirected to the Sign up page")
    public void userIsRedirectedToTheSignUpPage() {
        signUpPage.getTitleRadioBtns();
    }

    @And("User sees email field filled with previously provided email")
    public void userSeesEmailFieldFilledWithPreviouslyProvidedEmail() {
        var expectedEmail = getWorld().getCurrentlyTestedUserAccount().getEmail();
        var actualEmail = signUpPage.getEmailInput().getAttribute("value");
        assertEquals(expectedEmail, actualEmail);
    }

    @When("User inputs {string} into User's first name field")
    public void userInputsFirstNameIntoUserSFirstNameField(String firstName) {
        signUpPage.getUserFirstNameInput().sendKeys(firstName);
        var userAccount = getWorld().getCurrentlyTestedUserAccount();
        userAccount.setUserFirstName(firstName);
        userAccount.setAddressFirstName(firstName);
    }

    @Then("User sees address first name field filled with previously provided user first name")
    public void userSeesAddressFirstNameFieldFilledWithPreviouslyProvidedUserFirstName() {
        var expectedFirstName = getWorld().getCurrentlyTestedUserAccount().getUserFirstName();
        var actualFirstName = signUpPage.getAddressFirstNameInput().getAttribute("value");
        assertEquals(expectedFirstName, actualFirstName);
    }

    @When("User inputs {string} into User's last name field")
    public void userInputsIntoUserSLastNameField(String lastName) {
        signUpPage.getUserLastNameInput().sendKeys(lastName);
        var userAccount = getWorld().getCurrentlyTestedUserAccount();
        userAccount.setUserLastName(lastName);
        userAccount.setAddressLastName(lastName);
    }

    @Then("User sees address last name field filled with previously provided user last name")
    public void userSeesAddressLastNameFieldFilledWithPreviouslyProvidedLastName() {
        var expectedLastName = getWorld().getCurrentlyTestedUserAccount().getUserLastName();
        var actualLastName = signUpPage.getAddressLastNameInput().getAttribute("value");
        assertEquals(expectedLastName, actualLastName);
    }

    @When("User inputs {string} into the password field in sign up page")
    public void userInputsIntoThePasswordFieldInSignUpPage(String password) {
        signUpPage.getPasswordInput().sendKeys(password);
        getWorld().getCurrentlyTestedUserAccount().setPassword(password);
    }

    @And("User fills all fields other than first, last name and password fields with random valid input")
    @SuppressWarnings("checkstyle:MagicNumber")
    public void userFillsAllFieldsOtherThanFirstLastNameAndPasswordFieldsWithRandomValidInput() {
        var userAccount = getWorld().getCurrentlyTestedUserAccount();

        int randomIndex = getRandom().nextInt(2);
        signUpPage.getTitleRadioBtns().get(randomIndex).click();
        userAccount.setTitle(userTitles[randomIndex]);

        //For generating a valid date input, should create a date object within a specified year range
        // and check if the distance to today is greater than the youngest age limit.
        var day = chooseRandomSelectOption(signUpPage.getDaySelect(), 1, -1);
        userAccount.setDayOfBirth(Integer.parseInt(day.trim()));

        var month = chooseRandomSelectOption(signUpPage.getMonthSelect(), 1, -1);
        userAccount.setMonthOfBirth(month.trim());

        var year = chooseRandomSelectOption(signUpPage.getYearSelect(), 19, -1);
        userAccount.setYearOfBirth(year.trim());

        var randomLowerCaseGenerator = new RandomStringGenerator.Builder()
                .withinRange('A', 'z')
                .filteredBy(CharacterPredicates.LETTERS)
                .build();

        var company = randomLowerCaseGenerator.generate(8);
        signUpPage.getCompanyInput().sendKeys(company);
        userAccount.setCompany(company);

        var address = randomLowerCaseGenerator.generate(8);
        signUpPage.getAddressInput().sendKeys(address);
        userAccount.setAddress(address);

        var address2 = randomLowerCaseGenerator.generate(8);
        signUpPage.getAddress2Input().sendKeys(address2);
        userAccount.setAddress2(address2);

        var city = randomLowerCaseGenerator.generate(8);
        signUpPage.getCityInput().sendKeys(city);
        userAccount.setCity(city);

        var state = chooseRandomSelectOption(signUpPage.getStateSelect(), 1, -1);
        userAccount.setState(state);

        RandomStringGenerator randomIntGenerator = new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .build();

        var zip = randomIntGenerator.generate(5);
        signUpPage.getZipInput().sendKeys(zip);
        userAccount.setZip(zip);

        var country = chooseRandomSelectOption(signUpPage.getCountrySelect(), 1, -1);
        userAccount.setCountry(country);

        var additionalDetails = randomLowerCaseGenerator.generate(100);
        signUpPage.getAdditionalInfoInput().sendKeys(additionalDetails);
        userAccount.setAdditionalDetails(additionalDetails);

        var homePhone = "+" + randomIntGenerator.generate(9);
        signUpPage.getHomePhoneInput().sendKeys(homePhone);
        userAccount.setHomePhone(homePhone);

        var mobilePhone = "+" + randomIntGenerator.generate(9);
        signUpPage.getMobilePhoneInput().sendKeys(mobilePhone);
        userAccount.setMobilePhone(mobilePhone);

        var alias = randomLowerCaseGenerator.generate(8);
        var aliasInput = signUpPage.getAddressAliasInput();
        aliasInput.getRawElement().clear();
        aliasInput.sendKeys(alias);
        userAccount.setAddressAlias(alias);
    }

    @SuppressWarnings("SameParameterValue")
    private String chooseRandomSelectOption(Select select, int startIndex, int endIndex) {
        if (endIndex == -1) {
            endIndex = select.getOptions().size();
        }

        int randomIndex = startIndex + getRandom().nextInt(endIndex - startIndex);
        select.selectByIndex(randomIndex);

        var allSelectedOptions = select.getAllSelectedOptions();
        assertEquals(1, allSelectedOptions.size());

        return allSelectedOptions.get(0).getText().trim();
    }

    @When("User clicks Register button")
    public void userClicksRegisterButton() {
        signUpPage.getRegisterBtn().click();
    }

}
