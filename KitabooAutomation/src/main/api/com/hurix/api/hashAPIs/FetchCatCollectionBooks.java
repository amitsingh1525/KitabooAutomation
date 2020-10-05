package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class FetchCatCollectionBooks {
	
	public static Response fetchCatCollectionBooks(String userToken,String deviceId,String deviceType,String catname,String collectionName1)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("FetchCategoriesCollectionsBooks_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/"+deviceId+"/"+deviceType+"/books/fetchCatCollectionBooks"));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("category",catname)
					.header("collection",collectionName1)
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/"+deviceId+"/"+deviceType+"/books/fetchCatCollectionBooks"))
					.get("/DistributionServices/services/api/reader/books/"+deviceId+"/"+deviceType+"/books/fetchCatCollectionBooks");
			
			

			Log.info("FetchCategoriesCollectionsBooks_Hash Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}


}
