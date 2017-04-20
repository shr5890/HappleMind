package framework;

import java.awt.Desktop;
import java.io.File;
import java.util.*;

public class TestRunner {
	public static String myCurrentDir;
	public static File FilePath;
	public static void main(String[] args){
		try{
			String strCurrentDateandTime = TimeGenerator.getCurrentDateAndTime();
			TimeGenerator.getCustomTimeFormat(strCurrentDateandTime);
			myCurrentDir = System.getProperty("user.dir");          
			System.out.println("-------------------------------");
			System.out.println(":::::::Execution Started:::::::");
			System.out.println("-------------------------------");
			String ExcelDir = myCurrentDir+"\\TestscriptManager\\";	    			
			String[] arrWorkBooks = new String[args.length];	    	   
			for(int i = 0; i < args.length ; i++){
				arrWorkBooks[i] = args[i].substring(0, (args[i].indexOf('-')));
			}	    
			LinkedHashMap<String, List<String>> HashWorkSheets = new LinkedHashMap<String,List<String>>();
			for(int i = 0; i < args.length ; i++){
				String temp = args[i].substring(args[i].indexOf('-')+1, (args[i].length()));
				String[] tempsheet = temp.split(",");	    	
				List<String> lsttempsheet = Arrays.asList(tempsheet);	    	
				HashWorkSheets.put(arrWorkBooks[i], lsttempsheet);	    	
			}
			ExcelReader ER = new ExcelReader();
			ER.readExcel(ExcelDir,HashWorkSheets);
			XMLGenerator XMLG = new XMLGenerator();
			XMLG.createXML();
			//HTML Report Creation
			HTMLWriter HW = new HTMLWriter();
			FilePath = HW.createReportDirectory(strCurrentDateandTime);
			HW.createReportPage();
			HW.generateHeader(strCurrentDateandTime);
			CucumberRuntime CR = new CucumberRuntime();
			CR.CucumberRunner();
			HW.generateFooter(HW.generateMenuList(XMLGenerator.Scenarios).size());
			HW.generateSummaryReport("Test Summary Report");
			Desktop.getDesktop().browse(new File(FilePath.toString()+"\\Report.html").toURI());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}