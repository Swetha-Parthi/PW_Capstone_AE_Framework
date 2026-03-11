package pages.ae;

import static org.testng.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import framework.base.BasePage;

public class PaymentsPage extends BasePage{

	private final Locator cardName;
	private final Locator cardNum;
	private final Locator cvc;
	private final Locator expMonth;
	private final Locator expYear;
	private final Locator payConfirmOrderBtn;
	private final Locator invoiceBtn;
	

	public PaymentsPage(Page page) {
		super(page);
		this.cardName = page.getByTestId("name-on-card");
		this.cardNum = page.getByTestId("card-number");
		this.cvc = page.getByTestId("cvc");
		this.expMonth = page.getByTestId("expiry-month");
		this.expYear = page.getByTestId("expiry-year");
		this.payConfirmOrderBtn = page.getByTestId("pay-button");		
		this.invoiceBtn = page.getByText("Download Invoice");
	}

	// To fill payment details
	
	public void addPaymentDetails(String name, String number, String cvcValue, String month, String year) {
			
			cardName.fill(name);
			cardNum.fill(number);
			cvc.fill(cvcValue);
			expMonth.fill(month);
			expYear.fill(year);
	
		}
	
	// To click pay and confirm order button
	
	public void clickPayCOnfirmOrderBtn() {
		payConfirmOrderBtn.click();
	}
	
	
	// To download invoice
	
	public void downloadInvoice() {
		
		Download download = page.waitForDownload(() ->{ invoiceBtn.click(); });
		Path path = Paths.get("downloads/" + download.suggestedFilename()); // download file names suggested by browser/ server and convert into Java Path object 
		download.saveAs(path); // PW temporarily store the file, this step helps to save downloaded file to location defined by path
		
		assertTrue(path.toFile().exists(), "Invoice download failed"); //assertTrue(condition, failureMessage); true - test pass, failure - show error message
	}
	
}
