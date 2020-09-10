package com.hurix.api.utility;

import org.junit.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.hurix.automation.utility.*;

import io.restassured.response.*;


public class Validation {
	static ExtentReports extent;
	static ResponseBody<?> body;
	public static void responseHeaderCodeValidation(Response jsonResponse, int statusCode) 

	{
		Log.info("Expected StatusCode = "+statusCode); 
		int jsonResponse1=0;
		try {
			if(jsonResponse!=null)
			{
				//Log.info("HERE");
				Log.info("Actual StatusCode = "+jsonResponse.getStatusCode());
				jsonResponse1 = jsonResponse.getStatusCode();
			}
		} catch (AssertionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}

		if(jsonResponse1 == statusCode)
		{
			//Assert.assertThat(statusCode, jsonResponse.then().extract().path("responseCode"));
			Log.pass("successFull Header Validate, status code is = " +jsonResponse.getStatusCode());
		}
		else if(jsonResponse1 != statusCode)
		{
			//Assert.assertThat(statusCode, jsonResponse.then().extract().path("responseCode"));
			Log.fail("Fail Header Validated, status code is = " +jsonResponse);
		}
	}

	public static void responseTimeValidation(Response jsonResponse)
	{
		try {			
			long time =jsonResponse.time();
			//Log.pass("HERE "+jsonResponse+" API Reposne Time Is :: " +time);
			Log.pass("API Reposne Time in MillSeconds Is = " +time);

		} catch (Exception e) {
			Log.fail(e.getMessage());
		}
	}

	public static void responseCodeValidation1(Response jsonResponse, int statusCode) 
	{
		int act_responsecode=0;
		System.out.println("response ="+act_responsecode);//200
		if(jsonResponse!=null){
			act_responsecode = jsonResponse.then().extract().path("responseCode");
			System.out.println("statusCode = " +statusCode);//200
		}
		if(act_responsecode == statusCode)
		{
			System.out.println("In the Pass Assertion");
			Log.pass("successFull Actual Response code Validate, status code is = " +jsonResponse.then().extract().path("responseCode"));
		}
		else
		{
			System.out.println("in the Fail Assertion");
			Log.fail("Fail Actual Response code Validate, status code is = " +jsonResponse);
		}
		//Assert.assertSame(jsonResponse.then().extract().path("responseCode"),statusCode);
		//Log.pass("successFull Validate, status code is :: " +jsonResponse.getStatusCode());
	} 


	/*public static void responseKeyValidation(Response jsonResponse, String key,String value)
	{
		body = jsonResponse.getBody();
		String bodyStringValue = body.asString();
		Log.info("Whole string :: " +key+ "=" +value);
		try {
			Assert.assertTrue(bodyStringValue.contains(key));
			Log.pass("validation pass Parameter is Present are : " +key);
		} catch (AssertionError e) {
			e.printStackTrace();
			Log.fail("validated Asserting for contails= 1 Not FOUND are : " +key);
			//Log.fail("validated Not FOUND are : " +key+ "=" +value );
		}		    
		JsonPath jsonPathEvaluator = jsonResponse.jsonPath();
		String key1 = jsonPathEvaluator.getString(""+key+"");
		Log.info("Here We are");
		Log.info("key :: "+key1);
		try {
			Log.info("Assertion string"+key1.equals(value));
			Assert.assertTrue(key1.equals(value));
			Log.pass("validated value are : " +key1.equals(value));
		}catch (AssertionError e) {				
			e.printStackTrace();
			Log.fail("validated Not FOUND are : " +key1+ "=" +value);
		}
	} */
	public static void responseKeyValidation_key(Response jsonResponse, String key)
	{
		if(jsonResponse!=null)
		{body = jsonResponse.getBody();
		String bodyStringValue = body.asString();
		Log.info("KEY string :: " +key);
		try {
			Assert.assertTrue(bodyStringValue.contains(key));
			Log.pass("validation pass Parameter is Present are = " +key);
		} catch (AssertionError e) {
			e.printStackTrace();
			Log.fail("validated Asserting for contails= 1 Not FOUND are = " +key);
			//Log.fail("validated Not FOUND are : " +key+ "=" +value );
		}	
		}
	}
	public static void responseNOTKeyValidation_key(Response jsonResponse, String key)
	{
		body = jsonResponse.getBody();
		String bodyStringValue = body.asString();
		Log.info("Whole string :: " +key);
		Log.info("HERE we are-> "+" bodyStringValue="+bodyStringValue+" key ="+key+"");
		if(bodyStringValue != key)
		{
			//Assert.assertFalse(bodyStringValue.contains(key));
			Log.pass("validation key Not Present pass Parameter is Present are = " +key);
		}
		else if(bodyStringValue == key)
		{
			Log.fail("validated key Not Present Fails Asserting for contails= 1 Not FOUND are = " +key);

		}	
	}
	public static void responseISGreater(String  variable , int key,int value)
	{
		if(key >= value)
		{
			Log.pass("size Validation pass is : " + ""+variable+""+" =$GREATER THEN EQUALS TO$: "+value);
		}
		else
		{
			Log.fail("size Validation pass is : " +""+variable+""+" =$GREATER THEN EQUALS TO$: "+value);
		}
	}
	
	public static void responseISGreater_String(String  variable , int key,int value)
	{
		//int key= Integer.parseInt(""+key1+"");	
		
		if(key >= value)
		{
			Log.pass("size Validation pass is : " + ""+variable+""+" =$GREATER THEN EQUALS TO$: "+value);
		}
		else
		{
			Log.fail("size Validation pass is : " +""+variable+""+" =$GREATER THEN EQUALS TO$: "+value);
		}
	}
	public static void responseKeyAndValue(Response jsonResponse, String key,String value)
	{
		//JSONObject array = new JSONObject(jsonResponse .getBody().asString());
		//for(String i=0;i<array.length();i++)
		body = jsonResponse.getBody();
		String bodyStringValue = body.asString();
		Log.info("Whole string :: " +key);
		String chunk=null;
		chunk = "\""+key+"\": "+"\""+value+"\"";
		Log.info("WHOLE STRING is key:value = "+chunk);
		if(bodyStringValue.contains(chunk))
		{
			Log.pass("validation pass Parameter is Present are = " +chunk);
		}
		else
		{
			Log.fail("validated Asserting for contails= 1 Not FOUND are = " +chunk);
		}			
	}

	public static void responseINTEGERKeyAndValue(Response jsonResponse, String key,Object  value)
	{
		body = jsonResponse.getBody();
		String bodyStringValue = body.asString();
		Log.info("Whole string :: " +key);
		String chunk=null;

		chunk = "\""+key+"\": "+""+value+"";
		Log.info("WHOLE STRING INTEGER is key:value = "+chunk);

		if(bodyStringValue.contains(chunk))
		{
			//Assert.assertTrue(bodyStringValue.contains(here1));
			Log.pass("validation pass INTEGER Parameter is Present are = " +chunk);
		}
		else
		{
			Log.fail("validated INTEGER Asserting for contails= 1 Not FOUND are = " +chunk);
		}				
	}
}
