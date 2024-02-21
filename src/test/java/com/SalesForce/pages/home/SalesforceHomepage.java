package com.SalesForce.pages.home;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SalesForce.pages.Base.BasePage;

public class SalesforceHomepage extends BasePage {
	
	@FindBy(id="userNav-arrow")WebElement usermenuDropdownbutton;
	@FindBy(linkText="Logout")WebElement logout;
	
	
	

	public SalesforceHomepage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public String getTitleHome() {
		String title=driver.getTitle();
		return title;
		
	}
	public void clickusermenuDropdown(){
		clickElement(usermenuDropdownbutton, "user dropdown");
	}
	public WebDriver clicklogout() {
		clickElement(logout, "logout");
		return driver;
	}
	


}
