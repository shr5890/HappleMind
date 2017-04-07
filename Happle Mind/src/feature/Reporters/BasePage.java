package feature.Reporters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import cucumber.api.DataTable;
import cucumber.api.java.en.*;

public class BasePage {

	public TreeMap<String, List<String>> TreeReportScenarios = new TreeMap<String, List<String>>();
	public static TreeMap<String, List<String>> TreeReportScenarioSteps = new TreeMap<String, List<String>>();
	public static List<String> ScenarioSteps= new ArrayList<String>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void getCurrentScenarioStep(Object object, Object[] Obj){		
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
			String ScenarioStep = "@"+strAnnotationName+"(\""+strAnnotationValue+"\")";
			System.out.println(ScenarioStep);
			System.out.println(strAnnotationName+" "+strMethodName.replace("_", " "));
			ScenarioSteps.add(strAnnotationName+" "+strMethodName.replace("_", " "));
		} catch (Exception ex) {
			ex.printStackTrace();
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
}