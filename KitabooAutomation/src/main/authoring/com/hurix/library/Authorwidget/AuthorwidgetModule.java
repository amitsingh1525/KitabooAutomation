package com.hurix.library.Authorwidget;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hurix.automation.utility.ClearFile;
import com.hurix.automation.utility.DragAndDrop;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.SelectWord;
import com.hurix.automation.utility.UIElements;
import com.hurix.automation.utility.Uploadpath;
import com.hurix.reader.bookPlayer.BookPlayerModule;

public class AuthorwidgetModule extends AuthorwidgetStepModule {
	
	public static void Components(String widgetName,String category,String tag,String courseDescription, 
			String title, String author, String header, String text,String imageheader,String caption,
			String uploadimage,String videoheader,String uploadvideo, String uploadaudio, String uploadzip,
			String NoOfRows,String NoOfColumns,String tableheader, String staticSidebarheader,String MCQheader,String MCQinstruction,
			String question,String Option1,String Option2,String Option3,String Option4,String Slideshowtitle,String subtitle,String slidetitle,String placeholder,String uploadimage2,
			String labellingtitle,String labellingInstruction,String Label1,String Label2,String FillintheBlanksheader,String FillintheBlanksinstruction,String FillintheBlankssentence,String FillintheBlanksOption1,
			String FillintheBlanksOption2,String FillintheBlanksOption3,String introduction,String instruction,String statement,
			String correctionheader,String correctanswer,String sortingheader,String sortanswer,String mtpheader,String Element1,String Element2,String Question1,String Answer1,String Question2,
			String Answer2,String Question3,String Answer3,String Question4,String Answer4,String sidebartitle,String ClicktoRevealheader,String ButtonLabel,
			String wordsearchheader,String wordsearchquestion,String hiddenword,String EnterQuestion, String Format,String language,String theme) throws Exception {
		
		
		linkActivites();
		Driver.driver.navigate().refresh(); 
		WebDriverWait wait = new WebDriverWait(Driver.driver,60); 
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeBody"));
		//Driver.driver.switchTo().frame("iframeBody");
		System.out.println("Frame switched");
		createAuthorWidget(widgetName,category,tag,courseDescription);
		
		//New Window Handle	
		Thread.sleep(5000);
		String winHandleBefore = Driver.driver.getWindowHandle();
		for(String winHandle : Driver.driver.getWindowHandles()){
			Driver.driver.switchTo().window(winHandle);
		}
		Log.info("Handle the new window");
		Driver.driver.manage().window().maximize();
		Thread.sleep(5000);
		Thread.sleep(5000);
		
		titleComponent(title,author);
		headerComponent(header);
		textComponent(text);
		imageComponent(imageheader,caption,uploadimage);
		videoComponent(videoheader,caption,uploadvideo);
		audioComponent(caption, uploadaudio);
		htmlInteractivityComponent(caption, uploadzip);
	    tableComponent(NoOfRows,NoOfColumns,tableheader,caption);
		staticSidebarComponent(staticSidebarheader,text,uploadimage,caption);
		mcqComponent(MCQheader,MCQinstruction,question,Option1,Option2,Option3,Option4);
		slideshowComponent(Slideshowtitle,uploadimage,subtitle,slidetitle,caption,placeholder,uploadvideo,text);
		imageLabellingComponent(labellingtitle,labellingInstruction,Label1,Label2,caption,uploadimage2);
		fillintheBlanksComponent(FillintheBlanksheader,FillintheBlanksinstruction,FillintheBlankssentence,FillintheBlanksOption1,FillintheBlanksOption2,FillintheBlanksOption3);
		
		highlightComponent(introduction,instruction,statement);
		correctionComponent(correctionheader,instruction,statement,correctanswer);
		sortingComponent(sortingheader,instruction,statement,sortanswer);
		matchthepairsComponent(mtpheader,instruction,Element1,Element2,Question1,Answer1,Question2,Answer2,Question3,Answer3,Question4,Answer4);
		sidebarComponent(sidebartitle,text);
		clicktorevealComponent(ClicktoRevealheader,text,ButtonLabel);
		wordSearchComponent(wordsearchheader,text,wordsearchquestion,hiddenword);
		questionanswerComponent(EnterQuestion);
		
		Thread.sleep(3000);
		Driver.driver.close();
// 		Old Window Handle
	    Thread.sleep(5000);
	    Driver.driver.switchTo().window(winHandleBefore);
		
		Driver.driver.navigate().refresh(); 
		//WebDriverWait wait = new WebDriverWait(Driver.driver,60); 
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeBody"));
		//Driver.driver.switchTo().frame("iframeBody");
		System.out.println("Frame switched");
	//	Driver.driver.switchTo().frame("iframeBody");
	    
	 //publish author widget and download (list view)
	  	publishDownloadAuthorWidget(Format,language);
	
	//copy functionality
		copyAuthorWidget();
		
	//Rename functionality	
		renameAuthorWidget(widgetName);
	
	// Filter author widget and create widget Functionality
		filterAuthorWidget();
		
	/*//Multiple publish author widget
		multiplePublishDownload(theme,Format,language);*/
		
	// share functionaliy
		shareAuthorWidget();
		
	// Change cover functionality
		changeCover();
		
	//Delete functionality
		deleteAuthorWidget();
		
	}

