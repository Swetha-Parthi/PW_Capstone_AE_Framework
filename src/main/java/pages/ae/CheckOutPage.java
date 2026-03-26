package pages.ae;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;
import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import framework.base.BasePage;

public class CheckOutPage extends BasePage{

	private final Locator commentadd;
	private final Locator placeOrderBtn;
	
	
	public CheckOutPage(Page page) {
		super(page);
		this.commentadd = page.locator("textarea[name='message']");
		this.placeOrderBtn = page.getByText(Pattern.compile("Place Order", Pattern.CASE_INSENSITIVE));
	}
	
	// To verify address details
	
		public void verifyAddressDetails(String addressType, String gender, String firstname, String lastname,
				String address, String address2, String country, String state, String city, String zip, String phNumber) {

			// addressType : Delivery - id="address_delivery", Billing -id="address_invoice"
			Locator actualAddress = page.locator("#" + addressType);
			
			String prefix = gender.equalsIgnoreCase("male") ? "Mr. " : "Mrs. ";

			List<String> expAddress = List.of(prefix + firstname + " " + lastname, 
					address, 
					address2,
					city + " " + state,
					zip, 
					country, 
					phNumber);

			for (String value : expAddress) {
				assertThat(actualAddress).containsText(value);
			}
		}

		// To add description in comment box

		public void addComment(String comments) {
			commentadd.fill(comments);
		}

		// To click place order button
		
		public void clickPlaceOrderButton() {
			placeOrderBtn.click();
		}

}
