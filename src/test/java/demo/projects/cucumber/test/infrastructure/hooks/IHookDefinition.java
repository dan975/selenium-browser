package demo.projects.cucumber.test.infrastructure.hooks;

import io.cucumber.java.Scenario;

/**
 * Interface for defining test hooks.
 */
public interface IHookDefinition {

    /**
     * Before all - to be executed once before all scenarios started in current test run.
     */
    void beforeAll();

    /**
     * Before each - to be executed before each scenario.
     * @param scenario Current Cucumber scenario definition.
     */
    void beforeEach(Scenario scenario);

    /**
     * After each on failure - to be executed after a scenario, if scenario failed.
     * @param scenario Current Cucumber scenario definition.
     */
    void afterEachOnFailure(Scenario scenario);

    /**
     * After each on success - to be executed after a scenario, if scenario succeeded.
     * @param scenario Current Cucumber scenario definition.
     */
    void afterEachOnSuccess(Scenario scenario);

    /**
     * After each - to be executed after each test case and after {@link IHookDefinition#afterEachOnFailure(Scenario)}
     * or {@link IHookDefinition#afterEachOnSuccess(Scenario)} hooks have been executed.
     * @param scenario Current Cucumber scenario definition.
     */
    void afterEach(Scenario scenario);

    /**
     * After all - to be executed after all scenarios finished in current test run.
     */
    void afterAll();
}
