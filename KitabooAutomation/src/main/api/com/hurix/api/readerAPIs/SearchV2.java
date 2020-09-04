package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;

import io.restassured.response.Response;

import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class SearchV2 {
	
	//public static String POSTsearchV2Path=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/elasticsearch/distribution/5555/ANDROID/searchV2";

	private static String searchV2Body;// = "{\"from\":0,\"size\":50,\"searchText\":\"Native\",\"searchOn\":\"both\",\"searchType\":\"partial\",\"searchField\":[\"author\",\"ISBN\",\"description\",\"bookTitle\",\"subject\",\"Series Title\",\"Interest Level\",\"Publisher\",\"Book content\",\"grade\",\"board\"],\"books\":[]}";

	public static Response searchV2(String serachText,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			searchV2Body = "{\"from\":0,\"size\":50,\"searchText\":\""+serachText+"\",\"searchOn\":\"both\",\"searchType\":\"partial\",\"searchField\":[\"author\",\"ISBN\",\"description\",\"bookTitle\",\"subject\",\"Series Title\",\"Interest Level\",\"Publisher\",\"Book content\",\"grade\",\"board\"],\"books\":[]}";

			Log.startTestCase("searchV2");
			//System.out.println("RequestURL:" +POSTsearchV2Path);
			System.out.println("searchV2Body: "+searchV2Body);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(searchV2Body)					
					.post("/DistributionServices/services/api/elasticsearch/distribution/"+deviceID+"/"+deviceType+"/searchV2");
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "searchText");
			Validation.responseKeyValidation_key(jsonResponse, "bookTitle");
			Validation.responseKeyValidation_key(jsonResponse, "description");			
							
			Log.info("SearchV2 Response: "+jsonResponse.then().extract().response().prettyPrint());
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
