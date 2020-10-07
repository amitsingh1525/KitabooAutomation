package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class UpDateEpub {

	public static Response upDateEpub_OAuth(String consumerKey, String consumerSecret,String filePath,String title,String author,String category,String isbn, String description,String epubId)
	{		
		String uploadEpubBody="{\"title\":\""+title+"\",\"author\":\""+author+"\",\"category\":\""+category+"\",\"isbn\":\""+isbn+"\",\"description\":\""+description+"\",\"ftp\":{\"userName\":\"hurix_data\",\"password\":\"il1Nohve\",\"host\":\"ftpseepz.hurix.com\",\"filePath\":\""+filePath+"\"}}";

		Response jsonResponse = null;
		try {
			Log.startTestCase("UpDateEpub_OAuth");
			Log.info("title : "+title);
			Log.info("author : "+author);
			Log.info("category : "+category);
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(uploadEpubBody)
					.put("/DistributionServices/ext/api/"+epubId+"/updateEpub");
			/*Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);
			Validation.responseKeyValidation_key(jsonResponse, "Epub updated successfully.");*/

			Log.info("UpDateEpub_OAuth Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}	
}
