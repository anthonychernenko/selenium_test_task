package com.example.intent_iq_test_task.tests;

import com.example.intent_iq_test_task.pages.WhatIsMyBrowserPage;
import com.example.intent_iq_test_task.pages.browser_config_pages.FirefoxConfigPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirefoxTest extends BaseTest {

    private final String customFirefoxCookies = "Mozilla/5.0 (iPhone; CPU iPhone OS 16_3_1 like Mac OS X) " +
            "AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.3 Mobile/15E148 Safari/604.1";

    @BeforeEach
    public void setUp() {
        setWebDriverFirefoxCustomCookies(customFirefoxCookies);
    }

    @Test
    public void checkCookiesAndUserAgentTest() {
        WhatIsMyBrowserPage page = new WhatIsMyBrowserPage(driver);
        FirefoxConfigPage configPage = new FirefoxConfigPage(driver);
        page.navigateTo();

        assertEquals(customFirefoxCookies, page.getUserAgentValue());

        configPage.navigateTo();
        configPage.clickWarningButton();
    }
}
