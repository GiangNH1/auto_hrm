package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.DashBoardPO;
import PageObjects.EmployeePO;
import PageObjects.LoginPO;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;

public class Employee extends BaseTest{
	WebDriver driver;
	LoginPO loginPage;
	DashBoardPO dashBoardPage;
	EmployeePO employeePage;
	String firstName, lastName, phone, email, userName, password, officeShift, role, room, vaiTro, salaryBasic, hour, avatarImg;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		
		log.info("Pre-Condition - step 01 Open page with '"+ browserName + "'");
		driver = getBrowserDriverManager(browserName, url);
		log.info("Pre-Condition - step 02: Initialization 'Login' page");
		loginPage = PageGeneratorManager.getAdminLogin(driver);
		
		firstName = "Automation"; 
		lastName = "Testing"; 
		phone = "1234567555"; 
		email = "autotesting" + GlobalConstants.GENERATE_FAKE_NUMBER() + "@gmail.com"; 
		userName = "autotesting" + GlobalConstants.GENERATE_FAKE_NUMBER();
		password = "123456"; 
		officeShift = "Office Shift 01"; 
		role = "Nhân viên"; 
		room = "QA"; 
		vaiTro = "Manual";
		salaryBasic = "40000000"; 
		hour = "70";
		avatarImg = "cu-cai-1.jpg";
		
	}
	
	@Test
	public void TC_02_Add_Employee() {
		
		log.info("Login As Admin - step 01: Enter to userName with 'admin01'");
		enterToTextboxByID(driver, "iusername", "admin01");
		log.info("Login As Admin - step 02: Enter to Password with '123456'");
		enterToTextboxByID(driver, "ipassword", "123456");
		log.info("Login As Admin - step 03: Click to login button");
		dashBoardPage = loginPage.clickLoginToButton();
		//loginPageCookie = dashBoardPage.getAllCookie(driver);
		log.info("Login As Admin - step 04: Verify login success");
		verifyTrue(dashBoardPage.isDisplayLoginSuccess());
		
		dashBoardPage = PageGeneratorManager.getDashBoardPage(driver);
		log.info("Add Employee - step 01: Open page Employee");
		dashBoardPage.openMenuPage(driver, "Nhân viên");
		employeePage = PageGeneratorManager.getEmployeePage(driver);
		
		log.info("Add Employee - step 02: Verify List Employee");
		//verifyEquals(employeePage.checkListEmployee(), "4");
		
		log.info("Add Employee - step 03: Click to 'Thêm mới'");
		employeePage.clickToLinkPage(driver, "Thêm mới");
		
		log.info("Add Employee - step 04: Sendkey to 'Tên' '" + firstName + "'");
		employeePage.enterToTextboxByName(driver, "first_name", firstName);
		
		log.info("Add Employee - step 05: Sendkey to 'Họ' '" + lastName  + "'");
		employeePage.enterToTextboxByName(driver, "last_name", lastName);
		
		log.info("Add Employee - step 06: Sendkey to 'Số điện thoại' '" + phone  + "'");
		employeePage.enterToTextboxByName(driver, "contact_number", phone);
		
		log.info("Add Employee - step 07: Sendkey to 'Email' '" + email  + "'");
		employeePage.enterToTextboxByName(driver, "email", email);
		
		log.info("Add Employee - step 08: Sendkey to 'Tên người dùng' '" + userName  + "'");
		employeePage.enterToTextboxByName(driver, "username", userName);
		
		log.info("Add Employee - step 09: Sendkey to 'Mật khẩu' '" + password  + "'");
		employeePage.enterToTextboxByName(driver, "password", password);
		
		log.info("Add Employee - step 10: Selected to 'Office Shift' '" + officeShift  + "'");
		employeePage.seletedItemCustomByName(driver, "Office Shift", officeShift);
		
		log.info("Add Employee - step 11: Selected to 'Vai trò' '" + role  + "'");
		employeePage.seletedItemCustomByName(driver, "Vai trò", role);
		
		log.info("Add Employee - step 12: Selected to 'Khoa' '" + room  + "'");
		employeePage.seletedItemCustomByName(driver, "Khoa", room);
		employeePage.sleepInSecond(2);
		
		log.info("Add Employee - step 13: Selected to 'Chỉ định' '" + vaiTro  + "'");
		employeePage.seletedItemCustomByName(driver, "Chỉ định", vaiTro);
		
		log.info("Add Employee - step 14: Sendkey to 'Mức Lương Cơ Bản' '" + salaryBasic  + "'");
		employeePage.enterToTextboxByName(driver, "basic_salary", salaryBasic);
		
		log.info("Add Employee - step 15: Sendkey to 'Tỷ lệ hàng giờ' '" + hour  + "'");
		employeePage.enterToTextboxByName(driver, "hourly_rate", hour);
		
		log.info("Add Employee - step 16: Upload 'Avatar' '" + avatarImg  + "'");
		employeePage.uploadMultipalFiles(driver, avatarImg);
		
		
		log.info("Add Employee - step 17: click to button 'Lưu' '");
		employeePage.clickToButtonByText(driver, "Lưu");
		
		
		verifyEquals(employeePage.verifyEmployeeNew(email), email);
		employeePage.hoverToClickEmployeeNew(email);
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
