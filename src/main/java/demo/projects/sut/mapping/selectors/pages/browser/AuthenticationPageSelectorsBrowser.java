package demo.projects.sut.mapping.selectors.pages.browser;

import demo.projects.sut.mapping.selectors.pages.base.AuthenticationPageSelectors;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"desktopBrowser", "androidBrowser"})
public class AuthenticationPageSelectorsBrowser extends AuthenticationPageSelectors {

    public AuthenticationPageSelectorsBrowser() {
        this.newUserEmailInput = By.id("email_create");
        this.createAnAccountBtn = By.id("SubmitCreate");
        this.registeredUserEmailInput = By.id("email");
        this.passwordInput = By.id("passwd");
        this.signInBtn = By.id("SubmitLogin");
    }
}
