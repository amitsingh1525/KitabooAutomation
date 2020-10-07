package com.hurix.api.readerAPIs;

import static io.restassured.RestAssured.given;

import com.hurix.automation.utility.Log;

import io.restassured.response.Response;

public class RegisterUser {

	public static Response registerUser(String clientID, String firstName, String lastName, String emailID, String username, String password, String accessCode)
	{
		Response jsonResponse = null;
		try {
			jsonResponse = given()
					.queryParam("clientID", clientID)
					.header("Content-Type","application/json")
					.body("{\"user\":{\"firstName\":\""+firstName+"\",\"lastName\":\""+lastName+"\",\"email\":\""+emailID+"\",\"userName\":\""+username+"\",\"password\":\""+password+"\"},\"accessCode\":\""+accessCode+"\"}")
					.post("DistributionServices/services/api/reader/user/787545445/PC/registerUser");
		} catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
		}
		return jsonResponse;
	}

}
