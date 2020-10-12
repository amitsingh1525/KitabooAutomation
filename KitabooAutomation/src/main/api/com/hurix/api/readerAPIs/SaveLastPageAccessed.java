package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class SaveLastPageAccessed {
	
public static String SaveLastPageAccessedBody;
	
	public static Response saveLastPageAccessed(int bookID1,int folioId,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			SaveLastPageAccessedBody = "{\"bookId\":\""+bookID1+"\"}";
			Log.startTestCase("SaveLastPageAccessed.bookID1="+bookID1+"");
			Log.info("markAsFavourite_URL: "+"/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/"+bookID1+"/saveLastPageAccessed?folioId=1");
			Log.info("POSTmarkAsFavouriteBody : "+SaveLastPageAccessedBody);
			Log.info("userToken : "+userToken);
			Log.info("folioId : "+folioId);
			jsonResponse = given()
					 .header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(SaveLastPageAccessedBody)
					.queryParam("folioId", folioId)
					.post("/DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/"+bookID1+"/saveLastPageAccessed");					
							
			Log.info("SaveLastPageAccessed.bookID1="+bookID1+".folioId: "+folioId+" Response: "+jsonResponse.then().extract().response().prettyPrint());
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