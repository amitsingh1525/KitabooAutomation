package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchClientBookClassAnalytics {
	
	public static Response fetchClientBookClassAnalytics(int bookID1,String consumerKey, String consumerSecret)
	{
		Log.startTestCase("FetchClientBookClassAnalytics");
		Log.info("bookID1 : " +bookID1);
		Log.info("consumerKey : " +consumerKey);
		Log.info("consumerSecret : " +consumerSecret);		
		Log.info("URL : " +"/DistributionServices/ext/api/analytics/"+bookID1+"/fetchClientBookClassAnalytics");
		Response jsonResponse = null;
		try {
			//System.out.println("RequestURL:" +GETclientUserID_booksPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/analytics/"+bookID1+"/fetchClientBookClassAnalytics");					
							
			Log.info("FetchClientBookClassAnalytics Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response fetchClientBookClassAnalytics_useID(int bookID1,int userID,String consumerKey, String consumerSecret)
	{
		//String GETclientUserID_booksPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/"+com.hurix.api.runner.RestAssured.clientUserID+"/books?search="+search+"";
		Log.startTestCase("FetchClientBookClassAnalytics");
		Log.info("bookID1 : " +bookID1);
		Log.info("userID : " +userID);
		Log.info("consumerKey : " +consumerKey);
		Log.info("consumerSecret : " +consumerSecret);		
		Log.info("URL : " +"/DistributionServices/ext/api/analytics/"+userID+"/"+bookID1+"/fetchClientBookClassAnalytics");
		Response jsonResponse = null;
		try {
			//System.out.println("RequestURL:" +GETclientUserID_booksPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/analytics/"+userID+"/"+bookID1+"/fetchClientBookClassAnalytics");					
							
			Log.info("FetchClientBookClassAnalytics Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response fetchClientBookClassAnalytics_ClientClassID(int clientUserID,int bookID1,int clientClassID,String consumerKey, String consumerSecret)
	{
		//String GETclientUserID_booksPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/"+com.hurix.api.runner.RestAssured.clientUserID+"/books?search="+search+"";
		Log.startTestCase("FetchClientBookClassAnalytics");
		Log.info("bookID1 : " +bookID1);
		Log.info("clientClassID : " +clientClassID);
		Log.info("clientUserID : " +clientUserID);
		Log.info("consumerKey : " +consumerKey);
		Log.info("consumerSecret : " +consumerSecret);		
		Log.info("URL : " +"/DistributionServices/ext/api/analytics/"+clientUserID+"/"+bookID1+"/"+clientClassID+"/fetchClientBookClassAnalytics");
		Response jsonResponse = null;
		try {
			//System.out.println("RequestURL:" +GETclientUserID_booksPath);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")								
					.get("/DistributionServices/ext/api/analytics/"+clientUserID+"/"+bookID1+"/"+clientClassID+"/fetchClientBookClassAnalytics");					
							
			Log.info("FetchClientBookClassAnalytics Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
