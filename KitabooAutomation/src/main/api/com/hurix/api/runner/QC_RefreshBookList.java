package com.hurix.api.runner;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;

import com.hurix.api.readerAPIs.Authenticate;
import com.hurix.api.readerAPIs.FetchBookCount;
import com.hurix.api.readerAPIs.FetchBookList;
import com.hurix.api.readerAPIs.V1refreshBookList;
import com.hurix.api.utility.ExcelUtils;
import com.hurix.api.utility.ExtractCategory;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class QC_RefreshBookList {


	public static List<String> detailisbn =  ExcelUtils.getisbn();
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	//System.out.println(df.format(dateobj));
	//public static long startDate1 = EpochTime.getEpochTime("df.format(dateobj");
	//"2019/10/31 14:46:04"
	public static long startDate;
	public static String excelPath;
	public static Object startIndex = 0;
	public static Object endIndex = 0;
	//public static int level;
	public static String assetType;
	public static String Title;
	public static String userToken = "";
	public static int BookID_mark1;
	public static int  bookID1;
	public static int bookID2;
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
	//public static String consumerKey=ExcelUtils.Consumer_key;
	//public static String consumerSecret=ExcelUtils.secret_key;

	public static void   main(String []args) throws SQLException, JSONException{
		Log.initialization("DIS-Ref");	
		//List<String> detail =  ExcelUtils.getuserDetails();
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/DIS-ref.xlsx";
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

				Response fetchBookList_with_pagination = FetchBookList.fetchBookList_with_pagination(0,10,userToken,"45616452","IPAD");
				Validation.responseCodeValidation1(fetchBookList_with_pagination, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(fetchBookList_with_pagination, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchBookList_with_pagination);
				System.out.println("fetchBookList_with_pagination_RES : " +fetchBookList_with_pagination);
				totalbooks= fetchBookList_with_pagination.then().assertThat().extract().path("totalbooks");

				Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"45616452","IPAD");
				Validation.responseCodeValidation1(fetchBookList_without_pagination, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(fetchBookList_without_pagination, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchBookList_without_pagination);
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "title");
				bookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[0]");
				System.out.println("bookID_1: "+bookID1);
				bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[1]");
				System.out.println("bookID: "+bookID2);
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
				String archiveDate1=fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[0]");
				System.out.println("archiveDate1: "+archiveDate1);

				Response GETfetchBookCount_res = FetchBookCount.fetchBookCount(userToken,"45616452","IPAD");
				Validation.responseHeaderCodeValidation(GETfetchBookCount_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(GETfetchBookCount_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(GETfetchBookCount_res);
				Validation.responseKeyValidation_key(GETfetchBookCount_res, "totalbooks");
				System.out.println("fetchBookCount_res : "+GETfetchBookCount_res);
				nowEpochTime = Instant.now().toEpochMilli();

				//2019-12-31 12:50:23.0
				Response v1refreshBookList_res =V1refreshBookList.v1refreshBookList(""+archiveDate1+"","NEW","UPDATE",bookID1,bookID2,userToken,"56454", "IPAD",clientID);
				Validation.responseHeaderCodeValidation(v1refreshBookList_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(v1refreshBookList_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(v1refreshBookList_res);
				Validation.responseKeyValidation_key(v1refreshBookList_res, "archiveDate");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "assetType");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "assignedOn");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "author");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "bookActive");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "bookCode");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "bookDisLikeCount");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "bookId");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "bookLikeCount");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "category");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "categoryIdList");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "categoryList");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "collectionID");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "collectionTitle");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "collectionType");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "ebookID");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "reflow");
				Validation.responseKeyValidation_key(v1refreshBookList_res, "operation");
				String operation0=v1refreshBookList_res.then().extract().path("bookList.book.operation[0]");
				String operation1=v1refreshBookList_res.then().extract().path("bookList.book.operation[1]");
				Validation.responseKeyAndValue(v1refreshBookList_res, "operation", operation0);
				Validation.responseKeyAndValue(v1refreshBookList_res, "operation", operation1);
				System.out.println("v1/v1refreshBookList_res : "+v1refreshBookList_res);

			}
		}
		catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
	}

}

