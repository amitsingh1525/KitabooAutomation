package com.hurix.api.runner;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.poi.hssf.record.formula.functions.Replace;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;

//import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

import com.hurix.api.externalAPIs.*;
import com.hurix.api.hashAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.ExcelUtils;
import com.hurix.api.utility.ExtractCategory;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

/*
 * 
 * Author Name:
 * SHWETA KATARE THE BRAND
 * 
 */

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
	public static int bookID6;
	public static String isbn;
	public static String isbnMeta;
	public static String isbnIng;
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
	public static String detail;
	public static String externalURI;
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
			for(int i=1;i<=4;i++)
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
				Log.info("DIS-1466");
				io.restassured.RestAssured.baseURI = detail;
				//list of services which use http class
				
				
				io.restassured.RestAssured.baseURI = detail.replace("https", "http"); //http
				
				Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"65454","IPAD");
				Validation.responseHeaderCodeValidation(authenticateValue, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(authenticateValue, HttpStatus.SC_OK);
				Validation.responseTimeValidation(authenticateValue);
				Validation.responseKeyValidation_key(authenticateValue, "userName");
				Validation.responseKeyValidation_key(authenticateValue, userName);
				Validation.responseKeyValidation_key(authenticateValue, "clientUserID");
				Validation.responseKeyValidation_key(authenticateValue, "firstName");
				Validation.responseKeyValidation_key(authenticateValue, "lastName");
				Validation.responseKeyValidation_key(authenticateValue, "email");
				Validation.responseKeyValidation_key(authenticateValue, "roles");
				Validation.responseKeyValidation_key(authenticateValue, "userName");
				Validation.responseKeyValidation_key(authenticateValue, "profilePicURL");
				Validation.responseKeyValidation_key(authenticateValue, "sessionCount");
				Validation.responseKeyValidation_key(authenticateValue, "userToken");
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
				/*String title = fetchBookList_with_pagination.then().extract().path("bookList.book.title[0]");
				fetchBookList_with_pagination.then().assertThat().body("title", equalTo(title.getName()));
				int sizeOfisbn= ((Object) fetchBookList_with_pagination.body()).assertThat(title).isEqualTo(11);
				System.out.println("sizeOfisbn: "+sizeOfisbn);
				fetchBookList_with_pagination.then().body("isbn().size()", is(3));
				Assert.assertTrue(fetchBookList_with_pagination.equals(sizeOfisbn>=2));*/
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
				totalbooks= fetchBookList_with_pagination.then().assertThat().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 4);
				System.out.println("fetchBookList_with_pagination_RES : " +fetchBookList_with_pagination);


				//******START FETCHBOOKLIST PERMUTATION
				Response FetchbookListPermutation=FetchBookList.fetchBookList_with_permutation("isbn", "ASC", userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation);
				System.out.println("FetchbookListPermutation : "+FetchbookListPermutation);


				Response FetchbookListPermutation_withpagi=FetchBookList.fetchBookList_withPAGI_permutation("isbn","ASC",0,8,userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagi, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagi, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagi);
				System.out.println("FetchbookListPermutation_withpagi : "+FetchbookListPermutation_withpagi);

				Response FetchbookListPermutationDEC=FetchBookList.fetchBookList_with_permutation("isbn","DESC",userToken,"464","IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutationDEC, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutationDEC, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutationDEC);
				System.out.println("FetchbookListPermutation.DEC : "+FetchbookListPermutationDEC);


				Response FetchbookListPermutation_withpagiDEC=FetchBookList.fetchBookList_withPAGI_permutation("isbn","DESC",0,8,userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagiDEC, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagiDEC, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagiDEC);
				System.out.println("FetchbookListPermutation_withpagi : "+FetchbookListPermutation_withpagiDEC);

				////$$$$$$$$ END FETCHBOOKLIST PERMUTATION
			Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"45616452","IPAD");
				Validation.responseCodeValidation1(fetchBookList_without_pagination, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(fetchBookList_without_pagination, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchBookList_without_pagination);
				Validation.responseKeyValidation_key(fetchBookList_with_pagination, "isbn");
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
				Validation.responseKeyValidation_key(fetchBookList_with_pagination, "classID");
				//Validation.responseKeyValidation_key(jsonResponse, Title)
				bookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[0]");
				System.out.println("bookID_1: "+bookID1);
				bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[1]");			
				System.out.println("bookID: "+bookID2);
				bookID6 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[6]");
				System.out.println("bookID6 :: "+bookID6);
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

				archiveDate = fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[0]");
				System.out.println("archiveDate:"+archiveDate);
				archiveDate6=fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[6]");
				System.out.println("archiveDate:"+archiveDate);

				Response GETfetchBookCount_res = FetchBookCount.fetchBookCount(userToken,"45616452","IPAD");
				Validation.responseHeaderCodeValidation(GETfetchBookCount_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(GETfetchBookCount_res,HttpStatus.SC_OK);
				Validation.responseTimeValidation(GETfetchBookCount_res);
				Validation.responseKeyValidation_key(GETfetchBookCount_res, "totalbooks");
				Validation.responseKeyValidation_key(GETfetchBookCount_res, "timestamp");
				System.out.println("fetchBookCount_res : "+GETfetchBookCount_res);

				
				//HASH APIS

				Response FetchBookListHash_res = FetchBookListHash.fetchBookListHash(userToken,"45616452","IPAD");
				Validation.responseHeaderCodeValidation(FetchBookListHash_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookListHash_res,HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookListHash_res);
				Validation.responseKeyValidation_key(FetchBookListHash_res, "isbn");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "title");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "id");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "archiveDate");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "collectionID");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "assetType");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "assignedOn");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "bookActive");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "bookCode");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "bookId");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "category");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "categoryIdList");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "categoryList");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "locale");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "collectionThumbnail");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "collectionType");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "formats");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "readingPercentage");
				Validation.responseKeyValidation_key(FetchBookListHash_res, "classID");
				System.out.println("FetchBookListHash_res: "+FetchBookListHash_res);

				Response FetchCatBookList_res =FetchCatBookList.fetchCatBookList(catname,userToken,"45616452","IPAD");
				Validation.responseHeaderCodeValidation(FetchCatBookList_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchCatBookList_res,HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchCatBookList_res);
				Validation.responseKeyValidation_key(FetchCatBookList_res, "isbn");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "archiveDate");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "assetType");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "assignedOn");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "bookCode");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "bookId");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "category");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "categoryIdList");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "categoryList");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "collectionID");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "collectionThumbnail");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "collectionType");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "description");
				Validation.responseKeyValidation_key(FetchCatBookList_res, "ebookID");
				System.out.println("FetchCatBookList_res: "+FetchCatBookList_res);

				Response CategoriesV1Hash_res = CategoriesV1Hash.categoriesV1Hash(userToken,"45616452","IPAD");
				Validation.responseHeaderCodeValidation(CategoriesV1Hash_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(CategoriesV1Hash_res,HttpStatus.SC_OK);
				Validation.responseTimeValidation(CategoriesV1Hash_res);
				Validation.responseKeyValidation_key(CategoriesV1Hash_res, "hash");
				Validation.responseKeyValidation_key(CategoriesV1Hash_res, "id");
				Validation.responseKeyValidation_key(CategoriesV1Hash_res, "name");
				Validation.responseKeyValidation_key(CategoriesV1Hash_res, "numberOfBooks");
				Validation.responseKeyValidation_key(CategoriesV1Hash_res, "totalCategories");
				totalCategories=CategoriesV1Hash_res.then().extract().path("totalCategories");
				categoriesname=CategoriesV1Hash_res.then().extract().path("categories[0].name");
				Validation.responseKeyAndValue(CategoriesV1Hash_res, "name",categoriesname);
				Validation.responseISGreater("totalCategories", totalCategories, 3);				 
				System.out.println("CategoriesV1Hash_res : "+CategoriesV1Hash_res);


				Response CategoriesV2Hash_res = CategoriesV2Hash.categoriesV2Hash(userToken,"45616452","IPAD");
				Validation.responseHeaderCodeValidation(CategoriesV2Hash_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(CategoriesV2Hash_res,HttpStatus.SC_OK);
				Validation.responseTimeValidation(CategoriesV2Hash_res);
				Validation.responseKeyValidation_key(CategoriesV2Hash_res, "hash");
				Validation.responseKeyValidation_key(CategoriesV2Hash_res, "id");
				Validation.responseKeyValidation_key(CategoriesV2Hash_res, "name");
				Validation.responseKeyValidation_key(CategoriesV2Hash_res, "totalCategories");
				totalCategories=CategoriesV2Hash_res.then().extract().path("totalCategories");
				categoriesname=CategoriesV2Hash_res.then().extract().path("categories[0].name");
				Validation.responseKeyAndValue(CategoriesV2Hash_res, "name",categoriesname);
				Validation.responseISGreater("totalCategories", totalCategories, 3);	
				System.out.println("CategoriesV2Hash_res: "+CategoriesV2Hash_res);

				Response RefreshBookList_Hash = RefreshBookListHash.refreshBookListHash(userToken,"45616452","IPAD");
				Validation.responseHeaderCodeValidation(RefreshBookList_Hash, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(RefreshBookList_Hash, HttpStatus.SC_OK);
				Validation.responseTimeValidation(RefreshBookList_Hash);
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "archiveDate");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "assetType");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "assignedOn");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "author");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "bookActive");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "bookCode");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "bookDisLikeCount");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "bookId");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "bookLikeCount");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "category");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "categoryIdList");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "categoryList");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "collectionID");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "collectionTitle");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "collectionType");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "ebookID");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "expiryDate");
				Validation.responseKeyValidation_key(RefreshBookList_Hash, "favourite");
				System.out.println("RefreshBookList_Hash: "+RefreshBookList_Hash);

				Response FetchRecentlyViewedBook_Hash = FetchRecentlyViewedBooksSecuredHash.fetchRecentlyViewedBooksSecuredHash(userToken,"45616452","IPAD",bookID1);
				Validation.responseHeaderCodeValidation(FetchRecentlyViewedBook_Hash, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchRecentlyViewedBook_Hash, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchRecentlyViewedBook_Hash);
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "archiveDate");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "assetType");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "assignedOn");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "author");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "bookActive");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "bookCode");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "bookId");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "category");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "categoryIdList");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "categoryList");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "collectionID");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "collectionTitle");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "collectionType");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "ebookID");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "expiryDate");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "isbn");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "pages");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "readingPercentage");
				Validation.responseKeyValidation_key(FetchRecentlyViewedBook_Hash, "encryption");
				System.out.println("FetchRecentlyViewedBook_Hash: "+FetchRecentlyViewedBook_Hash);

				Response GetBookDetailsSecured_Hash = GetBookDetailsSecuredHash.getBookDetailsSecuredHash("2020-05-21 18:29:09.0",userToken,"45616452","IPAD",ebookID1,assetType);
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

				Response UnMarkAsFavourite_Hash = UnMarkAsFavouriteHash.unMarkAsFavourite(markesAsFav,userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(UnMarkAsFavourite_Hash, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(UnMarkAsFavourite_Hash, HttpStatus.SC_OK);
				Validation.responseTimeValidation(UnMarkAsFavourite_Hash);
				Validation.responseNOTKeyValidation_key(UnMarkAsFavourite_Hash, ""+markesAsFav+"");
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
				//"2019/10/31 14:46:04"
				Response V1RefreshBooks_hash = V1RefreshBooksHash.v1refreshBooks(archiveDate,bookID1,bookID2,userToken,"56454","IPAD");
				System.out.println("V1RefreshBooks_hash: "+V1RefreshBooks_hash);

				Response V1RefreshBooks_hash2 = V1RefreshBooksHash.v1refreshBooks_op("2020/08/20 10:52:23",bookID6,bookID2,"UPDATE","NEW",userToken,"56454","IPAD");
				System.out.println("V1RefreshBooks_hash: "+V1RefreshBooks_hash2);

				//HASH APIS ENDS 

				Response fetchPreferredLocale_res = FetchPreferredLocale.fetchPreferredLocale(userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(fetchPreferredLocale_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchPreferredLocale_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchPreferredLocale_res);
				Validation.responseKeyValidation_key(fetchPreferredLocale_res,"locale");
				Validation.responseKeyValidation_key(fetchPreferredLocale_res,"responseMsg");
				Validation.responseKeyValidation_key(fetchPreferredLocale_res,"Ok");
				System.out.println("fetchPreferredLocale_res: "+fetchPreferredLocale_res);


				Response downloadBookForANDROID_offline = DownloadBook.downloadBook(userToken,"ds9465","ANDROID",bookID1,"offline");
				Validation.responseHeaderCodeValidation(downloadBookForANDROID_offline, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForANDROID_offline, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForANDROID_offline);
				Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"content_ownership");
				content_ownership=downloadBookForANDROID_offline.then().extract().path("content_ownership");
				Validation.responseKeyAndValue(downloadBookForANDROID_offline, content_ownership, "FALSE");
				Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"timestamp");
				System.out.println("downloadBookForANDROID_offline: "+downloadBookForANDROID_offline);


				Response downloadBookForANDROID_online = DownloadBook.downloadBook(userToken,"ds9465","ANDROID",bookID1,"online");
				Validation.responseHeaderCodeValidation(downloadBookForANDROID_online, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForANDROID_online, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForANDROID_online);
				Validation.responseKeyValidation_key(downloadBookForANDROID_online,"content_ownership");
				content_ownership=downloadBookForANDROID_online.then().extract().path("content_ownership");
				Validation.responseKeyAndValue(downloadBookForANDROID_online, content_ownership, "FALSE");
				Validation.responseKeyValidation_key(downloadBookForANDROID_online,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForANDROID_online,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForANDROID_online,"timestamp");
				System.out.println("downloadBookForANDROID_online: "+downloadBookForANDROID_online);

				Response downloadBookForIPAD_online = DownloadBook.downloadBook(userToken,"ds9465","IPAD",bookID1,"online");
				Validation.responseHeaderCodeValidation(downloadBookForIPAD_online, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForIPAD_online, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForIPAD_online);
				Validation.responseKeyValidation_key(downloadBookForIPAD_online,"content_ownership");
				content_ownership=downloadBookForIPAD_online.then().extract().path("content_ownership");
				Validation.responseKeyAndValue(downloadBookForIPAD_online, content_ownership, "FALSE");
				Validation.responseKeyValidation_key(downloadBookForIPAD_online,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForIPAD_online,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForIPAD_online,"timestamp");
				System.out.println("downloadBookForIPAD_online: "+downloadBookForIPAD_online);

				Response downloadBookForIPAD_offline = DownloadBook.downloadBook(userToken,"ds9465","IPAD",bookID1,"offline");
				Validation.responseHeaderCodeValidation(downloadBookForIPAD_offline, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForIPAD_offline, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForIPAD_offline);
				Validation.responseKeyValidation_key(downloadBookForIPAD_offline,"content_ownership");
				content_ownership=downloadBookForIPAD_offline.then().extract().path("content_ownership");
				Validation.responseKeyAndValue(downloadBookForIPAD_offline, content_ownership, "FALSE");
				Validation.responseKeyValidation_key(downloadBookForIPAD_offline,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForIPAD_offline,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForIPAD_offline,"timestamp");
				System.out.println("downloadBookForIPAD_offline: "+downloadBookForIPAD_offline);

				Response downloadBookForWINDOWNS_offline = DownloadBook.downloadBook(userToken,"ds9465","WINDOWNS",bookID1,"offline");
				Validation.responseHeaderCodeValidation(downloadBookForWINDOWNS_offline, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForWINDOWNS_offline, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForWINDOWNS_offline);
				Validation.responseKeyValidation_key(downloadBookForWINDOWNS_offline,"content_ownership");
				content_ownership=downloadBookForWINDOWNS_offline.then().extract().path("content_ownership");
				Validation.responseKeyAndValue(downloadBookForWINDOWNS_offline, content_ownership, "FALSE");
				Validation.responseKeyValidation_key(downloadBookForWINDOWNS_offline,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForWINDOWNS_offline,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForWINDOWNS_offline,"timestamp");
				System.out.println("downloadBookForWINDOWNS_offline: "+downloadBookForWINDOWNS_offline);

				Response downloadBookForWINDOWNS_online = DownloadBook.downloadBook(userToken,"ds9465","WINDOWNS",bookID1,"online");
				Validation.responseHeaderCodeValidation(downloadBookForWINDOWNS_online, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForWINDOWNS_online, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForWINDOWNS_online);
				Validation.responseKeyValidation_key(downloadBookForWINDOWNS_online,"content_ownership");
				content_ownership=downloadBookForWINDOWNS_online.then().extract().path("content_ownership");
				Validation.responseKeyAndValue(downloadBookForWINDOWNS_online, content_ownership, "FALSE");
				Validation.responseKeyValidation_key(downloadBookForWINDOWNS_online,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForWINDOWNS_online,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForWINDOWNS_online,"timestamp");
				System.out.println("downloadBookForWINDOWNS_online: "+downloadBookForWINDOWNS_online);

				Response downloadBookForHTPM5_offline = DownloadBook.downloadBook(userToken,"ds9465","HTML5",bookID1,"offline");
				Validation.responseHeaderCodeValidation(downloadBookForHTPM5_offline, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForHTPM5_offline, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForHTPM5_offline);
				Validation.responseKeyValidation_key(downloadBookForHTPM5_offline,"content_ownership");
				content_ownership=downloadBookForHTPM5_offline.then().extract().path("content_ownership");
				Validation.responseKeyAndValue(downloadBookForHTPM5_offline, content_ownership, "FALSE");
				Validation.responseKeyValidation_key(downloadBookForHTPM5_offline,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForHTPM5_offline,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForHTPM5_offline,"timestamp");
				System.out.println("downloadBookForHTPM5_offline: "+downloadBookForHTPM5_offline);

				Response downloadBookForPC_ONLINE = DownloadBook.downloadBook(userToken,"ds9465","PC",bookID1,"online");
				Validation.responseHeaderCodeValidation(downloadBookForPC_ONLINE, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForPC_ONLINE, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForPC_ONLINE);
				Validation.responseKeyValidation_key(downloadBookForPC_ONLINE,"content_ownership");
				content_ownership=downloadBookForPC_ONLINE.then().extract().path("content_ownership");
				Validation.responseKeyAndValue(downloadBookForPC_ONLINE, content_ownership, "FALSE");
				Validation.responseKeyValidation_key(downloadBookForPC_ONLINE,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForPC_ONLINE,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForPC_ONLINE,"timestamp");
				System.out.println("downloadBookForPC_ONLINE: "+downloadBookForPC_ONLINE);


				System.out.println("consumerKey: "+consumerKey);
				System.out.println("consumerSecret: "+consumerSecret);
				Response searchV2res = SearchV2.searchV2("Native",userToken,"ds9465","PC");
				Validation.responseHeaderCodeValidation(searchV2res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(searchV2res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(searchV2res);
				Validation.responseKeyValidation_key(searchV2res,"searchResult");
				Validation.responseKeyValidation_key(searchV2res,"_id");
				Validation.responseKeyValidation_key(searchV2res,"_index");
				Validation.responseKeyValidation_key(searchV2res,"ISBN");
				Validation.responseKeyValidation_key(searchV2res,"bookTitle");
				Validation.responseKeyValidation_key(searchV2res,"description");
				Validation.responseKeyValidation_key(searchV2res,"_type");
				Validation.responseKeyValidation_key(searchV2res,"searchResult");
				total=searchV2res.then().extract().path("total");
				Validation.responseKeyValidation_key(searchV2res, "");
				Validation.responseISGreater("total", total, 1);
				System.out.println("searchV2res : "+searchV2res);

				Response SearchV2_OAuthres = SearchV2_OAuth.searchV2_OAuth("Native",consumerKey, consumerSecret,clientUserID);
				Validation.responseHeaderCodeValidation(SearchV2_OAuthres, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(SearchV2_OAuthres, HttpStatus.SC_OK);
				Validation.responseTimeValidation(SearchV2_OAuthres);
				Validation.responseKeyValidation_key(SearchV2_OAuthres,"searchResult");
				Validation.responseKeyValidation_key(SearchV2_OAuthres,"_id");
				Validation.responseKeyValidation_key(SearchV2_OAuthres,"_index");
				Validation.responseKeyValidation_key(SearchV2_OAuthres,"ISBN");
				Validation.responseKeyValidation_key(SearchV2_OAuthres,"bookTitle");
				Validation.responseKeyValidation_key(SearchV2_OAuthres,"description");
				Validation.responseKeyValidation_key(SearchV2_OAuthres,"_type");
				Validation.responseKeyValidation_key(SearchV2_OAuthres,"searchResult");
				total=SearchV2_OAuthres.then().extract().path("total");
				Validation.responseKeyValidation_key(SearchV2_OAuthres, "");
				Validation.responseISGreater("total", total, 1);
				System.out.println("SearchV2_OAuthres : "+SearchV2_OAuthres);

				Response searchV2_AdvanceFilterres =SearchV2_AdvanceFilter.searchV2_AdvanceFilter("Native",userToken, "5454545","IPAD",clientUserID);
				Validation.responseHeaderCodeValidation(searchV2_AdvanceFilterres, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(searchV2_AdvanceFilterres, HttpStatus.SC_OK);
				Validation.responseTimeValidation(searchV2_AdvanceFilterres);
				Validation.responseKeyValidation_key(searchV2_AdvanceFilterres,"searchResult");
				Validation.responseKeyValidation_key(searchV2_AdvanceFilterres,"_id");
				Validation.responseKeyValidation_key(searchV2_AdvanceFilterres,"_index");
				Validation.responseKeyValidation_key(searchV2_AdvanceFilterres,"ISBN");
				Validation.responseKeyValidation_key(searchV2_AdvanceFilterres,"bookTitle");
				Validation.responseKeyValidation_key(searchV2_AdvanceFilterres,"description");
				Validation.responseKeyValidation_key(searchV2_AdvanceFilterres,"_type");
				Validation.responseKeyValidation_key(searchV2_AdvanceFilterres,"searchResult");
				total=searchV2_AdvanceFilterres.then().extract().path("total");
				Validation.responseKeyValidation_key(searchV2_AdvanceFilterres, "");
				Validation.responseISGreater("total", total, 1);
				System.out.println("searchV2_AdvanceFilterres : "+searchV2_AdvanceFilterres);

				Response Booklist = BookList.bookList(userToken,"56454", "IPAD");
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

				Response getSecureURLres =GetSecureURL.getSecureURL(userToken, "5489989","IPAD",type);
				Validation.responseHeaderCodeValidation(getSecureURLres, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(getSecureURLres, HttpStatus.SC_OK);
				Validation.responseTimeValidation(getSecureURLres);
				Validation.responseKeyValidation_key(getSecureURLres,"responseMsg");
				responseMsg = getSecureURLres.then().extract().path("responseMsg");
				Validation.responseNOTKeyValidation_key(getSecureURLres, "URL_NOT_FORMED");
				System.out.println("getSecureURLres : "+getSecureURLres);

				System.out.println("startDate :: "+startDate); 
				Response bookdetails_res =Bookdetails.bookdetails("2019/10/31 14:46:04",userToken, "5489989","IPAD",""+ebookID1+"",""+assetType+"");
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
				System.out.println("bookdetails_res : "+bookdetails_res);

				Response fetchCategoriesCollectionsres =FetchCategoriesCollections.fetchCategoriesCollections(userToken, "5489989","IPAD");
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsres, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsres, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsres);
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"categories");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"name");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"numberOfBooks");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"collections");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"name");
				System.out.println("fetchCategoriesCollectionsres : "+fetchCategoriesCollectionsres);

				Response fetchCategoriesCollectionsBooksres =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks(userToken, "5489989","IPAD",catname1,collectionName1);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooksres);

				Response categoriesV1res = CategoriesV1.categoriesV1(userToken, "5489989","IPAD");
				Validation.responseHeaderCodeValidation(categoriesV1res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoriesV1res,HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoriesV1res);
				Validation.responseKeyValidation_key(categoriesV1res, "hash");
				Validation.responseKeyValidation_key(categoriesV1res, "id");
				Validation.responseKeyValidation_key(categoriesV1res, "name");
				Validation.responseKeyValidation_key(categoriesV1res, "totalCategories");
				totalCategories=categoriesV1res.then().extract().path("totalCategories");
				categoriesname=categoriesV1res.then().extract().path("categories[0].name");
				Validation.responseKeyAndValue(categoriesV1res, "name",categoriesname);
				Validation.responseISGreater("totalCategories", totalCategories, 3);	
				System.out.println("categoriesV1res : "+categoriesV1res);

				Response categoriesV2res =CategoriesV2.categoriesV2(userToken, "5489989","IPAD");
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
				System.out.println("categoriesV2res : " +categoriesV2res);


				String sqlhost="jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com";
				String sqlUsername="qcteam";
				String sqlPassword="JB88F-WT2Q3-DPXTT";		

				Response categoryBookListV1res = CategoryBookListV1.categoryBookListV1(""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
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
				System.out.println("categoryBookListV1res : " +categoryBookListV1res);


				Response CategoryBookListV2Res = CategoryBookListV2.categoryBookListV2(""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
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


				Response FetchCategorybooksV1Res = FetchCategorybooksV1.fetchCategorybooksV1(catname,userToken);
				Validation.responseHeaderCodeValidation(FetchCategorybooksV1Res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchCategorybooksV1Res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchCategorybooksV1Res);
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "assetType");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "assignedOn");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "author");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "bookActive");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "category");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "categoryIdList");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "categoryList");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "collectionID");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "collectionThumbnail");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "collectionTitle");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "collectionType");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "description");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "ebookID");
				Validation.responseKeyValidation_key(FetchCategorybooksV1Res, "expiryDate");
				System.out.println("FetchCategorybooksV1Res : " +FetchCategorybooksV1Res);

				/*Response multiCategories_res = MultiCategories.multiCategories(catlevel,userToken,"fs445","IPAD");
				Validation.responseHeaderCodeValidation(multiCategories_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(multiCategories_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(multiCategories_res);
				Validation.responseKeyValidation_key(multiCategories_res, "categories");
				Validation.responseKeyValidation_key(multiCategories_res, "collectionCount");
				Validation.responseKeyValidation_key(multiCategories_res, "bookCount");
				System.out.println("multiCategories_res : "+multiCategories_res);
				//int bookID=bookID1;

				Response MultiCategoryBookList_res = MultiCategoryBookList.multiCategoryBookList(catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				Validation.responseHeaderCodeValidation(MultiCategoryBookList_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList_res);
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "totalbooks");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "category");
				Validation.responseKeyValidation_key(MultiCategoryBookList_res, "description");
				System.out.println("MultiCategoryBookList_res : "+MultiCategoryBookList_res); 

				Response MultiCategoryCollection_BookList=MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollection_BookList, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollection_BookList, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollection_BookList);
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "totalbooks");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "category");
				Validation.responseKeyValidation_key(MultiCategoryCollection_BookList, "description");
				System.out.println("MultiCategoryCollection_BookList : "+MultiCategoryCollection_BookList);*/

				Response books_OAuthres = Books_OAuth.books_OAuth(consumerKey, consumerSecret);
				System.out.println("books_OAuthres : " +books_OAuthres);

				Response ListBooksV1_OAuth_With_Pagi_res = ListBooksV1_OAuth.listBooksV1_OAuth_With_Pagi(0, 60, consumerKey, consumerSecret);
				//Validation.responseISGreater(variable, key, value);
				System.out.println("ListBooksV1_OAuth_With_Pagi_res : "+ListBooksV1_OAuth_With_Pagi_res);

				Response ListBooksV1_OAuth_With_Pageno_res = ListBooksV1_OAuth.listBooksV1_OAuth_With_PageNO(0, 6, consumerKey, consumerSecret);
				System.out.println("ListBooksV1_OAuth_With_Pageno_res : "+ListBooksV1_OAuth_With_Pageno_res);

				Response listBooksV1_OAuth_without_pagires =ListBooksV1_OAuth.listBooksV1_OAuth_without_pagi(consumerKey, consumerSecret);
				System.out.println("listBooksV1_OAuth_without_pagires : "+listBooksV1_OAuth_without_pagires);

				Response ListBooks_OAuth_withpagi_res= ListBooks_OAuth.listBooks_OAuth_With_Pagi(0, 60, consumerKey, consumerSecret);
				System.out.println("ListBooks_OAuth_withpagi_res : "+ListBooks_OAuth_withpagi_res);

				Response ListBooks_OAuth_without_Pagi_res = ListBooks_OAuth.listBooks_OAuth_withoutpagi(consumerKey, consumerSecret);
				System.out.println("ListBooks_OAuth_res : "+ListBooks_OAuth_without_Pagi_res);
				clientBookID = ListBooks_OAuth_without_Pagi_res.then().extract().path("bookList.book.clientBookID[1]");
				System.out.println("clientBookID1: " +clientBookID);

				Response ListBooks_with_pageno=ListBooks_OAuth.listBooks_OAuth_With_PageNO(0, 5, consumerKey, consumerSecret);
				Validation.responseHeaderCodeValidation(ListBooks_with_pageno, 200);
				Validation.responseCodeValidation1(ListBooks_with_pageno, 200);
				System.out.println("ListBooks_with_pageno :: "+ListBooks_with_pageno);


				Response getBookMetadata_res = GetBookMetadata.getBookMetadata(consumerKey, consumerSecret,clientBookID);
				System.out.println("getBookMetadata_res : " +getBookMetadata_res);

				Response bookMetadata_res = BookMetadata.bookMetadata(consumerKey, consumerSecret,bookID1);
				System.out.println("bookMetadata_res : " +bookMetadata_res);


				Response clientUserID_books_res = ClientUserID_books.clientUserID_books(consumerKey, consumerSecret, search,clientUserID);
				System.out.println("clientUserID_books_res : "+clientUserID_books_res);
			
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
				System.out.println("userAssignedBooks_res : "+userAssignedBooks_res);

				Response userAssignedBooks_withPagi_Res = UserAssignedBooks_OAuth.userAssignedBooks_OAuth_with_pagi(0, 80, consumerKey, consumerSecret);
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
				totalbooks=userAssignedBooks_withPagi_Res.then().extract().path("totalbooks");
				Log.info("totalbooks: "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);
				System.out.println("userAssignedBooks_withPagi_Res : "+userAssignedBooks_withPagi_Res);

				Response refreshBookListres = RefreshBookList.refreshBookList(userToken,"56496","IPAD");
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
				
				
				//2019/10/31 14:46:04
				Response v1refreshBookList_res =V1refreshBookList.v1refreshBookList("archiveDate","NEW","UPDATE",""+bookID1+"",""+bookID2+"",userToken,"56454", "IPAD",clientID);
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


				
				Response markAsFavourite_res = MarkAsFavourite.markAsFavourite(bookID1,userToken,"45564595","IPAD");
				BookID_mark1 = bookID1;
				Validation.responseHeaderCodeValidation(markAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(markAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(markAsFavourite_res);
				System.out.println("markAsFavourite_res : "+markAsFavourite_res);

				Response FetchFavouriteBooks_res = FetchFavouriteBooks.fetchFavouriteBooks(userToken,"45564595","IPAD");
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
				System.out.println("FetchFavouriteBooks_res : "+FetchFavouriteBooks_res);

				Response unMarkAsFavourite_res = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark1,userToken,"45564595","IPAD");
				Validation.responseHeaderCodeValidation(unMarkAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(unMarkAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(unMarkAsFavourite_res);
				System.out.println("unMarkAsFavourite_res : "+unMarkAsFavourite_res);

				Response FetchFavouriteBooks_res1= FetchFavouriteBooks.fetchFavouriteBooks(userToken,"45564595","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res1);
				Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_res1, ""+BookID_mark1+"");
				System.out.println("FetchFavouriteBooks_res1 : "+FetchFavouriteBooks_res1);

				Response saveSessionHistory_res = SaveSessionHistory.saveSessionHistory(userToken,"45564595","IPAD",bookID1);
				Validation.responseHeaderCodeValidation(saveSessionHistory_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(saveSessionHistory_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(saveSessionHistory_res);
				Validation.responseKeyValidation_key(saveSessionHistory_res, "ok");
				System.out.println("saveSessionHistory_res : "+saveSessionHistory_res);


				Response FetchRecentlyViewed_res = FetchRecentlyViewed.fetchRecentlyViewed_without_pagi(userToken,"54254fd","IPAD",bookID1);
				Validation.responseHeaderCodeValidation(FetchRecentlyViewed_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchRecentlyViewed_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchRecentlyViewed_res);
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "archiveDate");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "assetType");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "assignedOn");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "author");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "bookActive");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "bookCode");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "bookId");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "category");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "categoryIdList");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "categoryList");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "collectionID");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "collectionTitle");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "collectionType");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "ebookID");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "expiryDate");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "isbn");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "pages");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "readingPercentage");
				Validation.responseKeyValidation_key(FetchRecentlyViewed_res, "encryption");
				System.out.println("FetchRecentlyViewed_res : "+FetchRecentlyViewed_res);

				Response fetchRecentlyViewed_with_pagi_res = FetchRecentlyViewed.fetchRecentlyViewed_with_pagi(0,20,userToken,"hf454","IPAD",bookID1);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_with_pagi_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_with_pagi_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_with_pagi_res);
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "archiveDate");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "assetType");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "assignedOn");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "author");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "bookActive");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "bookCode");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "bookId");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "category");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "categoryIdList");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "categoryList");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "collectionID");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "collectionTitle");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "collectionType");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "ebookID");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "expiryDate");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "isbn");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "pages");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "readingPercentage");
				Validation.responseKeyValidation_key(fetchRecentlyViewed_with_pagi_res, "encryption");
				System.out.println("fetchRecentlyViewed_with_pagi_res : "+fetchRecentlyViewed_with_pagi_res);
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
