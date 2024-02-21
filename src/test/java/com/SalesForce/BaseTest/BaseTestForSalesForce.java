package com.SalesForce.BaseTest;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.SalesForce.Utilities.PropertiesUtility;
import com.SalesForce.Utilities.Constants;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(com.SalesForce.Utilities.ListenersUtility.class)
public class BaseTestForSalesForce {
	protected static WebDriver driver = null;

	protected Logger BaseTest_log=LogManager.getLogger();
	//protected ExtentReportsUtility BaseTest_report=ExtentReportsUtility.getInstance();
	
	
	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeMethod(@Optional("chrome") String name,ITestResult result) {
		
		BaseTest_log.info("before method setup executed");
		//BaseTest_report.logTestInfo("BeforeMethod setUpBeforeMethod executed");
		launchBrowser(name);
		
		String url=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"url");
		goToUrl(url);
	}
	
	@AfterMethod
	public void tearDownAfterTestMethod() {
		closeBrowser();
		BaseTest_log.info("method ended");
		//BaseTest_report.logTestInfo("browser instance closed");
	}
	
	
	
	public void launchBrowser(String browserName) {

		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			BaseTest_log.info("browser instance chrome created.");
			//extentReport.logTestInfo("browser instance chrome created");
			driver.manage().window().maximize();
			BaseTest_log.info("window is maximized");
			System.out.println(driver);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;

		case "opera":
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			driver.manage().window().maximize();
			break;

		case "safari":
			// Safari does not require a separate driver setup, as it is included with macOS
			driver = new SafariDriver();
			break;

		default:
			BaseTest_log.info("Unsupported browser: " + browserName);
		}

		// return driver;
	}
	
	public void goToUrl(String url) {
		driver.get(url);
		BaseTest_log.info(url + "is entered");
		//BaseTest_report.logTestInfo(url + "is entered");
		
	}
	
	public void closeBrowser() {
		driver.close();
		BaseTest_log.info("browser instance closed");
		driver=null;
	}
	public void quitBrowser() {
		driver.quit();
		BaseTest_log.info("all browser closed");
		//BaseTest_report.logTestInfo("all browser closed");
		driver=null;
		
	}
	
	public void takescreenshot(String filepath) {
		 TakesScreenshot screenCapture=(TakesScreenshot)driver;
		 File src=screenCapture.getScreenshotAs(OutputType.FILE);
		 File destination=new File(filepath);
		 try {
			Files.copy(src, destination);
			BaseTest_log.info("captured the screen");
//			BaseTest_report.logTestInfo("captured the screen");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//BaseTest_log.info("went wrong when capturing the screen");
			
		}
	}
	
	
//		
//	}
//	public void logout() throws InterruptedException {
//		WebElement namedownarrow=driver.findElement(By.id("userNav-arrow"));
//		clickElement(namedownarrow,"downarrow");
//		//click logout
//		WebElement logoutbutton=driver.findElement(By.linkText("Logout"));
//		clickElement(logoutbutton,"logout");
//		String showTitle="Login | Salesforce";
//		Thread.sleep(3000);
//		if(getTitle(driver).equals(showTitle)) {
//			System.out.println("logout successful");
//		}
//		else {
//			System.out.println("failed to logout");
//		}
//		Thread.sleep(2000);
//	}
//	public void clickLead() throws InterruptedException{
//		WebElement lead=driver.findElement(By.xpath("//li[contains(@id,'Lead_Tab')]"));
//		clickElement(lead, "lead");
//		WebElement leadpage=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h1"));
//		
//		//System.out.println(getTextFromElement(leadpage, "leadpage"));
//		if(getTextFromElement(leadpage, "leadpage").equals("Leads")) {
//			System.out.println("lead page is verified");
//		}
//		else {
//			System.out.println("lead page is not verified");
//		}
//		
//	}
//	public Select leadDropdown(WebElement dropdownlead) {
//		Select select=new Select(dropdownlead);
//		return select;
//	}
//	public void opportunity() {
//		WebElement opprtunity=driver.findElement(By.xpath("//a[@title='Opportunities Tab']"));
//		clickElement(opprtunity, "opprtunity");
//		
//		WebElement oppText=driver.findElement(By.xpath("//a[contains(@name,'skiplink')]/following::div/h1"));
//		String expopp="Opportunities";
//		if(getTextFromElement(oppText, "opprtunity").equals(expopp)) {
//			System.out.println("opprtunities page displayed");
//		}
//		else {
//			System.out.println("opprtunitites page not displayed");
//		}
//	}
//	public Select select(WebElement ele,String obj) {
//		Select select=new Select(ele);
//		System.out.println(obj+" is selected");
//		
//		return select;
//	}
//	public void homeTab() {
//		WebElement home=driver.findElement(By.id("home_Tab"));
//		clickElement(home, "home tab");
//		WebElement homeverification=driver.findElement(By.xpath("//*[@id=\"home_Tab\"]/a"));
//		if(homeverification.getAttribute("title").equals("Home Tab - Selected")) {
//			System.out.println("home tab displayed home page");
//		}
//		else {
//			System.out.println("failed to display home page");
//		}
//	}
//	public void contactClick() {
//		WebElement contacts=driver.findElement(By.xpath("//li[@id='Contact_Tab']"));
//		clickElement(contacts,"contacts tab");
//	
//		//verify it is contact page
//		WebElement contactPage=driver.findElement(By.xpath("//h1[@class='pageType']"));
//		if(getTextFromElement(contactPage, "contact page ").equals("Contacts")) {
//			System.out.println("contact page verified");
//		}
//		else {
//			System.out.println("failed to verify contact page");
//		}
//	}
//	public void clickUserDropDown() throws InterruptedException {
//		String[] array={"My Profile","My Settings","Developer Console","Switch to Lightning Experience", "Logout"};
//		List<String> expectedDropDown=new ArrayList<>(Arrays.asList(array));
//		
//		
////		List<String> expectedDropDown=new ArrayList<>();
////
////		expectedDropDown.add("My Profile");
////		expectedDropDown.add("My Settings");
////		expectedDropDown.add("Developer Console");
////		expectedDropDown.add("Switch to Lightning Experience");
////		expectedDropDown.add("Logout");
//		WebElement namedownarrow=driver.findElement(By.id("userNav-arrow"));
//		clickElement(namedownarrow, "user dropdown");
//		
//		List <WebElement> dropdownUse=(ArrayList<WebElement>) namedownarrow.findElements(By.xpath("//*[@id=\"userNav-menuItems\"]"));
//		Thread.sleep(3000);
//		ArrayList<String> actualdropdownuser=new ArrayList<>();
//		for(WebElement e:dropdownUse) {
//			actualdropdownuser.add(e.getText());
//		}
//		
//		//System.out.println(actualdropdownuser);
//		for(int i=0;i<actualdropdownuser.size();i++) {
//			
//			if(actualdropdownuser.get(i).contains(expectedDropDown.get(i))) {
//				System.out.println("verified ");
//			}
//			else {
//				System.out.println("not verified");
// 		}
//		}
//		}
//	
	}
	

