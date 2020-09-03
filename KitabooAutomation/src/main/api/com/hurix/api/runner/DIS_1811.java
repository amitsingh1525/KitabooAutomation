package com.hurix.api.runner;


import io.restassured.response.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.json.JSONException;

import com.hurix.api.externalAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class DIS_1811 {
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
	public static int epubId;
	public static String isbn;
	public static String title;
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
			String excelPath="./testData/DIS-1811.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=5;i++)
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
				Response fetchBookList_with_pagination = FetchBookList.fetchBookList_with_pagination(userToken,"45616452","IPAD");
				System.out.println("fetchBookList_with_pagination_RES : " +fetchBookList_with_pagination);
				totalbooks= fetchBookList_with_pagination.then().assertThat().extract().path("totalbooks");

				Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"45616452","IPAD");
				bookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[0]");
				System.out.println("bookID_1: "+bookID1);
				bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[1]");
				System.out.println("bookID: "+bookID2);
				title = fetchBookList_without_pagination.then().extract().path("bookList.book.title[0]");
				System.out.println("title: "+title);
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
				Response GETfetchBookCount_res = FetchBookCount.fetchBookCount(userToken,"45616452","IPAD");
				System.out.println("fetchBookCount_res : "+GETfetchBookCount_res);
				long nowEpochTime = Instant.now().toEpochMilli();

				Response UploadEpub_res = UploadEpub.uploadEpub_OAuth(consumerKey, consumerSecret,"/Thirdepub/Reflow_epub.epub","Reflow_epub_02","Reflow_epub_02","level4",""+nowEpochTime+"","Reflow_epub_02");
				System.out.println("UploadEpub_res : "+UploadEpub_res);
				epubId = UploadEpub_res.then().extract().path("epubId");
				System.out.println("epubId: "+epubId);
				//Thread.sleep(28000);
				Response EpubStatus_res = EpubStatus.epubStatus(consumerKey, consumerSecret,epubId);
				System.out.println("EpubStatus_res : "+EpubStatus_res);
				Response upDateEpub_OAuth_res = UpDateEpub.upDateEpub_OAuth(consumerKey, consumerSecret,"/Thirdepub/Reflow_epub.epub","Reflow_epub_02","Reflow_epub_02","level4",""+nowEpochTime+"","Reflow_epub_02",""+epubId+"");
				System.out.println("upDateEpub_OAuth_res : "+upDateEpub_OAuth_res);
				Response CategoryBookListV1_res = CategoryBookListV1.categoryBookListV1("level4",userToken,"56454", "IPAD");
				System.out.println("CategoryBookListV1_res : "+CategoryBookListV1_res);
				Response CategoryBookListV2_res = CategoryBookListV2.categoryBookListV2("level4",userToken,"56454", "IPAD");
				System.out.println("CategoryBookListV2_res : "+CategoryBookListV2_res);
				Response fetchBookList_without_pagination1 = FetchBookList.fetchBookList_without_pagination(userToken,"45616452","IPAD");
				System.out.println("fetchBookList_without_pagination1 :: " +fetchBookList_without_pagination1);				
				Validation.responseKeyValidation_key(fetchBookList_without_pagination1, "Reflow_epub_02");

				Response FetchFavouriteBooks_res = FetchFavouriteBooks.fetchFavouriteBooks(userToken,"8742685","IPAD");
				System.out.println("FetchFavouriteBooks_res :: "+FetchFavouriteBooks_res);
				Response Bookdetails_Res = Bookdetails.bookdetails(startDate, userToken, "5489989", "IPAD");
				System.out.println("Bookdetails_Res : " +Bookdetails_Res);				
				Response RefreshBookList_res = RefreshBookList.refreshBookList(userToken,"56454", "IPAD");
				System.out.println("RefreshBookList_res : "+RefreshBookList_res);
				Response V1refreshBookList_Res = V1refreshBookList.v1refreshBookList(startDate,"NEW","UPDATE",""+bookID1+"",""+bookID2+"",userToken,"56454", "IPAD");
				System.out.println("V1refreshBookList_Res : "+V1refreshBookList_Res);
				Validation.responseKeyValidation_key(V1refreshBookList_Res, ""+title+"");
				Response BookList_Res = BookList.bookList(userToken,"56454", "IPAD");
				System.out.println("BookList_Res : "+BookList_Res);
				Validation.responseKeyValidation_key(V1refreshBookList_Res, "Reflow_epub_02");
				Response MultiCategoryBookList_Res = MultiCategoryBookList.multiCategoryBookList(catlevel, bookID1,"jdbc:mysql://172.18.10.147:3306","readonly","readonly@123",userToken,"8545748","IPAD");
				System.out.println("MultiCategoryBookList_Res : "+MultiCategoryBookList_Res);

				Response MultiCategoryCollectionBookList_Res =MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel, bookID1, "jdbc:mysql://172.18.10.147:3306","readonly","readonly@123", userToken, "8545748", "IPAD", ""+collectionName1+"");
				Validation.responseKeyValidation_key(MultiCategoryCollectionBookList_Res, "Native_cat1_UPD");
				System.out.println("MultiCategoryCollectionBookList_Res : "+MultiCategoryCollectionBookList_Res);

				Response UserAssignedBooks_res = UserAssignedBooks_OAuth.userAssignedBooks_OAuth(consumerKey, consumerSecret,clientUserID);				
				Validation.responseKeyValidation_key(UserAssignedBooks_res, "Reflow_epub_02");
				System.out.println("UserAssigned_res : "+UserAssignedBooks_res);

			}
		}catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}

	}
}
