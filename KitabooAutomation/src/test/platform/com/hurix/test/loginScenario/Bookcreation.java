package com.hurix.test.loginScenario;

import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.library.kitabooBooks.KitabooBooksModule;
import com.hurix.platform.loginPage.LoginModule;
import com.hurix.reader.bookPlayer.BookPlayerModule;

public class Bookcreation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.initialization("bookCreation");
		Log.startTestCase("bookcreatiom");
		BrowserConfigure.browserConfigure("Chrome");
		Driver.driver.navigate().to("https://create.kitaboo.com/home.xhtml");
		LoginModule.loginScenario("automation.test1@yopmail.com", "kitaboo!123");
		String title = KitabooBooksModule.bookCreationPDF("hello", "World", "level 2", "For automation Testing", "Hello, World");
	
		KitabooBooksModule.bookPublishAndArchivePDF(title);
		Log.endTestCase("End");
	}

}
