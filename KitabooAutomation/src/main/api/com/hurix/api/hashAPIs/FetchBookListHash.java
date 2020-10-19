package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.*;

import com.hurix.automation.utility.Log;

public class FetchBookListHash {	

	public static Response fetchBookListHash(String userToken,String deviceID,String deviceType)
	{
		
		Response jsonResponse = null;
		try {
			Log.startTestCase("FetchBookList_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/145644544/PC/fetchBookList"));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/145644544/PC/fetchBookList"))
					.get("/DistributionServices/services/api/reader/distribution/145644544/PC/fetchBookList");
				

			Log.info("FetchBookListHash Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
