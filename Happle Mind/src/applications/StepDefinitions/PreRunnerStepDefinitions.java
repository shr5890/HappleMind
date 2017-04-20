package applications.StepDefinitions;

import java.io.PrintWriter;
import java.util.HashMap;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import feature.Reporters.BasePage;
import framework.HTMLWriter;
import framework.StepStatus.EachTestStatus;

public class PreRunnerStepDefinitions extends BasePage{	
	private static PrintWriter writer = null;

	@Before
	public void before(Scenario scenario) {
		TestStatus = EachTestStatus.NORUN;
		DLStatus = null;
		intHidePointer = 1;
		HashMap<String,String> ScenarioData = new HashMap<String,String>();
		ScenarioData = getScenarioData(scenario);
		writer = new HTMLWriter().initializeMenuContent(ScenarioData);
	}

	@After
	public void after(Scenario scenario) {
		new HTMLWriter().finishMenuContent(writer);
		TestScriptStatus.put(scenario.getSourceTagNames().toArray()[0].toString(), TestStatus);
	}

	public HashMap<String,String> getScenarioData(Scenario scenario){
		HashMap<String,String> ScenarioData = new HashMap<String,String>();
		String arrFeatureID = scenario.getId().substring(0, scenario.getId().indexOf(';')).toUpperCase();
		Object[] arrScenarioTags = scenario.getSourceTagNames().toArray();
		ScenarioData.put("Feature",arrFeatureID);
		ScenarioData.put("Scenario",scenario.getName());
		ScenarioData.put("ScenarioTag", arrScenarioTags[0].toString());
		ScenarioData.put("FeatureTag", arrScenarioTags[1].toString());
		return ScenarioData;		
	}
	
	public static PrintWriter getHTMLWriter(){
		return writer;
	}
}