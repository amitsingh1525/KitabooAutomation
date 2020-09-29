package com.hurix.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFile {
	
	private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;
    static String excel_Path = null;
    
//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
public static void setExcelFile(String Path,String SheetName) throws Exception {
       try {
           	// Open the Excel file
    	   	File ExcelfilepathForMailCredential= new File("");
    	   	excel_Path = Path;
    	   	FileInputStream fileinput=new FileInputStream(ExcelfilepathForMailCredential.getAbsolutePath()+excel_Path);
    	   	// Access the required test data sheet
    	   	ExcelWBook = new XSSFWorkbook(fileinput);
    	   	ExcelWSheet = ExcelWBook.getSheet(SheetName);
    	   	System.out.println(ExcelfilepathForMailCredential.getAbsolutePath()+excel_Path);
    	   	Log.info("Excel sheet opened");
        	} catch (Exception e){
            throw (e);
        	}
}
//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
public static String getCellData(int RowNum, int ColNum) throws Exception{
       try{
    	   DataFormatter df = new DataFormatter();
    	  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
    	  String CellData = df.formatCellValue(Cell);
          //String CellData = Cell.toString();
          return CellData;
          }catch (Exception e){
            return"";
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

}
