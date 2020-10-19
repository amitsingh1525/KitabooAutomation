package com.hurix.api.runner;

import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

/*
 * 
 * Author Name:
 * SHWETA KATARE THE BRAND
 * 
 */

public class DIS_2127 {
	public static String consumerKey;
	public static String consumerSecret;
	public static String excelPath;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFSheet sheet1;
	public static String environMent;
	public static String userName;
	public static String password;
	public static String detail;
	static int BookID_mark1;
	static int BookID_mark2;
	static int BookID_mark3;
	public static String firstName;
	public static String version;
	public static String lastName;
	public static String clientID;
	public static String catlevel;
	public static String userName1;
	public static String email;
	public static String title;
	public static int  bookID1;
	public static int bookID2;
	public static int bookID3;
	public static int bookID4;
	public static int bookID5;
	public static int bookID6;
	static String isbn;
	static String userDeleted;
	static String assetType;
	static String type;
	static Object archiveDate;
	static Object archiveDate6;
	static String operation0;
	static String operation1;
	static String ebookID1;
	public static String category1;
	public static String collectionName0;
	public static String catname; 
	public static long nowEpochTime;
	public static String Title;
	public static int epubId; 
	public static String emailID;
	public static String assignedBy;
	public static String totalbooks;
	public static String pageID;
	public static String lastPage;
	public static String chapterID;
	public static String isbnMeta;
	public static String isbnIng;
	public static String sqlhost;
	public static String sqlUsername;
	public static String sqlPassword;
	public static int userID=0;

	public static void main(String[] args) throws Exception {
		//Log.initialization("APITesting");//DIS-1979	
		Log.initialization("Sprint34/DIS-2127");
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/Sprint34/DIS-2127.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{DataFormatter formatter = new DataFormatter();
			environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			userName = formatter.formatCellValue(sheet.getRow(i).getCell(1));			
			password = formatter.formatCellValue(sheet.getRow(i).getCell(2));				
			catlevel = formatter.formatCellValue(sheet.getRow(i).getCell(3));
			String deviceType = formatter.formatCellValue(sheet.getRow(i).getCell(4));
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

			if(environMent.contains("QC") && i==3)
			{clientID = JDBC_Queries.getReader_user_ID(95750095, sqlhost, sqlUsername, sqlPassword);}
			else{clientID = JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);}


			Log.info("detail : "+detail);
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			Log.info("ReaderKey : "+clientID);

			Log.startTestCase("Authenticate."+deviceType+"");
			Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"514185",deviceType);
			Log.info("Authenticate Response: "+authenticateValue.then().extract().response().prettyPrint());				
			Log.info("HERE_Before");
			Log.info("clientID : "+clientID);
			Validation.responseHeaderCodeValidation(authenticateValue, HttpStatus.SC_OK);
			//Validation.responseCodeValidation1(authenticateValue, HttpStatus.SC_OK);
			Validation.responseTimeValidation(authenticateValue);
			Validation.responseKeyValidation_key(authenticateValue, "userName");
			Validation.responseKeyValidation_key(authenticateValue, userName);			
			Log.info("detail : "+detail);
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

