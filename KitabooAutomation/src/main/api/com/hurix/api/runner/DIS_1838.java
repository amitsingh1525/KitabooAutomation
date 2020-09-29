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
import com.hurix.api.utility.JDBC_category;
import com.hurix.api.utility.Validation;
import com.hurix.automation.utility.Log;

public class DIS_1838 {

	static List<String> detailisbn =  ExcelUtils.getisbn();
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	//System.out.println(df.format(dateobj));
	// static long startDate1 = EpochTime.getEpochTime("df.format(dateobj");
	static long startDate ;//EpochTime.getEpochTime("2019/10/31 14:46:04");
	static long startIndex = 0;
	static long endIndex = 100;
	// static int level;
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
	static int client_Id;
	static String isbn;
	static String isbnMeta;
	static String isbnIng;
	static String forName;
	static String responseMsg;
	static String content_ownership;
	static int userID;
	static int totalbooks;
	static int total;
	static String archiveDate;
	static String archiveDate6;
	static String operation0;
	static String operation1;
	static String ebookID1;
	static String catname;
	static String categoriesname;
	static String collectionName0;
	static String collectionName1;
	static String catname1;
	static int totalCategories;
	static String clientUserID;
	static String category1;
	static String clientBookID;
	static String search = "Native";	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static String environMent;
	static String deviceT;
	static String userName;
	static String password;
	static String detail;
	static String sqlhost;
	static String sqlUsername;
	static String sqlPassword;
	static String externalURI;
	static String clientID;
	static String runY_N;
	static String catlevel;
	static int type;
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
	static String consumerKey;
	static String consumerSecret;
	static Object categoryIdList0;
	static Object categoryIdList1;
	static Object categoryIdList2;
	static Object categoryIdList3;
	static Object categoryIdList4;
	static String category;
	static Object categoryList;
	static Object categoryIdList;
	static String title;
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
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{DataFormatter formatter = new DataFormatter();
			environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			userName = formatter.formatCellValue(sheet.getRow(i).getCell(1));			
			password = formatter.formatCellValue(sheet.getRow(i).getCell(2));				
			catlevel = formatter.formatCellValue(sheet.getRow(i).getCell(3));
			deviceT = formatter.formatCellValue(sheet.getRow(i).getCell(4));
			runY_N = formatter.formatCellValue(sheet.getRow(i).getCell(5));
			//clientID = formatter.formatCellValue(sheet.getRow(i).getCell(3));

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

			clientID=JDBC_category.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			Log.info("clientID : "+clientID);
			Log.startTestCase("Authenticate");
			Log.info("TotalRows : "+sheet.getLastRowNum());
			Log.info("detail : "+detail);
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			Log.info("clientID : "+clientID);
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

			consumerKey = JDBC_category.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
			consumerSecret =JDBC_category.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);
			//clientID =JDBC_category.getreader(client_Id, sqlhost, sqlUsername, sqlPassword);

			Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"ssc5454",deviceT);
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
			Response Refreshcategory_Nat=RefreshCategory.refreshCategory("[13,2]", userToken, "fs313", deviceT);
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


			Response FetchBookList_nat=FetchBookList.fetchBookList_without_pagination(userToken, "ewe3242", deviceT);
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

			Response RefreshcategoryRefl=RefreshCategory.refreshCategory("[13,2]", userToken, "fs313",deviceT);
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

			Response FetchBookList_ver_Reflo=FetchBookList.fetchBookList_without_pagination(userToken,"ewe3242",deviceT);
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

			Response RefreshcategoryAudio=RefreshCategory.refreshCategory("[13,3]", userToken, "fs313", deviceT);
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

			Response FetchBookList_ver_audio=FetchBookList.fetchBookList_without_pagination(userToken,"ewe3242",deviceT);
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

			Response Refreshcategory_Fixed=RefreshCategory.refreshCategory("[13,2]", userToken, "fs313",deviceT);
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

			Response FetchBookList_ver_Fix=FetchBookList.fetchBookList_without_pagination(userToken, "ewe3242",deviceT);
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

			Response MultiCategorybookList=MultiCategoryBookList.multiCategoryBookList(catlevel, bookID1, sqlhost,  sqlUsername,  sqlPassword, userToken, "dsd3231332",deviceT);
			Validation.responseHeaderCodeValidation(MultiCategorybookList,HttpStatus.SC_OK);
			Validation.responseCodeValidation1(MultiCategorybookList, HttpStatus.SC_OK);
			Validation.responseTimeValidation(MultiCategorybookList);
			Validation.responseNOTKeyValidation_key(MultiCategorybookList, "category");
			Validation.responseNOTKeyValidation_key(MultiCategorybookList, "categoryIdList");
			Validation.responseNOTKeyValidation_key(MultiCategorybookList, "categoryList");
			Validation.responseKeyValidation_key(MultiCategorybookList, "collectionID");
			Validation.responseKeyValidation_key(MultiCategorybookList, "New_ADHOC");
			System.out.println("MultiCategorybookList : "+MultiCategorybookList);

			Response multiCategoryCollectionBookList=MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel,bookID1,sqlhost,  sqlUsername,  sqlPassword,userToken,"ds32323",deviceT,collectionName0);
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
			}
		  }
		}catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}
}
