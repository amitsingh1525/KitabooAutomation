package com.hurix.api.utility;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils
{
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static String environMent;
	public static String userName;
	public static String password;
	public static String detail;
	public static String clientID;
	public static String catlevel;
	public static String isbn1;
	public static String isbn2;
	public static String isbn3;
	public static String isbn4;
	public static String isbn5;
	public static String isbn6;
	public static String isbn7;
	public static String isbn8;
	public static String isbn9;
	public static String isbn10;
	public static String isbn11;
	public static String Consumer_key;
	public static String secret_key;	
	public static List<String> detailsisbn;

	public static void getuserDetails()
	{
		try {
			String excelPath="./testData/ExcelData.xlsx";

			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=2;i++)
			{	
				DataFormatter formatter = new DataFormatter();
				environMent=formatter.formatCellValue(sheet.getRow(i).getCell(0));
				userName=formatter.formatCellValue(sheet.getRow(i).getCell(1));			
				password=formatter.formatCellValue(sheet.getRow(i).getCell(2));
				Consumer_key=formatter.formatCellValue(sheet.getRow(i).getCell(4));
				secret_key=formatter.formatCellValue(sheet.getRow(i).getCell(5));
				clientID=formatter.formatCellValue(sheet.getRow(i).getCell(3));	
				catlevel=formatter.formatCellValue(sheet.getRow(i).getCell(6));	
				
				switch(environMent){
				case "QC":
					detail = "http://qc.kitaboo.com";
					break;
				case "Staging":
					detail = "http://qacloud.kitaboo.com";
					break;
				case "BASE_US":
					detail = "http://localhost:12346";
					break;
				case "BASE_EU":
					detail = "http://localhost:12347";
					break;
				case "PROD_US":
					detail = "http://cloud.kitaboo.com";
					break;
				case "PROD_EU":
					detail = "http://cloud.kitaboo.eu";
					break;
				}
			
				
				/*io.restassured.RestAssured.baseURI = detail;
				Response authenticateValue = Authenticate.authenticate();
				System.out.println("here");*/
			}
		}
		catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}
	
	public static List<String> getisbn()
	{
		try {
			String excelPath="./testData/ExcelData.xlsx";

			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");

			DataFormatter formatter = new DataFormatter();

			detailsisbn = new ArrayList<String>();
			for(int i=0;i<=11;i++)
			{
				if(i==0)
				{
					isbn1 = formatter.formatCellValue(sheet.getRow(2).getCell(0));
					//detailsisbn.add(isbn1);

				}
				if(i==1)
				{
					isbn2 = formatter.formatCellValue(sheet.getRow(2).getCell(1));
					//detailsisbn.add(isbn2);
				}
				if(i==2)
				{
					isbn3 = formatter.formatCellValue(sheet.getRow(2).getCell(2));
					//detailsisbn.add(isbn3);
				}
				if(i==3)
				{
					isbn4 = formatter.formatCellValue(sheet.getRow(2).getCell(3));
					detailsisbn.add(isbn4);

				}if(i==4)
				{
					isbn5 = formatter.formatCellValue(sheet.getRow(2).getCell(4));
					//detailsisbn.add(isbn5);				
				}
				if(i==5)
				{
					isbn6 = formatter.formatCellValue(sheet.getRow(2).getCell(5));
					//detailsisbn.add(isbn6);					
				}if(i==6)
				{
					isbn7 = formatter.formatCellValue(sheet.getRow(2).getCell(6));
					//detailsisbn.add(isbn7);
				}
				if(i==7)
				{
					isbn8 = formatter.formatCellValue(sheet.getRow(2).getCell(7));	
					//detailsisbn.add(isbn8);
				}
				if(i==8)
				{
					isbn9 = formatter.formatCellValue(sheet.getRow(2).getCell(8));
					//detailsisbn.add(isbn9);
				}				
				if(i==9)
				{
					isbn10 = formatter.formatCellValue(sheet.getRow(2).getCell(9));
					//detailsisbn.add(isbn10);
				}				
				if(i==10)
				{
					isbn11 = formatter.formatCellValue(sheet.getRow(2).getCell(10));	
					//detailsisbn.add(isbn11);
				}
			}	
			detailsisbn.add(isbn1);
			detailsisbn.add(isbn2);
			detailsisbn.add(isbn3);
			detailsisbn.add(isbn4);
			detailsisbn.add(isbn5);
			detailsisbn.add(isbn6);
			detailsisbn.add(isbn7);
			detailsisbn.add(isbn8);
			detailsisbn.add(isbn9);
			detailsisbn.add(isbn10);
			detailsisbn.add(isbn11);			
		}
		catch (Exception exp) 
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return detailsisbn;
	}

	public static void main( String[] args ) throws Exception
	{
		//List<String> detail =  getuserDetails();
		System.out.println(detail);
		/*System.out.println(detail.get(0));
		System.out.println(detail.get(1));
		System.out.println(detail.get(2));
		System.out.println(detail.get(3));
		System.out.println(detail.get(4));
		System.out.println(detail.get(5));
		System.out.println(detail.get(6));
		List<String> detailisbn =  getisbn();
		System.out.println(detailisbn.get(0));
		System.out.println(detailisbn.get(1));
		System.out.println(detailisbn.get(2));
		System.out.println(detailisbn.get(3));
		System.out.println(detailisbn.get(4));
		System.out.println(detailisbn.get(5));	
		System.out.println(detailisbn.get(6));
		System.out.println(detailisbn.get(7));	
		System.out.println(detailisbn.get(8));	
		System.out.println(detailisbn.get(9));
		System.out.println(detailisbn.get(10));	
		System.out.println(detailisbn.get(11));		
	*/}
}
