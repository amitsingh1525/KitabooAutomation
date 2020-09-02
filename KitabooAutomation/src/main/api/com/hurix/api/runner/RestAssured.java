package com.hurix.api.runner;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import io.restassured.response.Response;
import com.hurix.api.externalAPIs.*;
import com.hurix.api.hashAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.EpochTime;
import com.hurix.api.utility.ExcelUtils;
import com.hurix.api.utility.ExtractCategory;
import com.hurix.automation.utility.Log;

import static org.hamcrest.Matchers.*;

public class RestAssured {

	public static List<String> detailisbn =  ExcelUtils.getisbn();
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	//System.out.println(df.format(dateobj));
	//public static long startDate1 = EpochTime.getEpochTime("df.format(dateobj");
	public static long startDate = EpochTime.getEpochTime("2019/10/31 14:46:04");
	public static long startIndex = 0;
	public static long endIndex = 100;
	//public static int level;
	public static String assetType;
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
	public static String collectionName1;
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
				Response authenticateValue = Authenticate.authenticate(clientID, userName, password);
				System.out.println("here");
				userName = authenticateValue.then().extract().path("user.userName");
				userID = authenticateValue.then().extract().path("user.id");
				System.out.println("userID: "+userID);
				userToken = authenticateValue.then().extract().path("userToken");
				System.out.println("userToken:"+userToken);
				clientUserID = authenticateValue.then().extract().path("user.clientUserID");
				System.out.println("clientUserID:"+clientUserID);
				Response fetchBookList_with_pagination = FetchBookList.fetchBookList_with_pagination();
				System.out.println("fetchBookList_with_pagination_RES : " +fetchBookList_with_pagination);
				totalbooks= fetchBookList_with_pagination.then().assertThat().extract().path("totalbooks");

				Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination();
				bookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[0]");
				System.out.println("bookID_1: "+bookID1);
				bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[1]");
				System.out.println("bookID: "+bookID2);
				isbn = fetchBookList_without_pagination.then().extract().path("bookList.book.isbn[1]");
				System.out.println("isbn: "+isbn);
				ebookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.ebookID[0]");
				System.out.println("ebookID: "+ebookID1);
				assetType = fetchBookList_without_pagination.then().extract().path("bookList.book.assetType[0]");
				System.out.println("assetType: "+assetType);
				category1 = fetchBookList_without_pagination.then().extract().path("bookList.book.category[0]");
				System.out.println("category1: "+category1);
				collectionName1 = fetchBookList_without_pagination.then().extract().path("bookList.book.collectionTitle[0]");
				System.out.println("collectionName1: "+collectionName1);
				catname = ExtractCategory.extractCategory(category1);
				System.out.println("catname: " +catname);
				Response GETfetchBookCount_res =FetchBookCount.fetchBookCount();
				System.out.println("fetchBookCount_res : "+GETfetchBookCount_res);
				
