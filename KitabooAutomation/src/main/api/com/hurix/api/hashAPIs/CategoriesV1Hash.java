package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class CategoriesV1Hash {


	public static Response categoriesV1Hash()
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("CategoriesV1_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/145644544/PC/fetchBookList"));
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/145644544/PC/books/categories"))
					.get("/DistributionServices/services/api/reader/books/145644544/PC/books/categories");
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);		

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
