package com.hurix.library.kitabooBooks;

import io.cucumber.java.en.Then;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

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
	//String isbn,String title,String author,String catLevel, String description,String language, String bookType, String keywords, String bookOriantaion, String bookPath
	public static String bookCreationPDF(){

		drpAddNew_HTMLCreateBook();
		threadHold(); //wait for 5 sec
		for(String winHandle : Driver.driver.getWindowHandles())  // Switch to new opened window
		{
			Driver.driver.switchTo().window(winHandle);
			//hey

		}
		long nowEpochTime = Instant.now().toEpochMilli();
		txtISBN(nowEpochTime+"");
		Log.info("ISBN enterd i.e '"+nowEpochTime+""+"'");
		txtTitle("AutomTeam_"+nowEpochTime);
		Log.info("Title enterd i.e '"+"AutomTeam_"+nowEpochTime+"'");
		txtAuthor("Automation Team");
		Log.info("Author enterd i.e '"+"Automation Team"+"'");
		String catLevel = "level 1";
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

		txtDescription("This book create by Automation Team. For testing purpose.");
		Log.info("Description enterd i.e '"+"This book create by Automation Team. For testing purpose"+"'");
		drpLanguageSelection("English"); //language like English, Spanish, etc..
		Log.info("In Language selected i.e '"+"English"+"'");
		drpBookType("Other"); // Other, Standard, Workbook, etc.. 
		Log.info("In Book Type selected i.e '"+"Other"+"'");
		txtkeywords("Automation, Hurix,  PDGTeam");
		Log.info("Keywords enterd i.e '"+"Automation, Hurix,  PDGTeam"+"'");
		drpBookOriantation("Dynamic");// Dynamic, Lock Portrait, etc...
		Log.info("In Book Oriantaion selected i.e '"+"Dynamic"+"'");

		threadHold();
		btnUploadBook("C:/Users/amit.singh/git/KitabooAutomation/KitabooAutomation/resources/Hurix_Showcase.pdf");
		rdbDefaultImage();
		btnFinish();
		afterFinishBtnContinue();
		Driver.driver.switchTo().defaultContent();
		
		return "AutomTeam_"+nowEpochTime;

	}
	
	public static String bookPublishPDF(String title){
		
		
		btnPublish();
		return null;
	}
	
	
}
