package com.hurix.automation.utility;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.UIElements;

public class SaveActivity extends UIElements {
	private static Properties prop = getProperty("C:/Users/amit.singh/git/KitabooAutomation/KitabooAutomation/config/authoring/authoring.properties");

	public static void saveActivity(){
		try {
			elementFinderByXpath(prop.getProperty("save_activity_Xpath"), "btn_saveActivity").click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("loader_Xpath"))));
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	

}
