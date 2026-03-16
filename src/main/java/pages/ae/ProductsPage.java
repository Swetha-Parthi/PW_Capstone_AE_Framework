package pages.ae;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import framework.base.BasePage;
import framework.reporting.ReportManager;

public class ProductsPage extends BasePage {
	private final Locator SearchTextBox;
	private final Locator SearchButton;
	private final Locator products;
	private final Locator searchedProdResult;
	private final Locator ContinueShopButton;
	private final Locator BrandSection;
	private final Locator BrandList;
	private final Locator singleCard;

	// Dynamic locator for category

	private Locator getCategory(String category) {

		return page.getByText(Pattern.compile("^\\s*" + category + "\\s*$", Pattern.CASE_INSENSITIVE));
	}

	// Dynamic locator for sub category

	private Locator getSubCategory(String subcategory) {

		// Go to the Sidebar (#accordian), find a Link (LINK), make sure the name is exactly (^...$) 'Dress'

		return page.locator("#accordian").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions()
				.setName(Pattern.compile("^" + subcategory + "$", Pattern.CASE_INSENSITIVE)));
	}

	private Map<String, Map<String, Runnable>> categoryMap = new HashMap<>();// nested map, category -> sub category ->
																				// action click

	public ProductsPage(Page page) {
		super(page);
		this.SearchTextBox = page.locator("#search_product");
		this.SearchButton = page.locator("#submit_search");
		this.searchedProdResult = page.locator(".productinfo p");
		this.products = page.locator(".product-image-wrapper"); // Product page: Finds all product containers on the
																// page.
		// (products container--> Product 1 container, Product 2 container,.. -->Each
		// container contains: Image, Product name, Price, View Product link
		//this.ContinueShopButton = page.getByText("Continue Shopping");
		this.ContinueShopButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue Shopping"));
		this.BrandSection = page.locator(".brands_products");
		this.BrandList = page.locator(".brands-name ul li a");
		this.singleCard = page.locator(".single-products");
	}

	// To search products

	public String searchProduct(String name) {

		SearchTextBox.clear();
		SearchTextBox.fill(name);
		SearchButton.click();
		return name;
	}

	// To select brands in products page

	public String selectProductBrand(String brandName) {
		page.getByRole(AriaRole.LINK,
				new Page.GetByRoleOptions().setName(Pattern.compile(brandName, Pattern.CASE_INSENSITIVE))).click();
		return brandName;
	}

	// To verify Brand section is displayed

	public void verifyBrandSectionDisplay() {

		assertThat(BrandSection).isVisible();

		int brandCount = BrandList.count();
		logger.info("Brand count: " + brandCount);

		assertTrue(brandCount > 0, "No brands displayed");

		List<String> brands = BrandList.allInnerTexts();

		for (String brand : brands) {
			logger.info("Brand: " + brand);
		}
	}

	// To verify whether products related to particular brand is displayed

	public void verifyBrandDisplay(String brandName) {
		assertThat(page.locator(".title.text-center"))
				.containsText(Pattern.compile(brandName, Pattern.CASE_INSENSITIVE));

	}

	// To choose products based on index --> viewProductWithIndex(0); -- first
	// product

	public String viewProductWithIndex(int index) {

		// Verify products displayed

		Locator prod = products.nth(index); // selects a specific element from the list

		String productName = prod.locator(".productinfo p").textContent().trim();

		// Inside that specific product container, Find a link with text "View Product"
		prod.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("View Product")).click();

		return productName;

	}

	// To choose products based on product name --> viewProductWithName("Blue Top")

	public String viewProductWithName(String prodname) {

		products.filter(new Locator.FilterOptions().setHasText(prodname))
				.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("View Product")).click();
		// locator() → get elements, filter() → narrow them, getByRole() → find element
		// inside, click() → perform action

		return prodname;
	}

	// To choose products by hovering over a product using index

	public String addToCartHoverwithIndex(int index) {

		Locator productCard = products.nth(index);
		String productName = productCard.locator(".productinfo p").textContent().trim();

		productCard.hover();

		// THE FIX: Target the button specifically inside the orange OVERLAY
		// and use .first() to resolve the conflict between the two buttons.
		// We add .waitFor() to ensure the orange overlay has actually appeared.
		Locator overlayBtn = productCard.locator(".product-overlay").getByText("Add to cart").first();

		overlayBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		// Use Force Click just in case the animation isn't 100% finished
		overlayBtn.click(new Locator.ClickOptions().setForce(true));

		return productName;
	}

	// To verify products are listed whenever product category/ brand/ view product
	// is selected

	public void verifyProductsListVisibility() {
		assertThat(products).not().hasCount(0);
	}

	// To verify all searched products are displayed

	public List<String> verifySearchResult(String searchText) {

		int productCount = searchedProdResult.count();
		logger.info("Products displayed after search: " + productCount);

		// Verify products are displayed
		assertTrue(productCount > 0, "No products displayed for search: " + searchText);

		// Log all product names
		List<String> products = searchedProdResult.allInnerTexts();

		for (String product : products) {
			logger.info("Product found: " + product);
		}

		logger.info("Search results displayed successfully for: " + searchText);
		ReportManager.logStep("Search results displayed successfully for: " + searchText);

		return products;

	}

	// To add list of searched products into cart

	public List<String> addSearchListToCartPage() {

		List<String> addedProdNames = new ArrayList<>();

		int productCount = singleCard.count();
		
		for (int i = 0; i < productCount; i++) {
			String name = singleCard.nth(i).locator(searchedProdResult).textContent().trim();
			addedProdNames.add(name);

			singleCard.nth(i).hover();
			singleCard.nth(i).locator(".product-overlay").getByText("Add to cart").first()
					.click(new Locator.ClickOptions().setForce(true));
			
			ContinueShopButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
			
			ContinueShopButton.click();
			
			ContinueShopButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
				
			}
		return addedProdNames;
	}

	// To Initialize category

	public void initializeCategory() {

		// creates and fills the map with category and sub category actions

		// map for women sub categories
		Map<String, Runnable> women = new HashMap<>();
		women.put("dress", () -> getSubCategory("Dress").click());
		women.put("tops", () -> getSubCategory("Tops").click());
		women.put("saree", () -> getSubCategory("Saree").click());

		// map for men sub categories
		Map<String, Runnable> men = new HashMap<>();
		men.put("tshirt", () -> getSubCategory("Tshirts").click());
		men.put("jeans", () -> getSubCategory("Jeans").click());

		// map for kids sub categories
		Map<String, Runnable> kids = new HashMap<>();
		kids.put("dress", () -> getSubCategory("Dress").click());
		kids.put("tops & shirts", () -> getSubCategory("Tops & Shirts").click());

		// Adding to main map
		categoryMap.put("women", women);
		categoryMap.put("men", men);
		categoryMap.put("kids", kids);
	}

	// To select category

	public void selectCategory(String category, String subcategory) {

		category = category.toLowerCase();
		subcategory = subcategory.toLowerCase();

		switch (category) {

		case "women":
		case "men":
		case "kids":

			getCategory(category).click();
			break;

		default:
			logger.error("Invalid Category is selected: " + category
					+ ". Please choose correct category - Women/ Men/ Kids");
			ReportManager.logStep("Invalid Category is selected: " + category
					+ ". Please choose correct category - Women/ Men/ Kids");
			return;
		}

		Map<String, Runnable> subMap = categoryMap.get(category);

		if (subMap != null && subMap.containsKey(subcategory))

		// subMap will have categoryMap.get("women") AND subMap.containsKey("dress")
		// THEN subMap.get("dress").run() --> DressCategory.click()
		// without subMap..containsKey() -> if it was mobile, we'll get null pointer
		// exception
		{
			// This 'waitFor' ensures the accordion has finished opening
			getSubCategory(subcategory).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
			subMap.get(subcategory).run();
		}

		else {
			logger.error("Invalid subcategory is selected: " + subcategory);
			ReportManager.logStep("Invalid subcategory is selected: " + subcategory);

		}
	}
}
