package com.hurix.reader.bookPlayer;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class BookplayerStepModule extends UIElements {
	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/reader/bookplayer.properties");

	public static void btnbacktobookshelf(){
		try {
			elementFinderByXpath(prop.getProperty("backtobookshelf_xpath"), "btn_backtobookshelf").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btntableofcontentandresources(){
		try {
			//changes
			elementFinderByID(prop.getProperty("tableofcontentandresources_ID"), "btn_tableofcontentandresources").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnresources(){
		try {
			elementFinderByID(prop.getProperty("resources_ID"), "btn_resources").click();
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
	
	public static void btnfilter(){
		try {
			elementFinderByXpath(prop.getProperty("filter_xpath"), "btn_filter").click();
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
			Thread.sleep(9000);
			elementFinderByID(prop.getProperty("searchbtn_ID"), "btn_search").click();
			elementFinderByID(prop.getProperty("searchboxtxt_ID"), "txt_searchbox").sendKeys("the");
			Thread.sleep(1000);
			elementFinderByID(prop.getProperty("searchboxtxt_ID"), "txt_searchbox").sendKeys(Keys.ENTER);
			Thread.sleep(9000);
			WebElement element0 = elementFinderByID(prop.getProperty("searchresult_xpath"), "drpdwn_searchresult");
			JavascriptExecutor executor = (JavascriptExecutor)Driver.driver;
			executor.executeScript("arguments[0].click();", element0);
			Log.info("Click");
			
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtsearchbox(){
		try {
			
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
	
	public static void btnpencolourpaletteblack(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpaletteblack_xpath"), "btn_pencolourpaletteblack").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnpencolourpalettered(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpalettered_xpath"), "btn_pencolourpalettered").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnpencolourpalettepurple(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpalettepurple_xpath"), "btn_pencolourpalettepurple").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnpencolourpalettegreen(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpalettegreen_xpath"), "btn_pencolourpalettegreen").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnpencolourpaletteblue(){
		try {
			elementFinderByXpath(prop.getProperty("pencolourpaletteblue_xpath"), "btn_pencolourpaletteblue").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void dragthicknessbar(){
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("thicknessbar_xpath"), "thicknessbar");
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btneraser(){
		try {
			elementFinderByXpath(prop.getProperty("eraser_xpath"), "btn_eraser").click();
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
	
	public static void btnsave(){
		try {
			elementFinderByXpath(prop.getProperty("savebtn_xpath"), "btn_save").click();
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
	
	public static void btnhighlight(){
		try {
			elementFinderByID(prop.getProperty("highlight_ID"), "btn_highlight").click();
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
			actionclass(from, 0, 45);
		    Thread.sleep(1000);
		    Driver.driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnzoomin(){
		try {
			Thread.sleep(9000);
			Thread.sleep(9000);
			Thread.sleep(9000);
			Thread.sleep(9000);
			elementFinderByXpath(prop.getProperty("zoomin_xpath"), "btn_zoomin").click();
			Thread.sleep(2000);
			elementFinderByXpath(prop.getProperty("zoomsliderzoomin_xpath"), "Zoom sliderin xpath");
			WebElement slider = elementFinderByXpath(prop.getProperty("zoomsliderzoomin_xpath"), "Zoom sliderin xpath");
			Actions action =  new Actions(Driver.driver);
			action.clickAndHold(slider);
			action.moveByOffset(240, 0).build().perform();
			action.click();
			System.out.println("action completed");
			Thread.sleep(1000);
			//elementFinderByXpath(prop.getProperty("zoomsliderzoomin_xpath"), "Zoom slider xpath").click();
			System.out.println("slider");
			Thread.sleep(1000);
			//elementFinderByXpath(prop.getProperty("zoomin_xpath"), "btn_zoomin").click();
			btnhighlight();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnzoomout(){
		try {
			Thread.sleep(9000);
			Thread.sleep(9000);
			Thread.sleep(9000);
			elementFinderByID(prop.getProperty("zoomout_ID"), "btn_zoomout").click();
			Thread.sleep(2000);
			elementFinderByXpath(prop.getProperty("zoomsliderzoomout_xpath"), "Zoom slider xpath");
			WebElement slider = elementFinderByXpath(prop.getProperty("zoomsliderzoomout_xpath"), "Zoom slider xpath");
			Actions action =  new Actions(Driver.driver);
			action.clickAndHold(slider);
			action.moveByOffset(0, 0).build().perform();
			action.click();
			Thread.sleep(1000);
			//elementFinderByXpath(prop.getProperty("zoomsliderzoomout_xpath"), "Zoom slider xpath").click();
			System.out.println("slider");
			Thread.sleep(1000);
			//elementFinderByID(prop.getProperty("zoomout_ID"), "btn_zoomout").click();
			btnhighlight();
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	/*public static void zoomslider(){
		try {
			elementFinderByXpath(prop.getProperty("zoomslider_xpath"), "zoomslider").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}*/
	
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
	
	public static String txtgotopage(){
		String pageCount = "0";
		try {
			pageCount = elementFinderByID(prop.getProperty("gotopagetextbox_id"), "gotopagetextbox").getText();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
		return null;
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
	
	public static void btnfullscreen(){
		try {
			elementFinderByXpath(prop.getProperty("fullscreenbtn_xpath"), "fullscreen").click();
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
	
	public static void editnote(){
		try {
			elementFinderByID(prop.getProperty("editnote_ID"), "editnote").sendKeys("add note");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnpostnote(){
		try {
			elementFinderByXpath(prop.getProperty("postnotebtn_xpath"), "postnote").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
}
