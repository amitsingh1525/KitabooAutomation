package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.automation.utility.Log;

public class FetchCategoriesCollectionsBooks_Hash {
	
	public static Response fetchCategoriesCollectionsBooks_Hash(String userToken,String catname,String collectionName1,String deviceID,String deviceType)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("FetchCategoriesCollectionsBooks_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks"));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("category",catname)
					.header("collection",collectionName1)
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks"))
					.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");
			

			Log.info("FetchCategoriesCollectionsBooks_Hash Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
