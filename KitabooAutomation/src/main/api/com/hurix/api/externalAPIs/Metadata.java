package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class Metadata {
	private static String MetadataBody;
	//private static String MetadataBody = "{\"title\":\"test-41\",\"author\":\"cat4\",\"isbn\":\"2237564746574\",\"coverImage\":\"https://qathumbnails.kitaboo.com/distribution/thumbnails/books/18/18/31000.png\",\"metadata\":{\"keyOfMedataStr\":\"value for the metadata\",\"publisher\":\"cat4\"},\"categories\":[{\"category\":\"india\",\"child\":[{\"category\":\"india\",\"child\":[]},{\"category\":\"goa\",\"child\":[]},{\"category\":\"goa\",\"child\":[]},{\"category\":\"Punjab\",\"child\":[]}]},{\"category\":\"maharashtra\",\"child\":[{\"category\":\"bihar\",\"child\":[]},{\"category\":\"nagpur\",\"child\":[]},{\"category\":\"panjab\",\"child\":[]},{\"category\":\"Rajasthan\",\"child\":[]}]}]}";
	public static Response metadata(String consumerKey, String consumerSecret,String string)
	{
		MetadataBody = "{\"title\":\"test-41\",\"author\":\"cat4\",\"isbn\":\""+string+"\",\"coverImage\":\"https://qathumbnails.kitaboo.com/distribution/thumbnails/books/18/18/31000.png\",\"metadata\":{\"keyOfMedataStr\":\"value for the metadata\",\"publisher\":\"cat4\"},\"categories\":[{\"category\":\"india\",\"child\":[{\"category\":\"india\",\"child\":[]},{\"category\":\"goa\",\"child\":[]},{\"category\":\"goa\",\"child\":[]}]},{\"category\":\"maharashtra\",\"child\":[{\"category\":\"bihar\",\"child\":[]},{\"category\":\"nagpur\",\"child\":[]},{\"category\":\"panjab\",\"child\":[]}]}]}";
		
		Response jsonResponse = null;
		try {
			Log.startTestCase("Metadata");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(MetadataBody)
					.post("/DistributionServices/ext/api/book/metadata");
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);
			
			Log.info("Metadata Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
		
	}

}
