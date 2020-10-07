package com.hurix.users;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class UserStepModule extends UIElements {

	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/user.properties");

	public static void usertabusers(){
		try {
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("usertabusers_id"), "Click on user tab.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	
	public static void addnewusers(){
		try {
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("addnewusers_id"), "Click on Add user button.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	
	public static void emailidusers(String email){
		try {
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("emailidusers_id"), "email id textbox is found").clear();
			elementFinderByID(prop.getProperty("emailidusers_id"), "email id textbox is found").sendKeys(email);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

			
	public static void checkboxusers(String role){
		try {
			threadHold_2Sec();
				
			switch(role)
			{
			case "Client Admin":
				elementFinderByID(prop.getProperty("checkboxusers_id"), "Client admin is checked").click();
				break;
			case "Producer":
				elementFinderByID(prop.getProperty("checkboxusers1_id"), "Producer is checked").click();
				break;
			case "License Manager":
				elementFinderByID(prop.getProperty("checkboxusers2_id"), "License manager is checked").click();
				break;
			case "Tester":
				elementFinderByID(prop.getProperty("checkboxusers3_id"), "Tester is checked").click();
				break;
			case "Widget Author":
				elementFinderByID(prop.getProperty("checkboxusers4_id"), "Widget Author is checked").click();
				break;
			
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void resetusers(){
		try {
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("resetusers_id"), "email id textbox is found").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}		
			
	public static void invaliduser(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("invaliduser_xpath","invalid user msg display in screen"))));
			elementFinderByXpath(prop.getProperty("invaliduser_xpath"), "email id textbox is found").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	
	
	public static void useraddsave(){
		try {
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("useraddsave_id"), "Click on save button").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void reader(String url){
		Log.startTestCase("setup");
		BrowserConfigure.browserConfigure("Chrome");
		Driver.driver.navigate().to(url);
		if(Driver.driver.getCurrentUrl().contains(url)){
			Log.pass("URL Launch "+Driver.driver.getCurrentUrl());
		}else{
			Log.fail("URL NOT Launch "+Driver.driver.getCurrentUrl());
		}
		Log.endTestCase("END");
	}
			
}
