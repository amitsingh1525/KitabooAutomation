package authoringSanity;

import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.Log;
import com.hurix.library.Authorwidget.AuthorwidgetModule;
import com.hurix.library.kitabooBooks.KitabooBooksModule;
import com.hurix.platform.loginPage.LoginModule;
import com.hurix.reader.bookPlayer.BookPlayerModule;

public class Authoring_BVT {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Log.initialization("AuthoringBVT");
		Log.startTestCase("setup");
		BrowserConfigure.browserConfigure("Chrome");
		Driver.driver.navigate().to("https://create.kitaboo.com/home.xhtml");
		
		LoginModule.loginScenario("pdg.rakesh@hurix.com", "kitaboo!123");
		// Getting the current epoch time
		  long epoch = System.currentTimeMillis()/1000;
		  
		  KitabooBooksModule.bookCreationPDF("Test Authoring", "Test Author", "level 4", "discription", "keyWords");
		  
		  
		 // AuthorwidgetModule.UploadDocbook("\\testData\\STRUMENTI.zip","Nature, in the broadest sense, is the natural, physical, or material world","Automation Team");
		  
		/*AuthorwidgetModule.Components("Authoring Widget_"+epoch,"category","tag","courseDescriptionNature, in the broadest sense, is the natural, physical","Nature, in the broadest sense, is the natural, physical, or material world","Automation Team",
				"Nature, in the broadest sense, is the natural, physical, or material world or universe.Nature can refer to the phenomena of the physical world, and also to life in general.", "Nature, in the broadest sense, is the natural, physical, or material world or universe. Nature can refer to the phenomena of the physical world, and also to life in general. The study of nature is a large, if not the only, part of science. Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", 
				"Test Image component", "Nature, in the broadest sense, is the natural, physical, or material world or universe.","\\testData\\dog.jpg","Test video Component", "\\testData\\flower.mp4","\\testData\\Long_audio.mp3",
				"\\testData\\disco-lights-on-flying-cube.zip","4","3","Test Table Component","Test Static Sidebar Component",
				"Test MCQ Component", "Choose correct option from following","What is the Capital of India?","Maharashtra","Delhi","Punjab","Chennai",
				"Test slideshow component","Navigate slides to know more","Slide title 1","Nature, in the broadest sense, is the natural, physical, or material world or universe. Nature can refer to the phenomena of the physical world, and also to life in general.",
				"\\testData\\flower.jpg","Test ImageLabelling component","Label properly following given labels","flower","stem",
				"Test Fill in the Blanks component","Fill in the blanks instruction","The number 10 is","<20",">20","=20",
				"Test Highlighter Component","Find the highlighted words from following","Test Highlighter","Test correction component","Test Correction","Test Sorting Component","Sorting","Test Match The Pairs Component","Pair 1","Pair 2","Fruit","Mango","Flower","Rose","Pet Animal","Dog","Wild Animal","Tiger",
				"Test Sidebar Component","Test Click to reveal Component","Cllick to reveal button","Test Wordsearch component","Choose hidden words from following","Automation","Describe Nature in your own words.","HTML","English","Aqua");
		
		Driver.driver.switchTo().parentFrame();
		AuthorwidgetModule.userLogOut();
		LoginModule.loginScenario("author_beta@yopmail.com", "kitaboo@123");
		AuthorwidgetModule.linkActivities();
		AuthorwidgetModule.txtanotherSharedUser();*/
		//AuthorwidgetModule.txtSharedUser();
		
		
		/*String currentuser = AuthorwidgetModule.txtSharedUser();
		String anotheruser = BookPlayerModule.getCurrentPageNum();

		if(currentuser == anotheruser) {
			Log.pass("Widget is shared properly to particular user");
		}else {
			Log.fail("Shared user is wrong");
		}*/
		
	}

}
