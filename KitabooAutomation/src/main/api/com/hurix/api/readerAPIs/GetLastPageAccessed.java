package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class GetLastPageAccessed {
	public static Response getLastPageAccessed(int bookID1,String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {

			Log.startTestCase("GetLastPageAccessed.device:"+deviceType+".bookID1:"+bookID1+"");
			jsonResponse = given()
					.header("usertoken",userToken)
					.get("DistributionServices/services/api/reader/books/"+deviceID+"/"+deviceType+"/"+bookID1+"/getLastPageAccessed");
			Log.info("usertoken : "+userToken);
			Log.info("GetLastPageAccessed.device:"+deviceType+".bookID1:"+bookID1+" Response: "+jsonResponse.then().extract().response().prettyPrint());
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
