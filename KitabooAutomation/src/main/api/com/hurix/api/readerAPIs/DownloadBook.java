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

	public static Response downloadBookForANDROID_offline()
	{
		System.out.println("bookID here: "+com.hurix.api.runner.RestAssured.bookID1);
		//downloadBookPathANDROID = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/24Andr24/ANDROID/"+com.hurix.api.runner.RestAssured.bookID1+"/downloadBook?state=offline";

		Response jsonResponse = null;
		try {

			Log.startTestCase("downloadBookForANDROID_offline");
			//System.out.println("downloadBookPathANDROIDRequestURL:" +downloadBookPathANDROID);
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)						
					.get("/DistributionServices/services/api/reader/distribution/24Andr24/ANDROID/"+com.hurix.api.runner.RestAssured.bookID1+"/downloadBook?state=offline");

			Log.info("downloadBookForANDROID_offline Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	public static Response downloadBookForIPAD_offline()
	{
		System.out.println("bookID here: "+com.hurix.api.runner.RestAssured.bookID1);
		//downloadBookPathIpad = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+";

		Response jsonResponse = null;
		try {

			Log.startTestCase("downloadBookForIPAD_offline");
			System.out.println("GETdownloadBookForIPAD_offlineRequestURL:" +downloadBookPathIpad);
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)						
					.get("/DistributionServices/services/api/reader/distribution/98ipad98/IPAD/"+com.hurix.api.runner.RestAssured.bookID1+"/downloadBook?state=offline");

			Log.info("downloadBookForIPAD_offline Response : "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

	public static Response downloadBookForWindows_offline()
	{
		System.out.println("bookID here: "+com.hurix.api.runner.RestAssured.bookID1);
		//downloadBookPathwindows = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/54wind54/WINDOWS/"+com.hurix.api.runner.RestAssured.bookID1+"/downloadBook?state=offline";

		Response jsonResponse = null;
		try {

			Log.startTestCase("downloadBookForWindows_offline");
			System.out.println("GETdownloadBookForWindows_offlineRequestURL:" +downloadBookPathwindows);
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)
					.get("/DistributionServices/services/api/reader/distribution/54wind54/WINDOWS/"+com.hurix.api.runner.RestAssured.bookID1+"/downloadBook?state=offline");

			Log.info("downloadBookForWindows_offline Response : "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

	public static Response downloadBookForHTML5_offline()
	{
		System.out.println("bookID here: "+com.hurix.api.runner.RestAssured.bookID1);
		//downloadBookPathHtml5 = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/54html54/HTML5/"+com.hurix.api.runner.RestAssured.bookID1+"/downloadBook?state=offline";

		Response jsonResponse = null;
		try {

			Log.startTestCase("downloadBookForHTML5_offline");
			System.out.println("GETdownloadBookForHTML5_offlineRequestURL:" +downloadBookPathHtml5);
			jsonResponse = given()
					.header("usertoken",com.hurix.api.runner.RestAssured.userToken)
					.get("/DistributionServices/services/api/reader/distribution/54html54/HTML5/"+com.hurix.api.runner.RestAssured.bookID1+"/downloadBook?state=offline");

			Log.info("downloadBookForHTML5_offline Response : "+jsonResponse.then().extract().response().prettyPrint());
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
