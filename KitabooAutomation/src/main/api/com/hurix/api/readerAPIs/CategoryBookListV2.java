package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class CategoryBookListV2 {
	
	public static Response categoryBookListV2(String catname,String userToken,String deviceId,String DeiceType)
	{
		//GETfetchBookCountPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/145644544/PC/fetchBookCount";
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("CategoryBookListV2");
			
			jsonResponse = given()
					.header("usertoken",userToken)
					.header("category",catname)				
					.get("/DistributionServices/services/api/reader/books/"+deviceId+"/"+DeiceType+"/books/v2/categoryBookList");
			
			Log.info("CategoryBookListV2 Response: "+jsonResponse.then().extract().response().prettyPrint());
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
