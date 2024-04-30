package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constant;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	private WebDriver driver;
	private ElementUtil eleutil;
private By searchlocator=By.xpath("//div[@id='search']//input[@name='search']");
private By searchButton=By.xpath("//div[@id='search']//button");
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
public String getAccountPageTitle()
{
	return eleutil.waitForTitleIs(Constant.ACCOUT_PAGE_TITLE, 5);
}
public String getAccountPageUrl() {
	return eleutil.waitForURLContains(Constant.ACCOUNT_PAGE_URL_FRACTION, 5);
}
public SearchPage search(String searchkey) {
	eleutil.doSendKeys(searchlocator, searchkey);
	eleutil.doClick(searchButton, 5);
	return new SearchPage(driver);
	
}


}
