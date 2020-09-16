package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.restassured.response.Response;

import com.hurix.api.utility.EpochTime;
import com.hurix.automation.utility.Log;

public class Bookdetails {
	public static Response bookdetails(Object startDate,String userToken,String deviceID,String DeviceType,String ebookID1,String assetType)
	{
		//GETbookdetailsPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/distribution/123/pc/book/details?bookID="+com.hurix.api.runner.RestAssured.ebookID1+"&type=BOOK&t=1530958792";

		Response jsonResponse = null;
		try {

			Log.startTestCase("bookdetails");
			System.out.println("assetType : " +assetType);
			System.out.println("ebookID1 : " +ebookID1);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datenew = df.parse(""+startDate+"");
			long epoch = datenew.getTime();
			epoch=epoch/1000;
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DeviceType+"/book/details?bookID="+ebookID1+"&type="+assetType+"&t="+epoch+"");

			Log.info("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DeviceType+"/book/details?bookID="+ebookID1+"&type="+assetType+"&t="+epoch+"");
			/*Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "isbn");
			Validation.responseKeyValidation_key(jsonResponse, "bookCode");
			Validation.responseKeyValidation_key(jsonResponse, "collectionID");*/
			Log.info("Bookdetails Response: "+jsonResponse.then().extract().response().prettyPrint());
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
