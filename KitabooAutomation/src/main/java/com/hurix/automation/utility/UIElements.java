package com.hurix.automation.utility;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIElements {


	public static long waitTiming = 30;
	static WebDriverWait wait = new WebDriverWait(Driver.driver, waitTiming);

	public static WebElement elementFinderByID(String elmentID, String elementName) {
		try {
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elmentID)));
			Log.info("Element("+elementName+") is present on the DOM.");
			return Driver.driver.findElement(By.id(elmentID));
		} catch (InterruptedException e) {
			return null;
		}catch (ElementNotVisibleException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not visible, and so is not able to be interacted with. ");
			System.out.println("Element("+elementName+") is present on the DOM, it is not visible, and so is not able to be interacted with. "+e.getMessage());
			return null;
		}catch (ElementNotInteractableException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not in a state that can be interacted.");
			System.out.println("Element("+elementName+") is present on the DOM, it is not in a state that can be interacted. "+e.getMessage());
			return null;
		}catch (ElementNotSelectableException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not selectable, and so is not able to be interacted with. ");
			System.out.println("Element("+elementName+") is present on the DOM, it is not selectable, and so is not able to be interacted with. "+e.getMessage());
			return null;
		}
		catch (TimeoutException e) {
			Log.error("Command does not complete in enough time. Element: "+elementName);
			System.out.println("Command does not complete in enough time. "+e.getMessage());
			return null;
		}
		catch (NoSuchElementException e) {
			Log.error("Element("+elementName+") not found in the page.");
			System.out.println("Element("+elementName+") not found in the page. "+e.getMessage());
			return null;
		}
	}

	public static WebElement elementFinderByXpath(String elmentXpath, String elementName) {
		try {
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elmentXpath)));
			Log.info("Element("+elementName+") is present on the DOM.");
			return Driver.driver.findElement(By.xpath(elmentXpath));
		} catch (InterruptedException e) {
			return null;
		}catch (ElementNotVisibleException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not visible, and so is not able to be interacted with. ");
			System.out.println("Element("+elementName+") is present on the DOM, it is not visible, and so is not able to be interacted with. "+e.getMessage());
			return null;
		}catch (ElementNotInteractableException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not in a state that can be interacted.");
			System.out.println("Element("+elementName+") is present on the DOM, it is not in a state that can be interacted. "+e.getMessage());
			return null;
		}catch (ElementNotSelectableException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not selectable, and so is not able to be interacted with. ");
			System.out.println("Element("+elementName+") is present on the DOM, it is not selectable, and so is not able to be interacted with. "+e.getMessage());
			return null;
		}
		catch (TimeoutException e) {
			Log.error("Command does not complete in enough time. Element: "+elementName);
			System.out.println("Command does not complete in enough time. "+e.getMessage());
			return null;
		}
		catch (NoSuchElementException e) {
			Log.error("Element("+elementName+") not found in the page.");
			System.out.println("Element("+elementName+") not found in the page. "+e.getMessage());
			return null;
		}
	}

	public static WebElement elementFinderByLinkText(String elmentLinkText, String elementName) {
		try {
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(elmentLinkText)));
			Log.info("Element("+elementName+") is present on the DOM.");
			return Driver.driver.findElement(By.linkText(elmentLinkText));
		} catch (InterruptedException e) {
			return null;
		}catch (ElementNotVisibleException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not visible, and so is not able to be interacted with. ");
			System.out.println("Element("+elementName+") is present on the DOM, it is not visible, and so is not able to be interacted with. "+e.getMessage());
			return null;
		}catch (ElementNotInteractableException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not in a state that can be interacted.");
			System.out.println("Element("+elementName+") is present on the DOM, it is not in a state that can be interacted. "+e.getMessage());
			return null;
		}catch (ElementNotSelectableException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not selectable, and so is not able to be interacted with. ");
			System.out.println("Element("+elementName+") is present on the DOM, it is not selectable, and so is not able to be interacted with. "+e.getMessage());
			return null;
		}
		catch (TimeoutException e) {
			Log.error("Command does not complete in enough time. Element: "+elementName);
			System.out.println("Command does not complete in enough time. "+e.getMessage());
			return null;
		}
		catch (NoSuchElementException e) {
			Log.error("Element("+elementName+") not found in the page.");
			System.out.println("Element("+elementName+") not found in the page. "+e.getMessage());
			return null;
		}

	}

	public static List<WebElement> elementsFinderByIDs(String elmentID, String elementName) {
		try {
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elmentID)));
			Log.info("Element("+elementName+") is present on the DOM.");
			return Driver.driver.findElements(By.id(elmentID));
		} catch (InterruptedException e) {
			return null;
		}catch (ElementNotVisibleException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not visible, and so is not able to be interacted with. ");
			System.out.println("Element("+elementName+") is present on the DOM, it is not visible, and so is not able to be interacted with. "+e.getMessage());
			return null;
		}catch (ElementNotInteractableException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not in a state that can be interacted.");
			System.out.println("Element("+elementName+") is present on the DOM, it is not in a state that can be interacted. "+e.getMessage());
			return null;
		}catch (ElementNotSelectableException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not selectable, and so is not able to be interacted with. ");
			System.out.println("Element("+elementName+") is present on the DOM, it is not selectable, and so is not able to be interacted with. "+e.getMessage());
			return null;
		}
		catch (TimeoutException e) {
			Log.error("Command does not complete in enough time. Element: "+elementName);
			System.out.println("Command does not complete in enough time. "+e.getMessage());
			return null;
		}
		catch (NoSuchElementException e) {
			Log.error("Element("+elementName+") not found in the page.");
			System.out.println("Element("+elementName+") not found in the page. "+e.getMessage());
			return null;
		}

	}

	public static List<WebElement> elementsFinderByXpaths(String elmentXpath, String elementName) {
		try {
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elmentXpath)));
			Log.info("Element("+elementName+") is present on the DOM.");
			return Driver.driver.findElements(By.xpath(elmentXpath));
		} catch (InterruptedException e) {
			return null;
		}catch (ElementNotVisibleException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not visible, and so is not able to be interacted with. ");
			System.out.println("Element("+elementName+") is present on the DOM, it is not visible, and so is not able to be interacted with. "+e.getMessage());
			return null;
		}catch (ElementNotInteractableException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not in a state that can be interacted.");
			System.out.println("Element("+elementName+") is present on the DOM, it is not in a state that can be interacted. "+e.getMessage());
			return null;
		}catch (ElementNotSelectableException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not selectable, and so is not able to be interacted with. ");
			System.out.println("Element("+elementName+") is present on the DOM, it is not selectable, and so is not able to be interacted with. "+e.getMessage());
			return null;
		}
		catch (TimeoutException e) {
			Log.error("Command does not complete in enough time. Element: "+elementName);
			System.out.println("Command does not complete in enough time. "+e.getMessage());
			return null;
		}
		catch (NoSuchElementException e) {
			Log.error("Element("+elementName+") not found in the page.");
			System.out.println("Element("+elementName+") not found in the page. "+e.getMessage());
			return null;
		}

	}

	public static List<WebElement> elementsFinderByLinkTexts(String elmentLinkText, String elementName) {
		try {
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(elmentLinkText)));
			Log.info("Element("+elementName+") is present on the DOM.");
			return Driver.driver.findElements(By.linkText(elmentLinkText));
		} catch (InterruptedException e) {
			return null;
		}catch (ElementNotVisibleException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not visible, and so is not able to be interacted with. ");
			System.out.println("Element("+elementName+") is present on the DOM, it is not visible, and so is not able to be interacted with. "+e.getMessage());
			return null;
		}catch (ElementNotInteractableException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not in a state that can be interacted.");
			System.out.println("Element("+elementName+") is present on the DOM, it is not in a state that can be interacted. "+e.getMessage());
			return null;
		}catch (ElementNotSelectableException e) {
			Log.error("Element("+elementName+") is present on the DOM, it is not selectable, and so is not able to be interacted with. ");
			System.out.println("Element("+elementName+") is present on the DOM, it is not selectable, and so is not able to be interacted with. "+e.getMessage());
			return null;
		}
		catch (TimeoutException e) {
			Log.error("Command does not complete in enough time. Element: "+elementName);
			System.out.println("Command does not complete in enough time. "+e.getMessage());
			return null;
		}
		catch (NoSuchElementException e) {
			Log.error("Element("+elementName+") not found in the page.");
			System.out.println("Element("+elementName+") not found in the page. "+e.getMessage());
			return null;
		}
	}

	public static Properties getProperty(String path)
	{
		Properties prop = null;
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Faild to load properties class! "+e.getMessage());
		}
		return prop;
	}

	public static void threadHold(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void selectDropdown(By by, String visibleText, String elementName){
		try {
			Select drpLocator = new Select(Driver.driver.findElement(by));
			drpLocator.selectByVisibleText(visibleText);
			Log.info("In a "+elementName+" Dropdown "+visibleText+" will selected.");
		} catch (Exception e) {
			Log.error("In a "+elementName+" Dropdown "+visibleText+" will Not selected due to some reason!");
			e.printStackTrace();
		}
	}
}
