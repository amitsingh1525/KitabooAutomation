package com.hurix.institute.users;

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

public class InstituteUserStepModule extends UIElements {

	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/user.properties");


	public static void instituteuser(){
		try {
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("user_id"), "Click on Institute user tab.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	
	public static void adduser(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("Addnewuser_xpath"), "Click on Add user button.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	
	public static void emailidusers(String email){
		try {
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("emailid_id"), "email id textbox is found").clear();
			elementFinderByID(prop.getProperty("emailid_id"), "email id textbox is found").sendKeys(email);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	
	public static void Password(String password){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("Password_xpath"), "password textbox is found").clear();
			elementFinderByXpath(prop.getProperty("Password_xpath"), "password textbox is found").sendKeys(password);
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}		
	
	
	public static void checkboxusers(String role){
		try {
			threadHold_2Sec();
				
			switch(role)
			{
			case "Learner":
				elementFinderByID(prop.getProperty("learner_id"), "Learner is checked").click();
				break;
			case "Instructor":
				elementFinderByID(prop.getProperty("instructor_id"), "Instructor is checked").click();
				break;
			case "Distributor":
				elementFinderByID(prop.getProperty("distributor_id"), "Distributor is checked").click();
				break;
			case "Institute Admin":
				elementFinderByID(prop.getProperty("instituteadmin_id"), "Institute Admin is checked").click();
				break;
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void save(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("save_xpath"), "save button is found").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}		
		
	
	public static void skip()
	{
		try {
			
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("skip_xpath"), "skip button is found and click").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	
}
