package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class FetchCategoriesCollectionsBooks {
	public static String GETfetchCategoriesCollectionsBooksPath;
	
	public static Response fetchCategoriesCollectionsBooks(String userToken,String deviceID,String deviceType,String catname1,String collecName1)
	{
		//GETfetchCategoriesCollectionsBooksPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/books/123454/PC/books/fetchCategoriesCollectionsBooks";
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("fetchCategoriesCollectionsBooks");
		
			//System.out.println("GETfetchCategoriesCollectionsBooksRequestURL:" +GETfetchCategoriesCollectionsBooksPath);
			
			jsonResponse = given()
					.header("usertoken",userToken)
					.header("category",catname1)
					.header("collection",collecName1)
					.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");
			
			Log.info("FetchCategoriesCollectionsBooks Response: "+jsonResponse.then().extract().response().prettyPrint());
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
