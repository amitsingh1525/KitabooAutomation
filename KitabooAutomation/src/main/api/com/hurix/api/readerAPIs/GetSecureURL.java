package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class GetSecureURL {
	
	public static String GETgetSecureURLPath;
	public static Response getSecureURL()
	{
		//GETgetSecureURLPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/secure/aasd/IPAD/getSecureURL?entryID&type=3";
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("getSecureURL");
			//System.out.println("GETfetBookListRequestURL:" +GETgetSecureURLPath);
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)						
					.get("/DistributionServices/services/api/reader/secure/aasd/IPAD/getSecureURL?entryID&type=3");
			
			Log.info("GetSecureURLResponse: "+jsonResponse.then().extract().response().prettyPrint());
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
