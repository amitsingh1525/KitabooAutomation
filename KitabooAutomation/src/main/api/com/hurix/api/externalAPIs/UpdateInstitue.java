package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.api.utility.EpochTime;
import com.hurix.automation.utility.Log;

public class UpdateInstitue {
	
	public static Response updateInstitue(int instituteId,String consumerKey, String consumerSecret)
	{
		Response jsonResponse = null;
		try {
			String updateInstitueBODY ="{\"name\":\"sales admin"+EpochTime.current()+"\",\"clientInstituteID\":"
					+ "\"test"+EpochTime.current()+"\",\"admin\":{\"firstName\":\"sales\",\"lastName\":\"Admin\",\"email\":"
					+ "\"sales.admin"+EpochTime.current()+"@hurix.com\"}}" ;
			Log.startTestCase("UpdateInstitue");		
		
			Log.info("URL : "+"/DistributionServices/ext/api/institute/"+instituteId+"");
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			Log.info("updateInstitueBODY : "+updateInstitueBODY);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(updateInstitueBODY)
					.put("/DistributionServices/ext/api/institute/"+instituteId+"");
										
			Log.info("UpdateInstitue Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
}

