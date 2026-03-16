package framework.listeners;

import org.slf4j.Logger;
import org.slf4j.MDC;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.microsoft.playwright.Page;

import framework.config.ConfigManager;
import framework.drivers.DriverManager;
import framework.logging.LogManager;
import framework.reporting.ReportManager;

public class TestListener implements ITestListener, ISuiteListener {

	private static final Logger logger = LogManager.getLogger(TestListener.class);

	/**
	 * ============================= Suite Level Listener
	 * ============================
	 */

	/*
	 * This method is invoked before the SuiteRunner begins to run any tests in the
	 * suite
	 */

	@Override
	public void onStart(ISuite suite) {

		ConfigManager.initializeEnvironment(suite);

		logger.info("===================XXXXXXXXXXXXXXXXXXXXXXXX=================");
		logger.info("Starting Suite: {}", suite.getName());
		logger.info("Execution Enviornment: {}", ConfigManager.getEnvironment());
		logger.info("=============================================================");
	}

	/*
	 * This method is invoked after the SuiteRunner completed to run any tests in
	 * the suite
	 */

	@Override
	public void onFinish(ISuite suite) {

		ConfigManager.initializeEnvironment(suite);

		logger.info("===================XXXXXXXXXXXXXXXXXXXXXXXX=================");
		logger.info("Finished Suite: {}", suite.getName());
		logger.info("=============================================================");
	}

	/**
	 * ============================= Test Level Listener
	 * ============================
	 */

	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();

		// set MDC value for the logging pattern
		MDC.put("env", ConfigManager.getEnvironment());
		MDC.put("testname", testname);
		
		logger.info("----------- Starting Test : {} --------------", testname);
	}

	/*
	 * This method is invoked when any Test succeeds in the suite
	 */

	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();

		logger.info("{Test Passed: {}", testname);

		if (ConfigManager.isScreenshotonPass()) {
			attachScreenshot("Passed Screenshot: ", result);
		}

		MDC.clear();
	}

	/*
	 * This method is invoked when any Test Failure in the suite
	 */

	public void onTestFailure(ITestResult result) {

		String testname = result.getMethod().getMethodName();

		logger.info("{Test Failed: {}", testname);

		if (result.getThrowable() != null) {
			logger.error("Failure Reason: " + result.getThrowable());
		}
		if (ConfigManager.isScreenshotonFail()) {
			attachScreenshot("Failure Screenshot: ", result);
		}

		MDC.clear();
	}

	/*
	 * This method is invoked when any Test Skipped in the suite
	 */

	public void onTestSkipped(ITestResult result) {

		String testname = result.getMethod().getMethodName();

		logger.info("{Test Skipped: {}", testname);

		if (result.getThrowable() != null) {
			logger.error("Skip Reason: " + result.getThrowable());
		}

		MDC.clear();
	}

	/**
	 * ============================= Helper Methods ============================
	 */

	private void attachScreenshot(String name, ITestResult result) {

		Page page = DriverManager.getPage();

		if (page != null) {

			// Capture SS with PW

			byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));

			// Attach SS with PW

			ReportManager.attachScreenshot(name, screenshot);

			if (result.getStatus() == ITestResult.FAILURE) {

				ReportManager.logStep("Test Failed: {}" + result.getThrowable().getMessage());
			}
		}

	}
}
