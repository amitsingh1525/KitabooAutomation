package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class SaveReadingPercentage {

	public static Response saveReadingPercentage(int bookID1,int percentge,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("SaveReadingPercentage.id:"+bookID1+".percntge:"+percentge+"");
			Log.info("usertoken : "+userToken);
			Log.info("URL : "+"/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/saveReadingPercentage");
			Log.info("BODY "+"[{\"bookId\":"+bookID1+",\"percentage\":"+percentge+"}]");
			jsonResponse = given()
					.header("Content-Type","application/json")
					.queryParam("usertoken", userToken)
					.body("[{\"bookId\":"+bookID1+",\"percentage\":"+percentge+"}]")
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/saveReadingPercentage");
			
			Log.info("SaveReadingPercentage.device:"+deviceType+".id:"+bookID1+".percntge:"+percentge+" Response: "+jsonResponse.then().extract().response().prettyPrint());

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
