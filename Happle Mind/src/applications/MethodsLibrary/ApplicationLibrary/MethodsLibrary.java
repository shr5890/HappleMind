	package applications.MethodsLibrary.ApplicationLibrary;

public class MethodsLibrary {	
	public static void method1(){
		try{
			Thread.sleep(2000);
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
			System.out.println("Step Def2A written");
			}
			if(!false){
			System.out.println("Step Def3 written");
			System.out.println("Step Def4A written");
			System.out.println("Step Def4B written");
			System.out.println("Step Def4C written");
			System.out.println("Step Def4D written");
			System.out.println("Step Def4E written");
<<<<<<< HEAD
			System.out.println("Step Def4F written");
=======
>>>>>>> branch 'master' of https://github.com/shr5890/HappleMind.git
			System.out.println("Step Def4G written");
			System.out.println("Step Def5A written");
			System.out.println("Step Def5 written");
			System.out.println("Step Def6 written");
			System.out.println("Step Def7 written");
			}
//			System.out.println("Step Def written");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
