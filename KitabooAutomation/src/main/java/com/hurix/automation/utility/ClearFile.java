package com.hurix.automation.utility;

import java.io.File;

public class ClearFile {
	private static String file_Path;

	public static void clearfile() throws Exception
	{
		try 
		{
			
			String clearFilepath = "\\TestData\\Download";
			File ExcelfilepathForMailCredential= new File("");
		   	file_Path = clearFilepath;
		   	File folder = new File(ExcelfilepathForMailCredential.getAbsolutePath()+file_Path);
		   
			File[] listOfFiles = folder.listFiles();
	
			for (int i = 0; i < listOfFiles.length; i++) 
			{
				 if (listOfFiles[i].isFile()) 
				 {
					 String path = listOfFiles[i].getAbsolutePath();
					 System.out.println("File: " + path);
					 File deleteFile = new File(path);
					 if(deleteFile.exists())
					 {
						 deleteFile.delete();
					 }
				 }
			}
			 Log.info("Folder Clear");
		}
		catch(Exception e) {
			System.out.println("Fail:  "+e);
		}
	}


}
