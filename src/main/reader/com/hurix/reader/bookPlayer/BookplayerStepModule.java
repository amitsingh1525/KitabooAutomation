package com.hurix.reader.bookPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class BookplayerStepModule extends UIElements {
	public static Properties prop = getProperty(System.getProperty("user.dir")+"/config/reader/bookplayer.properties");

	public static void btnbacktobookshelf(){
		try {
			elementFinderByXpath(prop.getProperty("backtobookshelf_xpath"), "Click on BookShelf.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnBookPlayerProfileIcon(){
		try {
			elementFinderByID(prop.getProperty("bookPlayerProfileIcon_ID"), "Click on Profile Icon.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnBookPlayerProfileIcon_Signout(){
		try {
			elementFinderByXpath(prop.getProperty("bookPlayerProfileIconSignout_Xpath"), "Click on Signout.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnSubmit(){
		try {
			elementFinderByXpath(prop.getProperty("submit_xpath"), "Click on Submit.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnNextPageNavigation(){
		try {
			elementFinderByXpath(prop.getProperty("nextPageNavigation_xpath"), "Click on next page arrow.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnPreviousPageNavigation(){
		try {
			elementFinderByXpath(prop.getProperty("previousPageNavigation_xpath"), "Click on previous page arrow.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btntableofcontentandresources(){
		try {
			//changes
			elementFinderByID(prop.getProperty("tableofcontentandresources_ID"), "Click on TOC.").click();
			/*elementFinderByID(prop.getProperty("resources_ID"), "btn_resources").click();
			elementFinderByID(prop.getProperty("resources_drpdwn_xpath"), "drpdwn_resourcelist").click();*/
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnresources(){
		try {
			elementFinderByXpath(prop.getProperty("resources_xpath"), "Click on resources.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnResourceDrpdwn(){
		try {
			elementFinderByXpath(prop.getProperty("resources_drpdwn_xpath"), "Click on resources dropdown.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static int getResourcelst(){
		btntableofcontentandresources();
		btnresources();
		btnResourceDrpdwn();
		int size = elementsFinderByXpaths(prop.getProperty("resources_list_drpdwn_lstview_xpath"), "getting resources list.").size();
		btnResourceDrpdwn();
		btntableofcontentandresources();
		return size;
	}
	
	public static void btnResourcelst(int i){
		try {
			List<WebElement> element = elementsFinderByXpaths(prop.getProperty("resources_list_drpdwn_lstview_xpath"), "Click on resources.");
			element.get(i).click();
			String value = element.get(i).getAttribute("aria-label");
			String title = value.replaceAll("Page [0-9]", "");
			String pageNum = value.replaceAll("(.+?)Page ", "");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("epub_"+pageNum)));
			threadHold_2Sec();
			Driver.driver.switchTo().frame("epub_"+pageNum);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title=\""+title+"\"]")));
			threadHold_2Sec();
			WebElement ele = Driver.driver.findElement(By.xpath("//*[@title=\""+title+"\"]"));
			JavascriptExecutor executor = (JavascriptExecutor)Driver.driver;
			executor.executeScript("arguments[0].click();", ele);
			threadHold_5Sec();
			Driver.driver.navigate().refresh();
			Driver.driver.switchTo().alert().accept();
			threadHold_5Sec();
			Driver.driver.switchTo().defaultContent();
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
			Driver.driver.switchTo().defaultContent();
		}
	}
	
	public static void markup(int pageNum,String title){
		try {
			
			threadHold_2Sec();
			Driver.driver.switchTo().frame("epub_"+pageNum);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\""+title+"\"]")));
			threadHold_2Sec();
			WebElement ele = Driver.driver.findElement(By.xpath("//*[@class=\""+title+"\"]"));
			JavascriptExecutor executor = (JavascriptExecutor)Driver.driver;
			executor.executeScript("arguments[0].click();", ele);
			Log.info("Click on "+title+" markup");
			threadHold_2Sec();
			/*Driver.driver.navigate().refresh();
			Driver.driver.switchTo().alert().accept();
			threadHold_5Sec();*/
			Driver.driver.switchTo().defaultContent();
			threadHold_2Sec();
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
			Driver.driver.switchTo().defaultContent();
		}
	}
	
	public static void dropdownselect(int dropdownno){
		try {
			
			threadHold_2Sec();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"select_item"+dropdownno+"\"]")));
			WebElement ele = Driver.driver.findElement(By.xpath("//*[@id=\"select_item"+dropdownno+"\"]"));
			JavascriptExecutor executor = (JavascriptExecutor)Driver.driver;
			executor.executeScript("arguments[0].click();", ele);
			Log.info("Select dropdown "+dropdownno);
			threadHold_2Sec();
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void mathkeybord(){
		try {
			Driver.driver.switchTo().frame("equationFrame");
			elementFinderByXpath("//*[@data-latex-val='math-second-1']", "Click on log(x).").click();
			elementFinderByXpath("//*[@data-latex-val='math-first-2']", "Click on √x.").click();
			elementFinderByXpath("//*[@data-latex-val='math-second-3']", "Click on π.").click();
			
			elementFinderByXpath("//*[@data-latex-val='math-four-1']", "Click on 123.").click();
			elementFinderByXpath("//*[@data-latex-val='choosePanel2-first-4']", "Click on 4.").click();
			elementFinderByXpath("//*[@data-latex-val='choosePanel2-second-4']", "Click on /.").click();
			elementFinderByXpath("//*[@data-latex-val='choosePanel2-first-8']", "Click on 8.").click();
			elementFinderByXpath("//*[@data-latex-val='math-four-1']", "Click on 123.").click();
			threadHold_2Sec();
			
			elementFinderByXpath("//*[@class='addBtn']", "Click on save button.").click();
			Driver.driver.switchTo().defaultContent();

		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnmathtextbox(String mathtext){
		try {
			Driver.driver.switchTo().frame("equationFrame");
			elementFinderByXpath(prop.getProperty("mathSwitchKeybord_xpath"), "Click on Switch Keybord.").click();
			elementFinderByXpath(prop.getProperty("mathtextbox_xpath"), "Click on textbox.").click();
			elementFinderByXpath(prop.getProperty("mathtextbox_xpath"), "Enter text on textbox.").sendKeys(mathtext);
			elementFinderByXpath(prop.getProperty("mathsave_xpath"), "Click on Save.").click();
			
			threadHold_2Sec();
			Driver.driver.switchTo().defaultContent();

		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	
	public static void lstresources_list(){
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("resources_list_lstview_xpath"), "lst_resources_list");

		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void lstresources_list_drpdwn(){
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("resources_list_drpdwn_lstview_xpath"), "lst_resources_list_drpdwn");

		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btncontents(){
		try {
			elementFinderByXpath(prop.getProperty("contents_xpath"), "Click on Contents.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btncontentsdrpdwn(){
		try {
			List<WebElement> ele = elementsFinderByXpaths(prop.getProperty("content_drpdwn_lstview_xpath"), "Click on contents dropdown.");
			ele.get(0).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btncontentsChildlist(){
		try {
			List<WebElement> ele = elementsFinderByXpaths(prop.getProperty("content_childList_lstview_xpath"), "Click on content child list.");
			ele.get(0).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void lstcontent_list(int chapterno){
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("content_list_lstview_xpath"), "Click on list content.");
			element.get(chapterno).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnhighlights(){
		try {
			elementFinderByXpath(prop.getProperty("highlights_xpath"), "Click on highlight.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnnotes(){
		try {
			elementFinderByXpath(prop.getProperty("notes_xpath"), "Click on notes.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void lstfilter_list(){
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("filter_list_lstview_xpath"), "Click on filter");

		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnsearch(){
		try {
			elementFinderByID(prop.getProperty("searchbtn_ID"), "Click on search").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtSearch(String text){
		try {
			elementFinderByID(prop.getProperty("searchboxtxt_ID"), "Enterd search text '"+text+"'").sendKeys(text);
			elementFinderByID(prop.getProperty("searchboxtxt_ID"), "Press enter key").sendKeys(Keys.ENTER);
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static String searchResult(int i){
		String pageNumber = null;
		try {
			pageNumber = getPageNumsearchResult(i);
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("searchresult_lstview_xpath"), "Click on search result list.");
			element.get(i).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return pageNumber;
	}
	
	public static String getPageNumsearchResult(int i){
		String pagenum = null;
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("pageNumOnSearchText_lstview_xpath"), "Getting page number from the search result.");
			pagenum = element.get(i).getText();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return pagenum;
	}

	public static String getinvalidsearchmsg(){
		String msg = null;
		try {
			msg = elementFinderByXpath(prop.getProperty("invalidsearch_visibletext_xpath"), "Getting Invalid search msg.").getText();
			System.out.println("Invalid search message found: "+ msg);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return msg;
	}

	public static void btnmyData(){
		try {
			elementFinderByID(prop.getProperty("mydata_ID"), "Click on my data.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnmyDatahighlight(){
		try {
			elementFinderByXpath(prop.getProperty("highlights_xpath"), "Click on my data highlight tab.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnFilter(){
		try {
			elementFinderByXpath(prop.getProperty("filter_xpath"), "click on filter.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnClearAllFilter(){
		try {
			elementFinderByXpath(prop.getProperty("filter_allhighlight_uncheck_xpath"), "Click on all checkbox.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxyellowhighlight(){
		try {
			elementFinderByXpath(prop.getProperty("filter_yellowhighlight_uncheck_xpath"), "Click on yellow highlight checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxredhighlight(){
		try {
			elementFinderByXpath(prop.getProperty("filter_redhighlight_uncheck_xpath"), "Click on yellow red checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxpurplehighlight(){
		try {
			elementFinderByXpath(prop.getProperty("filter_purplehighlight_uncheck_xpath"), "Click on purple highlight checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxgreenhighlight(){
		try {
			elementFinderByXpath(prop.getProperty("filter_greenhighlight_uncheck_xpath"), "Click on green highlight checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxbluehighlight(){
		try {
			elementFinderByXpath(prop.getProperty("filter_bluehighlight_uncheck_xpath"), "Click on blue highlight checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnmyDatanotes(){
		try {
			elementFinderByXpath(prop.getProperty("notes_xpath"), "Click on notes.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnClearAllNoteschkbx(){
		try {
			elementFinderByXpath(prop.getProperty("filter_allnotes_uncheck_xpath"), "Click on all checkbox.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnmyDatanormalnotes(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("normalnotes_drpdwn_xpath"), "Click on normal notes dropdown xpath.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	
	public static int getHighLightCounts(){
		int size = 0;
		try {
			size= Driver.driver.findElements(By.xpath(prop.getProperty("highlights_list_xpath"))).size();
			System.out.println("List Count:"+ size);

			String highlightsize = Driver.driver.findElement(By.xpath(prop.getProperty("HighlightCount_visibletext_xpath"))).getText().replace("Highlights ", "");
			System.out.println("Indicator count :"+ highlightsize.replace("Highlights ", ""));
			
			if(size == Integer.parseInt(highlightsize)){
				Log.pass("Both counts matched. In Indicator: "+highlightsize+". And list shown: "+size);
			}else{
				Log.fail("Both counts dosen't matched. In Indicator: "+highlightsize+". And list shown: "+size);
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return size;
	}
	
	public static int getNotesCounts(){
		int size = 0;
		try {
			size= Driver.driver.findElements(By.xpath(prop.getProperty("highlights_list_xpath"))).size();
			System.out.println("List Count:"+ size);

			String highlightsize = Driver.driver.findElement(By.xpath(prop.getProperty("HighlightCount_visibletext_xpath"))).getText().replace("Notes ", "");
			System.out.println("Indicator count :"+ highlightsize);
			if(size == Integer.parseInt(highlightsize)){
				Log.pass("Both counts matched. In Indicator: "+highlightsize+". And list shown: "+size);
			}else{
				Log.fail("Both counts dosen't matched. In Indicator: "+highlightsize+". And list shown: "+size);
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return size;
	}
	
	public static void chkbxNotesForAll(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("chknormalnotesAll_xpath"), "Click on all checkbox.").click();
			elementFinderByXpath(prop.getProperty("chknormalnotesAll_xpath"), "Click on all checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void chkbxyellownotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_yellownotes_uncheck_xpath"), "Click on yellow notes checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxrednotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_rednotes_uncheck_xpath"), "Click on red notes checkbox.").click();
			threadHold_2Sec();

		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxpurplenotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_purplenotes_uncheck_xpath"), "Click on purple notes checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxgreennotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_greennotes_uncheck_xpath"), "Click on green notes checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxbluenotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_bluenotes_uncheck_xpath"), "Click on blue notes checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnmyDatacontextualnotes(){
		try {
			threadHold_5Sec();
			elementFinderByXpath(prop.getProperty("contextualnotes_drpdwn_xpath"), "Click on contextual notes dropdown.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxContextualNotesForAll(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("chkContextualNotesAll_xpath"), "Click on contextual notes for all checkbox.").click();
			elementFinderByXpath(prop.getProperty("chkContextualNotesAll_xpath"), "Click on contextual notes for all checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void chkbxyellowcontextualnotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_yellowcontextualnotes_uncheck_xpath"), "Click on yellow contextual checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxredcontextualnotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_redcontextualnotes_uncheck_xpath"), "Click on red contextual checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxpurplecontextualnotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_purplecontextualnotes_uncheck_xpath"), "Click on purple contextual checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxgreencontextualnotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_greencontextualnotes_uncheck_xpath"), "Click on green contextual checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxbluecontextualnotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_bluecontextualnotes_uncheck_xpath"), "Click on blue contextual checkbox.").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}



	public static void btnpentool(){
		try {
			elementFinderByID(prop.getProperty("pentoolbtn_ID"), "Click on pentool.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnPenColour_Black(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpaletteblack_xpath"), "Click on pen black colour.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnPenColour_Red(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpalettered_xpath"), "Click on pen red colour.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnPenColour_Purple(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpalettepurple_xpath"), "Click on pen purple colour.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnPenColour_Green(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpalettegreen_xpath"), "Click on pen green colour.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnPenColour_Blue(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpaletteblue_xpath"), "Click on pen blue colour.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void dragthicknessbar(){
		try {
			Thread.sleep(2000);
			elementFinderByXpath(prop.getProperty("thicknessbarpoint_xpath"), "Click on thickness bar.");
			WebElement slider = elementFinderByXpath(prop.getProperty("thicknessbarpoint_xpath"), "Click on thicknessbar pointer");
			WebElement stroke_slider = elementFinderByXpath(prop.getProperty("thicknessbar_xpath"), "thicknessbar point");
			Actions action =  new Actions(Driver.driver);
			action.clickAndHold(slider);
			Thread.sleep(1000);
			action.moveToElement(stroke_slider, 0, 50).build().perform();
			action.click().build().perform();
			System.out.println("action completed");
			Thread.sleep(1000);
			System.out.println("slider");
			Thread.sleep(1000);

		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btneraser(){
		try {
			elementFinderByXpath(prop.getProperty("eraserbtn_xpath"), "Click on eraser.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnundo(){
		try {
			elementFinderByXpath(prop.getProperty("undobtn_xpath"), "Click on undo.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnclearall(){
		try {
			elementFinderByXpath(prop.getProperty("clearallbtn_xpath"), "Click on clearAll.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnclearallAlertPopup_Yes(){
		try {
			elementFinderByXpath(prop.getProperty("clearallYesbtn_xpath"), "Click on clear all pop-up.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnsavePenTool(){
		try {
			elementFinderByXpath(prop.getProperty("pentoolsavebtn_xpath"), "Click on Save pen tool.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnteacherReview(int stdnt){
		try {
			elementFinderByXpath(prop.getProperty("teacherreview_xpath"), "Click on teacher review").click();
			elementFinderByXpath("//*[@aria-label=\"reader5_student"+stdnt+" \"]", "Click on Student "+stdnt).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnstickynotes(){
		try {
			elementFinderByID(prop.getProperty("stickynotes_ID"), "Click on sticky notes.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnDeleteStickyNotes(){
		try {
			elementFinderByXpath(prop.getProperty("deleteSticky_Xpath"), "Click on delete sticky notes.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnSharedStickyNotes(){
		try {
			elementFinderByXpath(prop.getProperty("sharedStickyNotes_xpath"), "Click on share sticky notes.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static String getSharedStickyNotesCommentmsg(){
		String msg = "NA";
		try {
			msg = elementFinderByXpath(prop.getProperty("SharedStickyNotesCommentmsg_xpath"), "Getting sticky notes comment message.").getText();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return msg;
	}
	
	public static void chkbxSharedNotesToAllTeacher(){
		try {
			elementFinderByXpath(prop.getProperty("sharedAllTeacherStickyNoteschkbx_xpath"), "Click on checkbox to all teacher.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btndoneSharedStickyNotes(){
		try {
			elementFinderByXpath(prop.getProperty("DoneSharedStickyNotes_xpath"), "Click on done shared sticky notes.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	//If there any sticky notes apply on page then use this method for open sticky notes pop-up
	public static void btnStickyNotesInsidePage(int i, String pageNum){
		try {
			Driver.driver.switchTo().frame("epub_"+pageNum);
			List<WebElement> element = elementsFinderByXpaths(prop.getProperty("StickyNote_lstView_Xpath"), "Click on sticky notes inside page.");
			element.get(i).click();
			Driver.driver.switchTo().parentFrame();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnMyDataNotificationIcon(){
		try {
			elementFinderByID(prop.getProperty("teacherNotification_id"), "Click on my data notification icon.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnAcceptStickyNotes(int i){
		try {
			List<WebElement> ele = elementsFinderByXpaths(prop.getProperty("sharedNotesAccept_lstview_xpath"), "Click on Accept Sticky notes.");
			ele.get(i).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnDeclinedStickyNotes(int i){
		try {
			List<WebElement> ele = elementsFinderByXpaths(prop.getProperty("sharedNotesDecline_lstview_xpath"), "Click on Decline Sticky notes.");
			ele.get(i).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnCommentOnNotes(int i){
		try {
			List<WebElement> ele = elementsFinderByXpaths(prop.getProperty("sharedNotesCommentbtn_lstview_xpath"), "Click on comment on notes.");
			ele.get(i).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static int getCommentOnNotesCount(int i){
		int count = 0;
		try {
			List<WebElement> ele = elementsFinderByXpaths(prop.getProperty("sharedNotesCommentbtn_lstview_xpath"), "Gettin comment count.");
			count = Integer.parseInt(ele.get(i).getText().replaceAll("Comments ", ""));
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return count;
	}
	
	public static void txtCommentOnNotes(String comment){
		try {
			elementFinderByXpath(prop.getProperty("sharedNotesCommenttxt_xpath"), "Enterd Comment i.e '"+comment+"'.").sendKeys(comment);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnSendCommentOnNotes(){
		try {
			elementFinderByXpath(prop.getProperty("sharedNotesSendCommentbtn_xpath"), "Click on send comment.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnBackArrow(){
		try {
			elementFinderByXpath(prop.getProperty("sharedNotesBackArrowbtn_xpath"), "Click on back arrow.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static String getSharedNotesCountFromMyDataIcon(){
		String count = "0";
		try {
			count = elementFinderByXpath(prop.getProperty("getCountFromMyDataIcon_xpath"), "Getting shared notes count from my data icon.").getText();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return count;
	}
	
	//return number of accept button count
	public static int getSharedNotesCountFromlist(){
		int count = 0;
		try {
			count = elementsFinderByXpaths(prop.getProperty("sharedNotesAccept_lstview_xpath"), "Getting shared notes count from list.").size();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return count;
	}
	
	public static void btnhighlight(){
		try {
			elementFinderByID(prop.getProperty("highlight_ID"), "Click on highlight.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnzoomin(){
		try {
			elementFinderByXpath(prop.getProperty("zoomin_xpath"), "Click on Zoom.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnzoomout(){
		try {
			elementFinderByID(prop.getProperty("zoomout_ID"), "Click on Zoom out icon.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static String getzoomValue(){
		String zoomValue = null;
		try {
			zoomValue = elementFinderByID(prop.getProperty("zoomvalue_id"), "Getting zoom value.").getText();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return zoomValue;
	}

	public static void btnfittowidth(){
		try {
			elementFinderByXpath(prop.getProperty("fittowidth_xpath"), "Click on fit to width.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnfittoheight(){
		try {
			elementFinderByXpath(prop.getProperty("fittoheight_xpath"), "Click on fit to height.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnsinglepage(){
		try {
			elementFinderByXpath(prop.getProperty("singlepagebtn_xpath"), "Click on single page view.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btndoublepage(){
		try {
			elementFinderByXpath(prop.getProperty("doublepagebtn_xpath"), "Click on double page view.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnthumbnail(){
		try {
			elementFinderByID(prop.getProperty("thumbnailbtn_ID"), "Click on thumbnail.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnhistoryprevious(){
		try {
			elementFinderByXpath(prop.getProperty("historyprevious_xpath"), "Click on history previous page.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnhistorynext(){
		try {
			elementFinderByXpath(prop.getProperty("historynext_xpath"), "Click on history next page.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void txtbxgotopage(String pageNum){
		try {
			elementFinderByID(prop.getProperty("gotopagetextbox_id"), "Click on go to page textbox.").click();
			elementFinderByID(prop.getProperty("gotopagetextbox_id"), "Clear go to page textbox field.").clear();
			elementFinderByID(prop.getProperty("gotopagetextbox_id"), "Entered page number in go to page textbox i.e. '"+pageNum+"'.").sendKeys(pageNum);
			elementFinderByID(prop.getProperty("gotopagetextbox_id"), "Press enter key.").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static String getpageNum(){
		String pageCount = "0";
		try {
			pageCount = elementFinderByXpath(prop.getProperty("getPageNumber_xpath"), "getting page number from go to page textbox.").getAttribute("aria-valuenow");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return pageCount;
	}

	public static void noOfpages(){
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("noofpages_lstview_xpath"), "noofpages");

		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static String getchapterTitle(){
		String chaptertitle = null;
		try {
			chaptertitle = elementFinderByID(prop.getProperty("chapterTitle_id"), "getting chapter details.").getText();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return chaptertitle;

	}

	public static void btnnextchapter(){
		try {
			elementFinderByXpath(prop.getProperty("nextchapterbtn_xpath"), "Click on next chapter.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnpreviouschapter(){
		try {
			elementFinderByXpath(prop.getProperty("previouschapterbtn_xpath"), "Click on previous chapter name.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnnextpage(){
		try {
			elementFinderByXpath(prop.getProperty("nextpagebtn_xpath"), "Click on next page.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnpreviouspage(){
		try {
			elementFinderByXpath(prop.getProperty("previouspagebtn_xpath"), "Click on previous page.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnfull_DefaultScreen(){
		try {
			elementFinderByID(prop.getProperty("full_Defaultscreenbtn_xpath"), "Click on full/Default screen.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnprofile(){
		try {
			elementFinderByXpath(prop.getProperty("profilebtn_xpath"), "Click on profile.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnsignout(){
		try {
			elementFinderByXpath(prop.getProperty("signoutbtn_xpath"), "Click on Signout.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void txtStickyNotes(String notes){
		try {
			elementFinderByID(prop.getProperty("editnote_ID"), "Enterd notes in sticky notes filed i.e. '"+notes+"'").sendKeys(notes);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnSaveStickyNotes(){
		try {
			elementFinderByXpath(prop.getProperty("postnotebtn_xpath"), "Clock on save sticky notes.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnStickyClr_orange(){
		try {
			elementFinderByXpath(prop.getProperty("clrOrangeSticky_xpath"), "Pick up a orange colour from sticky notes.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	
	public static void btnStickyClr_pink(){
		try {
			elementFinderByXpath(prop.getProperty("clrRedSticky_xpath"), "Pick up a pink colour from sticky notes.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnStickyClr_purple(){
		try {
			elementFinderByXpath(prop.getProperty("clrPurpleSticky_xpath"), "Pick up a purple colour from sticky notes.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnStickyClr_green(){
		try {
			elementFinderByXpath(prop.getProperty("clrGreenSticky_xpath"), "Pick up a green colour from sticky notes.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnStickyClr_blue(){
		try {
			elementFinderByXpath(prop.getProperty("clrBlueSticky_xpath"), "Pick up a blue colour from sticky notes.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void selectparagraph(int pageNum,int x, int y, String xpath){
		try {
			Thread.sleep(1000);
			Driver.driver.switchTo().frame("epub_"+pageNum);
			Thread.sleep(500);
			elementFinderByID(xpath, "Waiting for word..");
			WebElement from = elementFinderByID(xpath, "Click on word");
			JavascriptExecutor js = (JavascriptExecutor)Driver.driver;
			js.executeScript("arguments[0].click();", from);
			Thread.sleep(500);
			dragAndDrop(from, x, y);
			//dragAndDrop(from, 0, 45);
			Thread.sleep(1000);
			Driver.driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void selectparagraph_delete(int pageNum, String xpath){
		try {
			Thread.sleep(1000);
			Driver.driver.switchTo().frame("epub_"+pageNum);
			Thread.sleep(500);
			elementFinderByID(xpath, "Waiting for word..");
			WebElement from = elementFinderByID(xpath, "Click on word");
			from.click();
			JavascriptExecutor js = (JavascriptExecutor)Driver.driver;
			js.executeScript("arguments[0].click();", from);
			Thread.sleep(1000);
			Driver.driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static String getSharedHighlightCommentmsg(){
		String msg = "NA";
		try {
			msg = elementFinderByXpath("//*[@class='middleNOte sharedNote']/div[3]/div[2]/div[2]", "Getting Highlight notes comment message.").getText();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return msg;
	}
	
	public static void btnhighlightdelete(){
		try {
			elementFinderByID(prop.getProperty("highlightdelete_id"), "Click on delete highlight.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnhighlightsharedelete(){
		try {
			elementFinderByXpath(prop.getProperty("highlightShareDelete_xpath"), "Click on delete share highlight.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnhighlightnote(){
		try {
			elementFinderByID(prop.getProperty("highlightnote_id"), "Click on note highlight.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txthighlighttext(String note){
		try {
			elementFinderByID(prop.getProperty("highlightexttnote_id"), "Enter text on highlight text note").sendKeys(note);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnhighlightsearch(){
		try {
			elementFinderByID(prop.getProperty("highlightsearch_id"), "Click on search highlight").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}



	public static void bltyellow(){
		try {
			elementFinderByID(prop.getProperty("highlightyellow_id"), "Pick up a yellow colour from highlight.").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void bltred(){
		try {
			elementFinderByID(prop.getProperty("highlightred_id"), "Pick up a red colour from highlight").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void bltpurple(){
		try {
			elementFinderByID(prop.getProperty("highlightpurple_id"), "Pick up a purple colour from highlight").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void bltgreen(){
		try {
			elementFinderByID(prop.getProperty("highlightgreen_id"), "Pick up a green colour from highlight").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void bltblue(){
		try {
			elementFinderByID(prop.getProperty("highlightblue_id"), "Pick up a blue colour from highlight").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void drawLine(By element, int x,int y){
		try{
			UIElements.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			Thread.sleep(500);    
			WebElement slider = Driver.driver.findElement(element);
			Actions action =  new Actions(Driver.driver);
			action.clickAndHold(slider);
			Thread.sleep(1000);
			action.moveByOffset(x, y).build().perform();
			Log.info("Clicked to draw Line");
		}catch (Exception e){
			Log.error("Not Clicked to draw Line."+e.getMessage());
		}
	}
	
	public static void btnbookmark(){
		try {
			threadHold_5Sec();
			Driver.driver.switchTo().frame(elementFinderByID(prop.getProperty("bookmarkfram_ID"), "Switiching iframe.."));
			threadHold_2Sec();
			WebElement element0 = elementFinderByID(prop.getProperty("bookmarkThisPage_ID"), "Click on bookmark."); 
			JavascriptExecutor executor = (JavascriptExecutor)Driver.driver;
			executor.executeScript("arguments[0].click();", element0);
			Driver.driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtbookmark(String msg){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("bookmarkTitle_xpath"), "Cleared bookmark text.").clear();
			elementFinderByXpath(prop.getProperty("bookmarkTitle_xpath"), "Entered Bookmar i.e '"+msg+"'").sendKeys(msg);
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnaddbookmark(){
		try {
			elementFinderByXpath(prop.getProperty("addBookmark_xpath"), "Click on add bookmark.").click();	
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btntocbookmark(){
		try {
			elementFinderByXpath(prop.getProperty("bookmarks_xpath"), "Click on bookmark tab from toc.").click();	
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static int getbookmarkCounts(){
		int size = 0;
		try {
			size= Driver.driver.findElements(By.xpath(prop.getProperty("getting bookmark list count."))).size();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return size;
	}
	
	public static String getbookmarkpageno(int i){
		String pageno = "NA";
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("bookmarkpageno_lstView_xpath"), "getting page number from bookmark list count.");
			pageno = element.get(i).getText();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return pageno;
	}
	
	public static int bookmarklist(int i){
		int pageNum = 0;
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("bookmark_list_lstview_xpath"), "Click on bookmark from bookmark list.");
			pageNum = Integer.parseInt(getbookmarkpageno(i));
			element.get(i).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return pageNum;
	}
	
	/*public static int getbookmarkCount(){
		int size = 0;
		try {
			size= Driver.driver.findElements(By.xpath(prop.getProperty("highlights_list_xpath"))).size();
			System.out.println("List Count:"+ size);

			String highlightsize = Driver.driver.findElement(By.xpath(prop.getProperty("HighlightCount_visibletext_xpath"))).getText().replace("Notes ", "");
			System.out.println("Indicator count :"+ highlightsize);
			if(size == Integer.parseInt(highlightsize)){
				Log.pass("Both counts matched. In Indicator: "+highlightsize+". And list shown: "+size);
			}else{
				Log.fail("Both counts dosen't matched. In Indicator: "+highlightsize+". And list shown: "+size);
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return size;
	}*/
	
	public static void btndeletebookmark(){
		try {
		
			elementFinderByXpath(prop.getProperty("deleteBookmark_xpath"), "Click on delete bookmark.").click();	
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

}
