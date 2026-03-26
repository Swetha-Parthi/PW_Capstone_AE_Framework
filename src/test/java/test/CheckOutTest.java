package test;

import java.nio.file.Path;
import java.util.UUID;

import org.testng.annotations.Test;

import base.BaseTest;
import framework.reporting.ReportManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;

public class CheckOutTest extends BaseTest {

	// Case:14 - Verify user can successfully register while placing order and delete their account

	@Test(description = "AE06_TC14_Verify user can successfully register while placing order and delete their account")
	@Epic("AE-6, User should be able to place order and proceed with checkout")
	@Story("AE-17, Successfully place order")
	public void Test_AE06_TC14_Verify_Register_While_Checkout() {

		logger.info(
				"===================================================================================================");
		logger.info(
				"Start, Case:14 - Verify user can successfully register while placing order and delete their account");
		logger.info(
				"===================================================================================================");

		// Step:1 - Verify Home page is displayed and add products to cart page
		logger.info("Running, Step:1 - Verify Home page is displayed and add products to cart page");
		ReportManager.logStep("Verify Home page is displayed and add products to cart page");

		homePage().verifyPageLoaded("automationexercise", "Automation Exercise");
		String addProd = productsPage().addToCartHoverwithIndex(0);
		productsPage().clickViewCartLink();
		productsPage().verifyPageLoaded("view_cart", "Checkout");

		// Step:2 - Click register/login while trying to checkout and verify navigating to signup page
		logger.info("Running, Step:2 - Click register/login link while trying to checkout and verify navigating to signup page");
		ReportManager.logStep("Click register/login while trying to checkout and verify navigating to signup page");
				
		cartPage().proceedCheckout();
		cartPage().clickRegisterLoginLink();

		signuploginPage().verifyPageLoaded("/login", "Signup");

		// Step:3 - Enter SignUp details
		logger.info("Running, Step: 3 - Entering Signup credentials");
		ReportManager.logStep("Entering signup credentials on successful navigation");
		
		String userName = "CFTestUser" + System.currentTimeMillis();
		String email = userName + "@gmail.com";
		String pwd = "CF@pwd" + UUID.randomUUID().toString().substring(0, 3);
		signuploginPage().enterSignupDetails(userName, email);
		signuploginPage().clickSignup();
		signupdetailPage().verifyPageLoaded("/signup", "Signup");
		signupdetailPage().verifyTextMessageDisplayed("Enter Account Information", false);
		signupdetailPage().verifyTextMessageDisplayed("Address Information", true);
		signupdetailPage().verifyAutoPopNameandEmail(userName, email);

		// Step:4 - Enter user account details
		logger.info("Running, Step: 4 - Enter user account details");
		ReportManager.logStep("Entering new user account details");
		signupdetailPage().selectGender("male");
		signupdetailPage().enterPassword(pwd);
		signupdetailPage().selectDOB("10", "March", "1996");
		signupdetailPage().optNewsLetter(true);
		signupdetailPage().optSpecialOffer(false);
		ReportManager.attachScreenshot("Account Info Entered", captureScreenshot());
		signupdetailPage().enterNames(userName, "CFTest");
		signupdetailPage().enterCompanyDetails("CF");
		signupdetailPage().enterAddressDetails("584 Main RD", "D Colony", "India", "TN", "CBE", "600001");
		signupdetailPage().enterMobileNum("9517423000");
		ReportManager.attachScreenshot("Account address info Entered", captureScreenshot());
		signupdetailPage().clickCreateAcc();

		// Step: 5 - verify Account created
		logger.info("Running, Step: 5 - Verify account is created");
		ReportManager.logStep("Verifying whether account is created");
		signupdetailPage().verifyPageLoaded("/account_created", "Account Created");
		signupdetailPage().verifyTextMessageDisplayed("Account Created!", false);
		signupdetailPage().clickContinueButton();

		// Step:6 - verify Logged in as user name is visible and able to logout
		logger.info("Running, Step: 6 - Logged in as username is visible and able to logout");
		ReportManager.logStep("Verifying whether logged in as username is visible");
		
		homePage().verifyLoggedIn(userName);

		// Step:7 - Navigate to cart page and proceed to checkout
		logger.info("Running, Step: 7 - Navigate to cart page and proceed to checkout");
		ReportManager.logStep("Navigate to cart page and proceed to checkout");
				
		homePage().clickCartLink();
		cartPage().proceedCheckout();

		// Step:8 - Verify address details and review cart summary
		logger.info("Running, Step: 8 - Verify address details and review cart summary");
		ReportManager.logStep("Verify address details and review cart summary");
		
		checkoutPage().verifyAddressDetails("address_delivery", "male", userName, "CFTest", "584 Main RD", "D Colony",
				"India", "TN", "CBE", "600001", "9517423000");		
		checkoutPage().verifyAddressDetails("address_invoice", "male", userName, "CFTest", "584 Main RD", "D Colony",
				"India", "TN", "CBE", "600001", "9517423000");
		
		cartPage().verifyProductsInCart(addProd);
			
		// Step:9 - Place order, complete payment and verify order has been confirmed
		logger.info("Running, Step: 9 - Place order, complete payment and verify order has been confirmed");
		ReportManager.logStep("Place order, complete payment and verify order has been confirmed");
		
		checkoutPage().addComment("Expecting product delivery asap");
		checkoutPage().clickPlaceOrderButton();
		
		paymentPage().addPaymentDetails(userName, "15987462032", "311", "10", "2030");
		paymentPage().clickPayCOnfirmOrderBtn();
		
		paymentPage().verifyTextMessageDisplayed("Your order has been confirmed", false);
		ReportManager.attachScreenshot("Order has been confirmed", captureScreenshot());
		
		// Step:10 - Delete Account
		logger.info("Running, Step: 10 - Delete Account");
		ReportManager.logStep("Verifying whether account is deleted");
		
		homePage().clickDeleteAccountLink();
		homePage().verifyPageLoaded("/delete_account", "Account Created");
		homePage().verifyTextMessageDisplayed("Account Deleted!", true);
		homePage().clickContinueButton();
		
		logger.info("=====================End Case: Register while checkout=========================");
	}
	
