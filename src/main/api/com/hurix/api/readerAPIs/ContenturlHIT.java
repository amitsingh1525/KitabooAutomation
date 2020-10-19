package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class ContenturlHIT {
	@SuppressWarnings("static-access")
	public static Response contenturlHIT(String contenturl)
	{		
		Response jsonResponse = null;
		try {
			Log.startTestCase("ContenturlHIT");
			Log.info("contenturl : "+contenturl);
			Log.info("RUN : "+given().get(""+contenturl+"").asString());
			jsonResponse = ((RestAssured) given().delete("http://qacloud.kitaboo.com/"))
					      .get(""+contenturl+"");

			Log.info("ContenturlHIT Response: "+jsonResponse.then().extract().response().prettyPrint());
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
