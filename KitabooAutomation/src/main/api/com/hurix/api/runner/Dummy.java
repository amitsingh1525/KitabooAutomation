package com.hurix.api.runner;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hurix.api.externalAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.readerAPIs.RegisterUser;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class Dummy {

	public static String consumerKey;
	public static String consumerSecret;
	public static String excelPath;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static String environMent;
	public static String userName;
	public static String password;
	public static String detail;
	public static String firstName;
	public static String lastName;
	public static String clientID;
	public static String catlevel;
	public static String userName1;
	public static String email;
	public static String title;
	public static int  bookID1;
	public static int bookID2;
	public static int bookID3;
	public static int bookID6;
	static String isbn;
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
	public static String totalbooks;
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
			excelPath="./testData/Staging_Sanity.xlsx";
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
			String deviceLimit = formatter.formatCellValue(sheet.getRow(i).getCell(6));
			String type1 = formatter.formatCellValue(sheet.getRow(i).getCell(7));
			String  filePath =formatter.formatCellValue(sheet.getRow(i).getCell(8));
			String collectionRefID=formatter.formatCellValue(sheet.getRow(i).getCell(10));


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

			Log.info("deviceLimit : +deviceLimit");
			int deviceLimit1 = Integer.parseInt(""+deviceLimit+"");
			int typeF = Integer.parseInt(""+type1+"");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
			Date date = new Date();  
			String  time=formatter1.format(date);
			clientID = JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);


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
			Log.info("HERE_After");
			userName = authenticateValue.then().extract().path("user.userName");
			Validation.responseKeyAndValue(authenticateValue, "userName", userName);
			userID = authenticateValue.then().extract().path("user.id");
			Log.info("userID: "+userID);
			String userToken = authenticateValue.then().extract().path("userToken");
			Log.info("userToken:"+userToken);
			String clientUserID = authenticateValue.then().extract().path("user.clientUserID");
			Log.info("clientUserID:"+clientUserID);
			int client_Id = authenticateValue.then().extract().path("user.clientID");
			Log.info("client_Id:"+client_Id);
			firstName = authenticateValue.then().extract().path("user.firstName");
			Log.info("firstName:"+firstName);
			lastName = authenticateValue.then().extract().path("user.lastName");
			Log.info("lastName:"+lastName);
			Log.info("HEWEWEWEWE");
			userName1 = authenticateValue.then().extract().path("user.userName");
			Log.info("userName1:"+""+userName1+"");
			email = authenticateValue.then().extract().path("user.email");
			Log.info("email:"+email);

			Log.endTestCase("End");

			consumerKey = JDBC_Queries.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			consumerSecret = JDBC_Queries.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);


			/*Response fetchBookList_with_pagination = FetchBookList.fetchBookList_with_pagination(0,15,userToken,"45616452",deviceType);
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
			Validation.responseISGreater("totalbooks", totalbooks, 4);*/

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
			bookID6 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[6]");
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

			/*Response GETfetchBookCount_res = FetchBookCount.fetchBookCount(userToken,"45616452",deviceType);
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
			epubId = UploadEpub_res.then().extract().path("epubId");
			Log.info("epubId: "+epubId);


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

			String serachText = title.substring(0,4);
			Response searchv2 = SearchV2.searchV2(serachText, userToken, "daa3232", deviceType);
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
			Validation.responseKeyValidation_key(searchv2, "_type");
			//Validation.responseKeyValidation_key(searchv2, "bookId");
			Validation.responseKeyValidation_key(searchv2, "bookTitle");
			Validation.responseKeyValidation_key(searchv2, "description");
			Validation.responseKeyValidation_key(searchv2, "total");

			Response downloadBook = DownloadBook_ext.downloadBook_ext(consumerKey, consumerSecret, bookID6,"sds4344", deviceType);
			Log.info("detail : " +detail);
			Validation.responseCodeValidation1(downloadBook, HttpStatus.SC_OK);
			Log.info("detail : " +detail);
			Validation.responseTimeValidation(downloadBook);
			Validation.responseKeyValidation_key(downloadBook,"fileSize");
			Validation.responseKeyValidation_key(downloadBook,"responseMsg");


			Response searchv2_ext = SearchV2_OAuth.searchV2_OAuth(serachText, consumerKey,consumerSecret,clientUserID);
			Validation.responseCodeValidation1(searchv2_ext, HttpStatus.SC_OK);
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

			Response bookdetails_res = Bookdetails.bookdetails(""+archiveDate+"", userToken, "sdfg345",deviceType,bookID1,""+assetType+"");
			Validation.responseHeaderCodeValidation(bookdetails_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(bookdetails_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(bookdetails_res);
			Validation.responseKeyValidation_key(bookdetails_res,"archiveDate");
			Validation.responseKeyValidation_key(bookdetails_res,"assetType");
			Validation.responseKeyValidation_key(bookdetails_res,"author");
			Validation.responseKeyValidation_key(bookdetails_res,"bookCode");
			Validation.responseKeyValidation_key(bookdetails_res,"bookId");
			Validation.responseKeyValidation_key(bookdetails_res,"category");
			Validation.responseKeyValidation_key(bookdetails_res,"categoryIdList");
			Validation.responseKeyValidation_key(bookdetails_res,"categoryList");
			Validation.responseKeyValidation_key(bookdetails_res,"collectionID");
			Validation.responseKeyValidation_key(bookdetails_res,"collectionThumbnail");
			Validation.responseKeyValidation_key(bookdetails_res,"collectionTitle");
			Validation.responseKeyValidation_key(bookdetails_res,"collectionType");		


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
			password="kitaboo!123";
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
			Validation.responseKeyValidation_key(fetchBookClassExpandedAnalytics, "totalSession");
			int totalSession = fetchBookClassExpandedAnalytics.then().extract().path("analytics.studentAnalytics[0].totalSession");
			Log.info("totalSession : "+totalSession);
			
			
			Response fetchBookClassUserExpandedAnalytics = FetchBookClassUserExpandedAnalytics.fetchBookClassUserExpandedAnalytics(bookID1, classID, userID, userToken, "asdfg345", deviceType);
			Validation.responseCodeValidation1(fetchBookClassUserExpandedAnalytics, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchBookClassUserExpandedAnalytics);
			Validation.responseKeyAndValue(fetchBookClassUserExpandedAnalytics, "responseMsg","OK");
			Validation.responseKeyValidation_key(fetchBookClassUserExpandedAnalytics, "analytics");
			Validation.responseKeyValidation_key(fetchBookClassUserExpandedAnalytics, "name");
			Validation.responseKeyValidation_key(fetchBookClassUserExpandedAnalytics, "pagesRead");
			Validation.responseKeyValidation_key(fetchBookClassUserExpandedAnalytics, "id");
			Validation.responseKeyValidation_key(fetchBookClassUserExpandedAnalytics, "studentAnalytics");
			Validation.responseKeyValidation_key(fetchBookClassUserExpandedAnalytics, "sessionAnalytics");*/
			
			
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

			
			Response savecollection = Savecollection.savecollection(consumerKey, consumerSecret, bookID1, bookID2);
			Validation.responseCodeValidation1(savecollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(savecollection);
			Validation.responseKeyAndValue(savecollection, "responseMsg","OK");
			Validation.responseKeyValidation_key(savecollection, "clientCollectionId");
			Validation.responseKeyValidation_key(savecollection, "coverImage");
			Validation.responseKeyValidation_key(savecollection, "books");
			Validation.responseKeyValidation_key(savecollection, "id");
			
			
			Response listCollection= ListCollection.listCollection(consumerKey, consumerSecret);
			Validation.responseCodeValidation1(listCollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(listCollection);
			Validation.responseKeyAndValue(listCollection, "responseMsg","OK");
			Validation.responseKeyValidation_key(listCollection, "clientCollectionId");
			Validation.responseKeyValidation_key(listCollection, "title");
			Validation.responseKeyValidation_key(listCollection, "coverImageUrl");
			Validation.responseKeyValidation_key(listCollection, "collectionType");
			Validation.responseKeyValidation_key(listCollection, "referenceNumber");
			
			
			
			
			
			
			
			/*Response userAssignedBooks_res = UserAssignedBooks_OAuth.userAssignedBooks_OAuth(consumerKey, consumerSecret,clientUserID);
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
			int totalbooks = userAssignedBooks_withPagi_Res.then().extract().path("totalbooks");
			Log.info("totalbooks: "+totalbooks);
			Validation.responseISGreater_String(userAssignedBooks_withPagi_Res, "totalbooks", 3);
			//Validation.responseKeyAndValue(userAssignedBooks_withPagi_Res, "expiryDate", "PERPETUAL");
			 */
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