	public static void multiplePublishDownload(String theme,String Format,String language) throws Exception {
		/*chkSelectAll();
		Thread.sleep(3000);
		chkdeSelectAll();*/
		chkFirstbx();
		chkSecondbx();
		Thread.sleep(9000);
		Thread.sleep(9000);
		btnMultipublish();
		//drpdwTheme();
		
		if(theme.equalsIgnoreCase("Aqua")) {
			drpdwThemeAqua();
		}
		
		//drpdwFormat();
		
		if(Format.equalsIgnoreCase("HTML")) {
			drpdwFormatHtml();
		}
		if(Format.equalsIgnoreCase("Scorm")) {
			drpdwnFormatScorm();
		}
		
		//drpdwlanguage();
		if(language.equalsIgnoreCase("English")) {
			drpdwLanguageEnglish();
		}
		if(language.equalsIgnoreCase("Italian")) {
			drpdwLanguageItalian();
		}
		if(language.equalsIgnoreCase("purple")) {
			drpdwLanguageSpanish();
		}
		
		btnMultiplePublish();
		chk_SelectAllDownload();
		chk_FirstDownload();
		chk_secondDownload();
		btnMultipleDownload();
	}

	public static void publishDownloadAuthorWidget(String Format,String language) throws Exception {
		btnPublish();
		btnBlockMainPage();
		//Thread.sleep(1000);
		//drpdwnFormat();
		
		if(Format.equalsIgnoreCase("HTML")) {
			drpdwnFormatHTML();
		}
		if(Format.equalsIgnoreCase("Scorm")) {
			drpdwnFormatScorm();
		}
		
		
		//drpdwnLanguage();
		
		if(language.equalsIgnoreCase("English")) {
			drpdwnLanguageEnglish();
		}
		if(language.equalsIgnoreCase("Italian")) {
			drpdwnLanguageItalian();
		}
		if(language.equalsIgnoreCase("purple")) {
			drpdwnLanguageSpanish();
		}
		
		
		btnPublishOk();
		btnBlockMainPage();
		ClearFile.clearfile();
		btnDownloadPackage();
		//drpdwPublished();
		drpdwAll();
	}

	public static void filterAuthorWidget() {
		drpdwnselect();
		drpdwnauthorwidget();
		drpdwnselect();
		drpdwncreatewidget();
		drpdwnselectall();
	}

	public static void deleteAuthorWidget() {
		btndelete();
		btnconfirmdelete();
	}

	public static void shareAuthorWidget() {
		btnlistview_share();
		rdbtnoption_share();
		btnconfirm_share();
		btnBlockMainPage();
		btngridview();
		btngridview_share();
		rdbtnoption_share();
		btnconfirm_share();
		btnBlockMainPage();
		btnlistview();
	}

	public static void changeCover() {
		btngridview();
		btngridview_changeCover();
		Uploadpath.uploadpath("\\TestData\\photo.jpg");
	}

