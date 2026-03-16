package test;

import org.testng.annotations.Test;

import base.BaseTest;
import framework.reporting.ReportManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import pages.ae.CartPage;
import pages.ae.HomePage;
import pages.ae.ProductDetailPage;
import pages.ae.ProductsPage;

public class ProductsTest extends BaseTest {

	// Case:8 - Verify user is able to click 'Products' link, select first product and see product details

	@Test(description = "AE04_TC08_Verify user is able to click 'Products' link, select first product and see product details")
	@Epic("AE-4, User should be able to search & view the products and add the selected products to shopping cart")
	@Story("AE-13, View products")
	public void Test_AE04_TC08_Verify_click_ProductsLink_viewProductDetails() {

		logger.info(
				"==========================================================================================================");
		logger.info(
				"Start, Case:8 - Verify user is able to click 'Products' link, select first product and see product details");
		logger.info(
				"==========================================================================================================");

		HomePage homePage = pageManager.getHomePage();
		ProductsPage productsPage = pageManager.getproductsPage();
		ProductDetailPage productdetailPage = pageManager.getproductdetailPage();

		// Step:1 - Verify Home page is displayed and Navigate to Products page
		logger.info("Running, Step:1 - Verify Home page is displayed and Navigate to Products page");
		ReportManager.logStep("Verify Home page is displayed");

		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage.clickProductsLink();

		// Step:2 - Verify Products are listed under All Products page is displayed
		logger.info("Running, Step:2 - Verify Products are listed under All Products page is displayed ");
		ReportManager.logStep("Verify Products are listed under All Products page is displayed ");

		productsPage.verifyPageLoaded("products", "All Products");
		productsPage.verifyProductsListVisibility();

		// Step:3 - Select first product and verify product details are visible
		logger.info("Running, Step:3 - Selecting first product and verify product details are visible");
		ReportManager.logStep("Selecting first product and verify product details are visible");

		String prodIndex = productsPage.viewProductWithIndex(0);
		productsPage.verifyPageLoaded("product_details", "Product Details");
		productdetailPage.verifyProductDetails(prodIndex);
		ReportManager.attachScreenshot("Product Details displayed successfully", captureScreenshot());
		
		logger.info("=====================End Case: View Products=========================");
	}

	// Case:9 - Verify user is able search products and searched products are listed

	@Test(description = "AE04_TC09_Verify user is able search products and searched products are listed")
	@Epic("AE-4, User should be able to search & view the products and add the selected products to shopping cart")
	@Story("AE-14, Search products and submit review ")
	public void Test_AE04_TC09_Verify_ProductsSearch() {

		logger.info("====================================================================================");
		logger.info("Start, Case:9 - Verify user is able search products and searched products are listed");
		logger.info("====================================================================================");

		HomePage homePage = pageManager.getHomePage();
		ProductsPage productsPage = pageManager.getproductsPage();

		// Step:1 - Verify Home page is displayed and Navigate to Products page
		logger.info("Running, Step:1 - Verify Home page is displayed and Navigate to Products page");
		ReportManager.logStep("Verify Home page is displayed");

		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage.clickProductsLink();

		// Step:2 - Verify all Products page is displayed
		logger.info("Running, Step:2 - Verify all Products page is displayed ");
		ReportManager.logStep("Verify all Products page is displayed ");

		productsPage.verifyPageLoaded("products", "All Products");

		// Step:3 - Search product and verify searched products are visible
		logger.info("Running, Step:3 - Search product and verify searched products are visible");
		ReportManager.logStep("Search product and verify searched products are visible");

		String searchText = productsPage.searchProduct("Tops");
		productsPage.verifyTextMessageDisplayed("Searched Products", false);
		productsPage.verifySearchResult(searchText);
		ReportManager.attachScreenshot("Searched products are displayed successfully", captureScreenshot());
		
		logger.info("=====================End Case: Search Products=========================");

	}
	

	// Case:12 - Verify user can successfully add the products to cart from products page and see their prices, quantity and total price

