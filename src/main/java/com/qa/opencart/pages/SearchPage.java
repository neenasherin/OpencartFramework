package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class SearchPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	private By searchHeaderLocator = By.xpath("//h1[contains(text(),'Search')]");
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	@Step("get se4arch header msg")
	public String searchHeaderMsg() {
		return eleutil.doGetElementText(searchHeaderLocator).toString();
	}
	@Step("get productSearch by pdt name {0}")
	public ProductInfoPage productSearch(String pdtName) {
		eleutil.doClick(By.linkText(pdtName),10);
		return new ProductInfoPage(driver);
		
	}
}
