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
			
			Log.info("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DeviceType+"/book/details?bookID="+bookID1+"&type=&t="+startDate1+"");
			
			jsonResponse = given()
					.header("usertoken",userToken)	
					.queryParam("bookID", bookID1)	
					.queryParam("type", "")
					.queryParam("t", startDate1)
					//?bookID="+bookID1+"&type="+assetType+"&t="+startDate+"
					.get("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+DeviceType+"/book/details");

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
