package demo.projects.cucumber.test.infrastructure.hooks;

import demo.projects.cucumber.test.infrastructure.world.World;
import demo.projects.sut.mapping.pages.ProductQuickViewPage;
import demo.projects.test.framework.driver.abstraction.DriverWrapper;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Concrete {@link IHookDefinition} implementation.
 */
@Slf4j
@Component
@Scope("prototype")
public class HookDefinition implements IHookDefinition {

    @Autowired
    private DriverWrapper driverWrapper;

    /**
     * Product Quick view page.
     * Present to reset focused on quick view frame flag after each test case.
     */
    @Autowired
    private ProductQuickViewPage productQuickViewPage;

    @Autowired
    private World world;

    private final AtomicInteger screenshotCounter = new AtomicInteger(0);

    @Override
    public void beforeAll() {
        log.info("Executing before all hook");
        driverWrapper.initDriver();
        driverWrapper.loadInitialSUTPage();
    }

    @Override
    public void beforeEach(Scenario scenario) {
        log.info("Executing before each hook for scenario: {}", scenario.getName());
        driverWrapper.loadInitialSUTPage();
        driverWrapper.getDriver().manage().window().maximize();
    }

    @Override
    public void afterEach(Scenario scenario) {
        log.info("Executing after each hook for scenario: {}", scenario.getName());
        productQuickViewPage.setFocusedOnQuickViewFrame(false);
        world.reset();
    }

    @SneakyThrows
    @Override
    public void afterEachOnFailure(Scenario scenario) {
        log.info("Executing after each on failure hook for scenario: {}", scenario.getName());
        String screenshotPath = "target/screenshots/failed_"
                + scenario.getName().replaceAll("\\s+", "_")
                + screenshotCounter.getAndIncrement();
        driverWrapper.getScreenshot(screenshotPath);

        attachScreenshotToReport(screenshotPath);
    }

    /**
     * Method attaches screenshot to Allure report.
     * @param screenshotPath Path to screenshot file without file extension.
     */
    @SneakyThrows
    private void attachScreenshotToReport(String screenshotPath) {
        Path content = Paths.get(screenshotPath + ".png");

        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Last SUT state", is);
        }
    }

    @Override
    public void afterEachOnSuccess(Scenario scenario) {
        log.info("Executing after each on success hook for scenario: {}", scenario.getName());
    }

    @Override
    public void afterAll() {
        log.info("Executing after all hook");
        driverWrapper.getDriver().quit();
    }
}
