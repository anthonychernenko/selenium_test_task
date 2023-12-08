package com.example.intent_iq_test_task.tests;

import com.example.intent_iq_test_task.api.CheckoutRequestPartialUrls;
import com.example.intent_iq_test_task.utilities.DevToolsUtils;
import com.example.intent_iq_test_task.utilities.JsonMapperUtils;
import com.example.intent_iq_test_task.api.pojo.UserAddressPojo;
import com.example.intent_iq_test_task.api.pojo.UserPaymentPojo;
import com.example.intent_iq_test_task.data.User;
import com.example.intent_iq_test_task.utilities.FileWriterUtils;
import com.example.intent_iq_test_task.pages.magento_site_pages.BagsPage;
import com.example.intent_iq_test_task.pages.magento_site_pages.CheckoutPage;
import com.example.intent_iq_test_task.pages.magento_site_pages.HeaderPage;
import com.example.intent_iq_test_task.pages.magento_site_pages.HomePage;

import org.junit.jupiter.api.*;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyProductE2ETest extends BaseTest {

    User user = new User();

    @BeforeEach
    public void setUp() {
        setWebDriverChromeDefault();
    }

    @Test
    public void buyProductTest() {
        HeaderPage headerPage = new HeaderPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        DevToolsUtils devToolsUtils = new DevToolsUtils(driver);

        new HomePage(driver).navigateTo();
        headerPage.navigateToBags();

        // Calling this method can cause an error due to the fact that the quantity of the selected product is
        // not enough to purchase - this is not a test error, but an application bug
        new BagsPage(driver).addRandomBagToCart();
        headerPage.waitForCartElementsQuantityAndProceedToCheckout("1");

        String userState = checkoutPage.getRandomStateTitle();
        user.setState(userState);
        checkoutPage.fillShippingData(user);

        devToolsUtils.startDevToolsSession();

        CompletableFuture<String> requestAddressDataListener =
                devToolsUtils.addRequestWillBeSentListener(CheckoutRequestPartialUrls.SHIPPING_METHODS);
        CompletableFuture<String> requestPaymentDataListener =
                devToolsUtils.addRequestWillBeSentListener(CheckoutRequestPartialUrls.PAYMENT_INFO);

        checkoutPage.clickNextButton();

        String requestAddressDataJson = devToolsUtils.getRequestBodyJsonFromListener(requestAddressDataListener);
        UserAddressPojo userAddress = new JsonMapperUtils().getUserAddress(requestAddressDataJson);

        String requestPaymentDataJson = devToolsUtils.getRequestBodyJsonFromListener(requestPaymentDataListener);
        UserPaymentPojo userPayment = new JsonMapperUtils().getUserPayment(requestPaymentDataJson);

        devToolsUtils.stopDevToolsSession();

        checkUserData(userAddress, userPayment);

        checkoutPage.clickPlaceOrderButton();
        FileWriterUtils.writeToFile(checkoutPage.getOrderId(), "test_data.txt");
    }

    private void checkUserData(UserAddressPojo userAddress, UserPaymentPojo userPayment) {
        assertEquals(user.getEmail(), userPayment.getEmail());
        assertEquals(user.getCompany(), userAddress.getAddress().getCompany());
        assertEquals(user.getStreetAddress(), userAddress.getAddress().getStreet().get(0));
        assertEquals(user.getCity(), userAddress.getAddress().getCity());
        assertEquals(user.getState(), userAddress.getAddress().getRegion());
        assertEquals(user.getPostalCode(), userAddress.getAddress().getPostcode());
        assertEquals(user.getPhoneNumber(), userAddress.getAddress().getTelephone());
        assertEquals(user.getFirstName(), userAddress.getAddress().getFirstname());
        assertEquals(user.getLastName(), userAddress.getAddress().getLastname());
    }
}

