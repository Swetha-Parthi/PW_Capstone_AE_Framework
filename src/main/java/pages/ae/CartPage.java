package pages.ae;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import framework.base.BasePage;

public class CartPage extends BasePage {

	private final Locator cartProdName;
	private final Locator checkoutBtn;
	private final Locator registerLoginBtn;
	private final Locator removeBtn;
	

	public CartPage(Page page) {

		super(page);
		this.cartProdName = page.locator(".cart_description h4 a");
		this.checkoutBtn = page.getByText("Proceed To Checkout");
		this.registerLoginBtn = page.getByText("Register / Login");
		this.removeBtn = page.locator(".cart_quantity_delete");
		
	}

	// To verify products are added into Cart from Products page either HOVER/ INDEX/ NAME
	// To verify prices, quantity and total price

	public void verifyProductsInCart(String prodInput) {

		// Creates the locator: 
		//tr → select a table row,
		//:has(...) → that row must contain the following element, 
		//.cart_description → the product description cell, 
		//:has-text(prodInput) → containing the product name
		Locator row = page.locator("tr:has(.cart_description:has-text('" + prodInput + "'))");
		assertThat(row).isVisible();
		assertThat(row.locator(".cart_price p")).isVisible();
		assertThat(row.locator(".cart_quantity button")).isVisible();
		assertThat(row.locator(".cart_total p")).isVisible();

	}
	
	// To verify products list from recommended items
	
	public void verifyRecommendProdList(String prodName) {	
		
		assertThat(cartProdName).containsText(prodName);
	}

	// To remove products in cart
	
	public void removeProductsInCart(String prodInput) {
	
		Locator row = page.locator("tr:has(.cart_description:has-text('" + prodInput + "'))");
		assertThat(row).isVisible();
		row.locator(removeBtn).click();
		assertThat(row).isEmpty();
		
	}
	
	// Proceed to checkout
	
	public void proceedCheckout() {
		checkoutBtn.click();
	}

	// To click Register/ Login link
	
	public void clickRegisterLoginLink() {
		registerLoginBtn.click();
	}

	
}
