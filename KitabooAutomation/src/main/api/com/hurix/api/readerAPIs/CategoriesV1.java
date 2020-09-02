package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class CategoriesV1 {
	
	public static String GETcategoriesV1Path;
	public static Response categoriesV1()
	{
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("categoriesV1");
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)						
					.get("/DistributionServices/services/api/reader/books/145644544/PC/books/categories");
			
			Log.info("CategoriesV1Response: "+jsonResponse.then().extract().response().prettyPrint());
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
