package framework.chartobjects;

import java.awt.Color;
import java.util.List;

import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;

public class PieColors{         
	public static Color[]  FillColors(){ 
		Color[] colors = {Color.cyan, Color.yellow, Color.green, new Color(228, 114, 151), Color.orange};
		return colors;         
	}        

	public static void setColor(PiePlot plot, PieDataset dataset, Color[] color){ 
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List <Comparable> keys = dataset.getKeys(); 
		int aInt;        
		for (int i = 0; i < keys.size(); i++){ 
			aInt = i % color.length; 
			plot.setSectionPaint(keys.get(i), color[aInt]); 
		} 
	}
}

// Can give other colors :
//new Color(227, 26, 28),
//new Color(000,102, 204),
//new Color(102,051,153),
//new Color(102,51,0),
//new Color(156,136,48),
//new Color(153,204,102),
//new Color(153,51,51),
//new Color(102,51,0),
//new Color(204,153,51),
//new Color(0,51,0),
