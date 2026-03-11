package pages.ae;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import framework.base.BasePage;

public class ContactUsPage extends BasePage {

	private final Locator NameTextBox;
	private final Locator EmailTextBox;
	private final Locator SubjecTextBox;
	private final Locator MessageTextBox;
	private final Locator FileUpload;
	private final Locator SubmitButton;
	private final Locator HomeButton;

	public ContactUsPage(Page page) {
		super(page);
		this.NameTextBox = page.getByTestId("name");
		this.EmailTextBox = page.getByTestId("email");
		this.SubjecTextBox = page.getByTestId("subject");
		this.MessageTextBox = page.getByTestId("message");
		this.FileUpload = page.locator("//input[@name='upload_file']");
		this.SubmitButton = page.getByTestId("submit-button");	
		this.HomeButton = page.locator("#form-section .btn.btn-success span");
	}

	// To enter name
	public void enterName(String name) {
		NameTextBox.fill(name);
	}

	// To enter Email
	public void enterEmail(String email) {
		EmailTextBox.fill(email);
	}

	// To enter Subject
	public void enterSubject(String subject) {
		SubjecTextBox.fill(subject);
	}

	// To enter Message
	public void enterMessage(String msg) {
		MessageTextBox.fill(msg);
	}

	// To upload file
	public void uploadFile(String filePath) {
		FileUpload.setInputFiles(Paths.get(filePath)); // give filepath while invoking this
	}

	// To click submit
	public void clickSubmitBtn() {
		page.onDialog(dialog -> {
			String msg = dialog.message();
			System.out.println("Confirm dialog box: " + msg);
			dialog.accept();
		});
		SubmitButton.click();
	}
	
	// To click Home button
	public void clickHomeButton() {
		HomeButton.click();
	}

}
