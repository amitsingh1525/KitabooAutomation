package com.hurix.epubbooks.uploadEpubBook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hurix.asset.audio.AudioStepModule;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class UploadEpubBookModule extends UploadEpubBookStepModule
{
	
	public static void logout()
	{
		Log.startTestCase("Logout");
		if(Driver.driver.getCurrentUrl().contains("books.xhtml"))
		{
			AudioStepModule.btnlogout();
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}
		Log.endTestCase("End");
	}
			
	public static void epubuploadbookcreate()
	{
		linkepub();
		btnaddnewbuttonepubbook();
		linkepubupload();
		//UIElements.selectDropdown(By.id("accord:audioSyncselRateEpub"), "fast","log");
	}

	public static void epubuploaddatafill(String title, String author, String category, String isbn, String ref, String desc)
	{
		txtepubuploadinputTitle(title);
		txtepubuploadinputAuthor(author);
		txtepubuploadinputCategory(category);
		txtepubuploadinputIsbn(isbn);
		txtepubuploadinputref(ref);
		txtepubuploadinputDesc(desc);
	}

	public static void epubuploadamazonpolly(String voice,String language, String SpeechRate)
	{
		btnepubuploadamazonpoly();
		btnepubuploadmale(voice);
		drpSpeechRate(SpeechRate);
		drplanguage(language);
		btnepubuploaddone();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("epubuploadsucces_xpath"))));
		threadHold_2Sec();
		wait.until(ExpectedConditions.invisibilityOfElementLocated((By.id(prop.getProperty("epubuploadloader_image")))));
		threadHold_2Sec();
		Log.info("Epub upload done.");
	}
	
	public static void epubfileupload(String filePath)
	{
		btnepubuploadstart();
		WebDriverWait wait = new WebDriverWait(Driver.driver, 900); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("epubuploadfilepicker_id"))));
		Driver.driver.switchTo().frame(prop.getProperty("epubuploadfilepickerdialog_name"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("epubuploadfileInputContainer_id"))));
		Driver.driver.findElement(By.id(prop.getProperty("epubuploadfileUploadInput_id"))).sendKeys(filePath);
		
	}
	
	
}