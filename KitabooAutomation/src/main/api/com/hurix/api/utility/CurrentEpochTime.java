package com.hurix.api.utility;

import java.time.Clock;
import java.util.TimeZone;

public class CurrentEpochTime {
	
	public static long currentEpoch;
	public static long getEpochTime(){
		
		//currentEpoch=Clock.system(TimeZone.getTimeZone("GMT-7").toZoneId() ).millis()/1000;
	    return Clock.system(TimeZone.getTimeZone("GMT").toZoneId() ).millis();
		//return currentEpoch;
	}

	public static void   main(String []args)
	{
		System.out.println("getEpochTime :: " +getEpochTime());
	}
}
