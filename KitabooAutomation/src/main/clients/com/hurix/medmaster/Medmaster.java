package com.hurix.medmaster;

import java.util.Properties;

import com.hurix.automation.utility.UIElements;
import com.hurix.reader.bookShelf.BookShelfModule;
import com.hurix.reader.loginPage.LoginModule;

public class Medmaster extends UIElements{

	private static Properties prop = getProperty(System.getProperty("user.dir")+"/src/main/clients/com/hurix/aao/Medmaster.properties");
	
	public static void medmaster(String username, String password, int sheeRow, int rowIndex) {
		
		LoginModule.userLogin(username, password);
		
		BookShelfModule.catNavigationAndLaunch("All");
		
		
	}
}
