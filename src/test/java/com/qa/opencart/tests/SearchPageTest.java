package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.SearchPage;

public class SearchPageTest extends BaseTest {
	AccountPage accPage;
	SearchPage searchPage;
	@BeforeClass
	public void accSetup() {
		accPage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@DataProvider
		 public Object[][] productDetail(){
			 return new Object[][] {{"macbook","Search - macbook"},
				 {"samsung","Search - samsung"}
			 
			 };
		     }

	@Test(dataProvider = "productDetail")
	public void searchHeardMsg(String searchKey,String pdtHeader) {
		searchPage=accPage.search(searchKey);
		Assert.assertEquals(searchPage.searchHeaderMsg(), pdtHeader);
		
	}

}
