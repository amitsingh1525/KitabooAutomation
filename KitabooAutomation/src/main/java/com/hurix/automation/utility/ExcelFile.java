package com.hurix.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelFile {

	private static Sheet ExcelWSheet;
	private static Workbook ExcelWBook;
	private static Cell Cell;
	private static Row Row;
	static String excel_Path = null;

	public static void setExcelFile(String filePath, String SheetName) throws Exception {
		try {
			FileInputStream inputStream = new FileInputStream(new File(filePath));
			ExcelWBook = getWorkbook(inputStream, filePath);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e){
			throw (e);
		}
	}
	
	public static String getCellData(int RowNum, int ColNum) throws Exception{
		try{
			DataFormatter df = new DataFormatter();
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = df.formatCellValue(Cell);
			return CellData;
		}catch (Exception e){
			return""+e.getMessage();
		}
	}


	//This method is to write in the Excel cell, Row num and Col num are the parameters
	public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception    {
		try{
			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}

			// Constant variables Test Data path and Test Data file name
			File ExcelfilepathForMailCredential= new File("");
			FileOutputStream fileOut = new FileOutputStream(ExcelfilepathForMailCredential.getAbsolutePath()+excel_Path);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}catch(Exception e){
			throw (e);
		}
	}


	public static int getRowContains(String sTestCaseName, int colNum) throws Exception{
		int i;
		try {
			int rowCount = ExcelFile.getRowCount();
			for ( i=0 ; i<rowCount; i++){
				if  (ExcelFile.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){
					break;
				}
			}
			return i;
		}catch (Exception e){
			Log.error("Class ExcelUtil | Method getRowContains | Exception desc : " + e.getMessage());
			throw(e);
		}
	}

	public static int getRowCount() throws Exception {
		try{
			int RowCount = ExcelWSheet.getLastRowNum();
			Log.info("Total number of Row used return as < " + RowCount + " >.");		
			return RowCount;
		}catch (Exception e){
			Log.error("Class ExcelUtil | Method getRowUsed | Exception desc : "+e.getMessage());
			System.out.println(e.getMessage());
			throw (e);
		}

	}

	private static Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

}
