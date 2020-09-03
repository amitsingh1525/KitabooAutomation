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
import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.ExcelFile;
import com.hurix.automation.utility.Log;
import com.hurix.platform.loginPage.LoginModule;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;

public class audio {

	public static void main(String[] args) throws Exception 
	{

			ExcelFile.setExcelFile("\\testData\\TestData.xlsx", "Create_Kitaboo_Credential");
			String username = ExcelFile.getCellData(1, 0);
			String password = ExcelFile.getCellData(1, 1);
			String url = ExcelFile.getCellData(1, 2);
			File file = new File("");
			Log.initialization("AUDIO BOOK");
			Log.startTestCase("Login");
			BrowserConfigure.browserConfigure("Chrome");
			Driver.driver.navigate().to(url);
			LoginModule.loginScenario(username, password);
			Log.endTestCase("End Login");
			Log.startTestCase("Audio");
			ExcelFile.setExcelFile("\\testData\\TestData.xlsx", "Audio");
			long nowEpochTime = Instant.now().toEpochMilli();
			String id = ExcelFile.getCellData(1,2);
			String description = ExcelFile.getCellData(1,3);
			String tags = ExcelFile.getCellData(1,4);
			String subject = ExcelFile.getCellData(1,5);
			String coverpath = ExcelFile.getCellData(1,7);
			String transcriptfile = ExcelFile.getCellData(1,8);
			String wrongtranscriptfile = ExcelFile.getCellData(2,8);
			String invalidaudiofilepath = ExcelFile.getCellData(2,6);
			String audiofile = null;
			int i = 0;
			//AudioModule.audio(audiofile,"Audio"+nowEpochTime,description,tags,subject,transcriptfile,coverpath,invalidaudiofilepath,wrongtranscriptfile);
			for(i=1;i<=1;i++)
			{
				audiofile = ExcelFile.getCellData(i,6);	
				AudioModule.audio(audiofile,"Audio"+nowEpochTime,description,tags,subject,transcriptfile,coverpath,invalidaudiofilepath,wrongtranscriptfile);
			}
			for(i=1;i<=1;i++)
			{
				audiofile = ExcelFile.getCellData(i,6);	
				AudioModule.cancelaudio(audiofile,"Audio"+nowEpochTime,description,tags,subject,transcriptfile,coverpath,invalidaudiofilepath,wrongtranscriptfile);
			}
			for(i=4;i<=5;i++)
			{
				audiofile = ExcelFile.getCellData(i,6);	
				AudioModule.changeaudio(audiofile,"Audio"+nowEpochTime,description,tags,subject,transcriptfile,coverpath,invalidaudiofilepath,wrongtranscriptfile);
			}
			for(i=6;i<=6;i++)
			{
				audiofile = ExcelFile.getCellData(i,6);	
				AudioModule.audio(audiofile,"Audio"+nowEpochTime,description,tags,subject,transcriptfile,coverpath,invalidaudiofilepath,wrongtranscriptfile);
			}
			for(i=6;i<=6;i++)
			{
				audiofile = ExcelFile.getCellData(i,6);	
				AudioModule.Editaudio(audiofile,"Audio"+nowEpochTime,description,tags,subject,transcriptfile,coverpath,invalidaudiofilepath,wrongtranscriptfile);
			}
			
			
			ExcelFile.setExcelFile("\\testData\\TestData.xlsx", "b2blicense");
			//ExcelFile.setCellData(Result, RowNum, ColNum);
			Log.endTestCase("End Audio");
			}

}
