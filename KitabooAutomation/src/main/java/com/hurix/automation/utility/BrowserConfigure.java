package com.hurix.automation.utility;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;

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
				System.out.println("Safari Browser Start...");
				Driver.driver = new SafariDriver();
				Driver.driver.manage().window().maximize();
				break;
			
			case "Firefox":
				System.out.println("Firefox Browser Starting...");
				WebDriverManager.firefoxdriver().setup();
				Driver.driver = new FirefoxDriver();
				Driver.driver.manage().window().maximize();
				break;
				
			case "Internet Explorer":
				System.out.println("Internet Explorer Browser Starting...");
				WebDriverManager.iedriver().setup();
				Driver.driver = new InternetExplorerDriver();
				Driver.driver.manage().window().maximize();
				break;

			case "Chrome":
				System.out.println("Chrome Browser Starting...");
				ChromeOptions options = new ChromeOptions();
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				//chromePrefs.put("download.default_directory", "/pathToDownloadFolder");
				chromePrefs.put("download.prompt_for_download", false);
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
				System.out.println("Driver crashed while execution!");
				break;
			}
			System.out.println("Browser Launched...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
