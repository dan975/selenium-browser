package demo.projects.cucumber.step.def;

import demo.projects.cucumber.test.infrastructure.world.World;
import demo.projects.sut.mapping.test.data.UserAccount;
import demo.projects.sut.mapping.test.data.supporting.ImageComparisonThresholds;
import demo.projects.sut.mapping.test.data.supporting.TestDataKeys;
import demo.projects.test.framework.driver.abstraction.DriverWrapper;
import demo.projects.test.framework.helpers.test.data.loader.TestDataLoader;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * Base class for step definition.
 */
@Getter(AccessLevel.PROTECTED)
public abstract class BaseSteps {

    @Autowired
    @SuppressWarnings("checkstyle:VisibilityModifier")
    private DriverWrapper driverWrapper;

    @Autowired
    @SuppressWarnings("checkstyle:VisibilityModifier")
    private ImageComparisonThresholds imageComparisonThresholds;

    @Autowired
    @SuppressWarnings("checkstyle:VisibilityModifier")
    private TestDataLoader testDataLoader;

    @Autowired
    private Random random;

    @Autowired
    private World world;

    private UserAccount mainAccount;

    private UserAccount invoiceAccount;

    @PostConstruct
    public void init() {
        mainAccount = testDataLoader.getTestData(TestDataKeys.MAIN_ACCOUNT).get(0);
        invoiceAccount = testDataLoader.getTestData(TestDataKeys.INVOICE_ACCOUNT).get(0);
    }
}
