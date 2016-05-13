package framework;

import java.io.File;
import java.io.FileInputStream;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelReader {

	public void readExcel(String excelFilePath, TreeMap<String, List<String>> HashWorkSheets) {
		try{											
			Table<String, String, TreeMap<String, TreeMap<String, String>>> table = HashBasedTable.create();
			Map<String,Integer> HeaderPositions = new TreeMap<String,Integer>();
			TreeMap<String, String> ExecutionScenarios = new TreeMap<String,String>();
			TreeMap<String, TreeMap<String, String>> totalExecutionScenarios = new TreeMap<String, TreeMap<String, String>>();
			String temppath = excelFilePath;
			for(String WorkBook : HashWorkSheets.keySet()){
				excelFilePath=temppath+WorkBook;
				System.out.println(excelFilePath);
				FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
				Workbook workbook = null;			
				if (excelFilePath.endsWith("xlsx")) {
					workbook = new XSSFWorkbook(inputStream);
					System.out.println("Book Type -----> XLSX");
				}else if (excelFilePath.endsWith("xls")) {
					workbook = new HSSFWorkbook(inputStream);
					System.out.println("Book Type -----> XLS");
				}
				String [] SheetNames = new String[workbook.getNumberOfSheets()];			
				for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
					SheetNames[i] = workbook.getSheetName(i);
					System.out.println(SheetNames[i]);
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
									System.out.print(cellScenarioNo);													
								}else if(HeaderPositions.get("Scenario ID").equals(intInd)){
									cellScenarioID = cell.getStringCellValue();							
									System.out.print(cellScenarioID);													
								}else if(HeaderPositions.get("Scenario Description").equals(intInd)){
									cellScenarioDesc = cell.getStringCellValue();						
									System.out.print(cellScenarioDesc);													
								}else if(HeaderPositions.get("Trigger Execution").equals(intInd)){
									cellTriggerExecution = (boolean)cell.getBooleanCellValue();							
									System.out.print(cellTriggerExecution);													
								}
								intInd++;
								System.out.print(" - ");
							}
							if(cellTriggerExecution){
								ExecutionScenarios.put("Scenario No", cellScenarioNo+"");
								ExecutionScenarios.put("Scenario ID", cellScenarioID+"");
								ExecutionScenarios.put("Scenario Description", cellScenarioDesc+"");							
								totalExecutionScenarios.put(row.getRowNum()+"", ExecutionScenarios);
								table.put(WorkBook, SheetNames[i], totalExecutionScenarios);
								ExecutionScenarios=new TreeMap<String,String>();
							}
						}					
						System.out.println();				
					}
					totalExecutionScenarios = new TreeMap<String, TreeMap<String, String>>();
				}												
				System.out.println(table);
				workbook.close();
				inputStream.close();
			}
			Set<String> rowkeys = table.rowKeySet();
			Set<String> columnkeys = table.columnKeySet();
			for(String i: rowkeys){
				System.out.println(table.rowMap());
				for(String j: columnkeys){
					System.out.println(table.get(i, j));
				}				
			}
		}catch(Exception e){			
			e.printStackTrace();
		}
	}
}