package applications.StepDefinitions;

import feature.Reporters.BasePage;
import applications.MethodsLibrary.ApplicationLibrary.MethodsLibrary;
import cucumber.api.java.en.*;

public class StepDefinition1 extends BasePage{	

	@Given("I am Shaikh")
	public void I_am_Shaikh(){		
		MethodsLibrary.method1();
		getStepName();
	}
	
	@Given("I am Hifzur")
	public void I_am_Hifzur(){		
		MethodsLibrary.method1();
		getStepName();
	}

	@Given("I am Rahman")
	public void I_am_Rahman(){
		MethodsLibrary.method2();
		getStepName();
	}
}
