package demo.projects.sut.mapping.selectors.pages.base;

import demo.projects.sut.mapping.selectors.BaseSelectors;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public abstract class AuthenticationPageSelectors extends BaseSelectors {
    protected By newUserEmailInput;
    protected By createAnAccountBtn;
    protected By registeredUserEmailInput;
    protected By passwordInput;
    protected By signInBtn;
}
