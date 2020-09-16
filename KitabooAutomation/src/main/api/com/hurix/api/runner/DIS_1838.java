package com.hurix.api.runner;

import io.restassured.response.Response;

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

import com.hurix.api.readerAPIs.Authenticate;
import com.hurix.api.readerAPIs.FetchBookList;
import com.hurix.api.readerAPIs.MultiCategoryBookList;
import com.hurix.api.readerAPIs.MultiCategoryCollectionBookList;
import com.hurix.api.readerAPIs.RefreshCategory;
import com.hurix.api.utility.ExcelUtils;
import com.hurix.api.utility.ExtractCategory;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class DIS_1838 {

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
	//public static String consumerKey=ExcelUtils.Consumer_key;
	//public static String consumerSecret=ExcelUtils.secret_key;

	public static void   main(String []args) throws SQLException, JSONException{
		Log.initialization("DIS_1838");	
		//List<String> detail =  ExcelUtils.getuserDetails();
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			String excelPath="./testData/DIS-1838.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=5;i++)
			{	
				DataFormatter formatter = new DataFormatter();
				environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
				userName = formatter.formatCellValue(sheet.getRow(i).getCell(1));			
				password = formatter.formatCellValue(sheet.getRow(i).getCell(2));
				consumerKey = formatter.formatCellValue(sheet.getRow(i).getCell(4));
				consumerSecret = formatter.formatCellValue(sheet.getRow(i).getCell(5));
				clientID = formatter.formatCellValue(sheet.getRow(i).getCell(3));	
				catlevel = formatter.formatCellValue(sheet.getRow(i).getCell(6));
				categoryIdList0 = formatter.formatCellValue(sheet.getRow(i).getCell(7));
				categoryIdList1 = formatter.formatCellValue(sheet.getRow(i).getCell(8));
				categoryIdList2 = formatter.formatCellValue(sheet.getRow(i).getCell(9));
				categoryIdList3 = formatter.formatCellValue(sheet.getRow(i).getCell(10));

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
				System.out.println("detail : "+detail);
				System.out.println("userName : "+userName);
				System.out.println("password : "+password);
				Log.startTestCase("Authenticate");
				Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"514185","IPAD");
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
				Log.endTestCase("End");


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
				//Validation.responseKeyValidation_key(fetchBookList_without_pagination, "locale");
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "collectionThumbnail");
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "collectionType");
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "formats");
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "readingPercentage");
				//Validation.responseKeyValidation_key(fetchBookList_without_pagination, "classID");
				//Validation.responseKeyValidation_key(jsonResponse, Title)
				bookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[0]");
				System.out.println("bookID_1: "+bookID1);
				bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[1]");			
				System.out.println("bookID2: "+bookID2);
				/*bookID3 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[2]");			
				System.out.println("bookID3: "+bookID3);*/
				//bookID6 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[6]");
				//System.out.println("bookID6 :: "+bookID6);
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
				/*categoryIdList0 = fetchBookList_without_pagination.then().extract().path("bookList.book.categoryIdList[0]");
				Log.info("categoryIdList0 : "+categoryIdList0);*/

	//IPAD	
				//System.out.println("@#$%^&*(*&^%$#@#$%^&*&^%$# HERE");
				//String categoryIdList01=((String) categoryIdList0).replace("||", ",");
				//Log.info("categoryIdList01 : "+categoryIdList01);
				Response Refreshcategory_Nat=RefreshCategory.refreshCategory(""+categoryIdList0+"", userToken, "fs313", "IPAD");
				Validation.responseHeaderCodeValidation(Refreshcategory_Nat,HttpStatus.SC_OK);//Native_cat2_UPD
				Validation.responseCodeValidation1(Refreshcategory_Nat, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Refreshcategory_Nat);
				responseMsg=Refreshcategory_Nat.then().extract().path("responseMsg");
				Validation.responseKeyAndValue(Refreshcategory_Nat,"responseMsg","SUCCESS");
				Validation.responseKeyValidation_key(Refreshcategory_Nat, "id");
				int id0=Refreshcategory_Nat.then().extract().path("categories.id[0]");
				Log.info("id0 : "+id0);
				Validation.responseKeyValidation_key(Refreshcategory_Nat, "name");
				Validation.responseKeyValidation_key(Refreshcategory_Nat, "hash");
				Validation.responseKeyValidation_key(Refreshcategory_Nat, "bookIdList");	
				System.out.println("Refreshcategory_Nat : "+Refreshcategory_Nat);


				Response FetchBookList_nat=FetchBookList.fetchBookList_without_pagination(userToken, "ewe3242", "IPAD");
				Validation.responseHeaderCodeValidation(FetchBookList_nat,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookList_nat, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookList_nat);
				title=FetchBookList_nat.then().extract().path("bookList.book.title[0]");
				Log.info("title : "+title);
				category=FetchBookList_nat.then().extract().path("bookList.book.category[0]");
				Log.info("category : "+category);
				//Validation.responseKcount(FetchBookList_Refl, category);
				//Validation.responseKcount1(FetchBookList_Refl, "New_ADHOC");
				categoryList=FetchBookList_nat.then().extract().path("bookList.book.categoryList[0]");
				Log.info("categoryList : "+categoryList);
				categoryIdList=FetchBookList_nat.then().extract().path("bookList.book.categoryIdList[0]");
				Log.info("categoryIdList : "+categoryIdList);
				System.out.println("FetchBookList_Refl : "+FetchBookList_nat);
				Validation.responseKeyValidation_key(FetchBookList_nat, "New_ADHOC");
				//categoryIdList1=FetchBookList_nat.then().extract().path("bookList.book.categoryIdList[17]");

				Response RefreshcategoryRefl=RefreshCategory.refreshCategory(""+categoryIdList1+"", userToken, "fs313", "IPAD");
				Validation.responseHeaderCodeValidation(RefreshcategoryRefl,HttpStatus.SC_OK);//Reflow_cat2_UPD
				Validation.responseCodeValidation1(RefreshcategoryRefl, HttpStatus.SC_OK);
				Validation.responseTimeValidation(RefreshcategoryRefl);
				responseMsg=RefreshcategoryRefl.then().extract().path("responseMsg");
				Validation.responseKeyAndValue(RefreshcategoryRefl,"responseMsg","SUCCESS");
				Validation.responseKeyValidation_key(RefreshcategoryRefl, "id");
				Validation.responseKeyValidation_key(RefreshcategoryRefl, "name");
				Validation.responseKeyValidation_key(RefreshcategoryRefl, "hash");
				Validation.responseKeyValidation_key(RefreshcategoryRefl, "bookIdList");	
				System.out.println("RefreshCategory : "+RefreshcategoryRefl);

				Response FetchBookList_ver_Reflo=FetchBookList.fetchBookList_without_pagination(userToken,"ewe3242","IPAD");
				Validation.responseHeaderCodeValidation(FetchBookList_ver_Reflo,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookList_ver_Reflo, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookList_ver_Reflo);				
				//Validation.responseKeyValidation_key(FetchBookList_ver_Reflo, "146");
				title=FetchBookList_ver_Reflo.then().extract().path("bookList.book.title[2]");
				Log.info("title : "+title);
				category=FetchBookList_ver_Reflo.then().extract().path("bookList.book.category[2]");
				Log.info("category : "+category);
				categoryList=FetchBookList_ver_Reflo.then().extract().path("bookList.book.categoryList[2]");
				Log.info("categoryList : "+categoryList);
				categoryIdList=FetchBookList_ver_Reflo.then().extract().path("bookList.book.categoryIdList[2]");
				Log.info("categoryIdList : "+categoryIdList);
				System.out.println("FetchBookList_ver : "+FetchBookList_ver_Reflo);
				Validation.responseKeyValidation_key(FetchBookList_ver_Reflo, "New_ADHOC");
				//categoryIdList2=FetchBookList_ver_Reflo.then().extract().path("bookList.book.categoryIdList[18]");

				Response RefreshcategoryAudio=RefreshCategory.refreshCategory(""+categoryIdList2+"", userToken, "fs313", "IPAD");
				Validation.responseHeaderCodeValidation(RefreshcategoryAudio,HttpStatus.SC_OK);//Audio_cat2_UPD1
				Validation.responseCodeValidation1(RefreshcategoryAudio, HttpStatus.SC_OK);
				Validation.responseTimeValidation(RefreshcategoryAudio);
				responseMsg=RefreshcategoryAudio.then().extract().path("responseMsg");
				Validation.responseKeyAndValue(RefreshcategoryAudio,"responseMsg","SUCCESS");
				Validation.responseKeyValidation_key(RefreshcategoryAudio, "id");
				Validation.responseKeyValidation_key(RefreshcategoryAudio, "name");
				Validation.responseKeyValidation_key(RefreshcategoryAudio, "hash");
				Validation.responseKeyValidation_key(RefreshcategoryAudio, "bookIdList");	
				System.out.println("RefreshCategory : "+RefreshcategoryAudio);

				Response FetchBookList_ver_audio=FetchBookList.fetchBookList_without_pagination(userToken,"ewe3242","IPAD");
				Validation.responseHeaderCodeValidation(FetchBookList_ver_audio,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookList_ver_audio, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookList_ver_audio);				
				//Validation.responseKeyValidation_key(FetchBookList_ver_audio, "206");
				title=FetchBookList_ver_audio.then().extract().path("bookList.book.title[3]");
				Log.info("title : "+title);
				category=FetchBookList_ver_audio.then().extract().path("bookList.book.category[3]");
				Log.info("category : "+category);
				categoryList=FetchBookList_ver_audio.then().extract().path("bookList.book.categoryList[3]");
				Log.info("categoryList : "+categoryList);
				categoryIdList=FetchBookList_ver_audio.then().extract().path("bookList.book.categoryIdList[3]");
				Log.info("categoryIdList : "+categoryIdList);
				System.out.println("FetchBookList_ver : "+FetchBookList_ver_audio);
				Validation.responseKeyValidation_key(FetchBookList_ver_audio, "New_ADHOC");
				//categoryIdList3=FetchBookList_ver_audio.then().extract().path("bookList.book.categoryIdList[2]");

				Response Refreshcategory_Fixed=RefreshCategory.refreshCategory(""+categoryIdList3+"", userToken, "fs313", "IPAD");
				Validation.responseHeaderCodeValidation(Refreshcategory_Fixed,HttpStatus.SC_OK);//Fixed_cat2_UPD
				Validation.responseCodeValidation1(Refreshcategory_Fixed, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Refreshcategory_Fixed);
				responseMsg =  Refreshcategory_Fixed.then().extract().path("responseMsg");
				Validation.responseKeyAndValue(Refreshcategory_Fixed,"responseMsg","SUCCESS");
				Validation.responseKeyValidation_key(Refreshcategory_Fixed, "id");
				Validation.responseKeyValidation_key(Refreshcategory_Fixed, "name");
				Validation.responseKeyValidation_key(Refreshcategory_Fixed, "hash");
				Validation.responseKeyValidation_key(Refreshcategory_Fixed, "bookIdList");	
				System.out.println("RefreshCategory : "+Refreshcategory_Fixed);

				Response FetchBookList_ver_Fix=FetchBookList.fetchBookList_without_pagination(userToken, "ewe3242", "IPAD");
				Validation.responseHeaderCodeValidation(FetchBookList_ver_Fix,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookList_ver_Fix, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookList_ver_Fix);				
				//Validation.responseKeyValidation_key(FetchBookList_ver_Fix, "133");
				title=FetchBookList_ver_Fix.then().extract().path("bookList.book.title[5]");
				Log.info("title : "+title);
				category=FetchBookList_ver_Fix.then().extract().path("bookList.book.category[5]");
				Log.info("category : "+category);
				categoryList=FetchBookList_ver_Fix.then().extract().path("bookList.book.categoryList[5]");
				Log.info("categoryList : "+categoryList);
				categoryIdList=FetchBookList_ver_Fix.then().extract().path("bookList.book.categoryIdList[5]");
				Log.info("categoryIdList : "+categoryIdList);
				System.out.println("FetchBookList_ver : "+FetchBookList_ver_Fix);
				Validation.responseKeyValidation_key(FetchBookList_ver_Fix, "New_ADHOC");
				//categoryIdList4=FetchBookList_ver_Fix.then().extract().path("bookList.book.categoryIdList[19]");
				
				Response MultiCategorybookList=MultiCategoryBookList.multiCategoryBookList(catlevel, bookID1, "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123", userToken, "dsd3231332", "IPAD");
				Validation.responseHeaderCodeValidation(MultiCategorybookList,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategorybookList, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategorybookList);
				Validation.responseNOTKeyValidation_key(MultiCategorybookList, "category");
				Validation.responseNOTKeyValidation_key(MultiCategorybookList, "categoryIdList");
				Validation.responseNOTKeyValidation_key(MultiCategorybookList, "categoryList");
				Validation.responseKeyValidation_key(MultiCategorybookList, "collectionID");
				Validation.responseKeyValidation_key(MultiCategorybookList, "New_ADHOC");
				System.out.println("MultiCategorybookList : "+MultiCategorybookList);
				
				Response multiCategoryCollectionBookList=MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel,bookID1,"jdbc:mysql://172.18.10.147:3306","readonly","readonly@123",userToken,"ds32323","IPAD",collectionName0);
				Validation.responseHeaderCodeValidation(multiCategoryCollectionBookList,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(multiCategoryCollectionBookList, HttpStatus.SC_OK);
				Validation.responseTimeValidation(multiCategoryCollectionBookList);
				Validation.responseNOTKeyValidation_key(multiCategoryCollectionBookList, "category");
				Validation.responseNOTKeyValidation_key(multiCategoryCollectionBookList, "categoryIdList");
				Validation.responseNOTKeyValidation_key(multiCategoryCollectionBookList, "categoryList");
				Validation.responseKeyValidation_key(multiCategoryCollectionBookList, "collectionID");
				Validation.responseKeyValidation_key(multiCategoryCollectionBookList, "New_ADHOC");
				Validation.responseKeyAndValue(multiCategoryCollectionBookList,"collectionTitle",collectionName0);
				System.out.println("MultiCategoryCollectionBookList : "+multiCategoryCollectionBookList);

	//ANDROID		
				Refreshcategory_Nat=RefreshCategory.refreshCategory(""+categoryIdList0+"", userToken, "fs313", "ANDROID");
				Validation.responseHeaderCodeValidation(Refreshcategory_Nat,HttpStatus.SC_OK);//Native_cat2_UPD
				Validation.responseCodeValidation1(Refreshcategory_Nat, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Refreshcategory_Nat);
				responseMsg=Refreshcategory_Nat.then().extract().path("responseMsg");
				Validation.responseKeyAndValue(Refreshcategory_Nat,"responseMsg","SUCCESS");
				Validation.responseKeyValidation_key(Refreshcategory_Nat, "id");
				id0=Refreshcategory_Nat.then().extract().path("categories.id[0]");
				Log.info("id0 : "+id0);
				Validation.responseKeyValidation_key(Refreshcategory_Nat, "name");
				Validation.responseKeyValidation_key(Refreshcategory_Nat, "hash");
				Validation.responseKeyValidation_key(Refreshcategory_Nat, "bookIdList");	
				System.out.println("Refreshcategory_Nat : "+Refreshcategory_Nat);


				FetchBookList_nat=FetchBookList.fetchBookList_without_pagination(userToken, "ewe3242", "ANDROID");
				Validation.responseHeaderCodeValidation(FetchBookList_nat,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookList_nat, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookList_nat);
				title=FetchBookList_nat.then().extract().path("bookList.book.title[0]");
				Log.info("title : "+title);
				category=FetchBookList_nat.then().extract().path("bookList.book.category[0]");
				Log.info("category : "+category);
				//Validation.responseKcount(FetchBookList_Refl, category);
				//Validation.responseKcount1(FetchBookList_Refl, "New_ADHOC");
				categoryList=FetchBookList_nat.then().extract().path("bookList.book.categoryList[0]");
				Log.info("categoryList : "+categoryList);
				categoryIdList=FetchBookList_nat.then().extract().path("bookList.book.categoryIdList[0]");
				Log.info("categoryIdList : "+categoryIdList);
				System.out.println("FetchBookList_Refl : "+FetchBookList_nat);
				Validation.responseKeyValidation_key(FetchBookList_nat, "New_ADHOC");
				//categoryIdList1=FetchBookList_nat.then().extract().path("bookList.book.categoryIdList[17]");

				RefreshcategoryRefl=RefreshCategory.refreshCategory(""+categoryIdList1+"", userToken, "fs313", "ANDROID");
				Validation.responseHeaderCodeValidation(RefreshcategoryRefl,HttpStatus.SC_OK);//Reflow_cat2_UPD
				Validation.responseCodeValidation1(RefreshcategoryRefl, HttpStatus.SC_OK);
				Validation.responseTimeValidation(RefreshcategoryRefl);
				responseMsg=RefreshcategoryRefl.then().extract().path("responseMsg");
				Validation.responseKeyAndValue(RefreshcategoryRefl,"responseMsg","SUCCESS");
				Validation.responseKeyValidation_key(RefreshcategoryRefl, "id");
				Validation.responseKeyValidation_key(RefreshcategoryRefl, "name");
				Validation.responseKeyValidation_key(RefreshcategoryRefl, "hash");
				Validation.responseKeyValidation_key(RefreshcategoryRefl, "bookIdList");	
				System.out.println("RefreshCategory : "+RefreshcategoryRefl);

				FetchBookList_ver_Reflo=FetchBookList.fetchBookList_without_pagination(userToken, "ewe3242", "ANDROID");
				Validation.responseHeaderCodeValidation(FetchBookList_ver_Reflo,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookList_ver_Reflo, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookList_ver_Reflo);				
				//Validation.responseKeyValidation_key(FetchBookList_ver_Reflo, "146");
				title=FetchBookList_ver_Reflo.then().extract().path("bookList.book.title[2]");
				Log.info("title : "+title);
				category=FetchBookList_ver_Reflo.then().extract().path("bookList.book.category[2]");
				Log.info("category : "+category);
				categoryList=FetchBookList_ver_Reflo.then().extract().path("bookList.book.categoryList[2]");
				Log.info("categoryList : "+categoryList);
				categoryIdList=FetchBookList_ver_Reflo.then().extract().path("bookList.book.categoryIdList[2]");
				Log.info("categoryIdList : "+categoryIdList);
				System.out.println("FetchBookList_ver : "+FetchBookList_ver_Reflo);
				Validation.responseKeyValidation_key(FetchBookList_ver_Reflo, "New_ADHOC");
				//categoryIdList2=FetchBookList_ver_Reflo.then().extract().path("bookList.book.categoryIdList[18]");
				

				RefreshcategoryAudio=RefreshCategory.refreshCategory(""+categoryIdList2+"", userToken, "fs313", "ANDROID");
				Validation.responseHeaderCodeValidation(RefreshcategoryAudio,HttpStatus.SC_OK);//Audio_cat2_UPD1
				Validation.responseCodeValidation1(RefreshcategoryAudio, HttpStatus.SC_OK);
				Validation.responseTimeValidation(RefreshcategoryAudio);
				responseMsg=RefreshcategoryAudio.then().extract().path("responseMsg");
				Validation.responseKeyAndValue(RefreshcategoryAudio,"responseMsg","SUCCESS");
				Validation.responseKeyValidation_key(RefreshcategoryAudio, "id");
				Validation.responseKeyValidation_key(RefreshcategoryAudio, "name");
				Validation.responseKeyValidation_key(RefreshcategoryAudio, "hash");
				Validation.responseKeyValidation_key(RefreshcategoryAudio, "bookIdList");	
				System.out.println("RefreshCategory : "+RefreshcategoryAudio);

				FetchBookList_ver_audio=FetchBookList.fetchBookList_without_pagination(userToken, "ewe3242", "ANDROID");
				Validation.responseHeaderCodeValidation(FetchBookList_ver_audio,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookList_ver_audio, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookList_ver_audio);				
				//Validation.responseKeyValidation_key(FetchBookList_ver_audio, "206");
				title=FetchBookList_ver_audio.then().extract().path("bookList.book.title[3]");
				Log.info("title : "+title);
				category=FetchBookList_ver_audio.then().extract().path("bookList.book.category[3]");
				Log.info("category : "+category);
				categoryList=FetchBookList_ver_audio.then().extract().path("bookList.book.categoryList[3]");
				Log.info("categoryList : "+categoryList);
				categoryIdList=FetchBookList_ver_audio.then().extract().path("bookList.book.categoryIdList[3]");
				Log.info("categoryIdList : "+categoryIdList);
				System.out.println("FetchBookList_ver : "+FetchBookList_ver_audio);
				Validation.responseKeyValidation_key(FetchBookList_ver_audio, "New_ADHOC");
				//categoryIdList3=FetchBookList_ver_audio.then().extract().path("bookList.book.categoryIdList[2]");
				

				Refreshcategory_Fixed=RefreshCategory.refreshCategory(""+categoryIdList3+"", userToken, "fs313", "ANDROID");
				Validation.responseHeaderCodeValidation(Refreshcategory_Fixed,HttpStatus.SC_OK);//Fixed_cat2_UPD
				Validation.responseCodeValidation1(Refreshcategory_Fixed, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Refreshcategory_Fixed);
				responseMsg =  Refreshcategory_Fixed.then().extract().path("responseMsg");
				Validation.responseKeyAndValue(Refreshcategory_Fixed,"responseMsg","SUCCESS");
				Validation.responseKeyValidation_key(Refreshcategory_Fixed, "id");
				Validation.responseKeyValidation_key(Refreshcategory_Fixed, "name");
				Validation.responseKeyValidation_key(Refreshcategory_Fixed, "hash");
				Validation.responseKeyValidation_key(Refreshcategory_Fixed, "bookIdList");	
				System.out.println("RefreshCategory : "+Refreshcategory_Fixed);

				FetchBookList_ver_Fix=FetchBookList.fetchBookList_without_pagination(userToken, "ewe3242", "ANDROID");
				Validation.responseHeaderCodeValidation(FetchBookList_ver_Fix,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookList_ver_Fix, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookList_ver_Fix);				
				//Validation.responseKeyValidation_key(FetchBookList_ver_Fix, "133");
				title=FetchBookList_ver_Fix.then().extract().path("bookList.book.title[5]");
				Log.info("title : "+title);
				category=FetchBookList_ver_Fix.then().extract().path("bookList.book.category[5]");
				Log.info("category : "+category);
				categoryList=FetchBookList_ver_Fix.then().extract().path("bookList.book.categoryList[5]");
				Log.info("categoryList : "+categoryList);
				categoryIdList=FetchBookList_ver_Fix.then().extract().path("bookList.book.categoryIdList[5]");
				Log.info("categoryIdList : "+categoryIdList);
				System.out.println("FetchBookList_ver : "+FetchBookList_ver_Fix);
				Validation.responseKeyValidation_key(FetchBookList_ver_Fix, "New_ADHOC");
				
				MultiCategorybookList=MultiCategoryBookList.multiCategoryBookList(catlevel, bookID1, "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123", userToken, "dsd3231332", "IPAD");
				Validation.responseHeaderCodeValidation(MultiCategorybookList,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategorybookList, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategorybookList);
				Validation.responseNOTKeyValidation_key(MultiCategorybookList, "category");
				Validation.responseNOTKeyValidation_key(MultiCategorybookList, "categoryIdList");
				Validation.responseNOTKeyValidation_key(MultiCategorybookList, "categoryList");
				Validation.responseKeyValidation_key(MultiCategorybookList, "collectionID");
				Validation.responseKeyValidation_key(MultiCategorybookList, "New_ADHOC");
				System.out.println("MultiCategorybookList : "+MultiCategorybookList);
				
				multiCategoryCollectionBookList=MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel, bookID1,"jdbc:mysql://172.18.10.147:3306","readonly","readonly@123", userToken, "ds32323", "IPAD", collectionName0);
				Validation.responseHeaderCodeValidation(multiCategoryCollectionBookList,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(multiCategoryCollectionBookList, HttpStatus.SC_OK);
				Validation.responseTimeValidation(multiCategoryCollectionBookList);
				Validation.responseNOTKeyValidation_key(multiCategoryCollectionBookList, "category");
				Validation.responseNOTKeyValidation_key(multiCategoryCollectionBookList, "categoryIdList");
				Validation.responseNOTKeyValidation_key(multiCategoryCollectionBookList, "categoryList");
				Validation.responseKeyValidation_key(multiCategoryCollectionBookList, "collectionID");
				Validation.responseKeyValidation_key(multiCategoryCollectionBookList, "New_ADHOC");
				Validation.responseKeyAndValue(multiCategoryCollectionBookList, "collectionTitle", collectionName0);
				System.out.println("MultiCategoryCollectionBookList : "+multiCategoryCollectionBookList);

	//HTML5		
				Refreshcategory_Nat=RefreshCategory.refreshCategory(""+categoryIdList0+"", userToken, "fs313", "HTML5");
				Validation.responseHeaderCodeValidation(Refreshcategory_Nat,HttpStatus.SC_OK);//Native_cat2_UPD
				Validation.responseCodeValidation1(Refreshcategory_Nat, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Refreshcategory_Nat);
				responseMsg=Refreshcategory_Nat.then().extract().path("responseMsg");
				Validation.responseKeyAndValue(Refreshcategory_Nat,"responseMsg","SUCCESS");
				Validation.responseKeyValidation_key(Refreshcategory_Nat, "id");
				id0=Refreshcategory_Nat.then().extract().path("categories.id[0]");
				Log.info("id0 : "+id0);
				Validation.responseKeyValidation_key(Refreshcategory_Nat, "name");
				Validation.responseKeyValidation_key(Refreshcategory_Nat, "hash");
				Validation.responseKeyValidation_key(Refreshcategory_Nat, "bookIdList");	
				System.out.println("Refreshcategory_Nat : "+Refreshcategory_Nat);


				FetchBookList_nat=FetchBookList.fetchBookList_without_pagination(userToken, "ewe3242", "HTML5");
				Validation.responseHeaderCodeValidation(FetchBookList_nat,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookList_nat, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookList_nat);
				title=FetchBookList_nat.then().extract().path("bookList.book.title[0]");
				Log.info("title : "+title);
				category=FetchBookList_nat.then().extract().path("bookList.book.category[0]");
				Log.info("category : "+category);
				//Validation.responseKcount(FetchBookList_Refl, category);
				//Validation.responseKcount1(FetchBookList_Refl, "New_ADHOC");
				categoryList=FetchBookList_nat.then().extract().path("bookList.book.categoryList[0]");
				Log.info("categoryList : "+categoryList);
				categoryIdList=FetchBookList_nat.then().extract().path("bookList.book.categoryIdList[0]");
				Log.info("categoryIdList : "+categoryIdList);
				System.out.println("FetchBookList_Refl : "+FetchBookList_nat);
				Validation.responseKeyValidation_key(FetchBookList_nat, "New_ADHOC");
				//categoryIdList1=FetchBookList_nat.then().extract().path("bookList.book.categoryIdList[17]");

				RefreshcategoryRefl=RefreshCategory.refreshCategory(""+categoryIdList1+"", userToken, "fs313", "HTML5");
				Validation.responseHeaderCodeValidation(RefreshcategoryRefl,HttpStatus.SC_OK);//Reflow_cat2_UPD
				Validation.responseCodeValidation1(RefreshcategoryRefl, HttpStatus.SC_OK);
				Validation.responseTimeValidation(RefreshcategoryRefl);
				responseMsg=RefreshcategoryRefl.then().extract().path("responseMsg");
				Validation.responseKeyAndValue(RefreshcategoryRefl,"responseMsg","SUCCESS");
				Validation.responseKeyValidation_key(RefreshcategoryRefl, "id");
				Validation.responseKeyValidation_key(RefreshcategoryRefl, "name");
				Validation.responseKeyValidation_key(RefreshcategoryRefl, "hash");
				Validation.responseKeyValidation_key(RefreshcategoryRefl, "bookIdList");	
				System.out.println("RefreshCategory : "+RefreshcategoryRefl);

				FetchBookList_ver_Reflo=FetchBookList.fetchBookList_without_pagination(userToken, "ewe3242", "HTML5");
				Validation.responseHeaderCodeValidation(FetchBookList_ver_Reflo,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookList_ver_Reflo, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookList_ver_Reflo);				
				//Validation.responseKeyValidation_key(FetchBookList_ver_Reflo, "146");
				title=FetchBookList_ver_Reflo.then().extract().path("bookList.book.title[2]");
				Log.info("title : "+title);
				category=FetchBookList_ver_Reflo.then().extract().path("bookList.book.category[2]");
				Log.info("category : "+category);
				categoryList=FetchBookList_ver_Reflo.then().extract().path("bookList.book.categoryList[2]");
				Log.info("categoryList : "+categoryList);
				categoryIdList=FetchBookList_ver_Reflo.then().extract().path("bookList.book.categoryIdList[2]");
				Log.info("categoryIdList : "+categoryIdList);
				System.out.println("FetchBookList_ver : "+FetchBookList_ver_Reflo);
				Validation.responseKeyValidation_key(FetchBookList_ver_Reflo, "New_ADHOC");
				//categoryIdList2=FetchBookList_ver_Reflo.then().extract().path("bookList.book.categoryIdList[18]");

				RefreshcategoryAudio=RefreshCategory.refreshCategory(""+categoryIdList0+"", userToken, "fs313", "HTML5");
				Validation.responseHeaderCodeValidation(RefreshcategoryAudio,HttpStatus.SC_OK);//Audio_cat2_UPD1
				Validation.responseCodeValidation1(RefreshcategoryAudio, HttpStatus.SC_OK);
				Validation.responseTimeValidation(RefreshcategoryAudio);
				responseMsg=RefreshcategoryAudio.then().extract().path("responseMsg");
				Validation.responseKeyAndValue(RefreshcategoryAudio,"responseMsg","SUCCESS");
				Validation.responseKeyValidation_key(RefreshcategoryAudio, "id");
				Validation.responseKeyValidation_key(RefreshcategoryAudio, "name");
				Validation.responseKeyValidation_key(RefreshcategoryAudio, "hash");
				Validation.responseKeyValidation_key(RefreshcategoryAudio, "bookIdList");	
				System.out.println("RefreshCategory : "+RefreshcategoryAudio);

				FetchBookList_ver_audio=FetchBookList.fetchBookList_without_pagination(userToken, "ewe3242", "HTML5");
				Validation.responseHeaderCodeValidation(FetchBookList_ver_audio,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookList_ver_audio, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookList_ver_audio);				
				//Validation.responseKeyValidation_key(FetchBookList_ver_audio, "206");
				title=FetchBookList_ver_audio.then().extract().path("bookList.book.title[3]");
				Log.info("title : "+title);
				category=FetchBookList_ver_audio.then().extract().path("bookList.book.category[3]");
				Log.info("category : "+category);
				categoryList=FetchBookList_ver_audio.then().extract().path("bookList.book.categoryList[3]");
				Log.info("categoryList : "+categoryList);
				categoryIdList=FetchBookList_ver_audio.then().extract().path("bookList.book.categoryIdList[3]");
				Log.info("categoryIdList : "+categoryIdList);
				System.out.println("FetchBookList_ver : "+FetchBookList_ver_audio);
				Validation.responseKeyValidation_key(FetchBookList_ver_audio, "New_ADHOC");
				//categoryIdList3=FetchBookList_ver_audio.then().extract().path("bookList.book.categoryIdList[2]");

				Refreshcategory_Fixed=RefreshCategory.refreshCategory(""+categoryIdList0+"", userToken, "fs313", "ANDROID");
				Validation.responseHeaderCodeValidation(Refreshcategory_Fixed,HttpStatus.SC_OK);//Fixed_cat2_UPD
				Validation.responseCodeValidation1(Refreshcategory_Fixed, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Refreshcategory_Fixed);
				responseMsg =  Refreshcategory_Fixed.then().extract().path("responseMsg");
				Validation.responseKeyAndValue(Refreshcategory_Fixed,"responseMsg","SUCCESS");
				Validation.responseKeyValidation_key(Refreshcategory_Fixed, "id");
				Validation.responseKeyValidation_key(Refreshcategory_Fixed, "name");
				Validation.responseKeyValidation_key(Refreshcategory_Fixed, "hash");
				Validation.responseKeyValidation_key(Refreshcategory_Fixed, "bookIdList");	
				System.out.println("RefreshCategory : "+Refreshcategory_Fixed);

				FetchBookList_ver_Fix=FetchBookList.fetchBookList_without_pagination(userToken, "ewe3242", "HTML5");
				Validation.responseHeaderCodeValidation(FetchBookList_ver_Fix,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchBookList_ver_Fix, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchBookList_ver_Fix);				
				//Validation.responseKeyValidation_key(FetchBookList_ver_Fix, "133");
				title=FetchBookList_ver_Fix.then().extract().path("bookList.book.title[5]");
				Log.info("title : "+title);
				category=FetchBookList_ver_Fix.then().extract().path("bookList.book.category[5]");
				Log.info("category : "+category);
				categoryList=FetchBookList_ver_Fix.then().extract().path("bookList.book.categoryList[5]");
				Log.info("categoryList : "+categoryList);
				categoryIdList=FetchBookList_ver_Fix.then().extract().path("bookList.book.categoryIdList[5]");
				Log.info("categoryIdList : "+categoryIdList);
				System.out.println("FetchBookList_ver : "+FetchBookList_ver_Fix);
				Validation.responseKeyValidation_key(FetchBookList_ver_Fix, "New_ADHOC");
				
				MultiCategorybookList=MultiCategoryBookList.multiCategoryBookList(catlevel, bookID1, "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123", userToken, "dsd3231332", "IPAD");
				Validation.responseHeaderCodeValidation(MultiCategorybookList,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategorybookList, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategorybookList);
				Validation.responseNOTKeyValidation_key(MultiCategorybookList, "category");
				Validation.responseNOTKeyValidation_key(MultiCategorybookList, "categoryIdList");
				Validation.responseNOTKeyValidation_key(MultiCategorybookList, "categoryList");
				Validation.responseKeyValidation_key(MultiCategorybookList, "collectionID");
				Validation.responseKeyValidation_key(MultiCategorybookList, "New_ADHOC");
				System.out.println("MultiCategorybookList : "+MultiCategorybookList);
				
				multiCategoryCollectionBookList=MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel, bookID1,"jdbc:mysql://172.18.10.147:3306","readonly","readonly@123", userToken, "ds32323", "IPAD", collectionName0);
				Validation.responseHeaderCodeValidation(multiCategoryCollectionBookList,HttpStatus.SC_OK);
				Validation.responseCodeValidation1(multiCategoryCollectionBookList, HttpStatus.SC_OK);
				Validation.responseTimeValidation(multiCategoryCollectionBookList);
				Validation.responseNOTKeyValidation_key(multiCategoryCollectionBookList, "category");
				Validation.responseNOTKeyValidation_key(multiCategoryCollectionBookList, "categoryIdList");
				Validation.responseNOTKeyValidation_key(multiCategoryCollectionBookList, "categoryList");
				Validation.responseKeyValidation_key(multiCategoryCollectionBookList, "collectionID");
				Validation.responseKeyValidation_key(multiCategoryCollectionBookList, "New_ADHOC");
				Validation.responseKeyAndValue(multiCategoryCollectionBookList, "collectionTitle", collectionName0);
				System.out.println("MultiCategoryCollectionBookList : "+multiCategoryCollectionBookList);

			}
		}catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}
}
