package com.hurix.reader.bookPlayer;

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
	
	
	public static void highlight(){
		
		
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
	
	public static String getPageNum(){
		btnthumbnail();
		
		return null;
	}
}
