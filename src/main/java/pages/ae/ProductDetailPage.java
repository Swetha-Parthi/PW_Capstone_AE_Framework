package pages.ae;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import framework.base.BasePage;

public class ProductDetailPage extends BasePage {

	private final Locator reviewText;
	private final Locator reviewName;
	private final Locator reviewEmail;
	private final Locator reviewAdd;
	private final Locator reviewSubmitBtn;
	private final Locator category;
	private final Locator price;
	private final Locator availability;
	private final Locator condition;
	private final Locator brand;
	private final Locator quantityTextBox;
	private final Locator addToCartButton;

	public ProductDetailPage(Page page) {
		super(page);
		this.reviewText = page.getByText("Write Your Review");
		this.category = page.locator(".product-information p:has-text('Category')"); // CSS selector .className tagName
		this.price = page.getByText(Pattern.compile("Rs.", Pattern.CASE_INSENSITIVE));
		this.availability = page.locator(".product-information p:has-text('Availability')");
		this.condition = page.locator(".product-information p:has-text('Condition')");
		this.brand = page.locator(".product-information p:has-text('Brand')");
		this.reviewName = page.getByPlaceholder("Your Name");
		this.reviewEmail = page.locator("#email");
		this.reviewAdd = page.getByPlaceholder(Pattern.compile("Add Review Here!"));
		this.reviewSubmitBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(Pattern.compile("Submit", Pattern.CASE_INSENSITIVE)));
		this.quantityTextBox = page.locator("#quantity");
		this.addToCartButton = page.getByText(Pattern.compile("Add to cart", Pattern.CASE_INSENSITIVE));
	}
	
	// To verify 'Write Your Review' is visible
	
	public void verifyReviewTextVisibility() {
		assertThat(reviewText).isVisible();
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
