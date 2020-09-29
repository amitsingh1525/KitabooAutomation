package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ACEP_logout {

	public static Response aCEP_logout(String userToken,String readerKey,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			
			Log.info("userToken :" +userToken);
			
			jsonResponse = given()
					.header("usertoken",userToken)
					.get("/DistributionServices/saml/web/sp/logout?clientID="+readerKey+"");
			
			Log.info("ACEP_logout Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
		}	
		Log.endTestCase("End");
		return jsonResponse;
	}
}
