package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class Order {
	public static Response order_withBookID(long orderNo,String consumerKey, String consumerSecret,int BookID1,int keyCount,int deviceLimit,int type)
	{			
		Response jsonResponse = null;
		try {
			Log.startTestCase("orderV2_with.BookID."+BookID1+"");
			Log.info("BookID1 :"+ "\""+BookID1+"\"");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					/*.queryParam("orderNo", orderNo)	
					.queryParam("bookID", BookID1)
					.queryParam("type", type)
					.queryParam("keyCount", keyCount)					
					.queryParam("type", type)
					.queryParam("deviceLimit", deviceLimit)	
					.post("/DistributionServices/ext/api/order");*/
					.post("DistributionServices/ext/api/order?orderNo="+orderNo+"&bookID="+BookID1+"&type="+type+"&keyCount="+keyCount+"&deviceLimit="+deviceLimit+"");
			
			
			Log.info("OrderV2."+BookID1+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("Fails due to "+exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}

}
