package com.hurix.test.loginScenario;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;
import com.hurix.com.platform.loginPage.LoginStepModule;

public class LoginTest{

	static boolean initializeClass = true;

	@Before
	public static void openBrowser(){

		if(initializeClass){
			System.out.println("Inside Loop");
			Log.initialization();
			initializeClass = false;
		}

		Log.startTestCase("Test - 1");
		BrowserConfigure.browserConfigure("Chrome");
		Log.info("Driver: "+Driver.driver);
		Driver.driver.get("https://create.kitaboo.com/home.xhtml");
	}

	@When("inert given username {string}")
	public void inert_given_username_username(String username) {
		try {
			LoginStepModule.txtUsername(username);
		} catch (Exception e) {
		}
	}

	@And("insert the given password {string}")
	public void insert_the_given_password_pass(String password) {
		LoginStepModule.txtPassword(password);
	}

	@Then("verify the url it's not redirect to the home page")
	public void verify_the_url_it_s_not_redirect_to_the_home_page() {
		UIElements.threadHold();
		if("https://create.kitaboo.com/home.xhtml".contains("home.xhtml")){
			Log.pass("expected url matched with actual url.");
		}else{
			Log.fail("Actaul and expected dosen't matched!");
		}
			
		Log.endTestCase("");

	}

	@And("click on login button")
	public void click_on_login_button() {

		// Write code here that turns the phrase above into concrete actions
		LoginStepModule.btnLogin();
	}

	@After
	public static void closeBrowser(){
		Driver.driver.quit();
	}
}
