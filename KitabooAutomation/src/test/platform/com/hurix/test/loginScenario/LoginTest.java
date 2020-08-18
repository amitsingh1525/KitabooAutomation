package com.hurix.test.loginScenario;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;
import com.hurix.platform.loginPage.LoginStepModule;

public class LoginTest{

	@When("Open {string} Browser")
	public void open_browser(String browserName) {
		Log.initialization("PlatformSanity");
		BrowserConfigure.browserConfigure("Chrome");
		
	}

	@Then("Navigate to LogIn Page {string}")
	public void user_navigate_to_log_in_page_i_e_url(String url) {
		Log.startTestCase("Test the login page with given data");
		Driver.driver.navigate().to(url);
		if(Driver.driver.getCurrentUrl().equals(url)){
			Log.info("Expected and ACtual URL matched for login page. i.e '"+Driver.driver.getCurrentUrl()+"'");
		}else{
			Log.fail("Expected: "+url+" but found "+Driver.driver.getCurrentUrl());
		}
	}

	@And("User enters {string} and {string} on textbox")
	public void user_enters_user_name_and_pass(String username, String password) {
		try {
			
			LoginStepModule.txtUsername(username);
			Log.info("Username enterd i.e '"+username+"'");
			LoginStepModule.txtPassword(password);
			Log.info("Password enterd i.e '"+password+"'");
		} catch (PendingException e) {
			Log.error("Execution occur!");
			System.out.println(e.getMessage());
		}
	}

	@And("click on login button")
	public void click_on_login_button() {
		try {
			LoginStepModule.btnLogin();
		} catch (PendingException e) {
			Log.error("Execution occur!");
			System.out.println(e.getMessage());
		}
	}

	@Then("verify the url it's redirect to the home page {string}")
	public void verify_the_url_it_s_redirect_to_the_home_page(String homePageURL) {
		try {
			if(Driver.driver.getCurrentUrl().equals(homePageURL)){
				Log.pass("Expected and Actual URL matched after redirect to home page. i.e '"+Driver.driver.getCurrentUrl()+"'");
			}else{
				Log.fail("Expected: "+homePageURL+" but found "+Driver.driver.getCurrentUrl());
			}
		} catch (PendingException e) {
			Log.error("Execution occur!");
			System.out.println(e.getMessage());
			Log.endTestCase("End");
		}
		Log.endTestCase("End");
	}

	@When("Close Browser")
	public void close_browser() {
		Driver.driver.quit();
	}
}
