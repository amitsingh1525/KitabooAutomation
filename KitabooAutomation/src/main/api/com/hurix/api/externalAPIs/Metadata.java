package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class Metadata {
	private static String MetadataBody;
	public static Response metadata(String consumerKey, String consumerSecret,String isbnstring,String title,String author,String cat4,String description)
	{		
		Response jsonResponse = null;
		try {
			if(cat4 .contains("2"))
			{
				//MetadataBody = "{\"title\":\""+title+"\",\"author\":\""+author+"\",\"isbn\":\""+isbnstring+"\",\"coverImage\":\"https://wks.com/thumbnail/1237564746577.png\",\"metadata\":{\"keyOfMedataStr\":\"value for the metadata\",\"publisher\":\"testwk\"},\"categories\":[{\"category\":\"india\",\"child\":[{\"category\":\"india\",\"child\":[]},{\"category\":\"goa\",\"child\":[]},{\"category\":\"goa\",\"child\":[]}]},{\"category\":\"maharashtra\",\"child\":[{\"category\":\"bihar\",\"child\":[]},{\"category\":\"nagpur\",\"child\":[]},{\"category\":\"panjab\",\"child\":[]}]}]}";
				
				MetadataBody ="{\"title\":\""+title+"\",\"author\":\""+author+"\",\"isbn\":\""+isbnstring+"\",\"coverImage\":\"https://wks.com/thumbnail/1237564746577.png\",\"description\":\""+description+"\",\"metadata\":{\"keyOfMedataStr\":\"value for the metadata\",\"publisher\":\"mill and boons\"},\"categories\":[{\"category\":\"india\",\"child\":[{\"category\":\"india\",\"child\":[]},{\"category\":\"goa\",\"child\":[]},{\"category\":\"goa\",\"child\":[]}]},{\"category\":\"maharashtra\",\"child\":[{\"category\":\"bihar\",\"child\":[]},{\"category\":\"nagpur\",\"child\":[]},{\"category\":\"panjab\",\"child\":[]}]}]}";
				Log.startTestCase("Metadata");
				Log.info("MetadataBody : "+MetadataBody);
				jsonResponse = given()
						.auth()
						.oauth(consumerKey, consumerSecret, "", "")
						.header("Content-Type","application/json")
						.body(MetadataBody)
						.post("/DistributionServices/ext/api/book/metadata");

				Log.info("Metadata Response: "+jsonResponse.then().extract().response().prettyPrint());
			}
			else if(cat4 .contains("3"))
			{
				//MetadataBody = "{\"title\":\""+title+"\",\"author\":\""+author+"\",\"isbn\":\""+isbnstring+"\",\"coverImage\":\"https://wks.com/thumbnail/1237564746577.png\",\"metadata\":{\"keyOfMedataStr\":\"value for the metadata\",\"publisher\":\"testwk\"},\"categories\":[{\"category\":\"panjab\",\"child\":[{\"category\":\"amritsar\",\"child\":[{\"category\":\"golden temple\",\"child\":[]}]}]}]}";
				
				MetadataBody = "{\"title\":\""+title+"\",\"author\":\""+author+"\",\"isbn\":\""+isbnstring+"\",\"coverImage\":\"https://wks.com/thumbnail/1237564746577.png\",\"description\":\""+description+"\",\"metadata\":{\"keyOfMedataStr\":\"value for the metadata\",\"publisher\":\"mill and boons\"},\"categories\":[{\"category\":\"panjab\",\"child\":[{\"category\":\"amritsar\",\"child\":[{\"category\":\"golden temple\",\"child\":[]}]}]}]}";
				Log.startTestCase("Metadata");
				jsonResponse = given()
						.auth()
						.oauth(consumerKey, consumerSecret, "", "")
						.header("Content-Type","application/json")
						.body(MetadataBody)
						.post("/DistributionServices/ext/api/book/metadata");

				Log.info("Metadata Response: "+jsonResponse.then().extract().response().prettyPrint());
			}
			else if(cat4 .contains("4"))
			{
				//MetadataBody = "{\"title\":\""+title+"\",\"author\":\""+author+"\",\"isbn\":\""+isbnstring+"\","
				//		+ "\"coverImage\":\"https://qathumbnails.kitaboo.com/distribution/thumbnails/books/18/18/31000.png\","
				//		+ "\"metadata\":{\"keyOfMedataStr\":\"value for the metadata\",\"publisher\":\"cat4\"},\"categories\":[{\"category\":\"india\",\"child\":[{\"category\":\"india\",\"child\":[]},{\"category\":\"goa\",\"child\":[]},{\"category\":\"goa\",\"child\":[]}]},{\"category\":\"maharashtra\",\"child\":[{\"category\":\"bihar\",\"child\":[]},{\"category\":\"nagpur\",\"child\":[]},{\"category\":\"panjab\",\"child\":[]}]}]}";

				
				MetadataBody = "{\"title\":\""+title+"\",\"author\":\""+author+"\",\"isbn\":\""+isbnstring+"\",\"coverImage\":\"https://qathumbnails.kitaboo.com/distribution/thumbnails/books/18/18/31000.png\",\"description\":\""+description+"\",\"metadata\":{\"keyOfMedataStr\":\"value for the metadata\",\"publisher\":\"mill and boons\"},\"categories\":[{\"category\":\"india\",\"child\":[{\"category\":\"panjab\",\"child\":[{\"category\":\"amritsar\",\"child\":[{\"category\":\"golden temple\",\"child\":[]}]}]}]}]}";
				Log.startTestCase("Metadata");
				jsonResponse = given()
						.auth()
						.oauth(consumerKey, consumerSecret, "", "")
						.header("Content-Type","application/json")
						.body(MetadataBody)
						.post("/DistributionServices/ext/api/book/metadata");

				Log.info("Metadata Response: "+jsonResponse.then().extract().response().prettyPrint());
			}
			else if(cat4 .contains("1"))
			{
				//MetadataBody = "{\"title\":\""+title+"\",\"author\":\""+author+"\",\"isbn\":\""+isbnstring+"\",\"coverImage\":\"https://wks.com/thumbnail/1237564746577.png\",\"metadata\":{\"keyOfMedataStr\":\"value for the metadata\",\"publisher\":\"testwk\"},\"categories\":[{\"category\":\"panjab\",\"child\":[]}]}";
				
				MetadataBody = "{\"title\":\""+title+"\",\"author\":\""+author+"\",\"isbn\":\""+isbnstring+"\",\"coverImage\":\"https://wks.com/thumbnail/1237564746577.png\",\"description\":\""+description+"\",\"metadata\":{\"keyOfMedataStr\":\"value for the metadata\",\"publisher\":\"mill and boons\"},\"categories\":[{\"category\":\"panjab\",\"child\":[]}]}";
				Log.startTestCase("Metadata");
				jsonResponse = given()
						.auth()
						.oauth(consumerKey, consumerSecret, "", "")
						.header("Content-Type","application/json")
						.body(MetadataBody)
						.post("/DistributionServices/ext/api/book/metadata");

				Log.info("Metadata Response: "+jsonResponse.then().extract().response().prettyPrint());
			}
		}catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

}
