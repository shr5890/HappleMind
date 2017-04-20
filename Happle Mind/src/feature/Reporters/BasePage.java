package feature.Reporters;

import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import applications.StepDefinitions.PreRunnerStepDefinitions;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.BeanClass;
import framework.HTMLWriter;
import framework.StepFailureException;
import framework.UserDefinedReturns;
import framework.StepStatus.EachStepStatus;
import framework.StepStatus.EachTestStatus;

public class BasePage{
	public static String nextLine = "<BR/>";
	private static String ScenarioStepDL = null;
	private static String ScenarioStepAnnotation = null;
	public static LinkedList<UserDefinedReturns> DLStoreMap = null;
	public static EachStepStatus DLStatus = null;
	public static EachTestStatus TestStatus = null;
	public static int intHidePointer;
	public static LinkedHashMap<String, EachTestStatus> TestScriptStatus = new LinkedHashMap<String, EachTestStatus>();;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void setCurrentScenarioStep(Object object, Object[] Obj){
		DLStoreMap = new LinkedList<UserDefinedReturns>();
		DLStatus = EachStepStatus.PASS;
		try {			
			Class<?>[] argClasses = null;
			if(Obj!=null){
				int i = 0;
				argClasses = new Class[Obj.length];
				for(Object obj : Obj){
					argClasses[i] = obj.getClass();
					i++;
				}
			}
			Class clazz = object.getClass();
			String strMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
			Method method = null;
			try{				
				method = clazz.getDeclaredMethod(strMethodName, argClasses);
			}catch(NoSuchMethodException NSME){
				;
			}						
			Given annotationGiven = method.getAnnotation(Given.class);
			Then annotationThen = method.getAnnotation(Then.class);
			And annotationAnd = method.getAnnotation(And.class);
			When annotationWhen = method.getAnnotation(When.class);
			But annotationBut = method.getAnnotation(But.class);
			Annotation annotation;
			String strAnnotationName,strAnnotationValue;
			if(annotationGiven != null){
				annotation = annotationGiven;
				strAnnotationValue = annotationGiven.value();
			}else if(annotationThen != null){
				annotation = annotationThen;
				strAnnotationValue = annotationThen.value();
			}else if(annotationAnd != null){
				annotation = annotationAnd;
				strAnnotationValue = annotationAnd.value();
			}else if(annotationWhen != null){
				annotation = annotationWhen;
				strAnnotationValue = annotationWhen.value();
			}else if(annotationBut != null){
				annotation = annotationBut;
				strAnnotationValue = annotationBut.value();
			}else{
				throw new Exception("No Annotation Found");
			}			
			strAnnotationName = annotation.annotationType().getSimpleName();
			strAnnotationValue = strAnnotationValue.replaceAll("\'", "\\\\\'");
			strAnnotationValue = strAnnotationValue.replaceAll("\"", "\\\\\"");
			ScenarioStepDL = strAnnotationName+" "+strMethodName.replace("_", " ");
			ScenarioStepAnnotation = "@"+strAnnotationName+"(\""+strAnnotationValue+"\")";
			System.out.println(ScenarioStepDL);
			System.out.println(ScenarioStepAnnotation);
			initializeDL();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void initializeDL() {
		PrintWriter writer = PreRunnerStepDefinitions.getHTMLWriter();
		writer.println("<span class = 'whitefont'>"+ScenarioStepDL+"</span>");
		writer.println(nextLine);
		writer.println("<label class=\"collapse\" for=\"_"+intHidePointer+"\">"+ScenarioStepAnnotation+"</label>");
		writer.println("<input id=\"_"+intHidePointer+"\" type=\"checkbox\"></input>");
		writer.println("<div>");
		intHidePointer++;
	}
	
	public void saveToReport(){
		new HTMLWriter().appendMenuContent(PreRunnerStepDefinitions.getHTMLWriter());
		setTestStatus(EachTestStatus.valueOf(getDLStatus().toString()));
	}
	
	public static BeanClass getBeanClass(){
		return new BeanClass();
	}
	
	public static EachStepStatus getDLStatus(){
		return DLStatus;
	}
	
	public static EachTestStatus getTestStatus(){
		return TestStatus;
	}
	
	public static void setDLStatus(EachStepStatus status) {
		if(getDLStatus().equals(EachStepStatus.PASS)){
			if(status.equals(EachStepStatus.PASS)){
				DLStatus = EachStepStatus.PASS;
			}else if(status.equals(EachStepStatus.FAIL)){
				DLStatus = EachStepStatus.FAIL;
			}else if(status.equals(EachStepStatus.WARNING)){
				DLStatus = EachStepStatus.WARNING;
			}
		}else if(getDLStatus().equals(EachStepStatus.WARNING)){
			if(status.equals(EachStepStatus.PASS) || status.equals(EachStepStatus.WARNING)){
				DLStatus = EachStepStatus.WARNING;
			}else if(status.equals(EachStepStatus.FAIL)){
				DLStatus = EachStepStatus.FAIL;
			}
		}
		if(DLStatus.equals(EachStepStatus.FAIL))
			throw new StepFailureException("Step Failed");
	}
	
	public static void setTestStatus(EachTestStatus status){
		if(getTestStatus() == EachTestStatus.NORUN && DLStatus!=null){
			TestStatus = EachTestStatus.valueOf(DLStatus.toString());
		}else{
			if(getTestStatus().equals(EachTestStatus.PASS)){
				if(status.equals(EachTestStatus.PASS)){
					TestStatus = EachTestStatus.PASS;
				}else if(status.equals(EachTestStatus.FAIL)){
					TestStatus = EachTestStatus.FAIL;
				}else if(status.equals(EachTestStatus.WARNING)){
					TestStatus = EachTestStatus.WARNING;
				}
			}else if(getTestStatus().equals(EachTestStatus.WARNING)){
				if(status.equals(EachTestStatus.PASS) || status.equals(EachTestStatus.WARNING)){
					TestStatus = EachTestStatus.WARNING;
				}else if(status.equals(EachTestStatus.FAIL)){
					TestStatus = EachTestStatus.FAIL;
				}
			}
		}
		if(TestStatus.equals(EachTestStatus.FAIL)){
			Assert.fail("Test Failed");
		}
	}

	public List<HashMap<String,String>> convertFeatureData(DataTable DT){
		List<HashMap<String,String>> FeatureData = new ArrayList<HashMap<String,String>>();  
		HashMap<String,String> ColumnData = null;
		List<String> ColumnNames = DT.raw().get(0);
		int rows = DT.getGherkinRows().size();
		for(int i = 0; i < rows-1; i++){
			ColumnData = new HashMap<String,String>();
			for(int j = 0; j < ColumnNames.size(); j++){
				List<String> ColumnValues = DT.raw().get(i+1);
				ColumnData.put(ColumnNames.get(j), ColumnValues.get(j));
			}
			FeatureData.add(ColumnData);
		}
		return FeatureData;
	}
	
	public int getResultCount(EachTestStatus status){
		int intResult = 0;
		for (Map.Entry<String, EachTestStatus> e : TestScriptStatus.entrySet()) {
		    if(e.getValue().equals(status)){
		    	intResult++;
		    }
		}
		return intResult;
	}
}