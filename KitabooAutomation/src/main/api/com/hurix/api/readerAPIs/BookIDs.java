package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class BookIDs {
	
	
	public static Response bookIDs(String userToken,String deviceID,String deviceType)
	{
		//GETgetSecureURLPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/secure/aasd/IPAD/getSecureURL?entryID&type=3";
		
		Response jsonResponse = null;
		try {
			Log.startTestCase("BookIDs");
			Log.info("URL : "+"/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/user/book/ids");
			Log.info("userToken : "+userToken);
			
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/user/book/ids");
			
			Log.info("BookIDs Response : "+jsonResponse.then().extract().response().prettyPrint());
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