				Response FetchBookListHash_res =FetchBookListHash.fetchBookListHash();
				System.out.println("FetchBookListHash_res: "+FetchBookListHash_res);
				/*Response FetchCatBookList_res =FetchCatBookList.fetchCatBookList(catname);
				System.out.println("FetchCatBookList_res: "+FetchCatBookList_res);
				Response CategoriesV1Hash_res =CategoriesV1Hash.categoriesV1Hash();
				System.out.println("CategoriesV1Hash_res: "+CategoriesV1Hash_res);
				Response CategoriesV2Hash_res =CategoriesV2Hash.categoriesV2Hash();
				System.out.println("CategoriesV2Hash_res: "+CategoriesV2Hash_res);
				Response RefreshBookList_Hash =RefreshBookListHash.refreshBookListHash();
				System.out.println("RefreshBookList_Hash: "+RefreshBookList_Hash);
				
				Response FetchRecentlyViewedBook_Hash =FetchRecentlyViewedBooksSecuredHash.fetchRecentlyViewedBooksSecuredHash();
				System.out.println("FetchRecentlyViewedBook_Hash: "+FetchRecentlyViewedBook_Hash);
				Response GetBookDetailsSecured_Hash =GetBookDetailsSecuredHash.getBookDetailsSecuredHash(startDate);
				System.out.println("GetBookDetailsSecured_Hash: "+GetBookDetailsSecured_Hash);
				Response MarkAsFavourite_Hash =MarkAsFavouriteHash.markAsFavouriteHash();
				System.out.println("MarkAsFavourite_Hash: "+MarkAsFavourite_Hash);
				Response FetchFavouriteSecured_Hash =FetchFavouriteSecuredHash.fetchFavouriteSecuredHash();
				System.out.println("FetchFavouriteSecured_Hash: "+FetchFavouriteSecured_Hash);
				Response UnMarkAsFavourite_Hash =UnMarkAsFavourite.unMarkAsFavourite();
				System.out.println("UnMarkAsFavourite_Hash: "+UnMarkAsFavourite_Hash);
				Response FetchCategoriesCollectionsBooksHash =FetchCategoriesCollectionsBooks_Hash.fetchCategoriesCollectionsBooks_Hash();
				System.out.println("FetchCategoriesCollectionsBooksHash: "+FetchCategoriesCollectionsBooksHash);
				Response FetchCatCollection_Books = FetchCatCollectionBooks.fetchCatCollectionBooks();
				System.out.println("FetchCatCollection_Books: "+FetchCatCollection_Books);*/
				Response V1RefreshBooks_hash = V1RefreshBooks.v1refreshBooks(startDate);
				System.out.println("V1RefreshBooks_hash: "+V1RefreshBooks_hash);
				
