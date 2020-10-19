package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class FetchBookCount {
	
	//public static String GETfetchBookCountPath;
	public static Response fetchBookCount(String userToken,String DeviceID,String DeviceType)
	{
		//GETfetchBookCountPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/145644544/PC/fetchBookCount";
		
		Response jsonResponse = null;
		try {
			Log.startTestCase("fetchBookCount");
			//System.out.println("GETfetchBookCount RequestURL:" +GETfetchBookCountPath);
			jsonResponse = given()
					.header("usertoken",userToken)
						
					.get("/DistributionServices/services/api/reader/distribution/"+DeviceID+"/"+DeviceType+"/fetchBookCount");
			/*Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "totalbooks");*/
			
			Log.info("fetchBookCount Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}


}
