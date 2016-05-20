package applications.StepDefinitions;

import applications.MethodsLibrary.*;
import cucumber.api.java.en.*;

public class StepDefinition1 {
	@Given("I am Hifzur")
	public void I_am_Hifzur(){
		MethodsLibrary.method1();
	}
	
	@Given("I am Rahman")
	public void I_am_Rahman(){
		MethodsLibrary.method2();
	}
}
