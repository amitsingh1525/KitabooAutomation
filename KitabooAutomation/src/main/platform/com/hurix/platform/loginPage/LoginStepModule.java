package com.hurix.platform.loginPage;

import java.util.Properties;

import com.hurix.automation.utility.UIElements;

public class LoginStepModule extends UIElements {

	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/loginPage.properties");
	
	public static void txtUsername(String username){
		try {
			elementFinderByID(prop.getProperty("username_id"), "Clear username textbox.").clear();
			elementFinderByID(prop.getProperty("username_id"), "Entered username in the textbox i.e: '"+username+"'.").sendKeys(username);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtPassword(String password){
		try {
			elementFinderByID(prop.getProperty("password_id"), "Clear password textbox.").clear();
			elementFinderByID(prop.getProperty("password_id"), "Entered password in the textbox i.e: '"+password+"'.").sendKeys(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
	
	public static void btnLogin(){
		try {
			elementFinderByID(prop.getProperty("loginbtn_id"), "Login btn clicked").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
}
