package com.example.intent_iq_test_task.pages.browser_config_pages;

import com.example.intent_iq_test_task.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class ChromeConfigPage extends BasePage {

    public ChromeConfigPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo() {
        driver.get("chrome://settings/");
    }
}
