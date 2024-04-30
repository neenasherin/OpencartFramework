package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constant;
import com.qa.opencart.pages.AccountPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic no:200 Opencart login page")
@Feature("Feature !002 Login page Feature")
@Story("US no:!002")
public class AccountPageTest extends BaseTest {
	AccountPage accPage;
	@BeforeClass
	public void accSetup() {
		accPage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Description("Checking Account page title")
	@Severity(SeverityLevel.MINOR)
		@Test(priority = 1)
		public void getAccountpageTitile() {

			Assert.assertEquals(accPage.getAccountPageTitle(), "My Account");
		}
	@Description("Checking Account page URL")
	@Severity(SeverityLevel.MINOR)
		@Test(priority = 2)
		public void getAccountpageUrl() {
			Assert.assertTrue((accPage.getAccountPageUrl()).contains(Constant.ACCOUNT_PAGE_URL_FRACTION));
					
		}
	@Description("Checking Search pdt header")
	@Severity(SeverityLevel.CRITICAL)
		@Test(priority = 3)
public void doSearch() {
			searchPage=accPage.search("macbook");
			Assert.assertEquals(searchPage.searchHeaderMsg(),"Search - macbook");
}
}
