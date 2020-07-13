package com.hurix.com.platform.loginPage;

import java.util.Properties;

import com.hurix.automation.utility.UIElements;

public class LoginStepModule extends UIElements {

	private static Properties prop = getProperty("D:/eclipseWorkSpace/KitabooAutomation/config/platform/loginPage.properties");
	
	public static void txtUsername(String username){
		elementFinderByID(prop.getProperty("username_id"), "txtUserName").sendKeys(username);
	}
	
	public static void txtPassword(String password){
		elementFinderByID(prop.getProperty("password_id"), "txtPassword").sendKeys(password);
	}
	
	public static void btnLogin(){
		elementFinderByID(prop.getProperty("loginbtn_id"), "Login btn").click();
	}
}
