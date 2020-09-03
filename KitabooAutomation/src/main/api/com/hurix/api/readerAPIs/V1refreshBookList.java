package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.utility.EpochTime;
import com.hurix.api.utility.ExcelUtils;
import com.hurix.automation.utility.Log;

public class V1refreshBookList {

	private static String v1refreshBookListBody;
	public static Response v1refreshBookList(Object startDate,String operation1,String operation2,String bookID1,String bookID2,String userToken,String deviceID ,String DiviceType)
	{
		Response jsonResponse = null;
		try {
			v1refreshBookListBody = "{\"type\":[\""+operation1+"\",\""+operation2+"\"],\"bookids\":["+bookID1+","+bookID2+"]}";
			startDate=EpochTime.getEpochTime(""+startDate+"");
			Log.startTestCase("v1refreshBookList");
			//System.out.println("v1refreshBookListBodyRequestURL:" +POSTv1refreshBookListath);
			System.out.println("searchV2Body: "+v1refreshBookListBody);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(v1refreshBookListBody)					
					.post("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DiviceType+"/v1/refreshBookList?t="+startDate+"&clientID="+ExcelUtils.clientID+"");
			/*Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "isbn");
			Validation.responseKeyValidation_key(jsonResponse, "formats");
			Validation.responseKeyValidation_key(jsonResponse, "id");	
			Validation.responseKeyValidation_key(jsonResponse, "category");*/
			Log.info("v1refreshBookList Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}	

	public static Response v1refreshBookList_with_pagi(int startIndex, long endIndex,Object startDate,String operation1,String operation2,String bookID1,String bookID2,String userToken,String deviceID ,String deviceType)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("v1refreshBookList_with_pagi");
			//System.out.println("searchV2Body: "+v1refreshBookListBody);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.header("startIndex",startIndex)
					.header("endIndex", endIndex)
					.body(v1refreshBookListBody)					
					.post("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/v1/refreshBookList?t="+startDate+"&clientID="+ExcelUtils.clientID+"");
			/*Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "isbn");
			Validation.responseKeyValidation_key(jsonResponse, "formats");
			Validation.responseKeyValidation_key(jsonResponse, "id");*/				
			Log.info("v1refreshBookList_with_pagi Response: "+jsonResponse.then().extract().response().prettyPrint());
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
