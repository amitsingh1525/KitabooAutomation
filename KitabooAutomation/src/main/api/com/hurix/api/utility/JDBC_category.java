package com.hurix.api.utility;

import java.sql.*;

import org.apache.commons.codec.binary.Base64;

import com.hurix.automation.utility.Log;

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
			
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
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
		//System.out.println(getCategory(348933167, "level1", "jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"));
		//System.out.println(getCategory(348933167, "level2", "jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"));
		//System.out.println(getCategory(348933167, "level3", "jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"));
		//System.out.println(getCategory(348933167, "level4", "jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"));
		//System.out.println(getBookId(88, "Audio_cat1_UPD",  "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123"));
		//System.out.println(getArchiveDate(1317, "Audio_cat1", "jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"));
		//System.out.println(getCat(1317,"Audio_cat1","jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"));
	//System.out.println("HERE  : "+getBookId(2559, "Reflow_epub_UPD_", "jdbc:mysql://localhost:12345", "shweta-katare","J&P@O4A7HV"));
	//System.out.println("catN : " +getCat(2561, "Reflow_epub_UPD_", "jdbc:mysql://localhost:12345", "shweta-katare","J&P@O4A7HV"));
	
	//System.out.println(get_bookID_down(348958408,14111, "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123"));
	//System.out.println(getCKSK(1437,"jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"));
	System.out.println(getReader_user_ID(932896,"jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"));
	}
	
	public static int getBookId(int client_id, String Title, String sqlhost, String sqlUsername, String sqlPassword)
	{
		int id = 0;
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			Log.info("Title : " +Title);
			Log.info("client_id : " +client_id);
			result= stmt.executeQuery("SELECT id FROM cloudCore.COLLECTION_BOOK_MAP WHERE BOOK_ID=(SELECT book_package_id FROM cloudCore.BOOKS WHERE client_id = "+client_id+" AND title like '%"+Title+"%' LIMIT 1) LIMIT 1");
			Log.info("here : "+"SELECT id FROM cloudCore.COLLECTION_BOOK_MAP WHERE BOOK_ID=(SELECT book_package_id FROM cloudCore.BOOKS WHERE client_id = "+client_id+" AND title like '%"+Title+"%' LIMIT 1) LIMIT 1" );
			result.next();
			String id1 = result.getString("id");
			id= Integer.parseInt(""+id1+"");	
			Log.info("Result BookID : " +id);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return id;
		//return new String(encodeValue);
	}
	
	public static String getArchiveDate(int client_id, String Title, String sqlhost, String sqlUsername, String sqlPassword)
	{
		String archive_date = null;
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			result= stmt.executeQuery("SELECT archive_date FROM cloudCore.BOOKS WHERE title like'%"+Title+"%' AND client_id="+client_id+"");
			result.next();
			archive_date = result.getString("archive_date");
			//id= Integer.parseInt(""+id1+"");	
			Log.info("archive_date : " +archive_date);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return archive_date;
	}


	public static String getCat(int client_id, String Title, String sqlhost, String sqlUsername, String sqlPassword)
	{
		String keywords = null;
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			result= stmt.executeQuery("SELECT keywords FROM cloudCore.BOOKS WHERE title LIKE '%"+Title+"%' AND client_id="+client_id+" LIMIT 1");
			result.next();
			Log.info("here : "+"SELECT keywords FROM cloudCore.BOOKS WHERE title LIKE '%"+Title+"%' AND client_id="+client_id+" LIMIT 1");
			keywords = result.getString("keywords");
			//id= Integer.parseInt(""+id1+"");	
			System.out.println("keywords : " +keywords);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return keywords;
	}
	
	public static String getCategoryNAME(int client_id, String Title, String sqlhost, String sqlUsername, String sqlPassword)
	{
		String category = null;
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			result= stmt.executeQuery("SELECT category FROM cloudCore.BOOKS WHERE title LIKE '%"+Title+"%' AND client_id="+client_id+" LIMIT 1");
			result.next();
			Log.info("here : "+"SELECT category FROM cloudCore.BOOKS WHERE title LIKE '%"+Title+"%' AND client_id="+client_id+" LIMIT 1");
			category = result.getString("category");
			//id= Integer.parseInt(""+id1+"");	
			System.out.println("category : " +category);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return category;
	}
	
	
	public static String getAsset(int client_id, String Title, String sqlhost, String sqlUsername, String sqlPassword)
	{
		String subtype = null;
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			result= stmt.executeQuery("SELECT subtype FROM cloudCore.BOOKS WHERE title like '%"+Title+"%' AND client_id="+client_id+";");
			result.next();
			subtype = result.getString("subtype");
			//id= Integer.parseInt(""+id1+"");	
			System.out.println("subtype : " +subtype);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return subtype;		
	}
	
	public static String getReader(String userName,String sqlhost, String sqlUsername, String sqlPassword)
	{
		String reader_key = null;		
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Log.info("userName in query: " +userName);
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			Log.info("userName in query: " +userName);
			result= stmt.executeQuery("SELECT reader_key FROM cloudCore.CLIENT WHERE id=(SELECT client_id FROM cloudCore.USER WHERE username='"+userName+"' AND deleted = 0 LIMIT 1) limit 1");
			result.next();
			Log.info("URL : "+"SELECT reader_key FROM cloudCore.CLIENT WHERE id=(SELECT client_id FROM cloudCore.USER WHERE username='"+userName+"' AND deleted = 0 LIMIT 1) limit 1");
			reader_key = result.getString("reader_key");
			Log.info("Result reader_key : " +reader_key);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return reader_key;		
	}
	
	public static String getIDReader_userID(int userID,String sqlhost, String sqlUsername, String sqlPassword)
	{
		String reader_key = null;		
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			Log.info("userID : " +userID);
			result= stmt.executeQuery("SELECT reader_key FROM cloudCore.CLIENT WHERE id=(SELECT client_id FROM cloudCore.USER WHERE	id="+userID+" LIMIT 1)");
			result.next();
			reader_key = result.getString("reader_key");
			Log.info("Result reader_key : " +reader_key);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return reader_key;		
	}
	
	public static String getCK(int client_id, String sqlhost, String sqlUsername, String sqlPassword)
	{
		String consumer_key = null;		
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			Log.info("client_id : " +client_id);
			result= stmt.executeQuery("SELECT consumer_key FROM cloudCore.CLIENT WHERE id="+client_id+"");
			result.next();
			//reader_key = result.getString("reader_key");
			consumer_key = result.getString("consumer_key");
			Log.info("HERE : " +"SELECT consumer_key FROM cloudCore.CLIENT WHERE id="+client_id+"");
			Log.info("Result consumer_key : " +consumer_key);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return consumer_key;
	}
	
	public static String getSK(int client_id,String sqlhost, String sqlUsername, String sqlPassword)
	{
		String shared_secret = null;		
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			Log.info("client_id : " +client_id);
			result= stmt.executeQuery("SELECT shared_secret FROM cloudCore.CLIENT WHERE id="+client_id+"");
			result.next();
			Log.info("HERE : " +"SELECT shared_secret FROM cloudCore.CLIENT WHERE id="+client_id+"");
			//reader_key = result.getString("reader_key");
			shared_secret = result.getString("shared_secret");
			Log.info("Result shared_secret : " +shared_secret);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return shared_secret;		
	}	
	
	public static String getEbookId(int client_id,String title,String sqlhost, String sqlUsername, String sqlPassword)
	{
		String bookcode = null;		
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			Log.info("client_id : " +client_id);
			result= stmt.executeQuery("SELECT bookcode FROM cloudCore.BOOKS WHERE title LIKE '%"+title+"Reflow_epub_UPD_%' AND client_id="+client_id+" LIMIT 1;");
			result.next();
			Log.info("HERE : " +"SELECT bookcode FROM cloudCore.BOOKS WHERE title LIKE '%"+title+"Reflow_epub_UPD_%' AND client_id="+client_id+" LIMIT 1;");
			//reader_key = result.getString("reader_key");
			bookcode = result.getString("bookcode");
			Log.info("Result bookcode : " +bookcode);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return bookcode;		
	}	
	
	public static int getClientId(int userID,String sqlhost, String sqlUsername, String sqlPassword)
	{
		int client_id = 0;		
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			Log.info("userID : " +userID);
			result= stmt.executeQuery("SELECT client_id FROM cloudCore.USER WHERE id="+userID+"");
			result.next();
			Log.info("HERE : " +"SELECT client_id FROM cloudCore.USER WHERE id="+userID+"");
			//client_id= Integer.parseInt(""+client_id+"");
			String client_id1 = result.getString("client_id");
			client_id= Integer.parseInt(""+client_id1+"");
			Log.info("Result client_id : " +client_id);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return client_id;		
	}	
	
	public static int get_bookID_down(int bookID1,int bookId,String sqlhost, String sqlUsername, String sqlPassword)
	{
		int format_id = 0;		
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			Log.info("bookID1 : " +bookID1);
			
			result= stmt.executeQuery("SELECT schemaNAme FROM cloudCore.CLIENT WHERE Id IN (SELECT client_id FROM cloudCore.BOOKS WHERE ID IN (SELECT book_id FROM cloudCore.COLLECTION_BOOK_MAP WHERE ID = "+bookID1+"))");
			result.next();
			String schemaNAme = result.getString("schemaNAme");
			System.out.println("schemaNAme : " +schemaNAme);
			
			result= stmt.executeQuery("SELECT format_id FROM "+schemaNAme+".BOOK_FORMAT_MAP WHERE book_id="+bookId+" LIMIT 5");
			result.next();
			Log.info("HERE : " +"SELECT format_id FROM "+schemaNAme+".BOOK_FORMAT_MAP WHERE book_id="+bookId+" LIMIT 5");
			//client_id= Integer.parseInt(""+client_id+"");
			String format_id1 = result.getString("format_id");
			format_id = Integer.parseInt(""+format_id1+"");
			Log.info("Result format_id : " +format_id);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return format_id;		
	}	
	public static String getReader_clientID(long clientID,String sqlhost, String sqlUsername, String sqlPassword)
	{
		String reader_key = null;		
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Log.info("clientID in query: " +clientID);
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			Log.info("clientID in query: " +clientID);
			result= stmt.executeQuery("SELECT reader_key FROM cloudCore.CLIENT WHERE  id="+clientID+"");
			result.next();
			Log.info("URL : "+"SELECT reader_key FROM cloudCore.CLIENT WHERE  id="+clientID+"");
			reader_key = result.getString("reader_key");
			Log.info("Result reader_key : " +reader_key);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return reader_key;		
	}
	
	public static String getReader_user_ID(int user_id,String sqlhost, String sqlUsername, String sqlPassword)
	{
		String reader_key = null;		
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Log.info("user_id in query: " +user_id);
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			Log.info("user_id in query: " +user_id);
			result= stmt.executeQuery("SELECT reader_key FROM cloudCore.CLIENT WHERE id=(SELECT client_id FROM cloudCore.CLIENT_USER_MAP WHERE user_id="+user_id+") AND deleted =0");
			result.next();
			Log.info("URL : "+"SELECT reader_key FROM cloudCore.CLIENT WHERE id=(SELECT client_id FROM cloudCore.CLIENT_USER_MAP WHERE user_id="+user_id+") AND deleted =0");
			reader_key = result.getString("reader_key");
			Log.info("Result reader_key : " +reader_key);
			stmt.close();
			con.close();
		} catch (SQLException exp) {
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
		return reader_key;		
	}
	
	/*public static String getCKSK(int client_id, String sqlhost, String sqlUsername, String sqlPassword)
	{
		String[] cksk =null;
		//String ck1 = null;	
		//String  ck2 = null;	
	
		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://172.18.10.147:3306","readonly","readonly@123");
			Connection con = DriverManager.getConnection(sqlhost,sqlUsername,sqlPassword);
			Statement stmt = con.createStatement();
			ResultSet result = null;
			Log.info("client_id : " +client_id);
			result = stmt.executeQuery("SELECT consumer_key,shared_secret FROM cloudCore.CLIENT WHERE id="+client_id+"");
			result.next();
			//reader_key = result.getString("reader_key");
			String ck1 = result.getString("consumer_key");
			//result.next();
			String ck2 = result.getString("shared_secret");
			//String[] deviceT = {"IPAD","ANDROID","WINDOWS","PC","HTML5"};
			//String [] here  ={"\""+ck1+"\"","\""+ck2+"\""};
			//String[] 
			cksk  ={ck1, ck2};
			//Log.info("HERE : " +"SELECT consumer_key,shared_secret FROM cloudCore.CLIENT WHERE id="+client_id+"");
			Log.info("Result consumer_key : " +cksk);
			Log.info("Result shared_secret : " +cksk);
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cksk;
	}*/
	
}

