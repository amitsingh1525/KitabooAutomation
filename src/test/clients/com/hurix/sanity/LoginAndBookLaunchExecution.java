package com.hurix.sanity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.hurix.automation.utility.GoogleSpreadSheetData;
import com.hurix.automation.utility.Log;
import com.hurix.clientsConfig.BookLaunchForAllClients;
import com.hurix.clientsConfig.SheetMetaData;

public class LoginAndBookLaunchExecution {

	public static void main(String[] args) {

        //Login with googleSheet
        GoogleSpreadSheetData.loginToSheet();
        
        //get the tab UserConfig details
        SheetMetaData.UserConfig();
        
        //get the ClientName
        SheetMetaData.clientName();
        
        //get the ClientName
        SheetMetaData.clientURL();
        
        //get the clientUsername
        SheetMetaData.clientUsername();
        
        //get the clientPassword
        SheetMetaData.clientPassword();
        
        try {
        	Log.initialization("LoginAndBookLaunch");
			Log.startTestCase("System Info");
			Log.info("Script Start @"+new Date());
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!C1:C2", ""+new Date());
			InetAddress localhost = InetAddress.getLocalHost(); 
			Log.info("System IP Address : " +(localhost.getHostAddress()).trim()); 
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!F2", (localhost.getHostAddress()).trim());
			
			String userNam = System.getenv("USERNAME");
			Log.info("Script run By : " +userNam.replaceAll("\\.", " "));
			GoogleSpreadSheetData.setValue(SheetMetaData.SheetID, "Users!C3:C4", userNam.replaceAll("\\.", " "));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to getting system info!");
		}
        
        Result result = JUnitCore.runClasses(BookLaunchForAllClients.class);
		
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
    
	}
	
}
