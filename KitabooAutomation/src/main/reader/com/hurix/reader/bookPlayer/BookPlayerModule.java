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
		
		
		btnhighlight();
		selectparagraph();
		
		
	}
	
	

}
