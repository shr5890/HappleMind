package framework;

import java.util.*;

public class TestRunner {
	public static String myCurrentDir;
	public static void main(String[] args){
		try{
			myCurrentDir = System.getProperty("user.dir");          
			System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
			String ExcelDir = myCurrentDir+"\\TestscriptManager\\";	    
			System.out.println("Excel Path : "+ExcelDir);
			String[] arrWorkBooks = new String[args.length];	    	   
			for(int i = 0; i < args.length ; i++){
				arrWorkBooks[i] = args[i].substring(0, (args[i].indexOf('-')));
			}	    
			TreeMap<String, List<String>> HashWorkSheets = new TreeMap<String,List<String>>();
			for(int i = 0; i < args.length ; i++){
				String temp = args[i].substring(args[i].indexOf('-')+1, (args[i].length()));
				String[] tempsheet = temp.split(";");	    	
				List<String> lsttempsheet = Arrays.asList(tempsheet);	    	
				HashWorkSheets.put(arrWorkBooks[i], lsttempsheet);	    	
			}
			ExcelReader ER = new ExcelReader();
			ER.readExcel(ExcelDir,HashWorkSheets);
			XMLGenerator XMLG = new XMLGenerator();
			XMLG.createXML();
			CucumberRuntime CR = new CucumberRuntime();
			CR.CucumberRunner();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}