package com.hurix.epubbooks.uploadEpubBook;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.UIElements;

public class UploadEpubBookStepModule extends UIElements {

	public static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/EpubBook.properties");

	public static void linkepub(){
		try {
			elementFinderByID(prop.getProperty("epublinkepubbook_id"), "linkepub").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnaddnewbuttonepubbook(){
		try {
			elementFinderByXpath(prop.getProperty("addnewbuttonepubbook_xpath"), "btnaddnewbuttonepubbook").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void linkepubupload(){
		try {		
			elementFinderByLinkText(prop.getProperty("epubupload_link"), "Click on upload epub").click();
			}
		catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtepubuploadinputTitle(String title){
		try {		
			elementFinderByID(prop.getProperty("epubuploadinputTitle_id"), "Click on title textbox").click();
			elementFinderByID(prop.getProperty("epubuploadinputTitle_id"), "Title write in title textbox").sendKeys(title);
		}
		catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	
	
	public static void txtepubuploadinputAuthor(String author){
		try {		
			elementFinderByID(prop.getProperty("epubuploadinputAuthor_id"), "Click on author textbox").click();
			elementFinderByID(prop.getProperty("epubuploadinputAuthor_id"), "Author write in author textbox").sendKeys(author);
		}
		catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}		

	public static void txtepubuploadinputCategory(String category){
		try {		
			elementFinderByID(prop.getProperty("epubuploadinputCategory_id"), "Click on category textbox").click();
			elementFinderByID(prop.getProperty("epubuploadinputCategory_id"), "Category write in category textbox").sendKeys(category);
		}
		catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void txtepubuploadinputIsbn(String isbn){
		try {		
			elementFinderByID(prop.getProperty("epubuploadinputIsbn_id"), "Click on isbn textbox").click();
			elementFinderByID(prop.getProperty("epubuploadinputIsbn_id"), "Isbn write in isbn textbox").sendKeys(isbn);
		}
		catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void txtepubuploadinputref(String ref){
		try {		
			elementFinderByID(prop.getProperty("epubuploadinputref_id"), "Click on ref textbox").click();
			elementFinderByID(prop.getProperty("epubuploadinputref_id"), "Ref write in ref textbox").sendKeys(ref);
		}
		catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void txtepubuploadinputDesc(String desc){
		try {		
			elementFinderByID(prop.getProperty("epubuploadinputDesc_id"), "Click on description textbox").click();
			elementFinderByID(prop.getProperty("epubuploadinputDesc_id"), "Description write in description textbox").sendKeys(desc);
		}
		catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnepubuploadstart(){
		try {		
			elementFinderByID(prop.getProperty("epubuploadstart_id"), "Click on upload file button").click();
		}
		catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnepubuploadamazonpoly(){
		try {
			elementFinderByXpath(prop.getProperty("epubuploadamazonpoly_xpath"), "Click on amazonpolly button.").click();
			threadHold_2Sec();
			JavascriptExecutor js = (JavascriptExecutor) Driver.driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnepubuploadmale(String voice){
		try {
			if(voice.equalsIgnoreCase("Male"))
			{
				UIElements.elementFinderByXpath("//*[@for='accord:audioSyncconversionStatusEpub:0']", "").click();
				elementFinderByXpath(prop.getProperty("epubuploadmale_xpath"), "Click on "+voice+" voice button.").click();
			}
			else
			{
				UIElements.elementFinderByXpath("//*[@for='accord:audioSyncconversionStatusEpub:1']", "").click();
				elementFinderByXpath(prop.getProperty("epubuploadmale_xpath"), "Click on "+voice+" voice button.").click();
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpSpeechRate(String	SpeechRate){
		switch(SpeechRate){
		case "Fast":
			UIElements.selectDropdown(By.id("accord:audioSyncselRateEpub"), "Fast", "Fast");
			break;
		case "Medium":
			UIElements.selectDropdown(By.id("accord:audioSyncselRateEpub"), "Medium", "Medium");
			break;
		case "Slow":
			UIElements.selectDropdown(By.id("accord:audioSyncselRateEpub"), "Slow", "Slow");
			break;
		case "X-Fast":
			UIElements.selectDropdown(By.id("accord:audioSyncselRateEpub"), "X-Fast", "X-Fast");
			break;
		case "X-Slow":
			UIElements.selectDropdown(By.id("accord:audioSyncselRateEpub"), "X-Slow", "X-Slow");
			break;
			
		}
	}

	
	public static void drplanguage(String language){
		threadHold_5Sec();
		switch(language){
		case "US English":
			UIElements.selectDropdown(By.id("accord:audioSyncaudioLangEpub"), "US English", "US English");
			break;
			
		case "Danish":
			UIElements.selectDropdown(By.id("accord:audioSyncaudioLangEpub"), "Danish", "Danish");
			break;
		
		case "Dutch":
			UIElements.selectDropdown(By.id("accord:audioSyncaudioLangEpub"), "Dutch", "Dutch");
			break;
		
		case "German":
			UIElements.selectDropdown(By.id("accord:audioSyncaudioLangEpub"), "German", "German");
			break;
		
		case "Italian":
			UIElements.selectDropdown(By.id("accord:audioSyncaudioLangEpub"), "Italian", "Italian");
			break;
		
		case "Polish":
			UIElements.selectDropdown(By.id("accord:audioSyncaudioLangEpub"), "Polish", "Polish");
			break;
			
		case "Portuguese - Iberic":
			UIElements.selectDropdown(By.id("accord:audioSyncaudioLangEpub"), "Portuguese - Iberic", "Portuguese - Iberic");
			break;
			
		case "Russian":
			UIElements.selectDropdown(By.id("accord:audioSyncaudioLangEpub"), "Russian", "Russian");
			break;
			
		case "Spanish - Castilian":
			UIElements.selectDropdown(By.id("accord:audioSyncaudioLangEpub"), "Spanish - Castilian", "Spanish - Castilian");
			break;
			
		}
	}
	

	public static void btnepubuploadcancel(){
		try {
			elementFinderByXpath(prop.getProperty("epubuploadcancel_xpath"), "Click on cancel button").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

		
	public static void btnepubuploaddone(){
		try {
			elementFinderByXpath(prop.getProperty("epubuploaddone_xpath"), "Click on done button").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
			}
	}

}
