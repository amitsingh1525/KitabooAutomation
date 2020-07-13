package com.hurix.automation.utility;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public  class BrowserConfigure  {
	
	public static  void browserConfigure(String BrowserConfig)
	{
		try {
			switch (BrowserConfig) {
			case "Safari":
				Log.info("Safari Browser Start...");
				Driver.driver = new SafariDriver();
				Driver.driver.manage().window().maximize();
				break;
			
			case "Firefox":
				Log.info("Firefox Browser Starting...");
				WebDriverManager.firefoxdriver().setup();
				Driver.driver = new FirefoxDriver();
				Driver.driver.manage().window().maximize();
				break;
				
			case "Internet Explorer":
				Log.info("Internet Explorer Browser Starting...");
				WebDriverManager.iedriver().setup();
				Driver.driver = new InternetExplorerDriver();
				Driver.driver.manage().window().maximize();
				break;

			case "Chrome":
				Log.info("Chrome Browser Starting...");
				ChromeOptions options = new ChromeOptions();
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("download.default_directory", "/pathToDownloadFolder");
				chromePrefs.put("download.prompt_for_download", true);
				chromePrefs.put("download.directory_upgrade", false);
				chromePrefs.put("safebrowsing.enabled", true);
				options.setExperimentalOption("prefs", chromePrefs);
				
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("--ignore-ssl-errors");
			 	options.addArguments("test-type");
			 	options.addArguments("disable-popup-blocking");
			 	WebDriverManager.chromedriver().setup();
				Driver.driver = new ChromeDriver(options);
				Driver.driver.manage().window().maximize();
				break;
				
			default:
				Log.error("Driver crashed while execution!");
				break;
			}
			Log.info("Browser Launched...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
