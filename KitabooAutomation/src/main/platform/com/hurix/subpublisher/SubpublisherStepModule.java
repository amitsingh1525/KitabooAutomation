package com.hurix.subpublisher;

import java.util.Properties;

import org.openqa.selenium.By;

import com.hurix.automation.utility.UIElements;

public class SubpublisherStepModule extends UIElements {
	
	private static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/publisher.properties");
	
	public static void linkSubPublisher(){
		try {
			elementFinderByID(prop.getProperty("subpublishertab_id"), "SubPublisher link clicked").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
	
	public static void btnCreateAccount(){
		try {
			elementFinderByID(prop.getProperty("btncreateaccount_id"), "Add Account button clicked").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxOrganization(String Organization){
		try {
			elementFinderByID(prop.getProperty("txtbxOrganization_id"), "Organization textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxOrganization_id"), "Entered data in Organization textbox is: '"+Organization+"'.").sendKeys(Organization);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxAdministratorFirstName(String AdministratorFirstName){
		try {
			elementFinderByID(prop.getProperty("txtbxAdministratorFirstName_id"), "Administrator first name textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxAdministratorFirstName_id"), "Entered data in Administrator first name textbox is: '"+AdministratorFirstName+"'.").sendKeys(AdministratorFirstName);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxAdministratorLastName(String AdministratorLastName){
		try {
			elementFinderByID(prop.getProperty("txtbxAdministratorLastName_id"), "Administrator Last name textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxAdministratorLastName_id"), "Entered data in Administrator Last name textbox is: '"+AdministratorLastName+"'.").sendKeys(AdministratorLastName);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxAdminEmailId(String AdminEmailId){
		try {
			elementFinderByID(prop.getProperty("txtbxAdministratorLastName_id"), "Admin EmailId textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxAdministratorLastName_id"), "Entered data in Admin EmailId textbox is: '"+AdminEmailId+"'.").sendKeys(AdminEmailId);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxStreet(String Street){
		try {
			elementFinderByID(prop.getProperty("txtbxStreet_id"), "Street textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxStreet_id"), "Entered data in Street textbox is: '"+Street+"'.").sendKeys(Street);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxCity(String City){
		try {
			elementFinderByID(prop.getProperty("txtbxCity_id"), "City textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxCity_id"), "Entered data in City textbox is: '"+City+"'.").sendKeys(City);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void txtbxZip(String Zip){
		try {
			elementFinderByID(prop.getProperty("txtbxZip_id"), "Zip textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxZip_id"), "Entered data in Zip textbox is: '"+Zip+"'.").sendKeys(Zip);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void drpdwnCountry(){
		try {
			UIElements.selectDropdown(By.xpath("txtbxCountry_xpath"), "India", "India");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtbxContact(String Contact){
		try {
			elementFinderByID(prop.getProperty("txtbxContact_id"), "Contact textbox clicked.").click();
			elementFinderByID(prop.getProperty("txtbxContact_id"), "Entered data in Contact textbox is: '"+Contact+"'.").sendKeys(Contact);
		} catch (Exception e) {
			System.out.println("Element not present.");
		}
	}
	
	public static void btnBrowsefile(String filepath){
		try {
			elementFinderByID(prop.getProperty("btnBrowse_id"), "File uploaded is: '"+filepath+"'.").sendKeys("filepath");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
	
	public static void btnSave(){
		try {
			elementFinderByID(prop.getProperty("btnSave_id"), "Save button clicked").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
	
	public static void btnReset(){
		try {
			elementFinderByID(prop.getProperty("btnReset_id"), "Reset button clicked").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}
	
	public static void btnCancel(){
		try {
			elementFinderByID(prop.getProperty("btnCancel_id"), "Cancel button clicked").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element not present.");
		}
	}


}
