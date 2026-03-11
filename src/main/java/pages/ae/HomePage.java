package pages.ae;

import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import framework.base.BasePage;

public class HomePage extends BasePage {

	// private Page page;
	private final Locator SignupLoginLink;
	private final Locator DeleteAccountLink;
	private final Locator LogoutLink;
	private final Locator ContactusLink;
	private final Locator TestCaseLink;
	private final Locator ProductsLink;
	private final Locator CartLink;
	private final Locator iconRecommendItems;
	private final Locator addToCartButton;
	private final Locator captureProdName;
	private final Locator subscriptionText;
	private final Locator upArrow;

	public HomePage(Page page) {
		super(page);
		this.page = page;
		this.SignupLoginLink = page.getByText(Pattern.compile("Signup", Pattern.CASE_INSENSITIVE));
		this.DeleteAccountLink = page.getByRole(AriaRole.LINK,
				new Page.GetByRoleOptions().setName(Pattern.compile("Delete Account")));
		this.LogoutLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile("Logout")));
		this.ContactusLink = page.getByRole(AriaRole.LINK,
				new Page.GetByRoleOptions().setName(Pattern.compile("Contact us", Pattern.CASE_INSENSITIVE)));
		this.TestCaseLink = page.getByRole(AriaRole.LINK,
				new Page.GetByRoleOptions().setName(Pattern.compile("Test Cases", Pattern.CASE_INSENSITIVE))).first();
		this.ProductsLink = page.getByRole(AriaRole.LINK,
				new Page.GetByRoleOptions().setName(Pattern.compile("Products", Pattern.CASE_INSENSITIVE)));
		this.CartLink = page.getByRole(AriaRole.LINK,
				new Page.GetByRoleOptions().setName(Pattern.compile("Cart", Pattern.CASE_INSENSITIVE)));
		this.iconRecommendItems = page.getByText(Pattern.compile("recommended items", Pattern.CASE_INSENSITIVE));
		this.addToCartButton = page.locator("#recommended-item-carousel .item.active .add-to-cart");
		this.captureProdName = page.locator("#recommended-item-carousel .item.active .productinfo p");
		this.subscriptionText = page.getByText(Pattern.compile("Subscription", Pattern.CASE_INSENSITIVE));
		this.upArrow = page.locator("#scrollUp");
	}

	// To check whether correct user is logged in

	public void verifyLoggedIn(String userName) {
		Locator Loggedin = page.getByText(Pattern.compile("Logged in as.*" + userName));
		assertThat(Loggedin).isVisible();
	}

	// To click on Sign up/ Login link

	public void clickSignupLoginLink() {
		SignupLoginLink.click();
	}

	// To click on Delete Account link

	public void clickDeleteAccountLink() {
		DeleteAccountLink.click();
	}

	// To click on Logout link

	public void clickLogoutLink() {
		LogoutLink.click();
	}

	// To click on Contact us link

	public void clickContactusLink() {
		ContactusLink.click();
	}

	// To click on TestCase link

	public void clickTestCaseLink() {
		TestCaseLink.click();
	}

	// To click on Products link

	public void clickProductsLink() {
		ProductsLink.click();
	}

	// To click on Cart link

	public void clickCartLink() {
		CartLink.click();
	}

	// To scroll and see recommended items are visible and add products to cart

	public String addRecomendItems() {

		iconRecommendItems.scrollIntoViewIfNeeded();
		assertThat(iconRecommendItems).isVisible();
		String nameProduct = captureProdName.textContent().trim();
		captureProdName.locator(addToCartButton).first().click();
		return nameProduct;
	}

	// To scroll down and verify subscription text

	public void scrolldown() {

		subscriptionText.scrollIntoViewIfNeeded();
		assertThat(subscriptionText).isVisible();

	}

	// To scroll up using arrow mark

	public void clickArrow() {
		upArrow.click();
	}

	// To scroll up using scroll commands

	public void scrollup() {
		page.evaluate("window.scrollTo(0,0)");
	}
}
