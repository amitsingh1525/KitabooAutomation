package com.hurix.library.Authorwidget;

import java.util.Properties;

import org.openqa.selenium.By;

import com.hurix.automation.utility.DragAndDrop;
import com.hurix.automation.utility.UIElements;

public class AuthorwidgetStepModule extends UIElements {
	
private static Properties prop = getProperty("C:/Users/amit.singh/git/KitabooAutomation/KitabooAutomation/config/authoring/authoring.properties");

// create author widget
	
	public static void linkActivites(){
		try {
			elementFinderByID(prop.getProperty("link_Activites_ID"), "link_Activites").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnAuthorWidget(){
		try {
			elementFinderByID(prop.getProperty("authorWidget_btn_ID"), "btn_authorWidget").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtwidgetName(String WidgetName){
		try {
			elementFinderByID(prop.getProperty("widgetName_txtbx_ID"), "txtwidgetName").sendKeys(WidgetName);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txt_category(String category){
		try {
			elementFinderByID(prop.getProperty("category_txtbx_ID"), "txt_category").sendKeys(category);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txt_addTag(String tag){
		try {
			elementFinderByID(prop.getProperty("addTag_txtbx_ID"), "txt_addTag").sendKeys(tag);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txt_courseDescription(String courseDescription){
		try {
			elementFinderByID(prop.getProperty("courseDescription_txtbx_ID"), "txt_courseDescription").sendKeys(courseDescription);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btn_save(){
		try {
			elementFinderByID(prop.getProperty("save_btn_ID"), "btn_save").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btn_begin(){
		try {
			elementFinderByID(prop.getProperty("begin_btn_Id"), "btn_begin").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
// Authoring tool opened
	public static void btn_tableof_content(){
		try {
			elementFinderByID(prop.getProperty("tableof_content_Xpath"), "btn_tableof_content").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnFirstPage(){
		try {
			elementFinderByXpath(prop.getProperty("first_page_Xpath"), "btn_first_page").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btn_componenets(){
		try {
			elementFinderByXpath(prop.getProperty("componenets_Xpath"), "btn_componenets").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

// Title component started
	public static void cmp_title(){
		try {
			DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("title_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void dragDrop(){
		try {
			elementFinderByXpath(prop.getProperty("dropdown_ID"), "dragDrop()");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btn_titlestyle(){
		try {
			elementFinderByXpath(prop.getProperty("titlestyle_Xpath"), "btn_titlestyle").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btn_titleheader(){
		try {
			elementFinderByXpath(prop.getProperty("titleheader_Xpath"), "btn_titleheader").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btn_titleSetting(){
		try {
			elementFinderByXpath(prop.getProperty("titleSetting_Xpath"), "btn_titleSetting").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtbx_title(String title){
		try {
			elementFinderByXpath(prop.getProperty("title_txtbx_Xpath"), "txtbx_title").clear();
			elementFinderByXpath(prop.getProperty("title_txtbx_Xpath"), "txtbx_title").sendKeys(title);
			elementFinderByXpath(prop.getProperty("title_txtbx_Xpath"), "txtbx_title").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtbx_author(String author){
		try {
			elementFinderByXpath(prop.getProperty("author_txtbx_Xpath"), "txtbx_author").clear();
			elementFinderByXpath(prop.getProperty("author_txtbx_Xpath"), "txtbx_author").sendKeys(author);
			elementFinderByXpath(prop.getProperty("author_txtbx_Xpath"), "txtbx_author").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void saveActivity(){
		try {
			elementFinderByXpath(prop.getProperty("dropdown_ID"), "SaveActivity()");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
// Header component started
	
	public static void cmp_header(){
		try {
			DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("header_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	
}
