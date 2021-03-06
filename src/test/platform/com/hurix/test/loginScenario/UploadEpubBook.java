package com.hurix.test.loginScenario;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hurix.asset.audio.AudioModule;
import com.hurix.asset.audio.AudioStepModule;
import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.ExcelFile;
import com.hurix.automation.utility.Log;
import com.hurix.epubbooks.EpubBook.EpubBookModule;
import com.hurix.epubbooks.uploadEpubBook.UploadEpubBookModule;
import com.hurix.license.b2blicense.B2bLicenseModule;
import com.hurix.platform.loginPage.LoginModule;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;
import com.hurix.test.loginScenario.LoginTest;

public class UploadEpubBook {

	public static void main(String[] args) throws Exception 
	{
		Log.initialization("Upload_epub");
		String path="\\testData\\TestData.xlsx";
		ExcelFile.setExcelFile(path, "Create_Kitaboo_Credential");
		int size = ExcelFile.getRowCount();
		for(int j=1;j<=1;j++)
		{
			String username = ExcelFile.getCellData(j, 0);
			String password = ExcelFile.getCellData(j, 1);
			String url = ExcelFile.getCellData(1, 2);
			Log.info(username);
			Log.info(password);
			Log.info(url);
			File file = new File("");
			Log.startTestCase("Login");
			BrowserConfigure.browserConfigure("Chrome");
			Driver.driver.navigate().to(url);
			LoginModule.loginScenario(username, password);
			AudioStepModule.popupmsg();
			Log.endTestCase("End Login");
			Log.startTestCase("Upload_epub");
			ExcelFile.setExcelFile(path, "Upload_EPub");
			long nowEpochTime = Instant.now().toEpochMilli();
			int epubsize = ExcelFile.getRowCount();
			for(int i=1;i<=1;i++)
			{
				String title = ExcelFile.getCellData(i,1);
				String author = ExcelFile.getCellData(i,2);
				String category = ExcelFile.getCellData(i,3);
				String isbn = ExcelFile.getCellData(i,4);
				String ref = ExcelFile.getCellData(i,5); 
				String desc = ExcelFile.getCellData(i,6);
				String voice = ExcelFile.getCellData(i,7);
				String language = ExcelFile.getCellData(i,8);
				String SpeechRate = ExcelFile.getCellData(i,9); 		
				String filepath = ExcelFile.getCellData(i,10); 		
				UploadEpubBookModule.epubuploadbookcreate();
				UploadEpubBookModule.epubuploaddatafill(title+nowEpochTime, author, category, isbn, ref, desc);
				UploadEpubBookModule.epubfileupload(filepath);
				UploadEpubBookModule.epubuploadamazonpolly(voice, language, SpeechRate);
				Log.endTestCase("End Audio");
			}
			ExcelFile.setExcelFile(path, "Create_Kitaboo_Credential");	
			UploadEpubBookModule.logout();
		}

	}

}
