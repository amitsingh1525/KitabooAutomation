package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class MarkAsFavourite {
	
	public static String markAsFavouriteBody;
	
	public static Response markAsFavourite(int bookID1,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			markAsFavouriteBody = "{\"bookId\":\""+bookID1+"\"}";
			Log.startTestCase("markAsFavourite.bookID1="+bookID1+"");
			Log.info("markAsFavourite_URL: "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/markAsFavourite");
			Log.info("POSTmarkAsFavouriteBody : "+markAsFavouriteBody);
			Log.info("userToken : "+userToken);
			System.out.println("POSTmarkAsFavouriteBody: "+markAsFavouriteBody);
			jsonResponse = given()
					 .header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(markAsFavouriteBody)					
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/markAsFavourite");					
							
			Log.info("MarkAsFavourite.bookID1="+bookID1+" Response: "+jsonResponse.then().extract().response().prettyPrint());
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
