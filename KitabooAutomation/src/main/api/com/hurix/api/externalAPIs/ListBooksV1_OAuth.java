package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class ListBooksV1_OAuth {
	
	
	public static Response listBooksV1_OAuth_without_pagi(String consumerKey, String consumerSecret){
		//System.out.println("POSTresetDevices_clientUserIDPath: " +GETListBooksV1_OAuthPath);
		
		Response jsonResponse = null;
		try {
			Log.startTestCase("ListBooksV1_OAuth");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					.post("/DistributionServices/ext/api/v1/ListBooks");
			
			
			Log.info("ListBooksV1_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response listBooksV1_OAuth_With_Pagi(long startIndex, long endIndex,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("ListBooksV1_OAuth_With_Pagi");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					.header("startIndex",startIndex)
					.header("endIndex",endIndex)
					.post("/DistributionServices/ext/api/v1/ListBooks");
			
			Log.info("ListBooksV1_OAuth_With_Pagi Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to "+exp.getCause());
			//exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
	
	public static Response listBooksV1_OAuth_With_PageNO(long pageno, long pagesize,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("ListBooksV1_OAuth_With_PAGENO.pageno="+pageno+"AND.pagesize="+pagesize+"");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					.header("pageno",pageno)
					.header("pagesize",pagesize)
					.post("/DistributionServices/ext/api/v1/ListBooks");
			
			Log.info("ListBooksV1_OAuth_With_PAGENO.pageno="+pageno+"AND.pagesize="+pagesize+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to "+exp.getCause());
			//exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}


}
