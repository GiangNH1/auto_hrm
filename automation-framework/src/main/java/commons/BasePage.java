package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageUIs.BasePageUI;


public class BasePage {
	private JavascriptExecutor jsExecutor;
	private WebDriverWait explicitWait;
	private long longTime = GlobalConstants.LONG_TIMEOUT;
	
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	public void openPageURL(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public Set<Cookie> getAllCookie(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setAllCookie(WebDriver driver, Set<Cookie> allCookie) {
		for(Cookie cookie: allCookie) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(5);
		refresh(driver);
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void back(WebDriver driver) {
		driver.navigate().back();
	}
	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}
	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}
	public Alert waitForAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	public String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for(String id : allWindowIDs) {
			if(!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	public void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle=driver.getTitle();
			if(actualTitle.equals(tabTitle)) {
				break;
			}
		}
	}
	public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for(String id : allWindowIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}
	
	private By getByLocator(String locatorType) {
		By by = null;
		if(locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
			by = By.id(locatorType.substring(3));
		}else if(locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6));
		}else if(locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
			by = By.name(locatorType.substring(5));
		}else if(locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4));
		}else if(locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=") || locatorType.startsWith("XPATH=") ) {
			by = By.xpath(locatorType.substring(6));
		}
		else {
			throw new RuntimeException("Locator type is not supperted");
		}
		return by;
	}
	
	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		if(locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=") || locatorType.startsWith("XPATH=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}
	
	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	protected List<WebElement> getListWebElement(WebDriver driver, String locatorType){
		return driver.findElements(getByLocator(locatorType));
	}
	
	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}
	
	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue ) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void selectItemInDefaultDropdow(WebDriver  driver, String locatorType, String textValue) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textValue);
	}
	public void selectItemInDefaultDropdow(WebDriver  driver, String locatorType, String textValue, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textValue);
	}
	
	public String getSelectedItemInDefaultDropdow(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdowMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdow(WebDriver driver, String parentXpath,String childXpath, String expectedText) {
		getWebElement(driver, parentXpath).click();;
		sleepInSecond(1);
		
		explicitWait = new WebDriverWait(driver, longTime);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		for (WebElement item : allItems) {
			if(item.getText().trim().equals(expectedText)) {
				JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
				jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}
	
	public void selectItemInCustomDropdow(WebDriver driver, String parentXpath,String childXpath, String expectedText, String... dynamicValue) {
		getWebElement(driver, getDynamicXpath(parentXpath, dynamicValue)).click();;
		sleepInSecond(1);
		
		explicitWait = new WebDriverWait(driver, longTime);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		for (WebElement item : allItems) {
			if(item.getText().trim().equals(expectedText)) {
				JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
				//jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}
	
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	
	public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}
	
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRGBA( String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}
	
	public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}
	
	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void unCheckToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getListWebElement(driver, locator);
		
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		
		if(elements.size() == 0) {
			return true;
		}else if(elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	private void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	//Action
	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locatorType, String...dynamicValue) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue))).perform();
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void highlightElement(WebDriver driver, String locatorType) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element ,"style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	public void clickToElementByJS(WebDriver driver, String locatorType) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}
	
	public void scrollToElementByJS(WebDriver driver, String locatorType) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}
	
	public void sendkyeToElementByJS(WebDriver driver, String locatorType, String textValue) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value','"+ textValue + "')", getWebElement(driver, locatorType));
	}
	
	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('"+ attributeRemove +"');", getWebElement(driver, locatorType));
	}
	
	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTime);
		jsExecutor = (JavascriptExecutor) driver;
		
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active")==0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return argument[0].validationMessage;", getWebElement(driver, locatorType));
	}
	
	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if(status) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValue) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
		return status;
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		explicitWait = new WebDriverWait(driver, longTime);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		explicitWait = new WebDriverWait(driver, longTime);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		explicitWait = new WebDriverWait(driver, longTime);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		explicitWait = new WebDriverWait(driver, longTime);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		explicitWait = new WebDriverWait(driver, longTime);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		explicitWait = new WebDriverWait(driver, longTime);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		explicitWait = new WebDriverWait(driver, longTime);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		explicitWait = new WebDriverWait(driver, longTime);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait1 = new WebDriverWait(driver, longTime);
		explicitWait1.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait1 = new WebDriverWait(driver, longTime);
		explicitWait1.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	
//	public BasePageEdit openPagesAtMyAccountByName(WebDriver driver, String pageName) {
//		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
//		clickToElement(driver, BasePageUI.DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA, pageName);
//		switch (pageName) {
//		case "Customer info":
//			return PageGeneratorManager.getUserCustomerInforPage(driver);
//			
//		case "Addresses":
//			return PageGeneratorManager.getUserAddressPage(driver);
//			
//		case "My product reviews":
//			return PageGeneratorManager.getUserMyProductReviewPage(driver);
//			
//		case "Reward points":
//			return PageGeneratorManager.getUserRewardPointPage(driver);
//			
//		default:
//			throw new RuntimeException("Invalid page name at my account");
//		}
//	}
	
//	//level 08 Switch role
//	public UserHomePageObject clickToLogoutLinkAtUser(WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_USER);
//		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER);
//		return PageGeneratorManager.getUserHomePageObject(driver);
//	}
//	
//	public AdminLoginPageObject clickToLogoutLinkAtAdmin(WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
//		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
//		return PageGeneratorManager.getAdminLoginPage(driver);
//	}
	
	//Upload File
	public void uploadMultipalFiles(WebDriver driver, String... fileName) {
		String filePath = GlobalConstants.UPLOAD_FILE;
		String fullFileName = "";
		for (String file : fileName) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(fullFileName);
	}
	
	public void enterToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}
	
	public void openMenuPage(WebDriver driver, String menuPageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_OPEN_MENU_BY_TEXT, menuPageName);
		clickToElement(driver, BasePageUI.DYNAMIC_OPEN_MENU_BY_TEXT, menuPageName);
	}
	
	public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPage) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_OPEN_SUB_MENU_BY_TEXT, menuPageName);
		clickToElement(driver, BasePageUI.DYNAMIC_OPEN_SUB_MENU_BY_TEXT, menuPageName);
		
		waitForElementClickable(driver, BasePageUI.DYNAMIC_OPEN_SUB_MENU_BY_TEXT, subMenuPage);
		clickToElement(driver, BasePageUI.DYNAMIC_OPEN_SUB_MENU_BY_TEXT, subMenuPage);
	}
	
	public void clickToLinkPage(WebDriver driver, String linkPageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_CLICK_TO_LINK, linkPageName);
		clickToElement(driver, BasePageUI.DYNAMIC_CLICK_TO_LINK, linkPageName);
	}
	
	public void enterToTextboxByName(WebDriver driver, String textboxName, String value) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_ENTER_TEXTBOX_BY_NAME, textboxName);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_ENTER_TEXTBOX_BY_NAME, value, textboxName);
	}
	
	public void seletedItemCustomByName(WebDriver driver, String seletedLabel, String value) {
		selectItemInCustomDropdow(driver, BasePageUI.DYNAMIC_SELETED_CUSTOM_PARENT, BasePageUI.DYNAMIC_SELETED_CUSTOM_CHILD, value, seletedLabel);
	}
	
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}
	
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
