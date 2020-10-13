package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.utility.EpochTime;
import com.hurix.automation.utility.Log;

public class CompUserBook {	
	
	public static Response compUserBook(int bookID1,long orderNo,String emailID,int deviceLimit,String consumerKey, String consumerSecret)
	{
		//String GETclientUserID_booksPath=""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/ext/api/"+com.hurix.api.runner.RestAssured.clientUserID+"/books?search="+search+"";
		Log.startTestCase("CompUserBook");
		Log.info("bookID1 : " +bookID1);
		Log.info("emailID : " +emailID+""+EpochTime.current()+"@yopmail.com");
		Log.info("bookID1 : " +bookID1);
		Log.info("consumerKey : " +consumerKey);
		Log.info("consumerSecret : " +consumerSecret);
		Log.info("deviceLimit : " +deviceLimit);
		Log.info("URL : " +"DistributionServices/ext/api/compUserBook?"
				+ "orderNo="+orderNo+"&bookID="+bookID1+"&emailId="+emailID+""+EpochTime.current()+"@yopmail.com&deviceLimit="+deviceLimit+"");
		Response jsonResponse = null;
		try {
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/x-www-form-urlencoded")
					.queryParam("orderNo", orderNo)
					.queryParam("bookID", bookID1)
					.queryParam("emailId", emailID+""+EpochTime.current()+"@yopmail.com")
					.queryParam("deviceLimit", deviceLimit)
					.post("/DistributionServices/ext/api/compUserBook");					
							
			Log.info("CompUserBook Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}
