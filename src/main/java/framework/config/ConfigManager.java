package framework.config;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.testng.ISuite;

import framework.logging.LogManager;

public class ConfigManager {
	private static Properties property = new Properties();
	protected static final Logger logger = LogManager.getLogger(ConfigManager.class);
	private static String environment;
	
	static {
		try {
			InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties");

			if (input == null) {
				logger.error("Runtime exception", new RuntimeException("config.properties file not found"));
			}

			property.load(input);
			LogAllProperties();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error loading config.properties files: " + e.getMessage());
		}
	}

	public static String getProperty(String key) {
		return property.getProperty(key);
	}

	public static String getBaseURL() {
		return getProperty(getEnvironment() + ".base.url");
	}

	public static boolean isScreenshotonPass() {
		return Boolean.parseBoolean(getProperty("screenshot.on.pass"));
	}

	public static boolean isScreenshotonFail() {
		return Boolean.parseBoolean(getProperty("screenshot.on.fail"));
	}

	public static String getBrowser() {

		// System property - 1st
		
		String browser = System.getProperty("browser");

		if (browser == null || browser.isEmpty()) {
			browser = property.getProperty("browser", "chromium");
		}
		return browser.toLowerCase();
	}

	public static void LogAllProperties() {
		property.forEach((key, value) -> {
			logger.debug(key + " = " + value);
		});
	}

	// Called once for listener

	public static void initializeEnvironment(ISuite suite) {

		// System property - 1st Priority
		String env = System.getProperty("environment");

		// TestNG XML parameter - 2nd Priority
		if ((env == null) || env.isEmpty()) {
			env = suite.getParameter("environment");
		}

		// Config file property - 3rd Priority
		if ((env == null) || env.isEmpty()) {
			env = getProperty("env");
		}

		// Default to 'qa' if no environment is specified
		if ((env == null) || env.isEmpty()) {
			env = "qa";
			logger.warn("No environment specified. So defaulting to qa");
		}

		environment = env;

		// Optional for using unified environment

		System.setProperty("environment", env);
	}

	// Called from Test Listener

	public static String getEnvironment() {

		if ((environment == null) || environment.isEmpty()) {
			logger.warn("Environment not initialized. So defaulting to qa");
			environment = "qa";
		}
		return environment.toLowerCase();
	}
}
