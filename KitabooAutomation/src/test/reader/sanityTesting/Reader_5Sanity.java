package sanityTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.reader.bookShelf.BookShelfModule;
import com.hurix.reader.bookShelf.BookShelfStepModule;
import com.hurix.reader.loginPage.LoginModule;

public class Reader_5Sanity extends LoginModule{

	public static void TC_InvalidUserNameANDPassword(String username, String password){

		userLogin(username, password);
		String msg = getinvalidusernamePasswordmsg();
		if(msg.equalsIgnoreCase("Username or Password entered is incorrect")) {
			Log.info("Username or Password entered is incorrect");
		}
		else {
			Log.error("Username or Password entered is correct");
		}	
	}
	
	public static void TC_LoginWithValidUserNameANDPassword(String username, String password){

		userLogin(username, password);
		threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("bookshelf")){
			Log.pass("After successfully login redirected to the bookshelf"+Driver.driver.getCurrentUrl());
		}else{
			Log.fail("Expected URL contain bookshelf but found "+Driver.driver.getCurrentUrl());
		}	
	}

	public static void TC_RememberMe(String username, String password){

		txtUsername(username);
		txtPassword(password);
		chkRememberme();
		btnLogin();
		BookShelfModule.userLogOut();
		Driver.driver.navigate().refresh();
		btnLogin();
		threadHold_5Sec();
		if(Driver.driver.getCurrentUrl().contains("bookshelf")){
			Log.pass("After successfully login redirected to the bookshelf"+Driver.driver.getCurrentUrl());
			BookShelfModule.userLogOut();
		}else{
			Log.fail("Expected URL contain bookshelf but found "+Driver.driver.getCurrentUrl());
		}
	}

	public static  void TC_DownloadForDesktop(){

		//validation pending..
		btnDesktopInstaller();
	}

	public static  void TC_AvailableOnTheAppleStore()  {

		btnIOSInstaller();
	}

	public static  void TC_GetItOnGooglePlay(){

		btnAndroidInstaller();	
	}

	public static  void TC_DownlaodFromWindowsStore(){

		btnWindowsInstaller();
	}

	public static  void TC_InvalidAccessCodeSignup(String accesscode){
		String msg = accessCodeSignup(accesscode, "fullname", "emailid", "password", "confirmpassword");
		if(msg.equalsIgnoreCase("Invalid Access Code entered")) {
			Log.info("Accesscode entered is invalid");
		}
		else {
			Log.fail("Accesscode entered is valid");
		}
	}	
	
	public static  void TC_AccessCodeSignupByUsedEmailID(String accesscode, String fullname, String emailid, String password, String confirmpassword){

		accessCodeSignup(accesscode, fullname, emailid, password, confirmpassword);
	}	
	
	public static  void TC_AccessCodeSignupByEmailID(String accesscode, String fullname, String emailid, String password, String confirmpassword){

		accessCodeSignup(accesscode, fullname, emailid, password, confirmpassword);
		threadHold_5Sec();
		BookShelfStepModule.btnNextHelpScreen();
		BookShelfStepModule.btnSkipHelpScreen();
		if(Driver.driver.getCurrentUrl().contains("bookshelf")){
			Log.pass("After successfully login redirected to the bookshelf"+Driver.driver.getCurrentUrl());
		}else{
			Log.fail("Expected URL contain bookshelf but found "+Driver.driver.getCurrentUrl());
		}	
	}
	
}
