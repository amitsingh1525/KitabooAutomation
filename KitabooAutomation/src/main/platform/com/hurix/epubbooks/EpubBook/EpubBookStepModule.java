package com.hurix.epubbooks.EpubBook;

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

public class EpubBookStepModule extends UIElements {

	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/EpubBook.properties");

	public static void linkepub(){
		try {
			elementFinderByID(prop.getProperty("epublinkepubbook_id"), "Click on epub link.").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btncontinueepubbooks(){
		try {
			threadHold_5Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("loaderepubbooks_xpath"))));
			elementFinderByXpath(prop.getProperty("continueepubbooks_xpath"), "Continue button found");
			elementFinderByXpath(prop.getProperty("continueclickepubbooks_xpath"), "Click on continue button").click();
			threadHold_5Sec();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnfinishepubbook(){
		try {
			elementFinderByXpath(prop.getProperty("finishepubbooks_xpath"), "Click on finish button").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnaddnewbuttonepubbook(){
		try {
			elementFinderByXpath(prop.getProperty("addnewbuttonepubbook_xpath"), "Click on Addnew button").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	

	public static void btnhtmlcreatebookepubbook(){
		try {
			elementFinderByLinkText(prop.getProperty("htmlcreatebookepubbook_link"), "Click on create book button").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	public static void windowswitch(){
		try {
			threadHold_5Sec();
			String winHandleBefore = Driver.driver.getWindowHandle();
			for(String winHandle : Driver.driver.getWindowHandles())  // Switch to new opened window
			{
				Driver.driver.switchTo().window(winHandle);
				Log.info(winHandle);

			}
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}		
	
	public static void txtisbnepubbook(String isbn){
		try {
			elementFinderByXpath(prop.getProperty("isbnepubbook_xpath"), "Click on isbn").click();
			elementFinderByXpath(prop.getProperty("isbnepubbook_xpath"), "Enter data in isbn textbox.").sendKeys(isbn);
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	
	public static void rbPngpicformat(String picformate){
		try {
			String checkbox = elementFinderByXpath(prop.getProperty("fontpermissioncheckboxepubbook_xpath"), "").getAttribute("checked");
			if(checkbox == null)
			{
			
				elementFinderByXpath(prop.getProperty("fontpermissioncheckboccheckedepubbooks_xpath"), "Click on font permission checkbox").click();
			}else{
				System.out.println("else");
			}
			switch(picformate)
			{
			case "jpeg":
			elementFinderByXpath(prop.getProperty("jpegepubbooks_xpath"), "Click on jpeg radio button").click();
			break;
			}
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	
	public static void txttitleepubbook(String title){
		try {
			elementFinderByXpath(prop.getProperty("titleepubbook_xpath"), "Click on title textbox.").click();
			elementFinderByXpath(prop.getProperty("titleepubbook_xpath"), "Enter data in title textbox").sendKeys(title);
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	
	public static void drplanguagedropdownepubbook(String language){
		try {
			threadHold_5Sec();
			switch(language)
			{
			case "English":
				UIElements.selectDropdown(By.id("add_nw_drp1"), "English", "English");
				break;
			}		
			}
			catch (Exception e) 
			{
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void pdffileupload(String pdfanswer,String filepath ){
		try {
			threadHold_5Sec();
			if(pdfanswer.equals("Yes"))
			{			
				Driver.driver.findElement(By.xpath(prop.getProperty("fileuploadepubbooks_xpath",""))).sendKeys(filepath);
				Log.info("File upload in progress");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("fileuploadsuccessepubbooks_xpath","file uploaded sucessfully"))));
				Log.info("File upload successfully");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("okepubbooks_xpath","Click on ok button")))).click();
				Log.info("Click on ok button");
			}		
			}
			catch (Exception e) 
			{
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void uploadcoverimage(String coveranswer ,String filepath){
		try {
			threadHold_5Sec();
			if(coveranswer.equals("Yes"))
			{			
				Driver.driver.findElement(By.xpath(prop.getProperty("coveruploadepubbooks_xpath",""))).sendKeys(filepath);
				Log.info("image upload in progress");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("coverupoadsuccess_xpath","cover image uploaded sucessfully"))));
				Log.info("image upload successfully");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("okepubbooks_xpath","Click on ok button")))).click();
				Log.info("Click on ok button");
			}		
			}
			catch (Exception e) 
			{
			System.out.println("Element not present."+e.getMessage());
		}
	}
	public static void drpfontepubbook(String font){
		try {
			threadHold_5Sec();
			switch(font)
			{
			case "96 dpi":
				UIElements.selectDropdown(By.id("select_dpi"), "96 dpi", "96 dpi");
				break;
				
			case "125 dpi":
				UIElements.selectDropdown(By.id("select_dpi"), "125 dpi", "125 dpi");
				break;
			
			case "150 dpi":
				UIElements.selectDropdown(By.id("select_dpi"), "150 dpi", "150 dpi");
				break;
			
			case "300 dpi":
				UIElements.selectDropdown(By.id("select_dpi"), "300 dpi", "300 dpi");
				break;
			}
			
			}
			catch (Exception e) 
			{
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpbookorientationepubbook(String bookorientation){
		try {
			threadHold_5Sec();
			switch(bookorientation)
			{
			case "Lock Portrait":
				UIElements.selectDropdown(By.id("add_nw_drp3"), "Lock Portrait", "Lock Portrait");
				break;
				
			case "Dynamic":
				UIElements.selectDropdown(By.id("add_nw_drp3"), "Dynamic", "Dynamic");
				break;
			
			case "Lock Landscape":
				UIElements.selectDropdown(By.id("add_nw_drp3"), "Lock Landscape", "Lock Landscape");
				break;
		
			}	
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpepubtypeepubbook(String ebooktype){
		try {
			threadHold_5Sec();
			switch(ebooktype)
			{
			case "Reflow":
				UIElements.selectDropdown(By.id("add_nw_drp4"), "Reflow", "Reflow");
				break;
				
			case "Fixed":
				UIElements.selectDropdown(By.id("add_nw_drp4"), "Fixed", "Fixed");
				break;
			}	
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void drpspreadtypeepubbook(String spreadtype){
		try {
			threadHold_5Sec();
			switch(spreadtype)
			{
			case "None":
				UIElements.selectDropdown(By.id("add_nw_drp2_epub"), "None", "None");
				break;
			case "Landscape":
				UIElements.selectDropdown(By.id("add_nw_drp2_epub"), "Landscape", "Landscape");
				break;
			case "Portrait":
				UIElements.selectDropdown(By.id("add_nw_drp2_epub"), "Portrait", "Portrait");
				break;
			case "Both":
				UIElements.selectDropdown(By.id("add_nw_drp2_epub"), "Both", "Both");
				break;
			case "Auto":
				UIElements.selectDropdown(By.id("add_nw_drp2_epub"), "Auto", "Auto");
				break;
			}	
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void coverpageepubbook(){
		try {
			elementFinderByXpath(prop.getProperty("coverpageepubbook_xpath"), "coverpageepubbook").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
		
	public static void btnfinishbuttonepubbook(){
		try {
			elementFinderByXpath(prop.getProperty("finishbuttonepubbook_xpath"), "Click on finish button").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
		
}
