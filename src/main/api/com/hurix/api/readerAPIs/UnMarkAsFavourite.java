package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class UnMarkAsFavourite {
	
	public static String unMarkAsFavouritePath;
	public static Response unMarkAsFavourite(int markbookID,String userToken, String deviceID,String deviceType)
	{
		
		Response jsonResponse = null;
		try {			
			Log.startTestCase("unMarkAsFavourite."+markbookID+"");
			Log.info("markfav_bookID: " +markbookID);
			jsonResponse = given()
					.header("usertoken",userToken)
					.get("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/unMarkAsFavourite?bookid="+markbookID+"");
			
			Log.info("UnMarkAsFavourite: "+jsonResponse.then().extract().response().prettyPrint());
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
