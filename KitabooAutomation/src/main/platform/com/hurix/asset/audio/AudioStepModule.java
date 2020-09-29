package com.hurix.asset.audio;

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

public class AudioStepModule extends UIElements {

	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/audio.properties");

	public static void popupmsg(){
		try {
			WebDriverWait wait = new WebDriverWait(Driver.driver, 30); 
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/form[1]/div[20]/div[2]/div/div/div/h1/a")));
			int size=Driver.driver.findElements(By.xpath("//*[@id='bookDeleteModal']/div[2]")).size();
			if(size >= 1)
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/form[1]/div[20]/div[2]/div/div/div/h1/a")));
				Driver.driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[20]/div[2]/div/div/div/h1/a")).click();
			}
			elementFinderByXpath(prop.getProperty("deleteaudioinarchive_xpath"), "btndeleteaudioinarchive").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	
	

	public static void btndeleteaudioinarchive(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("deleteaudioinarchive_xpath"), "btndeleteaudioinarchive").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnsoftdeteleaudio(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("softdeteleaudio_xpath"), "btnsoftdeteleaudio").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}			

	public static void btnharddeleteaudio(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("harddeleteaudio_xpath"), "btnharddeleteaudio").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	


	public static void btndeleteaudioinprogress(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("deleteaudioinprogress_xpath"), "deleteaudioinprogress").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnaudioyes(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("audioyes_xpath"), "btnaudioyes").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnaudiono(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("audiono_xpath"), "btnaudiono").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void msgaudiodeletemsg(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("msgaudiodeletemsg_xpath"), "msgaudiodeletemsg").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void msgaudioarchive(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("msgaudioarchive_xpath"), "msgaudioarchive").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void msgaudiorevert(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("msgaudiorevert_xpath"), "msgaudiorevert").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void msgaudioupdate(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("msgaudioupdate_xpath"), "msgaudioupdate").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void msgaudioupload(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("msgaudioupload_xpath"), "msgaudioupload").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnlogout(){
		try {
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("profile_id"), "btnprofile").click();
			elementFinderByID(prop.getProperty("logout_id"), "btnlogout").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnrevertAudioyes(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement revertyes = elementFinderByXpath(prop.getProperty("revertAudioyes_xpath"), "revertAudioyes");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",revertyes);
			elementFinderByXpath(prop.getProperty("revertAudioyes_xpath"), "revertAudioyes").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnrevertAudiono(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement revertno = elementFinderByXpath(prop.getProperty("revertAudiono_xpath"), "revertAudiono");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",revertno);
			elementFinderByXpath(prop.getProperty("revertAudiono_xpath"), "revertAudiono").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnupdateAudio(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement updateAudio = elementFinderByXpath(prop.getProperty("updateAudio_xpath"), "updateAudio_xpath");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",updateAudio);
			elementFinderByXpath(prop.getProperty("updateAudio_xpath"), "updateAudio_xpath").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void dropdowninArchiveAudio(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement inArchive = elementFinderByXpath(prop.getProperty("inArchiveAudio_xpath"), "dropdowninArchiveAudio");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",inArchive);
			elementFinderByXpath(prop.getProperty("inArchiveAudio_xpath"), "dropdowninArchiveAudio").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnarchiveAudio(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement archiveAudio = elementFinderByXpath(prop.getProperty("archiveAudio_Xpath"), "btnarchiveAudio");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",archiveAudio);
			elementFinderByXpath(prop.getProperty("archiveAudio_Xpath"), "btnarchiveAudio").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void dropdowninprogressaudio(){
		try {
			threadHold_2Sec();
			/*wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("dropdowninprogressaudio")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement inprogressaudio = elementFinderByID(prop.getProperty("inprogressaudio_id"), "btnarchiveAudio");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",inprogressaudio);*/
			elementFinderByXpath(prop.getProperty("inprogressaudio_xpath"), "dropdowninprogressaudio").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnrevertAudio(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement revertAudio = elementFinderByXpath(prop.getProperty("revertAudio_xpath"), "btnrevertAudio");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",revertAudio);
			elementFinderByXpath(prop.getProperty("revertAudio_xpath"), "btnrevertAudio").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}



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

	public static void btnaudioEdit(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByXpath(prop.getProperty("audioEdit_xpath"), "btnaudioEdit").click();
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


	public static void coverAudio(String coverpath){
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

	public static void uploadAudio(String audiopath){
		try {
			threadHold_2Sec();
			//System.out.println("audiopath"+audiopath);
			threadHold_5Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			Driver.driver.switchTo().frame(prop.getProperty("Audioframe_id")); 	
			Actions action = new Actions(Driver.driver);
			threadHold_2Sec();
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
			threadHold_2Sec();
			int size= Driver.driver.findElements(By.xpath("//*[@id='fileUploadInput']")).size();
			System.out.println(size);
			Driver.driver.findElement(By.id("fileUploadInput")).sendKeys(audiopath);
			//elementFinderByXpath(prop.getProperty("uploadAudio_xpath"), "btnuploadAudio").sendKeys(path);
			Driver.driver.switchTo().parentFrame();
			Driver.driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
		} catch (Exception e) 
		{
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

	public static void btnAddchapter(){
		try {
			threadHold_2Sec();
			threadHold_2Sec();
			JavascriptExecutor js = (JavascriptExecutor) Driver.driver;
			js .executeScript("document.getElementById(\"audioPlay\").play()");
			threadHold_2Sec();
			js .executeScript("document.getElementById(\"audioPlay\").pause()");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			txtchapterTime();
			elementFinderByXpath(prop.getProperty("Addchapter_xpath"), "btnAddchapter").click();
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

	public static void linkAudio(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByXpath(prop.getProperty("Audio_xpath"), "linkAudio").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void btnAddAudio(){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByXpath(prop.getProperty("AudioTab_xpath"), "BtnAddAudio").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void txtAudioTitle(String AudioTitle){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement audioTitle = elementFinderByID(prop.getProperty("AudioTitle_id"), "txtAudioTitle");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",audioTitle);
			elementFinderByID(prop.getProperty("AudioTitle_id"), "txtAudioTitle").clear();
			elementFinderByID(prop.getProperty("AudioTitle_id"), "txtAudioTitle").sendKeys(AudioTitle);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void txtAudioDesription(String AudioDesription){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByID(prop.getProperty("AudioDesription_id"), "txtAudioDesription").clear();
			elementFinderByID(prop.getProperty("AudioDesription_id"), "txtAudioDesription").sendKeys(AudioDesription);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void txtAudioTag(String AudioTag){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByID(prop.getProperty("AudioTag_id"), "txtAudioTag").clear();
			elementFinderByID(prop.getProperty("AudioTag_id"), "txtAudioTag").sendKeys(AudioTag);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void txtAudioCategory(String AudioCategory){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByID(prop.getProperty("AudioCategory_id"), "txtAudioCategory").clear();
			elementFinderByID(prop.getProperty("AudioCategory_id"), "txtAudioCategory").sendKeys(AudioCategory);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

}
