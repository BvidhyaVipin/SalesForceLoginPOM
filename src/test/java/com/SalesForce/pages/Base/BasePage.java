package com.SalesForce.pages.Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SalesForce.Utilities.ExtentReportsUtility;

public class BasePage {
	public static WebDriver driver = null;
	protected Wait<WebDriver> wait = null;
	protected Logger base_page_log=LogManager.getLogger();
	protected ExtentReportsUtility extentReport=ExtentReportsUtility.getInstance();

	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForVisibility(WebElement ele,int time,String objectName) {
		System.out.println(objectName+" is waited for visibility");
		WebDriverWait wait;
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
	
	}

	public void enterText(WebElement ele, String data, String objectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			base_page_log.info("data is entered in " + objectName + " textbox");
			extentReport.logTestInfo("data is entered in " + objectName + " textbox");
			
		} else {
			base_page_log.info(objectName + " element is not displayed");
		}
	}
	
	public void clickElement(WebElement ele, String objectName) {
		if (ele.isEnabled()) {
			ele.click();
			base_page_log.info(objectName + "button is clicked");
		    extentReport.logTestInfo(objectName + "button is clicked");
			
		} else {
			base_page_log.info(objectName+" element is not enabled");
			extentReport.logTestFailed(objectName+" element is not enabled");
			
		}
	}
	
	public String getTextFromElement(WebElement ele, String objectName) {
		String data = ele.getText();
		base_page_log.info("text is extracted from "+objectName);
		extentReport.logTestInfo("text is extracted from "+objectName);
		return data;
	}
	
	
	
	public void maximiseBrowser() {
		driver.manage().window().maximize();
		base_page_log.info("browser window has maximized");
		extentReport.logTestInfo("browser window has maximized");
	}

	public String getPageTitle() {
		
		base_page_log.info("page title is extracted");
		extentReport.logTestInfo("page title is extracted");
		return driver.getTitle();
	}

	public void refreshPage() {
		driver.navigate().refresh();
		base_page_log.info("page is refreshed");
		extentReport.logTestInfo("page is refreshed");
	}


	public void clearElement(WebElement ele, String ObjectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			base_page_log.info(ObjectName + " is cleared");
			extentReport.logTestpassed(ObjectName + " is cleared");
		} else {
			base_page_log.info(ObjectName + " element is not displayed");
			extentReport.logTestFailed(ObjectName + " element is not displayed");
		}
	}
	
	public WebDriver getDriverInstance() {
		return this.driver;
	}


}
