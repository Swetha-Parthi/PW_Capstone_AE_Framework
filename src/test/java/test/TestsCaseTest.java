package test;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import base.BaseTest;
import framework.reporting.ReportManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;

public class TestsCaseTest extends BaseTest {

	// Case:7 - Verify user is able to click 'Contact Us' button, enter required
	// details and also verify success message

	@Test(description = "AE03_TC07_Verify user is able to navigate to test cases page and see all the test cases")
	@Epic("AE-3, The Test case page should display all test cases ")
	@Story("AE-12, All test cases related to the website should display")
	public void Test_AE03_TC07_Verify_TestCase_Display() {

		logger.info("=============================================================================================");
		logger.info("Start, Case:7 - Verify user is able to navigate to test cases page and see all the test cases");
		logger.info("=============================================================================================");

		List<String> expectedTitles = Arrays.asList("Test Case 1: Register User",
				"Test Case 2: Login User with correct email and password",
				"Test Case 3: Login User with incorrect email and password", "Test Case 4: Logout User",
				"Test Case 5: Register User with existing email", "Test Case 6: Contact Us Form",
				"Test Case 7: Verify Test Cases Page", "Test Case 8: Verify All Products and product detail page",
				"Test Case 9: Search Product", "Test Case 10: Verify Subscription in home page",
				"Test Case 11: Verify Subscription in Cart page", "Test Case 12: Add Products in Cart",
				"Test Case 13: Verify Product quantity in Cart", "Test Case 14: Place Order: Register while Checkout",
				"Test Case 15: Place Order: Register before Checkout",
				"Test Case 16: Place Order: Login before Checkout", "Test Case 17: Remove Products From Cart",
				"Test Case 18: View Category Products", "Test Case 19: View & Cart Brand Products",
				"Test Case 20: Search Products and Verify Cart After Login", "Test Case 21: Add review on product",
				"Test Case 22: Add to cart from Recommended items",
				"Test Case 23: Verify address details in checkout page",
				"Test Case 24: Download Invoice after purchase order",
				"Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality",
				"Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality");

		// Step:1 - Verify Home page is displayed and Navigate to Test Case page
		logger.info("Running, Step:1 - Verify Home page is displayed and Navigate to Test Case page");
		ReportManager.logStep("Verify Home page is displayed");
		homePage().verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage().clickTestCaseLink();

		// Step:2 - Expected titles are matched with Actual titles
		logger.info("Running, Step:2 - Validating whether expected titles are matched with Actual titles");
		ReportManager.logStep("Validating whether expected titles are matched with Actual titles");
		testcasePage().validateAllTestCases(expectedTitles);
		ReportManager.attachScreenshot("Test cases are displayed successfully", captureScreenshot());

		logger.info("=====================End Case: Test Case Page Validation=========================");
	}

}
