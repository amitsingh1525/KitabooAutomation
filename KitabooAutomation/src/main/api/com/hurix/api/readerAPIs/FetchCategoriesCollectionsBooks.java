package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchCategoriesCollectionsBooks {
	static String Fcatname=null;
	public static String c1;
	public static String c2;
	public static String c3;
	public static String c4;
	public static String GETfetchCategoriesCollectionsBooksPath;

	public static Response fetchCategoriesCollectionsBooks(String userToken,String deviceID,String deviceType,String catname1,String collecName1,int bookID,String sqlhost,String sqlUsername,String sqlPassword,String catLevel)
	{
		
		Response jsonResponse = null;
		try {

			Log.startTestCase("fetchCategoriesCollectionsBooks");
			Log.info("collecName1 : "+collecName1);
			Log.info("catname1 : "+catname1);
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			result= stmt.executeQuery("SELECT schemaNAme FROM cloudCore.CLIENT WHERE Id IN (SELECT client_id FROM cloudCore.BOOKS WHERE ID IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
			result.next();
			String schemaNAme = result.getString("schemaNAme");
			System.out.println("schemaNAme : " +schemaNAme);

			result= stmt.executeQuery("SELECT `TITLE` C1 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
			System.out.println("**************Results1**************");
			result.next();
			c1 = result.getString("c1");
			Fcatname=c1;
			Log.info("catname  : "+Fcatname);
			Log.info("userToken  : "+userToken);
			//System.out.println("GETfetchCategoriesCollectionsBooksRequestURL:" +GETfetchCategoriesCollectionsBooksPath);
			if(catLevel .contains("1"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("category",catname1)
						.header("collection",collecName1)
						.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");
			}
			else if(catLevel .contains("2"))
			{
				Log.info("Fcatname  : "+Fcatname);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("category",Fcatname)
						.header("collection",collecName1)
						.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");

			}
			else if(catLevel .contains("3"))
			{
				Log.info("Fcatname  : "+Fcatname);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("category",Fcatname)
						.header("collection",collecName1)
						.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");

			}
			else if(catLevel .contains("4"))
			{
				Log.info("Fcatname  : "+Fcatname);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("category",Fcatname)
						.header("collection",collecName1)
						.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");
				
			}
			stmt.close();
			con.close();
			Log.info("FetchCategoriesCollectionsBooks Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

	public static Response fetchCategoriesCollectionsBooks_per(String SortBy,String orderBy,String userToken,String deviceID,String deviceType,String catname1,String collecName1,int bookID,String sqlhost,String sqlUsername,String sqlPassword,String catLevel)
	{
		//GETfetchCategoriesCollectionsBooksPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/books/123454/PC/books/fetchCategoriesCollectionsBooks";

		Response jsonResponse = null;
		try {

			Log.startTestCase("fetchCategoriesCollectionsBooks.SortBy="+SortBy+".orderBy="+orderBy+"");

			Log.info("collecName1 : "+collecName1);
			Log.info("catname1 : "+catname1);
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			result= stmt.executeQuery("SELECT schemaNAme FROM cloudCore.CLIENT WHERE Id IN (SELECT client_id FROM cloudCore.BOOKS WHERE ID IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
			result.next();
			String schemaNAme = result.getString("schemaNAme");
			System.out.println("schemaNAme : " +schemaNAme);

			result= stmt.executeQuery("SELECT `TITLE` C1 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
			System.out.println("**************Results1**************");
			result.next();
			c1 = result.getString("c1");
			Fcatname=c1;
			Log.info("catname  : "+Fcatname);
			Log.info("userToken  : "+userToken);
			//System.out.println("GETfetchCategoriesCollectionsBooksRequestURL:" +GETfetchCategoriesCollectionsBooksPath);
			if(catLevel .contains("1"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("category",catname1)
						.header("collection",collecName1)
						.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");
			}
			else if(catLevel .contains("2"))
			{
				Log.info("Fcatname  : "+Fcatname);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("category",catname1)
						.header("collection",collecName1)
						.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");
			}
			else if(catLevel .contains("3"))
			{
				Log.info("Fcatname  : "+Fcatname);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("category",catname1)
						.header("collection",collecName1)
						.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");
			}
			else if(catLevel .contains("4"))
			{
				Log.info("Fcatname  : "+Fcatname);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("category",catname1)
						.header("collection",collecName1)
						.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");
				
			}		
			stmt.close();
			con.close();
			Log.info("fetchCategoriesCollectionsBooks.SortBy="+SortBy+".orderBy="+orderBy+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

	public static Response fetchCategoriesCollectionsBooks_per_withpagi(String SortBy,String orderBy,int startIndex,int endIndex,String userToken,String deviceID,String deviceType,String catname1,String collecName1,int bookID,String sqlhost,String sqlUsername,String sqlPassword,String catLevel)
	{
		//GETfetchCategoriesCollectionsBooksPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/books/123454/PC/books/fetchCategoriesCollectionsBooks";

		Response jsonResponse = null;
		try {

			Log.startTestCase("fetchCategoriesCollectionsBooks.SortBy="+SortBy+".orderBy="+orderBy+".startIndex="+startIndex+".endIndex="+endIndex+"");
			Log.info("collecName1 : "+collecName1);
			Log.info("catname1 : "+catname1);
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			result= stmt.executeQuery("SELECT schemaNAme FROM cloudCore.CLIENT WHERE Id IN (SELECT client_id FROM cloudCore.BOOKS WHERE ID IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
			result.next();
			String schemaNAme = result.getString("schemaNAme");
			System.out.println("schemaNAme : " +schemaNAme);

			result= stmt.executeQuery("SELECT `TITLE` C1 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
			System.out.println("**************Results1**************");
			result.next();
			c1 = result.getString("c1");
			Fcatname=c1;
			Log.info("catname  : "+Fcatname);
			Log.info("userToken  : "+userToken);
			//System.out.println("GETfetchCategoriesCollectionsBooksRequestURL:" +GETfetchCategoriesCollectionsBooksPath);
			if(catLevel .contains("1"))
			{
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("startIndex",startIndex)
						.header("endIndex",endIndex)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("category",catname1)
						.header("collection",collecName1)
						.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");
			}
			else if(catLevel .contains("2"))
			{
				Log.info("Fcatname  : "+Fcatname);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("startIndex",startIndex)
						.header("endIndex",endIndex)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("category",catname1)
						.header("collection",collecName1)
						.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");
			}
			else if(catLevel .contains("3"))
			{
				Log.info("Fcatname  : "+Fcatname);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("startIndex",startIndex)
						.header("endIndex",endIndex)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("category",catname1)
						.header("collection",collecName1)
						.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");
			}
			else if(catLevel .contains("4"))
			{
				Log.info("Fcatname  : "+Fcatname);
				jsonResponse = given()
						.header("usertoken",userToken)
						.header("startIndex",startIndex)
						.header("endIndex",endIndex)
						.header("SortBy",SortBy)
						.header("orderBy",orderBy)
						.header("category",catname1)
						.header("collection",collecName1)
						.get("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/books/fetchCategoriesCollectionsBooks");
			}	
			stmt.close();
			con.close();
			Log.info("fetchCategoriesCollectionsBooks.SortBy="+SortBy+".orderBy="+orderBy+".startIndex="+startIndex+".endIndex="+endIndex+" Response: "+jsonResponse.then().extract().response().prettyPrint());
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
