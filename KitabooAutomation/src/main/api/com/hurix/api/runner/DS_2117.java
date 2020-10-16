package com.hurix.api.runner;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.externalAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

import io.restassured.response.Response;


public class DS_2117 {	

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

		Log.initialization("Sprint34/DIS-2117");
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/Sprint34/DIS-2117.xlsx";
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
			String logINURL =  formatter.formatCellValue(sheet.getRow(i).getCell(6));
			String deviceLimit = formatter.formatCellValue(sheet.getRow(i).getCell(7));
			String type1 = formatter.formatCellValue(sheet.getRow(i).getCell(8));

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
			
			String[] deviceT = {"IPAD","ANDROID","WINDOWS","PC","HTML5"};
			
			

			BrowserConfigure.browserConfigure("Chrome");
			Driver.driver.navigate().to(""+logINURL+"");
			UIElements.threadHold_2Sec();
			System.out.println("userName : "+userName);
			System.out.println("password : "+password);
			System.out.println("deviceType : "+deviceType);
			//LoginModule.loginScenario(userName, password);
			Driver.driver.findElement(By.xpath("//*[@id='1-email']")).click();
			Driver.driver.findElement(By.xpath("//*[@id='1-email']")).sendKeys(""+userName+"");
			UIElements.threadHold_2Sec();
			Driver.driver.findElement(By.xpath("//*[@placeholder='your password']")).click();
			Driver.driver.findElement(By.xpath("//*[@placeholder='your password']")).sendKeys(""+password+"");
			UIElements.threadHold_2Sec();
			Driver.driver.findElement(By.xpath("//*[@id='auth0-lock-container-1']/div/div[2]/form/div/div/div/button")).click();
			UIElements.threadHold_2Sec();
			String currentURL1 = Driver.driver.getCurrentUrl();
			System.out.println(currentURL1);
			String keyClockSessionToken = currentURL1.replaceAll("https(.*?)code=", "").replaceAll("state(.*)", "").replaceAll("&", "");
			System.out.println(keyClockSessionToken);
			Log.info("keyClockSessionToken : "+keyClockSessionToken);
			Driver.driver.quit();


