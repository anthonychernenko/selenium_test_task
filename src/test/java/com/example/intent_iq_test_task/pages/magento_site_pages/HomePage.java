package com.example.intent_iq_test_task.pages.magento_site_pages;

import com.example.intent_iq_test_task.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo() {
        super.navigateTo("/");
    }
}

