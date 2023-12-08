package com.example.intent_iq_test_task.pages.browser_config_pages;

import com.example.intent_iq_test_task.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FirefoxConfigPage extends BasePage {

    private final By warningButtonLocator = By.cssSelector("#warningButton");

    public FirefoxConfigPage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo() {
        driver.get("about:config");
    }

    public void clickWarningButton() {
        findElement(warningButtonLocator).click();
    }
}