	// Case:15 - Verify user can successfully register, add products, place order and delete account
	
	@Test(description = "AE06_TC15_Verify user can successfully register, add products, place order and delete account")
	@Epic("AE-6, User should be able to place order and proceed with checkout")
	@Story("AE-17, Successfully place order")
	public void Test_AE06_TC15_Verify_Register_Before_Checkout() {

		logger.info(
				"====================================================================================================");
		logger.info(
				"Start, Case:15 - Verify user can successfully register, add products, place order and delete account");
		logger.info(
				"====================================================================================================");

		// Step:1 - Verify Home page is displayed and Navigate to SignUp page
		logger.info("Running, Step:1 - Verify Homepage is displayed and Navigate to SignUp page");
		ReportManager.logStep("Verify Homepage is displayed and Naviagting to Signup page");
		
		homePage().verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage().clickSignupLoginLink();
		
		// Step:2 - Enter SignUp details
		logger.info("Running, Step: 2 - Entering Signup credentials");
		ReportManager.logStep("Entering signup credentials on successful navigation");
		
		String userName = "CFTestUser" + System.currentTimeMillis();
		String email = userName + "@gmail.com";
		String pwd = "CF@pwd" + UUID.randomUUID().toString().substring(0, 3);
		signuploginPage().enterSignupDetails(userName, email);
		signuploginPage().clickSignup();
		signupdetailPage().verifyPageLoaded("/signup", "Signup");
		signupdetailPage().verifyTextMessageDisplayed("Enter Account Information", false);
		signupdetailPage().verifyTextMessageDisplayed("Address Information", true);
		signupdetailPage().verifyAutoPopNameandEmail(userName, email);

		// Step:3 - Enter user account details
		logger.info("Running, Step: 3 - Enter user account details");
		ReportManager.logStep("Entering new user account details");
		signupdetailPage().selectGender("male");
		signupdetailPage().enterPassword(pwd);
		signupdetailPage().selectDOB("10", "March", "1996");
		signupdetailPage().optNewsLetter(true);
		signupdetailPage().optSpecialOffer(false);
		ReportManager.attachScreenshot("Account Info Entered", captureScreenshot());
		signupdetailPage().enterNames(userName, "CFTest");
		signupdetailPage().enterCompanyDetails("CF");
		signupdetailPage().enterAddressDetails("584 Main RD", "D Colony", "India", "TN", "CBE", "600001");
		signupdetailPage().enterMobileNum("9517423000");
		ReportManager.attachScreenshot("Account address info Entered", captureScreenshot());
		signupdetailPage().clickCreateAcc();

		// Step: 4 - verify Account created
		logger.info("Running, Step: 4 - Verify account is created");
		ReportManager.logStep("Verifying whether account is created");
		signupdetailPage().verifyPageLoaded("/account_created", "Account Created");
		signupdetailPage().verifyTextMessageDisplayed("Account Created!", false);
		signupdetailPage().clickContinueButton();

		// Step:5 - verify Logged in as user name is visible and able to logout
		logger.info("Running, Step: 5 - Logged in as username is visible and able to logout");
		ReportManager.logStep("Verifying whether logged in as username is visible");
		
		homePage().verifyLoggedIn(userName);	
		
		// Step:6 - Add products to cart and navigate to cart page
		logger.info("Running, Step: 6  -Add products to cart and navigate to cart page");
		ReportManager.logStep("Add products to cart and navigate to cart page");
				
		String addProd = productsPage().viewProductWithIndex(2);
		productdetailPage().clickAddToCartButton();
		productdetailPage().clickViewCartLink();
		productsPage().verifyPageLoaded("view_cart", "Checkout");

		// Step:7 - Proceed to checkout, Verify address details and Review the order
		logger.info("Running, Step: 7 -  Proceed to checkout, Verify address details and Review the order");
		ReportManager.logStep(" Proceed to checkout, Verify address details and Review the order");
		
		cartPage().proceedCheckout();

		checkoutPage().verifyAddressDetails("address_delivery", "male", userName, "CFTest", "584 Main RD", "D Colony",
				"India", "TN", "CBE", "600001", "9517423000");
		checkoutPage().verifyAddressDetails("address_invoice", "male", userName, "CFTest", "584 Main RD", "D Colony",
				"India", "TN", "CBE", "600001", "9517423000");

		cartPage().verifyProductsInCart(addProd);

		// Step:8 - Place order, complete payment and verify order has been confirmed
		logger.info("Running, Step: 8 - Place order, complete payment and verify order has been confirmed");
		ReportManager.logStep("Place order, complete payment and verify order has been confirmed");

		checkoutPage().addComment("Expecting product delivery asap");
		checkoutPage().clickPlaceOrderButton();

		paymentPage().addPaymentDetails(userName, "15987462032", "311", "10", "2030");
		paymentPage().clickPayCOnfirmOrderBtn();

		paymentPage().verifyTextMessageDisplayed("Your order has been confirmed", false);
		ReportManager.attachScreenshot("Order has been confirmed", captureScreenshot());
		
		// Step:9 - Delete Account
		logger.info("Running, Step: 9 - Delete Account");
		ReportManager.logStep("Verifying whether account is deleted");

		homePage().clickDeleteAccountLink();
		homePage().verifyPageLoaded("/delete_account", "Account Created");
		homePage().verifyTextMessageDisplayed("Account Deleted!", true);
		homePage().clickContinueButton();
		
		logger.info("=====================End Case: Register before checkout=========================");
	}
	
