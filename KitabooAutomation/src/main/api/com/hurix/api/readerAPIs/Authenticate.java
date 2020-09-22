package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class Authenticate {
	
	public static Response authenticate(String clientID, String username, String password,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		Log.info("URL : "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/authenticateUser");
		try {
			
			jsonResponse = given()
				    .header("Content-Type","application/json")
					.queryParam("clientID", clientID)
					.body("{\"user\":{\"userName\":\""+username+"\",\"password\":\""+password+"\"}}")					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/authenticateUser");
			
			
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	/*private static ResponseAwareMatcher<Response> containsString(String string) {
		// TODO Auto-generated method stub
		return null;
	}*/
	/*public static void main( String[] args ) throws Exception
	{
		//Authenticate.authenticate();
	}*/

	
}
