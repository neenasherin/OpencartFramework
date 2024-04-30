package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constant;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic no:100 Opencart login page")
@Feature("Feature !001 Login page Feature")
@Story("US no:!001")
public class LoginPageTest extends BaseTest {
	@Description("Checking login page title")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void getTitile() {

		Assert.assertEquals(loginpage.getLoginTitle(),Constant.LOGIN_PAGE_TITLE);
	}
	@Description("Checking login page url")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void getUrl() {
		Assert.assertTrue((loginpage.getUrl().contains(Constant.LOGIN_PAGE_URL_FRACTION)));
	}
	@Description("Checking forgot pwd link")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void chkfpwdVisible() {
		Assert.assertTrue(loginpage.forgtpwdVisible());
	}
	@Description("Checking Account page title when login")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void login() {
		accPage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(), Constant.ACCOUT_PAGE_TITLE);
	}
}
