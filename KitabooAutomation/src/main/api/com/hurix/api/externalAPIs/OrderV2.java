package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class OrderV2 {
	
	public static Response orderV2_withBookID(long orderNo,String consumerKey, String consumerSecret,int BookID1,String activationDate,String firstName,String lastName,String userName,String password,String userID,String email,int deviceLimit,int type)
	{			
		Response jsonResponse = null;
		try {
			Log.startTestCase("orderV2_with.BookID."+BookID1+"");
			Log.info("userName :"+ "\""+userName+"\"");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					//.contentType(ContentType.URLENC.withCharset("UTF-8"))
					.queryParam("bookID", BookID1)
					.queryParam("activationDate", activationDate)
					.queryParam("firstName", firstName)
					.queryParam("lastName", lastName)
					.queryParam("userName", "\""+userName+"\"")
					.queryParam("password", password)
					.queryParam("userID", userID)
					.queryParam("email", email)
					.queryParam("deviceLimit", deviceLimit)
					.queryParam("type", type)
					.queryParam("orderNo", orderNo)	
					
					.post("/DistributionServices/ext/api/v2/order");
			
			
			Log.info("OrderV2."+BookID1+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
	
	public static Response orderV2_withclientBookID(long orderNo,String clientBookID,String consumerKey, String consumerSecret,int BookID1,String activationDate,String firstName,String lastName,String userName,String password,String userID,String email,int deviceLimit,int type)
	{			
		Response jsonResponse = null;
		try {
			Log.startTestCase("orderV2_withclientBookID."+clientBookID+"");
			Log.info("clientBookID : "+clientBookID);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					.queryParam("bookID", BookID1)
					.queryParam("activationDate", activationDate)
					.queryParam("firstName", firstName)
					.queryParam("lastName", lastName)
					.queryParam("userName", "\""+userName+"\"")
					.queryParam("password", password)
					.queryParam("userID", userID)
					.queryParam("email", email)
					.queryParam("orderNo", orderNo)	
					.queryParam("deviceLimit", deviceLimit)
					.queryParam("type", type)
					.queryParam("clientBookID", clientBookID)	
					.post("/DistributionServices/ext/api/v2/order");		
			
			Log.info("orderV2_with.clientBookID."+clientBookID+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
	
	public static Response orderV2_withcollectionRefID(long orderNo,String collectionRefID,String consumerKey, String consumerSecret,int BookID1,String activationDate,String firstName,String lastName,String userName,String password,String userID,String email,int deviceLimit,int type)
	{			
		Response jsonResponse = null;
		try {
			Log.startTestCase("orderV2_with.CollectionRefID."+collectionRefID+"");
			Log.info("collectionRefID : "+collectionRefID);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					//.queryParam("bookID", BookID1)
					.queryParam("activationDate", activationDate)
					.queryParam("firstName", firstName)
					.queryParam("lastName", lastName)
					.queryParam("userName", "\""+userName+"\"")
					.queryParam("password", password)
					.queryParam("userID", userID)
					.queryParam("email", email)
					.queryParam("orderNo", orderNo)
					.queryParam("deviceLimit", deviceLimit)
					.queryParam("type", type)
					.queryParam("collectionRefID", collectionRefID)	
					.post("/DistributionServices/ext/api/v2/order");		
			
			Log.info("orderV2_with.CollectionRefID."+collectionRefID+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
	
	public static Response orderV2_withlibraryRef(long orderNo,String libraryRef,String consumerKey, String consumerSecret,int BookID1,String activationDate,String firstName,String lastName,String userName,String password,String userID,String email,int deviceLimit,int type)
	{			
		Response jsonResponse = null;
		try {
			Log.startTestCase("orderV2_with.LibraryRef."+libraryRef+"");
			Log.info("libraryRef : "+libraryRef);
			Log.info("orderNo in : "+orderNo);
			Log.info("BookID1 in : "+BookID1);
			Log.info("activationDate in : "+activationDate);
			Log.info("firstName in : "+firstName);
			Log.info("lastName in : "+lastName);
			Log.info("userName: "+ "\""+userName+"\"");
			Log.info("password in : "+password);
			Log.info("userID in : "+userID);
			Log.info("email in : "+email);
						
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					.queryParam("bookID", BookID1)
					.queryParam("activationDate", activationDate)
					.queryParam("firstName", firstName)
					.queryParam("lastName", lastName)
					.queryParam("userName", "\""+userName+"\"")
					.queryParam("password", password)
					.queryParam("userID", userID)
					.queryParam("email", email)
					.queryParam("orderNo", orderNo)	
					.queryParam("deviceLimit", deviceLimit)
					.queryParam("type", type)
					.queryParam("libraryRef",libraryRef)	
					.post("/DistributionServices/ext/api/v2/order");		
			
			Log.info("orderV2_with.LibraryRef."+libraryRef+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}

	public static Response orderV2_withrefID(long orderNo,String refID,String consumerKey, String consumerSecret,int BookID1,String activationDate,String firstName,String lastName,String userName,String password,String userID,String email,int deviceLimit,int type)
	{			
		Response jsonResponse = null;
		try {
			Log.startTestCase("orderV2_with.RefID."+refID+"");
			Log.info("HERE we are  : "+refID);
			Log.info("refID : "+refID);
			Log.info("email : "+email);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					.queryParam("bookID", BookID1)
					.queryParam("activationDate", activationDate)
					.queryParam("firstName", firstName)
					.queryParam("lastName", lastName)
					.queryParam("userName", userName)
					.queryParam("password", password)
					.queryParam("userID", userID)
					.queryParam("email", email)
					.queryParam("orderNo", orderNo)
					.queryParam("deviceLimit", deviceLimit)
					.queryParam("type", type)
					.queryParam("refID", refID)	
					.post("/DistributionServices/ext/api/v2/order");		
			
			Log.info("orderV2_with.RefID."+refID+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}

}
