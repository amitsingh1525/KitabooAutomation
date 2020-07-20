package com.hurix.platform.loginPage;

import java.util.Properties;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;

public class LoginModule extends LoginStepModule {

	public static String loginScenario(String username, String password){

		txtUsername(username);
		txtPassword(password);
		btnLogin();
		threadHold();
		if(Driver.driver.getCurrentUrl().contains("books.xhtml")){
			Log.pass("After successfully login redirected to the home page"+Driver.driver.getCurrentUrl());
			return "After successfully login redirected to the home page"+Driver.driver.getCurrentUrl();
		}else{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
			return "Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl();
		}
	}
}
