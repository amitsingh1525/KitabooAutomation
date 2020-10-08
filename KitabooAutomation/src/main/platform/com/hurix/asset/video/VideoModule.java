package com.hurix.asset.video;

import java.time.Instant;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hurix.api.utility.EpochTime;
import com.hurix.asset.audio.AudioModule;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class VideoModule extends VideoStepModule
{
	
	public static void logout()
	{
		Log.startTestCase("Logout");
		if(Driver.driver.getCurrentUrl().contains("newVideo.xhtml"))
		{
			AudioModule.btnlogout();
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}
		Log.endTestCase("End");
	}
	
	public static void popup()
	{
		Log.startTestCase("Logout");
		if(Driver.driver.getCurrentUrl().contains("books.xhtml"))
		{
			AudioModule.popupmsg();
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}
		Log.endTestCase("End");
	}

	
	public static void video(String videopath,String VideoTitle,String VideoDesription,String VideoTag, String VideoCat ,String coverpath, String chaptername, String chapterdesc)
	{
		Log.startTestCase("create Video");
		AudioModule.linklibrary();
		AudioModule.linkAsset();
		long nowEpochTime = Instant.now().toEpochMilli();	
		btnaddVideo();
		threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("newVideo.xhtml"))
		{
			Log.pass("After successfully login redirected to the home page"+Driver.driver.getCurrentUrl());
			coverVideo(coverpath);
			uploadVideo(videopath);
			threadHold_5Sec();
			System.out.println("Wait Time: "+UIElements.waitTiming);
			WebDriverWait wait = new WebDriverWait(Driver.driver, 900); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-shadow ui-overlay-visible']/div[2]/img")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-shadow ui-overlay-visible']/div[2]/img")));
			threadHold_5Sec();
			threadHold_5Sec();
			txtVideoTitle(VideoTitle);
			txtVideoDesription(VideoDesription);
			txtVideoTag(VideoTag);
			txtVideocategory(VideoCat);
			System.out.println(System.getProperty("user.dir"));
			videopath=System.getProperty("user.dir")+videopath;
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			btnAddchapter();
			txtChapterTitle(chaptername+nowEpochTime);
			txtChapterDescription(chapterdesc+nowEpochTime);
			btnAddchapter();
			txtChapterTitle1(chaptername+nowEpochTime);
			txtChapterDescription1(chapterdesc+nowEpochTime);
			btnaudioarchive();
			btnaudioarchiveNo();
			btnAudioSave();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mesId']/div/ul/li/span[text()='Video uploaded successfully.']")));
			Videoarchive();
			
		}
		else
		{
			Log.fail("Expected URL contain 'books.xhtml' but found "+Driver.driver.getCurrentUrl());
		
		}
		Log.endTestCase("End");
	}
	
	
}