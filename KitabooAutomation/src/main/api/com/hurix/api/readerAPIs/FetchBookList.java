package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.runner.DIS_1811;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class FetchBookList {
	
	public static Response fetchBookList_without_pagination(String userToken,String DeviceID,String DeviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("fetchBookList_without_pagination");
			//System.out.println("GETfetchBookList RequestURL:" +fetchBookListPath);
			jsonResponse = given()
					.header("usertoken",userToken)
					.get("/DistributionServices/services/api/reader/distribution/"+DeviceID+"/"+DeviceType+"/fetchBookList");
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);
			//Validation.responseKeyValidation_key(jsonResponse, Title);

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
	public static Response fetchBookList_with_pagination(String userToken,String DeviceID,String DeviceType)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("fetchBookList_with_pagination");
			//System.out.println("GETfetchBookList RequestURL:" +fetchBookListPath);
			jsonResponse = given()
					.header("usertoken",DIS_1811.userToken)
					.header("StartIndex",0)
					.header("endIndex",100)
					.get("/DistributionServices/services/api/reader/distribution/"+DeviceID+"/"+DeviceType+"/fetchBookList");
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
