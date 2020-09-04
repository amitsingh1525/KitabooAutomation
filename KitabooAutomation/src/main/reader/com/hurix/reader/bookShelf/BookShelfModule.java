package com.hurix.reader.bookShelf;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.hurix.automation.utility.Driver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookShelfModule extends BookShelfStepModule {

	@Given("Search and launch a book")
	public static String searchAndLaunchBook(String title){

		btnSearch();
		txt_Search(title);
		String totalBook = afterSearchBookFound();
		System.out.println(totalBook);
		searchBookThumbnail();
		threadHold_5Sec();
		btnMarkAsFav(0);
		return Driver.driver.getCurrentUrl();
	}

	public static void profileIconChangeUsingJPG(){
		btnProfileIcon();
		btnEditProfile();
		btnUploadPic(System.getProperty("user.dir")+"/resources/einstein.jpg");
		btnSaveProfile();
	}
	
	public static void catNavigationAndLaunch(String catName){
		linkHorizontalCatName(catName);
		btnThumbnails();
	}
	
	public static String userLogOut(){
		btnProfileIcon();
		btnSignOut();
		threadHold_2Sec();
		return Driver.driver.getCurrentUrl();
	}
	
	public static void redeemAccessCode(String accessCode){
		btnAccessCode();
		txtAccessCode(accessCode);
		btnAccessCodeSubmit();
	}
	
	public static String privacyPolicy(){
		btnProfileIcon();
		linkPrivacyPolicy();
		String parent_Window = Driver.driver.getWindowHandle();
		windowhandle();
		String url =  Driver.driver.getCurrentUrl();
		Driver.driver.close();
		Driver.driver.switchTo().window(parent_Window);
		btnProfileIcon();
		return url;
	}
	
	public static String termAndCondition(){
		btnProfileIcon();
		linkTermsAndCondition();
		String parent_Window = Driver.driver.getWindowHandle();
		windowhandle();
		String url =  Driver.driver.getCurrentUrl();
		Driver.driver.close();
		Driver.driver.switchTo().window(parent_Window);
		btnProfileIcon();
		return url;
	}
	
	public static String aboutUS(){
		btnProfileIcon();
		linkAboutUS();
		String msg = getAboutUSVersion();
		threadHold_2Sec();
		Driver.driver.navigate().refresh();
		return msg;
		
	}
	
	public static void changePassword(String currentPassword, String newPassword, String confirmPassword){
		btnProfileIcon();
		linkChangePassword();
		txtCurrentPassword(currentPassword);
		txtNewPassword(newPassword);
		txtConfirmPassword(confirmPassword);
		btnSaveChangePassword();
	}
	
	public static void uploadProfilePic(String path){
		btnProfileIcon();
		btnEditProfile();
		btnUploadPic(path);
	}
	
	public static void markAsFav(int bookNum){
		linkHorizontalCatName("ALL");
		BookShelfStepModule.btnMarkAsFav(bookNum);
	}
	
	public static void unmarkAsFav(int bookNum){
		BookShelfStepModule.btnUnmarkAsFav(bookNum);
	}
	
	public static void changefirstNameAndLastName(String firstName, String lastName, boolean clickOnSave){
		btnProfileIcon();
		btnEditProfile();
		txtFirstName(firstName);
		txtLastName(lastName);
		if(clickOnSave){
			btnSaveProfile();
		}else{
			btnCancelProfile();
		}
		
	}
	
	public static void analytics(){
		 btnMoreInfo(0);
		 btnAnalytics();
		/*String msg = getAboutUSVersion();
		threadHold_2Sec();
		Driver.driver.navigate().refresh();
		return msg;*/
	}
	
}
