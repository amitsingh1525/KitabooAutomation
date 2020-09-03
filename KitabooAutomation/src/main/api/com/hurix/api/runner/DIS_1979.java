package com.hurix.api.runner;

import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.library.kitabooBooks.KitabooBooksModule;
import com.hurix.platform.loginPage.LoginModule;

public class DIS_1979 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BrowserConfigure.browserConfigure("Chrome");
		Driver.driver.navigate().to("https://qc.kitaboo.com/home.xhtml");
		LoginModule.loginScenario("automation.test1@yopmail.com", "kitaboo!123");
		String title = KitabooBooksModule.bookCreationPDF("hello", "World", "level 2", "For automation Testing", "Hello, World");
	
		KitabooBooksModule.bookPublishAndArchivePDF(title);

	}

}
