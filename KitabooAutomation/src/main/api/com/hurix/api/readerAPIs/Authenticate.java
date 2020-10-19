package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class Authenticate {

	public static Response authenticate(String clientID, String username, String password,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		Log.info("URL : "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/authenticateUser?clientID="+clientID+"");
		Log.info("BODY : "+"{\"user\":{\"userName\":\""+username+"\",\"password\":\""+password+"\"}}");
		Log.info("clientID : "+clientID);
		try {

			jsonResponse = given()
					.header("Content-Type","application/json")
					.queryParam("clientID", clientID)
					.body("{\"user\":{\"userName\":\""+username+"\",\"password\":\""+password+"\"}}")					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/authenticateUser");


		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());			
			Log.fail("Fails due to : "+exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}


	public static Response authenticateV1(String env, String username, String password,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			String clientID = "cHJvZi1raXRhYm9v";//DatabaseQuerry.getReaderKey(env, username);//"ZW50ZXJwcmlzZXByb2R1Y3Rpb25BZGFwdGVy";
			jsonResponse = given()
					.header("Content-Type","application/json")
					.queryParam("clientID", clientID)
					.body("{\"user\":{\"userName\":\""+username+"\",\"password\":\""+password+"\"}}")					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/authenticateUser");
			Log.info("Time Taken: "+jsonResponse.time());
		} catch (Exception e) 
		{
			Log.errorAPI("ERROR: Failed to getting response from authenticate API. "+e.getMessage());
		}
		return jsonResponse;
	}

}
