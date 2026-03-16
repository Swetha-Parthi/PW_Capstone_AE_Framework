package framework.managers;

import com.microsoft.playwright.Page;

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

public class PageObjectManager {
	private Page page;

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

	public PageObjectManager(Page page) {
		this.page = page;
	}

	// Home page

	public HomePage getHomePage() {

		if (homePage == null) {
			homePage = new HomePage(page);
		}
		return homePage;
	}

	// SignupLogin page

	public SignupLoginPage getSignupLoginPage() {

		if (signuploginPage == null) {
			signuploginPage = new SignupLoginPage(page);
		}
		return signuploginPage;
	}

	// SignupDetail page

	public SignupDetailPage getSignupDetailPage() {

		if (signupdetailPage == null) {
			signupdetailPage = new SignupDetailPage(page);
		}
		return signupdetailPage;
	}

	// Contact Us page

	public ContactUsPage getcontactusPage() {

		if (contactusPage == null) {
			contactusPage = new ContactUsPage(page);
		}
		return contactusPage;
	}

	// Products page

	public ProductsPage getproductsPage() {

		if (productsPage == null) {
			productsPage = new ProductsPage(page);
		}
		return productsPage;
	}

	// Carts page

	public CartPage getcartPage() {

		if (cartPage == null) {
			cartPage = new CartPage(page);
		}
		return cartPage;
	}

	// CheckOut Page

	public CheckOutPage getcheckoutPage() {

		if (checkoutPage == null) {
			checkoutPage = new CheckOutPage(page);
		}
		return checkoutPage;
	}

	// Payments Page

	public PaymentsPage getpaymentPage() {

		if (paymentPage == null) {
			paymentPage = new PaymentsPage(page);
		}
		return paymentPage;
	}

	// product detail Page

	public ProductDetailPage getproductdetailPage() {

		if (productdetailPage == null) {
			productdetailPage = new ProductDetailPage(page);
		}
		return productdetailPage;
	}

	// TestCase Page

	public TestCasePage gettestcasePage() {

		if (testcasePage == null) {
			testcasePage = new TestCasePage(page);
		}
		return testcasePage;
	}
}
