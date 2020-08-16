package com.hurix.reader.bookShelf;

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
		btnMarkAsFav().get(0).click();
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

	
}
