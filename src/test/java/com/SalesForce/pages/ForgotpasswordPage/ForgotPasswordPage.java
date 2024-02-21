package com.SalesForce.pages.ForgotpasswordPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SalesForce.pages.Base.BasePage;

public class ForgotPasswordPage extends BasePage {
	@FindBy(id="un")WebElement Username;
	@FindBy(id="continue")WebElement Continue;
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String getTitleFromforgotPasswordPage() {
		String message=driver.getTitle();
		return message;
		
	}
	public void enterUsename(String name){
		enterText(Username,name , "Username textbox");
		
	}
	public void clickContinue() {
		if(Continue.isEnabled()) {
		clickElement(Continue, "continue");
	}
	}
	

}
