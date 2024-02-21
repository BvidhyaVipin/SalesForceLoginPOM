package com.SalesForce.pages.Login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SalesForce.Utilities.ExtentReportsUtility;
import com.SalesForce.pages.Base.BasePage;


public class SalesForceLoginPage extends BasePage {
	protected Logger login_page_log=LogManager.getLogger();
    protected ExtentReportsUtility login_extentReport=ExtentReportsUtility.getInstance();
	private static SalesForceLoginPage login_page;
	

	@FindBy(id="username") WebElement userNameElement;
	@FindBy(id="password") WebElement passwordElement;
	@FindBy(id="Login") WebElement loginButtonElement;
	@FindBy(id="error") WebElement errorElementforemptypassword;
	@FindBy(xpath="//*[@id='error' and @class='loginError']") WebElement errorWrongUserNamePassword;
	@FindBy(xpath="//input[@id='rememberUn']") WebElement rememberMe;
	@FindBy(id="idcard-identity")WebElement afterlogoutusernameElement;
	@FindBy(id="forgot_password_link")WebElement forgotPassword;
	
	public SalesForceLoginPage(WebDriver driver) {
		super(driver);
	}
	
//	public static SalesForceLoginPage getInstance() {
//		if(login_page==null) {
//			login_page=new SalesForceLoginPage(driver);
//		}
//
//		return login_page;
//	}
	   public String getTextfromIvalidUsernameAndPassword() {
		return getTextFromElement(errorWrongUserNamePassword, "wrong username and password");
	   }
 
		public String getTheTextFromUserNameField() {
			String text=userNameElement.getAttribute("value");
			return text;
			
		}
		public String getTextFromUsernameField() {
			waitForVisibility(afterlogoutusernameElement, 30, "username field");
			String text=afterlogoutusernameElement.getText();
			return text;
			
		}
	
		public String getTextFromError() {
			String errormessage=errorElementforemptypassword.getText();
			return errormessage;
		}
		
		public void enterUserName(String data) {
			//userNameElement.sendKeys(data);
			enterText(userNameElement, data, "Username textbox");
		}
		public void enterPassword(String data) {
			enterText(passwordElement, data, "password field");
		}
		
		public WebDriver clickLoginButton() {
			clickElement(loginButtonElement,"login button");
			return driver;
			
		}
		public void clickRememberMe() {
			if(!rememberMe.isSelected()){
				clickElement(rememberMe, "remember me");
			}
		}
		
		public String getTitleOfThePage() {
			//waitUntilPageLoads();
			return getPageTitle();
		}
		public void clickForgotPassword() {
			clickElement(forgotPassword, "forgot password");
		}
		
		
//		public Alert switchToErrorAlert() {
//			return switchToAlert();
//		}
//		public String extractTextFromAlert(Alert a) {
//			return getAlertText(a,"LoginError Alert");
//		}
//		public void acceptErrorAlert(Alert a) {
//			AcceptAlert(a);
//		}

}
