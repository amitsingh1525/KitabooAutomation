package com.hurix.api.runner;

import io.restassured.response.Response;
import java.sql.SQLException;
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

public class DIS_1765_WilloPin {
	
	static long startDate ;
	static long startIndex = 0;
	static long endIndex = 100;
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
	static int userID1;
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
	static String firstName;
	static String lastName;
	static String userName1;
	static String email;
	static String searchV2TEXT;
	static String deviceLimit;
	static String type1;
	//public static String consumerKey=ExcelUtils.Consumer_key;
	//public static String consumerSecret=ExcelUtils.secret_key;

	public static void   main(String []args) throws SQLException, JSONException{
		Log.initialization("Sprint33.1/DIS-1765_Willopin");	
		//List<String> detail =  ExcelUtils.getuserDetails();
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			String excelPath="./testData/Sprint33.1/DIS-1765_Willopin.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{DataFormatter formatter = new DataFormatter();
			environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			userName = formatter.formatCellValue(sheet.getRow(i).getCell(1));			
			password = formatter.formatCellValue(sheet.getRow(i).getCell(2));				
			catlevel = formatter.formatCellValue(sheet.getRow(i).getCell(3));
			deviceT= formatter.formatCellValue(sheet.getRow(i).getCell(4));
			runY_N = formatter.formatCellValue(sheet.getRow(i).getCell(5));
			//pinKey = formatter.formatCellValue(sheet.getRow(i).getCell(6));
			//pinPair = formatter.formatCellValue(sheet.getRow(i).getCell(7));
			searchV2TEXT = formatter.formatCellValue(sheet.getRow(i).getCell(6));
			deviceLimit = formatter.formatCellValue(sheet.getRow(i).getCell(7));
			type1 = formatter.formatCellValue(sheet.getRow(i).getCell(8));
			clientID = formatter.formatCellValue(sheet.getRow(i).getCell(9));
			userToken = formatter.formatCellValue(sheet.getRow(i).getCell(10));

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

			//if(environMent.equals("Staging") &&  userName.contains(""))
			//clientID = JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			//String client_Id1 = formatter.formatCellValue(sheet.getRow(i).getCell(9));
			//int client_Id = Integer.parseInt(""+client_Id1+"");

			String[] deviceT = {"IPAD","ANDROID","WINDOWS","PC","HTML5"};
			for(int i4=0; i4<=4 ;i4++)
			{Log.startTestCase("Authenticate.device:"+deviceT[i4]+"");
			Log.info("TotalRows : "+sheet.getLastRowNum());
			Log.info("detail : "+detail);
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			
			
			//String reader_key =null;
			
			clientID = JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			Log.info("clientID : "+clientID);
			
			if(environMent.equals("Staging") &&  userName.contains("ent_lear_cat2@yopmail.com")){}			
			else{
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
			Log.endTestCase("End");}


			if(environMent.equals("Staging") &&  userName.contains("ent_lear_cat2@yopmail.com"))
			{client_Id = 1337;
			consumerKey = JDBC_Queries.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			consumerSecret =JDBC_Queries.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);}else{}
			
			consumerKey = JDBC_Queries.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			consumerSecret =JDBC_Queries.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);

			Log.startTestCase("GeneratePin");
			Response generatePin = GeneratePin.generatePin(userToken,"15331",deviceT[i4]);
			Validation.responseHeaderCodeValidation(generatePin, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(generatePin, HttpStatus.SC_OK);
			Validation.responseTimeValidation(generatePin);
			pinKey = generatePin.then().extract().path("pinKey");
			Log.info("pinKey : "+pinKey);
			Validation.responseKeyAndValue(generatePin, "pinKey", pinKey);
			System.out.println("generatePin : "+generatePin);			
			pinPair = generatePin.then().extract().path("pinPair");			
			Log.info("pinPair : "+pinPair);
			Validation.responseKeyAndValue(generatePin, "pinPair", pinPair);

			//userID=95744541;
			if(environMent.equals("QC")){clientID = JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);}
			//clientID = formatter.formatCellValue(sheet.getRow(i).getCell(9));

