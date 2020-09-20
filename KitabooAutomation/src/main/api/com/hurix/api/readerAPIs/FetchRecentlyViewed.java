package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchRecentlyViewed {
	
	public static Response fetchRecentlyViewed_without_pagi(String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("fetchRecentlyViewed_without_pagi");
		
			//System.out.println("GETfetchCategoriesCollectionsBooksRequestURL:" +GETfetchCategoriesCollectionsBooksPath);
			jsonResponse = given()
					.header("usertoken",userToken)
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
	
	public static Response fetchRecentlyViewed_with_pagi(long startIndex, long endIndex,String userToken,String deviceID,String devicetype)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("fetchRecentlyViewed_with_pagi");
			jsonResponse = given()
					.header("usertoken",userToken)
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
	
	public static Response fetchRecentlyViewed_permu(String SortBy, String orderBy,String userToken,String deviceID,String devicetype)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("fetchRecentlyViewed_.SortBy="+SortBy+".orderBy="+orderBy+"");
			jsonResponse = given()
					.header("usertoken",userToken)
					.header("SortBy",SortBy)
					.header("orderBy",orderBy)					
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+devicetype+"/fetchRecentlyViewed");
			
			Log.info("fetchRecentlyViewed_.SortBy="+SortBy+".orderBy="+orderBy+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response fetchRecentlyViewed_permu_withpagi(String SortBy, String orderBy,int startIndex,int endIndex,String userToken,String deviceID,String devicetype)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("fetchRecentlyViewed_.SortBy="+SortBy+".orderBy="+orderBy+".startIndex="+startIndex+".endIndex="+endIndex+"");
			jsonResponse = given()
					.header("usertoken",userToken)
					.header("startIndex",startIndex)
					.header("endIndex",endIndex)
					.header("SortBy",SortBy)
					.header("orderBy",orderBy)								
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+devicetype+"/fetchRecentlyViewed");
			
			Log.info("fetchRecentlyViewed_.SortBy="+SortBy+".orderBy="+orderBy+".startIndex="+startIndex+".endIndex="+endIndex+" Response: "+jsonResponse.then().extract().response().prettyPrint());
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
