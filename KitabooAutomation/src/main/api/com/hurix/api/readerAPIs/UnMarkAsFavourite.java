package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.automation.utility.Log;

public class UnMarkAsFavourite {
	
	public static String unMarkAsFavouritePath;
	public static Response unMarkAsFavourite(int markbookID,String userToken, String deviceID,String deviceType)
	{
		System.out.println("markfav_bookID: " +markbookID);
		Response jsonResponse = null;
		try {
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/unMarkAsFavourite?bookid="+markbookID+""));
			
			Log.startTestCase("unMarkAsFavourite");
			jsonResponse = given()
					.header("usertoken",userToken)
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/unMarkAsFavourite?bookid="+markbookID+""))
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/unMarkAsFavourite?bookid="+markbookID+"");
			
			Log.info("UnMarkAsFavourite: "+jsonResponse.then().extract().response().prettyPrint());
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
