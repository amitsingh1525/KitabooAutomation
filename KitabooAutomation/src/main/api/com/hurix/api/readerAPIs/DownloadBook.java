package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class DownloadBook {
	public static String downloadBookPathANDROID;
	public static String downloadBookPathIpad;
	public static String downloadBookPathwindows;
	public static String downloadBookPathHtml5;

	public static int bookID;

	public static Response downloadBook(String userToken,String deviceID,String deviceType,int bookID1,String State)
	{		
		Response jsonResponse = null;
		try {

			Log.startTestCase("downloadBookFor."+deviceType+"_"+State+"");
			Log.info("bookID here: "+bookID1);
			Log.info("deviceType : "+deviceType);
			Log.info("URL : "+"/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/"+bookID1+"/downloadBook?state="+State+"");
			//System.out.println("downloadBookPathANDROIDRequestURL:" +downloadBookPathANDROID);
			Log.info("userToken : "+userToken);
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/"+bookID1+"/downloadBook?state="+State+"");

			Log.info("downloadBookFor."+deviceType+"_"+State+" Response: "+jsonResponse.then().extract().response().prettyPrint());
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
