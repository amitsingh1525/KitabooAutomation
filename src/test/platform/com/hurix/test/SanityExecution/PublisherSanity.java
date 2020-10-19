package com.hurix.test.SanityExecution;

import java.time.Instant;
import java.util.List;

import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Credentials;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.ExcelFile;
import com.hurix.automation.utility.Log;
import com.hurix.library.kitabooBooks.KitabooBooksModule;
import com.hurix.platform.loginPage.LoginModule;

public class PublisherSanity {

	public static void main(String[] args) {
		
		try {
			List<String> credential = Credentials.getCredentials("platform", "Distribution");
			System.out.println("Username: "+credential.get(0));
			System.out.println("Password: "+credential.get(1));

			long nowEpochTime = Instant.now().toEpochMilli();
			Log.initialization("PublisherSanity_"+nowEpochTime);
			BrowserConfigure.browserConfigure(ExcelFile.getCellData(1, 1));
			
			Driver.driver.navigate().to(Credentials.URL);
			Log.startTestCase("Login Scenario");
			LoginModule.loginScenario(credential.get(0), credential.get(1));
			
			ExcelFile.setSheetName("CreateBook");
			Log.startTestCase("Book Creation");
			int lastRow = ExcelFile.getRowCount();
			Log.info("Number of Books: "+lastRow);
			System.out.println("lastRow: "+lastRow);
			for(int i=1; i<=lastRow; i++) {
				
				String title = ExcelFile.getCellData(i, 0);
				System.out.println(title);
				//KitabooBooksModule.bookCreationPDF(isbn, title, author, category, discription, language, bookType, keywords, bookOriantaion, bookPath)
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
