package com.example.intent_iq_test_task.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // the method was created in order to keep the base URL in the proper place, as well as with the idea of possible
    // further reuse (despite the fact that this solution violates the YAGNI principle)
    public void navigateTo(String urlPart) {
        driver.get("https://magento.softwaretestingboard.com" + urlPart);
    }

    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToBeInvisible(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    protected WebElement waitForElementToBeClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
    protected void waitForElementToHaveAttrValue(By locator, String attribute, String attrValue) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = findElement(locator);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(element, attribute, attrValue));
    }
    protected String getElementAttributeIfExists(WebElement element, String attribute) {
        String attributeValue = null;
        try {
            attributeValue = element.getAttribute(attribute);
        } catch (NullPointerException e) {
            LOGGER.info("Attribute " + attribute + " does not exist for element '" + element + "'");
        }
        return attributeValue;
    }

    protected void waitForElementHaveText(By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(locator, text));
    }

    protected void sendKeys(By locator, String text) {
        findElement(locator).sendKeys(text);
    }

    protected List<WebElement> getSelectOptions(By locator) {
        waitForElementToBeVisible(locator);
        Select select = new Select(findElement(locator));
        return select.getOptions();
    }

    protected void selectOptionByText(By selectLocator, String selectOptionText) {
        Select select = new Select(findElement(selectLocator));
        select.selectByVisibleText(selectOptionText);
    }
}
