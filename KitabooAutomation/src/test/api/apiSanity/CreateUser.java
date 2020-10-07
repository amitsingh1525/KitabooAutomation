package apiSanity;

import java.io.File;

import com.hurix.api.readerAPIs.RegisterUser;
import com.hurix.api.validation.ResponseCodeValidation;
import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.ExcelFile;
import com.hurix.automation.utility.Log;
import com.hurix.reader.bookShelf.BookShelfModule;
import com.hurix.reader.bookShelf.BookShelfStepModule;
import com.hurix.reader.loginPage.LoginModule;

import io.restassured.response.Response;

public class CreateUser {

	public static void main(String[] args) {

		boolean executionOnUI = false;
		try {
			Log.initialization("UserCreation");
			Log.startTestCase("Excel Initialization");
			File file = new File("");
			ExcelFile.setExcelFile(file.getAbsolutePath()+"\\testData\\userConfig.xlsx", "env");
			
			String env = ExcelFile.getCellData(1, 0);
			System.out.println(env);
			Log.endTestCase("END");
			
			ExcelFile.setExcelFile(file.getAbsolutePath()+"\\testData\\userConfig.xlsx", "userDetails");
			int lastRow = ExcelFile.getRowCount();
			for(int i=1; i<=lastRow; i++) {
				
				Log.startTestCase("User_"+i);
				String firstName = ExcelFile.getCellData(i, 0);
				String lastName = ExcelFile.getCellData(i, 1);
				String fullName = firstName+" "+lastName;
				String emailID = ExcelFile.getCellData(i, 2);
				String password = ExcelFile.getCellData(i, 3);
				String accessCode = ExcelFile.getCellData(i, 4);
				
				System.out.println("Full Name: "+fullName);
				System.out.println("emailID: "+emailID);
				System.out.println("Password: "+password);
				Log.info("Full Name: "+fullName);
				Log.info("emailID: "+emailID);
				Log.info("Password: "+password);
				Log.info("Access Code: "+accessCode);
				
				if(executionOnUI) {
					BrowserConfigure.browserConfigure("Chrome");
					if(env.contains("qc")) {
						Driver.driver.navigate().to("https://qc.kitaboo.com/reader/MobileReader/index.html#/login");
					}else if(env.contains("qacloud")) {
						Driver.driver.navigate().to("https://qaread.kitaboo.com/reader/MobileReader/index.html#/login");
					}else if(env.contains("qacloud")) {
						Driver.driver.navigate().to("https://read.kitaboo.com/reader/MobileReader/index.html#/login");
					}else if(env.contains("cloud.kitaboo.com")) {
						Driver.driver.navigate().to("https://read.kitaboo.com/reader/MobileReader/index.html#/login");
					}else if(env.contains("cloud.kitaboo.eu")) {
						Driver.driver.navigate().to("https://read.kitaboo.eu/reader/MobileReader/index.html#/login");
					}
					
					LoginModule.accessCodeSignup(accessCode, fullName, emailID, password, password);
					BookShelfStepModule.btnSkipHelpScreen();
					BookShelfModule.userLogOut();
				}else {
					
					io.restassured.RestAssured.baseURI = env;
					try {
						
						Response regUser = RegisterUser.registerUser("cHJvZi1raXRhYm9v", firstName, lastName, emailID, emailID, password, accessCode);
						String email = regUser.then().extract().path("user.email");
						Log.info("registerUser Response: "+regUser.then().extract().response().prettyPrint());
						if(email.equals(emailID)) {
							Log.pass("User Created!");
						}else {
							Log.fail("ERROR: Expected: "+emailID+" But Found: "+email);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Log.fail("ERROR: "+e.getMessage());
						continue;
					}
				}
				
				Log.endTestCase("END");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
