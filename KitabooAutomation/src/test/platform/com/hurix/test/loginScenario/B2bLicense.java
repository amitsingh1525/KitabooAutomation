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
import com.hurix.license.b2blicense.B2bLicenseModule;
import com.hurix.platform.loginPage.LoginModule;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;
import com.hurix.test.loginScenario.LoginTest;

public class B2bLicense {

	public static void main(String[] args) throws Exception 
	{
		
			Log.initialization("B2bLicense");
			ExcelFile.setExcelFile("\\testData\\TestData.xlsx", "Create_Kitaboo_Credential");
			int size = ExcelFile.getRowCount();
			int count=0;
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
				Log.endTestCase("End Login");
				Log.startTestCase("B2bLicense");
				ExcelFile.setExcelFile("\\testData\\TestData.xlsx", "B2BLicense");
				int licensecount = ExcelFile.getRowCount();
				for(int i=1;i<=1;i++)
				{
					long nowEpochTime = Instant.now().toEpochMilli();
					String instituteuser = ExcelFile.getCellData(i,0);
					String institutepassword = ExcelFile.getCellData(i,1);
					B2bLicenseModule.selectinstitute(instituteuser, institutepassword);
					for(j=1;j<=licensecount;j++)
					{
						String bookname = ExcelFile.getCellData(j,3);
						String accessCode = ExcelFile.getCellData(j,4);
						String groupname = ExcelFile.getCellData(j,5);
						String devicelimit = ExcelFile.getCellData(j,6);
						String deviceLicenses = ExcelFile.getCellData(j,7);
						String validity = ExcelFile.getCellData(j,8);
						String expiryDate = ExcelFile.getCellData(j,9);
						B2bLicenseModule.SelectBook(bookname,accessCode, groupname, devicelimit, deviceLicenses, validity, expiryDate);
					}
						String orderno = ExcelFile.getCellData(1,2);
						B2bLicenseModule.OrderNumber("75846");
		
					for(int k=1;k<=licensecount;k++)
					{
						
						String bookname = ExcelFile.getCellData(k,3);
						String accessCode = ExcelFile.getCellData(k,4);
						String groupname = ExcelFile.getCellData(k,5);
						String devicelimit = ExcelFile.getCellData(k,6);
						String deviceLicenses = ExcelFile.getCellData(k,7);
						String validity = ExcelFile.getCellData(k,8);
						String expiryDate = ExcelFile.getCellData(k,9);
						B2bLicenseModule.AccessCode(accessCode, groupname, devicelimit, deviceLicenses, validity, expiryDate,k, count,licensecount);
					}
					B2bLicenseModule.CompleteOrder();
			
				}
				AudioModule.logout();
		}
}

}
