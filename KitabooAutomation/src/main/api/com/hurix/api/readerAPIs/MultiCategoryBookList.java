package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import java.sql.SQLException;
import io.restassured.response.Response;
import com.hurix.api.utility.JDBC_category;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class MultiCategoryBookList {

	public static Response multiCategoryBookList(String catLevel,int bookID, String sqlhost, String sqlUsername, String sqlPassword,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("MultiCategoryBookList:"+catLevel+"");
			if(catLevel.contains ("4"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_category.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel4", JDBC_category.getCategory(bookID, "level4", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryBookList");
				/*Validation.responseHeaderCodeValidation(jsonResponse, 200);
				Validation.responseCodeValidation1(jsonResponse, 200);
				Validation.responseTimeValidation(jsonResponse);
				Validation.responseKeyValidation_key(jsonResponse, "totalbooks");
				Validation.responseKeyValidation_key(jsonResponse, "category");
				Validation.responseKeyValidation_key(jsonResponse, "description");*/	
				Log.info("MultiCategoryBookList: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());
				
			}
			else if(catLevel.contains ("3"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_category.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/123/IPAD/multiCategoryBookList");
				Validation.responseHeaderCodeValidation(jsonResponse, 200);
				Validation.responseCodeValidation1(jsonResponse, 200);
				Validation.responseTimeValidation(jsonResponse);
				Validation.responseKeyValidation_key(jsonResponse, "totalbooks");
				Validation.responseKeyValidation_key(jsonResponse, "category");
				Validation.responseKeyValidation_key(jsonResponse, "description");	
				Log.info("MultiCategoryBookList: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());
			}
			if(catLevel.contains ("2"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/123/IPAD/multiCategoryBookList");
				Validation.responseHeaderCodeValidation(jsonResponse, 200);
				Validation.responseCodeValidation1(jsonResponse, 200);
				Validation.responseTimeValidation(jsonResponse);
				Validation.responseKeyValidation_key(jsonResponse, "totalbooks");
				Validation.responseKeyValidation_key(jsonResponse, "category");
				Validation.responseKeyValidation_key(jsonResponse, "description");	
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

	/*private static int getCategory(String string, String string2,
			String string3, String string4, String string5) {
		// TODO Auto-generated method stub
		return 0;
	}*/
	public static void   main(String []args) throws SQLException
	{
		//MultiCategoryBookList.multiCategoryBookList();
	}
}
