package sanityTesting;

import java.time.Instant;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Gmail_ReadAndDelete;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;
import com.hurix.reader.bookPlayer.BookPlayerModule;
import com.hurix.reader.bookPlayer.BookplayerStepModule;
import com.hurix.reader.bookShelf.BookShelfModule;
import com.hurix.reader.bookShelf.BookShelfStepModule;
import com.hurix.reader.loginPage.LoginModule;
import com.hurix.reader.loginPage.LoginStepModule;

public class Reader_5Sanity{

	public static void setup(){
		Log.initialization("ReaderSanity_5.O");
		Log.startTestCase("setup");
		BrowserConfigure.browserConfigure("Chrome");
		Driver.driver.navigate().to("https://read.kitaboo.com/reader/V5/#!/");
		if(Driver.driver.getCurrentUrl().contains("read.kitaboo.com")){
			Log.pass("URL Launch "+Driver.driver.getCurrentUrl());
		}else{
			Log.fail("URL NOT Launch "+Driver.driver.getCurrentUrl());
		}
		Log.endTestCase("END");
	}

	public static void TC_InvalidUserNameANDPassword(String username, String password){
		Log.startTestCase("TC_InvalidUserNameANDPassword");
		LoginModule.userLogin("invalidUsername@yopmail.com", "InvalidP@$$");
		String msg = LoginModule.getinvalidusernamePasswordmsg();
		if(msg.equalsIgnoreCase("Username or Password entered is incorrect")) {
			Log.pass("Username or Password entered is incorrect");
		}
		else {
			//ja
			Log.fail("Username or Password entered is correct");
		}
		Log.endTestCase("End");
	}

	public static  void TC_DownloadForDesktop(){
		Log.startTestCase("TC_DownloadForDesktop");
		//validation pending..
		LoginModule.btnDesktopInstaller();
		Log.endTestCase("End");
	}

	public static  void TC_AvailableOnTheAppleStore()  {
		Log.startTestCase("TC_AvailableOnTheAppleStore");
		LoginModule.btnIOSInstaller();
		Log.endTestCase("End");
	}

	public static  void TC_GetItOnGooglePlay(){
		Log.startTestCase("TC_GetItOnGooglePlay");
		LoginModule.btnAndroidInstaller();
		Log.endTestCase("End");
	}

	public static  void TC_DownlaodFromWindowsStore(){
		Log.startTestCase("TC_DownlaodFromWindowsStore");
		LoginModule.btnWindowsInstaller();
		Log.endTestCase("End");
	}

