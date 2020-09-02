package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class MultiCategories {
	
	public static Response multiCategories(int level)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("MultiCategories");		
			//System.out.println("GETfetchCategoriesCollectionsBooksRequestURL:" +GETfetchCategoriesCollectionsBooksPath);
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)
					.header("level", level)
					.get("/DistributionServices/services/api/reader/user/123/IPAD/multiCategories");
			
			Log.info("MultiCategories Response: "+jsonResponse.then().extract().response().prettyPrint());
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
