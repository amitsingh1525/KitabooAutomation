package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class MarkRead {
	public static String MarkReadBody;
	
	public static Response markRead_read(int bookID1,int bookID2,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			MarkReadBody = "{\"readBookIds\":["+bookID1+","+bookID2+"]}";
			Log.startTestCase("MarkRead_read");		
			Log.info("MarkReadURL: "+"/DistributionServices/services/api/readerExt/user/"+deviceID+"/"+deviceType+"/markRead");
			Log.info("MarkReadBody : "+MarkReadBody);
			Log.info("userToken : "+userToken);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(MarkReadBody)					
					.post("/DistributionServices/services/api/readerExt/user/"+deviceID+"/"+deviceType+"/markRead");					
							
			Log.info("MarkRead Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
	public static Response markRead_unmarkRead(int bookID1,int bookID2,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			MarkReadBody = "{\"unreadBookIds\":["+bookID1+","+bookID2+"]}";
			Log.startTestCase("MarkRead_unmarkRead");		
			Log.info("MarkReadURL: "+"/DistributionServices/services/api/readerExt/user/"+deviceID+"/"+deviceType+"/markRead");
			Log.info("MarkReadBody : "+MarkReadBody);
			Log.info("userToken : "+userToken);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(MarkReadBody)					
					.post("/DistributionServices/services/api/readerExt/user/"+deviceID+"/"+deviceType+"/markRead");					
							
			Log.info("MarkRead Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
	public static Response markRead(int bookID3,int bookID4,int bookID5,int bookID6,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			MarkReadBody = "{\"readBookIds\":["+bookID3+","+bookID4+"],\"unreadBookIds\":["+bookID5+","+bookID6+"]}";
			Log.startTestCase("MarkRead_read_unread");		
			Log.info("MarkReadURL: "+"/DistributionServices/services/api/readerExt/user/"+deviceID+"/"+deviceType+"/markRead");
			Log.info("MarkReadBody : "+MarkReadBody);
			Log.info("userToken : "+userToken);
			jsonResponse = given()
					.header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(MarkReadBody)					
					.post("/DistributionServices/services/api/readerExt/user/"+deviceID+"/"+deviceType+"/markRead");					
							
			Log.info("MarkRead Response: "+jsonResponse.then().extract().response().prettyPrint());
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