				/*Response GETcategoryBookListV1res = CategoryBookListV1.categoryBookListV1(catname);
				System.out.println("categoryBookListV1res : " +GETcategoryBookListV1res);
				Response CategoryBookListV2Res = CategoryBookListV2.categoryBookListV2(catname);
				System.out.println("CategoryBookListV2Res : " +CategoryBookListV2Res);
				Response FetchCategorybooksV1Res = FetchCategorybooksV1.fetchCategorybooksV1(catname);
				System.out.println("FetchCategorybooksV1Res : " +FetchCategorybooksV1Res);

				Response fetchPreferredLocale_res = FetchPreferredLocale.fetchPreferredLocale();
				System.out.println("fetchPreferredLocale_res: "+fetchPreferredLocale_res);
				Response GETdownloadBookForANDROID_offline = DownloadBook.downloadBookForANDROID_offline();
				System.out.println("downloadBookForANDROID_offline: "+GETdownloadBookForANDROID_offline);
				Response GETdownloadBookForIPAD_offline=DownloadBook.downloadBookForIPAD_offline();
				System.out.println("downloadBookForIPAD_offline: "+GETdownloadBookForIPAD_offline);
				//JDBC_QC.JDBC_connection_QC();
				Response GETdownloadBookForWindows_offline=DownloadBook.downloadBookForWindows_offline();
				System.out.println("downloadBookForWindows_offline: "+GETdownloadBookForWindows_offline);
				System.out.println("consumerKey: "+consumerKey);
				System.out.println("consumerSecret: "+consumerSecret);
				Response POSTresetDevices_clientUserID= ResetDevices_clientUserID.resetDevices_clientUserID(consumerKey, consumerSecret);
				System.out.println("resetDevices_clientUserID: " +POSTresetDevices_clientUserID);		
				Response searchV2res =SearchV2.searchV2();
				System.out.println("searchV2res : "+searchV2res);
				Response POSTSearchV2_OAuthres =SearchV2_OAuth.searchV2_OAuth(consumerKey, consumerSecret);
				System.out.println("SearchV2_OAuthres : "+POSTSearchV2_OAuthres);
				Response POSTsearchV2_AdvanceFilterres =SearchV2_AdvanceFilter.searchV2_AdvanceFilter();
				System.out.println("searchV2_AdvanceFilterres : "+POSTsearchV2_AdvanceFilterres);
				Response Booklist = BookList.bookList();
				System.out.println("Booklist_res : "+Booklist);

				Response POSTupdateUser_OAuthres = UpdateUser_OAuth.updateUser_OAuth(consumerKey, consumerSecret);
				System.out.println("updateUser_OAuthres : "+POSTupdateUser_OAuthres);
				//Response updateUser_captital_OAuthres =UpdateUser_captital_OAuth.updateUser_captital_OAuth(consumerKey, consumerSecret);
				//System.out.println("updateUser_captital_OAuthres : "+updateUser_captital_OAuthres);

				Response getSecureURLres =GetSecureURL.getSecureURL();
				System.out.println("getSecureURLres : "+getSecureURLres);
				System.out.println("startDate :: "+startDate); 
				Response bookdetails_res =Bookdetails.bookdetails(startDate);
				System.out.println("bookdetails_res : "+bookdetails_res);
				Response GETfetchCategoriesCollectionsres =FetchCategoriesCollections.fetchCategoriesCollections();
				System.out.println("fetchCategoriesCollectionsres : "+GETfetchCategoriesCollectionsres);
				Response GETfetchCategoriesCollectionsBooksres =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks();
				System.out.println("fetchCategoriesCollectionsBooksres : "+GETfetchCategoriesCollectionsBooksres);
				Response GETcategoriesV1res =CategoriesV1.categoriesV1();
				System.out.println("categoriesV1res : "+GETcategoriesV1res);
				Response GETcategoriesV2res =CategoriesV2.categoriesV2();
				System.out.println("categoriesV2res : " +GETcategoriesV2res);
				Response GETbooks_OAuthres =Books_OAuth.books_OAuth(consumerKey, consumerSecret);
				System.out.println("books_OAuthres : " +GETbooks_OAuthres);
				Response GETListBooksV1_OAuthres =ListBooksV1_OAuth.listBooksV1_OAuth(consumerKey, consumerSecret);
				System.out.println("ListBooksV1_OAuthres : "+GETListBooksV1_OAuthres);
				Response ListBooksV1_OAuth_With_Pagi_res = ListBooksV1_OAuth.listBooksV1_OAuth_With_Pagi(startIndex, endIndex, consumerKey, consumerSecret);
				System.out.println("ListBooksV1_OAuth_With_Pagi_res : "+ListBooksV1_OAuth_With_Pagi_res);

				Response GETListBooks_OAuth_With_Pagi_res =ListBooksV1_OAuth.listBooksV1_OAuth_With_Pagi(startIndex, endIndex, consumerKey, consumerSecret);
				System.out.println("ListBooks_OAuth_With_Pagi_res : "+GETListBooks_OAuth_With_Pagi_res);
				Response GETListBooksV1_OAuth_res = ListBooksV1_OAuth.listBooksV1_OAuth(consumerKey, consumerSecret);
				System.out.println("ListBooksV1_OAuth_res : "+GETListBooksV1_OAuth_res);
				clientBookID = GETListBooksV1_OAuth_res.then().extract().path("bookList.book.clientBookID[1]");
				System.out.println("clientBookID1: " +clientBookID);
				Response GETgetBookMetadata_res = GetBookMetadata.getBookMetadata(consumerKey, consumerSecret);
				System.out.println("getBookMetadata_res : " +GETgetBookMetadata_res);
				Response GETbookMetadata_res = BookMetadata.bookMetadata(consumerKey, consumerSecret);
				System.out.println("bookMetadata_res : " +GETbookMetadata_res);
				Response GETclientUserID_books_res = ClientUserID_books.clientUserID_books(consumerKey, consumerSecret, search);
				System.out.println("clientUserID_books_res : "+GETclientUserID_books_res);
				Response GETuserAssignedBooks_OAuth_res = UserAssignedBooks_OAuth.userAssignedBooks_OAuth(consumerKey, consumerSecret);
				System.out.println("userAssignedBooks_OAuth_res : "+GETuserAssignedBooks_OAuth_res);
				Response GETuserAssignedBooks_OAuth_with_pagi_res = UserAssignedBooks_OAuth.userAssignedBooks_OAuth_with_pagi(startIndex, endIndex, consumerKey, consumerSecret);
				System.out.println("userAssignedBooks_OAuth_with_pagi_res : "+GETuserAssignedBooks_OAuth_with_pagi_res);
				Response GETrefreshBookListres = RefreshBookList.refreshBookList();
				System.out.println("refreshBookListres : "+GETrefreshBookListres);
				Response POSTv1refreshBookList_res =V1refreshBookList.v1refreshBookList();
				System.out.println("v1/refreshBookListr_res : "+POSTv1refreshBookList_res);
				Response POSTv1refreshBookList_with_pagi_res = V1refreshBookList.v1refreshBookList_with_pagi(startIndex, endIndex);
				System.out.println("v1/refreshBookList_with_pagi_res : "+POSTv1refreshBookList_with_pagi_res);
				Response POSTmarkAsFavourite_res = MarkAsFavourite.markAsFavourite();
				BookID_mark1 = bookID1;
				System.out.println("MarkAsFavourite_res : "+POSTmarkAsFavourite_res);
				Response FetchFavouriteBooks_res = FetchFavouriteBooks.fetchFavouriteBooks();
				System.out.println("FetchFavouriteBooks_res : "+FetchFavouriteBooks_res);
				Response GETunMarkAsFavourite_res = UnMarkAsFavourite.unMarkAsFavourite();
				System.out.println("unMarkAsFavourite_res : "+GETunMarkAsFavourite_res);
				FetchFavouriteBooks.fetchFavouriteBooks();
				System.out.println("FetchFavouriteBooks_res : "+FetchFavouriteBooks_res);
				Response POSTsaveSessionHistory_res = SaveSessionHistory.saveSessionHistory();
				System.out.println("saveSessionHistory_res : "+POSTsaveSessionHistory_res);
				Response FetchRecentlyViewed_res = FetchRecentlyViewed.fetchRecentlyViewed_without_pagi();
				System.out.println("FetchRecentlyViewed_res : "+FetchRecentlyViewed_res);
				Response fetchRecentlyViewed_with_pagi_res = FetchRecentlyViewed.fetchRecentlyViewed_with_pagi(startIndex, endIndex);
				System.out.println("fetchRecentlyViewed_with_pagi_res : "+fetchRecentlyViewed_with_pagi_res);*/

				/*if(catlevel.contains("4"))
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
					int level= 2;
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
				//4,bookID1, "level1", "jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com","qcteam","JB88F-WT2Q3-DPXTT"
				Response MultiCategoryBookList_res = MultiCategoryBookList.multiCategoryBookList(catlevel,bookID,sqlhost,sqlUsername,sqlPassword);
				System.out.println("MultiCategoryBookList_res : "+MultiCategoryBookList_res); */
				/*for(int i=1;i<=2;i++)
					{
					System.out.println("detailisbn = "+detailisbn.get(i));
					Response Metadata_res = Metadata.metadata(consumerKey, consumerSecret,detailisbn.get(i));
					System.out.println("Metadata_res : "+Metadata_res);
					isbnMeta = Metadata_res.then().extract().path("isbn");
					System.out.println("isbnMeta: "+isbnMeta);
					isbnMeta=detailisbn.get(i);
					Response IngectEpub_res = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,isbnMeta);
					System.out.println("IngectEpub_res : "+IngectEpub_res);
					isbnIng = IngectEpub_res.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);			
					Response IngestionStatus_res = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res);			
					}*/
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
