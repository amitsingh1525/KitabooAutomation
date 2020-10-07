package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class FetchCatBookList {
	public static Response fetchCatBookList(String catname,String userToken,String deviceID,String deviceType)
	{
		//GETcategoryBookListV1Path = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/books/145644544/PC/books/"+catname+"";

		Response jsonResponse = null;
		try {

			Log.startTestCase("FetchCatBookList_Hash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/v2/fetchCatBookList"));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("category",catname)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/v2/fetchCatBookList"))
					.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/v2/fetchCatBookList");
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);		

			Log.info("FetchCatBookList Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
