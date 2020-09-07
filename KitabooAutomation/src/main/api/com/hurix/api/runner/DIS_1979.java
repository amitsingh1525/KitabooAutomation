package com.hurix.api.runner;

import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.library.kitabooBooks.KitabooBooksModule;
import com.hurix.platform.loginPage.LoginModule;

public class DIS_1979 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BrowserConfigure.browserConfigure("Chrome");
		Driver.driver.navigate().to("https://qc.kitaboo.com/home.xhtml");
		LoginModule.loginScenario("automation.test1@yopmail.com", "kitaboo!123");
		String title = KitabooBooksModule.bookCreationPDF("hello", "World", "level 2", "For automation Testing", "Hello, World");
	
		KitabooBooksModule.bookPublishAndArchivePDF(title);
		/*for(int i1=1;i1<=2;i1++)
		{
			System.out.println("detailisbn = "+detailisbn.get(i1));
			Response Metadata_res = Metadata.metadata(consumerKey, consumerSecret,detailisbn.get(i1));
			System.out.println("Metadata_res : "+Metadata_res);
			isbnMeta = Metadata_res.then().extract().path("isbn");
			System.out.println("isbnMeta: "+isbnMeta);
			isbnMeta=detailisbn.get(i1);
			Response IngectEpub_res = IngectEpub.ingectEpub_ext(consumerKey, consumerSecret,isbnMeta);
			System.out.println("IngectEpub_res : "+IngectEpub_res);
			isbnIng = IngectEpub_res.then().extract().path("isbn");			
			System.out.println("isbnIng: "+isbnIng);			
			Response IngestionStatus_res = IngestionStatus.ingestionStatus(consumerKey, consumerSecret, isbnIng);
			System.out.println("IngestionStatus_res : "+IngestionStatus_res);			
		}
*/
	}

}
