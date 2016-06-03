package framework;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeGenerator {
	public static String getCurrentDateAndTime(){
		//***************** Current Date and Time *************************
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM_d_yy_h_m_s_S");
		System.out.println(date);
		System.out.println(sdf.format(date));
		String CurrentDateandTime = sdf.format(date);
		return CurrentDateandTime;
	}
	
	public static String getCustomTimeFormat(String strDateandTime){
		return strDateandTime;
	} 
}
