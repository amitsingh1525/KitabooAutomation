package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class Bookdetails {
	public static Response bookdetails(Object startDate,String userToken,String deviceID,String DeviceType,int bookID1,String assetType)
	{
		//GETbookdetailsPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/123/pc/book/details?bookID="+com.hurix.api.runner.RestAssured.ebookID1+"&type=BOOK&t=1530958792";

		Response jsonResponse = null;
		try {

			Log.startTestCase("bookdetails");
			Log.info("assetType : " +assetType);
			Log.info("bookID1 : " +bookID1);
			Log.info("userToken : " +userToken);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datenew = df.parse(""+startDate+"");
			long startDate1 = datenew.getTime();
			startDate1=startDate1/1000;
			jsonResponse = given()
					.header("usertoken",userToken)	
					.queryParam("bookID", bookID1)	
					.queryParam("type", assetType)
					.queryParam("t", startDate1)
					//?bookID="+bookID1+"&type="+assetType+"&t="+startDate+"
					.get("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DeviceType+"/book/details");

			Log.info("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DeviceType+"/book/details?bookID="+bookID1+"&type="+assetType+"&t="+startDate1+"");
			/*Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "isbn");
			Validation.responseKeyValidation_key(jsonResponse, "bookCode");
			Validation.responseKeyValidation_key(jsonResponse, "collectionID");*/
			Log.info("Bookdetails Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
