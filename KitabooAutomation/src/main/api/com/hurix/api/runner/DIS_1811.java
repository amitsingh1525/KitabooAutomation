package com.hurix.api.runner;

import io.restassured.response.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpStatus;
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
	//"2019/10/31 14:46:04"
	public static long startDate;
	public static String excelPath;
	public static Object startIndex = 0;
	public static Object endIndex = 0;
	//public static int level;
	public static String assetType;
	public static String Title;
	public static String userToken = "";
	public static int BookID_mark1;
	public static int  bookID1;
	public static int bookID2;
	public static int epubId;
	public static String isbn;
	public static long nowEpochTime;
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
	public static String consumerKey;
	public static String consumerSecret;
	//public static String consumerKey=ExcelUtils.Consumer_key;
	//public static String consumerSecret=ExcelUtils.secret_key;

	public static void   main(String []args) throws SQLException, JSONException{
		Log.initialization("DIS-1811");	
		//List<String> detail =  ExcelUtils.getuserDetails();
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/DIS-1811.xlsx";
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

				Log.startTestCase("Authenticate");
				Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"65454","IPAD");
				Log.info("Authenticate Response: "+authenticateValue.then().extract().response().prettyPrint());
				System.out.println("HERE_Before");
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

				Response fetchBookList_with_pagination = FetchBookList.fetchBookList_with_pagination(0,10,userToken,"45616452","IPAD");
				Validation.responseCodeValidation1(fetchBookList_with_pagination, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(fetchBookList_with_pagination, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchBookList_with_pagination);
				System.out.println("fetchBookList_with_pagination_RES : " +fetchBookList_with_pagination);
				totalbooks= fetchBookList_with_pagination.then().assertThat().extract().path("totalbooks");

				Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"45616452","IPAD");
				Validation.responseCodeValidation1(fetchBookList_without_pagination, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(fetchBookList_without_pagination, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchBookList_without_pagination);
				Validation.responseKeyValidation_key(fetchBookList_without_pagination, "title");
				bookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[66]");
				System.out.println("bookID_1: "+bookID1);
				bookID2 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[67]");
				System.out.println("bookID: "+bookID2);

				int bookID3 = fetchBookList_without_pagination.then().extract().path("bookList.book.id[2]");
				System.out.println("bookID: "+bookID3);
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
				collectionName1 = fetchBookList_without_pagination.then().extract().path("bookList.book.collectionTitle[66]");
				System.out.println("collectionName1: "+collectionName1);
				catname = ExtractCategory.extractCategory(category1);
				System.out.println("catname: " +catname);
				String archiveDate = fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[66]");
				System.out.println("archiveDate:"+archiveDate);
				//String archiveDate1=fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[1]");
				//System.out.println("archiveDate:"+archiveDate);

				Response GETfetchBookCount_res = FetchBookCount.fetchBookCount(userToken,"45616452","IPAD");
				Validation.responseHeaderCodeValidation(GETfetchBookCount_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(GETfetchBookCount_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(GETfetchBookCount_res);
				Validation.responseKeyValidation_key(GETfetchBookCount_res, "totalbooks");
				System.out.println("fetchBookCount_res : "+GETfetchBookCount_res);
				nowEpochTime = Instant.now().toEpochMilli();

				String title1=fetchBookList_without_pagination.then().extract().path("bookList.book.title[0]");
				System.out.println("title: "+title1);
				String title2=fetchBookList_without_pagination.then().extract().path("bookList.book.title[1]");
				System.out.println("title: "+title2);
				String[] TITLE1 = title2.trim().split("_");
				Response searchv2 = SearchV2.searchV2(""+TITLE1[0]+"", userToken, "bdhsbdhs213131", "IPAD");
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
				Validation.responseKeyValidation_key(searchv2, "bookReferenceId");
				Validation.responseKeyValidation_key(searchv2, "_type");
				Validation.responseKeyValidation_key(searchv2, "bookId");
				Validation.responseKeyValidation_key(searchv2, "bookTitle");
				Validation.responseKeyValidation_key(searchv2, "description");
				Validation.responseKeyValidation_key(searchv2, "total");
				System.out.println("searchv2 : "+searchv2);

				searchv2 = SearchV2.searchV2("Reflow", userToken, "bdhsbdhs213131", "IPAD");
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
				Validation.responseKeyValidation_key(searchv2, "bookReferenceId");
				Validation.responseKeyValidation_key(searchv2, "_type");
				Validation.responseKeyValidation_key(searchv2, "bookId");
				Validation.responseKeyValidation_key(searchv2, "bookTitle");
				Validation.responseKeyValidation_key(searchv2, "description");
				Validation.responseKeyValidation_key(searchv2, "total");
				System.out.println("searchv2 : "+searchv2);

				String isbning=formatter.formatCellValue(sheet.getRow(i).getCell(7));
				Log.info("isbnMeta: "+isbning);

				Response metadata500ch = Metadata.metadata(consumerKey, consumerSecret,""+isbning+"","NEW_meta","NEW_meta",catlevel,"5PEt24UoprwYB2wjEBdnchZuU1XfV5paO9X8SW52OHjbsCc90ywZl9JFN5lQkNE8xGkWHo5SD4BJPzGiE88x85rHNczvrjQki9xf9H2f8LFol8i22eEkVm4EVmjLTgswIsDBvutumCLRuJplxFVKIcOp3fCJqy1B40DzDyzsSgwAmUcASHjlHOaUb8EHRrwmTgBIDzKuIXsR7Zdf4zkPzv6OXHaiAuzXlVaGIWen1scifhaWqh67B5ilLuJONCG8hU7OKqUYribHiwyr4m3SLwJT8DrTeaUAEPAWjNzD80Sax1ncKBHEdIwu0PIWKjLumLfY3X9goyFKANhZX1lfmZmjxXKmGBk3Skuffgl1Tmiy92jX9jIpMN4Kd1OW4U9Hf916XAJEFnEy6qzN5bx2tkGp4Q2xZ24IrMwoEGqkxhYTyRoLS9M6v3oSDdx6Hn8cp2Flmxoij4J69rLAj5OuyjWjj1pKCos8LZYWTt7aJvWBknIT8MGz");
				Validation.responseHeaderCodeValidation(metadata500ch, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(metadata500ch, HttpStatus.SC_OK);
				Validation.responseTimeValidation(metadata500ch);
				System.out.println("metadata : "+metadata500ch);

				Response ingestEpub = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret, "https://hurix-staging-content.s3.amazonaws.com/test/2424242424285.epub?AWSAccessKeyId=AKIA4PI2NOPJV6BLDLWG&Expires=1605547188&Signature=FEjnkZxVdRTgPZPVLUFglsGlcFc%3D");
				Validation.responseHeaderCodeValidation(ingestEpub, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(ingestEpub, HttpStatus.SC_OK);
				Validation.responseTimeValidation(ingestEpub);
				System.out.println("ingestEpub : "+ingestEpub);

				Thread.sleep(9000);
				Response ingestStatus = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, ""+isbning+"");
				Validation.responseHeaderCodeValidation(ingestStatus, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(ingestStatus, HttpStatus.SC_OK);
				Validation.responseTimeValidation(ingestStatus);
				System.out.println("ingestEpub : "+ingestStatus);

				Response updatemetadata= UpdateMetadata.updateMetadata(consumerKey, consumerSecret, ""+isbning+"", "NEW_meta_UPD","NEW_meta_UPD",catlevel,"5PEt24UoprwYB2wjEBdnchZuU1XfV5paO9X8SW52OHjbsCc90ywZl9JFN5lQkNE8xGkWHo5SD4BJPzGiE88x85rHNczvrjQki9xf9H2f8LFol8i22eEkVm4EVmjLTgswIsDBvutumCLRuJplxFVKIcOp3fCJqy1B40DzDyzsSgwAmUcASHjlHOaUb8EHRrwmTgBIDzKuIXsR7Zdf4zkPzv6OXHaiAuzXlVaGIWen1scifhaWqh67B5ilLuJONCG8hU7OKqUYribHiwyr4m3SLwJT8DrTeaUAEPAWjNzD80Sax1ncKBHEdIwu0PIWKjLumLfY3X9goyFKANhZX1lfmZmjxXKmGBk3Skuffgl1Tmiy92jX9jIpMN4Kd1OW4U9Hf916XAJEFnEy6qzN5bx2tkGp4Q2xZ24IrMwoEGqkxhYTyRoLS9M6v3oSDdx6Hn8cp2Flmxoij4J69rLAj5OuyjWjj1pKCos8LZYWTt7aJvWBknIT8MGz");
				Validation.responseHeaderCodeValidation(updatemetadata, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(updatemetadata, HttpStatus.SC_OK);
				Validation.responseTimeValidation(updatemetadata);
				System.out.println("updatemetadata : "+updatemetadata);

				ingestEpub = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret, "https://hurix-staging-content.s3.amazonaws.com/test/2424242424285.epub?AWSAccessKeyId=AKIA4PI2NOPJV6BLDLWG&Expires=1605547188&Signature=FEjnkZxVdRTgPZPVLUFglsGlcFc%3D");
				Validation.responseHeaderCodeValidation(ingestEpub, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(ingestEpub, HttpStatus.SC_OK);
				Validation.responseTimeValidation(ingestEpub);
				System.out.println("ingestEpub : "+ingestEpub);

				Thread.sleep(9000);
				Response ingestStatus1 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, ""+isbning+"");
				Validation.responseHeaderCodeValidation(ingestStatus1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(ingestStatus1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(ingestStatus1);
				System.out.println("ingestEpub : "+ingestStatus1);

				Response UploadEpub_resSpecial3 = UploadEpub.uploadEpub_OAuth(consumerKey, consumerSecret,"/Thirdepub/JMeterTesting.epub","Reflow_special","Reflow_special","level4||level3||level2",""+nowEpochTime+"","@#$%$#^&@*(#*$&^#&@*##$%^*((");
				epubId = UploadEpub_resSpecial3.then().extract().path("epubId");
				System.out.println("epubId: "+epubId);
				Thread.sleep(40000);
				Response EpubStatus_res13 = EpubStatus.epubStatus(consumerKey, consumerSecret,epubId);
				Validation.responseHeaderCodeValidation(UploadEpub_resSpecial3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(EpubStatus_res13, HttpStatus.SC_OK);
				Validation.responseTimeValidation(EpubStatus_res13);
				Validation.responseKeyValidation_key(EpubStatus_res13, "status");
				Validation.responseKeyValidation_key(EpubStatus_res13, "100");
				System.out.println("EpubStatus_res : "+EpubStatus_res13);

				Response UploadEpub_500more = UploadEpub.uploadEpub_OAuth(consumerKey, consumerSecret,"/Thirdepub/JMeterTesting.epub","Reflow_500M","Reflow_500M","level4||level3||level2",""+nowEpochTime+"","5PEt24UoprwYB2wjEBdnchZuU1XfV5paO9X8SW52OHjbsCc90ywZl9JFN5lQkNE8xGkWHo5SD4BJPzGiE88x85rHNczvrjQki9xf9H2f8LFol8i22eEkVm4EVmjLTgswIsDBvutumCLRuJplxFVKIcOp3fCJqy1B40DzDyzsSgwAmUcASHjlHOaUb8EHRrwmTgBIDzKuIXsR7Zdf4zkPzv6OXHaiAuzXlVaGIWen1scifhaWqh67B5ilLuJONCG8hU7OKqUYribHiwyr4m3SLwJT8DrTeaUAEPAWjNzD80Sax1ncKBHEdIwu0PIWKjLumLfY3X9goyFKANhZX1lfmZmjxXKmGBk3Skuffgl1Tmiy92jX9jIpMN4Kd1OW4U9Hf916XAJEFnEy6qzN5bx2tkGp4Q2xZ24IrMwoEGqkxhYTyRoLS9M6v3oSDdx6Hn8cp2Flmxoij4J69rLAj5OuyjWjj1pKCos8LZYWTt7aJvWBknIT8MGz537");
				Validation.responseHeaderCodeValidation(UploadEpub_500more, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(UploadEpub_500more, HttpStatus.SC_BAD_REQUEST);
				Validation.responseTimeValidation(UploadEpub_500more);
				Validation.responseINTEGERKeyAndValue(UploadEpub_500more, "Description", 101);
				Validation.responseINTEGERKeyAndValue(UploadEpub_500more, "Mode", 100);
				System.out.println("UploadEpub_res2 : "+UploadEpub_500more);


				Response UploadEpub_res500ch = UploadEpub.uploadEpub_OAuth(consumerKey, consumerSecret,"/Thirdepub/JMeterTesting.epub","Reflow_500","Reflow_500","level4||level3||level2",""+nowEpochTime+"","5PEt24UoprwYB2wjEBdnchZuU1XfV5paO9X8SW52OHjbsCc90ywZl9JFN5lQkNE8xGkWHo5SD4BJPzGiE88x85rHNczvrjQki9xf9H2f8LFol8i22eEkVm4EVmjLTgswIsDBvutumCLRuJplxFVKIcOp3fCJqy1B40DzDyzsSgwAmUcASHjlHOaUb8EHRrwmTgBIDzKuIXsR7Zdf4zkPzv6OXHaiAuzXlVaGIWen1scifhaWqh67B5ilLuJONCG8hU7OKqUYribHiwyr4m3SLwJT8DrTeaUAEPAWjNzD80Sax1ncKBHEdIwu0PIWKjLumLfY3X9goyFKANhZX1lfmZmjxXKmGBk3Skuffgl1Tmiy92jX9jIpMN4Kd1OW4U9Hf916XAJEFnEy6qzN5bx2tkGp4Q2xZ24IrMwoEGqkxhYTyRoLS9M6v3oSDdx6Hn8cp2Flmxoij4J69rLAj5OuyjWjj1pKCos8LZYWTt7aJvWBknIT8MGz");
				epubId = UploadEpub_res500ch.then().extract().path("epubId");
				System.out.println("epubId: "+epubId);
				Thread.sleep(40000);
				Response EpubStatus_res1 = EpubStatus.epubStatus(consumerKey, consumerSecret,epubId);
				Validation.responseHeaderCodeValidation(EpubStatus_res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(EpubStatus_res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(EpubStatus_res1);
				Validation.responseKeyValidation_key(EpubStatus_res1, "status");
				Validation.responseKeyValidation_key(EpubStatus_res1, "100");
				System.out.println("EpubStatus_res : "+EpubStatus_res1);

				Title ="Reflow_epub_"+nowEpochTime+"";
				Log.info("Title : " +Title);
				Response UploadEpub_res = UploadEpub.uploadEpub_OAuth(consumerKey, consumerSecret,"/Thirdepub/JMeterTesting.epub","Reflow_500","Reflow_500","level4||level3||level2",""+nowEpochTime+"","Reflow_500");
				Validation.responseHeaderCodeValidation(UploadEpub_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(UploadEpub_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(UploadEpub_res);
				Validation.responseKeyValidation_key(UploadEpub_res, "The request for the uploadEpub taken successfully.");
				System.out.println("UploadEpub_res : "+UploadEpub_res);
				epubId = UploadEpub_res.then().extract().path("epubId");
				System.out.println("epubId: "+epubId);


				Thread.sleep(40000);
				Response EpubStatus_res = EpubStatus.epubStatus(consumerKey, consumerSecret,epubId);
				Validation.responseHeaderCodeValidation(EpubStatus_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(EpubStatus_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(EpubStatus_res);
				Validation.responseKeyValidation_key(EpubStatus_res, "status");
				Validation.responseKeyValidation_key(EpubStatus_res, "100");
				System.out.println("EpubStatus_res : "+EpubStatus_res);
				//Log.info(Validation.responseCodeValidation1(EpubStatus_res, HttpStatus.SC_OK));

				String title12112 = "Reflow_epub_UPD_"+nowEpochTime+"";
				Response upDateEpub_res = UpDateEpub.upDateEpub_OAuth(consumerKey, consumerSecret,"/Thirdepub/Reflow_epub.epub",title12112,"Reflow_epub_02","level4_upd||level3_upd||level2_upd",""+nowEpochTime+"","Reflow_epub_"+nowEpochTime+"",""+epubId+"");
				//String updateTitle="Reflow_epub_UPD_"+nowEpochTime+"";
				Validation.responseHeaderCodeValidation(upDateEpub_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(upDateEpub_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(upDateEpub_res);
				Validation.responseKeyValidation_key(upDateEpub_res, "Epub updated successfully.");
				System.out.println("upDateEpub_res : "+upDateEpub_res);


				Thread.sleep(9000);
				String splitStr = title12112.substring(0,15);
				Response fetchBookList_without_pagination_again = FetchBookList.fetchBookList_without_pagination(userToken,"45616452","IPAD");
				Validation.responseKeyValidation_key(fetchBookList_without_pagination_again,""+splitStr+"");
				Validation.responseCodeValidation1(fetchBookList_without_pagination_again, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(fetchBookList_without_pagination_again,HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchBookList_without_pagination_again);
				Validation.responseKeyValidation_key(fetchBookList_without_pagination_again,"level4");
				System.out.println("fetchBookList_without_pagination_again :: " +fetchBookList_without_pagination_again);	

				String sqlhost="jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com";
				String sqlUsername="qcteam";
				String sqlPassword="JB88F-WT2Q3-DPXTT";		
				Response CategoryBookListV1s = CategoryBookListV1.categoryBookListV1_cat("level2_upd",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(CategoryBookListV1s, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(CategoryBookListV1s, HttpStatus.SC_OK);
				Validation.responseTimeValidation(CategoryBookListV1s);
				Validation.responseKeyValidation_key(CategoryBookListV1s,""+splitStr+"");
				Validation.responseKeyValidation_key(CategoryBookListV1s,"category");
				Validation.responseKeyValidation_key(CategoryBookListV1s,"level2_upd");
				Validation.responseKeyValidation_key(CategoryBookListV1s,"isbn");
				Validation.responseKeyValidation_key(CategoryBookListV1s,"id");
				Validation.responseKeyValidation_key(CategoryBookListV1s,"title");
				Validation.responseKeyValidation_key(CategoryBookListV1s,"description");				
				System.out.println("CategoryBookListV1_res : "+CategoryBookListV1s);



				Response CategoryBook_ListV2 = CategoryBookListV2.categoryBookListV2_cat("level2_upd",userToken,"56454", "IPAD",bookID1,catlevel,sqlhost,sqlUsername,sqlPassword);
				Validation.responseHeaderCodeValidation(CategoryBook_ListV2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(CategoryBook_ListV2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(CategoryBook_ListV2);
				Validation.responseKeyValidation_key(CategoryBook_ListV2,""+splitStr+"");
				Validation.responseKeyValidation_key(CategoryBook_ListV2,"category");
				Validation.responseKeyValidation_key(CategoryBook_ListV2,"isbn");
				Validation.responseKeyValidation_key(CategoryBook_ListV2,"id");
				Validation.responseKeyValidation_key(CategoryBook_ListV2,"title");
				Validation.responseKeyValidation_key(CategoryBook_ListV2,"description");	
				System.out.println("CategoryBookListV2_res : "+CategoryBook_ListV2);


				Response markAsFav= MarkAsFavourite.markAsFavourite(bookID1,userToken,"8742685","IPAD");
				Validation.responseHeaderCodeValidation(markAsFav, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(markAsFav, HttpStatus.SC_OK);
				Validation.responseTimeValidation(markAsFav);
				Validation.responseKeyValidation_key(markAsFav, "OK");
				System.out.println("markAsFav : "+markAsFav);

				Response FetchFavouriteBooks_res = FetchFavouriteBooks.fetchFavouriteBooks(userToken,"8742685","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res);
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, "OK");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res, ""+bookID1+"");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res,"description");
				Validation.responseKeyValidation_key(FetchFavouriteBooks_res,""+splitStr+"");
				System.out.println("FetchFavouriteBooks_res :: "+FetchFavouriteBooks_res);

				Response unMarkFav=UnMarkAsFavourite.unMarkAsFavourite(bookID1,userToken,"8742685","IPAD");
				Validation.responseHeaderCodeValidation(unMarkFav, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(unMarkFav, HttpStatus.SC_OK);
				Validation.responseTimeValidation(unMarkFav);
				//Validation.responseKeyValidation_key(unMarkFav, "isbn");
				System.out.println("unMarkFav : "+unMarkFav);


				Response FetchFavouriteBooks_res1 = FetchFavouriteBooks.fetchFavouriteBooks(userToken,"8742685","IPAD");
				Validation.responseHeaderCodeValidation(FetchFavouriteBooks_res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(FetchFavouriteBooks_res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(FetchFavouriteBooks_res1);
				Validation.responseNOTKeyValidation_key(FetchFavouriteBooks_res1, ""+bookID1+"");
				System.out.println("FetchFavouriteBooks_res :: "+FetchFavouriteBooks_res1);


				//startDate=EpochTime.getEpochTime(""+startDate+"");
				ebookID1 = fetchBookList_without_pagination.then().extract().path("bookList.book.ebookID[66]");
				Log.info("ebookID1: "+ebookID1);
				archiveDate=fetchBookList_without_pagination.then().extract().path("bookList.book.archiveDate[66]");
				Log.info("archiveDate: "+archiveDate);
				assetType = fetchBookList_without_pagination.then().extract().path("bookList.book.assetType[66]");
				Log.info("assetType: "+assetType);


				Response Bookdetails_Res = Bookdetails.bookdetails(""+archiveDate+"", userToken, "5489989","IPAD",bookID1,""+assetType+"");
				Validation.responseHeaderCodeValidation(Bookdetails_Res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Bookdetails_Res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Bookdetails_Res);
				Validation.responseKeyValidation_key(Bookdetails_Res, "isbn");
				Validation.responseKeyValidation_key(Bookdetails_Res, "bookCode");
				Validation.responseKeyValidation_key(Bookdetails_Res, "description");
				Validation.responseKeyValidation_key(Bookdetails_Res, ""+splitStr+"");
				System.out.println("Bookdetails_Res : " +Bookdetails_Res);

				Response Refresh_BookList = RefreshBookList.refreshBookList(userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(Refresh_BookList, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Refresh_BookList, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Refresh_BookList);
				Validation.responseKeyValidation_key(Refresh_BookList,""+splitStr+"");
				Validation.responseKeyValidation_key(Refresh_BookList,"category");
				Validation.responseKeyValidation_key(Refresh_BookList,"isbn");
				Validation.responseKeyValidation_key(Refresh_BookList,"id");
				Validation.responseKeyValidation_key(Refresh_BookList,"title");
				Validation.responseKeyValidation_key(Refresh_BookList,"description");	
				System.out.println("RefreshBookList_res : "+Refresh_BookList);

				Response V1refresh_BookList = V1refreshBookList.v1refreshBookList(""+archiveDate+"","NEW","UPDATE",""+bookID1+"",""+bookID2+"",userToken,"56454", "IPAD",clientID);
				Validation.responseHeaderCodeValidation(V1refresh_BookList, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(V1refresh_BookList, HttpStatus.SC_OK);
				Validation.responseTimeValidation(V1refresh_BookList);
				Validation.responseKeyValidation_key(V1refresh_BookList,""+splitStr+"");
				Validation.responseKeyValidation_key(V1refresh_BookList,"category");
				Validation.responseKeyValidation_key(V1refresh_BookList,"isbn");
				Validation.responseKeyValidation_key(V1refresh_BookList,"id");
				Validation.responseKeyValidation_key(V1refresh_BookList,"title");
				Validation.responseKeyValidation_key(V1refresh_BookList,"description");	
				Validation.responseKeyValidation_key(V1refresh_BookList, ""+splitStr+"");
				System.out.println("V1refreshBookList_Res : "+V1refresh_BookList);

				Response downloadBookForANDROID_offline = DownloadBook.downloadBook(userToken,"ds9465","FIXED_EPUB_IMAGE",bookID1,"offline");
				Validation.responseCodeValidation1(downloadBookForANDROID_offline, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForANDROID_offline);
				Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForANDROID_offline,"timestamp");
				System.out.println("downloadBookForANDROID_offline: "+downloadBookForANDROID_offline);


				Response downloadBookForANDROID_online = DownloadBook.downloadBook(userToken,"ds9465","FIXED_EPUB_IMAGE",bookID1,"online");
				Validation.responseHeaderCodeValidation(downloadBookForANDROID_online, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForANDROID_online, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForANDROID_online);
				Validation.responseKeyValidation_key(downloadBookForANDROID_online,"fileSize");
				Validation.responseKeyValidation_key(downloadBookForANDROID_online,"responseMsg");
				Validation.responseKeyValidation_key(downloadBookForANDROID_online,"timestamp");
				System.out.println("downloadBookForANDROID_online: "+downloadBookForANDROID_online);

				Response bulkDownlaod =BulkDownloadBook.bulkDownloadBook(userToken, "463b43n4v3", "FIXED_EPUB_IMAGE", bookID1, bookID2, bookID3, "offline");
				Validation.responseHeaderCodeValidation(downloadBookForANDROID_online, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(downloadBookForANDROID_online, HttpStatus.SC_OK);
				Validation.responseTimeValidation(downloadBookForANDROID_online);
				System.out.println("bulkDonlaod : "+bulkDownlaod);


				Response BookList_Res = BookList.bookList(userToken,"56454", "IPAD");
				Validation.responseHeaderCodeValidation(BookList_Res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(BookList_Res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(BookList_Res);
				Validation.responseKeyValidation_key(BookList_Res,""+splitStr+"");
				Validation.responseKeyValidation_key(BookList_Res,"category");
				Validation.responseKeyValidation_key(BookList_Res,"isbn");
				Validation.responseKeyValidation_key(BookList_Res,"id");
				Validation.responseKeyValidation_key(BookList_Res,"title");
				Validation.responseKeyValidation_key(BookList_Res,"description");	
				Validation.responseKeyValidation_key(BookList_Res, "Reflow_epub_02");
				System.out.println("BookList_Res : "+BookList_Res);


				Response MultiCategory_BookList = MultiCategoryBookList.multiCategoryBookList(catlevel, bookID1,sqlhost,sqlUsername,sqlPassword,userToken,"8545748","IPAD");
				Validation.responseHeaderCodeValidation(MultiCategory_BookList, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategory_BookList, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategory_BookList);
				Validation.responseKeyValidation_key(MultiCategory_BookList,""+splitStr+"");
				Validation.responseKeyValidation_key(MultiCategory_BookList,"category");
				Validation.responseKeyValidation_key(MultiCategory_BookList,"isbn");
				Validation.responseKeyValidation_key(MultiCategory_BookList,"id");
				Validation.responseKeyValidation_key(MultiCategory_BookList,"title");
				Validation.responseKeyValidation_key(MultiCategory_BookList,"description");	
				Validation.responseKeyValidation_key(MultiCategory_BookList, "Reflow_epub_02");
				System.out.println("MultiCategoryBookList_Res : "+MultiCategory_BookList);

				Response MultiCategory_CollectionBookList = MultiCategoryCollectionBookList.multiCategoryCollectionBookList(catlevel, bookID1, sqlhost,sqlUsername,sqlPassword, userToken, "8545748", "IPAD", ""+collectionName1+"");
				Validation.responseHeaderCodeValidation(MultiCategory_CollectionBookList, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(MultiCategory_CollectionBookList, HttpStatus.SC_OK);
				Validation.responseTimeValidation(MultiCategory_CollectionBookList);
				Validation.responseKeyValidation_key(MultiCategory_CollectionBookList, "totalbooks");
				Validation.responseKeyValidation_key(MultiCategory_CollectionBookList, "category");
				Validation.responseKeyValidation_key(MultiCategory_CollectionBookList, "description");
				Validation.responseKeyValidation_key(MultiCategory_CollectionBookList, ""+splitStr+"");
				System.out.println("MultiCategoryCollectionBookList_Res : "+MultiCategory_CollectionBookList);

				Response UserAssigned_Books = UserAssignedBooks_OAuth.userAssignedBooks_OAuth(consumerKey, consumerSecret,clientUserID);
				Log.info("clientUserID : "+clientUserID);
				Validation.responseHeaderCodeValidation(UserAssigned_Books, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(UserAssigned_Books, HttpStatus.SC_OK);
				Validation.responseTimeValidation(UserAssigned_Books);
				Validation.responseKeyValidation_key(UserAssigned_Books,""+splitStr+"");
				Validation.responseKeyValidation_key(UserAssigned_Books, "isbn");
				Validation.responseKeyValidation_key(UserAssigned_Books, "formats");
				Validation.responseKeyValidation_key(UserAssigned_Books, "description");
				Validation.responseKeyValidation_key(UserAssigned_Books, "Reflow_epub_UPD");
				System.out.println("UserAssigned_res : "+UserAssigned_Books);




			}
		}catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}

	}
}
