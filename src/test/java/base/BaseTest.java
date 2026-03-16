package base;

import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Page;

import framework.drivers.DriverManager;
import framework.logging.LogManager;
import framework.managers.PageObjectManager;

public class BaseTest {

	protected Page page;
	protected Logger logger;
	protected PageObjectManager pageManager;
	
	@BeforeMethod(description = "Browser and URL Launch")

	public void setup() {
		
		logger = LogManager.getLogger(this.getClass());
		
		DriverManager.initDriver();
		
		page = DriverManager.getPage();
		
		logger.info("Initial Setup is completed");
		
		pageManager = new PageObjectManager(page);
		
				
	}

	@AfterMethod(description = "Browser and Playwright close")
	public void teardown() {
		
		DriverManager.quitDriver();
		
		logger.info("Driver Shutdown successful !!");
	}
	
	protected byte[] captureScreenshot() {
		return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
	}
}
