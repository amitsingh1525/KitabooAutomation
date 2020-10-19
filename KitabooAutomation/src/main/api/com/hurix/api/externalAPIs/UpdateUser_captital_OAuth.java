package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import com.hurix.api.utility.ExcelUtils;
import com.hurix.automation.utility.Log;
import io.restassured.response.Response;


public class UpdateUser_captital_OAuth {

	public static String consumerKey = ""+ExcelUtils.Consumer_key+"";
	public static String consumerSecret = ""+ExcelUtils.secret_key+"";
	
	public static Response updateUser_captital_OAuth(String firstName,String lastName,String email,String userName ,String role ,int userID,String consumerKey, String consumerSecret)
	{
		//System.out.println("updateUser_OAuthPathPath: " +updateUser_OAuthPath);		
		Response jsonResponse = null;
		try {
			//Log.startTestCase("updateUser_captital_OAuth");
			jsonResponse = given().with()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")	
					//.contentType(ContentType.URLENC.withCharset("UTF-8"))
					.formParam("firstName", firstName)
					.formParam("lastName", lastName)
					.formParam("email", email)
					.formParam("userName",userName)
					.formParam("password", "kitaboo!123")
					.formParam("Role", role)
					.formParam("userID", userID)
					.post("/DistributionServices/ext/api/UpdateUser");			

			Log.info("updateUser_captital_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
			//Log.info("updateUser_captital_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			//exp.printStackTrace();
		}
		//Log.endTestCase("End");
		return jsonResponse;

	}
}
