package com.hurix.license.b2blicense;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hurix.asset.audio.AudioStepModule;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.ExcelFile;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class B2bLicenseModule extends B2bLicenseStepModule
{

	public static void logout()
	{
		Log.startTestCase("Logout");
		if(Driver.driver.getCurrentUrl().contains("books.xhtml"))
		{
			AudioStepModule.btnlogout();
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());

		}
		Log.endTestCase("End");
	}
	
	public static void selectinstitute(String instituteuser,String institutepassword)
	{
		Log.startTestCase("SelectInstitute");
		linkLicenses();
		if(Driver.driver.getCurrentUrl().contains("order.xhtml"))
		{
			txtsearchtxtboxInstituteLicene(instituteuser);
			btnsearchInstituteFrLicense();
			btnradiobuttonlicense();
			btnissueenablebuttonlicense1();
		}
		else
		{
			Log.fail("Expected URL contain 'order.xhtml' but found "+Driver.driver.getCurrentUrl());

		}
		Log.endTestCase("End");
	}

	public static void SelectBook(String bookname,String accessCode, String groupname, String devicelimit,
			String deviceLicenses, String validity, String expiryDate)
	{
		Log.startTestCase("SelectBook");
		
		if(Driver.driver.getCurrentUrl().contains("order.xhtml"))
		{
			txtorderBookSearchText(bookname);
			btnorderBookSearch();
			chkfirstcheckboxlicense();
			btnresetbuttonlicense();
		}
		else
		{
			Log.fail("Expected URL contain 'order.xhtml' but found "+Driver.driver.getCurrentUrl());

		}
		Log.endTestCase("End");
	}

	public static void OrderNumber(String orderno)
	{
		Log.startTestCase("OrderNumber");
		
		if(Driver.driver.getCurrentUrl().contains("order.xhtml"))
		{
			btnIssuebuttonLicense();
			txtorderNolicense(orderno);
		}
		else
		{
			Log.fail("Expected URL contain 'order.xhtml' but found "+Driver.driver.getCurrentUrl());

		}
		Log.endTestCase("End");
	}

	public static void AccessCode(String accessCode, String groupname, String devicelimit,
			String deviceLicenses, String validity, String expiryDate, int k, int count, int licensecount)
	{
		
		k=k-1;
		Log.startTestCase("AccessCode");
		if(Driver.driver.getCurrentUrl().contains("order.xhtml"))
		{
			if(accessCode.equalsIgnoreCase("Individual"))
			{
				drpaccesscodelicense(k);
				accesscodeindividuallicense(k);
				licenselimitelicense(deviceLicenses, k);
			}
			else if(accessCode.equalsIgnoreCase("Group"))
			{
				drpaccesscodelicense(k);
				accesscodegrouplicense(k);
				accesscodegroupname(groupname,k);
				licensegrouplimitelicense(deviceLicenses, k);
			}
			else if(accessCode.equalsIgnoreCase("Group Licenses"))
			{
				drpaccesscodelicense(k);
				accesscodegrouplicensinglicense(k);
				licenselimitegrouplicense(deviceLicenses, k);
			}
			
			devicelimitelicense(devicelimit, k);
			
			if(validity.equalsIgnoreCase("Limited-Redemption"))
			{
				validitydropdownlicense(k);
				validityRedemptionlicense(k);
				expiredateredemlicense(expiryDate, k);
			}
			else if(validity.equalsIgnoreCase("Limited-Activation"))
			{
				validitydropdownlicense(k);
				validityactivatelicense(k);
				expiredatelicense(expiryDate,k);
			}
			else if(validity.equalsIgnoreCase("Perpetual"))
			{
				validitydropdownlicense(k);
				validityPerpetuallicense(k);
			}
				
			 count=count+1;
			 if(count!=10 && count%10==0 && count!=licensecount)
			 {
				 btnnavigationorderlicense();
			 }
		}
		else
		{
			Log.fail("Expected URL contain 'order.xhtml' but found "+Driver.driver.getCurrentUrl());
		}
		Log.endTestCase("End");
	}

	//*[@id='orderConfirmationTable:"+k+":order_validity_2']
	public static void CompleteOrder()
	{
		
		Log.startTestCase("CompleteOrder");
		if(Driver.driver.getCurrentUrl().contains("order.xhtml"))
		{
		btncompleteOrderlicense();	
		btndownloadOrderlicense();
		}
		else
		{
			Log.fail("Expected URL contain 'order.xhtml' but found "+Driver.driver.getCurrentUrl());
		}
		Log.endTestCase("End");
	}
	
	




}