	// Case:16 - Verify user can successfully login add products, place order and delete their account

	@Test(description = "AE06_TC16_Verify user can successfully login add products, place order and delete their account")
	@Epic("AE-6, User should be able to place order and proceed with checkout")
	@Story("AE-17, Successfully place order")
	public void Test_AE06_TC16_Verify_Login_Before_Checkout() {

			logger.info(
					"======================================================================================================");
			logger.info(
					"Start, Case:16 - Verify user can successfully login add products, place order and delete their account");
			logger.info(
					"======================================================================================================");
			
			// Step:1 - Verify Home page is displayed and Navigate to Login page
			logger.info("Running, Step:1 - Verify Homepage is displayed and Navigate to Login page");
			ReportManager.logStep("Verify Homepage is displayed and Naviagting to Login page");
			homePage().verifyPageLoaded("automationexercise", "Automation Exercise");
			homePage().clickSignupLoginLink();
			signuploginPage().verifyPageLoaded("/login", "Signup");

			// Step:2 - Enter SignUp details
			logger.info("Running, Step: 2 - Entering Signup credentials");
			ReportManager.logStep("Entering signup credentials on successful navigation");
			String userName = "CFTestUser" + System.currentTimeMillis();
			String email = userName + "@gmail.com";
			String pwd = "CF@pwd" + UUID.randomUUID().toString().substring(0, 3);
			signuploginPage().enterSignupDetails(userName, email);
			signuploginPage().clickSignup();
			signupdetailPage().verifyPageLoaded("/signup", "Signup");
			signupdetailPage().verifyTextMessageDisplayed("Enter Account Information", false);
			signupdetailPage().verifyTextMessageDisplayed("Address Information", true);
			signupdetailPage().verifyAutoPopNameandEmail(userName, email);

			// Step:3 - Enter user account details
			logger.info("Running, Step: 3 - Enter user account details");
			ReportManager.logStep("Entering new user account details");
			signupdetailPage().selectGender("male");
			signupdetailPage().enterPassword(pwd);
			signupdetailPage().selectDOB("10", "March", "1996");
			signupdetailPage().optNewsLetter(true);
			signupdetailPage().optSpecialOffer(false);
			ReportManager.attachScreenshot("Account Info Entered", captureScreenshot());
			signupdetailPage().enterNames(userName, "CFTest");
			signupdetailPage().enterCompanyDetails("CF");
			signupdetailPage().enterAddressDetails("584 Main RD", "D Colony", "India", "TN", "CBE", "600001");
			signupdetailPage().enterMobileNum("9517423000");
			signupdetailPage().clickCreateAcc();

			// Step: 4 - verify Account created
			logger.info("Running, Step: 4 - Verify account is created");
			ReportManager.logStep("Verifying whether account is created");
			signupdetailPage().verifyPageLoaded("/account_created", "Account Created");
			signupdetailPage().verifyTextMessageDisplayed("Account Created!", false);
			signupdetailPage().clickContinueButton();

			// Step: 5 - verify user is able to logout and navigate to login page
			logger.info("Running, Step: 5 - verify user is able to logout and navigate to login page");
			ReportManager.logStep("Verify whether user is able to logout and navigate to login page back");
			homePage().clickLogoutLink();
			signuploginPage().verifyPageLoaded("/login", "Signup");

			// Step: 6 - verify Login to your account is visible and enter Email and Password
			logger.info("Running, Step: 6 - verify Login to your account is visible and enter Email and Password");
			ReportManager.logStep("Verify whether Login to your account is visible and enter Email and Password");
			homePage().verifyTextMessageDisplayed("Login to your account", false);
			signuploginPage().enterLoginDetails(email, pwd);
			signuploginPage().clickLogin();

			// Step: 7 - verify Logged in as user name is visible
			logger.info("Running, Step: 7 - Logged in as username is visible");
			ReportManager.logStep("Verifying whether logged in as username is visible");
			homePage().verifyLoggedIn(userName);
			
			// Step:8 - Add products to cart and navigate to cart page
			logger.info("Running, Step: 8  -Add products to cart and navigate to cart page");
			ReportManager.logStep("Add products to cart and navigate to cart page");
					
			String addProd = productsPage().viewProductWithName("White");
			productdetailPage().clickAddToCartButton();
			productdetailPage().clickViewCartLink();
			productsPage().verifyPageLoaded("view_cart", "Checkout");

			// Step:9 - Proceed to checkout, Verify address details and Review the order
			logger.info("Running, Step: 9 -  Proceed to checkout, Verify address details and Review the order");
			ReportManager.logStep(" Proceed to checkout, Verify address details and Review the order");
			
			cartPage().proceedCheckout();

			checkoutPage().verifyAddressDetails("address_delivery", "male", userName, "CFTest", "584 Main RD", "D Colony",
					"India", "TN", "CBE", "600001", "9517423000");
			checkoutPage().verifyAddressDetails("address_invoice", "male", userName, "CFTest", "584 Main RD", "D Colony",
					"India", "TN", "CBE", "600001", "9517423000");

			cartPage().verifyProductsInCart(addProd);

			// Step:10 - Place order, complete payment and verify order has been confirmed
			logger.info("Running, Step: 10 - Place order, complete payment and verify order has been confirmed");
			ReportManager.logStep("Place order, complete payment and verify order has been confirmed");

			checkoutPage().addComment("Expecting product delivery asap");
			checkoutPage().clickPlaceOrderButton();

			paymentPage().addPaymentDetails(userName, "15987462032", "311", "10", "2030");
			paymentPage().clickPayCOnfirmOrderBtn();

			paymentPage().verifyTextMessageDisplayed("Your order has been confirmed", false);
			ReportManager.attachScreenshot("Order has been confirmed", captureScreenshot());
			
			// Step:11 - Delete Account
			logger.info("Running, Step: 11 - Delete Account");
			ReportManager.logStep("Verifying whether account is deleted");

			homePage().clickDeleteAccountLink();
			homePage().verifyPageLoaded("/delete_account", "Account Created");
			homePage().verifyTextMessageDisplayed("Account Deleted!", true);
			homePage().clickContinueButton();
			
			logger.info("=====================End Case: Login before checkout=========================");
	}		

