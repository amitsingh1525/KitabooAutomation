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

public class DIS_1763 {

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
	public static int bookID20;
	public static int bookID21;
	public static int bookID22;
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
	public static String detail;
	public static String sqlhost;
	public static String sqlUsername;
	public static String sqlPassword;
	public static String externalURI;
	public static String clientID;
	public static String catlevel;
	public static String  deviceT;
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
	public static String title;
	public static String runY_N;
	//public static String consumerKey=ExcelUtils.Consumer_key;
	//public static String consumerSecret=ExcelUtils.secret_key;

	public static void   main(String []args) throws SQLException, JSONException{
		Log.initialization("DIS-1763");	
		//List<String> detail =  ExcelUtils.getuserDetails();
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			String excelPath="./testData/DIS-1763.xlsx";
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

			clientID = JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			Log.startTestCase("Authenticate");
			Log.info("detail : "+detail);
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			Log.info("clientID : "+clientID);
			clientID =JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			Log.info("ReaderKey : "+clientID);
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
			Log.endTestCase("End");

			consumerKey = JDBC_Queries.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			consumerSecret = JDBC_Queries.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);


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
			if(catlevel .contains("1"))
			{archiveDate1 = fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[0]");
			System.out.println("archiveDate:"+archiveDate);
			archiveDate6=fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[1]");
			System.out.println("archiveDate:"+archiveDate);	}
			if(catlevel .contains("2"))
			{archiveDate = fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[0]");
			System.out.println("archiveDate:"+archiveDate);
			archiveDate6=fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[1]");
			System.out.println("archiveDate:"+archiveDate);	}

