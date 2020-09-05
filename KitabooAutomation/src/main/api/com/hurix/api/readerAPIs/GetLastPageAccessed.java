package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class GetLastPageAccessed {
	public static Response getLastPageAccessed(String userToken,String DeviceID,String DeviceType)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("GetLastPageAccessed");
			//System.out.println("GETfetchBookList RequestURL:" +fetchBookListPath);
			jsonResponse = given()
					.header("usertoken",userToken)
					.get("DistributionServices/services/api/reader/books/"+DeviceID+"/"+DeviceType+"/348931329/getLastPageAccessed");
			/*Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
		Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
		Validation.responseTimeValidation(jsonResponse);*/
			Log.info("GetLastPageAccessed Response: "+jsonResponse.then().extract().response().prettyPrint());
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
