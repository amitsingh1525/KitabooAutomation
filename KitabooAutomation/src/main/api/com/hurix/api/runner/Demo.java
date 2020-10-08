package com.hurix.api.runner;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;


public class Demo {
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Date dateobj = new Date();
	//System.out.println(df.format(dateobj));
	//public static long startDate1 = EpochTime.getEpochTime("df.format(dateobj");
	public static long startDate ;//EpochTime.getEpochTime("2019/10/31 14:46:04");
	public static long startIndex = 0;
	public static long endIndex = 100;
	//public static int level;
	public static String assetType;
	public static int level;
	public static String numberOfBooks;
	public static String userToken = "";
	public static int BookID_mark1;
	public static int BookID_mark2;
	public static int BookID_mark3;
	public static int  bookID1;
	public static int bookID2;
	public static int bookID3;
	public static int bookID6;
	public static String isbn;
	public static String isbnMeta;
	public static String isbnIng;
	public static String forName;
	public static String responseMsg;
	public static String content_ownership;
	public static int userID;
	public static int client_Id;
	public static int totalbooks;
	public static int total;
	public static String archiveDate;
	public static String archiveDate1;
	public static String archiveDate2;
	public static String operation0;
	public static String operation1;
	public static String ebookID1;
	public static String catname;
	public static String categoriesname;
	public static String collectionName0;
	public static String collectionName1;
	public static String catname1;
	public static int totalCategories;
	public static String clientUserID;
	public static String category1;
	public static String clientBookID;
	public static String search = "Native";	
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static String environMent;
	public static String userName;
	public static String password;
	public static String detail;
	public static String sqlhost;
	public static String sqlUsername;
	public static String sqlPassword;
	public static String externalURI;
	public static String clientID;
	public static String catlevel;
	public static String  deviceT;
	public static int type;
	public static String isbn1;
	public static String searchV2TEXT;
	public static String isbn3;
	public static String isbn4;
	public static String isbn5;
	public static String isbn6;
	public static String isbn7;
	public static String isbn8;
	public static String isbn9;
	public static String isbn10;
	public static String isbn11;
	public static String  email;
	public static String  firstName;
	public static String  lastName;
	public static String consumerKey;
	public static String consumerSecret;
	public static Object categoryIdList0;
	public static Object categoryIdList1;
	public static Object categoryIdList2;
	public static Object categoryIdList3;
	public static Object categoryIdList4;
	public static String category;
	public static Object categoryList;
	public static Object categoryIdList;
	public static String title;
	public static String runY_N;
	public static void   main(String []args) throws SQLException, JSONException, IOException{
		String finalEmailID = "";
		String excelPath="./testData/Sprint33.1/DIS-2137.xlsx";
		workbook = new XSSFWorkbook(excelPath);
		sheet= workbook.getSheet("Register_DIS-2137");
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{DataFormatter formatter = new DataFormatter();
		environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
		userName = formatter.formatCellValue(sheet.getRow(i).getCell(1));			
		password = formatter.formatCellValue(sheet.getRow(i).getCell(2));
		catlevel = formatter.formatCellValue(sheet.getRow(i).getCell(3));
		deviceT = formatter.formatCellValue(sheet.getRow(i).getCell(4));				
		runY_N=formatter.formatCellValue(sheet.getRow(i).getCell(5));

		firstName= formatter.formatCellValue(sheet.getRow(i).getCell(7));
		lastName= formatter.formatCellValue(sheet.getRow(i).getCell(8));
		for(int i2=1; i2<=10;i2++)
		{
			
			String email= formatter.formatCellValue(sheet.getRow(i2).getCell(6));
			finalEmailID = finalEmailID+"\""+email+"\""+",";
			
			

		//}
		}
		}
		System.out.println(finalEmailID);
		/*Response registerUSER = BulkUsersRegistration.bulkUsersRegistration(consumerKey, consumerSecret, finalEmailID);
		Validation.responseHeaderCodeValidation(registerUSER, HttpStatus.SC_OK);
		Validation.responseCodeValidation1(registerUSER, HttpStatus.SC_OK);
		Validation.responseTimeValidation(registerUSER);
		Validation.responseKeyValidation_key(registerUSER, "id");}*/
		//Validation.responseINTEGERKeyAndValue(registerUSER, "responseMsg", "OK");
		
	}

}
