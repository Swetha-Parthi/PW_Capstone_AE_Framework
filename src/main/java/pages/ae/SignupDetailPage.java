package pages.ae;

import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.options.AriaRole;

import framework.base.BasePage;

public class SignupDetailPage extends BasePage {

	//private final Locator PageHeaderAccInfo;
	private final Locator MaleRadioButton;
	private final Locator FeMaleRadioButton;
	private final Locator NameTextbox;
	private final Locator EmailTextbox;
	private final Locator PwdTextbox;
	private final Locator DaysDd;
	private final Locator MonthsDd;
	private final Locator YearsDd;
	private final Locator NewsletterCB;
	private final Locator SpecialOfferCB;
	//private final Locator PageHeaderAddInfo;
	private final Locator FirstNameTB;
	private final Locator LastNameTB;
	private final Locator CompanyTB;
	private final Locator AddressTB;
	private final Locator Address2TB;
	private final Locator CountryDd;
	private final Locator StateTB;
	private final Locator CityTB;
	private final Locator ZipcodeTB;
	private final Locator MobileNumTB;
	private final Locator CreateAccButton;

	public SignupDetailPage(Page page) {
		super(page);
		//this.PageHeaderAccInfo = page.getByText(Pattern.compile("Enter Account Information"));
		this.MaleRadioButton = page.getByLabel(Pattern.compile("^\\s*Mr.\\s*$"));
		this.FeMaleRadioButton = page.getByLabel(Pattern.compile("^\\s*Mrs.\\s*$"));
		this.NameTextbox = page.locator("#name");
		this.EmailTextbox = page.locator("#email");
		this.PwdTextbox = page.getByTestId("password");
		this.DaysDd = page.getByTestId("days");
		this.MonthsDd = page.getByTestId("months");
		this.YearsDd = page.getByTestId("years");
		this.NewsletterCB = page.getByText("Sign up for our newsletter!");
		this.SpecialOfferCB = page.getByText("Receive special offers from our partners!");
		//this.PageHeaderAddInfo = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(Pattern.compile("Address Information")));
		this.FirstNameTB = page.getByTestId("first_name");
		this.LastNameTB = page.locator("#last_name");
		this.CompanyTB = page.getByTestId("company");
		this.AddressTB = page.getByTestId("address");
		this.Address2TB = page.getByTestId("address2");
		this.CountryDd = page.getByTestId("country");
		this.StateTB = page.getByTestId("state");
		this.CityTB = page.getByTestId("city");
		this.ZipcodeTB = page.getByTestId("zipcode");
		this.MobileNumTB = page.getByTestId("mobile_number");
		this.CreateAccButton = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName(Pattern.compile("Create Account")));
	}

	// To select Gender
	
	public void selectGender(String gender) {
		
		if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")) {
			MaleRadioButton.click();
		} else if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f")) {
			FeMaleRadioButton.click();
		} else {
			System.out.println("Incorrect Gender passed");
		}
	}

	// To enter Password
	
	public void enterPassword(String pwd) {
		PwdTextbox.fill(pwd);
	}

	//To Select DOB
	
	public void selectDOB(String day, String month, String year) {
		DaysDd.selectOption(day);
		MonthsDd.selectOption(month);
		YearsDd.selectOption(year);
	}

	// To check NewsLetter checkbox
	
	public void optNewsLetter(boolean opt) {
		setCheckBox(NewsletterCB, opt, "Newsletter");
	}

	// To check Special Offer checkbox
	
	public void optSpecialOffer(boolean opt) {
		setCheckBox(SpecialOfferCB, opt, "Special Offers");
	}

	// To enter names 
	
	public void enterNames(String fname, String lname) {
		FirstNameTB.fill(fname);
		LastNameTB.fill(lname);
	}

	// To enter company name
	
	public void enterCompanyDetails(String company) {
		CompanyTB.fill(company);
	}

	// To enter Address Details
	
	public void enterAddressDetails(String address, String address2, String country, String state, String city,
			String zip) {
		AddressTB.fill(address);
		Address2TB.fill(address2);
		CountryDd.selectOption(country);
		StateTB.fill(state);
		CityTB.fill(city);
		ZipcodeTB.fill(zip);
	}

	// To enter Mobile number 
	
	public void enterMobileNum(String phnum) {
		MobileNumTB.fill(phnum);
	}

	// To click create account button
	
	public void clickCreateAcc() {
		CreateAccButton.click();
	}

	//To verify Name and Email is auto - populated
	
	public void verifyAutoPopNameandEmail(String expName, String expEmail) {
		assertThat(NameTextbox).hasAttribute("value", expName);
		assertThat(EmailTextbox).hasAttribute("value", expEmail);
	}
}
