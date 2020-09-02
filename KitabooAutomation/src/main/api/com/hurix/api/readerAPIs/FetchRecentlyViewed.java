package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchRecentlyViewed {
	
	public static Response fetchRecentlyViewed_without_pagi()
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("fetchRecentlyViewed_without_pagi");
		
			//System.out.println("GETfetchCategoriesCollectionsBooksRequestURL:" +GETfetchCategoriesCollectionsBooksPath);
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)
					.queryParam("bookid", +com.hurix.api.runner.RestAssured.bookID1)
					.get("/DistributionServices/services/api/reader/user/123/IPAD/fetchRecentlyViewed");
			
			Log.info("fetchRecentlyViewed_without_pagi Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response fetchRecentlyViewed_with_pagi(long startIndex, long endIndex)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("fetchRecentlyViewed_with_pagi");
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)
					.queryParam("bookid", +com.hurix.api.runner.RestAssured.bookID1)
					.header("startIndex", startIndex)
					.header("endIndex", endIndex)
					.get("/DistributionServices/services/api/reader/user/123/IPAD/fetchRecentlyViewed");
			
			Log.info("fetchRecentlyViewed_with_pagi Response: "+jsonResponse.then().extract().response().prettyPrint());
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
