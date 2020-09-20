package com.hurix.api.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.hurix.automation.utility.Log;

public class EpochTime {

	public static long getEpochTime(String date){
		long unixTime = 0;
		try {
			 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.s");
			Date dateFormate = sdf.parse(date);
			long millis = dateFormate.getTime();
			unixTime = millis / 1000L;
		} catch (Exception e) {
			e.getMessage();
		}
		return unixTime;
	}
	
	public static long current(){
		//long unixTime = 0;
		long result=0;
		try {
			 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date1 = new Date();
			sdf.setTimeZone(TimeZone.getTimeZone("GMT-7"));
			String value = sdf.format(date1);
			System.out.println(value);
			Date date2 = sdf.parse(value);
			result = date2.getTime();
			Log.info("Time "+result);//1600267004000
		} catch (Exception e) {
			e.getMessage();
		}
		return result;
	}
}
