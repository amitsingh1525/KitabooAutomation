package com.hurix.api.runner;

import io.restassured.response.Response;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;

import com.hurix.api.externalAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class DIS_1964 {

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
	public static String user_Id;
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
		Log.initialization("Sprint33.1/DIS-1964");	
		//List<String> detail =  ExcelUtils.getuserDetails();
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			String excelPath="./testData/Sprint33.1/DIS-1964.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{DataFormatter formatter = new DataFormatter();
			environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			userName = formatter.formatCellValue(sheet.getRow(i).getCell(1));			
			password = formatter.formatCellValue(sheet.getRow(i).getCell(2));
			deviceT = formatter.formatCellValue(sheet.getRow(i).getCell(3));
			catlevel = formatter.formatCellValue(sheet.getRow(i).getCell(4));
			runY_N=formatter.formatCellValue(sheet.getRow(i).getCell(5));
			searchV2TEXT = formatter.formatCellValue(sheet.getRow(i).getCell(6));
			user_Id = formatter.formatCellValue(sheet.getRow(i).getCell(7));

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
			int user_Id1=0;
			if(environMent.equals("Staging") && userName.equals("Individual_stag@yopmail.com"))
			{user_Id1 = Integer.parseInt(""+user_Id+"");
			clientID = JDBC_Queries.getReader_user_ID(user_Id1, sqlhost, sqlUsername, sqlPassword);}

			else if(environMent.equals("Staging") && userName.equals("group_pushstag@yopmail.com"))
			{Log.info("****************here 2*********");
			user_Id1 = Integer.parseInt(""+user_Id+"");
			clientID =JDBC_Queries.getReader_user_ID(user_Id1, sqlhost, sqlUsername, sqlPassword);}

			else{clientID =JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);}

			String[] deviceT = {"IPAD","ANDROID","WINDOWS","PC","HTML5"};
			for(int i4=0; i4<=3; i4++)
			{
				Log.startTestCase("Authenticate");
				Log.info("detail : "+detail);
				Log.info("userName : "+userName);
				Log.info("password : "+password);
				Log.info("clientID : "+clientID);
				//clientID =JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);
				Log.info("ReaderKey : "+clientID);
				Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"514185",deviceT[i4]);
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

				if(environMent.equals("Staging") && userName.equals("Individual_stag@yopmail.com"))
				{client_Id=1426;}
				else if(environMent.equals("Staging") && userName.equals("group_pushstag@yopmail.com"))
				{Log.info("here in group user");client_Id=1283;}
				else{}
				consumerKey = JDBC_Queries.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
				consumerSecret = JDBC_Queries.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);


				Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"45616452",deviceT[i4]);
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
				bookID3 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[2]");			
				System.out.println("bookID3: "+bookID3);				
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
				Log.info("category1: "+category1);
				collectionName0 = fetchBookList_without_pagination.then().extract().path("bookList.book.collectionTitle[0]");
				System.out.println("collectionName0: "+collectionName0);
				catname = ExtractCategory.extractCategory(category1);
				System.out.println("catname: " +catname);
				archiveDate1 = fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[0]");
				System.out.println("archiveDate1:"+archiveDate1);
				archiveDate2=fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[1]");
				System.out.println("archiveDate2:"+archiveDate2);	


				Response fetchbookcount = FetchBookCount.fetchBookCount(userToken, "ad7zd67", deviceT[i4]);
				Validation.responseHeaderCodeValidation(fetchbookcount, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchbookcount, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchbookcount);
				totalbooks=fetchbookcount.then().extract().path("totalbooks");
				Validation.responseINTEGERKeyAndValue(fetchbookcount, "totalbooks", totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 2);
				//Validation.responseINTEGERKeyAndValue(fetchbookcount, "totalbooks", 5);

				Log.info("searchV2TEXT : "+searchV2TEXT);
				Response searchv2 = SearchV2.searchV2(searchV2TEXT, userToken, "dwsd323", deviceT[i4]);
				Validation.responseHeaderCodeValidation(searchv2, HttpStatus.SC_OK);
				//Validation.responseCodeValidation1(searchv2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(searchv2);
				Validation.responseKeyValidation_key(searchv2,"searchResult");
				Validation.responseKeyValidation_key(searchv2,"_id");
				Validation.responseKeyValidation_key(searchv2,"_index");
				Validation.responseKeyValidation_key(searchv2,"ISBN");
				Validation.responseKeyValidation_key(searchv2,"bookTitle");
				Validation.responseKeyValidation_key(searchv2,"description");
				Validation.responseKeyValidation_key(searchv2,"_type");
				Validation.responseKeyValidation_key(searchv2,"searchResult");


				Response books = ClientUserID_books.clientUserID_books(consumerKey, consumerSecret, searchV2TEXT, ""+clientUserID+"");
				Validation.responseHeaderCodeValidation(books, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(books, HttpStatus.SC_OK);
				Validation.responseTimeValidation(books);
				Validation.responseKeyValidation_key(books, "id");
				Validation.responseKeyValidation_key(books, "isbn");
				Validation.responseKeyValidation_key(books, "title");
				Validation.responseKeyValidation_key(books, "thumbURL");
				Validation.responseKeyValidation_key(books, "author");
				Validation.responseKeyValidation_key(books, "createdOn");
				Validation.responseKeyValidation_key(books, "description");
				Validation.responseKeyValidation_key(books, "category");
				Validation.responseKeyValidation_key(books, "categoryList");
				Validation.responseKeyValidation_key(books, "bookActive");


				Response categoryBookListv1 = CategoryBookListV1.categoryBookListV1(category1, userToken, "das3232", deviceT[i4], bookID1, catlevel, sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListv1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListv1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListv1);
				Validation.responseKeyValidation_key(categoryBookListv1,"archiveDate");
				Validation.responseKeyValidation_key(categoryBookListv1,"category");
				Validation.responseKeyValidation_key(categoryBookListv1,"assetType");
				Validation.responseKeyValidation_key(categoryBookListv1,"assignedOn");
				Validation.responseKeyValidation_key(categoryBookListv1,"author");
				Validation.responseKeyValidation_key(categoryBookListv1,"bookId");
				Validation.responseKeyValidation_key(categoryBookListv1,"bookLikeCount");
				Validation.responseKeyValidation_key(categoryBookListv1,"categoryIdList");
				Validation.responseKeyValidation_key(categoryBookListv1,"categoryList");
				Validation.responseKeyValidation_key(categoryBookListv1,"formats");
				Validation.responseKeyValidation_key(categoryBookListv1,"pages");
				Validation.responseKeyValidation_key(categoryBookListv1,"readingPercentage");
				Validation.responseKeyValidation_key(categoryBookListv1,"reflow");
				Validation.responseKeyValidation_key(categoryBookListv1,"thumbURL");
				Validation.responseKeyValidation_key(categoryBookListv1,"version");
				Validation.responseKeyValidation_key(categoryBookListv1,"isbn");
				Validation.responseKeyValidation_key(categoryBookListv1,"id");
				Validation.responseKeyValidation_key(categoryBookListv1,"title");


				Response CategoryBookListV2Res = CategoryBookListV2.categoryBookListV2(category1,userToken,"56454", deviceT[i4],bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Log.info("category1 : "+category1);
				Validation.responseHeaderCodeValidation(CategoryBookListV2Res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(CategoryBookListV2Res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(CategoryBookListV2Res);
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"archiveDate");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"category");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"assetType");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"assignedOn");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"author");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"bookId");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"bookLikeCount");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"categoryIdList");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"categoryList");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"formats");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"pages");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"readingPercentage");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"reflow");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"thumbURL");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"version");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"isbn");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"id");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"title");


				Response Booklist = BookList.bookList(userToken,"56454",deviceT[i4]);
				Validation.responseHeaderCodeValidation(Booklist, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist);
				Validation.responseKeyValidation_key(Booklist,"archiveDate");
				Validation.responseKeyValidation_key(Booklist,"assetType");
				Validation.responseKeyValidation_key(Booklist,"assignedOn");
				Validation.responseKeyValidation_key(Booklist,"bookCode");
				Validation.responseKeyValidation_key(Booklist,"bookId");
				Validation.responseKeyValidation_key(Booklist,"category");
				Validation.responseKeyValidation_key(Booklist,"categoryIdList");
				Validation.responseKeyValidation_key(Booklist,"categoryList");
				Validation.responseKeyValidation_key(Booklist,"readingPercentage");
				Validation.responseKeyValidation_key(Booklist,"pages");
				Validation.responseKeyValidation_key(Booklist,"title");
				Validation.responseKeyValidation_key(Booklist,"reflow");
				Validation.responseKeyValidation_key(Booklist,"version");


				Response refreshBookListres = RefreshBookList.refreshBookList(userToken,"56496",deviceT[i4]);
				Validation.responseHeaderCodeValidation(refreshBookListres, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(refreshBookListres, HttpStatus.SC_OK);
				Validation.responseTimeValidation(refreshBookListres);
				Validation.responseKeyValidation_key(refreshBookListres, "archiveDate");
				Validation.responseKeyValidation_key(refreshBookListres, "assetType");
				Validation.responseKeyValidation_key(refreshBookListres, "assignedOn");
				Validation.responseKeyValidation_key(refreshBookListres, "author");
				Validation.responseKeyValidation_key(refreshBookListres, "bookActive");
				Validation.responseKeyValidation_key(refreshBookListres, "bookCode");
				Validation.responseKeyValidation_key(refreshBookListres, "bookDisLikeCount");
				Validation.responseKeyValidation_key(refreshBookListres, "bookId");
				Validation.responseKeyValidation_key(refreshBookListres, "bookLikeCount");
				Validation.responseKeyValidation_key(refreshBookListres, "category");
				Validation.responseKeyValidation_key(refreshBookListres, "categoryIdList");
				Validation.responseKeyValidation_key(refreshBookListres, "categoryList");
				Validation.responseKeyValidation_key(refreshBookListres, "collectionID");
				Validation.responseKeyValidation_key(refreshBookListres, "collectionTitle");
				Validation.responseKeyValidation_key(refreshBookListres, "collectionType");
				Validation.responseKeyValidation_key(refreshBookListres, "ebookID");
				Validation.responseKeyValidation_key(refreshBookListres, "expiryDate");
				Validation.responseKeyValidation_key(refreshBookListres, "favourite");


				Response v1refreshBookList_res  =V1refreshBookList.v1refreshBookList(archiveDate1,"NEW","UPDATE",bookID1,bookID2,userToken,"56454", deviceT[i4],clientID);
				Log.info("archiveDate1 : "+archiveDate1);
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
				operation0=v1refreshBookList_res.then().extract().path("bookList.book.operation[0]");
				operation1=v1refreshBookList_res.then().extract().path("bookList.book.operation[1]");
				Validation.responseKeyAndValue(v1refreshBookList_res, "operation", operation0);
				Validation.responseKeyAndValue(v1refreshBookList_res, "operation", operation1);


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList_res = MultiCategoryBookList.multiCategoryBookList(catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT[i4]);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList_res);
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "totalbooks");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "archiveDate");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "assetType");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "bookCode");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "bookId");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "category");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "categoryIdList");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "categoryList");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "collectionID");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "collectionThumbnail");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "collectionType");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "ebookID");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "favourite");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "id");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "isbn");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "title");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "keywords");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "pages");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "readingPercentage");}



				if(catlevel.contains ("1")){}	
				else{Response MultiCategoryCollection_BookList=MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel, bookID1, sqlhost,sqlUsername,sqlPassword, userToken, "4524242",deviceT[i4], collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollection_BookList, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollection_BookList, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollection_BookList);
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "totalbooks");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "archiveDate");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "assetType");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "bookCode");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "bookId");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "category");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "categoryIdList");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "categoryList");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "collectionID");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "collectionThumbnail");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "collectionType");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "ebookID");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "favourite");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "id");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "isbn");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "title");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "keywords");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "pages");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "readingPercentage");}


				Response fetchCategoriesCollectionsres =FetchCategoriesCollections.fetchCategoriesCollections(userToken, "5489989",deviceT[i4]);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsres, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsres, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsres);
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"categories");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"name");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"numberOfBooks");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"collections");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"name");



				Response fetchCategoriesCollectionsBooksres =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks(userToken, "5489989",deviceT[i4],category1,collectionName0,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooksres, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooksres,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooksres);
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "archiveDate");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "assetType");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "author");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "bookActive");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "bookCode");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "bookId");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "category");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "categoryIdList");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "categoryList");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "collectionID");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "collectionThumbnail");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "collectionTitle");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "collectionType");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "description");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooksres, "ebookID");



				Response markAsFavourite_res = MarkAsFavourite.markAsFavourite(bookID1,userToken,"45564595",deviceT[i4]);
				BookID_mark1 = bookID1;
				Validation.responseHeaderCodeValidation(markAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(markAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(markAsFavourite_res);


				Response markAsFavourite_res1 = MarkAsFavourite.markAsFavourite(bookID2,userToken,"45564595",deviceT[i4]);
				BookID_mark2 = bookID2;
				Validation.responseHeaderCodeValidation(markAsFavourite_res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(markAsFavourite_res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(markAsFavourite_res1);



				Response FetchFavouriteBooks_res = FetchFavouriteBooks.fetchFavouriteBooks(userToken,"45564595",deviceT[i4]);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res);
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "archiveDate");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "assetType");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "assignedOn");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "author");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "bookActive");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "bookCode");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "bookDisLikeCount");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "category");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "categoryIdList");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "categoryList");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "collectionID");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "collectionThumbnail");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "collectionTitle");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "collectionType");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "ebookID");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "id");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "isbn");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "like");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "pages");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "readingPercentage");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "reflow");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "title");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "version");
				Validation.responseINTEGERKeyAndValue(FetchFavouriteBooks_res, "id", BookID_mark1);
				Validation.responseINTEGERKeyAndValue(FetchFavouriteBooks_res, "id", BookID_mark2);


				Response unMarkAsFavourite_res = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark1,userToken,"45564595",deviceT[i4]);
				Validation.responseHeaderCodeValidation(unMarkAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(unMarkAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(unMarkAsFavourite_res);


				Response unMarkAsFavourite_res1 = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark2,userToken,"45564595",deviceT[i4]);
				Validation.responseHeaderCodeValidation(unMarkAsFavourite_res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(unMarkAsFavourite_res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(unMarkAsFavourite_res1);


				Response books_OAuthres = Books_OAuth.books_OAuth(consumerKey, consumerSecret);
				Validation.responseHeaderCodeValidation(books_OAuthres, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(books_OAuthres, HttpStatus.SC_OK);
				Validation.responseTimeValidation(books_OAuthres);
				Validation.responseKeyValidation_key(books_OAuthres, "id");
				Validation.responseKeyValidation_key(books_OAuthres, "isbn");
				Validation.responseKeyValidation_key(books_OAuthres, "title");
				Validation.responseKeyValidation_key(books_OAuthres, "author");
				Validation.responseKeyValidation_key(books_OAuthres, "createdOn");
				Validation.responseKeyValidation_key(books_OAuthres, "description");
				Validation.responseKeyValidation_key(books_OAuthres, "category");
				Validation.responseKeyValidation_key(books_OAuthres, "categoryList");
				Validation.responseKeyValidation_key(books_OAuthres, "bookActive");


				Response downloadBookForANDROID_offline=null;
				if(environMent.equals("Staging") && userName.equals("Individual_stag@yopmail.com"))
				{downloadBookForANDROID_offline = DownloadBook.downloadBook(userToken,"ds9465","FIXED_EPUB_IMAGE",bookID1,"offline");}
				else{downloadBookForANDROID_offline = DownloadBook.downloadBook(userToken,"ds9465",deviceT[i4],bookID1,"offline");}		
				//Validation.responseCodeValidation1(downloadBookForANDROID_offline, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForANDROID_offline);
				//Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"timestamp");


				Response downloadBookForANDROID_online =null;
				if(environMent.equals("Staging") && userName.equals("Individual_stag@yopmail.com"))
				{downloadBookForANDROID_online = DownloadBook.downloadBook(userToken,"ds9465","FIXED_EPUB_IMAGE",bookID1,"online");}
				else {downloadBookForANDROID_online = DownloadBook.downloadBook(userToken,"ds9465",deviceT[i4],bookID1,"online");}
				Validation.responseHeaderCodeValidation(downloadBookForANDROID_online, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForANDROID_online, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForANDROID_online);
				//Validation.responseKeyValidation_key(downloadBookForANDROID_online,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForANDROID_online,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForANDROID_online,"timestamp");

				/*if(environMent.equals("Staging") && userName.equals("Individual_stag@yopmail.com")){
				downloadBookForANDROID_offline = DownloadBook.downloadBook(userToken,"ds9465","HTML5",bookID3,"offline");}	
			downloadBookForANDROID_offline = DownloadBook.downloadBook(userToken,"ds9465","HTML5",bookID1,"offline");
			Validation.responseCodeValidation1(downloadBookForANDROID_offline, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBookForANDROID_offline);
			//Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"fileSize");
			Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"responseMsg");
			Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"timestamp");*/


				if(environMent.equals("Staging") && userName.equals("Individual_stag@yopmail.com")){
					downloadBookForANDROID_offline = DownloadBook.downloadBook(userToken,"ds9465","HTML5",bookID3,"online");}
				downloadBookForANDROID_online = DownloadBook.downloadBook(userToken,"ds9465","HTML5",bookID3,"online");
				Validation.responseHeaderCodeValidation(downloadBookForANDROID_online, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForANDROID_online, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForANDROID_online);
				Validation.responseKeyValidation_key(downloadBookForANDROID_online,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForANDROID_online,"timestamp");


				assetType = fetchBookList_without_pagination.then().extract().path("bookList.book.assetType[1]");
				Log.info("assetType: "+assetType);
				assetType = fetchBookList_without_pagination.then().extract().path("bookList.book.assetType[2]");
				Log.info("assetType: "+assetType);



				//Response bulkDownloadBook = null;
				Response bulkDownloadBook= BulkDownloadBook.bulkDownloadBook(userToken, "GHgah242", deviceT[i4], bookID1, bookID2,bookID3,"offline");
				bulkDownloadBook=BulkDownloadBook.bulkDownloadBook(userToken, "GHgah242", "ANDROID", bookID1, bookID2,bookID3,"offline");
				Validation.responseHeaderCodeValidation(bulkDownloadBook, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(bulkDownloadBook, HttpStatus.SC_OK);
				Validation.responseTimeValidation(bulkDownloadBook);
				Validation.responseKeyValidation_key(bulkDownloadBook,"responseMsg");
				Validation.responseKeyValidation_key(bulkDownloadBook,"timestamp");

			}
			}

			}
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			//exp.printStackTrace();
		}
		Log.endTestCase("End");
	}
}

