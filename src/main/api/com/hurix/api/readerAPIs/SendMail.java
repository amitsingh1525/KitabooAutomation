package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class SendMail {
	private static String sendMailBODY;
	public static Response sendMail(String emailID,String assignedBy,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {//2020-08-07 08:43:20
			sendMailBODY = "{\"subject\":\"For Testing Purpose\",\"text\":\"This is Testing Thread.\",\"emailId\":"
					+ "\""+emailID+"\",\"assignedBy\":\""+assignedBy+"\"}";
			
			Log.startTestCase("SendMail");
			Log.info("sendMailBODY: "+sendMailBODY);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(sendMailBODY)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/sendMail");
										
			Log.info("SendMail Response: "+jsonResponse.then().extract().response().prettyPrint());
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
