package PageUIs;

public class BasePageUI {
	public static final String CUSTOMER_INFO_LINK = "xpath=//a[text()='Customer info']";  
	public static final String MY_ACCOUNT_LINK = "class=a[class='ico-account']";  
	public static final String ADDRESS_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Addresses']";  
	public static final String ORDERS_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Orders']";  
	public static final String DOWNLOADADABLE_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Downloadable products']";  
	public static final String BACK_IN_STOCK_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Back in stock subscriptions']";  
	public static final String REWARD_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Reward points']";  
	public static final String CHANGE_PASSWORD_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Change password']";  
	public static final String MYPRODUCT_REVIEW_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='My product reviews']";  
	public static final String LOGOUT_LINK_AT_USER = "xpath=//a[@class='ico-logout']";  
	public static final String LOGOUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']"; 
	public static final String DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA = "xpath=//div[contains(@class,'account-navigation')]//a[text()='%s']"; 

	public static final String CLICK_BUTTON = "xpath=//span[contains(text(),'Login')]//parent::button";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_OPEN_MENU_BY_TEXT = "xpath=//span[contains(text(),'%s')]/parent::a";
	public static final String DYNAMIC_OPEN_SUB_MENU_BY_TEXT = "xpath=//ul[@class='pc-submenu']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_CLICK_TO_LINK = "xpath=//a[contains(string(),'%s')]";
	public static final String DYNAMIC_ENTER_TEXTBOX_BY_NAME = "xpath=//input[@name='%s']";
	public static final String DYNAMIC_SELETED_CUSTOM_PARENT = "xpath=//label[contains(text(),'%s')]//following-sibling::span//span[@role='combobox']";
	public static final String DYNAMIC_SELETED_CUSTOM_CHILD = "xpath=//li[contains(@class,'select2-results__option')]";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[contains(string(),'%s')]";
	public static final String UPLOAD_FILE = "xpath=//input[@type='file']";
	
}
