package com.checkers.utils.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
	private static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {
	        String projectPath = System.getProperty("user.dir");
	        String driverPath = projectPath + "\\Drivers\\chromedriver.exe";
	        System.setProperty("webdriver.chrome.driver", driverPath);

	        // Create ChromeOptions
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--remote-allow-origins=*");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--no-sandbox");
	        options.addArguments("--window-size=1280,1000");
	        // options.addArguments("--headless=new"); // Uncomment for headless mode

	        driver = new ChromeDriver(options); // ✅ pass options
	        driver.manage().window().maximize();
	    }
	    return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("Driver already closed.");
			}
		}
	}
}
