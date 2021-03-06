package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class CategoriesV2 {
	
	
	public static Response categoriesV2(String userToken, String deviceID,String deviceType)
	{
		//GETcategoriesV2Path = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/books/145644544/PC/books/v2/categories";
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("categoriesV2");
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/v2/categories");
			
			Log.info("CategoriesV2 Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}


}
