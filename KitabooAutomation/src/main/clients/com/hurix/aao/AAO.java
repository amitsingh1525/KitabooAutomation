package com.hurix.aao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.GoogleSpreadSheetData;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;
import com.hurix.clientsConfig.SheetMetaData;

public class AAO extends UIElements {

	private static Properties prop = getProperty(System.getProperty("user.dir")+"/src/main/clients/com/hurix/aao/AAO.properties");
	
	public static void aao(String username, String password, int sheeRow, int rowIndex) {
		try {
			try {
				elementFinderByID(prop.getProperty("username_ID"), "Entered username i.e. '"+username+"'").sendKeys(username);
				elementFinderByID(prop.getProperty("password_ID"), "Entered password i.e. '"+password+"'").sendKeys(password);
				elementFinderByXpath(prop.getProperty("loginBtn_xpath"), "Click on Login button.").click();
			} catch (Exception e) {
				GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!G"+sheeRow, "Fail");
			}
			
			threadHold_2Sec();
			if(Driver.driver.findElements(By.xpath(prop.getProperty("invalidmsg_xpath"))).size()>0){
				String actualStr = elementFinderByXpath(prop.getProperty("invalidmsg_xpath"), "Getting invalid msg text").getText();
				String expectedStr = GoogleSpreadSheetData.getValues(SheetMetaData.SheetID, "Users!E"+sheeRow);
				GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!F"+sheeRow, actualStr);
				Assert.assertEquals(actualStr, expectedStr); 
				GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!G"+sheeRow, "Credential Mismatch");
				Log.error("Credential Mismatch....!!!");
			}else {
				elementFinderByID(prop.getProperty("profileIcon_ID"), "ProfileIcon Found on the UI.");
				GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!F"+sheeRow, "Login Successful.");
				GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!G"+sheeRow, "Pass");
				Log.pass("Login Successful....!!!");
				
				List<WebElement> ele = elementsFinderByXpaths(prop.getProperty("thumbnail_Xpath"), "Click on thumbnail.");
				ele.get(0).click();
				long startTime = System.nanoTime();
				elementFinderByID(prop.getProperty("bookMark_ID"), "Bookmark Found on the UI.");
				long endTime   = System.nanoTime();
				long totalTime = endTime - startTime;
				Log.info("Time taken to open book: "+TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS)+" Second");
				GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!H"+sheeRow, Long.toString(TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS)));
				Thread.sleep(500);
				GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!i"+sheeRow, "Pass");
				
			}
		} catch (ElementNotVisibleException e) {
			System.out.println("Element is present on the DOM, it is not visible, and so is not able to be interacted with. "+e.getMessage());
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!i"+sheeRow, "FAIL");
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!j"+sheeRow, "Element is present on the DOM, it is not visible, and so is not able to be interacted with. "+e.getMessage());
			Log.error("ERROR>>> "+e);
		}catch (ElementNotInteractableException e) {
			System.out.println("Element is present on the DOM, it is not in a state that can be interacted. "+e.getMessage());
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!i"+sheeRow, "FAIL");
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!j"+sheeRow, "Element is present on the DOM, it is not in a state that can be interacted. "+e.getMessage());
			Log.error("ERROR>>> "+e);
		}catch (ElementNotSelectableException e) {
			System.out.println("Element is present on the DOM, it is not selectable, and so is not able to be interacted with. "+e.getMessage());
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!i"+sheeRow, "FAIL");
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!j"+sheeRow, "Element is present on the DOM, it is not selectable, and so is not able to be interacted with. "+e.getMessage());
			Log.error("ERROR>>> "+e);
		}
		catch (TimeoutException e) {
			System.out.println("Command does not complete in enough time. "+e.getMessage());
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!i"+sheeRow, "FAIL");
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!j"+sheeRow, "Command does not complete in enough time. "+e.getMessage());
			Log.error("ERROR>>> "+e);
		}
		catch (NoSuchElementException e) {
			System.out.println("Element not found in the page. "+e.getMessage());
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!i"+sheeRow, "FAIL");
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!j"+sheeRow, "Element not found in the page. "+e.getMessage());
			Log.error("ERROR>>> "+e);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!i"+sheeRow, "FAIL");
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!j"+sheeRow, e.getMessage());
			Log.error("ERROR>>> "+e);
		}
	}

}
