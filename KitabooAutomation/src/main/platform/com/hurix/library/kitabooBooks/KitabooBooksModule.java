package com.hurix.library.kitabooBooks;

import io.cucumber.java.en.Given;

import java.time.Instant;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.api.services.sheets.v4.model.DeleteProtectedRangeRequest;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;

public class KitabooBooksModule extends KitabooBooksStepModule {
	/*
	 * 
	 * Author: Amit Singh
	 * Date: 16/07/2020
	 * 
	 * 
	 */
	@Given("create a book with data")
	//String isbn,String title,String author,String catLevel, String description,String language, String bookType, String keywords, String bookOriantaion, String bookPath
	//public static String bookCreationPDF(){
	public static String bookCreationPDF(String title, String iSBN,
			String author, String categories, String description,
			String keywords, String language, String bookType,
			String bookOriantaion, boolean fontPermission, String picformat,
			String dPI, boolean showFolio, String glossary,
			String glossaryPath, String metaData, String metaDataPath,
			String uploadFile, String pDF_DocPath, String uploadCover,
			String lowImageQuality, String lowImagePath,
			String highImageQuality, String highImagePath, String uploadTOC,
			String tOCPath)
			{
		String parentWin = Driver.driver.getWindowHandle();
		drpAddNew_HTMLCreateBook();
		threadHold_5Sec(); 
		for(String winHandle : Driver.driver.getWindowHandles())
		{
			Driver.driver.switchTo().window(winHandle);
		}
		
		txtISBN("1245484578547");
		txtTitle(title);
		txtAuthor(author);
		drpCategory(categories);
		txtDescription(description);
		btnsavedraft();
		Driver.driver.switchTo().window(parentWin);
		btnresume();
		for(String winHandle : Driver.driver.getWindowHandles())
		{
			Driver.driver.switchTo().window(winHandle);
		}
		btncancel();
		Driver.driver.switchTo().window(parentWin);
		btnresume();
		for(String winHandle : Driver.driver.getWindowHandles())
		{
			Driver.driver.switchTo().window(winHandle);
		}
		drpLanguageSelection(language);
		drpBookType(bookType); 
		txtkeywords(keywords);
		drpBookOriantation(bookOriantaion);
		chkFontPermission(true);
		chkshowfolio(showFolio);
		drpdpi(dPI, picformat);
		linksamplemetadata();
		samplefileupload(metaDataPath);
		GlossaryfileDownload();
		Glossaryfileupload(glossaryPath);
		threadHold_5Sec();
		threadHold_5Sec();
		String[] paths = pDF_DocPath.split(",");
		for(String path : paths) {
			btnUploadBook(path,uploadFile);
		}
		deletebook();
		String Lowquality="Low quality";
		rdbDefaultImage(uploadCover, Lowquality, lowImagePath);
		String Highquality="High quality";
		rdbDefaultImage(uploadCover, Highquality, highImagePath);
		SampleTOC(); 
		UploadTOC(uploadTOC);
		DownloadTOC();
		Deletelevel();
		UploadTOC("D:\\eclipseWorkSpace\\KitabooAutomation\\KitabooAutomation\\testData\\uploadTOC.xls");
		ChapterNumber();
		PagePosition();
		DeleteFolio();
		DeletePageNo();
		DeleteTOC();
		GenerateFolio();
		refresh();
		UploadTOC("D:\\eclipseWorkSpace\\KitabooAutomation\\KitabooAutomation\\testData\\uploadTOC.xls");
		btnFinish();
		afterFinishBtnContinue();
		threadHold_5Sec();
		threadHold_5Sec();
		Driver.driver.switchTo().window(parentWin);
		return title;
		
	}
	
public static String bookverify(String title,String isbn){
		
		txtSearch(title);
		btnSearch();
		isbnverify(isbn);
		bookname(title);
		date();
		threadHold_5Sec();
		btnPublish();
		btnArchived();
		return null;
	}
	

public static void serachby(String searchby,String searchvalue){
	
	searchFilteruncheck();
	if(searchby.equalsIgnoreCase("Title"))
	{
		searchbytitle();
	}
	else if(searchby.equalsIgnoreCase("ISBN"))
	{
		searchbyISBN();
	}
	else if(searchby.equalsIgnoreCase("Ref Id"))
	{
		searchbyRefid();
		
	}
	else if(searchby.equalsIgnoreCase("Producer"))
	{
		searchbyProducer();
	}
	txtSearch(searchvalue);
	btnSearch();
	
}

	
	public static String bookPublishAndArchivePDF(String title){
		
		txtSearch(title);
		threadHold_5Sec();
		btnPublish();
		btnArchived();
		return null;
	}
}
