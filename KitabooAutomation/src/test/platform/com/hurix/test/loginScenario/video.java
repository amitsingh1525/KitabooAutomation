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
import com.hurix.asset.video.VideoModule;
import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.ExcelFile;
import com.hurix.automation.utility.Log;
import com.hurix.platform.loginPage.LoginModule;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;

public class video {

	public static void main(String[] args) throws Exception 
	{
			Log.initialization("Video Book");
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
				Log.startTestCase("Video");
				ExcelFile.setExcelFile(path, "Video");
				int size1 = ExcelFile.getRowCount();
				for(int i=1;i<=size1;i++)
				{
				long nowEpochTime = Instant.now().toEpochMilli();
				String VideoTitle = ExcelFile.getCellData(i,0);
				String VideoDesription = ExcelFile.getCellData(i,1);
				String VideoTag = ExcelFile.getCellData(i,2);
				String VideoCat = ExcelFile.getCellData(i,3);
				String videopath = ExcelFile.getCellData(i,4);
				String coverpath = ExcelFile.getCellData(i,5);
				String changevideo = ExcelFile.getCellData(i,6);
				String chaptername = ExcelFile.getCellData(i,7);
				String chapterdesc = ExcelFile.getCellData(i,8);
			
				VideoModule.video(videopath, VideoTitle+nowEpochTime, VideoDesription, VideoTag, VideoCat, coverpath, chaptername, chapterdesc);
				}
				
				ExcelFile.setExcelFile(path, "Create_Kitaboo_Credential");
				
				Log.endTestCase("End Audio");
				}
			//	AudioModule.logout();
		}
}
