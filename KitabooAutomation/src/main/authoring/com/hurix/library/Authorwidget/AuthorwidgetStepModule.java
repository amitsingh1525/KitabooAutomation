package com.hurix.library.Authorwidget;

import static com.hurix.library.Authorwidget.AuthorwidgetStepModule.prop;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hurix.automation.utility.DragAndDrop;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.UIElements;

public class AuthorwidgetStepModule extends UIElements {
	
public static Properties prop = getProperty(System.getProperty("user.dir")+"/config/authoring/authoring.properties");

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
			elementFinderByLinkText(prop.getProperty("save_btn_linktext"), "btn_save").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btn_begin(){
		try {
			elementFinderByLinkText(prop.getProperty("begin_btn_Linktext"), "btn_begin").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
// Authoring tool opened
	public static void btn_tableof_content(){
		try {
			elementFinderByXpath(prop.getProperty("tableof_content_Xpath"), "btn_tableof_content").click();
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

	

// copy functionality  (List and grid view)	
	
	public static void btnlistview_copy(){
		try {
			elementFinderByID(prop.getProperty("listviewscopy_btn_ID"), "btn_copy").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btngridview_copy(){
		try {
			elementFinderByID(prop.getProperty("gridviewscopy_btn__ID"), "btn_copy").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
// Rename functionality  (grid view)	
	
	public static void btnrenamewidget(){
		try {
			elementFinderByID(prop.getProperty("rename_btn_ID"), "btn_rename").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtbx_widgetname(String widgetname){
		try {
			elementFinderByID(prop.getProperty("widgetname_txtbx_ID"), "txtbx_widgetname").clear();
			elementFinderByID(prop.getProperty("widgetname_txtbx_ID"), "txtbx_widgetname").sendKeys(widgetname);
			elementFinderByID(prop.getProperty("widgetname_txtbx_ID"), "txtbx_widgetnamer").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnsavewidget(){
		try {
			elementFinderByLinkText(prop.getProperty("save_btn_linktext"), "btn_save").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
// Change cover functionality  (grid view)	
	
	public static void btngridview_changeCover(){
		try {
			elementFinderByXpath(prop.getProperty("changecover_bx_Xpath"), "btn_changeCover").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
// Share functionality (List and Grid view)
	
	public static void btnlistview_share(){
		try {
			elementFinderByID(prop.getProperty("listviewshare_btn_ID"), "btn_share").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void rdbtnoption_share(){
		try {
			elementFinderByID(prop.getProperty("radiooption_btn_ID"), "btn_radiooption").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtSharedUser(){
		try {
			String user=elementFinderByXpath(prop.getProperty("shareduser_txt_xpath"), "txt shared user").getText();
			System.out.println("Shared User: "+ user);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtanotherSharedUser(){
		try {
			String user=elementFinderByXpath(prop.getProperty("anothershareduser_txt_xpath"), "txt another shared user").getText();
			System.out.println("Another Shared User: "+ user);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnconfirm_share(){
		try {
			elementFinderByID(prop.getProperty("shareconfirm_btn_Id"), "btn_confirm").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btngridview(){
		try {
			elementFinderByID(prop.getProperty("gridview_btn_ID"), "btn_gridview").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btngridview_share(){
		try {
			elementFinderByID(prop.getProperty("gridviewshare_btn__ID"), "btn_share").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnlistview(){
		try {
			elementFinderByID(prop.getProperty("listview_btn_ID"), "btn_listview").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
// Delete author widget Functionality
	public static void btndelete(){
		try {
			elementFinderByID(prop.getProperty("delete_btn_ID"), "btn_delete").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnconfirmdelete(){
		try {
			elementFinderByID(prop.getProperty("deleteconfirm_btn_ID"), "btn_confirmdelete").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
// Filter author widget and create widget Functionality
	public static void drpdwnselect(){
		try {
			elementFinderByXpath(prop.getProperty("select_drpdwn_Xpath"), "drpdwn_select").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwnauthorwidget(){
		try {
			elementFinderByXpath(prop.getProperty("authorwidget_drpdwn_Xpath"), "drpdwn_authorwidget").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwncreatewidget(){
		try {
			elementFinderByXpath(prop.getProperty("createwidget_drpdwn_Xpath"), "drpdwn_createwidget").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwnselectall(){
		try {
			elementFinderByXpath(prop.getProperty("selectall_drpdwn_Xpath"), "drpdwn_selectall").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
//search functionality	
	public static void txtbxSearch(String WidgetName){
		try {
			elementFinderByID(prop.getProperty("search_txtbox_ID"), "searchwidget").sendKeys(WidgetName);
			elementFinderByID(prop.getProperty("search_txtbox_ID"), "searchwidget").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnSearch(){
		try {
			JavascriptExecutor js2 = (JavascriptExecutor) Driver.driver;
			 WebElement element2 = Driver.driver.findElement(By.id("search1"));
			 js2.executeScript("arguments[0].setAttribute('style', 'visibility: visible;')",element2);
			 Thread.sleep(1000);
			 elementFinderByID(prop.getProperty("btn_search_ID"), "btnsearch").click();
			 System.out.println("search clicked");

		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	//Filter and search functionality	
		public static void btnfilter(){
			try {
				elementFinderByID(prop.getProperty("filter_btn_ID"), "btn filter").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void chkbxfilterFirst(){
			try {
				elementFinderByID(prop.getProperty("filterfirst_chk_ID"), "chkbx filter First").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void chkbxfilterAll(){
			try {
				elementFinderByID(prop.getProperty("filterall_chk_ID"), "chkbx filter All").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnApplyFilter(){
			try {
				elementFinderByID(prop.getProperty("apply_btn_ID"), "btn apply filter").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnClearFilter(){
			try {
				elementFinderByID(prop.getProperty("clearfilter_btn_ID"), "btn clear filter").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		
	
//Publish author widget (List view)	
	public static void btnPublish(){
		try {
			elementFinderByID(prop.getProperty("publish_btn_ID"), "btn_publish").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwnFormat(){
		try {
			elementFinderByID(prop.getProperty("format_drpdwn_ID"), "drpdw Format").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwnFormatHTML(){
		try {
			
			/*Select drpcountry = new Select(Driver.driver.findElement(By.id("theme1tt233")));
			Thread.sleep(2000);
			drpcountry.selectByVisibleText("SCORM");
			
			Select drpCountry = new Select(Driver.driver.findElement(By.id("pubLangForAuthor")));
			Thread.sleep(2000);
			drpCountry.selectByVisibleText("Italian");
			Thread.sleep(500);*/
			UIElements.selectDropdown(By.id("theme1tt233"), "HTML", "HTML");
			//elementFinderByLinkText(prop.getProperty("formatHTML_drpdwn_linktext"), "drpdw Format HTML").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwnFormatScorm(){
		try {
			UIElements.selectDropdown(By.id("pubLangForAuthor"), "SCORM", "SCORM");
			//elementFinderByLinkText(prop.getProperty("formatSCORM_drpdwn_linktext"), "drpdw Format Scorm").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwnLanguage(){
		try {
			elementFinderByID(prop.getProperty("language_drpdwn_ID"), "drpdw Language").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwnLanguageEnglish(){
		try {
			UIElements.selectDropdown(By.id("pubLangForAuthor"), "English", "English");
			//elementFinderByLinkText(prop.getProperty("languageEnglish_drpdwn_linktext"), "drpdw Language English").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwnLanguageItalian(){
		try {
			UIElements.selectDropdown(By.id("pubLangForAuthor"), "Italian", "Italian");
			//elementFinderByLinkText(prop.getProperty("languageItalian_drpdwn_linktext"), "drpdw Language Italian").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwnLanguageSpanish(){
		try {
			UIElements.selectDropdown(By.id("pubLangForAuthor"), "Spanish", "Spanish");
			//elementFinderByLinkText(prop.getProperty("languageSpanish_drpdwn_linktext"), "drpdw Language Spanish").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnPublishOk(){
		try {
			elementFinderByLinkText(prop.getProperty("publishok_btn_linktext"), "btn publish ok").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnBlockMainPage(){
		try {
			UIElements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(prop.getProperty("blockMainPage_btn_ID"))));
			//elementFinderByID(prop.getProperty("blockMainPage_btn_ID"), "btn Block main page");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
//Download published package
	
	public static void btnDownloadPackage(){
		try {
			elementFinderByXpath(prop.getProperty("downloadpackage_btn_Xpath"), "btn Download package").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwPublished(){
		try {
			elementFinderByID(prop.getProperty("published_drpdw_ID"), "drpdwn Published").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwAll(){
		try {
			UIElements.selectDropdown(By.id("singleMode"), "All", "All");
			//elementFinderByLinkText(prop.getProperty("all_drpdw_linktext"), "drpdwn All").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
//Multipublish author widget (List view)
	
	public static void chkSelectAll(){
		try {
			elementFinderByID(prop.getProperty("chk_select_all_ID"), "chk select all").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void chkdeSelectAll(){
		try {
			elementFinderByID(prop.getProperty("chk_deslect_all_ID"), "chk deselect all").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void chkFirstbx(){
		try {
			elementFinderByID(prop.getProperty("chk_first_ID"), "chk firstbx").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void chkSecondbx(){
		try {
			elementFinderByID(prop.getProperty("chk_second_ID"), "chk secondbx").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnMultipublish(){
		try {
			elementFinderByID(prop.getProperty("btn_multipublish_ID"), "btn multipublish").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwTheme(){
		try {
			elementFinderByID(prop.getProperty("drpdw_theme_ID"), "drpdwn Theme").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwThemeAqua(){
		try {
			
			UIElements.selectDropdown(By.id("theme12"), "Aqua", "Aqua");
			//elementFinderByLinkText(prop.getProperty("drpdw_themeAqua_linktext"), "drpdwn ThemeAqua").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwFormat(){
		try {
			elementFinderByID(prop.getProperty("drpdw_format_ID"), "drpdwn Format").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwFormatHtml(){
		try {
			UIElements.selectDropdown(By.id("theme1tt"), "HTML", "HTML");
			//elementFinderByLinkText(prop.getProperty("drpdw_formatHTML_linktext"), "drpdwn Format HTML").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwlanguage(){
		try {
			
			elementFinderByID(prop.getProperty("drpdw_language_ID"), "drpdwn Language").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwLanguageEnglish(){
		try {
			UIElements.selectDropdown(By.id("pubLangForAuthor1"), "English", "English");
			//elementFinderByLinkText(prop.getProperty("drpdw_languageEnglish_linktext"), "drpdwn Language English").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnMultiplePublish(){
		try {
			elementFinderByLinkText(prop.getProperty("btn_publishnultiple_ID"), "btn multiple publish").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwLanguageItalian(){
		try {
			UIElements.selectDropdown(By.id("pubLangForAuthor1"), "Italian", "Italian");
			//elementFinderByLinkText(prop.getProperty("drpdw_languageItalian_linktext"), "drpdwn Language Italian").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void drpdwLanguageSpanish(){
		try {
			UIElements.selectDropdown(By.id("pubLangForAuthor1"), "Spanish", "Spanish");
			//elementFinderByID(prop.getProperty("drpdw_languageSpanish_linktext"), "drpdwn Language Spanish").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
//Download Multiple published package functionality
	public static void chk_SelectAllDownload(){
		try {
			elementFinderByID(prop.getProperty("chk_selectAlldownload_ID"), "chk select all download").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void chk_FirstDownload(){
		try {
			elementFinderByID(prop.getProperty("chk_firstdownload_ID"), "chk first download").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void chk_secondDownload(){
		try {
			elementFinderByID(prop.getProperty("chk_seconddownload_ID"), "chk second download").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnMultipleDownload(){
		try {
			elementFinderByID(prop.getProperty("btn_multiple_download_ID"), "btn multiple download").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
//	Components started

// Title component started
	
	public static void cmp_title(){
		try {
			System.out.println("drag title comp");
			DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("title_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			System.out.println("Dropped title component");
			
			//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void dragDrop(){
		try {
			elementFinderByID(prop.getProperty("dropdown_ID"), "dragDrop()");
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
			elementFinderByXpath(prop.getProperty("save_activity_Xpath"), "SaveActivity()").click();
			 //UIElements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("loader_Xpath"))));
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void loader(){
		try {
			 UIElements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("loader_Xpath"))));
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
// Header component started
	
	public static void addPage(){
		try {
			DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("addPage_btn_Xpath")), By.xpath(prop.getProperty("dragandropPage_Xpath")));
			//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void cmpheader(){
		try {
			DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("header_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtbxHeader(String header){
		try {
			elementFinderByXpath(prop.getProperty("header_txtbx_Xpath"), "txtbx header").clear();
			elementFinderByXpath(prop.getProperty("header_txtbx_Xpath"), "txtbx header").sendKeys(header);
			elementFinderByXpath(prop.getProperty("header_txtbx_Xpath"), "txtbx header").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnheaderStyle(){
		try {
			elementFinderByXpath(prop.getProperty("header_style_Xpath"), "btn headerStyle").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnheaderSecondStyle(){
		try {
			elementFinderByXpath(prop.getProperty("header_secondstyle_Xpath"), "btn header Second Style").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnheaderSettings(){
		try {
			elementFinderByXpath(prop.getProperty("header_setting_Xpath"), "btn header Settings").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtbxBackgroundColour(){
		try {
			elementFinderByXpath(prop.getProperty("background_colour_txtbx_Xpath"), "txtbx Background Colour").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void txtbxSelectColour(){
		try {
			elementFinderByXpath(prop.getProperty("selectcolour_txtbx_Xpath"), "txtbx Select Colour").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	// Text component started
		
		public static void cmptext(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("text_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
				//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxtext(String text){
			try {
				elementFinderByXpath(prop.getProperty("text_txtbx_Xpath"), "txtbx text").clear();
				elementFinderByXpath(prop.getProperty("text_txtbx_Xpath"), "txtbx text").sendKeys(text);
				elementFinderByXpath(prop.getProperty("text_txtbx_Xpath"), "txtbx text").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
	
	//Image component started
		
		public static void cmpimage(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("image_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
				//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxImageHeader(String imageheader){
			try {
				//elementFinderByXpath(prop.getProperty("imageheader_txtbx_Xpath"), "txtbx image header").clear();
				elementFinderByXpath(prop.getProperty("imageheader_txtbx_Xpath"), "txtbx image header").sendKeys(imageheader);
				//elementFinderByXpath(prop.getProperty("imageheader_txtbx_Xpath"), "txtbx image header").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxImageCaption(String caption){
			try {
				elementFinderByXpath(prop.getProperty("captionimage_txtbx_Xpath"), "txtbx image caption").clear();
				elementFinderByXpath(prop.getProperty("captionimage_txtbx_Xpath"), "txtbx image caption").sendKeys(caption);
				elementFinderByXpath(prop.getProperty("captionimage_txtbx_Xpath"), "txtbx image caption").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnUploadImage(){
			try {
				 UIElements.wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("uploadimage_area_ID"))));
				elementFinderByID(prop.getProperty("uploadimage_area_ID"), "btn upload Image").click();
				 Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
	//Video component started
		
		public static void cmpVideo(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("video_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
				//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnUploadVideo(){
			try {
				UIElements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("uploadvideo_area_Xpath"))));
				elementFinderByXpath(prop.getProperty("uploadvideo_area_Xpath"), "btn upload Video").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxVideoHeader(String header){
			try {
				elementFinderByXpath(prop.getProperty("videoheader_txtbx_Xpath"), "txtbx video header").clear();
				elementFinderByXpath(prop.getProperty("videoheader_txtbx_Xpath"), "txtbx video header").sendKeys(header);
				//elementFinderByXpath(prop.getProperty("imageheader_txtbx_Xpath"), "txtbx image header").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxVideoCaption(String caption){
			try {
				elementFinderByXpath(prop.getProperty("captionvideo_txtbx_Xpath"), "txtbx video caption").clear();
				elementFinderByXpath(prop.getProperty("captionvideo_txtbx_Xpath"), "txtbx video caption").sendKeys(caption);
				elementFinderByXpath(prop.getProperty("captionvideo_txtbx_Xpath"), "txtbx video caption").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
     //Audio component started	
		
		public static void cmpAudio(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("audio_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
				//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxAudioCaption(String Caption){
			try {
				elementFinderByXpath(prop.getProperty("captionaudio_txtbx_Xpath"), "txtbx Audio Caption").clear();
				elementFinderByXpath(prop.getProperty("captionaudio_txtbx_Xpath"), "txtbx Audio Caption").sendKeys(Caption);
				//elementFinderByXpath(prop.getProperty("imageheader_txtbx_Xpath"), "txtbx image header").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
						
	// HTML interactivity
		
		public static void cmpHTMLinteractivity(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("html_interactivity_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
				//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnUploadHTMLinteractivity(){
			try {
				UIElements.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("upload_zip_btn_Xpath"))));
				elementFinderByXpath(prop.getProperty("upload_zip_btn_Xpath"), "btn upload HTMLinteractivity").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxHTMLinteractivityCaption(String Caption){
			try {
				elementFinderByXpath(prop.getProperty("captioninteractivity_txtbx_Xpath"), "txtbx HTMLinteractivity Caption").clear();
				elementFinderByXpath(prop.getProperty("captioninteractivity_txtbx_Xpath"), "txtbx HTMLinteractivity Caption").sendKeys(Caption);
				//elementFinderByXpath(prop.getProperty("imageheader_txtbx_Xpath"), "txtbx image header").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
	// Table component
		public static void cmpTable(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("table_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
				//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxNoOfRows(String NoOfRows){
			try {
				elementFinderByXpath(prop.getProperty("rows_txtbx_Xpath"), "txtbx no of rows").clear();
				elementFinderByXpath(prop.getProperty("rows_txtbx_Xpath"), "txtbx no of rows").sendKeys(NoOfRows);
				
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxNoOfColumns(String NoOfColumns){
			try {
				elementFinderByXpath(prop.getProperty("columns_txtbx_Xpath"), "txtbx No Of Columns").clear();
				elementFinderByXpath(prop.getProperty("columns_txtbx_Xpath"), "txtbx No Of Columns").sendKeys(NoOfColumns);
				
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnCreate(){
			try {
				elementFinderByXpath(prop.getProperty("create_btn_Xpath"), "btn create").click();
				
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxTableHeader(String text){
			try {
				elementFinderByXpath(prop.getProperty("tableheader_txtbx_Xpath"), "txtbx Table header").clear();
				elementFinderByXpath(prop.getProperty("tableheader_txtbx_Xpath"), "txtbx Table header").sendKeys(text);
				
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxTableCaption(String caption){
			try {
				elementFinderByXpath(prop.getProperty("captiontable_txtbx_Xpath"), "txtbx Table caption").clear();
				elementFinderByXpath(prop.getProperty("captiontable_txtbx_Xpath"), "txtbx Table caption").sendKeys(caption);
			
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxTableHeader1(String Header1){
			try {
				elementFinderByXpath(prop.getProperty("tableheader1_txtbx_Xpath"), "txtbx Table Header1").click();
				elementFinderByXpath(prop.getProperty("tableheader1_txtbx_Xpath"), "txtbx Table Header1").sendKeys(Header1);
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
	// Static sidebar
		public static void cmpStaticSidebar(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("staticsidebar_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
				//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxstaticsidebarHeader(String header){
			try {
				elementFinderByXpath(prop.getProperty("staticsidebarheader_txtbx_Xpath"), "txtbx staticsidebar header").clear();
				elementFinderByXpath(prop.getProperty("staticsidebarheader_txtbx_Xpath"), "txtbx staticsidebar header").sendKeys(header);
				
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxstaticsidebarText(String text){
			try {
				elementFinderByXpath(prop.getProperty("staticsidebartext_txtbx_Xpath"), "txtbx staticsidebar text").clear();
				elementFinderByXpath(prop.getProperty("staticsidebartext_txtbx_Xpath"), "txtbx staticsidebar text").sendKeys(text);
				elementFinderByXpath(prop.getProperty("staticsidebartext_txtbx_Xpath"), "txtbx staticsidebar text").click();
				
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnUploadImagesidebar(){
			try {
				elementFinderByID(prop.getProperty("uploadimage_area_id"), "btn upload Image static sidebar").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxStaticSidebarCaption(String Caption){
			try {
				elementFinderByXpath(prop.getProperty("captionstaticsidebar_txtbx_Xpath"), "txtbx Static sidebar Caption").clear();
				elementFinderByXpath(prop.getProperty("captionstaticsidebar_txtbx_Xpath"), "txtbx Static sidebar Caption").sendKeys(Caption);
				elementFinderByXpath(prop.getProperty("captionstaticsidebar_txtbx_Xpath"), "txtbx Static sidebar Caption").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
	// Multiple choice questions
		public static void cmpMultipleChoiceQuestions(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("multipleChoice_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
				//elementFinderByXpath(prop.getProperty("title_cmp_Xpath"), "cmp_title");
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxMCQHeader(String MCQheader){
			try {
				//elementFinderByXpath(prop.getProperty("multipleChoiceheader_txtbx_Xpath"), "txtbx MCQ header").clear();
				elementFinderByXpath(prop.getProperty("multipleChoiceheader_txtbx_Xpath"), "txtbx MCQ header").sendKeys(MCQheader);
				//elementFinderByXpath(prop.getProperty("imageheader_txtbx_Xpath"), "txtbx image header").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxMCQinstruction(String MCQinstruction){
			try {
				//elementFinderByXpath(prop.getProperty("multipleChoiceinstruction_txtbx_Xpath"), "txtbx MCQ instruction").clear();
				elementFinderByXpath(prop.getProperty("multipleChoiceinstruction_txtbx_Xpath"), "txtbx MCQ instruction").sendKeys(MCQinstruction);
				//elementFinderByXpath(prop.getProperty("multipleChoiceinstruction_txtbx_Xpath"), "txtbx MCQ instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxMCQquestion(String question){
			try {
				//elementFinderByXpath(prop.getProperty("multipleChoice_question_txtbx_Xpath"), "txtbx MCQ question").clear();
				elementFinderByXpath(prop.getProperty("multipleChoice_question_txtbx_Xpath"), "txtbx MCQ question").sendKeys(question);
				//elementFinderByXpath(prop.getProperty("multipleChoiceinstruction_txtbx_Xpath"), "txtbx MCQ instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxMCQOption1(String Option1){
			try {
				//elementFinderByXpath(prop.getProperty("multipleChoice_question_txtbx_Xpath"), "txtbx MCQ question").clear();
				elementFinderByXpath(prop.getProperty("multipleChoice_option1_txtbx_Xpath"), "txtbx MCQ Option1").sendKeys(Option1);
				//elementFinderByXpath(prop.getProperty("multipleChoiceinstruction_txtbx_Xpath"), "txtbx MCQ instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxMCQOption2(String Option2){
			try {
				//elementFinderByXpath(prop.getProperty("multipleChoice_question_txtbx_Xpath"), "txtbx MCQ question").clear();
				elementFinderByXpath(prop.getProperty("multipleChoice_option2_txtbx_Xpath"), "txtbx MCQ Option2").sendKeys(Option2);
				//elementFinderByXpath(prop.getProperty("multipleChoiceinstruction_txtbx_Xpath"), "txtbx MCQ instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxMCQOption3(String Option3){
			try {
				//elementFinderByXpath(prop.getProperty("multipleChoice_question_txtbx_Xpath"), "txtbx MCQ question").clear();
				elementFinderByXpath(prop.getProperty("multipleChoice_option3_txtbx_Xpath"), "txtbx MCQ Option3").sendKeys(Option3);
				//elementFinderByXpath(prop.getProperty("multipleChoiceinstruction_txtbx_Xpath"), "txtbx MCQ instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxMCQOption4(String Option4){
			try {
				//elementFinderByXpath(prop.getProperty("multipleChoice_question_txtbx_Xpath"), "txtbx MCQ question").clear();
				elementFinderByXpath(prop.getProperty("multipleChoice_option4_txtbx_Xpath"), "txtbx MCQ Option4").sendKeys(Option4);
				//elementFinderByXpath(prop.getProperty("multipleChoiceinstruction_txtbx_Xpath"), "txtbx MCQ instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void chkbxcorrectoption(){
			try {
				elementFinderByXpath(prop.getProperty("multipleChoice_correctoption_chkbx_Xpath"), "chkbx correct option").click();
				
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
	//Slideshow
		public static void cmpSlideshow(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("slideshow_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxSlideshowTitle(String Slideshowtitle){
			try {
				elementFinderByXpath(prop.getProperty("slideshowtitle_txtbx_Xpath"), "txtbx shideshow title").sendKeys(Slideshowtitle);
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnSlideshowUploadImage(){
			try {
				elementFinderByXpath(prop.getProperty("uploadimage_area_xpath"), "btn Slideshow Upload Image").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxSlideshowSubTitle(String subtitle){
			try {
				elementFinderByXpath(prop.getProperty("slideshow_subtitle_txtbx_Xpath "), "txtbx shideshow subtitle").clear();
				elementFinderByXpath(prop.getProperty("slideshow_subtitle_txtbx_Xpath"), "txtbx shideshow subtitle").sendKeys(subtitle);
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxSlideshowSlideTitle(String slidetitle){
			try {
				//elementFinderByXpath(prop.getProperty("slideshow_slidetitle_txtbx_Xpath "), "txtbx shideshow slidetitle").clear();
				elementFinderByXpath(prop.getProperty("slideshow_slidetitle_txtbx_Xpath"), "txtbx shideshow slidetitle").sendKeys(slidetitle);
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxSlideshowCaption(String Caption){
			try {
				elementFinderByXpath(prop.getProperty("captionslideshow_txtbx_Xpath"), "txtbx shideshow Caption").clear();
				elementFinderByXpath(prop.getProperty("captionslideshow_txtbx_Xpath"), "txtbx shideshow Caption").sendKeys(Caption);
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxSlideshowPlaceholder(String placeholder){
			try {
				elementFinderByXpath(prop.getProperty("slideshow_placeholder_txtbx_Xpath"), "txtbx shideshow placeholder").clear();
				elementFinderByXpath(prop.getProperty("slideshow_placeholder_txtbx_Xpath"), "txtbx shideshow placeholder").sendKeys(placeholder);
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnSlideshowNextSlide(){
			try {
				elementFinderByXpath(prop.getProperty("slideshow_nextslide_Xpath"), "btn Slideshow next slide").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void chkbxSlideshowUploadVideo(){
			try {
				elementFinderByXpath(prop.getProperty("uploadvideo2_chkbx_Xpath"), "chkbx Upload video Slideshow").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnSlideshowUploadVideo2(){
			try {
				elementFinderByID(prop.getProperty("uploadvideo2_area_ID"), "btn Upload video2 Slideshow").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void chkbxSlideshowAddText(){
			try {
				elementFinderByXpath(prop.getProperty("uploadtext3_chkbx_Xpath"), "chkbx add text Slideshow").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxAddText(String text){
			try {
				elementFinderByXpath(prop.getProperty("uploadtext3_area_Xpath"), "txtbx add text Slideshow").clear();
				elementFinderByXpath(prop.getProperty("uploadtext3_area_Xpath"), "txtbx add text Slideshow").sendKeys(text);
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		
		
	//Image Labelling
		public static void cmpImagelabelling(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("Imagelabelling_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxImagelabellingTitle(String labellingtitle){
			try {
				//elementFinderByXpath(prop.getProperty("Imagelabellingtitle_txtbx_Xpath "), "txtbx Imagelabelling title").clear();
				elementFinderByXpath(prop.getProperty("Imagelabellingtitle_txtbx_Xpath "), "txtbx Imagelabelling title").sendKeys(labellingtitle);
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxImagelabellingInstruction(String Instruction){
			try {
				//elementFinderByXpath(prop.getProperty("ImagelabellingInstruction_txtbx_Xpath "), "txtbx Imagelabelling Instruction").clear();
				elementFinderByXpath(prop.getProperty("ImagelabellingInstruction_txtbx_Xpath "), "txtbx Imagelabelling Instruction").sendKeys(Instruction);
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnImagelabellingUploadImage(){
			try {
				elementFinderByXpath(prop.getProperty("uploadimage_area_Xpath"), "txtbx Imagelabelling Upload Image").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnImagelabellingAddLabel(){
			try {
				elementFinderByXpath(prop.getProperty("Imagelabelling_addlabel_btn_Xpath"), "txtbx Imagelabelling Add label").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnImagelabellingAddLabel1(){
			try {
				  WebElement element = elementFinderByXpath(prop.getProperty("Imagelabelling_addlabel1_btn_Xpath"), "txtbx Imagelabelling Add label");
				   Actions action = new Actions(Driver.driver);
				    action.moveToElement(element, 700, 512).doubleClick().perform();
				    System.out.println("Add Label 1");
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnImagelabellingLabel1(){
			try {
				elementFinderByXpath(prop.getProperty("Imagelabelling_label1_txt_Xpath"), "btn Imagelabelling label1").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxImagelabellingTextArea1(String Label1){
			try {
				elementFinderByXpath(prop.getProperty("Imagelabelling_textarea1_txt_Xpath"), "txtbx Imagelabelling TextArea1").sendKeys(Label1);;
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnImagelabellingCircle(){
			try {
				elementFinderByXpath(prop.getProperty("Imagelabelling_circle_btn_Xpath"), "btn Imagelabelling Circle").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnImagelabellingAddLabel2(){
			try {
				  WebElement element = elementFinderByXpath(prop.getProperty("Imagelabelling_addlabel2_btn_Xpath"), "txtbx Imagelabelling Add label2");
				  Actions action = new Actions(Driver.driver);
				  action.moveToElement(element, 400, 100).doubleClick().perform();
				    System.out.println("Add Label 2");
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnImagelabellingLabel2(){
			try {
				elementFinderByXpath(prop.getProperty("Imagelabelling_label2_btn_Xpath"), "btn Imagelabelling label1").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxImagelabellingTextArea2(String Label2){
			try {
				elementFinderByXpath(prop.getProperty("Imagelabelling_textarea2_txt_Xpath"), "txtbx Imagelabelling TextArea1").sendKeys(Label2);;
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxImagelabellingCaption(String Caption){
			try {
				elementFinderByXpath(prop.getProperty("Imagelabelling_caption_txtbx_Xpath "), "txtbx Imagelabelling Caption").click();
				elementFinderByXpath(prop.getProperty("Imagelabelling_caption_txtbx_Xpath "), "txtbx Imagelabelling Caption").clear();
				elementFinderByXpath(prop.getProperty("Imagelabelling_caption_txtbx_Xpath "), "txtbx Imagelabelling Caption").sendKeys(Caption);
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
	//Fill in the blanks
		public static void cmpFillintheBlanks(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("fillintheBlank_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxFillintheBlanksHeader(String FillintheBlanksheader){
			try {
				//elementFinderByXpath(prop.getProperty("fillintheBlank_header_btn_Xpath"), "txtbx fillintheBlank header").clear();
				elementFinderByXpath(prop.getProperty("fillintheBlank_header_btn_Xpath"), "txtbx fillintheBlank header").sendKeys(FillintheBlanksheader);
				elementFinderByXpath(prop.getProperty("fillintheBlank_header_btn_Xpath"), "txtbx fillintheBlank header").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxFillintheBlanksInstruction(String FillintheBlanksinstruction){
			try {
				//elementFinderByXpath(prop.getProperty("fillintheBlank_header_btn_Xpath"), "txtbx fillintheBlank header").clear();
				elementFinderByXpath(prop.getProperty("fillintheBlank_instruction_btn_Xpath"), "txtbx fillintheBlank instruction").sendKeys(FillintheBlanksinstruction);
				elementFinderByXpath(prop.getProperty("fillintheBlank_instruction_btn_Xpath"), "txtbx fillintheBlank instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxFillintheBlankSentence(String FillintheBlankssentence){
			try {
				//elementFinderByXpath(prop.getProperty("fillintheBlank_header_btn_Xpath"), "txtbx fillintheBlank header").clear();
				elementFinderByXpath(prop.getProperty("fillintheBlank_sentence_txt_Xpath"), "txtbx fillintheBlank sentence").sendKeys(FillintheBlankssentence);
				elementFinderByXpath(prop.getProperty("fillintheBlank_sentence_txt_Xpath"), "txtbx fillintheBlank sentence").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnfillintheBlankInsertBlank(){
			try {
				elementFinderByXpath(prop.getProperty("fillintheBlank_insert_blank_btn_Xpath"), "btn fillintheBlank InsertBlank").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnselectAnswerDrpdwn(){
			try {
				elementFinderByXpath(prop.getProperty("fillintheBlank_dropdown_btn_Xpath"), "btn fillintheBlank Select answer dropdown").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnAddOption(){
			try {
				elementFinderByXpath(prop.getProperty("fillintheBlank_addoption_btn_Xpath"), "btn fillintheBlank Add option").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxFillintheBlankOption1(String FillintheBlanksOption1){
			try {
				//elementFinderByXpath(prop.getProperty("fillintheBlank_header_btn_Xpath"), "txtbx fillintheBlank header").clear();
				elementFinderByXpath(prop.getProperty("fillintheBlank_option1_btn_Xpath"), "txtbx fillintheBlank option 1").sendKeys(FillintheBlanksOption1);
				//elementFinderByXpath(prop.getProperty("fillintheBlank_sentence_txt_Xpath"), "txtbx fillintheBlank sentence").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxFillintheBlankOption2(String FillintheBlanksOption2){
			try {
				//elementFinderByXpath(prop.getProperty("fillintheBlank_header_btn_Xpath"), "txtbx fillintheBlank header").clear();
				elementFinderByXpath(prop.getProperty("fillintheBlank_option2_btn_Xpath"), "txtbx fillintheBlank option 2").sendKeys(FillintheBlanksOption2);
				//elementFinderByXpath(prop.getProperty("fillintheBlank_sentence_txt_Xpath"), "txtbx fillintheBlank sentence").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxFillintheBlankOption3(String FillintheBlanksOption3){
			try {
				//elementFinderByXpath(prop.getProperty("fillintheBlank_header_btn_Xpath"), "txtbx fillintheBlank header").clear();
				elementFinderByXpath(prop.getProperty("fillintheBlank_option3_btn_Xpath"), "txtbx fillintheBlank option 3").sendKeys(FillintheBlanksOption3);
				//elementFinderByXpath(prop.getProperty("fillintheBlank_sentence_txt_Xpath"), "txtbx fillintheBlank sentence").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbxFillintheBlankradio1(){
			try {
				elementFinderByXpath(prop.getProperty("fillintheBlank_radio1_rad_Xpath"), "txtbx fillintheBlank radio 1").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
	//Highlighter
		public static void cmpHighlighter(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("highlighter_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_introduction(String introduction){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_title").clear();
				elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_introduction").sendKeys(introduction);
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_introduction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_instruction(String instruction){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").sendKeys(instruction);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_1statement(String statement){
			try {
				elementFinderByXpath(prop.getProperty("highlighter_1statement_btn_Xpath"), "txtbx_instruction").click();
				elementFinderByXpath(prop.getProperty("highlighter_1statement_btn_Xpath"), "txtbx_instruction").sendKeys(statement);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void doubleClickAWord(){
			try {
				Actions action = new Actions(Driver.driver);
				action.moveToElement(Driver.driver.findElement(By.xpath(prop.getProperty("highlighter_1statement_btn_Xpath")))).click().perform();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnhighlight(){
			try {
				elementFinderByXpath(prop.getProperty("highlighter_myHighLight_btn_Xpath"), "btn highlight").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void highlightelement(){
			try {
				   WebElement element = Driver.driver.findElement(By.xpath(prop.getProperty("highlighter_myHighLight_btn_Xpath")));
				   JavascriptExecutor js = (JavascriptExecutor) Driver.driver;
		      	   js.executeScript("arguments[0].setAttribute('style', 'visibility: block;')",element);
		      	   Thread.sleep(500);
		      	   System.out.println("highlight  element");
				//elementFinderByXpath(prop.getProperty("highlighter_myHighLight_btn_Xpath"), "btn highlight").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
	//correction
		public static void cmpCorrection(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("correction_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_correctionHeader(String correctionheader){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_title").clear();
				elementFinderByXpath(prop.getProperty("correction_header_xpath"), "txtbx_header").sendKeys(correctionheader);
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_introduction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_correctionInstruction(String instruction){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("correction_instruction_xpath"), "txtbx_instruction").sendKeys(instruction);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_statement(String statement){
			try {
				elementFinderByXpath(prop.getProperty("correction_1statement_btn_Xpath"), "txtbx_statement").click();
				elementFinderByXpath(prop.getProperty("correction_1statement_btn_Xpath"), "txtbx_statement").sendKeys(statement);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void Strikeelement(){
			try {
				   WebElement element = Driver.driver.findElement(By.xpath(prop.getProperty("correctionstrikethrough_btn_Xpath")));
				   JavascriptExecutor js = (JavascriptExecutor) Driver.driver;
		      	   js.executeScript("arguments[0].setAttribute('style', 'visibility: block;')",element);
		      	   Thread.sleep(500);
		      	   System.out.println("Strike element");
				//elementFinderByXpath(prop.getProperty("highlighter_myHighLight_btn_Xpath"), "btn highlight").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnstrikethrough(){
			try {
				elementFinderByXpath(prop.getProperty("correctionstrikethrough_btn_Xpath"), "btn strikethrough").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_correctanswer(String correctanswer){
			try {
				elementFinderByXpath(prop.getProperty("correction_1stanswer_txtbx_Xpath"), "txtbx_correctanswer").click();
				elementFinderByXpath(prop.getProperty("correction_1stanswer_txtbx_Xpath"), "txtbx_correctanswer").sendKeys(correctanswer);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
//sorting component
		public static void cmpSorting(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("Sorting_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_sortingHeader(String sortingheader){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_title").clear();
				elementFinderByXpath(prop.getProperty("Sortingheader_txtbx_Xpath"), "txtbx_header").sendKeys(sortingheader);
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_introduction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_sortingInstruction(String instruction){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("Sortinginstruction_txtbx_Xpath"), "txtbx_instruction").sendKeys(instruction);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_sortingstatement(String statement){
			try {
				elementFinderByXpath(prop.getProperty("Sorting_Description_txt_Xpath"), "txtbx_statement").click();
				elementFinderByXpath(prop.getProperty("Sorting_Description_txt_Xpath"), "txtbx_statement").sendKeys(statement);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btninsertSortItem(){
			try {
				elementFinderByXpath(prop.getProperty("Sorting_sortitem_btn_Xpath"), "btn insertSortItem").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_sortanswer(String sortanswer){
			try {
				elementFinderByXpath(prop.getProperty("Sorting_sortanswer_txtbx_Xpath"), "txtbx_sortanswer").click();
				elementFinderByXpath(prop.getProperty("Sorting_sortanswer_txtbx_Xpath"), "txtbx_sortanswer").sendKeys(sortanswer);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
	
// Match the Pairs
		public static void cmpmatchthePairs(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("matchthePairs_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_mtpHeader(String mtpheader){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_title").clear();
				elementFinderByXpath(prop.getProperty("mtpheader_txtbx_Xpath"), "txtbx_header").sendKeys(mtpheader);
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_introduction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_mtpInstruction(String instruction){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("mtpinstruction_txtbx_Xpat"), "txtbx_instruction").sendKeys(instruction);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_mtpElement1(String Element1){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("element1_txtbx_Xpath"), "txtbx_Element1").sendKeys(Element1);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_mtpElement2(String Element2){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("element2_txtbx_Xpath"), "txtbx_Element2").sendKeys(Element2);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnmtpAddPairs(){
			try {
				elementFinderByXpath(prop.getProperty("addpairs_btn_Xpath"), "btn mtp add pairs").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_mtpQuestion1(String Question1){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("question1_txtbx_Xpath"), "txtbx_Question1").sendKeys(Question1);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_mtpAnswer1(String Answer1){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("answer1_txtbx_Xpath"), "txtbx_Answer1").sendKeys(Answer1);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_mtpQuestion2(String Question2){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("question2_txtbx_Xpath"), "txtbx_Question2").sendKeys(Question2);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_mtpAnswer2(String Answer2){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("answer2_txtbx_Xpath"), "txtbx_Answer2").sendKeys(Answer2);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_mtpQuestion3(String Question3){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("question3_txtbx_Xpath"), "txtbx_Question3").sendKeys(Question3);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_mtpAnswer3(String Answer3){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("answer3_txtbx_Xpath"), "txtbx_Answer3").sendKeys(Answer3);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_mtpQuestion4(String Question4){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("question4_txtbx_Xpath"), "txtbx_Question4").sendKeys(Question4);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_mtpAnswer4(String Answer4){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("answer4_txtbx_Xpath"), "txtbx_Answer4").sendKeys(Answer4);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
// Sidebar Component
		public static void cmpsidebar(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("sidebar_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_sidebarTitle(String sidebartitle){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_title").clear();
				elementFinderByXpath(prop.getProperty("sidebartitle_txtbx_Xpath"), "txtbx_sidebartitle").sendKeys(sidebartitle);
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_introduction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_sidebarEnterText(String text){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").clear();
				elementFinderByXpath(prop.getProperty("entertext_txtbx_Xpath"), "txtbx_EnterText").sendKeys(text);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
//Click to reveal component
		public static void cmpclicktoreveal(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("clicktoReveal_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_ClicktoRevealHeader(String ClicktoRevealheader){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_title").clear();
				elementFinderByXpath(prop.getProperty("ClicktoRevealheader_txt_Xpath"), "txtbx_ClicktoRevealheader").sendKeys(ClicktoRevealheader);
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_introduction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_ClicktoRevealdescription(String text){
			try {
				elementFinderByXpath(prop.getProperty("ClicktoRevealdescription_txt_Xpath"), "txtbx_EnterText").clear();
				elementFinderByXpath(prop.getProperty("ClicktoRevealdescription_txt_Xpath"), "txtbx_EnterText").sendKeys(text);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_ClicktoRevealButtonLabel(String ButtonLabel){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_title").clear();
				elementFinderByXpath(prop.getProperty("ClicktoRevealButtonLabel_txtbx_xpath"), "txtbx_ButtonLabel").sendKeys(ButtonLabel);
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_introduction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
//WordSearch
		public static void cmpWordSearch(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("wordsearch_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_WordsearchHeader(String wordsearchheader){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_title").clear();
				elementFinderByXpath(prop.getProperty("wordsearchheader_txt_Xpath"), "txtbx_wordsearch header").sendKeys(wordsearchheader);
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_introduction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_Wordsearchdescription(String text){
			try {
			//	elementFinderByXpath(prop.getProperty("wordsearchinstruction_txt_Xpath"), "txtbx_EnterText").clear();
				elementFinderByXpath(prop.getProperty("wordsearchinstruction_txt_Xpath"), "txtbx_EnterText").sendKeys(text);
				//elementFinderByXpath(prop.getProperty("highlighterinstruction_txtbx_Xpath"), "txtbx_instruction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_wordsearchquestion(String wordsearchquestion){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_title").clear();
				elementFinderByXpath(prop.getProperty("wordsearchquestion_txt_Xpath"), "txtbx_ButtonLabel").sendKeys(wordsearchquestion);
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_introduction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_wordsearchhiddenword(String hiddenword){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_title").clear();
				elementFinderByXpath(prop.getProperty("hiddenword_txt_Xpath"), "txtbx_ButtonLabel").sendKeys(hiddenword);
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_introduction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void btnadd(){
			try {
				elementFinderByXpath(prop.getProperty("addbutton_btn_Xpath"), "btn add").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
	
	//Question answer component
		public static void cmpQuestionanswer(){
			try {
				DragAndDrop.dragAndDrop(By.xpath(prop.getProperty("Questionanswer_cmp_Xpath")), By.id(prop.getProperty("dragDrop_ID")));
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void txtbx_EnterQuestion(String EnterQuestion){
			try {
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_title").clear();
				elementFinderByXpath(prop.getProperty("question_txt_Xpath"), "txtbx_EnterQuestion").sendKeys(EnterQuestion);
				//elementFinderByXpath(prop.getProperty("highlighterintroduction_txtbx_Xpath"), "txtbx_introduction").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
		
		public static void rdbtn_longAnswer(){
			try {
				elementFinderByXpath(prop.getProperty("long_rdbtn_Xpath"), "btn longAnswer").click();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
    
		public static void btnProfileIcon(){
			try {
				elementFinderByID(prop.getProperty("profileIcon_id"), "Click on profile icon.").click();
			} catch (Exception e) {
				System.out.println("Element not present. ERROR: "+e.getMessage());
			}
		}
		
		public static void btnLogOut(){
			try {
				elementFinderByID(prop.getProperty("LogOut_id"), "Click on Log out.").click();
			} catch (Exception e) {
				System.out.println("Element not present. ERROR: "+e.getMessage());
			}
		}
	
}
