package com.hurix.reader.loginPage;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class LoginStepModule extends UIElements {

	public static Properties prop = getProperty(System.getProperty("user.dir")+"/config/reader/loginPage.properties");

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
			System.out.println("Element not present.");
		}
	}

	public static void btnShowpassword(){
		try {
			elementFinderByID(prop.getProperty("showPAssword_btn_id"), "Click on show password.").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnLogin(){
		try {
			elementFinderByXpath(prop.getProperty("loginbtn_xpath"), "Click on login").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static String getinvalidusernamePasswordmsg(){
		String msg = null;
		try {
			msg = elementFinderByXpath(prop.getProperty("invalidusernamePassword_visibletext_xpath"), "Getting invalid username message.").getText();

		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return msg;
	}

	public static void chkRememberme(){
		try {
			elementFinderByXpath(prop.getProperty("remebermechk_xpath"), "Click on remember me.").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnDesktopInstaller(){
		try {
			elementFinderByXpath(prop.getProperty("desktopInstaller_btn_xpath"), "Click on desktop installer.").click();
			elementFinderByXpath(prop.getProperty("downloadzip_visibletext_xpath"), "Click on Download .zip").click();
			elementFinderByID(prop.getProperty("installerCancel_btn_id"), "Click on Installer cancel").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnIOSInstaller(){
		try {
			String parent_Window = Driver.driver.getWindowHandle();
			elementFinderByXpath(prop.getProperty("IOSInstaller_btn_xpath"), "Click on IOS installer").click();
			windowhandle();
			String ebookreader = elementFinderByXpath(prop.getProperty("IOSebookreader_xpath"), "Getting text IOS kitaboo ebookreader").getText();
			if(ebookreader.equalsIgnoreCase("Kitaboo eBook Reader 4+")) {
				Log.pass("Kitaboo eBook reader is found on Apple store");
			}
			else {
				Log.fail("Kitaboo eBook reader is not found on Apple store");
			}
			Driver.driver.close();
			Driver.driver.switchTo().window(parent_Window);
			
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnAndroidInstaller(){
		try {
			String parent_Window = Driver.driver.getWindowHandle();
			elementFinderByXpath(prop.getProperty("androidInstaller_btn_xpath"), "Clickk on Android installe.r").click();
			windowhandle();
			String ebookreader = elementFinderByXpath(prop.getProperty("androideBookreader_xpath"), "Getting text Apple store kitaboo ebookreader.").getText();
			if(ebookreader.equalsIgnoreCase("Kitaboo eBook Reader")) {
				Log.pass("Kitaboo eBook reader is found on Google play store");
			}
			else {
				Log.fail("Kitaboo eBook reader is not found on Google play store");
			}
			Driver.driver.close();
			Driver.driver.switchTo().window(parent_Window);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnWindowsInstaller(){
		try {
			String parent_Window = Driver.driver.getWindowHandle();
			elementFinderByXpath(prop.getProperty("windowsInstaller_btn_xpath"), "Click on Windows installer.").click();
			windowhandle();
			elementFinderByXpath(prop.getProperty("windowsCancelbtn_xpath"), "Click on Popup close.").click();
			String ebookreader = elementFinderByID(prop.getProperty("windowsebookreader_id"), "Getting text windows installer kitaboo ebookreader.").getText();
			if(ebookreader.equalsIgnoreCase("Kitaboo eBook Reader")) {
				Log.pass("Kitaboo eBook reader is found on Windows store");
			}
			else {
				Log.fail("Kitaboo eBook reader is not found on Windows store");
			}
			Driver.driver.close();
			Driver.driver.switchTo().window(parent_Window);
			
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void linkForgotPassword(){
		try {
			elementFinderByXpath(prop.getProperty("forgetpassword_visiblelink_xpath"), "Click on Forgot Password").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtForgotUsername(String username){
		try {
			elementFinderByID(prop.getProperty("forgetusername_txt_id"), "Clear Forgot username textbox.").clear();
			elementFinderByID(prop.getProperty("forgetusername_txt_id"), "Entered Forgot username i.e '"+username+"'.").sendKeys(username);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnForgetSubmit(){
		try {
			elementFinderByXpath(prop.getProperty("forgetSubmitbtn_xpath"), "Click on submit.").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnCancel(){
		try {
			elementFinderByXpath(prop.getProperty("cancelbtn_xpath"), "Click on cancel.").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnOk(){
		try {
			elementFinderByXpath(prop.getProperty("okbtn_xpath"), "Click on Ok.").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnAccesscode(){
		try {
			elementFinderByXpath(prop.getProperty("accesscode_xpath"), "Click on accesscode.").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtAccesscodeUsername(String accesscode){
		try {
			elementFinderByID(prop.getProperty("accesscode_txt_id"), "Clear accesscode textbox.").clear();
			elementFinderByID(prop.getProperty("accesscode_txt_id"), "Entered accesscode in the textbox i.e '"+accesscode+"").sendKeys(accesscode);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnSend(){
		try {
			elementFinderByXpath(prop.getProperty("sendbtn_xpath"), "Click on send.").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static String getinvalidacccesscodemsg(){
		String msg = null;
		try {
			msg = elementFinderByID(prop.getProperty("invalidaccesscode_visibletext_id"), "Getting text invalid Access code massage").getText();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return msg;
	}

	public static void txtfullname(String fullname){
		try {
			elementFinderByXpath(prop.getProperty("fullname_txt_xpath"), "Clear full name textbox.").clear();
			elementFinderByXpath(prop.getProperty("fullname_txt_xpath"), "Entered fullname i.e '"+fullname+"'.").sendKeys(fullname);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtemailid(String emailid){
		try {
			elementFinderByXpath(prop.getProperty("emailid_txt_xpath"), "Clear emailid textbox.").clear();
			elementFinderByXpath(prop.getProperty("emailid_txt_xpath"), "Entered email id i.e '"+emailid+"'.").sendKeys(emailid);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtNewUserPassword(String password){
		try {
			elementFinderByXpath(prop.getProperty("password_txt_xpath"), "Clear password textbox.").clear();
			elementFinderByXpath(prop.getProperty("password_txt_xpath"), "Entered password i.e '"+password+"'.").sendKeys(password);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtconfirmpassword(String password){
		try {
			elementFinderByXpath(prop.getProperty("confirmpassword_txt_xpath"), "Clear confirm password textbox.").clear();
			elementFinderByXpath(prop.getProperty("confirmpassword_txt_xpath"), "Entered confirm password i.e '"+password+"'.").sendKeys(password);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btncancel(){
		try {
			elementFinderByLinkText(prop.getProperty("cancelbtn_linktext"), "Click on cancel.").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnsubmitaccesscode(){
		try {
			elementFinderByXpath(prop.getProperty("submitbtn_xpath"), "Click on submit.").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static String getexistsemailidmsg(){
		String msg = null;
		try {
			msg = elementFinderByXpath(prop.getProperty("emailidexists_visibletext_xpath"), "Getting text email id already exists.").getText();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return msg;
	}
	
	public static String getinvalidForgetEmailmsg(){
		String msg = null;
		try {
			msg = elementFinderByID(prop.getProperty("invalidForgetEmailID_id"), "Getting text Forget Email ID entered is incorrect.").getText();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return msg;
	}
	
	public static String getForgetmsg(){
		String msg = null;
		try {
			msg = elementFinderByXpath(prop.getProperty("resetconfirm_visibletext_xpath"), "Forget msg found.").getText();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return msg;
	}

	public static void chkacceptcondition(){
		try {
			elementFinderByXpath(prop.getProperty("acceptcondition_chk_xpath"), "Click on accept condition checkbox.").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void linktermsandCondition(){
		try {
			elementFinderByXpath(prop.getProperty("termsandcondition_link_xpath"), "Click on Terms and condition.").click();
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
			elementFinderByXpath(prop.getProperty("privacypolicy_link_xpath"), "Click on Privacy Policy.").click();
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
