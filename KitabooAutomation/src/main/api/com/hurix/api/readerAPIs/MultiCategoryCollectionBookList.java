package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import org.mortbay.jetty.security.B64Code;
import io.restassured.response.Response;
import com.hurix.api.utility.JDBC_category;
import com.hurix.automation.utility.Log;

public class MultiCategoryCollectionBookList {

	public static Response multiCategoryCollectionBookList(String catLevel,int bookID, String sqlhost, String sqlUsername, String sqlPassword,String userToken,String deviceID,String deviceType,String collectName)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("MultiCategoryCollectionBookList"+catLevel+"");
			Log.info("collectName : " +collectName);
			if(catLevel.contains ("4"))
			{
				Log.info("collectName Before string : "+collectName);
				collectName =  B64Code.encode(collectName);
				Log.info("collectName After string : "+collectName);
				Log.info("Base 64 encoded  String : " + new String(collectName));
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("base64CollectionName", collectName)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_category.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel4", JDBC_category.getCategory(bookID, "level4", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryCollectionBookList");

			}
			else if(catLevel.contains ("3"))
			{
				Log.info("Before string : "+collectName);
				collectName =  B64Code.encode(collectName);
				Log.info("Base 64 encoded  String : " + new String(collectName));
				jsonResponse = given()
						.header("base64CollectionName", collectName)
						.header("usertoken",userToken)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_category.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryCollectionBookList");

			}
			if(catLevel.contains ("2"))
			{
				Log.info("Before string : "+collectName);
				collectName =  B64Code.encode(collectName);
				Log.info("Base 64 encoded  String : " + new String(collectName));
				jsonResponse = given()
						.header("base64CollectionName", collectName)
						.header("usertoken",userToken)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryCollectionBookList");

			}
			Log.info("MultiCategoryCollectionBookList: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());
		}catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

	public static Response multiCategoryCollectionBookList_Per(String SortBy,String orderBy,String catLevel,int bookID, String sqlhost, String sqlUsername, String sqlPassword,String userToken,String deviceID,String deviceType,String collectName)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("MultiCategoryCollectionBookList"+catLevel+".SortBy="+SortBy+".orderBy="+orderBy+"");
			if(catLevel.contains ("4"))
			{
				Log.info("collectName Before string : "+collectName);
				collectName =  B64Code.encode(collectName);
				Log.info("collectName After string : "+collectName);
				Log.info("Base 64 encoded  String : " + new String(collectName));
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("base64CollectionName", collectName)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_category.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel4", JDBC_category.getCategory(bookID, "level4", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryCollectionBookList");

			}
			else if(catLevel.contains ("3"))
			{
				Log.info("Before string : "+collectName);
				collectName =  B64Code.encode(collectName);
				Log.info("Base 64 encoded  String : " + new String(collectName));
				jsonResponse = given()
						.header("base64CollectionName", collectName)
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_category.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryCollectionBookList");

			}
			if(catLevel.contains ("2"))
			{
				Log.info("Before string : "+collectName);
				collectName =  B64Code.encode(collectName);
				Log.info("Base 64 encoded  String : " + new String(collectName));
				jsonResponse = given()
						.header("base64CollectionName", collectName)
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryCollectionBookList");

			}
			Log.info("MultiCategoryCollectionBookList"+catLevel+".SortBy="+SortBy+".orderBy="+orderBy+" Reponse: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());
		}catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

	public static Response multiCategoryCollectionBookList_Per_withpagi(String SortBy,String orderBy,int startIndex,int endIndex,String catLevel,int bookID, String sqlhost, String sqlUsername, String sqlPassword,String userToken,String deviceID,String deviceType,String collectName)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("MultiCategoryCollectionBookList"+catLevel+".SortBy="+SortBy+".orderBy="+orderBy+"");
			if(catLevel.contains ("4"))
			{
				Log.info("collectName Before string : "+collectName);
				collectName =  B64Code.encode(collectName);
				Log.info("collectName After string : "+collectName);
				Log.info("Base 64 encoded  String : " + new String(collectName));
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("startIndex",startIndex)
						.header("endIndex",endIndex)
						.header("base64CollectionName", collectName)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_category.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel4", JDBC_category.getCategory(bookID, "level4", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryCollectionBookList");

			}
			else if(catLevel.contains ("3"))
			{
				Log.info("Before string : "+collectName);
				collectName =  B64Code.encode(collectName);
				Log.info("Base 64 encoded  String : " + new String(collectName));
				jsonResponse = given()
						.header("base64CollectionName", collectName)
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("startIndex",startIndex)
						.header("endIndex",endIndex)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_category.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryCollectionBookList");

			}
			if(catLevel.contains ("2"))
			{
				Log.info("Before string : "+collectName);
				collectName =  B64Code.encode(collectName);
				Log.info("Base 64 encoded  String : " + new String(collectName));
				jsonResponse = given()
						.header("base64CollectionName", collectName)
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("startIndex",startIndex)
						.header("endIndex",endIndex)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryCollectionBookList");

			}
			Log.info("MultiCategoryCollectionBookList"+catLevel+".SortBy="+SortBy+".orderBy="+orderBy+" Reponse: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());
		}catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

	public static void main (String[] args)
	{
		String collectName="Native_cat1_UPD";
		System.out.println("Before String = "+collectName);
		collectName =  B64Code.encode(collectName);
		System.out.println("Base 64 encoded  String : " + new String(collectName));
	}
}
