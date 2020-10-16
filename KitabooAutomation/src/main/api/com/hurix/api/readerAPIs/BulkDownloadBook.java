package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class BulkDownloadBook {
	
	public static Response bulkDownloadBook(String userToken,String deviceID,String deviceType,int bookID1,int bookID2,int bookID3,String State)
	{
		String bulkDownload="{\"bookIds\":[\""+bookID1+"\",\""+bookID2+"\",\""+bookID3+"\"]}";

		Response jsonResponse = null;
		try {
			String[] state1 = {"online","offline"};
			for(int i=0; i<=1 ;i++)
			{
				Log.startTestCase("BulkDownloadBook."+deviceType+"_"+state1[i]+"");
				Log.info("deviceType : " +deviceType);
				Log.info("userToken: " +userToken);
				Log.info("bulkDownload " +bulkDownload);
				Log.info("deviceType: " +deviceType);
				Log.info("deviceID: " +deviceID);
				Log.info("State: " +state1[i]);
				jsonResponse = given()
						.header("usertoken",userToken)	
						.header("Content-Type","application/json")
						.body(bulkDownload)
						.post("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/bulkDownloadBook?state="+state1[i]+"");

				Log.info("BulkDownloadBook."+deviceType+"_"+state1[i]+" Response: "+jsonResponse.then().extract().response().prettyPrint());
			}
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
