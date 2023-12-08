package com.example.intent_iq_test_task.pages.magento_site_pages;

import com.example.intent_iq_test_task.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HeaderPage extends BasePage {

    private final By gearSectionLocator = By.cssSelector(".nav-4 .ui-menu-icon");
    private final By bagsSubSectionLocator = By.cssSelector("a[href*='bags']");
    private final By cartLocator = By.cssSelector(".showcart");
    private final By cartQuantityInputLocator = By.cssSelector(".item-qty");
    private final By proceedToCheckoutButtonLocator = By.cssSelector(".checkout");
    private final By cartCounterLocator = By.cssSelector(".counter-number");

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToBags() {
        WebElement gearsSection = waitForElementToBeVisible(gearSectionLocator);
        WebElement bagsSubSection = findElement(bagsSubSectionLocator);
        new Actions(driver).moveToElement(gearsSection)
                .moveToElement(bagsSubSection)
                .click()
                .perform();
    }

    public void waitForCartElementsQuantityAndProceedToCheckout(String expectedCounter) {
        waitForElementHaveText(cartCounterLocator, expectedCounter);
        waitForElementToBeClickable(cartLocator).click();
        waitForElementToHaveAttrValue(cartQuantityInputLocator, "data-item-qty", expectedCounter);
        waitForElementToBeClickable(proceedToCheckoutButtonLocator).click();
    }
}
