package applications.MethodsLibrary.ApplicationLibrary;

public class MethodsLibrary {	
	public static void method1(){
		try{
			Thread.sleep(2000);
//			System.out.println("Step Def written");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void method2(){
		try{
			Thread.sleep(1000);
			if(true){
			System.out.println("Step Def2 written");				
			System.out.println("Step Def1 written");
			}
			if(!false){
			System.out.println("Step Def3 written");
			System.out.println("Step Def4A written");
			System.out.println("Step Def5 written");
			System.out.println("Step Def6 written");
			}
//			System.out.println("Step Def written");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
