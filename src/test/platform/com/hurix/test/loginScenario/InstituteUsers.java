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
import com.hurix.institute.users.InstituteUserModule;
import com.hurix.platform.loginPage.LoginModule;
import com.hurix.users.UserModule;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;

public class InstituteUsers {

	public static void main(String[] args) throws Exception 
	{
			Log.initialization("Institute Users");
			String path="\\testData\\TestData.xlsx";
			ExcelFile.setExcelFile(path, "Institute_Account");
			int size = ExcelFile.getRowCount();
			for(int j=1;j<=1;j++)
			{
				String username = ExcelFile.getCellData(j, 0);
				String password = ExcelFile.getCellData(j, 1);
				String url = ExcelFile.getCellData(j, 2);
				Log.info(username);
				Log.info(password);
				Log.info(url);
				File file = new File("");
				Log.startTestCase("Login");
				BrowserConfigure.browserConfigure("Chrome");
				Driver.driver.navigate().to(url);
				LoginModule.loginScenario(username, password);
				//AudioStepModule.popupmsg();
				Log.endTestCase("End Login");
				Log.startTestCase("Institute Users");
				int usersize = ExcelFile.getRowCount();
				long nowEpochTime = Instant.now().toEpochMilli();
				for(int i=513;i<=usersize;i++)
				{
				String usernm = ExcelFile.getCellData(i,3);
				String passwd = ExcelFile.getCellData(i,4);
				String email = "Insti"+ExcelFile.getCellData(i,5);
				Log.info(email);
				Log.info("---------------------------------------------------------------");
				InstituteUserModule.createusers(email, passwd);
				for(int k=1;k<=2;k++)
				{
				String role = ExcelFile.getCellData(k,6);
				InstituteUserModule.selectrole(role);
				}
				InstituteUserModule.saveuser(email);
				
				}
				ExcelFile.setExcelFile(path, "Institute_Account");
				
				Log.endTestCase("End");
				}
				InstituteUserModule.logout();
				for(int r=0;r<=size;r++)
				{
					String username = ExcelFile.getCellData(r, 4);
					String password = ExcelFile.getCellData(r, 5);
					String readerurl = ExcelFile.getCellData(1, 7);
					Log.info(username);
					Log.info(password);
					Log.info(readerurl);
					InstituteUserModule.reader(readerurl, username, password);
				}
		}
}
