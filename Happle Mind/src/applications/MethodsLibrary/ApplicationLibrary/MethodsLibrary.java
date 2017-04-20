package applications.MethodsLibrary.ApplicationLibrary;

import applications.MethodsLibrary.CommonLibrary.CommonLibrary;
import framework.StepStatus.EachStepStatus;

public class MethodsLibrary extends CommonLibrary{	
	public static void method1(String str) {
		try{
			if(str.equalsIgnoreCase("PASS")){
				setTestStatus("Run Method 1", "Method 1 is Executed", EachStepStatus.PASS);
			}else if(str.equalsIgnoreCase("FAIL")){
				setTestStatus("Run Method 1", "Method 1 is Executed", EachStepStatus.FAIL);
			}else if(str.equalsIgnoreCase("WARNING")){
				setTestStatus("Run Method 1", "Method 1 is Executed ksjhdkhkhskdhkhskh kkhkjshd kjhfjkshkgjhksj hgkjhskjgh khskjgh", EachStepStatus.WARNING);
			}else{
				setTestStatus("Run Method 1", "Method 1 is Executed ksjhdkhkhskdhkhskh kkhkjshd kjhfjkshkgjhksj hgkjhskjgh khskjgh", EachStepStatus.FAIL);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void method2(String str) {
		try{
			if(str.equalsIgnoreCase("PASS")){
				setTestStatus("Run Method 2", "Method 2 is Executed", EachStepStatus.PASS);
			}else if(str.equalsIgnoreCase("FAIL")){
				setTestStatus("Run Method 2", "Method 2 is Executed", EachStepStatus.FAIL);
			}else if(str.equalsIgnoreCase("WARNING")){
				setTestStatus("Run Method 2", "Method 2 is Executed", EachStepStatus.WARNING);
			}else{
				setTestStatus("Run Method 2", "Method 1 is Executed", EachStepStatus.FAIL);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}