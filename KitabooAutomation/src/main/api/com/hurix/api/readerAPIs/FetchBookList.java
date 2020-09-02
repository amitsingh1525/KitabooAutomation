package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class FetchBookList {

	public static String fetchBookListPath;
	public static Response fetchBookList_without_pagination()
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("fetchBookList_without_pagination");
			System.out.println("GETfetchBookList RequestURL:" +fetchBookListPath);
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)
					.get("/DistributionServices/services/api/reader/distribution/145644544/PC/fetchBookList");
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);

			Log.info("fetchBookList Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	public static Response fetchBookList_with_pagination()
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("fetchBookList_with_pagination");
			System.out.println("GETfetchBookList RequestURL:" +fetchBookListPath);
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)
					.header("StartIndex",0)
					.header("endIndex",100)
					.get("/DistributionServices/services/api/reader/distribution/145644544/PC/fetchBookList");
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);
			Log.info("fetchBookList Response: "+jsonResponse.then().extract().response().prettyPrint());
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
