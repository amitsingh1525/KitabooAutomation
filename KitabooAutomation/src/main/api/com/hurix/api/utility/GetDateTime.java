package com.hurix.api.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDateTime {
	
	static String  date_time = null;
	
	public static String getDateTime(){

		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
		Date date = new Date();  
		date_time = formatter1.format(date);
		return date_time;
	}

}
