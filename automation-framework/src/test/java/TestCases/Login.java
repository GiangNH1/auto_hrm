package TestCases;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.DashBoardPO;
import PageObjects.EmployeePO;
import PageObjects.LoginPO;
import commons.BaseTest;
import commons.PageGeneratorManager;

public class Login extends BaseTest{
	WebDriver driver;
	LoginPO loginPage;
	DashBoardPO dashBoardPage;
	EmployeePO employeePage;
	public static Set<Cookie> loginPageCookie;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		
		log.info("Pre-Condition - step 01 Open page with '"+ browserName + "'");
		driver = getBrowserDriverManager(browserName, url);
		log.info("Pre-Condition - step 02: Initialization 'Login' page");
		loginPage = PageGeneratorManager.getAdminLogin(driver);
	}
	
	@Test
	public void TC_01_LoginAsAdmin() {
		log.info("Login As Admin - step 01: Enter to userName with 'admin01'");
		enterToTextboxByID(driver, "iusername", "admin01");
		log.info("Login As Admin - step 02: Enter to Password with '123456'");
		enterToTextboxByID(driver, "ipassword", "123456");
		log.info("Login As Admin - step 03: Click to login button");
		dashBoardPage = loginPage.clickLoginToButton();
		loginPageCookie = dashBoardPage.getAllCookie(driver);
		log.info("Login As Admin - step 04: Verify login success");
		verifyTrue(dashBoardPage.isDisplayLoginSuccess());
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
