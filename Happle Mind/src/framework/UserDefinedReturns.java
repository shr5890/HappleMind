package framework;

import framework.StepStatus.EachStepStatus;
public class UserDefinedReturns {
	public String Summary;
	public String Description;
	public EachStepStatus Status;
	public UserDefinedReturns(String Summary, String Description, EachStepStatus StepStatus) {
		this.Summary = Summary;
		this.Description = Description;
		this.Status = StepStatus;
	}
	public UserDefinedReturns(UserDefinedReturns UDR) {
		this.Summary = UDR.Summary;
		this.Description = UDR.Description;
		this.Status = UDR.Status;
//		return new LinkedList<String>(Arrays.asList(UDR.Description, UDR.Status.toString()));
	}
}