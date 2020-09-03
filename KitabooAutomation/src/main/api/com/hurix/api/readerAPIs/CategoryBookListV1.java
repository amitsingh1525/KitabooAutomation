package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class CategoryBookListV1 {
	
	public static String GETcategoryBookListV1Path;

	public static Response categoryBookListV1(String catname,String userToken,String DeviceID,String DeviceType)
	{
		//GETcategoryBookListV1Path = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/books/145644544/PC/books/"+catname+"";
		
		Response jsonResponse = null;
		try {			
			Log.startTestCase("categoryBookListV1");
			Log.info("catname  : "+catname);
			System.out.println("GETcategoryBookListV1 RequestURL:" +GETcategoryBookListV1Path);
			jsonResponse = given()
					.header("usertoken",userToken)	
					.get("/DistributionServices/services/api/reader/books/"+DeviceID+"/"+DeviceType+"/books/"+catname+"");
			
			Log.info("categoryBookListV1 Response: "+jsonResponse.then().extract().response().prettyPrint());
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
