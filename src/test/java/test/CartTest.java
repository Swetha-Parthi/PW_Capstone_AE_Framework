package test;

import java.util.List;

import org.testng.annotations.Test;

import base.BaseTest;
import framework.reporting.ReportManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import pages.ae.CartPage;
import pages.ae.HomePage;
import pages.ae.ProductDetailPage;
import pages.ae.ProductsPage;

public class CartTest extends BaseTest {

	// Case:11 - Verify user can successfully subscribe from the Cart page

	@Test(description = "AE05_TC11_Verify user can successfully subscribe from the Cart page")
	@Epic("AE-5, Users should be able to subscribe to automation exercise website")
	@Story("AE-16, User Subscription")
	public void Test_AE05_TC11_Verify_CartPageSubscription() {

		logger.info("==========================================================================");
		logger.info("Start, Case:11 - Verify user can successfully subscribe from the Cart page");
		logger.info("==========================================================================");

		HomePage homePage = pageManager.getHomePage();
		CartPage cartPage = pageManager.getcartPage();

		// Step:1 - Verify Home page is displayed and navigate to cart page
		logger.info("Running, Step:1 - Verify Home page is displayed and Navigate to Products page");
		ReportManager.logStep("Verify Home page is displayed");

		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage.clickCartLink();

		// Step:2 - Scroll down to footer and verify subscription text
		logger.info("Running, Step:2 - Scroll down to footer and verify subscription text");
		ReportManager.logStep("Scroll down to footer and verify subscription text");

		cartPage.scrolldown();
		cartPage.verifyTextMessageDisplayed("SUBSCRIPTION", true);

		// Step:3 - Enter email and verify text message is displayed
		logger.info("Running, Step:3 - Enter email and verify text message is displayed");
		ReportManager.logStep("Enter email and verify text message is displayed");

		cartPage.enterSubscriptionEmail("AETestUser@gmail.com");
		ReportManager.attachScreenshot("Successfully subscribed", captureScreenshot());
		cartPage.verifyTextMessageDisplayed("You have been successfully subscribed!", false);

		logger.info("=====================End Case: User subscription from cart page=========================");
	}

	// Case:17 - Verify user can successfully add and remove products

	@Test(description = "AE04_TC17_Verify user can successfully add and remove products")
	@Epic("AE-4, User should be able to search & view the products and add the selected products to shopping cart")
	@Story("AE-15, Add, manage and remove products in cart")
	public void Test_AE04_TC17_Verify_Products_AddRemove() {

		logger.info("=====================================================================");
		logger.info("Start, Case:17 - Verify user can successfully add and remove products");
		logger.info("=====================================================================");

		HomePage homePage = pageManager.getHomePage();
		ProductsPage productsPage = pageManager.getproductsPage();
		ProductDetailPage productdetailPage = pageManager.getproductdetailPage();
		CartPage cartPage = pageManager.getcartPage();

		// Step:1 - Verify Home page is displayed and add products to cart
		logger.info("Running, Step:1 - Verify Home page is displayed and add products to cart");
		ReportManager.logStep("Verify Home page is displayed and add products to cart");

		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		String removeProd = productsPage.viewProductWithName("Blue Top");
		productdetailPage.clickAddToCartButton();

		// Step:2 - Verify cart page is displayed and remove products from cart
		logger.info("Running, Step:2 - Verify cart page is displayed and remove products from cart");
		ReportManager.logStep("Verify cart page is displayed and remove products from cart");

		productsPage.clickViewCartLink();
		homePage.verifyPageLoaded("view_cart", "Checkout");
		cartPage.removeProductsInCart(removeProd);

		logger.info("=====================End Case: Successfully removed products=========================");
	}

	// Case:20 - Verify user is able to add products, login and view selected products again cart page

	@Test(description = "AE04_TC20_Verify user is able to add products, login and view selected products again cart page")
	@Epic("AE-4, User should be able to search & view the products and add the selected products to shopping cart")
	@Story("AE-15, Add, manage and remove products in cart")
	public void Test_AE04_TC20_Verify_AddProducts_Login_ViewCart() {

		logger.info(
				"======================================================================================================");
		logger.info(
				"Start, Case:20 - Verify user is able to add products, login and view selected products again cart page");
		logger.info(
				"======================================================================================================");

		HomePage homePage = pageManager.getHomePage();
		ProductsPage productsPage = pageManager.getproductsPage();
		CartPage cartPage = pageManager.getcartPage();

		// Step:1 - Verify Home page is displayed and navigate to products page
		logger.info("Running, Step:1 - Verify Home page is displayed and navigate to products page");
		ReportManager.logStep("Verify Home page is displayed and navigate to products page");

		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage.clickProductsLink();

		// Step:2 - Verify all Products page is displayed
		logger.info("Running, Step:2 - Verify all Products page is displayed ");
		ReportManager.logStep("Verify all Products page is displayed ");

		productsPage.verifyPageLoaded("products", "All Products");

		// Step:3 - Search product and verify searched products are visible
		logger.info("Running, Step:3 - Search product and verify searched products are visible");
		ReportManager.logStep("Search product and verify searched products are visible");

		String searchText = productsPage.searchProduct("Jean");
		productsPage.verifyTextMessageDisplayed("Searched Products", false);
		productsPage.verifySearchResult(searchText);

		// Step:4 - Verify searched products are added to cart and visible in cart
		logger.info("Running, Step:4 - Verify searched products are added to cart and visible in cart");
		ReportManager.logStep("Verify searched products are added to cart and visible in cart");
				
		List<String> listProd = productsPage.addSearchListToCartPage();
		homePage.clickCartLink();
		cartPage.verifySearchListProdInCart(listProd);

		logger.info(
				"=====================End Case: Add products, Login and View products in cart=========================");
	}
}
