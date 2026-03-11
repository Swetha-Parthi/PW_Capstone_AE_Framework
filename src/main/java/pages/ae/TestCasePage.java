package pages.ae;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import framework.base.BasePage;
import framework.reporting.ReportManager;

public class TestCasePage extends BasePage {

	private final Locator testCaseTitles;

	public TestCasePage(Page page) {
		super(page);
		this.testCaseTitles = page.locator(".panel-title a u");

	}

	// Method to get all TC titles

	public List<String> getAllTcTitles() {

		testCaseTitles.first().waitFor();

		return testCaseTitles.allInnerTexts().stream().map(String::trim).toList();

	}

	// Method for validation

	public void validateAllTestCases(List<String> expectedTitles) {

		List<String> actualTitles = getAllTcTitles();

		for (String expected : expectedTitles) {

			if (actualTitles.contains(expected)) {
				logger.info("Test Case matched: " + expected);
				ReportManager.logStep("Test Case matched: " + expected);
			}

			else {
				logger.error("Missing Test Case: " + expected);
				ReportManager.logStep("Missing Test Case: " + expected);
			}
		}
	}

}
