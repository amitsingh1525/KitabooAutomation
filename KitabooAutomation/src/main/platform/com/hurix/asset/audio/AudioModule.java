package com.hurix.asset.audio;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class AudioModule extends AudioStepModule
{
	public static void audio(String audiopath,String title,String desc,String tag, String subject ,String transcriptfilepath,String coverpath,String invalidaudiofilepath,String wrongtranscriptfile)
	{
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
			//btnaudioCancel();
			//btnaudioEdit();
			
			/*JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('//id of element').setAttribute('attr', '10')");*/
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}
		
	}
	
	public static void changeaudio(String audiopath,String title,String desc,String tag, String subject ,String transcriptfilepath,String coverpath,String invalidaudiofilepath,String wrongtranscriptfile)
	{
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
		
		}
		
	}
	
	public static void cancelaudio(String audiopath,String title,String desc,String tag, String subject ,String transcriptfilepath,String coverpath,String invalidaudiofilepath,String wrongtranscriptfile)
	{
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
			
			audiopath=System.getProperty("user.dir")+audiopath;
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
		
		}
		
	}
	
	public static void Editaudio(String audiopath,String title,String desc,String tag, String subject ,String transcriptfilepath,String coverpath,String invalidaudiofilepath,String wrongtranscriptfile)
	{
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
			
			btnChangeaudio();
			
			audiopath=System.getProperty("user.dir")+audiopath;
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
						
			btnaudioarchive();
			btnaudioarchiveNo();
			btnaudioarchive();
			btnaudioarchiveYes();
			btnAudioSave();
			//btnaudioCancel();
			//btnaudioEdit();
			
			/*JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('//id of element').setAttribute('attr', '10')");*/
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}
		
	}
	
}