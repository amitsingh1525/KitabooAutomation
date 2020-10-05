package com.hurix.epubbooks.EpubBook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hurix.asset.audio.AudioStepModule;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class EpubBookModule extends EpubBookStepModule
{
	
	public static void logout()
	{
		Log.startTestCase("Logout");
		if(Driver.driver.getCurrentUrl().contains("audio.xhtml"))
		{
			AudioStepModule.btnlogout();
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}
		Log.endTestCase("End");
	}
			
	public static void epubhtmlbookcreate()
	{
		linkepub();
		btnaddnewbuttonepubbook();
		btnhtmlcreatebookepubbook();
		windowswitch();
	}

	public static void metadata(String isbn, String title, String language, String spreadtype, String bookorientation, String ebooktype, String font, String picformate)
	{
		txtisbnepubbook(isbn);
		//rbPngpicformat(picformate);
		drpfontepubbook(font);
		txttitleepubbook(title);
		drplanguagedropdownepubbook(language);
		drpspreadtypeepubbook(spreadtype);
		drpbookorientationepubbook(bookorientation);
		drpepubtypeepubbook(ebooktype);
	}
	
	public static void fileuplod(String pdffilepath, String pdfanswer, String coveranswer, String coverfilepath)
	{
		String parentWin = Driver.driver.getWindowHandle();
		pdffileupload(pdfanswer,pdffilepath);
		uploadcoverimage(coveranswer, coverfilepath);
		btnfinishepubbook();
		btncontinueepubbooks();
		Driver.driver.switchTo().window(parentWin);
			
	}
	
	
	
}