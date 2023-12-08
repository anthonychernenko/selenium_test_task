package com.example.intent_iq_test_task.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WhatIsMyBrowserPage extends BasePage {

    private final By userAgentLocator = By.cssSelector("#detected_value a");

    public WhatIsMyBrowserPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo() {
        driver.get("https://www.whatismybrowser.com/detect/what-is-my-user-agent/");
    }

    public String getUserAgentValue() {
        return findElement(userAgentLocator).getText();
    }
}
