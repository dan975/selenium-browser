package demo.projects.cucumber.step.def;

import io.cucumber.java.en.Given;

public class NonPageSteps extends BaseSteps {

    @Given("User browser client is maximized")
    public void userBrowserClientIsMaximized() {
        if (getDriverWrapper().isDesktopBrowser()) {
            getDriverWrapper().getDriver().manage().window().maximize();
        }
    }
}
