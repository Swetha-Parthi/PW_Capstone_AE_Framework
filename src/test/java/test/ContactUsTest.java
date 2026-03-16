package test;

import org.testng.annotations.Test;

import base.BaseTest;
import framework.reporting.ReportManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import pages.ae.ContactUsPage;
import pages.ae.HomePage;

public class ContactUsTest extends BaseTest {

	// Case:6 - Verify user is able to click 'Contact Us' button, enter required details and also verify success message

	@Test(description = "AE02_TC06_Verify user is able to click 'Contact Us' button, enter required details and also verify success message")
	@Epic("AE-2, The customers should be able to easily contact with the team for any support or feedback via Contact Us Form")
	@Story("AE-11, Submitting enquiry through Contact us Form")
	public void Test_AE02_TC06_Verify_ContactUsForm_Submission() {

		logger.info(
				"========================================================================================================================");
		logger.info(
				"Start, Case:6 - Verify user is able to click 'Contact Us' button, enter required details and also verify success message");
		logger.info(
				"========================================================================================================================");

		HomePage homePage = pageManager.getHomePage();
		ContactUsPage contactusPage = pageManager.getcontactusPage();
		String filePath = "C:\\Assignment_Workspace\\ContactUs_Message_Document.txt";

		// Step:1 - Verify Home page is displayed and Navigate to Contact Us page
		logger.info("Running, Step:1 - Verify Home page is displayed and Navigate to contact us page");
		ReportManager.logStep("Verify Home page is displayed");
		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		ReportManager.logStep("Naviagting to Signup page");
		homePage.clickContactusLink();

		// Step:2 - Submit details in contact us form
		logger.info("Running, Step:2 - Submit details in contact us form");
		ReportManager.logStep("Verify 'GET IN TOUCH' is displayed");
		contactusPage.verifyTextMessageDisplayed("GET IN TOUCH", false);
		
		ReportManager.logStep("Enter details as required");
		contactusPage.enterName("Test_User_1001");
		contactusPage.enterEmail("testuser1001@gmail.com");
		contactusPage.enterSubject("Test inquiry");
		contactusPage.enterMessage(
				"Test message submitted via contact form to verify that form submission working correctly");
		contactusPage.uploadFile(filePath);
		ReportManager.attachScreenshot("Details entered successfully", captureScreenshot());
		
		contactusPage.clickSubmitBtn();

		// Step:3 - Verify form submitted successfully and navigate back to home page
		logger.info("Running, Step:3 - Verify form submitted successfully and navigate back to home page ");
		ReportManager.logStep("Verify form submitted successfully and navigate back to home page ");
		contactusPage.verifyTextMessageDisplayed("Success! Your details have been submitted successfully.", false);
		ReportManager.attachScreenshot("Form submitted successfully", captureScreenshot());
		contactusPage.clickHomeButton();
		contactusPage.verifyPageLoaded("automationexercise", "Automation Exercise");
	
		logger.info("=====================End Case: Contact us form submitted successfully=========================");
	}
}
