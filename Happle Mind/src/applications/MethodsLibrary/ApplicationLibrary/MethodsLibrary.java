package applications.MethodsLibrary.ApplicationLibrary;

public class MethodsLibrary {	
	public static void method1(){
		try{
			System.out.println("Executing method");
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
				System.out.println("Step Def2A written");
			}
			if(!false){
				System.out.println("Step Def3 written");
				System.out.println("Step Def3A written");
				System.out.println("Step Def3B written");
				System.out.println("Step Def3C written");
				System.out.println("Step Def4A written");
				System.out.println("Step Def4B written");
				System.out.println("Step Def4C written");
				System.out.println("Step Def4D written");
				System.out.println("Step Def4E written");
				System.out.println("Step Def4F written");
				System.out.println("Step Def4G written");
				System.out.println("Step Def5A written");
				System.out.println("Step Def5B written");
				System.out.println("Step Def5C written");
				System.out.println("Step Def5D written");
				System.out.println("Step Def5E written");
				System.out.println("Step Def5F written");
				System.out.println("Step Def5G written");
				System.out.println("Step Def6 written");
				System.out.println("Step Def7 written");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
