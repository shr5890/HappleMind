package framework;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class TestRunner {
	public static void main(String[] args){
		String myCurrentDir = System.getProperty("user.dir")
	            + File.separator
	            + System.getProperty("sun.java.command")
	                    .substring(0, System.getProperty("sun.java.command").lastIndexOf("."))
	                    .replace(".", File.separator);
	    System.out.println("Main Class' Current Directory : "+myCurrentDir);
	    File fileToTest = new File(myCurrentDir);
	    String parentDir = fileToTest.getParent();
	    fileToTest = new File(parentDir);
	    String ProjectDir = fileToTest.getParent();	   
	    System.out.println("Project Directory : "+ProjectDir);
	    String ExcelDir = ProjectDir+"\\TestscriptManager\\";
	    ExcelDir="D:\\workspace\\Happle Mind\\TestscriptManager\\";
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
	}
}
