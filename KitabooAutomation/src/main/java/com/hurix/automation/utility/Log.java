package com.hurix.automation.utility; 

import java.io.File;
import java.time.Instant;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;
 
public class Log {
	
    static ExtentReports extent;
    private static ExtentTest test;
    private static Logger Log = Logger.getLogger(Log.class.getName()); 
     
	public static void initialization(String logFileName){
	 	try {
			String log_Path = "/logs/"+logFileName+".html";
			extent = new ExtentReports(System.getProperty("user.dir")+log_Path, true, NetworkMode.OFFLINE);
			extent.loadConfig(new File(System.getProperty("user.dir")+"/config/extentConfig/extent-config.xml"));
			System.out.println(System.getProperty("user.dir")+log_Path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  
	public static void startTestCase(String sTestCaseName){
		try {
			Log.info("****************************************************************************************");
			Log.info("****************************************************************************************");
			Log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
			Log.info("****************************************************************************************");
			Log.info("****************************************************************************************");
			test = extent.startTest(sTestCaseName, "Sample description");
			extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void endTestCase(String message){
		try {
			Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX\n \n \n");
			
			extent.endTest(test);
			extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void pass(String message){
		test.log(LogStatus.PASS, message);
		extent.flush();
	}
	public static void info(String message) {
		try {
			Log.info(message);
			test.log(LogStatus.INFO, message);
			extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void warn(String message) {
		test.log(LogStatus.WARNING, message);
		extent.flush();
	}
	public static void error(String message) {
		try {
			//Log.error(message);
			
			File file = new File("");
			
			long now = Instant.now().toEpochMilli();
			String scr_Path = "/logs/screenshots/"+now+".jpg";
			TakesScreenshot scrShot =((TakesScreenshot) Driver.driver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(file.getAbsolutePath()+scr_Path);
			FileUtils.copyFile(SrcFile, DestFile);
			
			//BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			//ImageIO.write(image, "png", new File(file.getAbsolutePath()+scr_Path));
			
			test.log(LogStatus.ERROR, message);
			test.log(LogStatus.ERROR, "Find the Screenshot below: " + test.addScreenCapture(file.getAbsolutePath()+scr_Path));
			extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void errorAPI(String message) {
		try {
			test.log(LogStatus.ERROR, message);
			extent.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void fatal(String message) {
		test.log(LogStatus.FATAL, message);
		extent.flush();
	}
	public static void fail(String message){
		test.log(LogStatus.FAIL, message);
		extent.flush();
	}
	public static void debug(String message) {
		test.log(LogStatus.UNKNOWN, message);
		extent.flush();
	}

	public static void main(String args[]){
		System.out.println("hey");
		initialization("TestingLog");
		startTestCase("Testing_1");
		info("Step - 1");
		info("Step - 2");
		error("Step - 3");
		endTestCase("done");
		
		startTestCase("Testing_2");
		info("Step - 1");
		fail("Step - 2");
		error("Step - 3");
		endTestCase("done");
		
		startTestCase("Testing_3");
		info("Step - 1");
		info("Step - 2");
		info("Step - 3");
		endTestCase("done");
		
	}
}
