package com.hurix.test.SanityExecution;

import java.time.Instant;
import java.util.List;

import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Credentials;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.ExcelFile;
import com.hurix.automation.utility.Log;
import com.hurix.epubbooks.EpubBook.EpubBookModule;
import com.hurix.library.kitabooBooks.KitabooBooksModule;
import com.hurix.platform.loginPage.LoginModule;

public class PublisherSanity {

	public static void main(String[] args) {
		
		try {
			List<String> credential = Credentials.getCredentials("platform", "Kitaboo");
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
				
				String title		 = ExcelFile.getCellData(i, 0);
				String ISBN 		 = ExcelFile.getCellData(i, 1);
				String Author		 = ExcelFile.getCellData(i, 2);	
				String Categories	 = ExcelFile.getCellData(i, 3);
				String Description	 = ExcelFile.getCellData(i, 4);
				String Keywords		 = ExcelFile.getCellData(i, 5);
				String Language		 = ExcelFile.getCellData(i, 6);
				String BookType  	 = ExcelFile.getCellData(i, 7);	
				String BookOriantaion= ExcelFile.getCellData(i, 8);	
				boolean FontPermission= Boolean.parseBoolean(ExcelFile.getCellData(i, 9));	
				String picformat	 = ExcelFile.getCellData(i, 10);	
				String DPI           = ExcelFile.getCellData(i, 11);	
				boolean ShowFolio     = Boolean.parseBoolean(ExcelFile.getCellData(i, 12));	
				String Glossary      = ExcelFile.getCellData(i, 13);	
				String GlossaryPath	 = ExcelFile.getCellData(i, 14);
				String MetaData		 = ExcelFile.getCellData(i, 15);
				String MetaDataPath	 = ExcelFile.getCellData(i, 16);
				String UploadFile	 = ExcelFile.getCellData(i, 17);
				String PDF_DocPath	 = ExcelFile.getCellData(i, 18);
				String UploadCover	 = ExcelFile.getCellData(i, 19);
				String LowImageQuality = ExcelFile.getCellData(i, 20);
				String LowImagePath	 = ExcelFile.getCellData(i, 21);
				String HighImageQuality	 = ExcelFile.getCellData(i, 22);
				String HighImagePath	 = ExcelFile.getCellData(i, 23);
				String UploadTOC	 = ExcelFile.getCellData(i, 24);
				String TOCPath		 = ExcelFile.getCellData(i, 25);
				String publish 		 = ExcelFile.getCellData(i, 26);
				String archive 	 = ExcelFile.getCellData(i, 27);
				String searchby      = ExcelFile.getCellData(i, 29);
				String searchvalue      = ExcelFile.getCellData(i, 30);
				// Excel file me true/false karna padega
				
				System.out.println(title);
				//KitabooBooksModule.bookCreationPDF(isbn, title, author, category, discription, language, bookType, keywords, bookOriantaion, bookPath)
				//KitabooBooksModule.bookCreationPDF(title,ISBN,Author,Categories,Description,Keywords,Language,BookType,BookOriantaion,FontPermission,picformat,DPI,ShowFolio,Glossary,GlossaryPath,MetaData,MetaDataPath,UploadFile,PDF_DocPath,UploadCover,LowImageQuality,LowImagePath,HighImageQuality,HighImagePath,UploadTOC,TOCPath);
				//KitabooBooksModule.bookverify(title,ISBN);
				//KitabooBooksModule.serachby(searchby, searchvalue);
				
				EpubBookModule.epubhtmlbookcreate(title,Language,"Portrait","Reflow",BookOriantaion,DPI,picformat,MetaDataPath,PDF_DocPath,UploadCover,LowImagePath,UploadTOC,UploadFile);
			
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
