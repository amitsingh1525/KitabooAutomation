package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.automation.utility.Log;

public class FetchRecentlyViewedBooksSecuredHash {

	public static Response fetchRecentlyViewedBooksSecuredHash(String userToken,String deviceID,String deviceType,int bookID1)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("FetchRecentlyViewedBooksSecuredHash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchRecentlyViewed?bookid="+bookID1+""));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchRecentlyViewed?bookid="+bookID1+""))
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchRecentlyViewed?bookid="+bookID1+"");
			
			Log.info("FetchRecentlyViewedBooksSecuredHash Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
