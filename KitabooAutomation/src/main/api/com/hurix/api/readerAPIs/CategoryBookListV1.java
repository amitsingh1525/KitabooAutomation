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

	public static Response categoryBookListV1(String catname,String userToken,String DeviceID,String DeviceType,int bookID, String catLevel, String sqlhost, String sqlUsername, String sqlPassword)
	{
		String Fcatname=null;
		Response jsonResponse = null;
		try {			
			Log.startTestCase("categoryBookListV1.catname="+catname+"");
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

			if(catLevel .contains("1"))
			{
				//catname=catname;
				Log.info("catname  : "+catname);
				System.out.println("GETcategoryBookListV1 RequestURL:" +GETcategoryBookListV1Path);
				jsonResponse = given()
						.header("usertoken",userToken)	
						.get("/DistributionServices/services/api/reader/books/"+DeviceID+"/"+DeviceType+"/books/"+catname+"");

				Log.info("categoryBookListV1.catname="+catname+" Response: "+jsonResponse.then().extract().response().prettyPrint());
			}		

			else if(catLevel .contains("2"))
			{
				Log.info("Fcatname  : "+Fcatname);
				System.out.println("GETcategoryBookListV1 RequestURL:" +GETcategoryBookListV1Path);
				jsonResponse = given()
						.header("usertoken",userToken)	
						.get("/DistributionServices/services/api/reader/books/"+DeviceID+"/"+DeviceType+"/books/"+Fcatname+"");

				Log.info("categoryBookListV1 Response: "+jsonResponse.then().extract().response().prettyPrint());
			}

			else if(catLevel .contains("3"))
			{
				
				System.out.println("schemaNAme : " +schemaNAme);
				
				Log.info("Fcatname  : "+Fcatname);
				System.out.println("GETcategoryBookListV1 RequestURL:" +GETcategoryBookListV1Path);
				jsonResponse = given()
						.header("usertoken",userToken)	
						.get("/DistributionServices/services/api/reader/books/"+DeviceID+"/"+DeviceType+"/books/"+Fcatname+"");

				Log.info("categoryBookListV1 Response: "+jsonResponse.then().extract().response().prettyPrint());
			}

			else if(catLevel .contains("4"))
			{
				/*Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
				Statement stmt = con.createStatement();
				ResultSet result = null;
				result= stmt.executeQuery("SELECT schemaNAme FROM cloudCore.CLIENT WHERE Id IN (SELECT client_id FROM cloudCore.BOOKS WHERE ID IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
				result.next();
				String schemaNAme = result.getString("schemaNAme");*/
				/*System.out.println("schemaNAme : " +schemaNAme);
				//String schemaNAme="client18";
				result= stmt.executeQuery("SELECT `TITLE` C1 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
				System.out.println("**************Results1**************");
				result.next();
				c1 = result.getString("c1");

				catname=c1;*/
				Log.info("Fcatname  : "+Fcatname);
				System.out.println("GETcategoryBookListV1 RequestURL:" +GETcategoryBookListV1Path);
				jsonResponse = given()
						.header("usertoken",userToken)	
						.get("/DistributionServices/services/api/reader/books/"+DeviceID+"/"+DeviceType+"/books/"+Fcatname+"");

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
}
/*Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
Statement stmt = con.createStatement();
ResultSet result = null;
result= stmt.executeQuery("SELECT schemaNAme FROM cloudCore.CLIENT WHERE Id IN (SELECT client_id FROM cloudCore.BOOKS WHERE ID IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
result.next();
String schemaNAme = result.getString("schemaNAme");*/


/*//String schemaNAme="client18";
result= stmt.executeQuery("SELECT `TITLE` C1 FROM "+schemaNAme+".`CATEGORY_METADATA` WHERE ID IN (SELECT `CATEGORY` FROM "+schemaNAme+".`BOOKS_CATEGORY_MAP`WHERE book_id IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID+"))");
System.out.println("**************Results1**************");
result.next();
c1 = result.getString("c1");
catname=c1;*/