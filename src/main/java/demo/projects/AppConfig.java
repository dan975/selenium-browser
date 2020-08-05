package demo.projects;

import demo.projects.test.framework.annotations.ThreadBean;
import demo.projects.test.framework.enums.DriverType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.SimpleThreadScope;

import java.util.Random;

/**
 * Spring boot configuration.
 */
@Slf4j
@SpringBootConfiguration
@PropertySources({
        @PropertySource("images/imageComparison.properties"),
        @PropertySource("textvalue.properties")
})
@ComponentScan(basePackages = {"demo.projects"})
@EnableAspectJAutoProxy
@SuppressWarnings("checkstyle:MissingJavadocMethod")
public class AppConfig {

    @Bean(name = "driverType")
    @Profile("desktopBrowser")
    public DriverType browserType() {
        return DriverType.DESKTOP_BROWSER;
    }

    @Bean(name = "driverType")
    @Profile("androidApp")
    public DriverType mobileType() {
        return DriverType.ANDROID_APP;
    }

    @Bean(name = "driverType")
    @Profile("androidBrowser")
    public DriverType mobileBrowserType() {
        return DriverType.ANDROID_BROWSER;
    }

    @Bean(name = "testDataPath")
    @Profile("dev")
    public String devTestDataPath() {
        return testDataPathHelper("dev");
    }

    @Bean(name = "testDataPath")
    @Profile("prod")
    public String prodTestDataPath() {
        return testDataPathHelper("prod");
    }

    protected String testDataPathHelper(String env) {
        return String.format("env/dependent/%s/", env);
    }

    @ThreadBean
    public Random random() {
        return new Random();
    }

    /**
     * Register {@link SimpleThreadScope}. Thread scope allows to inject driver wrapper, page object and other bean
     * instances unique for each test execution thread when executing in parallel.
     * @return BeanFactoryPostProcessor.
     */
    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return beanFactory -> beanFactory.registerScope("thread", new SimpleThreadScope());
    }
}
