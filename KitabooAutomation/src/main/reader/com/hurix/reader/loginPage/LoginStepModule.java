package com.hurix.reader.loginPage;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class LoginStepModule extends UIElements {

	private static Properties prop = getProperty("D:/Kitaboo Automation/KitabooAutomation/KitabooAutomation/config/reader/loginPage.properties");

	public static void txtUsername(String username){
		try {
			elementFinderByID(prop.getProperty("username_id"), "txtUserName").clear();
			elementFinderByID(prop.getProperty("username_id"), "txtUserName").sendKeys(username);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtPassword(String password){
		try {
			elementFinderByID(prop.getProperty("password_id"), "txtPassword").clear();
			elementFinderByID(prop.getProperty("password_id"), "txtPassword").sendKeys(password);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnShowpassword(){
		try {
			elementFinderByID(prop.getProperty("showPAssword_btn_id"), "Show Password").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnLogin(){
		try {
			elementFinderByXpath(prop.getProperty("loginbtn_xpath"), "Login btn").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static String getinvalidusernamePasswordmsg(){
		String msg = null;
		try {
			msg = elementFinderByXpath(prop.getProperty("invalidusernamePassword_visibletext_xpath"), "invalid username password massage").getText();

		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return msg;
	}

	public static void chkRememberme(){
		try {
			elementFinderByXpath(prop.getProperty("remebermechk_xpath"), "Rememberme chk").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnDesktopInstaller(){
		try {
			elementFinderByXpath(prop.getProperty("desktopInstaller_btn_xpath"), "desktop installer").click();
			elementFinderByXpath(prop.getProperty("downloadzip_visibletext_xpath"), "Download .zip").click();
			elementFinderByID(prop.getProperty("installerCancel_btn_id"), "Installer cancel btn").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnIOSInstaller(){
		try {
			elementFinderByXpath(prop.getProperty("IOSInstaller_btn_xpath"), "IOS installer").click();
			windowhandle();
			String ebookreader = elementFinderByXpath(prop.getProperty("IOSebookreader_xpath"), "IOS kitaboo ebookreader").getText();
			if(ebookreader.equalsIgnoreCase("Kitaboo eBook Reader")) {
				Log.info("Kitaboo eBook reader is found on Apple store");
			}
			else {
				Log.error("Kitaboo eBook reader is not found on Apple store");
			}
			Driver.driver.close();
			windowhandle();
			
			
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnAndroidInstaller(){
		try {
			elementFinderByXpath(prop.getProperty("androidInstaller_btn_xpath"), "Android installer").click();
			windowhandle();
			String ebookreader = elementFinderByXpath(prop.getProperty("androideBookreader_xpath"), "Apple store kitaboo ebookreader").getText();
			if(ebookreader.equalsIgnoreCase("Kitaboo eBook Reader")) {
				Log.info("Kitaboo eBook reader is found on Google play store");
			}
			else {
				Log.error("Kitaboo eBook reader is not found on Google play store");
			}
			Driver.driver.close();
			windowhandle();
			
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnWindowsInstaller(){
		try {
			elementFinderByXpath(prop.getProperty("windowsInstaller_btn_xpath"), "Windows installer").click();
			windowhandle();
			elementFinderByXpath(prop.getProperty("windowsCancelbtn_xpath"), "Popup cancel btn").click();
			String ebookreader = elementFinderByID(prop.getProperty("windowsebookreader_id"), "windows installer kitaboo ebookreader").getText();
			if(ebookreader.equalsIgnoreCase("Kitaboo eBook Reader")) {
				Log.info("Kitaboo eBook reader is found on Windows store");
			}
			else {
				Log.error("Kitaboo eBook reader is not found on Windows store");
			}
			Driver.driver.close();
			windowhandle();
			
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void linkForgotPassword(){
		try {
			elementFinderByXpath(prop.getProperty("forgetpassword_visiblelink_xpath"), "Forgot Password").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtForgotUsername(String username){
		try {
			elementFinderByID(prop.getProperty("forgetusername_txt_id"), "Forgot username").clear();
			elementFinderByID(prop.getProperty("forgetusername_txt_id"), "Forgot username").sendKeys(username);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnSubmit(){
		try {
			elementFinderByXpath(prop.getProperty("submitbtn_xpath"), "Submit btn").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnCancel(){
		try {
			elementFinderByXpath(prop.getProperty("cancelbtn_xpath"), "Cancel btn").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnOk(){
		try {
			elementFinderByXpath(prop.getProperty("okbtn_xpath"), "Ok btn").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnAccesscode(){
		try {
			elementFinderByLinkText(prop.getProperty("accesscode_linktext"), "Accesscode link").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtAccesscodeUsername(String accesscode){
		try {
			elementFinderByID(prop.getProperty("accesscode_txt_id"), "Accesscode username").clear();
			elementFinderByID(prop.getProperty("accesscode_txt_id"), "Accesscode username").sendKeys(accesscode);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnSend(){
		try {
			elementFinderByID(prop.getProperty("sendbtn_xpath"), "Send btn").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static String getinvalidacccesscodemsg(){
		String msg = null;
		try {
			msg = elementFinderByID(prop.getProperty("invalidaccesscode_visibletext_id"), "invalid Access code massage").getText();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return msg;
	}

	public static void txtfullname(String fullname){
		try {
			elementFinderByXpath(prop.getProperty("fullname_txt_xpath"), "Fullname txt").clear();
			elementFinderByXpath(prop.getProperty("fullname_txt_xpath"), "Fullname txt").sendKeys(fullname);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtemailid(String emailid){
		try {
			elementFinderByXpath(prop.getProperty("emailid_txt_xpath"), "emailid txt").clear();
			elementFinderByXpath(prop.getProperty("emailid_txt_xpath"), "emailid txt").sendKeys(emailid);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtpassword(String password){
		try {
			elementFinderByXpath(prop.getProperty("password_txt_xpath"), "password txt").clear();
			elementFinderByXpath(prop.getProperty("password_txt_xpath"), "password txt").sendKeys(password);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtconfirmpassword(String password){
		try {
			elementFinderByXpath(prop.getProperty("confirmpassword_txt_xpath"), "confirmpassword txt").clear();
			elementFinderByXpath(prop.getProperty("confirmpassword_txt_xpath"), "confirmpassword txt").sendKeys(password);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btncancel(){
		try {
			elementFinderByLinkText(prop.getProperty("cancelbtn_linktext"), "cancel btn").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnsubmitaccesscode(){
		try {
			elementFinderByLinkText(prop.getProperty("submitbtn_linktext"), "submit btn").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static String getexistsemailidmsg(){
		String msg = null;
		try {
			msg = elementFinderByXpath(prop.getProperty("emailidexists_visibletext_xpath"), "email id already exists").getText();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return msg;
	}

	public static void chkacceptcondition(){
		try {
			elementFinderByXpath(prop.getProperty("acceptcondition_chk_xpath"), "Accept condition").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void linktermsandCondition(){
		try {
			elementFinderByXpath(prop.getProperty("termsandcondition_link_xpath"), "Terms and condition").click();
			windowhandle();
			if(Driver.driver.getCurrentUrl().contains("https://kitaboo.com/terms-and-conditions/")){
				Log.pass("After click on Terms and condition redirect to this page"+Driver.driver.getCurrentUrl());
			}else{
				Log.fail("Expected URL contain terms and condition but found "+Driver.driver.getCurrentUrl());
			}
			Driver.driver.close();
			windowhandle();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void linkprivacypolicy(){ //
		try {
			elementFinderByXpath(prop.getProperty("privacypolicy_link_xpath"), "Privacy Policy").click();
			windowhandle();
			if(Driver.driver.getCurrentUrl().contains("https://kitaboo.com/privacy-policy/")){
				Log.pass("After click on privacy policy redirect to this page"+Driver.driver.getCurrentUrl());
			}else{
				Log.fail("Expected URL contain privacy policy but found "+Driver.driver.getCurrentUrl());
			}
			Driver.driver.close();
			windowhandle();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}




}
