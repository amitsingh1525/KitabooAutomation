package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5Genration;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class FetchRecentlyViewedBooksSecuredHash {

	public static Response fetchRecentlyViewedBooksSecuredHash(String userToken,String deviceID,String deviceType,int bookID1)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("FetchRecentlyViewedBooksSecuredHash");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchRecentlyViewed?bookid="+bookID1+""));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchRecentlyViewed?bookid="+bookID1+""))
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/fetchRecentlyViewed?bookid="+bookID1+"");
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "bookId");
			Validation.responseKeyValidation_key(jsonResponse, "category");
			Validation.responseKeyValidation_key(jsonResponse, "archiveDate");


			Log.info("FetchRecentlyViewedBooksSecuredHash Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
