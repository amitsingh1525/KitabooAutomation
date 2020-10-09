package com.hurix.api.validation;

import org.hamcrest.core.Is;
import org.junit.Assert;

import com.hurix.automation.utility.Log;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class ResponseValueValidation {

	public static void responseStringValidation(Response response, String JSONXpath, String value) {
		String[] JSONXpaths = JSONXpath.split(",");
		String[] values = value.split(",");
		for(int i = 0; i < JSONXpaths.length; i++) {
			try {
				response.then().assertThat().body(JSONXpaths[i], Is.is(values[i]));
				Log.pass("JSON path '"+JSONXpaths[i]+"' matched with expected value '"+values[i]+"'.");
			} catch (AssertionError e) {
				Log.fail(e.getMessage());
				continue;
			}
		}
	}
	
	
	public static void responseValueValidation(Response response, String JSONXpath, Object value) {
		try {
			response.then().assertThat().body(JSONXpath, Is.is(value));
			Log.pass("JSON path '"+JSONXpath+"' matched with expected value '"+value+"'.");
		} catch (AssertionError e) {
			Log.fail(e.getMessage());
		}
	}
	
	public static void responseKeyValidation(Response jsonResponse, String key)
	{
		ResponseBody<?> body = jsonResponse.getBody();
		if(body!=null)
		{
			String bodyStringValue = body.asString();
			try {
				Assert.assertTrue(bodyStringValue.contains(key));
				
				Log.pass("Parameter is Present= "+key+"  .inside response body.");
			} catch (AssertionError e) {
				e.printStackTrace();
				Log.fail("Expected Parameter is NOT Present= "+key+" .inside response body.");
				//Log.fail("validated Not FOUND are : " +key+ "=" +value );
			}	
		}else{
			Log.warn("Response null found!");
		}
	}
	
}
