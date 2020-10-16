package com.hurix.library.kitabooBooks;

import static com.hurix.library.kitabooBooks.KitabooBooksStepModule.prop;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;
import com.hurix.epubbooks.EpubBook.EpubBookStepModule;

public class KitabooBooksStepModule extends UIElements {

	public static Properties prop = getProperty(System.getProperty("user.dir")+"/config/platform/kitabooBooks.properties");

	public static void drpAddNew_BulkConversion(){
		try {
			elementFinderByID(prop.getProperty("addNew_ID"), "addNew").click();
			elementFinderByXpath(prop.getProperty("addNew_BulkConversion_Xpath"), "addNew_BulkConversion").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void drpAddNew_CreateBook(){
		//dsds
		try {
			elementFinderByID(prop.getProperty("addNew_ID"), "addNew").click();
			elementFinderByXpath(prop.getProperty("addNew_CreateNBook_Xpath"), "addNew_CreateNBook").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void drpAddNew_HTMLCreateBook(){
		try {
			elementFinderByID(prop.getProperty("addNew_ID"), "addNew").click();
			elementFinderByXpath(prop.getProperty("addNew_HTMLCreateBook_Xpath"), "addNew_HTMLCreateBook").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void txtISBN(String ISBN){
		try {
			elementFinderByXpath(prop.getProperty("isbn_Xpath"), "txt_isbn").sendKeys(ISBN);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void txtTitle(String Title){
		try {
			elementFinderByID(prop.getProperty("title_ID"), "txt_title").sendKeys(Title);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void txtAuthor(String Author){
		try {
			elementFinderByXpath(prop.getProperty("author_Xpath"), "txt_author").sendKeys(Author);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void drpCategory(String categoryName){
		try {
			selectDropdown(By.id(prop.getProperty("category_drp_ID")), prop.getProperty("category_addNewCategory_VisibleText"), "Add New Category");
			elementFinderByXpath(prop.getProperty("categoryName_Xpath"), "txt_NewCategory").sendKeys(categoryName);
			elementFinderByXpath(prop.getProperty("categorySave_Xpath"), "txt_SaveCategory").click();
			Thread.sleep(500);
			UIElements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("loaderClass_xpath"))));
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void drpLanguageSelection(String language){
		try {
			selectDropdown(By.id(prop.getProperty("language_drp_ID")), language, "Language");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void txtDescription(String Description){
		try {
			elementFinderByXpath(prop.getProperty("description_Xpath"), "txt_description").sendKeys(Description);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void drpBookType(String BookType){
		try {
			selectDropdown(By.id(prop.getProperty("bookType_drp_ID")), BookType, "Book_Type");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void txtkeywords(String keywords){
		try {
			elementFinderByXpath(prop.getProperty("keywords_Xpath"), "txt_keywords").sendKeys(keywords);
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void drpBookOriantation(String BookOriantation){
		try {
			selectDropdown(By.id(prop.getProperty("bookOriantaion_drp_ID")), BookOriantation, "Book_Oriantation");
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void drpdpi(String DPI,String picformat){
		try {
			if(picformat.equalsIgnoreCase("PNG"))
			{
				threadHold_5Sec();
				elementFinderByXpath(prop.getProperty("pngepubbooks_xpath"), "Click on png radio button").click();
			}
			else if(picformat.equalsIgnoreCase("JPEG"))
			{
				threadHold_5Sec();
				elementFinderByXpath(prop.getProperty("jpegepubbooks_xpath"), "Click on png radio button").click();
			}
			selectDropdown(By.id("select_dpi"), DPI, DPI);
		}
		catch (Exception e)
		{
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void linksamplemetadata(){
		try {
			elementFinderByXpath(prop.getProperty("sampledownload_xpath"), "sample metadata file downaload").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void samplefileupload(String metaDataPath)
	{
		WebDriverWait wait = new WebDriverWait(Driver.driver, 900); 
		Driver.driver.findElement(By.id(prop.getProperty("upload_id"))).sendKeys(metaDataPath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("uploadsuccessfully_xpath"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("ok_xpath")))).click();
		threadHold_2Sec();
	}


	public static void GlossaryfileDownload(){
		try {
			elementFinderByXpath(prop.getProperty("downloadglossaryfile_xpath"), "Glossary file downaload").click();
			threadHold_5Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void Glossaryfileupload(String glossaryPath)
	{

		try {
			WebDriverWait wait = new WebDriverWait(Driver.driver, 400); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("clickglossaryfile_xpath","click on upload glossary file button")))).click();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(prop.getProperty("filepicker_dialog_id")));
			threadHold_5Sec();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("uploadglossaryfile_id"))));
			Driver.driver.findElement(By.id(prop.getProperty("uploadglossaryfile_id"))).sendKeys(glossaryPath);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='loader']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='loader']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("glossaryuploadsuccessfully_xpath","file upload succesfully."))));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("ok_xpath","click on ok button")))).click();
		} 
		catch (Exception e)
		{
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	
	
	
	public static void chkFontPermission(boolean fontPermission){
		try {
			
			if(fontPermission)
			{	
				boolean res = Driver.driver.findElement(By.xpath(prop.getProperty("fontpermission_id"))).isSelected();
				if(!res)
				{
				elementFinderByXpath(prop.getProperty("fontpermission_xpath"), "fontpermission checkbox is found").click();
				}
				else
				{
					Log.info("Check of is already check");
				}
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}


	public static void chkshowfolio(boolean showfolio){
		try {
			if(showfolio)
			{
			boolean res = Driver.driver.findElement(By.xpath(prop.getProperty("showfolio_xpath"))).isSelected();
			if(!res)
			{
				elementFinderByXpath(prop.getProperty("showfolio_xpath"), "show folio button is found").click();
			}
			}
		}
			catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnUploadBook(String path,String uploadfile){
		try {
			if(uploadfile.equalsIgnoreCase("Browse"))
			{
				File projectLocation = new File("");
				Driver.driver.findElement(By.xpath(prop.getProperty("uploadFile_Browse_Xpath"))).sendKeys(path);
				UIElements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("loaderClass_xpath"))));
				elementFinderByXpath(prop.getProperty("afterFileUploadPopUp_xpath"), "Ok Button(After Upload Book Popup msg)").click();
				Log.info("Book Sucessfully Uploaded i.e '"+path+"'");
			}
			else if(uploadfile.equalsIgnoreCase("FileStack"))
			{
				WebDriverWait wait = new WebDriverWait(Driver.driver, 400); 
				JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
				WebElement Filestack = elementFinderByXpath(prop.getProperty("uploadFilestack_Browse_Xpath"), "file upload using filestack");
				threadHold_5Sec();
				js.executeScript("arguments[0].scrollIntoView(true)",Filestack);
				threadHold_5Sec();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("uploadFilestack_Browse_Xpath","click on filestack upload file button")))).click();
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(prop.getProperty("filepicker_dialog_id")));
				threadHold_5Sec();
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("uploadglossaryfile_id"))));
				Driver.driver.findElement(By.id(prop.getProperty("uploadglossaryfile_id"))).sendKeys(path);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='loader']")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='loader']")));
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("ok_xpath","click on ok button")))).click();
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void deletebook(){
		try {
			threadHold_2Sec();
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement delete = elementFinderByXpath(prop.getProperty("deletebook_xpath"), "file upload using filestack");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",delete);
			
			int beforesize = Driver.driver.findElements(By.xpath(prop.getProperty("deleteverify_xpath"))).size();
			Log.info("level size before deltele"+beforesize);
			elementFinderByXpath(prop.getProperty("deletebook_xpath"), "book delete successfully.").click();
			threadHold_2Sec();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("delete_xpath","File uploaded successfully."))));
			threadHold_2Sec();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("deletebutton_xpath","Click on delete button.")))).click();
			threadHold_2Sec();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("filedelete_xpath","File delete successfully."))));
			threadHold_2Sec();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("ok_xpath","click on ok button")))).click();
			threadHold_2Sec();
			int aftersize = Driver.driver.findElements(By.xpath(prop.getProperty("deleteverify_xpath"))).size();
			Log.info("level size before deltele"+aftersize);
			if(beforesize > aftersize)
			{
				Log.info("PDf or DOC delete successfully");
			}
			else
			{
				Log.fail("PDf or DOC is not deleted");
			}
			}
		catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
	
	
	public static void rdbDefaultImage(String uploadcover,String imagequality,String imagepath){
		try {
			WebDriverWait wait = new WebDriverWait(Driver.driver, 400); 
			threadHold_2Sec();
			if(uploadcover.equalsIgnoreCase("Default"))
			{
			elementFinderByXpath(prop.getProperty("defaultImage_Xpath"), "rdb_Default").click();
			threadHold_5Sec();
			}
			else if(uploadcover.equalsIgnoreCase("Image"))
			{				
				if(imagequality.equalsIgnoreCase("None"))
				{
					elementFinderByXpath(prop.getProperty("coverimageepubbooks"), "Click on cover image radio button").click();
					Driver.driver.findElement(By.xpath(prop.getProperty("coveruploadepubbooks_xpath",""))).sendKeys(imagepath);
					Log.info("image upload in progress");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("coverupoadsuccess_xpath","cover image uploaded sucessfully"))));
					Log.info("image upload successfully");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("okepubbooks_xpath","Click on ok button")))).click();
					Log.info("Click on ok button");
				}
				else if(imagequality.equalsIgnoreCase("Low Quality"))
				{
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("image_xpath","Click on image radio button")))).click();
					threadHold_2Sec();
					Driver.driver.findElement(By.xpath(prop.getProperty("coverimagelow_xpath",""))).sendKeys(imagepath);
					Log.info("image upload in progress");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("coverupoadsuccess_xpath","cover image uploaded sucessfully"))));
					Log.info("image upload successfully");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("ok_xpath","Click on ok button")))).click();
					Log.info("Click on ok button");
					btnFinish();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("coverimageverify_xpath","please Upload High quality Cover Image."))));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("ok_xpath","Click on ok button")))).click();
					Log.info("Click on ok button");
								
				}
				
				else if(imagequality.equalsIgnoreCase("High Quality"))
				{
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("image_xpath","Click on image radio button")))).click();
					threadHold_2Sec();
					Driver.driver.findElement(By.xpath(prop.getProperty("coverimagehigh_xpath",""))).sendKeys(imagepath);
					Log.info("image upload in progress");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("coverupoadsuccess_xpath","cover image uploaded sucessfully"))));
					Log.info("image upload successfully");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("ok_xpath","Click on ok button")))).click();
					Log.info("Click on ok button");
				}
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}                                                                                                                                                                                                              
	}

	public static void btnsavedraft(){
		try {
			elementFinderByXpath(prop.getProperty("saveasdraft_xpath"), "Click on savedraft button").click();
			UIElements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("loaderClass_xpath"))));
			UIElements.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("ebooksave_xpath"))));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("ok_xpath","Click on ok button")))).click();
			Log.info("Click on ok button");
			threadHold_5Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btncancel(){
		try {
			elementFinderByXpath(prop.getProperty("cancel_xpath"), "Click on cancel button").click();
			threadHold_5Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void btnresume(){
		try {
			elementFinderByXpath(prop.getProperty("resume_xpath"), "Click on resume button").click();
			UIElements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("loaderClass_xpath"))));
			threadHold_2Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	
	public static void btnFinish(){
		try {
			
			elementFinderByXpath(prop.getProperty("finish_xpath"), "btn_Finish").click();
			//elementFinderByXpath(prop.getProperty("bookCreation_FinishBtn_Xpath"), "btn_Finish").click();
			UIElements.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("loaderClass_xpath"))));
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void afterFinishBtnContinue(){
		try {
			elementFinderByXpath(prop.getProperty("afterFinishBtnContinue_xpath"), "btn_Continue").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnPublish(){
		try {
			UIElements.waitTiming = 240;
			elementFinderByID(prop.getProperty("publishBtn_ID"), "btn_Continue").click();
			elementFinderByID("kitabooFormatGrid:0:kitabooformatid", "Ipad").click();
			elementFinderByID("kitabooFormatGrid:2:kitabooformatid", "HTML").click();
			elementFinderByID("kitabooFormatGrid:1:kitabooformatid", "Android").click();
			elementFinderByID("kitabooFormatGrid:3:kitabooformatid", "Windows").click();
			elementFinderByID("kitabooFormatGrid:4:kitabooformatid", "Offline HTML").click();
			elementFinderByID("updateID", "btnPublish").click();
			UIElements.waitTiming = 60;
		} catch (Exception e) {
			UIElements.waitTiming = 60;
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnArchived(){
		try {
			UIElements.waitTiming = 240;
			threadHold_2Sec();
			int failsize = Driver.driver.findElements(By.xpath(prop.getProperty("publishfail_xpath"))).size();
			if(failsize > 0)
			{
				btnPublish();
			}
			failsize = Driver.driver.findElements(By.xpath(prop.getProperty("publishfail_xpath"))).size();
			if(failsize > 0)
			{
				elementFinderByID(prop.getProperty("publishBtn_ID"), "btn_Continue").click();
				threadHold_2Sec();
				for(int i=1;i<=3;i++)
				{
					for(int j=1;j<=2;j++)
					{
						int formatsize=Driver.driver.findElements(By.xpath("//*[@id='kitabooFormatGrid_content']/table/tbody/tr["+i+"]/td["+j+"]/img")).size();
						System.out.println("//*[@id='kitabooFormatGrid_content']/table/tbody/tr["+i+"]/td["+j+"]/img");
						if(formatsize >= 1 )
						{
							if(i==1 && j==1)
							{
								Log.fail("IOS fail while publishing");
							}
							else if(i==1 && j==2)
							{
								Log.fail("Android fail while publishing");
							}
							else if(i==2 && j==1)
							{
								Log.fail("HTML5 fail while publishing");
							}
							else if(i==2 && j==2)
							{
								Log.fail("Windows fail while publishing");
							}	
							else if(i==3 && j==1)
							{
								Log.fail("Offline HTML5 fail while publishing");
							}
						}
					}
				}
				elementFinderByID("updateID", "btnPublish").click();
			}
			threadHold_2Sec();
			elementFinderByID(prop.getProperty("archiveBtn_ID"), "btnArchived").click();
			elementFinderByXpath(prop.getProperty("Yes_xpath"), "yes button is click").click();
			UIElements.waitTiming = 60;
		} catch (Exception e) {
			UIElements.waitTiming = 60;
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void txtSearch(String title){
		try {
			elementFinderByID(prop.getProperty("search_id"), "txtSearchTitle").sendKeys(title);
			btnSearch();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}

	public static void btnSearch(){
		try {
			elementFinderByID(prop.getProperty("searchbtn_id"), "btnSearch").click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	
	public static void isbnverify(String isbn){
		try {
			String result = elementFinderByXpath(prop.getProperty("isbn_xpath"), "verify book isbn").getText().replace("ISBN: ", "");
			System.out.println("result"+result);
			if(result.equalsIgnoreCase(isbn))
			{
				Log.info("isbn verification pass");
			}
			else
			{
				Log.fail("isbn verification fail");
			}
			threadHold_5Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
		public static void bookname(String title){
		try {
			String result = elementFinderByXpath(prop.getProperty("bookname_xpath"), "verify book title").getText();
			System.out.println("result"+result);
			if(result.equalsIgnoreCase(title))
			{
				Log.info("Title verification pass");
			}
			else
			{
				Log.fail("Title verification fail");
			}
			threadHold_5Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
		
		public static void date(){
			try {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
				LocalDateTime now = LocalDateTime.now();  
				String currentdate = dtf.format(now);
				System.out.println(currentdate);  
				String result = elementFinderByXpath(prop.getProperty("date_xpath"), "verify book creation date").getText();
				System.out.println("result"+result);
				if(result.equalsIgnoreCase(currentdate))
				{
					Log.info("date verification pass");
				}
				else
				{
					Log.fail("date verification fail");
				}
				threadHold_5Sec();
			} catch (Exception e) {
				System.out.println("Element not present."+e.getMessage());
			}
		}
	
	
	
	
	public static void SampleTOC(){
		try {
			elementFinderByXpath(prop.getProperty("SampleTOC_xpath"), "Click on sample TOC").click();
			threadHold_5Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	public static void searchFilteruncheck(){
		try {
				
				elementFinderByID(prop.getProperty("searchFilterImg_id"), "Click on search filter button").click();
				boolean result = elementFinderByXpath(prop.getProperty("ISBN_xpath"), "Click on ISBN checkbox").isSelected();
				if(result == true)
				{
					elementFinderByXpath(prop.getProperty("ISBN_xpath"), "Click on ISBN checkbox").click();
						
				}
				result = elementFinderByXpath(prop.getProperty("producer_xpath"), "Click on producer checkbox").isSelected();
				if(result == true)
				{
					elementFinderByXpath(prop.getProperty("producer_xpath"), "Click on producer checkbox").click();
						
				}
				result = elementFinderByXpath(prop.getProperty("refid_xpath"), "Click on refid checkbox").isSelected();
				if(result == true)
				{
					elementFinderByXpath(prop.getProperty("refid_xpath"), "Click on refid checkbox").click();
				}
				
		elementFinderByID(prop.getProperty("searchFilterImg_id"), "Click on search filter button").click();
			threadHold_5Sec();
			
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	
		
	public static void searchbytitle(){
		try {
				elementFinderByID(prop.getProperty("searchFilterImg_id"), "Click on search filter button").click();
				boolean result = elementFinderByXpath(prop.getProperty("producer_xpath"), "Click on title checkbox").isSelected();
				if(result == false)
				{
					elementFinderByXpath(prop.getProperty("producer_xpath"), "Click on title checkbox").click();
				}
				elementFinderByID(prop.getProperty("searchFilterImg_id"), "Click on search filter button").click();
				threadHold_5Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void searchbyISBN(){
		try {
				elementFinderByID(prop.getProperty("searchFilterImg_id"), "Click on search filter button").click();
				boolean result = elementFinderByXpath(prop.getProperty("ISBN_xpath"), "Click on ISBN checkbox").isSelected();
				if(result == false)
				{
					elementFinderByXpath(prop.getProperty("ISBN_xpath"), "Click on ISBN checkbox").click();
				}
				elementFinderByID(prop.getProperty("searchFilterImg_id"), "Click on search filter button").click();
				threadHold_5Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void searchbyProducer(){
		try {
				elementFinderByID(prop.getProperty("searchFilterImg_id"), "Click on search filter button").click();
				boolean result = elementFinderByXpath(prop.getProperty("producer_xpath"), "Click on producer checkbox").isSelected();
				if(result == false)
				{
					elementFinderByXpath(prop.getProperty("producer_xpath"), "Click on producer checkbox").click();
					
				}
				elementFinderByID(prop.getProperty("searchFilterImg_id"), "Click on search filter button").click();
				threadHold_5Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	public static void searchbyRefid(){
		try {
				
				elementFinderByID(prop.getProperty("searchFilterImg_id"), "Click on search filter button").click();
				boolean result = elementFinderByXpath(prop.getProperty("refid_xpath"), "Click on refid checkbox").isSelected();
				if(result == false)
					{
					elementFinderByXpath(prop.getProperty("refid_xpath"), "Click on refid checkbox").click();
					}
				elementFinderByID(prop.getProperty("searchFilterImg_id"), "Click on search filter button").click();
			threadHold_5Sec();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
			
	public static void UploadTOC(String UploadTOC){
		try
		{
			threadHold_2Sec();
			WebDriverWait wait = new WebDriverWait(Driver.driver, 400); 
			Driver.driver.findElement(By.id(prop.getProperty("UploadTOC_id"))).sendKeys(UploadTOC);
			Log.info("uploadTOC is in progress");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("UploadTOCsuccessful_xpath","Click on upload TOC."))));
			threadHold_2Sec();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("ok_xpath","Click on ok button")))).click();
			Log.info("Click on ok button");
		}
		 catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}                                                                                                                                                                                                              
	}
			
	public static void DownloadTOC(){
		try 
		{
			elementFinderByXpath(prop.getProperty("DownloadTOC_xpath"), "Click on Download TOC").click();
			threadHold_2Sec();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("DownloadTOCsuccessful_xpath","Click on ok button"))));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("ok_xpath")))).click();
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
	
	public static void GenerateFolio(){
		try 
		{
			threadHold_2Sec();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("GenerateFolio_xpath","Click on generatefolio button")))).click();
		}
		catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}		

	public static void Deletelevel(){
		try 
		{	
			threadHold_2Sec();
			int beforesize = Driver.driver.findElements(By.xpath(prop.getProperty("lastrow_xpath"))).size();
			Log.info("level size before deltele"+beforesize);
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement Deletelevel = elementFinderByXpath(prop.getProperty("Deletelevel_xpath"), "file upload using filestack");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",Deletelevel);
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("Deletelevel_xpath"), "Click on delete level button").click();
			threadHold_5Sec();
			int aftersize = Driver.driver.findElements(By.xpath(prop.getProperty("lastrow_xpath"))).size();
			if(beforesize > aftersize)
			{
				Log.info("Delete level functionality is working fine");
			}
			else
			{
				Log.error("Delete level functionality is not working");
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Element not present."+e.getMessage());
		}
	}			

	public static void DeleteTOC(){
		try 
			{
			threadHold_2Sec();
			int beforesize = Driver.driver.findElements(By.xpath(prop.getProperty("lastrow_xpath"))).size();
			Log.info("level size before deltele"+beforesize);
			elementFinderByXpath(prop.getProperty("DeleteTOC_xpath"), "Click on DeleteTOC button").click();
			int aftersize = Driver.driver.findElements(By.xpath(prop.getProperty("lastrow_xpath"))).size();
			
			if(aftersize == 1)
			{
				Log.info("Delete level functionality is working fine");
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}			
			
	public static void DeletePageNo(){
		try 
		{
			threadHold_2Sec();
			/*String beforetext = elementFinderByXpath(prop.getProperty("DetelePageNodata_xpath"), "Data get before delete page number").getText();
			Log.info(beforetext);*/
			elementFinderByXpath(prop.getProperty("DetelePageNo_xpath"), "Click on DetelePageNo button").click();
			/*String aftertext = elementFinderByXpath(prop.getProperty("DetelePageNodata_xpath"), "Data get after delete page number").getText();
			if(!beforetext.equalsIgnoreCase(aftertext))
			{
				Log.info("Delete PageNo functionality is working fine");
			}*/
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}	

	public static void DeleteFolio(){
		try 
		{
			threadHold_2Sec();
			/*String beforetext = elementFinderByXpath(prop.getProperty("DeleteFolioData_xpath"), "Data get before delete folio number").getText();
			Log.info(""+beforetext);*/
			JavascriptExecutor js = ( JavascriptExecutor)Driver.driver;
			WebElement DeleteFolio = elementFinderByXpath(prop.getProperty("DeleteFolio_xpath"), "file upload using filestack");
			threadHold_2Sec();
			js.executeScript("arguments[0].scrollIntoView(true)",DeleteFolio);
			threadHold_2Sec();
			elementFinderByXpath(prop.getProperty("DeleteFolio_xpath"), "Click on DeleteFolio button").click();
			/*String aftertext = elementFinderByXpath(prop.getProperty("DeleteFolioData_xpath"), "Data get after delete folio number").getText();
			if(!beforetext.equalsIgnoreCase(aftertext))
			{
				Log.info("DeleteFolio functionality is working fine");
			}*/
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}		
		
	public static void PagePosition(){
		try 
		{
			threadHold_2Sec();
			Select beforetext1 = new Select (elementFinderByXpath(prop.getProperty("PagePositionData_xpath"), "Data get before pagepossion dropdown"));
			String beforetext = beforetext1.getFirstSelectedOption().getText();
			Log.info(""+beforetext);
			elementFinderByXpath(prop.getProperty("PagePosition_xpath"), "Click on PagePosition button").click();
			threadHold_2Sec();
			Select aftertext1 = new Select (elementFinderByXpath(prop.getProperty("PagePositionData_xpath"), "Data get before pagepossion dropdown"));
			String aftertext = aftertext1.getFirstSelectedOption().getText();
			Log.info(""+aftertext1);
			if(!beforetext.equalsIgnoreCase(aftertext))
			{
				Log.info("PagePosition delete functionality is working fine");
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}		
		
	public static void ChapterNumber(){
		try 
		{
			/*elementFinderByXpath(prop.getProperty("ChapterNumberdata_xpath"), "Data get from chapternumber").getText();
			String beforetext = elementFinderByXpath(prop.getProperty("ChapterNumberdata_xpath"), "Data get from chapternumber").getText();
			Log.info(beforetext);*/
			elementFinderByXpath(prop.getProperty("ChapterNumber_xpath"), "Click on ChapterNumber button").click();
			/*String aftertext = elementFinderByXpath(prop.getProperty("ChapterNumberdata_xpath"), "Click on ChapterNumber button").getText();
			if(!beforetext.equalsIgnoreCase(aftertext))
			{
				Log.info("chapter delete functionality is working fine");
			}*/
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
		
	public static void refresh(){
		try 
		{
			elementFinderByXpath(prop.getProperty("refresh_xpath"), "Click on refresh button").click();
			int aftersize = Driver.driver.findElements(By.xpath(prop.getProperty("lastrow_xpath"))).size();
			if(aftersize == 10)
			{
				Log.info("refresh functionality is working fine");
			}
		} catch (Exception e) {
			System.out.println("Element not present."+e.getMessage());
		}
	}
}
