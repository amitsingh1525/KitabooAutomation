package com.hurix.reader.bookShelf;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class BookShelfStepModule extends UIElements{


	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/loginPage.properties");

	public static void btnSearch(){
		try {
			elementFinderByID(prop.getProperty("search_id"), "btnSearch").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txt_Search(String title){
		try {
			elementFinderByID(prop.getProperty("search_txt_id"), "btnSearch").clear();
			elementFinderByID(prop.getProperty("search_txt_id"), "btnSearch").sendKeys(title);
			elementFinderByID(prop.getProperty("search_txt_id"), "btnSearch").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static String afterSearchBookFound(){
		String totalBook = null;
		try {
			Thread.sleep(1000);
			totalBook = elementFinderByXpath(prop.getProperty("searchCount_xpath"), "searchCount").getText();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return totalBook;
	}

	public static void searchBookThumbnail(){
		try {
			elementFinderByXpath(prop.getProperty("searchBookThumbnail_xpath"), "searchBookThumbnail").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnCancel(){
		try {
			elementFinderByID(prop.getProperty("searchCancel_id"), "btnCancel").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnRefresh(){
		try {
			elementFinderByXpath(prop.getProperty("refreshBook_xpath"), "btnRefresh").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnAccessCode(){
		try {
			elementFinderByXpath(prop.getProperty("accessCode_Xpath"), "btnAccessCode").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtAccessCode(String accessCode){
		try {
			elementFinderByID(prop.getProperty("accessCode_txt_id"), "txtAccessCode").sendKeys(accessCode);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnAccessCodeCancel(){
		try {
			elementFinderByXpath(prop.getProperty("accessCodeCancel_xpath"), "btnAccessCodeCancel").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnAccessCodeSubmit(){
		try {
			elementFinderByXpath(prop.getProperty("accessCodeSubmit_xpath"), "btnAccessCodeSubmit").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnProfileIcon(){
		try {
			elementFinderByID(prop.getProperty("profileIcon_id"), "btnProfileIcon").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void linkAboutUS(){
		try {
			elementFinderByID(prop.getProperty("aboutUs_id"), "linkAboutUS").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void linkPrivacyPolicy(){
		try {
			elementFinderByXpath(prop.getProperty("privacyPolicy_xpath"), "linkPrivacyPolicy").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void linkTermsAndCondition(){
		try {
			elementFinderByXpath(prop.getProperty("termsConditions_xpath"), "linkTermsAndCondition").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnSignOut(){
		try {
			elementFinderByID(prop.getProperty("signOut_id"), "btnSignOut").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnEditProfile(){
		try {
			elementFinderByID(prop.getProperty("editprofileIcon_id"), "btnEditProfile").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtFirstName(String firstName){
		try {
			elementFinderByXpath(prop.getProperty("firstName_xpath"), "txtFirstName").sendKeys(firstName);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void txtLastName(String lastName){
		try {
			elementFinderByXpath(prop.getProperty("lastName_xpath"), "txtFirstName").sendKeys(lastName);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnCancelProfile(){
		try {
			elementFinderByXpath(prop.getProperty("cancelProfile_xpath"), "btnSaveProfile").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnSaveProfile(){
		try {
			elementFinderByID(prop.getProperty("saveProfile_id"), "btnSaveProfile").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnUploadPic(String filePath){
		try {
			elementFinderByID(prop.getProperty("uploadProfile_id"), "btnUploadPic").sendKeys(filePath);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btn_XClose(){
		try {
			elementFinderByXpath(prop.getProperty("closeXbtn_xpath"), "btn_XCloseProfilePopup").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void linkHorizontalCatName(String catName){
		try {
			List<WebElement> ele = elementsFinderByXpaths(prop.getProperty("catNameHorizontal_listView_xpath"), "linkHorizontalCatName");
			for(WebElement element : ele){
				String categorie = element.getAttribute("aria-label");
				if(categorie.equalsIgnoreCase(catName)){
					element.click();
				}
			}
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static void btnThumbnails(){
		try {
			elementFinderByXpath(prop.getProperty("bookThumbnails_xpath"), "btnThumbnails").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	
	public static void linkRecentlyView(){
		linkHorizontalCatName("Recently Viewed");
	}

	public static void linkFavorite(){
		linkHorizontalCatName("Favorite");
	}
	
	public static int FavCount(){
		int size = 0;
		try {
			linkFavorite();
			size = elementsFinderByXpaths(prop.getProperty("markAsFav_listview_xpath"), "btnMarkAsFav").size();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return size;
	}
	
	public static List<WebElement> btnMarkAsFav(){
		List<WebElement> elements = null;
		try {
			elements = elementsFinderByXpaths(prop.getProperty("markAsFav_listview_xpath"), "btnMarkAsFav");
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return elements;
	}
	
	public static List<WebElement> btnUnmarkAsFav(){
		List<WebElement> elements = null;
		try {
			linkFavorite();
			elements = elementsFinderByXpaths(prop.getProperty("unmarkAsFav_listview_xpath"), "btnUnmarkAsFav");
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return elements;
		
	}
	
	public static void allUnmarkAsFav(){
		try {
			linkFavorite();
			List<WebElement> elements = elementsFinderByXpaths(prop.getProperty("unmarkAsFav_listview_xpath"), "btnUnmarkAsFav");
			for(WebElement element : elements){
				element.click();
				threadHold_2Sec();
			}
			threadHold_2Sec();
			if(elementFinderByXpath(prop.getProperty("unmarkAsFav_listview_xpath"), "msg No book Availabe in Favourite")
					.equals("No books have been marked as favorite")){
				Log.info("message found 'No books have been marked as favorite'");
			}else{
				Log.info("message NOT found 'No books have been marked as favorite'");
			}
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}

	public static List<WebElement> btnMoreInfo(){
		List<WebElement> elements = null;
		try {
			elements = elementsFinderByXpaths(prop.getProperty("moreInfo_listview_xpath"), "btnMoreInfo");
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return elements;
	}
	
	public static void btnAnalytics(){
		try {
			elementFinderByID(prop.getProperty("analytics_id"), "btnAnalytics").click();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static String getBookTitleMoreInfo(){
		String bookTitle = null;
		try {
			bookTitle = elementFinderByXpath(prop.getProperty("bookTitleMoreInfo_xpath"), "getBookTitleMoreInfo").getText();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return bookTitle;
	}
	
	public static String getBookAuthorMoreInfo(){
		String bookAuthor = null;
		try {
			bookAuthor = elementFinderByXpath(prop.getProperty("authorMoreInfo_xpath"), "getBookAuthorMoreInfo").getText();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return bookAuthor;
	}
	
	public static String getBookDiscriptionMoreInfo(){
		String bookDiscription = null;
		try {
			bookDiscription = elementFinderByXpath(prop.getProperty("descriptionMoreInfo_xpath"), "getBookDiscriptionMoreInfo").getText();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return bookDiscription;
	}
	
	
	//Analytics part pending
}