	public static void TC_LoginWithValidUserNameANDPassword(String username, String password){
		Log.startTestCase("TC_LoginWithValidUserNameANDPassword");
		LoginModule.userLogin("sharing.teacher@yopmail.com", "kitaboo@123");
		LoginModule.threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("bookshelf")){
			Log.pass("After successfully login redirected to the bookshelf"+Driver.driver.getCurrentUrl());
			BookShelfModule.userLogOut();
		}else{
			Log.fail("Expected URL contain bookshelf but found "+Driver.driver.getCurrentUrl());
		}
		Log.endTestCase("End");
	}

	public static void TC_RememberMe(String username, String password){
		Log.startTestCase("TC_RememberMe");
		LoginModule.txtUsername("sharing.teacher@yopmail.com");
		LoginModule.txtPassword("kitaboo@123");
		LoginModule.chkRememberme();
		LoginModule.btnLogin();
		BookShelfModule.userLogOut();
		Driver.driver.navigate().refresh();
		LoginModule.btnLogin();
		LoginModule.threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("bookshelf")){
			Log.pass("After successfully login redirected to the bookshelf"+Driver.driver.getCurrentUrl());
			BookShelfModule.userLogOut();
		}else{
			Log.fail("Expected URL contain bookshelf but found "+Driver.driver.getCurrentUrl());
		}
		Log.endTestCase("End");
	}

	public static  void TC_InvalidAccessCodeSignup(String accesscode){
		Log.startTestCase("TC_InvalidAccessCodeSignup");
		String msg = LoginModule.accessCodeSignup("3195071149694502", "fullname", "emailid", "password", "confirmpassword");
		if(msg.equalsIgnoreCase("Invalid Access Code entered")) {
			Log.pass("Accesscode entered is invalid");
		}
		else {
			Log.fail("Accesscode entered is valid");
		}
		Log.endTestCase("End");
	}	

	public static  void TC_AccessCodeSignupByUsedEmailID(String accesscode, String fullname, String emailid, String password, String confirmpassword){
		Log.startTestCase("TC_AccessCodeSignupByUsedEmailID");
		LoginModule.accessCodeSignup(accesscode, "hello Tester_4324", "helloTester4324@yopmail.com", 
				"kitaboo@123", "kitaboo@123");
		String msg = LoginModule.getexistsemailidmsg();
		if(msg.contains("Entered Email Id already exists")){
			Log.pass("email id already exists msg found");
		}else{
			Log.fail("email id already exists msg NOT found!");
		}
		Driver.driver.navigate().refresh();
		Log.endTestCase("End");
	}	

	public static  void TC_AccessCodeSignup(String accesscode, String fullname, String emailid, String password, String confirmpassword){
		Log.startTestCase("TC_AccessCodeSignup");
		long nowEpochTime = Instant.now().toEpochMilli();
		String msg = LoginModule.accessCodeSignup(accesscode, "hello Tester_4334", "helloTester"+nowEpochTime+"@yopmail.com", 
				"kitaboo@123", "kitaboo@123");
		if(msg == null){
			LoginModule.threadHold_5Sec();
			BookShelfStepModule.btnNextHelpScreen();
			BookShelfStepModule.btnSkipHelpScreen();
			if(Driver.driver.getCurrentUrl().contains("bookshelf")){
				Log.pass("After successfully login redirected to the bookshelf"+Driver.driver.getCurrentUrl());
			}else{
				Log.fail("Expected URL contain bookshelf but found "+Driver.driver.getCurrentUrl());
			}	
			BookShelfModule.userLogOut();
		}else{
			if(msg.contains("invalid")){
				Log.fail("Invalid Access token.");
			}else{
				Log.fail("unknown error occured.");
			}
		}
		Log.endTestCase("End");
	}

	public static  void TC_InvalidForgetPassword(String username){
		Log.startTestCase("TC_InvalidForgetPassword");
		LoginModule.forgetPassword("invalidEmailID@gmail.com");
		LoginModule.threadHold_5Sec();
		String msg = LoginModule.getinvalidForgetEmailmsg();
		if(msg.contains("incorrect")){
			Log.pass("incorrect msg found.");
		}else{
			Log.fail("incorrect msg NOT found.");
		}
		Driver.driver.navigate().refresh();
		Log.endTestCase("End");
	}

	public static  void TC_ForgetPassword(String username){
		Log.startTestCase("TC_ForgetPassword");
		Gmail_ReadAndDelete.deleteAllMails();
		LoginModule.forgetPassword("hurix.reader@gmail.com");
		String msg = LoginModule.getForgetmsg();
		if(msg.contains("reset")){
			Log.pass("Forget Reset message found. message was: "+msg);
		}else{
			Log.fail("reset msg NOT found.. message found: "+msg);
		}
		LoginModule.btnOk();
		Log.endTestCase("End");
	}

	public static  void TC_ChangePassword(String username, String newPass){
		Log.startTestCase("TC_ChangePassword");
		String newPassword = Gmail_ReadAndDelete.readMails();
		LoginModule.userLogin("hurix.reader@gmail.com", newPassword);
		BookShelfModule.changePassword(newPassword, "kitaboo@123", "kitaboo@321");
		String msg = BookShelfStepModule.getForgetChangePasswordMissmatchText();
		System.out.println(msg);
		if(msg != null){
			if(msg.contains("should match")){
				Log.pass(msg+" msg found");
			}else{
				Log.fail("Unkonwon msg occured!");
			}
		}else{
			Log.fail("Unkonwon msg occured!");
		}
		BookShelfStepModule.btnCancelChangePassword();
		BookShelfModule.changePassword(newPassword, newPassword, newPassword);
		msg = BookShelfStepModule.getForgetChangePasswordMissmatchText();
		System.out.println(msg);
		if(msg != null){
			if(msg.contains("Cannot Be Same")){
				Log.pass(msg+" msg found");
			}else{
				Log.fail("Unkonwon msg occured!");
			}
		}else{
			Log.fail("Unkonwon msg occured!");
		}
		BookShelfStepModule.btnCancelChangePassword();
		BookShelfModule.changePassword(newPassword, "kitaboo@123", "kitaboo@123");
		msg = BookShelfStepModule.getForgetChangePasswordMissmatchText();
		System.out.println(msg);
		if(msg != null){
			if(msg.contains("Changed Successfully")){
				Log.pass(msg+" msg found");
			}else{
				Log.fail("Unkonwon msg occured!");
			}
		}else{
			Log.fail("Unkonwon msg occured!");
		}
		BookShelfStepModule.btnCancelChangePassword();
		Driver.driver.navigate().refresh();
		BookShelfModule.userLogOut();
		Log.endTestCase("End");
	}

	public static  void TC_TermAndCondition(){
		Log.startTestCase("TC_TermAndCondition");
		String url = BookShelfModule.termAndCondition();
		if(url.contains("terms-and-conditions")){
			Log.pass(url+" URL found");
		}else{
			Log.pass(url+" URL NOT found");
		}
		Log.endTestCase("End");
	}

	public static  void TC_PrivacyPolicy(){
		Log.startTestCase("TC_PrivacyPolicy");
		String url = BookShelfModule.privacyPolicy();
		if(url.contains("privacy-policy")){
			Log.pass(url+" URL found");
		}else{
			Log.pass(url+" URL NOT found");
		}
		Log.endTestCase("End");
	}

	public static  void TC_AboutUS(){
		Log.startTestCase("TC_AboutUS");
		LoginModule.userLogin("hurixlearner@gmail.com", "kitaboo@123");
		String msg = BookShelfModule.aboutUS();
		System.out.println(msg);
		if(msg.contains("Version")){
			Log.pass(msg+" msg Found");
		}else{
			Log.fail(msg+" msg NOT Found!");
		}
		Log.endTestCase("End");
	}

	public static  void TC_Refresh(){
		Log.startTestCase("TC_Refresh");
		Driver.driver.navigate().refresh();
		BookPlayerModule.threadHold_2Sec();
		BookShelfStepModule.btnRefresh();
		Log.endTestCase("End");
	}

	public static  void TC_ChangeFirstNameAndLastnameClickOnCancel(){
		Log.startTestCase("TC_ChangeFirstNameAndLastnameClickOnCancel");
		BookShelfModule.changefirstNameAndLastName("Clickon", "Cancel", false);
		Log.endTestCase("End");
	}

	public static  void TC_ChangeFirstNameAndLastname(){
		Log.startTestCase("TC_ChangeFirstNameAndLastname");
		BookShelfModule.changefirstNameAndLastName("Amit", "Singh", true);
		UIElements.threadHold_2Sec();
		BookShelfModule.changefirstNameAndLastName("Hurix", "Reader", true);
		Log.endTestCase("End");
	}

	public static  void TC_CatNavigationAndLaunch(){
		Log.startTestCase("TC_CatNavigationAndLaunch");
		BookShelfModule.catNavigationAndLaunch("Automation");
		LoginModule.threadHold_5Sec();
		BookplayerStepModule.btnbacktobookshelf();
		Log.endTestCase("End");
	}

	public static  void TC_SearchAndLaunchBook(String title){
		Log.startTestCase("TC_SearchAndLaunchBook");
		BookShelfModule.searchAndLaunchBook(title);
		LoginModule.threadHold_5Sec();
		BookplayerStepModule.btnbacktobookshelf();
		Log.endTestCase("End");
	}

	public static  void TC_MarkAsFav(){
		Log.startTestCase("TC_MarkAsFav");
		BookShelfModule.markAsFav(0);
		Log.endTestCase("End");
	}

	public static  void TC_unmarkAsFav(){
		Log.startTestCase("TC_unmarkAsFav");
		LoginModule.threadHold_2Sec();
		BookShelfModule.unmarkAsFav(0);
		Log.endTestCase("End");
	}

	public static  void TC_redeemAccessCode(String accessCode){
		Log.startTestCase("TC_redeemAccessCode");
		BookShelfModule.redeemAccessCode("3195071483305723");
		BookShelfStepModule.btnAccessCodeCancel();
		Log.endTestCase("End");
	}

	public static  void TC_moreInfo(){
		Log.startTestCase("TC_moreInfo");
		BookShelfModule.linkHorizontalCatName("All");
		BookShelfStepModule.btnMoreInfo(0);
		Log.pass("Title Found: "+BookShelfStepModule.getBookTitleMoreInfo());
		Log.pass("Discription Found: "+BookShelfStepModule.getBookDiscriptionMoreInfo());
		Log.pass("Author Found: "+BookShelfStepModule.getBookAuthorMoreInfo());
		BookShelfStepModule.btn_XClose();
		Log.endTestCase("End");
	}

	public static  void TC_Analytics(){
		Log.startTestCase("TC_Analytics");
		BookShelfModule.linkHorizontalCatName("All");
		BookShelfModule.analytics(0);
		Log.endTestCase("End");
		Driver.driver.navigate().refresh();
	}

	public static void termination(){
		Driver.driver.quit();
	}


	//Book Player
	public static  void TC_goToPage(){
		Log.startTestCase("TC_goToPage");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.goToPage("20");
		String pageNum = BookPlayerModule.getCurrentPageNum();
		if(pageNum.equalsIgnoreCase("20")){
			Log.pass("Current page Num.: "+pageNum);
		}else{
			Log.fail("Expected Page Number: '20' But Found :'"+pageNum+"'");
		}
		Log.endTestCase("End");
	}

	public static  void TC_HistoryPrevious(){
		Log.startTestCase("TC_HistoryPrevious");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.goToPage("5");
		BookPlayerModule.threadHold_2Sec();
		BookPlayerModule.goToPage("10");
		BookPlayerModule.threadHold_2Sec();
		BookPlayerModule.btnthumbnail();
		BookplayerStepModule.btnhistoryprevious();
		BookPlayerModule.threadHold_2Sec();
		String pageNum = BookPlayerModule.getCurrentPageNum();
		if(pageNum.equalsIgnoreCase("5")){
			Log.pass("Current page Num.: "+pageNum);
		}else{
			Log.fail("Expected Page Number: '5' But Found :'"+pageNum+"'");
		}
		Log.endTestCase("End");
	}

	public static  void TC_HistoryNext(){
		Log.startTestCase("TC_HistoryNext");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.goToPage("8");
		BookPlayerModule.threadHold_2Sec();
		BookPlayerModule.goToPage("13");
		BookPlayerModule.threadHold_2Sec();
		BookPlayerModule.btnthumbnail();
		BookplayerStepModule.btnhistoryprevious();
		BookPlayerModule.threadHold_2Sec();
		BookPlayerModule.btnthumbnail();
		BookplayerStepModule.btnhistorynext();
		BookPlayerModule.threadHold_2Sec();
		String pageNum = BookPlayerModule.getCurrentPageNum();
		if(pageNum.equalsIgnoreCase("13")){
			Log.pass("Current page Num.: "+pageNum);
		}else{
			Log.fail("Expected Page Number: '13' But Found :'"+pageNum+"'");
		}
		Log.endTestCase("End");
	}

	public static  void TC_SinglePageView(){
		Log.startTestCase("TC_SinglePageView");
		BookplayerStepModule.btnsinglepage();
		Log.endTestCase("End");
	}

	public static  void TC_DoublePageView(){
		Log.startTestCase("TC_DoublePageView");
		BookplayerStepModule.btndoublepage();
		Log.endTestCase("End");
	}

	public static  void TC_RightNavigation(){
		Log.startTestCase("TC_RightNavigation");
		String pageNum = BookPlayerModule.getCurrentPageNum();
		Log.info("Current Page Num: "+pageNum);
		BookplayerStepModule.btnNextPageNavigation();
		int nextPage = 0;
		if(UIElements.elementFinderByXpath(BookplayerStepModule.prop.getProperty("CommonbtnForSingleAndDbl_xpath"), 
				"CommonbtnForSingleAndDbl_xpath").getAttribute("aria-label").contains("Single Page View")) {
			nextPage = Integer.parseInt(pageNum) + 2;
		}else if(UIElements.elementFinderByXpath(BookplayerStepModule.prop.getProperty("CommonbtnForSingleAndDbl_xpath"), 
				"CommonbtnForSingleAndDbl_xpath").getAttribute("aria-label").contains("Double Page View")) {
			nextPage = Integer.parseInt(pageNum) + 1;
		}
		String curtPage = BookPlayerModule.getCurrentPageNum();
		if(Integer.toString(nextPage).equalsIgnoreCase(curtPage)){
			Log.pass("After navigate page number: "+nextPage);
		}else{
			Log.fail("Expected Page Number: "+nextPage+" But Found :'"+curtPage+"'");
		}
		Log.endTestCase("End");
	}

	public static  void TC_LeftNavigation(){
		Log.startTestCase("TC_LeftNavigation");
		String pageNum = BookPlayerModule.getCurrentPageNum();
		Log.info("Current Page Num: "+pageNum);
		BookplayerStepModule.btnPreviousPageNavigation();
		int nextPage = 0;
		if(UIElements.elementFinderByXpath(BookplayerStepModule.prop.getProperty("CommonbtnForSingleAndDbl_xpath"), 
				"CommonbtnForSingleAndDbl_xpath").getAttribute("aria-label").contains("Single Page View")) {
			nextPage = Integer.parseInt(pageNum) - 2;
		}else if(UIElements.elementFinderByXpath(BookplayerStepModule.prop.getProperty("CommonbtnForSingleAndDbl_xpath"), 
				"CommonbtnForSingleAndDbl_xpath").getAttribute("aria-label").contains("Double Page View")) {
			nextPage = Integer.parseInt(pageNum) - 1;
		}
		String curtPage = BookPlayerModule.getCurrentPageNum();
		if(Integer.toString(nextPage).equalsIgnoreCase(curtPage)){
			Log.pass("After navigate page number: "+nextPage);
		}else{
			Log.fail("Expected Page Number: "+nextPage+" But Found :'"+curtPage+"'");
		}
		Log.endTestCase("End");
	}


	public static void TC_Fit2widhtVerification() {
		Log.startTestCase("TC_Fit2widhtVerification");
		BookplayerStepModule.btnfittowidth();
		Log.endTestCase("End");
	}

	public static void TC_Fit2heightVerification() {
		Log.startTestCase("TC_Fit2heightVerification");
		BookplayerStepModule.btnfittoheight();
		Log.endTestCase("End");
	}

	public static void TC_ZoomIn() {
		Log.startTestCase("TC_ZoomIn");
		//String zoomVal = BookplayerStepModule.getzoomValue();
		BookPlayerModule.zoomIn(240);
		//Log.info("Current Zoom Percantage: "+zoomVal);
		//String curZoomVal = BookplayerStepModule.getzoomValue();
		/*
		 * if(Integer.parseInt(zoomVal.replace("%", "")) <
		 * Integer.parseInt(curZoomVal.replace("%", ""))) {
		 * Log.pass("After ZoomIn Percantage: "+curZoomVal); }else {
		 * Log.fail("Expected Increment by n number of current Percentage: "
		 * +zoomVal+" But Found :'"+curZoomVal+"'"); }
		 */
		Log.endTestCase("End");
	}

	public static void TC_ZoomOut() {
		Log.startTestCase("TC_ZoomOut");
		//String zoomVal = BookplayerStepModule.getzoomValue();
		BookPlayerModule.zoomOut(120);
		//Log.info("Current Zoom Percantage: "+zoomVal);
		//String curZoomVal = BookplayerStepModule.getzoomValue();
		//if(Integer.parseInt(zoomVal.replace("%", "")) < Integer.parseInt(curZoomVal.replace("%", ""))) {
		//	Log.pass("After ZoomIn Percantage: "+curZoomVal);
		//}else {
		//	Log.fail("Expected Increment by n number of current Percentage: "+zoomVal+" But Found :'"+curZoomVal+"'");
		//}
		Log.endTestCase("End");
	}

	public static void TC_SearchBooktext() {
		Log.startTestCase("TC_SearchBooktext");
		String pageNum = BookPlayerModule.searchBookText("the", 0);
		System.out.println(">>>"+pageNum);
		String curPageNum = BookPlayerModule.getCurrentPageNum();
		if(pageNum.replace("Page ", "").equals(curPageNum)) {
			Log.pass("Yes, it's redirect to the right page. Page Num: "+curPageNum);
		}else {
			Log.fail("Expected page number: "+pageNum.replace("Page ", "")+" But Found: "+curPageNum);
		}
		Log.endTestCase("End");
	}

	public static void TC_Full_DefaultScreen() {
		Log.startTestCase("TC_Full_DefaultScreen");
		int beforeFullscrSize = Driver.driver.manage().window().getSize().getHeight();
		BookplayerStepModule.btnfull_DefaultScreen();
		UIElements.threadHold_2Sec();
		int afterFullscrSize = Driver.driver.manage().window().getSize().getHeight();
		if(beforeFullscrSize < afterFullscrSize) {
			Log.pass("Before full screen mode height: "+beforeFullscrSize+" and after full screen mode height: "+afterFullscrSize);
		}else {
			Log.fail("Expected Increment by n number of current height: "+beforeFullscrSize+ " But Found: "+afterFullscrSize);
		}
		BookplayerStepModule.btnfull_DefaultScreen();
		UIElements.threadHold_2Sec();
		int afterDefaultscrSize = Driver.driver.manage().window().getSize().getHeight();
		if(afterFullscrSize > afterDefaultscrSize) {
			Log.pass("Before full screen mode height: "+afterFullscrSize+" and after full screen mode height: "+afterDefaultscrSize);
		}else {
			Log.fail("Expected decrement by n number of current height: "+beforeFullscrSize+ " But Found: "+afterFullscrSize);
		}
		Log.endTestCase("End");
	}

	public static void TC_DefaultScreen() {
		Log.startTestCase("TC_CollapseScreen");
		UIElements.threadHold_2Sec();
		BookplayerStepModule.btnfull_DefaultScreen();
		Log.endTestCase("End");
	}

	public static void TC_AddingStickyNotes() {
		Log.startTestCase("TC_AddingStickyNotes");
		int countAfterNotes = BookPlayerModule.myDatanormalNotesCount("purple");
		BookPlayerModule.stickyNotes("purple", "5", 70, 60, "How are you?");
		int countBeforeNotes = BookPlayerModule.myDatanormalNotesCount("purple");
		if(countAfterNotes < countBeforeNotes) {
			Log.pass("Before adding sticky Notes Count: "+countAfterNotes+" After adding sticky notes Count: "+countBeforeNotes);
		}else {
			Log.fail("Expected increment by n number of current notes: "+countAfterNotes+ " But Found: "+countBeforeNotes);
		}
		Log.endTestCase("End");
	}

	public static void TC_DeleteStickyNotes() {
		Log.startTestCase("TC_DeleteStickyNotes");
		int countAfterNotes = BookPlayerModule.myDatanormalNotesCount("all");
		BookPlayerModule.openStickyNotesWithCordinates("purple", "5", 70, 60);
		BookPlayerModule.btnDeleteStickyNotes();
		int countBeforeNotes = BookPlayerModule.myDatanormalNotesCount("all");
		if(countAfterNotes > countBeforeNotes) {
			Log.pass("Before delete sticky Notes Count: "+countAfterNotes+" After adding sticky notes Count: "+countBeforeNotes);
		}else {
			Log.fail("Expected decrement by n number of current notes: "+countAfterNotes+ " But Found: "+countBeforeNotes);
		}
		Log.endTestCase("End");
	}

	public static void TC_Highlight() {
		Log.startTestCase("TC_Highlight");
		int countAfterNotes = BookPlayerModule.myDataHighlightCount("green,yellow,blue");
		BookPlayerModule.highlight("green");
		int countBeforeNotes = BookPlayerModule.myDataHighlightCount("green");
		if(countAfterNotes < countBeforeNotes) {
			Log.pass("Before apply highlight Count: "+countAfterNotes+" After adding sticky notes Count: "+countBeforeNotes);
		}else {
			Log.fail("Expected increment by n number of current highlight: "+countAfterNotes+ " But Found: "+countBeforeNotes);
		}
		Log.endTestCase("End");
	}

	public static void TC_DeleteHighlight() {
		Log.startTestCase("TC_DeleteHighlight");
		int countAfterNotes = BookPlayerModule.myDataHighlightCount("green");
		BookPlayerModule.deleteHighlight();
		int countBeforeNotes = BookPlayerModule.myDataHighlightCount("green");
		if(countAfterNotes > countBeforeNotes) {
			Log.pass("Before delete highlight Count: "+countAfterNotes+" After delete highlight Count: "+countBeforeNotes);
		}else {
			Log.fail("Expected decrement by n number of current highlight: "+countAfterNotes+ " But Found: "+countBeforeNotes);
		}
		Log.endTestCase("End");
	}

	public static void TC_PenTool() {
		Log.startTestCase("TC_PenTool");
		BookPlayerModule.pentool("blue", "5","//*[@id='p5-textid50001']", 70, 60);
		Log.endTestCase("End");
	}

	public static void TC_ErasePenTool() {
		Log.startTestCase("TC_ErasePenTool");
		BookPlayerModule.erasepentool("5", 70, 60);
		Log.endTestCase("End");
		UIElements.threadHold_2Sec();
		Log.startTestCase("TC_UndoPenTool");
		BookPlayerModule.btnundo();
		Log.endTestCase("End");
		UIElements.threadHold_2Sec();
		Log.startTestCase("TC_ClearAllPenTool");
		BookPlayerModule.clearAllpentool();
		Log.endTestCase("End");
	}

	public static void TC_ContentResources() {
		Log.startTestCase("TC_ContentResources");
		int resourceSize = BookplayerStepModule.getResourcelst();
		Log.info("Resources Count: "+resourceSize);
		for(int i=0; i<=  2; i++) {
			BookPlayerModule.threadHold_2Sec();
			BookPlayerModule.contentResources(i);
		}
		Log.endTestCase("End");
	}
	
	public static void TC_JumptoBook() {
		Log.startTestCase("TC_JumptoBook");
		LoginModule.userLogin("reader5_teacher@yopmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("history");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.jumptobook(1,"pageresource jumptobook ");
		BookPlayerModule.bookPlayerLogout();
		Log.endTestCase("End");
	}

	public static void TC_AddBookmark() {
		Log.startTestCase("TC_AddBookmark");
		BookPlayerModule.addbookmark("10", "Important Page!");
		Log.endTestCase("End");
	}

	public static void TC_DeleteBookmark() {
		Log.startTestCase("TC_DeleteBookmark");
		BookPlayerModule.deletebookmark(0);
		Log.endTestCase("End");
	}


	public static void TC_NoteSharing() {
		Log.startTestCase("TC_NoteSharing");
		LoginModule.userLogin("hurixlearner@gmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("Automation");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.stickyNotes("purple", "5", 70, 60, "I noted down my text.");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.stickyNotes("blue", "5", 195, 75, "If you read this message please comment us.");
		BookPlayerModule.openStickyNotesWithCordinates("purple", "5", 70, 60);
		BookPlayerModule.btnSharedStickyNotes();
		BookPlayerModule.chkbxSharedNotesToAllTeacher();
		BookPlayerModule.btndoneSharedStickyNotes();

		BookPlayerModule.openStickyNotesWithCordinates("blue", "5", 195, 75);
		BookPlayerModule.btnSharedStickyNotes();
		BookPlayerModule.chkbxSharedNotesToAllTeacher();
		BookPlayerModule.btndoneSharedStickyNotes();

		BookPlayerModule.bookPlayerLogout();
		LoginModule.userLogin("hurixteacher5@gmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("Automation");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.myDataSharedNotes(0, true);
		BookPlayerModule.myDataSharedNotes(1, false);
		BookPlayerModule.myDataCommentOnSharedNotes(0, "Yes, I found your message!");
		BookPlayerModule.bookPlayerLogout();
		LoginModule.userLogin("hurixlearner@gmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("Automation");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.openStickyNotesWithCordinates("purple", "5", 70, 60);
		BookPlayerModule.btnDeleteStickyNotes();
		BookPlayerModule.openStickyNotesWithCordinates("blue", "5", 195, 75);
		String msg = BookPlayerModule.getSharedStickyNotesCommentmsg();
		if(msg.equals("Yes, I found your message!")) {
			Log.pass("Teacher comment found in a student login. msg was: '"+msg+"'");
		}else {
			Log.fail("Teacher comment NOT found in a student login. msg was: '"+msg+"'");
		}
		BookPlayerModule.btnDeleteStickyNotes();
		Log.endTestCase("End");
	}
	
	public static void TC_HighlightSharing() {
		Log.startTestCase("TC_HighlightSharing");
		LoginModule.userLogin("reader5_student1@yopmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("history");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.highlightsharing("p5-textid50332","green","student 1 share highlight to teacher");
		BookPlayerModule.bookPlayerLogout();
		
		LoginModule.userLogin("reader5_student2@yopmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("history");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.highlightsharing("p5-textid50332","green","student 2 share highlight to teacher");
		BookPlayerModule.bookPlayerLogout();
		
		LoginModule.userLogin("reader5_teacher@yopmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("history");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.myDataSharedNotes(1, true);
		BookPlayerModule.myDataSharedNotes(0, true);
		BookPlayerModule.myDataCommentOnSharedNotes(1, "Yes, I found your message!");
		BookPlayerModule.myDataCommentOnSharedNotes(0, "Yes, I found your message!");
	//	BookPlayerModule.highlightsharing("green","student 2 share highlight to teacher");

		BookPlayerModule.bookPlayerLogout();
		LoginModule.userLogin("reader5_student1@yopmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("history");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.selectparagraph_delete(5,"p5-textid50332");
		String msg = BookPlayerModule.getSharedHighlightCommentmsg();
		if(msg.equals("Yes, I found your message!")) {
			Log.pass("Teacher comment found in a student 1 login. msg was: '"+msg+"'");
		}else {
			Log.fail("Teacher comment NOT found in a student 1 login. msg was: '"+msg+"'");
		}
		BookPlayerModule.btnhighlightsharedelete();
		
		BookPlayerModule.bookPlayerLogout();
		LoginModule.userLogin("reader5_student2@yopmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("history");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.selectparagraph_delete(5,"p5-textid50332");
	//	String msg = BookPlayerModule.getSharedHighlightCommentmsg();
		if(msg.equals("Yes, I found your message!")) {
			Log.pass("Teacher comment found in a student 2 login. msg was: '"+msg+"'");
		}else {
			Log.fail("Teacher comment NOT found in a student 2 login. msg was: '"+msg+"'");
		}
		BookPlayerModule.btnhighlightsharedelete();
		
		Log.endTestCase("End");
		BookPlayerModule.bookPlayerLogout();
	}
	
	public static void TC_fib_dropdown_pentoolSharing() throws InterruptedException {
		Log.startTestCase("TC_Clear All");
		LoginModule.userLogin("reader5_student1@yopmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("history");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.clearAll(1);
		Log.endTestCase("End");
		BookPlayerModule.threadHold_5Sec();
	//	BookPlayerModule.bookPlayerLogout();
		
		Log.startTestCase("TC_Fib_Dropdown_Pentool Sharing");
		BookPlayerModule.fib_dropdown_pentoolSharing(1,"Student 1 share fib_dropdown_pentool");
		BookPlayerModule.pentool("purple", "2","//*[@id='p2-textid20002']", 70, 60);
		Thread.sleep(1000);
		BookPlayerModule.btnSubmit();
		BookPlayerModule.threadHold_2Sec();
		BookPlayerModule.bookPlayerLogout();
		
		LoginModule.userLogin("reader5_student2@yopmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("history");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.fib_dropdown_pentoolSharing(2,"Student 2 share fib_dropdown_pentool");
		BookPlayerModule.pentool("blue", "2","//*[@id='p2-textid20002']", 160, 160);
		Thread.sleep(1000);
		BookPlayerModule.btnSubmit();
		BookPlayerModule.threadHold_2Sec();
		BookPlayerModule.bookPlayerLogout();
		
		LoginModule.userLogin("reader5_teacher@yopmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("history");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.teacherReview(1);
		BookPlayerModule.teacherReview(2);
		
		Log.endTestCase("End");
	}

	public static void TC_SerachA_word() {
		Log.startTestCase("TC_SerachA_word");
		BookPlayerModule.searchAWord();
		Log.endTestCase("End");
	}

	public static void TC_Contents() {
		Log.startTestCase("TC_Contents");
		BookPlayerModule.content();
		Log.endTestCase("End");
	}
	public static void TC_NavigationWithChapter() {
		Log.startTestCase("TC_NavigationWithChapter");
		String currentpage = BookPlayerModule.getCurrentPageNum();
		BookPlayerModule.btnnextchapter();
		String nextpage = BookPlayerModule.getCurrentPageNum();

		if(Integer.parseInt(currentpage) < Integer.parseInt(nextpage)) {
			Log.pass("Increment by current pageNum: "+currentpage+" Next pageNum: "+nextpage);
		}else {
			Log.fail("Expected it's increment by current pageNum: "+currentpage+" but Found pageNum: "+nextpage);
		}
		BookPlayerModule.btnpreviouschapter();
		String previouspage = BookPlayerModule.getCurrentPageNum();
		if(Integer.parseInt(nextpage) > Integer.parseInt(previouspage)){
			Log.pass("Decrement by current pageNum: "+nextpage+" previous pageNum: "+previouspage);
		}else {
			Log.fail("Expected it's decrement by current pageNum: "+nextpage+" but Found pageNum: "+previouspage);
		}
		Log.endTestCase("End");
	}

	public static void main(String []args) throws InterruptedException{
		/*setup();
		TC_InvalidUserNameANDPassword("", "");
		TC_DownloadForDesktop();
		TC_AvailableOnTheAppleStore();
		TC_GetItOnGooglePlay();
		TC_DownlaodFromWindowsStore();
		TC_LoginWithValidUserNameANDPassword("", "");
		TC_RememberMe("", "");
		TC_InvalidAccessCodeSignup("");
		TC_AccessCodeSignupByUsedEmailID("0503111170616024", "", "", "", "");
		TC_AccessCodeSignup("0503111170616024", "", "", "", "");

		TC_InvalidForgetPassword("");
		TC_ForgetPassword("");
		TC_ChangePassword("", "");

		TC_AboutUS();
		TC_TermAndCondition();
		TC_PrivacyPolicy();
		TC_Refresh();
		TC_ChangeFirstNameAndLastnameClickOnCancel();
		TC_ChangeFirstNameAndLastname();
		LoginModule.threadHold_2Sec();
		TC_CatNavigationAndLaunch();
		TC_MarkAsFav();
		TC_unmarkAsFav();
		TC_redeemAccessCode("");
		TC_moreInfo();
		TC_Analytics();

		BookShelfModule.userLogOut();
		//LoginModule.userLogin("hurixlearner@gmail.com", "kitaboo@123");
		//BookShelfModule.catNavigationAndLaunch("Automation");
		LoginModule.userLogin("hurixreader5@gmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("Automation");

		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();

		TC_goToPage();
		TC_HistoryPrevious();
		TC_HistoryNext();
		TC_SinglePageView();
		BookPlayerModule.threadHold_2Sec();
		TC_DoublePageView();
		TC_RightNavigation();
		TC_LeftNavigation();
		TC_ZoomIn();//need assertion
		TC_ZoomOut();//need assertion
		TC_Fit2widhtVerification();
		TC_Fit2heightVerification();
		TC_SearchBooktext();
		TC_Full_DefaultScreen();
		TC_AddingStickyNotes();
		TC_DeleteStickyNotes();
		TC_Highlight();
		TC_DeleteHighlight();
		TC_PenTool();
		TC_ErasePenTool();
		TC_ContentResources();
		TC_Contents();
		BookPlayerModule.bookPlayerLogout();
		TC_NoteSharing();
		BookPlayerModule.bookPlayerLogout();
		LoginModule.userLogin("rajesha.sdk@yopmail.com", "kitaboo@123");
		//TC_SearchAndLaunchBook("Porto raw Tracking");
		BookShelfModule.catNavigationAndLaunch("SDK Testing");
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		TC_AddBookmark();
		TC_DeleteBookmark();
		TC_NavigationWithChapter();
		TC_SerachA_word();
		BookPlayerModule.bookPlayerLogout();*/
		
		setup();
		TC_JumptoBook();
		TC_HighlightSharing();
		TC_fib_dropdown_pentoolSharing();
		
		//termination();

	}

}