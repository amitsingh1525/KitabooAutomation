package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class GeneratePin {
	
	public static Response generatePin(String usertoken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("GeneratePin.device:"+deviceType+"");
			jsonResponse = given()
				    .header("Content-Type","application/json")
					.queryParam("usertoken", usertoken)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/generatePin");
			Log.info("GeneratePin.device:"+deviceType+" Response: "+jsonResponse.then().extract().response().prettyPrint());
			
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
