package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class MultiCategories {
	
	public static Response multiCategories(String catLevel,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			
			Log.startTestCase("MultiCategories");		
			if(catLevel.contains ("4"))
			{
				int level = 4;
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("level", level)
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategories");
				
				/*Validation.responseHeaderCodeValidation(jsonResponse, 200);
				Validation.responseCodeValidation1(jsonResponse, 200);
				Validation.responseTimeValidation(jsonResponse);
				Validation.responseKeyValidation_key(jsonResponse, "categories");
				Validation.responseKeyValidation_key(jsonResponse, "collectionCount");
				Validation.responseKeyValidation_key(jsonResponse, "bookCount");	*/
				Log.info("MultiCategoryBookList: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());
				
			}
			else if(catLevel.contains ("3"))
			{
				int level = 3;
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("level", level)
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategories");
				
				/*Validation.responseHeaderCodeValidation(jsonResponse, 200);
				Validation.responseCodeValidation1(jsonResponse, 200);
				Validation.responseTimeValidation(jsonResponse);
				Validation.responseKeyValidation_key(jsonResponse, "categories");
				Validation.responseKeyValidation_key(jsonResponse, "collectionCount");
				Validation.responseKeyValidation_key(jsonResponse, "bookCount");	*/
				Log.info("MultiCategoryBookList: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());
				
			}
			else if(catLevel.contains ("2"))
			{
				int level = 2;
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("level", level)
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategories");
				
				/*Validation.responseHeaderCodeValidation(jsonResponse, 200);
				Validation.responseCodeValidation1(jsonResponse, 200);
				Validation.responseTimeValidation(jsonResponse);
				Validation.responseKeyValidation_key(jsonResponse, "categories");
				Validation.responseKeyValidation_key(jsonResponse, "collectionCount");
				Validation.responseKeyValidation_key(jsonResponse, "bookCount");	*/
				Log.info("MultiCategoryBookList: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());
				
			}
						
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
