package com.hurix.api.utility;

import java.util.List;

import org.junit.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.hurix.automation.utility.*;

import io.restassured.response.*;

public class Validation {
	static ExtentReports extent;
	public static void responseHeaderCodeValidation(Response jsonResponse, int statusCode) 
	{
		int responseCode = jsonResponse.getStatusCode();
		
		if(responseCode == statusCode)
		{
			Log.pass("SuccessFull!! Expected and actual statusCode: " +jsonResponse.getStatusCode()+" is matched.");
		}
		else if(responseCode != statusCode)
		{
			Log.fail("Expected HeaderCode: " +statusCode+" But found: "+jsonResponse.getStatusCode()+". Both are not matched");
		}
	}


	public static void responseTimeValidation(Response jsonResponse)
	{
		try {			
			long time =jsonResponse.time();
			//Log.pass("HERE "+jsonResponse+" API Reposne Time Is :: " +time);
			Log.pass("API Reposne Time in MilliSeconds Is = " +time);

		} catch (Exception e) {
			Log.fail(e.getMessage());
			Log.fail("fails due to"+ e.getCause());
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
			Log.pass("Actual Response code Validate, status code is = " +jsonResponse.then().extract().path("responseCode"));
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
		ResponseBody<?> body = jsonResponse.getBody();
		if(body!=null)
		{
			String bodyStringValue = body.asString();
			try {
				Assert.assertTrue(bodyStringValue.contains(key));
				
				Log.pass("Parameter: "+key+" .is Present inside response body.");
			} catch (AssertionError e) {
				e.printStackTrace();
				Log.fail("Expected Parameter is NOT Present : "+key+" .inside response body.");
				//Log.fail("validated Not FOUND are : " +key+ "=" +value );
			}	
		}else{
			Log.warn("Response null found!");
		}
	}
	public static void responseNOTKeyValidation_key(Response jsonResponse, String key)
	{
		ResponseBody<?> body = jsonResponse.getBody();
		String bodyStringValue = body.asString();
		Log.info("Whole string :: " +key);
		// bodyStringValue="+bodyStringValue+"
		Log.info("HERE we are-> "+" key ="+key+"");
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

	public static void responseISGreater_String(Response variable,String key1,int value)
	{
		int key= Integer.parseInt(""+key1+"");	

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
		ResponseBody<?> body = jsonResponse.getBody();
		//body = jsonResponse.getBody();
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
		//body = jsonResponse.getBody();
		ResponseBody<?> body = jsonResponse.getBody();
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
			Log.fail("validation pass INTEGER Parameter is NOT are= " +chunk);
		}				
	}

	/*public static void responseKeyAndValue_obj(Response jsonResponse, String key,Object value)
	{
		//JSONObject array = new JSONObject(jsonResponse .getBody().asString());
		//for(String i=0;i<array.length();i++)
		ResponseBody<?> body = jsonResponse.getBody();
		//body = jsonResponse.getBody();
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
	}*/

	public static void responseKcount(Response jsonResponse, String key)
	{
		/*int total = 0;
		String[] here2=null;
		String here1=null;*/
		//JSONObject array = new JSONObject(jsonResponse .getBody().asString());
		//for(String i=0;i<array.length();i++)
		//ResponseBody<?> body = jsonResponse.getBody();
		//body = jsonResponse.getBody();
		//String bodyStringValue = body.asString();
		List<String>  key1 =jsonResponse.then().extract().path("bookList.book."+key+"");

		Log.info("key1="+key+" :"+key1.size());

		Log.pass("TOTAL COUNT Present are = " +key1.size());

	}

	public static void responseKcount1(Response jsonResponse, String key)
	{
		int total = 0;
		//String[] here2=null;
		//String here1=null;
		total = Integer.parseInt(""+key+"");	
		
		//JSONObject array = new JSONObject(jsonResponse .getBody().asString());
		//for(String i=0;i<array.length();i++)
		
		Log.pass("TOTAL TOTALS Present are = " +total);

	}
	
	public static void responseKeyValidation_Str(Object jsonResponse, String key)
	{
		//ResponseBody<?> body = jsonResponse.getBody();
		if(jsonResponse!=null)
		{
			String bodyStringValue = jsonResponse.toString();
			try {
				Assert.assertTrue(bodyStringValue.contains(key));
				
				Log.pass("String Parameter is Present= "+key+"  .inside response body.");
			} catch (AssertionError e) {
				e.printStackTrace();
				Log.fail("String Expected Parameter is NOT Present= "+key+" .inside response body.");
				//Log.fail("validated Not FOUND are : " +key+ "=" +value );
			}	
		}else{
			Log.warn("Response null found!");
		}
	}
	
	/*public static void responseKcount_Str(List<String> jsonResponse, String key)
	{
		int total = 0;
		String[] here2=null;
		String here1=null;
		//JSONObject array = new JSONObject(jsonResponse .getBody().asString());
		//for(String i=0;i<array.length();i++)
		//ResponseBody<?> body = jsonResponse.getBody();
		//body = jsonResponse.getBody();
		//String bodyStringValue = jsonResponse.toString();
		//List<String>  key1 =bodyStringValue.substring(key);

		List<String> category = ((Validatable<ValidatableResponse, Response>) jsonResponse).then().extract().path("bookList.book."+key+"");
		//boolean count = (jsonResponse.contains(key));
		Object key1 =  ((Validatable<ValidatableResponse, Response>) jsonResponse).then().extract().path(""+key+"");
		
		
		Log.info("key1="+key+" :"+((List<String>) key1).size());

		Log.pass("TOTAL COUNT Present are = " +((List<String>) key1).size());

	}*/
}
