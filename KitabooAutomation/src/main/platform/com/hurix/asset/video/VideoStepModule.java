package com.hurix.asset.video;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.UIElements;

public class VideoStepModule extends UIElements {

	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/audio.properties");
	
	
	public static void txtVideoTitle(String VideoTitle){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement audioTitle = elementFinderByID(prop.getProperty("txttitle_id"), "");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",audioTitle);
			elementFinderByID(prop.getProperty("txttitle_id"), "Video Title textbox is found").clear();
			elementFinderByID(prop.getProperty("txttitle_id"), "Video Title data write in textbox").sendKeys(VideoTitle);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void txtVideoDesription(String VideoDesription){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByID(prop.getProperty("txtdescription_id"), "Video description textbox is found").clear();
			elementFinderByID(prop.getProperty("txtdescription_id"), "Video description data write in textbox").sendKeys(VideoDesription);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void txtVideoTag(String VideoTag){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByID(prop.getProperty("AudioTag_id"), "Video tag textbox is found").clear();
			elementFinderByID(prop.getProperty("AudioTag_id"), "Video tag data write in textbox").sendKeys(VideoTag);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	
	public static void txtVideocategory(String VideoCat){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByID(prop.getProperty("txtcategory_id"), "Video Category textbox is found").clear();
			elementFinderByID(prop.getProperty("txtcategory_id"), "Video Category data write in textbox").sendKeys(VideoCat);
			}
			catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnaddVideo(){
		try {
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("btnaddVideo_id"), "Add Video button is found").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

		
	
	
	public static void txtchTitle(){
		try {
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("txtchTitle_id"), "Chapter textbox is found").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtchDescription(){
		try {
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("txtchDescription_id"), "Chapter description is found").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void  btnsave(){
		try {
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("btnsave_id"), "Save button is found").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnchangevideo(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("btnchangevideo_xpath"), "Change video button is found").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnplay(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("btnplay_xpath"), "Play button is found").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnAddchapter(){
		try {
			threadHold_2Sec();
			threadHold_2Sec();
			JavascriptExecutor js = (JavascriptExecutor) Driver.driver;
			js .executeScript("document.getElementById(\"videoPlay\").play()");
			threadHold_2Sec();
			js .executeScript("document.getElementById(\"videoPlay\").pause()");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			txtchapterTime();
			elementFinderByXpath(prop.getProperty("btnaddchapter_xpath"), "Add chapter button is found").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}		
		
	public static void uploadVideo(String videopath){
		try {
			threadHold_2Sec();
			//System.out.println("audiopath"+audiopath);
			threadHold_5Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			Driver.driver.switchTo().frame(prop.getProperty("videoframe_id")); 	
			Actions action = new Actions(Driver.driver);
			threadHold_2Sec();
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
			threadHold_2Sec();
			int size= Driver.driver.findElements(By.xpath("//*[@id='fileUploadInput']")).size();
			System.out.println(size);
			Driver.driver.findElement(By.id("fileUploadInput")).sendKeys(videopath);
			//elementFinderByXpath(prop.getProperty("uploadAudio_xpath"), "btnuploadAudio").sendKeys(path);
			Driver.driver.switchTo().parentFrame();
			Driver.driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
		} catch (Exception e) 
		{
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void coverVideo(String coverpath){
		try {
			threadHold_2Sec();
			System.out.println("coverpath"+coverpath);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			int size= Driver.driver.findElements(By.id("fileuploader_input")).size();
			System.out.println(size);
			Driver.driver.findElement(By.id("fileuploader_input")).sendKeys(coverpath);
			//elementFinderByID(prop.getProperty("audioTranscriptFileNamePanel_id"), "btnaudioTranscriptFileNamePanel").sendKeys(transcriptfilepath);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void Videoarchive(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("Videoarchive_xpath"), "video is archived.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
//-----------------------------------------------------------------


	public static void msg_invalidevttValidation(){
		try {
			threadHold_2Sec();
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement messageSummary = elementFinderByXpath(prop.getProperty("messageSummary_xpath"), "messageSummary");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",messageSummary);
			//elementFinderByID(prop.getProperty("messageSummary_xpath"), "messageSummary").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void txtchapterTime(){
		try {
			threadHold_2Sec();
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement chapterTime = elementFinderByXpath(prop.getProperty("chapterTime_xpath"), "txtchapterTime");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",chapterTime);
			elementFinderByXpath(prop.getProperty("chapterTime_xpath"), "txtchapterTime").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnaudioTranscriptFileNamePanel(String transcriptfilepath){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			int size= Driver.driver.findElements(By.id("transcriptFileSheet_input")).size();
			System.out.println(size);
			Driver.driver.findElement(By.id("transcriptFileSheet_input")).sendKeys(transcriptfilepath);
			//elementFinderByID(prop.getProperty("audioTranscriptFileNamePanel_id"), "btnaudioTranscriptFileNamePanel").sendKeys(transcriptfilepath);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	


	public static void btnaudioCancel(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement audioCancel = elementFinderByXpath(prop.getProperty("audioCancel_xpath"), "btnaudioCancel");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",audioCancel);
			elementFinderByXpath(prop.getProperty("audioCancel_xpath"), "btnaudioCancel").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnaudioarchive(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement audioarchive = elementFinderByXpath(prop.getProperty("audioArchive_xpath"), "btnaudioarchive");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",audioarchive);
			elementFinderByXpath(prop.getProperty("audioArchive_xpath"), "btnaudioarchive").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnaudioarchiveNo(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement audioarchiveNo = elementFinderByXpath(prop.getProperty("audioNo_xpath"), "btnaudioarchiveNo");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",audioarchiveNo);
			elementFinderByXpath(prop.getProperty("audioNo_xpath"), "btnaudioarchiveNo").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void btnaudioarchiveYes(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement audioarchiveNo = elementFinderByXpath(prop.getProperty("audioYes_xpath"), "btnaudioarchiveYes");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",audioarchiveNo);
			elementFinderByXpath(prop.getProperty("audioYes_xpath"), "btnaudioarchiveYes").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	


	public static void linklibrary(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByID(prop.getProperty("Library_id"), "linklibrary").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnEditaudio(){
		try {

			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("Editaudio_xpath"), "btnEditaudio").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnChangeaudio(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement Changeaudio = elementFinderByXpath(prop.getProperty("Changeaudio_xpath"), "btnChangeaudio");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",Changeaudio);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByXpath(prop.getProperty("Changeaudio_xpath"), "btnChangeaudio").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void txtChapterTitle(String title){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement chaptertitle = elementFinderByID(prop.getProperty("ChapterTitle_id"), "txtChapterTitle");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",chaptertitle);
			elementFinderByID(prop.getProperty("ChapterTitle_id"), "txtChapterTitle").clear();
			elementFinderByID(prop.getProperty("ChapterTitle_id"), "txtChapterTitle").sendKeys(title);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void txtChapterDescription(String Description){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement chapterDescription = elementFinderByID(prop.getProperty("ChapterDescription_id"), "txtChapterDescription");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",chapterDescription);
			elementFinderByID(prop.getProperty("ChapterDescription_id"), "txtChapterDescription").clear();
			elementFinderByID(prop.getProperty("ChapterDescription_id"), "txtChapterDescription").sendKeys(Description);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	


	public static void txtChapterTitle1(String title){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement chaptertitle = elementFinderByID(prop.getProperty("ChapterTitle1_id"), "txtChapterTitle");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",chaptertitle);
			elementFinderByID(prop.getProperty("ChapterTitle1_id"), "txtChapterTitle").clear();
			elementFinderByID(prop.getProperty("ChapterTitle1_id"), "txtChapterTitle").sendKeys(title);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void txtChapterDescription1(String Description){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement chapterDescription = elementFinderByID(prop.getProperty("ChapterDescription1_id"), "txtChapterDescription");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",chapterDescription);
			elementFinderByID(prop.getProperty("ChapterDescription1_id"), "txtChapterDescription").clear();
			elementFinderByID(prop.getProperty("ChapterDescription1_id"), "txtChapterDescription").sendKeys(Description);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void btnAudioSave(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement AudioSave = elementFinderByID(prop.getProperty("AudioSave_id"), "btnAudioSave");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",AudioSave);
			elementFinderByID(prop.getProperty("AudioSave_id"), "btnAudioSave").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void linkAsset(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByID(prop.getProperty("Asset_id"), "linkAsset").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

}
