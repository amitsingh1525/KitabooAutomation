package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class CategoryBookListV1 {
	public static String c1;
	public static String c2;
	public static String c3;
	public static String c4;
	public static String GETcategoryBookListV1Path;

	//@SuppressWarnings("resource")
	public static Response categoryBookListV1(String catname,String userToken,String DeviceID,String DeviceType,int bookID, String catLevel, String sqlhost, String sqlUsername, String sqlPassword)
	{
		//GETcategoryBookListV1Path = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/books/145644544/PC/books/"+catname+"";
		//String catname="";
		Response jsonResponse = null;
		try {			
			Log.startTestCase("categoryBookListV1");

			if(catLevel .contains("1"))
			{
				//catname=catname;
				Log.info("catname  : "+catname);
				System.out.println("GETcategoryBookListV1 RequestURL:" +GETcategoryBookListV1Path);
				jsonResponse = given()
						.header("usertoken",userToken)	
						.get("/DistributionServices/services/api/reader/books/"+DeviceID+"/"+DeviceType+"/books/"+catname+"");

				Log.info("categoryBookListV1 Response: "+jsonResponse.then().extract().response().prettyPrint());
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
				System.out.println("GETcategoryBookListV1 RequestURL:" +GETcategoryBookListV1Path);
				jsonResponse = given()
						.header("usertoken",userToken)	
						.get("/DistributionServices/services/api/reader/books/"+DeviceID+"/"+DeviceType+"/books/"+catname+"");

				Log.info("categoryBookListV1 Response: "+jsonResponse.then().extract().response().prettyPrint());
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
				System.out.println("GETcategoryBookListV1 RequestURL:" +GETcategoryBookListV1Path);
				jsonResponse = given()
						.header("usertoken",userToken)	
						.get("/DistributionServices/services/api/reader/books/"+DeviceID+"/"+DeviceType+"/books/"+catname+"");

				Log.info("categoryBookListV1 Response: "+jsonResponse.then().extract().response().prettyPrint());
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
				System.out.println("GETcategoryBookListV1 RequestURL:" +GETcategoryBookListV1Path);
				jsonResponse = given()
						.header("usertoken",userToken)	
						.get("/DistributionServices/services/api/reader/books/"+DeviceID+"/"+DeviceType+"/books/"+catname+"");

				Log.info("categoryBookListV1 Response: "+jsonResponse.then().extract().response().prettyPrint());
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

	/*public static String getCategory(int bookID, String catLevel, String sqlhost, String sqlUsername, String sqlPassword)
	{
		String encodeValue = null;
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			result= stmt.executeQuery("SELECT schemaNAme FROM cloudCore.CLIENT WHERE Id IN (SELECT client_id FROM cloudCore.BOOKS WHERE ID IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
			result.next();
			String schemaNAme = result.getString("schemaNAme");
			System.out.println("schemaNAme : " +schemaNAme);
			//String schemaNAme="client18";
			if(catLevel.contains("level1")){
				result= stmt.executeQuery("SELECT `TITLE` C1 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
				System.out.println("**************Results1**************");
				result.next();
				String c1 = result.getString("c1");

				//System.out.println("String1 : " + new String(encodeValue));
				byte[] decoded = Base64.decodeBase64(encodedc11);      
			     System.out.println("Base 64 Decoded  String : " + new String(decoded));
				return new String(Base64.encodeBase64(c1.getBytes()));
			}
			else if(catLevel.contains("level2")){
				result= stmt.executeQuery("SELECT `TITLE` C2 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY_2` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
				System.out.println("**************Results2**************");
				result.next();
				String c2 = result.getString("c2");
				return new String(Base64.encodeBase64(c2.getBytes()));
			}
			else if(catLevel.contains("level3")){
				result= stmt.executeQuery("SELECT `TITLE` C3 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY_3` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
				System.out.println("**************Results3**************");
				result.next();
				String c3 = result.getString("c3");
				return new String(Base64.encodeBase64(c3.getBytes()));
			}
			else if(catLevel.contains("level4")){
				result = stmt.executeQuery("SELECT `TITLE` C4 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY_4` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
				System.out.println("**************Results4**************");
				result.next();
				String c4 = result.getString("c4");
				System.out.println("String4"+c4);
				return new String(Base64.encodeBase64(c4.getBytes()));
			}
			//result.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new String(encodeValue);
	}
	 */


}
