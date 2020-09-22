package com.hurix.api.runner;

import io.restassured.response.Response;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;

import com.hurix.api.externalAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.ExcelUtils;
import com.hurix.api.utility.JDBC_category;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;


public class Upload {

	static List<String> detailisbn =  ExcelUtils.getisbn();
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	//System.out.println(df.format(dateobj));
	// static long startDate1 = EpochTime.getEpochTime("df.format(dateobj");
	static long startDate ;//EpochTime.getEpochTime("2019/10/31 14:46:04");
	static long startIndex = 0;
	static long endIndex = 100;
	// static int level;
	static String assetType;
	static int level;
	static String numberOfBooks;
	static String pinKey;
	static String pinPair;
	static String userToken = "";
	static int BookID_mark1;
	static int BookID_mark2;
	static int BookID_mark3;
	static int  bookID1;
	static int bookID2;
	static int bookID3;
	static int bookID6;
	static int client_Id;
	static String isbn;
	static String isbnMeta;
	static String isbnIng;
	static String forName;
	static String responseMsg;
	static String content_ownership;
	static int userID;
	static int totalbooks;
	static int total;
	static String archiveDate;
	static String archiveDate6;
	static String operation0;
	static String operation1;
	static String ebookID1;
	static String catname;
	static String categoriesname;
	static String collectionName0;
	static String collectionName1;
	static String catname1;
	static int totalCategories;
	static String clientUserID;
	static String category1;
	static String clientBookID;
	static String search = "Native";	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static String environMent;
	static String deviceT;
	static String userName;
	static String password;
	static String detail;
	static String sqlhost;
	static String sqlUsername;
	static String sqlPassword;
	static String externalURI;
	static String clientID;
	static String runY_N;
	static String catlevel;
	static int type;
	static String isbn1;
	static String isbn2;
	static String isbn3;
	static String isbn4;
	static String isbn5;
	static String isbn6;
	static String isbn7;
	static String isbn8;
	static String isbn9;
	static String isbn10;
	static String isbn11;
	static String consumerKey;
	static String consumerSecret;
	static Object categoryIdList0;
	static Object categoryIdList1;
	static Object categoryIdList2;
	static Object categoryIdList3;
	static Object categoryIdList4;
	static String category;
	static Object categoryList;
	static Object categoryIdList;
	static String title;
	//public static String consumerKey=ExcelUtils.Consumer_key;
	//public static String consumerSecret=ExcelUtils.secret_key;

	public static void   main(String []args) throws SQLException, JSONException{
		Log.initialization("UPLOAD");	
		//List<String> detail =  ExcelUtils.getuserDetails();
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			String excelPath="./testData/UPLOAD.xlsx";
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
			String filePath = formatter.formatCellValue(sheet.getRow(i).getCell(6));
			String Title= formatter.formatCellValue(sheet.getRow(i).getCell(7));
			String author= formatter.formatCellValue(sheet.getRow(i).getCell(8));
			String description= formatter.formatCellValue(sheet.getRow(i).getCell(9));
			String category=formatter.formatCellValue(sheet.getRow(i).getCell(10));
			String isbn =formatter.formatCellValue(sheet.getRow(i).getCell(11));
			String clientID =formatter.formatCellValue(sheet.getRow(i).getCell(12));
			String title_prefix=formatter.formatCellValue(sheet.getRow(i).getCell(13));

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

			if(i==1)
			{String reader_key = JDBC_category.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			Log.info("reader_key : "+reader_key);
			Log.startTestCase("Authenticate");
			Log.info("TotalRows : "+sheet.getLastRowNum());
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
			Log.endTestCase("End");}

		
			client_Id=JDBC_category.getClientId(userID, sqlhost, sqlUsername, sqlPassword);
			
			consumerKey=JDBC_category.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			
			consumerSecret=JDBC_category.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);
			
			
			Response uploadEPup=UploadEpub.uploadEpub_OAuth(consumerKey, consumerSecret, filePath, ""+title_prefix+""+Title+"", author, category, isbn, description);	
			Validation.responseHeaderCodeValidation(uploadEPup, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(uploadEPup, HttpStatus.SC_OK);
			Validation.responseTimeValidation(uploadEPup);
			System.out.println("uploadEPup : "+uploadEPup);
			int epubID=uploadEPup.then().extract().path("epubId");

			Thread.sleep(9000);
			Response epubstatus=EpubStatus.epubStatus(consumerKey, consumerSecret, epubID);
			Validation.responseHeaderCodeValidation(epubstatus, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(epubstatus, HttpStatus.SC_OK);
			Validation.responseTimeValidation(epubstatus);
			System.out.println("epubstatus : "+epubstatus);

			}
			}
		}catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}
}
