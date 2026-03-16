package pages.ae;

import java.util.List;
import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

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
	private final Locator prodRecommend;
	private final Locator captureProdName;
	private final Locator categoryHeader;
	private final Locator categoryList;

	public HomePage(Page page) {
		super(page);
		this.page = page;
		this.SignupLoginLink = page.getByText(Pattern.compile("Signup", Pattern.CASE_INSENSITIVE));
		this.DeleteAccountLink = page.getByRole(AriaRole.LINK,
				new Page.GetByRoleOptions().setName(Pattern.compile("Delete Account")));
		this.LogoutLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile("Logout")));
		this.ContactusLink = page.getByRole(AriaRole.LINK,
				new Page.GetByRoleOptions().setName(Pattern.compile("Contact us", Pattern.CASE_INSENSITIVE)));
		this.TestCaseLink = page
				.getByRole(AriaRole.LINK,
						new Page.GetByRoleOptions().setName(Pattern.compile("Test Cases", Pattern.CASE_INSENSITIVE)))
				.first();
		this.ProductsLink = page.getByRole(AriaRole.LINK,
				new Page.GetByRoleOptions().setName(Pattern.compile("Products", Pattern.CASE_INSENSITIVE)));
		this.CartLink = page.getByRole(AriaRole.LINK,
				new Page.GetByRoleOptions().setName(Pattern.compile("Cart", Pattern.CASE_INSENSITIVE)));
		this.iconRecommendItems = page.getByText(Pattern.compile("recommended items", Pattern.CASE_INSENSITIVE));
		this.prodRecommend = page.locator("#recommended-item-carousel .item.active");
		this.captureProdName = page.locator("#recommended-item-carousel .item.active .productinfo p");	
		this.categoryHeader = page.locator(".left-sidebar h2").first();
		this.categoryList = page.locator(".panel-group.category-products .panel-title a");
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
		
		captureProdName.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		
		String nameProduct = captureProdName.first().textContent().trim();
		
		prodRecommend.filter(new Locator.FilterOptions().setHasText(nameProduct))
		.locator("a.add-to-cart").first().click(new Locator.ClickOptions().setForce(true));
		
		return nameProduct;
		
	}

	// To verify Brand section is displayed

	public void verifyBrandCategoryDisplay() {

		assertThat(categoryHeader).isVisible();

		int categoryCount = categoryList.count();
		logger.info("Category count: " + categoryCount);

		assertTrue(categoryCount > 0, "No Category displayed");

		List<String> category = categoryList.allInnerTexts();

		for (String list : category) {
			logger.info("Brand: " + list);
		}
	}

}
