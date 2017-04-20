package framework;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;

import feature.Reporters.BasePage;
import framework.StepStatus.EachTestStatus;
import framework.chartobjects.Charts;

public class HTMLWriter extends BasePage {
	public void generateSummaryReport(String chartTitle) throws IOException {
		try{
			String myCurrentDir = System.getProperty("user.dir");
			File htmlFile = new File(TestRunner.FilePath+"\\Summary\\SummaryReport.html");
			final OutputStream out = new BufferedOutputStream(new FileOutputStream(htmlFile));
			PrintWriter writer = new PrintWriter(out);
			writer.println("<HTML>");		
			writer.println("<HEAD>");
			writer.println("<title>Report Foot</title>");
			writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+myCurrentDir+"/CSS/Report.CSS\">");
			writer.println("</HEAD>");
			writer.println("<body class = 'content'>");
			String[] arrchartSubjects = {"PASS", "FAIL", "WARNING", "NORUN"};
			int[] arrchartSubjectValues = { getResultCount(EachTestStatus.PASS),
					getResultCount(EachTestStatus.FAIL),
					getResultCount(EachTestStatus.WARNING),
					getResultCount(EachTestStatus.NORUN)};
			JFreeChart chart = Charts.Chart(chartTitle, arrchartSubjects, arrchartSubjectValues);                          
			ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setSectionPaint("PASS", Color.GREEN);
			plot.setSectionPaint("FAIL", Color.RED);
			plot.setSectionPaint("WARNING", Color.ORANGE);
			plot.setSectionPaint("NORUN", Color.CYAN);
			LegendTitle legend = new LegendTitle(plot);
			chart.addLegend(legend);
			plot.setSectionPaint("J-1", new Color(120, 0, 120));
			final File pngFile = new File(TestRunner.FilePath+"\\Summary\\piechart.png");
			ChartUtilities.saveChartAsPNG(pngFile, chart, 500, 305, info);
			writer.println("</BR>");
			writer.println("<center>");
			writer.println("<div class='tv'>");
			writer.println("</BR></BR></BR>");
			writer.println("<div class='chart'>");
			writer.println("<IMG SRC=\"piechart.png\" WIDTH=\"500\" HEIGHT=\"305\" BORDER=\"0\" USEMAP=\"#chart\">");
			writer.println("</div>");
			writer.println("</div>");
			writer.println("</center>");
			writer.println("</BODY>");
			writer.println("</HTML>");
			writer.close();
			System.out.println("Report Written");
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
			new File(outputDir+"\\Scenarios").mkdir();
			new File(outputDir+"\\FrameFiles").mkdir();
			new File(outputDir+"\\Summary").mkdir();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return FilePath;
	}

	public void createReportPage(){
		try{
			File htmlFile = new File(TestRunner.FilePath+"\\Report.html");
			final OutputStream out = new BufferedOutputStream(new FileOutputStream(htmlFile));
			final PrintWriter writer = new PrintWriter(out);
			writer.println("<html>");
			writer.println("<head>");
			writer.println("<title>Execution Report</title>");
			writer.println("</head>");
			writer.println("<frameset rows='142,*,50' frameborder='1' border='0' framespacing='0'>");
			writer.println("<frame name='topNav' src='FrameFiles\\header.html'>");
			writer.println("<frameset cols='400,*' frameborder='1' border='0' framespacing='0'>");
			writer.println("<frame name='menu' src='FrameFiles\\MenuList.html' marginheight='0' marginwidth='0'");
			writer.println("scrolling='auto' noresize>");
			writer.println("<frame name='content' src='Summary\\SummaryReport.html' marginheight='0'");
			writer.println("marginwidth='0' scrolling='auto' noresize>");
			writer.println("</frameset>");
			writer.println("<frame name='footer' src='FrameFiles\\Footer.html'>");
			writer.println("</frameset>");
			writer.println("<noframes>");
			writer.println("<p>This section (everything between the 'noframes' tags) will only "+
					"be displayed if the users' browser doesn't support frames. You can " +
					"provide a link to a non-frames version of the website here. Feel free " +
					"to use HTML tags within this section.</p>");
			writer.println("</noframes>");
			writer.println("</html>");
			writer.close();
			System.out.println("Header Written");
		}
		catch(Exception e){
			e.getStackTrace();
		}
	}

