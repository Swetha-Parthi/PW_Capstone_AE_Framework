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

public class LoginTest extends BaseTest {

	// Case:2 - Login user with correct details

	@Test(description = "AE01_TC02_Verify user is able to login with correct email and password")
	@Epic("AE-1, The Automation exercise website should allow Secure User Login, Registration, and Logout")
	@Story("AE-9, User Login")
	public void Test_AE01_TC02_Verify_Login_CorrectDetails() {

		logger.info("=======================================================");
		logger.info("Start, Case:2 - Login with correct email and password");
		logger.info("=======================================================");

		HomePage homePage = pageManager.getHomePage();
		SignupLoginPage signuploginPage = pageManager.getSignupLoginPage();
		SignupDetailPage signupdetailPage = pageManager.getSignupDetailPage();
		
		// Step:1 - Verify Home page is displayed and Navigate to Login page
		logger.info("Running, Step:1 - Verify Homepage is displayed and Navigate to Login page");
		ReportManager.logStep("Verify Homepage is displayed and Naviagting to Login page");
		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage.clickSignupLoginLink();
		signuploginPage.verifyPageLoaded("/login", "Signup");

		// Step:2 - Enter SignUp details
		logger.info("Running, Step: 2 - Entering Signup credentials");
		ReportManager.logStep("Entering signup credentials on successful navigation");
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
		signupdetailPage.clickCreateAcc();

		// Step: 4 - verify Account created
		logger.info("Running, Step: 4 - Verify account is created");
		ReportManager.logStep("Verifying whether account is created");
		signupdetailPage.verifyPageLoaded("/account_created", "Account Created");
		signupdetailPage.verifyTextMessageDisplayed("Account Created!", false);
		signupdetailPage.clickContinueButton();

		// Step: 5 - verify user is able to logout and navigate to login page
		logger.info("Running, Step: 5 - verify user is able to logout and navigate to login page");
		ReportManager.logStep("Verify whether user is able to logout and navigate to login page back");
		homePage.clickLogoutLink();
		signuploginPage.verifyPageLoaded("/login", "Signup");

		// Step: 6 - verify Login to your account is visible and enter Email and
		// Password
		logger.info("Running, Step: 6 - verify Login to your account is visible and enter Email and Password");
		ReportManager.logStep("Verify whether Login to your account is visible and enter Email and Password");
		homePage.verifyTextMessageDisplayed("Login to your account", false);
		signuploginPage.enterLoginDetails(email, pwd);
		signuploginPage.clickLogin();

		// Step: 7 - verify Logged in as user name is visible
		logger.info("Running, Step: 7 - Logged in as username is visible");
		ReportManager.logStep("Verifying whether logged in as username is visible");
		homePage.verifyLoggedIn(userName);

		// Step:3 - Delete Account
		logger.info("Running, Step:8 - Delete Account");
		ReportManager.logStep("Verifying whether account is deleted");
		homePage.clickDeleteAccountLink();
		homePage.verifyTextMessageDisplayed("Account Deleted", false);

		logger.info("==========End Case: Login user with correct details===========");
	}

	// Case:3 - Login user with incorrect details

	@Test(description = "AE01_TC03_Verify user is able to see error message  with incorrect email and password on Login page")
	@Epic("AE-1, The Automation exercise website should allow Secure User Login, Registration, and Logout")
	@Story("AE-9, User Login")
	public void Test_AE01_TC03_Verify_Login_Incorrect_MailPwd() {

		logger.info("=====================================================================");
		logger.info("Start, Case:3 - Entering Incorrect Login Details(Mail and Password)");
		logger.info("=====================================================================");

		HomePage homePage = pageManager.getHomePage();
		SignupLoginPage signuploginPage = pageManager.getSignupLoginPage();
		
		// Step:1 - Verify Home page is displayed, Navigate to Login page and check 'Login into your acccount' is
		// visible
		logger.info("Running, Step:1 - Verify Home page is displayed, Navigate to Login page and check 'Login into your acccount' is visible");
		ReportManager.logStep("Verify Home page is displayed and Naviagting to Login page");
		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage.clickSignupLoginLink();
		homePage.verifyTextMessageDisplayed("Login to your account", false);

		// Step:2 - Entering user credentials
		logger.info("Running, Step:2 - Entering user credentials");
		ReportManager.logStep("Entering user credentials");
		signuploginPage.enterLoginDetails("AEtest@gmail.com", "AEtestCF");
		signuploginPage.clickLogin();

		// Step:3 - Verify user is getting error message
		logger.info("Running, Step:3 - Verify user is getting error message");
		ReportManager.logStep("Verifying whether user is getting error message with incorrect details");
		signuploginPage.verifyTextMessageDisplayed("Your email or password is incorrect!", true);
		ReportManager.attachScreenshot("Your email or password is incorrect!", captureScreenshot());

		logger.info("==============End Case: Login user with incorrect details==============");
	}
}