package framework.base;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

import org.slf4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import framework.logging.LogManager;

public abstract class BasePage {

	protected Page page;
	protected Logger logger;
	protected Locator ContinueButton;
	private final Locator subscriptionText;
	private final Locator clickSubscriptionMailArrow;
	private final Locator upArrow;
	private final Locator EmailSubscription;
	private final Locator ContinueShopButton;
	private final Locator ViewCartLink;

	// Parameterized constructor

	public BasePage(Page page) {
		this.page = page;
		this.logger = LogManager.getLogger(this.getClass());
		this.ContinueButton = page.getByText(Pattern.compile("Continue", Pattern.CASE_INSENSITIVE));
		this.subscriptionText = page.getByText(Pattern.compile("Subscription", Pattern.CASE_INSENSITIVE));
		this.clickSubscriptionMailArrow = page.locator(".fa-arrow-circle-o-right");
		this.upArrow = page.locator("#scrollUp");
		this.EmailSubscription = page.locator("#susbscribe_email");
		this.ContinueShopButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue Shopping"));
		this.ViewCartLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("View Cart"));
	}

	// Check the title and URL

	public void verifyPageLoaded(String expURL, String expTitle) {
		assertThat(page).hasURL(Pattern.compile(expURL, Pattern.CASE_INSENSITIVE));
		assertThat(page).hasTitle(Pattern.compile(expTitle, Pattern.CASE_INSENSITIVE));
	}

	// CheckBox

	public void setCheckBox(Locator checkbox, boolean shouldBeChecked, String checkboxName) {
		boolean isChecked = checkbox.isChecked();
		if (isChecked != shouldBeChecked) {
			checkbox.click();
		}
		// final verification to ensure the check box is in expected state
		if (shouldBeChecked) {
			assertThat(checkbox).isChecked();
		} else {
			assertThat(checkbox).not().isChecked();
		}
		logger.info("Checkbox: " + checkboxName + " is set to: " + shouldBeChecked);
	}

	// verify all text messages

	public void verifyTextMessageDisplayed(String expMsg, Boolean exactMatch) {
		if (exactMatch) {
			assertThat(page.getByText(expMsg)).isVisible();
		} else {
			assertThat(page.locator("body")).hasText(Pattern.compile(expMsg, Pattern.CASE_INSENSITIVE));
		}
	}

	// To click continue button

	public void clickContinueButton() {
		ContinueButton.click();
	}

	// To scroll down to footer

	public void scrolldown() {

		subscriptionText.scrollIntoViewIfNeeded();
	}

	// Verify subscription text

	public void verifySubscriptionText() {

		assertThat(subscriptionText).isVisible();
	}

	// To enter subscription email

	public void enterSubscriptionEmail(String email) {
		EmailSubscription.fill(email);
		clickSubscriptionMailArrow.click();
	}

	// To scroll up using arrow mark

	public void clickArrow() {
		upArrow.click();
	}

	// To scroll up using scroll commands

	public void scrollup() {
		page.evaluate("window.scrollTo(0,0)");
	}
	

	// To click 'Continue Shopping' button

	public void clickContinueShopBtn() {
		ContinueShopButton.click();
	}

	// To click 'View Cart' link

	public void clickViewCartLink() {
		ViewCartLink.click();
	}

}
