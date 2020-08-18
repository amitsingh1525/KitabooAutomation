package com.hurix.api.runner;

import io.restassured.response.Response;

import com.hurix.api.externalAPIs.GetRawTrackingData;
import com.hurix.api.utility.EpochTime;
import com.hurix.automation.utility.Log;



public class RestAssured {

	static String consumerKey = "LTE6Y2xpZW50MjE5NToyMTk1";
	static String consumerSecret = "7dba1849ad00b2f7eaf417faec8ab8e9f5529405";
	static String userToken = "";
	
	public static void main(String []args){
		Log.initialization("RestAssuredAPI");
		
		io.restassured.RestAssured.baseURI = "https://cloud.kitaboo.com";
		long startDate = EpochTime.getEpochTime("2019/04/30 23:45:15");
		long endDate = EpochTime.getEpochTime("2019/04/30 23:50:15");
		
		Response rawValue = GetRawTrackingData.getRawTracking(startDate, endDate, consumerKey, consumerSecret);
		//System.out.println(rawValue.then().extract().asString());
		String userID = rawValue.then().extract().path("trackings.userID[1]");
		System.out.println(rawValue.then().extract().path("trackings.userID[1]"));
	}
}