			clientID = JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);

			Response Mobilesession = MobileSession.mobileSession(clientID, keyClockSessionToken, "wert", deviceT[0]);
			Validation.responseTimeValidation(Mobilesession);
			Validation.responseKeyValidation_key(Mobilesession, "userName");
			Validation.responseKeyValidation_key(Mobilesession, userName);			
			userName = Mobilesession.then().extract().path("user.userName");
			Validation.responseKeyAndValue(Mobilesession, "userName", userName);
			int userID = Mobilesession.then().extract().path("user.id");
			Log.info("userID: "+userID);
			String userToken = Mobilesession.then().extract().path("userToken");
			Log.info("userToken:"+userToken);
			String clientUserID = Mobilesession.then().extract().path("user.clientUserID");
			Log.info("clientUserIDT :"+clientUserID);
			userID = Mobilesession.then().extract().path("user.id");
			Log.info("userID: "+userID);
			userToken = Mobilesession.then().extract().path("userToken");
			Log.info("userToken:"+userToken);
			int client_Id = Mobilesession.then().extract().path("user.clientID");
			Log.info("client_Id:"+client_Id);
			

			
			Response validateUserToken = ValidateUserToken.validateUserToken(userToken,"wert", deviceT[0]);
			Validation.responseHeaderCodeValidation(validateUserToken, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validateUserToken, HttpStatus.SC_OK);
			Validation.responseTimeValidation(validateUserToken);
			Validation.responseKeyValidation_key(validateUserToken, "userName");
			//Validation.responseKeyValidation_key(validateUserToken, "lastName");
			Validation.responseKeyValidation_key(validateUserToken, "email");
			Validation.responseKeyValidation_key(validateUserToken, "userName");
			Validation.responseKeyValidation_key(validateUserToken, "userToken");
			Validation.responseKeyValidation_key(validateUserToken, "userID");
			Validation.responseKeyValidation_key(validateUserToken, "instituteID");
			Validation.responseKeyValidation_key(validateUserToken, "clientID");
			Validation.responseKeyAndValue(validateUserToken, "userName", userName);
			userToken = validateUserToken.then().extract().path("userToken");
			
			
			validateUserToken = ValidateUserToken.validateUserToken(userToken,"wert", deviceT[0]);
			Validation.responseHeaderCodeValidation(validateUserToken, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validateUserToken, HttpStatus.SC_OK);
			Validation.responseTimeValidation(validateUserToken);
			Validation.responseKeyValidation_key(validateUserToken, "userName");
			//Validation.responseKeyValidation_key(validateUserToken, "lastName");
			Validation.responseKeyValidation_key(validateUserToken, "email");
			Validation.responseKeyValidation_key(validateUserToken, "userName");
			Validation.responseKeyValidation_key(validateUserToken, "userToken");
			Validation.responseKeyValidation_key(validateUserToken, "userID");
			Validation.responseKeyValidation_key(validateUserToken, "instituteID");
			Validation.responseKeyValidation_key(validateUserToken, "clientID");
			Validation.responseKeyAndValue(validateUserToken, "userName", userName);
			userToken = validateUserToken.then().extract().path("userToken");
			
			
			
			validateUserToken = ValidateUserToken.validateUserToken(userToken,"wert", deviceT[0]);
			Validation.responseHeaderCodeValidation(validateUserToken, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validateUserToken, HttpStatus.SC_OK);
			Validation.responseTimeValidation(validateUserToken);
			Validation.responseKeyValidation_key(validateUserToken, "userName");
			//Validation.responseKeyValidation_key(validateUserToken, "lastName");
			Validation.responseKeyValidation_key(validateUserToken, "email");
			Validation.responseKeyValidation_key(validateUserToken, "userName");
			Validation.responseKeyValidation_key(validateUserToken, "userToken");
			Validation.responseKeyValidation_key(validateUserToken, "userID");
			Validation.responseKeyValidation_key(validateUserToken, "instituteID");
			Validation.responseKeyValidation_key(validateUserToken, "clientID");
			Validation.responseKeyAndValue(validateUserToken, "userName", userName);
			userToken = validateUserToken.then().extract().path("userToken");
			
			consumerKey = JDBC_Queries.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			consumerSecret =JDBC_Queries.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);
			
			Response fetchbooklist=FetchBookList.fetchBookList_without_pagination(userToken, "rwds32323", deviceT[0]);
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
			String collectionName1 = fetchbooklist.then().extract().path("bookList.book.collectionTitle[1]");
			System.out.println("collectionName1: "+collectionName1);
			catname = ExtractCategory.extractCategory(category1);
			System.out.println("catname: " +catname);
			String archiveDate = fetchbooklist.then().extract().path("bookList.book.archiveDate[1]");
			System.out.println("archiveDate:"+archiveDate);


			Response bookdetails = Bookdetails.bookdetails(archiveDate,userToken,"fg3cg23",deviceT[0],bookID2,"");
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


			Response fetchbookcount = FetchBookCount.fetchBookCount(userToken, "ad7zd67", deviceT[0]);
			Validation.responseHeaderCodeValidation(fetchbookcount, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fetchbookcount, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fetchbookcount);

			Response refreshBookList = RefreshBookList.refreshBookList(userToken, "gedsff87f7s", deviceT[0]);
			Validation.responseHeaderCodeValidation(refreshBookList, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(refreshBookList, HttpStatus.SC_OK);
			Validation.responseTimeValidation(refreshBookList);

			Response getsecuredURL =  GetSecureURL.getSecureURL(userToken, "sagsg76767", deviceT[0], 3);
			Validation.responseHeaderCodeValidation(getsecuredURL, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(getsecuredURL, HttpStatus.SC_OK);
			Validation.responseTimeValidation(getsecuredURL);

			Response markAsFav= MarkAsFavourite.markAsFavourite(bookID1,userToken,"8742685",deviceT[0]);
			Validation.responseHeaderCodeValidation(markAsFav, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(markAsFav, HttpStatus.SC_OK);
			Validation.responseTimeValidation(markAsFav);
			Validation.responseKeyValidation_key(markAsFav, "OK");
			System.out.println("markAsFav : "+markAsFav);

			Response FetchFavouriteBooks_res = FetchFavouriteBooks.fetchFavouriteBooks(userToken,"8742685",deviceT[0]);
			Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(FetchFavouriteBooks_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(FetchFavouriteBooks_res);
			Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "OK");
			Validation.responseKeyValidation_key(FetchFavouriteBooks_res, ""+bookID1+"");
			Validation.responseKeyValidation_key(FetchFavouriteBooks_res,"description");


			Response unMarkFav=UnMarkAsFavourite.unMarkAsFavourite(bookID1,userToken,"8742685",deviceT[0]);
			Validation.responseHeaderCodeValidation(unMarkFav, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(unMarkFav, HttpStatus.SC_OK);
			Validation.responseTimeValidation(unMarkFav);
			//Validation.responseKeyValidation_key(unMarkFav, "isbn");
			System.out.println("unMarkFav : "+unMarkFav);


			Response FetchFavouriteBooks_res1 = FetchFavouriteBooks.fetchFavouriteBooks(userToken,"8742685",deviceT[0]);
			Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(FetchFavouriteBooks_res1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(FetchFavouriteBooks_res1);
			Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_res1, ""+bookID1+"");


			SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			Date date = new Date();  
			String  time=formatter1.format(date);
			Response savesessionHistory = SaveSessionHistory.saveSessionHistory(userToken,"dshdg63236",deviceT[0],bookID1,time);
			Log.info("TIME : "+time);
			Validation.responseHeaderCodeValidation(savesessionHistory, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(savesessionHistory, HttpStatus.SC_OK);
			Validation.responseTimeValidation(savesessionHistory);



			Response fetchRecentlyViewed = FetchRecentlyViewed.fetchRecentlyViewed_without_pagi(userToken,"fscssx67777",deviceT[0]);
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

			fetchRecentlyViewed = FetchRecentlyViewed.fetchRecentlyViewed_with_pagi(0, 10, userToken,"wertg123456543",deviceT[0]);
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

			Response fethcatcollection = FetchCategoriesCollections.fetchCategoriesCollections(userToken,"shjh5637456",deviceT[0]);
			Validation.responseHeaderCodeValidation(fethcatcollection, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(fethcatcollection, HttpStatus.SC_OK);
			Validation.responseTimeValidation(fethcatcollection);
			Validation.responseKeyValidation_key(fethcatcollection,"categories");
			Validation.responseKeyValidation_key(fethcatcollection,"name");
			Validation.responseKeyValidation_key(fethcatcollection,"numberOfBooks");
			Validation.responseKeyValidation_key(fethcatcollection,"collections");
			Validation.responseKeyValidation_key(fethcatcollection,"name");

			Response fetchCategoriesCollectionsBooks = FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks(userToken,"fs424",deviceT[0],category1,collectionName1,bookID2,sqlhost,sqlUsername,sqlPassword,catlevel);
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

			Response categories = CategoriesV1.categoriesV1(userToken, "s4324", deviceT[0]);
			Validation.responseHeaderCodeValidation(categories, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(categories,HttpStatus.SC_OK);
			Validation.responseTimeValidation(categories);
			Validation.responseKeyValidation_key(categories, "id");
			Validation.responseKeyValidation_key(categories, "name");
			Validation.responseKeyValidation_key(categories, "totalCategories");
			int totalCategories=categories.then().extract().path("totalCategories");
			System.out.println("totalCategories : "+totalCategories);
			String categoriesname=categories.then().extract().path("categories[0].name");
			Validation.responseKeyAndValue(categories, "name",categoriesname);

			categories = CategoriesV2.categoriesV2(userToken, "whsdj4637", deviceT[0]);
			Validation.responseHeaderCodeValidation(categories, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(categories,HttpStatus.SC_OK);
			Validation.responseTimeValidation(categories);
			Validation.responseKeyValidation_key(categories, "id");
			Validation.responseKeyValidation_key(categories, "name");
			Validation.responseKeyValidation_key(categories, "totalCategories");
			totalCategories=categories.then().extract().path("totalCategories");
			categoriesname=categories.then().extract().path("categories[0].name");
			Validation.responseKeyAndValue(categories, "name",categoriesname);


			Response categoryBookListV1 = CategoryBookListV1.categoryBookListV1(""+category1+"", userToken, "djsdh64374", deviceT[0], bookID1, catlevel, sqlhost, sqlUsername, sqlPassword);
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

			Response CategoryBookListV2Res = CategoryBookListV2.categoryBookListV2(""+category1+"",userToken,"56454",deviceT[0],bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
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

			Response fetchcategorybooksV1 = FetchCategorybooksV1.fetchCategorybooksV1(""+category1+"", userToken,bookID1, catlevel, sqlhost, sqlUsername, sqlPassword);
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

			
			String searchTEXT = title.substring(0,4);
			Log.info("searchTEXT : "+searchTEXT);
			Response searchv2 = SearchV2.searchV2(searchTEXT, userToken, "dwsd323", deviceT[0]);
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

			Response books = ClientUserID_books.clientUserID_books(consumerKey, consumerSecret, searchTEXT, ""+clientUserID+"");
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
			

			/*BrowserConfigure.browserConfigure("Chrome");
			Driver.driver.navigate().to(""+logINURL+"");
			UIElements.threadHold_2Sec();
			System.out.println("userName : "+userName);
			System.out.println("password : "+password);
			//LoginModule.loginScenario(userName, password);
			Driver.driver.findElement(By.xpath("//*[@id='1-email']")).click();
			Driver.driver.findElement(By.xpath("//*[@id='1-email']")).sendKeys(""+userName+"");
			UIElements.threadHold_2Sec();
			Driver.driver.findElement(By.xpath("//*[@placeholder='your password']")).click();
			Driver.driver.findElement(By.xpath("//*[@placeholder='your password']")).sendKeys(""+password+"");
			UIElements.threadHold_2Sec();
			Driver.driver.findElement(By.xpath("//*[@id='auth0-lock-container-1']/div/div[2]/form/div/div/div/button")).click();
			UIElements.threadHold_2Sec();
			currentURL1 = Driver.driver.getCurrentUrl();
			System.out.println(currentURL1);
			keyClockSessionToken = currentURL1.replaceAll("https(.*?)code=", "").replaceAll("state(.*)", "").replaceAll("&", "");
			System.out.println(keyClockSessionToken);
			Log.info("keyClockSessionToken : "+keyClockSessionToken);
			Driver.driver.quit();
			
			Mobilesession = MobileSession.mobileSession(clientID, keyClockSessionToken, "wert", deviceType);
			Validation.responseTimeValidation(Mobilesession);
			Validation.responseKeyValidation_key(Mobilesession, "userName");
			Validation.responseKeyValidation_key(Mobilesession, userName);			
			userName = Mobilesession.then().extract().path("user.userName");
			Validation.responseKeyAndValue(Mobilesession, "userName", userName);
			userID = Mobilesession.then().extract().path("user.id");
			Log.info("userID: "+userID);
			userToken = Mobilesession.then().extract().path("userToken");
			Log.info("userToken:"+userToken);
			int client_Id = Mobilesession.then().extract().path("user.clientID");
			Log.info("client_Id:"+client_Id);
			
			
			
			
			
			Response updateNotification = UpdateNotification.updateNotification_Str(clientUserID, 1, consumerKey, consumerSecret);
			Validation.responseHeaderCodeValidation(updateNotification, HttpStatus.SC_OK);
			Validation.responseTimeValidation(updateNotification);
			Validation.responseKeyAndValue(updateNotification, "message","OK");
			
			int userDeleted= JDBC_Queries.getUserDeleted(userID,sqlhost, sqlUsername, sqlPassword);
			Log.info("userDeleted : "+userDeleted);
			
			validateUserToken = ValidateUserToken.validateUserToken(userToken,"wert", deviceType);
			Validation.responseHeaderCodeValidation(validateUserToken, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(validateUserToken, HttpStatus.SC_OK);
			Validation.responseTimeValidation(validateUserToken);
			Validation.responseKeyValidation_key(validateUserToken, "userName");
			//Validation.responseKeyValidation_key(validateUserToken, "lastName");
			Validation.responseKeyValidation_key(validateUserToken, "email");
			Validation.responseKeyValidation_key(validateUserToken, "userName");
			Validation.responseKeyValidation_key(validateUserToken, "userToken");
			Validation.responseKeyValidation_key(validateUserToken, "userID");
			Validation.responseKeyValidation_key(validateUserToken, "instituteID");
			Validation.responseKeyValidation_key(validateUserToken, "clientID");
			Validation.responseKeyAndValue(validateUserToken, "userName", userName);
			userToken = validateUserToken.then().extract().path("userToken");*/
			
			
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
