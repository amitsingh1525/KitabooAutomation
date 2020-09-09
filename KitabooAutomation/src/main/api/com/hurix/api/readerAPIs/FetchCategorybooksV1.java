package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class FetchCategorybooksV1 {
	
	
	public static Response fetchCategorybooksV1(String catname, String userToken)
	{
		//GETcategoryBookListV1Path = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/books/145644544/PC/books/"+catname+"";
		
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("FetchCategorybooksV1");
			jsonResponse = given()
					.header("usertoken",userToken)	
					.get("/DistributionServices/services/api/reader/books/145644544/IPAD/books/"+catname+"");					
			
			Log.info("FetchCategorybooksV1 Response: "+jsonResponse.then().extract().response().prettyPrint());
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
