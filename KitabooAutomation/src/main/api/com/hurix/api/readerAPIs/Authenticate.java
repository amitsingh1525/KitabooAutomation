package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class Authenticate {
	
	public static Response authenticate(String clientID, String username, String password,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		String authBody = "{\"user\":{\"userName\":\""+username+"\",\"password\":\""+password+"\"}}";
		try {
			Log.startTestCase("Authenticate");
			jsonResponse = given()
				    .header("Content-Type","application/json")
					.queryParam("clientID", clientID)
					.body(authBody)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/authenticateUser");
			/*System.out.println("BODY :: "+jsonResponse.getBody().asString());
			System.out.println("StatusCode :: " +jsonResponse.getStatusCode());
			System.out.println("Time :: " +jsonResponse.getTime());	*/
			
			/*System.out.println("HERE_Before");
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "userName");
			Validation.responseKeyValidation_key(jsonResponse, username);
			System.out.println("HERE_After");*/
			/*String userToken =jsonResponse.getBody().path("userToken");
			Assert.assertEquals(jsonResponse.getBody().path("userToken"), isPresent());*/
			//response.then().body("userToken", isPresent());
			//Assert.assertEquals(response.path("userToken"), isPresent());
			//Log.info("Authenticate Response: "+jsonResponse.then().body("user.userName", containsString("ent_lear_cat4@yopmail.com")));
			Log.info("Authenticate Response: "+jsonResponse.then().extract().response().prettyPrint());
			
			//Log.info("Authenticate Response: " +Assert.assertEquals(jsonResponse.getBody().path("userToken"), isPresent()));
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
