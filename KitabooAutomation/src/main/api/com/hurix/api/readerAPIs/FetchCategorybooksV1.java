package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class FetchCategorybooksV1 {
	
	public static String c1;
	
	public static Response fetchCategorybooksV1(String catname, String userToken,int bookID, String catLevel, String sqlhost, String sqlUsername, String sqlPassword)
	{
		//GETcategoryBookListV1Path = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/books/145644544/PC/books/"+catname+"";
		String Fcatname=null;
		Response jsonResponse = null;
		try {
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
			Fcatname=c1;
			Log.startTestCase("FetchCategorybooksV1");
			if(catLevel .contains("1"))
			{		
			jsonResponse = given()
					.header("usertoken",userToken)	
					.get("/DistributionServices/services/api/reader/books/145644544/IPAD/books/"+catname+"");	
			}
			else if(catLevel .contains("2"))
			{
				Log.info("Fcatname  : "+Fcatname);
				jsonResponse = given()
						.header("usertoken",userToken)	
						.get("/DistributionServices/services/api/reader/books/145644544/IPAD/books/"+Fcatname+"");	
			}
			else if(catLevel .contains("3"))
			{
				Log.info("Fcatname  : "+Fcatname);
				jsonResponse = given()
						.header("usertoken",userToken)	
						.get("/DistributionServices/services/api/reader/books/145644544/IPAD/books/"+Fcatname+"");	
			}
			else if(catLevel .contains("4"))
			{
				Log.info("Fcatname  : "+Fcatname);
				jsonResponse = given()
						.header("usertoken",userToken)	
						.get("/DistributionServices/services/api/reader/books/145644544/IPAD/books/"+Fcatname+"");	
			}
			Log.info("FetchCategorybooksV1 Response: "+jsonResponse.then().extract().response().prettyPrint());
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
