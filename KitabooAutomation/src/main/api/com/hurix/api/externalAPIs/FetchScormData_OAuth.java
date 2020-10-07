package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchScormData_OAuth {
	public static Response fetchScormData_OAuth(String consumerKey, String consumerSecret,int clientClassID,int userID)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("FetchScormData_OAuth");
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.queryParam("client_Class_Id", clientClassID)
					.queryParam("userID", userID)
					.get("/DistributionServices/ext/api/fetchScormData");
					//.get("/DistributionServices/ext/api/fetchScormData?userID="+userID+"&client_Class_Id="+clientClassID+"");
						
			Log.info("FetchScormData_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}

}
