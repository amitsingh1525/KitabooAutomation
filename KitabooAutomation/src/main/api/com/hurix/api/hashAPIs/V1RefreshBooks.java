package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;

import io.restassured.response.Response;

import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.CurrentEpochTime;
import com.hurix.api.utility.EpochTime;
import com.hurix.api.utility.MD5Genration;
import com.hurix.api.utility.MD5GenrationWithBodyData;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class V1RefreshBooks {

	public static String v1refreshBooksBody ="{\"bookids\":["+RestAssured.bookID1+"]}";

	public static Response v1refreshBooks(long startDate)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("V1RefreshBooks_Hash");
			System.out.println("current time  :: "+CurrentEpochTime.getEpochTime());
			System.out.println("Header HASH == " +MD5GenrationWithBodyData.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/123/PC/v1/refreshBook?t="+CurrentEpochTime.getEpochTime()+""));
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)	
					.body(v1refreshBooksBody)
					//.header("hash","MlFT4oxTtWrruEfHPuin72kwD8Do6spMZ0vT6mX3LqWgFnbJ+jaYvE01E/iv4N2m")
					.header("hash",MD5GenrationWithBodyData.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/123/PC/v1/refreshBooks?t="+CurrentEpochTime.getEpochTime()+""))
					.post("/DistributionServices/services/api/reader/distribution/123/PC/v1/refreshBooks?t="+CurrentEpochTime.getEpochTime()+"");
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);		

			Log.info("V1RefreshBooks Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	public static void   main(String []args)
	{ long  startdate=EpochTime.getEpochTime("2019/10/31 14:46:04");
	//long startdate= 1598866517971;
	System.out.println("getEpochTime :: " +v1refreshBooks(startdate));
	}

}
