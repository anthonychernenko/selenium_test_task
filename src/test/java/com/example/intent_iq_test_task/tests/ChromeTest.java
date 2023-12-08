package com.example.intent_iq_test_task.tests;

import com.example.intent_iq_test_task.pages.browser_config_pages.ChromeConfigPage;
import org.junit.jupiter.api.*;

public class ChromeTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        setWebDriverChromeRejectedCookies();
    }

    @Test
    public void openSettingsPageTest() {
        new ChromeConfigPage(driver).navigateTo();
    }
}
