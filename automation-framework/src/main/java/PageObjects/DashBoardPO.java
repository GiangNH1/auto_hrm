package PageObjects;

import org.openqa.selenium.WebDriver;

import PageUIs.DashBoardPageUI;
import commons.BasePage;

public class DashBoardPO extends BasePage{
	private WebDriver driver;
	
	public DashBoardPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDisplayLoginSuccess() {
		waitForElementVisible(driver, DashBoardPageUI.VERIFY_LOGIN_SUCCESS);
		return isElementDisplayed(driver, DashBoardPageUI.VERIFY_LOGIN_SUCCESS);
		
	}

	public String checkListEmployee() {
		waitForAllElementVisible(driver, DashBoardPageUI.VERIFY_LIST_EMPLOYEE);
		int size = getElementSize(driver, DashBoardPageUI.VERIFY_LIST_EMPLOYEE);
		return String.valueOf(size);
	}
	
	
}
