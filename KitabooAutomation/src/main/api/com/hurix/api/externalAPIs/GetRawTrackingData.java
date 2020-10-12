package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hurix.api.utility.EpochTime;
import com.hurix.automation.utility.Log;

import io.restassured.response.Response;

public class GetRawTrackingData{

	public static Response getRawTracking(String startDate, String endDate, String consumerKey, String consumerSecret){

		Response jsonResponse = null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datenew = df.parse(""+startDate+"");
			long startDate1 = datenew.getTime();
			startDate1=startDate1/1000;
			
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datenew1 = df1.parse(""+endDate+"");
			long endDate1 = datenew1.getTime();
			endDate1=endDate1/1000;
			
			Log.startTestCase("GetRawTrackingData");
			Log.info("startDate1 : "+startDate1);
			Log.info("endDate1 : "+endDate1);
			long fetit = EpochTime.getEpochTime("2019-04-30 18:15:15");
			Log.info("startDate : "+fetit);
			Log.info("URL : "+"/DistributionServices/ext/api/getRawTrackingData");
			jsonResponse = given()
					.queryParam("startDate",startDate1)
					.queryParam("endDate", endDate1)
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.get("/DistributionServices/ext/api/getRawTrackingData");

			Log.info("GetRawTrackingData Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	public static Response getRawTrackingV1(String startDate,String endDate,int pageNo,String consumerKey, String consumerSecret){

		Response jsonResponse = null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datenew = df.parse(""+startDate+"");
			long startDate1 = datenew.getTime();
			startDate1=startDate1/1000;
			
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datenew1 = df1.parse(""+endDate+"");
			long endDate1 = datenew1.getTime();
			endDate1=endDate1/1000;
			
			Log.startTestCase("getRawTracking");
			Log.info("startDate1 : "+startDate1);
			Log.info("endDate1 : "+endDate1);
			
			Log.info("URL : "+"/DistributionServices/ext/api/v1/getRawTrackingData");
			jsonResponse = given()
					.queryParam("startDate",startDate1)
					.queryParam("endDate", endDate1)
					.queryParam("pageNo", pageNo)
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.get("/DistributionServices/ext/api/v1/getRawTrackingData");

			Log.info("Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;

	}
}
