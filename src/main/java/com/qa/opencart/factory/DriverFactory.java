package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.exceptions.BrowserExceptions;
import com.qa.opencart.exceptions.FrameworkExceptions;
import com.qa.opencart.logger.Log;

import io.qameta.allure.Step;

public class DriverFactory {
	WebDriver driver;
	String title;
	Properties prop;
	OptionManager optionManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		highlight = prop.getProperty("highlight");
		Log.info("Browser name " + browserName);
		optionManager = new OptionManager(prop);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tldriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver(optionManager.getFirfoxOptions());
			tldriver.set(new FirefoxDriver(optionManager.getFirfoxOptions()));
			break;
		default:
			Log.error("-----Plz pass correct browser------" + browserName);
			throw new BrowserExceptions("Browser not found");

		}
		getdriver().manage().deleteAllCookies();
		getdriver().manage().window().maximize();
		getdriver().get(prop.getProperty("url"));
		return getdriver();
	}
	public static WebDriver getdriver() {
		return tldriver.get();
	}

	public Properties initProp() {
		// mvn clean install -Denv="qa"
		FileInputStream ip = null;
		prop = new Properties();
		String envName = System.getProperty("env");
		Log.info("Enviornment is " + envName);
		if (envName == null) {
			Log.info("No Enviornment so running in QA");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			switch (envName.toLowerCase().trim()) {

			case "qa":
				try {
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case "dev":
				try {
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "stage":
				try {
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "prod":
				try {
					ip = new FileInputStream("./src/test/resources/config/config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				Log.error("-----File not Found------");
				throw new FrameworkExceptions("-----File not Found------");

			}
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}
	
	/**
	 * take screenshot
	 */
	@Step("taking Scrteenshot for failed method {0}")
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getdriver()).getScreenshotAs(OutputType.FILE);//temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	
}
