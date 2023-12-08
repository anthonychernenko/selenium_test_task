package com.example.intent_iq_test_task.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.util.Optional;

public class WebDriverUtils {

    public static WebDriver initializeWebDriver(BrowserType browserType) {
        return initializeWebDriver(browserType, Optional.empty());
    }

    /*
    The relatively complex WebDriver initialization system was chosen because of the significant difference between
    the two groups of tests - the UI test and the browser settings tests.

    Manually configuring WebDriver instead of using the WebDriverManager library was chosen with the idea that tests 
    will be performed on the environment without automatically updating browser versions.
    */
    public static WebDriver initializeWebDriver(BrowserType browserType, Optional<String> customCookies) {
        switch (browserType) {
            case FIREFOX_WITH_CUSTOM_COOKIES:
                return customCookies.map(WebDriverUtils::initializeFirefoxWithCustomCookies)
                        .orElseThrow(() -> new IllegalArgumentException("Custom cookies are required for Firefox."));
            case CHROME_DEFAULT:
                return initializeChromeDefault();
            case CHROME_REJECTED_THIRD_PARTY_COOKIES:
                return initializeChromeRejectedThirdPartyCookies();
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    private static WebDriver initializeFirefoxWithCustomCookies(String customCookies) {
        System.setProperty("webdriver.gecko.driver", getDriverDirPath() + getDriverFilename(BrowserNames.FIREFOX));

        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("network.cookie.cookieBehavior", 2);
        options.addPreference("general.useragent.override", customCookies);

        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initializeChromeDefault() {
        System.setProperty("webdriver.chrome.driver", getDriverDirPath() + getDriverFilename(BrowserNames.CHROME));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        return new ChromeDriver(options);
    }

    /*
    This solution contains a visual bug for the chrome://settings page, with the "Block third-party cookies in 
    Incognito mode" setting selected. Nevertheless, you can check the solution's functionality by opening 
    the twitter.com website, for example - once enabled, an eye icon will appear in the address bar as a sign.

    Link: 
        https://mspoweruser.com/test-third-party-cookie-phaseout/
        https://twitter.com/Leopeva64/status/1687657537552666624

    Another possible solution is to use a pre-configured Google Chrome profile.
    */
    private static WebDriver initializeChromeRejectedThirdPartyCookies() {
        System.setProperty("webdriver.chrome.driver", getDriverDirPath() + getDriverFilename(BrowserNames.CHROME));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-third-party-cookie-phaseout");

        return new ChromeDriver(options);
    }

    private static String getDriverDirPath() {
        String driversDirPath = "src/test/java/com/example/intent_iq_test_task/drivers/";
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return driversDirPath + "windows/";
        } else if (os.contains("nix")) {
            return driversDirPath + "linux/";
        } else if (os.contains("mac")) {
            return driversDirPath + "mac/";
        } else {
            throw new RuntimeException("Unsupported operating system: " + os);
        }
    }

    private static String getDriverFilename(BrowserNames browserName) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return browserName.getBrowserName() + ".exe";
        } else if (os.contains("nix") || os.contains("mac")) {
            return browserName.getBrowserName();
        } else {
            throw new RuntimeException("Unsupported operating system: " + os);
        }
    }

    enum BrowserNames {
        CHROME("chromedriver"),
        FIREFOX("geckodriver");

        private final String browserName;

        BrowserNames(String browserName) {
            this.browserName = browserName;
        }

        public String getBrowserName() {
            return browserName;
        }
    }

    public enum BrowserType {
        FIREFOX_WITH_CUSTOM_COOKIES,
        CHROME_DEFAULT,
        CHROME_REJECTED_THIRD_PARTY_COOKIES
    }
}
