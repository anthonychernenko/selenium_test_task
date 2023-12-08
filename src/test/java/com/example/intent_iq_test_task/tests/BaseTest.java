package com.example.intent_iq_test_task.tests;

import com.example.intent_iq_test_task.utilities.WebDriverUtils;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import java.util.Optional;

public abstract class BaseTest {

    protected WebDriver driver;

    public void setWebDriverChromeDefault() {
        this.driver = WebDriverUtils.initializeWebDriver(WebDriverUtils.BrowserType.CHROME_DEFAULT);
    }

    public void setWebDriverChromeRejectedCookies() {
        this.driver = WebDriverUtils.initializeWebDriver(WebDriverUtils.BrowserType.CHROME_REJECTED_THIRD_PARTY_COOKIES);
    }

    public void setWebDriverFirefoxCustomCookies(String customCookies) {
        this.driver = WebDriverUtils.initializeWebDriver(WebDriverUtils.BrowserType.FIREFOX_WITH_CUSTOM_COOKIES, Optional.of(customCookies));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

