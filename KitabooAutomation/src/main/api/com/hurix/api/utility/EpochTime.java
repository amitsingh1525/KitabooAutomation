package com.hurix.api.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EpochTime {

	public static long getEpochTime(String date){
		long unixTime = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date dateFormate = sdf.parse(date);
			long millis = dateFormate.getTime();
			unixTime = millis / 1000L;
		} catch (Exception e) {
			e.getMessage();
		}
		return unixTime;
	}
}
