package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.automation.utility.Log;

public class CategoriesV1Hash {

	public static Response categoriesV1Hash(String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("CategoriesV1_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/categories"));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/categories"))
					.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/categories");
					

			Log.info("CategoriesV1_Hash Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
