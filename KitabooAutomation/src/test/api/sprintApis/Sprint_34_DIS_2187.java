package sprintApis;

import java.util.List;

import com.hurix.api.readerAPIs.Authenticate;
import com.hurix.api.readerAPIs.DownloadBook;
import com.hurix.api.readerAPIs.FetchBookList;
import com.hurix.api.utility.RSADecryption;
import com.hurix.api.validation.ResponseCodeValidation;
import com.hurix.api.validation.ResponseValueValidation;
import com.hurix.automation.utility.Credentials;
import com.hurix.automation.utility.Log;

import io.restassured.response.Response;

public class Sprint_34_DIS_2187 {

	static String[] deviceType = {"IPAD","PC", "HTML5"};
	public static void main(String[] args) {

		Log.initialization("APIDetailsLog");

		//done
		List<String> credential = Credentials.getCredentials("restAPI", "Learner");
		io.restassured.RestAssured.baseURI = Credentials.URL;

		System.out.println("URI: "+Credentials.URL);
		System.out.println("Username: "+credential.get(0));
		System.out.println("Password: "+credential.get(1));
		Log.startTestCase("Authenticate");
		Response authResponse = Authenticate.authenticateV1("QC", credential.get(0), credential.get(1), "123", deviceType[1]);
		ResponseCodeValidation.responseCodeValidation(authResponse.getStatusCode(), 200);
		ResponseValueValidation.responseStringValidation(authResponse, "user.email", credential.get(0));
		Log.info(authResponse.then().extract().response().prettyPrint());
		String userToken = authResponse.then().extract().path("userToken");
		Log.endTestCase("END");

		Log.startTestCase("FetchBookList");
		Response fetchBookListRes = FetchBookList.fetchBookList(userToken, "123", deviceType[1]);
		Log.info(fetchBookListRes.then().extract().response().prettyPrint());
		List<Integer> bookIDList = fetchBookListRes.jsonPath().getList("bookList.book.id");
		List<String> ebookIDList = fetchBookListRes.jsonPath().getList("bookList.book.ebookID");
		//ResponseValueValidation.responseStringValidation(authResponse, "bookList[0].book.id", "enterpriseproduction21jan@gmail.com,1567,2195");
		ResponseCodeValidation.responseCodeValidation(authResponse.getStatusCode(), 200);
		Log.endTestCase("END");

		Log.startTestCase("Download Book");
		Response downloadBook = DownloadBook.downloadBookV1(userToken,"123", deviceType[2], bookIDList.get(0), "online");
		Log.info(downloadBook.then().extract().response().prettyPrint());
		String privateKey = downloadBook.then().extract().path("privateKey");
		Log.endTestCase("END");

		Log.startTestCase("fetchPublicKey");
		Response fetchPublicKey = DownloadBook.fetchPublicKey(userToken,"123", deviceType[2], ebookIDList.get(0));
		Log.info(fetchPublicKey.then().extract().response().prettyPrint());
		String publicKey = fetchPublicKey.then().extract().path("publicKey");
		Log.endTestCase("END");

		Log.startTestCase("Encryption/Decryption Part");
		Log.info("Public Key(from fetchPublicKey API): "+publicKey);
		Log.info("private Key(from downloadBook API): "+privateKey);
		String value = "Hey.. What are you doing man?";
		Log.info("String("+value+") which encrypted by public key.");
		String decryptedValue = RSADecryption.RSAEncryptionDecryption(value, publicKey, privateKey);
		if(decryptedValue.equals(value)) {
			Log.pass("Expected and Actual string are matched after decrypted by using private key.");
		}else {
			Log.fail("Expected string: '"+value+"' But Found: '"+decryptedValue+"'");
		}
		Log.endTestCase("END");
	}

}
