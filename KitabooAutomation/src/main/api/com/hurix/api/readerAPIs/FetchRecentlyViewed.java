package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchRecentlyViewed {
	
	public static Response fetchRecentlyViewed_without_pagi(String userToken,String deviceID,String deviceType,int bookID1)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("fetchRecentlyViewed_without_pagi");
		
			//System.out.println("GETfetchCategoriesCollectionsBooksRequestURL:" +GETfetchCategoriesCollectionsBooksPath);
			jsonResponse = given()
					.header("usertoken",userToken)
					.queryParam("bookid",bookID1)
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchRecentlyViewed");
			
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
	
	public static Response fetchRecentlyViewed_with_pagi(long startIndex, long endIndex,String userToken,String deviceID,String devicetype,int bookID1)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("fetchRecentlyViewed_with_pagi");
			jsonResponse = given()
					.header("usertoken",userToken)
					.queryParam("bookid", +bookID1)
					.header("startIndex", startIndex)
					.header("endIndex", endIndex)
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+devicetype+"/fetchRecentlyViewed");
			
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
