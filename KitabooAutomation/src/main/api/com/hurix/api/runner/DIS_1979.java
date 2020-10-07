package com.hurix.api.runner;

import io.restassured.response.Response;

import java.time.Instant;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hurix.api.externalAPIs.*;
import com.hurix.api.readerAPIs.Authenticate;
import com.hurix.api.readerAPIs.FetchBookList;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.*;

public class DIS_1979 {
	public static List<String> detailisbn =  ExcelUtils.getisbn();
	public static String consumerKey;
	public static String consumerSecret;
	public static String excelPath;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static String environMent;
	public static String userName;
	public static String password;
	public static String detail;
	public static String clientID;
	public static String catlevel;
	public static String title;
	public static long nowEpochTime;
	public static String Title;
	public static int epubId;
	public static String isbnMeta;
	public static String isbnIng;
	public static String sqlhost;
	public static String sqlUsername;
	public static String sqlPassword;

	public static void main(String[] args) {
		//Log.initialization("APITesting");//DIS-1979	
		Log.initialization("DIS-1979");
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/DIS-1979.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=1;i++)
			{DataFormatter formatter = new DataFormatter();
			environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			userName = formatter.formatCellValue(sheet.getRow(i).getCell(1));			
			password = formatter.formatCellValue(sheet.getRow(i).getCell(2));				
			catlevel = formatter.formatCellValue(sheet.getRow(i).getCell(3));
			String deviceT = formatter.formatCellValue(sheet.getRow(i).getCell(4));
			String runY_N = formatter.formatCellValue(sheet.getRow(i).getCell(5));
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
				/*BrowserConfigure.browserConfigure("Chrome");
				Driver.driver.navigate().to("https://qc.kitaboo.com/home.xhtml");
				LoginModule.loginScenario("automation.test1@yopmail.com", "kitaboo!123");
				String title = KitabooBooksModule.bookCreationPDF("hello", "World", "level2", "For automation Testing", "Hello, World");
				KitabooBooksModule.bookPublishAndArchivePDF(title);*/
				clientID =JDBC_Queries.getReader(userName, sqlhost, sqlUsername, sqlPassword);
				Log.startTestCase("Authenticate");
				Log.info("detail : "+detail);
				Log.info("userName : "+userName);
				Log.info("password : "+password);
				Log.info("ReaderKey : "+clientID);
				Response authenticateValue = Authenticate.authenticate(clientID, userName, password,"514185",deviceT);
				Log.info("Authenticate Response: "+authenticateValue.then().extract().response().prettyPrint());				
				Log.info("HERE_Before");
				Log.info("clientID : "+clientID);
				Validation.responseHeaderCodeValidation(authenticateValue, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(authenticateValue, HttpStatus.SC_OK);
				Validation.responseTimeValidation(authenticateValue);
				Validation.responseKeyValidation_key(authenticateValue, "userName");
				Validation.responseKeyValidation_key(authenticateValue, userName);
				Log.info("HERE_After");
				System.out.println("here");
				userName = authenticateValue.then().extract().path("user.userName");
				int userID = authenticateValue.then().extract().path("user.id");
				Log.info("userID: "+userID);
				String userToken = authenticateValue.then().extract().path("userToken");
				Log.info("userToken:"+userToken);
				String clientUserID = authenticateValue.then().extract().path("user.clientUserID");
				Log.info("clientUserID:"+clientUserID);
				int client_Id = authenticateValue.then().extract().path("user.clientID");
				Log.info("client_Id:"+client_Id);
				Log.endTestCase("End");

								
				consumerKey = JDBC_Queries.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
				consumerSecret =JDBC_Queries.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);
				
				
				Log.info("URL : "+detail);
				nowEpochTime = Instant.now().toEpochMilli();
				Title ="Reflow_epub_"+nowEpochTime+"";
				Log.info("Title : " +Title);
				Response UploadEpub_res = UploadEpub.uploadEpub_OAuth(consumerKey, consumerSecret,"/Thirdepub/JMeterTesting.epub",""+Title+"",""+Title+"","level4_upd",""+nowEpochTime+"",""+Title+"");
				Validation.responseHeaderCodeValidation(UploadEpub_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(UploadEpub_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(UploadEpub_res);
				Validation.responseKeyValidation_key(UploadEpub_res, "The request for the uploadEpub taken successfully.");
				epubId = UploadEpub_res.then().extract().path("epubId");
				Log.info("epubId: "+epubId);


				Thread.sleep(9000);
				Response EpubStatus_res = EpubStatus.epubStatus(consumerKey, consumerSecret,epubId);
				Validation.responseHeaderCodeValidation(EpubStatus_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(EpubStatus_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(EpubStatus_res);
				Validation.responseKeyValidation_key(EpubStatus_res, "status");
				Validation.responseKeyValidation_key(EpubStatus_res, "100");
				//Log.info(Validation.responseCodeValidation1(EpubStatus_res, HttpStatus.SC_OK));
				//2 qand 3 success

				/*for(int i1=0;i1<=9;i1++)
				{//isbnMeta=formatter.formatCellValue(sheet.getRow(5).getCell(i1));					
					isbnMeta=formatter.formatCellValue(sheet.getRow(3).getCell(i1));
					System.out.println("isbnMeta: "+isbnMeta);
					Log.info("isbnMeta: "+isbnMeta);
					//String consumerKey, String consumerSecret,String string,String title,String author,String cat4
					Response Metadata_res = Metadata.metadata(consumerKey, consumerSecret,isbnMeta,"Reflow_"+isbnMeta+"","Reflow_"+isbnMeta+"",catlevel,"description");
					Validation.responseHeaderCodeValidation(Metadata_res, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(Metadata_res, HttpStatus.SC_OK);
					Validation.responseTimeValidation(Metadata_res);
					Validation.responseKeyValidation_key(Metadata_res, "isbn");
					Validation.responseKeyValidation_key(Metadata_res, "The request for the uploadEpub taken successfully.");
					System.out.println("Metadata_res : "+Metadata_res);
					isbnMeta = Metadata_res.then().extract().path("isbn");
					System.out.println("isbnMeta: "+isbnMeta);
					//isbnMeta=detailisbn.get(i1);
					//isbnMeta=formatter.formatCellValue(sheet.getRow(6).getCell(i1));
				}*/
				

				/*isbnMeta=formatter.formatCellValue(sheet.getRow(2).getCell(i));
				Response Metadata_res = Metadata.metadata(consumerKey, consumerSecret,isbnMeta,"Reflow_"+isbnMeta+"","Reflow_"+isbnMeta+"",catlevel,"description");
				Validation.responseHeaderCodeValidation(Metadata_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Metadata_res, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Metadata_res);
				Validation.responseKeyValidation_key(Metadata_res, "isbn");
				Validation.responseKeyValidation_key(Metadata_res, "The request for the uploadEpub taken successfully.");
				System.out.println("Metadata_res : "+Metadata_res);
				isbnMeta = Metadata_res.then().extract().path("isbn");
				System.out.println("isbnMeta: "+isbnMeta);
				//isbnMeta=detailisbn.get(i1);*/


				String isbnURL=formatter.formatCellValue(sheet.getRow(4).getCell(0));
				Log.info("isbnURL : "+isbnURL);				
				Response IngectEpub_res1 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Log.info("URL : "+detail);
				Validation.responseHeaderCodeValidation(IngectEpub_res1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res1);
				Validation.responseKeyValidation_key(IngectEpub_res1, "The request for the uploadEpub taken successfully.");
				

				isbnURL=formatter.formatCellValue(sheet.getRow(4).getCell(1));
				Response IngectEpub_res2 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Log.info("URL : "+detail);
				Validation.responseHeaderCodeValidation(IngectEpub_res2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res2, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res2);
				Validation.responseKeyValidation_key(IngectEpub_res2, "The request for the uploadEpub taken successfully.");
				
				
				isbnURL=formatter.formatCellValue(sheet.getRow(4).getCell(2));
				Response IngectEpub_res3 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Log.info("URL : "+detail);
				Validation.responseHeaderCodeValidation(IngectEpub_res3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res3, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res3);
				Validation.responseKeyValidation_key(IngectEpub_res3, "The request for the uploadEpub taken successfully.");

				isbnURL=formatter.formatCellValue(sheet.getRow(4).getCell(3));
				Response IngectEpub_res4 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Log.info("URL : "+detail);
				Validation.responseHeaderCodeValidation(IngectEpub_res4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res4, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res4);
				Validation.responseKeyValidation_key(IngectEpub_res4, "The request for the uploadEpub taken successfully.");

				isbnURL=formatter.formatCellValue(sheet.getRow(4).getCell(4));
				Response IngectEpub_res5 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Log.info("URL : "+detail);
				Validation.responseHeaderCodeValidation(IngectEpub_res5, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res5, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res5);
				Validation.responseKeyValidation_key(IngectEpub_res5, "The request for the uploadEpub taken successfully.");

				isbnURL=formatter.formatCellValue(sheet.getRow(4).getCell(5));
				Response IngectEpub_res6 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Log.info("URL : "+detail);
				Validation.responseHeaderCodeValidation(IngectEpub_res6, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res6, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res6);
				Validation.responseKeyValidation_key(IngectEpub_res6, "The request for the uploadEpub taken successfully.");

				
				isbnURL=formatter.formatCellValue(sheet.getRow(4).getCell(6));
				Response IngectEpub_res7 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Log.info("URL : "+detail);
				Validation.responseHeaderCodeValidation(IngectEpub_res7, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res7, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res7);
				Validation.responseKeyValidation_key(IngectEpub_res7, "The request for the uploadEpub taken successfully.");

				
				isbnURL=formatter.formatCellValue(sheet.getRow(4).getCell(7));
				Response IngectEpub_res8= IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Log.info("URL : "+detail);
				Validation.responseHeaderCodeValidation(IngectEpub_res8, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res8, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res8);
				Validation.responseKeyValidation_key(IngectEpub_res8, "The request for the uploadEpub taken successfully.");

				
				isbnURL=formatter.formatCellValue(sheet.getRow(4).getCell(8));
				Response IngectEpub_res9 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Log.info("URL : "+detail);
				Validation.responseHeaderCodeValidation(IngectEpub_res9, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res9, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res9);
				Validation.responseKeyValidation_key(IngectEpub_res9, "The request for the uploadEpub taken successfully.");

				
				isbnURL=formatter.formatCellValue(sheet.getRow(4).getCell(9));
				Response IngectEpub_res10 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Log.info("URL : "+detail);
				Validation.responseHeaderCodeValidation(IngectEpub_res10, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res10, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res10);
				Validation.responseKeyValidation_key(IngectEpub_res10, "The request for the uploadEpub taken successfully.");
				
				
				/*isbnURL=formatter.formatCellValue(sheet.getRow(4).getCell(10));
				Response IngectEpub_res11 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Validation.responseHeaderCodeValidation(IngectEpub_res11, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res11, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res11);
				Validation.responseKeyValidation_key(IngectEpub_res11, "The request for the uploadEpub taken successfully.");
				System.out.println("IngectEpub_res : "+IngectEpub_res11);*/

				Thread.sleep(9000);
				isbnIng = IngectEpub_res1.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(4000);
				Response IngestionStatus_res = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				Validation.responseHeaderCodeValidation(IngestionStatus_res, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngestionStatus_res, HttpStatus.SC_OK);
				Validation.responseINTEGERKeyAndValue(IngestionStatus_res, "status", 100);	
				
				
				Response updatemeta = UpdateMetadata.updateMetadata(consumerKey, consumerSecret,isbnIng,"Reflow_"+isbnIng+"_UPD","Reflow_"+isbnIng+"_UPD",catlevel,"description");
				Validation.responseHeaderCodeValidation(updatemeta, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(updatemeta, HttpStatus.SC_OK);
				Validation.responseKeyValidation_Str(updatemeta, "The request for the update ePub metadata completed successfully.");

				isbnIng = IngectEpub_res2.then().extract().path("isbn");			
				Log.info("isbnIng: "+isbnIng);
				Thread.sleep(4000);
				Response IngestionStatus_res2 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				Validation.responseHeaderCodeValidation(IngestionStatus_res2, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngestionStatus_res2, HttpStatus.SC_OK);
				Validation.responseINTEGERKeyAndValue(IngestionStatus_res2, "status", 100);					
			

				updatemeta = UpdateMetadata.updateMetadata(consumerKey, consumerSecret,isbnIng,"Reflow_"+isbnIng+"_UPD","Reflow_"+isbnIng+"_UPD",catlevel,"description");
				Validation.responseHeaderCodeValidation(updatemeta, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(updatemeta, HttpStatus.SC_OK);
				Validation.responseKeyValidation_Str(updatemeta, "The request for the update ePub metadata completed successfully.");
				

				isbnIng = IngectEpub_res3.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(4000);
				Response IngestionStatus_res3 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				Validation.responseHeaderCodeValidation(IngestionStatus_res3, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngestionStatus_res3, HttpStatus.SC_OK);
				Validation.responseINTEGERKeyAndValue(IngestionStatus_res3, "status", 100);	
				

				updatemeta = UpdateMetadata.updateMetadata(consumerKey, consumerSecret,isbnIng,"Reflow_"+isbnIng+"_UPD","Reflow_"+isbnIng+"_UPD",catlevel,"description");
				Validation.responseHeaderCodeValidation(updatemeta, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(updatemeta, HttpStatus.SC_OK);
				Validation.responseKeyValidation_Str(updatemeta, "The request for the update ePub metadata completed successfully.");
				

				isbnIng = IngectEpub_res4.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(4000);
				Response IngestionStatus_res4 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				Validation.responseHeaderCodeValidation(IngestionStatus_res4, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngestionStatus_res4, HttpStatus.SC_OK);
				Validation.responseINTEGERKeyAndValue(IngestionStatus_res4, "status", 100);	
			
				
				updatemeta = UpdateMetadata.updateMetadata(consumerKey, consumerSecret,isbnIng,"Reflow_"+isbnIng+"_UPD","Reflow_"+isbnIng+"_UPD",catlevel,"description");
				Validation.responseHeaderCodeValidation(updatemeta, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(updatemeta, HttpStatus.SC_OK);
				Validation.responseKeyValidation_Str(updatemeta, "The request for the update ePub metadata completed successfully.");


				isbnIng = IngectEpub_res5.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(4000);
				Response IngestionStatus_res5 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				Validation.responseHeaderCodeValidation(IngestionStatus_res5, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngestionStatus_res5, HttpStatus.SC_OK);
				Validation.responseINTEGERKeyAndValue(IngestionStatus_res5, "status", 100);	
				
				
				updatemeta = UpdateMetadata.updateMetadata(consumerKey, consumerSecret,isbnIng,"Reflow_"+isbnIng+"_UPD","Reflow_"+isbnIng+"_UPD",catlevel,"description");
				



				isbnIng = IngectEpub_res6.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(4000);
				Response IngestionStatus_res6 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				Validation.responseHeaderCodeValidation(IngestionStatus_res6, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngestionStatus_res6, HttpStatus.SC_OK);
				Validation.responseINTEGERKeyAndValue(IngestionStatus_res6, "status", 100);	
				
				
				updatemeta = UpdateMetadata.updateMetadata(consumerKey, consumerSecret,isbnIng,"Reflow_"+isbnIng+"_UPD","Reflow_"+isbnIng+"_UPD",catlevel,"description");
				


				isbnIng = IngectEpub_res7.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(4000);
				Response IngestionStatus_res7 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				Validation.responseHeaderCodeValidation(IngestionStatus_res7, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngestionStatus_res7, HttpStatus.SC_OK);
				Validation.responseINTEGERKeyAndValue(IngestionStatus_res7, "status", 100);	
				
				
				
				updatemeta = UpdateMetadata.updateMetadata(consumerKey, consumerSecret,isbnIng,"Reflow_"+isbnIng+"_UPD","Reflow_"+isbnIng+"_UPD",catlevel,"description");
				Validation.responseHeaderCodeValidation(updatemeta, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(updatemeta, HttpStatus.SC_OK);
				Validation.responseKeyValidation_Str(updatemeta, "The request for the update ePub metadata completed successfully.");



				isbnIng = IngectEpub_res8.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(4000);
				Response IngestionStatus_res8 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				Validation.responseHeaderCodeValidation(IngestionStatus_res8, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngestionStatus_res8, HttpStatus.SC_OK);
				Validation.responseINTEGERKeyAndValue(IngestionStatus_res8, "status", 100);	
				
				updatemeta = UpdateMetadata.updateMetadata(consumerKey, consumerSecret,isbnIng,"Reflow_"+isbnIng+"_UPD","Reflow_"+isbnIng+"_UPD",catlevel,"description");
				Validation.responseHeaderCodeValidation(updatemeta, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(updatemeta, HttpStatus.SC_OK);
				Validation.responseKeyValidation_Str(updatemeta, "The request for the update ePub metadata completed successfully.");


				isbnIng = IngectEpub_res9.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(4000);
				Response IngestionStatus_res9 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				Validation.responseHeaderCodeValidation(IngestionStatus_res9, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngestionStatus_res9, HttpStatus.SC_OK);
				Validation.responseINTEGERKeyAndValue(IngestionStatus_res9, "status", 100);	
			
				updatemeta = UpdateMetadata.updateMetadata(consumerKey, consumerSecret,isbnIng,"Reflow_"+isbnIng+"_UPD","Reflow_"+isbnIng+"_UPD",catlevel,"description");
				Validation.responseHeaderCodeValidation(updatemeta, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(updatemeta, HttpStatus.SC_OK);
				Validation.responseKeyValidation_Str(updatemeta, "The request for the update ePub metadata completed successfully.");

				isbnIng = IngectEpub_res10.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(4000);
				Response IngestionStatus_res10 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				Validation.responseHeaderCodeValidation(IngestionStatus_res10, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngestionStatus_res10, HttpStatus.SC_OK);
				Validation.responseINTEGERKeyAndValue(IngestionStatus_res10, "status", 100);	
			
				updatemeta = UpdateMetadata.updateMetadata(consumerKey, consumerSecret,isbnIng,"Reflow_"+isbnIng+"_UPD","Reflow_"+isbnIng+"_UPD",catlevel,"description");
				Validation.responseHeaderCodeValidation(updatemeta, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(updatemeta, HttpStatus.SC_OK);
				Validation.responseKeyValidation_Str(updatemeta, "The request for the update ePub metadata completed successfully.");


				Response fetchBookList_without_pagination = FetchBookList.fetchBookList_without_pagination(userToken,"45616452",deviceT);
				Validation.responseCodeValidation1(fetchBookList_without_pagination, HttpStatus.SC_OK);
				Validation.responseHeaderCodeValidation(fetchBookList_without_pagination, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchBookList_without_pagination);
				Validation.responseKeyAndValue(fetchBookList_without_pagination, "title", "Reflow_"+isbnIng+"_UPD");
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
				/*isbnIng = IngectEpub_res11.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(6000);
				Response IngestionStatus_res11 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				System.out.println("IngestionStatus_res : "+IngestionStatus_res11);*/


				/*		
		//SELENIUM		
					BrowserConfigure.browserConfigure("Chrome");
					//Driver.driver.navigate().to("https://qc.kitaboo.com/home.xhtml");
					Log.startTestCase("SELENIUM CODE - BY Shweta Katare");
					System.out.println("environMent : "+detail);
					Driver.driver.navigate().to(detail);

					userName="entlibrary_cat1@yopmail.com";
					System.out.println("userName : "+userName);
					System.out.println("password : "+password);
					LoginModule.loginScenario(userName, password);
					//Thread.sleep(500); //threadHold_2Sec()
					Driver.driver.findElement(By.id("categoryLink")).click();//Adhoc tab click
					Thread.sleep(500);
					Driver.driver.findElement(By.id("addCategories")).click(); //Add cat click
					Thread.sleep(500);
					Driver.driver.switchTo().defaultContent();
					Thread.sleep(9000);
					System.out.println("here");
					Driver.driver.findElement(By.id("main_category")).click(); //main cat textbox click
					System.out.println("here....");
					Driver.driver.findElement(By.id("main_category")).sendKeys("ADHOC_1979");
					System.out.println("@@@@@@@@@@@here");
					Thread.sleep(1000);
					Driver.driver.findElement(By.id("displayCategory")).click(); //displayCategory radio box

					Driver.driver.findElement(By.id("resourceList_source_filter")).click();//bookname click
					Driver.driver.findElement(By.id("resourceList_source_filter")).sendKeys("Reflow");
					Driver.driver.findElement(By.id("resourceList_source_filter")).sendKeys(Keys.ENTER);
					Driver.driver.findElement(By.xpath("//*[@id='resourceList']/tbody/tr/td[1]/ul/li[2]/table/tbody/tr/td[1]/div/div")).click();//radio book select 
					Driver.driver.findElement(By.xpath("//*[@id='resourceList']/tbody/tr/td[2]/button[1]/span[1]")).click();
					Thread.sleep(5000);
					WebElement element = Driver.driver.findElement(By.id("resourceSubmit"));
					JavascriptExecutor executor = (JavascriptExecutor)Driver.driver;
					executor.executeScript("arguments[0].click();", element);
					//Driver.driver.findElement(By.id("resourceSubmit")).click(); ///save button click
				 */

				/*

				String isbnURL=formatter.formatCellValue(sheet.getRow(6).getCell(0));
				Response IngectEpub_res12 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Validation.responseHeaderCodeValidation(IngectEpub_res12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res12);
				Validation.responseKeyValidation_key(IngectEpub_res12, "The request for the uploadEpub taken successfully.");
				System.out.println("IngectEpub_res : "+IngectEpub_res12);

				isbnURL=formatter.formatCellValue(sheet.getRow(6).getCell(1));
				Response IngectEpub_res22 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Validation.responseHeaderCodeValidation(IngectEpub_res22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res22);
				Validation.responseKeyValidation_key(IngectEpub_res22, "The request for the uploadEpub taken successfully.");
				System.out.println("IngectEpub_res : "+IngectEpub_res22);

				isbnURL=formatter.formatCellValue(sheet.getRow(6).getCell(2));
				Response IngectEpub_res33 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Validation.responseHeaderCodeValidation(IngectEpub_res33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res33);
				Validation.responseKeyValidation_key(IngectEpub_res33, "The request for the uploadEpub taken successfully.");
				System.out.println("IngectEpub_res : "+IngectEpub_res33);

				isbnIng = IngectEpub_res12.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(6000);
				Response IngestionStatus_res1 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				System.out.println("IngestionStatus_res : "+IngestionStatus_res1);

				isbnIng = IngectEpub_res22.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(6000);
				Response IngestionStatus_res22 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				System.out.println("IngestionStatus_res : "+IngestionStatus_res22);


				isbnIng = IngectEpub_res33.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(6000);
				Response IngestionStatus_res33 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				System.out.println("IngestionStatus_res : "+IngestionStatus_res33);*/

				/*Response Metadata_res_upd = Metadata.metadata(consumerKey, consumerSecret,"2424242424261","Reflow_2424242424261","Reflow_2424242424261",catlevel);
				Validation.responseHeaderCodeValidation(Metadata_res_upd, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Metadata_res_upd, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Metadata_res_upd);
				Validation.responseKeyValidation_key(Metadata_res_upd, "isbn");

				Response Metadata_res_upd1 = Metadata.metadata(consumerKey, consumerSecret,"2424242424262","Reflow_2424242424262","Reflow_2424242424262",catlevel);
				Validation.responseHeaderCodeValidation(Metadata_res_upd1, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Metadata_res_upd1, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Metadata_res_upd1);
				Validation.responseKeyValidation_key(Metadata_res_upd1, "isbn");

				Response Metadata_res_upd12 = Metadata.metadata(consumerKey, consumerSecret,"2424242424263","Reflow_2424242424263","Reflow_2424242424263",catlevel);
				Validation.responseHeaderCodeValidation(Metadata_res_upd12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(Metadata_res_upd12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(Metadata_res_upd12);
				Validation.responseKeyValidation_key(Metadata_res_upd12, "isbn");

				String isbnURL=formatter.formatCellValue(sheet.getRow(6).getCell(7));
				Response IngectEpub_res12 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Validation.responseHeaderCodeValidation(IngectEpub_res12, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res12, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res12);
				Validation.responseKeyValidation_key(IngectEpub_res12, "The request for the uploadEpub taken successfully.");
				System.out.println("IngectEpub_res : "+IngectEpub_res12);

				isbnURL=formatter.formatCellValue(sheet.getRow(6).getCell(8));
				Response IngectEpub_res22 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Validation.responseHeaderCodeValidation(IngectEpub_res22, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res22, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res22);
				Validation.responseKeyValidation_key(IngectEpub_res22, "The request for the uploadEpub taken successfully.");
				System.out.println("IngectEpub_res : "+IngectEpub_res22);

				isbnURL=formatter.formatCellValue(sheet.getRow(6).getCell(9));
				Response IngectEpub_res33 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,""+isbnURL+"");
				Validation.responseHeaderCodeValidation(IngectEpub_res33, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(IngectEpub_res33, HttpStatus.SC_OK);
				Validation.responseTimeValidation(IngectEpub_res33);
				Validation.responseKeyValidation_key(IngectEpub_res33, "The request for the uploadEpub taken successfully.");
				System.out.println("IngectEpub_res : "+IngectEpub_res33);

				isbnIng = IngectEpub_res12.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(6000);
				Response IngestionStatus_res1 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				System.out.println("IngestionStatus_res : "+IngestionStatus_res1);

				isbnIng = IngectEpub_res22.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(6000);
				Response IngestionStatus_res22 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				System.out.println("IngestionStatus_res : "+IngestionStatus_res22);


				isbnIng = IngectEpub_res33.then().extract().path("isbn");			
				System.out.println("isbnIng: "+isbnIng);
				Thread.sleep(6000);
				Response IngestionStatus_res33 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
				System.out.println("IngestionStatus_res : "+IngestionStatus_res33);*/
			}
			}
		}catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("fails due to"+ exp.getCause());
			exp.printStackTrace();
		}
	}
}
