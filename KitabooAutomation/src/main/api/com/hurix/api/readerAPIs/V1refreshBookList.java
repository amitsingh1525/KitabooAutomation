package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.utility.ExcelUtils;
import com.hurix.automation.utility.Log;

public class V1refreshBookList {
	
	
	//public static String POSTv1refreshBookListath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/123/HTML5/v1/refreshBookList?t=1596298740&clientID="+com.hurix.api.utility.ExcelUtils.getClientID()+"";
	
	private static String v1refreshBookListBody = "{\"type\":[\"UPDATE\",\"DELETE\"],\"bookids\":["+com.hurix.api.runner.RestAssured.bookID1+","+com.hurix.api.runner.RestAssured.bookID2+"]}";

	public static Response v1refreshBookList()
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("v1refreshBookList");
			//System.out.println("v1refreshBookListBodyRequestURL:" +POSTv1refreshBookListath);
			System.out.println("searchV2Body: "+v1refreshBookListBody);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)
					.body(v1refreshBookListBody)					
					.post("/DistributionServices/services/api/reader/distribution/123/HTML5/v1/refreshBookList?t=1596298740&clientID="+ExcelUtils.clientID+"");
					
							
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
	
	public static Response v1refreshBookList_with_pagi(long startIndex, long endIndex)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("v1refreshBookList_with_pagi");
			//System.out.println("searchV2Body: "+v1refreshBookListBody);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)
					.header("startIndex",startIndex)
					.header("endIndex", endIndex)
					.body(v1refreshBookListBody)					
					.post("/DistributionServices/services/api/reader/distribution/123/HTML5/v1/refreshBookList?t=1596298740&clientID="+ExcelUtils.clientID+"");
					
							
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
