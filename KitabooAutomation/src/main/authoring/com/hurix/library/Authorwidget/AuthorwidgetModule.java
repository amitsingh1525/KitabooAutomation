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
		
	//copy functionality
		btnlistview_copy();
		btngridview();
		btngridview_copy();
		
	//Rename functionality	
		btnrenamewidget();
		txtbx_widgetname(WidgetName);
		btnsavewidget();
		
	// Change cover functionality
		btngridview();
		btngridview_changeCover();
		btngridview_changeCover();
		
	// share functionaliy
		btnlistview_share();
		rdbtnoption_share();
		btnconfirm_share();
		btngridview();
		btngridview_share();
		rdbtnoption_share();
		btnconfirm_share();
		btnlistview();
		
	//Delete functionality
		btndelete();
		btnconfirmdelete();
		
	// Filter author widget and create widget Functionality
		drpdwnselect();
		drpdwnauthorwidget();
		drpdwnselect();
		drpdwncreatewidget();
		drpdwnselectall();
	}

}
