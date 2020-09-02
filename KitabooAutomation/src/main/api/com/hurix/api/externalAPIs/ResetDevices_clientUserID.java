package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.utility.ExcelUtils;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class ResetDevices_clientUserID {
	//static List<String> detail =  ExcelUtils.getuserDetails();
	public static String consumerKey =""+ExcelUtils.Consumer_key+"";
	public static String consumerSecret = ""+ExcelUtils.secret_key+"";

	//public static String POSTresetDevices_clientUserIDPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/resetDevices?clientUserID="+com.hurix.api.runner.RestAssured.clientUserID+"";
	

	public static Response resetDevices_clientUserID(String consumerKey, String consumerSecret){
		//List<String> detail =  ExcelUtils.getuserDetails();
		//io.restassured.RestAssured.baseURI = detail.get(0);
		//Response authenticateValue = Authenticate.authenticate();
		//System.out.println("POSTresetDevices_clientUserIDPath: " +POSTresetDevices_clientUserIDPath);		
		Response jsonResponse = null;
		try {
			Log.startTestCase("resetDevices_clientUserID");
			System.out.println("ck="+consumerKey);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.post("/DistributionServices/ext/api/resetDevices?clientUserID="+com.hurix.api.runner.RestAssured.clientUserID+"");
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);
			
			Log.info("ResetDevices_clientUserID Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}
	
	/*public static void main( String[] args ) throws Exception
	{
		Authenticate.authenticate();
		ResetDevices_clientUserID.resetDevices_clientUserID(consumerKey, consumerSecret);
	}*/

}
