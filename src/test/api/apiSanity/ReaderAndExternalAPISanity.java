package apiSanity;

import java.util.List;
import java.util.Scanner;

import org.hamcrest.core.Is;

import com.hurix.api.readerAPIs.Authenticate;
import com.hurix.api.readerAPIs.FetchBookList;
import com.hurix.api.validation.ResponseCodeValidation;
import com.hurix.api.validation.ResponseValueValidation;
import com.hurix.automation.utility.Log;

import io.restassured.response.Response;

public class ReaderAndExternalAPISanity {

	static String[] deviceType = {"IPAD","PC", "HTML"};
	public static void main(String[] args) {

		Log.initialization("APIDetailsLog");
		
		io.restassured.RestAssured.baseURI = "https://cloud.kitaboo.com";
		
		Log.startTestCase("Authenticate");
		Response authResponse = Authenticate.authenticateV1("ProdUS", "enterpriseproduction21jan@gmail.com", "kitaboo!123", "123", deviceType[1]);
		ResponseCodeValidation.responseCodeValidation(authResponse.getStatusCode(), 200);
		ResponseValueValidation.responseStringValidation(authResponse, "user.email,user.userID,user.clientID", "enterpriseproduction21jan@gmail.com,1567,2195");
		ResponseValueValidation.responseValueValidation(authResponse, "user.clientID", 2195);
		Log.info(authResponse.then().extract().response().prettyPrint());
		String userToken = authResponse.then().extract().path("userToken");
		Log.endTestCase("END");
		
		
		Log.startTestCase("FetchBookList");
		
		Response fetchBookListRes = FetchBookList.fetchBookList(userToken, "123", deviceType[1]);
		Log.info(fetchBookListRes.then().extract().response().prettyPrint());
		List<Long> idList = fetchBookListRes.jsonPath().getList("bookList.book.id");
		ResponseValueValidation.responseStringValidation(authResponse, "bookList[0].book.id", "enterpriseproduction21jan@gmail.com,1567,2195");
		ResponseCodeValidation.responseCodeValidation(authResponse.getStatusCode(), 200);
		Log.endTestCase("END");
	}

}
