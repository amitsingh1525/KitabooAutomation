package com.hurix.api.runner;

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
import io.restassured.response.Response;
import com.hurix.api.externalAPIs.*;
import com.hurix.api.hashAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.ExcelUtils;
import com.hurix.api.utility.ExtractCategory;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;
//import static org.hamcrest.Matchers.*;

public class RestAssured {

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
	public static String userToken = "";
	public static int BookID_mark1;
	public static int  bookID1;
	public static int bookID2;
	public static String isbn;
	public static String isbnMeta;
	public static String isbnIng;
	public static int userID;
	public static int totalbooks;
	public static String ebookID1;
	public static String catname;
	public static String collectionName0;
	public static String collectionName1;
	public static String catname1;
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
	public static String consumerKey=ExcelUtils.Consumer_key;
	public static String consumerSecret=ExcelUtils.secret_key;

	public static void   main(String []args) throws SQLException, JSONException{
		Log.initialization("APITesting");	
		//List<String> detail =  ExcelUtils.getuserDetails();
		collectionName1="Col1";
		catname1="History";
		try {
			String excelPath="./testData/ExcelData.xlsx";
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
				Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"65454","IPAD");
				Validation.responseHeaderCodeValidation(authenticateValue, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(authenticateValue, HttpStatus.SC_OK);
				Validation.responseTimeValidation(authenticateValue);
				Validation.responseKeyValidation_key(authenticateValue, "userName");
				Validation.responseKeyValidation_key(authenticateValue, userName);
				System.out.println("here");
				userName = authenticateValue.then().extract().path("user.userName");
				userID = authenticateValue.then().extract().path("user.id");
				System.out.println("userID: "+userID);
				userToken = authenticateValue.then().extract().path("userToken");
				System.out.println("userToken:"+userToken);
				clientUserID = authenticateValue.then().extract().path("user.clientUserID");
				System.out.println("clientUserID:"+clientUserID);


				Response fetchBookList_with_pagination = FetchBookList.fetchBookList_with_pagination(0,15,userToken,"45616452","IPAD");
				Validation.responseCodeValidation1(fetchBookList_with_pagination, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(fetchBookList_with_pagination, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchBookList_with_pagination);
				Validation.responseKeyValidation_key(fetchBookList_with_pagination, "isbn");
				Validation.responseKeyValidation_key(fetchBookList_with_pagination, "title");
				Validation.responseKeyValidation_key(fetchBookList_with_pagination, "id");
				Validation.responseKeyValidation_key(fetchBookList_with_pagination, "collectionID");
				System.out.println("fetchBookList_with_pagination_RES : " +fetchBookList_with_pagination);
				totalbooks= fetchBookList_with_pagination.then().assertThat().extract().path("totalbooks");

				Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"45616452","IPAD");
				Validation.responseCodeValidation1(fetchBookList_without_pagination, 200);
				Validation.responseHeaderCodeValidation(fetchBookList_without_pagination, 200);
				Validation.responseTimeValidation(fetchBookList_without_pagination);
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "isbn");
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "title");
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "id");
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "archiveDate");
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "readingPercentage");
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "assignedOn");
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "bookId");
				//Validation.responseKeyValidation_key(jsonResponse, Title)
				bookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[0]");
				System.out.println("bookID_1: "+bookID1);
				bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[1]");
				System.out.println("bookID: "+bookID2);
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

				Response GETfetchBookCount_res = FetchBookCount.fetchBookCount(userToken,"45616452","IPAD");
				Validation.responseHeaderCodeValidation(GETfetchBookCount_res, 200);
				Validation.responseCodeValidation1(GETfetchBookCount_res, 200);
				Validation.responseTimeValidation(GETfetchBookCount_res);
				Validation.responseKeyValidation_key(GETfetchBookCount_res, "totalbooks");
				Validation.responseKeyValidation_key(GETfetchBookCount_res, "timestamp");
				System.out.println("fetchBookCount_res : "+GETfetchBookCount_res);
				/*				  
				 * HASH APIS
				 * 		
				 */
				Response FetchBookListHash_res = FetchBookListHash.fetchBookListHash(userToken,"45616452","IPAD");
				System.out.println("FetchBookListHash_res: "+FetchBookListHash_res);
				
				Response FetchCatBookList_res =FetchCatBookList.fetchCatBookList(catname,userToken,"45616452","IPAD");
				System.out.println("FetchCatBookList_res: "+FetchCatBookList_res);
				
				Response CategoriesV1Hash_res =CategoriesV1Hash.categoriesV1Hash(userToken,"45616452","IPAD");
				System.out.println("CategoriesV1Hash_res: "+CategoriesV1Hash_res);
				
				Response CategoriesV2Hash_res = CategoriesV2Hash.categoriesV2Hash(userToken,"45616452","IPAD");
				System.out.println("CategoriesV2Hash_res: "+CategoriesV2Hash_res);
				
				Response RefreshBookList_Hash = RefreshBookListHash.refreshBookListHash(userToken,"45616452","IPAD");
				System.out.println("RefreshBookList_Hash: "+RefreshBookList_Hash);

				Response FetchRecentlyViewedBook_Hash = FetchRecentlyViewedBooksSecuredHash.fetchRecentlyViewedBooksSecuredHash(userToken,"45616452","IPAD",bookID1);
				System.out.println("FetchRecentlyViewedBook_Hash: "+FetchRecentlyViewedBook_Hash);
				
				Response GetBookDetailsSecured_Hash = GetBookDetailsSecuredHash.getBookDetailsSecuredHash("2019/10/31 14:46:04",userToken,"45616452","IPAD",ebookID1,assetType);
				System.out.println("GetBookDetailsSecured_Hash: "+GetBookDetailsSecured_Hash);
				
				Response MarkAsFavourite_Hash = MarkAsFavouriteHash.markAsFavouriteHash(bookID1,userToken,"56454", "IPAD");
				System.out.println("MarkAsFavourite_Hash: "+MarkAsFavourite_Hash);
				int markesAsFav=bookID1;
				Response FetchFavouriteSecured_Hash = FetchFavouriteSecuredHash.fetchFavouriteSecuredHash(userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteSecured_Hash, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteSecured_Hash, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteSecured_Hash);
				Validation.responseKeyValidation_key(FetchFavouriteSecured_Hash, ""+bookID1+"");
				System.out.println("FetchFavouriteSecured_Hash: "+FetchFavouriteSecured_Hash);
				
				Response UnMarkAsFavourite_Hash = UnMarkAsFavourite.unMarkAsFavourite(markesAsFav,userToken,"56454", "IPAD");
				System.out.println("UnMarkAsFavourite_Hash: "+UnMarkAsFavourite_Hash);
				
				Response FetchFavouriteSecured_Hash1 = FetchFavouriteSecuredHash.fetchFavouriteSecuredHash(userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteSecured_Hash1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteSecured_Hash1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteSecured_Hash1);
				Validation.responseNOTKeyValidation_key(FetchFavouriteSecured_Hash1, ""+bookID1+"");
				System.out.println("FetchFavouriteSecured_Hash: "+FetchFavouriteSecured_Hash1);
				
				Response FetchCategoriesCollectionsBooksHash = FetchCategoriesCollectionsBooks_Hash.fetchCategoriesCollectionsBooks_Hash(userToken,catname1,collectionName1,"5454gdf","IPAD");
				System.out.println("FetchCategoriesCollectionsBooksHash: "+FetchCategoriesCollectionsBooksHash);
				
				Response FetchCatCollection_Books = FetchCatCollectionBooks.fetchCatCollectionBooks(userToken,"5454gdf","IPAD",catname1,collectionName1);
				System.out.println("FetchCatCollection_Books: "+FetchCatCollection_Books);
				
				Response V1RefreshBooks_hash = V1RefreshBooksHash.v1refreshBooks("2019/10/31 14:46:04","NEW","UPDATE",""+bookID1+"",""+bookID2+"",userToken,"56454","IPAD");
				System.out.println("V1RefreshBooks_hash: "+V1RefreshBooks_hash);
				/*				  
				 * HASH APIS ENDS
				 * 		
				 */
				Response categoryBookListV1res = CategoryBookListV1.categoryBookListV1("level4",userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(categoryBookListV1res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res);
				Validation.responseKeyValidation_key(categoryBookListV1res,"category");
				Validation.responseKeyValidation_key(categoryBookListV1res,"isbn");
				Validation.responseKeyValidation_key(categoryBookListV1res,"id");
				Validation.responseKeyValidation_key(categoryBookListV1res,"title");
				Validation.responseKeyValidation_key(categoryBookListV1res,"formats");		
				System.out.println("categoryBookListV1res : " +categoryBookListV1res);


				Response CategoryBookListV2Res = CategoryBookListV2.categoryBookListV2("level4",userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(CategoryBookListV2Res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(CategoryBookListV2Res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(CategoryBookListV2Res);
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"category");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"isbn");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"id");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"title");
				Validation.responseKeyValidation_key(CategoryBookListV2Res,"formats");
				System.out.println("CategoryBookListV2Res : " +CategoryBookListV2Res);
				Response FetchCategorybooksV1Res = FetchCategorybooksV1.fetchCategorybooksV1(catname);
				System.out.println("FetchCategorybooksV1Res : " +FetchCategorybooksV1Res);


				Response fetchPreferredLocale_res = FetchPreferredLocale.fetchPreferredLocale(userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(fetchPreferredLocale_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchPreferredLocale_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchPreferredLocale_res);
				Validation.responseKeyValidation_key(fetchPreferredLocale_res,"locale");
				Validation.responseKeyValidation_key(fetchPreferredLocale_res,"responseMsg");
				Validation.responseKeyValidation_key(fetchPreferredLocale_res,"Ok");
				System.out.println("fetchPreferredLocale_res: "+fetchPreferredLocale_res);


				Response downloadBookForANDROID_offline = DownloadBook.downloadBook(userToken,"ds9465","ANDROID",bookID1,"offline");
				System.out.println("downloadBookForANDROID_offline: "+downloadBookForANDROID_offline);


				Response downloadBookForANDROID_online = DownloadBook.downloadBook(userToken,"ds9465","ANDROID",bookID1,"online");
				System.out.println("downloadBookForANDROID_online: "+downloadBookForANDROID_online);

				Response downloadBookForIPAD_online = DownloadBook.downloadBook(userToken,"ds9465","IPAD",bookID1,"online");
				System.out.println("downloadBookForIPAD_online: "+downloadBookForIPAD_online);

				Response downloadBookForIPAD_offline = DownloadBook.downloadBook(userToken,"ds9465","IPAD",bookID1,"offline");
				System.out.println("downloadBookForIPAD_offline: "+downloadBookForIPAD_offline);

				Response downloadBookForWINDOWNS_offline = DownloadBook.downloadBook(userToken,"ds9465","WINDOWNS",bookID1,"offline");
				System.out.println("downloadBookForWINDOWNS_offline: "+downloadBookForWINDOWNS_offline);

				Response downloadBookForWINDOWNS_online = DownloadBook.downloadBook(userToken,"ds9465","WINDOWNS",bookID1,"online");
				System.out.println("downloadBookForWINDOWNS_online: "+downloadBookForWINDOWNS_online);

				Response downloadBookForHTPM5_offline = DownloadBook.downloadBook(userToken,"ds9465","HTML5",bookID1,"offline");
				System.out.println("downloadBookForHTPM5_offline: "+downloadBookForHTPM5_offline);

				Response downloadBookForPC_ONLINE = DownloadBook.downloadBook(userToken,"ds9465","PC",bookID1,"online");
				System.out.println("downloadBookForPC_ONLINE: "+downloadBookForPC_ONLINE);


				System.out.println("consumerKey: "+consumerKey);
				System.out.println("consumerSecret: "+consumerSecret);
				Response searchV2res = SearchV2.searchV2("Native",userToken,"ds9465","PC");
				System.out.println("searchV2res : "+searchV2res);

				Response SearchV2_OAuthres = SearchV2_OAuth.searchV2_OAuth("Native",consumerKey, consumerSecret,clientUserID);
				System.out.println("SearchV2_OAuthres : "+SearchV2_OAuthres);

				Response searchV2_AdvanceFilterres =SearchV2_AdvanceFilter.searchV2_AdvanceFilter("Native",userToken, "5454545","IPAD",clientUserID);
				System.out.println("searchV2_AdvanceFilterres : "+searchV2_AdvanceFilterres);

				Response Booklist = BookList.bookList(userToken,"56454", "IPAD");
				System.out.println("Booklist_res : "+Booklist);

				Response POSTupdateUser_OAuthres = UpdateUser_OAuth.updateUser_OAuth(consumerKey, consumerSecret);
				System.out.println("updateUser_OAuthres : " +POSTupdateUser_OAuthres);
				//Response updateUser_captital_OAuthres =UpdateUser_captital_OAuth.updateUser_captital_OAuth(consumerKey, consumerSecret);
				//System.out.println("updateUser_captital_OAuthres : "+updateUser_captital_OAuthres);

				Response getSecureURLres =GetSecureURL.getSecureURL(userToken, "5489989","IPAD",type);
				System.out.println("getSecureURLres : "+getSecureURLres);

				System.out.println("startDate :: "+startDate); 
				Response bookdetails_res =Bookdetails.bookdetails("2019/10/31 14:46:04",userToken, "5489989","IPAD",""+ebookID1+"",""+assetType+"");
				System.out.println("bookdetails_res : "+bookdetails_res);
				
				Response fetchCategoriesCollectionsres =FetchCategoriesCollections.fetchCategoriesCollections(userToken, "5489989","IPAD");
				System.out.println("fetchCategoriesCollectionsres : "+fetchCategoriesCollectionsres);
				
				Response fetchCategoriesCollectionsBooksres =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks(userToken, "5489989","IPAD",catname1,collectionName1);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooksres);
				
				Response categoriesV1res = CategoriesV1.categoriesV1(userToken, "5489989","IPAD");
				System.out.println("categoriesV1res : "+categoriesV1res);
				
				Response categoriesV2res =CategoriesV2.categoriesV2(userToken, "5489989","IPAD");
				System.out.println("categoriesV2res : " +categoriesV2res);
				
				Response books_OAuthres = Books_OAuth.books_OAuth(consumerKey, consumerSecret);
				System.out.println("books_OAuthres : " +books_OAuthres);
				
				Response ListBooksV1_OAuth_With_Pagi_res = ListBooksV1_OAuth.listBooksV1_OAuth_With_Pagi(0, 60, consumerKey, consumerSecret);
				System.out.println("ListBooksV1_OAuth_With_Pagi_res : "+ListBooksV1_OAuth_With_Pagi_res);

				Response listBooksV1_OAuth_without_pagires =ListBooksV1_OAuth.listBooksV1_OAuth_without_pagi(consumerKey, consumerSecret);
				System.out.println("listBooksV1_OAuth_without_pagires : "+listBooksV1_OAuth_without_pagires);
				
				Response ListBooks_OAuth_withpagi_res= ListBooks_OAuth.listBooks_OAuth_With_Pagi(0, 60, consumerKey, consumerSecret);
				System.out.println("ListBooks_OAuth_withpagi_res : "+ListBooks_OAuth_withpagi_res);
				
				Response ListBooks_OAuth_res = ListBooks_OAuth.listBooks_OAuth_withoutpagi(consumerKey, consumerSecret);
				System.out.println("ListBooks_OAuth_res : "+ListBooks_OAuth_res);
				clientBookID = listBooksV1_OAuth_without_pagires.then().extract().path("bookList.book.clientBookID[1]");
				System.out.println("clientBookID1: " +clientBookID);
				
				
				Response getBookMetadata_res = GetBookMetadata.getBookMetadata(consumerKey, consumerSecret,clientBookID);
				System.out.println("getBookMetadata_res : " +getBookMetadata_res);
				
				Response bookMetadata_res = BookMetadata.bookMetadata(consumerKey, consumerSecret,bookID1);
				System.out.println("bookMetadata_res : " +bookMetadata_res);
				
				
				Response clientUserID_books_res = ClientUserID_books.clientUserID_books(consumerKey, consumerSecret, search,clientUserID);
				System.out.println("clientUserID_books_res : "+clientUserID_books_res);
				
				
				Response userAssignedBooks_res = UserAssignedBooks_OAuth.userAssignedBooks_OAuth(consumerKey, consumerSecret,clientUserID);
				System.out.println("userAssignedBooks_res : "+userAssignedBooks_res);
				
				Response userAssignedBooks_withPagi_Res = UserAssignedBooks_OAuth.userAssignedBooks_OAuth_with_pagi(0, 80, consumerKey, consumerSecret);
				System.out.println("userAssignedBooks_withPagi_Res : "+userAssignedBooks_withPagi_Res);
				
				Response refreshBookListres = RefreshBookList.refreshBookList(userToken,"56496","IPAD");
				System.out.println("refreshBookListres : "+refreshBookListres);
				
				Response v1refreshBookList_res =V1refreshBookList.v1refreshBookList("2019/10/31 14:46:04","NEW","UPDATE",""+bookID1+"",""+bookID2+"",userToken,"56454", "IPAD");
				System.out.println("v1/v1refreshBookList_res : "+v1refreshBookList_res);
				
				
				Response v1refreshBookList_withPagi_res = V1refreshBookList.v1refreshBookList_with_pagi(0,100,"2019/10/31 14:46:04","NEW","UPDATE","bookID1","bookID2",userToken,"6565656","IPAD");
				System.out.println("v1refreshBookList_withPagi_res : "+v1refreshBookList_withPagi_res);
				
				Response markAsFavourite_res = MarkAsFavourite.markAsFavourite(bookID1,userToken,"45564595","IPAD");
				BookID_mark1 = bookID1;
				System.out.println("markAsFavourite_res : "+markAsFavourite_res);
				
				Response FetchFavouriteBooks_res = FetchFavouriteBooks.fetchFavouriteBooks(userToken,"45564595","IPAD");
				System.out.println("FetchFavouriteBooks_res : "+FetchFavouriteBooks_res);
				
				Response unMarkAsFavourite_res = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark1,userToken,"45564595","IPAD");
				System.out.println("unMarkAsFavourite_res : "+unMarkAsFavourite_res);
				
				FetchFavouriteBooks.fetchFavouriteBooks(userToken,"45564595","IPAD");
				System.out.println("FetchFavouriteBooks_res : "+FetchFavouriteBooks_res);
				
				Response saveSessionHistory_res = SaveSessionHistory.saveSessionHistory(userToken,"45564595","IPAD",bookID1);
				System.out.println("saveSessionHistory_res : "+saveSessionHistory_res);
				
				
				Response FetchRecentlyViewed_res = FetchRecentlyViewed.fetchRecentlyViewed_without_pagi(userToken,"54254fd","IPAD",bookID1);
				System.out.println("FetchRecentlyViewed_res : "+FetchRecentlyViewed_res);
				
				Response fetchRecentlyViewed_with_pagi_res = FetchRecentlyViewed.fetchRecentlyViewed_with_pagi(0,200,userToken,"hf454","IPAD",bookID1);
				System.out.println("fetchRecentlyViewed_with_pagi_res : "+fetchRecentlyViewed_with_pagi_res);

				if(catlevel.contains("4"))
				{
					catlevel= "4";
					int level= 4;
					String catLevel1= "level4";
					Response multiCategories_res = MultiCategories.multiCategories(level);
					System.out.println("multiCategories_res : "+multiCategories_res);

				}
				else if(catlevel.contains("3"))
				{
					catlevel= "3";
					int level= 3;
					String catLevel1= "level3";
					Response multiCategories_res = MultiCategories.multiCategories(level);
					System.out.println("multiCategories_res : "+multiCategories_res);

				}
				else if(catlevel.contains("2"))
				{
					catlevel= "2";
					level= 2;
					String catLevel1= "level2";
					Response multiCategories_res = MultiCategories.multiCategories(level);
					System.out.println("multiCategories_res : "+multiCategories_res);

				}
				Response multiCategories_res = MultiCategories.multiCategories(level);
				System.out.println("multiCategories_res : "+multiCategories_res);
				int bookID=bookID1;
				String sqlhost="jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com";
				String sqlUsername="qcteam";
				String sqlPassword="JB88F-WT2Q3-DPXTT";
				
				
				Response MultiCategoryBookList_res = MultiCategoryBookList.multiCategoryBookList(catlevel,bookID,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				System.out.println("MultiCategoryBookList_res : "+MultiCategoryBookList_res); 
				
				Response MultiCategoryCollection_BookList=MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName1);
				System.out.println("MultiCategoryCollection_BookList : "+MultiCategoryCollection_BookList);
			}
		}
		catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}
}
