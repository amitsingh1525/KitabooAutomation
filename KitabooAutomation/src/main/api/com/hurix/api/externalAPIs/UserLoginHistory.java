package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class UserLoginHistory {
	
	public static Response userLoginHistory(String startDate, String endDate, String consumerKey, String consumerSecret)
	{

		Response jsonResponse = null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datenew = df.parse(""+startDate+"");
			long startDate1 = datenew.getTime();
			startDate1=startDate1/1000;
			
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datenew1 = df1.parse(""+endDate+"");
			long endDate1 = datenew1.getTime();
			endDate1=endDate1/1000;
			
			Log.startTestCase("UserLoginHistory");
			Log.info("startDate1 : "+startDate1);
			Log.info("endDate1 : "+endDate1);
			
			Log.info("URL : "+"/DistributionServices/ext/api/userLoginHistory");
			jsonResponse = given()
					.queryParam("startDate",startDate1)
					.queryParam("endDate", endDate1)
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.get("/DistributionServices/ext/api/userLoginHistory");

			Log.info("UserLoginHistory Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
