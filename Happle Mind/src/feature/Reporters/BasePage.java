package feature.Reporters;

public class BasePage {	
	public static void getStepName() {
		try{
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName().replace("_", " "));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}