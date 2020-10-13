package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchMarkedReadBooksIds {
	public static Response fetchMarkedReadBooksIds(String userToken,String devideId,String deviceType)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("FetchMarkedReadBooksIds");		
			Log.info("URL:" +"/DistributionServices/services/api/readerExt/user/"+devideId+"/"+deviceType+"/fetchMarkedReadBooksIds");
			Log.info("userToken : "+userToken);
			jsonResponse = given()
					.header("usertoken",userToken)
					//.header("startIndex",startIndex)
					//.header("endIndex",endIndex)
					.get("/DistributionServices/services/api/readerExt/user/"+devideId+"/"+deviceType+"/fetchMarkedReadBooksIds");
			
			Log.info("FetchMarkedReadBooksIds Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
