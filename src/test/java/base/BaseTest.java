package base;

import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Page;

import framework.drivers.DriverManager;
import framework.logging.LogManager;
import pages.ae.CartPage;
import pages.ae.CheckOutPage;
import pages.ae.ContactUsPage;
import pages.ae.HomePage;
import pages.ae.PaymentsPage;
import pages.ae.ProductDetailPage;
import pages.ae.ProductsPage;
import pages.ae.SignupDetailPage;
import pages.ae.SignupLoginPage;
import pages.ae.TestCasePage;

public class BaseTest {

	protected Page page;
	protected Logger logger;
	private HomePage homePage;
	private SignupLoginPage signuploginPage;
	private SignupDetailPage signupdetailPage;
	private ContactUsPage contactusPage;
	private ProductsPage productsPage;
	private CartPage cartPage;
	private CheckOutPage checkoutPage;
	private PaymentsPage paymentPage;
	private ProductDetailPage productdetailPage;
	private TestCasePage testcasePage;
	
	@BeforeMethod(description = "Browser and URL Launch")

	public void setup() {
		
		logger = LogManager.getLogger(this.getClass());
		
		DriverManager.initDriver();
		
		page = DriverManager.getPage();
		
		logger.info("Initial Setup is completed");		
				
	}
	

	// Home page

	public HomePage homePage() {

		if (homePage == null) {
			homePage = new HomePage(page);
		}
		return homePage;
	}

	// SignupLogin page

	public SignupLoginPage signuploginPage() {

		if (signuploginPage == null) {
			signuploginPage = new SignupLoginPage(page);
		}
		return signuploginPage;
	}

	// SignupDetail page

	public SignupDetailPage signupdetailPage() {

		if (signupdetailPage == null) {
			signupdetailPage = new SignupDetailPage(page);
		}
		return signupdetailPage;
	}

	// Contact Us page

	public ContactUsPage contactusPage() {

		if (contactusPage == null) {
			contactusPage = new ContactUsPage(page);
		}
		return contactusPage;
	}

	// Products page

	public ProductsPage productsPage() {

		if (productsPage == null) {
			productsPage = new ProductsPage(page);
		}
		return productsPage;
	}

	// Carts page

	public CartPage cartPage() {

		if (cartPage == null) {
			cartPage = new CartPage(page);
		}
		return cartPage;
	}

	// CheckOut Page

	public CheckOutPage checkoutPage() {

		if (checkoutPage == null) {
			checkoutPage = new CheckOutPage(page);
		}
		return checkoutPage;
	}

	// Payments Page

	public PaymentsPage paymentPage() {

		if (paymentPage == null) {
			paymentPage = new PaymentsPage(page);
		}
		return paymentPage;
	}

	// product detail Page

	public ProductDetailPage productdetailPage() {

		if (productdetailPage == null) {
			productdetailPage = new ProductDetailPage(page);
		}
		return productdetailPage;
	}

	// TestCase Page

	public TestCasePage testcasePage() {

		if (testcasePage == null) {
			testcasePage = new TestCasePage(page);
		}
		return testcasePage;
	}

	@AfterMethod(description = "Browser and Playwright close")
	public void teardown() {
		
		homePage = null;
		signuploginPage = null;
		signupdetailPage = null;
		contactusPage = null;
		productsPage = null;
		cartPage = null;
		checkoutPage = null;
		paymentPage = null;
		productdetailPage = null;
		testcasePage = null;
		
		DriverManager.quitDriver();
		
		logger.info("Driver Shutdown successful !!");
	}
	
	protected byte[] captureScreenshot() {
		return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
	}
}
