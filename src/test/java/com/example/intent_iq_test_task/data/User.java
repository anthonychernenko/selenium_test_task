package com.example.intent_iq_test_task.data;

import com.github.javafaker.Faker;

public class User {
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String company;
    private final String streetAddress;
    private final String city;
    private final String postalCode;
    private final String phoneNumber;
    private String state;

    public User() {
        Faker faker = new Faker();
        this.email = faker.internet().emailAddress();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.company = faker.company().name();
        this.streetAddress = faker.address().streetAddress();
        this.city = faker.address().city();
        this.postalCode = faker.address().zipCode();
        this.phoneNumber = faker.phoneNumber().cellPhone();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCompany() {
        return company;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

