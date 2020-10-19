package com.hurix.epubbooks.bulkuploadepub;

import java.beans.Visibility;
import java.util.Properties;

import javax.xml.ws.WebEndpoint;

import org.mortbay.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.UIElements;

public class bulkuploadepubStepModule extends UIElements {

	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/EpubBook.properties");

	public static void linkepub(){
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByID(prop.getProperty("epublinkepubbook_id"), "Click on epub link.").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnaddnewbuttonepubbook(){
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByXpath(prop.getProperty("addnewbuttonepubbook_xpath"), "Click on Addnew button").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void linkbulkuploadconversion(){
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByLinkText(prop.getProperty("bulkuploadepub_linktext"), "Click on bulk upload epub conversion").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void sampledownload(){
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByXpath(prop.getProperty("sampledownloadbulkuploadepub_xpath"), "Click on download sample").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void profilebulkuploadepub(){
		try {
			threadHold_5Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByID(prop.getProperty("profilebulkuploadepub_id"), "Click on profile dropdown").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void checkBulkUploadConversionStatus(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByID(prop.getProperty("checkBulkUploadConversionStatus_id"), "Click on bulk conversion status").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void downloaderrorlog(){
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByXpath(prop.getProperty("downloaderrorlog_xpath"), "Click on download error log").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
		
	public static void librarybulkuploadepub(){
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByID(prop.getProperty("librarylinkbulkuploadepub_id"), "Click on epub link.").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void fileupload(String filepath)
	{
		try {
			threadHold_5Sec();
			Driver.driver.findElement(By.id(prop.getProperty("epubbulkconversionfileupload_id",""))).sendKeys(filepath);
			Log.info("Bulk Conersion Excel File Uploaded.");
			elementFinderByXpath(prop.getProperty("uploadbuttonbulkuploadepub_xpath"), "Click on Upload Button").click();
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("closepopupbulkuploadepub_xpath"), "Click on Close PopUp").click();
			}
			catch(Exception e) 
			{
			System.out.println("Element not present."+e.getMessage());
		}
	}
	

}
