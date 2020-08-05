package demo.projects.cucumber.test.infrastructure.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class executing hooks defined in {@link IHookDefinition} injected implementation.
 */
public class HookRunner {

    /**
     * Set of initialized thread ids that have the "before all" hook executed
     * and "after all" hook added on JVM shutdown.
     */
    private static final Set<Long> INITIALIZED_THREADS = Collections.synchronizedSet(new HashSet<>());

    /**
     * Object defining the execution hook methods.
     */
    @Autowired
    private IHookDefinition hookDefinition;

    /**
     * Cucumber before hook, executes before all, before each, adds after all hook on JVM shutdown.
     * @param scenario Cucumber Scenario object injected by Cucumber.
     */
    @Before
    public void before(Scenario scenario) {
        long id = Thread.currentThread().getId();
        if (!INITIALIZED_THREADS.contains(id)) {
            hookDefinition.beforeAll();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> hookDefinition.afterAll()));

            INITIALIZED_THREADS.add(id);
        }

        hookDefinition.beforeEach(scenario);
    }

    /**
     * Cucumber after hook, executes after each on failure, after each on success, after each hooks.
     * @param scenario Cucumber Scenario injected by Cucumber.
     */
    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            hookDefinition.afterEachOnFailure(scenario);
        } else {
            hookDefinition.afterEachOnSuccess(scenario);
        }

        hookDefinition.afterEach(scenario);
    }
}
