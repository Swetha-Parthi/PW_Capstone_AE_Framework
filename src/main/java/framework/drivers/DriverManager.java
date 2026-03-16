package framework.drivers;

import java.nio.file.Paths;

import org.slf4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import framework.config.ConfigManager;
import framework.logging.LogManager;

public class DriverManager {

	private static Playwright pw;
	private static Browser browser;
	private static BrowserContext context;
	private static Page page;
	private static Logger logger = LogManager.getLogger(DriverManager.class);
	private static boolean headlessMode;

	public static void initDriver() {
		headlessMode = Boolean.parseBoolean(ConfigManager.getProperty("headless"));

		String baseURL = ConfigManager.getBaseURL();

		try {
			logger.info("Initiating the Driver.....");

			pw = Playwright.create();

			pw.selectors().setTestIdAttribute(ConfigManager.getProperty("test-id"));

			browser = initBrowser();

			logger.info("Launching Browser. HeadlessMode: {} ", String.valueOf(headlessMode));

			context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));

			logger.info("Browser context launched");

			page = context.newPage();

			logger.info("Browser Page opened.");
			
			context.tracing().start(new Tracing.StartOptions()
					  .setScreenshots(true)
					  .setSnapshots(true)
					  .setSources(true));

			page.route("**/*", route -> {
				String url = route.request().url();

				if (url.contains("googlesyndication") || url.contains("doubleclick") || url.contains("ads")
						|| url.contains("quantserve")) {
					route.abort();
				} else {
					route.resume();
		        }
		    });

			page.navigate(baseURL);

			logger.info("Navigated to Base URL: {}", baseURL);

		} catch (Exception e) {

			logger.error("Exception during driver initiation", e);

			throw new RuntimeException("Driver initialization failed", e);
		}
	}

	private static Browser initBrowser() {

		BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(headlessMode).setSlowMo(1000);

		switch (ConfigManager.getBrowser().toLowerCase()) {

		case "chrome":
			return pw.chromium().launch(options.setChannel("chrome"));

		case "firefox":
			return pw.firefox().launch(options);

		case "edge":
			return pw.chromium().launch(options.setChannel("msedge"));

		case "webkit":
			return pw.webkit().launch(options);

		case "chromium":
		default:
			return pw.webkit().launch(options);
		}
	}

	public static void quitDriver() {
		context.tracing().stop(new Tracing.StopOptions()
				  .setPath(Paths.get("trace.zip")));
		
		if (context != null)
			context.close();
		if (browser != null)
			browser.close();
		if (pw != null)
			pw.close();

		logger.info("Closed Browser !!");
	}

	public static Page getPage() {
		return page;
	}
}
