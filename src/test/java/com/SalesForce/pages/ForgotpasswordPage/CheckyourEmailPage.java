package com.SalesForce.pages.ForgotpasswordPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SalesForce.pages.Base.BasePage;

public class CheckyourEmailPage extends BasePage {
	@FindBy(id="forgotPassForm")WebElement emailConfirmation;
	public CheckyourEmailPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String getTitleofPage() throws InterruptedException {
		return getPageTitle();
		
	}

	public String getmessageFromEmailConfirmation() {
		// TODO Auto-generated method stub
		return getTextFromElement(emailConfirmation, "email send confirmation");
	}


}
