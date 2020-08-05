package demo.projects.sut.mapping.pages;

import demo.projects.sut.mapping.selectors.pages.base.AuthenticationPageSelectors;
import demo.projects.test.framework.annotations.ThreadComponent;
import demo.projects.test.framework.driver.abstraction.WebElementWrapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Authentication page object.
 * Page displayed to user when user clicks "Sign in" button in header component.
 */
@ThreadComponent
public class AuthenticationPage extends BasePageComponent {

    @Autowired
    private AuthenticationPageSelectors authenticationPageSelectors;

    /**
     * @return Email address for registration input element.
     */
    public WebElementWrapper getNewUserEmailInput() {
        return getDriverWrapper().waitUntilVisible(authenticationPageSelectors.getNewUserEmailInput());
    }

    /**
     * @return Create an account button.
     */
    public WebElementWrapper getCreateAnAccountBtn() {
        return getDriverWrapper().waitUntilVisible(authenticationPageSelectors.getCreateAnAccountBtn());
    }

    /**
     * @return Registered user's sign in email input element.
     */
    public WebElementWrapper getRegisteredUserEmailInput() {
        return getDriverWrapper().waitUntilVisible(authenticationPageSelectors.getRegisteredUserEmailInput());
    }

    /**
     * @return Registered user's sign in password input element.
     */
    public WebElementWrapper getPasswordInput() {
        return getDriverWrapper().waitUntilVisible(authenticationPageSelectors.getPasswordInput());
    }

    /**
     * @return Sign in button.
     */
    public WebElementWrapper getSignInBtn() {
        return getDriverWrapper().waitUntilVisible(authenticationPageSelectors.getSignInBtn());
    }
}
