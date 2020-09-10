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
			for(int i=1;i<=2;i++)
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
				totalbooks= fetchBookList_with_pagination.then().assertThat().extract().path("totalbooks");
				System.out.println("!@#$%^%#$%^%$#$%$#   totalbooks : "+totalbooks);
				Log.info("totalbooks : "+totalbooks);
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


				Response FetchbookListPermutation2=FetchBookList.fetchBookList_with_permutation("title", "ASC", userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation2, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation2);
				System.out.println("FetchbookListPermutation : "+FetchbookListPermutation2);


				Response FetchbookListPermutation_withpagi21=FetchBookList.fetchBookList_withPAGI_permutation("title","ASC",0,8,userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagi21, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagi21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagi21);
				System.out.println("FetchbookListPermutation_withpagi : "+FetchbookListPermutation_withpagi21);

				Response FetchbookListPermutationDEC22=FetchBookList.fetchBookList_with_permutation("title","DESC",userToken,"464","IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutationDEC22, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutationDEC22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutationDEC22);
				System.out.println("FetchbookListPermutation.DEC : "+FetchbookListPermutationDEC22);


				Response FetchbookListPermutation_withpagiDEC23=FetchBookList.fetchBookList_withPAGI_permutation("title","DESC",0,8,userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagiDEC23, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagiDEC23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagiDEC23);
				System.out.println("FetchbookListPermutation_withpagi : "+FetchbookListPermutation_withpagiDEC23);

				Response FetchbookListPermutation3=FetchBookList.fetchBookList_with_permutation("title", "ASC", userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation3, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation3);
				System.out.println("FetchbookListPermutation : "+FetchbookListPermutation3);


				Response FetchbookListPermutation_withpagi31=FetchBookList.fetchBookList_withPAGI_permutation("title","ASC",0,8,userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagi31, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagi31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagi31);
				System.out.println("FetchbookListPermutation_withpagi : "+FetchbookListPermutation_withpagi31);

				Response FetchbookListPermutationDEC32=FetchBookList.fetchBookList_with_permutation("title","DESC",userToken,"464","IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutationDEC32, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutationDEC32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutationDEC32);
				System.out.println("FetchbookListPermutation.DEC : "+FetchbookListPermutationDEC32);


				Response FetchbookListPermutation_withpagiDEC33=FetchBookList.fetchBookList_withPAGI_permutation("title","DESC",0,8,userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagiDEC33, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagiDEC33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagiDEC33);
				System.out.println("FetchbookListPermutation_withpagi : "+FetchbookListPermutation_withpagiDEC33);

				Response FetchbookListPermutation4=FetchBookList.fetchBookList_with_permutation("author", "ASC", userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation4, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation4);
				System.out.println("FetchbookListPermutation : "+FetchbookListPermutation4);


				Response FetchbookListPermutation_withpagi41=FetchBookList.fetchBookList_withPAGI_permutation("author","ASC",0,8,userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagi41, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagi41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagi41);
				System.out.println("FetchbookListPermutation_withpagi : "+FetchbookListPermutation_withpagi41);

				Response FetchbookListPermutationDEC42=FetchBookList.fetchBookList_with_permutation("author","DESC",userToken,"464","IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutationDEC42, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutationDEC42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutationDEC42);
				System.out.println("FetchbookListPermutation.DEC : "+FetchbookListPermutationDEC42);


				Response FetchbookListPermutation_withpagiDEC43=FetchBookList.fetchBookList_withPAGI_permutation("author","DESC",0,8,userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagiDEC43, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagiDEC43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagiDEC43);
				System.out.println("FetchbookListPermutation_withpagi : "+FetchbookListPermutation_withpagiDEC43);

				Response FetchbookListPermutation5=FetchBookList.fetchBookList_with_permutation("archive_Date", "ASC", userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation5, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation5, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation5);
				System.out.println("FetchbookListPermutation : "+FetchbookListPermutation5);


				Response FetchbookListPermutation_withpagi51=FetchBookList.fetchBookList_withPAGI_permutation("archive_Date","ASC",0,8,userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagi51, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagi51, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagi51);
				System.out.println("FetchbookListPermutation_withpagi : "+FetchbookListPermutation_withpagi51);

				Response FetchbookListPermutationDEC52=FetchBookList.fetchBookList_with_permutation("archive_Date","DESC",userToken,"464","IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutationDEC52, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutationDEC52, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutationDEC52);
				System.out.println("FetchbookListPermutation.DEC : "+FetchbookListPermutationDEC52);


				Response FetchbookListPermutation_withpagiDEC53=FetchBookList.fetchBookList_withPAGI_permutation("archive_Date","DESC",0,8,userToken, "464", "IPAD");
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagiDEC53, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagiDEC53, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagiDEC53);
				System.out.println("FetchbookListPermutation_withpagi : "+FetchbookListPermutation_withpagiDEC53);

				////$$$$$$$$ END FETCHBOOKLIST PERMUTATION

				Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"45616452","IPAD");
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
				/*forName = fetchBookList_without_pagination.then().extract().path("bookList.book.formats.name[0]");
				Log.info("$#@$#@#@#@##@#$!@#$%^#@#$%^ forName :: "+forName);*/
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

				SaveSessionHistory.saveSessionHistory(userToken, "dssat3323", "IPAD", bookID1,"2020-09-10 18:43:20");

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

				/*Log.info("BEFORE under for loop");
				for(int i2=1;i2<=2;i2++)
				{ Log.info("here under for loop");
				forName=fetchBookList_without_pagination.then().extract().path("bookList.book.formats["+i2+"].name");
				System.out.println("$#@$#@#@#@##@#$!@#$%^#@#$%^ forName :: "+forName);
				Log.info("forName :: " +forName);//ipad
				Response downloadBookForANDROID_offline1 = DownloadBook.downloadBook(userToken,"ds9465",forName,bookID1,"offline");

				Response downloadBookForANDROID_offline2 = DownloadBook.downloadBook(userToken,"ds9465",forName,bookID1,"online");
				}*/

				Response downloadBookForANDROID_offline = DownloadBook.downloadBook(userToken,"ds9465","ANDROID",bookID1,"offline");
				Validation.responseCodeValidation1(downloadBookForANDROID_offline, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForANDROID_offline);
				Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"content_ownership");
				content_ownership=downloadBookForANDROID_offline.then().extract().path("content_ownership");
				Validation.responseKeyAndValue(downloadBookForANDROID_offline, "content_ownership", "FALSE");
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
				Validation.responseKeyAndValue(downloadBookForANDROID_online, "content_ownership", "FALSE");
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
				Validation.responseKeyAndValue(downloadBookForIPAD_online, "content_ownership", "FALSE");
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
				Validation.responseKeyAndValue(downloadBookForIPAD_offline, "content_ownership", "FALSE");
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
				Validation.responseKeyAndValue(downloadBookForWINDOWNS_offline, "content_ownership", "FALSE");
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
				Validation.responseKeyAndValue(downloadBookForWINDOWNS_online, "content_ownership", "FALSE");
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
				Validation.responseKeyAndValue(downloadBookForHTPM5_offline, "content_ownership", "FALSE");
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
				Validation.responseKeyAndValue(downloadBookForPC_ONLINE, "content_ownership", "FALSE");
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
				total=searchV2res.then().extract().path("searchResult.hits.total");
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
				total=SearchV2_OAuthres.then().extract().path("searchResult.hits.total");
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
				total=searchV2_AdvanceFilterres.then().extract().path("searchResult.hits.total");
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

				//START BOOKLIST PERMUTATION
				Response Booklist1 = BookList.bookList_per("isbn","ASC",userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist1);
				System.out.println("Booklist : "+Booklist1);

				Response Booklist11 = BookList.bookList_per_withPagi("isbn","ASC",0,10,userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist11);
				System.out.println("Booklist : "+Booklist11);

				Response Booklist12 = BookList.bookList_per("isbn","DESC",userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist12);
				System.out.println("Booklist : "+Booklist12);

				Response Booklist13 = BookList.bookList_per_withPagi("isbn","DESC",0,10,userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist13);
				System.out.println("Booklist : "+Booklist13);

				Response Booklist2 = BookList.bookList_per("author","ASC",userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist2);
				System.out.println("Booklist : "+Booklist2);

				Response Booklist21 = BookList.bookList_per_withPagi("author","ASC",0,10,userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist21);
				System.out.println("Booklist : "+Booklist21);

				Response Booklist22 = BookList.bookList_per("author","DESC",userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist22);
				System.out.println("Booklist : "+Booklist22);

				Response Booklist23 = BookList.bookList_per_withPagi("author","DESC",0,10,userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist23);
				System.out.println("Booklist : "+Booklist23);

				Response Booklist3 = BookList.bookList_per("title","ASC",userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist3);
				System.out.println("Booklist : "+Booklist3);

				Response Booklist31 = BookList.bookList_per_withPagi("title","ASC",0,10,userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist31);
				System.out.println("Booklist : "+Booklist31);

				Response Booklist32 = BookList.bookList_per("title","DESC",userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist32);
				System.out.println("Booklist : "+Booklist32);

				Response Booklist33 = BookList.bookList_per_withPagi("title","DESC",0,10,userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist33);
				System.out.println("Booklist : "+Booklist33);

				Response Booklist4 = BookList.bookList_per("archive_date","ASC",userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist4);
				System.out.println("Booklist : "+Booklist4);

				Response Booklist41 = BookList.bookList_per_withPagi("archive_date","ASC",0,10,userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist41);
				System.out.println("Booklist : "+Booklist41);

				Response Booklist42 = BookList.bookList_per("archive_date","DESC",userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist42);
				System.out.println("Booklist : "+Booklist42);

				Response Booklist43 = BookList.bookList_per_withPagi("archive_date","DESC",0,10,userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Booklist43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist43);
				System.out.println("Booklist : "+Booklist43);
				//END BOOKLIST PERMUTATION


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

				//START fetchCategoriesCollectionsBooks PERMUTATION

				Response fetchCategoriesCollectionsBooks1 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("isbn","ASC",userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks1,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks1);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks1);

				Response fetchCategoriesCollectionsBooks11 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("isbn","ASC",0,10,userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks11,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks11);
				numberOfBooks=fetchCategoriesCollectionsBooks11.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				int numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks11);

				Response fetchCategoriesCollectionsBooks12 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("isbn","DESC",userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks12,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks12);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks12);

				Response fetchCategoriesCollectionsBooks13 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("isbn","DESC",0,10,userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks13,HttpStatus.SC_OK);
				numberOfBooks=fetchCategoriesCollectionsBooks11.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks13);				
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks13);


				Response fetchCategoriesCollectionsBooks2 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("title","ASC",userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks2,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks2);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks2);

				Response fetchCategoriesCollectionsBooks21 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("title","ASC",0,10,userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks21,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks21);
				numberOfBooks=fetchCategoriesCollectionsBooks21.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks21);

				Response fetchCategoriesCollectionsBooks22 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("title","DESC",userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks22,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks22);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks22);

				Response fetchCategoriesCollectionsBooks23 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("title","DESC",0,10,userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks23,HttpStatus.SC_OK);
				numberOfBooks=fetchCategoriesCollectionsBooks23.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks23);				
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks23);

				Response fetchCategoriesCollectionsBooks3 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("author","ASC",userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks3,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks3);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks3);

				Response fetchCategoriesCollectionsBooks31 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("author","ASC",0,10,userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks31,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks31);
				numberOfBooks=fetchCategoriesCollectionsBooks31.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks31);

				Response fetchCategoriesCollectionsBooks32 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("author","DESC",userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks32,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks32);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks32);

				Response fetchCategoriesCollectionsBooks33 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("author","DESC",0,10,userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks33,HttpStatus.SC_OK);
				numberOfBooks=fetchCategoriesCollectionsBooks33.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks33);				
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks33);

				Response fetchCategoriesCollectionsBooks4 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("author","ASC",userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks4,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks4);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks3);

				Response fetchCategoriesCollectionsBooks41 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("author","ASC",0,10,userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks41,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks41);
				numberOfBooks=fetchCategoriesCollectionsBooks41.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks41);

				Response fetchCategoriesCollectionsBooks42 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("author","DESC",userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks42,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks42);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks42);

				Response fetchCategoriesCollectionsBooks43 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("author","DESC",0,10,userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks43,HttpStatus.SC_OK);
				numberOfBooks=fetchCategoriesCollectionsBooks43.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks43);				
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks43);

				Response fetchCategoriesCollectionsBooks5 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("archive_date","ASC",userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks5, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks5,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks5);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks5);

				Response fetchCategoriesCollectionsBooks51 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("archive_date","ASC",0,10,userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks51, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks51,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks51);
				numberOfBooks=fetchCategoriesCollectionsBooks41.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks51);

				Response fetchCategoriesCollectionsBooks52 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("archive_date","DESC",userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks52, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks52,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks52);
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks52);

				Response fetchCategoriesCollectionsBooks53 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("archive_date","DESC",0,10,userToken, "5489989","IPAD",catname1,collectionName1);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks53, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks53,HttpStatus.SC_OK);
				numberOfBooks=fetchCategoriesCollectionsBooks53.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks53);				
				System.out.println("fetchCategoriesCollectionsBooksres : "+fetchCategoriesCollectionsBooks53);

				//END fetchCategoriesCollectionsBooks PERMUTATION

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


				//START categoryBookLisyV1 PERMUTATION
				Response categoryBookListV1res1 = CategoryBookListV1.categoryBookListV1_per("isbn","ASC",""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res1);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res1);

				Response categoryBookListV1res11 = CategoryBookListV1.categoryBookListV1_per_withPagi("isbn","ASC",0,10,""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res11);
				totalbooks=categoryBookListV1res11.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res11);

				Response categoryBookListV1res12 = CategoryBookListV1.categoryBookListV1_per("isbn","DESC",""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res12);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res12);

				Response categoryBookListV1res13 = CategoryBookListV1.categoryBookListV1_per_withPagi("isbn","DESC",0,10,""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res13);
				totalbooks=categoryBookListV1res13.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res13);

				Response categoryBookListV1res2 = CategoryBookListV1.categoryBookListV1_per("title","ASC",""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res2);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res2);

				Response categoryBookListV1res21 = CategoryBookListV1.categoryBookListV1_per_withPagi("title","ASC",0,10,""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res21);
				totalbooks=categoryBookListV1res21.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res21);

				Response categoryBookListV1res22 = CategoryBookListV1.categoryBookListV1_per("title","DESC",""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res22);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res22);

				Response categoryBookListV1res23 = CategoryBookListV1.categoryBookListV1_per_withPagi("title","DESC",0,10,""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res23);
				totalbooks=categoryBookListV1res23.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res23);

				Response categoryBookListV1res3 = CategoryBookListV1.categoryBookListV1_per("author","ASC",""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res3);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res3);

				Response categoryBookListV1res31 = CategoryBookListV1.categoryBookListV1_per_withPagi("author","ASC",0,10,""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res31);
				totalbooks=categoryBookListV1res31.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res31);

				Response categoryBookListV1res32 = CategoryBookListV1.categoryBookListV1_per("author","DESC",""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res32);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res32);

				Response categoryBookListV1res33 = CategoryBookListV1.categoryBookListV1_per_withPagi("author","DESC",0,10,""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res33);
				totalbooks=categoryBookListV1res33.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res33);

				Response categoryBookListV1res4 = CategoryBookListV1.categoryBookListV1_per("archive_date","ASC",""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res4);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res4);

				Response categoryBookListV1res41 = CategoryBookListV1.categoryBookListV1_per_withPagi("archive_date","ASC",0,10,""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res41);
				totalbooks=categoryBookListV1res41.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res41);

				Response categoryBookListV1res42 = CategoryBookListV1.categoryBookListV1_per("archive_date","DESC",""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res42);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res42);

				Response categoryBookListV1res43 = CategoryBookListV1.categoryBookListV1_per_withPagi("archive_date","DESC",0,10,""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res43);
				totalbooks=categoryBookListV1res43.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res43);

				//END categoryBooklistv1 Permutation

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

				Response CategoryBookListV2Res_withpagi = CategoryBookListV2.categoryBookListV2_withpagi(0,10,""+category1+"",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
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
				System.out.println("CategoryBookListV2Res : " +CategoryBookListV2Res_withpagi);


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


				Response multiCategories_res = MultiCategories.multiCategories(catlevel,userToken,"fs445","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(multiCategories_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(multiCategories_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(multiCategories_res);
				Validation.responseKeyValidation_key(multiCategories_res, "categories");
				Validation.responseKeyValidation_key(multiCategories_res, "collectionCount");
				Validation.responseKeyValidation_key(multiCategories_res, "bookCount");
				Validation.responseKeyValidation_key(multiCategories_res, "hash");
				Validation.responseKeyValidation_key(multiCategories_res, "id");
				System.out.println("multiCategories_res : "+multiCategories_res);}

				//int bookID=bookID1;


				Response MultiCategoryBookList_res = MultiCategoryBookList.multiCategoryBookList(catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList_res, HttpStatus.SC_OK);
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


				//START MultcategoryBookList PERMUTATION
				Response MultiCategoryBookList1 = MultiCategoryBookList.multiCategoryBookList_Per("isbn","ASC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList1);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList1);}


				Response MultiCategoryBookList11 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("isbn","ASC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel .contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList11);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList11);}


				Response MultiCategoryBookList12 = MultiCategoryBookList.multiCategoryBookList_Per("isbn","DESC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList12);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList12);}


				Response MultiCategoryBookList13 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("isbn","DESC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList13);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList13);}


				Response MultiCategoryBookList2 = MultiCategoryBookList.multiCategoryBookList_Per("title","ASC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList2);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList2);}


				Response MultiCategoryBookList21 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("title","ASC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList21);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList21);}


				Response MultiCategoryBookList22 = MultiCategoryBookList.multiCategoryBookList_Per("title","DESC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList22);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList22);}


				Response MultiCategoryBookList23 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("title","DESC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList23);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList23);}

				Response MultiCategoryBookList3 = MultiCategoryBookList.multiCategoryBookList_Per("author","ASC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList3);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList3);}


				Response MultiCategoryBookList31 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("author","ASC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList31);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList31);}


				Response MultiCategoryBookList32 = MultiCategoryBookList.multiCategoryBookList_Per("author","DESC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList32);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList32);}

				Response MultiCategoryBookList33 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("author","DESC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel .contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList33);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList33);}


				Response MultiCategoryBookList4 = MultiCategoryBookList.multiCategoryBookList_Per("archive_date","ASC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList4);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList4);}


				Response MultiCategoryBookList41 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("archive_date","ASC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel .contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList41);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList41);}


				Response MultiCategoryBookList42 = MultiCategoryBookList.multiCategoryBookList_Per("archive_date","DESC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList42);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList42);}


				Response MultiCategoryBookList43 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("archive_date","DESC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595","IPAD");
				if(catlevel.contains("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList43);
				System.out.println("MultiCategoryBookList : "+MultiCategoryBookList43);}


				//END MultcategoryBookList PERMUTATION


				Response MultiCategoryCollection_BookList=MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
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

				Response MultiCategoryCollectionBookList1=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("isbn","ASC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList1);}

				Response MultiCategoryCollectionBookList11=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("isbn","ASC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList11);
				totalbooks=MultiCategoryCollectionBookList11.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				Response MultiCategoryCollectionBookList12=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("isbn","DESC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList12);}

				Response MultiCategoryCollectionBookList13=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("isbn","DESC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList13);
				totalbooks=MultiCategoryCollectionBookList13.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				Response MultiCategoryCollectionBookList2=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("title","ASC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList2);}

				Response MultiCategoryCollectionBookList21=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("title","ASC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList21);
				totalbooks=MultiCategoryCollectionBookList21.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				Response MultiCategoryCollectionBookList22=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("title","DESC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList22);}

				Response MultiCategoryCollectionBookList23=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("title","DESC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList23);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList23);
				totalbooks=MultiCategoryCollectionBookList23.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				Response MultiCategoryCollectionBookList3=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("author","ASC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList3);}

				Response MultiCategoryCollectionBookList31=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("author","ASC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList31);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList31);
				totalbooks=MultiCategoryCollectionBookList31.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				Response MultiCategoryCollectionBookList32=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("author","DESC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList32);}

				Response MultiCategoryCollectionBookList33=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("author","DESC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList33);Validation.responseTimeValidation(MultiCategoryCollectionBookList33);
				totalbooks=MultiCategoryCollectionBookList33.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				Response MultiCategoryCollectionBookList4=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("archive_date","ASC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList4);}

				Response MultiCategoryCollectionBookList41=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("archive_date","ASC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList41);Validation.responseTimeValidation(MultiCategoryCollectionBookList33);
				totalbooks=MultiCategoryCollectionBookList41.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				Response MultiCategoryCollectionBookList42=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("archive_date","DESC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList42);}

				Response MultiCategoryCollectionBookList43=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("archive_date","DESC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", "IPAD", collectionName0);
				if(catlevel.contains ("1")){continue;}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList33);
				totalbooks=MultiCategoryCollectionBookList33.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

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

				Response ListBooksV1_OAuth_With_Pagi_res = ListBooksV1_OAuth.listBooksV1_OAuth_With_Pagi(0, 60, consumerKey, consumerSecret);
				Validation.responseHeaderCodeValidation(ListBooksV1_OAuth_With_Pagi_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(ListBooksV1_OAuth_With_Pagi_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(ListBooksV1_OAuth_With_Pagi_res);
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pagi_res, "id");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pagi_res, "isbn");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pagi_res, "title");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pagi_res, "thumbURL");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pagi_res, "author");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pagi_res, "createdOn");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pagi_res, "category");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pagi_res, "categoryList");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pagi_res, "version");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pagi_res, "bookId");
				System.out.println("ListBooksV1_OAuth_With_Pagi_res : "+ListBooksV1_OAuth_With_Pagi_res);

				Response ListBooksV1_OAuth_With_Pageno_res = ListBooksV1_OAuth.listBooksV1_OAuth_With_PageNO(0, 6, consumerKey, consumerSecret);
				Validation.responseHeaderCodeValidation(ListBooksV1_OAuth_With_Pageno_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(ListBooksV1_OAuth_With_Pageno_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(ListBooksV1_OAuth_With_Pageno_res);
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pageno_res, "id");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pageno_res, "isbn");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pageno_res, "title");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pageno_res, "thumbURL");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pageno_res, "author");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pageno_res, "createdOn");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pageno_res, "category");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pageno_res, "categoryList");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pageno_res, "version");
				Validation.responseKeyValidation_key(ListBooksV1_OAuth_With_Pageno_res, "bookId");
				System.out.println("ListBooksV1_OAuth_With_Pageno_res : "+ListBooksV1_OAuth_With_Pageno_res);

				Response listBooksV1_OAuth_without_pagires =ListBooksV1_OAuth.listBooksV1_OAuth_without_pagi(consumerKey, consumerSecret);
				Validation.responseHeaderCodeValidation(listBooksV1_OAuth_without_pagires, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(listBooksV1_OAuth_without_pagires, HttpStatus.SC_OK);
				Validation.responseTimeValidation(listBooksV1_OAuth_without_pagires);
				Validation.responseKeyValidation_key(listBooksV1_OAuth_without_pagires, "id");
				Validation.responseKeyValidation_key(listBooksV1_OAuth_without_pagires, "isbn");
				Validation.responseKeyValidation_key(listBooksV1_OAuth_without_pagires, "title");
				Validation.responseKeyValidation_key(listBooksV1_OAuth_without_pagires, "thumbURL");
				Validation.responseKeyValidation_key(listBooksV1_OAuth_without_pagires, "author");
				Validation.responseKeyValidation_key(listBooksV1_OAuth_without_pagires, "createdOn");
				Validation.responseKeyValidation_key(listBooksV1_OAuth_without_pagires, "category");
				Validation.responseKeyValidation_key(listBooksV1_OAuth_without_pagires, "categoryList");
				Validation.responseKeyValidation_key(listBooksV1_OAuth_without_pagires, "version");
				Validation.responseKeyValidation_key(listBooksV1_OAuth_without_pagires, "bookId");
				System.out.println("listBooksV1_OAuth_without_pagires : "+listBooksV1_OAuth_without_pagires);

				Response ListBooks_OAuth_withpagi_res= ListBooks_OAuth.listBooks_OAuth_With_Pagi(0, 60, consumerKey, consumerSecret);
				Validation.responseHeaderCodeValidation(ListBooks_OAuth_withpagi_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(ListBooks_OAuth_withpagi_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(ListBooks_OAuth_withpagi_res);
				Validation.responseKeyValidation_key(ListBooks_OAuth_withpagi_res, "id");
				Validation.responseKeyValidation_key(ListBooks_OAuth_withpagi_res, "isbn");
				Validation.responseKeyValidation_key(ListBooks_OAuth_withpagi_res, "title");
				Validation.responseKeyValidation_key(ListBooks_OAuth_withpagi_res, "thumbURL");
				Validation.responseKeyValidation_key(ListBooks_OAuth_withpagi_res, "author");
				Validation.responseKeyValidation_key(ListBooks_OAuth_withpagi_res, "createdOn");
				Validation.responseKeyValidation_key(ListBooks_OAuth_withpagi_res, "category");
				Validation.responseKeyValidation_key(ListBooks_OAuth_withpagi_res, "categoryList");
				Validation.responseKeyValidation_key(ListBooks_OAuth_withpagi_res, "version");
				Validation.responseKeyValidation_key(ListBooks_OAuth_withpagi_res, "bookId");
				System.out.println("ListBooks_OAuth_withpagi_res : "+ListBooks_OAuth_withpagi_res);

				Response ListBooks_OAuth_without_Pagi_res = ListBooks_OAuth.listBooks_OAuth_withoutpagi(consumerKey, consumerSecret);
				System.out.println("ListBooks_OAuth_res : "+ListBooks_OAuth_without_Pagi_res);
				Validation.responseHeaderCodeValidation(ListBooks_OAuth_without_Pagi_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(ListBooks_OAuth_without_Pagi_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(ListBooks_OAuth_without_Pagi_res);
				Validation.responseKeyValidation_key(ListBooks_OAuth_without_Pagi_res, "id");
				Validation.responseKeyValidation_key(ListBooks_OAuth_without_Pagi_res, "isbn");
				Validation.responseKeyValidation_key(ListBooks_OAuth_without_Pagi_res, "title");
				Validation.responseKeyValidation_key(ListBooks_OAuth_without_Pagi_res, "thumbURL");
				Validation.responseKeyValidation_key(ListBooks_OAuth_without_Pagi_res, "author");
				Validation.responseKeyValidation_key(ListBooks_OAuth_without_Pagi_res, "createdOn");
				Validation.responseKeyValidation_key(ListBooks_OAuth_without_Pagi_res, "category");
				Validation.responseKeyValidation_key(ListBooks_OAuth_without_Pagi_res, "categoryList");
				Validation.responseKeyValidation_key(ListBooks_OAuth_without_Pagi_res, "version");
				Validation.responseKeyValidation_key(ListBooks_OAuth_without_Pagi_res, "bookId");
				clientBookID = ListBooks_OAuth_without_Pagi_res.then().extract().path("bookList.book.clientBookID[1]");
				System.out.println("clientBookID1: " +clientBookID);

				Response ListBooks_with_pageno=ListBooks_OAuth.listBooks_OAuth_With_PageNO(0, 5, consumerKey, consumerSecret);
				System.out.println("ListBooks_OAuth_res : "+ListBooks_with_pageno);
				Validation.responseHeaderCodeValidation(ListBooks_with_pageno, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(ListBooks_with_pageno, HttpStatus.SC_OK);
				Validation.responseTimeValidation(ListBooks_with_pageno);
				Validation.responseKeyValidation_key(ListBooks_with_pageno, "id");
				Validation.responseKeyValidation_key(ListBooks_with_pageno, "isbn");
				Validation.responseKeyValidation_key(ListBooks_with_pageno, "title");
				Validation.responseKeyValidation_key(ListBooks_with_pageno, "thumbURL");
				Validation.responseKeyValidation_key(ListBooks_with_pageno, "author");
				Validation.responseKeyValidation_key(ListBooks_with_pageno, "createdOn");
				Validation.responseKeyValidation_key(ListBooks_with_pageno, "category");
				Validation.responseKeyValidation_key(ListBooks_with_pageno, "categoryList");
				Validation.responseKeyValidation_key(ListBooks_with_pageno, "version");
				Validation.responseKeyValidation_key(ListBooks_with_pageno, "bookId");
				System.out.println("ListBooks_with_pageno :: "+ListBooks_with_pageno);


				Response getBookMetadata_res = GetBookMetadata.getBookMetadata(consumerKey, consumerSecret,clientBookID);
				Validation.responseHeaderCodeValidation(getBookMetadata_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(getBookMetadata_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(getBookMetadata_res);
				Validation.responseKeyValidation_key(getBookMetadata_res, "flagForExt");
				Validation.responseKeyValidation_key(getBookMetadata_res, "license");
				Validation.responseKeyValidation_key(getBookMetadata_res, "useKitabooMail");
				Validation.responseKeyValidation_key(getBookMetadata_res, "pagination");
				Validation.responseKeyValidation_key(getBookMetadata_res, "clientBookId");
				Validation.responseKeyValidation_key(getBookMetadata_res, "kitabooId");
				Validation.responseKeyValidation_key(getBookMetadata_res, "isbn");
				Validation.responseKeyValidation_key(getBookMetadata_res, "title");
				Validation.responseKeyValidation_key(getBookMetadata_res, "thumbnail");
				Validation.responseKeyValidation_key(getBookMetadata_res, "mimeType");
				Validation.responseKeyValidation_key(getBookMetadata_res, "pages");
				Validation.responseKeyValidation_key(getBookMetadata_res, "version");
				Validation.responseKeyValidation_key(getBookMetadata_res, "formats");
				Validation.responseKeyValidation_key(getBookMetadata_res, "author");
				Validation.responseKeyValidation_key(getBookMetadata_res, "description");
				Validation.responseKeyValidation_key(getBookMetadata_res, "categoryIdList");
				System.out.println("getBookMetadata_res : " +getBookMetadata_res);

				Response bookMetadata_res = BookMetadata.bookMetadata(consumerKey, consumerSecret,bookID1);
				Validation.responseHeaderCodeValidation(bookMetadata_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(bookMetadata_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(bookMetadata_res);
				Validation.responseKeyValidation_key(bookMetadata_res, "flagForExt");
				Validation.responseKeyValidation_key(bookMetadata_res, "license");
				Validation.responseKeyValidation_key(bookMetadata_res, "useKitabooMail");
				Validation.responseKeyValidation_key(bookMetadata_res, "pagination");
				Validation.responseKeyValidation_key(bookMetadata_res, "clientBookId");
				Validation.responseKeyValidation_key(bookMetadata_res, "kitabooId");
				Validation.responseKeyValidation_key(bookMetadata_res, "isbn");
				Validation.responseKeyValidation_key(bookMetadata_res, "title");
				Validation.responseKeyValidation_key(bookMetadata_res, "thumbnail");
				Validation.responseKeyValidation_key(bookMetadata_res, "mimeType");
				Validation.responseKeyValidation_key(bookMetadata_res, "pages");
				Validation.responseKeyValidation_key(bookMetadata_res, "version");
				Validation.responseKeyValidation_key(bookMetadata_res, "formats");
				Validation.responseKeyValidation_key(bookMetadata_res, "author");
				Validation.responseKeyValidation_key(bookMetadata_res, "description");
				Validation.responseKeyValidation_key(bookMetadata_res, "categoryIdList");
				System.out.println("bookMetadata_res : " +bookMetadata_res);


				Response clientUserID_books_res = ClientUserID_books.clientUserID_books(consumerKey, consumerSecret, search,clientUserID);
				Validation.responseHeaderCodeValidation(clientUserID_books_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(clientUserID_books_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(clientUserID_books_res);
				Validation.responseKeyValidation_key(clientUserID_books_res, "id");
				Validation.responseKeyValidation_key(clientUserID_books_res, "isbn");
				Validation.responseKeyValidation_key(clientUserID_books_res, "title");
				Validation.responseKeyValidation_key(clientUserID_books_res, "thumbURL");
				Validation.responseKeyValidation_key(clientUserID_books_res, "author");
				Validation.responseKeyValidation_key(clientUserID_books_res, "createdOn");
				Validation.responseKeyValidation_key(clientUserID_books_res, "description");
				Validation.responseKeyValidation_key(clientUserID_books_res, "category");
				Validation.responseKeyValidation_key(clientUserID_books_res, "categoryList");
				Validation.responseKeyValidation_key(clientUserID_books_res, "bookActive");
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

				Response markAsFavourite_res1 = MarkAsFavourite.markAsFavourite(bookID2,userToken,"45564595","IPAD");
				BookID_mark2 = bookID2;
				Validation.responseHeaderCodeValidation(markAsFavourite_res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(markAsFavourite_res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(markAsFavourite_res1);
				System.out.println("markAsFavourite_res : "+markAsFavourite_res1);

				Response markAsFavourite_res3 = MarkAsFavourite.markAsFavourite(bookID3,userToken,"45564595","IPAD");
				BookID_mark3 = bookID3;
				Validation.responseHeaderCodeValidation(markAsFavourite_res3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(markAsFavourite_res3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(markAsFavourite_res3);
				System.out.println("markAsFavourite_res : "+markAsFavourite_res3);

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
				Validation.responseINTEGERKeyAndValue(FetchFavouriteBooks_res, "id", BookID_mark2);
				Validation.responseINTEGERKeyAndValue(FetchFavouriteBooks_res, "id", BookID_mark3);
				System.out.println("FetchFavouriteBooks_res : "+FetchFavouriteBooks_res);

				//******START FETCHFAVOURATESBOOKS PERMUTATION
				Response FetchFavouriteBooks_res1 = FetchFavouriteBooks.fetchFavouriteBooks_per("isbn", "ASC", userToken, "464", "IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res1);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res1);

				Response FetchFavouriteBooks_res11 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("isbn", "ASC",0,10, userToken, "464", "IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res11);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res11);

				Response FetchFavouriteBooks_res12 = FetchFavouriteBooks.fetchFavouriteBooks_per("isbn","DESC",userToken,"464","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res12);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res12);

				Response FetchFavouriteBooks_res13 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("isbn","DESC",0,10,userToken,"464","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res13);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res13);

				Response FetchFavouriteBooks_res2 = FetchFavouriteBooks.fetchFavouriteBooks_per("title", "ASC", userToken, "464", "IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res2);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res2);

				Response FetchFavouriteBooks_res21 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("title", "ASC",0,10, userToken, "464", "IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res21);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res21);

				Response FetchFavouriteBooks_res22 = FetchFavouriteBooks.fetchFavouriteBooks_per("title","DESC",userToken,"464","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res22);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res12);

				Response FetchFavouriteBooks_res23 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("title","DESC",0,10,userToken,"464","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res23);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res23);

				Response FetchFavouriteBooks_res3 = FetchFavouriteBooks.fetchFavouriteBooks_per("title", "ASC", userToken, "464", "IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res3);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res3);

				Response FetchFavouriteBooks_res31 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("title", "ASC",0,10, userToken, "464", "IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res31);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res31);

				Response FetchFavouriteBooks_res32 = FetchFavouriteBooks.fetchFavouriteBooks_per("title","DESC",userToken,"464","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res32);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res32);

				Response FetchFavouriteBooks_res33 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("title","DESC",0,10,userToken,"464","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res33);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res33);

				Response FetchFavouriteBooks_res4 = FetchFavouriteBooks.fetchFavouriteBooks_per("author", "ASC", userToken, "464", "IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res4);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res4);

				Response FetchFavouriteBooks_res41 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("author", "ASC",0,10, userToken, "464", "IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res41);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res41);

				Response FetchFavouriteBooks_res42 = FetchFavouriteBooks.fetchFavouriteBooks_per("author","DESC",userToken,"464","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res42);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res42);

				Response FetchFavouriteBooks_res43 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("author","DESC",0,10,userToken,"464","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res43);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res43);

				Response FetchFavouriteBooks_res5 = FetchFavouriteBooks.fetchFavouriteBooks_per("archive_date", "ASC", userToken, "464", "IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res5, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res5, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res5);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res5);

				Response FetchFavouriteBooks_res51 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("archive_date", "ASC",0,10, userToken, "464", "IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res51, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res51, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res51);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res51);

				Response FetchFavouriteBooks_res52 = FetchFavouriteBooks.fetchFavouriteBooks_per("archive_date","DESC",userToken,"464","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res52, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res52, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res52);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res52);

				Response FetchFavouriteBooks_res53 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("archive_date","DESC",0,10,userToken,"464","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res53, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res53, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res53);
				System.out.println("FetchFavouriteBooks_res1 :: "+FetchFavouriteBooks_res53);


				//********* END FETCHFAVOURATESBOOKS PERMUTATION

				Response unMarkAsFavourite_res = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark1,userToken,"45564595","IPAD");
				Validation.responseHeaderCodeValidation(unMarkAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(unMarkAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(unMarkAsFavourite_res);
				System.out.println("unMarkAsFavourite_res : "+unMarkAsFavourite_res);

				Response unMarkAsFavourite_res1 = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark2,userToken,"45564595","IPAD");
				Validation.responseHeaderCodeValidation(unMarkAsFavourite_res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(unMarkAsFavourite_res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(unMarkAsFavourite_res1);
				System.out.println("unMarkAsFavourite_res : "+unMarkAsFavourite_res1);

				Response unMarkAsFavourite_res3 = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark3,userToken,"45564595","IPAD");
				Validation.responseHeaderCodeValidation(unMarkAsFavourite_res3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(unMarkAsFavourite_res3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(unMarkAsFavourite_res3);
				System.out.println("unMarkAsFavourite_res : "+unMarkAsFavourite_res3);

				Response FetchFavouriteBooks_resA= FetchFavouriteBooks.fetchFavouriteBooks(userToken,"45564595","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_resA, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_resA, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_resA);
				Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_resA, ""+BookID_mark1+"");
				Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_resA, ""+BookID_mark2+"");
				Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_resA, ""+BookID_mark3+"");
				System.out.println("FetchFavouriteBooks_res1 : "+FetchFavouriteBooks_resA);


				Response saveSessionHistory_res = SaveSessionHistory.saveSessionHistory(userToken,"45564595","IPAD",bookID1,"2020-09-10 18:43:20");
				Validation.responseHeaderCodeValidation(saveSessionHistory_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(saveSessionHistory_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(saveSessionHistory_res);
				Validation.responseKeyValidation_key(saveSessionHistory_res, "ok");
				System.out.println("saveSessionHistory_res : "+saveSessionHistory_res);


				Response FetchRecentlyViewed_res = FetchRecentlyViewed.fetchRecentlyViewed_without_pagi(userToken,"54254fd","IPAD");
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


				//START Recently viewed BOOKS PERMUTATION
				Response fetchRecentlyViewed_permu1 = FetchRecentlyViewed.fetchRecentlyViewed_permu("isbn", "ASC", userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu1);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu1);

				Response fetchRecentlyViewed_permu11 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("isbn","ASC",0,10,userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu11);
				totalbooks=fetchRecentlyViewed_permu11.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu11);

				Response fetchRecentlyViewed_permu12 = FetchRecentlyViewed.fetchRecentlyViewed_permu("isbn", "DESC", userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu12);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu12);

				Response fetchRecentlyViewed_permu13 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("isbn","DESC",0,10,userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu13);
				totalbooks=fetchRecentlyViewed_permu13.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu13);

				Response fetchRecentlyViewed_permu2 = FetchRecentlyViewed.fetchRecentlyViewed_permu("title", "ASC", userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu2);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu2);

				Response fetchRecentlyViewed_permu21 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("title","ASC",0,10,userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu21);
				totalbooks=fetchRecentlyViewed_permu21.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu21);

				Response fetchRecentlyViewed_permu22 = FetchRecentlyViewed.fetchRecentlyViewed_permu("title", "DESC", userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu22);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu22);

				Response fetchRecentlyViewed_permu23 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("title","DESC",0,10,userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu23);
				totalbooks=fetchRecentlyViewed_permu23.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu23);

				Response fetchRecentlyViewed_permu3 = FetchRecentlyViewed.fetchRecentlyViewed_permu("archive_date", "ASC", userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu3);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu3);

				Response fetchRecentlyViewed_permu31 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("archive_date","ASC",0,10,userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu31);
				totalbooks=fetchRecentlyViewed_permu31.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu31);

				Response fetchRecentlyViewed_permu32 = FetchRecentlyViewed.fetchRecentlyViewed_permu("archive_date", "DESC", userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu32);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu32);

				Response fetchRecentlyViewed_permu33 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("archive_date","DESC",0,10,userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu33);
				totalbooks=fetchRecentlyViewed_permu33.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu33);

				Response fetchRecentlyViewed_permu4 = FetchRecentlyViewed.fetchRecentlyViewed_permu("author", "ASC", userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu4);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu4);

				Response fetchRecentlyViewed_permu41 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("author","ASC",0,10,userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu41);
				totalbooks=fetchRecentlyViewed_permu41.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu41);

				Response fetchRecentlyViewed_permu42 = FetchRecentlyViewed.fetchRecentlyViewed_permu("author", "DESC", userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu42);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu42);

				Response fetchRecentlyViewed_permu43 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("author","DESC",0,10,userToken,"da31131","IPAD");
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu43);
				totalbooks=fetchRecentlyViewed_permu43.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu43);

				//END Rectently viewed BOOKS PERMUTATION


				Response fetchRecentlyViewed_with_pagi_res = FetchRecentlyViewed.fetchRecentlyViewed_with_pagi(0,20,userToken,"hf454","IPAD");
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
