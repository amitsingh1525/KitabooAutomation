package com.hurix.subpublisher;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hurix.asset.audio.AudioStepModule;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class SubpublisherModule extends SubpublisherStepModule
{
	
	private static final String firstname = null;

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
			
	public static void createsubpublisher(String organization,
			String firstname, String lastname, String email, String startdate,
			String enddate, String street, String city, String zip, String contact,
			String uploadlogo) 
	{
		
		linkSubPublisher();
		btnCreateAccount();
		txtbxOrganization(organization);
		txtbxAdministratorFirstName(firstname);
		txtbxAdministratorLastName(lastname);
		txtbxAdminEmailId(email);
		selectdate(startdate, enddate);
		txtbxStreet(street);
		txtbxCity(city);
		txtbxZip(zip);
		txtbxContact(contact);
		//drpdwnCountry();
		btnBrowsefile(uploadlogo);
			
	}
	
	public static void saveuser(String email)
	{		
		btnSave();
		WebDriverWait wait = new WebDriverWait(Driver.driver, 10); 
		try
		{
			UIElements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mesId")));
			String msg= Driver.driver.findElement(By.id("mesId")).getText();
			if(msg.contains("Successfully")){
				Log.info("email:"+email+ "User created with msg: '"+msg+"'");
			}else{
				Log.fail("email:"+email+ "User NOT created with msg: '"+msg+"'");
			}
		}
		catch(Exception e)
		{
			Log.fail("Fail");
		}
		 
	}

	
	
	
	
}