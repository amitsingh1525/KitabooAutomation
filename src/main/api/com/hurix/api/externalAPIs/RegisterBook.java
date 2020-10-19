package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import com.hurix.automation.utility.Log;

public class RegisterBook {
	public static Response registerBook(String formate,String subject,String kitabooId,String clientBookId,String isbn,String consumerKey, String consumerSecret)
	{
		String registerBookBODY = "{\"kitabooId\":\""+kitabooId+"\",\"clientBookId\":\""+clientBookId+"\","
				+ "\"isbn\":\""+isbn+"\",\"partNo\":\"1\",\"schoolYear\":\"5\",\"subject\":\""+subject+"\","
				+ "\"title\":\"Native_book_RESTAPI\",\"author\":\".\",\"pages\":\"6\",\"thumbnail\":{\"base64\":\"22\",\"mimeType\":"
				+ "\"image/jpeg\"},\"version\":\"2.0\",\"formats\":\""+formate+"\",\"dictionaryId\":\"lingua-portuguesa\","
				+ "\"resources\":\"\",\"units\":[],\"current_page_map\":[{\"pageNum\":\"1\",\"folio\":\"1\"},"
				+ "{\"pageNum\":\"2\",\"folio\":\"2\"},{\"pageNum\":\"3\",\"folio\":\"3\"},{\"pageNum\":\"4\",\"folio\":\"4\"},"
				+ "{\"pageNum\":\"5\",\"folio\":\"5\"}],\"folio_remap\":[]}";
		Response jsonResponse = null;
		try {
			Log.startTestCase("RegisterBook");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(registerBookBODY)
					.post("/DistributionServices/ext/api/registerBook");			
			
			Log.info("RegisterBook Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			//exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response registerBook_char(String formate,String subject,String kitabooId,String clientBookId,String isbn,String consumerKey, String consumerSecret)
	{
		String registerBookBODY = "{\"kitabooId\":\""+kitabooId+"\",\"clientBookId\":\""+clientBookId+"\","
				+ "\"isbn\":\""+isbn+"\",\"partNo\":\"1\",\"schoolYear\":\"5\",\"subject\":\""+subject+"\","
				+ "\"title\":\"Native_book_RESTAPI\",\"author\":\".\",\"pages\":\"6\",\"thumbnail\":{\"base64\":\"22\",\"mimeType\":"
				+ "\"image/jpeg\"},\"version\":\"2.0\",\"formats\":\""+formate+"\",\"dictionaryId\":\"lingua-portuguesa\","
				+ "\"resources\":\"\",\"units\":[],\"current_page_map\":[{\"pageNum\":\"1\",\"folio\":\"a\"},{\"pageNum\":\"2\","
				+ "\"folio\":\"b\"},{\"pageNum\":\"3\",\"folio\":\"c\"},{\"pageNum\":\"4\",\"folio\":\"d\"},{\"pageNum\":\"5\","
				+ "\"folio\":\"e\"}],\"folio_remap\":[]}";
		Response jsonResponse = null;
		try {
			Log.startTestCase("RegisterBook");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(registerBookBODY)
					.post("/DistributionServices/ext/api/registerBook");			
			
			Log.info("RegisterBook Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			//exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response registerBook_charAA(String formate,String subject,String kitabooId,String clientBookId,String isbn,String consumerKey, String consumerSecret)
	{
		String registerBookBODY = "{\"kitabooId\":\""+kitabooId+"\",\"clientBookId\":\""+clientBookId+"\","
				+ "\"isbn\":\""+isbn+"\",\"partNo\":\"1\",\"schoolYear\":\"5\",\"subject\":\""+subject+"\","
				+ "\"title\":\"Native_book_RESTAPI\",\"author\":\".\",\"pages\":\"6\",\"thumbnail\":{\"base64\":\"22\",\"mimeType\":"
				+ "\"image/jpeg\"},\"version\":\"2.0\",\"formats\":\""+formate+"\",\"dictionaryId\":\"lingua-portuguesa\","
				+ "\"resources\":\"\",\"units\":[],\"current_page_map\":[{\"pageNum\":\"1\",\"folio\":\"AA\"},"
				+ "{\"pageNum\":\"2\",\"folio\":\"BB\"},{\"pageNum\":\"3\",\"folio\":\"CC\"},{\"pageNum\":\"4\",\"folio\":"
				+ "\"DD\"},{\"pageNum\":\"5\",\"folio\":\"EE\"}],\"folio_remap\":[]}";
		Response jsonResponse = null;
		try {
			Log.startTestCase("RegisterBook");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(registerBookBODY)
					.post("/DistributionServices/ext/api/registerBook");			
			
			Log.info("RegisterBook Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			//exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	
	public static Response registerBook_Roman(String formate,String subject,String kitabooId,String clientBookId,String isbn,String consumerKey, String consumerSecret)
	{
		String registerBookBODY = "{\"kitabooId\":\""+kitabooId+"\",\"clientBookId\":\""+clientBookId+"\","
				+ "\"isbn\":\""+isbn+"\",\"partNo\":\"1\",\"schoolYear\":\"5\",\"subject\":\""+subject+"\","
				+ "\"title\":\"Native_book_RESTAPI\",\"author\":\".\",\"pages\":\"6\",\"thumbnail\":{\"base64\":\"22\",\"mimeType\":"
				+ "\"image/jpeg\"},\"version\":\"2.0\",\"formats\":\""+formate+"\",\"dictionaryId\":\"lingua-portuguesa\","
				+ "\"resources\":\"\",\"units\":[],\"current_page_map\":[{\"pageNum\":\"1\",\"folio\":\"i\"},"
				+ "{\"pageNum\":\"2\",\"folio\":\"ii\"},{\"pageNum\":\"3\",\"folio\":\"iii\"},{\"pageNum\":\"4\",\"folio\":"
				+ "\"iv\"},{\"pageNum\":\"5\",\"folio\":\"v\"}],\"folio_remap\":[]}";
		Response jsonResponse = null;
		try {
			Log.startTestCase("RegisterBook");
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(registerBookBODY)
					.post("/DistributionServices/ext/api/registerBook");			
			
			Log.info("RegisterBook Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			//exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}	
}
