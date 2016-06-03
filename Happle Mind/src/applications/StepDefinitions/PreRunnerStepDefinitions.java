package applications.StepDefinitions;

import java.util.ArrayList;
import java.util.List;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import feature.Reporters.BasePage;
import framework.HTMLWriter;
import framework.XMLGenerator;

public class PreRunnerStepDefinitions extends BasePage{	
	public Scenario FeatureScenario;
	public Object[] arrScenarioTags;
	public List<String> ScenarioData= new ArrayList<String>();

	@Before
	public void before(Scenario scenario) {
		this.FeatureScenario = scenario;
		getScenarioData(this.FeatureScenario);
		arrScenarioTags = FeatureScenario.getSourceTagNames().toArray();		
		HTMLWriter HW = new HTMLWriter();
		HW.generateMenuList(arrScenarioTags[0].toString());
		HW.generateFooter(XMLGenerator.Scenarios.size());
		System.out.println(arrScenarioTags[0].toString());
	}
	
	@After
	public void after() {
		try{
		System.out.println(FeatureScenario.getStatus());
		ScenarioData.add(FeatureScenario.getStatus());				
		TreeReportScenarios.put(arrScenarioTags[0].toString(),ScenarioData);
		TreeReportScenarioSteps.put(arrScenarioTags[0].toString(),ScenarioSteps);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void getScenarioData(Scenario scenario){				
		String arrFeatureID = FeatureScenario.getId().substring(0, FeatureScenario.getId().indexOf(';')).toUpperCase();
		System.out.println(arrFeatureID);
		ScenarioData.add(arrFeatureID);
		ScenarioData.add(FeatureScenario.getName());		
	}
}
