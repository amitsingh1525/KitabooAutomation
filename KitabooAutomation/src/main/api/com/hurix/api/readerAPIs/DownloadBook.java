package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class DownloadBook {
	
	
	public static Response downloadBook(String userToken,String deviceID,String deviceType,int bookID1,String State)
	{		
		Response jsonResponse = null;
		try {
            String[] state1 = {"online","offline"};
        	for(int i=0; i<=1 ;i++)
            {
            Log.startTestCase("downloadBookFor."+deviceType+"_"+state1[i]+"");
			Log.info("bookID here: "+bookID1);
			Log.info("formate : "+deviceType);
			Log.info("deviceID : "+deviceID);
			Log.info("State: "+state1[i]);
			Log.info("URL : "+"/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/"+bookID1+"/downloadBook?state="+state1[i]+"");
			//System.out.println("downloadBookPathANDROIDRequestURL:" +downloadBookPathANDROID);
			Log.info("userToken : "+userToken);
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/"+bookID1+"/downloadBook?state="+state1[i]+"");

			Log.info("downloadBookFor."+deviceType+"_"+state1[i]+" Response: "+jsonResponse.then().extract().response().prettyPrint());
            }
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