			if(catlevel.contains("1"))
			{bookID20=JDBC_Queries.getBookId(client_Id,"Audio_cat1_UPD",sqlhost,sqlUsername,sqlPassword);
			category1=JDBC_Queries.getCategoryNAME(client_Id, "Audio_cat1_UPD", sqlhost, sqlUsername, sqlPassword);
			bookID20=fetchBookList_without_pagination.then().extract().path("bookList.book.id[23]");
			System.out.println("bookID20:"+bookID20);}
			else
			{bookID20=JDBC_Queries.getBookId(client_Id,"Audio_cat2_UPD",sqlhost,sqlUsername,sqlPassword);
			category1=JDBC_Queries.getCategoryNAME(client_Id, "Audio_cat2_UPD", sqlhost, sqlUsername, sqlPassword);
			bookID20=fetchBookList_without_pagination.then().extract().path("bookList.book.id[26]");
			System.out.println("bookID20:"+bookID20);}
			Response downloadBookForANDROID_online1 = DownloadBook.downloadBook(userToken,"ds9465","FIXED_EPUB_IMAGE",bookID20,"online");
			System.out.println("downloadBookForANDROID_online1 : "+downloadBookForANDROID_online1);
			Validation.responseHeaderCodeValidation(downloadBookForANDROID_online1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(downloadBookForANDROID_online1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBookForANDROID_online1);
			downloadBookForANDROID_online1 = DownloadBook.downloadBook(userToken,"ds9465","HTML5",bookID20,"offline");
			Validation.responseHeaderCodeValidation(downloadBookForANDROID_online1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(downloadBookForANDROID_online1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBookForANDROID_online1);

			downloadBookForANDROID_online1 = DownloadBook.downloadBook(userToken,"ds9465","ANDROID",bookID20,"online");
			Validation.responseHeaderCodeValidation(downloadBookForANDROID_online1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(downloadBookForANDROID_online1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBookForANDROID_online1);
			downloadBookForANDROID_online1 = DownloadBook.downloadBook(userToken,"ds9465","ANDROID",bookID20,"offline");
			Validation.responseHeaderCodeValidation(downloadBookForANDROID_online1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(downloadBookForANDROID_online1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBookForANDROID_online1);

			Response catbooklistv1 =null;
			if(catlevel .contains("1"))
			{catbooklistv1 = CategoryBookListV1.categoryBookListV1_cat(category1, userToken, "das3232", deviceT, bookID20, catlevel, sqlhost,sqlUsername,sqlPassword);}
			
			if(catlevel .contains("2"))
			{catbooklistv1 = CategoryBookListV1.categoryBookListV1(category1, userToken, "das3232", deviceT, bookID20, catlevel, sqlhost,sqlUsername,sqlPassword);}
			Validation.responseHeaderCodeValidation(catbooklistv1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(catbooklistv1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(catbooklistv1);
			Validation.responseKeyValidation_key(catbooklistv1,"archiveDate");
			Validation.responseKeyValidation_key(catbooklistv1,"category");
			Validation.responseKeyValidation_key(catbooklistv1,"assetType");
			Validation.responseKeyValidation_key(catbooklistv1,"assignedOn");
			Validation.responseKeyValidation_key(catbooklistv1,"author");
			Validation.responseKeyValidation_key(catbooklistv1,"bookId");
			Validation.responseKeyValidation_key(catbooklistv1,"bookLikeCount");
			Validation.responseKeyValidation_key(catbooklistv1,"categoryIdList");
			Validation.responseKeyValidation_key(catbooklistv1,"categoryList");
			Validation.responseKeyValidation_key(catbooklistv1,"formats");
			Validation.responseKeyValidation_key(catbooklistv1,"pages");
			Validation.responseKeyValidation_key(catbooklistv1,"readingPercentage");
			Validation.responseKeyValidation_key(catbooklistv1,"reflow");
			Validation.responseKeyValidation_key(catbooklistv1,"thumbURL");
			Validation.responseKeyValidation_key(catbooklistv1,"version");
			Validation.responseKeyValidation_key(catbooklistv1,"isbn");
			Validation.responseKeyValidation_key(catbooklistv1,"id");
			Validation.responseKeyValidation_key(catbooklistv1,"title");
			System.out.println("catbooklistv1 : "+catbooklistv1);


			Response CategoryBookListV2Res =null;
			if(catlevel.contains ("1"))
			{category1=JDBC_Queries.getCategoryNAME(client_Id, "Audio_cat1_UPD", sqlhost, sqlUsername, sqlPassword);
			CategoryBookListV2Res = CategoryBookListV2.categoryBookListV2_cat(category1,userToken,"56454", deviceT,bookID20,catlevel,sqlhost,sqlUsername,sqlPassword);
			}
			else //if(catlevel.contains ("2"))
			{category1=JDBC_Queries.getCategoryNAME(client_Id, "Audio_cat2_UPD", sqlhost, sqlUsername, sqlPassword);}
			CategoryBookListV2Res = CategoryBookListV2.categoryBookListV2(category1,userToken,"56454", deviceT,bookID20,catlevel,sqlhost,sqlUsername,sqlPassword);
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
			System.out.println("CategoryBookListV2Res : " +CategoryBookListV2Res);

			/*bookID20=SELECT id FROM cloudCore.COLLECTION_BOOK_MAP WHERE BOOK_ID=(SELECT book_package_id FROM cloudCore.BOOKS WHERE client_id = 88 
				AND  title = 'Audio_cat1_UPD')*/
			Response CategoryBookListV2Res_withpagi = CategoryBookListV2.categoryBookListV2_withpagi(0,10,""+category1+"",userToken,"56454",deviceT,bookID20,catlevel,sqlhost,sqlUsername,sqlPassword);
			Validation.responseHeaderCodeValidation(CategoryBookListV2Res_withpagi, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(CategoryBookListV2Res_withpagi, HttpStatus.SC_OK);
			Validation.responseTimeValidation(CategoryBookListV2Res_withpagi);
			totalbooks=CategoryBookListV2Res_withpagi.then().extract().path("totalbooks");
			Log.info("totalbooks : "+totalbooks);
			Validation.responseISGreater("totalbooks", totalbooks, 3);
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"archiveDate");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"category");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"assetType");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"assignedOn");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"author");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"bookId");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"bookLikeCount");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"categoryIdList");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"categoryList");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"formats");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"pages");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"readingPercentage");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"reflow");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"thumbURL");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"version");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"isbn");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"id");
			Validation.responseKeyValidation_key(CategoryBookListV2Res_withpagi,"title");

			Response Booklist = BookList.bookList(userToken,"56454",deviceT);
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
			System.out.println("Booklist_res : "+Booklist);

			Response refreshBookListres = RefreshBookList.refreshBookList(userToken,"56496",deviceT);
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
			System.out.println("refreshBookListres : "+refreshBookListres);


			//2019/10/31 14:46:04  //
			if(catlevel .contains("1"))				
			{archiveDate = JDBC_Queries.getArchiveDate(client_Id, "Audio_cat1_UPD", sqlhost, sqlUsername, sqlPassword);}
			else if(catlevel .contains("2"))				
			{archiveDate = JDBC_Queries.getArchiveDate(client_Id, "Audio_cat2_UPD", sqlhost, sqlUsername, sqlPassword);}
			Response v1refreshBookList_res  =V1refreshBookList.v1refreshBookList(archiveDate,"NEW","UPDATE",bookID20,bookID2,userToken,"56454", deviceT,clientID);
			Log.info("archiveDate : "+archiveDate);
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
			System.out.println("v1/v1refreshBookList_res : "+v1refreshBookList_res);

			if(catlevel.contains ("1")){}			
			else{Response MultiCategoryBookList_res = MultiCategoryBookList.multiCategoryBookList(catlevel,bookID20,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
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
			Validation.responseKeyValidation_key(MultiCategoryBookList_res, "readingPercentage");
			System.out.println("MultiCategoryBookList_res : "+MultiCategoryBookList_res);}

			Response MultiCategoryCollection_BookList=MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel, bookID20, sqlhost,sqlUsername,sqlPassword, userToken, "4524242",deviceT, collectionName0);
			if(catlevel.contains ("1")){}	
			else{Validation.responseHeaderCodeValidation(MultiCategoryCollection_BookList, HttpStatus.SC_OK);
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
			Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "readingPercentage");
			System.out.println("MultiCategoryCollection_BookList : "+MultiCategoryCollection_BookList);}				


			Response fetchCategoriesCollectionsres =FetchCategoriesCollections.fetchCategoriesCollections(userToken, "5489989",deviceT);
			Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsres, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchCategoriesCollectionsres, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchCategoriesCollectionsres);
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"categories");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"name");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"numberOfBooks");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"collections");
			Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"name");
			System.out.println("fetchCategoriesCollectionsres : "+fetchCategoriesCollectionsres);

			Response fetchCategoriesCollectionsBooksres =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks(userToken, "5489989",deviceT,"level4_upd",collectionName0,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
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
			System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooksres);

			Response markAsFavourite_res = MarkAsFavourite.markAsFavourite(bookID20,userToken,"45564595",deviceT);
			BookID_mark1 = bookID20;
			Validation.responseHeaderCodeValidation(markAsFavourite_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(markAsFavourite_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(markAsFavourite_res);
			System.out.println("markAsFavourite_res : "+markAsFavourite_res);

			Response markAsFavourite_res1 = MarkAsFavourite.markAsFavourite(bookID2,userToken,"45564595",deviceT);
			BookID_mark2 = bookID2;
			Validation.responseHeaderCodeValidation(markAsFavourite_res1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(markAsFavourite_res1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(markAsFavourite_res1);
			System.out.println("markAsFavourite_res : "+markAsFavourite_res1);


			Response FetchFavouriteBooks_res = FetchFavouriteBooks.fetchFavouriteBooks(userToken,"45564595",deviceT);
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
			System.out.println("FetchFavouriteBooks_res : "+FetchFavouriteBooks_res);

			Response unMarkAsFavourite_res = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark1,userToken,"45564595",deviceT);
			Validation.responseHeaderCodeValidation(unMarkAsFavourite_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(unMarkAsFavourite_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(unMarkAsFavourite_res);
			System.out.println("unMarkAsFavourite_res : "+unMarkAsFavourite_res);

			Response unMarkAsFavourite_res1 = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark2,userToken,"45564595",deviceT);
			Validation.responseHeaderCodeValidation(unMarkAsFavourite_res1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(unMarkAsFavourite_res1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(unMarkAsFavourite_res1);
			System.out.println("unMarkAsFavourite_res : "+unMarkAsFavourite_res1);

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
			System.out.println("books_OAuthres : " +books_OAuthres);

			Response downloadBookForANDROID_offline = DownloadBook.downloadBook(userToken,"ds9465",deviceT,bookID20,"offline");
			
			Validation.responseCodeValidation1(downloadBookForANDROID_offline, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBookForANDROID_offline);
			Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"fileSize");
			Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"responseMsg");
			Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"timestamp");
			System.out.println("downloadBookForANDROID_offline: "+downloadBookForANDROID_offline);


			Response downloadBookForANDROID_online = DownloadBook.downloadBook(userToken,"ds9465",deviceT,bookID20,"online");
			Validation.responseHeaderCodeValidation(downloadBookForANDROID_online, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(downloadBookForANDROID_online, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBookForANDROID_online);
			Validation.responseKeyValidation_key(downloadBookForANDROID_online,"fileSize");
			Validation.responseKeyValidation_key(downloadBookForANDROID_online,"responseMsg");
			Validation.responseKeyValidation_key(downloadBookForANDROID_online,"timestamp");
			System.out.println("downloadBookForANDROID_online: "+downloadBookForANDROID_online);

			downloadBookForANDROID_offline = DownloadBook.downloadBook(userToken,"ds9465","HTML5",bookID20,"offline");
			Validation.responseCodeValidation1(downloadBookForANDROID_offline, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBookForANDROID_offline);
			Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"fileSize");
			Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"responseMsg");
			Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"timestamp");
			System.out.println("downloadBookForANDROID_offline: "+downloadBookForANDROID_offline);


			downloadBookForANDROID_online = DownloadBook.downloadBook(userToken,"ds9465","HTML5",bookID20,"online");
			Validation.responseHeaderCodeValidation(downloadBookForANDROID_online, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(downloadBookForANDROID_online, HttpStatus.SC_OK);
			Validation.responseTimeValidation(downloadBookForANDROID_online);
			//Validation.responseKeyValidation_key(downloadBookForANDROID_online,"fileSize");
			Validation.responseKeyValidation_key(downloadBookForANDROID_online,"responseMsg");
			Validation.responseKeyValidation_key(downloadBookForANDROID_online,"timestamp");
			System.out.println("downloadBookForANDROID_online: "+downloadBookForANDROID_online);

			assetType = fetchBookList_without_pagination.then().extract().path("bookList.book.assetType[1]");
			Log.info("assetType: "+assetType);
			assetType = fetchBookList_without_pagination.then().extract().path("bookList.book.assetType[2]");
			Log.info("assetType: "+assetType);
			assetType=JDBC_Queries.getAsset(client_Id, "Audio_cat1_UPD", sqlhost, sqlUsername, sqlPassword);
			Response bulkDownloadBook = null;
			if(catlevel.contains("1"))					
			{bulkDownloadBook= BulkDownloadBook.bulkDownloadBook(userToken, "GHgah242",deviceT, bookID20, bookID2, bookID3, "offline");}
			else if(catlevel.contains("2"))					
			{bulkDownloadBook= BulkDownloadBook.bulkDownloadBook(userToken, "GHgah242", deviceT, bookID20, bookID2,bookID3,"offline");
			bulkDownloadBook= BulkDownloadBook.bulkDownloadBook(userToken, "GHgah242", "ANDROID", bookID20, bookID2,bookID3,"offline");}
			Validation.responseHeaderCodeValidation(bulkDownloadBook, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(bulkDownloadBook, HttpStatus.SC_OK);
			Validation.responseTimeValidation(bulkDownloadBook);
			//Validation.responseKeyValidation_key(bulkDownloadBook,"fileSize");
			Validation.responseKeyValidation_key(bulkDownloadBook,"responseMsg");
			Validation.responseKeyValidation_key(bulkDownloadBook,"timestamp");
			System.out.println("bulkDownloadBook : "+bulkDownloadBook);
			}

			}
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");


	}	

}
