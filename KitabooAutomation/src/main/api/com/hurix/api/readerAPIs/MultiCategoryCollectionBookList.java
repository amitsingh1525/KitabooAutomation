package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.mortbay.jetty.security.B64Code;

import io.restassured.response.Response;

import com.hurix.api.utility.JDBC_category;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class MultiCategoryCollectionBookList {

	public static Response multiCategoryCollectionBookList(String catLevel,int bookID, String sqlhost, String sqlUsername, String sqlPassword,String userToken,String deviceID,String deviceType,String collectName)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("MultiCategoryCollectionBookList"+catLevel+"");
			if(catLevel.contains ("4"))
			{
				Log.info("Before string : "+collectName);
				collectName =  B64Code.encode(collectName);
				Log.info("Base 64 encoded  String : " + new String(collectName));
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("base64CollectionName", collectName)
						.header("base64CategoryLevel1", JDBC_category.getCategory(bookID, "level1", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel2", JDBC_category.getCategory(bookID, "level2", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel3", JDBC_category.getCategory(bookID, "level3", sqlhost,sqlUsername,sqlPassword))
						.header("base64CategoryLevel4", JDBC_category.getCategory(bookID, "level4", sqlhost,sqlUsername,sqlPassword))
						.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/multiCategoryCollectionBookList");
				Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
				Validation.responseTimeValidation(jsonResponse);
				Validation.responseKeyValidation_key(jsonResponse, "totalbooks");
				Validation.responseKeyValidation_key(jsonResponse, "category");
				Validation.responseKeyValidation_key(jsonResponse, "description");
				Log.info("MultiCategoryCollectionBookList: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());

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
				Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
				Validation.responseTimeValidation(jsonResponse);
				Validation.responseKeyValidation_key(jsonResponse, "totalbooks");
				Validation.responseKeyValidation_key(jsonResponse, "category");
				Validation.responseKeyValidation_key(jsonResponse, "description");	
				Log.info("MultiCategoryCollectionBookList : "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());

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
				Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
				Validation.responseTimeValidation(jsonResponse);
				Validation.responseKeyValidation_key(jsonResponse, "totalbooks");
				Validation.responseKeyValidation_key(jsonResponse, "category");
				Validation.responseKeyValidation_key(jsonResponse, "description");
				Log.info("MultiCategoryCollectionBookList: "+catLevel+ " Response: "+jsonResponse.then().extract().response().prettyPrint());

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
	public static void main (String[] args)
	{
		String collectName="Native_cat1_UPD";
		System.out.println("Before String = "+collectName);
		collectName =  B64Code.encode(collectName);
		System.out.println("Base 64 encoded  String : " + new String(collectName));
	}
}
