package com.hurix.institute.users;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hurix.asset.audio.AudioStepModule;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;
import com.hurix.platform.loginPage.LoginStepModule;
import com.hurix.reader.bookShelf.BookShelfModule;
import com.hurix.reader.loginPage.LoginModule;

public class InstituteUserModule extends InstituteUserStepModule
{
	
	public static void logout()
	{
		Log.startTestCase("Logout");
		if(Driver.driver.getCurrentUrl().contains("user.xhtml"))
		{
			AudioStepModule.btnlogout();
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}
		Log.endTestCase("End");
	}
	
	public static void popup()
	{
		Log.startTestCase("Logout");
		if(Driver.driver.getCurrentUrl().contains("user.xhtml"))
		{
			AudioStepModule.popupmsg();
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		}
		Log.endTestCase("End");
	}

	
	public static void createusers(String email,String password)
	{
		instituteuser();
		adduser();
		emailidusers(email);
		Password(password);
		
	}
	
	

	public static void selectrole(String role)
	{
		checkboxusers(role);
		
		
	}
	public static void saveuser(String email)
	{		
		save();
		WebDriverWait wait = new WebDriverWait(Driver.driver, 10); 
		try
		{
			UIElements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mesId")));
			String msg= Driver.driver.findElement(By.id("mesId")).getText();
			if(msg.contains("successfully")){
				Log.info("email:"+email+ " User created with msg: '"+msg+"'");
			}else{
				Log.fail("email:"+email+ " User NOT created with msg: '"+msg+"'");
			}
		
		}
		catch(Exception e)
		{
			Log.fail("Fail");
		}
		 
	}
	
	public static void reader(String readerurl,String password,String username)
	{
		Log.info("---------------------------------------------------------------");
		Driver.driver.navigate().to(readerurl);
		LoginModule.userLogin("Insti"+username, password);
		InstituteUserStepModule.skip();
		BookShelfModule.userLogOut();
		Log.info("---------------------------------------------------------------");
	
	}
		
	}