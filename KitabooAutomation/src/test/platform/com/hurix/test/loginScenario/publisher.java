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
import com.hurix.subpublisher.SubpublisherModule;
import com.hurix.users.UserModule;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;

public class publisher {

	public static void main(String[] args) throws Exception 
	{
			Log.initialization("Sub_publisher_EU");
			String path="\\testData\\TestData.xlsx";
			ExcelFile.setExcelFile(path, "Sub_Publisher");
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
				Log.startTestCase("Sub_publisher_EU");
				ExcelFile.setExcelFile(path, "Sub_Publisher");
				int usersize = ExcelFile.getRowCount();
				long nowEpochTime = Instant.now().toEpochMilli();
				for(int i=1;i<=usersize;i++)
				{
				Log.info("--------------------------------------------------------------------------");
				String organization = ExcelFile.getCellData(i,3); 
				String firstname = ExcelFile.getCellData(i,4);
				String lastname = ExcelFile.getCellData(i,5);
				String email = "subEU"+ExcelFile.getCellData(i,6);
				String startdate = ExcelFile.getCellData(i,7);
				String enddate = ExcelFile.getCellData(i,8);
				String street = ExcelFile.getCellData(i,9);
				String city = ExcelFile.getCellData(i,10);
				String zip = ExcelFile.getCellData(i,11);
				String contact = ExcelFile.getCellData(i,12);
				String uploadlogo = ExcelFile.getCellData(i,13);
				Log.info(email);
				SubpublisherModule.createsubpublisher(organization, firstname, lastname, email, startdate, enddate, street, city, zip, contact, uploadlogo);
				SubpublisherModule.saveuser(email);
				}
				
				ExcelFile.setExcelFile(path, "Create_Kitaboo_Credential");
				
				Log.endTestCase("End");
				}
			//	AudioModule.logout();
		}
}
