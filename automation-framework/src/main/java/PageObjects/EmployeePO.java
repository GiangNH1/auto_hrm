package PageObjects;

import org.openqa.selenium.WebDriver;

import PageUIs.DashBoardPageUI;
import PageUIs.EmployeePageUI;
import commons.BasePage;

public class EmployeePO extends BasePage{
	private WebDriver driver;
	
	public EmployeePO(WebDriver driver) {
		this.driver = driver;
	}

	public String checkListEmployee() {
		waitForAllElementVisible(driver, DashBoardPageUI.VERIFY_LIST_EMPLOYEE);
		int size = getElementSize(driver, DashBoardPageUI.VERIFY_LIST_EMPLOYEE);
		return String.valueOf(size);
	}

	public String verifyEmployeeNew(String email) {
		waitForAllElementVisible(driver, EmployeePageUI.VERIFY_EMPLOYEE_NEW, email);
	
		return getElementText(driver, EmployeePageUI.VERIFY_EMPLOYEE_NEW, email);
	}

	public void hoverToClickEmployeeNew(String email) {
		waitForAllElementVisible(driver, EmployeePageUI.VERIFY_EMPLOYEE_NEW, email);
		hoverMouseToElement(driver, EmployeePageUI.VERIFY_EMPLOYEE_NEW, email);
	}
	
	
}
