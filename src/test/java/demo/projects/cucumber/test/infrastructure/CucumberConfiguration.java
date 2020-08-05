package demo.projects.cucumber.test.infrastructure;

import demo.projects.AppConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Cucumber with Spring configuration.
 */
@CucumberContextConfiguration
@ContextConfiguration
@SpringBootTest(classes = AppConfig.class)
public class CucumberConfiguration {
}
