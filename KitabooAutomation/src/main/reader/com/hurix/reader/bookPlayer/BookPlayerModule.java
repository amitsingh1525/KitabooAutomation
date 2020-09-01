package com.hurix.reader.bookPlayer;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
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
		Properties prop = getProperty(System.getProperty("user.dir")+"/config/reader/bookplayer.properties");
		btnsearch("encyclopedia");
		threadHold_2Sec();
		List<WebElement> element= elementsFinderByXpaths(prop.getProperty("searchresult_lstview_xpath"), "searchresult_lstview_xpath");
		int size=  element.size();
		System.out.println("Size of search element is :"+ element.size());
		String msg = null;
		if(size>=0){
			searchResult(0);
			threadHold_5Sec();
		}else{
			msg = getinvalidsearchmsg();
		}
		return msg;

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

}
