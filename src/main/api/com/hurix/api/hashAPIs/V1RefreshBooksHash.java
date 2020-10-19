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

	public static Response v1refreshBooks(Object startDate,int bookID1,int bookID2,String userToken,String deviceID ,String deviceType)
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
			System.out.println("USERTOKEN ::  " +userToken);
			String hashVal = MD5GenrationWithBodyData.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/123/PC/v1/refreshBooks?t=1599557170188");
			System.out.println("Hash Value: "+hashVal);
			jsonResponse = given()
					.header("usertoken",userToken)	
					.body(v1refreshBooksBody1)
					.header("Content-Type","application/json")
					//.header("hash","2njlFNZ8AdmARq/yNFOoCfue0wbAUeoU4hU7uyXPaGhJM18V2jdvuVyiQKGlVYIt")
					///DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DiviceType+"/v1/refreshBooks?t="+startDate+"
					.header("hash",hashVal)
					.post("/DistributionServices/services/api/reader/distribution/123/PC/v1/refreshBooks?t=1599557170188");
			
			Log.info("V1RefreshBooks Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
		
	public static Response v1refreshBooks_op(Object startDate,int bookID1,int bookID2,String operation1,String operation2,String userToken,String deviceID ,String deviceType)
	{
		//,"+bookID2+"
		v1refreshBooksBody1 ="{\"type\":[\""+operation1+"\",\""+operation2+"\"],\"bookids\":["+bookID1+","+bookID2+"]}";
		Response jsonResponse = null;
		try {
			//startDate = EpochTime.getEpochTime(""+startDate+"");
			Log.info("startDate : "+startDate);
			Log.startTestCase("V1RefreshBooks_Hash.operation1="+operation1+".operation2="+operation2+"");
			startDate=Instant.now().toEpochMilli();
			Log.info("startDate2 : "+startDate);	
			//System.out.println("current time  :: "+CurrentEpochTime.getEpochTime());
			System.out.println("USERTOKEN ::  " +userToken);
			String hashVal = MD5GenrationWithBodyData.hashGenration(RestAssured.detail+"DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/v1/refreshBookList?t="+startDate+"");
			System.out.println("Hash Value: "+hashVal);
			jsonResponse = given()
					.header("usertoken",userToken)	
					.body(v1refreshBooksBody1)
					.header("Content-Type","application/json")
					.header("hash",hashVal)
					.post("DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/v1/refreshBookList?t="+startDate+"");
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);		

			Log.info("V1RefreshBooks_Hash.operation1="+operation1+".operation2="+operation2+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
