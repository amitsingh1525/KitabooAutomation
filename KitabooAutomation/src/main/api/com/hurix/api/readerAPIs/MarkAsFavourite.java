package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class MarkAsFavourite {
	
	//public static String POSTmarkAsFavouritePath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/user/123/IPAD/markAsFavourite";

	public static String POSTmarkAsFavouriteBody;// = "{\"bookId\":\""+com.hurix.api.runner.RestAssured.bookID1+"\"}";

	public static Response markAsFavourite(int bookID1,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			POSTmarkAsFavouriteBody = "{\"bookId\":\""+bookID1+"\"}";
			Log.startTestCase("markAsFavourite.bookID1="+bookID1+"");
			//System.out.println("POSTmarkAsFavouriteRequestURL:" +POSTmarkAsFavouritePath);
			System.out.println("POSTmarkAsFavouriteBody: "+POSTmarkAsFavouriteBody);
			jsonResponse = given()
					 .header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(POSTmarkAsFavouriteBody)					
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
