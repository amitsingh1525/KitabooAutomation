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

import com.hurix.api.externalAPIs.SearchV2_OAuth;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class DIS_2055 {

	public static List<String> detailisbn =  ExcelUtils.getisbn();
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	//System.out.println(df.format(dateobj));
	//public static long startDate1 = EpochTime.getEpochTime("df.format(dateobj");
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
	public static int totalbooks;
	public static int total;
	public static String archiveDate;
	public static String archiveDate6;
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
	public static String runY_N;
	public static String deviceT;
	public static String detail;
	static String sqlUsername;
	static String sqlPassword;
	static String sqlhost;
	public static String externalURI;
	public static String clientID;
	public static String catlevel;
	public static int type;
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
	public static Object categoryIdList0;
	public static Object categoryIdList1;
	public static Object categoryIdList2;
	public static Object categoryIdList3;
	public static Object categoryIdList4;
	public static String category;
	public static Object categoryList;
	public static Object categoryIdList;
	public static String title1;
	public static String title2;
	
	//public static String consumerKey=ExcelUtils.Consumer_key;
	//public static String consumerSecret=ExcelUtils.secret_key;

	public static void   main(String []args) throws SQLException, JSONException{
		Log.initialization("DIS-2055");	
		//List<String> detail =  ExcelUtils.getuserDetails();
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			String excelPath="./testData/DIS-2055.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{DataFormatter formatter = new DataFormatter();
			environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			userName = formatter.formatCellValue(sheet.getRow(i).getCell(1));			
			password = formatter.formatCellValue(sheet.getRow(i).getCell(2));
			//consumerKey = formatter.formatCellValue(sheet.getRow(i).getCell(4));
			//consumerSecret = formatter.formatCellValue(sheet.getRow(i).getCell(5));
			//clientID = formatter.formatCellValue(sheet.getRow(i).getCell(3));	
			catlevel = formatter.formatCellValue(sheet.getRow(i).getCell(3));
			deviceT = formatter.formatCellValue(sheet.getRow(i).getCell(4));
			runY_N = formatter.formatCellValue(sheet.getRow(i).getCell(5));
			categoryIdList0 = formatter.formatCellValue(sheet.getRow(i).getCell(7));
			categoryIdList1 = formatter.formatCellValue(sheet.getRow(i).getCell(8));
			categoryIdList2 = formatter.formatCellValue(sheet.getRow(i).getCell(9));
			categoryIdList3 = formatter.formatCellValue(sheet.getRow(i).getCell(10));

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
			clientID=JDBC_category.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			Log.info("clientID : "+clientID);
			Log.info("detail : "+detail);
			Log.info("SHEET LOOP"+sheet.getLastRowNum());
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			Log.info("clientID : "+clientID);
			Log.startTestCase("Authenticate");
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
			//userID = authenticateValue.then().extract().path("user.id");
			System.out.println("userID: "+userID);
			userToken = authenticateValue.then().extract().path("userToken");
			System.out.println("userToken:"+userToken);
			clientUserID = authenticateValue.then().extract().path("user.clientUserID");
			System.out.println("clientUserID:"+clientUserID);
			Log.endTestCase("End");
			int client_Id = authenticateValue.then().extract().path("user.clientID");
			System.out.println("client_Id:"+client_Id);
			Log.endTestCase("End");

			consumerKey = JDBC_category.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			consumerSecret =JDBC_category.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);

			Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"45616452",deviceT);
			Validation.responseCodeValidation1(fetchBookList_without_pagination, HttpStatus.SC_OK);
			Validation.responseHeaderCodeValidation(fetchBookList_without_pagination, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchBookList_without_pagination);
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "isbn");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "title");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "id");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "archiveDate");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "assetType");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "assignedOn");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "bookActive");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "bookCode");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "bookId");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "category");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "categoryIdList");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "categoryList");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "locale");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "collectionThumbnail");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "collectionType");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "formats");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "readingPercentage");
			Validation.responseKeyValidation_key(fetchBookList_without_pagination, "classID");
			//Validation.responseKeyValidation_key(jsonResponse, Title)
			bookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[0]");
			System.out.println("bookID_1: "+bookID1);
			bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[1]");			
			System.out.println("bookID2: "+bookID2);
			title1=fetchBookList_without_pagination.then().extract().path("bookList.book.title[0]");
			System.out.println("title: "+title1);
			title2=fetchBookList_without_pagination.then().extract().path("bookList.book.title[1]");
			System.out.println("title: "+title2);
			//bookID3 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[2]");			
			//System.out.println("bookID3: "+bookID3);
			//bookID6 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[6]");
			//System.out.println("bookID6 :: "+bookID6);
			isbn = fetchBookList_without_pagination.then().extract().path("bookList.book.isbn[1]");
			System.out.println("isbn: "+isbn);
			type=fetchBookList_without_pagination.then().extract().path("bookList.book.type[0]");
			System.out.println("type: "+type);
			ebookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.ebookID[0]");
			System.out.println("ebookID: "+ebookID1);
			assetType = fetchBookList_without_pagination.then().extract().path("bookList.book.assetType[0]");
			System.out.println("assetType: "+assetType);
			category1 = fetchBookList_without_pagination.then().extract().path("bookList.book.category[0]");
			System.out.println("category1: "+category1);
			collectionName0 = fetchBookList_without_pagination.then().extract().path("bookList.book.collectionTitle[0]");
			System.out.println("collectionName0: "+collectionName0);
			catname = ExtractCategory.extractCategory(category1);
			System.out.println("catname: " +catname);
			archiveDate = fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[0]");
			System.out.println("archiveDate:"+archiveDate);
			archiveDate6=fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[6]");
			System.out.println("archiveDate:"+archiveDate);				


			String[] TITLE = title1.trim().split("_");
			Response searchv2=SearchV2.searchV2(""+TITLE[0]+"", userToken, "bdhsbdhs213131", deviceT);
			//Validation.responseCodeValidation1(searchv2, HttpStatus.SC_OK);
			//Validation.responseHeaderCodeValidation(searchv2, HttpStatus.SC_OK);
			Validation.responseTimeValidation(searchv2);
			Validation.responseKeyValidation_key(searchv2, "_id");
			Validation.responseKeyValidation_key(searchv2, "_index");
			Validation.responseKeyValidation_key(searchv2, "_score");
			Validation.responseKeyValidation_key(searchv2, "ISBN");
			Validation.responseKeyValidation_key(searchv2, "bookReferenceId");
			Validation.responseKeyValidation_key(searchv2, "bookThumbnail");
			Validation.responseKeyValidation_key(searchv2, "bookTitle");
			Validation.responseKeyValidation_key(searchv2, "description");
			Validation.responseKeyValidation_key(searchv2, "bookReferenceId");
			Validation.responseKeyValidation_key(searchv2, "_type");
			Validation.responseKeyValidation_key(searchv2, "bookId");
			Validation.responseKeyValidation_key(searchv2, "bookTitle");
			Validation.responseKeyValidation_key(searchv2, "description");
			Validation.responseKeyValidation_key(searchv2, "total");
			//Validation.responseKeyValidation_key(searchv2, "took");
			System.out.println("searchv2 : "+searchv2);


			title2=fetchBookList_without_pagination.then().extract().path("bookList.book.title[1]");
			System.out.println("title: "+title2);
			System.out.println("@#$$#%^@&*(@#*&$^#& title2 : "+title2);
			String[] TITLE1 = title2.trim().split("_");
			searchv2 = SearchV2.searchV2(""+TITLE1[0]+"", userToken, "bdhsbdhs213131",deviceT);
			Validation.responseCodeValidation1(searchv2, HttpStatus.SC_OK);
			Validation.responseHeaderCodeValidation(searchv2, HttpStatus.SC_OK);
			Validation.responseTimeValidation(searchv2);
			Validation.responseKeyValidation_key(searchv2, "_id");
			Validation.responseKeyValidation_key(searchv2, "_index");
			Validation.responseKeyValidation_key(searchv2, "_score");
			Validation.responseKeyValidation_key(searchv2, "ISBN");
			Validation.responseKeyValidation_key(searchv2, "bookThumbnail");
			Validation.responseKeyValidation_key(searchv2, "bookTitle");
			Validation.responseKeyValidation_key(searchv2, "description");
			Validation.responseKeyValidation_key(searchv2, "bookReferenceId");
			Validation.responseKeyValidation_key(searchv2, "_type");
			Validation.responseKeyValidation_key(searchv2, "bookId");
			Validation.responseKeyValidation_key(searchv2, "bookTitle");
			Validation.responseKeyValidation_key(searchv2, "description");
			Validation.responseKeyValidation_key(searchv2, "total");
			System.out.println("searchv2 : "+searchv2);

			//GetSecureURL.getSecureURL(userToken, "dsjd4242", "IPAD", type);
			Response searchv2_ext =SearchV2_OAuth.searchV2_OAuth(""+TITLE[0]+"", consumerKey,consumerSecret,clientUserID);
			//Validation.responseCodeValidation1(searchv2_ext, HttpStatus.SC_OK);
			//Validation.responseHeaderCodeValidation(searchv2_ext, HttpStatus.SC_OK);
			Validation.responseTimeValidation(searchv2_ext);
			Validation.responseKeyValidation_key(searchv2_ext, "_id");
			Validation.responseKeyValidation_key(searchv2_ext, "_index");
			Validation.responseKeyValidation_key(searchv2_ext, "_score");
			Validation.responseKeyValidation_key(searchv2_ext, "ISBN");
			Validation.responseKeyValidation_key(searchv2_ext, "bookThumbnail");
			Validation.responseKeyValidation_key(searchv2_ext, "bookTitle");
			Validation.responseKeyValidation_key(searchv2_ext, "description");
			Validation.responseKeyValidation_key(searchv2_ext, "bookReferenceId");
			Validation.responseKeyValidation_key(searchv2_ext, "_type");
			Validation.responseKeyValidation_key(searchv2_ext, "bookId");
			Validation.responseKeyValidation_key(searchv2_ext, "bookTitle");
			Validation.responseKeyValidation_key(searchv2_ext, "description");
			Validation.responseKeyValidation_key(searchv2_ext, "total");
			System.out.println("searchv2_ext : "+searchv2_ext);

			searchv2_ext =SearchV2_OAuth.searchV2_OAuth(""+TITLE1[0]+"", consumerKey,consumerSecret,clientUserID);
			//Validation.responseCodeValidation1(searchv2_ext, HttpStatus.SC_OK);
			//Validation.responseHeaderCodeValidation(searchv2_ext, HttpStatus.SC_OK);
			Validation.responseTimeValidation(searchv2_ext);
			Validation.responseKeyValidation_key(searchv2_ext, "_id");
			Validation.responseKeyValidation_key(searchv2_ext, "_index");
			Validation.responseKeyValidation_key(searchv2_ext, "_score");
			Validation.responseKeyValidation_key(searchv2_ext, "ISBN");
			Validation.responseKeyValidation_key(searchv2_ext, "bookThumbnail");
			Validation.responseKeyValidation_key(searchv2_ext, "bookTitle");
			Validation.responseKeyValidation_key(searchv2_ext, "description");
			Validation.responseKeyValidation_key(searchv2_ext, "bookReferenceId");
			Validation.responseKeyValidation_key(searchv2_ext, "_type");
			Validation.responseKeyValidation_key(searchv2_ext, "bookId");
			Validation.responseKeyValidation_key(searchv2_ext, "bookTitle");
			Validation.responseKeyValidation_key(searchv2_ext, "description");
			Validation.responseKeyValidation_key(searchv2_ext, "total");
			System.out.println("searchv2_ext : "+searchv2_ext);
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
