package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.utility.EpochTime;
import com.hurix.automation.utility.Log;

public class RegisterUser {
	
	private static String registerUser;
	
	public static Response registerUser(String firstName,String lastName,String accessToken, String userToken,String deviceID,String deviceType)
	{
		//firstName+EpochTime.current()+""@yopmail.com"
		Response jsonResponse = null;
		try {			
			registerUser= "{\"user\":{\"firstName\":\""+firstName+"\",\"lastName\":\""+lastName+"\",\"email\":"
					+ "\""+firstName+EpochTime.current()+"@yopmail.com\",\"userName\":\""+firstName+EpochTime.current()+"@yopmail.com\","
					+ "\"password\":\"kitaboo@123\"},\"accessCode\":\""+accessToken+"\"}";
					
					
			Log.startTestCase("RegisterUser");
			Log.info("RegisterUserBody : "+registerUser);
			Log.info("userToken : "+userToken);
			Log.info("RegisterUserURL : "+"DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/registerUser");
			
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(registerUser)					
					.post("DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/registerUser");
					

			Log.info("RegisterUser Response: "+jsonResponse.then().extract().response().prettyPrint());
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
