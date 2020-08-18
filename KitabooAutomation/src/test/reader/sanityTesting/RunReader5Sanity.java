package sanityTesting;

import java.time.Instant;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Gmail_ReadAndDelete;
import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;
import com.hurix.reader.bookShelf.BookShelfModule;
import com.hurix.reader.bookShelf.BookShelfStepModule;
import com.hurix.reader.loginPage.LoginModule;

public class RunReader5Sanity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Log.initialization("TestingReader5.O");
		Log.startTestCase("Reader");
		BrowserConfigure.browserConfigure("Chrome");
		Driver.driver.navigate().to("https://read.kitaboo.com/reader/V5/#!/");
		/*Reader_5Sanity.TC_InvalidUserNameANDPassword("invalidUsername@yopmail.com", "InvalidP@$$");
		Reader_5Sanity.TC_LoginWithValidUserNameANDPassword("sharing.teacher@yopmail.com", "kitaboo@123");
		BookShelfModule.userLogOut();
		Reader_5Sanity.TC_DownloadForDesktop();
		Reader_5Sanity.TC_GetItOnGooglePlay();
		Reader_5Sanity.TC_DownlaodFromWindowsStore();
		Reader_5Sanity.TC_AvailableOnTheAppleStore();
		Reader_5Sanity.TC_RememberMe("sharing.teacher@yopmail.com", "kitaboo@123");
		Reader_5Sanity.TC_InvalidAccessCodeSignup("3195071149694502");
		long nowEpochTime = Instant.now().toEpochMilli();
		Reader_5Sanity.TC_AccessCodeSignupByEmailID("3195071642111167", "hello Tester_4334", "helloTester"+nowEpochTime+"@yopmail.com", 
				"kitaboo@123", "kitaboo@123");
		BookShelfModule.userLogOut();
		Reader_5Sanity.TC_AccessCodeSignupByUsedEmailID("3195071703641834", "hello Tester_4324", "helloTester4324@yopmail.com", 
				"kitaboo@123", "kitaboo@123");
		Driver.driver.navigate().refresh();
		Gmail_ReadAndDelete.deleteAllMails();
		LoginModule.forgetPassword("hurix.reader@gmail.com");
		String newPassword = Gmail_ReadAndDelete.readMails();
		Reader_5Sanity.TC_LoginWithValidUserNameANDPassword("hurix.reader@gmail.com", newPassword);
		BookShelfModule.changePassword(newPassword, "kitaboo@123", "kitaboo@321");
		BookShelfStepModule.btnCancelChangePassword();
		BookShelfModule.changePassword(newPassword, "kitaboo@123", "kitaboo@123");
		Driver.driver.navigate().refresh();
		BookShelfModule.userLogOut();*/
		
		Reader_5Sanity.TC_LoginWithValidUserNameANDPassword("sharing.teacher@yopmail.com", "kitaboo@123");
		/*BookShelfModule.termAndCondition();
		BookShelfModule.privacyPolicy();
		BookShelfModule.aboutUS();
		BookShelfStepModule.btnRefresh();*/
		
		//BookShelfModule.uploadProfilePic(System.getProperty("user.dir")+"/resources/einstein.jpg");//need to update
		
		//BookShelfModule.changefirstNameAndLastName("Amit", "Singh", true);
		//BookShelfModule.changefirstNameAndLastName("Clickon", "Cancel", false);
		//BookShelfModule.changefirstNameAndLastName("Hurix", "Reader", true);
		//BookShelfModule.catNavigationAndLaunch("Showcase");
		//BookShelfModule.searchAndLaunchBook("Showcase eBook");
		BookShelfModule.markAsFav(0);
		BookShelfModule.unmarkAsFav(0);
		System.out.println(BookShelfStepModule.getUnmarkAsFav_msgNoBookFound());
		UIElements.threadHold_5Sec();
		BookShelfModule.redeemAccessCode("3195071996706803");
		Log.endTestCase("End");
	}
}
