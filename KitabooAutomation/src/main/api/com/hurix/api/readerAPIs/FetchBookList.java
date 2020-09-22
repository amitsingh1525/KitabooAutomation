package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;


public class FetchBookList {

	public static Response fetchBookList_without_pagination(String userToken,String deviceID,String deviceType)
	{
		Response jsonResponse = null;
		try {
			Log.startTestCase("fetchBookList_without_pagination.Device:"+deviceType+"");
			//System.out.println("GETfetchBookList RequestURL:" +fetchBookListPath);
			jsonResponse = given()
					.header("usertoken",userToken)
					.get("/DistributionServices/services/api/reader/distribution/"+deviceID+"/"+deviceType+"/fetchBookList");
			/*Validation.responseCodeValidation1(jsonResponse, 200);
			Validation.responseHeaderCodeValidation(jsonResponse, 200);
			Validation.responseTimeValidation(jsonResponse);
			//Validation.responseKeyValidation_key(jsonResponse, Title);
			 */
			Log.info("fetchBookList.Device:"+deviceType+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}
	public static Response fetchBookList_with_pagination(int startIndex,int endIndex,String userToken,String DeviceID,String DeviceType)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("fetchBookList_with_pagination");
			//System.out.println("GETfetchBookList RequestURL:" +fetchBookListPath);
			jsonResponse = given()
					.header("usertoken",userToken)
					.header("StartIndex",startIndex)
					.header("endIndex",endIndex)
					.get("/DistributionServices/services/api/reader/distribution/"+DeviceID+"/"+DeviceType+"/fetchBookList");
			/*Validation.responseCodeValidation1(jsonResponse, HttpStatus.SC_OK);
			Validation.responseHeaderCodeValidation(jsonResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(jsonResponse);*/
			Log.info("fetchBookList Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

	public static Response fetchBookList_with_permutation(String SortBy,String orderBy,String userToken,String DeviceID,String DeviceType)
	{

		Response jsonResponse = null;
		try {
			
			//assertTrue(Ordering.natural().isOrdered(list));

			Log.startTestCase("fetchBookList_SortBy="+SortBy+".orderBy="+orderBy+"");
			//System.out.println("GETfetchBookList RequestURL:" +fetchBookListPath);
			jsonResponse = given()
					.header("usertoken",userToken)
					.header("SortBy",SortBy)
					.header("orderBy",orderBy)
					.get("/DistributionServices/services/api/reader/distribution/"+DeviceID+"/"+DeviceType+"/fetchBookList");
			//List<String> jsonResponse1 = jsonResponse.jsonPath().getList(""+SortBy+"");
			//boolean result= 
			//assertTrue(Ordering.natural().isOrdered(jsonResponse1));
			
			
				   // Using the orderBy function from lodash 
				   // Read docs: https://lodash.com/docs/4.17.10#orderBy
				   // var expectedSortedOrder = _.orderBy(responseBody.employees, ["firstname"],["asc"]);

				    //pm.expect(responseBody.employees).to.eql(expectedSortedOrder);    
					Log.info("fetchBookList_SortBy="+SortBy+".orderBy="+orderBy+" Response: "+jsonResponse.then().extract().response().prettyPrint());
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;
	}

	
	public static Response fetchBookList_withPAGI_permutation(String SortBy,String orderBy,int startIndex,int endIndex,String userToken,String DeviceID,String DeviceType)
	{

		Response jsonResponse = null;
		try {

			Log.startTestCase("fetchBookList_SortBy="+SortBy+".orderBy="+orderBy+".start="+startIndex+".end="+endIndex+"");
			//System.out.println("GETfetchBookList RequestURL:" +fetchBookListPath);
			jsonResponse = given()
					.header("usertoken",userToken)
					.header("startIndex",startIndex)
					.header("endIndex",endIndex)
					.header("SortBy",SortBy)
					.header("orderBy",orderBy)
					.get("/DistributionServices/services/api/reader/distribution/"+DeviceID+"/"+DeviceType+"/fetchBookList");
			Log.info("fetchBookList_SortBy="+SortBy+".orderBy="+orderBy+".start="+startIndex+".end="+endIndex+"="+SortBy+".orderBy="+orderBy+" Response: "+jsonResponse.then().extract().response().prettyPrint());
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
