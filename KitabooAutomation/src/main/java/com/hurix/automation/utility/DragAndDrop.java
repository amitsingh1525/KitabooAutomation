package com.hurix.automation.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragAndDrop {
	
	public static void dragAndDrop(By fromElement, By toElement){
		try {
			WebElement From = Driver.driver.findElement(fromElement);
			WebElement To = Driver.driver.findElement(toElement);	
			Actions act=new Actions(Driver.driver);		
		    act.dragAndDrop(From, To).build().perform();
		} catch (Exception e) {
			System.out.println("Element NOT present."+e.getMessage());
		}
	}

}
