package applications.StepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class PreRunnerStepDefinitions {
	
	public Scenario scenario;
	public String scenariostatus;

	@Before
	public void before(Scenario scenario) {
	    this.scenario = scenario;
	}
	
	@After
	public void after() {
		scenariostatus = scenario.getStatus();
		System.out.println(scenario.getId());
		System.out.println(scenario.getStatus());
	    System.out.println(scenario.getName());
	    System.out.println(scenario.getSourceTagNames());
	}	
}
