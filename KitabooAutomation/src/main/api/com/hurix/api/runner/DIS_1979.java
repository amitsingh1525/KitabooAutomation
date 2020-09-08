package com.hurix.api.runner;

import io.restassured.response.*;

import java.time.Instant;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;

import com.hurix.api.externalAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.*;
import com.hurix.platform.loginPage.LoginModule;

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

	public static void main(String[] args) {
		Log.initialization("APITesting");//DIS-1979	
		Log.initialization("DIS_1979");
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/DIS-1979.xlsx";
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
				/*BrowserConfigure.browserConfigure("Chrome");
				Driver.driver.navigate().to("https://qc.kitaboo.com/home.xhtml");
				LoginModule.loginScenario("automation.test1@yopmail.com", "kitaboo!123");
				String title = KitabooBooksModule.bookCreationPDF("hello", "World", "level2", "For automation Testing", "Hello, World");
				KitabooBooksModule.bookPublishAndArchivePDF(title);*/


				nowEpochTime = Instant.now().toEpochMilli();
				Title ="Reflow_epub_"+nowEpochTime+"";
				//Log.info("Title : " +Title);
				/*Response UploadEpub_res = UploadEpub.uploadEpub_OAuth(consumerKey, consumerSecret,"/Thirdepub/JMeterTesting.epub","Title","Title","level4",""+nowEpochTime+"","Title");
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
				System.out.println("EpubStatus_res : "+EpubStatus_res);*/
				//Log.info(Validation.responseCodeValidation1(EpubStatus_res, HttpStatus.SC_OK));
//2 qand 3 success

				for(int i1=1;i1<=11;i1++)
				{
					isbnMeta=formatter.formatCellValue(sheet.getRow(6).getCell(i1));
					System.out.println("isbnMeta: "+isbnMeta);
					System.out.println("detailisbn = "+detailisbn.get(i1));
					//String consumerKey, String consumerSecret,String string,String title,String author,String cat4
					Response Metadata_res = Metadata.metadata(consumerKey, consumerSecret,isbnMeta,"Reflow_1979","Reflow_1979","4");
					System.out.println("Metadata_res : "+Metadata_res);
					isbnMeta = Metadata_res.then().extract().path("isbn");
					System.out.println("isbnMeta: "+isbnMeta);
					//isbnMeta=detailisbn.get(i1);
					isbnMeta=formatter.formatCellValue(sheet.getRow(6).getCell(i1));
					Response IngectEpub_res = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,isbnMeta);
					System.out.println("IngectEpub_res : "+IngectEpub_res);
					isbnIng = IngectEpub_res.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);
					Thread.sleep(4000);
					Response IngestionStatus_res = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res);	
					
					
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
