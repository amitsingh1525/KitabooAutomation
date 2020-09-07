package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import java.time.Instant;
import org.apache.http.HttpStatus;
import io.restassured.response.Response;
import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.MD5GenrationWithBodyData;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class V1RefreshBooksHash {

	public static String v1refreshBooksBody1;// ="{\"bookids\":["+RestAssured.bookID1+"]}";

	public static Response v1refreshBooks(Object startDate,String operation1,String operation2,String bookID1,String bookID2,String userToken,String deviceID ,String DiviceType)
	{
		//,"+bookID2+"
		v1refreshBooksBody1 ="{\"bookids\":["+bookID1+"]}";
		Response jsonResponse = null;
		try {
			//startDate = EpochTime.getEpochTime(""+startDate+"");
			Log.info("startDate : "+startDate);
			Log.startTestCase("V1RefreshBooks_Hash");
			startDate=Instant.now().toEpochMilli();
			Log.info("startDate2 : "+startDate);	
			//System.out.println("current time  :: "+CurrentEpochTime.getEpochTime());
			System.out.println("Header HASH == " +MD5GenrationWithBodyData.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DiviceType+"/v1/refreshBook?t="+startDate+""));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.body(v1refreshBooksBody1)
					//.header("hash","MlFT4oxTtWrruEfHPuin72kwD8Do6spMZ0vT6mX3LqWgFnbJ+jaYvE01E/iv4N2m")
					.header("hash",MD5GenrationWithBodyData.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DiviceType+"/v1/refreshBooks?t="+startDate+""))
					.post("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DiviceType+"/v1/refreshBooks?t="+startDate+"");
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
	/*public static void   main(String []args)
	{ long  startdate=EpochTime.getEpochTime("2019/10/31 14:46:04");
	//long startdate= 1598866517971;
	//System.out.println("getEpochTime :: " +v1refreshBooks(startdate));
	}*/

}
