package applications.MethodsLibrary.CommonLibrary;

import feature.Reporters.BasePage;
import framework.StepFailureException;
import framework.StepStatus.EachStepStatus;
import framework.UserDefinedReturns;

public class CommonLibrary extends BasePage{

	public static void setTestStatus(String Summary, String Description, EachStepStatus status) throws StepFailureException {
		DLStoreMap.add(new UserDefinedReturns(Summary, Description, status));
		setDLStatus(status);
	}

}