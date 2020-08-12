package com.hurix.library.Authorwidget;

import com.hurix.automation.utility.Driver;

public class AuthorwidgetModule extends AuthorwidgetStepModule {
	
	public static void Components() {
		String WidgetName = "Test New Authoring";
		
		linkActivites();
		Driver.driver.navigate().refresh(); 
		Driver.driver.switchTo().frame("iframeBody");
		btnAuthorWidget();
		txtwidgetName(WidgetName);
	}

}