	public void generateHeader(String strCurrentDateandTime){
		try{
			String myCurrentDir = System.getProperty("user.dir");
			File htmlFile = new File(TestRunner.FilePath+"\\FrameFiles\\header.html");
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

	public List<String> generateMenuList(List<String> Scenarios){
		List<String> lst = null;
		try{
			String myCurrentDir = System.getProperty("user.dir");
			File MenuListhtmlFile = new File(TestRunner.FilePath+"\\FrameFiles\\MenuList.html");
			final OutputStream outMenuList = new BufferedOutputStream(new FileOutputStream(MenuListhtmlFile));
			final PrintWriter writer = new PrintWriter(outMenuList);
			writer.println("<HTML>");		
			writer.println("<HEAD>");
			writer.println("<title>Report List</title>");
			writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+myCurrentDir+"/CSS/Report.CSS\">");
			writer.println("</HEAD>");
			writer.println("<body class = 'menulist'>");
			writer.println("<div>");
			writer.println("<a href='"+TestRunner.FilePath+"\\Summary\\SummaryReport.html' target='content'>Summary Report</a>");
			writer.println("</div>");
			writer.println(nextLine);
			writer.println("<div><B>Scenario List</B></div>");
			writer.println("<table width='380' cellpadding=\"2\" cellspacing=\"2\">");
			lst = new ArrayList<String>();
			for(String strScenario : Scenarios){
				if(TestScriptStatus.containsKey(strScenario) && !lst.contains(strScenario)){
					lst.add(strScenario);
					writer.println("<tr>");
					writer.println("<td class = 'customfont'><a href='"+TestRunner.FilePath+"\\Scenarios\\"+strScenario+".html' target='content'><span 'customfont'>"+strScenario+"</span></a></td>");
					writer.println("<td width= '70' class = 'customfont'><span class = '"+getHTMLClass(TestScriptStatus.get(strScenario).toString())+"'>"+TestScriptStatus.get(strScenario)+"</span></td>");
					writer.println("</tr>");
				}
			}
			writer.println(nextLine);
			writer.println("</body>");
			writer.println("</HTML>");
			writer.close();
			System.out.println("Menu List Written");
		}
		catch(Exception e){
			e.getStackTrace();
		}
		return lst;
	}

	public PrintWriter initializeMenuContent(HashMap<String,String> ScenarioData){
		PrintWriter writer = null;
		try{
			String myCurrentDir = System.getProperty("user.dir");
			File MenuListhtmlFile = new File(TestRunner.FilePath+"\\Scenarios\\"+ScenarioData.get("ScenarioTag")+".html");
			final OutputStream outMenuList = new BufferedOutputStream(new FileOutputStream(MenuListhtmlFile));
			writer = new PrintWriter(outMenuList);
			writer.println("<HTML>");		
			writer.println("<HEAD>");
			writer.println("<title>"+ScenarioData.get("ScenarioTag")+" Report</title>");
			writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+myCurrentDir+"/CSS/Report.CSS\">");
			writer.println("</HEAD>");
			writer.println("<body class = 'content'");
			writer.println(nextLine);
			writer.println("<span class ='whitefont'>"+ScenarioData.get("FeatureTag")+"</span>");
			writer.println(nextLine);
			writer.println("<span class ='whitefont'>Feature : "+ScenarioData.get("Feature")+"</span>");
			writer.println(nextLine);
			writer.println(nextLine);
			writer.println("<span class ='whitefont'>"+ScenarioData.get("ScenarioTag")+"</span>");
			writer.println(nextLine);
			writer.println("<span class ='whitefont'>Scenario : "+ScenarioData.get("Scenario")+"</span>");
			writer.println(nextLine);
			writer.println(nextLine);
		}
		catch(Exception e){
			e.getStackTrace();
		}
		return writer;
	}

	public void finishMenuContent(PrintWriter writer){
		try{
			writer.println("</body>");
			writer.println("</HTML>");
			writer.close();
		}
		catch(Exception e){
			e.getStackTrace();
		}
	}

	public void appendMenuContent(PrintWriter writer){
		UserDefinedReturns UDR = null;
		writer.println("<table width='900' border=\"1\" cellpadding=\"5\" cellspacing=\"5\">");
		for(int i = 0; i < DLStoreMap.size(); i++ ){
			UDR = new UserDefinedReturns(DLStoreMap.get(i));
			getHTMLClass(UDR.Status.toString());
			writer.println("<tr>");
			writer.println("<td width= '70' class = 'customfont'><span id='bordercolor' class = 'customfont'>Step "+(i+1)+"</span></td>");
			writer.println("<td width= '200' class = 'customfont'><span id='bordercolor' class = 'customfont'>"+UDR.Summary+"</span></td>");
			writer.println("<td class = 'customfont'><span class = id='bordercolor' 'customfont'>"+UDR.Description+"</span></td>");
			writer.println("<td width= '70' class = 'customfont'><span id='bordercolor' class = '"+getHTMLClass(UDR.Status.toString())+"'>"+UDR.Status+"</span></td>");
			writer.println("</tr>");
		}
		writer.println("</table>");
		writer.println("</div>");
		writer.println("<div class ='"+getHTMLClass(getDLStatus().toString())+"'><B>Status : </B><B><I>"+getDLStatus()+"</B></I></div>");
		writer.println(nextLine);
	}

	private String getHTMLClass(String status){
		String HTMLclass = null;
		if(status.equals("PASS")){
			HTMLclass = "passed";
		}else if(status.equals("FAIL")){
			HTMLclass = "failed";
		}else if(status.equals("WARNING")){
			HTMLclass = "warned";
		}else{
			HTMLclass = "norun";
		}
		return HTMLclass;
	}

	public void generateFooter(int TotalTCs){
		try{
			String myCurrentDir = System.getProperty("user.dir");
			File htmlFile = new File(TestRunner.FilePath+"\\FrameFiles\\Footer.html");
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
			writer.println("<span class='passed'>Passed : "+getResultCount(EachTestStatus.PASS)+"</span>");
			writer.println("<span class='failed'>Failed : "+getResultCount(EachTestStatus.FAIL)+"</span>");
			writer.println("<span class='warned'>Warned : "+getResultCount(EachTestStatus.WARNING)+"</span>");
			writer.println("<span class='norun'>No Run : "+getResultCount(EachTestStatus.NORUN)+"</span>");
			writer.println("</B>");
			writer.println(nextLine);
			writer.println(nextLine);
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