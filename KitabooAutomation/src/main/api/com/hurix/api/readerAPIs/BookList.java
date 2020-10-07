package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class BookList {	

	public static Response bookList(String userToken,String deviceID, String DeviceType)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("bookList");
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+DeviceType+"/bookList");
			Log.info("bookListResponse: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response bookList_per(String SortBy,String orderBy,String userToken,String deviceID, String DeviceType)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("bookList.SortBy="+SortBy+".orderBy="+orderBy+"");
			jsonResponse = given()
					.header("usertoken",userToken)
					.header("SortBy",SortBy)
					.header("orderBy",orderBy)
					.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+DeviceType+"/bookList");
			Log.info("bookList.SortBy="+SortBy+".orderBy="+orderBy+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response bookList_per_withPagi(String SortBy,String orderBy,int startIndex,int endIndex,String userToken,String deviceID, String DeviceType)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("bookList.SortBy="+SortBy+".orderBy="+orderBy+".startIndex="+startIndex+".endIndex="+endIndex+"");
			jsonResponse = given()
					.header("usertoken",userToken)
					.header("SortBy",SortBy)
					.header("orderBy",orderBy)
					.header("startIndex",startIndex)
					.header("endIndex",endIndex)
					.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+DeviceType+"/bookList");
			Log.info("bookList.SortBy="+SortBy+".orderBy="+orderBy+".startIndex="+startIndex+".endIndex="+endIndex+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
