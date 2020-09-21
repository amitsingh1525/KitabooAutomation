package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class BulkDownloadBook {
	public static String bulkDownload;
	
	public static Response bulkDownloadBook(String userToken,String deviceID,String deviceType,int bookID1,int bookID2,int bookID3,String State)
	{
		System.out.println("bookID here: "+bookID1);
		
		//downloadBookPathANDROID = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/24Andr24/ANDROID/"+com.hurix.api.runner.RestAssured.bookID1+"/downloadBook?state=offline";
		bulkDownload="{\"bookIds\":[\""+bookID1+"\",\""+bookID2+"\",\""+bookID3+"\"]}";
		Log.info("bookID1: "+bookID1+ "bookID2: " +bookID2+  "bookID3: " +bookID3);
		Response jsonResponse = null;
		try {

			Log.startTestCase("BulkDownloadBook."+deviceType+"_"+State+".bookID1:"+bookID1+".bookID2:"+bookID2+".bookID3:"+bookID3+"");
			Log.info("deviceType : "+deviceType);
			//System.out.println("downloadBookPathANDROIDRequestURL:" +downloadBookPathANDROID);
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("Content-Type","application/json")
					.body(bulkDownload)
					.post("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/bulkDownloadBook?state="+State+"");

			Log.info("BulkDownloadBook."+deviceType+"_"+State+" Response: "+jsonResponse.then().extract().response().prettyPrint());
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
