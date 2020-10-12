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

public class Users {

	public static void main(String[] args) throws Exception 
	{
			Log.initialization("Users");
			String path="\\testData\\TestData.xlsx";
			ExcelFile.setExcelFile(path, "Users");
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
				Log.startTestCase("Users");
				ExcelFile.setExcelFile(path, "Users");
				int usersize = ExcelFile.getRowCount();
				long nowEpochTime = Instant.now().toEpochMilli();
				for(int i=566;i<=usersize;i++)
				{
				String email = "U"+ExcelFile.getCellData(i,3);
				Log.info(email);
				UserModule.createusers(email);
				for(int k=1;k<=5;k++)
				{
				String role = ExcelFile.getCellData(k,4);
				UserModule.selectrole(role);
				}
				UserModule.saveuser(email);
				}
				
				ExcelFile.setExcelFile(path, "Create_Kitaboo_Credential");
				
				Log.endTestCase("End Audio");
				}
			//	AudioModule.logout();
		}
}
