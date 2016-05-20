package framework;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelReader {

	public static Table<String, String, TreeMap<String, TreeMap<String, String>>> table; 
	public void readExcel(String excelFilePath, TreeMap<String, List<String>> TreeWorkSheets) {
		try{											
			table = TreeBasedTable.create();			
			String temppath = excelFilePath;
			for(String strWorkBook : TreeWorkSheets.keySet()){
				excelFilePath=temppath+strWorkBook;				
				FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
				Workbook workbook = null;			
				if (excelFilePath.endsWith("xlsx")) {
					workbook = new XSSFWorkbook(inputStream);					
				}else if (excelFilePath.endsWith("xls")) {
					workbook = new HSSFWorkbook(inputStream);					
				}
				List<String> LST_Sheets = TreeWorkSheets.get(strWorkBook);
				if(LST_Sheets.get(0).equalsIgnoreCase("ALL")){
					getAllSheetData(workbook, strWorkBook); 
				}
				else{
					getRequiredSheetData(workbook, strWorkBook, LST_Sheets); 
				}
				workbook.close();
				inputStream.close();
			}			
		}catch(Exception e){			
			e.printStackTrace();
		}
	}

	public void getAllSheetData(Workbook workbook, String strWorkBook){
		Map<String,Integer> HeaderPositions = new TreeMap<String,Integer>();
		TreeMap<String, String> ExecutionScenarios = new TreeMap<String,String>();
		TreeMap<String, TreeMap<String, String>> totalExecutionScenarios = new TreeMap<String, TreeMap<String, String>>();
		String [] SheetNames = new String[workbook.getNumberOfSheets()];			
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			SheetNames[i] = workbook.getSheetName(i);			
			Sheet Sheet = workbook.getSheetAt(i);
			Iterator<Row> iterator = Sheet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int cellScenarioNo = 0;
				String cellScenarioID = null,cellScenarioDesc = null;
				boolean cellTriggerExecution = false;
				if(row.getRowNum() == 0){
					int intInd = 0;															
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();						
						if(cell.getStringCellValue().equalsIgnoreCase("Scenario No")){
							HeaderPositions.put("Scenario No", intInd);
						}else if(cell.getStringCellValue().equalsIgnoreCase("Scenario ID")){
							HeaderPositions.put("Scenario ID", intInd);
						}else if(cell.getStringCellValue().equalsIgnoreCase("Scenario Description")){
							HeaderPositions.put("Scenario Description", intInd);
						}else if(cell.getStringCellValue().equalsIgnoreCase("Trigger Execution")){
							HeaderPositions.put("Trigger Execution", intInd);
						}						
						intInd++;						
					}					
				}
				else{
					int intInd = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if(HeaderPositions.get("Scenario No").equals(intInd)){
							cellScenarioNo = (int)cell.getNumericCellValue();																		
						}else if(HeaderPositions.get("Scenario ID").equals(intInd)){
							cellScenarioID = cell.getStringCellValue();										
						}else if(HeaderPositions.get("Scenario Description").equals(intInd)){
							cellScenarioDesc = cell.getStringCellValue();												
						}else if(HeaderPositions.get("Trigger Execution").equals(intInd)){
							cellTriggerExecution = (boolean)cell.getBooleanCellValue();												
						}
						intInd++;						
					}
					if(cellTriggerExecution){
						ExecutionScenarios.put("Scenario No", cellScenarioNo+"");
						ExecutionScenarios.put("Scenario ID", cellScenarioID+"");
						ExecutionScenarios.put("Scenario Description", cellScenarioDesc+"");							
						totalExecutionScenarios.put(row.getRowNum()+"", ExecutionScenarios);
						table.put(strWorkBook, SheetNames[i], totalExecutionScenarios);
						ExecutionScenarios=new TreeMap<String,String>();
					}
				}
			}
			totalExecutionScenarios = new TreeMap<String, TreeMap<String, String>>();
		}
	}

	public void getRequiredSheetData(Workbook workbook, String strWorkBook, List<String> LST_Sheets){
		Map<String,Integer> HeaderPositions = new TreeMap<String,Integer>();
		TreeMap<String, String> ExecutionScenarios = new TreeMap<String,String>();
		TreeMap<String, TreeMap<String, String>> totalExecutionScenarios = new TreeMap<String, TreeMap<String, String>>();		
		for (String SheetName : LST_Sheets) {
			Sheet Sheet = workbook.getSheet(SheetName);						
			Iterator<Row> iterator = Sheet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int cellScenarioNo = 0;
				String cellScenarioID = null,cellScenarioDesc = null;
				boolean cellTriggerExecution = false;
				if(row.getRowNum() == 0){
					int intInd = 0;															
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();						
						if(cell.getStringCellValue().equalsIgnoreCase("Scenario No")){
							HeaderPositions.put("Scenario No", intInd);
						}else if(cell.getStringCellValue().equalsIgnoreCase("Scenario ID")){
							HeaderPositions.put("Scenario ID", intInd);
						}else if(cell.getStringCellValue().equalsIgnoreCase("Scenario Description")){
							HeaderPositions.put("Scenario Description", intInd);
						}else if(cell.getStringCellValue().equalsIgnoreCase("Trigger Execution")){
							HeaderPositions.put("Trigger Execution", intInd);
						}						
						intInd++;						
					}					
				}
				else{
					int intInd = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if(HeaderPositions.get("Scenario No").equals(intInd)){
							cellScenarioNo = (int)cell.getNumericCellValue();
						}else if(HeaderPositions.get("Scenario ID").equals(intInd)){
							cellScenarioID = cell.getStringCellValue();
						}else if(HeaderPositions.get("Scenario Description").equals(intInd)){
							cellScenarioDesc = cell.getStringCellValue();
						}else if(HeaderPositions.get("Trigger Execution").equals(intInd)){
							cellTriggerExecution = (boolean)cell.getBooleanCellValue();
						}
						intInd++;
					}
					if(cellTriggerExecution){
						ExecutionScenarios.put("Scenario No", cellScenarioNo+"");
						ExecutionScenarios.put("Scenario ID", cellScenarioID+"");
						ExecutionScenarios.put("Scenario Description", cellScenarioDesc+"");							
						totalExecutionScenarios.put(row.getRowNum()+"", ExecutionScenarios);
						table.put(strWorkBook, SheetName, totalExecutionScenarios);
						ExecutionScenarios=new TreeMap<String,String>();
					}
				}
			}
			totalExecutionScenarios = new TreeMap<String, TreeMap<String, String>>();
		}
	}
}