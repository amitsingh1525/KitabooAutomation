package com.hurix.api.runner;

import java.sql.SQLException;
import java.text.*;
import java.util.Date;
import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import io.restassured.response.Response;
import com.hurix.api.externalAPIs.*;
import com.hurix.api.hashAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

/*
 * 
 * Author Name:
 * SHWETA KATARE THE BRAND
 * 
 */

public class RestAssured {

	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	//System.out.println(df.format(dateobj));
	//public static long startDate1 = EpochTime.getEpochTime("df.format(dateobj");
	static long startDate ;//EpochTime.getEpochTime("2019/10/31 14:46:04");
	static long startIndex = 0;
	static long endIndex = 100;
	//public static int level;
	static String assetType;
	static int level;
	static String numberOfBooks;
	static String userToken = "";
	static int BookID_mark1;
	static int BookID_mark2;
	static int BookID_mark3;
	static int  bookID1;
	static int bookID2;
	static int bookID3;
	static int bookID6;
	static String isbn;
	static String isbnMeta;
	static String isbnIng;
	static String forName;
	static String responseMsg;
	static String content_ownership;
	static int userID;
	static int totalbooks;
	static int total;
	static int totalCategories;
	static int type;
	static Object archiveDate;
	static Object archiveDate6;
	static String operation0;
	static String operation1;
	static String ebookID1;
	static String catname;
	static String categoriesname;
	static String collectionName0;
	static String collectionName1;
	static String catname1;	
	static String clientUserID;
	static String category1;
	static String clientBookID;
	static String search = "Native";	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static String environMent;
	static String userName;
	static String password;
	public static String detail;
	public static String sqlhost;
	public static String sqlUsername;
	public static String sqlPassword;
	static String externalURI;
	static String clientID;
	static String catlevel;
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
	static String deviceT;
	static String consumerKey;
	static String consumerSecret;

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
				catlevel=formatter.formatCellValue(sheet.getRow(i).getCell(3));	
				deviceT=formatter.formatCellValue(sheet.getRow(i).getCell(4));	
				String runY_N = formatter.formatCellValue(sheet.getRow(i).getCell(5));

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
				Log.info("DIS-1466");
				io.restassured.RestAssured.baseURI = detail;
				//list of services which use http class


				io.restassured.RestAssured.baseURI = detail.replace("https", "http"); //http


