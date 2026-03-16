package pages.ae;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import framework.base.BasePage;

public class CartPage extends BasePage {

	private final Locator cartProdName;
	private final Locator checkoutBtn;
	private final Locator registerLoginBtn;
	private final Locator removeBtn;
	private final Locator cartquantity;
	
	public CartPage(Page page) {

		super(page);
		this.cartProdName = page.locator(".cart_description h4 a");
		this.checkoutBtn = page.getByText("Proceed To Checkout");
		this.registerLoginBtn = page.locator(".modal-content p a");
		this.removeBtn = page.locator(".cart_quantity_delete");
		this.cartquantity = page.locator(".cart_quantity button");
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

	// To check quantity is correctly visible

	public void verifQuantity(int quantityValue, String nameProd) {
		assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(nameProd))).isVisible();
		assertThat(cartquantity).hasText(String.valueOf(quantityValue));
	}
	
	// To verify products are getting displayed in cart based on search 
	
	public void verifySearchListProdInCart(List<String> inputList) {
		
		for(String products : inputList) {
			Locator row = page.locator("tr").filter(new Locator.FilterOptions().setHasText(products));
			assertThat(row).isVisible();
			logger.info("List of products in cart: " + products);
		}		
	}
	
	// To remove products in cart
	
	public void removeProductsInCart(String prodInput) {
	
		Locator row = page.locator("tr:has(.cart_description:has-text('" + prodInput + "'))");
		assertThat(row).isVisible();
		row.locator(removeBtn).click();
		assertThat(row).isHidden();	
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
