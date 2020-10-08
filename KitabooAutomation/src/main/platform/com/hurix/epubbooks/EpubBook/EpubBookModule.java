package com.hurix.epubbooks.EpubBook;

import java.util.Iterator;
import java.util.Set;

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
			
	public static void epubhtmlbookcreate(String isbn,String title,String language,String spreadtype,String bookorentation,String epubtype,String font,String picformate,String pdffilepath,String pdfanswer,String coveranswer,String coverfilepath)
	{
		linkepub();
		String parent = Driver.driver.getWindowHandle();
		btnaddnewbuttonepubbook();
		btnhtmlcreatebookepubbook();
		windowswitch();
		txtisbnepubbook(isbn);
		//rbPngpicformat(picformate);
		drpfontepubbook(font);
		txttitleepubbook(title);
		drplanguagedropdownepubbook(language);
		drpspreadtypeepubbook(spreadtype);
		drpbookorientationepubbook(bookorentation);
		drpepubtypeepubbook(epubtype);
		Log.info(parent);
		pdffileupload(pdfanswer,pdffilepath);
		uploadcoverimage(coveranswer, coverfilepath);
		btnfinishepubbook();
		btncontinueepubbooks();
		Driver.driver.switchTo().window(parent);
		Log.info(parent);		
	
	}
}

