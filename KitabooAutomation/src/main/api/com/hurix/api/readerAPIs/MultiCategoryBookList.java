package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class MultiCategoryBookList {

	public static Response multiCategoryBookList(String catLevel,int bookID, String sqlhost, String sqlUsername, String sqlPassword,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("MultiCategoryBookList:"+catLevel+"");
			Log.info("bookID : "+bookID);
			if(catLevel.contains ("4"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("base64CategoryLevel1", JDBC_Queries.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_Queries.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_Queries.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel4", JDBC_Queries.getCategory(bookID, "level4", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryBookList");

				Log.info("MultiCategoryBookList: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());
			}
			else if(catLevel.contains ("3"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("base64CategoryLevel1", JDBC_Queries.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_Queries.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_Queries.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/123/IPAD/multiCategoryBookList");

				Log.info("MultiCategoryBookList: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());
			}
			if(catLevel.contains ("2"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("base64CategoryLevel1", JDBC_Queries.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_Queries.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/123/IPAD/multiCategoryBookList");

				Log.info("MultiCategoryBookList: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());
			}
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

	public static Response multiCategoryBookList_Per(String SortBy,String orderBy,String catLevel,int bookID, String sqlhost, String sqlUsername, String sqlPassword,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("MultiCategoryBookList:"+catLevel+".SortBy="+SortBy+".orderBy="+orderBy+"");
			if(catLevel.contains ("4"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("base64CategoryLevel1", JDBC_Queries.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_Queries.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_Queries.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel4", JDBC_Queries.getCategory(bookID, "level4", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryBookList");

			}
			else if(catLevel.contains ("3"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("base64CategoryLevel1", JDBC_Queries.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_Queries.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_Queries.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/123/IPAD/multiCategoryBookList");

			}
			if(catLevel.contains ("2"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("base64CategoryLevel1", JDBC_Queries.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_Queries.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/123/IPAD/multiCategoryBookList");
			}
			Log.info("MultiCategoryBookList:"+catLevel+".SortBy="+SortBy+".orderBy="+orderBy+" Response: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());

		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

	public static Response multiCategoryBookList_Per_withPagi(String SortBy,String orderBy,int startIndex,int endIndex,String catLevel,int bookID, String sqlhost, String sqlUsername, String sqlPassword,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("MultiCategoryBookList:"+catLevel+".SortBy="+SortBy+".orderBy="+orderBy+"");
			if(catLevel.contains ("4"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("startIndex",startIndex)
						.header("endIndex",endIndex)
						.header("base64CategoryLevel1", JDBC_Queries.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_Queries.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_Queries.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel4", JDBC_Queries.getCategory(bookID, "level4", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryBookList");

			}
			else if(catLevel.contains ("3"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("startIndex",startIndex)
						.header("endIndex",endIndex)
						.header("base64CategoryLevel1", JDBC_Queries.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_Queries.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_Queries.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/123/IPAD/multiCategoryBookList");

			}
			if(catLevel.contains ("2"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("startIndex",startIndex)
						.header("endIndex",endIndex)
						.header("base64CategoryLevel1", JDBC_Queries.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_Queries.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/123/IPAD/multiCategoryBookList");
			}
			Log.info("MultiCategoryBookList:"+catLevel+".SortBy="+SortBy+".orderBy="+orderBy+" Response: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());

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
