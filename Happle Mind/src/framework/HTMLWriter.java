package framework;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;

import framework.chartobjects.Charts;

import framework.XMLGenerator;

public class HTMLWriter {
	public static void GenerateHTML(String CurrentDateandTime, String chartTitle, String[] arrchartSubjects, int[] arrchartSubjectValues) throws IOException {
		try{
			//***************** Creates a HTML File and Opens it *************************
			String myCurrentDir = System.getProperty("user.dir");	            
			System.out.println(myCurrentDir);
			File fileToTest = new File(myCurrentDir);
			String parentDir = fileToTest.getParent();
			System.out.println(parentDir);
			String outputDir = parentDir+"\\Output\\ChartReport\\RUN_"+CurrentDateandTime;
			File FilePath = new File(outputDir);
			FilePath.mkdir();
			File htmlFile = new File(FilePath+"\\Result.html");	        
			JFreeChart chart = Charts.Chart(chartTitle, arrchartSubjects, arrchartSubjectValues);                          
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File pngFile = new File(FilePath+"\\piechart.png");
			ChartUtilities.saveChartAsPNG(pngFile, chart, 600, 400, info);      
			final OutputStream out = new BufferedOutputStream(new FileOutputStream(htmlFile));
			final PrintWriter writer = new PrintWriter(out);
			writer.println("<HTML>");		
			writer.println("<HEAD>");
			writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"D:/workspace/Reports/src/css/Report.CSS\">");		
			writer.println("<TITLE>PIE Chart Report</TITLE></HEAD>");		
			writer.println("<BODY bgcolor=\"#38ACEC\">");
			writer.println("<B><Big><center>Report Generator</center></Big></B>");
			writer.println("</BR>");
			writer.println("<center>");
			writer.println("<div class='tv'>");
			writer.println("</BR></BR></BR>");
			writer.println("<div class='chart'>");
			writer.println("<IMG SRC=\"piechart.png\" WIDTH=\"600\" HEIGHT=\"400\" BORDER=\"0\" USEMAP=\"#chart\">");
			writer.println("</div>");
			writer.println("</div>");
			writer.println("</BR>");		
			writer.println("</BODY>");
			writer.println("</HTML>");
			writer.close();
			Desktop.getDesktop().browse(htmlFile.toURI());
		}
		catch(Exception e){
			e.getStackTrace();
		}
	}

	public File createReportDirectory(String strCurrentDateandTime){
		File FilePath = null;
		try{
			String myCurrentDir = System.getProperty("user.dir");	            
			System.out.println(myCurrentDir);	    	    
			String outputDir = myCurrentDir+"\\Reports\\RUN_"+strCurrentDateandTime;
			FilePath = new File(outputDir);
			FilePath.mkdir();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return FilePath;
	}	

	public void generateHeader(String strCurrentDateandTime){
		try{
			String myCurrentDir = System.getProperty("user.dir");
			File htmlFile = new File(TestRunner.FilePath+"\\header.html");
			final OutputStream out = new BufferedOutputStream(new FileOutputStream(htmlFile));
			final PrintWriter writer = new PrintWriter(out);
			writer.println("<HTML>");		
			writer.println("<HEAD>");
			writer.println("<title>Report Header</title>");
			writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+myCurrentDir+"/CSS/Report.CSS\">");
			writer.println("</HEAD>");			
			writer.println("<body class = 'header'>");			
			writer.println("<div class='corporateName'><div><B>Happle Mind India PVT LTD</B></div>");
			writer.println("<div>Customized - <I>Cucumber Report</I></div>");
			writer.println("<div>Regression Execution Report</div>");
			writer.println("<div>Test Executed @ "+strCurrentDateandTime+"</div></div>");
			writer.println("</body>");
			writer.println("</HTML>");
			writer.close();
            System.out.println("Header Written");
		}
		catch(Exception e){
			e.getStackTrace();
		}
	}
	
	public void generateMenuList(String strScenario){
		try{
			String myCurrentDir = System.getProperty("user.dir");
			File MenuListhtmlFile = new File(TestRunner.FilePath+"\\MenuList.html");
			final OutputStream outMenuList = new BufferedOutputStream(new FileOutputStream(MenuListhtmlFile));
			final PrintWriter writer = new PrintWriter(outMenuList);
			writer.println("<HTML>");		
			writer.println("<HEAD>");
			writer.println("<title>Report List</title>");
			writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+myCurrentDir+"/CSS/Report.CSS\">");
			writer.println("</HEAD>");
			writer.println("<body class = 'menulist'>");
			writer.println("<div>");
			writer.println("<a href='SummaryReport.html' target='content'>Summary Report</a>");
			writer.println("</div>");
			writer.println("<BR/>");
			writer.println("<div><B>Scenario List</B></div>");
			writer.println("<BR/>");			
			writer.println("<a href='"+strScenario+".html' target='content'>"+strScenario+"</a>");
			writer.println("<BR/>");
			writer.println("</body>");
			writer.println("</HTML>");
			writer.close();
            System.out.println("Menu List Written");
		}
		catch(Exception e){
			e.getStackTrace();
		}
	}
	
	public void generateFooter(int TotalTCs){
		try{
			String myCurrentDir = System.getProperty("user.dir");
			File htmlFile = new File(TestRunner.FilePath+"\\Footer.html");
			final OutputStream out = new BufferedOutputStream(new FileOutputStream(htmlFile));
			PrintWriter writer = new PrintWriter(out);
			writer.println("<HTML>");		
			writer.println("<HEAD>");
			writer.println("<title>Report Foot</title>");
			writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+myCurrentDir+"/CSS/Report.CSS\">");
			writer.println("</HEAD>");
			writer.println("<body class = 'footer'>");
			writer.println("<B>");
			writer.println("Total : "+TotalTCs);
			writer.println("<span class='passed'>Passed : "+0+"</span>");
			writer.println("<span class='failed'>Failed : "+0+"</span>");
			writer.println("<span class='warned'>Warned : "+0+"</span>");
			writer.println("<span class='norun'>No Run : "+0+"</span>");
			writer.println("</B>");
			writer.println("<BR/>");
			writer.println("<BR/>");
			writer.close();			
			writer.println("@HappleMind");			
			writer.println("</body>");
			writer.println("</HTML>");
			writer.close();
            System.out.println("Footer Written");
		}
		catch(Exception e){
			e.getStackTrace();
		}
	}
}