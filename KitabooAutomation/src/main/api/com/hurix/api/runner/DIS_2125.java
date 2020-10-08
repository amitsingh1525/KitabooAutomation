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

public class DIS_2125 {

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
		Log.initialization("Sprint33.1/DIS-2125");
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/Sprint33.1/DIS-2125.xlsx";
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

			clientID =JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			Log.startTestCase("Authenticate");
			Log.info("detail : "+detail);
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			Log.info("ReaderKey : "+clientID);
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
			Log.info("here");
			userName = authenticateValue.then().extract().path("user.userName");///userName="excel"
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

			Response getclientsUser=null;

			/*getclientsUser = GetClientUsers.getClientUsers(consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(getclientsUser, HttpStatus.SC_OK);
			//Validation.responseCodeValidation1(getclientsUser, HttpStatus.SC_OK);
			Thread.sleep(4000);
			Validation.responseTimeValidation(getclientsUser);
			String userId1 = getclientsUser.then().extract().path("users[1].userid");
			Log.info("userId1 is : "+userId1);
			String userName1 = getclientsUser.then().extract().path("users[1].username");
			Log.info("userName1 is : "+userName1);
			String userId = getclientsUser.then().extract().path("users[0].userid");
			Log.info("userId is : "+userId);
			String userName = getclientsUser.then().extract().path("users[0].username");
			Log.info("username is : "+userName);
			Validation.responseKeyValidation_key(getclientsUser, "userid");
			Validation.responseKeyValidation_key(getclientsUser, "username");
			Validation.responseKeyValidation_key(getclientsUser, "maxDeviceCount");
			Validation.responseKeyValidation_key(getclientsUser, "createDate");
			Validation.responseKeyValidation_key(getclientsUser, "lastAccessDate");
			Validation.responseKeyValidation_key(getclientsUser, "active");
			Validation.responseKeyAndValue(getclientsUser, "userid", ""+userId+"");
			Validation.responseKeyAndValue(getclientsUser, "username", ""+userName+"");
			Validation.responseKeyAndValue(getclientsUser, "userid", ""+userId1+"");
			Validation.responseKeyAndValue(getclientsUser, "username", ""+userName1+"");*/