	// Case:23 - Verify user is able to register, log in, add products to cart, proceed to checkout, delivery/billing address check and delete their account

	@Test(description = "AE06_TC23_Verify user is able to register, log in, add products, checkout, address check and delete account")
	@Epic("AE-6, User should be able to place order and proceed with checkout")
	@Story("AE-18, Address verification on checkout and Invoice download  ")
	public void Test_AE06_TC23_Verify_Address_CheckoutPage() {

		logger.info(
				"==================================================================================================================");
		logger.info(
				"Start, Case:23 - Verify user is able to register, log in, add products, checkout, address check and delete account");
		logger.info(
				"==================================================================================================================");
		// Step:1 - Verify Home page is displayed and Navigate to SignUp page
		logger.info("Running, Step:1 - Verify Homepage is displayed and Navigate to SignUp page");
		ReportManager.logStep("Verify Homepage is displayed and Naviagting to Signup page");

		homePage().verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage().clickSignupLoginLink();

		// Step:2 - Enter SignUp details
		logger.info("Running, Step: 2 - Entering Signup credentials");
		ReportManager.logStep("Entering signup credentials on successful navigation");

		String userName = "CFTestUser" + System.currentTimeMillis();
		String email = userName + "@gmail.com";
		String pwd = "CF@pwd" + UUID.randomUUID().toString().substring(0, 3);
		signuploginPage().enterSignupDetails(userName, email);
		signuploginPage().clickSignup();
		signupdetailPage().verifyPageLoaded("/signup", "Signup");
		signupdetailPage().verifyTextMessageDisplayed("Enter Account Information", false);
		signupdetailPage().verifyTextMessageDisplayed("Address Information", true);
		signupdetailPage().verifyAutoPopNameandEmail(userName, email);

		// Step:3 - Enter user account details
		logger.info("Running, Step: 3 - Enter user account details");
		ReportManager.logStep("Entering new user account details");
		signupdetailPage().selectGender("male");
		signupdetailPage().enterPassword(pwd);
		signupdetailPage().selectDOB("10", "March", "1996");
		signupdetailPage().optNewsLetter(true);
		signupdetailPage().optSpecialOffer(false);
		ReportManager.attachScreenshot("Account Info Entered", captureScreenshot());
		signupdetailPage().enterNames(userName, "CFTest");
		signupdetailPage().enterCompanyDetails("CF");
		signupdetailPage().enterAddressDetails("584 Main RD", "D Colony", "India", "TN", "CBE", "600001");
		signupdetailPage().enterMobileNum("9517423000");
		ReportManager.attachScreenshot("Account address info Entered", captureScreenshot());
		signupdetailPage().clickCreateAcc();

		// Step: 4 - verify Account created
		logger.info("Running, Step: 4 - Verify account is created");
		ReportManager.logStep("Verifying whether account is created");
		signupdetailPage().verifyPageLoaded("/account_created", "Account Created");
		signupdetailPage().verifyTextMessageDisplayed("Account Created!", false);
		signupdetailPage().clickContinueButton();

		// Step:5 - verify Logged in as user name is visible and able to logout
		logger.info("Running, Step: 5 - Logged in as username is visible and able to logout");
		ReportManager.logStep("Verifying whether logged in as username is visible");

		homePage().verifyLoggedIn(userName);

		// Step:6 - Add products to cart and navigate to cart page
		logger.info("Running, Step: 6  -Add products to cart and navigate to cart page");
		ReportManager.logStep("Add products to cart and navigate to cart page");

		productsPage().addToCartHoverwithIndex(1);
		productsPage().clickViewCartLink();
		productsPage().verifyPageLoaded("view_cart", "Checkout");

		// Step:7 - Proceed to checkout, Verify address details and Review the order
		logger.info("Running, Step: 7 -  Proceed to checkout, Verify address details and Review the order");
		ReportManager.logStep(" Proceed to checkout, Verify address details and Review the order");

		cartPage().proceedCheckout();

		checkoutPage().verifyAddressDetails("address_delivery", "male", userName, "CFTest", "584 Main RD", "D Colony",
				"India", "TN", "CBE", "600001", "9517423000");
		checkoutPage().verifyAddressDetails("address_invoice", "male", userName, "CFTest", "584 Main RD", "D Colony",
				"India", "TN", "CBE", "600001", "9517423000");

		ReportManager.attachScreenshot("Address has been verified successfully", captureScreenshot());

		// Step:8 - Delete Account
		logger.info("Running, Step: 8 - Delete Account");
		ReportManager.logStep("Verifying whether account is deleted");

		homePage().clickDeleteAccountLink();
		homePage().verifyPageLoaded("/delete_account", "Account Created");
		homePage().verifyTextMessageDisplayed("Account Deleted!", true);
		homePage().clickContinueButton();

		logger.info("=====================End Case: Address verification at checkout page=========================");
	}

