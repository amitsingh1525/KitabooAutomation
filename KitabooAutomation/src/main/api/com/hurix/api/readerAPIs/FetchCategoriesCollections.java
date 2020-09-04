package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchCategoriesCollections {

	
	public static String GETfetchCategoriesCollectionsPath;
	public static Response fetchCategoriesCollections(String userToken,String deviceID,String deviceType)
	{
		//GETfetchCategoriesCollectionsPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/books/145644544/PC/books/fetchCategoriesCollections";
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("fetchCategoriesCollections");
			//System.out.println("GETfetchCategoriesCollectionsRequestURL:" +GETfetchCategoriesCollectionsPath);
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollections");
			
			Log.info("FetchCategoriesCollections Response: "+jsonResponse.then().extract().response().prettyPrint());
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
