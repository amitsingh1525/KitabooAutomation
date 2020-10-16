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
import com.hurix.library.kitabooBooks.KitabooBooksStepModule;

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
			
	public static void epubhtmlbookcreate(String title, String language,
			String spreadtype, String epubtype, String bookOriantaion,
			String dPI, String picformat, String metaDataPath,
			String pDF_DocPath, String uploadCover, String lowImagePath,
			String uploadTOC, String uploadFile)
	{
		
		String parentWin = Driver.driver.getWindowHandle();
		linkepub();
		KitabooBooksStepModule.drpAddNew_HTMLCreateBook();
		windowswitch();
		KitabooBooksStepModule.txtISBN("1245484578547");
		KitabooBooksStepModule.txtTitle(title);
		KitabooBooksStepModule.btnsavedraft();
		Driver.driver.switchTo().window(parentWin);
		KitabooBooksStepModule.btnresume();
		windowswitch();
		KitabooBooksStepModule.btncancel();
		Driver.driver.switchTo().window(parentWin);
		KitabooBooksStepModule.btnresume();
		windowswitch();
		KitabooBooksStepModule.drpLanguageSelection(language);
		drpspreadtypeepubbook(spreadtype);
		drpepubtypeepubbook(epubtype);
		KitabooBooksStepModule.drpBookOriantation(bookOriantaion);
		KitabooBooksStepModule.chkFontPermission(true);
		KitabooBooksStepModule.drpdpi(dPI, picformat);
		KitabooBooksStepModule.linksamplemetadata();
		KitabooBooksStepModule.samplefileupload(metaDataPath);
		String[] paths = pDF_DocPath.split(",");
		for(String path : paths) {
			KitabooBooksStepModule.btnUploadBook(path,uploadFile);
		}
		KitabooBooksStepModule.deletebook();
		String Lowquality="Low quality";
		KitabooBooksStepModule.rdbDefaultImage(uploadCover, Lowquality, lowImagePath);
		String Highquality="High quality";
		KitabooBooksStepModule.rdbDefaultImage(uploadCover, Highquality, lowImagePath);
		KitabooBooksStepModule.SampleTOC(); 
		KitabooBooksStepModule.UploadTOC(uploadTOC);
		KitabooBooksStepModule.DownloadTOC();
		KitabooBooksStepModule.Deletelevel();
		KitabooBooksStepModule.UploadTOC("D:\\eclipseWorkSpace\\KitabooAutomation\\KitabooAutomation\\testData\\uploadTOC.xls");
		KitabooBooksStepModule.ChapterNumber();
		KitabooBooksStepModule.PagePosition();
		KitabooBooksStepModule.DeleteFolio();
		KitabooBooksStepModule.DeletePageNo();
		KitabooBooksStepModule.DeleteTOC();
		KitabooBooksStepModule.GenerateFolio();
		KitabooBooksStepModule.refresh();
		KitabooBooksStepModule.UploadTOC("D:\\eclipseWorkSpace\\KitabooAutomation\\KitabooAutomation\\testData\\uploadTOC.xls");
		KitabooBooksStepModule.btnFinish();
		KitabooBooksStepModule.afterFinishBtnContinue();
		threadHold_5Sec();
		threadHold_5Sec();
		Driver.driver.switchTo().window(parentWin);
		
			
	}

}

