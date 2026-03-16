package test;

import java.util.UUID;

import org.testng.annotations.Test;

import base.BaseTest;
import framework.reporting.ReportManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import pages.ae.HomePage;
import pages.ae.SignupDetailPage;
import pages.ae.SignupLoginPage;

public class SignupTest extends BaseTest {

	// Case:1 - Register New User

	@Test(description = "AE01_TC01_Verify user is able to register, log in, and delete their account")
	@Epic("AE-1, The Automation exercise website should allow Secure User Login, Registration, and Logout")
	@Story("AE-8, User Signup")
	public void Test_AE01_TC01_Verify_NewUser_SignUp() {

		logger.info("=================================================================================");
		logger.info("Start, Case:1 - Verify user is able to register, log in, and delete their account");
		logger.info("=================================================================================");

		HomePage homePage = pageManager.getHomePage();
		SignupLoginPage signuploginPage = pageManager.getSignupLoginPage();
		SignupDetailPage signupdetailPage = pageManager.getSignupDetailPage();
		
		// Step:1 - Verify Home page is displayed and Navigate to SignUp page
		logger.info("Running, Step:1 - Verify Homepage is displayed and Navigate to SignUp page");
		ReportManager.logStep("Verify Homepage is displayed and Naviagting to Signup page");
		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage.clickSignupLoginLink();
		signuploginPage.verifyPageLoaded("/login", "Signup");
		signuploginPage.verifyPageHeader("signup", "New User Signup!");

		// Step:2 - Enter SignUp details
		logger.info("Running, Step: 2 - Entering Signup credentials");
		ReportManager.logStep("Entering signup credentials on successful navigation");
		// constants
		String userName = "CFTestUser" + System.currentTimeMillis();
		String email = userName + "@gmail.com";
		String pwd = "CF@pwd" + UUID.randomUUID().toString().substring(0, 3);
		signuploginPage.enterSignupDetails(userName, email);
		signuploginPage.clickSignup();
		signupdetailPage.verifyPageLoaded("/signup", "Signup");
		signupdetailPage.verifyTextMessageDisplayed("Enter Account Information", false);
		signupdetailPage.verifyTextMessageDisplayed("Address Information", true);
		signupdetailPage.verifyAutoPopNameandEmail(userName, email);

		// Step:3 - Enter user account details
		logger.info("Running, Step: 3 - Enter user account details");
		ReportManager.logStep("Entering new user account details");
		signupdetailPage.selectGender("male");
		signupdetailPage.enterPassword(pwd);
		signupdetailPage.selectDOB("10", "March", "1996");
		signupdetailPage.optNewsLetter(true);
		signupdetailPage.optSpecialOffer(false);
		ReportManager.attachScreenshot("Account Info Entered", captureScreenshot());
		signupdetailPage.enterNames(userName, "CFTest");
		signupdetailPage.enterCompanyDetails("CF");
		signupdetailPage.enterAddressDetails("584 Main RD", "D Colony", "India", "TN", "CBE", "600001");
		signupdetailPage.enterMobileNum("9517423000");
		ReportManager.attachScreenshot("Account address info Entered", captureScreenshot());
		signupdetailPage.clickCreateAcc();

		// Step: 4 - verify Account created
		logger.info("Running, Step: 4 - Verify account is created");
		ReportManager.logStep("Verifying whether account is created");
		signupdetailPage.verifyPageLoaded("/account_created", "Account Created");
		signupdetailPage.verifyTextMessageDisplayed("Account Created!", false);
		signupdetailPage.clickContinueButton();

		// Step:5 - verify Logged in as username is visible and able to logout
		logger.info("Running, Step: 5 - Logged in as username is visible and able to logout");
		ReportManager.logStep("Verifying whether logged in as username is visible");
		homePage.verifyLoggedIn(userName);

		// Step:6 - Delete Account
		logger.info("Running, Step: 6 - Delete Account");
		ReportManager.logStep("Verifying whether account is deleted");
		homePage.clickDeleteAccountLink();
		homePage.verifyPageLoaded("/delete_account", "Account Created");
		homePage.verifyTextMessageDisplayed("Account Deleted!", true);
		homePage.clickContinueButton();

		logger.info("=====================End Case: User Registration=========================");
	}

	// Case:5 - Register User with existing mail

	@Test(description = "AE01_TC05_Verify whether user getting error message using registered mail while Signup")
	@Epic("AE-1, The Automation exercise website should allow Secure User Login, Registration, and Logout")
	@Story("AE-8, User Signup")
	public void Test_AE01_TC05_Signup_ExistingMail() {

		logger.info("==================================================================================");
		logger.info("Start, Case:5 - verify signup using already registered mail display error message");
		logger.info("==================================================================================");

		HomePage homePage = pageManager.getHomePage();
		SignupLoginPage signuploginPage = pageManager.getSignupLoginPage();
		
		// Step:1 - Verify Home page is displayed and Navigate to SignUp page
		logger.info("Running, Step:1 - Verify Homepage is displayed and Navigate to SignUp page");
		ReportManager.logStep("Verify Homepage is displayed and Naviagting to Signup page");
		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage.clickSignupLoginLink();
		signuploginPage.verifyPageLoaded("/login", "Signup");
		signuploginPage.verifyTextMessageDisplayed("New User Signup!", true);

		// Step:2 - Enter new name and already registered email for SignUp
		logger.info("Running, Step:2 - Entering new name and already registered email for SignUp");
		ReportManager.logStep("Entering new name and already registered email for SignUp");
		signuploginPage.enterSignupDetails("AEtestCF06", "CFTestUser1770978867421@gmail.com");
		signuploginPage.clickSignup();

		// Step:3 - See error message
		logger.info("Running, Step:3 - Verify error message is getting displayed");
		ReportManager.logStep("Verify whether error message is getting displayed");
		signuploginPage.verifyTextMessageDisplayed("Email Address already exist!", true);
		ReportManager.attachScreenshot("Email Address already exist!", captureScreenshot());

		logger.info("=====================End Case: Register User with existing mail====================");
	}
}
