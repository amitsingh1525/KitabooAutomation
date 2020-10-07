package com.hurix.reader.bookPlayer;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

public class BookPlayerModule extends BookplayerStepModule
{

	public static String backtoBookshelf(){
		btnbacktobookshelf();
		threadHold_2Sec();
		return Driver.driver.getCurrentUrl();
	}

	public static void bookPlayerLogout(){
		btnBookPlayerProfileIcon();
		btnBookPlayerProfileIcon_Signout();
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
		selectparagraph(0, 45);
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
		//btnhighlight();
		selectparagraph_delete();
		btnhighlightdelete();
		//btnhighlight();
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

	public static void clearAllpentool(){
		btnclearall();
		btnclearallAlertPopup_Yes();
		btnsavePenTool();
	}

	public static void stickyNotes(String color, String pageNum, int x, int y, String msg){
		goToPage(pageNum);
		threadHold_5Sec();
		threadHold_2Sec();
		btnstickynotes();
		Driver.driver.switchTo().frame("epub_"+pageNum);
		WebElement element = Driver.driver.findElement(By.id("p5-textid50001"));
		dragAndDrop(element, x, y);
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

		txtStickyNotes(msg);
		threadHold_2Sec();
		btnSaveStickyNotes();
		threadHold_2Sec();
		Driver.driver.switchTo().parentFrame();
		threadHold_2Sec();
	}

	public static void openStickyNotesWithCordinates(String color, String pageNum, int x, int y){
		goToPage(pageNum);
		threadHold_5Sec();
		threadHold_2Sec();
		Driver.driver.switchTo().frame("epub_"+pageNum);
		WebElement element = Driver.driver.findElement(By.id("p5-textid50001"));
		Actions act=new Actions(Driver.driver);					
		act.dragAndDropBy(element, x, y).click().build().perform();
		Driver.driver.switchTo().parentFrame();
	}

	public static void deleteStickyNotes(String pageNum, int x, int y, int i){
		goToPage(pageNum);
		threadHold_5Sec();
		threadHold_2Sec();
		btnStickyNotesInsidePage(i, pageNum);
		btnDeleteStickyNotes();
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

	public static void zoomIn(int zoomInLevel){
		btnzoomin();
		elementFinderByXpath(prop.getProperty("zoomsliderzoomin_xpath"), "Waiting for zoom slider..");
		WebElement slider = elementFinderByXpath(prop.getProperty("zoomsliderzoomin_xpath"), "Click on zoom slider.");
		moveToGivenPoint(slider, zoomInLevel, 0);
		btnhighlight();
	}

	public static void zoomOut(int zoomoutLevel){
		btnzoomout();
		elementFinderByXpath(prop.getProperty("zoomsliderzoomout_xpath"), "Waiting for zoom slider..");
		WebElement slider = elementFinderByXpath(prop.getProperty("zoomsliderzoomout_xpath"), "Click on zoom slider.");
		moveToGivenPoint(slider, zoomoutLevel, 0);
		btnhighlight();
	}

	public static String searchBookText(String searchtxt, int elementNum){
		btnsearch();
		txtSearch(searchtxt);
		int size= elementsFinderByXpaths(prop.getProperty("searchresult_lstview_xpath"), "getting search result count..").size();
		Log.info("Number of search found:"+ size);
		String msg = null;
		if(size>0){

			msg = searchResult(elementNum);
			threadHold_5Sec();
		}else{
			msg = getinvalidsearchmsg();
		}
		return msg;
	}
	
	public static void searchAWord(){
		btnthumbnail();
		txtbxgotopage("5");
		btnhighlight();
		selectparagraph(0, 0);
		btnhighlightsearch();
		int size= elementsFinderByXpaths(prop.getProperty("searchresult_lstview_xpath"), "Search result count").size();
		Log.info("Number of search found:"+ size);
		String msg = null;
		if(size>0){
			msg = searchResult(0);
			threadHold_5Sec();
		}else{
			msg = getinvalidsearchmsg();
		}
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
		elementFinderByXpath(prop.getProperty("filter_yellowhighlight_uncheck_xpath"), "Close filter plate").sendKeys(Keys.ESCAPE);
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
		elementFinderByXpath(prop.getProperty("filter_bluenotes_uncheck_xpath"), "Close filter plate.").sendKeys(Keys.ESCAPE);
		int count = getNotesCounts();
		btnmyData();
		return count;
	}

	public static void myDataSharedNotes(int i, boolean accept){
		btnmyData();
		String iconCount = getSharedNotesCountFromMyDataIcon();
		btnMyDataNotificationIcon();
		int sharedList = getSharedNotesCountFromlist();
		if(accept) {
			btnAcceptStickyNotes(i);
		}else {
			btnDeclinedStickyNotes(i);
		}
		if(iconCount.equals(Integer.toString(sharedList))) {
			Log.pass("iconCount: "+iconCount+" sharedList: "+sharedList);
		}else {
			Log.error("iconCount: "+iconCount+" sharedList: "+sharedList);
		}
		btnmyData();
	}

	public static void myDataCommentOnSharedNotes(int i, String comment){
		btnmyData();
		btnmyDatanotes();
		int beforeCommentCount = getCommentOnNotesCount(i);
		btnCommentOnNotes(i);
		txtCommentOnNotes(comment);
		btnSendCommentOnNotes();
		btnBackArrow();
		int afterCommentCount = getCommentOnNotesCount(i);
		if(beforeCommentCount < afterCommentCount) {
			Log.pass("Comment Counts are matched. after Comment Count is: "+beforeCommentCount
					+" Before Comment Count is: "+afterCommentCount);
		}else {
			Log.fail("Expected increment by n number of current Comment: "+beforeCommentCount+" But Found: "+afterCommentCount);
		}
		btnmyData();
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
		elementFinderByXpath(prop.getProperty("filter_bluecontextualnotes_uncheck_xpath"), "Close filter plate.").sendKeys(Keys.ESCAPE);
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


	public static String getCurrentPageNum(){
		threadHold_5Sec();
		btnthumbnail();
		String pageNum = getpageNum();
		System.out.println("Page Number: "+pageNum);
		btnthumbnail();
		return pageNum;

	}

	public static void addbookmark(String bookMarkPageNo, String bookmark){
		goToPage(bookMarkPageNo);
		btntableofcontentandresources();
		btntocbookmark();
		int afterBookmark = getbookmarkCounts();
		btntableofcontentandresources();
		btnbookmark();
		txtbookmark(bookmark);
		btnaddbookmark();
		threadHold_5Sec();
		btntableofcontentandresources();
		btntocbookmark();
		btntableofcontentandresources();
		threadHold_2Sec();
		btntableofcontentandresources();
		int beforeBookmark = getbookmarkCounts();
		if(afterBookmark < beforeBookmark) {
			Log.pass("After add bookmark count is: "+afterBookmark+" Before added Bookmark count is: "+beforeBookmark);
		}else {
			Log.fail("Expected increment of n number of current bookmark, current count: "+afterBookmark+" But found: "+beforeBookmark);
		}

		int pageNum = bookmarklist(0);
		int redirectPageNum = Integer.parseInt(getCurrentPageNum());
		if(pageNum == redirectPageNum) {
			Log.pass("Bookmark added on pageNum: "+pageNum+" redirect pageNum: "+redirectPageNum+" Both are same.");
		}else {
			Log.fail("Bookmark added on pageNum: "+pageNum+" redirect pageNum: "+redirectPageNum+" Both are NOT same.");
		}
	}

	public static void deletebookmark(int i){
		btntableofcontentandresources();
		btntocbookmark();
		int afterBookmarkDelete = getbookmarkCounts();
		int pageNum = bookmarklist(i);
		btnbookmark();
		btndeletebookmark();
		threadHold_5Sec();
		btntableofcontentandresources();
		btntocbookmark();
		int beforeBookmarkDelete = getbookmarkCounts();
		btntableofcontentandresources();
		if(afterBookmarkDelete > beforeBookmarkDelete) {
			Log.pass("After delete bookmark count is: "+afterBookmarkDelete+" Before deleted Bookmark count is: "+beforeBookmarkDelete);
		}else {
			Log.fail("Expected decrement of n number of current bookmark, current count: "+afterBookmarkDelete+" But found: "+beforeBookmarkDelete);
		}
		int currentpage = Integer.parseInt(getCurrentPageNum());
		if(pageNum == currentpage) {
			Log.pass("Bookmark added on pageNum: "+pageNum+" redirect pageNum: "+currentpage+" Both are same.");
		}else {
			Log.fail("Bookmark added on pageNum: "+pageNum+" redirect pageNum: "+currentpage+" Both are NOT same.");
		}
	}

	public static void contentResources(int i){
		btntableofcontentandresources();
		btnresources();
		btnResourceDrpdwn();
		btnResourcelst(i);
	}

	public static void content(){
		btntableofcontentandresources();
		btncontents();
		//We need to change inside it's a static method always click on first node
		btncontentsdrpdwn();
		btncontentsChildlist();
	}
	
	
	public static void allmarkup(int pageNum,String title) {
		threadHold_2Sec();
		markup (pageNum, title);	
		windowhandle();
		threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("https://read.kitaboo.com/reader/V5/reader/index.html")){
			Log.pass("After click on Test go to book redirect to this page"+Driver.driver.getCurrentUrl());
		}else{
			Log.fail("Expected URL contain Test go to book but found "+Driver.driver.getCurrentUrl());
		}
		Driver.driver.close();
		windowhandle();
		
	}

	

}
