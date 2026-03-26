package test;

import org.testng.annotations.Test;

import base.BaseTest;
import framework.reporting.ReportManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;

public class LogoutTest extends BaseTest {
	
	// Case:4 - Check whether user is able to logout successfully

	@Test(description = "AE01_TC04_Verify user is able to login and logout sucessfully")
	@Epic("AE-1, The Automation exercise website should allow Secure User Login, Registration, and Logout")
	@Story("AE-10, User Logout")
		public void Test_AE01_TC04_Verify_LogoutUser() {
			
			logger.info("===============================================");
			logger.info("Start, Case:4 - Verify user is able to logout");
			logger.info("===============================================");
			
			// Step:1 -  Verify Home page is displayed, Navigate to Login page and check 'Login into your acccount' is visible		
			logger.info("Running, Step:1 -  Verify Home page is displayed, Navigate to Login page and check 'Login into your acccount' is visible");
			ReportManager.logStep(" Verify Home page is displayed and Naviagting to Logout page");
			homePage().verifyPageLoaded("automationexercise", "Automation Exercise");
			homePage().clickSignupLoginLink();
			homePage().verifyTextMessageDisplayed("Login to your account", false);

			// Step:2 - Entering user credentials
			logger.info("Running, Step:2 - Entering user credentials");
			ReportManager.logStep("Entering user credentials");
			signuploginPage().enterLoginDetails("CFTestUser1770978867421@gmail.com", "CF@pwd0a0");
			signuploginPage().clickLogin();
			
			// Step:3 - verify Logged in as username is visible
			logger.info("Running, Step: 3 - Logged in as username is visible");
			ReportManager.logStep("Verifying whether logged in as username is visible");
			homePage().verifyLoggedIn("CFTestUser1770978867421");
			
			// Step:4 - verify user is able to logout and navigate to login page
			logger.info("Running, Step: 4 - verify user is able to logout and navigate to login page");
			ReportManager.logStep("Verify whether user is able to logout and navigate to login page back");
			homePage().clickLogoutLink();
			signuploginPage().verifyPageLoaded("/login", "Signup");
			
			logger.info("==================End case: Logout User================");
		}
}
