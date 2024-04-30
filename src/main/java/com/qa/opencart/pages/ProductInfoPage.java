package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class ProductInfoPage {
	private WebDriver driver;
	private Map<String, String> pdtData = new HashMap<String, String>();
	private ElementUtil eleutil;
	private By pdtHeader = By.tagName("h1");
	private By imageCount = By.xpath("//div[@id='content']//img");
	private By pdtMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private By pdtPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	@Step("getting pdt header")
	public String productHeader() {
		String pdtHeadertxt = eleutil.doGetElementText(pdtHeader);
		return pdtHeadertxt;
	}
	@Step("getting pdt imagecount")
	public int productSearchImageCount() {
		int count = eleutil.waitForElementsVisible(imageCount, 10).size();
		return count;
	}
	@Step("getting productmetaDataDetails")
	public void productmetaDataDetails() {
		List<String> getData = eleutil.getElementsTextList(pdtMetaData);
		for (int i = 0; i < getData.size(); i++) {
			String data = getData.get(i);
			pdtData.put(data.split(":")[0].trim(), data.split(":")[1].trim());
		}
	}
	@Step("getting productpriceDetails")
	public void productpriceDetails() {
		List<String> getData = eleutil.getElementsTextList(pdtPriceData);
		pdtData.put("Price", getData.get(0));
		String data = getData.get(1);
		pdtData.put(data.split(":")[0].trim(), data.split(":")[1].trim());
	}
	@Step("getting pdtDeatilsMap")
	public Map<String, String> pdtDeatilsMap() {
		pdtData.put("ProductHeader", productHeader());
		productmetaDataDetails();
		productpriceDetails();
		return pdtData;
	}
}
