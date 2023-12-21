package com.amazon.atacustomerservicelambda.service;

import com.amazon.ata.customerservice.GetCustomerProfileRequest;
import com.amazon.ata.customerservice.GetCustomerProfileResponse;
import com.amazon.ata.customerservice.GetCustomerSpendCategoriesRequest;
import com.amazon.ata.customerservice.GetCustomerSpendCategoriesResponse;

import com.amazon.atacustomerservicelambda.activity.GetCustomerProfileActivity;
import com.amazon.atacustomerservicelambda.activity.GetCustomerSpendCategoriesActivity;

public class ATACustomerService {

    private final GetCustomerProfileActivity getCustomerProfileActivity =
            new GetCustomerProfileActivity();
    private final GetCustomerSpendCategoriesActivity getCustomerSpendCategoriesActivity =
            new GetCustomerSpendCategoriesActivity();

    /**
     * Gets a profile for the customer. Customer profiles contain a customer's demographic information.
     *
     * @param request The customer to get a profile for.
     * @return GetCustomerProfileResponse - Contains the profile for the customer.
     */
    public GetCustomerProfileResponse getCustomerProfile(GetCustomerProfileRequest request) {
        return getCustomerProfileActivity.handleRequest(request, null);
    }

    /**
     * Gets the categories a customer has spent money in and how much money they have spent in the trailing 6 months.
     * This data is refreshed daily.
     *
     * @param request The customer to get spend categories for.
     * @return GetCustomerSpendCategoriesResponse - Contains spend category information for the customer.
     */
    public GetCustomerSpendCategoriesResponse getCustomerSpendCategories(GetCustomerSpendCategoriesRequest request) {
        return getCustomerSpendCategoriesActivity.handleRequest(request, null);
    }
}
