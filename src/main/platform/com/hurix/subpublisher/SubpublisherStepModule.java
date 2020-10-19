package com.hurix.subpublisher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.UIElements;

public class SubpublisherStepModule extends UIElements {
	
	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/publisher.properties");
	
	public static void linkSubPublisher(){
		try {
			elementFinderByID(prop.getProperty("subpublishertab_id"), "SubPublisher link clicked").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
	
	public static void btnCreateAccount(){
		try {
			elementFinderByID(prop.getProperty("btncreateaccount_id"), "Add Account button clicked").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxOrganization(String Organization){
		try {
			elementFinderByID(prop.getProperty("txtbxOrganization_id"), "Organization textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxOrganization_id"), "Entered data in Organization textbox is: '"+Organization+"'.").sendKeys(Organization);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxAdministratorFirstName(String AdministratorFirstName){
		try {
			elementFinderByID(prop.getProperty("txtbxAdministratorFirstName_id"), "Administrator first name textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxAdministratorFirstName_id"), "Entered data in Administrator first name textbox is: '"+AdministratorFirstName+"'.").sendKeys(AdministratorFirstName);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxAdministratorLastName(String AdministratorLastName){
		try {
			elementFinderByID(prop.getProperty("txtbxAdministratorLastName_id"), "Administrator Last name textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxAdministratorLastName_id"), "Entered data in Administrator Last name textbox is: '"+AdministratorLastName+"'.").sendKeys(AdministratorLastName);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxAdminEmailId(String AdminEmailId){
		try {
			elementFinderByID(prop.getProperty("txtbxAdminEmailId_id"), "Admin EmailId textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxAdminEmailId_id"), "Entered data in Admin EmailId textbox is: '"+AdminEmailId+"'.").sendKeys(AdminEmailId);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxStreet(String Street){
		try {
			elementFinderByID(prop.getProperty("txtbxStreet_id"), "Street textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxStreet_id"), "Entered data in Street textbox is: '"+Street+"'.").sendKeys(Street);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxCity(String City){
		try {
			elementFinderByID(prop.getProperty("txtbxCity_id"), "City textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxCity_id"), "Entered data in City textbox is: '"+City+"'.").sendKeys(City);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxZip(String Zip){
		try {
			elementFinderByID(prop.getProperty("txtbxZip_id"), "Zip textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxZip_id"), "Entered data in Zip textbox is: '"+Zip+"'.").sendKeys(Zip);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void drpdwnCountry(){
		try {
			UIElements.selectDropdown(By.xpath("txtbxCountry_xpath"), "India", "India");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtbxContact(String Contact){
		try {
			elementFinderByID(prop.getProperty("txtbxContact_id"), "Contact textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxContact_id"), "Entered data in Contact textbox is: '"+Contact+"'.").sendKeys(Contact);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void btnBrowsefile(String filepath){
		try {
				threadHold_2Sec();
					//System.out.println("audiopath"+audiopath);
				
					/*wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
					Driver.driver.switchTo().frame(prop.getProperty("Audioframe_id"));*/ 	
					Actions action = new Actions(Driver.driver);
					threadHold_2Sec();
					action.sendKeys(Keys.PAGE_DOWN).build().perform();
					threadHold_2Sec();
					int size= Driver.driver.findElements(By.xpath("//*[@id='fileuploader_input']")).size();
					System.out.println(size);
					Driver.driver.findElement(By.xpath("//*[@id='fileuploader_input']")).sendKeys(filepath);
					//elementFinderByXpath(prop.getProperty("uploadAudio_xpath"), "btnuploadAudio").sendKeys(path);
					Driver.driver.switchTo().parentFrame();
					Driver.driver.switchTo().defaultContent();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
				} 
			
					//elementFinderByID(prop.getProperty("btnBrowse_id"), "File uploaded is: '"+filepath+"'.").sendKeys("filepath");
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
	
	public static void btnSave(){
		try {
			elementFinderByID(prop.getProperty("btnSave_id"), "Save button clicked").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
	
	public static void btnReset(){
		try {
			elementFinderByID(prop.getProperty("btnReset_id"), "Reset button clicked").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
	
	public static void btnCancel(){
		try {
			elementFinderByID(prop.getProperty("btnCancel_id"), "Cancel button clicked").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
	
	public static void selectdate(String startdate, String enddate){
		try {
			threadHold_2Sec();
			int increaseDate = Integer.parseInt(startdate);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Calendar c = Calendar.getInstance();    
			c.add(Calendar.DATE, increaseDate);
			startdate = dateFormat.format(c.getTime());
			System.out.println("Start: "+startdate);
			
			int increaseDate1 = Integer.parseInt(enddate);
			DateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
			Calendar c1 = Calendar.getInstance();    
			c1.add(Calendar.DATE, increaseDate1);
			enddate = dateFormat1.format(c1.getTime());
			System.out.println("Exp: "+enddate);
			
			((JavascriptExecutor)Driver.driver).executeScript ("document.getElementById('startDate_input').removeAttribute('readonly',0);");
			elementFinderByID(prop.getProperty("bxStartDate_id"), "start date textbox click").clear();
			elementFinderByID(prop.getProperty("bxStartDate_id"), "start date textbox click").sendKeys(startdate);
			((JavascriptExecutor)Driver.driver).executeScript ("document.getElementById('endDate_input').removeAttribute('readonly',0);");
			elementFinderByID(prop.getProperty("bxEndDate_id"), "start date textbox click").clear();
			elementFinderByID(prop.getProperty("bxEndDate_id"), "start date textbox click").sendKeys(enddate);
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


}
