package com.hurix.api.runner;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.hurix.api.externalAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class DIS_2010 {
	public static String consumerKey;
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
	public static String isbnMeta;
	public static String isbnIng;
	public static String sqlhost;
	public static String sqlUsername;
	public static String sqlPassword;

	public static void main(String[] args) throws Exception {
		//Log.initialization("APITesting");//DIS-1979	
		Log.initialization("Sprint34/DIS-2010");
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/Sprint34/DIS-2010.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{DataFormatter formatter = new DataFormatter();
			environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			userName = formatter.formatCellValue(sheet.getRow(i).getCell(1));			
			password = formatter.formatCellValue(sheet.getRow(i).getCell(2));				
			catlevel = formatter.formatCellValue(sheet.getRow(i).getCell(3));
			String deviceT = formatter.formatCellValue(sheet.getRow(i).getCell(4));
			String runY_N = formatter.formatCellValue(sheet.getRow(i).getCell(5));
			//clientID = formatter.formatCellValue(sheet.getRow(i).getCell(3));

			Log.info("runY_N : "+runY_N);
			if(runY_N.contains("NO"))
			{Log.info("runY_N : "+runY_N);Log.info("Permission to Run that Row is Denied!!..Please change YES in Ith row in Respective Sheet of Yours, Thank You");}
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

			clientID = JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			
			
			Log.info("detail : "+detail);
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			Log.info("ReaderKey : "+clientID);
			
			Log.startTestCase("Authenticate."+deviceT+"");
			Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"514185",deviceT);
			Log.info("Authenticate Response: "+authenticateValue.then().extract().response().prettyPrint());				
			Log.info("HERE_Before");
			Log.info("clientID : "+clientID);
			Validation.responseHeaderCodeValidation(authenticateValue, HttpStatus.SC_OK);
			//Validation.responseCodeValidation1(authenticateValue, HttpStatus.SC_OK);
			Validation.responseTimeValidation(authenticateValue);
			Validation.responseKeyValidation_key(authenticateValue, "userName");
			Validation.responseKeyValidation_key(authenticateValue, userName);			
			Log.info("HERE_After");
			userName = authenticateValue.then().extract().path("user.userName");
			Validation.responseKeyAndValue(authenticateValue, "userName", userName);
			int userID = authenticateValue.then().extract().path("user.id");
			Log.info("userID: "+userID);
			String userToken = authenticateValue.then().extract().path("userToken");
			Log.info("userToken:"+userToken);
			String clientUserID = authenticateValue.then().extract().path("user.clientUserID");
			Log.info("clientUserID:"+clientUserID);
			int client_Id = authenticateValue.then().extract().path("user.clientID");
			Log.info("client_Id:"+client_Id);
			Log.endTestCase("End");

			consumerKey = JDBC_Queries.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			consumerSecret =JDBC_Queries.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);
			
			Response userAssignedBooks_res = UserAssignedBooks_OAuth.userAssignedBooks_OAuth(consumerKey, consumerSecret,clientUserID);
			Validation.responseHeaderCodeValidation(userAssignedBooks_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(userAssignedBooks_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(userAssignedBooks_res);
			Validation.responseKeyValidation_key(userAssignedBooks_res, "assignedOn");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "author");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "bookActive");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "category");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "categoryIdList");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "categoryList");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "description");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "id");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "isbn");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "keywords");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "language");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "pages");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "refId");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "version");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "expiryDate");
			Validation.responseKeyAndValue(userAssignedBooks_res, "expiryDate", "PERPETUAL");

			Response userAssignedBooks_withPagi_Res = UserAssignedBooks_OAuth.userAssignedBooks_OAuth_with_pagi(0, 80, consumerKey, consumerSecret,clientUserID);
			Validation.responseHeaderCodeValidation(userAssignedBooks_withPagi_Res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(userAssignedBooks_withPagi_Res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(userAssignedBooks_withPagi_Res);
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "assignedOn");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "author");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "bookActive");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "category");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "categoryIdList");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "categoryList");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "description");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "id");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "isbn");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "keywords");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "language");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "pages");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "refId");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "version");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "totalbooks");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "expiryDate");
			int totalbooks=userAssignedBooks_withPagi_Res.then().extract().path("totalbooks");
			Log.info("totalbooks: "+totalbooks);
			Validation.responseISGreater("totalbooks", totalbooks, 3);
			Validation.responseKeyAndValue(userAssignedBooks_withPagi_Res, "expiryDate", "PERPETUAL");

			}
			}
		}catch (AssertionError exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
	}

}
