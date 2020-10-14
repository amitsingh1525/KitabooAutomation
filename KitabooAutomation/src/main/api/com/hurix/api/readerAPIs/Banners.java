package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class Banners {
	
	public static Response banners(String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("Banners");
			
			Log.info("URL :"+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/banners");
			Log.info("userToken : "+userToken);
					
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/banners");
			
			Log.info("Banners Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	public static Response banners_pagi(int startIndex,int endIndex, String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("Banners");
			
			Log.info("URL :"+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/banners");
			Log.info("userToken : "+userToken);
			Log.info("startIndex : "+startIndex);
			Log.info("userToken : "+userToken);
			jsonResponse = given()
					.header("usertoken", userToken)	
					.header("startIndex", startIndex)	
					.header("endIndex", endIndex)
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/banners");
			
			Log.info("Banners Response: "+jsonResponse.then().extract().response().prettyPrint());
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
