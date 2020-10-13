package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class RefreshCategory {

	public static Response refreshCategory(Object categoryIdList,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {

			Log.info("categoryIdList : "+categoryIdList);
			if(categoryIdList.toString().contains("||"))
			{
				Log.info("categoryIdList contains || and in the if condition");
				categoryIdList = categoryIdList.toString().substring(0,2).trim();
				Log.info("categoryIdList : "+categoryIdList);
				categoryIdList=categoryIdList +"]";
			}
			Log.startTestCase("RefreshCategory.device:"+deviceType+"");
			Log.info("categoryIdList : "+categoryIdList);
			Log.info("URL : "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/refreshCategory");
			jsonResponse = given()
					.header("Content-Type","application/json")
					.queryParam("usertoken", userToken)
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
