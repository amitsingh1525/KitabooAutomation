package com.hurix.api.runner;

import io.restassured.response.*;
import java.time.Instant;
import java.util.List;
import org.apache.http.HttpStatus;
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
				System.out.println("HEREE GHGHDGHDDGGG consumerKey : "+consumerKey);
				consumerKey=formatter.formatCellValue(sheet.getRow(1).getCell(4));
				System.out.println("HEREE &^%#$%^&^%$%^&^ consumerKey2 : "+consumerKey);
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

				for(int i1=0;i1<=10;i1++)
				{
					isbnMeta=formatter.formatCellValue(sheet.getRow(5).getCell(i1));
					System.out.println("isbnMeta: "+isbnMeta);
					Log.info("isbnMeta: "+isbnMeta);
					System.out.println("detailisbn = "+detailisbn.get(i1));
					//String consumerKey, String consumerSecret,String string,String title,String author,String cat4
					Response Metadata_res = Metadata.metadata(consumerKey, consumerSecret,isbnMeta,"Reflow_"+isbnMeta+"","Reflow_"+isbnMeta+"",catlevel);
					Validation.responseHeaderCodeValidation(Metadata_res, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(Metadata_res, HttpStatus.SC_OK);
					Validation.responseTimeValidation(Metadata_res);
					Validation.responseKeyValidation_key(Metadata_res, "isbn");
					System.out.println("Metadata_res : "+Metadata_res);
					isbnMeta = Metadata_res.then().extract().path("isbn");
					System.out.println("isbnMeta: "+isbnMeta);
					//isbnMeta=detailisbn.get(i1);
					isbnMeta=formatter.formatCellValue(sheet.getRow(6).getCell(i1));

				}
			
					Response IngectEpub_res1 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,"https://hurix-staging-content.s3.amazonaws.com/test/2424242424241.epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889267&Signature=yJt%2BAsW39SwEBzAbOJLlEhVpqoc%3D");
					Validation.responseHeaderCodeValidation(IngectEpub_res1, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(IngectEpub_res1, HttpStatus.SC_OK);
					Validation.responseTimeValidation(IngectEpub_res1);
					Validation.responseKeyValidation_key(IngectEpub_res1, "The request for the uploadEpub taken successfully.");
					System.out.println("IngectEpub_res : "+IngectEpub_res1);

					Response IngectEpub_res2 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,"https://hurix-staging-content.s3.amazonaws.com/test/2424242424242.epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889365&Signature=BeH6o9%2F9JfyXG2ANIagKIAEarXg%3D");
					Validation.responseHeaderCodeValidation(IngectEpub_res2, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(IngectEpub_res2, HttpStatus.SC_OK);
					Validation.responseTimeValidation(IngectEpub_res2);
					Validation.responseKeyValidation_key(IngectEpub_res2, "The request for the uploadEpub taken successfully.");
					System.out.println("IngectEpub_res : "+IngectEpub_res2);

					Response IngectEpub_res3 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,"https://hurix-staging-content.s3.amazonaws.com/test/2424242424243.epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889384&Signature=sixD166ziWfCrWC5cv39MJSF5MA%3D");
					Validation.responseHeaderCodeValidation(IngectEpub_res3, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(IngectEpub_res3, HttpStatus.SC_OK);
					Validation.responseTimeValidation(IngectEpub_res3);
					Validation.responseKeyValidation_key(IngectEpub_res3, "The request for the uploadEpub taken successfully.");
					System.out.println("IngectEpub_res : "+IngectEpub_res3);

					Response IngectEpub_res4 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,"https://hurix-staging-content.s3.amazonaws.com/test/2424242424244.epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889410&Signature=h2%2FRRXLD%2Bn%2Bb8Rr3%2Fge61UCxIvc%3D");
					Validation.responseHeaderCodeValidation(IngectEpub_res4, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(IngectEpub_res4, HttpStatus.SC_OK);
					Validation.responseTimeValidation(IngectEpub_res4);
					Validation.responseKeyValidation_key(IngectEpub_res4, "The request for the uploadEpub taken successfully.");
					System.out.println("IngectEpub_res : "+IngectEpub_res4);

					Response IngectEpub_res5 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,"https://hurix-staging-content.s3.amazonaws.com/test/2424242424245.epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889449&Signature=Usu9mM9JvtbPyJwKpqUmPmHzNGk%3D");
					Validation.responseHeaderCodeValidation(IngectEpub_res5, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(IngectEpub_res5, HttpStatus.SC_OK);
					Validation.responseTimeValidation(IngectEpub_res5);
					Validation.responseKeyValidation_key(IngectEpub_res5, "The request for the uploadEpub taken successfully.");
					System.out.println("IngectEpub_res : "+IngectEpub_res5);

					Response IngectEpub_res6 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,"https://hurix-staging-content.s3.amazonaws.com/test/2424242424246.epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889496&Signature=S2bM19bw7fjIohfFp5LJ9DfYz20%3D");
					Validation.responseHeaderCodeValidation(IngectEpub_res6, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(IngectEpub_res6, HttpStatus.SC_OK);
					Validation.responseTimeValidation(IngectEpub_res6);
					Validation.responseKeyValidation_key(IngectEpub_res6, "The request for the uploadEpub taken successfully.");
					System.out.println("IngectEpub_res : "+IngectEpub_res6);

					Response IngectEpub_res7 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,"https://hurix-staging-content.s3.amazonaws.com/test/2424242424247.epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889514&Signature=KNsQo4OdTvfColU%2BHKz3MaXtW7g%3D");
					Validation.responseHeaderCodeValidation(IngectEpub_res7, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(IngectEpub_res7, HttpStatus.SC_OK);
					Validation.responseTimeValidation(IngectEpub_res7);
					Validation.responseKeyValidation_key(IngectEpub_res7, "The request for the uploadEpub taken successfully.");
					System.out.println("IngectEpub_res : "+IngectEpub_res7);

					Response IngectEpub_res8= IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,"https://hurix-staging-content.s3.amazonaws.com/test/2424242424248.epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889527&Signature=rioCbOzymbeReVUDrY%2FeNgeUEAc%3D");
					Validation.responseHeaderCodeValidation(IngectEpub_res8, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(IngectEpub_res8, HttpStatus.SC_OK);
					Validation.responseTimeValidation(IngectEpub_res8);
					Validation.responseKeyValidation_key(IngectEpub_res8, "The request for the uploadEpub taken successfully.");
					System.out.println("IngectEpub_res : "+IngectEpub_res8);			


					Response IngectEpub_res9 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,"https://hurix-staging-content.s3.amazonaws.com/test/2424242424249.epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889542&Signature=NyHTU5a3J8B%2Fg6eDzQ8PVVisZDU%3D");
					Validation.responseHeaderCodeValidation(IngectEpub_res9, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(IngectEpub_res9, HttpStatus.SC_OK);
					Validation.responseTimeValidation(IngectEpub_res9);
					Validation.responseKeyValidation_key(IngectEpub_res9, "The request for the uploadEpub taken successfully.");
					System.out.println("IngectEpub_res : "+IngectEpub_res9);					

					Response IngectEpub_res10 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,"https://hurix-staging-content.s3.amazonaws.com/test/2424242424250.epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889556&Signature=RAKdJMCeUZE5Ph9OJyhY2W%2BLwPY%3D");
					Validation.responseHeaderCodeValidation(IngectEpub_res10, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(IngectEpub_res10, HttpStatus.SC_OK);
					Validation.responseTimeValidation(IngectEpub_res10);
					Validation.responseKeyValidation_key(IngectEpub_res10, "The request for the uploadEpub taken successfully.");
					System.out.println("IngectEpub_res : "+IngectEpub_res10);									

					Response IngectEpub_res11 = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,"https://hurix-staging-content.s3.amazonaws.com/test/2424242424251.epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889556&Signature=RAKdJMCeUZE5Ph9OJyhY2W%2BLwPY%3D");
					Validation.responseHeaderCodeValidation(IngectEpub_res11, HttpStatus.SC_OK);
					Validation.responseCodeValidation1(IngectEpub_res11, HttpStatus.SC_OK);
					Validation.responseTimeValidation(IngectEpub_res11);
					Validation.responseKeyValidation_key(IngectEpub_res11, "The request for the uploadEpub taken successfully.");
					System.out.println("IngectEpub_res : "+IngectEpub_res11);	

					isbnIng = IngectEpub_res1.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);
					Thread.sleep(6000);
					Response IngestionStatus_res = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res);

					isbnIng = IngectEpub_res2.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);
					Thread.sleep(6000);
					Response IngestionStatus_res2 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res2);


					isbnIng = IngectEpub_res3.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);
					Thread.sleep(6000);
					Response IngestionStatus_res3 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res3);

					isbnIng = IngectEpub_res4.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);
					Thread.sleep(6000);
					Response IngestionStatus_res4 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res4);

					isbnIng = IngectEpub_res5.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);
					Thread.sleep(6000);
					Response IngestionStatus_res5 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res5);


					isbnIng = IngectEpub_res6.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);
					Thread.sleep(6000);
					Response IngestionStatus_res6 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res6);


					isbnIng = IngectEpub_res7.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);
					Thread.sleep(6000);
					Response IngestionStatus_res7 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res7);


					isbnIng = IngectEpub_res8.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);
					Thread.sleep(6000);
					Response IngestionStatus_res8 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res8);


					isbnIng = IngectEpub_res9.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);
					Thread.sleep(6000);
					Response IngestionStatus_res9 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res9);

					isbnIng = IngectEpub_res10.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);
					Thread.sleep(6000);
					Response IngestionStatus_res10 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res10);


					isbnIng = IngectEpub_res11.then().extract().path("isbn");			
					System.out.println("isbnIng: "+isbnIng);
					Thread.sleep(6000);
					Response IngestionStatus_res11 = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
					System.out.println("IngestionStatus_res : "+IngestionStatus_res11);


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
			}
		}catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}
}
