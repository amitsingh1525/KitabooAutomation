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
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class DIS_1816 {

	public static List<String> detailisbn =  ExcelUtils.getisbn();
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	public static long startDate;
	public static String excelPath;
	public static Object startIndex = 0;
	public static Object endIndex = 0;
	public static String assetType;
	public static String Title;
	public static String userToken = "";
	public static int BookID_mark1;
	public static int  bookID1;
	public static int bookID2;
	public static int bookID3;
	public static int bookID4;
	public static int bookID5;
	public static int bookID6;
	public static int bookID7;
	public static int epubId;
	public static String isbn;
	public static long nowEpochTime;
	public static String title;
	public static String isbnMeta;
	public static String isbnIng;
	public static int userID;
	public static int totalbooks;
	public static String ebookID1;
	public static String catname;
	public static String collectionName1;
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
	public static String clientID;
	public static String catlevel;
	public static String isbn1;
	public static String isbn2;
	public static String isbn3;
	public static String isbn4;
	public static String isbn5;
	public static String isbn6;
	public static String isbn7;
	public static String isbn8;
	public static String isbn9;
	public static String isbn10;
	public static String isbn11;
	public static String consumerKey;
	public static String consumerSecret;
	public static String readingPercentage1;
	public static String readingPercentage2;
	public static String readingPercentage3;
	public static String readingPercentage4;
	public static String readingPercentage5;
	public static String classID;

	public static void   main(String []args) throws SQLException, JSONException{
		Log.initialization("DIS-1816");	
		//List<String> detail =  ExcelUtils.getuserDetails();
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/DIS-1816.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=1;i++)
			{	
				DataFormatter formatter = new DataFormatter();
				environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
				userName=formatter.formatCellValue(sheet.getRow(i).getCell(1));			
				password=formatter.formatCellValue(sheet.getRow(i).getCell(2));
				consumerKey=formatter.formatCellValue(sheet.getRow(i).getCell(4));
				consumerSecret=formatter.formatCellValue(sheet.getRow(i).getCell(5));
				clientID=formatter.formatCellValue(sheet.getRow(i).getCell(3));	
				catlevel=formatter.formatCellValue(sheet.getRow(i).getCell(6));	

				switch(environMent){
				case "QC":
					detail = "http://qc.kitaboo.com";
					break;
				case "Staging":
					detail = "http://qacloud.kitaboo.com";
					break;
				case "BASE_US":
					detail = "http://localhost:12346";
					break;
				case "BASE_EU":
					detail = "http://localhost:12347";
					break;
				case "PROD_US":
					detail = "http://cloud.kitaboo.com";
					break;
				case "PROD_EU":
					detail = "http://cloud.kitaboo.eu";
					break;
				}				
				io.restassured.RestAssured.baseURI = detail;

				Log.startTestCase("Authenticate");
				Log.info("detail : "+detail);
				Log.info("userName : "+userName);
				Log.info("password : "+password);
				Log.info("clientID : "+clientID);
				Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"65454","IPAD");
				Log.info("Authenticate Response: "+authenticateValue.then().extract().response().prettyPrint());
				System.out.println("HERE_Before");
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
				Log.endTestCase("End");

				Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"45616452","IPAD");
				Validation.responseCodeValidation1(fetchBookList_without_pagination, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(fetchBookList_without_pagination, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchBookList_without_pagination);
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "title");
				bookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[0]");
				System.out.println("bookID_1: "+bookID1);
				bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[1]");
				System.out.println("bookID: "+bookID2);
				bookID3 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[2]");
				System.out.println("bookID: "+bookID3);
				bookID4 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[3]");
				System.out.println("bookID: "+bookID4);				
				bookID5 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[4]");
				System.out.println("bookID: "+bookID5);
				/*bookID6 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[5]");
				System.out.println("bookID: "+bookID6);
				bookID7 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[6]");
				System.out.println("bookID: "+bookID7);*/
				title = fetchBookList_without_pagination.then().extract().path("bookList.book.title[0]");
				System.out.println("title: "+title);
				isbn = fetchBookList_without_pagination.then().extract().path("bookList.book.isbn[1]");
				System.out.println("isbn: "+isbn);
				ebookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.ebookID[0]");
				System.out.println("ebookID: "+ebookID1);
				assetType = fetchBookList_without_pagination.then().extract().path("bookList.book.assetType[0]");
				System.out.println("assetType: "+assetType);
				category1 = fetchBookList_without_pagination.then().extract().path("bookList.book.category[0]");
				System.out.println("category1: "+category1);
				collectionName1 = fetchBookList_without_pagination.then().extract().path("bookList.book.collectionTitle[0]");
				System.out.println("collectionName1: "+collectionName1);
				catname = ExtractCategory.extractCategory(category1);
				System.out.println("catname: " +catname);
				classID=fetchBookList_without_pagination.then().extract().path("bookList.book.classList[0].classID[0]");
				System.out.println("classID: "+classID);

				/*Response GetLastpageAccessed = GetLastPageAccessed.getLastPageAccessed(bookID1,userToken, "32324axsc", "IPAD");
				Validation.responseCodeValidation1(GetLastpageAccessed, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(GetLastpageAccessed, HttpStatus.SC_OK);
				Validation.responseTimeValidation(GetLastpageAccessed);
				Validation.responseKeyValidation_key(GetLastpageAccessed, "LastPageFolio");
				System.out.println("GetLastpageAccessed : "+GetLastpageAccessed);;*/

				Response fetchBookList_verf = FetchBookList.fetchBookList_without_pagination(userToken,"45616452","IPAD");
				Validation.responseCodeValidation1(fetchBookList_verf, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(fetchBookList_verf, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchBookList_verf);
				Validation.responseKeyValidation_key(fetchBookList_verf, "title");
				readingPercentage1 = fetchBookList_verf.then().extract().path("bookList.book.readingPercentage[0]");
				System.out.println("readingPercentage1: "+readingPercentage1); //Native page 1
				readingPercentage2 = fetchBookList_verf.then().extract().path("bookList.book.readingPercentage[1]");
				System.out.println("readingPercentage2: "+readingPercentage2);
				readingPercentage3 = fetchBookList_verf.then().extract().path("bookList.book.readingPercentage[2]");
				System.out.println("readingPercentage3: "+readingPercentage3);
				readingPercentage4 = fetchBookList_verf.then().extract().path("bookList.book.readingPercentage[3]");
				System.out.println("readingPercentage4: "+readingPercentage4);
				readingPercentage5 = fetchBookList_verf.then().extract().path("bookList.book.readingPercentage[4]");
				System.out.println("readingPercentage5: "+readingPercentage5);

				Response Readingspeed = ReadingSpeed.readingSpeed(bookID1, bookID2, bookID3,bookID4,bookID5, userToken, "ds313131", "IPAD");
				Validation.responseCodeValidation1(Readingspeed, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(Readingspeed, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Readingspeed);
				Validation.responseKeyValidation_key(Readingspeed, "redingPercentage");
				System.out.println("Readingspeed : "+Readingspeed);

				String readingPercentage_speed1=Readingspeed.then().extract().path("redingPercentage."+bookID1+"");
				Log.info("readingPercentage_speed1 : "+readingPercentage_speed1);
				Validation.responseKeyAndValue(Readingspeed, ""+bookID1+"", readingPercentage1);

				String readingPercentage_speed2=Readingspeed.then().extract().path("redingPercentage."+bookID2+"");
				Log.info("readingPercentage_speed2 : "+readingPercentage_speed2);
				Validation.responseKeyAndValue(Readingspeed, ""+bookID2+"", readingPercentage2);

				String readingPercentage_speed3=Readingspeed.then().extract().path("redingPercentage."+bookID3+"");
				Log.info("readingPercentage_speed3 : "+readingPercentage_speed3);
				Validation.responseKeyAndValue(Readingspeed, ""+bookID3+"", readingPercentage3);

				String readingPercentage_speed4=Readingspeed.then().extract().path("redingPercentage."+bookID4+"");
				Log.info("readingPercentage_speed4 : "+readingPercentage_speed4);
				Validation.responseKeyAndValue(Readingspeed, ""+bookID4+"", readingPercentage4);

				String readingPercentage_speed5 = Readingspeed.then().extract().path("redingPercentage."+bookID5+"");
				Log.info("readingPercentage_speed5 : "+readingPercentage_speed5);
				Validation.responseKeyAndValue(Readingspeed, ""+bookID5+"", readingPercentage5);
				
				
				Response saveTrackingdata=SaveTrackingData.saveTrackingData(bookID1, classID, "2020-09-12 22:41:00.499", "2020-09-12 22:51:00.499", 1, 1, 1, userToken, "jdwj3242", "IPAD");
				Validation.responseCodeValidation1(saveTrackingdata, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(saveTrackingdata, HttpStatus.SC_OK);
				Validation.responseTimeValidation(saveTrackingdata);
				Validation.responseKeyValidation_key(saveTrackingdata, "timestamp");
				Validation.responseKeyAndValue(saveTrackingdata, "responseMsg", "OK");
				System.out.println("saveTrackingdata : "+saveTrackingdata);
				
				Response fetchBookList_verf1 = FetchBookList.fetchBookList_without_pagination(userToken,"45616452","IPAD");
				Validation.responseCodeValidation1(fetchBookList_verf1, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(fetchBookList_verf1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchBookList_verf1);
				Validation.responseKeyValidation_key(fetchBookList_verf1, "title");
				readingPercentage1 = fetchBookList_verf1.then().extract().path("bookList.book.readingPercentage[0]");
				System.out.println("readingPercentage1: "+readingPercentage1); //Native page 1
				
				Response Readingspeed1 = ReadingSpeed.readingSpeed(bookID1, bookID2, bookID3,bookID4,bookID5, userToken, "ds313131", "IPAD");
				Validation.responseCodeValidation1(Readingspeed1, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(Readingspeed1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Readingspeed1);
				Validation.responseKeyValidation_key(Readingspeed1, "redingPercentage");
				System.out.println("Readingspeed : "+Readingspeed1);
			}
		}catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}
}

