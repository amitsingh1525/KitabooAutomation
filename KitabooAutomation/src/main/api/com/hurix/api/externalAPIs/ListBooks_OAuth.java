package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class ListBooks_OAuth {
	

	public static Response listBooks_OAuth_withoutpagi(String consumerKey, String consumerSecret)
	{			
		Response jsonResponse = null;
		try {
			Log.startTestCase("ListBooks_OAuth");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					.get("/DistributionServices/ext/api/ListBooks");
			/*Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);*/
			
			Log.info("ListBooks_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}
	public static Response listBooks_OAuth_With_Pagi(int startIndex, int endIndex,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("ListBooks_OAuth_With_Pagi");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					.header("startIndex",startIndex)
					.header("endIndex", endIndex)
					.post("/DistributionServices/ext/api/ListBooks");
			/*Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);*/
			
			Log.info("ListBooks_OAuth_With_Pagi Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to "+exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}

	
	public static Response listBooks_OAuth_With_PageNO(int pageno, int pageSize,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("listBooks_OAuth_With_PageNO Response.pageno="+pageno+"AND.pageSize="+pageSize+"");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					.header("pageno",pageno)
					.header("pageSize", pageSize)
					.post("/DistributionServices/ext/api/ListBooks");
			/*Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);*/
			
			Log.info("listBooks_OAuth_With_PageNO Response.pageno="+pageno+"AND.pageSize="+pageSize+": "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to "+exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}
}
