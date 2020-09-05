package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class CategoryBookListV2 {
	public static String c1;
	public static String c2;
	public static String c3;
	public static String c4;

	public static Response categoryBookListV2(String catname,String userToken,String deviceId,String deviceType,int bookID, String catLevel, String sqlhost, String sqlUsername, String sqlPassword)
	{
		//GETfetchBookCountPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/145644544/PC/fetchBookCount";

		Response jsonResponse = null;
		try {

			Log.startTestCase("CategoryBookListV2");


			if(catLevel .contains("1"))
			{
				//catname=catname;
				Log.info("catname  : "+catname);
				//System.out.println("GETcategoryBookListV1 RequestURL:" +GETcategoryBookListV1Path);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("category",catname)				
						.get("/DistributionServices/services/api/reader/books/"+deviceId+"/"+deviceType+"/books/v2/categoryBookList");

				Log.info("CategoryBookListV2 Response: "+jsonResponse.then().extract().response().prettyPrint());
			}	
			else if(catLevel .contains("2"))
			{
				Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
				Statement stmt = con.createStatement();
				ResultSet result = null;
				result= stmt.executeQuery("SELECT schemaNAme FROM cloudCore.CLIENT WHERE Id IN (SELECT client_id FROM cloudCore.BOOKS WHERE ID IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
				result.next();
				String schemaNAme = result.getString("schemaNAme");
				System.out.println("schemaNAme : " +schemaNAme);
				//String schemaNAme="client18";
				//Log.info("QUERY"+"SELECT `TITLE` C1 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))+");
				result= stmt.executeQuery("SELECT `TITLE` C1 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
				System.out.println("**************Results1**************");
				result.next();
				c1 = result.getString("c1");
				catname=c1;
				Log.info("catname  : "+catname);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("category",catname)				
						.get("/DistributionServices/services/api/reader/books/"+deviceId+"/"+deviceType+"/books/v2/categoryBookList");

				Log.info("CategoryBookListV2 Response: "+jsonResponse.then().extract().response().prettyPrint());

			}

			else if(catLevel .contains("3"))
			{
				Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
				Statement stmt = con.createStatement();
				ResultSet result = null;
				result= stmt.executeQuery("SELECT schemaNAme FROM cloudCore.CLIENT WHERE Id IN (SELECT client_id FROM cloudCore.BOOKS WHERE ID IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
				result.next();
				String schemaNAme = result.getString("schemaNAme");
				System.out.println("schemaNAme : " +schemaNAme);
				//String schemaNAme="client18";
				result= stmt.executeQuery("SELECT `TITLE` C1 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
				System.out.println("**************Results1**************");
				result.next();
				c1 = result.getString("c1");
				catname=c1;
				Log.info("catname  : "+catname);
				//System.out.println("GETcategoryBookListV1 RequestURL:" +GETcategoryBookListV1Path);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("category",catname)				
						.get("/DistributionServices/services/api/reader/books/"+deviceId+"/"+deviceType+"/books/v2/categoryBookList");

				Log.info("CategoryBookListV2 Response: "+jsonResponse.then().extract().response().prettyPrint());
			}

			else if(catLevel .contains("4"))
			{
				Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
				Statement stmt = con.createStatement();
				ResultSet result = null;
				result= stmt.executeQuery("SELECT schemaNAme FROM cloudCore.CLIENT WHERE Id IN (SELECT client_id FROM cloudCore.BOOKS WHERE ID IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
				result.next();
				String schemaNAme = result.getString("schemaNAme");
				System.out.println("schemaNAme : " +schemaNAme);
				//String schemaNAme="client18";
				result= stmt.executeQuery("SELECT `TITLE` C1 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
				System.out.println("**************Results1**************");
				result.next();
				c1 = result.getString("c1");

				catname=c1;
				Log.info("catname  : "+catname);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("category",catname)				
						.get("/DistributionServices/services/api/reader/books/"+deviceId+"/"+deviceType+"/books/v2/categoryBookList");

				Log.info("CategoryBookListV2 Response: "+jsonResponse.then().extract().response().prettyPrint());
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
