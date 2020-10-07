package com.hurix.api.runner;

import io.restassured.response.Response;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import com.hurix.api.externalAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class DIS_1881 {

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
	public static int client_Id;
	public static int totalbooks;
	public static int total;
	public static int epubId;
	public static String archiveDate;
	public static String archiveDate1;
	public static String archiveDate2;
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
	public static String sqlhost;
	public static String sqlUsername;
	public static String sqlPassword;
	public static String externalURI;
	public static String clientID;
	public static String catlevel;
	public static String  deviceT;
	public static int type;
	public static String isbn1;
	public static String searchV2TEXT;
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
	public static String runY_N;
	public static String filePath;
	//public static String consumerKey=ExcelUtils.Consumer_key;
	//public static String consumerSecret=ExcelUtils.secret_key;

	public static void   main(String []args) throws SQLException, JSONException{
		Log.initialization("Sprint33.1/DIS-1881");	
		//List<String> detail =  ExcelUtils.getuserDetails();
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			String excelPath="./testData/Sprint33.1/DIS-1881.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{DataFormatter formatter = new DataFormatter();
			environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			userName = formatter.formatCellValue(sheet.getRow(i).getCell(1));			
			password = formatter.formatCellValue(sheet.getRow(i).getCell(2));
			deviceT = formatter.formatCellValue(sheet.getRow(i).getCell(3));
			catlevel = formatter.formatCellValue(sheet.getRow(i).getCell(4));
			runY_N=formatter.formatCellValue(sheet.getRow(i).getCell(5));
			searchV2TEXT = formatter.formatCellValue(sheet.getRow(i).getCell(6));
			title= formatter.formatCellValue(sheet.getRow(i).getCell(7));
			filePath=formatter.formatCellValue(sheet.getRow(i).getCell(8));


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

			clientID = JDBC_category.getReader(userName, sqlhost, sqlUsername, sqlPassword);


			Log.startTestCase("Authenticate");
			Log.info("detail : "+detail);
			Log.info("userName : "+userName);
			Log.info("password : "+password);
			Log.info("clientID : "+clientID);


			//clientID =JDBC_category.getReader(userName, sqlhost, sqlUsername, sqlPassword);
			Log.info("ReaderKey : "+clientID);


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

			/*long nowEpochTime = Instant.now().toEpochMilli();
			Response  UploadEpub_res= UploadEpub.uploadEpub_OAuth(consumerKey, consumerSecret, filePath ,title+""+nowEpochTime+"",""+title+""+nowEpochTime+"","level4_upd",""+nowEpochTime+"",""+title+""+nowEpochTime+"");
			Validation.responseHeaderCodeValidation(UploadEpub_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(UploadEpub_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(UploadEpub_res);
			Validation.responseKeyValidation_key(UploadEpub_res, "The request for the uploadEpub taken successfully.");
			System.out.println("UploadEpub_res : "+UploadEpub_res);
			epubId = UploadEpub_res.then().extract().path("epubId");
			System.out.println("epubId: "+epubId);


			Thread.sleep(9000);
			Response EpubStatus_res = EpubStatus.epubStatus(consumerKey, consumerSecret,epubId);
			Validation.responseHeaderCodeValidation(EpubStatus_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(EpubStatus_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(EpubStatus_res);
			Validation.responseKeyValidation_key(EpubStatus_res, "status");
			Validation.responseKeyValidation_key(EpubStatus_res, "100");*/


			for(int i1=1; i1<=1; i1++)
			{Log.info("HERE in for_"+i1);
			isbnMeta=formatter.formatCellValue(sheet.getRow(i1).getCell(9));
			Log.info("isbnMeta : "+isbnMeta);
			Log.info("isbnMeta: "+isbnMeta);
			//String consumerKey, String consumerSecret,String string,String title,String author,String cat4
			Response Metadata_res = Metadata.metadata(consumerKey, consumerSecret,isbnMeta,"Reflow_"+isbnMeta+"","Reflow_"+isbnMeta+"",catlevel,"description");
			Validation.responseHeaderCodeValidation(Metadata_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(Metadata_res, HttpStatus.SC_OK);
			Validation.responseTimeValidation(Metadata_res);
			Validation.responseKeyValidation_key(Metadata_res, "isbn");
			Validation.responseKeyValidation_key(Metadata_res, "The request for the uploadEpub taken successfully.");
			isbnMeta = Metadata_res.then().extract().path("isbn");
			Log.info("isbnMeta: "+isbnMeta);}
			
			Response IngectEpub_res1=null;
			for(int i2=1; i2<=1;i2++)
			{String isbnURL=formatter.formatCellValue(sheet.getRow(i2).getCell(10));
			IngectEpub_res1 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
			Log.info("URL : "+detail);
			Validation.responseHeaderCodeValidation(IngectEpub_res1, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(IngectEpub_res1, HttpStatus.SC_OK);
			Validation.responseTimeValidation(IngectEpub_res1);
			Validation.responseKeyValidation_key(IngectEpub_res1, "The request for the uploadEpub taken successfully.");}
			isbnIng = IngectEpub_res1.then().extract().path("isbn");			
			Log.info("isbnIng: "+isbnIng);		
			
			
			Response IngestionStatus_res = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
			Validation.responseHeaderCodeValidation(IngestionStatus_res, HttpStatus.SC_OK);
			Validation.responseCodeValidation1(IngestionStatus_res, HttpStatus.SC_OK);
			Validation.responseINTEGERKeyAndValue(IngestionStatus_res, "status", 100);	
			System.out.println("IngestionStatus_res : "+IngestionStatus_res);
			
			
			}
			}
		} catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
	}
}

