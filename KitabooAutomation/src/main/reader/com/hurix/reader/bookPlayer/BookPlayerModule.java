package com.hurix.reader.bookPlayer;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.hurix.automation.utility.Driver;

public class BookPlayerModule extends BookplayerStepModule
{

	public static String backtoBookshelf(){
		btnbacktobookshelf();
		threadHold_2Sec();
		return Driver.driver.getCurrentUrl();
	}

	public static void tableofContent(int chapterno){
		btntableofcontentandresources();
		btncontents();
		lstcontent_list(chapterno);	
		getchapterTitle();           // get chapter title validation
		btnresources();
	}


	public static void highlight(String color){
		btnthumbnail();
		txtbxgotopage("5");
		btnhighlight();
		selectparagraph();
		if(color.equalsIgnoreCase("yellow")) {
			bltyellow();
		}
		if(color.equalsIgnoreCase("red")) {
			bltred();
		}
		if(color.equalsIgnoreCase("purple")) {
			bltpurple();
		}
		if(color.equalsIgnoreCase("green")) {
			bltgreen();
		}
		if(color.equalsIgnoreCase("blue")) {
			bltblue();
		}

	}

	public static void deleteHighlight(){
		btnhighlight();
		selectparagraph_delete();
		btnhighlightdelete();
		btnhighlight();
	}

	public static void pentool(String color, String pageNum, int x, int y){

		btnthumbnail();
		txtbxgotopage("5");
		btnpentool();
		dragthicknessbar();
		//btnpentool();
		if(color.equalsIgnoreCase("black")) {
			btnPenColour_Black();
		}
		if(color.equalsIgnoreCase("red")) {
			btnPenColour_Red();
		}
		if(color.equalsIgnoreCase("purple")) {
			btnPenColour_Purple();
		}
		if(color.equalsIgnoreCase("green")) {
			btnPenColour_Green();
		}
		if(color.equalsIgnoreCase("blue")) {
			btnPenColour_Blue();
		}

		Driver.driver.switchTo().frame("epub_"+pageNum);
		drawLine(By.id("p5-textid50001"), x, y);
		Driver.driver.switchTo().parentFrame();
		btnpentool();
	}

	public static void erasepentool(String pageNum, int x, int y){
		btnpentool();
		btneraser();
		Driver.driver.switchTo().frame("epub_"+pageNum);
		drawLine(By.id("p5-textid50001"), x, y);
		Driver.driver.switchTo().parentFrame();
	}

	public static void stickyNotes(String color, String pageNum, int x, int y){
		btnstickynotes();
		Driver.driver.switchTo().frame("epub_"+pageNum);
		WebElement element = Driver.driver.findElement(By.id("p5-textid50001"));
		actionclass(element, x, y);
		Driver.driver.switchTo().parentFrame();
		if(color.equalsIgnoreCase("Orange")) {
			btnStickyClr_orange();
		}
		if(color.equalsIgnoreCase("pink")) {
			btnStickyClr_pink();
		}
		if(color.equalsIgnoreCase("purple")) {
			btnStickyClr_purple();
		}
		if(color.equalsIgnoreCase("green")) {
			btnStickyClr_green();
		}
		if(color.equalsIgnoreCase("blue")) {
			btnStickyClr_blue();
		}

		txtStickyNotes("Hello Brother!");
		btnSaveStickyNotes();
		Driver.driver.switchTo().parentFrame();
	}

	public static void goToPage(String pageNum){
		btnthumbnail();
		txtbxgotopage(pageNum);
	}

	public static void historyPrevious(){
		btnthumbnail();
		btnhistoryprevious();
	}

	public static void historyNext(){
		btnthumbnail();
		btnhistorynext();
	}

	public static void zoomIn(){
		btnzoomin();
	}

	public static void zoomOut(){
		btnzoomout();
	}

	public static String searchBookText(){
		btnsearch("the");
		threadHold_2Sec();
		int size= Driver.driver.findElements(By.xpath(prop.getProperty("searchresult_lstview_xpath"))).size();
		System.out.println("Size of search element is :"+ size);
		String msg = null;
		if(size>0){
			searchResult(0);
			threadHold_5Sec();
		}else{
			msg = getinvalidsearchmsg();
		}
		return msg;

	}

