package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class RefreshBookList 
{	
	public static String refreshBookListPath;
	public static Response refreshBookList(String userToken,String deviceID, String deviceType)
	{
		//refreshBookListPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/123234234/PC/refreshBookList";
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("refreshBookList");
			//System.out.println("GETrefreshBookList:" +refreshBookListPath);
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)						
					.get("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/refreshBookList");
			
			Log.info("RefreshBookListResponse: "+jsonResponse.then().extract().response().prettyPrint());
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
