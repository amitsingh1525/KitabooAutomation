package com.hurix.api.runner;

import io.restassured.response.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.readerAPIs.DownloadBook;
import com.hurix.api.readerAPIs.FetchBookList;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class CDNoffline {

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
	public static String state;
	public static int userID1=0;
	//public static String deviceLimit;
	//public static String type;


	public static void main(String[] args) {
		//Log.initialization("APITesting");//DIS-1979	
		Log.initialization("CDN_Offline");
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/CDN_Offline.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				DataFormatter formatter = new DataFormatter();
				environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
				userName=formatter.formatCellValue(sheet.getRow(i).getCell(1));			
				password=formatter.formatCellValue(sheet.getRow(i).getCell(2));		
				deviceT =  formatter.formatCellValue(sheet.getRow(i).getCell(3));
				client_T = formatter.formatCellValue(sheet.getRow(i).getCell(4));							
				String runY_N = formatter.formatCellValue(sheet.getRow(i).getCell(5));
				state =  formatter.formatCellValue(sheet.getRow(i).getCell(6));	

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

				SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
				Date date = new Date();  
				String  time=formatter1.format(date);
				System.out.println("time : "+time);
				if(i==1 || i==8|| i==15){
				clientID =JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);
				//int client_Id = Integer.parseInt(""+client_Id+"");					
				Log.startTestCase("Authenticate");
				Log.info("TotalRows : "+sheet.getLastRowNum());
				Log.info("detail : "+detail);
				Log.info("userName : "+userName);
				Log.info("password : "+password);
				Log.info("clientID : "+clientID);
				Log.info("deviceT : "+deviceT);
				
				Response authenticateValue = Authenticate.authenticate(clientID, userName, password, "514185",deviceT);
				Log.info("Authenticate Response: "+authenticateValue.then().extract().response().prettyPrint());				
				System.out.println("HERE_Before");
				Log.info("clientID : "+clientID);
				Validation.responseHeaderCodeValidation(authenticateValue, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(authenticateValue, HttpStatus.SC_OK);
				Validation.responseTimeValidation(authenticateValue);
				userToken = authenticateValue.then().extract().path("userToken");
				Log.info("userToken:"+userToken);
				
				Response fetchbooklist = FetchBookList.fetchBookList_without_pagination(userToken, "dasdsd434", deviceT);
				bookID1=fetchbooklist.then().extract().path("bookList.book.id[0]");
				Log.info("bookID1:"+bookID1);}else {}
				
				
				Response download = DownloadBook.downloadBook(userToken,"dadad646",deviceT,bookID1,state);
				Log.info("detail : "+detail);
				Validation.responseHeaderCodeValidation(download, HttpStatus.SC_OK);
				//Validation.responseCodeValidation1(download, HttpStatus.SC_OK);
				Validation.responseTimeValidation(download);
				
				
				
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