		// Case:24 - Verify user can successfully register while checkout, place order, do payments, download invoice, and delete their account

		@Test(description = "AE06_TC24_Verify user is able to register while checkout, place order, do payments, download invoice, and delete account")
		@Epic("AE-6, User should be able to place order and proceed with checkout")
		@Story("AE-18, Successfully place order")
		public void Test_AE06_TC24_Verify_DownloadInvoice_AfterPurchaseOrder() {

			logger.info(
					"===============================================================================================================================");
			logger.info(
					"Start, Case:24 - Verify user is able to register while checkout, place order, do payments, download invoice, and delete account");
			logger.info(
					"===============================================================================================================================");
			
			// Step:1 - Verify Home page is displayed and add products to cart page
			logger.info("Running, Step:1 - Verify Home page is displayed and add products to cart page");
			ReportManager.logStep("Verify Home page is displayed and add products to cart page");

			homePage().verifyPageLoaded("automationexercise", "Automation Exercise");
			String addProd = productsPage().addToCartHoverwithIndex(1);
			productsPage().clickViewCartLink();
			productsPage().verifyPageLoaded("view_cart", "Checkout");

			// Step:2 - Click register/login while trying to checkout and verify navigating to sign up page
			logger.info(
					"Running, Step:2 - Click register/login link while trying to checkout and verify navigating to signup page");
			ReportManager.logStep("Click register/login while trying to checkout and verify navigating to signup page");

			cartPage().proceedCheckout();
			cartPage().clickRegisterLoginLink();

			signuploginPage().verifyPageLoaded("/login", "Signup");

			// Step:3 - Enter SignUp details
			logger.info("Running, Step: 3 - Entering Signup credentials");
			ReportManager.logStep("Entering signup credentials on successful navigation");

			String userName = "CFTestUser" + System.currentTimeMillis();
			String email = userName + "@gmail.com";
			String pwd = "CF@pwd" + UUID.randomUUID().toString().substring(0, 3);
			signuploginPage().enterSignupDetails(userName, email);
			signuploginPage().clickSignup();
			signupdetailPage().verifyPageLoaded("/signup", "Signup");
			signupdetailPage().verifyTextMessageDisplayed("Enter Account Information", false);
			signupdetailPage().verifyTextMessageDisplayed("Address Information", true);
			signupdetailPage().verifyAutoPopNameandEmail(userName, email);

			// Step:4 - Enter user account details
			logger.info("Running, Step: 4 - Enter user account details");
			ReportManager.logStep("Entering new user account details");
			signupdetailPage().selectGender("male");
			signupdetailPage().enterPassword(pwd);
			signupdetailPage().selectDOB("10", "March", "1996");
			signupdetailPage().optNewsLetter(true);
			signupdetailPage().optSpecialOffer(false);
			ReportManager.attachScreenshot("Account Info Entered", captureScreenshot());
			signupdetailPage().enterNames(userName, "CFTest");
			signupdetailPage().enterCompanyDetails("CF");
			signupdetailPage().enterAddressDetails("584 Main RD", "D Colony", "India", "TN", "CBE", "600001");
			signupdetailPage().enterMobileNum("9517423000");
			ReportManager.attachScreenshot("Account address info Entered", captureScreenshot());
			signupdetailPage().clickCreateAcc();

			// Step: 5 - verify Account created
			logger.info("Running, Step: 5 - Verify account is created");
			ReportManager.logStep("Verifying whether account is created");
			signupdetailPage().verifyPageLoaded("/account_created", "Account Created");
			signupdetailPage().verifyTextMessageDisplayed("Account Created!", false);
			signupdetailPage().clickContinueButton();

			// Step:6 - verify Logged in as user name is visible and able to logout
			logger.info("Running, Step: 6 - Logged in as username is visible and able to logout");
			ReportManager.logStep("Verifying whether logged in as username is visible");

			homePage().verifyLoggedIn(userName);

			// Step:7 - Navigate to cart page and proceed to checkout
			logger.info("Running, Step: 7 - Navigate to cart page and proceed to checkout");
			ReportManager.logStep("Navigate to cart page and proceed to checkout");

			homePage().clickCartLink();
			cartPage().proceedCheckout();

			// Step:8 - Verify address details and review cart summary
			logger.info("Running, Step: 8 - Verify address details and review cart summary");
			ReportManager.logStep("Verify address details and review cart summary");

			checkoutPage().verifyAddressDetails("address_delivery", "male", userName, "CFTest", "584 Main RD", "D Colony",
					"India", "TN", "CBE", "600001", "9517423000");
			checkoutPage().verifyAddressDetails("address_invoice", "male", userName, "CFTest", "584 Main RD", "D Colony",
					"India", "TN", "CBE", "600001", "9517423000");

			cartPage().verifyProductsInCart(addProd);

			// Step:9 - Place order, complete payment and verify order has been confirmed
			logger.info("Running, Step: 9 - Place order, complete payment and verify order has been confirmed");
			ReportManager.logStep("Place order, complete payment and verify order has been confirmed");

			checkoutPage().addComment("Expecting product delivery asap");
			checkoutPage().clickPlaceOrderButton();

			paymentPage().addPaymentDetails(userName, "15987462032", "311", "10", "2030");
			paymentPage().clickPayCOnfirmOrderBtn();

			paymentPage().verifyTextMessageDisplayed("Your order has been confirmed", false);
			ReportManager.attachScreenshot("Order has been confirmed", captureScreenshot());

			// Step:10 - Download invoice
			logger.info("Running, Step: 10 - Download invoice");
			ReportManager.logStep("Download invoice");

			Path path = paymentPage().downloadInvoice();
			paymentPage().verifyInvoiceDwonload(path);

			// Step:11 - Delete Account
			logger.info("Running, Step: 11 - Delete Account");
			ReportManager.logStep("Verifying whether account is deleted");

			homePage().clickDeleteAccountLink();
			homePage().verifyPageLoaded("/delete_account", "Account Created");
			homePage().verifyTextMessageDisplayed("Account Deleted!", true);
			homePage().clickContinueButton();

			logger.info("=====================End Case: Invoice downloaded successfully=========================");
		}

	}
