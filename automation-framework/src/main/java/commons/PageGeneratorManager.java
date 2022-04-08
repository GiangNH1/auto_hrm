package commons;

import org.openqa.selenium.WebDriver;

import PageObjects.DashBoardPO;
import PageObjects.EmployeePO;
import PageObjects.LoginPO;

public class PageGeneratorManager {
	
	public static DashBoardPO getDashBoardPage(WebDriver driver){
		return new DashBoardPO(driver);
	}
	
	public static LoginPO getAdminLogin(WebDriver driver) {
		return new LoginPO(driver);
	}
	
	public static EmployeePO getEmployeePage(WebDriver driver) {
		return new EmployeePO(driver);
	}
	
}
