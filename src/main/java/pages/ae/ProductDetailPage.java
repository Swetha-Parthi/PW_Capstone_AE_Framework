package pages.ae;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import framework.base.BasePage;

public class ProductDetailPage extends BasePage {

	private final Locator reviewName;
	private final Locator reviewEmail;
	private final Locator reviewAdd;
	private final Locator reviewSubmitBtn;
	private final Locator category;
	private final Locator price;
	private final Locator availability;
	private final Locator condition;
	private final Locator brand;
	private final Locator cartquantity;
	private final Locator quantityTextBox;
	private final Locator addToCartButton;

	public ProductDetailPage(Page page) {
		super(page);
		this.category = page.locator(".product-information p:has-text('Category')"); // CSS selector .className tagName
		this.price = page.locator(".product-information span:has-text('Rs')");
		this.availability = page.getByText(Pattern.compile("Availability"));
		this.condition = page.getByText(Pattern.compile("Condition"));
		this.brand = page.getByText(Pattern.compile("Brand"));
		this.reviewName = page.getByPlaceholder("Your Name");
		this.reviewEmail = page.getByPlaceholder("Email Address");
		this.reviewAdd = page.getByPlaceholder(Pattern.compile("Add Review Here!"));
		this.reviewSubmitBtn = page.locator("#reviewSubmitBtn");
		this.quantityTextBox = page.getByRole(AriaRole.TEXTBOX,
				new Page.GetByRoleOptions().setName(Pattern.compile("quantity", Pattern.CASE_INSENSITIVE)));
		this.cartquantity = page.locator(".cart_quantity button");
		this.addToCartButton = page.getByText(Pattern.compile("Add to cart", Pattern.CASE_INSENSITIVE));
	}

	// To verify product details are visible: product name, category, price, availability, condition, brand

	public void verifyProductDetails(String prodNameIndex) {

		assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(prodNameIndex))).isVisible();
		assertThat(category).isVisible();
		assertThat(price).isVisible();
		assertThat(availability).isVisible();
		assertThat(condition).isVisible();
		assertThat(brand).isVisible();

	}

	// To add quantity values

	public int addQuantity(int quantityValue) {
		quantityTextBox.clear();
		quantityTextBox.fill(String.valueOf(quantityValue));
		return quantityValue;
	}

	// To check quantity is correctly visible

	public void verifyuantity(int quantityValue) {
		assertThat(cartquantity).hasText(String.valueOf(quantityValue));
	}

	// To click 'Add to Cart' button

	public void clickAddToCartButton() {
		addToCartButton.click();
	}

	// To add review name

	public void addReviewName(String name) {
		reviewName.fill(name);
	}

	// To add review email

	public void addReviewEmail(String email) {
		reviewEmail.fill(email);
	}

	// To add review comments

	public void addReviewComments(String comment) {
		reviewAdd.fill(comment);
	}

	// To submit review

	public void clickReviewSubmitBtn() {
		reviewSubmitBtn.click();
	}

}
