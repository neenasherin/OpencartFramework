package com.qa.opencart.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	private By fpwd = By.linkText("Forgotten Password");
	private By usernamelocator = By.id("input-email");
	private By pswd = By.id("input-password");
	private By loginbutton=By.xpath("//input[@value='Login']");
	@Step("getting login page tiltle")
	public String getLoginTitle() {
		return eleutil.waitForTitleIs("Account Login", 5);

	}
	@Step("getting login page url")
	public String getUrl() {
		return eleutil.waitForURLContains("route=account/login", 5);
	}
	@Step("getting forgt pwd link visible or not")
	public boolean forgtpwdVisible() {
		return eleutil.isElementDisplayed(fpwd);
	}
	@Step("Login with Username: {0} and pwd: {1}")
	public AccountPage doLogin(String username,String pwd) {
		eleutil.doSendKeys(usernamelocator, username);
		eleutil.doSendKeys(pswd, pwd);
		eleutil.doClick(loginbutton, 5);
		return new AccountPage(driver);
	}

}
