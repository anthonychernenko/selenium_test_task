package com.example.intent_iq_test_task.utilities;

import com.example.intent_iq_test_task.api.pojo.UserAddressPojo;
import com.example.intent_iq_test_task.api.pojo.UserPaymentPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapperUtils {
    ObjectMapper mapper = new ObjectMapper();

    public UserAddressPojo getUserAddress(String requestDataJson) {
        return mapJsonToObject(requestDataJson, UserAddressPojo.class);
    }

    public UserPaymentPojo getUserPayment(String requestDataJson) {
        return mapJsonToObject(requestDataJson, UserPaymentPojo.class);
    }

    private  <T> T mapJsonToObject(String requestDataJson, Class<T> targetClass) {
        try {
            return mapper.readValue(requestDataJson, targetClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
