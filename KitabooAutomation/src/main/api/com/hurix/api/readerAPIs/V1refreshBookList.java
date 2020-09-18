package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import io.restassured.response.Response;

import com.hurix.api.utility.*;
import com.hurix.automation.utility.*;

public class V1refreshBookList {

	private static String v1refreshBookListBody;
	public static Response v1refreshBookList(Object startDate,String operation1,String operation2,String bookID1,String bookID2,String userToken,String deviceID ,String DiviceType,String clientID)
	{
		Response jsonResponse = null;
		try {

			/*Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
			DateFormat formatter = new SimpleDateFormat("2019-12-31 12:50:23.0");    
			formatter.setTimeZone(TimeZone.getTimeZone("GMT+13"));
			String newZealandTime = formatter.format(calendar.getTime());
			Log.info("newZealandTime : "+newZealandTime);*/


			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datenew = df.parse(""+startDate+"");
			long epoch = datenew.getTime();
			epoch=epoch/1000;
			v1refreshBookListBody = "{\"type\":[\""+operation1+"\",\""+operation2+"\"],\"bookids\":["+bookID1+","+bookID2+"]}";
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			Log.startTestCase("v1refreshBookList");
			Log.info("Before startDate : "+startDate);
			
			Log.info("epoch : "+epoch);
			//startDate=EpochTime.getEpochTime(""+startDate+"");

			Log.info("After startDate : "+startDate);
			Log.info("URL : "+"/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DiviceType+"/v1/refreshBookList?t="+epoch+"&clientID="+clientID+"");
			//System.out.println("v1refreshBookListBodyRequestURL:" +POSTv1refreshBookListath);
			System.out.println("searchV2Body: "+v1refreshBookListBody);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(v1refreshBookListBody)					
					.post("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DiviceType+"/v1/refreshBookList?t="+epoch+"&clientID="+clientID+"");

			Log.info("v1refreshBookList Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}	

	public static Response v1refreshBookList_with_pagi(int startIndex, long endIndex,Object startDate,String operation1,String operation2,String bookID1,String bookID2,String userToken,String deviceID ,String deviceType,String clientID)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("v1refreshBookList_with_pagi");
			//System.out.println("searchV2Body: "+v1refreshBookListBody);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.header("startIndex",startIndex)
					.header("endIndex", endIndex)
					.body(v1refreshBookListBody)					
					.post("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/v1/refreshBookList?t="+startDate+"&clientID="+clientID+"");
			/*Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "isbn");
			Validation.responseKeyValidation_key(jsonResponse, "formats");
			Validation.responseKeyValidation_key(jsonResponse, "id");*/				
			Log.info("v1refreshBookList_with_pagi Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}	
}
