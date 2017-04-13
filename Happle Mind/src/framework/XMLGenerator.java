package framework;

import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import com.google.common.collect.Table.Cell;

public class XMLGenerator extends ExcelReader{
	public static List<String> Scenarios = new ArrayList<String>();
	public void createXML(){
		try {
			int intInd = 0;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();			
			Element rootElement = doc.createElementNS(TestRunner.myCurrentDir, "TestScenarios");
			doc.appendChild(rootElement);			
			for (Cell<String, String, LinkedHashMap<String, LinkedHashMap<String, String>>> cell: table.cellSet()){
				System.out.println();
			    System.out.println("======================");
				System.out.print(cell.getRowKey()+" "+cell.getColumnKey()+" ");
			    Element ExcelFile = doc.createElement("excelfile");
			    ExcelFile.setAttribute("name", cell.getRowKey());
			    rootElement.appendChild(ExcelFile);
			    Element SheetName = doc.createElement("sheetname");
			    SheetName.setAttribute("name", cell.getColumnKey());
			    ExcelFile.appendChild(SheetName);
			    LinkedHashMap<String, LinkedHashMap<String, String>> ScenarioFeatures = cell.getValue();			    
			    Set<String> Featurekeys = ScenarioFeatures.keySet();
		        for(String key1: Featurekeys){		            
		            Element TestCaseNumber = doc.createElement("testcasenumber");
		            TestCaseNumber.setAttribute("id", key1);
		            SheetName.appendChild(TestCaseNumber);		            
		            intInd++;		            
		            LinkedHashMap<String, String> ScenarioData = ScenarioFeatures.get(key1);
		            Set<String> Datakeys = ScenarioData.keySet();
		            for(String key2: Datakeys){		            				            
			            if(key2.equals("Scenario Description")){
			            	Element ScenarioDesc = doc.createElement("scenariodesc");
			            	TestCaseNumber.appendChild(ScenarioDesc);
			            	ScenarioDesc.appendChild(doc.createTextNode(ScenarioData.get(key2)));
			            }
			            else if(key2.equals("Scenario ID")){
			            	System.out.print(ScenarioData.get(key2)+" ");
			            	Scenarios.add(ScenarioData.get(key2));
			            	Element ScenarioID = doc.createElement("scenarioid");
			            	TestCaseNumber.appendChild(ScenarioID);
			            	ScenarioID.appendChild(doc.createTextNode(ScenarioData.get(key2)));
			            }
			            else if(key2.equals("Scenario No")){
			            	Element ScenarioNo = doc.createElement("Scenariono");
			            	TestCaseNumber.appendChild(ScenarioNo);
			            	ScenarioNo.appendChild(doc.createTextNode(ScenarioData.get(key2)));
			            }			            			           
		            }
		        }		        
			}
			System.out.println();
			System.out.println("======================");
			System.out.println("Total Scenarios : "+intInd);
			System.out.println("Scenarios : "+Scenarios);									
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    DOMSource source = new DOMSource(doc);		    
			String myCurrentDir = System.getProperty("user.dir");
			StreamResult result = new StreamResult(new File(myCurrentDir+"\\Details.xml"));
			transformer.transform(source, result);
			System.out.println("======================");
			System.out.println("XML saved!");
			System.out.println("======================");
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException te) {
			te.printStackTrace();
		}
	}
}	