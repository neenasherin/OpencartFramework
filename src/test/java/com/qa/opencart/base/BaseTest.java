package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchPage;

import io.qameta.allure.Step;

public class BaseTest {
	DriverFactory df;
	protected Properties prop;
	WebDriver driver;
	protected LoginPage loginpage;
	protected AccountPage accPage;
	protected SearchPage searchPage;
	protected ProductInfoPage pdtInfoPage;
	@Step("Browser setup {0} and initt properties")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		prop=df.initProp();	
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);

	}
	@Step("closing browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
