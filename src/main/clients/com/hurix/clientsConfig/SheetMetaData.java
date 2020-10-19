package com.hurix.clientsConfig;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.hurix.automation.utility.GoogleSpreadSheetData;

public class SheetMetaData {

	private static String lastIndex = "59";
	
	public static String SheetID = "1K6n_lUAv2jdIn7OasRltL47k1SJjDNU3MBFQdNnpDMY"; //"1SfXfg-97R9uprud9maElgsn7WFwfnp7B5XD5eJrpn5k";//
	public static String browserName;
	public static String mailDetails;
	public static List<String> clientNames = null;
	public static List<String> clientURL = null;
	public static List<String> clientUsernames = null;
	public static List<String> clientPasswords = null;
	
	public static void UserConfig(){
		List<List<Object>> values = getvalue(SheetID, "UserConfig!A2:B2");
		
    	List<String> clintInfo = new ArrayList<String>();
		if (values == null || values.isEmpty()) {
        } else {
            for (List row : values) {
            	
            	clintInfo.add(row.get(0).toString());
            	clintInfo.add(row.get(1).toString());
            }
        }
		browserName = clintInfo.get(0);
		mailDetails = clintInfo.get(1);
	}
	
	public static void clientName(){
		List<List<Object>> KFAutomationTCName = getvalue(SheetID, "Users!A6:A"+lastIndex);
		
		clientNames = new ArrayList<String>();
		if (KFAutomationTCName == null || KFAutomationTCName.isEmpty()) {
        } else {
            for (List row : KFAutomationTCName) {
            	clientNames.add(row.get(0).toString());
            }
        }
	}
	
	public static void clientURL(){
		List<List<Object>> KFAutomationTCName = getvalue(SheetID, "Users!B6:B"+lastIndex);
		
		clientURL = new ArrayList<String>();
		if (KFAutomationTCName == null || KFAutomationTCName.isEmpty()) {
        } else {
            for (List row : KFAutomationTCName) {
            	clientURL.add(row.get(0).toString());
            }
        }
	}
	
	public static void clientUsername(){
		List<List<Object>> KFAutomationTCName = getvalue(SheetID, "Users!C6:C"+lastIndex);
		
		clientUsernames = new ArrayList<String>();
		if (KFAutomationTCName == null || KFAutomationTCName.isEmpty()) {
        } else {
            for (List row : KFAutomationTCName) {
            	clientUsernames.add(row.get(0).toString());
            }
        }
	}
	
	public static void clientPassword(){
		List<List<Object>> KFAutomationTCName = getvalue(SheetID, "Users!D6:D"+lastIndex);
		
		clientPasswords = new ArrayList<String>();
		if (KFAutomationTCName == null || KFAutomationTCName.isEmpty()) {
        } else {
            for (List row : KFAutomationTCName) {
            	clientPasswords.add(row.get(0).toString());
            }
        }
	}
	
	private static List<List<Object>> getvalue(String SheetID, String rangeNum){
			
			List<List<Object>> values = null;
			try {
				final String spreadsheetid = SheetID;
				final String range = rangeNum;
				
				ValueRange response = GoogleSpreadSheetData.service.spreadsheets().values()
				        .get(spreadsheetid, range)
				        .execute();
	      
				values = response.getValues();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return values;
	        
		}
}
