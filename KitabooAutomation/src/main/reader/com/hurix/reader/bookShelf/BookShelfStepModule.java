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


	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/reader/BookShelf.properties");

	public static void btnSearch(){
		try {
			elementFinderByID(prop.getProperty("search_id"), "Click on search.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void txt_Search(String title){
		try {
			elementFinderByID(prop.getProperty("search_txt_id"), "Clear Search textbox.").clear();
			elementFinderByID(prop.getProperty("search_txt_id"), "Entered title in the search textbox i.e: '"+title+"'.").sendKeys(title);
			elementFinderByID(prop.getProperty("search_txt_id"), "Press enter key.").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static String afterSearchBookFound(){
		String totalBook = null;
		try {
			Thread.sleep(1000);
			totalBook = elementFinderByXpath(prop.getProperty("searchCount_xpath"), "Getting search book count msg.").getText();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return totalBook;
	}

	public static void searchBookThumbnail(){
		try {
			elementFinderByXpath(prop.getProperty("searchBookThumbnail_xpath"), "Click on search book thumbnail,").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnCancel(){
		try {
			elementFinderByID(prop.getProperty("searchCancel_id"), "Click on cancel.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnRefresh(){
		try {
			elementFinderByXpath(prop.getProperty("refreshBook_xpath"), "Click on refresh.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnAccessCode(){
		try {
			elementFinderByXpath(prop.getProperty("accessCode_Xpath"), "Click on access code.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void txtAccessCode(String accessCode){
		try {
			elementFinderByID(prop.getProperty("accessCode_txt_id"), "Entered access code i.e. '"+accessCode+"'.").sendKeys(accessCode);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnAccessCodeCancel(){
		try {
			elementFinderByXpath(prop.getProperty("accessCodeCancel_xpath"), "Click on cancel access code.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnAccessCodeSubmit(){
		try {
			elementFinderByXpath(prop.getProperty("accessCodeSubmit_xpath"), "Click on submit access code.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnProfileIcon(){
		try {
			elementFinderByID(prop.getProperty("profileIcon_id"), "Click on profile icon.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static void linkChangePassword(){
		try {
			elementFinderByID(prop.getProperty("changePassword_id"), "Click on change password.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static void txtCurrentPassword(String currentPassword){
		try {
			elementFinderByXpath(prop.getProperty("currentPassword_xpath"), "Entered current password i.e '"+currentPassword+"'.").sendKeys(currentPassword);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static void txtNewPassword(String newPassword){
		try {
			elementFinderByID(prop.getProperty("newPassword_id"), "Entered new password i.e '"+newPassword+"'.").sendKeys(newPassword);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static void txtConfirmPassword(String confirmPassword){
		try {
			elementFinderByXpath(prop.getProperty("confirmPassword_xpath"), "Entered confirm password i.e '"+confirmPassword+"'.").sendKeys(confirmPassword);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static void btnSaveChangePassword(){
		try {
			elementFinderByXpath(prop.getProperty("saveChangePassword_xpath"), "Click on save password").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static void btnCancelChangePassword(){
		try {
			elementFinderByXpath(prop.getProperty("cancelChangePassword_xpath"), "Click on cancel password.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static String getForgetChangePasswordMissmatchText(){
		String msg = null;
		try {
			msg = elementFinderByID(prop.getProperty("passwordMissmatchText_id"), "Getting change password message..").getAttribute("aria-label");
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return null;
	}

	public static void linkAboutUS(){
		try {
			elementFinderByID(prop.getProperty("aboutUs_id"), "Click on About us.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static String getAboutUSVersion(){
		String versionInfo = null;
		try {
			versionInfo = elementFinderByXpath(prop.getProperty("aboutUsPopUpgetVersionText_xpath"), "getting version from about us.").getText();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return versionInfo;
	}

	public static void linkPrivacyPolicy(){
		try {
			elementFinderByXpath(prop.getProperty("privacyPolicy_xpath"), "Click on privacy policy.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void linkTermsAndCondition(){
		try {
			elementFinderByXpath(prop.getProperty("termsConditions_xpath"), "Click on Terms and condition.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnSignOut(){
		try {
			elementFinderByID(prop.getProperty("signOut_id"), "Click on sign out.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnEditProfile(){
		try {
			elementFinderByID(prop.getProperty("editprofileIcon_id"), "Click on edit profile.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void txtFirstName(String firstName){
		try {
			elementFinderByXpath(prop.getProperty("firstName_xpath"), "Clear first name textbox.").clear();
			elementFinderByXpath(prop.getProperty("firstName_xpath"), "Entered first name i.e '"+firstName+"'.").sendKeys(firstName);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void txtLastName(String lastName){
		try {
			elementFinderByXpath(prop.getProperty("lastName_xpath"), "Clear last name textbox.").clear();
			elementFinderByXpath(prop.getProperty("lastName_xpath"), "Entered last name i.e '"+lastName+"'.").sendKeys(lastName);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnCancelProfile(){
		try {
			elementFinderByXpath(prop.getProperty("cancelProfile_xpath"), "Click on cancel profile.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnSaveProfile(){
		try {
			elementFinderByID(prop.getProperty("saveProfile_id"), "Click on save profile.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnUploadPic(String filePath){
		try {
			System.out.println(filePath);
			elementFinderByXpath(prop.getProperty("uploadProfile_id"), "Changed profile pic i.e: '"+filePath+"'.").sendKeys(filePath);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btn_XClose(){
		try {
			elementFinderByXpath(prop.getProperty("closeXbtn_xpath"), "Click on 'X' button.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void linkHorizontalCatName(String catName){
		try {
			List<WebElement> ele = elementsFinderByXpaths(prop.getProperty("catNameHorizontal_listView_xpath"), "Click on category name. i.e: "+catName+"'.");
			for(WebElement element : ele){
				String categorie = element.getAttribute("aria-label");
				if(categorie.equalsIgnoreCase(catName)){
					element.click();
				}
			}
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnThumbnails(){
		try {
			elementFinderByXpath(prop.getProperty("bookThumbnails_xpath"), "Click on book thumbnail.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
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
			size = elementsFinderByXpaths(prop.getProperty("markAsFav_listview_xpath"), "Getting favorite book count..").size();
			Log.info("Favourite book count is: "+size+"'.");
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return size;
	}
	
	public static void btnMarkAsFav(int bookNum){
		try {
			List<WebElement> elements = elementsFinderByXpaths(prop.getProperty("markAsFav_listview_xpath"), "Click on mark as favorite on book number: "+bookNum+1+".");
			elements.get(bookNum).click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static void btnUnmarkAsFav(int bookNum){
		try {
			linkFavorite();
			List<WebElement> elements = elementsFinderByXpaths(prop.getProperty("unmarkAsFav_listview_xpath"), "Click on mark as unfavorite on book number: "+bookNum+1+".");
			elements.get(bookNum).click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static String getUnmarkAsFav_msgNoBookFound(){
		String msg = null;
		try {
			msg = elementFinderByXpath("unmarkAsFav_msgNoBookAvailable_xpath", "Getting message from unmark as favorite.").getText();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return null;
	}
	
	public static void allUnmarkAsFav(){
		try {
			linkFavorite();
			List<WebElement> elements = elementsFinderByXpaths(prop.getProperty("unmarkAsFav_listview_xpath"), "All books mark as an unfavorite.");
			for(WebElement element : elements){
				element.click();
				threadHold_2Sec();
			}
			threadHold_2Sec();
			if(elementFinderByXpath(prop.getProperty("unmarkAsFav_listview_xpath"), "Getting msg... No book Availabe in Favourite.")
					.equals("No books have been marked as favorite")){
				Log.info("message found 'No books have been marked as favorite'");
			}else{
				Log.info("message NOT found 'No books have been marked as favorite'");
			}
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}

	public static void btnMoreInfo(int num){
		try {
			List<WebElement> elements = elementsFinderByXpaths(prop.getProperty("moreInfo_listview_xpath"), "Click on more info.");
			elements.get(num).click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static void btnAnalytics(){
		try {
			elementFinderByID(prop.getProperty("analytics_id"), "Click on Analytics.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static void drpdwnStudentInfo(){
		try {
			elementFinderByXpath(prop.getProperty("studentInfo_xpath"), "Click on student info in a statistic.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static String getBookOpenedAssigned(){
		//String bookTitle = null;
		
		String bookTitlecount=null;
		try {
			//bookTitle = elementFinderByXpath(prop.getProperty("getBookOpenedAssigned_xpath"), "getBookOpenedAssigned").getText();
			bookTitlecount = elementFinderByXpath(prop.getProperty("getBookOpenedAssignedCount_xpath"), "Getting message from 'BookOpenedAssigned'.").getText();
			Log.info("BookOpenedAssigned: "+bookTitlecount);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return bookTitlecount;
	}
	
	public static String getAverageReadingTime(){
		String AverageReadingTimecount=null;
		try {
			AverageReadingTimecount = elementFinderByXpath(prop.getProperty("getAverageReadingTimeCount_xpath"), "Getting message from 'AverageReadingTime'.").getText();
			Log.info("AverageReadingTime: "+AverageReadingTimecount);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return AverageReadingTimecount;
	}
	
	public static String getAveragePagesRead(){
		String AveragePagesReadCount=null;
		try {
			AveragePagesReadCount = elementFinderByXpath(prop.getProperty("getAveragePagesReadCount_xpath"), "Getting message from 'AveragePagesRead'.").getText();
			Log.info("AverageReadingTime: "+AveragePagesReadCount);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return AveragePagesReadCount;
	}
	
	public static String getAverageReadingSession(){
		String AverageReadingSession=null;
		try {
			AverageReadingSession = elementFinderByXpath(prop.getProperty("getAverageReadingSessionCount_xpath"), "Getting message from 'AveragePagesRead'.").getText();
			Log.info("AverageReadingSession: "+AverageReadingSession);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return AverageReadingSession;
	}
	
	public static String getAverageReadingTimeSession(){
		String AverageReadingTimeSession=null;
		try {
			AverageReadingTimeSession = elementFinderByXpath(prop.getProperty("getAverageReadingTime/SessionCount_xpath"), "Getting message from 'AverageReadingTimeSession'.").getText();
			Log.info("AverageReadingTimeSession: "+AverageReadingTimeSession);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return AverageReadingTimeSession;
	}
	
	public static String getAveragePagesReadSessionCount(){
		String AveragePagesReadSessionCount=null;
		try {
			AveragePagesReadSessionCount = elementFinderByXpath(prop.getProperty("getAveragePagesRead/SessionCount_xpath"), "Getting message from 'AveragePagesReadSessionCount'.").getText();
			Log.info("AverageReadingTimeSession: "+AveragePagesReadSessionCount);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return AveragePagesReadSessionCount;
	}
	
	public static String getAverageNotesSharedCreatedCount(){
		String AverageNotesSharedCreatedCount=null;
		try {
			AverageNotesSharedCreatedCount = elementFinderByXpath(prop.getProperty("getAverageNotesShared/CreatedCount_xpath"), "Getting message from 'AverageNotesSharedCreatedCount'.").getText();
			Log.info("AverageNotesSharedCreatedCount: "+AverageNotesSharedCreatedCount);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return AverageNotesSharedCreatedCount;
	}
	
	public static String getAverageHighlightSharedCreatedCount(){
		String AverageHighlightSharedCreatedCount=null;
		try {
			AverageHighlightSharedCreatedCount = elementFinderByXpath(prop.getProperty("getAverageHighlightShared/CreatedCount_xpath"), "Getting message from 'AverageHighlightSharedCreatedCount'.").getText();
			Log.info("AverageHighlightSharedCreatedCount: "+AverageHighlightSharedCreatedCount);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return AverageHighlightSharedCreatedCount;
	}
	
	public static String getAvgResourceViewedAvailableCount(){
		String AvgResourceViewedAvailableCount=null;
		try {
			AvgResourceViewedAvailableCount = elementFinderByXpath(prop.getProperty("getAvg.ResourceViewed/AvailableCount_ID"), "Getting message from 'AvgResourceViewedAvailableCount'.").getText();
			Log.info("AvgResourceViewedAvailableCount: "+AvgResourceViewedAvailableCount);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return AvgResourceViewedAvailableCount;
	}
	
	public static String getBookTitleMoreInfo(){
		String bookTitle = null;
		try {
			bookTitle = elementFinderByXpath(prop.getProperty("bookTitleMoreInfo_xpath"), "Getting Title from more info.").getText();
			Log.info("Title: "+bookTitle);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return bookTitle;
	}
	
	public static String getBookAuthorMoreInfo(){
		String bookAuthor = null;
		try {
			bookAuthor = elementFinderByXpath(prop.getProperty("authorMoreInfo_xpath"), "Getting Author from more info.").getText();
			Log.info("Author: "+bookAuthor);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return bookAuthor;
	}
	
	public static String getBookDiscriptionMoreInfo(){
		String bookDiscription = null;
		try {
			bookDiscription = elementFinderByXpath(prop.getProperty("descriptionMoreInfo_xpath"), "Getting Description from more info.").getText();
			Log.info("Description: "+bookDiscription);
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
		return bookDiscription;
	}
	
	public static void btnSkipHelpScreen(){
		try {
			elementFinderByXpath(prop.getProperty("Skip_xpath"), "Click on skip help screen.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	
	public static void btnNextHelpScreen(){
		try {
			elementFinderByXpath(prop.getProperty("NextHelpScreen_xpath"), "Click on next help screen.").click();
		} catch (Exception e) {
			System.out.println("Element not present. ERROR: "+e.getMessage());
		}
	}
	//Analytics part pending
}
