package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;

import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.ExtractCategory;
import com.hurix.api.utility.MD5Genration;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class FetchCategoriesCollectionsBooks_Hash {
	
	public static Response fetchCategoriesCollectionsBooks_Hash()
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("FetchCategoriesCollectionsBooks_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/123454/PC/books/fetchCategoriesCollectionsBooks"));
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)	
					.header("category",RestAssured.catname)
					.header("collection",RestAssured.collectionName1)
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/123454/PC/books/fetchCategoriesCollectionsBooks"))
					.get("/DistributionServices/services/api/reader/books/123454/PC/books/fetchCategoriesCollectionsBooks");
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "collection");
			Validation.responseKeyValidation_key(jsonResponse, "category");
			Validation.responseKeyValidation_key(jsonResponse, "isbn");
			

			Log.info("FetchCategoriesCollectionsBooks_Hash Response: "+jsonResponse.then().extract().response().prettyPrint());
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
