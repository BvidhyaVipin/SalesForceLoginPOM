package com.SalesForce.TestScripts;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.SalesForce.BaseTest.BaseTestForSalesForce;
import com.SalesForce.Utilities.Constants;
import com.SalesForce.Utilities.ExtentReportsUtility;
import com.SalesForce.Utilities.PropertiesUtility;
import com.SalesForce.pages.ForgotpasswordPage.CheckyourEmailPage;
import com.SalesForce.pages.ForgotpasswordPage.ForgotPasswordPage;
import com.SalesForce.pages.Login.SalesForceLoginPage;
import com.SalesForce.pages.home.SalesforceHomepage;

//test case or scripts

public class SalesforceLoginAutomationScript extends BaseTestForSalesForce {
	//protected static SalesForceLoginPage login_page=SalesForceLoginPage.getInstance();
	protected Logger Automation_login_log=LogManager.getLogger();
	protected ExtentReportsUtility Automation_login_report=ExtentReportsUtility.getInstance();
	
	
	
	@Test
	public void testcase1() throws InterruptedException {
	
		String actTitle="Login | Salesforce";
		String expTitle=driver.getTitle();
		assertEquals(expTitle, actTitle);
		
		String username=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		//String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
		SalesForceLoginPage s1=new SalesForceLoginPage(driver);
		s1.enterUserName(username);
		s1.enterPassword("");
		driver=s1.clickLoginButton();
		
		String ActError="Please enter your password.";
		assertEquals(ActError,s1.getTextFromError());
		Automation_login_log.info("test case 1 passed");
		Automation_login_report.logTestpassed("test case 1 passed: login error meggage displayed");
	}
	@Test
	public void testcase2() throws InterruptedException {
		String actTitle="Login | Salesforce";
		String expTitle=driver.getTitle();
		assertEquals(expTitle, actTitle);
		String username=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
		SalesForceLoginPage s1=new SalesForceLoginPage(driver);
		s1.enterUserName(username);
		s1.enterPassword(passWord);
		driver=s1.clickLoginButton();
		SalesforceHomepage home=new SalesforceHomepage(driver);
		String expTitleHome="Home Page ~ Salesforce - Developer Edition";
		assertEquals(home.getTitleHome(),expTitleHome);
		Automation_login_log.info("test case 2 passed");
		Automation_login_report.logTestpassed("test case 2 passed: succesful login ");
		
		
	}
	@Test
	public void testcase3() throws InterruptedException {

		String username=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
		SalesForceLoginPage s1=new SalesForceLoginPage(driver);
		s1.enterUserName(username);
		s1.enterPassword(passWord);
		s1.clickRememberMe();
		driver=s1.clickLoginButton();
		SalesforceHomepage home=new SalesforceHomepage(driver);
		String expTitleHome="Home Page ~ Salesforce - Developer Edition";
		
		//System.out.println(home.getPageTitle());
		assertEquals(home.getPageTitle(),expTitleHome);
		
		home.clickusermenuDropdown();
		home.clicklogout();
		Thread.sleep(3000);
		s1.getTextFromUsernameField();
		System.out.println(s1.getTextFromUsernameField());
		assertEquals(s1.getTextFromUsernameField(), username);
		Automation_login_log.info("test case 3 passed");
		Automation_login_report.logTestpassed("test case 3 passed: rememberme function displayed the correct username");
		
	}
	@Test
	public void testcase4A() throws InterruptedException {
		String username=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		//String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
		SalesForceLoginPage s1=new SalesForceLoginPage(driver);
		s1.enterUserName(username);

		//find element for forgot password and 
		s1.clickForgotPassword();
		ForgotPasswordPage fo=new ForgotPasswordPage(driver);
		String expTitle="Forgot Your Password | Salesforce";
		assertEquals(fo.getPageTitle(),expTitle);
	
		fo.enterUsename(username);
		fo.clickContinue();
		
		CheckyourEmailPage ch=new CheckyourEmailPage(driver);
		
		String expPage="Check Your Email | Salesforce";
		assertEquals(ch.getTitleofPage(),expPage);
		Automation_login_report.logTestpassed(ch.getmessageFromEmailConfirmation());
		Automation_login_log.info("test case 4A passed");
		Automation_login_report.logTestpassed("test case 4A passed");
			
	}
	@Test
	public void testcase4B() throws InterruptedException {
		//String username=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		//String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
		SalesForceLoginPage s1=new SalesForceLoginPage(driver);
		s1.enterUserName("123");
		s1.enterPassword("22321");
		Automation_login_report.logTestInfo("password 22321 entered");
		driver=s1.clickLoginButton();
		Automation_login_report.logTestInfo("login button clicked");
		String error="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		assertEquals(s1.getTextfromIvalidUsernameAndPassword(), error);
		Automation_login_report.logTestInfo("errormessage displayed");
		Automation_login_report.logTestInfo(s1.getTextfromIvalidUsernameAndPassword());
		}
}