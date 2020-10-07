package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class RefreshCategory {
	
	public static Response refreshCategory(Object categoryIdList,String usertoken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("RefreshCategory.device:"+deviceType+"");
			Log.info("categoryIdList : "+categoryIdList);
			jsonResponse = given()
				    .header("Content-Type","application/json")
					.queryParam("usertoken", usertoken)
					.body("{\"categoriesIdList\":"+categoryIdList+"}")					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/refreshCategory");
			Log.info("RefreshCategory.device:"+deviceType+" Response: "+jsonResponse.then().extract().response().prettyPrint());
			
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
