package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class BookList {
	
	
	public static Response bookList()
	{
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("bookList");
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)						
					.get("/DistributionServices/services/api/reader/books/123234234/IPAD/bookList");
			
			Log.info("bookListResponse: "+jsonResponse.then().extract().response().prettyPrint());
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