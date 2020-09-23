package com.hurix.automation.utility;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class Uploadpath {
	
	public static void uploadpath(String upload){
		try {
			Thread.sleep(4000);
			File imgfileobject= new File("");
			System.out.println(upload);
			String imgFilePath = upload;
			StringSelection ss = new StringSelection(imgfileobject.getAbsolutePath()+imgFilePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Log.info("file uploaded");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

}