			//consumerKey = JDBC_Queries.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			//consumerSecret =JDBC_Queries.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);

			Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"45616452",deviceType);
			Validation.responseCodeValidation1(fetchBookList_without_pagination, HttpStatus.SC_OK);
			Validation.responseHeaderCodeValidation(fetchBookList_without_pagination, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchBookList_without_pagination);
			Log.info("detail : "+detail);
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
			title = fetchBookList_without_pagination.then().extract().path("bookList.book.title[0]");
			Log.info("title: "+title);
			bookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[0]");
			Log.info("bookID_1: "+bookID1);
			bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[1]");			
			Log.info("bookID2: "+bookID2);
			try{bookID3 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[2]");			
			Log.info("bookID3: "+bookID3);
			bookID4 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[3]");
			Log.info("bookID4 :: "+bookID4);
			bookID5 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[4]");
			Log.info("bookID5 :: "+bookID5);
			bookID6 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[5]");
			Log.info("bookID6 :: "+bookID6);
			}
			catch (Exception e) 
			{Log.warn("BOOK ID's are not Present to fetch and further proccedure");
			e.printStackTrace();}
			isbn = fetchBookList_without_pagination.then().extract().path("bookList.book.isbn[1]");
			Log.info("isbn: "+isbn);
			int type=fetchBookList_without_pagination.then().extract().path("bookList.book.type[0]");
			Log.info("type: "+type);
			ebookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.ebookID[0]");
			Log.info("ebookID: "+ebookID1);
			assetType = fetchBookList_without_pagination.then().extract().path("bookList.book.assetType[0]");
			Log.info("assetType: "+assetType);
			category1 = fetchBookList_without_pagination.then().extract().path("bookList.book.category[0]");
			Log.info("category1: "+category1);
			collectionName0 = fetchBookList_without_pagination.then().extract().path("bookList.book.collectionTitle[0]");
			Log.info("collectionName0: "+collectionName0);
			catname = ExtractCategory.extractCategory(category1);
			Log.info("catname: " +catname);
			/*forName = fetchBookList_without_pagination.then().extract().path("bookList.book.formats.name[0]");
			Log.info("$#@$#@#@#@##@#$!@#$%^#@#$%^ forName :: "+forName);*/
			archiveDate = fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[0]");
			Log.info("archiveDate:"+archiveDate);
			archiveDate6=fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[6]");
			Log.info("archiveDate:"+archiveDate);
			category1 = fetchBookList_without_pagination.then().extract().path("bookList.book.category[0]");
			Log.info("category1: "+category1);
			Log.info("category1: "+category1);
			String collectionID = fetchBookList_without_pagination.then().extract().path("bookList.book.collectionID[0]");
			Log.info("collectionID: "+collectionID);
			collectionName0 = fetchBookList_without_pagination.then().extract().path("bookList.book.collectionTitle[0]");
			Log.info("collectionName0: "+collectionName0);
			String classID =fetchBookList_without_pagination.then().extract().path("bookList.book.classList[0].classID[0]");
			Log.info("classID: "+classID);
			version = fetchBookList_without_pagination.then().extract().path("bookList.book.version[0]");
			Log.info("version: "+version);

			Response GETfetchBookCount = FetchBookCount.fetchBookCount(userToken,"45616452",deviceType);
			Validation.responseHeaderCodeValidation(GETfetchBookCount, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(GETfetchBookCount,HttpStatus.SC_OK);
			Validation.responseTimeValidation(GETfetchBookCount);
			Validation.responseKeyValidation_key(GETfetchBookCount, "totalbooks");
			Validation.responseKeyValidation_key(GETfetchBookCount, "timestamp");
			
			Response markasRead_2 = MarkRead.markRead_read(bookID5,bookID6, userToken, "hgfds23456", deviceType);
			Validation.responseHeaderCodeValidation(markasRead_2, HttpStatus.SC_OK);
			Validation.responseTimeValidation(markasRead_2);
			Validation.responseKeyAndValue(markasRead_2, "responseMsg","OK");
			
			Response markRead_1 = MarkRead.markRead(bookID3,bookID4,bookID5,bookID6, userToken, "hgfds23456", deviceType);
			Log.info("detail : "+detail);
			Validation.responseHeaderCodeValidation(markRead_1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(markRead_1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(markRead_1);
			Validation.responseKeyAndValue(markRead_1, "responseMsg","OK");
			
			
			Response markRead = MarkRead.markRead_read(bookID1,bookID2, userToken, "hgfds23456", deviceType);
			Log.info("detail : "+detail);
			Validation.responseHeaderCodeValidation(markRead, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(markRead, HttpStatus.SC_OK);
			Validation.responseTimeValidation(markRead);
			Validation.responseKeyAndValue(markRead, "responseMsg","OK");


			Response fetchMarkedReadBooksIds = FetchMarkedReadBooksIds.fetchMarkedReadBooksIds(userToken, "sdfg23456", deviceType);
			Validation.responseHeaderCodeValidation(fetchMarkedReadBooksIds, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchMarkedReadBooksIds, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchMarkedReadBooksIds);
			Validation.responseKeyAndValue(fetchMarkedReadBooksIds, "responseMsg","OK");
			Validation.responseKeyValidation_key(fetchMarkedReadBooksIds, ""+bookID1+"");
			Validation.responseKeyValidation_key(fetchMarkedReadBooksIds, ""+bookID2+"");
			Validation.responseKeyValidation_key(fetchMarkedReadBooksIds, ""+bookID3+"");
			Validation.responseKeyValidation_key(fetchMarkedReadBooksIds, ""+bookID4+"");

			Response fetchMarkedReadBooks = FetchMarkedReadBooks.fetchMarkedReadBooks(0, 100, userToken, "sdfgh456", deviceType);
			Validation.responseHeaderCodeValidation(fetchMarkedReadBooks, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchMarkedReadBooks);
			Validation.responseKeyAndValue(fetchMarkedReadBooks, "responseMsg","OK");
			Validation.responseKeyValidation_key(fetchMarkedReadBooks, ""+bookID1+"");
			Validation.responseKeyValidation_key(fetchMarkedReadBooks, ""+bookID2+"");
			Validation.responseKeyValidation_key(fetchMarkedReadBooks, ""+bookID3+"");
			Validation.responseKeyValidation_key(fetchMarkedReadBooks, ""+bookID4+"");

			Response markRead_unmarkRead = MarkRead.markRead_unmarkRead(bookID1,bookID2, userToken, "hgfds23456", deviceType);
			Log.info("detail : "+detail);
			Validation.responseHeaderCodeValidation(markRead_unmarkRead, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(markRead_unmarkRead, HttpStatus.SC_OK);
			Validation.responseTimeValidation(markRead_unmarkRead);
			Validation.responseKeyAndValue(markRead_unmarkRead, "responseMsg","OK");

			fetchMarkedReadBooksIds = FetchMarkedReadBooksIds.fetchMarkedReadBooksIds(userToken, "sdfg23456", deviceType);
			Validation.responseHeaderCodeValidation(fetchMarkedReadBooksIds, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchMarkedReadBooksIds, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchMarkedReadBooksIds);
			Validation.responseKeyAndValue(fetchMarkedReadBooksIds, "responseMsg","OK");
			Log.info("detail : "+detail);
			Validation.responseNOTKeyValidation_key(fetchMarkedReadBooksIds, ""+bookID1+"");
			Validation.responseNOTKeyValidation_key(fetchMarkedReadBooksIds, ""+bookID2+"");
			
			fetchMarkedReadBooks = FetchMarkedReadBooks.fetchMarkedReadBooks(0, 100, userToken, "sdfgh456", deviceType);
			Validation.responseHeaderCodeValidation(fetchMarkedReadBooks, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchMarkedReadBooks);
			Validation.responseKeyAndValue(fetchMarkedReadBooks, "responseMsg","OK");
			Validation.responseNOTKeyValidation_key(fetchMarkedReadBooks, ""+bookID1+"");
			Validation.responseNOTKeyValidation_key(fetchMarkedReadBooks, ""+bookID2+"");
			
			markRead_unmarkRead = MarkRead.markRead_unmarkRead(bookID3,bookID4, userToken, "hgfds23456", deviceType);
			Log.info("detail : "+detail);
			Validation.responseHeaderCodeValidation(markRead_unmarkRead, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(markRead_unmarkRead, HttpStatus.SC_OK);
			Validation.responseTimeValidation(markRead_unmarkRead);
			Validation.responseKeyAndValue(markRead_unmarkRead, "responseMsg","OK");

			fetchMarkedReadBooksIds = FetchMarkedReadBooksIds.fetchMarkedReadBooksIds(userToken, "sdfg23456", deviceType);
			Validation.responseHeaderCodeValidation(fetchMarkedReadBooksIds, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchMarkedReadBooksIds, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchMarkedReadBooksIds);
			Validation.responseKeyAndValue(fetchMarkedReadBooksIds, "responseMsg","OK");
			Log.info("detail : "+detail);
			Validation.responseNOTKeyValidation_key(fetchMarkedReadBooksIds, ""+bookID3+"");
			Validation.responseNOTKeyValidation_key(fetchMarkedReadBooksIds, ""+bookID4+"");
			
			fetchMarkedReadBooks = FetchMarkedReadBooks.fetchMarkedReadBooks(0, 100, userToken, "sdfgh456", deviceType);
			Validation.responseNOTKeyValidation_key(fetchMarkedReadBooks, ""+bookID3+"");
			Validation.responseNOTKeyValidation_key(fetchMarkedReadBooks, ""+bookID4+"");

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

