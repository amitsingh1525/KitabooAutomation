package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class BulkReadingSpeed {
	
	public static Response bulkReadingSpeed(int bookID1,int bookID2,int bookID3,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			String BulkReadingSpeedBODY = "{\"bookIds\":[\""+bookID1+"\",\""+bookID2+"\",\""+bookID3+"\"]}";
			
			Log.startTestCase("BulkReadingSpeed");
			
			Log.info("BulkReadingSpeed_URL: "+"/DistributionServices/services/api/reader/analytics/"+deviceID+"/"+deviceType+"/bulkReadingSpeed");
			Log.info("BulkReadingSpeedBODY : "+BulkReadingSpeedBODY);
			Log.info("userToken : "+userToken);
		
			jsonResponse = given()
					 .header("Content-Type","application/json")
					.header("usertoken",userToken)
					.body(BulkReadingSpeedBODY)					
					.post("/DistributionServices/services/api/reader/analytics/"+deviceID+"/"+deviceType+"/bulkReadingSpeed");					
							
			Log.info("BulkReadingSpeed Response: "+jsonResponse.then().extract().response().prettyPrint());
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
