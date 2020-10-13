package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class Vote {
	
public static String VoteBODY;
	
	public static Response Vote_like(int bookID1,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			VoteBODY = "{\"like\":1,\"bookId\":"+bookID1+"}";
			Log.startTestCase("Vote_like.bookID1="+bookID1+"");
			Log.info("Vote_URL: "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/vote");
			Log.info("VoteBODY : "+VoteBODY);
			Log.info("userToken : "+userToken);
		
			jsonResponse = given()
					 .header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(VoteBODY)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/vote");					
							
			Log.info("Vote_like.bookID1="+bookID1+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
	
	public static Response Vote_Dislike(int bookID1,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			VoteBODY = "{\"like\":-1,\"bookId\":"+bookID1+"}";
			Log.startTestCase("Vote_Dislike.bookID1="+bookID1+"");
			
			Log.info("Vote_URL: "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/vote");
			Log.info("VoteBODY : "+VoteBODY);
			Log.info("userToken : "+userToken);
		
			jsonResponse = given()
					 .header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(VoteBODY)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/vote");					
							
			Log.info("Vote_Dislike.bookID1="+bookID1+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
	
	public static Response Vote_Reset(int bookID1,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			VoteBODY = "{\"like\":0,\"bookId\":"+bookID1+"}";
			Log.startTestCase("Vote_Reset.bookID1="+bookID1+"");
			
			Log.info("Vote_URL: "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/vote");
			Log.info("VoteBODY : "+VoteBODY);
			Log.info("userToken : "+userToken);
		
			jsonResponse = given()
					 .header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(VoteBODY)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/vote");					
							
			Log.info("Vote_Reset.bookID1="+bookID1+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}

