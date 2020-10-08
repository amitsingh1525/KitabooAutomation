package com.hurix.library.kitabooBooks;

import io.cucumber.java.en.Given;
import java.time.Instant;
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
	
	
	@Given("create a book with data")
	//String isbn,String title,String author,String catLevel, String description,String language, String bookType, String keywords, String bookOriantaion, String bookPath
	//public static String bookCreationPDF(){
	public static String bookCreationPDF(String isbn,String title,String author,String category, String discription,
			String language, String bookType, String keywords, String bookOriantaion, String bookPath){
		
		String parentWin = Driver.driver.getWindowHandle();
		drpAddNew_HTMLCreateBook();
		threadHold_5Sec(); 
		for(String winHandle : Driver.driver.getWindowHandles())
		{
			Driver.driver.switchTo().window(winHandle);
		}
		txtISBN(isbn);
		txtTitle(title);
		txtAuthor(author);
		drpCategory(category);
		txtDescription(discription);
		drpLanguageSelection(language);
		drpBookType(bookType); 
		txtkeywords(keywords);
		drpBookOriantation(bookOriantaion);
		threadHold_5Sec();
		String[] paths = bookPath.split(",");
		for(String path : paths) {
			btnUploadBook(bookPath);
		}
		btnFinish();
		afterFinishBtnContinue();
		threadHold_5Sec();
		threadHold_5Sec();
		Driver.driver.switchTo().window(parentWin);
		return title;

	}
	
	public static String bookPublishAndArchivePDF(String title){
		
		txtSearch(title);
		threadHold_5Sec();
		btnPublish();
		btnArchived();
		return null;
	}
	
	
}
