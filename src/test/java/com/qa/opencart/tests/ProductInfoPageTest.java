package com.qa.opencart.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchPage;

public class ProductInfoPageTest extends BaseTest {
	AccountPage accPage;
	SearchPage searchPage;
	ProductInfoPage pdtInfoPage;

	@BeforeClass
	public void accSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] productSearchImageDetails() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "samsung", "Samsung SyncMaster 941BW", 1 },
				{ "samsung", "Samsung Galaxy Tab 10.1", 7 } };
	}
	@DataProvider
	public Object[][] productSearchHeaderDetails() {
		return new Object[][] { { "macbook","MacBook Pro", "MacBook Pro"}, { "samsung", "Samsung SyncMaster 941BW", "Samsung SyncMaster 941BW" },
				{ "samsung", "Samsung Galaxy Tab 10.1","Samsung Galaxy Tab 10.1"} };
	}

	@Test(priority = 1, dataProvider = "productSearchImageDetails")
	public void getProductSearchImageCount(String searchKey, String pdtName, int count) {
		searchPage = accPage.search(searchKey);
		pdtInfoPage = searchPage.productSearch(pdtName);
		Assert.assertEquals(pdtInfoPage.productSearchImageCount(), count);
	}

	@Test(priority = 2 ,dataProvider = "productSearchHeaderDetails")
	public void getProductSeacrhHeader(String searchKey,String pdtName, String pdtHeader) {
		searchPage = accPage.search(searchKey);
		pdtInfoPage = searchPage.productSearch(pdtName);
		Assert.assertEquals(pdtInfoPage.productHeader(), pdtHeader);
	}
	@Test
	public void getpdtMetaData() {
		searchPage = accPage.search("macbook");
		pdtInfoPage = searchPage.productSearch("MacBook Pro");
		 Map<String, String> pdtDataMap =pdtInfoPage.pdtDeatilsMap();
		 Assert.assertEquals(pdtDataMap.get("Brand"), "Apple");
	}
}