			getclientsUser = GetClientUsers.getClientUsers_Pagi(0,100,consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(getclientsUser, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getclientsUser, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getclientsUser);
			/*userId1 = getclientsUser.then().extract().path("users[1].userid");
			Log.info("userId1 is : "+userId1);
			userName1 = getclientsUser.then().extract().path("users[1].username");
			Log.info("userName1 is : "+userName1);
			userId = getclientsUser.then().extract().path("users[0].userid");
			Log.info("userId is : "+userId);
			userName = getclientsUser.then().extract().path("users[0].username");
			Log.info("username is : "+userName);*/
			Validation.responseKeyValidation_key(getclientsUser, "userid");
			Validation.responseKeyValidation_key(getclientsUser, "username");
			Validation.responseKeyValidation_key(getclientsUser, "maxDeviceCount");
			Validation.responseKeyValidation_key(getclientsUser, "createDate");
			Validation.responseKeyValidation_key(getclientsUser, "lastAccessDate");
			Validation.responseKeyValidation_key(getclientsUser, "active");
			String userId1 = getclientsUser.then().extract().path("users[1].userid");
			Log.info("userId1 is : "+userId1);
			String userName1 = getclientsUser.then().extract().path("users[1].username");
			Log.info("userName1 is : "+userName1);
			String userId = getclientsUser.then().extract().path("users[0].userid");
			Log.info("userId is : "+userId);
			String userName = getclientsUser.then().extract().path("users[0].username");
			Log.info("username is : "+userName);
			Validation.responseKeyValidation_key(getclientsUser, "userid");
			Validation.responseKeyValidation_key(getclientsUser, "username");
			Validation.responseKeyValidation_key(getclientsUser, "maxDeviceCount");
			Validation.responseKeyValidation_key(getclientsUser, "createDate");
			Validation.responseKeyValidation_key(getclientsUser, "lastAccessDate");
			Validation.responseKeyValidation_key(getclientsUser, "active");
			Validation.responseKeyAndValue(getclientsUser, "userid", ""+userId+"");
			Validation.responseKeyAndValue(getclientsUser, "username", ""+userName+"");
			Validation.responseKeyAndValue(getclientsUser, "userid", ""+userId1+"");
			Validation.responseKeyAndValue(getclientsUser, "username", ""+userName1+"");
			Validation.responseKeyAndValue(getclientsUser, "userid", ""+userId+"");
			Validation.responseKeyAndValue(getclientsUser, "username", ""+userName+"");
			Validation.responseKeyAndValue(getclientsUser, "userid", ""+userId1+"");
			Validation.responseKeyAndValue(getclientsUser, "username", ""+userName1+"");
			

			Response getUsers_0_100 = Getusers.getusers_pagi(0, 100, consumerKey, consumerSecret, clientUserID);
			Validation.responseHeaderCodeValidation(getUsers_0_100, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getUsers_0_100, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getUsers_0_100);
			/*userId1 = getUsers.then().extract().path("users[1].userid");
			Log.info("userId1 is : "+userId1);
			userName1 = getUsers.then().extract().path("users[1].username");
			Log.info("userName1 is : "+userName1);*/
			String userId11 = getUsers_0_100.then().extract().path("users[0].userid");
			Log.info("userId11 is : "+userId11);
			String userName11 = getUsers_0_100.then().extract().path("users[0].username");
			Log.info("username11 is : "+userName11);
			Validation.responseKeyValidation_key(getUsers_0_100, "userid");
			Validation.responseKeyValidation_key(getUsers_0_100, "username");
			Validation.responseKeyValidation_key(getUsers_0_100, "maxDeviceCount");
			Validation.responseKeyValidation_key(getUsers_0_100, "createDate");
			//Validation.responseKeyValidation_key(getUsers, "lastAccessDate");
			Validation.responseKeyValidation_key(getUsers_0_100, "active");
			Validation.responseKeyAndValue(getUsers_0_100, "userid", ""+userId11+"");
			Validation.responseKeyAndValue(getUsers_0_100, "username", ""+userName11+"");	

			Validation.responseKeyAndValue(getUsers_0_100, "userid", ""+userId+"");  //from getclient user Assertion
			Validation.responseKeyAndValue(getUsers_0_100, "username", ""+userName+"");//from getclient user Assertion

			/*else{
			Response getUsers = Getusers.getusers(consumerKey, consumerSecret,clientUserID);
			Validation.responseHeaderCodeValidation(getUsers, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getUsers, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getUsers);
			userId1 = getUsers.then().extract().path("users[1].userid");
			Log.info("userId1 is : "+userId1);
			userName1 = getUsers.then().extract().path("users[1].username");
			Log.info("userName1 is : "+userName1);
			String userId11 = getUsers.then().extract().path("users[0].userid");
			Log.info("userId11 is : "+userId11);
			String userName11 = getUsers.then().extract().path("users[0].username");
			Log.info("username11 is : "+userName11);
			Validation.responseKeyValidation_key(getUsers, "userid");
			Validation.responseKeyValidation_key(getUsers, "username");
			Validation.responseKeyValidation_key(getUsers, "maxDeviceCount");
			Validation.responseKeyValidation_key(getUsers, "createDate");
			//Validation.responseKeyValidation_key(getUsers, "lastAccessDate");
			Validation.responseKeyValidation_key(getUsers, "active");
			Validation.responseKeyAndValue(getUsers, "userid", ""+userId11+"");
			Validation.responseKeyAndValue(getUsers, "username", ""+userName11+"");


			Validation.responseKeyAndValue(getUsers, "userid", ""+userId+"");  //from getclient user Assertion
			Validation.responseKeyAndValue(getUsers, "username", ""+userName+"");*/ //from getclient user Assertion



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
