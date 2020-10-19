package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class SSIServlet {
	public static Response sSIServlet(int bookID1,String classID,String firstName,String lastname,int instituteID,String role,int userID,String consumerKey, String consumerSecret)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("SSIServlet");			
			Log.info("bookID : "+bookID1);
			Log.info("classID : "+classID);
			Log.info("firstname : " +firstName);
			Log.info("lastname : " +lastname);
			Log.info("instituteID : "+instituteID);
			Log.info("role : " +role);	
			Log.info("userId : " +userID);			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.queryParam("bookID", bookID1)
					.queryParam("classID", classID)
					.queryParam("firstname", firstName)
					.queryParam("lastname", lastname)
					.queryParam("instituteID", instituteID)
					.queryParam("role", role)
					.queryParam("userId", userID)
					.post("/DistributionServices/ext/api/SSIServlet");		

			Log.info("SSIServlet Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}
