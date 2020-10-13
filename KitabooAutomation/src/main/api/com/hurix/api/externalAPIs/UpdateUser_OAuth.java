package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class UpdateUser_OAuth {
	
	//public static String consumerKey = ""+com.hurix.api.utility.ExcelUtils.getConsumer_key()+"";
	//public static String consumerSecret = ""+com.hurix.api.utility.ExcelUtils.getsecret_key()+"";
	
	//public static String updateUser_OAuthPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/updateUser";
	private static String updateUserBody ;//= "{\"user\":{\"firstName\":\"ent_learner\",\"lastName\":\"cat4\",\"userName\":\"ent_lear_cat4@yopmail.com\",\"password\":\"kitaboo!123\",\"clientUserID\":\"95750033\",\"email\":\"ent_lear_cat4@yopmail.com\"}}";


	public static Response updateUser_OAuth(String email,String userName,String password,String firstName,String lastName,String clientUserID,String consumerKey, String consumerSecret)
	{
		//System.out.println("POSTresetDevices_clientUserIDPath: " +updateUser_OAuthPath);		
		Response jsonResponse = null;
		try {
			updateUserBody = "{\"user\":{\"firstName\":\"ent_learner\",\"lastName\":\"cat4\",\"userName\":\""+userName+"\","
					+ "\"password\":\""+password+"\",\"clientUserID\":\""+clientUserID+"\",\"email\":\""+email+"\"}}";

			Log.startTestCase("updateUser_OAuth");
			Log.info("updateUserBody : "+updateUserBody);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(updateUserBody)
					.post("/DistributionServices/ext/api/updateUser");
			
			Log.info("updateUser_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}

}
