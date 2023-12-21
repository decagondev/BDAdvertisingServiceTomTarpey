package com.amazon.ata.advertising.service.dao;

import com.amazon.ata.customerservice.CustomerProfile;
import com.amazon.ata.customerservice.GetCustomerProfileRequest;

import com.amazon.atacustomerservicelambda.service.ATACustomerService;

/**
 * Gets the profile of a customer filled with the customers estimated demographic information.
 */
public class CustomerProfileDao implements ReadableDao<String, CustomerProfile> {

    private final ATACustomerService customerClient;

    /**
     * Access Customer Profile data.
     * @param customerClient Client to connect to the CustomerService
     */
    public CustomerProfileDao(ATACustomerService customerClient) {
        this.customerClient = customerClient;
    }

    /**
     * Get a CustomerProfile for a customer.
     *
     * @param customerId The customerId to get demographic information for.
     * @return CustomerProfile
     */
    @Override
    public CustomerProfile get(String customerId) {
        final GetCustomerProfileRequest request = GetCustomerProfileRequest.builder()
                .withCustomerId(customerId)
                .build();
        return customerClient.getCustomerProfile(request)
                .getCustomerProfile();
    }
}
