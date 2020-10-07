package com.hurix.automation.utility;

import java.util.ArrayList;
import java.util.List;

public class Credentials {

	public static String URL = "";
	
	private static String readerExcel = "\\testData\\readerConfig.xlsx";
	private static String platformExcel = "\\testData\\platformConfig.xlsx";
	private static String authoringExcel = "\\testData\\authoringConfig.xlsx";
	
	public static List<String> getCredentials(String product, String module){

		List<String> credential = new ArrayList<>();
		
		try {

			String filePath = "";
			if(product.equalsIgnoreCase("reader")) {
				filePath = System.getProperty("user.dir")+readerExcel;
			}else if(product.equalsIgnoreCase("platform")) {
				filePath = System.getProperty("user.dir")+platformExcel;
			}else if(product.equalsIgnoreCase("authoring")) {
				filePath = System.getProperty("user.dir")+authoringExcel;
			}

			ExcelFile.setExcelFile(filePath, "userConfig");
			String env = ExcelFile.getCellData(0, 1);

			String username = "";
			String password = "";
			
			int lastRow = ExcelFile.getRowCount();
			for(int i=9; i<=lastRow; i++) {
				String envi = ExcelFile.getCellData(i, 2);
				String modu = ExcelFile.getCellData(i, 3);
				if(envi.equalsIgnoreCase(env) && modu.equalsIgnoreCase(module)) {
					URL = ExcelFile.getCellData(i, 4);
					username = ExcelFile.getCellData(i, 5);
					password = ExcelFile.getCellData(i, 6);
					break;
				}
			}
			credential.add(username);
			credential.add(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return credential;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
			getCredentials("platform", "kitaboo");
	}

}
