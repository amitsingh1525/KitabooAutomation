package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class GetSecureURL {
	
	public static String GETgetSecureURLPath;
	public static Response getSecureURL(String userToken,String deviceID,String deviceType,int type)
	{
		//GETgetSecureURLPath = ""+com.hurix.api.utility.ExcelUtils.getbaseURI()+"/DistributionServices/services/api/reader/secure/aasd/IPAD/getSecureURL?entryID&type=3";
		
		Response jsonResponse = null;
		try {
			Log.startTestCase("getSecureURL");
			Log.info("URL : "+"/DistributionServices/services/api/reader/secure/"+deviceID+"/"+deviceType+"/getSecureURL?entryID&type="+type+"");
			Log.info("userToken : "+userToken);
			//System.out.println("GETfetBookListRequestURL:" +GETgetSecureURLPath);
			jsonResponse = given()
					.header("usertoken",userToken)						
					.get("/DistributionServices/services/api/reader/secure/"+deviceID+"/"+deviceType+"/getSecureURL?entryID&type="+type+"");
			
			Log.info("GetSecureURLResponse: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}


}
