package com.hurix.asset.audio;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class AudioModule extends AudioStepModule
{
	
	public static void logout()
	{
		Log.startTestCase("Logout");
		if(Driver.driver.getCurrentUrl().contains("books.xhtml"))
		{
			btnlogout();
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}
		Log.endTestCase("End");
	}
	
	
	
	
	public static void audio(String audiopath,String title,String desc,String tag, String subject ,String transcriptfilepath,String coverpath,String invalidaudiofilepath,String wrongtranscriptfile)
	{
		Log.startTestCase("create audio");
		linklibrary();
		linkAsset();
		linkAudio();
		btnAddAudio();
		threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("audio.xhtml"))
		{
			Log.pass("After successfully login redirected to the home page"+Driver.driver.getCurrentUrl());
			
			invalidaudiofilepath=System.getProperty("user.dir")+coverpath;
			uploadAudio(invalidaudiofilepath);
			threadHold_5Sec();
						
			coverpath=System.getProperty("user.dir")+coverpath;
			coverAudio(coverpath); 
			
			
			audiopath=System.getProperty("user.dir")+audiopath;
			uploadAudio(audiopath);
			System.out.println("Wait Time: "+UIElements.waitTiming);
			//bracket ke ander apna invisible wala daal d

			WebDriverWait wait = new WebDriverWait(Driver.driver, 900); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-shadow ui-overlay-visible']/div[2]/img")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-shadow ui-overlay-visible']/div[2]/img")));
			
			threadHold_5Sec();
			threadHold_5Sec();
			txtAudioTitle(title);
			txtAudioDesription(desc);
			txtAudioTag(tag);
			txtAudioCategory(subject);
			
			System.out.println(System.getProperty("user.dir"));
			audiopath=System.getProperty("user.dir")+audiopath;
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			
			btnAddchapter();
			txtChapterTitle("ChapterTitle");
			txtChapterDescription("ChapterDescription");
			btnAddchapter();
			txtChapterTitle1("ChapterTitle1");
			txtChapterDescription1("ChapterDescription1");
						
			wrongtranscriptfile=System.getProperty("user.dir")+wrongtranscriptfile;
			btnaudioTranscriptFileNamePanel(wrongtranscriptfile);
			msg_invalidevttValidation();
			
			transcriptfilepath=System.getProperty("user.dir")+transcriptfilepath;
			btnaudioTranscriptFileNamePanel(transcriptfilepath);  //error for element is not found
						
			btnaudioarchive();
			btnaudioarchiveNo();
			btnAudioSave();
			//btnarchiveAudio();
			//btnaudioCancel();
			//btnaudioEdit();
			
			/*JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('//id of element').setAttribute('attr', '10')");*/
			
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}
		Log.endTestCase("End");
	}
	
	public static void changeaudio(String audiopath,String title,String desc,String tag, String subject ,String transcriptfilepath,String coverpath,String invalidaudiofilepath,String wrongtranscriptfile)
	{
		Log.startTestCase("Change audio");
		linklibrary();
		linkAsset();
		linkAudio();
		btnAddAudio();
		threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("audio.xhtml"))
		{
			Log.pass("After successfully login redirected to the home page"+Driver.driver.getCurrentUrl());
			
			invalidaudiofilepath=System.getProperty("user.dir")+invalidaudiofilepath;
			uploadAudio(invalidaudiofilepath);
						
			coverpath=System.getProperty("user.dir")+coverpath;
			coverAudio(coverpath); 
			
			audiopath=System.getProperty("user.dir")+audiopath;
			uploadAudio(audiopath);
					
			btnChangeaudio();
			
			uploadAudio(audiopath);
			
			threadHold_5Sec();
			threadHold_5Sec();
			threadHold_5Sec();
			threadHold_5Sec();
			txtAudioTitle(title);
			txtAudioDesription(desc);
			txtAudioTag(tag);
			txtAudioCategory(subject);
			
			System.out.println(System.getProperty("user.dir"));
			audiopath=System.getProperty("user.dir")+audiopath;
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			btnAddchapter();
			txtChapterTitle("ChapterTitle");
			txtChapterDescription("ChapterDescription");
			btnAddchapter();
			txtChapterTitle1("ChapterTitle1");
			txtChapterDescription1("ChapterDescription1");
						
			wrongtranscriptfile=System.getProperty("user.dir")+wrongtranscriptfile;
			btnaudioTranscriptFileNamePanel(wrongtranscriptfile);
			
			transcriptfilepath=System.getProperty("user.dir")+transcriptfilepath;
			btnaudioTranscriptFileNamePanel(transcriptfilepath);  //error for element is not found
						
			btnaudioarchive();
			btnaudioarchiveYes();
			//btnaudioCancel();
			//btnaudioEdit();
			
			/*JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('//id of element').setAttribute('attr', '10')");*/
			
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}Log.endTestCase("End");
		
	}
	
	public static void cancelaudio(String audiopath,String title,String desc,String tag, String subject ,String transcriptfilepath,String coverpath,String invalidaudiofilepath,String wrongtranscriptfile)
	{
		Log.startTestCase("Cancel audio");
		linklibrary();
		linkAsset();
		linkAudio();
		btnAddAudio();
		threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("audio.xhtml"))
		{
			Log.pass("After successfully login redirected to the home page"+Driver.driver.getCurrentUrl());
			
			invalidaudiofilepath=System.getProperty("user.dir")+coverpath;
			uploadAudio(invalidaudiofilepath);
						
			coverpath=System.getProperty("user.dir")+coverpath;
			coverAudio(coverpath); 
			
			audiopath=System.getProperty("user.dir")+audiopath;
			uploadAudio(audiopath);
			
			btnChangeaudio();
			
			uploadAudio(audiopath);
			
			txtAudioTitle(title);
			txtAudioDesription(desc);
			txtAudioTag(tag);
			txtAudioCategory(subject);
			
			System.out.println(System.getProperty("user.dir"));
			audiopath=System.getProperty("user.dir")+audiopath;
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			btnAddchapter();
			txtChapterTitle("ChapterTitle");
			txtChapterDescription("ChapterDescription");
			btnAddchapter();
			txtChapterTitle1("ChapterTitle1");
			txtChapterDescription1("ChapterDescription1");
						
			wrongtranscriptfile=System.getProperty("user.dir")+wrongtranscriptfile;
			btnaudioTranscriptFileNamePanel(wrongtranscriptfile);
			
			transcriptfilepath=System.getProperty("user.dir")+transcriptfilepath;
			btnaudioTranscriptFileNamePanel(transcriptfilepath);  //error for element is not found
		
			btnaudioCancel();
			//btnaudioEdit();
			
			
			/*JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('//id of element').setAttribute('attr', '10')");*/
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}Log.endTestCase("End");
		
	}
	
	public static void Editaudio(String audiopath,String title,String desc,String tag, String subject ,String transcriptfilepath,String coverpath,String invalidaudiofilepath,String wrongtranscriptfile)
	{
		Log.startTestCase("Edit audio");
		linklibrary();
		linkAsset();
		linkAudio();
		btnaudioEdit();
		threadHold_5Sec();
		btnaudioCancel();
		threadHold_5Sec();
		btnaudioEdit();
		
		if(Driver.driver.getCurrentUrl().contains("audio.xhtml"))
		{
			Log.pass("After successfully login redirected to the home page"+Driver.driver.getCurrentUrl());
			
			btnChangeaudio();
			
			invalidaudiofilepath=System.getProperty("user.dir")+invalidaudiofilepath;
			uploadAudio(invalidaudiofilepath);
						
			coverpath=System.getProperty("user.dir")+coverpath;
			coverAudio(coverpath); 
			
			audiopath=System.getProperty("user.dir")+audiopath;
			uploadAudio(audiopath);
			
			UIElements.waitTiming = 240;
			//bracket ke ander apna invisible wala daal d
						
			UIElements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-shadow ui-overlay-visible']/div[2]/img")));
			UIElements.waitTiming = 60;
			
			threadHold_5Sec();
			threadHold_5Sec();
			threadHold_5Sec();
			threadHold_5Sec();
			txtAudioTitle(title);
			txtAudioDesription(desc);
			txtAudioTag(tag);
			txtAudioCategory(subject);
			
			System.out.println(System.getProperty("user.dir"));
			audiopath=System.getProperty("user.dir")+audiopath;
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			btnAddchapter();
			txtChapterTitle("ChapterTitle");
			txtChapterDescription("ChapterDescription");
			btnAddchapter();
			txtChapterTitle1("ChapterTitle1");
			txtChapterDescription1("ChapterDescription1");
						
			wrongtranscriptfile=System.getProperty("user.dir")+wrongtranscriptfile;
			btnaudioTranscriptFileNamePanel(wrongtranscriptfile);
			
			transcriptfilepath=System.getProperty("user.dir")+transcriptfilepath;
			btnaudioTranscriptFileNamePanel(transcriptfilepath);  //error for element is not found
						
			btnaudioarchive();
			btnaudioarchiveNo();
			btnaudioarchive();
			btnaudioarchiveYes();
			//btnAudioSave();
			//btnaudioCancel();
			//btnaudioEdit();
			
			/*JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('//id of element').setAttribute('attr', '10')");*/
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}
		Log.endTestCase("End");
	}
	
	public static void revertaudio(String audiopath,String title,String desc,String tag, String subject ,String transcriptfilepath,String coverpath,String invalidaudiofilepath,String wrongtranscriptfile)
	{
		Log.startTestCase("Revert audio");
		linklibrary();
		linkAsset();
		linkAudio();
		dropdowninprogressaudio();
		dropdowninArchiveAudio();
		btnupdateAudio();
		//Update Book started..
		btnrevertAudio();
		btnrevertAudiono();
		btnrevertAudio();
		btnrevertAudioyes();
		dropdowninprogressaudio();
		dropdowninArchiveAudio();
		btnupdateAudio();
		//Update Book started..
		btnEditaudio();
		
		//Audio book has been reverted to previous version successfully.
		threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("audio.xhtml"))
		{
			Log.pass("After successfully login redirected to the home page"+Driver.driver.getCurrentUrl());
			
			threadHold_5Sec();
						
			coverpath=System.getProperty("user.dir")+coverpath;
			coverAudio(coverpath); 
			
			btnChangeaudio();
			
			audiopath=System.getProperty("user.dir")+audiopath;
			uploadAudio(audiopath);
					
			threadHold_5Sec();
			threadHold_5Sec();
			txtAudioTitle(title);
			txtAudioDesription(desc);
			txtAudioTag(tag);
			txtAudioCategory(subject);
			
			System.out.println(System.getProperty("user.dir"));
			audiopath=System.getProperty("user.dir")+audiopath;
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			
			btnAddchapter();
			txtChapterTitle("ChapterTitle");
			txtChapterDescription("ChapterDescription");
			btnAddchapter();
			txtChapterTitle1("ChapterTitle1");
			txtChapterDescription1("ChapterDescription1");
						
			wrongtranscriptfile=System.getProperty("user.dir")+wrongtranscriptfile;
			btnaudioTranscriptFileNamePanel(wrongtranscriptfile);
			msg_invalidevttValidation();
			
			transcriptfilepath=System.getProperty("user.dir")+transcriptfilepath;
			btnaudioTranscriptFileNamePanel(transcriptfilepath);  //error for element is not found
						
			btnaudioarchive();
			btnaudioarchiveNo();
			btnAudioSave();
			btnrevertAudio();
			//btnaudioCancel();
			//btnaudioEdit();
			
			/*JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('//id of element').setAttribute('attr', '10')");*/
			
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}
		Log.endTestCase("End");
	}
	
}