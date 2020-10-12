package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.hurix.automation.utility.Log;

public class FetchSessionHistory {
	public static Response fetchSessionHistory(String startDate, String endDate, String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			Log.info("startDate before : "+startDate);
			Log.info("endDate before : "+endDate);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datenew = df.parse(""+startDate+"");
			long startDate1 = datenew.getTime();
			startDate1=startDate1/1000;
			
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datenew1 = df1.parse(""+endDate+"");
			long endDate1 = datenew1.getTime();
			endDate1=endDate1/1000;
			
			Log.startTestCase("FetchSessionHistory");
			Log.info("startDate1 : "+startDate1);
			Log.info("endDate1 : "+endDate1);
			
			Log.info("URL : "+"/DistributionServices/ext/api/fetchSessionHistory");
			jsonResponse = given()
					.queryParam("startDate",startDate1)
					.queryParam("endDate", endDate1)
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.get("/DistributionServices/ext/api/fetchSessionHistory");

			Log.info("FetchSessionHistory Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception e) {
			Log.fail(e.getMessage());
			Log.fail("fails due to"+ e.getCause());
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
