package com.hurix.api.runner;

import io.restassured.response.Response;

import java.util.*;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class DIS_1604 {

	public static List<String> detailisbn =  ExcelUtils.getisbn();
	public static String consumerKey;
	public static String userToken;
	public static String consumerSecret;
	public static String excelPath;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static String environMent;
	public static String userName;
	public static String password;
	public static String detail;
	public static String clientID;
	public static String catlevel;
	public static String title;
	public static long nowEpochTime;
	public static String Title;
	public static int epubId;
	//public static String userID;
	public static String isbnMeta;
	public static String isbnIng;
	public static String sqlhost;
	public static String sqlUsername;
	public static String sqlPassword;
	public static String client_Id;
	public static String firstName;
	public static String lastName;
	public static String deviceT;
	public static String refID;
	public static String client_T;
	public static String clientBookID;
	public static String collectionRefID;
	public static String libraryRefID;
	public static String userName1;
	public static String email = null ;
	public static int bookID1;
	public static int userID1=0;
	//public static String deviceLimit;
	//public static String type;


	public static void main(String[] args) {
		//Log.initialization("APITesting");//DIS-1979	
		Log.initialization("Sprint33.1/DIS-1604");
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/Sprint33.1/DIS-1604.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{DataFormatter formatter = new DataFormatter();
			environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			userName=formatter.formatCellValue(sheet.getRow(i).getCell(1));			
			password=formatter.formatCellValue(sheet.getRow(i).getCell(2));
			String runY_N = formatter.formatCellValue(sheet.getRow(i).getCell(3));
			deviceT =  formatter.formatCellValue(sheet.getRow(i).getCell(4));
			userToken =  formatter.formatCellValue(sheet.getRow(i).getCell(5));


			Log.info("runY_N : "+runY_N);
			if(runY_N.contains("NO")){Log.info("Permission to Run that Row is Denied!!..Please change YES in Ith row in Respective Sheet of Yours, Thank You");}
			else if(runY_N.contains("YES"))
			{switch(environMent){
			case "QC":
				detail = "http://qc.kitaboo.com";
				sqlhost = "jdbc:mysql://172.18.10.147:3306";
				sqlUsername = "readonly";
				sqlPassword = "readonly@123";
				break;
			case "Staging":
				detail = "http://qacloud.kitaboo.com";
				sqlhost="jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com";
				sqlUsername="qcteam";
				sqlPassword="JB88F-WT2Q3-DPXTT";	
				break;
			case "BASE_US":
				detail = "http://localhost:12346";
				sqlhost="jdbc:mysql://localhost:12345";
				sqlUsername="shweta-katare";
				sqlPassword="J&P@O4A7HV";	

				break;
			case "BASE_EU":
				detail = "http://localhost:12347";
				sqlhost="jdbc:mysql://localhost:56789";
				sqlUsername="shweta-katare";
				sqlPassword="J&P@O4A7HV";
				break;
			case "PROD_US":
				detail = "http://cloud.kitaboo.com";
				sqlhost="jdbc:mysql://localhost:12345";
				sqlUsername="shweta-katare";
				sqlPassword="J&P@O4A7HV";
				break;
			case "PROD_EU":
				detail = "http://cloud.kitaboo.eu";
				sqlhost="jdbc:mysql://localhost:56789";
				sqlUsername="shweta-katare";
				sqlPassword="J&P@O4A7HV";
				break;
			}				
			io.restassured.RestAssured.baseURI = detail;	

			//clientID =JDBC_category.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			String readerKey = JDBC_Queries.getReader_clientID(1219, sqlhost, sqlUsername, sqlPassword);
			//int client_Id = Integer.parseInt(""+client_Id+"");					
			//String userName1=null;			

			/*Log.startTestCase("Authenticate");
			Log.info("TotalRows : "+sheet.getLastRowNum());
			Log.info("detail : "+detail);
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			Log.info("clientID : "+clientID);
			Log.info("deviceT : "+deviceT);
			Response authenticateValue = Authenticate.authenticate(readerKey, userName, password, "514185",deviceT);
			Log.info("Authenticate Response: "+authenticateValue.then().extract().response().prettyPrint());				
			System.out.println("HERE_Before");
			Log.info("clientID : "+clientID);
			Validation.responseHeaderCodeValidation(authenticateValue, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(authenticateValue, HttpStatus.SC_OK);
			Validation.responseTimeValidation(authenticateValue);
			userToken = authenticateValue.then().extract().path("userToken");
			Log.info("userToken:"+userToken);

			int client_Id = authenticateValue.then().extract().path("user.clientID");
			Log.info("client_Id:"+client_Id);
			userID1 = authenticateValue.then().extract().path("user.id");
			Log.info("userID1:"+userID1);
			firstName = authenticateValue.then().extract().path("user.firstName");
			Log.info("firstName:"+firstName);
			lastName = authenticateValue.then().extract().path("user.lastName");
			Log.info("lastName:"+lastName);
			Log.info("HEWEWEWEWE");
			userName1 = authenticateValue.then().extract().path("user.userName");
			Log.info("userName1:"+""+userName1+"");
			email = authenticateValue.then().extract().path("user.email");
			Log.info("email:"+email);

			consumerKey = JDBC_category.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			consumerSecret =JDBC_category.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);*/

			//TC-738
			Response ValidateUsertoken = ValidateUserToken.validateUserToken(userToken,"gsahd74834", deviceT);
			Validation.responseHeaderCodeValidation(ValidateUsertoken, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(ValidateUsertoken, HttpStatus.SC_OK);
			Validation.responseTimeValidation(ValidateUsertoken);
			userToken = ValidateUsertoken.then().extract().path("userToken");
			Log.info("userToken : "+userToken);
			int userID = ValidateUsertoken.then().extract().path("user.userID");
			Log.info("userID : "+userID);
			int clientID = ValidateUsertoken.then().extract().path("clientID");
			Log.info("userID : "+userID);

			readerKey = JDBC_Queries.getReader_clientID(clientID, sqlhost, sqlUsername, sqlPassword);

			//TC-742
			Log.startTestCase("ACEP_logout");
			Response ACEP_Logout = ACEP_logout.aCEP_logout(userToken, readerKey, "dsd34343", deviceT);
			Validation.responseHeaderCodeValidation(ACEP_Logout, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(ACEP_Logout, HttpStatus.SC_OK);
			Validation.responseTimeValidation(ACEP_Logout);
			Validation.responseKeyValidation_key(ACEP_Logout, "logoutURL");
			String logoutURL= ACEP_Logout .then().extract().path("logoutURL");
			Log.info("logoutURL : "+logoutURL);


			ACEP_HIT_logoutURL.aCEP_HIT_logoutURL(logoutURL);
			
			
			BrowserConfigure.browserConfigure("Chrome");
			Driver.driver.navigate().to(""+logoutURL+"");
			UIElements.threadHold_5Sec();
			String currentURL = Driver.driver.getCurrentUrl();
			if(currentURL.contains("https://openbook.acep.org/"))
			{
				Log.pass("URL prod directed SuccessFulyy..!!!");
			}
			else
			{
				Log.fail("URL Prod ***NOT*** directed SuccessFulyy..!!!");
			}
			Driver.driver.quit();

			//TC-743
			for(int i1=1; i1<=5; i1++)					
			{Log.startTestCase("ACEP_logout_"+i1+"");
			Response ACEP_Logout1= ACEP_logout.aCEP_logout(userToken, readerKey, "dsd34343", deviceT);
			Validation.responseHeaderCodeValidation(ACEP_Logout1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(ACEP_Logout1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(ACEP_Logout1);
			Validation.responseKeyValidation_key(ACEP_Logout1, "logoutURL");
			logoutURL= ACEP_Logout1 .then().extract().path("logoutURL");
			Log.info("logoutURL : "+logoutURL);}

			//TC-744
			Log.startTestCase("ACEP_logout_Invalid UserToken");
			Response ACEP_Logout1= ACEP_logout.aCEP_logout(userToken+"123", readerKey, "dsd34343", deviceT);
			Validation.responseHeaderCodeValidation(ACEP_Logout1, HttpStatus.SC_BAD_REQUEST);
			Validation.responseCodeValidation1(ACEP_Logout1, HttpStatus.SC_SWITCHING_PROTOCOLS);
			Validation.responseTimeValidation(ACEP_Logout1);
			Validation.responseKeyValidation_key(ACEP_Logout1, "Invalid usertoken");
			Validation.responseKeyValidation_key(ACEP_Logout1, "null");
			}
			}
		}catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
	}	
}