package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class UnMarkAsFavourite {
	
	public static String unMarkAsFavouritePath;
	public static Response unMarkAsFavourite()
	{
		System.out.println("markfav_bookID: " +com.hurix.api.runner.RestAssured.BookID_mark1);
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("unMarkAsFavourite");
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)						
					.get("/DistributionServices/services/api/reader/user/123/IPAD/unMarkAsFavourite?bookid="+com.hurix.api.runner.RestAssured.BookID_mark1+"");
			
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
