package com.hurix.epubbooks.bulkuploadepub;

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

public class bulkuploadepub extends bulkuploadepubStepModule
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
			
	public static void bulkconversion(String filepath)
	{
		linkepub();
		btnaddnewbuttonepubbook();
		linkbulkuploadconversion();
		sampledownload();
		fileupload(filepath);
		
	}

	public static void bulkconversionstatus()
	{
		profilebulkuploadepub();
		checkBulkUploadConversionStatus();
		downloaderrorlog();
		librarybulkuploadepub();
		
	}
	
	
	
	
}