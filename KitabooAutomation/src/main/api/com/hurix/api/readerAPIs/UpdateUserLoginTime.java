package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class UpdateUserLoginTime {
	
	public static String unMarkAsFavouritePath;
	public static Response updateUserLoginTime(String userToken,int userID)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("updateUserLoginTime");
			//System.out.println("GETunMarkAsFavouriteURL:" +unMarkAsFavouritePath);
			jsonResponse = given()
							.header("usertoken",userToken)						
							.get("/DistributionServices/services/api/reader/user/"+userID+"/updateUserLoginTime");
			
			Log.info("updateUserLoginTime : "+jsonResponse.then().extract().response().prettyPrint());
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
