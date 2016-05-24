package feature.Reporters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import cucumber.api.java.en.*;

public class BasePage {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void getCurrentScenarioStep(Object object, Object[] Obj){		
		try {			
			Class<?>[] argClasses = null;
			if(Obj!=null){
				int i = 0;
				argClasses = new Class[Obj.length];
				for(Object obj : Obj){
				System.out.println(obj.getClass().getSimpleName());				
				argClasses[i] = obj.getClass();
				i++;
				}
			}
			Class clazz = object.getClass();
			String strMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();			
			System.out.println(strMethodName);
			Method method = null;			
//			try{
//				method = clazz.getMethod(strMethodName);
//			}catch(NoSuchMethodException NSME){
//				;;
//			}
			try{				
				//				Method m = clazz.getDeclaredMethod("foo", argClasses);
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
//			System.out.println(strAnnotationName);
//			System.out.println(strAnnotationValue);
			System.out.println("Step Definition Name : @"+strAnnotationName+"(\""+strAnnotationValue+"\")");
//			System.out.println("Step Scenario Name : "+strAnnotationName+" "+strAnnotationValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}