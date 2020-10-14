package com.hurix.api.runner;

/*
 * 
 * Author Name:
 * SHWETA KATARE THE BRAND
 * 
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hurix.api.externalAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class Stag_Sanity {

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
	public static String categoryIdList;
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

		Log.initialization("Staging_Sanity");
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/Rest_Sanity.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Stag");
			sheet1= workbook.getSheet("RegisterUSER");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{DataFormatter formatter = new DataFormatter();
			environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			userName = formatter.formatCellValue(sheet.getRow(i).getCell(1));			
			password = formatter.formatCellValue(sheet.getRow(i).getCell(2));				
			catlevel = formatter.formatCellValue(sheet.getRow(i).getCell(3));
			String deviceType = formatter.formatCellValue(sheet.getRow(i).getCell(4));
			String runY_N = formatter.formatCellValue(sheet.getRow(i).getCell(5));
			String deviceLimit = formatter.formatCellValue(sheet.getRow(i).getCell(6));
			String type1 = formatter.formatCellValue(sheet.getRow(i).getCell(7));
			String  filePath =formatter.formatCellValue(sheet.getRow(i).getCell(8));
			String collectionRefID=formatter.formatCellValue(sheet.getRow(i).getCell(10));
			pageID =formatter.formatCellValue(sheet.getRow(i).getCell(11));
			lastPage =formatter.formatCellValue(sheet.getRow(i).getCell(12));
			chapterID =formatter.formatCellValue(sheet.getRow(i).getCell(13));
			emailID = formatter.formatCellValue(sheet.getRow(i).getCell(14));
			assignedBy = formatter.formatCellValue(sheet.getRow(i).getCell(15));
			userDeleted = formatter.formatCellValue(sheet.getRow(i).getCell(16));			
			String emailS = formatter.formatCellValue(sheet.getRow(i).getCell(17));
			String firstNameT1 = formatter.formatCellValue(sheet1.getRow(1).getCell(0));
			String lastNameT1 = formatter.formatCellValue(sheet1.getRow(1).getCell(1));
			String userNameT1 = formatter.formatCellValue(sheet1.getRow(1).getCell(2));
			String emailIDT1= formatter.formatCellValue(sheet1.getRow(1).getCell(3));

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
				sqlhost="jdbc:mysql://localhost:12348";
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
				sqlhost="jdbc:mysql://localhost:12348";
				sqlUsername="shweta-katare";
				sqlPassword="J&P@O4A7HV";
				break;
			}				
			io.restassured.RestAssured.baseURI = detail;


			Log.info("deviceLimit : +deviceLimit");
			int deviceLimit1 = Integer.parseInt(""+deviceLimit+"");
			int typeF = Integer.parseInt(""+type1+"");
			int pageIDF = Integer.parseInt(""+pageID+"");
			int lastPageF = Integer.parseInt(""+lastPage+"");
			int chapterIDF = Integer.parseInt(""+chapterID+"");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
			Date date = new Date();  
			String  time=formatter1.format(date);
			clientID = JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);


			String userNameC = formatter.formatCellValue(sheet.getRow(3).getCell(1));			
			String passwordC = formatter.formatCellValue(sheet.getRow(3).getCell(2));
			Log.startTestCase("Authenticate."+deviceType+"_Client");
			Response authenticateValueC = Authenticate.authenticate(clientID, userNameC, passwordC,"514185",deviceType);
			Log.info("Authenticate Response: "+authenticateValueC.then().extract().response().prettyPrint());				
			//Log.info("clientID : "+clientID);
			Validation.responseHeaderCodeValidation(authenticateValueC, HttpStatus.SC_OK);
			//Validation.responseCodeValidation1(authenticateValue, HttpStatus.SC_OK);
			Validation.responseTimeValidation(authenticateValueC);
			Validation.responseKeyValidation_key(authenticateValueC, "userName");
			Validation.responseKeyValidation_key(authenticateValueC, userNameC);			
			//Log.info("HERE_After");
			String userNameCC = authenticateValueC.then().extract().path("user.userName");
			Validation.responseKeyAndValue(authenticateValueC, "userName", userNameCC);
			int userIDC = authenticateValueC.then().extract().path("user.id");
			Log.info("userIDC : "+userIDC);
			String userTokenC = authenticateValueC.then().extract().path("userToken");
			System.out.println("userTokenT :"+userTokenC);
			String clientUserIDC = authenticateValueC.then().extract().path("user.clientUserID");
			System.out.println("clientUserIDT :"+clientUserIDC);
			int client_IdC = authenticateValueC.then().extract().path("user.clientID");
			System.out.println("client_IdC :"+client_IdC);
			String firstNameC= authenticateValueC.then().extract().path("user.firstName");
			System.out.println("firstNameC:"+firstNameC);
			String lastNameC = authenticateValueC.then().extract().path("user.lastName");
			System.out.println("lastNameC :"+lastNameC);
			//Log.info("HEWEWEWEWE");
			String userName1C = authenticateValueC.then().extract().path("user.userName");
			System.out.println("userName1C :"+""+userName1C+"");
			String emailC = authenticateValueC.then().extract().path("user.email");
			System.out.println("emailT :"+emailC);




			String userNameT = formatter.formatCellValue(sheet.getRow(2).getCell(1));			
			String passwordT = formatter.formatCellValue(sheet.getRow(2).getCell(2));
			Log.startTestCase("Authenticate."+deviceType+"_Instructor");
			Response authenticateValueT = Authenticate.authenticate(clientID, userNameT, passwordT,"514185",deviceType);
			Log.info("Authenticate Response: "+authenticateValueT.then().extract().response().prettyPrint());				
			Log.info("HERE_Before");
			Log.info("clientID : "+clientID);
			Validation.responseHeaderCodeValidation(authenticateValueT, HttpStatus.SC_OK);
			//Validation.responseCodeValidation1(authenticateValue, HttpStatus.SC_OK);
			Validation.responseTimeValidation(authenticateValueT);
			Validation.responseKeyValidation_key(authenticateValueT, "userName");
			Validation.responseKeyValidation_key(authenticateValueT, userNameT);			
			//Log.info("HERE_After");
			//String userNameTT = authenticateValueT.then().extract().path("user.userName");
			Validation.responseKeyAndValue(authenticateValueT, "userName", userNameT);
			int userIDT = authenticateValueT.then().extract().path("user.id");
			System.out.println("userIDT : "+userID);
			String userTokenT = authenticateValueT.then().extract().path("userToken");
			System.out.println("userTokenT :"+userTokenT);
			String clientUserIDT = authenticateValueT.then().extract().path("user.clientUserID");
			System.out.println("clientUserIDT :"+clientUserIDT);
			int client_IdT = authenticateValueT.then().extract().path("user.clientID");
			System.out.println("client_IdT :"+client_IdT);
			String firstNameT = authenticateValueT.then().extract().path("user.firstName");
			System.out.println("firstNameT:"+firstNameT);
			String lastNameT = authenticateValueT.then().extract().path("user.lastName");
			System.out.println("lastNameT :"+lastNameT);
			//Log.info("HEWEWEWEWE");
			String userName1T = authenticateValueT.then().extract().path("user.userName");
			System.out.println("userName1T :"+""+userName1T+"");
			String emailT = authenticateValueT.then().extract().path("user.email");
			Log.info("emailT :"+emailT);

			Log.info("detail : "+detail);
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			Log.info("ReaderKey : "+clientID);

			Log.startTestCase("Authenticate."+deviceType+"_Learner");
			Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"514185",deviceType);
			Log.info("Authenticate Response: "+authenticateValue.then().extract().response().prettyPrint());				
			//Log.info("HERE_Before");
			Log.info("clientID : "+clientID);
			Validation.responseHeaderCodeValidation(authenticateValue, HttpStatus.SC_OK);
			//Validation.responseCodeValidation1(authenticateValue, HttpStatus.SC_OK);
			Validation.responseTimeValidation(authenticateValue);
			Validation.responseKeyValidation_key(authenticateValue, "userName");
			Validation.responseKeyValidation_key(authenticateValue, userName);			
			//Log.info("HERE_After");
			userName = authenticateValue.then().extract().path("user.userName");
			Validation.responseKeyAndValue(authenticateValue, "userName", userName);
			userID = authenticateValue.then().extract().path("user.userID");
			System.out.println("userID_learner  : "+userID);
			String userToken = authenticateValue.then().extract().path("userToken");
			System.out.println("userToken:"+userToken);
			String clientUserID = authenticateValue.then().extract().path("user.clientUserID");
			System.out.println("clientUserID:"+clientUserID);
			int client_Id = authenticateValue.then().extract().path("user.clientID");
			System.out.println("client_Id:"+client_Id);
			firstName = authenticateValue.then().extract().path("user.firstName");
			System.out.println("firstName:"+firstName);
			lastName = authenticateValue.then().extract().path("user.lastName");
			System.out.println("lastName:"+lastName);
			//Log.info("HEWEWEWEWE");
			userName1 = authenticateValue.then().extract().path("user.userName");
			System.out.println("userName1:"+""+userName1+"");
			email = authenticateValue.then().extract().path("user.email");
			System.out.println("email:"+email);
			Log.endTestCase("End");

			consumerKey = JDBC_Queries.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			consumerSecret = JDBC_Queries.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);


			Response fetchBookList_with_pagination = FetchBookList.fetchBookList_with_pagination(0,15,userToken,"45616452",deviceType);
			Validation.responseCodeValidation1(fetchBookList_with_pagination, HttpStatus.SC_OK);
			Validation.responseHeaderCodeValidation(fetchBookList_with_pagination, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchBookList_with_pagination);
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "isbn");
			//String title = fetchBookList_with_pagination.then().extract().path("bookList.book.title[0]");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "title");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "id");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "archiveDate");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "collectionID");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "assetType");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "assignedOn");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "bookActive");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "bookCode");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "bookId");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "category");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "categoryIdList");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "categoryList");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "locale");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "collectionThumbnail");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "collectionType");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "formats");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "readingPercentage");
			Validation.responseKeyValidation_key(fetchBookList_with_pagination, "showFolio");
			int totalbooks= fetchBookList_with_pagination.then().assertThat().extract().path("totalbooks");
			Log.info("!@#$%^%#$%^%$#$%$#   totalbooks : "+totalbooks);
			Log.info("totalbooks : "+totalbooks);
			Validation.responseISGreater("totalbooks", totalbooks, 4);

			Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"45616452",deviceType);
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
			title = fetchBookList_without_pagination.then().extract().path("bookList.book.title[0]");
			Log.info("title: "+title);
			bookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[0]");
			Log.info("bookID_1: "+bookID1);
			bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[1]");			
			Log.info("bookID2: "+bookID2);
			bookID3 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[2]");			
			Log.info("bookID3: "+bookID3);
			bookID4 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[3]");
			Log.info("bookID4 :: "+bookID4);
			bookID5 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[4]");
			Log.info("bookID5 :: "+bookID5);
			bookID6 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[5]");
			Log.info("bookID6 :: "+bookID6);
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
			Object categoryIdList = fetchBookList_without_pagination.then().extract().path("bookList.book.categoryIdList[0]");
			Log.info("categoryIdList: "+categoryIdList);


			Response GETfetchBookCount_res = FetchBookCount.fetchBookCount(userToken,"45616452",deviceType);
			Validation.responseHeaderCodeValidation(GETfetchBookCount_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(GETfetchBookCount_res,HttpStatus.SC_OK);
			Validation.responseTimeValidation(GETfetchBookCount_res);
			Validation.responseKeyValidation_key(GETfetchBookCount_res, "totalbooks");
			Validation.responseKeyValidation_key(GETfetchBookCount_res, "timestamp");



			Response Orderv1 = Order.order_withBookID(EpochTime.current(), consumerKey,consumerSecret,bookID1,1,deviceLimit1,typeF);
			Log.info("OrderNumber : " +EpochTime.current());
			Log.info("detail : "+detail);
			Log.info("consumerKey : " +consumerKey);
			Log.info("consumerSecret : " +consumerSecret);
			Log.info("bookID1 : " +bookID1);
			Validation.responseHeaderCodeValidation(Orderv1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(Orderv1);
			Validation.responseKeyValidation_key(Orderv1, "accesstoken");
			Validation.responseKeyValidation_key(Orderv1, "code");



			Response UploadEpub_res = UploadEpub.uploadEpub_OAuth(consumerKey, consumerSecret,filePath,Title+EpochTime.current(),Title+EpochTime.current(),category1,""+EpochTime.current()+"",Title+EpochTime.current());
			Validation.responseHeaderCodeValidation(UploadEpub_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(UploadEpub_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(UploadEpub_res);
			Validation.responseKeyValidation_key(UploadEpub_res, "The request for the uploadEpub taken successfully.");
			try {epubId = UploadEpub_res.then().extract().path("epubId");
			Log.info("epubId: "+epubId);} catch (Exception e2) {Log.fail(e2.getMessage());}


			Thread.sleep(9000);
			Response EpubStatus_res = EpubStatus.epubStatus(consumerKey, consumerSecret,epubId);
			Validation.responseHeaderCodeValidation(EpubStatus_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(EpubStatus_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(EpubStatus_res);
			Validation.responseKeyValidation_key(EpubStatus_res, "status");
			Validation.responseKeyValidation_key(EpubStatus_res, "100");


			Response fetchTransferDetails=FetchTransferDetails.fetchTransferDetails(userToken, "wsdxs8d7", deviceType);
			Validation.responseHeaderCodeValidation(fetchTransferDetails, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchTransferDetails);
			Validation.responseKeyValidation_key(fetchTransferDetails, "online_transfer");
			Validation.responseKeyValidation_key(fetchTransferDetails, "bucket");
			Validation.responseKeyValidation_key(fetchTransferDetails, "accessKey");
			Validation.responseKeyValidation_key(fetchTransferDetails, "secretKey");
			Validation.responseKeyValidation_key(fetchTransferDetails, "accessKey");
			Validation.responseKeyValidation_key(fetchTransferDetails, "secretKey");

			Response updateUserLoginTime= UpdateUserLoginTime.updateUserLoginTime(userToken, userID);
			Validation.responseHeaderCodeValidation(updateUserLoginTime, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(updateUserLoginTime, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateUserLoginTime);
			Validation.responseKeyAndValue(updateUserLoginTime, "responseMsg", "OK");

			Response fetchPreferredLocale= FetchPreferredLocale.fetchPreferredLocale(userToken, "dshj65376", deviceType);
			Validation.responseHeaderCodeValidation(fetchPreferredLocale, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchPreferredLocale, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchPreferredLocale);
			Validation.responseKeyValidation_key(fetchPreferredLocale, "locale");

			Response getSecureURL=GetSecureURL.getSecureURL(userToken, "dsjhjcs4378", deviceType,3);
			Validation.responseHeaderCodeValidation(getSecureURL, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getSecureURL, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getSecureURL);
			Validation.responseKeyValidation_key(getSecureURL, "responseMsg");
			Validation.responseKeyValidation_key(getSecureURL, "timestamp");

			Response getClientSecureURL =GetClientSecureURL.getClientSecureURL(userToken, "dahasg3627", deviceType);
			Validation.responseHeaderCodeValidation(getClientSecureURL, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getClientSecureURL, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getClientSecureURL);

			Response bookIDs =BookIDs.bookIDs(userToken, "dsd456", deviceType);
			Validation.responseHeaderCodeValidation(bookIDs, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(bookIDs, HttpStatus.SC_OK);
			Validation.responseTimeValidation(bookIDs);
			Validation.responseKeyValidation_key(bookIDs, "bookIds");

			String serachText = title.substring(0,5);
			Response searchv2 = SearchV2.searchV2(serachText, userToken, "daa3232", deviceType);
			try {Validation.responseCodeValidation1(searchv2, HttpStatus.SC_OK);
			} catch (Exception e) {Log.fail(e.getMessage());}
			Validation.responseHeaderCodeValidation(searchv2, HttpStatus.SC_OK);
			Validation.responseTimeValidation(searchv2);
			Validation.responseKeyValidation_key(searchv2, "_id");
			Validation.responseKeyValidation_key(searchv2, "_index");
			Validation.responseKeyValidation_key(searchv2, "_score");
			Validation.responseKeyValidation_key(searchv2, "ISBN");
			Validation.responseKeyValidation_key(searchv2, "bookThumbnail");
			Validation.responseKeyValidation_key(searchv2, "bookTitle");
			Validation.responseKeyValidation_key(searchv2, "description");
			Validation.responseKeyValidation_key(searchv2, "_type");
			//Validation.responseKeyValidation_key(searchv2, "bookId");
			Validation.responseKeyValidation_key(searchv2, "bookTitle");
			Validation.responseKeyValidation_key(searchv2, "description");
			Validation.responseKeyValidation_key(searchv2, "total");

			Response downloadBook1 = DownloadBook_ext.downloadBook_ext(consumerKey, consumerSecret, bookID6,"sds4344", deviceType);
			Log.info("detail : " +detail);
			Validation.responseCodeValidation1(downloadBook1, HttpStatus.SC_OK);
			Log.info("detail : " +detail);
			Validation.responseTimeValidation(downloadBook1);
			Validation.responseKeyValidation_key(downloadBook1,"fileSize");
			Validation.responseKeyValidation_key(downloadBook1,"responseMsg");


			Response searchv2_ext = SearchV2_OAuth.searchV2_OAuth(serachText, consumerKey,consumerSecret,clientUserID);
			try {Validation.responseCodeValidation1(searchv2_ext, HttpStatus.SC_OK);
			}catch (Exception e) {Log.fail(e.getMessage());}
			Validation.responseHeaderCodeValidation(searchv2_ext, HttpStatus.SC_OK);
			Validation.responseTimeValidation(searchv2_ext);
			Validation.responseKeyValidation_key(searchv2_ext, "_id");
			Validation.responseKeyValidation_key(searchv2_ext, "_index");
			Validation.responseKeyValidation_key(searchv2_ext, "_score");
			Validation.responseKeyValidation_key(searchv2_ext, "ISBN");
			Validation.responseKeyValidation_key(searchv2_ext, "bookThumbnail");
			Validation.responseKeyValidation_key(searchv2_ext, "bookTitle");
			Validation.responseKeyValidation_key(searchv2_ext, "description");
			//Validation.responseKeyValidation_key(searchv2_ext, "bookReferenceId");
			Validation.responseKeyValidation_key(searchv2_ext, "_type");
			//Validation.responseKeyValidation_key(searchv2_ext, "bookId");
			Validation.responseKeyValidation_key(searchv2_ext, "bookTitle");
			Validation.responseKeyValidation_key(searchv2_ext, "description");
			Validation.responseKeyValidation_key(searchv2_ext, "total");

			Response fetchCollabShareData = FetchCollabShareData.fetchCollabShareData(userToken, "sdfg2345", deviceType, bookID1);
			Validation.responseCodeValidation1(fetchCollabShareData, HttpStatus.SC_OK);
			Validation.responseHeaderCodeValidation(fetchCollabShareData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchCollabShareData);


			Response bookshelfStateData = BookshelfStateData.bookshelfStateData(userToken, "wert345", deviceType);
			Validation.responseCodeValidation1(bookshelfStateData, HttpStatus.SC_OK);
			Validation.responseHeaderCodeValidation(bookshelfStateData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(bookshelfStateData);
			Validation.responseKeyValidation_key(bookshelfStateData, "user");
			Validation.responseKeyValidation_key(bookshelfStateData, "name");
			String name=bookshelfStateData .then().extract().path("user.currentUserEmail.name");
			Log.info("name : "+name);
			Validation.responseKeyAndValue(bookshelfStateData, "name", name);

			Response categoriesV1res = CategoriesV1.categoriesV1(userToken, "5489989",deviceType);
			Validation.responseHeaderCodeValidation(categoriesV1res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(categoriesV1res,HttpStatus.SC_OK);
			Validation.responseTimeValidation(categoriesV1res);
			Validation.responseKeyValidation_key(categoriesV1res, "hash");
			Validation.responseKeyValidation_key(categoriesV1res, "id");
			Validation.responseKeyValidation_key(categoriesV1res, "name");
			Validation.responseKeyValidation_key(categoriesV1res, "totalCategories");
			int  totalCategories=categoriesV1res.then().extract().path("totalCategories");
			String categoriesname=categoriesV1res.then().extract().path("categories[0].name");
			Validation.responseKeyAndValue(categoriesV1res, "name",categoriesname);
			Validation.responseISGreater("totalCategories", totalCategories, 3);	


			Response categoriesV2res =CategoriesV2.categoriesV2(userToken, "5489989",deviceType);
			Validation.responseHeaderCodeValidation(categoriesV2res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(categoriesV2res,HttpStatus.SC_OK);
			Validation.responseTimeValidation(categoriesV2res);
			Validation.responseKeyValidation_key(categoriesV2res, "hash");
			Validation.responseKeyValidation_key(categoriesV2res, "id");
			Validation.responseKeyValidation_key(categoriesV2res, "name");
			Validation.responseKeyValidation_key(categoriesV2res, "totalCategories");
			totalCategories=categoriesV2res.then().extract().path("totalCategories");
			categoriesname=categoriesV2res.then().extract().path("categories[0].name");
			Validation.responseKeyAndValue(categoriesV2res, "name",categoriesname);
			Validation.responseISGreater("totalCategories", totalCategories, 3);


			Response fetchCategoriesCollectionsres =FetchCategoriesCollections.fetchCategoriesCollections(userToken, "5489989",deviceType);
			Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsres, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchCategoriesCollectionsres, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchCategoriesCollectionsres);
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"categories");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"name");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"numberOfBooks");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"collections");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"name");


			Response fetchCategoriesCollectionsBooksres =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks(userToken, "5489989",deviceType,category1,collectionName0,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
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

			Response categoryBookListV1res = CategoryBookListV1.categoryBookListV1(""+category1+"",userToken,"56454", deviceType,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
			Validation.responseHeaderCodeValidation(categoryBookListV1res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(categoryBookListV1res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(categoryBookListV1res);
			Validation.responseKeyValidation_key(categoryBookListV1res,"archiveDate");
			Validation.responseKeyValidation_key(categoryBookListV1res,"category");
			Validation.responseKeyValidation_key(categoryBookListV1res,"assetType");
			Validation.responseKeyValidation_key(categoryBookListV1res,"assignedOn");
			Validation.responseKeyValidation_key(categoryBookListV1res,"author");
			Validation.responseKeyValidation_key(categoryBookListV1res,"bookId");
			Validation.responseKeyValidation_key(categoryBookListV1res,"bookLikeCount");
			Validation.responseKeyValidation_key(categoryBookListV1res,"categoryIdList");
			Validation.responseKeyValidation_key(categoryBookListV1res,"categoryList");
			Validation.responseKeyValidation_key(categoryBookListV1res,"formats");
			Validation.responseKeyValidation_key(categoryBookListV1res,"pages");
			Validation.responseKeyValidation_key(categoryBookListV1res,"readingPercentage");
			Validation.responseKeyValidation_key(categoryBookListV1res,"reflow");
			Validation.responseKeyValidation_key(categoryBookListV1res,"thumbURL");
			Validation.responseKeyValidation_key(categoryBookListV1res,"version");
			Validation.responseKeyValidation_key(categoryBookListV1res,"isbn");
			Validation.responseKeyValidation_key(categoryBookListV1res,"id");
			Validation.responseKeyValidation_key(categoryBookListV1res,"title");


			Response CategoryBookListV2Res = CategoryBookListV2.categoryBookListV2(""+category1+"",userToken,"56454", deviceType,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
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


			if(catlevel.contains ("1")){}			
			else{Response multiCategories_res = MultiCategories.multiCategories(catlevel,userToken,"fs445",deviceType);
			Validation.responseHeaderCodeValidation(multiCategories_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(multiCategories_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(multiCategories_res);
			Validation.responseKeyValidation_key(multiCategories_res, "categories");
			Validation.responseKeyValidation_key(multiCategories_res, "collectionCount");
			Validation.responseKeyValidation_key(multiCategories_res, "bookCount");
			Validation.responseKeyValidation_key(multiCategories_res, "hash");
			Validation.responseKeyValidation_key(multiCategories_res, "id");}


			if(catlevel.contains ("1")){}			
			else{Response MultiCategoryBookList_res = MultiCategoryBookList.multiCategoryBookList(catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceType);
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
			else{Response MultiCategoryCollection_BookList=MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel, bookID1, sqlhost,sqlUsername,sqlPassword, userToken, "4524242",deviceType, collectionName0);
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


			Response fetchTransferDetailsJson=FetchTransferDetailsJson.fetchTransferDetailsJson(userToken, "sdfgh234", deviceType, "true");
			Validation.responseHeaderCodeValidation(fetchTransferDetailsJson, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchTransferDetailsJson);
			Validation.responseKeyValidation_key(fetchTransferDetailsJson, "online_transfer");
			Validation.responseKeyValidation_key(fetchTransferDetailsJson, "bucket");
			Validation.responseKeyValidation_key(fetchTransferDetailsJson, "accessKey");
			Validation.responseKeyValidation_key(fetchTransferDetailsJson, "secretKey");
			Validation.responseKeyValidation_key(fetchTransferDetailsJson, "accessKey");
			Validation.responseKeyValidation_key(fetchTransferDetailsJson, "secretKey");

			Response fetchUserSetting = FetchUserSetting.fetchUserSetting(userToken, userID, deviceType);
			Validation.responseHeaderCodeValidation(fetchUserSetting, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchUserSetting, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchUserSetting);
			Validation.responseKeyValidation_key(fetchUserSetting, "clientSetting");
			Validation.responseKeyValidation_key(fetchUserSetting, "logoURL");
			Validation.responseKeyValidation_key(fetchUserSetting, "instituteSetting");
			Validation.responseKeyValidation_key(fetchUserSetting, "theme");
			Validation.responseKeyValidation_key(fetchUserSetting, "bookShell");
			Validation.responseKeyValidation_key(fetchUserSetting, "header");
			Validation.responseKeyValidation_key(fetchUserSetting, "downloaded_Area");
			Validation.responseKeyValidation_key(fetchUserSetting, "bookshelf_back");
			Validation.responseKeyValidation_key(fetchUserSetting, "footer");
			Validation.responseKeyValidation_key(fetchUserSetting, "font_color");
			Validation.responseKeyValidation_key(fetchUserSetting, "icons_color");
			Validation.responseKeyValidation_key(fetchUserSetting, "sub_header");
			Validation.responseKeyValidation_key(fetchUserSetting, "usertxt");
			Validation.responseKeyValidation_key(fetchUserSetting, "poweredByKitaboo");
			Validation.responseKeyValidation_key(fetchUserSetting, "menu_note");
			Validation.responseKeyValidation_key(fetchUserSetting, "readerSetting");
			Validation.responseKeyValidation_key(fetchUserSetting, "userDataList");
			Validation.responseKeyValidation_key(fetchUserSetting, "id");
			Validation.responseKeyValidation_key(fetchUserSetting, "name");
			Validation.responseKeyValidation_key(fetchUserSetting, "enable");
			Validation.responseKeyValidation_key(fetchUserSetting, "toolList");
			Validation.responseKeyValidation_key(fetchUserSetting, "annotationList");
			Validation.responseKeyValidation_key(fetchUserSetting, "miscList");
			Validation.responseKeyValidation_key(fetchUserSetting, "readerStatistics");
			Validation.responseKeyValidation_key(fetchUserSetting, "pagesReadTime");
			Validation.responseKeyValidation_key(fetchUserSetting, "important");
			Validation.responseKeyValidation_key(fetchUserSetting, "readerURL");
			Validation.responseKeyValidation_key(fetchUserSetting, "userSyncSettings");
			Validation.responseKeyValidation_key(fetchUserSetting, "audioMute");
			Validation.responseKeyValidation_key(fetchUserSetting, "categoryLevel");


			Response activateTrailUser= ActivateTrailUser.activateTrailUser(userToken, "sdf234", deviceType, clientUserID, clientID);
			Validation.responseHeaderCodeValidation(activateTrailUser, HttpStatus.SC_OK);
			Validation.responseTimeValidation(activateTrailUser);
			Validation.responseKeyValidation_key(activateTrailUser, "User Already Activated");


			Response uploadMediaFile = UploadMediaFile.uploadMediaFile(userToken, "asdfgh3456", deviceType);
			Validation.responseHeaderCodeValidation(uploadMediaFile, HttpStatus.SC_OK);
			Validation.responseTimeValidation(uploadMediaFile);
			Validation.responseKeyValidation_key(uploadMediaFile, "timestamp");
			Validation.responseKeyValidation_key(uploadMediaFile, "responseMsg");

			Response bookdetails = Bookdetails.bookdetails(""+archiveDate+"", userToken, "sdfg345",deviceType,bookID6,""+assetType+"");
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


			Response saveUserStateData = SaveUserStateData.saveUserStateData(type, bookID1, classID, userToken, "sdf345", deviceType);
			Validation.responseHeaderCodeValidation(saveUserStateData, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(saveUserStateData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveUserStateData);
			Validation.responseKeyValidation_key(saveUserStateData,"timestamp");
			Validation.responseKeyValidation_key(saveUserStateData,"responseMsg");

			Response fetchUserStateData =FetchUserStateData.fetchUserStateData(bookID1, userToken, "asdf345", deviceType);
			Validation.responseHeaderCodeValidation(fetchUserStateData, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchUserStateData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchUserStateData);
			Validation.responseKeyValidation_key(fetchUserStateData,"userStates");
			Validation.responseKeyValidation_key(fetchUserStateData,"timestamp");
			Validation.responseKeyValidation_key(fetchUserStateData,"type");
			Validation.responseKeyValidation_key(fetchUserStateData,"data");
			Validation.responseKeyValidation_key(fetchUserStateData,"folioID");
			Validation.responseKeyValidation_key(fetchUserStateData,"pageID");

			Response fetchBookLaunchURL =FetchBookLaunchURL.fetchBookLaunchURL(ebookID1, userToken);
			Validation.responseHeaderCodeValidation(fetchBookLaunchURL, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchBookLaunchURL);

			Response releaseBookLicense = ReleaseBookLicense.releaseBookLicense(collectionID, userToken, "fghj3456", deviceType);
			Validation.responseHeaderCodeValidation(releaseBookLicense, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(releaseBookLicense, HttpStatus.SC_OK);
			Validation.responseTimeValidation(releaseBookLicense);



			//int userID1 = Integer.parseInt(""+userID+"");
			//password="kitaboo!123";
			Response orderV2_withBookID = OrderV2.orderV2_withcollectionRefID(EpochTime.current(),collectionRefID, consumerKey, consumerSecret, bookID1, time,firstName,lastName,userName1,password,""+userID+"", email,deviceLimit1,typeF);
			Log.info("detail : "+detail);
			Validation.responseHeaderCodeValidation(orderV2_withBookID, HttpStatus.SC_OK);
			//Validation.responseCodeValidation1(orderV2_withBookID, HttpStatus.SC_OK);//Order created successfully
			Validation.responseKeyValidation_key(orderV2_withBookID, "Order created successfully");
			Validation.responseTimeValidation(orderV2_withBookID);	

			Response Orderv11 = Order.order_withBookID(EpochTime.current(), consumerKey,consumerSecret,bookID1,8,deviceLimit1,5);
			Log.info("OrderNumber : " +EpochTime.current());
			Log.info("detail : "+detail);
			Log.info("typeF : "+typeF);
			Validation.responseHeaderCodeValidation(Orderv11, HttpStatus.SC_OK);
			Validation.responseTimeValidation(Orderv11);
			Validation.responseKeyValidation_key(Orderv11, "accesstoken");
			Validation.responseKeyValidation_key(Orderv11, "code");
			String accesstoken = Orderv11.then().extract().path("accesstoken");
			Log.info("accesstoken : "+accesstoken);

			String[] accesstoken1= accesstoken.trim().split(",");
			Log.info("accesstoken : "+accesstoken);
			Response registerUser = RegisterUser.registerUser(firstName, lastName, ""+accesstoken1[0]+"", userToken, "dfgh324", deviceType);
			Validation.responseHeaderCodeValidation(registerUser, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(registerUser, HttpStatus.SC_OK);
			Validation.responseTimeValidation(registerUser);
			Validation.responseKeyValidation_key(registerUser, "userName");
			userName=registerUser.then().extract().path("user.userName");
			Validation.responseKeyValidation_key(registerUser, "lastName");
			Validation.responseKeyValidation_key(registerUser, "email");
			Validation.responseKeyValidation_key(registerUser, "password");
			Validation.responseKeyValidation_key(registerUser, "userName");
			Validation.responseKeyValidation_key(registerUser, "userToken");
			String userToken_regi = registerUser.then().extract().path("userToken");
			Validation.responseKeyValidation_key(registerUser, "userID");
			Validation.responseKeyValidation_key(registerUser, "instituteID");
			Validation.responseKeyValidation_key(registerUser, "clientID");

			Response validateAccessCode = ValidateAccessCode.validateAccessCode(""+accesstoken1[0]+"", userToken, "dfgh324", deviceType);
			Validation.responseHeaderCodeValidation(validateAccessCode, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validateAccessCode, HttpStatus.SC_OK);
			Validation.responseTimeValidation(validateAccessCode);
			Validation.responseKeyAndValue(validateAccessCode, "responseMsg","OK");

			Response validateUserToken = ValidateUserToken.validateUserToken(userToken_regi, "edfg2345", deviceType);
			Validation.responseHeaderCodeValidation(validateUserToken, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validateUserToken, HttpStatus.SC_OK);
			Validation.responseTimeValidation(validateUserToken);
			Validation.responseKeyAndValue(validateUserToken, "responseMsg","OK");
			Validation.responseKeyValidation_key(validateUserToken, "userName");
			Validation.responseKeyValidation_key(validateUserToken, "lastName");
			Validation.responseKeyValidation_key(validateUserToken, "email");
			Validation.responseKeyValidation_key(validateUserToken, "userName");
			Validation.responseKeyValidation_key(validateUserToken, "userToken");
			Validation.responseKeyValidation_key(validateUserToken, "userID");
			Validation.responseKeyValidation_key(validateUserToken, "instituteID");
			Validation.responseKeyValidation_key(validateUserToken, "clientID");
			Validation.responseKeyAndValue(validateUserToken, "userName", userName);

			validateUserToken = ValidateUserToken.validateUserTokenV1(userToken_regi, "edfg2345", deviceType);
			Validation.responseHeaderCodeValidation(validateUserToken, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validateUserToken, HttpStatus.SC_OK);
			Validation.responseTimeValidation(validateUserToken);
			Validation.responseKeyAndValue(validateUserToken, "responseMsg","OK");
			Validation.responseKeyValidation_key(validateUserToken, "userName");
			Validation.responseKeyValidation_key(validateUserToken, "lastName");
			Validation.responseKeyValidation_key(validateUserToken, "email");
			Validation.responseKeyValidation_key(validateUserToken, "userName");
			Validation.responseKeyValidation_key(validateUserToken, "userToken");
			Validation.responseKeyValidation_key(validateUserToken, "userID");
			Validation.responseKeyValidation_key(validateUserToken, "instituteID");
			Validation.responseKeyValidation_key(validateUserToken, "clientID");
			Validation.responseKeyAndValue(validateUserToken, "userName", userName);


			Response fetchBookClassExpandedAnalytics=FetchBookClassExpandedAnalytics.fetchBookClassExpandedAnalytics(bookID1, classID, userToken, "sdf345", deviceType);
			Validation.responseHeaderCodeValidation(fetchBookClassExpandedAnalytics, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchBookClassExpandedAnalytics, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchBookClassExpandedAnalytics);
			Validation.responseKeyAndValue(fetchBookClassExpandedAnalytics, "responseMsg","OK");
			Validation.responseKeyValidation_key(fetchBookClassExpandedAnalytics, "analytics");
			Validation.responseKeyValidation_key(fetchBookClassExpandedAnalytics, "classAnalytics");
			Validation.responseKeyValidation_key(fetchBookClassExpandedAnalytics, "name");
			Validation.responseKeyValidation_key(fetchBookClassExpandedAnalytics, "id");
			Validation.responseKeyValidation_key(fetchBookClassExpandedAnalytics, "bookOpened");
			Validation.responseKeyValidation_key(fetchBookClassExpandedAnalytics, "bookAssigned");
			Validation.responseKeyValidation_key(fetchBookClassExpandedAnalytics, "studentAnalytics");
			/*try{Validation.responseKeyValidation_key(fetchBookClassExpandedAnalytics, "totalSession");
			int totalSession = fetchBookClassExpandedAnalytics.then().extract().path("analytics.studentAnalytics[0].totalSession");
			Log.info("totalSession : "+totalSession);} catch (Exception e) {Log.fail(e.getMessage());}*/


			Response fetchBookClassUserExpandedAnalytics = FetchBookClassUserExpandedAnalytics.fetchBookClassUserExpandedAnalytics(bookID1, classID, userID, userToken, "asdfg345", deviceType);
			Validation.responseCodeValidation1(fetchBookClassUserExpandedAnalytics, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchBookClassUserExpandedAnalytics);
			Validation.responseKeyAndValue(fetchBookClassUserExpandedAnalytics, "responseMsg","OK");
			Validation.responseKeyValidation_key(fetchBookClassUserExpandedAnalytics, "analytics");
			Validation.responseKeyValidation_key(fetchBookClassUserExpandedAnalytics, "name");
			Validation.responseKeyValidation_key(fetchBookClassUserExpandedAnalytics, "pagesRead");
			Validation.responseKeyValidation_key(fetchBookClassUserExpandedAnalytics, "id");
			Validation.responseKeyValidation_key(fetchBookClassUserExpandedAnalytics, "studentAnalytics");
			Validation.responseKeyValidation_key(fetchBookClassUserExpandedAnalytics, "sessionAnalytics");


			Response fetchBookClassInfo=FetchBookClassInfo.fetchBookClassInfo(bookID1, userToken, "fdghs78", deviceType);
			Validation.responseCodeValidation1(fetchBookClassInfo, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchBookClassInfo);
			Validation.responseKeyAndValue(fetchBookClassInfo, "responseMsg","OK");
			Validation.responseKeyValidation_key(fetchBookClassInfo, "bookClassList");
			Validation.responseKeyValidation_key(fetchBookClassInfo, "book");
			Validation.responseKeyValidation_key(fetchBookClassInfo, "id");	
			Validation.responseKeyValidation_key(fetchBookClassInfo, "bookActive");
			Validation.responseKeyValidation_key(fetchBookClassInfo, "bookClassList");
			Validation.responseKeyValidation_key(fetchBookClassInfo, "classInfo");
			Validation.responseKeyValidation_key(fetchBookClassInfo, "name");
			Validation.responseKeyValidation_key(fetchBookClassInfo, "sharingSetting");
			Validation.responseKeyValidation_key(fetchBookClassInfo, "instructors");
			Validation.responseKeyValidation_key(fetchBookClassInfo, "learners");
			Validation.responseKeyValidation_key(fetchBookClassInfo, "profilePicURL");

			long clientCollectionId=EpochTime.current();
			Response savecollection = Savecollection.savecollection(clientCollectionId,consumerKey, consumerSecret, bookID1, bookID2);
			Validation.responseCodeValidation1(savecollection, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(savecollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(savecollection);
			Validation.responseKeyAndValue(savecollection, "responseMsg","OK");
			Validation.responseKeyValidation_key(savecollection, "kitabooCollectionId");
			Log.info("clientCollectionId : "+clientCollectionId);		
			int kitabooCollectionId =	savecollection.then().extract().path("kitabooCollectionId");
			Log.info("kitabooCollectionId : "+kitabooCollectionId);


			Response listCollection= ListCollection.listCollection(consumerKey, consumerSecret);
			Validation.responseCodeValidation1(listCollection, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(listCollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(listCollection);
			Validation.responseKeyValidation_key(listCollection, "clientCollectionId");
			Validation.responseKeyValidation_key(listCollection, "title");
			Validation.responseKeyValidation_key(listCollection, "coverImageUrl");
			Validation.responseKeyValidation_key(listCollection, "collectionType");
			Validation.responseKeyValidation_key(listCollection, "referenceNumber");

			Response updateCollection = UpdateCollection.updateCollection(clientCollectionId,consumerKey, consumerSecret, bookID1, bookID2, bookID3);
			Validation.responseCodeValidation1(updateCollection, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(updateCollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateCollection);
			Validation.responseKeyAndValue(updateCollection, "responseMsg","OK");

			Response releaseCollection = ReleaseCollection.releaseCollection(kitabooCollectionId, userToken, "sdfg2345", deviceType);
			Validation.responseCodeValidation1(releaseCollection, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(releaseCollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(releaseCollection);
			Validation.responseKeyAndValue(releaseCollection, "responseMsg","OK");

			listCollection= ListCollection.listCollection(consumerKey, consumerSecret);
			Validation.responseCodeValidation1(listCollection, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(listCollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(listCollection);
			Validation.responseKeyValidation_key(listCollection, "clientCollectionId");
			Validation.responseKeyValidation_key(listCollection, "title");
			Validation.responseKeyValidation_key(listCollection, "coverImageUrl");
			Validation.responseKeyValidation_key(listCollection, "collectionType");
			Validation.responseKeyValidation_key(listCollection, "referenceNumber");
			Validation.responseINTEGERKeyAndValue(listCollection, "id", kitabooCollectionId);

			String clientCollectionID = JDBC_Queries.getclientCollectionID(bookID1, client_Id, "Hey World", sqlhost, sqlUsername,sqlPassword);


			Response deleteClientCollection = DeleteClientCollection.deleteClientCollection(clientCollectionID, consumerKey,consumerSecret);
			Validation.responseCodeValidation1(deleteClientCollection, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(deleteClientCollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(deleteClientCollection);
			Validation.responseKeyAndValue(deleteClientCollection, "responseMsg","Success");

			long clientCollectionId1 = EpochTime.current();
			savecollection = Savecollection.savecollection(clientCollectionId1,consumerKey, consumerSecret, bookID1, bookID2);
			Validation.responseCodeValidation1(savecollection, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(savecollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(savecollection);
			Validation.responseKeyAndValue(savecollection, "responseMsg","OK");
			Validation.responseKeyValidation_key(savecollection, "kitabooCollectionId");
			Log.info("clientCollectionId : "+clientCollectionId);		
			int kitabooCollectionId1 =	savecollection.then().extract().path("kitabooCollectionId");
			Log.info("kitabooCollectionId1 : "+kitabooCollectionId1);

			deleteClientCollection = DeleteClientCollection.deleteClientCollection(""+clientCollectionId1+"", consumerKey,consumerSecret);
			Validation.responseCodeValidation1(deleteClientCollection, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(deleteClientCollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(deleteClientCollection);
			Validation.responseKeyAndValue(deleteClientCollection, "responseMsg","Success");


			listCollection= ListCollection.listCollection(consumerKey, consumerSecret);
			Validation.responseCodeValidation1(listCollection, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(listCollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(listCollection);
			Validation.responseKeyValidation_key(listCollection, "clientCollectionId");
			Validation.responseKeyValidation_key(listCollection, "title");
			Validation.responseKeyValidation_key(listCollection, "coverImageUrl");
			Validation.responseKeyValidation_key(listCollection, "collectionType");
			Validation.responseKeyValidation_key(listCollection, "referenceNumber");
			Validation.responseNOTKeyValidation_key(listCollection, ""+kitabooCollectionId1+"");

			Response uploadProfilePic = UploadProfilePic.uploadProfilePic(userToken, "sdfg6543", deviceType);
			Validation.responseCodeValidation1(uploadProfilePic, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(uploadProfilePic, HttpStatus.SC_OK);
			Validation.responseTimeValidation(uploadProfilePic);
			Validation.responseKeyAndValue(uploadProfilePic, "responseMsg","OK");
			Validation.responseKeyValidation_key(uploadProfilePic, "user");
			Validation.responseKeyValidation_key(uploadProfilePic, "usernameForInstitute");
			Validation.responseKeyValidation_key(uploadProfilePic, "trialUser");
			Validation.responseKeyValidation_key(uploadProfilePic, "profilePicURL");
			Validation.responseKeyValidation_key(uploadProfilePic, "clientID");
			int client_idProfile = uploadProfilePic.then().extract().path("user.clientID");
			Log.info("client_idProfile : "+client_idProfile);
			Validation.responseINTEGERKeyAndValue(uploadProfilePic, "clientID", client_Id);			

			Response fetchBookMetaData = FetchBookMetaData.fetchBookMetaData(bookID1, userToken, "sdfgh65432", deviceType);
			Validation.responseCodeValidation1(fetchBookMetaData, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchBookMetaData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchBookMetaData);
			Validation.responseKeyValidation_key(fetchBookMetaData, "resultSet");
			Validation.responseKeyValidation_key(fetchBookMetaData, "publisherLogo");
			Validation.responseKeyAndValue(fetchBookMetaData, "responseMsg","OK");

			Response fetchMarkupSetting = FetchMarkupSetting.fetchMarkupSetting(userToken, "asdfg345", deviceType);
			Validation.responseCodeValidation1(fetchMarkupSetting, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchMarkupSetting, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchMarkupSetting);
			Validation.responseKeyValidation_key(fetchMarkupSetting, "markupSettings");
			Validation.responseKeyValidation_key(fetchMarkupSetting, "name");
			Validation.responseKeyValidation_key(fetchMarkupSetting, "description");
			Validation.responseKeyValidation_key(fetchMarkupSetting, "selectionsStatus");
			Validation.responseKeyValidation_key(fetchMarkupSetting, "type");
			Validation.responseKeyValidation_key(fetchMarkupSetting, "value");
			Validation.responseKeyValidation_key(fetchMarkupSetting, "showTOR");
			Validation.responseKeyValidation_key(fetchMarkupSetting, "hideIcon");
			Validation.responseKeyValidation_key(fetchMarkupSetting, "width");
			Validation.responseKeyValidation_key(fetchMarkupSetting, "height");
			Validation.responseKeyValidation_key(fetchMarkupSetting, "subtitle");
			Validation.responseKeyValidation_key(fetchMarkupSetting, "autoLogging");

			Response fetchDefaultCBMId = FetchDefaultCBMId.fetchDefaultCBMId(bookID1, userToken, "kjh9876", deviceType);
			Validation.responseCodeValidation1(fetchDefaultCBMId, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchDefaultCBMId, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchDefaultCBMId);
			Validation.responseKeyValidation_key(fetchDefaultCBMId, "defaultkitabooId");
			Validation.responseKeyAndValue(fetchDefaultCBMId, "responseMsg","OK");


			Response refreshCategory = RefreshCategory.refreshCategory(categoryIdList, userToken, "23456asd", deviceType);
			Validation.responseHeaderCodeValidation(refreshCategory, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(refreshCategory, HttpStatus.SC_OK);
			Validation.responseTimeValidation(refreshCategory);

			Response fetchCollectionCBMId = FetchCollectionCBMId.fetchCollectionCBMId(bookID3, userToken, "kjh9876", deviceType);
			Validation.responseCodeValidation1(fetchCollectionCBMId, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchCollectionCBMId, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchCollectionCBMId);
			Validation.responseKeyValidation_key(fetchCollectionCBMId, "assignedkitabooId");
			Validation.responseKeyAndValue(fetchCollectionCBMId, "responseMsg","OK");

			Response fetchRecentlyViewed =  FetchRecentlyViewed.fetchRecentlyViewed_without_pagi(userToken,"54254fd",deviceType);
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


			Response getLastPageAccessed =GetLastPageAccessed.getLastPageAccessed(bookID1, userToken, "sdfgh65432", deviceType);
			Validation.responseHeaderCodeValidation(getLastPageAccessed, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getLastPageAccessed, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getLastPageAccessed);
			Validation.responseKeyValidation_key(getLastPageAccessed, "LastPageFolio");
			Validation.responseKeyAndValue(getLastPageAccessed, "responseMsg","OK");

			Response markAsFavourite_res = MarkAsFavourite.markAsFavourite(bookID1,userToken,"45564595",deviceType);
			BookID_mark1 = bookID1;
			Validation.responseHeaderCodeValidation(markAsFavourite_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(markAsFavourite_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(markAsFavourite_res);

			Response markAsFavourite_res1 = MarkAsFavourite.markAsFavourite(bookID2,userToken,"45564595",deviceType);
			BookID_mark2 = bookID2;
			Validation.responseHeaderCodeValidation(markAsFavourite_res1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(markAsFavourite_res1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(markAsFavourite_res1);



			Response markAsFavourite_res3 = MarkAsFavourite.markAsFavourite(bookID3,userToken,"45564595",deviceType);
			BookID_mark3 = bookID3;
			Validation.responseHeaderCodeValidation(markAsFavourite_res3, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(markAsFavourite_res3, HttpStatus.SC_OK);
			Validation.responseTimeValidation(markAsFavourite_res3);


			Response FetchFavouriteBooks_res = FetchFavouriteBooks.fetchFavouriteBooks(userToken,"45564595",deviceType);
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
			Validation.responseINTEGERKeyAndValue(FetchFavouriteBooks_res, "id", BookID_mark3);

			Response unMarkAsFavourite_res = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark1,userToken,"45564595",deviceType);
			Validation.responseHeaderCodeValidation(unMarkAsFavourite_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(unMarkAsFavourite_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(unMarkAsFavourite_res);


			Response unMarkAsFavourite_res1 = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark2,userToken,"45564595",deviceType);
			Validation.responseHeaderCodeValidation(unMarkAsFavourite_res1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(unMarkAsFavourite_res1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(unMarkAsFavourite_res1);


			Response unMarkAsFavourite_res3 = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark3,userToken,"45564595",deviceType);
			Validation.responseHeaderCodeValidation(unMarkAsFavourite_res3, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(unMarkAsFavourite_res3, HttpStatus.SC_OK);
			Validation.responseTimeValidation(unMarkAsFavourite_res3);


			Response FetchFavouriteBooks_resA= FetchFavouriteBooks.fetchFavouriteBooks(userToken,"45564595",deviceType);
			Validation.responseHeaderCodeValidation(FetchFavouriteBooks_resA, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(FetchFavouriteBooks_resA, HttpStatus.SC_OK);
			Validation.responseTimeValidation(FetchFavouriteBooks_resA);
			Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_resA, ""+BookID_mark1+"");
			Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_resA, ""+BookID_mark2+"");
			Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_resA, ""+BookID_mark3+"");


			String bookOpenTime= time+5;
			Log.info("bookOpenTime : "+bookOpenTime);
			Response SavetrackingDATA = SaveTrackingData.saveTrackingData(bookID1, classID, time, bookOpenTime, pageIDF, lastPageF, chapterIDF, userToken, "sdfgh345", deviceType);
			Validation.responseCodeValidation1(SavetrackingDATA, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(SavetrackingDATA, HttpStatus.SC_OK);
			Validation.responseTimeValidation(SavetrackingDATA);
			Validation.responseKeyAndValue(SavetrackingDATA, "responseMsg","OK");


			bookOpenTime= time+10;
			SavetrackingDATA = SaveTrackingData.saveTrackingDataV1(bookID1, classID, time, bookOpenTime, pageIDF, lastPageF, chapterIDF, userToken, "sdfgh345", deviceType);
			Validation.responseCodeValidation1(SavetrackingDATA, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(SavetrackingDATA, HttpStatus.SC_OK);
			Validation.responseTimeValidation(SavetrackingDATA);
			Validation.responseKeyAndValue(SavetrackingDATA, "responseMsg","OK");


			Response scormDataForClassAndBook = ScormDataForClassAndBook.scormDataForClassAndBook(bookID1, classID, userToken, "asdfgh6543", deviceType);
			Validation.responseCodeValidation1(scormDataForClassAndBook, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(scormDataForClassAndBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(scormDataForClassAndBook);
			Validation.responseKeyAndValue(scormDataForClassAndBook, "responseMsg","OK");


			Response fetchScormDataForBook =FetchScormDataForBook.fetchScormDataForBook(bookID1, userToken, "sdfgh7654", deviceType);
			Validation.responseCodeValidation1(fetchScormDataForBook, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchScormDataForBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchScormDataForBook);
			Validation.responseKeyAndValue(fetchScormDataForBook, "responseMsg","OK");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "scoList");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "classID");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "bookID");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "activityName");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "scoid");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "cmi.core");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "activityName");
			String scoid= fetchScormDataForBook.then().extract().path("scoList[1].scoid");
			Log.info("scoid : "+scoid);	
			String classID_1= fetchScormDataForBook.then().extract().path("scoList[0].classID");
			Log.info("classID_1 : "+classID_1);	


			Response saveScormData =SaveScormData.saveScormData(scoid, bookID1,userToken, "145644544", deviceType);
			Log.info("detail : "+detail);	
			Validation.responseCodeValidation1(saveScormData, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(saveScormData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveScormData);
			Validation.responseKeyAndValue(saveScormData, "responseMsg","OK");


			Response saveScormDataForBook =SaveScormDataForBook.saveScormDataForBook(bookID1,classID, userToken, "hgfds4567", deviceType);
			Validation.responseCodeValidation1(saveScormDataForBook, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(saveScormDataForBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveScormDataForBook);
			Validation.responseKeyAndValue(saveScormDataForBook, "responseMsg","OK");

			Response fetchScormData = FetchScormData.fetchScormData(scoid, bookID1, userToken, "hgfds9876", deviceType);
			Validation.responseCodeValidation1(fetchScormData, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchScormData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchScormData);
			Validation.responseKeyAndValue(fetchScormData, "responseMsg","OK");
			Validation.responseKeyValidation_key(fetchScormData, "activityName");
			Validation.responseKeyValidation_key(fetchScormData, "scoid");
			Validation.responseKeyValidation_key(fetchScormData, "cmi.core");


			int clientClassID = JDBC_Queries.getclientClassID(bookID1, classID, sqlhost, sqlUsername, sqlPassword);
			Log.info("clientClassID : "+clientClassID);			
			Response fetchScormDataForClass =FetchScormDataForClass.fetchScormDataForClass(consumerKey, consumerSecret, clientClassID);
			Validation.responseTimeValidation(fetchScormDataForClass);
			Validation.responseKeyValidation_key(fetchScormDataForClass, "scoList");
			Validation.responseKeyValidation_key(fetchScormDataForClass, "classID");
			Validation.responseKeyValidation_key(fetchScormDataForClass, "bookID");
			Validation.responseKeyValidation_key(fetchScormDataForClass, "userID");
			Validation.responseKeyValidation_key(fetchScormDataForClass, "clientClassId");
			Validation.responseKeyValidation_key(fetchScormDataForClass, "scoid");
			Validation.responseKeyValidation_key(fetchScormDataForClass, "cmi.core");
			int userID1 =fetchScormDataForClass.then().extract().path("scoList[0].userID");
			Log.info("userID1 : "+userID1);


			Response fetchScormData_OAuth = FetchScormData_OAuth.fetchScormData_OAuth(consumerKey, consumerSecret, clientClassID,userID1);
			Validation.responseCodeValidation1(fetchScormData_OAuth, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchScormData_OAuth, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchScormData_OAuth);
			Validation.responseKeyValidation_key(fetchScormData_OAuth, "scoList");
			Validation.responseKeyValidation_key(fetchScormData_OAuth, "classID");
			Validation.responseKeyValidation_key(fetchScormData_OAuth, "bookID");
			Validation.responseKeyValidation_key(fetchScormData_OAuth, "clientClassId");
			Validation.responseKeyValidation_key(fetchScormData_OAuth, "scoid");
			Validation.responseKeyValidation_key(fetchScormData_OAuth, "cmi.core");


			fetchScormDataForBook =FetchScormDataForBook.fetchScormDataForBook(bookID1, userToken, "sdfgh7654", deviceType);
			Validation.responseCodeValidation1(fetchScormDataForBook, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchScormDataForBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchScormDataForBook);
			Validation.responseKeyAndValue(fetchScormDataForBook, "responseMsg","OK");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "scoList");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "classID");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "bookID");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "activityName");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "scoid");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "cmi.core");
			Validation.responseKeyValidation_key(fetchScormDataForBook, "activityName");
			scoid= fetchScormDataForBook.then().extract().path("scoList[1].scoid");
			Log.info("scoid : "+scoid);	
			classID_1= fetchScormDataForBook.then().extract().path("scoList[0].classID");
			Log.info("classID_1 : "+classID_1);

			Response fetchBookClassInfo1=FetchBookClassInfo.fetchBookClassInfo(bookID1, userToken, "fdghs78", deviceType);
			Validation.responseCodeValidation1(fetchBookClassInfo1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchBookClassInfo1);
			Validation.responseKeyAndValue(fetchBookClassInfo1, "responseMsg","OK");
			Validation.responseKeyValidation_key(fetchBookClassInfo1, "bookClassList");
			Validation.responseKeyValidation_key(fetchBookClassInfo1, "book");
			Validation.responseKeyValidation_key(fetchBookClassInfo1, "id");	
			Validation.responseKeyValidation_key(fetchBookClassInfo1, "bookActive");
			Validation.responseKeyValidation_key(fetchBookClassInfo1, "bookClassList");
			Validation.responseKeyValidation_key(fetchBookClassInfo1, "classInfo");
			Validation.responseKeyValidation_key(fetchBookClassInfo1, "name");
			Validation.responseKeyValidation_key(fetchBookClassInfo1, "sharingSetting");
			Validation.responseKeyValidation_key(fetchBookClassInfo1, "instructors");
			Validation.responseKeyValidation_key(fetchBookClassInfo1, "learners");
			Validation.responseKeyValidation_key(fetchBookClassInfo1, "profilePicURL");


			Response saveUGC=SaveUGC.saveUGC(bookID1, time, userToken, "sdfgh2345", deviceType);
			Validation.responseCodeValidation1(saveUGC, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveUGC);
			Validation.responseKeyAndValue(saveUGC, "responseMsg","OK");
			Validation.responseKeyValidation_key(saveUGC, "bookUgcList");
			Validation.responseKeyValidation_key(saveUGC, "id");
			Validation.responseKeyValidation_key(saveUGC, "bookID");
			Validation.responseKeyValidation_key(saveUGC, "bookVersion");
			Validation.responseKeyValidation_key(saveUGC, "ugcList");
			Validation.responseKeyValidation_key(saveUGC, "localId");
			Validation.responseKeyValidation_key(saveUGC, "type");


			Response fetchUGC = FetchUGC.fetchUGC(bookID1, userToken, "23456543asdf", deviceType);
			Validation.responseCodeValidation1(fetchUGC, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchUGC);
			Validation.responseKeyValidation_key(fetchUGC, "bookUgcList");
			Validation.responseKeyValidation_key(fetchUGC, "bookID");
			Validation.responseKeyValidation_key(fetchUGC, "ugcList");
			Validation.responseKeyValidation_key(fetchUGC, "id");
			Validation.responseKeyValidation_key(fetchUGC, "localId");
			Validation.responseKeyValidation_key(fetchUGC, "ugcData");
			Validation.responseKeyValidation_key(fetchUGC, "type");
			Validation.responseKeyValidation_key(fetchUGC, "createdOn");
			Validation.responseKeyValidation_key(fetchUGC, "metadata");
			Validation.responseKeyValidation_key(fetchUGC, "folioID");
			Validation.responseKeyValidation_key(fetchUGC, "status");
			Validation.responseKeyValidation_key(fetchUGC, "important");			

			Response saveTeacherAnnotations = SaveTeacherAnnotations.saveTeacherAnnotations(bookID1, userID, userToken, "rt23", deviceType);
			Log.info("%%%%%%%%%%saveTeacherAnnotations _Student%%%%%%%");
			Validation.responseCodeValidation1(saveTeacherAnnotations, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveTeacherAnnotations);
			Validation.responseKeyValidation_key(saveTeacherAnnotations, "ugcList");
			Validation.responseKeyValidation_key(saveTeacherAnnotations, "id");
			Validation.responseKeyValidation_key(saveTeacherAnnotations, "localId");
			Validation.responseKeyValidation_key(saveTeacherAnnotations, "type");			

			saveTeacherAnnotations = SaveTeacherAnnotations.saveTeacherAnnotations(bookID1, userIDT, userTokenT, "rt23", deviceType);
			Log.info("%%%%%%%%%%saveTeacherAnnotations _Instructor%%%%%%%");
			Validation.responseCodeValidation1(saveTeacherAnnotations, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveTeacherAnnotations);
			Validation.responseKeyValidation_key(saveTeacherAnnotations, "ugcList");
			Validation.responseKeyValidation_key(saveTeacherAnnotations, "id");
			Validation.responseKeyValidation_key(saveTeacherAnnotations, "localId");
			Validation.responseKeyValidation_key(saveTeacherAnnotations, "type");

			Response fetchStudentAnnotations =FetchStudentAnnotations.fetchStudentAnnotations(bookID1, userID, version, userToken, "re23", deviceType);
			Validation.responseCodeValidation1(fetchStudentAnnotations, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchStudentAnnotations, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchStudentAnnotations);
			Validation.responseKeyValidation_key(fetchStudentAnnotations, "pageUgc");
			Validation.responseKeyValidation_key(fetchStudentAnnotations, "folioID");
			Validation.responseKeyValidation_key(fetchStudentAnnotations, "ugcList");
			Validation.responseKeyValidation_key(fetchStudentAnnotations, "id");
			Validation.responseKeyValidation_key(fetchStudentAnnotations, "localId");
			Validation.responseKeyValidation_key(fetchStudentAnnotations, "ugcData");
			Validation.responseKeyValidation_key(fetchStudentAnnotations, "type");
			Validation.responseKeyValidation_key(fetchStudentAnnotations, "type");
			Validation.responseKeyValidation_key(fetchStudentAnnotations, "createdBy");
			Validation.responseKeyValidation_key(fetchStudentAnnotations, "firstName");
			Validation.responseKeyValidation_key(fetchStudentAnnotations, "lastName");
			Validation.responseKeyValidation_key(fetchStudentAnnotations, "metadata");

			Response fetchTeacherAnnotations =FetchTeacherAnnotations.fetchTeacherAnnotations(bookID1, userID, version, userTokenT, "re23", deviceType);
			Validation.responseCodeValidation1(fetchTeacherAnnotations, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchTeacherAnnotations, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchTeacherAnnotations);
			Validation.responseKeyValidation_key(fetchTeacherAnnotations, "pageUgc");
			Validation.responseKeyValidation_key(fetchTeacherAnnotations, "folioID");
			Validation.responseKeyValidation_key(fetchTeacherAnnotations, "ugcList");
			Validation.responseKeyValidation_key(fetchTeacherAnnotations, "id");
			Validation.responseKeyValidation_key(fetchTeacherAnnotations, "localId");
			Validation.responseKeyValidation_key(fetchTeacherAnnotations, "ugcData");
			Validation.responseKeyValidation_key(fetchTeacherAnnotations, "type");
			Validation.responseKeyValidation_key(fetchTeacherAnnotations, "type");
			Validation.responseKeyValidation_key(fetchTeacherAnnotations, "createdBy");
			Validation.responseKeyValidation_key(fetchTeacherAnnotations, "firstName");
			Validation.responseKeyValidation_key(fetchTeacherAnnotations, "lastName");
			Validation.responseKeyValidation_key(fetchTeacherAnnotations, "metadata");


			String formate1=null;
			String formate2=null;
			String formate3=null;
			String formate4=null;
			String formate5=null;
			String formate = JDBC_Queries.getFormate(bookID6,sqlhost,sqlUsername,sqlPassword);
			if(formate.equals("IPAD"))
			{formate1 = JDBC_Queries.getFormate_3(bookID6,sqlhost,sqlUsername,sqlPassword);
			formate2 = JDBC_Queries.getFormate_5(bookID6,sqlhost,sqlUsername,sqlPassword);
			formate3 = JDBC_Queries.getFormate_12(bookID6,sqlhost,sqlUsername,sqlPassword);
			formate4 = JDBC_Queries.getFormate_13(bookID6,sqlhost,sqlUsername,sqlPassword);
			}
			formate5 = JDBC_Queries.getFormate(bookID1,sqlhost,sqlUsername,sqlPassword);	


			Response downloadBook = DownloadBook.downloadBook(userToken,"ds9465",formate,bookID6,"offline");
			Validation.responseCodeValidation1(downloadBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBook);
			Validation.responseKeyValidation_key(downloadBook,"content_ownership");
			String content_ownership=downloadBook.then().extract().path("content_ownership");
			Validation.responseKeyAndValue(downloadBook, "content_ownership", content_ownership);
			Validation.responseKeyValidation_key(downloadBook,"fileSize");
			Validation.responseKeyValidation_key(downloadBook,"responseMsg");
			Validation.responseKeyValidation_key(downloadBook,"timestamp");						

			downloadBook = DownloadBook.downloadBook(userToken,"wert345",formate1,bookID6,"offline");
			Validation.responseCodeValidation1(downloadBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBook);
			Validation.responseKeyValidation_key(downloadBook,"content_ownership");
			content_ownership=downloadBook.then().extract().path("content_ownership");
			Validation.responseKeyAndValue(downloadBook, "content_ownership", content_ownership);
			Validation.responseKeyValidation_key(downloadBook,"fileSize");
			Validation.responseKeyValidation_key(downloadBook,"responseMsg");
			Validation.responseKeyValidation_key(downloadBook,"timestamp");


			downloadBook = DownloadBook.downloadBook(userToken,"ds9465",formate2,bookID6,"online");
			Validation.responseCodeValidation1(downloadBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBook);
			Validation.responseKeyValidation_key(downloadBook,"content_ownership");
			content_ownership=downloadBook.then().extract().path("content_ownership");
			Validation.responseKeyAndValue(downloadBook, "content_ownership", content_ownership);
			Validation.responseKeyValidation_key(downloadBook,"fileSize");
			Validation.responseKeyValidation_key(downloadBook,"responseMsg");
			Validation.responseKeyValidation_key(downloadBook,"timestamp");


			downloadBook = DownloadBook.downloadBook(userToken,"ds9465",formate3,bookID6,"online");
			Validation.responseCodeValidation1(downloadBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBook);
			Validation.responseKeyValidation_key(downloadBook,"content_ownership");
			content_ownership=downloadBook.then().extract().path("content_ownership");
			Validation.responseKeyAndValue(downloadBook, "content_ownership", content_ownership);
			Validation.responseKeyValidation_key(downloadBook,"fileSize");
			Validation.responseKeyValidation_key(downloadBook,"responseMsg");
			Validation.responseKeyValidation_key(downloadBook,"timestamp");

			downloadBook = DownloadBook.downloadBook(userToken,"ds9465",formate4,bookID6,"online");
			Validation.responseCodeValidation1(downloadBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBook);
			Validation.responseKeyValidation_key(downloadBook,"content_ownership");
			content_ownership=downloadBook.then().extract().path("content_ownership");
			Validation.responseKeyAndValue(downloadBook, "content_ownership", content_ownership);
			Validation.responseKeyValidation_key(downloadBook,"fileSize");
			Validation.responseKeyValidation_key(downloadBook,"responseMsg");
			Validation.responseKeyValidation_key(downloadBook,"timestamp");


			downloadBook = DownloadBook.downloadBook(userToken,"ds9465",formate5,bookID1,"online");
			try {Validation.responseCodeValidation1(downloadBook, HttpStatus.SC_OK);
			} catch (Exception e1) {Log.fail(e1.getMessage());}
			Validation.responseTimeValidation(downloadBook);

			try {Validation.responseKeyValidation_key(downloadBook,"content_ownership");
			content_ownership=downloadBook.then().extract().path("content_ownership");
			Validation.responseKeyAndValue(downloadBook, "content_ownership", content_ownership);
			} catch (Exception e) {Log.fail(e.getMessage());}
			Validation.responseKeyValidation_key(downloadBook,"fileSize");
			Validation.responseKeyValidation_key(downloadBook,"responseMsg");
			Validation.responseKeyValidation_key(downloadBook,"timestamp");

			Response saveUGC1 = SaveUGC.saveUGC(bookID1, time, userToken, "sdfgh2345", deviceType);
			Validation.responseCodeValidation1(saveUGC1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveUGC1);
			Validation.responseKeyAndValue(saveUGC1, "responseMsg","OK");
			Validation.responseKeyValidation_key(saveUGC1, "bookUgcList");
			Validation.responseKeyValidation_key(saveUGC1, "id");
			Validation.responseKeyValidation_key(saveUGC1, "bookID");
			Validation.responseKeyValidation_key(saveUGC1, "bookVersion");
			Validation.responseKeyValidation_key(saveUGC1, "ugcList");
			Validation.responseKeyValidation_key(saveUGC1, "localId");
			Validation.responseKeyValidation_key(saveUGC1, "type");
			int UGCId =saveUGC1.then().extract().path("bookUgcList[0].ugcList[0].id");
			Log.info("UGCId : "+UGCId);

			Response submitAnnotations = SubmitAnnotations.submitAnnotations(bookID1, userToken, "gfd6543", deviceType);
			Validation.responseCodeValidation1(submitAnnotations, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(submitAnnotations, HttpStatus.SC_OK);
			Validation.responseTimeValidation(submitAnnotations);
			Validation.responseKeyAndValue(submitAnnotations, "responseMsg","OK");

			Response acceptCollabData = AcceptCollabData.acceptCollabData(UGCId, userToken, "gfd6543", deviceType);
			Validation.responseCodeValidation1(acceptCollabData, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(acceptCollabData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(acceptCollabData);
			Validation.responseKeyAndValue(acceptCollabData, "responseMsg","OK");

			Response saveCollabData = SaveCollabData.saveCollabData(UGCId, bookID1, userID, userToken, "gfd6543", deviceType);
			Validation.responseCodeValidation1(saveCollabData, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(saveCollabData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveCollabData);
			Validation.responseKeyAndValue(saveCollabData, "responseMsg","OK");

			Response fetchCollabData= FetchCollabData.fetchCollabData(version, bookID1, userToken, "gfd6543", deviceType);
			Validation.responseCodeValidation1(fetchCollabData, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchCollabData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchCollabData);
			Validation.responseKeyAndValue(fetchCollabData, "responseMsg","OK");
			Validation.responseKeyValidation_key(fetchCollabData, "id");
			Validation.responseKeyValidation_key(fetchCollabData, "localId");
			Validation.responseKeyValidation_key(fetchCollabData, "ugcData");
			Validation.responseKeyValidation_key(fetchCollabData, "type");
			Validation.responseKeyValidation_key(fetchCollabData, "createdOn");
			Validation.responseKeyValidation_key(fetchCollabData, "createdBy");
			Validation.responseKeyValidation_key(fetchCollabData, "firstName");
			Validation.responseKeyValidation_key(fetchCollabData, "lastName");
			Validation.responseKeyValidation_key(fetchCollabData, "userName");
			Validation.responseKeyValidation_key(fetchCollabData, "metadata");
			Validation.responseKeyValidation_key(fetchCollabData, "folioID");
			Validation.responseKeyValidation_key(fetchCollabData, "status");
			Validation.responseKeyValidation_key(fetchCollabData, "important");
			Validation.responseKeyValidation_key(fetchCollabData, "noteShared");
			Validation.responseKeyValidation_key(fetchCollabData, "actionTaken");


			fetchCollabData= FetchCollabData.fetchCollabData_v3(version, bookID1, userToken, "gfd6543", deviceType);
			Validation.responseCodeValidation1(fetchCollabData, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchCollabData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchCollabData);
			Validation.responseKeyAndValue(fetchCollabData, "responseMsg","OK");
			Validation.responseKeyValidation_key(fetchCollabData, "id");
			Validation.responseKeyValidation_key(fetchCollabData, "localId");
			Validation.responseKeyValidation_key(fetchCollabData, "ugcData");
			Validation.responseKeyValidation_key(fetchCollabData, "type");
			Validation.responseKeyValidation_key(fetchCollabData, "createdOn");
			Validation.responseKeyValidation_key(fetchCollabData, "createdBy");
			Validation.responseKeyValidation_key(fetchCollabData, "firstName");
			Validation.responseKeyValidation_key(fetchCollabData, "lastName");
			Validation.responseKeyValidation_key(fetchCollabData, "userName");
			Validation.responseKeyValidation_key(fetchCollabData, "metadata");
			Validation.responseKeyValidation_key(fetchCollabData, "folioID");
			Validation.responseKeyValidation_key(fetchCollabData, "status");
			Validation.responseKeyValidation_key(fetchCollabData, "important");
			Validation.responseKeyValidation_key(fetchCollabData, "noteShared");
			Validation.responseKeyValidation_key(fetchCollabData, "actionTaken");

			String bookOpenTime1= time+5;
			Log.info("bookOpenTime : "+bookOpenTime1);
			Response SavetrackingDATA1 = SaveTrackingData.saveTrackingData(bookID1, classID, time, bookOpenTime1, pageIDF, lastPageF, chapterIDF, userToken, "sdfgh345", deviceType);
			Validation.responseCodeValidation1(SavetrackingDATA1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(SavetrackingDATA1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(SavetrackingDATA1);
			Validation.responseKeyAndValue(SavetrackingDATA1, "responseMsg","OK");

			Response fetchBookClassAnalytics = FetchBookClassAnalytics.fetchBookClassAnalytics(bookID1,classID, userToken, "rew432", deviceType);
			try {Validation.responseCodeValidation1(fetchBookClassAnalytics, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchBookClassAnalytics, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchBookClassAnalytics);
			} catch (Exception e) {Log.fail(e.getMessage());	}

			Response fetchAnalytics = FetchAnalytics.fetchAnalytics(bookID1,classID, userToken, "rew432", deviceType);
			Validation.responseCodeValidation1(fetchAnalytics, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchAnalytics, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchAnalytics);
			Validation.responseKeyValidation_key(fetchAnalytics, "analytics");
			Validation.responseKeyValidation_key(fetchAnalytics, "classAnalytics");
			Validation.responseKeyValidation_key(fetchAnalytics, "name");
			Validation.responseKeyValidation_key(fetchAnalytics, "id");
			Validation.responseKeyValidation_key(fetchAnalytics, "totalPages");
			Validation.responseKeyValidation_key(fetchAnalytics, "studentAnalytics");
			Validation.responseKeyValidation_key(fetchAnalytics, "totalPages");
			Validation.responseKeyValidation_key(fetchAnalytics, "subAnalytics");

			Response saveUGC11 = SaveUGC.saveUGC(bookID1, time, userToken, "sdfgh2345", deviceType);
			Validation.responseCodeValidation1(saveUGC11, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveUGC11);
			Validation.responseKeyAndValue(saveUGC11, "responseMsg","OK");
			Validation.responseKeyValidation_key(saveUGC11, "bookUgcList");
			Validation.responseKeyValidation_key(saveUGC11, "id");
			Validation.responseKeyValidation_key(saveUGC11, "bookID");
			Validation.responseKeyValidation_key(saveUGC11, "bookVersion");
			Validation.responseKeyValidation_key(saveUGC11, "ugcList");
			Validation.responseKeyValidation_key(saveUGC11, "localId");
			Validation.responseKeyValidation_key(saveUGC11, "type");
			int UGCId1 =saveUGC11.then().extract().path("bookUgcList[0].ugcList[0].id");
			Log.info("UGCId : "+UGCId1);

			Response deleteUGCdata = SaveUGC.deleteUGCdata(bookID1, classID, userToken, "as23", deviceType);
			Validation.responseCodeValidation1(deleteUGCdata, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(deleteUGCdata, HttpStatus.SC_OK);
			Validation.responseTimeValidation(deleteUGCdata);
			Validation.responseKeyValidation_key(deleteUGCdata, "bookUgcList");
			Validation.responseKeyValidation_key(deleteUGCdata, "bookID");
			Validation.responseKeyValidation_key(deleteUGCdata, "bookVersion");
			Validation.responseKeyValidation_key(deleteUGCdata, "ugcList");
			Validation.responseKeyValidation_key(deleteUGCdata, "id");
			Validation.responseKeyValidation_key(deleteUGCdata, "localId");
			Validation.responseKeyValidation_key(deleteUGCdata, "type");
			int UGCId11 =deleteUGCdata.then().extract().path("bookUgcList[0].ugcList[0].id");
			Log.info("UGCId : "+UGCId11);


			Response fetchUGC1 = FetchUGC.fetchUGC(bookID1, userToken, "23456543asdf", deviceType);
			Validation.responseCodeValidation1(fetchUGC1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchUGC1);
			Validation.responseKeyValidation_key(fetchUGC1, "bookUgcList");
			Validation.responseKeyValidation_key(fetchUGC1, "bookID");
			Validation.responseKeyValidation_key(fetchUGC1, "ugcList");
			Validation.responseKeyValidation_key(fetchUGC1, "id");
			Validation.responseKeyValidation_key(fetchUGC1, "localId");
			Validation.responseKeyValidation_key(fetchUGC1, "ugcData");
			Validation.responseKeyValidation_key(fetchUGC1, "type");
			Validation.responseKeyValidation_key(fetchUGC1, "createdOn");
			Validation.responseKeyValidation_key(fetchUGC1, "metadata");
			Validation.responseKeyValidation_key(fetchUGC1, "folioID");
			Validation.responseKeyValidation_key(fetchUGC1, "status");
			Validation.responseKeyValidation_key(fetchUGC1, "important");
			Validation.responseNOTKeyValidation_key(fetchUGC1, ""+UGCId11+"");

			Response books = Books.books(userToken, "sd234", deviceType);
			Validation.responseCodeValidation1(books, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(books, HttpStatus.SC_OK);
			Validation.responseTimeValidation(books);


			Response Booklist = BookList.bookList(userToken,"56454", deviceType);
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


			Response refreshBookList = RefreshBookList.refreshBookList(userToken,"56496",deviceType);
			Validation.responseHeaderCodeValidation(refreshBookList, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(refreshBookList, HttpStatus.SC_OK);
			Validation.responseTimeValidation(refreshBookList);
			Validation.responseKeyValidation_key(refreshBookList, "archiveDate");
			Validation.responseKeyValidation_key(refreshBookList, "assetType");
			Validation.responseKeyValidation_key(refreshBookList, "assignedOn");
			Validation.responseKeyValidation_key(refreshBookList, "author");
			Validation.responseKeyValidation_key(refreshBookList, "bookActive");
			Validation.responseKeyValidation_key(refreshBookList, "bookCode");
			Validation.responseKeyValidation_key(refreshBookList, "bookDisLikeCount");
			Validation.responseKeyValidation_key(refreshBookList, "bookId");
			Validation.responseKeyValidation_key(refreshBookList, "bookLikeCount");
			Validation.responseKeyValidation_key(refreshBookList, "category");
			Validation.responseKeyValidation_key(refreshBookList, "categoryIdList");
			Validation.responseKeyValidation_key(refreshBookList, "categoryList");
			Validation.responseKeyValidation_key(refreshBookList, "collectionID");
			Validation.responseKeyValidation_key(refreshBookList, "collectionTitle");
			Validation.responseKeyValidation_key(refreshBookList, "collectionType");
			Validation.responseKeyValidation_key(refreshBookList, "ebookID");
			Validation.responseKeyValidation_key(refreshBookList, "expiryDate");
			Validation.responseKeyValidation_key(refreshBookList, "favourite");



			//2019/10/31 14:46:04
			Response v1refreshBookList =V1refreshBookList.v1refreshBookList(""+archiveDate+"","NEW","UPDATE",bookID1,bookID2,userToken,"56454", deviceType,clientID);
			Validation.responseHeaderCodeValidation(v1refreshBookList, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(v1refreshBookList, HttpStatus.SC_OK);
			Validation.responseTimeValidation(v1refreshBookList);
			Validation.responseKeyValidation_key(v1refreshBookList, "archiveDate");
			Validation.responseKeyValidation_key(v1refreshBookList, "assetType");
			Validation.responseKeyValidation_key(v1refreshBookList, "assignedOn");
			Validation.responseKeyValidation_key(v1refreshBookList, "author");
			Validation.responseKeyValidation_key(v1refreshBookList, "bookActive");
			Validation.responseKeyValidation_key(v1refreshBookList, "bookCode");
			Validation.responseKeyValidation_key(v1refreshBookList, "bookDisLikeCount");
			Validation.responseKeyValidation_key(v1refreshBookList, "bookId");
			Validation.responseKeyValidation_key(v1refreshBookList, "bookLikeCount");
			Validation.responseKeyValidation_key(v1refreshBookList, "category");
			Validation.responseKeyValidation_key(v1refreshBookList, "categoryIdList");
			Validation.responseKeyValidation_key(v1refreshBookList, "categoryList");
			Validation.responseKeyValidation_key(v1refreshBookList, "collectionID");
			Validation.responseKeyValidation_key(v1refreshBookList, "collectionTitle");
			Validation.responseKeyValidation_key(v1refreshBookList, "collectionType");
			Validation.responseKeyValidation_key(v1refreshBookList, "ebookID");
			Validation.responseKeyValidation_key(v1refreshBookList, "reflow");
			Validation.responseKeyValidation_key(v1refreshBookList, "operation");
			operation0=v1refreshBookList.then().extract().path("bookList.book.operation[0]");
			operation1=v1refreshBookList.then().extract().path("bookList.book.operation[1]");
			Validation.responseKeyAndValue(v1refreshBookList, "operation", operation0);
			Validation.responseKeyAndValue(v1refreshBookList, "operation", operation1);

			Response savePreferredLocale = SavePreferredLocale.savePreferredLocale(userToken, "234asd", deviceType);
			Validation.responseHeaderCodeValidation(savePreferredLocale, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(savePreferredLocale, HttpStatus.SC_OK);
			Validation.responseTimeValidation(savePreferredLocale);
			Validation.responseKeyAndValue(savePreferredLocale, "responseMsg","Ok");

			Response saveSessionHistory = SaveSessionHistory.saveSessionHistory(userToken,"45564595",deviceType,bookID1,time);
			Log.info("TIME : "+time);
			Validation.responseHeaderCodeValidation(saveSessionHistory, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(saveSessionHistory, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveSessionHistory);
			Validation.responseKeyValidation_key(saveSessionHistory, "ok");


			SimpleDateFormat formatter11 = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");  
			Date date1 = new Date();  
			String  time1 = formatter11.format(date1);
			Response saveSessionHistoryForMutipleBooks = SaveSessionHistoryForMutipleBooks.saveSessionHistoryForMutipleBooks(bookID1, bookID2,time1, userToken, "fgh45", deviceType);
			Validation.responseHeaderCodeValidation(saveSessionHistoryForMutipleBooks, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(saveSessionHistoryForMutipleBooks, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveSessionHistoryForMutipleBooks);
			Validation.responseKeyValidation_key(saveSessionHistoryForMutipleBooks, "ok");


			Response checkClientSession = CheckClientSession.checkClientSession(bookID1, userToken, "dfg345", deviceType);
			Validation.responseHeaderCodeValidation(checkClientSession, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(checkClientSession, HttpStatus.SC_OK);
			Validation.responseTimeValidation(checkClientSession);
			Validation.responseKeyValidation_key(checkClientSession, "OK");

			Response saveHighlightSetting = SaveHighlightSetting.saveHighlightSetting(bookID1, userID, userToken, "tre3456", deviceType);
			Validation.responseHeaderCodeValidation(saveHighlightSetting, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(saveHighlightSetting, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveHighlightSetting);
			Validation.responseKeyValidation_key(saveHighlightSetting, "OK");


			Response uploadProfilePic1 = UploadProfilePic.uploadProfilePic(userToken, "oi987", deviceType);
			Validation.responseHeaderCodeValidation(uploadProfilePic1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(uploadProfilePic1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(uploadProfilePic1);
			Validation.responseKeyValidation_key(uploadProfilePic1, "OK");
			Validation.responseKeyValidation_key(uploadProfilePic1, "clientID");
			Validation.responseKeyValidation_key(uploadProfilePic1, "user");
			Validation.responseKeyValidation_key(uploadProfilePic1, "usernameForInstitute");
			Validation.responseKeyValidation_key(uploadProfilePic1, "trialUser");
			Validation.responseKeyValidation_key(uploadProfilePic1, "profilePicURL");

			Response fetchSessionTimeout = FetchSessionTimeout.fetchSessionTimeout(userToken, "wert345", deviceType);
			Validation.responseHeaderCodeValidation(fetchSessionTimeout, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchSessionTimeout, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchSessionTimeout);
			Validation.responseKeyValidation_key(fetchSessionTimeout, "ok");

			Response changePassword = ChangePassword.changePassword(email, password,password,userToken, "sdfg345", deviceType);
			Validation.responseHeaderCodeValidation(changePassword, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(changePassword, HttpStatus.SC_OK);
			Validation.responseTimeValidation(changePassword);
			Validation.responseKeyValidation_key(changePassword, "OK");

			Response sendMail = SendMail.sendMail(emailID, assignedBy, userToken, "asdf2345", deviceType);
			Validation.responseHeaderCodeValidation(sendMail, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(sendMail, HttpStatus.SC_OK);
			Validation.responseTimeValidation(sendMail);
			Validation.responseKeyValidation_key(sendMail, "OK");


			Response logout = Logout.logout(userToken, "sdfdsd2343", deviceType);
			Validation.responseHeaderCodeValidation(logout, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(logout, HttpStatus.SC_OK);
			Validation.responseTimeValidation(logout);
			Validation.responseKeyValidation_key(logout, "OK");

			Response validateUserToken1 = ValidateUserToken.validateUserToken(userToken, "edfg2345", deviceType);
			Validation.responseHeaderCodeValidation(validateUserToken1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validateUserToken1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(validateUserToken1);
			Validation.responseKeyAndValue(validateUserToken1, "responseMsg","OK");
			Validation.responseKeyValidation_key(validateUserToken1, "userName");
			Validation.responseKeyValidation_key(validateUserToken1, "email");
			Validation.responseKeyValidation_key(validateUserToken1, "userName");
			Validation.responseKeyValidation_key(validateUserToken1, "userToken");
			Validation.responseKeyValidation_key(validateUserToken1, "userID");
			Validation.responseKeyValidation_key(validateUserToken1, "instituteID");
			Validation.responseKeyValidation_key(validateUserToken1, "clientID");
			Validation.responseKeyAndValue(validateUserToken1, "userName", userName1);

			Response books_OAuth = Books_OAuth.books_OAuth(consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(books_OAuth, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(books_OAuth, HttpStatus.SC_OK);
			Validation.responseTimeValidation(books_OAuth);
			Validation.responseKeyValidation_key(books_OAuth, "id");
			Validation.responseKeyValidation_key(books_OAuth, "isbn");
			Validation.responseKeyValidation_key(books_OAuth, "title");
			Validation.responseKeyValidation_key(books_OAuth, "author");
			Validation.responseKeyValidation_key(books_OAuth, "createdOn");
			Validation.responseKeyValidation_key(books_OAuth, "description");
			Validation.responseKeyValidation_key(books_OAuth, "category");
			Validation.responseKeyValidation_key(books_OAuth, "categoryList");
			Validation.responseKeyValidation_key(books_OAuth, "bookActive");


			int userDeleted1 = Integer.parseInt(""+userDeleted+"");
			Response updateNotification = UpdateNotification.updateNotification(userID, userDeleted1, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(updateNotification, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateNotification);
			Validation.responseKeyAndValue(updateNotification, "message","OK");

			userDeleted1 = Integer.parseInt(""+userDeleted+"");
			updateNotification = UpdateNotification.updateNotification(userIDC, userDeleted1, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(updateNotification, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateNotification);
			Validation.responseKeyAndValue(updateNotification, "message","OK");

			String consumerKey1 =null;
			String consumerSecret1=null;
			if(environMent.equals("Staging"))
			{consumerKey1 = JDBC_Queries.getCK(881, sqlhost, sqlUsername, sqlPassword);
			consumerSecret1 = JDBC_Queries.getSK(881, sqlhost, sqlUsername, sqlPassword);}


			Response fetchInstitutes = FetchInstitutes.fetchInstitutes(consumerKey1, consumerSecret1);
			Validation.responseHeaderCodeValidation(fetchInstitutes, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchInstitutes, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchInstitutes);
			Validation.responseKeyValidation_key(fetchInstitutes, "id");
			Validation.responseKeyValidation_key(fetchInstitutes, "email");
			Validation.responseKeyValidation_key(fetchInstitutes, "instituteId");
			int InstitutesID = fetchInstitutes.then().extract().path("institutes[0].instituteId");
			Log.info("InstitutesID : "+InstitutesID );


			Response fetchSingleinstitutes = FetchSingleinstitutes.fetchSingleinstitutes(InstitutesID, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(fetchSingleinstitutes, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchSingleinstitutes, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchSingleinstitutes);
			String instiName = fetchSingleinstitutes.then().extract().path("institutes[0].name");
			Log.info("instiName : "+instiName);
			int instituteId = fetchSingleinstitutes.then().extract().path("instituteId");
			Log.info("instituteId : "+instituteId);
			Validation.responseKeyValidation_key(fetchSingleinstitutes, "instituteId");
			Validation.responseKeyValidation_key(fetchSingleinstitutes, "id");
			Validation.responseKeyValidation_key(fetchSingleinstitutes, "admin");
			Validation.responseKeyValidation_key(fetchSingleinstitutes, "name");
			Validation.responseKeyValidation_key(fetchSingleinstitutes, "firstName");
			Validation.responseKeyValidation_key(fetchSingleinstitutes, "email");			
			Validation.responseKeyValidation_key(fetchSingleinstitutes, "admin");

			Response addInstitute = AddInstitute.addInstitute(consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(addInstitute, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(addInstitute, HttpStatus.SC_OK);
			Validation.responseTimeValidation(addInstitute);			
			Validation.responseKeyValidation_key(addInstitute, "instituteId");			
			int instituteId1 = fetchSingleinstitutes.then().extract().path("instituteId");
			Log.info("instituteId1 : "+instituteId1);

			Response fetchAllOrdersForInstitute = FetchAllOrdersForInstitute.fetchAllOrdersForInstitute(instituteId1, consumerKey, consumerSecret, "asdf234", deviceType);
			Validation.responseHeaderCodeValidation(fetchAllOrdersForInstitute, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchAllOrdersForInstitute, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchAllOrdersForInstitute);
			Validation.responseKeyAndValue(fetchAllOrdersForInstitute,"responseMsg","OK");

			String clientInstituteID =JDBC_Queries.getclientInstituteID(bookID1, instiName, client_Id, sqlhost, sqlUsername, sqlPassword);


			fetchAllOrdersForInstitute = FetchAllOrdersForInstitute.fetchAllOrdersForInstitute(clientInstituteID, consumerKey, consumerSecret, "asdf234", deviceType);
			Validation.responseHeaderCodeValidation(fetchAllOrdersForInstitute, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchAllOrdersForInstitute, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchAllOrdersForInstitute);
			Validation.responseKeyAndValue(fetchAllOrdersForInstitute,"responseMsg","OK");

			Response updateInstitue = UpdateInstitue.updateInstitue(instituteId1, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(updateInstitue, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(updateInstitue, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateInstitue);
			Validation.responseKeyAndValue(updateInstitue,"responseMsg","success");
			Validation.responseINTEGERKeyAndValue(updateInstitue, "instituteId", instituteId1);


			Response updateKitabooUser = UpdateKitabooUser.updateKitabooUser(email, clientUserID,password, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(updateKitabooUser, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(updateKitabooUser, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateKitabooUser);

			Response users = Users.users(consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(users, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(users, HttpStatus.SC_OK);
			Validation.responseTimeValidation(users);
			String username=users.then().extract().path("users[0].username");

			Response userExists = UserExists.userExists(username,consumerKey, consumerSecret);
			Validation.responseTimeValidation(userExists);
			Validation.responseKeyValidation_key(userExists, "true");

			Response bulkUsersRegistration = BulkUsersRegistration.bulkUsersRegistration(consumerKey, consumerSecret, emailS);
			Validation.responseHeaderCodeValidation(bulkUsersRegistration, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(bulkUsersRegistration, HttpStatus.SC_OK);
			Validation.responseTimeValidation(bulkUsersRegistration);
			Validation.responseKeyValidation_key(bulkUsersRegistration, "id");
			Validation.responseKeyValidation_key(bulkUsersRegistration, "trialUser");
			Validation.responseKeyValidation_key(bulkUsersRegistration, "clientUserID");

			bulkUsersRegistration = BulkUsersRegistration.bulkUsersRegistration_new(consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(bulkUsersRegistration, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(bulkUsersRegistration, HttpStatus.SC_OK);
			Validation.responseTimeValidation(bulkUsersRegistration);
			Validation.responseKeyValidation_key(bulkUsersRegistration, "id");
			//Validation.responseKeyValidation_key(bulkUsersRegistration, "level");
			Validation.responseKeyValidation_key(bulkUsersRegistration, "usernameForInstitute");
			Validation.responseKeyValidation_key(bulkUsersRegistration, "trialUser");
			Validation.responseKeyValidation_key(bulkUsersRegistration, "schema");
			Validation.responseKeyValidation_key(bulkUsersRegistration, "clientID");
			Validation.responseKeyValidation_key(bulkUsersRegistration, "trialUser");
			Validation.responseKeyValidation_key(bulkUsersRegistration, "clientUserID");

			String consumerKey11 =null;
			String consumerSecret11=null;
			if(environMent.equals("Staging"))
			{consumerKey11 = JDBC_Queries.getCK(881, sqlhost, sqlUsername, sqlPassword);
			consumerSecret11 = JDBC_Queries.getSK(881, sqlhost, sqlUsername, sqlPassword);}
			Response fetchInstitutes1 = FetchInstitutes.fetchInstitutes(consumerKey11, consumerSecret11);
			Validation.responseHeaderCodeValidation(fetchInstitutes1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchInstitutes1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchInstitutes1);
			Validation.responseKeyValidation_key(fetchInstitutes1, "id");
			Validation.responseKeyValidation_key(fetchInstitutes1, "email");
			Validation.responseKeyValidation_key(fetchInstitutes1, "instituteId");
			int InstitutesID1 = fetchInstitutes1.then().extract().path("institutes[0].instituteId");
			Log.info("InstitutesID : "+InstitutesID1);


			Response ssIServlet = SSIServlet.sSIServlet(bookID1, classID, firstName, lastName, InstitutesID1, "learner", userID, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(ssIServlet, HttpStatus.SC_OK);
			Validation.responseTimeValidation(ssIServlet);
			Validation.responseKeyValidation_key(ssIServlet, "readerurl");
			Validation.responseKeyValidation_key(ssIServlet, "subdomain");



			int clientClassID1 = JDBC_Queries.getclientClassID(bookID1, classID, sqlhost, sqlUsername, sqlPassword);
			Response fetchClassAnalytics = FetchClassAnalytics.fetchClassAnalytics(clientClassID1, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(fetchClassAnalytics, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchClassAnalytics, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchClassAnalytics);
			Validation.responseKeyValidation_key(fetchClassAnalytics, "tokenId");
			Validation.responseKeyAndValue(fetchClassAnalytics,"responseMsg","OK");
			String tokenId = fetchClassAnalytics.then().extract().path("tokenId");
			Log.info("tokenId : "+tokenId);


			Response fetchClientBookClassAnalytics = FetchClientBookClassAnalytics.fetchClientBookClassAnalytics(bookID1, consumerKey, consumerSecret);
			try {Validation.responseHeaderCodeValidation(fetchClientBookClassAnalytics, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchClientBookClassAnalytics, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchClientBookClassAnalytics);
			} catch (Exception e) {Log.fail(e.getMessage());}

			Response fetchClassAnalyticsResponse = FetchClassAnalyticsResponse.fetchClassAnalyticsResponse(tokenId, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(fetchClassAnalyticsResponse, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchClassAnalyticsResponse, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchClassAnalyticsResponse);

			Response fetchClientBookClassAnalytics1 = FetchClientBookClassAnalytics.fetchClientBookClassAnalytics_useID(bookID1, userID, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(fetchClientBookClassAnalytics1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchClientBookClassAnalytics1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchClientBookClassAnalytics1);
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics1, "analytics");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics1, "classAnalytics");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics1, "totalPages");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics1, "bookOpened");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics1, "bookAssigned");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics1, "pagesRead");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics1, "avgPageReadPerSession");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics1, "bookCompleted");

			Response fetchClientBookClassAnalytics_ClientClassID = FetchClientBookClassAnalytics.fetchClientBookClassAnalytics_ClientClassID(userID,bookID1, clientClassID1, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(fetchClientBookClassAnalytics_ClientClassID, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchClientBookClassAnalytics_ClientClassID, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchClientBookClassAnalytics_ClientClassID);
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics_ClientClassID, "analytics");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics_ClientClassID, "classAnalytics");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics_ClientClassID, "totalPages");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics_ClientClassID, "bookOpened");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics_ClientClassID, "bookAssigned");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics_ClientClassID, "pagesRead");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics_ClientClassID, "avgPageReadPerSession");
			Validation.responseKeyValidation_key(fetchClientBookClassAnalytics_ClientClassID, "bookCompleted");


			Response listBooks = ListBooks_OAuth.listBooks_OAuth_withoutpagi(consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(listBooks, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(listBooks, HttpStatus.SC_OK);
			Validation.responseTimeValidation(listBooks);
			Validation.responseKeyValidation_key(listBooks, "id");
			Validation.responseKeyValidation_key(listBooks, "isbn");
			Validation.responseKeyValidation_key(listBooks, "title");
			Validation.responseKeyValidation_key(listBooks, "thumbURL");
			Validation.responseKeyValidation_key(listBooks, "author");
			Validation.responseKeyValidation_key(listBooks, "createdOn");
			Validation.responseKeyValidation_key(listBooks, "category");
			Validation.responseKeyValidation_key(listBooks, "categoryList");
			Validation.responseKeyValidation_key(listBooks, "version");
			String clientBookID = listBooks.then().extract().path("bookList[3].book.clientBookID");
			Log.info("clientBookID : "+clientBookID);

			Response listBooksV1 = ListBooksV1_OAuth.listBooksV1_OAuth_without_pagi(consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(listBooksV1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(listBooksV1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(listBooksV1);
			Validation.responseKeyValidation_key(listBooksV1, "id");
			Validation.responseKeyValidation_key(listBooksV1, "isbn");
			Validation.responseKeyValidation_key(listBooksV1, "title");
			Validation.responseKeyValidation_key(listBooksV1, "thumbURL");
			Validation.responseKeyValidation_key(listBooksV1, "author");
			Validation.responseKeyValidation_key(listBooksV1, "createdOn");
			Validation.responseKeyValidation_key(listBooksV1, "category");
			Validation.responseKeyValidation_key(listBooksV1, "categoryList");
			Validation.responseKeyValidation_key(listBooksV1, "version");

			Response getChapterDetails = GetChapterDetails.getChapterDetails(clientBookID, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(getChapterDetails, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getChapterDetails, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getChapterDetails);

			long client_user_id=EpochTime.current();
			String usernameFinal=userNameT1+""+EpochTime.current()+"";
			String emailIDF = emailIDT1+""+EpochTime.current()+"@yopmail.com";
			Response registerUser1 = RegisterUser_ext.registerUser_ext(consumerKey, consumerSecret, emailIDF, firstNameT1, lastNameT1, usernameFinal,client_user_id);
			Validation.responseHeaderCodeValidation(registerUser1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(registerUser1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(registerUser1);
			Validation.responseKeyAndValue(registerUser1,"responseMsg","OK");
			Validation.responseKeyValidation_key(registerUser1, "user");
			Validation.responseKeyValidation_key(registerUser1, "id");
			Validation.responseKeyValidation_key(registerUser1, "usernameForInstitute");
			Validation.responseKeyValidation_key(registerUser1, "trialUser");
			Validation.responseKeyValidation_key(registerUser1, "user");


			Response updateUser = UpdateUser_OAuth.updateUser_OAuth(emailIDF, usernameFinal, passwordT, firstNameT1, lastNameT1, ""+client_user_id+"",consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(updateUser, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(updateUser, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateUser);
			Validation.responseKeyAndValue(updateUser,"responseMsg","OK");

			Response getUsers = Getusers.getusers_pagi(0, 100,consumerKey, consumerSecret, clientUserID);
			Validation.responseHeaderCodeValidation(getUsers, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getUsers, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getUsers);
			Validation.responseKeyValidation_key(getUsers, "userid");
			Validation.responseKeyValidation_key(getUsers, "username");
			Validation.responseKeyValidation_key(getUsers, "maxDeviceCount");
			Validation.responseKeyValidation_key(getUsers, "createDate");

			SaveSessionHistory.saveSessionHistory(userToken, "514185", deviceType,bookID1, time);

			Response fetchbookDeviceIDs = FetchbookDeviceIDs.fetchbookDeviceIDs(clientUserID, bookID1, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(fetchbookDeviceIDs, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchbookDeviceIDs, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchbookDeviceIDs);

			Response fetchDeviceIds = FetchDeviceIds.fetchDeviceIds("514185", consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(fetchDeviceIds, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchDeviceIds, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchDeviceIds);

			String time11 = time;
			SaveSessionHistory.saveSessionHistory(userToken, "514185", deviceType,bookID1, time);

			SimpleDateFormat formatter111 = new SimpleDateFormat("dd-MM-yyyy");
			String strDate= formatter111.format(date);
			System.out.println(strDate);
			Response fetchSessionHistory= FetchSessionHistory.fetchSessionHistory(time11, ""+strDate+" 20:50:20", consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(fetchSessionHistory, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchSessionHistory, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchSessionHistory);
			Validation.responseKeyAndValue(fetchSessionHistory,"responseMsg","OK");

			Response getUserBookDetails = GetUserBookDetails.getUserBookDetails(clientUserIDT, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(getUserBookDetails, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getUserBookDetails, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getUserBookDetails);
			Validation.responseKeyValidation_key(getUserBookDetails, "id");
			Validation.responseKeyValidation_key(getUserBookDetails, "username");
			Validation.responseKeyValidation_key(getUserBookDetails, "firstName");
			Validation.responseKeyValidation_key(getUserBookDetails, "email");
			Validation.responseKeyValidation_key(getUserBookDetails, "createdOn");
			Validation.responseKeyValidation_key(getUserBookDetails, "classes");
			Validation.responseKeyValidation_key(getUserBookDetails, "profilePicURL");
			Validation.responseKeyValidation_key(getUserBookDetails, "clientUserID");
			Validation.responseKeyValidation_key(getUserBookDetails, "userID");
			Validation.responseKeyValidation_key(getUserBookDetails, "instituteID");
			Validation.responseKeyValidation_key(getUserBookDetails, "clientID");
			Validation.responseKeyValidation_key(getUserBookDetails, "association");
			Validation.responseKeyValidation_key(getUserBookDetails, "userToken");
			Validation.responseKeyValidation_key(getUserBookDetails, "instituteID");
			Validation.responseKeyValidation_key(getUserBookDetails, "clientID");

			SimpleDateFormat formatter1111 = new SimpleDateFormat("dd-MM-yyyy");
			String strDate1= formatter1111.format(date);
			Log.info(strDate1);
			Response userLoginHistory = UserLoginHistory.userLoginHistory(time11, ""+strDate1+" 20:50:20", consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(userLoginHistory, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(userLoginHistory, HttpStatus.SC_OK);
			Validation.responseTimeValidation(userLoginHistory);
			Validation.responseKeyAndValue(userLoginHistory,"responseMsg","OK");


			Response listBooks1 = ListBooks_OAuth.listBooks_OAuth_withoutpagi(consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(listBooks1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(listBooks1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(listBooks1);
			String clientBookID1 = listBooks1.then().extract().path("bookList[3].book.clientBookID");
			Log.info("clientBookID1 : "+clientBookID1);
			Response getBookMetadata = GetBookMetadata.getBookMetadata(consumerKey, consumerSecret, clientBookID1);
			Validation.responseHeaderCodeValidation(getBookMetadata, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getBookMetadata, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getBookMetadata);
			Validation.responseKeyValidation_key(getBookMetadata, "book");
			Validation.responseKeyValidation_key(getBookMetadata, "flagForExt");
			Validation.responseKeyValidation_key(getBookMetadata, "clientBookId");
			Validation.responseKeyValidation_key(getBookMetadata, "kitabooId");
			Validation.responseKeyValidation_key(getBookMetadata, "isbn");
			Validation.responseKeyValidation_key(getBookMetadata, "title");
			Validation.responseKeyValidation_key(getBookMetadata, "base64");
			Validation.responseKeyValidation_key(getBookMetadata, "thumbnailURL");
			Validation.responseKeyValidation_key(getBookMetadata, "pages");
			Validation.responseKeyValidation_key(getBookMetadata, "version");
			Validation.responseKeyValidation_key(getBookMetadata, "categoryIdList");

			Response bookMetadata = BookMetadata.bookMetadata(consumerKey, consumerSecret, bookID2);
			Validation.responseHeaderCodeValidation(bookMetadata, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(bookMetadata, HttpStatus.SC_OK);
			Validation.responseTimeValidation(bookMetadata);
			Validation.responseKeyValidation_key(bookMetadata, "book");
			Validation.responseKeyValidation_key(bookMetadata, "flagForExt");
			Validation.responseKeyValidation_key(bookMetadata, "pagination");
			Validation.responseKeyValidation_key(bookMetadata, "clientBookId");
			Validation.responseKeyValidation_key(bookMetadata, "kitabooId");
			Validation.responseKeyValidation_key(bookMetadata, "isbn");
			Validation.responseKeyValidation_key(bookMetadata, "title");
			Validation.responseKeyValidation_key(bookMetadata, "thumbnail");
			Validation.responseKeyValidation_key(bookMetadata, "base64");
			Validation.responseKeyValidation_key(bookMetadata, "pages");
			Validation.responseKeyValidation_key(bookMetadata, "version");
			Validation.responseKeyValidation_key(bookMetadata, "formats");
			Validation.responseKeyValidation_key(bookMetadata, "author");
			Validation.responseKeyValidation_key(bookMetadata, "description");
			Validation.responseKeyValidation_key(bookMetadata, "subject");
			Validation.responseKeyValidation_key(bookMetadata, "categoryIdList");
			String clientBookId = bookMetadata.then().extract().path("book.clientBookId");
			Log.info("clientBookId : "+clientBookId);
			String clientcategory = bookMetadata.then().extract().path("book.category");
			Log.info("clientcategory : "+clientcategory);
			String kitabooId = bookMetadata.then().extract().path("book.kitabooId");
			Log.info("kitabooId : "+kitabooId);			
			String isbn = bookMetadata.then().extract().path("book.isbn");
			Log.info("isbn : "+isbn);

			Response updateClientBookId = UpdateClientBookId.updateClientBookId(bookID1, clientBookID1, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(updateClientBookId, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(updateClientBookId, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateClientBookId);
			Validation.responseKeyAndValue(updateClientBookId, "responseMsg","SUCCESS");

			updateClientBookId = UpdateClientBookId.updateClientBookId(bookID1, clientBookID1, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(updateClientBookId, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(updateClientBookId, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateClientBookId);
			Validation.responseKeyAndValue(updateClientBookId, "responseMsg","SUCCESS");


			String[] formate11 ={"html5,android,ipad","fixed_epub","reflow_epub","author_epub"};
			for(int i2=0;i2<=3;i2++)
			{Response registerBook = RegisterBook.registerBook(formate11[i2],category1,kitabooId, clientBookId, isbn, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(registerBook, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(registerBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(registerBook);

			registerBook = RegisterBook.registerBook_char(formate11[i2],category1,kitabooId,clientBookId, isbn, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(registerBook, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(registerBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(registerBook);

			registerBook = RegisterBook.registerBook_charAA(formate11[i2],category1,kitabooId,clientBookId, isbn, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(registerBook, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(registerBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(registerBook);

			registerBook = RegisterBook.registerBook_Roman(formate11[i2],kitabooId,category1,clientBookId, isbn, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(registerBook, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(registerBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(registerBook);
			}

			Response saveLastPageAccessed = SaveLastPageAccessed.saveLastPageAccessed(bookID1, 1, userToken, "trewq4323", deviceType);
			Validation.responseHeaderCodeValidation(saveLastPageAccessed, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(saveLastPageAccessed, HttpStatus.SC_OK);
			Validation.responseTimeValidation(saveLastPageAccessed);

			Response updateBookMetadata = UpdateBookMetadata.updateBookMetadata(clientcategory, clientBookId, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(updateBookMetadata, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(updateBookMetadata, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateBookMetadata);

			Response getUsers1 = Getusers.getusers_pagi(0, 100,consumerKey, consumerSecret, clientUserID);
			Validation.responseHeaderCodeValidation(getUsers1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getUsers1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getUsers1);
			Validation.responseKeyValidation_key(getUsers1, "userid");
			Validation.responseKeyValidation_key(getUsers1, "username");
			Validation.responseKeyValidation_key(getUsers1, "maxDeviceCount");
			Validation.responseKeyValidation_key(getUsers1, "createDate");

			Response getClientUsers = GetClientUsers.getClientUsers_Pagi(0,100,consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(getClientUsers, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getClientUsers, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getClientUsers);
			Validation.responseKeyValidation_key(getClientUsers, "userid");
			Validation.responseKeyValidation_key(getClientUsers, "username");
			Validation.responseKeyValidation_key(getClientUsers, "maxDeviceCount");
			Validation.responseKeyValidation_key(getClientUsers, "createDate");
			Validation.responseKeyValidation_key(getClientUsers, "lastAccessDate");
			Validation.responseKeyValidation_key(getClientUsers, "active");


			Response getLastPageAccessed1 = GetLastPageAccessed.getLastPageAccessed(bookID1, userToken, "hgfd234", deviceType);
			Validation.responseHeaderCodeValidation(getLastPageAccessed1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getLastPageAccessed1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getLastPageAccessed1);

			Response updateClientUserID = UpdateClientUserID.updateClientUserID(clientUserID,"Test1", consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(updateClientUserID, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(updateClientUserID, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateClientUserID);

			updateClientUserID = UpdateClientUserID.updateClientUserID("Test1", clientUserID, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(updateClientUserID, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(updateClientUserID, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateClientUserID);


			Response changePassword1 = ChangePassword.changePassword(email, password,password, userToken, "sdf343", deviceType);
			Validation.responseHeaderCodeValidation(changePassword1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(changePassword1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(changePassword1);

			Response epubStatus = EpubStatus.epubStatus(consumerKey, consumerSecret, epubId);
			Validation.responseHeaderCodeValidation(epubStatus, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(epubStatus, HttpStatus.SC_OK);
			Validation.responseTimeValidation(epubStatus);

			Response bulkDownloadBook = BulkDownloadBook.bulkDownloadBook(userToken, "we24", deviceType, bookID1, bookID2, bookID2, "online");
			Validation.responseHeaderCodeValidation(bulkDownloadBook, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(bulkDownloadBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(bulkDownloadBook);

			//String[] searchTEXT = title.trim().split(" ");
			String searchTEXT = title.substring(0,4);
			Response searchV2_AdvanceFilter = SearchV2_AdvanceFilter.searchV2_AdvanceFilter(searchTEXT, userToken, "fghtyu", deviceType, clientUserID);
			Validation.responseHeaderCodeValidation(searchV2_AdvanceFilter, HttpStatus.SC_OK);
			//Validation.responseCodeValidation1(searchV2_AdvanceFilter, HttpStatus.SC_OK);
			Validation.responseTimeValidation(searchV2_AdvanceFilter);


			Response readingSpeed = ReadingSpeed.readingSpeed(bookID1, bookID2, bookID3, bookID4, bookID5,userToken, "234we", deviceType);
			Validation.responseHeaderCodeValidation(readingSpeed, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(readingSpeed, HttpStatus.SC_OK);
			Validation.responseTimeValidation(readingSpeed);


			if(catlevel.contains ("1")){}			
			else{Response multiCategories_res = MultiCategories.multiCategories(catlevel,userToken,"fs445",deviceType);
			Validation.responseHeaderCodeValidation(multiCategories_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(multiCategories_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(multiCategories_res);
			Validation.responseKeyValidation_key(multiCategories_res, "categories");
			Validation.responseKeyValidation_key(multiCategories_res, "collectionCount");
			Validation.responseKeyValidation_key(multiCategories_res, "bookCount");
			Validation.responseKeyValidation_key(multiCategories_res, "hash");
			Validation.responseKeyValidation_key(multiCategories_res, "id");}



			if(catlevel.contains ("1")){}			
			else{Response MultiCategoryBookList_res = MultiCategoryBookList.multiCategoryBookList(catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceType);
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
			else{Response MultiCategoryCollection_BookList=MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel, bookID1, sqlhost,sqlUsername,sqlPassword, userToken, "4524242",deviceType, collectionName0);
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
			//Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "keywords");
			Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "pages");
			Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "readingPercentage");}


			Response userAssignedBooks_res = UserAssignedBooks_OAuth.userAssignedBooks_OAuth(consumerKey, consumerSecret,clientUserID);
			Validation.responseHeaderCodeValidation(userAssignedBooks_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(userAssignedBooks_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(userAssignedBooks_res);
			Validation.responseKeyValidation_key(userAssignedBooks_res, "assignedOn");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "author");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "bookActive");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "category");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "categoryIdList");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "categoryList");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "description");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "id");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "isbn");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "keywords");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "language");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "pages");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "refId");
			Validation.responseKeyValidation_key(userAssignedBooks_res, "version");
			//Validation.responseKeyValidation_key(userAssignedBooks_res, "expiryDate");
			//Validation.responseKeyAndValue(userAssignedBooks_res, "expiryDate", "PERPETUAL");

			Response userAssignedBooks_withPagi_Res = UserAssignedBooks_OAuth.userAssignedBooks_OAuth_with_pagi(0, 80, consumerKey, consumerSecret,clientUserID);
			Validation.responseHeaderCodeValidation(userAssignedBooks_withPagi_Res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(userAssignedBooks_withPagi_Res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(userAssignedBooks_withPagi_Res);
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "assignedOn");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "author");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "bookActive");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "category");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "categoryIdList");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "categoryList");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "description");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "id");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "isbn");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "keywords");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "language");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "pages");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "refId");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "version");
			Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "totalbooks");
			//Validation.responseKeyValidation_key(userAssignedBooks_withPagi_Res, "expiryDate");
			int totalbooks1 = userAssignedBooks_withPagi_Res.then().extract().path("totalbooks");
			Log.info("totalbooks: "+totalbooks1);
			Validation.responseISGreater("totalbooks",totalbooks1, 3);
			//Validation.responseKeyAndValue(userAssignedBooks_withPagi_Res, "expiryDate", "PERPETUAL");


			Response moreInfoViewList = MoreInfoViewList.moreInfoViewList_pagi(0,100,userToken, "fds543", deviceType);
			Validation.responseHeaderCodeValidation(moreInfoViewList, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(moreInfoViewList, HttpStatus.SC_OK);
			Validation.responseTimeValidation(moreInfoViewList);


			Response deRegisterUser = DeRegisterUser.deRegisterUser(userToken, "kjh098", deviceType);
			Validation.responseHeaderCodeValidation(deRegisterUser, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(deRegisterUser, HttpStatus.SC_OK);
			Validation.responseTimeValidation(deRegisterUser);

		
			Response banners = Banners.banners_pagi(0,100,userToken, "asd4544", deviceType);
			Validation.responseHeaderCodeValidation(banners, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(banners, HttpStatus.SC_OK);
			Validation.responseTimeValidation(banners);

			Response like = Vote.Vote_like(bookID1, userToken, "gf78", deviceType);
			Validation.responseHeaderCodeValidation(like, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(like, HttpStatus.SC_OK);
			Validation.responseTimeValidation(like);			

			Response Dislike = Vote.Vote_Dislike(bookID1, userToken, "gf78", deviceType);
			Validation.responseHeaderCodeValidation(Dislike, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(Dislike, HttpStatus.SC_OK);
			Validation.responseTimeValidation(Dislike);			

			Response Reset = Vote.Vote_Reset(bookID1, userToken, "gf78", deviceType);
			Validation.responseHeaderCodeValidation(Reset, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(Reset, HttpStatus.SC_OK);
			Validation.responseTimeValidation(Reset);

			Response bulkReadingSpeed = BulkReadingSpeed.bulkReadingSpeed(bookID1, bookID2, bookID3, userToken, "gf45", deviceType);
			Validation.responseHeaderCodeValidation(bulkReadingSpeed, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(bulkReadingSpeed, HttpStatus.SC_OK);
			Validation.responseTimeValidation(bulkReadingSpeed);

			Response books1 = Books.books(userToken, "sd234", deviceType);
			Validation.responseCodeValidation1(books1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(books1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(books1);


			String searchTEXT1 = title.substring(0,5);
			Response clientUserID_books = ClientUserID_books.clientUserID_books(consumerKey, consumerSecret, searchTEXT1, clientUserID);
			Validation.responseCodeValidation1(clientUserID_books, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(clientUserID_books, HttpStatus.SC_OK);
			Validation.responseTimeValidation(clientUserID_books);


			Response compUserBook = CompUserBook.compUserBook(bookID2,EpochTime.current(),"RestAPISK12_",100,consumerKey,consumerSecret);
			Validation.responseCodeValidation1(compUserBook, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(compUserBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(compUserBook);


			String isbn_reflow = JDBC_Queries.isbn_Title("Upload_Reflow_3jully", sqlhost, sqlUsername, sqlPassword);

			Response epubcontentextract = Epubcontentextract.epubcontentextract(isbn_reflow,consumerKey, consumerSecret);
			Validation.responseCodeValidation1(epubcontentextract, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(epubcontentextract, HttpStatus.SC_OK);
			Validation.responseTimeValidation(epubcontentextract);
			String contenturl = epubcontentextract.then().extract().path("contenturl");
			Log.info("contenturl : "+contenturl);

			Response contenturlHIT = ContenturlHIT.contenturlHIT(contenturl);
			Validation.responseCodeValidation1(contenturlHIT, HttpStatus.SC_OK);


			Response resetDevices_clientUserID = ResetDevice.resetDevices_clientUserID(consumerKey, consumerSecret, clientUserID);
			Validation.responseCodeValidation1(resetDevices_clientUserID, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(resetDevices_clientUserID, HttpStatus.SC_OK);
			Validation.responseTimeValidation(resetDevices_clientUserID);
			Validation.responseKeyAndValue(resetDevices_clientUserID, "responseMsg","Ok");

			Response resetDevices_userName = ResetDevice.resetDevices_userName(consumerKey, consumerSecret,userName);
			Validation.responseCodeValidation1(resetDevices_userName, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(resetDevices_userName, HttpStatus.SC_OK);
			Validation.responseTimeValidation(resetDevices_userName);
			Validation.responseKeyAndValue(resetDevices_userName, "responseMsg","Ok");



			isbnMeta = "1212121212121";
			Log.info("isbnMeta: "+isbnMeta);
			//String consumerKey, String consumerSecret,String string,String title,String author,String cat4
			/*Response Metadata_res = Metadata.metadata(consumerKey, consumerSecret,isbnMeta,"Reflow_"+isbnMeta+"","Reflow_"+isbnMeta+"",catlevel,"description");
			Validation.responseHeaderCodeValidation(Metadata_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(Metadata_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(Metadata_res);
			Validation.responseKeyValidation_key(Metadata_res, "isbn");
			Validation.responseKeyValidation_key(Metadata_res, "The request for the uploadEpub taken successfully.");*/


			String filepath = "https://hurix-staging-content.s3.amazonaws.com/test/1212121212121.epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1608523234&Signature=m%2BvmfWSzjUlNSVIorqDdmnS69F4%3D";
			Log.info("filepath : "+filepath);				
			Response IngectEpub_res1 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+filepath+"");
			Log.info("URL : "+detail);
			Validation.responseHeaderCodeValidation(IngectEpub_res1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(IngectEpub_res1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(IngectEpub_res1);
			Validation.responseKeyValidation_key(IngectEpub_res1, "The request for the uploadEpub taken successfully.");



			isbnIng = IngectEpub_res1.then().extract().path("isbn");			
			Log.info("isbnIng: "+isbnIng);
			Thread.sleep(4000);
			Response IngestionStatus_res = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
			Validation.responseHeaderCodeValidation(IngestionStatus_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(IngestionStatus_res, HttpStatus.SC_OK);
			Validation.responseINTEGERKeyAndValue(IngestionStatus_res, "status", 100);	


			Response updatemeta = UpdateMetadata.updateMetadata(consumerKey, consumerSecret,isbnIng,"Reflow_"+isbnIng+"_UPD","Reflow_"+isbnIng+"_UPD",catlevel,"description");
			Validation.responseHeaderCodeValidation(updatemeta, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(updatemeta, HttpStatus.SC_OK);
			//Validation.responseKeyValidation_Str(updatemeta, "The request for the update ePub metadata completed successfully");
			Validation.responseKeyValidation_key(updatemeta, "The request for the update ePub metadata completed successfully");

			Thread.sleep(5000);
			IngestionStatus_res = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
			Validation.responseHeaderCodeValidation(IngestionStatus_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(IngestionStatus_res, HttpStatus.SC_OK);
			Validation.responseINTEGERKeyAndValue(IngestionStatus_res, "status", 100);	


			Response getRawTrackingData = GetRawTrackingData.getRawTrackingV1("2019-04-30 18:18:40","2019-04-30 18:20:40",1,consumerKey,consumerSecret);
			try {Validation.responseHeaderCodeValidation(getRawTrackingData, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getRawTrackingData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getRawTrackingData);
			} catch (Exception e) {	Log.fail(e.getMessage());}
			


			getRawTrackingData = GetRawTrackingData.getRawTracking("2019-04-30 19:18:40","2019-04-30 18:20:40",consumerKey,consumerSecret);
			Validation.responseHeaderCodeValidation(getRawTrackingData, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getRawTrackingData, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getRawTrackingData);
			}
			}
		}catch (AssertionError exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			//exp.printStackTrace();
		}
	}
}
