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
	
	public static void pentool(){
		btnthumbnail();
		txtbxgotopage("5");
		btnpentool();
		dragthicknessbar();
		btnpencolourpalettered();
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
	
	public static void myDataHighlight(String filtercolour){
		btnmyData();
		
		if(filtercolour.equalsIgnoreCase("yellow")) {
			chkbxyellow();
		}
		if(filtercolour.equalsIgnoreCase("red")) {
			chkbxred();
		}
		if(filtercolour.equalsIgnoreCase("purple")) {
			chkbxpurple();
		}
		if(filtercolour.equalsIgnoreCase("green")) {
			chkbxgreen();
		}
		if(filtercolour.equalsIgnoreCase("blue")) {
			chkbxblue();
		}
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
