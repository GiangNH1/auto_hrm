package PageObjects;

import org.openqa.selenium.WebDriver;

import PageUIs.LoginPageUI;
import commons.BasePage;
import commons.PageGeneratorManager;

public class LoginPO extends BasePage{
	private WebDriver driver;
	
	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}

	private void enterToUserName(String value) {
		waitForElementVisible(driver, LoginPageUI.USER_NAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USER_NAME_TEXTBOX, value);
	}
	
	private void enterToPassword(String value) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, value);
	}
	
	public DashBoardPO clickLoginToButton() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getDashBoardPage(driver);
	}
	
	public DashBoardPO loginAsAdmin(String userName, String password) {
		
		enterToUserName(userName);
		enterToPassword(password);
		return clickLoginToButton();
	}
}
