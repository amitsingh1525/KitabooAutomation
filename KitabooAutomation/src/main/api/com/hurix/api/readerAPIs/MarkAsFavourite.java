package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class MarkAsFavourite {
	
	//public static String POSTmarkAsFavouritePath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/user/123/IPAD/markAsFavourite";

	public static String POSTmarkAsFavouriteBody = "{\"bookId\":\""+com.hurix.api.runner.RestAssured.bookID1+"\"}";

	public static Response markAsFavourite()
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("markAsFavourite");
			//System.out.println("POSTmarkAsFavouriteRequestURL:" +POSTmarkAsFavouritePath);
			System.out.println("POSTmarkAsFavouriteBody: "+POSTmarkAsFavouriteBody);
			jsonResponse = given()
					 .header("Content-Type","application/json")
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)
					.body(POSTmarkAsFavouriteBody)					
					.post("/DistributionServices/services/api/reader/user/123/IPAD/markAsFavourite");
					
							
			Log.info("MarkAsFavourite Response: "+jsonResponse.then().extract().response().prettyPrint());
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
