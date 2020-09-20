package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class SaveReadingPercentage {

	public static Response saveReadingPercentage(int bookID1,int percentge,String usertoken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("SaveReadingPercentage.device:"+deviceType+".id:"+bookID1+"percntge:"+percentge+"");
			jsonResponse = given()
					.header("Content-Type","application/json")
					.queryParam("usertoken", usertoken)
					.body("[{\"bookId\":"+bookID1+",\"percentage\":"+percentge+"}]")	
					//?t=1586866923045
					.post("/DistributionServices/services/api/reader/user/"+deviceID+"/"+deviceType+"/saveReadingPercentage");
			
			Log.info("SaveReadingPercentage.device:"+deviceType+".id:"+bookID1+"percntge:"+percentge+" Response: "+jsonResponse.then().extract().response().prettyPrint());

		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());

			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
