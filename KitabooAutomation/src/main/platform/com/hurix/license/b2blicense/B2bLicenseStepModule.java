package com.hurix.license.b2blicense;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.UIElements;

public class B2bLicenseStepModule extends UIElements {

	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/license.properties");

	
	
	public static void btndownloadOrderlicense(){
		try {			
			elementFinderByID(prop.getProperty("downloadOrderlicense_id"), "btndownloadOrderlicense").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	
	public static void btncompleteOrderlicense(){
		try {
			elementFinderByID(prop.getProperty("completeOrderlicense_id"), "txtsearchtxtboxInstituteLicene").click();
			} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	
	public static void txtsearchtxtboxInstituteLicene(String instituteuser){
		try {
			elementFinderByID(prop.getProperty("searchtxtboxInstituteLicene_id"), "txtsearchtxtboxInstituteLicene").clear();
			elementFinderByID(prop.getProperty("searchtxtboxInstituteLicene_id"), "txtsearchtxtboxInstituteLicene").click();
			elementFinderByID(prop.getProperty("searchtxtboxInstituteLicene_id"), "txtsearchtxtboxInstituteLicene").sendKeys(instituteuser);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnsearchInstituteFrLicense(){
		try {
			elementFinderByID(prop.getProperty("searchInstituteFrLicense_id"), "btnsearchInstituteFrLicense").click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	
	public static void btnorderBookSearch(){
		try {
			elementFinderByXpath(prop.getProperty("orderBookSearch_xpath"), "btnorderBookSearch").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	
	public static void txtorderBookSearchText(String book){
		try {
			elementFinderByID(prop.getProperty("orderBookSearchText_id"), "txtorderBookSearchText").clear();
			elementFinderByID(prop.getProperty("orderBookSearchText_id"), "txtorderBookSearchText").click();
			elementFinderByID(prop.getProperty("orderBookSearchText_id"), "txtorderBookSearchText").sendKeys(book);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	
	
	public static void txtorderNolicense(String book){
		try {
			elementFinderByID(prop.getProperty("orderNolicense_id"), "txtorderNolicense").clear();
			elementFinderByID(prop.getProperty("orderNolicense_id"), "txtorderNolicense").click();
			elementFinderByID(prop.getProperty("orderNolicense_id"), "txtorderNolicense").sendKeys(book);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}



	public static void btnnavigationbookselectpagelicense(){
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement navigationbookselectpagelicense = elementFinderByXpath(prop.getProperty("navigationbookselectpagelicense_xpath"), "btnnavigationbookselectpagelicense");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",navigationbookselectpagelicense);
			elementFinderByXpath(prop.getProperty("navigationbookselectpagelicense_xpath"), "btnnavigationbookselectpagelicense").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}		

	public static void btnnavigationorderlicense(){
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement navigationorderlicense = elementFinderByXpath(prop.getProperty("navigationorderlicense_xpath"), "btnnavigationorderlicense");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",navigationorderlicense);
			elementFinderByXpath(prop.getProperty("navigationorderlicense_xpath"), "btnnavigationorderlicense").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void linkLicenses(){
		try {
			elementFinderByID(prop.getProperty("linkLicenses_id"), "linkLicenses").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnradiobuttonlicense(){
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			elementFinderByXpath(prop.getProperty("radiobuttonlicense_xpath"), "radiobuttonlicense").click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnissueenablebuttonlicense1(){
		try {
			UIElements.elementFinderByID("enableLicense","").click();
		    } catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void chkfirstcheckboxlicense(){
		try {
			elementFinderByID(prop.getProperty("firstcheckboxlicense_id"), "firstcheckboxlicense").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnresetbuttonlicense(){
		try {
			elementFinderByID(prop.getProperty("resetbuttonlicense_id"), "resetbuttonlicense").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void btnIssuebuttonLicense(){
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement IssuebuttonLicense = elementFinderByID(prop.getProperty("IssuebuttonLicense_id"), "btnIssuebuttonLicense");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",IssuebuttonLicense);
			elementFinderByID(prop.getProperty("IssuebuttonLicense_id"), "btnIssuebuttonLicense").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void drpaccesscodelicense(int k){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":order_type_label"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			UIElements.elementFinderByID("orderConfirmationTable:"+k+":order_type_label", "").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void accesscodeindividuallicense(int k){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.xpath(".//*[@id='orderConfirmationTable:"+k+":order_type_panel']/div/ul/li[1]"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			UIElements.elementFinderByXpath(".//*[@id='orderConfirmationTable:"+k+":order_type_panel']/div/ul/li[1]", "").click();
					
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void accesscodegrouplicense(int k){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.xpath(".//*[@id='orderConfirmationTable:"+k+":order_type_panel']/div/ul/li[2]"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			UIElements.elementFinderByXpath(".//*[@id='orderConfirmationTable:"+k+":order_type_panel']/div/ul/li[2]","").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void accesscodegroupname(String groupname, int k){
		try {
			threadHold_2Sec();
			/*wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":groupname"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);*/
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":groupname")).clear();
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":groupname")).click();
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":groupname")).sendKeys(groupname);
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void accesscodegrouplicensinglicense(int k){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.xpath(".//*[@id='orderConfirmationTable:"+k+":order_type_panel']/div/ul/li[3]"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			UIElements.elementFinderByXpath(".//*[@id='orderConfirmationTable:"+k+":order_type_panel']/div/ul/li[3]","").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void licenselimitelicense(String deviceLicenses,int k){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":totalLicenseSpinner_input"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			UIElements.elementFinderByID("orderConfirmationTable:"+k+":totalLicenseSpinner_input","").clear();
			UIElements.elementFinderByID("orderConfirmationTable:"+k+":totalLicenseSpinner_input","").click();
			UIElements.elementFinderByID("orderConfirmationTable:"+k+":totalLicenseSpinner_input","").sendKeys(deviceLicenses);
				
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	
	public static void licenselimitegrouplicense(String deviceLicenses,int k){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":totalGroupLicenseSpinner_input"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":totalGroupLicenseSpinner_input")).clear();
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":totalGroupLicenseSpinner_input")).click();
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":totalGroupLicenseSpinner_input")).sendKeys(deviceLicenses);
			
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	
	
	
	public static void licensegrouplimitelicense(String deviceLicenses,int k){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":totalGroupSpinner_input"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":totalGroupSpinner_input")).clear();
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":totalGroupSpinner_input")).click();
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":totalGroupSpinner_input")).sendKeys(deviceLicenses);
			
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	

	public static void devicelimitelicense(String devicelimit,int k){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":devicelimit_input"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":devicelimit_input")).clear();
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":devicelimit_input")).click();
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":devicelimit_input")).sendKeys(devicelimit);
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}			

	public static void expiredatelicense(String expiryDate, int k){
		try {
			threadHold_2Sec();
			int increaseDate = Integer.parseInt("10");
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Calendar c = Calendar.getInstance();    
			c.add(Calendar.DATE, increaseDate);
			String date = dateFormat.format(c.getTime());
	        ((JavascriptExecutor)Driver.driver).executeScript ("document.getElementById('orderConfirmationTable:"+k+":endDate_input').removeAttribute('readonly',0);");
	        WebElement startDateBox= Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":endDate_input"));
			startDateBox.sendKeys(date);
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	
	

	public static void expiredateredemlicense(String expiryDate, int k){
		try {
			threadHold_2Sec();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":redemSize_input"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":redemSize_input")).clear();
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":redemSize_input")).click();
			Driver.driver.findElement(By.id("orderConfirmationTable:"+k+":redemSize_input")).sendKeys(expiryDate);
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	
	public static void validitydropdownlicense(int k){
		try {
			threadHold_2Sec();
			k=k+1;
		/*	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.xpath("//*[@id='orderConfirmationTable:"+k+":order_validity_2']"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);*/
			
			Driver.driver.findElement(By.xpath("//tbody[@id='orderConfirmationTable_data']/tr["+k+"]/td[5]/span/div")).click();
			k=k-1;
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

		public static void validityactivatelicense(int k){
		try {
			threadHold_2Sec();
			/*wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.xpath("//*[@id='orderConfirmationTable:"+k+":order_validity_2_panel']/div/ul/li[1]"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);*/
			UIElements.elementFinderByXpath("//*[@id='orderConfirmationTable:"+k+":order_validity_2_panel']/div/ul/li[1]","").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void validityRedemptionlicense(int k){
		try {
			threadHold_2Sec();
			/*wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.xpath("//*[@id='orderConfirmationTable:"+k+":order_validity_2_panel']/div/ul/li[2]"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);*/
			Driver.driver.findElement(By.xpath("//*[@id='orderConfirmationTable:"+k+":order_validity_2_panel']/div/ul/li[2]")).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void validityPerpetuallicense(int k){
		try {
			threadHold_2Sec();
		/*	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader_image")));
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement element=Driver.driver.findElement(By.xpath("//*[@id='orderConfirmationTable:"+k+":order_validity_2_panel']/div/ul/li[3]"));
			js.executeScript("arguments[0].scrollIntoView(true);",element);*/
			Driver.driver.findElement(By.xpath("//tbody[@id='orderConfirmationTable_data']/tr["+k+"]/td[5]/span/div/div/select/option[text()='Perpetual']")).click();
			//tbody[@id='orderConfirmationTable_data']/tr[3]/td[5]/span/div/div/select/option[text()='Perpetual']
			//*[@id='orderConfirmationTable:"+k+":order_validity_2_panel']/div/ul/li[3]
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	


	public static void dayaccessdropdownlicense(){
		try {
			elementFinderByID(prop.getProperty("dayaccessdropdownlicense_id"), "dayaccessdropdownlicense").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}



}
