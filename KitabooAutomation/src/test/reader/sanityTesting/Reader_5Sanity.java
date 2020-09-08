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
		LoginModule.accessCodeSignup("3195071661782080", "hello Tester_4324", "helloTester4324@yopmail.com", 
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
		String msg = LoginModule.accessCodeSignup("3195071661782080", "hello Tester_4334", "helloTester"+nowEpochTime+"@yopmail.com", 
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
			Log.pass("reset msg found.");
		}else{
			Log.fail("reset msg NOT found.");
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
		LoginModule.userLogin("sharing.teacher@yopmail.com", "kitaboo@123");
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
		BookShelfModule.catNavigationAndLaunch("Showcase");
		LoginModule.threadHold_5Sec();
		BookplayerStepModule.btnbacktobookshelf();
		Log.endTestCase("End");
	}

	public static  void TC_SearchAndLaunchBook(){
		Log.startTestCase("TC_SearchAndLaunchBook");
		BookShelfModule.searchAndLaunchBook("Showcase eBook");
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
		BookShelfModule.catNavigationAndLaunch("All");
		BookShelfStepModule.btnMoreInfo(0);
		Log.pass("Title Found: "+BookShelfStepModule.getBookTitleMoreInfo());
		Log.pass("Discription Found: "+BookShelfStepModule.getBookDiscriptionMoreInfo());
		Log.pass("Author Found: "+BookShelfStepModule.getBookAuthorMoreInfo());
		BookShelfStepModule.btn_XClose();
		Log.endTestCase("End");
	}

	public static  void TC_Analytics(){
		Log.startTestCase("TC_Analytics");
		BookShelfModule.catNavigationAndLaunch("Showcase");
		BookShelfStepModule.btnMoreInfo(1);
		BookShelfStepModule.btnAnalytics();
		Log.endTestCase("End");
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
		BookplayerStepModule.btnhistoryprevious();
		BookPlayerModule.threadHold_2Sec();
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
		String zoomVal = BookplayerStepModule.getzoomValue();
		BookPlayerModule.zoomIn(240);
		Log.info("Current Zoom Percantage: "+zoomVal);
		String curZoomVal = BookplayerStepModule.getzoomValue();
		if(Integer.parseInt(zoomVal.replace("%", "")) < Integer.parseInt(curZoomVal.replace("%", ""))) {
			Log.pass("After ZoomIn Percantage: "+curZoomVal);
		}else {
			Log.fail("Expected Increment by n number of current Percentage: "+zoomVal+" But Found :'"+curZoomVal+"'");
		}
		Log.endTestCase("End");
	}

	public static void TC_ZoomOut() {
		Log.startTestCase("TC_ZoomOut");
		String zoomVal = BookplayerStepModule.getzoomValue();
		BookPlayerModule.zoomOut(120);
		Log.info("Current Zoom Percantage: "+zoomVal);
		String curZoomVal = BookplayerStepModule.getzoomValue();
		if(Integer.parseInt(zoomVal.replace("%", "")) < Integer.parseInt(curZoomVal.replace("%", ""))) {
			Log.pass("After ZoomIn Percantage: "+curZoomVal);
		}else {
			Log.fail("Expected Increment by n number of current Percentage: "+zoomVal+" But Found :'"+curZoomVal+"'");
		}
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
		BookPlayerModule.stickyNotes("purple", "5", 70, 60);
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
		BookPlayerModule.deleteStickyNotes("5", 70, 60);
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
		int countAfterNotes = BookPlayerModule.myDataHighlightCount("green");
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
			Log.pass("Before delete highlight Count: "+countAfterNotes+" After adding sticky notes Count: "+countBeforeNotes);
		}else {
			Log.fail("Expected decrement by n number of current highlight: "+countAfterNotes+ " But Found: "+countBeforeNotes);
		}
		Log.endTestCase("End");
	}

	public static void TC_PenTool() {
		Log.startTestCase("TC_PenTool");
		BookPlayerModule.pentool("blue", "5", 70, 60);
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
		for(int i=0; i< resourceSize; i++) {
			BookPlayerModule.threadHold_2Sec();
			BookPlayerModule.contentResources(i);
		}
		Log.endTestCase("End");
	}
	
	public static void TC_Content() {
		Log.startTestCase("TC_Content");
		
		Log.endTestCase("End");
	}

	public static void main(String []args){
		setup();
		/*TC_InvalidUserNameANDPassword("", "");
		TC_DownloadForDesktop();
		TC_AvailableOnTheAppleStore();
		TC_GetItOnGooglePlay();
		TC_DownlaodFromWindowsStore();
		TC_LoginWithValidUserNameANDPassword("", "");
		TC_RememberMe("", "");
		TC_InvalidAccessCodeSignup("");
		TC_AccessCodeSignupByUsedEmailID("", "", "", "", "");
		TC_AccessCodeSignup("", "", "", "", "");
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
		TC_SearchAndLaunchBook();
		TC_moreInfo();
		TC_Analytics();*/


		LoginModule.userLogin("hurixreader5@gmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("Automation");
		//TC_goToPage();
		//TC_HistoryPrevious();
		//TC_HistoryNext();
		
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		BookPlayerModule.threadHold_5Sec();
		
		//TC_SinglePageView();
		//BookPlayerModule.threadHold_2Sec();
		//TC_DoublePageView();
		//TC_RightNavigation();
		//TC_LeftNavigation();
		//TC_Fit2widhtVerification();
		//TC_Fit2heightVerification();
		//TC_ZoomIn();//need assertion
		//TC_ZoomOut();//need assertion
		//TC_SearchBooktext();
		//TC_Full_DefaultScreen();
		//TC_AddingStickyNotes();
		//TC_DeleteStickyNotes();
		//TC_Highlight();
		//TC_DeleteHighlight();
		//TC_PenTool();
		//TC_ErasePenTool();
		TC_ContentResources();

		//BookPlayerModule.highlight();
		//BookPlayerModule.goToPage("20");
		/*LoginModule.threadHold_5Sec();
		LoginModule.threadHold_5Sec();
		LoginModule.threadHold_5Sec();
		LoginModule.threadHold_5Sec();
		//BookPlayerModule.getPageNum();
		BookplayerStepModule.btnhighlight();
		Driver.driver.switchTo().frame("epub_5");
		System.out.println(UIElements.elementFinderByID("p5-textid50001", "").getText());*/
		//Driver.driver.findElement(By.cssSelector("body.disable-user-selection")).click();
		//WebElement elem = UIElements.elementFinderByID("p5-textid50001", "");

		/*List <WebElement> list = Driver.driver.findElements(By.tagName("span"));
		System.out.println("Number of links: "+list.size());
		List <WebElement> ele = null;
		for(int i = 0; i < list.size(); i++){
				System.out.println(list.get(i).getText());
				list.get(i).click();
				Driver.driver.switchTo().defaultContent();
				int size = Driver.driver.findElements(By.id("highlight_0")).size();
				if(size <= 1){
					System.out.println("present...");
				}
				Driver.driver.switchTo().frame("epub_5");

		}*/

		//Driver.driver.findElement(By.id("highlight_0")).click();
		//int width = elem.getSize().getWidth();
		//int height = elem.getSize().getHeight();
		//Actions act = new Actions(Driver.driver);
		//System.out.println(width);
		//Point location = elem.getLocation();
		// int x = location.getX();
		//int y = location.getY();
		// System.out.println("Coordinates of an element is : " + x + " and " + y);
		//act.moveToElement(elem).moveByOffset(width, height).click().perform();
		//act.moveToElement(from).click().perform();


		
		/*
		 * LoginModule.userLogin("sharing.teacher@yopmail.com", "kitaboo@123");
		 * BookShelfModule.catNavigationAndLaunch("showcase");
		 * 
		 * BookPlayerModule.backtoBookshelf(); BookShelfModule.analytics();
		 */
		 
		/*LoginModule.userLogin("rajesha.sdk1@yopmail.com", "kitaboo@123");
	//	BookShelfModule.catNavigationAndLaunch("showcase");
		BookPlayerModule.addbookmark();
		BookPlayerModule.deletebookmark();*/
		//LoginModule.userLogin("sharing.teacher@yopmail.com", "kitaboo@123");
		//BookShelfModule.catNavigationAndLaunch("showcase");
		/*BookPlayerModule.zoomIn();
		BookPlayerModule.zoomOut();
		BookPlayerModule.fitToWidth();
		BookPlayerModule.searchBookText();*/
		//BookPlayerModule.myDataHighlightCount("all");
		//BookPlayerModule.myDatanormalNotesCount("all");//y
		//	BookPlayerModule.myDatacontextualNotesCount("yellow,red");

		//BookPlayerModule.myDatacontextualNotesCount("yellow,red");

		/*LoginModule.userLogin("hurixreader5@gmail.com", "kitaboo@123");
		BookShelfModule.catNavigationAndLaunch("Hurix Showcase");
		//BookPlayerModule.highlight("blue");
		//BookPlayerModule.deleteHighlight();

		//BookPlayerModule.pentool("black", "5", 50, 60);
		//BookPlayerModule.pentool("blue", "5", 70, 60);
		BookPlayerModule.goToPage("5");
		BookPlayerModule.stickyNotes("green", "5", 70, 60);
		//BookPlayerModule.erasepentool("5", 50, 60);
		BookPlayerModule.highlight("blue");
		BookPlayerModule.deleteHighlight();*/
		//BookPlayerModule.pentool();

		//termination();
	}

}