			Response validatePin = ValidatePin.validatePin(clientID,pinKey,pinPair,"15331",deviceT[i4]);
			Log.info("clientID : "+clientID);
			Log.info("detail : "+detail);
			Validation.responseHeaderCodeValidation(validatePin, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validatePin, HttpStatus.SC_OK);
			Validation.responseTimeValidation(validatePin);
			//System.out.println("validatePin : "+validatePin);
			String userToken_re = validatePin.then().extract().path("userToken");
			Log.info("userToken_re: "+userToken_re);
			userID1=validatePin.then().extract().path("user.id");
			Log.info("userID1: "+userID1);
			clientUserID=validatePin.then().extract().path("user.clientUserID");
			Log.info("clientUserID: "+clientUserID);
			firstName=validatePin.then().extract().path("user.firstName");
			Log.info("firstName: "+firstName);
			lastName=validatePin.then().extract().path("user.lastName");
			Log.info("lastName: "+lastName);
			userName1=validatePin.then().extract().path("user.userName");
			Log.info("userName1: "+userName1);
			email=validatePin.then().extract().path("user.email");
			Log.info("email: "+email);


			Log.startTestCase("GeneratePin_OAuth");
			generatePin=GeneratePin_ext.generatePin_ext(consumerKey, consumerSecret, clientUserID);
			Validation.responseHeaderCodeValidation(generatePin, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(generatePin, HttpStatus.SC_OK);
			Validation.responseTimeValidation(generatePin);
			pinKey = generatePin.then().extract().path("pinKey");
			Log.info("pinKey : "+pinKey);
			Validation.responseKeyAndValue(generatePin, "pinKey", pinKey);
			System.out.println("generatePin : "+generatePin);			
			pinPair = generatePin.then().extract().path("pinPair");			
			Log.info("pinPair : "+pinPair);
			Validation.responseKeyAndValue(generatePin, "pinPair", pinPair);

			validatePin = ValidatePin.validatePin(clientID,pinKey,pinPair,"15331",deviceT[i4]);
			Log.info("clientID : "+clientID);
			Log.info("detail : "+detail);
			Validation.responseHeaderCodeValidation(validatePin, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validatePin, HttpStatus.SC_OK);
			Validation.responseTimeValidation(validatePin);
			//System.out.println("validatePin : "+validatePin);
			userToken_re = validatePin.then().extract().path("userToken");
			Log.info("userToken_re: "+userToken_re);
			userID1=validatePin.then().extract().path("user.id");
			Log.info("userID1: "+userID1);
			clientUserID=validatePin.then().extract().path("user.clientUserID");
			Log.info("clientUserID: "+clientUserID);
			firstName=validatePin.then().extract().path("user.firstName");
			Log.info("firstName: "+firstName);
			lastName=validatePin.then().extract().path("user.lastName");
			Log.info("lastName: "+lastName);
			userName1=validatePin.then().extract().path("user.userName");
			Log.info("userName1: "+userName1);
			email=validatePin.then().extract().path("user.email");
			Log.info("email: "+email);


			Response invalidValidate = ValidatePin.validatePin(clientID,pinKey,pinPair+"12","15331",deviceT[i4]);
			Log.info("InValid PinPair 12 is extend at the end");
			Validation.responseHeaderCodeValidation(invalidValidate, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(invalidValidate, HttpStatus.SC_BAD_REQUEST);
			Validation.responseTimeValidation(invalidValidate);
			Validation.responseINTEGERKeyAndValue(invalidValidate, "PIN_PAIR", 101);

			invalidValidate = ValidatePin.validatePin(clientID,pinKey+"12",pinPair,"15331",deviceT[i4]);
			Log.info("InValid PinKey 12 is extend at the end");
			Validation.responseHeaderCodeValidation(invalidValidate, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(invalidValidate, HttpStatus.SC_BAD_REQUEST);
			Validation.responseTimeValidation(invalidValidate);
			Validation.responseINTEGERKeyAndValue(invalidValidate, "PIN", 101);

			invalidValidate = ValidatePin.validatePin("cHJvZi1raXRhYm9v",pinKey,pinPair,"15331",deviceT[i4]);
			Log.info("InValid clientID check: cHJvZi1raXRhYm9v");
			Validation.responseHeaderCodeValidation(invalidValidate, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(invalidValidate, HttpStatus.SC_UNAUTHORIZED);
			Validation.responseTimeValidation(invalidValidate);
			Validation.responseINTEGERKeyAndValue(invalidValidate, "PIN_API", 401);

			invalidValidate = ValidatePin.validatePin("VEFGVEM=",pinKey,pinPair,"15331",deviceT[i4]);
			Log.info("InValid clientID  check: VEFGVEM=");
			Validation.responseHeaderCodeValidation(invalidValidate, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(invalidValidate, HttpStatus.SC_BAD_REQUEST);
			Validation.responseTimeValidation(invalidValidate);
			Validation.responseINTEGERKeyAndValue(invalidValidate, "clientID", 101 );


			Response fetchbooklist=FetchBookList.fetchBookList_without_pagination(userToken_re, "rwds32323", deviceT[i4]);
			Validation.responseHeaderCodeValidation(fetchbooklist, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchbooklist, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchbooklist);
			bookID1 = fetchbooklist.then().extract().path("bookList.book.id[0]");
			System.out.println("bookID_1: "+bookID1);
			bookID2 = fetchbooklist.then().extract().path("bookList.book.id[1]");
			System.out.println("bookID: "+bookID2);
			bookID3 = fetchbooklist.then().extract().path("bookList.book.id[2]");
			System.out.println("bookID: "+bookID3);
			title = fetchbooklist.then().extract().path("bookList.book.title[0]");
			System.out.println("title: "+title);
			isbn = fetchbooklist.then().extract().path("bookList.book.isbn[1]");
			System.out.println("isbn: "+isbn);
			ebookID1 = fetchbooklist.then().extract().path("bookList.book.ebookID[0]");
			System.out.println("ebookID: "+ebookID1);
			assetType = fetchbooklist.then().extract().path("bookList.book.assetType[1]");
			System.out.println("assetType: "+assetType);
			category1 = fetchbooklist.then().extract().path("bookList.book.category[1]");
			System.out.println("category1: "+category1);
			collectionName1 = fetchbooklist.then().extract().path("bookList.book.collectionTitle[1]");
			System.out.println("collectionName1: "+collectionName1);
			catname = ExtractCategory.extractCategory(category1);
			System.out.println("catname: " +catname);
			String archiveDate = fetchbooklist.then().extract().path("bookList.book.archiveDate[1]");
			System.out.println("archiveDate:"+archiveDate);


			Response bookdetails = Bookdetails.bookdetails(archiveDate,userToken_re,"fg3cg23",deviceT[i4],bookID2,"");
			Log.info("detail : "+detail);
			Validation.responseHeaderCodeValidation(bookdetails, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(bookdetails, HttpStatus.SC_OK);
			Validation.responseTimeValidation(bookdetails);
			Validation.responseKeyValidation_key(bookdetails,"archiveDate");
			Validation.responseKeyValidation_key(bookdetails,"assetType");
			Validation.responseKeyValidation_key(bookdetails,"author");
			Validation.responseKeyValidation_key(bookdetails,"bookCode");
			Validation.responseKeyValidation_key(bookdetails,"bookId");
			Validation.responseKeyValidation_key(bookdetails,"category");
			Validation.responseKeyValidation_key(bookdetails,"categoryIdList");
			Validation.responseKeyValidation_key(bookdetails,"categoryList");
			Validation.responseKeyValidation_key(bookdetails,"collectionID");
			Validation.responseKeyValidation_key(bookdetails,"collectionThumbnail");
			Validation.responseKeyValidation_key(bookdetails,"collectionTitle");
			Validation.responseKeyValidation_key(bookdetails,"collectionType");


			int deviceLimit1 = Integer.parseInt(""+deviceLimit+"");
			int type = Integer.parseInt(""+type1+"");
			Response Orderv2 = Order.order_withBookID(EpochTime.current(), consumerKey,consumerSecret,bookID1,1,deviceLimit1,type);
			Log.info("OrderNumber : " +EpochTime.current());
			Log.info("detail : "+detail);
			Log.info("consumerKey : " +consumerKey);
			Log.info("consumerSecret : " +consumerSecret);
			Log.info("bookID1 : " +bookID1);
			Validation.responseHeaderCodeValidation(Orderv2, HttpStatus.SC_OK);
			Validation.responseTimeValidation(Orderv2);


			Response fetchbookcount = FetchBookCount.fetchBookCount(userToken_re, "ad7zd67", deviceT[i4]);
			Validation.responseHeaderCodeValidation(fetchbookcount, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchbookcount, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchbookcount);

			Response refreshBookList = RefreshBookList.refreshBookList(userToken_re, "gedsff87f7s", deviceT[i4]);
			Validation.responseHeaderCodeValidation(refreshBookList, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(refreshBookList, HttpStatus.SC_OK);
			Validation.responseTimeValidation(refreshBookList);

			Response getsecuredURL =  GetSecureURL.getSecureURL(userToken_re, "sagsg76767", deviceT[i4], 3);
			Validation.responseHeaderCodeValidation(getsecuredURL, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getsecuredURL, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getsecuredURL);

			Response markAsFav= MarkAsFavourite.markAsFavourite(bookID1,userToken_re,"8742685",deviceT[i4]);
			Validation.responseHeaderCodeValidation(markAsFav, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(markAsFav, HttpStatus.SC_OK);
			Validation.responseTimeValidation(markAsFav);
			Validation.responseKeyValidation_key(markAsFav, "OK");
			System.out.println("markAsFav : "+markAsFav);

			Response FetchFavouriteBooks_res = FetchFavouriteBooks.fetchFavouriteBooks(userToken_re,"8742685",deviceT[i4]);
			Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(FetchFavouriteBooks_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(FetchFavouriteBooks_res);
			Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "OK");
			Validation.responseKeyValidation_key(FetchFavouriteBooks_res, ""+bookID1+"");
			Validation.responseKeyValidation_key(FetchFavouriteBooks_res,"description");


			Response unMarkFav=UnMarkAsFavourite.unMarkAsFavourite(bookID1,userToken_re,"8742685",deviceT[i4]);
			Validation.responseHeaderCodeValidation(unMarkFav, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(unMarkFav, HttpStatus.SC_OK);
			Validation.responseTimeValidation(unMarkFav);
			//Validation.responseKeyValidation_key(unMarkFav, "isbn");
			System.out.println("unMarkFav : "+unMarkFav);


			Response FetchFavouriteBooks_res1 = FetchFavouriteBooks.fetchFavouriteBooks(userToken_re,"8742685",deviceT[i4]);
			Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(FetchFavouriteBooks_res1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(FetchFavouriteBooks_res1);
			Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_res1, ""+bookID1+"");


			SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			Date date = new Date();  
			String  time=formatter1.format(date);
			Response savesessionHistory = SaveSessionHistory.saveSessionHistory(userToken_re,"dshdg63236",deviceT[i4],bookID1,time);
			Log.info("TIME : "+time);
			Validation.responseHeaderCodeValidation(savesessionHistory, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(savesessionHistory, HttpStatus.SC_OK);
			Validation.responseTimeValidation(savesessionHistory);



			Response fetchRecentlyViewed = FetchRecentlyViewed.fetchRecentlyViewed_without_pagi(userToken_re,"fscssx67777",deviceT[i4]);
			Validation.responseHeaderCodeValidation(fetchRecentlyViewed, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchRecentlyViewed, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchRecentlyViewed);
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "archiveDate");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "assetType");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "assignedOn");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "author");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "bookActive");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "bookCode");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "bookId");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "category");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "categoryIdList");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "categoryList");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "collectionID");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "collectionTitle");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "collectionType");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "ebookID");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "expiryDate");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "isbn");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "pages");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "readingPercentage");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "encryption");

			fetchRecentlyViewed = FetchRecentlyViewed.fetchRecentlyViewed_with_pagi(0, 10, userToken_re,"wertg123456543",deviceT[i4]);
			Validation.responseHeaderCodeValidation(fetchRecentlyViewed, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchRecentlyViewed, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchRecentlyViewed);
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "archiveDate");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "assetType");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "assignedOn");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "author");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "bookActive");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "bookCode");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "bookId");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "category");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "categoryIdList");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "categoryList");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "collectionID");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "collectionTitle");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "collectionType");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "ebookID");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "expiryDate");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "isbn");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "pages");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "readingPercentage");
			Validation.responseKeyValidation_key(fetchRecentlyViewed, "encryption");

			Response fethcatcollection = FetchCategoriesCollections.fetchCategoriesCollections(userToken_re,"shjh5637456",deviceT[i4]);
			Validation.responseHeaderCodeValidation(fethcatcollection, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fethcatcollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fethcatcollection);
			Validation.responseKeyValidation_key(fethcatcollection,"categories");
			Validation.responseKeyValidation_key(fethcatcollection,"name");
			Validation.responseKeyValidation_key(fethcatcollection,"numberOfBooks");
			Validation.responseKeyValidation_key(fethcatcollection,"collections");
			Validation.responseKeyValidation_key(fethcatcollection,"name");

			Response fetchCategoriesCollectionsBooks = FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks(userToken_re,"fs424",deviceT[i4],category1,collectionName1,bookID2,sqlhost,sqlUsername,sqlPassword,catlevel);
			Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks,HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchCategoriesCollectionsBooks);
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "archiveDate");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "assetType");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "author");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "bookActive");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "bookCode");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "bookId");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "category");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "categoryIdList");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "categoryList");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "collectionID");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "collectionThumbnail");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "collectionTitle");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "collectionType");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "description");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsBooks, "ebookID");

			Response categories = CategoriesV1.categoriesV1(userToken_re, "s4324", deviceT[i4]);
			Validation.responseHeaderCodeValidation(categories, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(categories,HttpStatus.SC_OK);
			Validation.responseTimeValidation(categories);
			Validation.responseKeyValidation_key(categories, "id");
			Validation.responseKeyValidation_key(categories, "name");
			Validation.responseKeyValidation_key(categories, "totalCategories");
			totalCategories=categories.then().extract().path("totalCategories");
			categoriesname=categories.then().extract().path("categories[0].name");
			Validation.responseKeyAndValue(categories, "name",categoriesname);

			categories = CategoriesV2.categoriesV2(userToken_re, "whsdj4637", deviceT[i4]);
			Validation.responseHeaderCodeValidation(categories, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(categories,HttpStatus.SC_OK);
			Validation.responseTimeValidation(categories);
			Validation.responseKeyValidation_key(categories, "id");
			Validation.responseKeyValidation_key(categories, "name");
			Validation.responseKeyValidation_key(categories, "totalCategories");
			totalCategories=categories.then().extract().path("totalCategories");
			categoriesname=categories.then().extract().path("categories[0].name");
			Validation.responseKeyAndValue(categories, "name",categoriesname);


			Response categoryBookListV1 = CategoryBookListV1.categoryBookListV1(""+category1+"", userToken_re, "djsdh64374", deviceT[i4], bookID1, catlevel, sqlhost, sqlUsername, sqlPassword);
			Validation.responseHeaderCodeValidation(categoryBookListV1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(categoryBookListV1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(categoryBookListV1);
			Validation.responseKeyValidation_key(categoryBookListV1,"archiveDate");
			Validation.responseKeyValidation_key(categoryBookListV1,"category");
			Validation.responseKeyValidation_key(categoryBookListV1,"assetType");
			Validation.responseKeyValidation_key(categoryBookListV1,"assignedOn");
			Validation.responseKeyValidation_key(categoryBookListV1,"author");
			Validation.responseKeyValidation_key(categoryBookListV1,"bookId");
			Validation.responseKeyValidation_key(categoryBookListV1,"bookLikeCount");
			Validation.responseKeyValidation_key(categoryBookListV1,"categoryIdList");
			Validation.responseKeyValidation_key(categoryBookListV1,"categoryList");
			Validation.responseKeyValidation_key(categoryBookListV1,"formats");
			Validation.responseKeyValidation_key(categoryBookListV1,"pages");
			Validation.responseKeyValidation_key(categoryBookListV1,"readingPercentage");
			Validation.responseKeyValidation_key(categoryBookListV1,"reflow");
			Validation.responseKeyValidation_key(categoryBookListV1,"thumbURL");
			Validation.responseKeyValidation_key(categoryBookListV1,"version");
			Validation.responseKeyValidation_key(categoryBookListV1,"isbn");
			Validation.responseKeyValidation_key(categoryBookListV1,"id");
			Validation.responseKeyValidation_key(categoryBookListV1,"title");

			Response CategoryBookListV2Res = CategoryBookListV2.categoryBookListV2(""+category1+"",userToken_re,"56454",deviceT[i4],bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
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

			Response fetchcategorybooksV1 = FetchCategorybooksV1.fetchCategorybooksV1(""+category1+"", userToken_re,bookID1, catlevel, sqlhost, sqlUsername, sqlPassword);
			Validation.responseHeaderCodeValidation(fetchcategorybooksV1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchcategorybooksV1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchcategorybooksV1);
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "assetType");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "assignedOn");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "author");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "bookActive");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "category");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "categoryIdList");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "categoryList");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "collectionID");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "collectionThumbnail");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "collectionTitle");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "collectionType");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "description");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "ebookID");
			Validation.responseKeyValidation_key(fetchcategorybooksV1, "expiryDate");

			Log.info("searchV2TEXT : "+searchV2TEXT);
			Response searchv2 = SearchV2.searchV2(searchV2TEXT, userToken_re, "dwsd323", deviceT[i4]);
			Validation.responseHeaderCodeValidation(searchv2, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(searchv2, HttpStatus.SC_OK);
			Validation.responseTimeValidation(searchv2);
			Validation.responseKeyValidation_key(searchv2,"searchResult");
			Validation.responseKeyValidation_key(searchv2,"_id");
			Validation.responseKeyValidation_key(searchv2,"_index");
			Validation.responseKeyValidation_key(searchv2,"ISBN");
			Validation.responseKeyValidation_key(searchv2,"bookTitle");
			Validation.responseKeyValidation_key(searchv2,"description");
			Validation.responseKeyValidation_key(searchv2,"_type");
			Validation.responseKeyValidation_key(searchv2,"searchResult");
			//total=searchv2.then().extract().path("searchResult.hits.total");
			//Validation.responseKeyValidation_key(searchv2, "total");
			//Validation.responseISGreater("total", total, 1);

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

			books = Books_OAuth.books_OAuth(consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(books, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(books, HttpStatus.SC_OK);
			Validation.responseTimeValidation(books);
			Validation.responseKeyValidation_key(books, "id");
			Validation.responseKeyValidation_key(books, "isbn");
			Validation.responseKeyValidation_key(books, "title");
			Validation.responseKeyValidation_key(books, "author");
			Validation.responseKeyValidation_key(books, "createdOn");
			Validation.responseKeyValidation_key(books, "description");
			Validation.responseKeyValidation_key(books, "category");
			Validation.responseKeyValidation_key(books, "categoryList");
			Validation.responseKeyValidation_key(books, "bookActive");

			Response ListBooks = ListBooksV1_OAuth.listBooksV1_OAuth_without_pagi(consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(ListBooks, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(ListBooks, HttpStatus.SC_OK);
			Validation.responseTimeValidation(ListBooks);
			Validation.responseKeyValidation_key(ListBooks, "id");
			Validation.responseKeyValidation_key(ListBooks, "isbn");
			Validation.responseKeyValidation_key(ListBooks, "title");
			Validation.responseKeyValidation_key(ListBooks, "thumbURL");
			Validation.responseKeyValidation_key(ListBooks, "author");
			Validation.responseKeyValidation_key(ListBooks, "createdOn");
			Validation.responseKeyValidation_key(ListBooks, "category");
			Validation.responseKeyValidation_key(ListBooks, "categoryList");
			Validation.responseKeyValidation_key(ListBooks, "version");


			ListBooks= ListBooksV1_OAuth.listBooksV1_OAuth_With_Pagi(0,10,consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(ListBooks, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(ListBooks, HttpStatus.SC_OK);
			Validation.responseTimeValidation(ListBooks);
			Validation.responseKeyValidation_key(ListBooks, "id");
			Validation.responseKeyValidation_key(ListBooks, "isbn");
			Validation.responseKeyValidation_key(ListBooks, "title");
			Validation.responseKeyValidation_key(ListBooks, "thumbURL");
			Validation.responseKeyValidation_key(ListBooks, "author");
			Validation.responseKeyValidation_key(ListBooks, "createdOn");
			Validation.responseKeyValidation_key(ListBooks, "category");
			Validation.responseKeyValidation_key(ListBooks, "categoryList");
			Validation.responseKeyValidation_key(ListBooks, "version");

			ListBooks = ListBooks_OAuth.listBooks_OAuth_With_Pagi(0, 10, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(ListBooks, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(ListBooks, HttpStatus.SC_OK);
			Validation.responseTimeValidation(ListBooks);
			Validation.responseKeyValidation_key(ListBooks, "id");
			Validation.responseKeyValidation_key(ListBooks, "isbn");
			Validation.responseKeyValidation_key(ListBooks, "title");
			Validation.responseKeyValidation_key(ListBooks, "thumbURL");
			Validation.responseKeyValidation_key(ListBooks, "author");
			Validation.responseKeyValidation_key(ListBooks, "createdOn");
			Validation.responseKeyValidation_key(ListBooks, "category");
			Validation.responseKeyValidation_key(ListBooks, "categoryList");
			Validation.responseKeyValidation_key(ListBooks, "version");


			ListBooks = ListBooks_OAuth.listBooks_OAuth_withoutpagi(consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(ListBooks, HttpStatus.SC_OK);
			//Validation.responseCodeValidation1(ListBooks, HttpStatus.SC_OK);
			Validation.responseTimeValidation(ListBooks);
			Validation.responseKeyValidation_key(ListBooks, "id");
			Validation.responseKeyValidation_key(ListBooks, "isbn");
			Validation.responseKeyValidation_key(ListBooks, "title");
			Validation.responseKeyValidation_key(ListBooks, "thumbURL");
			Validation.responseKeyValidation_key(ListBooks, "author");
			Validation.responseKeyValidation_key(ListBooks, "createdOn");
			Validation.responseKeyValidation_key(ListBooks, "category");
			Validation.responseKeyValidation_key(ListBooks, "categoryList");
			Validation.responseKeyValidation_key(ListBooks, "version");
			
			validatePin = ValidatePin.validatePin(clientID,pinKey,pinPair,"15331",deviceT[i4]);
			Log.info("clientID : "+clientID);
			Log.info("detail : "+detail);
			Validation.responseHeaderCodeValidation(validatePin, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validatePin, HttpStatus.SC_BAD_REQUEST);
			Validation.responseTimeValidation(validatePin);
			Validation.responseINTEGERKeyAndValue(validatePin, "PIN", 101);
			Validation.responseKeyValidation_key(validatePin, "PIN is already consumed");}

			Log.startTestCase("GeneratePin_10Mins");
			String deviceT1="IPAD";
			Response generatePin=GeneratePin.generatePin(userToken,"15331",deviceT1);
			Log.info("***************GeneratePin for 10 mins Check**********");
			Validation.responseHeaderCodeValidation(generatePin, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(generatePin, HttpStatus.SC_OK);
			Validation.responseTimeValidation(generatePin);
			pinKey = generatePin.then().extract().path("pinKey");
			Log.info("pinKey : "+pinKey);
			Validation.responseKeyAndValue(generatePin, "pinKey", pinKey);
			System.out.println("generatePin : "+generatePin);			
			pinPair = generatePin.then().extract().path("pinPair");			
			Log.info("pinPair : "+pinPair);
			Validation.responseKeyAndValue(generatePin, "pinPair", pinPair);

			//userID=95744541;
			
			clientID = JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			//clientID = formatter.formatCellValue(sheet.getRow(i).getCell(9));

			Log.info("*********validate for 10 mins Check************");
			Thread.sleep(600000);

			Response validatePin1 = ValidatePin.validatePin(clientID,pinKey,pinPair,"15331",deviceT1);
			Log.info("clientID : "+clientID);
			Log.info("detail : "+detail);
			Validation.responseHeaderCodeValidation(validatePin1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validatePin1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(validatePin1);
			//System.out.println("validatePin : "+validatePin);
			//userToken_re = validatePin1.then().extract().path("userToken");
			//Log.info("userToken_re: "+userToken_re);
			userID1=validatePin1.then().extract().path("user.id");
			Log.info("userID1: "+userID1);
			clientUserID=validatePin1.then().extract().path("user.clientUserID");
			Log.info("clientUserID: "+clientUserID);
			firstName=validatePin1.then().extract().path("user.firstName");
			Log.info("firstName: "+firstName);
			lastName=validatePin1.then().extract().path("user.lastName");
			Log.info("lastName: "+lastName);
			userName1=validatePin1.then().extract().path("user.userName");
			Log.info("userName1: "+userName1);
			email=validatePin1.then().extract().path("user.email");
			Log.info("email: "+email);


			Log.startTestCase("GeneratePin_ext_15Mins");
			generatePin=GeneratePin_ext.generatePin_ext(consumerKey, consumerSecret, clientUserID);
			Log.info("***********GeneratePin for 15 mins Check****************");
			Validation.responseHeaderCodeValidation(generatePin, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(generatePin, HttpStatus.SC_OK);
			Validation.responseTimeValidation(generatePin);
			pinKey = generatePin.then().extract().path("pinKey");
			Log.info("pinKey : "+pinKey);
			Validation.responseKeyAndValue(generatePin, "pinKey", pinKey);
			System.out.println("generatePin : "+generatePin);			
			pinPair = generatePin.then().extract().path("pinPair");			
			Log.info("pinPair : "+pinPair);
			Validation.responseKeyAndValue(generatePin, "pinPair", pinPair);


			Log.info("***************validatePin15 for 15 mins Check*********************");
			Thread.sleep(910000);
			Response validatePin15 = ValidatePin.validatePin(clientID,pinKey,pinPair,"15331",deviceT1);
			Log.info("clientID : "+clientID);
			Log.info("detail : "+detail);
			Log.info("15 mins more : 910000");
			Validation.responseHeaderCodeValidation(validatePin15, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validatePin15, HttpStatus.SC_BAD_REQUEST);
			Validation.responseTimeValidation(validatePin15);
			Validation.responseINTEGERKeyAndValue(validatePin15, "PIN", 101);
			
			}
		}
		}catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("cause "+exp.getCause());
			exp.printStackTrace();
		}
	}
}
