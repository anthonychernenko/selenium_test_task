package com.example.intent_iq_test_task.pages.magento_site_pages;

import com.example.intent_iq_test_task.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

public class BagsPage extends BasePage {

    private final By productElementsLocator = By.cssSelector(".product-item-info");

    public BagsPage(WebDriver driver) {
        super(driver);
    }

    public void addRandomBagToCart() {
        List<WebElement> bags = findElements(productElementsLocator);
        WebElement bag = bags.get(new Random().nextInt(bags.size()));
        new Actions(driver).moveToElement(bag)
                .moveToElement(bag.findElement(By.cssSelector("button[type='submit']")))
                .click()
                .perform();
    }
}
