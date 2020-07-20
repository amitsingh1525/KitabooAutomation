package com.hurix.library.kitabooBooks;

import io.cucumber.java.en.Then;

import com.google.api.client.googleapis.media.MediaHttpUploader.UploadState;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;

public class KitabooBooksModule extends KitabooBooksStepModule {
	/*
	 * 
	 * Author: Amit Singh
	 * Date: 16/07/2020
	 * 
	 * 
	 */
	@Then("create a book with data")
	public static String bookCreationPDF(String isbn,String title,String author,String catLevel, String description,String language,
			String bookType, String keywords, String bookOriantaion, String bookPath){

		drpAddNew_HTMLCreateBook();
		threadHold(); //wait for 5 sec
		for(String winHandle : Driver.driver.getWindowHandles())  // Switch to new opened window
		{
			Driver.driver.switchTo().window(winHandle);

		}
		txtISBN(isbn);
		Log.info("ISBN enterd i.e '"+isbn+"'");
		txtTitle(title);
		Log.info("Title enterd i.e '"+title+"'");
		txtAuthor(author);
		Log.info("Author enterd i.e '"+author+"'");
		switch(catLevel){
		case "level 1":
			drpCategory("Automation");
			Log.info("Cat level 1 selected i.e 'Automation'");
			break;

		case "level 2":
			drpCategory("Com||Automation");
			Log.info("Cat level 2 selected i.e 'Com||Automation'");
			break;

		case "level 3":
			drpCategory("Com||Hurix||Automation");
			Log.info("Cat level 3 selected i.e 'Com||Hurix||Automation'");
			break;

		case "level 4":
			drpCategory("Com||Hurix||Team||Automation");
			Log.info("Cat level 4 selected i.e 'Com||Hurix||Team||Automation'");
			break;

		default:
			System.out.println("kindly pass 'level 1', 'level 2', 'level 3', and 'level 4'");
			Log.error("Invalid Category passed! You passed '"+catLevel+"'. Pass any one on them('level 1', 'level 2', 'level 3', and 'level 4') "
					+ "same formate");
			break;
		}

		txtDescription(description);
		Log.info("Description enterd i.e '"+description+"'");
		drpLanguageSelection(language); //language like English, Spanish, etc..
		Log.info("In Language selected i.e '"+language+"'");
		drpBookType(bookType); // Other, Standard, Workbook, etc.. 
		Log.info("In Book Type selected i.e '"+bookType+"'");
		txtkeywords(keywords);
		drpBookOriantation(bookOriantaion);// Dynamic, Lock Portrait, etc...
		Log.info("In Book Oriantaion selected i.e '"+bookOriantaion+"'");

		btnUploadBook(bookPath);
		Log.info("Book Sucessfully Uploaded i.e '"+bookPath+"'");
		btnFinish();
		return null;

	}
}
