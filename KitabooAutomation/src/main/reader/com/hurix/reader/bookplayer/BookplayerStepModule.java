package com.hurix.reader.bookplayer;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.hurix.automation.utility.UIElements;

public class BookplayerStepModule extends UIElements {
	private static Properties prop = getProperty("C:/Users/amit.singh/git/KitabooAutomation/KitabooAutomation/config/reader/bookplayer.properties");

	public static void btnbacktobookshelf(){
		try {
			elementFinderByXpath(prop.getProperty("backtobookshelf_xpath"), "btn_backtobookshelf").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btntableofcontentandresources(){
		try {
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
	
	public static void lstcontent_list(){
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("content_list_lstview_xpath"), "lst_content_list");
			
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
			elementFinderByID(prop.getProperty("searchbtn_ID"), "btn_search").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtsearchbox(){
		try {
			elementFinderByID(prop.getProperty("searchboxtxt_ID"), "txt_searchbox").click();
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
	
	public static void btnzoom(){
		try {
			elementFinderByID(prop.getProperty("zoom_ID"), "btn_zoom").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void zoomslider(){
		try {
			elementFinderByXpath(prop.getProperty("zoomslider_xpath"), "zoomslider").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
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
	
	public static void txtbxgotopage(){
		try {
			elementFinderByXpath(prop.getProperty("gotopagetextbox_id"), "gotopagetextbox").click();
			elementFinderByXpath(prop.getProperty("gotopagetextbox_id"), "gotopagetextbox").clear();
			elementFinderByXpath(prop.getProperty("gotopagetextbox_id"), "gotopagetextbox").sendKeys("2");
			elementFinderByXpath(prop.getProperty("gotopagetextbox_id"), "gotopagetextbox").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void noofpages(){
		try {
			List<WebElement> element= elementsFinderByXpaths(prop.getProperty("noofpages_lstview_xpath"), "noofpages");
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
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
