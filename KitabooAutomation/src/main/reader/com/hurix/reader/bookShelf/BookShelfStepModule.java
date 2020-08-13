package com.hurix.reader.bookShelf;

import java.util.Properties;

import com.hurix.automation.utility.UIElements;

public class BookShelfStepModule extends UIElements{

	
	private static Properties prop = getProperty("C:/Users/amit.singh/git/KitabooAutomation/KitabooAutomation/config/platform/loginPage.properties");

	public static void txtUsername(String username){
		
		try {
			elementFinderByID(prop.getProperty("username_id"), "txtUserName").clear();
			elementFinderByID(prop.getProperty("username_id"), "txtUserName").sendKeys(username);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
}
