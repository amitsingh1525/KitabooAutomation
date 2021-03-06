package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class SearchV2_AdvanceFilter {

	//public static String POSTsearchV2Path=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/elasticsearch/distribution/123/WINDOWS/searchV2";

	private static String searchV2Body; //= "{\"searchText\":\"Native\",\"searchOn\":\"both\",\"searchType\":\"partial\",\"books\":[],\"searchField\":[\"author\",\"ISBN\",\"bookTitle\",\"subject\",\"Publisher\",\"Book content\",\"category\"],\"from\":0,\"size\":50,\"advancedFilter\":{\"ISBN\":{\"term\":\"444444\"}}}";

	public static Response searchV2_AdvanceFilter(String searchTEXT,String userToken,String deviceID,String deviceType,String clientUserID)
	{
		Response jsonResponse = null;
		try {
			searchV2Body = "{\"searchText\":\""+searchTEXT+"\",\"searchOn\":\"both\",\"searchType\":\"partial\",\"books\":[],\"searchField\":[\"author\",\"ISBN\",\"description\",\"bookTitle\",\"subject\",\"Publisher\",\"Book content\"],\"from\":0,\"size\":50,\"userId\":\""+clientUserID+"\"}";
			
			Log.startTestCase("searchV2_AdvanceFilter");
			//System.out.println("RequestURL:" +POSTsearchV2Path);
			//System.out.println("searchV2Body: "+searchV2Body);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(searchV2Body)					
					.post("/DistributionServices/services/api/elasticsearch/distribution/"+deviceID+"/"+deviceType+"/searchV2");
			

			Log.info("SearchV2_AdvanceFilter Response: "+jsonResponse.then().extract().response().prettyPrint());
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
