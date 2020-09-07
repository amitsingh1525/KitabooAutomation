package com.hurix.api.utility;
import java.sql.*;

import org.apache.commons.codec.binary.Base64;

public class JDBC_category {	

	@SuppressWarnings("resource")
	public static String getCategory(int bookID, String catLevel, String sqlhost, String sqlUsername, String sqlPassword)
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
				/*byte[] decoded = Base64.decodeBase64(encodedc11);      
			     System.out.println("Base 64 Decoded  String : " + new String(decoded));*/
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


	public static void   main(String []args) throws SQLException
	{
		//getCategory(""+com.hurix.api.runner.RestAssured.bookID1, "level1", "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
		/*System.out.println(getCategory(349019102, "level1", "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123"));
		System.out.println(getCategory(349019102, "level2", "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123"));
		System.out.println(getCategory(349019102, "level3", "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123"));
		System.out.println(getCategory(349019102, "level4", "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123"));*/
		//getCategory(349019102, "level2", "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
		//getCategory(349019102, "level3", "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
		//getCategory(349019102, "level4", "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
		System.out.println(getCategory(348933167, "level1", "jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"));
		System.out.println(getCategory(348933167, "level2", "jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"));
		System.out.println(getCategory(348933167, "level3", "jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"));
		System.out.println(getCategory(348933167, "level4", "jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"));
		
	}


}

