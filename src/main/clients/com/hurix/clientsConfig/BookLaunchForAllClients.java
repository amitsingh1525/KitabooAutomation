package com.hurix.clientsConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hurix.aao.AAO;
import com.hurix.automation.utility.BrowserConfigure;
import com.hurix.automation.utility.Driver;
import com.hurix.automation.utility.GoogleSpreadSheetData;
import com.hurix.automation.utility.Log;

public class BookLaunchForAllClients {

	@Before
	public void setUp() {
		GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!I1:I4", "WIP");
		BrowserConfigure.browserConfigure(SheetMetaData.browserName);
	}


	@Test
	public void TC_ClientWiseBookLaunch() throws Exception {
		int sheetRow = 5;
		for(int i=0;i<SheetMetaData.clientURL.size();i++) {
			try {
				sheetRow++;
				GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!F"+sheetRow, "");
				Thread.sleep(500);
				GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!G"+sheetRow, "");
				Thread.sleep(500);
				GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!H"+sheetRow, "");
				Thread.sleep(500);
				GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!i"+sheetRow, "");
				Thread.sleep(500);
				GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!j"+sheetRow, "");
				Thread.sleep(500);
				Log.startTestCase(i+1+". "+SheetMetaData.clientNames.get(i));
				Log.info("Client Name: '"+SheetMetaData.clientNames.get(i)+"'");
				Log.info("URL: "+SheetMetaData.clientURL.get(i));

				Driver.driver.navigate().to(SheetMetaData.clientURL.get(i));
				switch(SheetMetaData.clientNames.get(i)) 
				{
				case "AAO":
					AAO.aao(SheetMetaData.clientUsernames.get(i), SheetMetaData.clientPasswords.get(i),sheetRow, i); 
					break;



				default: 
					GoogleSpreadSheetData.setValue(SheetMetaData.SheetID,"Users!J"+sheetRow,"Not Implemented!"); 
				}

			} catch (Exception e) {
				Log.error("Script Failed due to >>> "+e);
				//Driver.driver.quit();
				Thread.sleep(1000);
				//BrowserConfigure.browserConfigure(SheetMetaData.browserName);
				continue;
			}
			break;
		}
	}


	@After
	public void tearDown() throws Exception {
		GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!I1:I4", "Script Finished!");
		
		//new SendEmail("amit.singh@hurix.com", "P@$$word#456", SheetMetaData.mailDetails);
		Driver.driver.quit();
	}
}
