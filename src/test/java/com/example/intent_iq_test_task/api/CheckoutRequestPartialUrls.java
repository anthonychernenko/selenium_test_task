package com.example.intent_iq_test_task.api;

public enum CheckoutRequestPartialUrls {
    SHIPPING_METHODS("estimate-shipping-methods"),
    PAYMENT_INFO("set-payment-information");

    private final String partialUrl;

    CheckoutRequestPartialUrls(String partialUrl) {
        this.partialUrl = partialUrl;
    }

    public String getPartialUrl() {
        return partialUrl;
    }
}