	@Test(description = "AE04_TC12_Verify user can succesfully add products and see their details")
	@Epic("AE-4, User should be able to search & view the products and add the selected products to shopping cart")
	@Story("AE-15, Add, manage and remove products in cart")
	public void Test_AE04_TC12_Verify_AddProduct_VerifyDetails() {

		logger.info("====================================================================================");
		logger.info("Start, Case:12 - Verify user can succesfully add products and see their details");
		logger.info("====================================================================================");

		HomePage homePage = pageManager.getHomePage();
		ProductsPage productsPage = pageManager.getproductsPage();
		CartPage cartPage = pageManager.getcartPage();
		
		// Step:1 - Verify Home page is displayed and navigate to products page
		logger.info("Running, Step:1 - Verify Home page is displayed and navigate to products page");
		ReportManager.logStep("Verify Home page is displayed and navigate to products page");

		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage.clickProductsLink();
		
		// Step:2 - Add first product to cart by hovering over
		logger.info("Running, Step:2 - Add first product to cart by hovering over");
		ReportManager.logStep("Add first product to cart by hovering over");

		String product1 = productsPage.addToCartHoverwithIndex(0);
		productsPage.clickContinueShopBtn();
		
		// Step:3 - Add second product to cart by hovering over
		logger.info("Running, Step:3 - Add second product to cart by hovering over");
		ReportManager.logStep("Add second product to cart by hovering over");
		
		String product2 =productsPage.addToCartHoverwithIndex(1);
		productsPage.clickViewCartLink();
		
		// Step:4 - Verify product details like price, quantity and total price in cart page
		logger.info("Running, Step:4 -  Verify product details like price, quantity and total price in cart page");
		ReportManager.logStep(" Verify product details like price, quantity and total price in cart page");
		
		cartPage.verifyProductsInCart(product1);
		cartPage.verifyProductsInCart(product2);	
		ReportManager.attachScreenshot("Product Details are displayed successfully", captureScreenshot());
		
		logger.info("=====================End Case: Add products=========================");
	}
	

	// Case:13 - Verify user can successfully add any products with quantity 4

	@Test(description = "AE04_TC13_Verify user can successfully add any products with quantity 4")
	@Epic("AE-4, User should be able to search & view the products and add the selected products to shopping cart")
	@Story("AE-15, Add, manage and remove products in cart")
	public void Test_AE04_TC13_Verify_AddProductwithQuantity4_VerifyDetails() {

		logger.info("====================================================================================");
		logger.info("Start, Case:13 - Verify user can successfully add any products with quantity 4");
		logger.info("====================================================================================");

		HomePage homePage = pageManager.getHomePage();
		ProductsPage productsPage = pageManager.getproductsPage();
		ProductDetailPage productdetailPage = pageManager.getproductdetailPage();
		CartPage cartPage = pageManager.getcartPage();
		
		// Step:1 - Verify Home page is displayed and navigate to products page
		logger.info("Running, Step:1 - Verify Home page is displayed and navigate to products page");
		ReportManager.logStep("Verify Home page is displayed and navigate to products page");

		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage.clickProductsLink();
		
		// Step:2 - Add any product to cart with quantity to 4
		logger.info("Running, Step:2 - Add any product to cart with quantity to 4");
		ReportManager.logStep("Add any product to cart with quantity to 4");

		String nameProd = productsPage.viewProductWithIndex(5);
		int addedQuantity = productdetailPage.addQuantity(4);
		productdetailPage.clickAddToCartButton();
		
		// Step:3 - Verify product is displayed with added quantity in cart page
		logger.info("Running, Step:3 - Verify product is displayed with added quantity in cart page");
		ReportManager.logStep("Verify product is displayed with added quantity in cart page");
		
		productsPage.clickViewCartLink();
		cartPage.verifQuantity(addedQuantity, nameProd);
		ReportManager.attachScreenshot("Products with selected quantity are displayed successfully", captureScreenshot());
	
		logger.info("=====================End Case: Add products with Quantity 4=========================");
	}
	
	// Case:18 - Verify user can successfully navigate between the product categories

	@Test(description = "AE04_TC18_Verify user can succesfully naviagte between the product categories")
	@Epic("AE-4, User should be able to search & view the products and add the selected products to shopping cart")
	@Story("AE-13, View Products")
	public void Test_AE04_TC18_Verify_ProductNavigation_Categories() {

		logger.info("====================================================================================");
		logger.info("Start, Case:18 - Verify user can succesfully naviagte between the product categories");
		logger.info("====================================================================================");

		HomePage homePage = pageManager.getHomePage();
		ProductsPage productsPage = pageManager.getproductsPage();

		// Step:1 - Verify Home page is displayed 
		logger.info("Running, Step:1 - Verify Home page is displayed");
		ReportManager.logStep("Verify Home page is displayed");

		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");

		// Step:2 - Verify categories are displayed
		logger.info("Running, Step:2 - Verify categories are displayed");
		ReportManager.logStep("Verify brands are displayed");

		homePage.verifyBrandCategoryDisplay();

		// Step:3 - Choose Women category with sub category 'Dress' and Verify category page related to that products are displayed
		logger.info(
				"Running, Step:3 -  Choosing Women category & sub category 'Dress', Verify category page related to that products are displayed");
		ReportManager.logStep("Choosing Women category & sub category 'Dress', Verify category page related to that products are displayed");

		productsPage.initializeCategory();
		productsPage.selectCategory("women", "dress");
		productsPage.verifyPageLoaded("category_products", "Dress Products");
		productsPage.verifyTextMessageDisplayed("Women - Dress Products", false);
		ReportManager.attachScreenshot("First category products are displayed succesfully", captureScreenshot());

		// Step:4 - Choose Men category with sub category 'Jeans' and Verify category page related to that products are displayed
		logger.info("Running, Step:3 -  Choosing Men category & sub category 'Jeans', Verify category page related to that products are displayed");
		ReportManager.logStep("Choosing Men category & sub category 'Jeans', Verify category page related to that products are displayed");

		productsPage.initializeCategory();
		productsPage.selectCategory("Men", "Jeans");
		productsPage.verifyPageLoaded("category_products", "Jeans Products");
		productsPage.verifyTextMessageDisplayed("Men - Jeans Products", false);
		ReportManager.attachScreenshot("Second category products are displayed succesfully", captureScreenshot());

		logger.info("=====================End Case: View Products based on category=========================");
	}
	
	
	// Case:19 - Verify user can successfully navigate between the product brands

