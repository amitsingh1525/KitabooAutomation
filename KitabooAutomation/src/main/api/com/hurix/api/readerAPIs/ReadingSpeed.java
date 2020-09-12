package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ReadingSpeed {
	
	public static Response readingSpeed(int bookID1,int bookID2,int bookID3,int bookID4,int bookID5,String usertoken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("ReadingSpeed.device:"+deviceType+".id1="+bookID1+".id2="+bookID2+".id3="+bookID3+"");
			jsonResponse = given()
					.header("Content-Type","application/json")
					.queryParam("usertoken", usertoken)
					.body("{\"bookIds\":["+bookID1+","+bookID2+","+bookID3+","+bookID4+","+bookID5+"]}")					
					.post("/DistributionServices/services/api/readerExt/user/"+deviceID+"/"+deviceType+"/fetchReadingPercentage");
			Log.info("ReadingSpeed URL : "+"/DistributionServices/services/api/readerExt/user/"+deviceID+"/"+deviceType+"/fetchReadingPercentage");
			Log.info("usertoken : "+usertoken);
			Log.info("ReadingSpeed.device:"+deviceType+".id1="+bookID1+".id2="+bookID2+".id3="+bookID3+" Response: "+jsonResponse.then().extract().response().prettyPrint());

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
