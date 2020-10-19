package com.hurix.api.runner;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ChangePassword {
	
	private static String changePasswordBody;
	public static Response changePassword(String emailID,String password,String newPassword,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {//2020-08-07 08:43:20
			changePasswordBody = "{\"user\":{\"userName\":\""+emailID+"\",\"password\":"
					+ "\""+password+"\"},\"newPassword\":\""+newPassword+"\"}";
			
			Log.startTestCase("ChangePassword");
			Log.info("changePasswordBody: "+changePasswordBody);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(changePasswordBody)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/changePassword");
										
			Log.info("ChangePassword Response: "+jsonResponse.then().extract().response().prettyPrint());
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
