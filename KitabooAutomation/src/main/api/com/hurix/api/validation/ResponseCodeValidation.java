package com.hurix.api.validation;

import com.hurix.automation.utility.Log;

public class ResponseCodeValidation {

	public static void responseCodeValidation(int responseCode, int statusCode) 
	{
		if(responseCode == statusCode)
		{
			Log.pass("Expected and actual statusCode: " +responseCode+" is matched.");
		}
		else if(responseCode != statusCode)
		{
			Log.fail("Expected HeaderCode: " +statusCode+" But found: "+responseCode+". Both are not matched");
		}
	}
}
