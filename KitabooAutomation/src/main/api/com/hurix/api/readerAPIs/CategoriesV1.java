package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class CategoriesV1 {
	
	public static String GETcategoriesV1Path;
	public static Response categoriesV1(String userToken,String deviceID,String deviceType)
	{
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("categoriesV1");
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/categories");
			
			Log.info("CategoriesV1Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}


}
