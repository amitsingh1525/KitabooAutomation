package com.hurix.api.runner;

import io.restassured.response.Response;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import com.hurix.api.externalAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class DIS_2136 {

	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	
	public static long startDate ;//EpochTime.getEpochTime("2019/10/31 14:46:04");
	public static long startIndex = 0;
	public static long endIndex = 100;
	//public static int level;
	public static String assetType;
	public static int level;
	public static String numberOfBooks;
	public static String userToken = "";
	public static int BookID_mark1;
	public static int BookID_mark2;
	public static int BookID_mark3;
	public static int  bookID1;
	public static int bookID2;
	public static int bookID3;
	public static int bookID6;
	public static String isbn;
	public static String isbnMeta;
	public static String isbnIng;
	public static String forName;
	public static String responseMsg;
	public static String content_ownership;
	public static int userID;
	public static int client_Id;
	public static int totalbooks;
	public static int total;
	public static String archiveDate;
	public static String archiveDate1;
	public static String archiveDate2;
	public static String operation0;
	public static String operation1;
	public static String ebookID1;
	public static String catname;
	public static String categoriesname;
	public static String collectionName0;
	public static String collectionName1;
	public static String catname1;
	public static int totalCategories;
	public static String clientUserID;
	public static String category1;
	public static String clientBookID;
	public static String search = "Native";	
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static String environMent;
	public static String userName;
	public static String password;
	public static String detail;
	public static String sqlhost;
	public static String sqlUsername;
	public static String sqlPassword;
	public static String externalURI;
	public static String clientID;
	public static String catlevel;
	public static String  deviceT;
	public static int type;
	public static String isbn1;
	public static String searchV2TEXT;
	public static String isbn3;
	public static String isbn4;
	public static String isbn5;
	public static String isbn6;
	public static String isbn7;
	public static String isbn8;
	public static String isbn9;
	public static String isbn10;
	public static String isbn11;
	public static String  email;
	public static String  firstName;
	public static String  lastName;
	public static String consumerKey;
	public static String consumerSecret;
	public static Object categoryIdList0;
	public static Object categoryIdList1;
	public static Object categoryIdList2;
	public static Object categoryIdList3;
	public static Object categoryIdList4;
	public static String category;
	public static Object categoryList;
	public static Object categoryIdList;
	public static String title;
	public static String runY_N;
	//public static String consumerKey=ExcelUtils.Consumer_key;
	//public static String consumerSecret=ExcelUtils.secret_key;

	public static void   main(String []args) throws SQLException, JSONException{
		Log.initialization("Sprint33.1/DIS-2136");	
		//List<String> detail =  ExcelUtils.getuserDetails();
		try {
			String finalEmailID = "";
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			String excelPath="./testData/Sprint33.1/DIS-2136.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{DataFormatter formatter = new DataFormatter();
			environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			userName = formatter.formatCellValue(sheet.getRow(i).getCell(1));			
			password = formatter.formatCellValue(sheet.getRow(i).getCell(2));
			catlevel = formatter.formatCellValue(sheet.getRow(i).getCell(3));
			deviceT = formatter.formatCellValue(sheet.getRow(i).getCell(4));				
			runY_N = formatter.formatCellValue(sheet.getRow(i).getCell(5));
			String email1 = formatter.formatCellValue(sheet.getRow(i).getCell(6));
			String email2 = formatter.formatCellValue(sheet.getRow(i).getCell(7));

			//String[] deviceT = {"IPAD","ANDROID","WINDOWS","PC","HTML5"};
			Log.info("runY_N : "+runY_N);
			if(runY_N.equals("NO")){Log.info("Permission to Run that Row is Denied!!..Please change YES in Ith row in Respective Sheet of Yours, Thank You");}
			else if(runY_N.equals("YES"))
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

			//clientID = JDBC_category.getReader_clientID(clientID, sqlhost, sqlUsername, sqlPassword);		
			clientID =JDBC_category.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			Log.info("ReaderKey : "+clientID);


			//for(int i4=0; i4<=4; i4++)
			//{
			Log.startTestCase("Authenticate.device: "+deviceT);
			Log.info("detail : "+detail);
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			Log.info("clientID : "+clientID);
			Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"514185",deviceT);
			Log.info("Authenticate Response: "+authenticateValue.then().extract().response().prettyPrint());				
			System.out.println("HERE_Before");
			Log.info("clientID : "+clientID);
			Validation.responseHeaderCodeValidation(authenticateValue, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(authenticateValue, HttpStatus.SC_OK);
			Validation.responseTimeValidation(authenticateValue);
			Validation.responseKeyValidation_key(authenticateValue, "userName");
			Validation.responseKeyValidation_key(authenticateValue, userName);
			System.out.println("HERE_After");
			System.out.println("here");
			userName = authenticateValue.then().extract().path("user.userName");
			userID = authenticateValue.then().extract().path("user.id");
			System.out.println("userID: "+userID);
			userToken = authenticateValue.then().extract().path("userToken");
			System.out.println("userToken:"+userToken);
			clientUserID = authenticateValue.then().extract().path("user.clientUserID");
			System.out.println("clientUserID:"+clientUserID);
			client_Id = authenticateValue.then().extract().path("user.clientID");
			System.out.println("client_Id:"+client_Id);
			Log.endTestCase("End");


			//String ck= JDBC_category.getCKSK(client_Id, sqlhost, sqlUsername, sqlPassword);
			//Log.info("ck : "+ck);
			//Log.info("Sk : "+ck[1]);
			consumerKey = JDBC_category.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			consumerSecret =JDBC_category.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);


			for(int i2=1; i2<=5; i2++)
			{
				String email= formatter.formatCellValue(sheet.getRow(i2).getCell(6));
				finalEmailID = finalEmailID+"\""+email+"\""+",";
			}			
			Log.info("finalEmailID : "+finalEmailID);
			//finalEmailID = "\""Stag1Samar.Rylee_Allen+5498@mafthy.acadmey"\";
			finalEmailID = "\""+email1+"\",\""+email2+"\"";
			Log.info("email1 : "+email1);
			Log.info("email2 : "+email2);
			Response bulkusersRegistration = BulkUsersRegistration.bulkUsersRegistration(consumerKey, consumerSecret, finalEmailID);
			Log.info("detail : "+detail);
			Validation.responseHeaderCodeValidation(bulkusersRegistration, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(bulkusersRegistration, HttpStatus.SC_OK);
			Validation.responseTimeValidation(bulkusersRegistration);
			Validation.responseKeyAndValue(bulkusersRegistration, "responseMsg", "OK");
			//Validation.responseKeyValidation_key(registerUSER, "id");
			
			
			}
			
			}
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
	}
}
