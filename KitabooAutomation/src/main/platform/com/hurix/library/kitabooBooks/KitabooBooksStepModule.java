package com.hurix.library.kitabooBooks;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class KitabooBooksStepModule extends UIElements {

private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/kitabooBooks.properties");
	
	public static void drpAddNew_BulkConversion(){
		try {
			elementFinderByID(prop.getProperty("addNew_ID"), "addNew").click();
			elementFinderByXpath(prop.getProperty("addNew_BulkConversion_Xpath"), "addNew_BulkConversion").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpAddNew_CreateBook(){
		try {
			elementFinderByID(prop.getProperty("addNew_ID"), "addNew").click();
			elementFinderByXpath(prop.getProperty("addNew_CreateNBook_Xpath"), "addNew_CreateNBook").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpAddNew_HTMLCreateBook(){
		try {
			elementFinderByID(prop.getProperty("addNew_ID"), "addNew").click();
			elementFinderByXpath(prop.getProperty("addNew_HTMLCreateBook_Xpath"), "addNew_HTMLCreateBook").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtISBN(String ISBN){
		try {
			elementFinderByXpath(prop.getProperty("isbn_Xpath"), "txt_isbn").sendKeys(ISBN);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtTitle(String Title){
		try {
			elementFinderByID(prop.getProperty("title_ID"), "txt_title").sendKeys(Title);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtAuthor(String Author){
		try {
			elementFinderByXpath(prop.getProperty("author_Xpath"), "txt_author").sendKeys(Author);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpCategory(String categoryName){
		try {
			selectDropdown(By.id(prop.getProperty("category_drp_ID")), prop.getProperty("category_addNewCategory_VisibleText"), "Add New Category");
			elementFinderByXpath(prop.getProperty("categoryName_Xpath"), "txt_NewCategory").sendKeys(categoryName);
			elementFinderByXpath(prop.getProperty("categorySave_Xpath"), "txt_SaveCategory").click();
			Thread.sleep(500);
			UIElements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("loaderClass_xpath"))));
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpLanguageSelection(String language){
		try {
			selectDropdown(By.id(prop.getProperty("language_drp_ID")), language, "Language");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtDescription(String Description){
		try {
			elementFinderByXpath(prop.getProperty("description_Xpath"), "txt_description").sendKeys(Description);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpBookType(String BookType){
		try {
			selectDropdown(By.id(prop.getProperty("bookType_drp_ID")), BookType, "Book_Type");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtkeywords(String keywords){
		try {
			elementFinderByXpath(prop.getProperty("keywords_Xpath"), "txt_keywords").sendKeys(keywords);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpBookOriantation(String BookOriantation){
		try {
			selectDropdown(By.id(prop.getProperty("bookOriantaion_drp_ID")), BookOriantation, "Book_Oriantation");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void chkFontPermission(boolean fontPermission){
		try {
			if(fontPermission){
				if(elementFinderByXpath(prop.getProperty("keywords_Xpath"), "txt_keywords").getAttribute("checked").equals("checked")){
					
				}
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnUploadBook(String path){
		try {
			File projectLocation = new File("");
			Driver.driver.findElement(By.xpath(prop.getProperty("uploadFile_Browse_Xpath"))).sendKeys(path);
			UIElements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("loaderClass_xpath"))));
			elementFinderByXpath(prop.getProperty("afterFileUploadPopUp_xpath"), "Ok Button(After Upload Book Popup msg)").click();
			Log.info("Book Sucessfully Uploaded i.e '"+"C:/Users/amit.singh/git/KitabooAutomation/KitabooAutomation/resources/TwoPagePdfFile.pdf"+"'");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void rdbDefaultImage(){
		try {
			elementFinderByXpath(prop.getProperty("defaultImage_Xpath"), "rdb_Default").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnFinish(){
		try {
			elementFinderByXpath(prop.getProperty("bookCreation_FinishBtn_Xpath"), "btn_Finish").click();
			UIElements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("loaderClass_xpath"))));
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void afterFinishBtnContinue(){
		try {
			elementFinderByXpath(prop.getProperty("afterFinishBtnContinue_xpath"), "btn_Continue").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnPublish(){
		try {
			elementFinderByID(prop.getProperty("publishBtn_ID"), "btn_Continue");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
}
