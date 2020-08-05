package demo.projects.cucumber;

import demo.projects.AppConfig;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test runner for Cucumber tests.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        strict = true,
        features = "classpath:cucumber/automation/practice/",
        glue = {"demo.projects.cucumber.step.def", "demo.projects.cucumber.test.infrastructure"})
@SpringBootTest(classes = AppConfig.class)
public class CucumberTest {
}
