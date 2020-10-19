package com.hurix.reader.loginPage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.hurix.automation.utility.Driver;

public class LoginModule extends LoginStepModule {

	public static String userLogin(String username, String password){

		txtUsername(username);
		txtPassword(password);
		btnLogin();
		threadHold_5Sec();
		return Driver.driver.getCurrentUrl();
	}

	public static String accessCodeSignup(String accesscode, String fullname, String usedemailid, String password, String confirmpassword){

		String msg = null;
		btnAccesscode();
		txtAccesscodeUsername(accesscode);
		btnSend();
		threadHold_5Sec();
		threadHold_2Sec();
		if(Driver.driver.findElement(By.xpath(prop.getProperty("fullname_txt_xpath"))).isDisplayed()){
			txtfullname(fullname);
			txtemailid(usedemailid);
			txtNewUserPassword(password);
			txtconfirmpassword(confirmpassword);
			chkacceptcondition();
			threadHold_2Sec();
			btnsubmitaccesscode();
			threadHold_5Sec();

		}else{
			msg = getinvalidacccesscodemsg();
		}
		return msg;
	}

	public static void forgetPassword(String username){

		linkForgotPassword();
		txtForgotUsername(username);
		threadHold_5Sec();
		btnForgetSubmit();
	}

}
