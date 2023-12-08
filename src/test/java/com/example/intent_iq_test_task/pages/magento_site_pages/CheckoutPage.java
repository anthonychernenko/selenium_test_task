package com.example.intent_iq_test_task.pages.magento_site_pages;

import com.example.intent_iq_test_task.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class CheckoutPage extends BagsPage{

    private final By emailAddressInputLocator = By.cssSelector("._with-tooltip > #customer-email");
    private final By firstNameInputLocator = By.cssSelector("input[name='firstname']");
    private final By lastNameInputLocator = By.cssSelector("input[name='lastname']");
    private final By companyInputLocator = By.cssSelector("input[name='company']");
    private final By streetAddressInputLocator = By.cssSelector("input[name='street[0]']");
    private final By cityInputLocator = By.cssSelector("input[name='city']");
    private final By stateProvinceSelectLocator = By.cssSelector("select[name='region_id']");
    private final By postcodeInputLocator = By.cssSelector("input[name='postcode']");
    private final By phoneNumberInputLocator = By.cssSelector("input[name='telephone']");
    private final By shippingMethodFlatRateCheckboxLocator = By.cssSelector("input[value*='flatrate']");
    private final By nextButtonLocator = By.cssSelector("button.continue");
    private final By placeOrderButtonLocator = By.cssSelector("button.checkout");
    private final By orderIdLocator = By.cssSelector(".checkout-success span");
    private final By loaderLocator = By.cssSelector(".loading-mask");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillShippingData(User user) {
        fillEmail(user.getEmail())
                .fillFirstName(user.getFirstName())
                .fillLastName(user.getLastName())
                .fillCompany(user.getCompany())
                .fillStreetAddress(user.getStreetAddress())
                .fillCity(user.getCity())
                .selectState(user.getState())
                .fillPostCode(user.getPostalCode())
                .fillPhoneNumber(user.getPhoneNumber())
                .selectFlatRateShipping();
    }

    private CheckoutPage fillEmail(String email) {
        sendKeys(emailAddressInputLocator, email);
        return this;
    }

    private CheckoutPage fillFirstName(String firstName) {
        sendKeys(firstNameInputLocator, firstName);
        return this;
    }

    private CheckoutPage fillLastName(String lastName) {
        sendKeys(lastNameInputLocator, lastName);
        return this;
    }

    private CheckoutPage fillCompany(String companyName) {
        sendKeys(companyInputLocator, companyName);
        return this;
    }

    private CheckoutPage fillStreetAddress(String address) {
        sendKeys(streetAddressInputLocator, address);
        return this;
    }

    private CheckoutPage fillCity(String city) {
        sendKeys(cityInputLocator, city);
        return this;
    }

    private CheckoutPage selectState(String state) {
        selectOptionByText(stateProvinceSelectLocator, state);
        return this;
    }

    public String getRandomStateTitle() {
        List<WebElement> states = getSelectOptions(stateProvinceSelectLocator);
        WebElement randomState = states.get(new Random().nextInt(states.size()));
        String randomStateTitle = getElementAttributeIfExists(randomState, "data-title");
        return !(randomStateTitle == null) ? randomStateTitle : getRandomStateTitle();
    }

    private CheckoutPage fillPostCode(String postCode) {
        sendKeys(postcodeInputLocator, postCode);
        return this;
    }

    private CheckoutPage fillPhoneNumber(String phoneNumber) {
        sendKeys(phoneNumberInputLocator, phoneNumber);
        return this;
    }

    private void selectFlatRateShipping() {
        findElement(shippingMethodFlatRateCheckboxLocator).click();
    }

    public void clickNextButton() {
        waitForElementToBeClickable(nextButtonLocator).click();
    }
    public void clickPlaceOrderButton() {
        waitForElementToBeInvisible(loaderLocator);
        waitForElementToBeClickable(placeOrderButtonLocator).click();
    }

    public String getOrderId() {
        waitForElementToBeInvisible(loaderLocator);
        return findElement(orderIdLocator).getText();
    }
}