	@Test(description = "AE04_TC19_Verify user can succesfully naviagte between the product brands")
	@Epic("AE-4, User should be able to search & view the products and add the selected products to shopping cart")
	@Story("AE-13, View Products")
	public void Test_AE04_TC19_Verify_ProductNavigation_Brands() {

		logger.info("================================================================================");
		logger.info("Start, Case:19 - Verify user can succesfully naviagte between the product brands");
		logger.info("================================================================================");

		HomePage homePage = pageManager.getHomePage();
		ProductsPage productsPage = pageManager.getproductsPage();

		// Step:1 - Verify Home page is displayed and Navigate to Products page
		logger.info("Running, Step:1 - Verify Home page is displayed and Navigate to Products page");
		ReportManager.logStep("Verify Home page is displayed");

		homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
		homePage.clickProductsLink();

		// Step:2 - Verify brands are displayed
		logger.info("Running, Step:2 - Verify brands are displayed");
		ReportManager.logStep("Verify brands are displayed");

		productsPage.verifyBrandSectionDisplay();

		// Step:3 - Choose one brand and Verify page and products related to that brand is displayed
		logger.info(
				"Running, Step:3 - Choose one brand and Verify page and products related to that brand is displayed");
		ReportManager.logStep("Choose one brand and Verify page and products related to that brand is displayed");

		String brandInput = productsPage.selectProductBrand("Polo");
		productsPage.verifyPageLoaded(brandInput, brandInput);
		productsPage.verifyBrandDisplay(brandInput);
		ReportManager.attachScreenshot("First brand products are displayed succesfully", captureScreenshot());

		// Step:4 - Choose second brand and Verify page and products related to that brand is displayed
		logger.info(
				"Running, Step:4 - Choose second brand and Verify page and products related to that brand is displayed");
		ReportManager.logStep("Choose second brand and Verify page and products related to that brand is displayed");

		String brandInput2 = productsPage.selectProductBrand("Biba");
		productsPage.verifyPageLoaded(brandInput2, brandInput2);
		productsPage.verifyBrandDisplay(brandInput2);
		ReportManager.attachScreenshot("Second brand products are displayed succesfully", captureScreenshot());
		
		logger.info("=====================End Case: View products based on brands=========================");
	}
	
	// Case:21 - Verify user can add review for the selected product successfully

		@Test(description = "AE04_TC21_Verify user can add review for the selected product successfully")
		@Epic("AE-4, User should be able to search & view the products and add the selected products to shopping cart")
		@Story("AE-14, Search products and submit review ")
		public void Test_AE04_TC21_Verify_AddProductsReview() {

			logger.info("=================================================================================");
			logger.info("Start, Case:21 - Verify user can add review for the selected product successfully");
			logger.info("=================================================================================");

			HomePage homePage = pageManager.getHomePage();
			ProductsPage productsPage = pageManager.getproductsPage();
			ProductDetailPage productdetailPage = pageManager.getproductdetailPage();

			// Step:1 - Verify Home page is displayed and Navigate to Products page
			logger.info("Running, Step:1 - Verify Home page is displayed and Navigate to Products page");
			ReportManager.logStep("Verify Home page is displayed");

			homePage.verifyPageLoaded("automationexercise", "Automation Exercise");
			homePage.clickProductsLink();
			
			// Step:2 - Verify All products page loaded and click on view product
			logger.info("Running, Step:2 -  Verify All products page loaded and click on view product");
			ReportManager.logStep(" Verify All products page loaded and click on view product");
			productsPage.verifyPageLoaded("products", "All Products");
			productsPage.viewProductWithIndex(0);
			
			// Step:3 - Add review on selected product and verify success message
			logger.info("Running, Step:2 -  Add review on the selected product and verify success message");
			ReportManager.logStep("Add review on the selected product and verify success message");
			productdetailPage.verifyReviewTextVisibility();
			productdetailPage.addReviewName("AETestUser1001");
			productdetailPage.addReviewEmail("AETestUser1001@gamil.com");
			productdetailPage.addReviewComments("Product quality is good");		
			productdetailPage.clickReviewSubmitBtn();
			productdetailPage.verifyTextMessageDisplayed("Thank you for your review", false);
			ReportManager.attachScreenshot("Review added succesfully", captureScreenshot());
		
			logger.info("=====================End Case: Add review on selected products =========================");
		}

}
