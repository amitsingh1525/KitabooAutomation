package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.automation.utility.Log;

public class UnMarkAsFavouriteHash {
	
	public static Response unMarkAsFavourite(int markAsFacbookID1,String userToken,String deviceID,String deviceType)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("UnMarkAsFavourite_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/unMarkAsFavourite?bookid="+markAsFacbookID1+""));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/unMarkAsFavourite?bookid="+markAsFacbookID1+""))
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/unMarkAsFavourite?bookid="+markAsFacbookID1+"");
					

			Log.info("UnMarkAsFavourite Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}


}
