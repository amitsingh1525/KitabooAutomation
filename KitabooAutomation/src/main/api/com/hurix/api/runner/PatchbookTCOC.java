package com.hurix.api.runner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hurix.api.externalAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class PatchbookTCOC {


	public static List<String> detailisbn =  ExcelUtils.getisbn();
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	public static long startDate ;//EpochTime.getEpochTime("2019/10/31 14:46:04");
	public static long startIndex = 0;
	public static long endIndex = 100;
	public static String assetType;
	public static int level;
	public static String numberOfBooks;
	public static String userToken = "";
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
	public static String excelPath;
	static String sqlhost;
	public static String externalURI;
	public static String clientID;
	public static String catlevel;
	public static int type;
	public static String isbn1;
	public static String isbn2;
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

	public static void main(String[] args) throws Exception {
		//Log.initialization("APITesting");//DIS-1979	
		Log.initialization("Sprint34/PatchTOC");
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/Sprint34/PatchTOC.xlsx";
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
				detail = "http://qacloud.kitaboo.com";//https://stagmicro.kitaboo.com
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
			//clientID = JDBC_Queries.getIDReader_userID(939764, sqlhost, sqlUsername, sqlPassword);

			Log.info("detail : "+detail);
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			Log.info("ReaderKey : "+clientID);

			Log.startTestCase("Authenticate."+deviceT+"");
			Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"514185",deviceT);
			Log.info("Authenticate Response: "+authenticateValue.then().extract().response().prettyPrint());				
			Log.info("HERE_Before");
			Log.info("clientID : "+clientID);
			Log.info("detail : "+detail);
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
			//Validation.responseKeyValidation_key(fetchBookList_without_pagination, "classID");
			//Validation.responseKeyValidation_key(jsonResponse, Title)
			bookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[0]");
			System.out.println("bookID_1: "+bookID1);
			/*bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[1]");			
			System.out.println("bookID2: "+bookID2);
			title1=fetchBookList_without_pagination.then().extract().path("bookList.book.title[0]");
			System.out.println("title: "+title1);
			title2=fetchBookList_without_pagination.then().extract().path("bookList.book.title[1]");
			System.out.println("title: "+title2);*/
			//bookID3 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[2]");			
			//System.out.println("bookID3: "+bookID3);
			//bookID6 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[6]");
			//System.out.println("bookID6 :: "+bookID6);
			/*isbn = fetchBookList_without_pagination.then().extract().path("bookList.book.isbn[1]");
			System.out.println("isbn: "+isbn);*/
			title1=fetchBookList_without_pagination.then().extract().path("bookList.book.title[0]");
			System.out.println("title: "+title1);

			String title2=null;
			String title3=null;
			String title4=null;
			String title5=null;
			String title6=null;
			String title7=null;
			String title8=null;
			String title9=null;
			String title10=null;
			if(i==1)
			{
				title2=fetchBookList_without_pagination.then().extract().path("bookList.book.title[1]");
				Log.info("title2: "+title2);
				title3=fetchBookList_without_pagination.then().extract().path("bookList.book.title[2]");
				Log.info("title3: "+title3);
				title4=fetchBookList_without_pagination.then().extract().path("bookList.book.title[3]");
				Log.info("title4: "+title4);
				title5=fetchBookList_without_pagination.then().extract().path("bookList.book.title[4]");
				Log.info("title5: "+title5);
				title6=fetchBookList_without_pagination.then().extract().path("bookList.book.title[5]");
				Log.info("title6: "+title6);
				title7=fetchBookList_without_pagination.then().extract().path("bookList.book.title[6]");
				Log.info("title7: "+title7);
				title8=fetchBookList_without_pagination.then().extract().path("bookList.book.title[7]");
				Log.info("title8: "+title8);
				title9=fetchBookList_without_pagination.then().extract().path("bookList.book.title[8]");
				Log.info("title9: "+title9);
				title10=fetchBookList_without_pagination.then().extract().path("bookList.book.title[9]");
				Log.info("title10: "+title10);
			}
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
			//archiveDate6=fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[6]");



			String[] TITLE = title1.trim().split(" ");
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
			//Validation.responseKeyValidation_key(searchv2, "bookId");
			Validation.responseKeyValidation_key(searchv2, "bookTitle");
			Validation.responseKeyValidation_key(searchv2, "description");
			Validation.responseKeyValidation_key(searchv2, "total");
			String bookReferenceId=searchv2.then().extract().path("searchResult.hits.hits[0]._source.bookReferenceId");
			Log.info("bookReferenceId : "+bookReferenceId);		


			String bookReferenceId1=null;
			String bookReferenceId2=null;
			String bookReferenceId3=null;
			String bookReferenceId4=null;
			String bookReferenceId5=null;
			String bookReferenceId6=null;
			String bookReferenceId7=null;
			String bookReferenceId8=null;
			String bookReferenceId9=null;
			
			if(i==1)
			{
				String[] TITLE1 = title2.trim().split(" ");
				searchv2=SearchV2.searchV2(""+TITLE1[0]+"", userToken, "bdhsbdhs213131", deviceT);
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
				//Validation.responseKeyValidation_key(searchv2, "bookId");
				Validation.responseKeyValidation_key(searchv2, "bookTitle");
				Validation.responseKeyValidation_key(searchv2, "description");
				Validation.responseKeyValidation_key(searchv2, "total");
				bookReferenceId1=searchv2.then().extract().path("searchResult.hits.hits[0]._source.bookReferenceId");
				Log.info("bookReferenceId1 : "+bookReferenceId1);		



				String[] TITLE2 = title3.trim().split(" ");
				searchv2=SearchV2.searchV2(""+TITLE2[0]+"", userToken, "bdhsbdhs213131", deviceT);
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
				//Validation.responseKeyValidation_key(searchv2, "bookId");
				Validation.responseKeyValidation_key(searchv2, "bookTitle");
				Validation.responseKeyValidation_key(searchv2, "description");
				Validation.responseKeyValidation_key(searchv2, "total");
				bookReferenceId2=searchv2.then().extract().path("searchResult.hits.hits[0]._source.bookReferenceId");
				Log.info("bookReferenceId2 : "+bookReferenceId2);	


				String[] TITLE3 = title4.trim().split(" ");
				searchv2=SearchV2.searchV2(""+TITLE3[0]+"", userToken, "bdhsbdhs213131", deviceT);
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
				//Validation.responseKeyValidation_key(searchv2, "bookId");
				Validation.responseKeyValidation_key(searchv2, "bookTitle");
				Validation.responseKeyValidation_key(searchv2, "description");
				Validation.responseKeyValidation_key(searchv2, "total");
				bookReferenceId3=searchv2.then().extract().path("searchResult.hits.hits[0]._source.bookReferenceId");
				Log.info("bookReferenceId3 : "+bookReferenceId3);

				String[] TITLE4 = title5.trim().split(" ");
				searchv2=SearchV2.searchV2(""+TITLE4[0]+"", userToken, "bdhsbdhs213131", deviceT);
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
				//Validation.responseKeyValidation_key(searchv2, "bookId");
				Validation.responseKeyValidation_key(searchv2, "bookTitle");
				Validation.responseKeyValidation_key(searchv2, "description");
				Validation.responseKeyValidation_key(searchv2, "total");
				bookReferenceId4=searchv2.then().extract().path("searchResult.hits.hits[0]._source.bookReferenceId");
				Log.info("bookReferenceId4 : "+bookReferenceId4);

				String[] TITLE5 = title6.trim().split(" ");
				searchv2=SearchV2.searchV2(""+TITLE5[0]+"", userToken, "bdhsbdhs213131", deviceT);
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
				//Validation.responseKeyValidation_key(searchv2, "bookId");
				Validation.responseKeyValidation_key(searchv2, "bookTitle");
				Validation.responseKeyValidation_key(searchv2, "description");
				Validation.responseKeyValidation_key(searchv2, "total");
				bookReferenceId5=searchv2.then().extract().path("searchResult.hits.hits[0]._source.bookReferenceId");
				Log.info("bookReferenceId5 : "+bookReferenceId5);


				String[] TITLE6 = title7.trim().split(" ");
				searchv2=SearchV2.searchV2(""+TITLE6[0]+"", userToken, "bdhsbdhs213131", deviceT);
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
				//Validation.responseKeyValidation_key(searchv2, "bookId");
				Validation.responseKeyValidation_key(searchv2, "bookTitle");
				Validation.responseKeyValidation_key(searchv2, "description");
				Validation.responseKeyValidation_key(searchv2, "total");
				bookReferenceId6=searchv2.then().extract().path("searchResult.hits.hits[0]._source.bookReferenceId");
				Log.info("bookReferenceId6 : "+bookReferenceId6);

				String[] TITLE7 = title8.trim().split(" ");
				searchv2=SearchV2.searchV2(""+TITLE7[0]+"", userToken, "bdhsbdhs213131", deviceT);
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
				//Validation.responseKeyValidation_key(searchv2, "bookId");
				Validation.responseKeyValidation_key(searchv2, "bookTitle");
				Validation.responseKeyValidation_key(searchv2, "description");
				Validation.responseKeyValidation_key(searchv2, "total");
				bookReferenceId7=searchv2.then().extract().path("searchResult.hits.hits[0]._source.bookReferenceId");
				Log.info("bookReferenceId7 : "+bookReferenceId7);

				String[] TITLE8 = title9.trim().split(" ");
				searchv2=SearchV2.searchV2(""+TITLE8[0]+"", userToken, "bdhsbdhs213131", deviceT);
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
				//Validation.responseKeyValidation_key(searchv2, "bookId");
				Validation.responseKeyValidation_key(searchv2, "bookTitle");
				Validation.responseKeyValidation_key(searchv2, "description");
				Validation.responseKeyValidation_key(searchv2, "total");
				bookReferenceId8=searchv2.then().extract().path("searchResult.hits.hits[0]._source.bookReferenceId");
				Log.info("bookReferenceId8 : "+bookReferenceId8);

				String[] TITLE9 = title10.trim().split(" ");
				searchv2=SearchV2.searchV2(""+TITLE9[0]+"", userToken, "bdhsbdhs213131", deviceT);
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
				//Validation.responseKeyValidation_key(searchv2, "bookId");
				Validation.responseKeyValidation_key(searchv2, "bookTitle");
				Validation.responseKeyValidation_key(searchv2, "description");
				Validation.responseKeyValidation_key(searchv2, "total");
				bookReferenceId9=searchv2.then().extract().path("searchResult.hits.hits[0]._source.bookReferenceId");
				Log.info("bookReferenceId9 : "+bookReferenceId9);
			}

			String formate = JDBC_Queries.getFormate_3(bookID1,sqlhost,sqlUsername,sqlPassword);

			Response downloadBook =DownloadBook.downloadBook(userToken, "2345", formate, bookID1, "online");
			Validation.responseCodeValidation1(downloadBook, HttpStatus.SC_OK);
			Validation.responseHeaderCodeValidation(downloadBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBook);


			if(environMent.equals("Staging"))
			{
				detail = "https://stagmicro.kitaboo.com";
				sqlhost="jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com";
				sqlUsername="qcteam";
				sqlPassword="JB88F-WT2Q3-DPXTT";
			}
			else if(environMent.equals("PROD_US"))
			{
				detail = "https://worthpointservices.kitaboo.com";
				sqlhost="jdbc:mysql://localhost:12345";
				sqlUsername="shweta-katare";
				sqlPassword="J&P@O4A7HV";
			}
			consumerKey = JDBC_Queries.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			consumerSecret =JDBC_Queries.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);

			Response accessToken = AccessToken.accessToken(consumerKey, consumerSecret);
			String access_token=accessToken.then().extract().path("access_token");
			Log.info("access_token : "+access_token);

			io.restassured.RestAssured.baseURI = detail;
			
			Response booktoc=Booktoc.booktoc(bookReferenceId, access_token);
			Log.info("detail : "+detail);
			Validation.responseCodeValidation1(booktoc, HttpStatus.SC_OK);
			Validation.responseHeaderCodeValidation(booktoc, HttpStatus.SC_OK);
			Validation.responseTimeValidation(booktoc);
			Log.warn("children");
			if(i==2)
			{
				Validation.responseISGreater("pageNo", 180, 180);
			}
			if(i==1)
			{
				booktoc=Booktoc.booktoc(bookReferenceId1, access_token);
				Log.info("detail : "+detail);
				Validation.responseCodeValidation1(booktoc, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(booktoc, HttpStatus.SC_OK);
				Validation.responseTimeValidation(booktoc);

				booktoc=Booktoc.booktoc(bookReferenceId2, access_token);
				Log.info("detail : "+detail);
				Validation.responseCodeValidation1(booktoc, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(booktoc, HttpStatus.SC_OK);
				Validation.responseTimeValidation(booktoc);

				booktoc=Booktoc.booktoc(bookReferenceId3, access_token);
				Log.info("detail : "+detail);
				Validation.responseCodeValidation1(booktoc, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(booktoc, HttpStatus.SC_OK);
				Validation.responseTimeValidation(booktoc);

				booktoc=Booktoc.booktoc(bookReferenceId4, access_token);
				Log.info("detail : "+detail);
				Validation.responseCodeValidation1(booktoc, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(booktoc, HttpStatus.SC_OK);
				Validation.responseTimeValidation(booktoc);

				booktoc=Booktoc.booktoc(bookReferenceId5, access_token);
				Log.info("detail : "+detail);
				Validation.responseCodeValidation1(booktoc, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(booktoc, HttpStatus.SC_OK);
				Validation.responseTimeValidation(booktoc);

				booktoc=Booktoc.booktoc(bookReferenceId6, access_token);
				Log.info("detail : "+detail);
				Validation.responseCodeValidation1(booktoc, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(booktoc, HttpStatus.SC_OK);
				Validation.responseTimeValidation(booktoc);

				booktoc=Booktoc.booktoc(bookReferenceId7, access_token);
				Log.info("detail : "+detail);
				Validation.responseCodeValidation1(booktoc, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(booktoc, HttpStatus.SC_OK);
				Validation.responseTimeValidation(booktoc);

				booktoc=Booktoc.booktoc(bookReferenceId8, access_token);
				Log.info("detail : "+detail);
				Validation.responseCodeValidation1(booktoc, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(booktoc, HttpStatus.SC_OK);
				Validation.responseTimeValidation(booktoc);

				booktoc=Booktoc.booktoc(bookReferenceId9, access_token);
				Log.info("detail : "+detail);
				Validation.responseCodeValidation1(booktoc, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(booktoc, HttpStatus.SC_OK);
				Validation.responseTimeValidation(booktoc);		

			}
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