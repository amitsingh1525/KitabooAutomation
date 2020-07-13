package com.hurix.automation.utility;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoogleSpreadSheetData {
	
    private static final String APPLICATION_NAME = "OAuth client";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    public static Sheets service = null;
    private static String sheenID = "";

	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		GoogleAuthorizationCodeFlow flow = null;
		LocalServerReceiver receier = null;
	    	try {
				// Load client secrets.
				InputStream in = GoogleSpreadSheetData.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
				GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

				// Build flow and trigger user authorization request.
				flow = new GoogleAuthorizationCodeFlow.Builder(
				        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				        .setAccessType("offline")
				        .build();
				
				receier = new LocalServerReceiver.Builder().setPort(8888).build();
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	    	return new AuthorizationCodeInstalledApp(flow, receier).authorize("user");
	    }
	
	public static void loginToSheet(){
		try {
			final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
			        .setApplicationName(APPLICATION_NAME)
			        .build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Facing problem while connecting to sheet. ERROR: "+e.getMessage());
		}
	}
	
	public static String getValues(String SheetID, String sheetRange) {
		String value = null;
	    	try {
				// Build a new authorized API client service.
				//final String spreadsheetid = "1FtaJ_U-mR2bJ_23ineFIpMiFMEDSEq7VvUTUgEfVw7A";
				final String spreadsheetid = SheetID;//"1QgubwCpvrBv37--Fr8Ktf6UHjaJP5Kc0O_hD7IVPxmQ";
				final String range = sheetRange;
				
				
				ValueRange response = service.spreadsheets().values()
				        .get(spreadsheetid, range)
				        .execute();
      
				List<List<Object>> values = response.getValues();
				
				if (values == null || values.isEmpty()) {
				    //System.out.println("No data found.");
				} else {
				    for (List row : values) {
				    	value = row.get(0).toString();
				    	return value;
				    }
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return value;
	    }
	
	public static void setValue(String SheetID, String sheetRange, String value) {
		String result = value;
	    	try {
				//String spreadsheetid = "1FtaJ_U-mR2bJ_23ineFIpMiFMEDSEq7VvUTUgEfVw7A";
				String spreadsheetid = SheetID;//"1QgubwCpvrBv37--Fr8Ktf6UHjaJP5Kc0O_hD7IVPxmQ";
				String writeRange = sheetRange;
				

				List<List<Object>> writeData = new ArrayList<List<Object>>();

				List<Object> dataRow = new ArrayList<Object>();
				dataRow.add(result);
				writeData.add(dataRow);
  
				ValueRange vr = new ValueRange().setValues(writeData).setMajorDimension("ROWS");
				service.spreadsheets().values()
				    .update(spreadsheetid, writeRange, vr)
				    .setValueInputOption("RAW")
				    .execute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
	
	public static String lastValues(String SheetID, String sheetRange) {
		String size = null;
		try {
			// Build a new authorized API client service.
			   final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			   final String spreadsheetid = SheetID;
			   final String range = sheetRange;
			   
			   Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
			           .setApplicationName(APPLICATION_NAME)
			           .build();
			   ValueRange response = service.spreadsheets().values()
			           .get(spreadsheetid, range)
			           .execute();
			 
			   //System.out.println("Colum: "+response.getRange());
			   size = response.getRange();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}
	
	/*public static void main(String []args) throws Exception{
	    	
	    	System.out.println(lastValues("Fixed_ePUB!B2:B"));
	    }*/
}