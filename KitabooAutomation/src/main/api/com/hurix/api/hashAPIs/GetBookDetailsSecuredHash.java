package com.hurix.api.hashAPIs;

import static io.restassured.RestAssured.given;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import io.restassured.response.Response;
import com.hurix.api.runner.RestAssured;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class GetBookDetailsSecuredHash {
	
	public static Response getBookDetailsSecuredHash(Object startDate,String userToken,String deviceID,String deviceType,int bookID1,String assetType)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("GetBookDetailsSecuredHash");
			Log.info("BEFORE startDate : "+startDate);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datenew = df.parse(""+startDate+"");
			long epoch = datenew.getTime();
			epoch=epoch/1000;
			Log.info("AFTER epoch : "+epoch);
			Log.info("URL : "+"/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/book/details?bookID="+bookID1+"&type="+assetType+"&t="+epoch+"");
			Log.info("userToken : "+userToken);
			//startDate = EpochTime.getEpochTime(""+startDate+"");
			System.out.println("Header HASH == " +MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/book/details?bookID="+bookID1+"&type="+assetType+"&t="+epoch+""));
			jsonResponse = given()
					.header("usertoken",userToken)	
					.header("hash",MD5Genration.hashGenration(RestAssured.detail+"/DistributionServices/services/api/reader/distribution/123/pc/book/details?bookID="+bookID1+"&type="+assetType+"&t="+epoch+""))
					.get("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/book/details?bookID="+bookID1+"&type="+assetType+"&t="+epoch+"");
			
			

			Log.info("GetBookDetailsSecuredHash Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