				clientID = JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);

				
				/*String[] deviceT = {"IPAD","ANDROID","WINDOWS","PC","HTML5"};
				for(int i4=0; i4<=4 ;i4++)*/
				Log.startTestCase("Authenticate."+deviceT+"");
				Log.info("clientID : "+clientID);
				Log.info("userName : "+userName);
				Log.info("password : "+password);
				Log.info("clientID : "+clientID);

				Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"65454",deviceT);
				Log.info("Authenticate Response: "+authenticateValue.then().extract().response().prettyPrint());
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
				Log.info("here");
				userName = authenticateValue.then().extract().path("user.userName");
				userID = authenticateValue.then().extract().path("user.id");
				Log.info("userID: "+userID);
				collectionName1="Col1";
				catname1="History";
				userToken = authenticateValue.then().extract().path("userToken");
				Log.info("userToken:"+userToken);
				clientUserID = authenticateValue.then().extract().path("user.clientUserID");
				Log.info("clientUserID:"+clientUserID);
				Log.endTestCase("End");
				int client_Id = authenticateValue.then().extract().path("user.clientID");
				Log.info("client_Id:"+client_Id);



				consumerKey = JDBC_Queries.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
				consumerSecret =JDBC_Queries.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);


				Response fetchBookList_with_pagination = FetchBookList.fetchBookList_with_pagination(0,15,userToken,"45616452",deviceT);
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



				//******START FETCHBOOKLIST PERMUTATION
				Response FetchbookListPermutation=FetchBookList.fetchBookList_with_permutation("isbn", "ASC", userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation);


				Response FetchbookListPermutation_withpagi=FetchBookList.fetchBookList_withPAGI_permutation("isbn","ASC",0,8,userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagi, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagi, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagi);


				Response FetchbookListPermutationDEC=FetchBookList.fetchBookList_with_permutation("isbn","DESC",userToken,"464",deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutationDEC, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutationDEC, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutationDEC);


				Response FetchbookListPermutation_withpagiDEC=FetchBookList.fetchBookList_withPAGI_permutation("isbn","DESC",0,8,userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagiDEC, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagiDEC, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagiDEC);


				Response FetchbookListPermutation2=FetchBookList.fetchBookList_with_permutation("title", "ASC", userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation2, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation2);


				Response FetchbookListPermutation_withpagi21=FetchBookList.fetchBookList_withPAGI_permutation("title","ASC",0,8,userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagi21, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagi21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagi21);

				Response FetchbookListPermutationDEC22=FetchBookList.fetchBookList_with_permutation("title","DESC",userToken,"464",deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutationDEC22, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutationDEC22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutationDEC22);


				Response FetchbookListPermutation_withpagiDEC23=FetchBookList.fetchBookList_withPAGI_permutation("title","DESC",0,8,userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagiDEC23, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagiDEC23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagiDEC23);

				Response FetchbookListPermutation3=FetchBookList.fetchBookList_with_permutation("title", "ASC", userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation3, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation3);


				Response FetchbookListPermutation_withpagi31=FetchBookList.fetchBookList_withPAGI_permutation("title","ASC",0,8,userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagi31, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagi31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagi31);

				Response FetchbookListPermutationDEC32=FetchBookList.fetchBookList_with_permutation("title","DESC",userToken,"464",deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutationDEC32, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutationDEC32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutationDEC32);


				Response FetchbookListPermutation_withpagiDEC33=FetchBookList.fetchBookList_withPAGI_permutation("title","DESC",0,8,userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagiDEC33, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagiDEC33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagiDEC33);

				Response FetchbookListPermutation4=FetchBookList.fetchBookList_with_permutation("author", "ASC", userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation4, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation4);


				Response FetchbookListPermutation_withpagi41=FetchBookList.fetchBookList_withPAGI_permutation("author","ASC",0,8,userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagi41, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagi41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagi41);

				Response FetchbookListPermutationDEC42=FetchBookList.fetchBookList_with_permutation("author","DESC",userToken,"464",deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutationDEC42, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutationDEC42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutationDEC42);


				Response FetchbookListPermutation_withpagiDEC43=FetchBookList.fetchBookList_withPAGI_permutation("author","DESC",0,8,userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagiDEC43, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagiDEC43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagiDEC43);

				Response FetchbookListPermutation5=FetchBookList.fetchBookList_with_permutation("archive_Date", "ASC", userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation5, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation5, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation5);



				Response FetchbookListPermutation_withpagi51=FetchBookList.fetchBookList_withPAGI_permutation("archive_Date","ASC",0,8,userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagi51, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagi51, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagi51);


				FetchbookListPermutation_withpagi51=FetchBookList.fetchBookList_with_permutation("archive_Date","DESC",userToken,"464",deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagi51, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagi51, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagi51);



				Response FetchbookListPermutation_withpagiDEC53=FetchBookList.fetchBookList_withPAGI_permutation("archive_Date","DESC",0,8,userToken, "464", deviceT);
				Validation.responseCodeValidation1(FetchbookListPermutation_withpagiDEC53, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(FetchbookListPermutation_withpagiDEC53, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchbookListPermutation_withpagiDEC53);


				////$$$$$$$$ END FETCHBOOKLIST PERMUTATION

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
				Log.info("bookID_1: "+bookID1);
				bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[1]");			
				Log.info("bookID2: "+bookID2);
				bookID3 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[2]");			
				Log.info("bookID3: "+bookID3);
				bookID6 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[6]");
				Log.info("bookID6 :: "+bookID6);
				isbn = fetchBookList_without_pagination.then().extract().path("bookList.book.isbn[1]");
				Log.info("isbn: "+isbn);
				type=fetchBookList_without_pagination.then().extract().path("bookList.book.type[0]");
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


				Response GETfetchBookCount_res = FetchBookCount.fetchBookCount(userToken,"45616452",deviceT);
				Validation.responseHeaderCodeValidation(GETfetchBookCount_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(GETfetchBookCount_res,HttpStatus.SC_OK);
				Validation.responseTimeValidation(GETfetchBookCount_res);
				Validation.responseKeyValidation_key(GETfetchBookCount_res, "totalbooks");
				Validation.responseKeyValidation_key(GETfetchBookCount_res, "timestamp");



				//HASH APIS
				Response FetchBookListHash_res = FetchBookListHash.fetchBookListHash(userToken,"45616452",deviceT);
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


				Response FetchCatBookList_res =FetchCatBookList.fetchCatBookList(catname,userToken,"45616452",deviceT);
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


				Response CategoriesV1Hash_res = CategoriesV1Hash.categoriesV1Hash(userToken,"45616452",deviceT);
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


				Response CategoriesV2Hash_res = CategoriesV2Hash.categoriesV2Hash(userToken,"45616452",deviceT);
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


				Response RefreshBookList_Hash = RefreshBookListHash.refreshBookListHash(userToken,"45616452",deviceT);
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



				SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
				Date date = new Date();  
				String  time=formatter1.format(date);
				Response Savesessionhistory = SaveSessionHistory.saveSessionHistory(userToken, "dssat3323", deviceT, bookID1,time);
				Log.info("TIME : "+time);
				Validation.responseHeaderCodeValidation(Savesessionhistory, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Savesessionhistory, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Savesessionhistory);


				Response FetchRecentlyViewedBook_Hash = FetchRecentlyViewedBooksSecuredHash.fetchRecentlyViewedBooksSecuredHash(userToken,"45616452",deviceT,bookID1);
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



				Response GetBookDetailsSecured_Hash = GetBookDetailsSecuredHash.getBookDetailsSecuredHash(""+archiveDate+"", userToken, "5489989",deviceT,bookID1,""+assetType+"");
				Validation.responseHeaderCodeValidation(GetBookDetailsSecured_Hash, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(GetBookDetailsSecured_Hash, HttpStatus.SC_OK);
				Validation.responseTimeValidation(GetBookDetailsSecured_Hash);
				Validation.responseHeaderCodeValidation(GetBookDetailsSecured_Hash, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(GetBookDetailsSecured_Hash, HttpStatus.SC_OK);
				Validation.responseTimeValidation(GetBookDetailsSecured_Hash);
				Validation.responseKeyValidation_key(GetBookDetailsSecured_Hash, "bookId");
				Validation.responseKeyValidation_key(GetBookDetailsSecured_Hash, "category");
				Validation.responseKeyValidation_key(GetBookDetailsSecured_Hash, "archiveDate");



				Response MarkAsFavourite_Hash = MarkAsFavouriteHash.markAsFavouriteHash(bookID1,userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(MarkAsFavourite_Hash, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MarkAsFavourite_Hash, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MarkAsFavourite_Hash);
				int markesAsFav=bookID1;


				Response FetchFavouriteSecured_Hash = FetchFavouriteSecuredHash.fetchFavouriteSecuredHash(userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteSecured_Hash, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteSecured_Hash, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteSecured_Hash);
				Validation.responseKeyValidation_key(FetchFavouriteSecured_Hash, ""+bookID1+"");


				Response UnMarkAsFavourite_Hash = UnMarkAsFavouriteHash.unMarkAsFavourite(markesAsFav,userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(UnMarkAsFavourite_Hash, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(UnMarkAsFavourite_Hash, HttpStatus.SC_OK);
				Validation.responseTimeValidation(UnMarkAsFavourite_Hash);
				Validation.responseNOTKeyValidation_key(UnMarkAsFavourite_Hash, ""+markesAsFav+"");


				Response FetchFavouriteSecured_Hash1 = FetchFavouriteSecuredHash.fetchFavouriteSecuredHash(userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteSecured_Hash1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteSecured_Hash1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteSecured_Hash1);
				Validation.responseNOTKeyValidation_key(FetchFavouriteSecured_Hash1, ""+bookID1+"");



				Response FetchCategoriesCollectionsBooksHash = FetchCategoriesCollectionsBooks_Hash.fetchCategoriesCollectionsBooks_Hash(userToken,catname1,collectionName1,"5454gdf",deviceT);
				Validation.responseHeaderCodeValidation(FetchCategoriesCollectionsBooksHash, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchCategoriesCollectionsBooksHash,HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchCategoriesCollectionsBooksHash);
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "archiveDate");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "assetType");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "author");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "bookActive");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "bookCode");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "bookId");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "category");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "categoryIdList");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "categoryList");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "collectionID");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "collectionThumbnail");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "collectionTitle");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "collectionType");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "description");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooksHash, "ebookID");				


				Response FetchCategoriesCollectionsBooks_Hash = FetchCatCollectionBooks.fetchCatCollectionBooks(userToken,"5454gdf",deviceT,catname1,collectionName1);
				Validation.responseHeaderCodeValidation(FetchCategoriesCollectionsBooks_Hash, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchCategoriesCollectionsBooks_Hash, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchCategoriesCollectionsBooks_Hash);
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "id");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "isbn");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "title");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "thumbURL");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "pages");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "author");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "formats");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "category");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "categoryList");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "categoryIdList");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "bookCode");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "version");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "ebookID");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "collectionID");
				Validation.responseKeyValidation_key(FetchCategoriesCollectionsBooks_Hash, "collectionTitle");



				//"2019/10/31 14:46:04"
				Response V1RefreshBooks_hash = V1RefreshBooksHash.v1refreshBooks(""+archiveDate+"",bookID1,bookID2,userToken,"56454",deviceT);
				Validation.responseHeaderCodeValidation(V1RefreshBooks_hash, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(V1RefreshBooks_hash, HttpStatus.SC_OK);
				Validation.responseTimeValidation(V1RefreshBooks_hash);
				Validation.responseKeyValidation_key(V1RefreshBooks_hash, "totalBooks");	
				Validation.responseKeyValidation_key(V1RefreshBooks_hash, "operation");
				Validation.responseKeyValidation_key(V1RefreshBooks_hash, "collectionID");
				Validation.responseKeyValidation_key(V1RefreshBooks_hash, "collectionTitle");
				Validation.responseKeyValidation_key(V1RefreshBooks_hash, "collectionThumbnail");


				Response V1RefreshBooks_hash2 = V1RefreshBooksHash.v1refreshBooks_op(""+archiveDate+"",bookID6,bookID2,"UPDATE","NEW",userToken,"56454",deviceT);
				Validation.responseHeaderCodeValidation(V1RefreshBooks_hash2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(V1RefreshBooks_hash2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(V1RefreshBooks_hash2);
				Validation.responseKeyValidation_key(V1RefreshBooks_hash2, "totalBooks");	
				Validation.responseKeyValidation_key(V1RefreshBooks_hash2, "operation");
				Validation.responseKeyValidation_key(V1RefreshBooks_hash2, "collectionID");
				Validation.responseKeyValidation_key(V1RefreshBooks_hash2, "collectionTitle");
				Validation.responseKeyValidation_key(V1RefreshBooks_hash2, "collectionThumbnail");			


				//HASH APIS ENDS 

				Response fetchPreferredLocale_res = FetchPreferredLocale.fetchPreferredLocale(userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(fetchPreferredLocale_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchPreferredLocale_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchPreferredLocale_res);
				Validation.responseKeyValidation_key(fetchPreferredLocale_res,"locale");
				Validation.responseKeyValidation_key(fetchPreferredLocale_res,"responseMsg");
				Validation.responseKeyValidation_key(fetchPreferredLocale_res,"Ok");


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


				Response downloadBookForIPAD_online = DownloadBook.downloadBook(userToken,"ds9465",deviceT,bookID1,"online");
				Validation.responseHeaderCodeValidation(downloadBookForIPAD_online, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForIPAD_online, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForIPAD_online);
				Validation.responseKeyValidation_key(downloadBookForIPAD_online,"content_ownership");
				content_ownership=downloadBookForIPAD_online.then().extract().path("content_ownership");
				Validation.responseKeyAndValue(downloadBookForIPAD_online, "content_ownership", "FALSE");
				Validation.responseKeyValidation_key(downloadBookForIPAD_online,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForIPAD_online,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForIPAD_online,"timestamp");


				Response downloadBookForIPAD_offline = DownloadBook.downloadBook(userToken,"ds9465",deviceT,bookID1,"offline");
				Validation.responseHeaderCodeValidation(downloadBookForIPAD_offline, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForIPAD_offline, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForIPAD_offline);
				Validation.responseKeyValidation_key(downloadBookForIPAD_offline,"content_ownership");
				content_ownership=downloadBookForIPAD_offline.then().extract().path("content_ownership");
				Validation.responseKeyAndValue(downloadBookForIPAD_offline, "content_ownership", "FALSE");
				Validation.responseKeyValidation_key(downloadBookForIPAD_offline,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForIPAD_offline,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForIPAD_offline,"timestamp");


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


				Response downloadBookForPC_ONLINE = DownloadBook.downloadBook(userToken,"ds9465","PC",bookID1,"online");
				Validation.responseHeaderCodeValidation(downloadBookForPC_ONLINE, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForPC_ONLINE, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForPC_ONLINE);
				Validation.responseKeyValidation_key(downloadBookForPC_ONLINE,"content_ownership");
				content_ownership=downloadBookForPC_ONLINE.then().extract().path("content_ownership");
				Validation.responseKeyAndValue(downloadBookForPC_ONLINE, "content_ownership", "FALSE");
				Validation.responseKeyValidation_key(downloadBookForPC_ONLINE,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForPC_ONLINE,"timestamp");



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
				Validation.responseKeyValidation_key(searchV2res, "total");
				Validation.responseISGreater("total", total, 1);


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


				Response searchV2_AdvanceFilterres =SearchV2_AdvanceFilter.searchV2_AdvanceFilter("Native",userToken, "5454545",deviceT,clientUserID);
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


				Response Booklist = BookList.bookList(userToken,"56454", deviceT);
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


				//START BOOKLIST PERMUTATION
				Response Booklist1 = BookList.bookList_per("isbn","ASC",userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist1);



				Response Booklist11 = BookList.bookList_per_withPagi("isbn","ASC",0,10,userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist11);


				Response Booklist12 = BookList.bookList_per("isbn","DESC",userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist12);


				Response Booklist13 = BookList.bookList_per_withPagi("isbn","DESC",0,10,userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist13);



				Response Booklist2 = BookList.bookList_per("author","ASC",userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist2);


				Response Booklist21 = BookList.bookList_per_withPagi("author","ASC",0,10,userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist21);


				Response Booklist22 = BookList.bookList_per("author","DESC",userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist22);


				Response Booklist23 = BookList.bookList_per_withPagi("author","DESC",0,10,userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist23);


				Response Booklist3 = BookList.bookList_per("title","ASC",userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist3);

				Response Booklist31 = BookList.bookList_per_withPagi("title","ASC",0,10,userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist31);


				Response Booklist32 = BookList.bookList_per("title","DESC",userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist32);


				Response Booklist33 = BookList.bookList_per_withPagi("title","DESC",0,10,userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist33);

				Response Booklist4 = BookList.bookList_per("archive_date","ASC",userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist4);


				Response Booklist41 = BookList.bookList_per_withPagi("archive_date","ASC",0,10,userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist41);


				Response Booklist42 = BookList.bookList_per("archive_date","DESC",userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist42);


				Response Booklist43 = BookList.bookList_per_withPagi("archive_date","DESC",0,10,userToken,"56454", deviceT);
				Validation.responseHeaderCodeValidation(Booklist43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Booklist43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Booklist43);
				//END BOOKLIST PERMUTATION


				Response getSecureURLres =GetSecureURL.getSecureURL(userToken, "5489989",deviceT,3);
				Validation.responseHeaderCodeValidation(getSecureURLres, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(getSecureURLres, HttpStatus.SC_OK);
				Validation.responseTimeValidation(getSecureURLres);
				Validation.responseKeyValidation_key(getSecureURLres,"responseMsg");
				responseMsg = getSecureURLres.then().extract().path("responseMsg");
				Validation.responseNOTKeyValidation_key(getSecureURLres, "URL_NOT_FORMED");


				System.out.println("startDate :: "+startDate);
				Response bookdetails_res = Bookdetails.bookdetails(""+archiveDate+"", userToken, "5489989",deviceT,bookID1,""+assetType+"");
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


				Response fetchCategoriesCollectionsres =FetchCategoriesCollections.fetchCategoriesCollections(userToken, "5489989",deviceT);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsres, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsres, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsres);
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"categories");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"name");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"numberOfBooks");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"collections");
				Validation.responseKeyValidation_key(fetchCategoriesCollectionsres,"name");


				Response fetchCategoriesCollectionsBooksres =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks(userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
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

				//START fetchCategoriesCollectionsBooks PERMUTATION

				Response fetchCategoriesCollectionsBooks1 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("isbn","ASC",userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks1,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks1);


				Response fetchCategoriesCollectionsBooks11 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("isbn","ASC",0,10,userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks11,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks11);
				numberOfBooks=fetchCategoriesCollectionsBooks11.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				int numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);


				Response fetchCategoriesCollectionsBooks12 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("isbn","DESC",userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks12,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks12);


				Response fetchCategoriesCollectionsBooks13 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("isbn","DESC",0,10,userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks13,HttpStatus.SC_OK);
				numberOfBooks=fetchCategoriesCollectionsBooks11.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks13);				


				Response fetchCategoriesCollectionsBooks2 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("title","ASC",userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks2,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks2);


				Response fetchCategoriesCollectionsBooks21 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("title","ASC",0,10,userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks21,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks21);
				numberOfBooks=fetchCategoriesCollectionsBooks21.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);

				Response fetchCategoriesCollectionsBooks22 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("title","DESC",userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks22,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks22);

				Response fetchCategoriesCollectionsBooks23 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("title","DESC",0,10,userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks23,HttpStatus.SC_OK);
				numberOfBooks=fetchCategoriesCollectionsBooks23.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks23);				

				Response fetchCategoriesCollectionsBooks3 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("author","ASC",userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks3,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks3);

				Response fetchCategoriesCollectionsBooks31 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("author","ASC",0,10,userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks31,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks31);
				numberOfBooks=fetchCategoriesCollectionsBooks31.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);


				Response fetchCategoriesCollectionsBooks32 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("author","DESC",userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks32,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks32);


				Response fetchCategoriesCollectionsBooks33 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("author","DESC",0,10,userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks33,HttpStatus.SC_OK);
				numberOfBooks=fetchCategoriesCollectionsBooks33.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks33);				

				Response fetchCategoriesCollectionsBooks4 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("author","ASC",userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks4,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks4);


				Response fetchCategoriesCollectionsBooks41 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("author","ASC",0,10,userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks41,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks41);
				numberOfBooks=fetchCategoriesCollectionsBooks41.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);


				Response fetchCategoriesCollectionsBooks42 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("author","DESC",userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks42,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks42);


				Response fetchCategoriesCollectionsBooks43 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("author","DESC",0,10,userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks43,HttpStatus.SC_OK);
				numberOfBooks=fetchCategoriesCollectionsBooks43.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks43);				


				Response fetchCategoriesCollectionsBooks5 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("archive_date","ASC",userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks5, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks5,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks5);


				Response fetchCategoriesCollectionsBooks51 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("archive_date","ASC",0,10,userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks51, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks51,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks51);
				numberOfBooks=fetchCategoriesCollectionsBooks41.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);


				Response fetchCategoriesCollectionsBooks52 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per("archive_date","DESC",userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks52, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks52,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks52);

				Response fetchCategoriesCollectionsBooks53 =FetchCategoriesCollectionsBooks.fetchCategoriesCollectionsBooks_per_withpagi("archive_date","DESC",0,10,userToken, "5489989",deviceT,catname1,collectionName1,bookID1,sqlhost,sqlUsername,sqlPassword,catlevel);
				Validation.responseHeaderCodeValidation(fetchCategoriesCollectionsBooks53, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchCategoriesCollectionsBooks53,HttpStatus.SC_OK);
				numberOfBooks=fetchCategoriesCollectionsBooks53.then().extract().path("category.numberOfBooks");
				Log.info("numberOfBooks : "+numberOfBooks);
				System.out.println("numberOfBooks : "+numberOfBooks);
				numberOfBooks1= Integer.parseInt(""+numberOfBooks+"");
				Validation.responseISGreater("numberOfBooks", numberOfBooks1, 3);
				Validation.responseTimeValidation(fetchCategoriesCollectionsBooks53);				


				//END fetchCategoriesCollectionsBooks PERMUTATION

				Response categoriesV1res = CategoriesV1.categoriesV1(userToken, "5489989",deviceT);
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


				Response categoriesV2res =CategoriesV2.categoriesV2(userToken, "5489989",deviceT);
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



				//String sqlhost="jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com";
				//String sqlUsername="qcteam";
				//String sqlPassword="JB88F-WT2Q3-DPXTT";		

				Response categoryBookListV1res = CategoryBookListV1.categoryBookListV1(""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
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



				//START categoryBookLisyV1 PERMUTATION
				Response categoryBookListV1res1 = CategoryBookListV1.categoryBookListV1_per("isbn","ASC",""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res1);


				Response categoryBookListV1res11 = CategoryBookListV1.categoryBookListV1_per_withPagi("isbn","ASC",0,10,""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res11);
				totalbooks=categoryBookListV1res11.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);


				Response categoryBookListV1res12 = CategoryBookListV1.categoryBookListV1_per("isbn","DESC",""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res12);
				System.out.println("categoryBookListV1res1 : "+categoryBookListV1res12);

				Response categoryBookListV1res13 = CategoryBookListV1.categoryBookListV1_per_withPagi("isbn","DESC",0,10,""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res13);
				totalbooks=categoryBookListV1res13.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);


				Response categoryBookListV1res2 = CategoryBookListV1.categoryBookListV1_per("title","ASC",""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res2);


				Response categoryBookListV1res21 = CategoryBookListV1.categoryBookListV1_per_withPagi("title","ASC",0,10,""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res21);
				totalbooks=categoryBookListV1res21.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);


				Response categoryBookListV1res22 = CategoryBookListV1.categoryBookListV1_per("title","DESC",""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res22);


				Response categoryBookListV1res23 = CategoryBookListV1.categoryBookListV1_per_withPagi("title","DESC",0,10,""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res23);
				totalbooks=categoryBookListV1res23.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);


				Response categoryBookListV1res3 = CategoryBookListV1.categoryBookListV1_per("author","ASC",""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res3);


				Response categoryBookListV1res31 = CategoryBookListV1.categoryBookListV1_per_withPagi("author","ASC",0,10,""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res31);
				totalbooks=categoryBookListV1res31.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);


				Response categoryBookListV1res32 = CategoryBookListV1.categoryBookListV1_per("author","DESC",""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res32);


				Response categoryBookListV1res33 = CategoryBookListV1.categoryBookListV1_per_withPagi("author","DESC",0,10,""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res33);
				totalbooks=categoryBookListV1res33.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);


				Response categoryBookListV1res4 = CategoryBookListV1.categoryBookListV1_per("archive_date","ASC",""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res4);


				Response categoryBookListV1res41 = CategoryBookListV1.categoryBookListV1_per_withPagi("archive_date","ASC",0,10,""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res41);
				totalbooks=categoryBookListV1res41.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);



				Response categoryBookListV1res42 = CategoryBookListV1.categoryBookListV1_per("archive_date","DESC",""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res42);



				Response categoryBookListV1res43 = CategoryBookListV1.categoryBookListV1_per_withPagi("archive_date","DESC",0,10,""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(categoryBookListV1res43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(categoryBookListV1res43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(categoryBookListV1res43);
				totalbooks=categoryBookListV1res43.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 2);



				//END categoryBooklistv1 Permutation

				Response CategoryBookListV2Res = CategoryBookListV2.categoryBookListV2(""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
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


				Response CategoryBookListV2Res_withpagi = CategoryBookListV2.categoryBookListV2_withpagi(0,10,""+category1+"",userToken,"56454", deviceT,bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
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


				Response FetchCategorybooksV1Res = FetchCategorybooksV1.fetchCategorybooksV1(catname,userToken,bookID1, catlevel, sqlhost, sqlUsername, sqlPassword);
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



				if(catlevel.contains ("1")){}			
				else{Response multiCategories_res = MultiCategories.multiCategories(catlevel,userToken,"fs445",deviceT);
				Validation.responseHeaderCodeValidation(multiCategories_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(multiCategories_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(multiCategories_res);
				Validation.responseKeyValidation_key(multiCategories_res, "categories");
				Validation.responseKeyValidation_key(multiCategories_res, "collectionCount");
				Validation.responseKeyValidation_key(multiCategories_res, "bookCount");
				Validation.responseKeyValidation_key(multiCategories_res, "hash");
				Validation.responseKeyValidation_key(multiCategories_res, "id");}

				//int bookID=bookID1;


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList_res = MultiCategoryBookList.multiCategoryBookList(catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
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



				//START MultcategoryBookList PERMUTATION
				Response MultiCategoryBookList1 = MultiCategoryBookList.multiCategoryBookList_Per("isbn","ASC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				if(catlevel.contains ("1")){}			
				else{Validation.responseHeaderCodeValidation(MultiCategoryBookList1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList1);
				}


				if(catlevel .contains ("1")){}			
				else{Response MultiCategoryBookList11 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("isbn","ASC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList11);
				}


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList12 = MultiCategoryBookList.multiCategoryBookList_Per("isbn","DESC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList12);
				}


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList13 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("isbn","DESC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList13);
				}


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList2 = MultiCategoryBookList.multiCategoryBookList_Per("title","ASC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList2);}


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList21 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("title","ASC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList21);}


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList22 = MultiCategoryBookList.multiCategoryBookList_Per("title","DESC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList22);}


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList23 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("title","DESC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList23);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList3 = MultiCategoryBookList.multiCategoryBookList_Per("author","ASC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList3);}


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList31 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("author","ASC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList31);}


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList32 = MultiCategoryBookList.multiCategoryBookList_Per("author","DESC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList32);}

				if(catlevel .contains ("1")){}			
				else{Response MultiCategoryBookList33 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("author","DESC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList33);}


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList4 = MultiCategoryBookList.multiCategoryBookList_Per("archive_date","ASC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList4);}


				if(catlevel .contains ("1")){}			
				else{Response MultiCategoryBookList41 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("archive_date","ASC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList41);}


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryBookList42 = MultiCategoryBookList.multiCategoryBookList_Per("archive_date","DESC",catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList42);}


				if(catlevel.contains("1")){}			
				else{Response MultiCategoryBookList43 = MultiCategoryBookList.multiCategoryBookList_Per_withPagi("archive_date","DESC",0,10,catlevel,bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(MultiCategoryBookList43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryBookList43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryBookList43);}


				//END MultcategoryBookList PERMUTATION


				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollection_BookList=MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
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

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList1=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("isbn","ASC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList1);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList11=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("isbn","ASC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList11);
				totalbooks=MultiCategoryCollectionBookList11.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList12=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("isbn","DESC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList12);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList13=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("isbn","DESC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList13);
				totalbooks=MultiCategoryCollectionBookList13.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList2=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("title","ASC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList2);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList21=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("title","ASC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList21);
				totalbooks=MultiCategoryCollectionBookList21.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList22=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("title","DESC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList22);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList23=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("title","DESC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList23);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList23);
				totalbooks=MultiCategoryCollectionBookList23.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList3=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("author","ASC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList3);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList31=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("author","ASC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList31);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList31);
				totalbooks=MultiCategoryCollectionBookList31.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList32=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("author","DESC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList32);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList33=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("author","DESC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList33);Validation.responseTimeValidation(MultiCategoryCollectionBookList33);
				totalbooks=MultiCategoryCollectionBookList33.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList4=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("archive_date","ASC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList4);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList41=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("archive_date","ASC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList41);Validation.responseTimeValidation(MultiCategoryCollectionBookList41);
				totalbooks=MultiCategoryCollectionBookList41.then().extract().path("totalbooks");
				Validation.responseISGreater("totalbooks", totalbooks, 3);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList42=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per("archive_date","DESC",catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList42);}

				if(catlevel.contains ("1")){}			
				else{Response MultiCategoryCollectionBookList43=MultiCategoryCollectionBookList.multiCategoryCollectionBookList_Per_withpagi("archive_date","DESC",0,5,catlevel, bookID1, sqlhost, sqlUsername, sqlPassword, userToken, "4524242", deviceT, collectionName0);
				Validation.responseHeaderCodeValidation(MultiCategoryCollectionBookList43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategoryCollectionBookList43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategoryCollectionBookList43);
				totalbooks=MultiCategoryCollectionBookList43.then().extract().path("totalbooks");
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
				clientBookID = ListBooks_OAuth_without_Pagi_res.then().extract().path("bookList.book.clientBookID[1]");
				System.out.println("clientBookID1: " +clientBookID);

				Response ListBooks_with_pageno=ListBooks_OAuth.listBooks_OAuth_With_PageNO(0, 5, consumerKey, consumerSecret);				
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
				//Validation.responseKeyValidation_key(bookMetadata_res, "mimeType");
				Validation.responseKeyValidation_key(bookMetadata_res, "pages");
				Validation.responseKeyValidation_key(bookMetadata_res, "version");
				Validation.responseKeyValidation_key(bookMetadata_res, "formats");
				Validation.responseKeyValidation_key(bookMetadata_res, "author");
				Validation.responseKeyValidation_key(bookMetadata_res, "description");
				Validation.responseKeyValidation_key(bookMetadata_res, "categoryIdList");



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
				totalbooks=userAssignedBooks_withPagi_Res.then().extract().path("totalbooks");
				Log.info("totalbooks: "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);


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



				//2019/10/31 14:46:04
				Response v1refreshBookList_res =V1refreshBookList.v1refreshBookList(""+archiveDate+"","NEW","UPDATE",bookID1,bookID2,userToken,"56454", deviceT,clientID);
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



				Response markAsFavourite_res = MarkAsFavourite.markAsFavourite(bookID1,userToken,"45564595",deviceT);
				BookID_mark1 = bookID1;
				Validation.responseHeaderCodeValidation(markAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(markAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(markAsFavourite_res);


				Response markAsFavourite_res1 = MarkAsFavourite.markAsFavourite(bookID2,userToken,"45564595",deviceT);
				BookID_mark2 = bookID2;
				Validation.responseHeaderCodeValidation(markAsFavourite_res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(markAsFavourite_res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(markAsFavourite_res1);



				Response markAsFavourite_res3 = MarkAsFavourite.markAsFavourite(bookID3,userToken,"45564595",deviceT);
				BookID_mark3 = bookID3;
				Validation.responseHeaderCodeValidation(markAsFavourite_res3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(markAsFavourite_res3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(markAsFavourite_res3);



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
				Validation.responseINTEGERKeyAndValue(FetchFavouriteBooks_res, "id", BookID_mark3);


				//******START FETCHFAVOURATESBOOKS PERMUTATION
				Response FetchFavouriteBooks_res1 = FetchFavouriteBooks.fetchFavouriteBooks_per("isbn", "ASC", userToken, "464", deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res1);



				Response FetchFavouriteBooks_res11 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("isbn", "ASC",0,10, userToken, "464", deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res11);


				Response FetchFavouriteBooks_res12 = FetchFavouriteBooks.fetchFavouriteBooks_per("isbn","DESC",userToken,"464",deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res12);


				Response FetchFavouriteBooks_res13 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("isbn","DESC",0,10,userToken,"464",deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res13);


				Response FetchFavouriteBooks_res2 = FetchFavouriteBooks.fetchFavouriteBooks_per("title", "ASC", userToken, "464", deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res2);


				Response FetchFavouriteBooks_res21 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("title", "ASC",0,10, userToken, "464", deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res21);


				Response FetchFavouriteBooks_res22 = FetchFavouriteBooks.fetchFavouriteBooks_per("title","DESC",userToken,"464",deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res22);


				Response FetchFavouriteBooks_res23 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("title","DESC",0,10,userToken,"464",deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res23);


				Response FetchFavouriteBooks_res3 = FetchFavouriteBooks.fetchFavouriteBooks_per("title", "ASC", userToken, "464", deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res3);


				Response FetchFavouriteBooks_res31 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("title", "ASC",0,10, userToken, "464", deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res31);


				Response FetchFavouriteBooks_res32 = FetchFavouriteBooks.fetchFavouriteBooks_per("title","DESC",userToken,"464",deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res32);


				Response FetchFavouriteBooks_res33 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("title","DESC",0,10,userToken,"464",deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res33);


				Response FetchFavouriteBooks_res4 = FetchFavouriteBooks.fetchFavouriteBooks_per("author", "ASC", userToken, "464", deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res4);


				Response FetchFavouriteBooks_res41 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("author", "ASC",0,10, userToken, "464", deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res41);


				Response FetchFavouriteBooks_res42 = FetchFavouriteBooks.fetchFavouriteBooks_per("author","DESC",userToken,"464",deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res42);


				Response FetchFavouriteBooks_res43 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("author","DESC",0,10,userToken,"464",deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res43);


				Response FetchFavouriteBooks_res5 = FetchFavouriteBooks.fetchFavouriteBooks_per("archive_date", "ASC", userToken, "464", deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res5, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res5, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res5);


				Response FetchFavouriteBooks_res51 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("archive_date", "ASC",0,10, userToken, "464", deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res51, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res51, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res51);


				Response FetchFavouriteBooks_res52 = FetchFavouriteBooks.fetchFavouriteBooks_per("archive_date","DESC",userToken,"464",deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res52, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res52, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res52);


				Response FetchFavouriteBooks_res53 = FetchFavouriteBooks.fetchFavouriteBooks_per_withPagi("archive_date","DESC",0,10,userToken,"464",deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res53, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res53, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res53);



				//********* END FETCHFAVOURATESBOOKS PERMUTATION

				Response unMarkAsFavourite_res = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark1,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(unMarkAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(unMarkAsFavourite_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(unMarkAsFavourite_res);


				Response unMarkAsFavourite_res1 = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark2,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(unMarkAsFavourite_res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(unMarkAsFavourite_res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(unMarkAsFavourite_res1);


				Response unMarkAsFavourite_res3 = UnMarkAsFavourite.unMarkAsFavourite(BookID_mark3,userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(unMarkAsFavourite_res3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(unMarkAsFavourite_res3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(unMarkAsFavourite_res3);


				Response FetchFavouriteBooks_resA= FetchFavouriteBooks.fetchFavouriteBooks(userToken,"45564595",deviceT);
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_resA, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_resA, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_resA);
				Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_resA, ""+BookID_mark1+"");
				Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_resA, ""+BookID_mark2+"");
				Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_resA, ""+BookID_mark3+"");



				SimpleDateFormat formatter11 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
				Date date1 = new Date();  
				String  time1=formatter11.format(date1);
				Response saveSessionHistory_res = SaveSessionHistory.saveSessionHistory(userToken,"45564595",deviceT,bookID1,time1);
				Log.info("TIME : "+time);
				Validation.responseHeaderCodeValidation(saveSessionHistory_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(saveSessionHistory_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(saveSessionHistory_res);
				Validation.responseKeyValidation_key(saveSessionHistory_res, "ok");


				time1 = formatter11.format(date1);
				Response saveSessionHistory_res2 = SaveSessionHistory.saveSessionHistory(userToken,"45564595",deviceT,bookID2,time1);
				Log.info("TIME : "+time1);
				Validation.responseHeaderCodeValidation(saveSessionHistory_res2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(saveSessionHistory_res2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(saveSessionHistory_res2);
				Validation.responseKeyValidation_key(saveSessionHistory_res2, "ok");


				time1 = formatter11.format(date1);
				Response saveSessionHistory_res3 = SaveSessionHistory.saveSessionHistory(userToken,"45564595",deviceT,bookID3,time);
				Log.info("TIME : "+time1);
				Validation.responseHeaderCodeValidation(saveSessionHistory_res3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(saveSessionHistory_res3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(saveSessionHistory_res3);
				Validation.responseKeyValidation_key(saveSessionHistory_res3, "ok");



				Response FetchRecentlyViewed_res = FetchRecentlyViewed.fetchRecentlyViewed_without_pagi(userToken,"54254fd",deviceT);
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



				//START Recently viewed BOOKS PERMUTATION
				Response fetchRecentlyViewed_permu1 = FetchRecentlyViewed.fetchRecentlyViewed_permu("isbn", "ASC", userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu1);


				Response fetchRecentlyViewed_permu11 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("isbn","ASC",0,10,userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu11);
				totalbooks=fetchRecentlyViewed_permu11.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);


				Response fetchRecentlyViewed_permu12 = FetchRecentlyViewed.fetchRecentlyViewed_permu("isbn", "DESC", userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu12);


				Response fetchRecentlyViewed_permu13 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("isbn","DESC",0,10,userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu13, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu13);
				totalbooks=fetchRecentlyViewed_permu13.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);


				Response fetchRecentlyViewed_permu2 = FetchRecentlyViewed.fetchRecentlyViewed_permu("title", "ASC", userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu2);


				Response fetchRecentlyViewed_permu21 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("title","ASC",0,10,userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu21, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu21, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu21);
				totalbooks=fetchRecentlyViewed_permu21.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);


				Response fetchRecentlyViewed_permu22 = FetchRecentlyViewed.fetchRecentlyViewed_permu("title", "DESC", userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu22);


				Response fetchRecentlyViewed_permu23 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("title","DESC",0,10,userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu23, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu23, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu23);
				totalbooks=fetchRecentlyViewed_permu23.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);


				Response fetchRecentlyViewed_permu3 = FetchRecentlyViewed.fetchRecentlyViewed_permu("archive_date", "ASC", userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu3);


				Response fetchRecentlyViewed_permu31 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("archive_date","ASC",0,10,userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu31, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu31, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu31);
				totalbooks=fetchRecentlyViewed_permu31.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);


				Response fetchRecentlyViewed_permu32 = FetchRecentlyViewed.fetchRecentlyViewed_permu("archive_date", "DESC", userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu32, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu32, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu32);
				System.out.println("fetchRecentlyViewed_permu1 : "+fetchRecentlyViewed_permu32);

				Response fetchRecentlyViewed_permu33 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("archive_date","DESC",0,10,userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu33);
				totalbooks=fetchRecentlyViewed_permu33.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);


				Response fetchRecentlyViewed_permu4 = FetchRecentlyViewed.fetchRecentlyViewed_permu("author", "ASC", userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu4);



				Response fetchRecentlyViewed_permu41 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("author","ASC",0,10,userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu41, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu41, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu41);
				totalbooks=fetchRecentlyViewed_permu41.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);


				Response fetchRecentlyViewed_permu42 = FetchRecentlyViewed.fetchRecentlyViewed_permu("author", "DESC", userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu42, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu42, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu42);


				Response fetchRecentlyViewed_permu43 = FetchRecentlyViewed.fetchRecentlyViewed_permu_withpagi("author","DESC",0,10,userToken,"da31131",deviceT);
				Validation.responseHeaderCodeValidation(fetchRecentlyViewed_permu43, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(fetchRecentlyViewed_permu43, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchRecentlyViewed_permu43);
				totalbooks=fetchRecentlyViewed_permu43.then().extract().path("totalbooks");
				Log.info("totalbooks : "+totalbooks);
				Validation.responseISGreater("totalbooks", totalbooks, 3);


				//END Rectently viewed BOOKS PERMUTATION


				Response fetchRecentlyViewed_with_pagi_res = FetchRecentlyViewed.fetchRecentlyViewed_with_pagi(0,20,userToken,"hf454",deviceT);
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
				}
			}
		}
		catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
		}
	}
}
