package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import com.hurix.api.utility.ExcelUtils;
import com.hurix.automation.utility.Log;
import io.restassured.response.Response;


public class UpdateUser_captital_OAuth {

	/*private static String  lastName="cat";
	private static String  username="ent_learner";
	private static String  firstName="ent_learner";
	private static String  password="kitaboo!123";
	private static String email= "ent_lear_cat4@yopmail.com";*/
	public static String consumerKey = ""+ExcelUtils.Consumer_key+"";
	public static String consumerSecret = ""+ExcelUtils.secret_key+"";
	
	//public static String updateUser_OAuthPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/UpdateUser";
	
	//private static String updateUserBody = "{\"user\":{\"firstName\":\"ent_learner\",\"lastName\":\"cat4\",\"userName\":\"ent_lear_cat4@yopmail.com\",\"password\":\"kitaboo!123\",\"clientUserID\":\"95750033\",\"email\":\"ent_lear_cat4@yopmail.com\"}}";


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
			exp.printStackTrace();
		}
		//Log.endTestCase("End");
		return jsonResponse;

	}
	/*public static void   main(String []args) throws SQLException{

		updateUser_captital_OAuth(consumerKey,consumerSecret,"testingent","cat","");
	}*/
}
