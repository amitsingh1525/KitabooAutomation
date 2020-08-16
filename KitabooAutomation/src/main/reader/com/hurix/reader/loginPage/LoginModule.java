package com.hurix.reader.loginPage;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;

public class LoginModule extends LoginStepModule {

	public static void TC_InvalidUserNameANDPassword(String username, String password){

		txtUsername(username);
		txtPassword(password);
		btnLogin();
		threadHold_5Sec();
		String msg = getinvalidusernamePasswordmsg();
		if(msg.equalsIgnoreCase("Username or Password entered is incorrect")) {
			Log.info("Username or Password entered is incorrect");
		}
		else {
			Log.error("Username or Password entered is correct");
		}	
	}

	public static void TC_RememberMe(String username, String password){

		txtUsername(username);
		txtPassword(password);
		chkRememberme();
		btnLogin();
		threadHold_5Sec();
		// change the code
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("profileIcon")));
		Driver.driver.findElement(By.id("profileIcon")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signOutButton")));
		Driver.driver.findElement(By.id("signOutButton")).click();

		btnLogin();
		threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("bookshelf")){
			Log.pass("After successfully login redirected to the bookshelf"+Driver.driver.getCurrentUrl());
		}else{
			Log.fail("Expected URL contain bookshelf but found "+Driver.driver.getCurrentUrl());
		}

	}

	public static  void TC_DownloadForDesktop(){

		btnDesktopInstaller();
	}

	public static  void TC_AvailableOnTheAppleStore() throws InterruptedException {

		btnIOSInstaller();
	}

	public static  void TC_GetItOnGooglePlay() throws InterruptedException{

		btnAndroidInstaller();	
	}

	public static  void TC_DownlaodFromWindowsStore() throws InterruptedException{

		btnWindowsInstaller();
	}
	
	public static  void TC_PrivacyPolicy(String accesscode){

		btnAccesscode();
		txtAccesscodeUsername(accesscode);
		btnSend();
		linkprivacypolicy();
	}	
	
	public static  void TC_TermsAndCondition(String accesscode){

		btnAccesscode();
		txtAccesscodeUsername(accesscode);
		btnSend();
		linktermsandCondition();
	}	

	public static  void TC_InvalidAccessCodeSignup(String invalidaccesscode){

		btnAccesscode();
		txtAccesscodeUsername(invalidaccesscode);
		btnSend();
		threadHold_5Sec();
		String msg = getinvalidacccesscodemsg();
		if(msg.equalsIgnoreCase("Invalid Access Code entered")) {
			Log.info("Accesscode entered is invalid");
		}
		else {
			Log.error("Accesscode entered is valid");
		}	
	}	
	
	public static  void TC_AccessCodeSignupByUsedEmailID(String accesscode, String fullname, String usedemailid, String password, String confirmpassword){

		btnAccesscode();
		txtAccesscodeUsername(accesscode);
		btnSend();
		txtfullname(fullname);
		txtemailid(usedemailid);
		txtpassword(password);
		txtconfirmpassword(confirmpassword);
		chkacceptcondition();
		btnsubmitaccesscode();
		threadHold_5Sec();
		String msg = getexistsemailidmsg();
		if(msg.equalsIgnoreCase("Entered Email Id already exists")) {
			Log.info("Entered Email Id already exists");
		}
		else {
			Log.error("Entered Email Id is new");
		}	
	}	
	
	public static  void TC_AccessCodeSignup(String accesscode, String fullname, String emailid, String password, String confirmpassword){

		btnAccesscode();
		txtAccesscodeUsername(accesscode);
		btnSend();
		txtfullname(fullname);
		txtemailid(emailid);
		txtpassword(password);
		txtconfirmpassword(confirmpassword);
		chkacceptcondition();
		btnsubmitaccesscode();
		threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("bookshelf")){
			Log.pass("After successfully login redirected to the bookshelf"+Driver.driver.getCurrentUrl());
			//	change code
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("profileIcon")));
			Driver.driver.findElement(By.id("profileIcon")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signOutButton")));
			Driver.driver.findElement(By.id("signOutButton")).click();

		}else{
			Log.fail("Expected URL contain bookshelf but found "+Driver.driver.getCurrentUrl());
		}
	}	

	public static void TC_LoginWithValidCredential(String username, String password){

		txtUsername(username);
		txtPassword(password);
		btnLogin();
		threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("bookshelf")){
			Log.pass("After successfully login redirected to the bookshelf"+Driver.driver.getCurrentUrl());
		}else{
			Log.fail("Expected URL contain bookshelf but found "+Driver.driver.getCurrentUrl());
		}
	}





}
