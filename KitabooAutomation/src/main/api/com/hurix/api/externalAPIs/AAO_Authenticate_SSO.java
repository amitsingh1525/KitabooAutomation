package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class AAO_Authenticate_SSO {
	
	//public static String aao_Authenticate_SSOPath="https://secure-qa.aao.org/AAO.Controllers/api/SSOIntegration/Authenticate";
	private static String aao_Authenticate_SSOBody = "{\"user\":{\"firstName\":\"ent_learner\",\"lastName\":\"cat4\",\"userName\":\"ent_lear_cat4@yopmail.com\",\"password\":\"kitaboo!123\",\"clientUserID\":\"95750033\",\"email\":\"ent_lear_cat4@yopmail.com\"}}";


	public static Response aao_Authenticate_SSO(){
		//System.out.println("POSTresetDevices_clientUserIDPath: " +aao_Authenticate_SSOPath);		
		Response jsonResponse = null;
		try {
			Log.startTestCase("aao_Authenticate_SSO");
			jsonResponse = given()
									
					.header("Content-Type","application/x-www-form-urlencoded")
					.body(aao_Authenticate_SSOBody)
					.post("https://secure-qa.aao.org/AAO.Controllers/api/SSOIntegration/Authenticate");
			
			/*RequestSpecification request = ((RequestSpecification) RestAssured.given()
					.log().all().config(RestAssured.config()
				     .encoderConfig(EncoderConfig.encoderConfig()
				     .encodeContentTypeAs("x-www-form-urlencoded",
				     ContentType.URLENC)))
				     .contentType("x-www-form-urlencoded")
				      .formParam("Accesskey", "RrJps^3V!$LZMJvhmOZC")
				      .formParam("Username", "spage@aao.org")
				      .formParam("Password", "password")
				      //.header("accept", "application/json")
				      .post("https://secure-qa.aao.org/AAO.Controllers/api/SSOIntegration/Authenticate")
				     /*
				     .header("accept", "application/json")
				     .header("123", "abc")
				     .header("456", "ttt")
				     .header("channel", "1111").request();*/
			
			Log.info("aao_Authenticate_SSO Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}
	public static void   main(String []args) throws SQLException{

		aao_Authenticate_SSO();
	}

}
