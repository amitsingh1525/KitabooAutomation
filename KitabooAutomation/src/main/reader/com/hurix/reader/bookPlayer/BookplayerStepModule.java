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
			elementFinderByXpath(prop.getProperty("backtobookshelf_xpath"), "btn_backtobookshelf").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnNextPageNavigation(){
		try {
			elementFinderByXpath(prop.getProperty("nextPageNavigation_xpath"), "btn_backtobookshelf").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnPreviousPageNavigation(){
		try {
			elementFinderByXpath(prop.getProperty("previousPageNavigation_xpath"), "btn_backtobookshelf").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btntableofcontentandresources(){
		try {
			//changes
			elementFinderByID(prop.getProperty("tableofcontentandresources_ID"), "btn_tableofcontentandresources").click();
			/*elementFinderByID(prop.getProperty("resources_ID"), "btn_resources").click();
			elementFinderByID(prop.getProperty("resources_drpdwn_xpath"), "drpdwn_resourcelist").click();*/
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnresources(){
		try {
			elementFinderByXpath(prop.getProperty("resources_xpath"), "btn_resources").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnResourceDrpdwn(){
		try {
			elementFinderByXpath(prop.getProperty("resources_drpdwn_xpath"), "drpdwn_resourcelist").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static int getResourcelst(){
		btntableofcontentandresources();
		btnresources();
		btnResourceDrpdwn();
		int size = elementsFinderByXpaths(prop.getProperty("resources_list_drpdwn_lstview_xpath"), "drpdwn_resourcelist").size();
		btnResourceDrpdwn();
		btntableofcontentandresources();
		return size;
	}
	
	public static void btnResourcelst(int i){
		try {
			List<WebElement> element = elementsFinderByXpaths(prop.getProperty("resources_list_drpdwn_lstview_xpath"), "drpdwn_resourcelist");
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
			elementFinderByID(prop.getProperty("contents_ID"), "btn_contents").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void lstcontent_list(int chapterno){
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("content_list_lstview_xpath"), "lst_content_list");
			element.get(chapterno).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnmydata(){
		try {
			elementFinderByID(prop.getProperty("mydata_ID"), "btn_mydata").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnhighlights(){
		try {
			elementFinderByXpath(prop.getProperty("highlights_xpath"), "btn_highlights").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnnotes(){
		try {
			elementFinderByXpath(prop.getProperty("notes_xpath"), "btn_notes").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void lstfilter_list(){
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("filter_list_lstview_xpath"), "lst_filter_list");

		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnsearch(){
		try {
			elementFinderByID(prop.getProperty("searchbtn_ID"), "btn_search").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtSearch(String text){
		try {
			elementFinderByID(prop.getProperty("searchboxtxt_ID"), "txt_searchbox").sendKeys(text);
			elementFinderByID(prop.getProperty("searchboxtxt_ID"), "txt_searchbox").sendKeys(Keys.ENTER);
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static String searchResult(int i){
		String pageNumber = null;
		try {
			pageNumber = getPageNumsearchResult(i);
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("searchresult_lstview_xpath"), "get page number");
			element.get(i).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return pageNumber;
	}
	
	public static String getPageNumsearchResult(int i){
		String pagenum = null;
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("pageNumOnSearchText_lstview_xpath"), "searchresult_lstview_xpath");
			pagenum = element.get(i).getText();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return pagenum;
	}

	public static String getinvalidsearchmsg(){
		String msg = null;
		try {
			msg = elementFinderByXpath(prop.getProperty("invalidsearch_visibletext_xpath"), "search results not found").getText();
			System.out.println("Invalid search message found: "+ msg);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return msg;
	}

	public static void btnmyData(){
		try {
			elementFinderByID(prop.getProperty("mydata_ID"), "btn_mydata").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnmyDatahighlight(){
		try {
			elementFinderByXpath(prop.getProperty("highlights_xpath"), "btn_highlights").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnFilter(){
		try {
			elementFinderByXpath(prop.getProperty("filter_xpath"), "btn_filter").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnClearAllFilter(){
		try {
			elementFinderByXpath(prop.getProperty("filter_allhighlight_uncheck_xpath"), "chkbx_all").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxyellowhighlight(){
		try {
			elementFinderByXpath(prop.getProperty("filter_yellowhighlight_uncheck_xpath"), "chkbx_yellow").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxredhighlight(){
		try {
			elementFinderByXpath(prop.getProperty("filter_redhighlight_uncheck_xpath"), "chkbx_red").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxpurplehighlight(){
		try {
			elementFinderByXpath(prop.getProperty("filter_purplehighlight_uncheck_xpath"), "chkbx_purple").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxgreenhighlight(){
		try {
			elementFinderByXpath(prop.getProperty("filter_greenhighlight_uncheck_xpath"), "chkbx_green").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxbluehighlight(){
		try {
			elementFinderByXpath(prop.getProperty("filter_bluehighlight_uncheck_xpath"), "chkbx_blue").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnmyDatanotes(){
		try {
			elementFinderByXpath(prop.getProperty("notes_xpath"), "btn_notes").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnClearAllNoteschkbx(){
		try {
			elementFinderByXpath(prop.getProperty("filter_allnotes_uncheck_xpath"), "chkbx_all").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnmyDatanormalnotes(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("normalnotes_drpdwn_xpath"), "drpdwn_normalnotes").click();
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
			elementFinderByXpath(prop.getProperty("chknormalnotesAll_xpath"), "chkbxNotesForAll").click();
			elementFinderByXpath(prop.getProperty("chknormalnotesAll_xpath"), "chkbxNotesForAll").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void chkbxyellownotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_yellownotes_uncheck_xpath"), "chkbx_yellow").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxrednotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_rednotes_uncheck_xpath"), "chkbx_red").click();
			threadHold_2Sec();

		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxpurplenotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_purplenotes_uncheck_xpath"), "chkbx_purple").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxgreennotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_greennotes_uncheck_xpath"), "chkbx_green").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxbluenotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_bluenotes_uncheck_xpath"), "chkbx_blue").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnmyDatacontextualnotes(){
		try {
			threadHold_5Sec();
			elementFinderByXpath(prop.getProperty("contextualnotes_drpdwn_xpath"), "drpdwn_contextualnotes").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxContextualNotesForAll(){
		try {
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("chkContextualNotesAll_xpath"), "chkbxContextualNotesForAll").click();
			elementFinderByXpath(prop.getProperty("chkContextualNotesAll_xpath"), "chkbxContextualNotesForAll").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void chkbxyellowcontextualnotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_yellowcontextualnotes_uncheck_xpath"), "chkbx_yellow").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxredcontextualnotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_redcontextualnotes_uncheck_xpath"), "chkbx_red").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxpurplecontextualnotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_purplecontextualnotes_uncheck_xpath"), "chkbx_purple").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxgreencontextualnotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_greencontextualnotes_uncheck_xpath"), "chkbx_green").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkbxbluecontextualnotes(){
		try {
			elementFinderByXpath(prop.getProperty("filter_bluecontextualnotes_uncheck_xpath"), "chkbx_blue").click();
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}



	public static void btnpentool(){
		try {
			elementFinderByID(prop.getProperty("pentoolbtn_ID"), "btn_pentool").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnPenColour_Black(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpaletteblack_xpath"), "btnPenColour_Black").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnPenColour_Red(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpalettered_xpath"), "btnPenColour_Red").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnPenColour_Purple(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpalettepurple_xpath"), "btnPenColour_Purple").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnPenColour_Green(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpalettegreen_xpath"), "btnPenColour_Green").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnPenColour_Blue(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpaletteblue_xpath"), "btnPenColour_Blue").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void dragthicknessbar(){
		try {
			Thread.sleep(2000);
			elementFinderByXpath(prop.getProperty("thicknessbarpoint_xpath"), "thicknessbar");
			WebElement slider = elementFinderByXpath(prop.getProperty("thicknessbarpoint_xpath"), "thicknessbar point");
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
			elementFinderByXpath(prop.getProperty("eraserbtn_xpath"), "btn_eraser").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnundo(){
		try {
			elementFinderByXpath(prop.getProperty("undobtn_xpath"), "btn_undo").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnclearall(){
		try {
			elementFinderByXpath(prop.getProperty("clearallbtn_xpath"), "btn_clearall").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnclearallAlertPopup_Yes(){
		try {
			elementFinderByXpath(prop.getProperty("clearallYesbtn_xpath"), "btnclearallAlertPopup_Yes").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnsavePenTool(){
		try {
			elementFinderByXpath(prop.getProperty("pentoolsavebtn_xpath"), "btnsavePenTool").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnstickynotes(){
		try {
			elementFinderByID(prop.getProperty("stickynotes_ID"), "btn_stickynotes").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnDeleteStickyNotes(){
		try {
			elementFinderByXpath(prop.getProperty("deleteSticky_Xpath"), "btnDeleteStickyNotes").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	//If there any sticky notes apply on page then use this method for open sticky notes pop-up
	public static void btnStickyNotesInsidePage(){
		try {
			elementFinderByXpath(prop.getProperty("StickyNote_Xpath"), "btnStickyNotesInsidePage").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnhighlight(){
		try {
			elementFinderByID(prop.getProperty("highlight_ID"), "btn_highlight").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnzoomin(){
		try {
			elementFinderByXpath(prop.getProperty("zoomin_xpath"), "btn_zoomin").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnzoomout(){
		try {
			elementFinderByID(prop.getProperty("zoomout_ID"), "btn_zoomout").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static String getzoomValue(){
		String zoomValue = null;
		try {
			zoomValue = elementFinderByID(prop.getProperty("zoomvalue_id"), "getzoomValue").getText();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return zoomValue;
	}

	public static void btnfittowidth(){
		try {
			elementFinderByXpath(prop.getProperty("fittowidth_xpath"), "fittowidth").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnfittoheight(){
		try {
			elementFinderByXpath(prop.getProperty("fittoheight_xpath"), "fittoheight").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnsinglepage(){
		try {
			elementFinderByXpath(prop.getProperty("singlepagebtn_xpath"), "singlepage").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btndoublepage(){
		try {
			elementFinderByXpath(prop.getProperty("doublepagebtn_xpath"), "doublepage").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnthumbnail(){
		try {
			elementFinderByID(prop.getProperty("thumbnailbtn_ID"), "thumbnail").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnhistoryprevious(){
		try {
			elementFinderByXpath(prop.getProperty("historyprevious_xpath"), "historyprevious").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnhistorynext(){
		try {
			elementFinderByXpath(prop.getProperty("historynext_xpath"), "historynext").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void txtbxgotopage(String pageNum){
		try {
			elementFinderByID(prop.getProperty("gotopagetextbox_id"), "gotopagetextbox").click();
			elementFinderByID(prop.getProperty("gotopagetextbox_id"), "gotopagetextbox").clear();
			elementFinderByID(prop.getProperty("gotopagetextbox_id"), "gotopagetextbox").sendKeys(pageNum);
			elementFinderByID(prop.getProperty("gotopagetextbox_id"), "gotopagetextbox").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static String getpageNum(){
		String pageCount = "0";
		try {
			pageCount = elementFinderByXpath(prop.getProperty("getPageNumber_xpath"), "gotopagetextbox").getAttribute("aria-valuenow");
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
			chaptertitle = elementFinderByID(prop.getProperty("chapterTitle_id"), "Chapter Title").getText();
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
		return chaptertitle;

	}

	public static void btnnextchapter(){
		try {
			elementFinderByXpath(prop.getProperty("nextchapterbtn_xpath"), "nextchapter").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnpreviouschapter(){
		try {
			elementFinderByXpath(prop.getProperty("previouschapterbtn_xpath"), "previouschapter").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnnextpage(){
		try {
			elementFinderByXpath(prop.getProperty("nextpagebtn_xpath"), "nextpage").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnpreviouspage(){
		try {
			elementFinderByXpath(prop.getProperty("previouspagebtn_xpath"), "previouspage").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnfull_DefaultScreen(){
		try {
			elementFinderByID(prop.getProperty("full_Defaultscreenbtn_xpath"), "full_Defaultscreenbtn").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnprofile(){
		try {
			elementFinderByXpath(prop.getProperty("profilebtn_xpath"), "profile").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnsignout(){
		try {
			elementFinderByXpath(prop.getProperty("signoutbtn_xpath"), "signout").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void txtStickyNotes(String notes){
		try {
			elementFinderByID(prop.getProperty("editnote_ID"), "txtStickyNotes").sendKeys(notes);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnSaveStickyNotes(){
		try {
			elementFinderByXpath(prop.getProperty("postnotebtn_xpath"), "btnSaveStickyNotes").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnStickyClr_orange(){
		try {
			elementFinderByXpath(prop.getProperty("clrOrangeSticky_xpath"), "btnStickyClr_orange").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnStickyClr_pink(){
		try {
			elementFinderByXpath(prop.getProperty("clrRedSticky_xpath"), "btnStickyClr_pink").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnStickyClr_purple(){
		try {
			elementFinderByXpath(prop.getProperty("clrPurpleSticky_xpath"), "btnStickyClr_purple").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnStickyClr_green(){
		try {
			elementFinderByXpath(prop.getProperty("clrGreenSticky_xpath"), "btnStickyClr_green").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnStickyClr_blue(){
		try {
			elementFinderByXpath(prop.getProperty("clrBlueSticky_xpath"), "btnStickyClr_blue").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void selectparagraph(){
		try {
			Thread.sleep(1000);
			Driver.driver.switchTo().frame(elementFinderByID(prop.getProperty("highlightfram_ID"), "highlight frame id"));
			Thread.sleep(500);
			elementFinderByID(prop.getProperty("highlightword_id"), "highlight word id");
			WebElement from = elementFinderByID(prop.getProperty("highlightword_id"), "highlight word id");
			JavascriptExecutor js = (JavascriptExecutor)Driver.driver;
			js.executeScript("arguments[0].click();", from);
			Thread.sleep(500);
			System.out.println("mouse movement");
			dragAndDrop(from, 0, 45);
			Thread.sleep(1000);
			Driver.driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnhighlightdelete(){
		try {
			elementFinderByID(prop.getProperty("highlightdelete_id"), "highlight delete btn").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void selectparagraph_delete(){
		try {
			Thread.sleep(1000);
			Driver.driver.switchTo().frame(elementFinderByID(prop.getProperty("highlightfram_ID"), "highlight frame id"));
			Thread.sleep(500);
			elementFinderByID(prop.getProperty("highlightword_id"), "highlight word id");
			WebElement from = elementFinderByID(prop.getProperty("highlightword_id"), "highlight word id");
			JavascriptExecutor js = (JavascriptExecutor)Driver.driver;
			js.executeScript("arguments[0].click();", from);
			Thread.sleep(500);
			System.out.println("mouse movement");
			Thread.sleep(1000);
			Driver.driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void bltyellow(){
		try {
			elementFinderByID(prop.getProperty("highlightyellow_id"), "yellow highlight color").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void bltred(){
		try {
			elementFinderByID(prop.getProperty("highlightred_id"), "red highlight color").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void bltpurple(){
		try {
			elementFinderByID(prop.getProperty("highlightpurple_id"), "purple highlight color").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void bltgreen(){
		try {
			elementFinderByID(prop.getProperty("highlightgreen_id"), "green highlight color").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void bltblue(){
		try {
			elementFinderByID(prop.getProperty("highlightblue_id"), "blue highlight color").click();
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
			Thread.sleep(8000);
			Driver.driver.switchTo().frame(elementFinderByID(prop.getProperty("bookmarkfram_ID"), "bookmark frame id"));
			Thread.sleep(500);
			System.out.println("Frame Switch");
			Thread.sleep(9000);
			Thread.sleep(9000);
			
	//	elementFinderByID(prop.getProperty("bookmarkThisPage_ID"), "bookmark this page").sendKeys(Keys.ENTER);
			//elementFinderByID(prop.getProperty("bookmarkThisPage_ID"), "bookmark this page").click();
			
			WebElement element0 = elementFinderByID(prop.getProperty("bookmarkThisPage_ID"), "bookmark this page"); 
			JavascriptExecutor executor = (JavascriptExecutor)Driver.driver;
			executor.executeScript("arguments[0].click();", element0);
			System.out.println("Clicked on bookmark");
			Driver.driver.switchTo().defaultContent();
			System.out.println("Default content Switch");
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtbookmark(){
		try {
			Thread.sleep(8000);
			elementFinderByXpath(prop.getProperty("bookmarkTitle_xpath"), "bookmark title").clear();
			elementFinderByXpath(prop.getProperty("bookmarkTitle_xpath"), "bookmark title").sendKeys("Bookmark title");
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnaddbookmark(){
		try {
		
			elementFinderByXpath(prop.getProperty("addBookmark_xpath"), "add bookmark").click();	
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btntocbookmark(){
		try {
		
			elementFinderByXpath(prop.getProperty("bookmarks_xpath"), "bookmark").click();	
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static int getbookmarkCounts(){
		int size = 0;
		try {
			size= Driver.driver.findElements(By.xpath(prop.getProperty("bookmark_list_lstview_xpath"))).size();
			System.out.println("List Count:"+ size);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return size;
	}
	
	public static void btnbookmarkpageno(){
		try {
			String pageno = null;
			pageno =elementFinderByXpath(prop.getProperty("bookmarkpageno_xpath"), "bookmark page number").getText();	
			System.out.println("bookmark page number: "+ pageno );
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void bookmarklist(int i){
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("bookmark_list_lstview_xpath"), "bookmark_lstview_xpath");
			element.get(i).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
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
		
			elementFinderByXpath(prop.getProperty("deleteBookmark_xpath"), "delete bookmark").click();	
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

}
