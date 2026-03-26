package test;

import org.testng.annotations.Test;

import base.BaseTest;
import framework.reporting.ReportManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;

public class HomeTest extends BaseTest {

	// Case:10 - Verify user can successfully subscribe from the Home page

	@Test(description = "AE05_TC10_Verify user can successfully subscribe from the Home page")
	@Epic("AE-5, Users should be able to subscribe to automation exercise website")
	@Story("AE-16, User Subscription")
	public void Test_AE05_TC10_Verify_HomePageSubscription() {

		logger.info("==========================================================================");
		logger.info("Start, Case:10 - Verify user can successfully subscribe from the Home page");
		logger.info("==========================================================================");

		// Step:1 - Verify Home page is displayed
		logger.info("Running, Step:1 - Verify Home page is displayed");
		ReportManager.logStep("Verify Home page is displayed");

		homePage().verifyPageLoaded("automationexercise", "Automation Exercise");

		// Step:2 - Scroll down to footer and verify subscription text
		logger.info("Running, Step:2 - Scroll down to footer and verify subscription text");
		ReportManager.logStep("Scroll down to footer and verify subscription text");

		homePage().scrolldown();
		homePage().verifyTextMessageDisplayed("SUBSCRIPTION", true);

		// Step:3 - Enter email and verify text message is displayed
		logger.info("Running, Step:3 - Enter email and verify text message is displayed");
		ReportManager.logStep("Enter email and verify text message is displayed");

		homePage().enterSubscriptionEmail("AETestUser@gmail.com");
		ReportManager.attachScreenshot("Successfully subscribed", captureScreenshot());
		homePage().verifyTextMessageDisplayed("You have been successfully subscribed!", false);
	
		logger.info("=====================End Case: User subscription from Home page=========================");
	}

	// Case:25 - Verify user can successfully Scroll Down and Scroll Up using 'Arrow' button on home page

	@Test(description = "AE07_TC25_Verify user can successfully Scroll Down and Scroll Up using 'Arrow' button on home page")
	@Epic("AE-7, Usability - Scroll up and scroll down on home page")
	@Story("AE-19, Home page scroll up and scroll down")
	public void Test_AE07_TC25_Verify_HomePageScrollUpArrow_ScrollDown() {

		logger.info("=========================================================================================================");
		logger.info("Start, Case:25 - Verify user can successfully Scroll Down and Scroll Up using 'Arrow' button on home page");
		logger.info("=========================================================================================================");

		// Step:1 - Verify Home page is displayed
		logger.info("Running, Step:1 - Verify Home page is displayed and Navigate to Products page");
		ReportManager.logStep("Verify Home page is displayed");

		homePage().verifyPageLoaded("automationexercise", "Automation Exercise");

		// Step:2 - Scroll down to footer and verify subscription text
		logger.info("Running, Step:2 - Scroll down to footer and verify subscription text");
		ReportManager.logStep("Scroll down to footer and verify subscription text");

		homePage().scrolldown();
		homePage().verifyTextMessageDisplayed("SUBSCRIPTION", true);

		// Step:3 - Click arrow and move upward and verify text ''Full-Fledged practice.....' is visible
		logger.info(
				"Running, Step:3 - Clicking arrow and move upward and verify text ''Full-Fledged practice.....' is visible");
		ReportManager
				.logStep("Clicking arrow and move upward and verify text ''Full-Fledged practice.....' is visible");

		homePage().clickArrow();
		homePage().verifyTextMessageDisplayed("Full-Fledged practice website for Automation Engineers", false);

		ReportManager.attachScreenshot("Text is displayed successfully", captureScreenshot());
		
		logger.info("=====================End Case: Verify scroll down and scrollup using arror button=========================");
	}

	// Case:26 - Verify user can successfully Scroll Down and Scroll Up without 'Arrow' button on home page

	@Test(description = "AE07_TC26_Verify user can successfully Scroll Down and Scroll Up without 'Arrow' button on home page")
	@Epic("AE-7, Usability - Scroll up and scroll down on home page")
	@Story("AE-19, Home page scroll up and scroll down")
	public void Test_AE07_TC26_Verify_HomePageScrollUpWithoutArrow_ScrollDown() {

		logger.info("=========================================================================================================");
		logger.info("Start, Case:26 - Verify user can successfully Scroll Down and Scroll Up using 'Arrow' button on home page");
		logger.info("=========================================================================================================");

		// Step:1 - Verify Home page is displayed
		logger.info("Running, Step:1 - Verify Home page is displayed and Navigate to Products page");
		ReportManager.logStep("Verify Home page is displayed");

		homePage().verifyPageLoaded("automationexercise", "Automation Exercise");

		// Step:2 - Scroll down to footer and verify subscription text
		logger.info("Running, Step:2 - Scroll down to footer and verify subscription text");
		ReportManager.logStep("Scroll down to footer and verify subscription text");

		homePage().scrolldown();
		homePage().verifyTextMessageDisplayed("SUBSCRIPTION", true);

		// Step:3 - Scroll up and verify text ''Full-Fledged practice.....' is visible
		logger.info("Running, Step:3 - Scroll up and verify text ''Full-Fledged practice.....' is visible");
		ReportManager.logStep("Scroll up and verify text ''Full-Fledged practice.....' is visible");

		homePage().scrollup();
		homePage().verifyTextMessageDisplayed("Full-Fledged practice website for Automation Engineers", false);
		ReportManager.attachScreenshot("Text is displayed successfully", captureScreenshot());
		
		logger.info("=====================End Case: Verify scroll down and scrollup=========================");
	}
	
	// Case:22 - Verify user can successfully add products from Recommended items

		@Test(description = "AE04_TC22_Verify user can successfully add products from Recommended items")
		@Epic("AE-4, User should be able to search & view the products and add the selected products to shopping cart")
		@Story("AE-15, Add, manage and remove products in cart")
		public void Test_AE04_TC22_Verify_RecommendedProducts_AddedtoCart() {

			logger.info("=================================================================================");
			logger.info("Start, Case:22 - Verify user can successfully add products from Recommended items");
			logger.info("=================================================================================");

			// Step:1 - Verify Home page is displayed
			logger.info("Running, Step:1 - Verify Home page is displayed and Navigate to Products page");
			ReportManager.logStep("Verify Home page is displayed");

			homePage().verifyPageLoaded("automationexercise", "Automation Exercise");
			
			// Step:2 - Add recommended products to cart and verify its visible in cart page
			logger.info("Running, Step:2 - Add recommended products to cart and verify its visible in cart page");
			ReportManager.logStep("Add recommended products to cart and verify its visible in cart page");
			String recomProd = homePage().addRecomendItems();
			homePage().clickViewCartLink();
			cartPage().verifyRecommendProdList(recomProd);
			ReportManager.attachScreenshot("Product Details displayed successfully", captureScreenshot());
			
			logger.info("=====================End Case: Added products from recommended items=========================");
		}
		

}
