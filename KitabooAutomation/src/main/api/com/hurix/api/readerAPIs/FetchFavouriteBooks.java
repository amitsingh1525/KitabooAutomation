package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchFavouriteBooks {
	
	public static Response fetchFavouriteBooks(String userToken,String devideId,String DeviceType)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("FetchFavouriteBooks");		
			//System.out.println("GETfetchCategoriesCollectionsBooksRequestURL:" +GETfetchCategoriesCollectionsBooksPath);
			jsonResponse = given()
					.header("usertoken",userToken)
					.get("/DistributionServices/services/api/reader/user/"+devideId+"/"+DeviceType+"/fetchFavouriteBooks");
			
			Log.info("FetchFavouriteBooks Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response fetchFavouriteBooks_per(String SortBy,String orderBy,String userToken,String devideId,String DeviceType)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("FetchFavouriteBooks.SortBy="+SortBy+"orderBy="+orderBy+"");		
			//System.out.println("GETfetchCategoriesCollectionsBooksRequestURL:" +GETfetchCategoriesCollectionsBooksPath);
			jsonResponse = given()
					.header("usertoken",userToken)
					.header("SortBy",SortBy)
					.header("orderBy",orderBy)
					.get("/DistributionServices/services/api/reader/user/"+devideId+"/"+DeviceType+"/fetchFavouriteBooks");
			
			Log.info("FetchFavouriteBooks.SortBy="+SortBy+"orderBy="+orderBy+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	public static Response fetchFavouriteBooks_per_withPagi(String SortBy,String orderBy,int startIndex,int endIndex,String userToken,String devideId,String DeviceType)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("FetchFavouriteBooks.SortBy="+SortBy+"orderBy="+orderBy+"");		
			//System.out.println("GETfetchCategoriesCollectionsBooksRequestURL:" +GETfetchCategoriesCollectionsBooksPath);
			jsonResponse = given()
					.header("usertoken",userToken)
					.header("startIndex",startIndex)
					.header("endIndex",endIndex)
					.header("SortBy",SortBy)
					.header("orderBy",orderBy)
					.get("/DistributionServices/services/api/reader/user/"+devideId+"/"+DeviceType+"/fetchFavouriteBooks");
			
			Log.info("FetchFavouriteBooks.SortBy="+SortBy+"orderBy="+orderBy+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
