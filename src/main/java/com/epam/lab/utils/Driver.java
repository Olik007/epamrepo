package com.epam.lab.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Driver {
	private static final String BROWSER_NAME = "BROWSER_NAME";
	private static final String DRIVER_NAME = "DRIVER_NAME";
	private static final String DRIVER_LOCATION = "DRIVER_LOCATION";
	private static final String IMPLICITLY_WAIT = "IMPLICITLY_WAIT";
	private static final String URL = "URL";
	private static final Logger logger = Logger.getLogger(Driver.class);
	
	private static Properties prop;
	private Driver(){};

	public static WebDriver getDriver(String driverName) throws IOException {
		prop = new PropertiesLoader().getProperties(driverName + ".properties");
		return threadLocalScope.get();
	}

	private static WebDriver initDriver() throws Exception {		
		System.setProperty(prop.getProperty(DRIVER_NAME), prop.getProperty(DRIVER_LOCATION));
		WebDriver webDriver;
		switch (prop.getProperty(BROWSER_NAME)) {
		case "chrome":
			logger.info("Chrome driver set");
			webDriver = new ChromeDriver();					
			break;
		case "firefox":
			logger.info("Firefox driver set");
			webDriver = new FirefoxDriver();		
			break;
		default:
			throw new Exception("No valid driver for your choice");
		}	
		webDriver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
		webDriver.get(prop.getProperty(URL));
		return webDriver;
	}

	 public static void quitDriver(){
		 logger.info("close WebDriver");
		 threadLocalScope.get().quit();
		 threadLocalScope.remove();
	 }

	private static final ThreadLocal<WebDriver> threadLocalScope = new ThreadLocal<WebDriver>() {
		@Override
		protected WebDriver initialValue() {

			WebDriver driver = null;
			try {
				driver = initDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return driver;
		}
	};

}
