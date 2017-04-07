package applications.StepDefinitions;

import feature.Reporters.BasePage;

import java.util.HashMap;
import java.util.List;

import applications.MethodsLibrary.ApplicationLibrary.MethodsLibrary;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;

public class StepDefinition1 extends BasePage {
	@Given("^I am Shaikh with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void I_am_Shaikh_with_User_and_Password(String str, String str2, DataTable DT){		
		getCurrentScenarioStep(new StepDefinition1(), new Object[]{str,str2,DT});
		List<HashMap<String,String>> FeatureDataTable = convertFeatureData(DT);
		System.out.println(FeatureDataTable);
		System.out.println(FeatureDataTable.get(0).get("Surname"));
		System.out.println(FeatureDataTable.get(0).get("FirstName"));
		System.out.println(FeatureDataTable.get(0).get("LastName"));
		System.out.println(FeatureDataTable.get(0).get("Hometown"));
		System.out.println(FeatureDataTable.get(0).get("Native"));
		MethodsLibrary.method1();		
	}

	@Given("I am Hifzur")
	public void I_am_Hifzur(){
		getCurrentScenarioStep(new StepDefinition1(), null);
		MethodsLibrary.method1();		
	}

	@Given("I am Rahman")
	public void I_am_Rahman(){
		getCurrentScenarioStep(new StepDefinition1(), null);
		MethodsLibrary.method2();		
	}

	@When("I am cool")
	public void I_am_cool(){
		getCurrentScenarioStep(new StepDefinition1(), null);
		MethodsLibrary.method1();
	}

	@When("I am smarty")
	public void I_am_smarty(){
		getCurrentScenarioStep(new StepDefinition1(), null);
		MethodsLibrary.method1();
	}

	@Then("I am COC champ")
	public void I_am_COC_champ(){
		getCurrentScenarioStep(new StepDefinition1(), null);
		MethodsLibrary.method1();		
	}

	@Then("I am COC war hero")
	public void I_am_COC_war_hero(){	
		getCurrentScenarioStep(new StepDefinition1(), null);
		MethodsLibrary.method1();		
	}

	@Then("I am good boy")
	public void I_am_good_boy(){
		getCurrentScenarioStep(new StepDefinition1(), null);
		MethodsLibrary.method1();		
	}
}