package com.hurix.automation.utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;

public class SelectWord {
	private static WebElement element = null;
	
	 public static WebElement selectword() throws AWTException, InterruptedException{
	    	Thread.sleep(1000);
	    	Robot robot = new Robot();
	    	robot.keyPress(KeyEvent.VK_CONTROL);
	    	robot.keyPress(KeyEvent.VK_A);
	    	robot.keyRelease(KeyEvent.VK_CONTROL);
			return element;
		    }

}
