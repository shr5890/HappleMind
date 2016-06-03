package framework.chartobjects;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Point;
import java.text.DecimalFormat;

import org.jfree.chart.*;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class Charts { 
	public static JFreeChart Chart(String chartTitle, String[] arrchartSubjects, int[] arrchartSubjectValues) {		
		PieDataset dataset = createDataset( arrchartSubjects, arrchartSubjectValues); 
		JFreeChart chart = createChart(dataset, chartTitle, arrchartSubjects);	
		return chart;
	}           
	public static PieDataset createDataset(String[] arrchartSubjects, int[] arrchartSubjectValues) {
		DefaultPieDataset result = new DefaultPieDataset();
		for(int i=0;i<arrchartSubjects.length;i++){
		result.setValue(arrchartSubjects[i], arrchartSubjectValues[i]);//retreive datas		
		}
		return result;
	}  	
	public static JFreeChart createChart(PieDataset dataset, String title, String[] arrchartSubjects) {    	
		JFreeChart chart = ChartFactory.createPieChart3D(
				title,        			  // chart title
				dataset,       	         // data
				true,                   // include legend
				true,
				false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setForegroundAlpha( 0.7f ); 
		plot.setSectionPaint("Windows", Color.cyan);
		plot.setInteriorGap( 0.04 );  		
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setStartAngle(90);
		plot.setCircular(true);	
		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		Color[] colors = PieColors.FillColors();
		PieColors.setColor(plot, dataset, colors);
		plot.setDarkerSides(false);	
		setExplodePercent(plot,arrchartSubjects);
		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
	            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
	        plot.setLabelGenerator(gen);
        plot.setSimpleLabels(false);
        chart.getLegend().setBackgroundPaint(Color.pink);                 
//        chart.setPadding(new RectangleInsets(2,2,2,2));        
//        chart.getTitle().setBackgroundPaint(Color.yellow);
        chart.setBackgroundPaint(new GradientPaint(new Point(0, 0), Color.yellow, new Point(600, 0), Color.cyan));
//        chart.getPlot().setBackgroundPaint(Color.magenta);
		return chart;
	} 
	public static void setExplodePercent(PiePlot3D plot, String[] arrchartSubjects) {	
		for(int i=0;i<arrchartSubjects.length;i++){
		plot.setExplodePercent(arrchartSubjects[i], 0.10);	
		}
	} 
}