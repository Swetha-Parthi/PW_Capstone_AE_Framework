package pages.ae;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import framework.base.BasePage;

public class SignupLoginPage extends BasePage {
	private final Locator PageHeaderSignup;
	private final Locator PageHeaderLogin;
	private final Locator SignupNameTextbox;
	private final Locator SignupEmailTextbox;
	private final Locator SignupButton;
	private final Locator LoginEmailTextbox;
	private final Locator LoginPwdTextbox;
	private final Locator LoginButton;

	public SignupLoginPage(Page page) {
		super(page);
		this.PageHeaderSignup = page.getByRole(AriaRole.HEADING,
				new Page.GetByRoleOptions().setName(Pattern.compile("Signup")));
		this.PageHeaderLogin = page.getByRole(AriaRole.HEADING,
				new Page.GetByRoleOptions().setName(Pattern.compile("Login", Pattern.CASE_INSENSITIVE)));
		this.SignupNameTextbox = page.getByPlaceholder("Name");
		this.SignupEmailTextbox = page.getByTestId("signup-email");
		this.SignupButton = page.getByTestId("signup-button");
		this.LoginEmailTextbox = page.getByTestId("login-email");
		this.LoginPwdTextbox = page.getByTestId("login-password");
		this.LoginButton = page.getByTestId("login-button");
	}

	// To choose either Signup or Login
	
	public void verifyPageHeader(String pagename, String expHeader) {

		switch (pagename.toLowerCase()) {
		case "signup":
			assertThat(PageHeaderSignup).containsText(expHeader);
			break;
		case "login":
			assertThat(PageHeaderLogin).containsText(expHeader);
			break;
		default:
			logger.error("Invalid Pagename:" + pagename);
		}
	}

	// To enter Signup Name
	
	public void enterSigunupName(String name) {
		SignupNameTextbox.fill(name);
	}

	// To enter Signup Email
	
	public void enterSigunupEmail(String email) {
		SignupEmailTextbox.fill(email);
	}

	// To call Signup Name and Email methods
	
	public void enterSignupDetails(String name, String email) {
		enterSigunupName(name);
		enterSigunupEmail(email);
	}

	// To enter Login Email
	
	public void enterLoginEmail(String email) {
		LoginEmailTextbox.fill(email);
	}

	// // To enter Login Password
	
	public void enterLoginpPassword(String pwd) {
		LoginPwdTextbox.fill(pwd);
	}

	// To call Login Email and Password methods
	
	public void enterLoginDetails(String email, String pwd) {
		enterLoginEmail(email);
		enterLoginpPassword(pwd);
	}

	// To click signup button
	
	public void clickSignup() {
		SignupButton.click();
	}
	
	// To click Login button

	public void clickLogin() {
		LoginButton.click();
	}
}
