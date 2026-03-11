package pages.ae;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import framework.base.BasePage;
import framework.reporting.ReportManager;

public class ProductsPage extends BasePage {
	private final Locator SearchTextBox;
	private final Locator SearchButton;
	private final Locator products;
	private final Locator searchedProdResult;
	private final Locator ContinueShopButton;
	private final Locator ViewCartLink;
	
	// Dynamic locator for category 
	
	private Locator getCategory(String category) {
		
		return page.getByText(Pattern.compile("^\\s*" + category + "\\s*$", Pattern.CASE_INSENSITIVE));
	}
	
	// Dynamic locator for sub category 
	
	private Locator getSubCategory(String subcategory) {
		
		return page.getByText(Pattern.compile(subcategory, Pattern.CASE_INSENSITIVE));
	}
	
	private Map<String, Map<String, Runnable>> categoryMap = new HashMap<>();// nested map, category -> sub category -> action click
	
	public ProductsPage(Page page) {
		super(page);
		this.SearchTextBox = page.locator("#search_product");
		this.SearchButton = page.locator("#submit_search");
		this.searchedProdResult = page.locator(".productinfo.text-center p").first();
		this.products = page.locator(".product-image-wrapper"); // Product page: Finds all product containers on the page.
		// (products container--> Product 1 container, Product 2 container,.. -->Each
		// container contains: Image, Product name, Price, View Product link
		this.ContinueShopButton = page.getByText("Continue Shopping");
		this.ViewCartLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("View Cart"));
	}

	// To search products

	public void searchProduct(String name) {

		SearchTextBox.fill(name);
		SearchButton.click();
	}

	// To select brands in products page

	public void selectProductBrand(String brandName) {
		page.getByRole(AriaRole.LINK,
				new Page.GetByRoleOptions().setName(Pattern.compile(brandName, Pattern.CASE_INSENSITIVE))).click();
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

		Locator hoverproduct = products.nth(index); // selects a specific element from the list

		String productName = hoverproduct.locator(".productinfo p").textContent().trim();

		hoverproduct.hover(); // Hover over the product based on index

		// Inside that specific product container, Find a link With text "Add to cart"
		hoverproduct.getByRole(AriaRole.LINK,
				new Locator.GetByRoleOptions().setName(Pattern.compile("Add to cart", Pattern.CASE_INSENSITIVE)))
				.click();

		return productName;
	}

	// To verify products are listed whenever product category/ brand/ view product
	// is selected

	public void verifyProductsListVisibility() {
		assertThat(products).not().hasCount(0);
	}

	// To click 'Continue Shopping' button

	public void clickContinueShopBtn() {
		ContinueShopButton.click();
	}

	// To click 'View Cart' link

	public void clickViewCartLink() {
		ViewCartLink.click();
	}

	// To verify all searched products are displayed

	public void verifySearchResult(String searchText) {

		int count = searchedProdResult.count(); // This will avoid multiple time DOM query run. count() action will run
												// once and store count value in count variable

		if (count == 0) {
			logger.info("No products found for the input search: " + searchText);
			ReportManager.logStep("No products found for the input search: " + searchText);
			return;
		}
		assertThat(searchedProdResult).containsText(Pattern.compile(searchText, Pattern.CASE_INSENSITIVE));

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
			// subMap will have categoryMap.get("women") AND subMap.containsKey("dress") THEN subMap.get("dress").run() --> DressCategory.click()
			// without subMap..containsKey() -> if it was mobile, we'll get null pointer exception
			{
				subMap.get(subcategory).run();
			} 
			
			else {
				logger.error("Invalid subcategory is selected: " + subcategory);
				ReportManager.logStep("Invalid subcategory is selected: " + subcategory);

			}
		}
	}

