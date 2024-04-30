package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager {
	private ChromeOptions co;
	private FirefoxOptions fo;
	private Properties prop;
	public OptionManager(Properties prop) {
		this.prop=prop;
	}
	 public ChromeOptions getChromeOptions() {
		 co=new ChromeOptions();
		 if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			 co.addArguments("--headless");
		 }
		 if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			 co.addArguments("--incognito");
		 }
		 return co;
	 }
	 public FirefoxOptions getFirfoxOptions() {
		 fo=new FirefoxOptions();
		 if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			 fo.addArguments("--headless");
		 }
		 if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			 fo.addArguments("--incognito");
		 }
		 return fo;
	 }
}