	public static int myDataHighlightCount(String clrName){
		btnmyData();
		btnmyDatahighlight();
		btnFilter();
		String[] filtercolourshighlight = clrName.split(",");
		btnClearAllFilter();

		for(String filtercolourhighlight: filtercolourshighlight){
			if(filtercolourhighlight.equalsIgnoreCase("All")) {
				btnClearAllFilter();
			}
			if(filtercolourhighlight.equalsIgnoreCase("yellow")) {
				chkbxyellowhighlight();
			}
			if(filtercolourhighlight.equalsIgnoreCase("red")) {
				chkbxredhighlight();
			}
			if(filtercolourhighlight.equalsIgnoreCase("purple")) {
				chkbxpurplehighlight();
			}
			if(filtercolourhighlight.equalsIgnoreCase("green")) {
				chkbxgreenhighlight();
			}
			if(filtercolourhighlight.equalsIgnoreCase("blue")) {
				chkbxbluehighlight();
			}
		}
		//justForRemoveFilterPlate
		elementFinderByXpath(prop.getProperty("filter_yellowhighlight_uncheck_xpath"), "chkbx_yellow").sendKeys(Keys.ESCAPE);
		int count = getHighLightCounts();
		btnmyData();
		return count;
	}

	public static int myDatanormalNotesCount(String clrName){
		btnmyData();
		btnmyDatanotes();
		btnFilter();
		String[] filtercolourshighlight = clrName.split(",");
		btnClearAllNoteschkbx();
		btnmyDatanormalnotes();
		for(String filtercolour: filtercolourshighlight){
			if(filtercolour.equalsIgnoreCase("All")) {
				chkbxNotesForAll();
			}
			if(filtercolour.equalsIgnoreCase("yellow")) {
				chkbxyellownotes();
			}
			if(filtercolour.equalsIgnoreCase("red")) {
				chkbxrednotes();
			}
			if(filtercolour.equalsIgnoreCase("purple")) {
				chkbxpurplenotes();
			}
			if(filtercolour.equalsIgnoreCase("green")) {
				chkbxgreennotes();
			}
			if(filtercolour.equalsIgnoreCase("blue")) {
				chkbxbluenotes();
			}
		}

		//justForRemoveFilterPlate
		elementFinderByXpath(prop.getProperty("filter_bluenotes_uncheck_xpath"), "chkbx_blue").sendKeys(Keys.ESCAPE);
		int count = getNotesCounts();
		btnmyData();
		return count;
	}

	public static int myDatacontextualNotesCount(String clrName){
		
		btnmyData();
		btnmyDatanotes();
		btnFilter();
		String[] filtercoloursContex = clrName.split(",");
		btnClearAllNoteschkbx();
		btnmyDatacontextualnotes();
		for(String filtercolour: filtercoloursContex){
			if(filtercolour.equalsIgnoreCase("All")) {
				chkbxContextualNotesForAll();
			}
			if(filtercolour.equalsIgnoreCase("yellow")) {
				chkbxyellowcontextualnotes();
			}
			if(filtercolour.equalsIgnoreCase("red")) {
				chkbxredcontextualnotes();
			}
			if(filtercolour.equalsIgnoreCase("purple")) {
				chkbxpurplecontextualnotes();
			}
			if(filtercolour.equalsIgnoreCase("green")) {
				chkbxgreencontextualnotes();
			}
			if(filtercolour.equalsIgnoreCase("blue")) {
				chkbxbluecontextualnotes();
			}
		}

		//justForRemoveFilterPlate
		elementFinderByXpath(prop.getProperty("filter_bluecontextualnotes_uncheck_xpath"), "chkbx_blue").sendKeys(Keys.ESCAPE);
		int count = getNotesCounts();
		btnmyData();
		return count;
	
	}


	public static void tableOfContentAndResources(){
		btntableofcontentandresources();
	}

	public static void fitToWidth(){
		btnfittowidth();
	}

	public static void fitToHeight(){
		btnfittoheight();
	}


	public static String getPageNum(){
		threadHold_5Sec();
		btnthumbnail();
		System.out.println("Page Number: "+getpageNum());
		return getpageNum();

	}
	
	public static void addbookmark(){
		//txtbxgotopage("10");
		btnbookmark();
		txtbookmark();
		btnaddbookmark();
		btntableofcontentandresources();
		btntocbookmark();
		getbookmarkCounts();
		btnbookmarkpageno();
		bookmarklist(0);
		getPageNum();
	}
	
	public static void deletebookmark(){
		btnbookmark();
		btndeletebookmark();
		btntableofcontentandresources();
		btntocbookmark();
		getbookmarkCounts();
	}

}
