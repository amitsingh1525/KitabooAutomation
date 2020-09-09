package com.hurix.api.utility;

import org.junit.Assert;
import com.relevantcodes.extentreports.ExtentReports;
import com.hurix.automation.utility.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;


public class Validation {
	static ExtentReports extent;
	static ResponseBody<?> body;
	public static void responseHeaderCodeValidation(Response jsonResponse, int statusCode) {

		try {
			try {
				Assert.assertEquals(statusCode, jsonResponse.getStatusCode());
				//Assert.assertThat(statusCode, jsonResponse.then().extract().path("responseCode"));
				Log.pass("successFull Header Validate, status code is :: " +jsonResponse.getStatusCode());
			} catch (AssertionError e) {
				//Assert.assertNotSame(statusCode, jsonResponse.getStatusCode());
				Log.fail("Fail Header Validated, status code is :: " +jsonResponse.getStatusCode());
				e.printStackTrace();
			}

		} catch (AssertionError e) {
			Log.fail(e.getMessage());		
			//test.log(LogStatus.FAIL,"Expected Status code Is ::" +statusCode+ ", instead of getting  :: " +jsonResponse.getStatusCode());
		}
		catch(Exception e)
		{
			Log.fail(e.getMessage());
			//test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}	

	public static void responseTimeValidation(Response jsonResponse)
	{
		try {			
			long time =jsonResponse.time();
			Log.pass("API Reposne Time Is :: " +time);

		} catch (Exception e) {
			//test.log(LogStatus.FAIL, e.fillInStackTrace());
			Log.fail(e.getMessage());
		}
	}

	public static void responseCodeValidation1(Response jsonResponse, int statusCode) {

		try {
			try {
				int act_responsecode = jsonResponse.then().extract().path("responseCode");
				System.out.println("response ="+act_responsecode);//200
				System.out.println("statusCode = " +statusCode);//200
				if(act_responsecode == statusCode)
				{
					System.out.println("In the Pass Assertion");
					Log.pass("successFull Actual Response code Validate, status code is :: " +jsonResponse.then().extract().path("responseCode"));
				}
				else
				{
					System.out.println("in the Fail Assertion");
					Log.fail("Fail Actual Response code Validate, status code is :: " +jsonResponse.then().extract().path("responseCode"));
				}
				//Assert.assertSame(jsonResponse.then().extract().path("responseCode"),statusCode);
				//Log.pass("successFull Validate, status code is :: " +jsonResponse.getStatusCode());
			} catch (AssertionError e) {
				//Assert.assertNotSame(jsonResponse.then().extract().path("responseCode"),statusCode);
				Log.fail("Fail Validated, status code is :: " +jsonResponse.getStatusCode());
				e.printStackTrace();
			}

		} catch (AssertionError e) {
			Log.fail(e.getMessage());		
			//test.log(LogStatus.FAIL,"Expected Status code Is ::" +statusCode+ ", instead of getting  :: " +jsonResponse.getStatusCode());
		}
		catch(Exception e)
		{
			Log.fail(e.getMessage());
			//test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}	


	public static void responseKeyValidation(Response jsonResponse, String key,String value)
	{
		try {			
			//JSONObject array = new JSONObject(jsonResponse .getBody().asString());
			//for(String i=0;i<array.length();i++)

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
				//Log.info("Whole string :: " +keyv.equals(value));
				Log.pass("validated value are : " +key1.equals(value));
			}catch (AssertionError e) {				
				e.printStackTrace();
				Log.fail("validated Not FOUND are : " +key1+ "=" +value);
			}
		} catch (Exception e) 
		{			
			Log.fail(e.getMessage());
		}
	}

	public static void responseKeyValidation_key(Response jsonResponse, String key)
	{
		try {			
			//JSONObject array = new JSONObject(jsonResponse .getBody().asString());
			//for(String i=0;i<array.length();i++)
			body = jsonResponse.getBody();
			String bodyStringValue = body.asString();
			Log.info("KEY string :: " +key);
			try {
				Assert.assertTrue(bodyStringValue.contains(key));
				Log.pass("validation pass Parameter is Present are : " +key);
			} catch (AssertionError e) {
				e.printStackTrace();
				Log.fail("validated Asserting for contails= 1 Not FOUND are : " +key);
				//Log.fail("validated Not FOUND are : " +key+ "=" +value );
			}
		} catch (Exception e) 
		{			
			Log.fail(e.getMessage());
		}
	}
	public static void responseNOTKeyValidation_key(Response jsonResponse, String key)
	{
		try {			
			//JSONObject array = new JSONObject(jsonResponse .getBody().asString());
			//for(String i=0;i<array.length();i++)
			body = jsonResponse.getBody();
			String bodyStringValue = body.asString();
			Log.info("Whole string :: " +key);

			try {
				Log.info("HERE we are-> "+" bodyStringValue: :"+bodyStringValue+" key: :"+key+"");
				if(bodyStringValue != key)
				{
					//Assert.assertFalse(bodyStringValue.contains(key));
					Log.pass("validation key Not Present pass Parameter is Present are : " +key);
				}
				else if(bodyStringValue == key)
				{
					Log.fail("validated key Not Present Fails Asserting for contails= 1 Not FOUND are : " +key);

				}
			} catch (AssertionError e) {
				e.printStackTrace();
				Log.fail("validated key Not Present Fails Asserting for contails= 1 Not FOUND are : " +key);

				//Log.fail("validated Not FOUND are : " +key+ "=" +value );
			}
		} catch (Exception e) 
		{			
			Log.fail(e.getMessage());
		}
	}
	public static void responseISGreater(String  variable , int key,int value)
	{
		try {

			if(key >= value)
			{
				Log.pass("size Validation pass is : " + ""+variable+""+" =$GREATER THEN EQUALS TO$: "+value);
			}
			else
			{
				Log.fail("size Validation pass is : " +""+variable+""+" =$GREATER THEN EQUALS TO$: "+value);
			}

		} catch (Exception e) {

			e.printStackTrace();
			Log.fail("size Validation pass is : " +""+variable+""+" =$GREATER THEN EQUALS TO$: "+value);
		}

	}
	public static void responseKeyAndValue(Response jsonResponse, String key,String value)
	{
		try {
			try {			
				//JSONObject array = new JSONObject(jsonResponse .getBody().asString());
				//for(String i=0;i<array.length();i++)
				body = jsonResponse.getBody();
				String bodyStringValue = body.asString();
				Log.info("Whole string :: " +key);
				String chunk=null;
				try {
					chunk = "\""+key+"\": "+"\""+value+"\"";
					Log.info("WHOLE STRING is key:value = "+chunk);

					if(bodyStringValue.contains(chunk))
					{
						//Assert.assertTrue(bodyStringValue.contains(here1));
						Log.pass("validation pass Parameter is Present are : " +chunk);
					}
					else
					{
						Log.fail("validated Asserting for contails= 1 Not FOUND are : " +chunk);
					}
				} catch (AssertionError e) {
					e.printStackTrace();
					Log.fail("validated Asserting for contails= 1 Not FOUND are : " +chunk);
					//Log.fail("validated Not FOUND are : " +key+ "=" +value );
				}
			} catch (Exception e) 
			{			
				Log.fail(e.getMessage());
			}
		} catch (Exception e) 
		{			
			Log.fail(e.getMessage());
		}
	}
	
	public static void responseINTEGERKeyAndValue(Response jsonResponse, String key,Object  value)
	{
		try {
			try {			
				//JSONObject array = new JSONObject(jsonResponse .getBody().asString());
				//for(String i=0;i<array.length();i++)
				body = jsonResponse.getBody();
				String bodyStringValue = body.asString();
				Log.info("Whole string :: " +key);
				String chunk=null;
				try {
					//chunk = "\""+key+"\": "+"\""+value+"\"";
					chunk = "\""+key+"\": "+""+value+"";
					Log.info("WHOLE STRING INTEGER is key:value = "+chunk);

					if(bodyStringValue.contains(chunk))
					{
						//Assert.assertTrue(bodyStringValue.contains(here1));
						Log.pass("validation pass INTEGER Parameter is Present are : " +chunk);
					}
					else
					{
						Log.fail("validated INTEGER Asserting for contails= 1 Not FOUND are : " +chunk);
					}
				} catch (AssertionError e) {
					e.printStackTrace();
					Log.fail("validated Asserting INTEGER for contails= 1 Not FOUND are : " +chunk);
					//Log.fail("validated Not FOUND are : " +key+ "=" +value );
				}
			} catch (Exception e) 
			{			
				Log.fail(e.getMessage());
			}
		} catch (Exception e) 
		{			
			Log.fail(e.getMessage());
		}
	}


	/*public static void responseKeyGreater(Response jsonResponse, String key,String value)
	{
		try {
			try {			
				//JSONObject array = new JSONObject(jsonResponse .getBody().asString());
				//for(String i=0;i<array.length();i++)
				body = jsonResponse.getBody();
				String bodyStringValue = body.asString();
				Log.info("Whole string :: " +key);
				String here2=null;
				try {
					here2 = "\""+key+"\": "+"\""+value+"\"";
					Log.info("WHOLE STRING is here2 = "+here2);

					if(bodyStringValue .>= here2)
					{
						//Assert.assertTrue(bodyStringValue.contains(here1));
						Log.pass("validation pass Parameter is Present are : " +here2);
					}
					else
					{
						Log.fail("validated Asserting for contails= 1 Not FOUND are : " +here2);
					}
				} catch (AssertionError e) {
					e.printStackTrace();
					Log.fail("validated Asserting for contails= 1 Not FOUND are : " +here2);
					//Log.fail("validated Not FOUND are : " +key+ "=" +value );
				}
			} catch (Exception e) 
			{			
				Log.fail(e.getMessage());
			}
		} catch (Exception e) 
		{			
			Log.fail(e.getMessage());
		}
	}*/
}