	public static void questionanswerComponent(String EnterQuestion) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpQuestionanswer();
		txtbx_EnterQuestion(EnterQuestion);
		/*cmpQuestionanswer();
		txtbx_EnterQuestion(EnterQuestion);
		rdbtn_longAnswer();*/
		saveActivity();
		loader();
	}

	public static void createAuthorWidget(String widgetName,String category,String tag,String courseDescription) throws Exception {
		btnAuthorWidget();
		txtwidgetName(widgetName);
		txt_category(category);
		txt_addTag(tag);
		txt_courseDescription(courseDescription);
		btn_save();
		Thread.sleep(7000);
		Thread.sleep(5000);
		btn_begin();
		
	}

	public static void titleComponent(String title,String author) {
		btn_tableof_content();
		btnFirstPage();
		btn_componenets(); 
		cmp_title();
		btn_titlestyle();
		btn_titleheader();
		btn_titleSetting();
		txtbx_title(title);
		txtbx_author(author);
		saveActivity();
		loader();
	}
	
	public static void headerComponent(String header) {
		btn_tableof_content();
		addPage();
		btn_componenets(); 
		cmpheader();
		txtbxHeader(header);
		btnheaderStyle();
		btnheaderSecondStyle();
		saveActivity();
		loader();
		/*btnheaderSettings();
		txtbxBackgroundColour();
		txtbxSelectColour();*/
	}
	
	public static void textComponent(String text) {
		btn_tableof_content();
		addPage();
		btn_componenets(); 
		cmptext();
		txtbxtext(text);
		saveActivity();
		loader();
	}
	
	public static void imageComponent(String imageheader,String caption,String uploadimage) {
		btn_tableof_content();
		addPage();
		btn_componenets(); 
		cmpimage();
		txtbxImageHeader(imageheader);
		btnUploadImage();
		Uploadpath.uploadpath(uploadimage);
		loader();
		txtbxImageCaption(caption);
		saveActivity();
		loader();
	}
	
	public static void videoComponent(String videoheader,String caption,String uploadvideo) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpVideo();
		txtbxVideoHeader(videoheader);
		txtbxVideoCaption(caption);
		btnUploadVideo();
		Uploadpath.uploadpath(uploadvideo);
		loader();
		saveActivity();
		loader();
	}
	
	public static void audioComponent(String caption,String uploadaudio) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpAudio();
		Uploadpath.uploadpath(uploadaudio);
		loader();
		txtbxAudioCaption(caption);
		saveActivity();
		loader();
	}
	
	public static void htmlInteractivityComponent(String caption, String uploadzip) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpHTMLinteractivity();
		btnUploadHTMLinteractivity();
		Uploadpath.uploadpath(uploadzip);
		loader();
		txtbxHTMLinteractivityCaption(caption);
		saveActivity();
		loader();
	}
	
	public static void tableComponent(String NoOfRows,String NoOfColumns,String tableheader, String caption) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpTable();
		txtbxNoOfRows(NoOfRows);
		txtbxNoOfColumns(NoOfColumns);
		btnCreate();
		txtbxTableHeader(tableheader);
		txtbxTableCaption(caption);
		/*//txtbxTableHeader1("Book Name");
		tableWriter(prop.getProperty("tableheader1_txtbx_Xpath"), "Book Name");
		tableWriter(prop.getProperty("tableheader2_txtbx_Xpath"), "Book Author");
		tableWriter(prop.getProperty("tableheader3_txtbx_Xpath"), "Book Details");
		tableWriter(prop.getProperty("column_name1_txtbx_Xpath"), "The Discovery of India");
		tableWriter(prop.getProperty("column_name2_txtbx_Xpath"), "Jawahar Lal Nehru");
		tableWriter(prop.getProperty("column_name3_txtbx_Xpath"), "Feeling of loss is no alien to human nature; indeed it abounds in our modern age marked by pace and aspiration. But seldom does it happen that a book leaves you in that feeling, like a loss of a long friend. A book is essentially a monologue and at most exposes one to the writer’s mind. But even that monologue can resonate with your inner intimate questions and then the book becomes a dialogue. Discovery of India is one such book.");
		tableWriter(prop.getProperty("column_id1_txtbx_Xpath"), "The Harry Potter Series");
		tableWriter(prop.getProperty("column_id2_txtbx_Xpath"), "J. K. Rowling");
		tableWriter(prop.getProperty("column_id3_txtbx_Xpath"), "Harry Potter is a series of seven fantasy novels written by British author J. K. Rowling. The novels chronicle the lives of a young wizard, Harry Potter, and his friends Hermione Granger and Ron Weasley, all of whom are students at Hogwarts School of Witchcraft and Wizardry. The main story arc concerns Harry's struggle against Lord Voldemort, a dark wizard who intends to become immortal, overthrow the wizard governing body known as the Ministry of Magic and subjugate all wizards and Muggles");
		tableWriter(prop.getProperty("column_designation1_txtbx_Xpath"), "My Country My Life");
		tableWriter(prop.getProperty("column_designation2_txtbx_Xpath"), "Lal Krishna Advani");
		tableWriter(prop.getProperty("column_designation3_txtbx_Xpath"), "My Country My Life is an autobiographical book by L. K. Advani, an Indian politician who served as the Deputy Prime Minister of India from 2002 to 2004, and was the Leader of the Opposition in the 15th Lok Sabha. The book was released on 19 March 2008 by Abdul Kalam, the eleventh President of India.The book has 1,040 pages and narrates autobiographical accounts and events in the life of Advani. It became the best seller book in the non-fiction category and Advani joined Archer as a bestseller author.");
		*/
		saveActivity();
		loader();
	}
	
	

	public static void tableWriter(String ele,String header) {
		WebElement element= UIElements.elementFinderByXpath(prop.getProperty(ele), "elementName");
		// Action class use
	    Actions action = new Actions(Driver.driver);
	    Action seriesOfActions = action	.click().sendKeys(element,header).build();
	    seriesOfActions.perform();
	}
	
	public static void staticSidebarComponent(String staticSidebarheader,String text,String uploadimage,String caption) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpStaticSidebar();
		txtbxstaticsidebarHeader(staticSidebarheader);
		txtbxstaticsidebarText(text);
		btnUploadImagesidebar();
		Uploadpath.uploadpath(uploadimage);
		loader();
		txtbxStaticSidebarCaption(caption);
		saveActivity();
		loader();
	}
	
	public static void mcqComponent(String MCQheader,String MCQinstruction,String question,String Option1,String Option2,String Option3,String Option4) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpMultipleChoiceQuestions();
		txtbxMCQHeader(MCQheader);
		txtbxMCQinstruction(MCQinstruction);
		txtbxMCQquestion(question);
		txtbxMCQOption1(Option1);
		txtbxMCQOption2(Option2);
		txtbxMCQOption3(Option3);
		txtbxMCQOption4(Option4);
		chkbxcorrectoption();
		saveActivity();
		loader();
	}
	
	public static void slideshowComponent(String Slideshowtitle,String uploadimage, String subtitle,String slidetitle,String caption, String placeholder,String uploadvideo,String text) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpSlideshow();
		txtbxSlideshowTitle(Slideshowtitle);
		txtbxSlideshowSubTitle(subtitle);
		txtbxSlideshowSlideTitle(slidetitle);
		txtbxSlideshowCaption(caption);
		txtbxSlideshowPlaceholder(placeholder);
		btnSlideshowUploadImage();
		Uploadpath.uploadpath(uploadimage);
		loader();
		btnSlideshowNextSlide();
		chkbxSlideshowUploadVideo();
		btnSlideshowUploadVideo2();
		Uploadpath.uploadpath(uploadvideo);
		loader();
		saveActivity();
		loader();
		//txtbxAddText(text);
		//Uploadpath.uploadpath(uploadimage);
		//loader();
	}
	
	public static void imageLabellingComponent(String labellingtitle,String labellingInstruction,String Label1,String Label2,String caption,String uploadimage2) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpImagelabelling();
		txtbxImagelabellingTitle(labellingtitle);
		txtbxImagelabellingInstruction(labellingInstruction);
		txtbxImagelabellingCaption(caption);
		btnImagelabellingUploadImage();
		Uploadpath.uploadpath(uploadimage2);
		loader();
		btnImagelabellingAddLabel();
		btnImagelabellingAddLabel1();
		btnImagelabellingLabel1();
		txtbxImagelabellingTextArea1(Label1);
		/*btnImagelabellingCircle();
		btnImagelabellingAddLabel2();
		btnImagelabellingLabel2();
		txtbxImagelabellingTextArea2(Label2);*/
		txtbxImagelabellingCaption(caption);
		saveActivity();
		loader();
	}
	
	public static void fillintheBlanksComponent(String FillintheBlanksheader,String FillintheBlanksinstruction,String FillintheBlankssentence,String FillintheBlanksOption1,String FillintheBlanksOption2,String FillintheBlanksOption3) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpFillintheBlanks();
		txtbxFillintheBlanksHeader(FillintheBlanksheader);
		txtbxFillintheBlanksInstruction(FillintheBlanksinstruction);
		txtbxFillintheBlankSentence(FillintheBlankssentence);
		btnfillintheBlankInsertBlank();
		btnselectAnswerDrpdwn();
		btnAddOption();
		txtbxFillintheBlankOption1(FillintheBlanksOption1);
		txtbxFillintheBlankOption2(FillintheBlanksOption2);
		txtbxFillintheBlankOption3(FillintheBlanksOption3);
		txtbxFillintheBlankradio1();
		saveActivity();
		loader();
	}
	
	public static void highlightComponent(String introduction,String instruction,String statement) throws AWTException, InterruptedException {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpHighlighter();
		txtbx_introduction(introduction);
		txtbx_instruction(instruction);
		txtbx_1statement(statement);
		SelectWord.selectword();
		btnhighlight();
		txtbx_1statement(statement);
		saveActivity();
		loader();
	}
	
	public static void correctionComponent(String correctionheader,String instruction,String statement,String correctanswer) throws AWTException, InterruptedException {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpCorrection();
		txtbx_correctionHeader(correctionheader);
		txtbx_correctionInstruction(instruction);
		txtbx_statement(statement);
		SelectWord.selectword();
		btnstrikethrough();
		txtbx_correctanswer(correctanswer);
		saveActivity();
		loader();
	}
	
	public static void sortingComponent(String sortingheader,String instruction,String statement,String sortanswer) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpSorting();
		txtbx_sortingHeader(sortingheader);
		txtbx_sortingInstruction(instruction);
		txtbx_sortingstatement(statement);
		btninsertSortItem();
		txtbx_sortanswer(sortanswer);
		saveActivity();
		loader();
	}
	
	public static void matchthepairsComponent(String mtpheader,String instruction,String Element1,String Element2,String Question1,String Answer1,String Question2,String Answer2,String Question3,String Answer3,String Question4,String Answer4) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpmatchthePairs();
		txtbx_mtpHeader(mtpheader);
		txtbx_mtpInstruction(instruction);
		txtbx_mtpElement1(Element1);
		txtbx_mtpElement2(Element2);
		btnmtpAddPairs();
		txtbx_mtpQuestion1(Question1);
		txtbx_mtpAnswer1(Answer1);
		txtbx_mtpQuestion2(Question2);
		txtbx_mtpAnswer2(Answer2);
		txtbx_mtpQuestion3(Question3);
		txtbx_mtpAnswer3(Answer3);
		txtbx_mtpQuestion4(Question4);
		txtbx_mtpAnswer4(Answer4);
		saveActivity();
		loader();
	}

	public static void sidebarComponent(String sidebartitle,String text) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpsidebar();
		txtbx_sidebarTitle(sidebartitle);
		txtbx_sidebarEnterText(text);
		saveActivity();
		loader();
	}
	
	public static void clicktorevealComponent(String ClicktoRevealheader,String text,String ButtonLabel) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpclicktoreveal();
		txtbx_ClicktoRevealHeader(ClicktoRevealheader);
		txtbx_ClicktoRevealdescription(text);
		txtbx_ClicktoRevealButtonLabel(ButtonLabel);
		saveActivity();
		loader();
	}

	
	public static void wordSearchComponent(String wordsearchheader,String text,String wordsearchquestion,String hiddenword) {
		btn_tableof_content();
		addPage();
		btn_componenets();
		cmpWordSearch();
		txtbx_WordsearchHeader(wordsearchheader);
		txtbx_Wordsearchdescription(text);
		txtbx_wordsearchquestion(wordsearchquestion);
		txtbx_wordsearchhiddenword(hiddenword);
		btnadd();
		saveActivity();
		loader();
	}



	public static void renameAuthorWidget(String WidgetName) {
		btngridview();
		btnrenamewidget();
		btnBlockMainPage();
		txtbx_widgetname(WidgetName);
		btnsavewidget();
		btnBlockMainPage();
	}

	public static void copyAuthorWidget() {
		btnlistview_copy();
		btnBlockMainPage();
		btngridview();
		btngridview_copy();
		btnBlockMainPage();
	}

}
