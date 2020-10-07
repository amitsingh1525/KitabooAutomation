package apiSanity;

import com.hurix.api.readerAPIs.Authenticate;
import com.hurix.api.validation.ResponseCodeValidation;
import com.hurix.api.validation.ResponseValueValidation;
import com.hurix.automation.utility.ExcelFile;
import com.hurix.automation.utility.Log;

import io.restassured.response.Response;

public class Authenticate_Demo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Log.initialization("AuthenticateAkib");
		Log.startTestCase("Excel Initialization");
		ExcelFile.setExcelFile("D:\\eclipse-workspace\\KitabooAutomation\\KitabooAutomation\\testData\\userConfig1.xlsx", "auth_Val");
		int lastRow = ExcelFile.getRowCount();
		Log.info("Excel Row count is: "+lastRow);
		Log.endTestCase("END");
		for(int i=1; i<=lastRow; i++) {
			
			io.restassured.RestAssured.baseURI = "https://cloud.kitaboo.com";
			Log.startTestCase("User_"+i);
			String emailID = ExcelFile.getCellData(i, 0);
			String password = ExcelFile.getCellData(i, 1);
			Log.info("Credential: Username: '"+emailID+"' Password: '"+password+"'");
			Response authResponse = Authenticate.authenticateV1("ProdUS", emailID, password, "123", "PC");
			ResponseCodeValidation.responseCodeValidation(authResponse.getStatusCode(), 200);
			ResponseValueValidation.responseStringValidation(authResponse, "user.email", emailID);
			Log.info(authResponse.then().extract().response().prettyPrint());
			ResponseValueValidation.responseKeyValidation(authResponse, "userToken");
			Log.endTestCase("END");
			
			
		}
	}

}
