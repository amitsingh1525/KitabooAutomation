package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class UpdateUserLoginTime {
	
	public static String unMarkAsFavouritePath;
	public static Response updateUserLoginTime()
	{
		System.out.println("markfav_bookID: " +com.hurix.api.runner.RestAssured.BookID_mark1);
		//unMarkAsFavouritePath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/user/123/IPAD/unMarkAsFavourite?bookid="+com.hurix.api.runner.RestAssured.BookID_mark1+"";
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("GETunMarkAsFavourite");
			//System.out.println("GETunMarkAsFavouriteURL:" +unMarkAsFavouritePath);
			jsonResponse = given()
							.header("usertoken",com.hurix.api.runner.RestAssured.userToken)						
							.get("/DistributionServices/services/api/reader/user/"+com.hurix.api.runner.RestAssured.userID+"/updateUserLoginTime");
			
			Log.info("GETunMarkAsFavourite: "+jsonResponse.then().extract().response().prettyPrint());
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
