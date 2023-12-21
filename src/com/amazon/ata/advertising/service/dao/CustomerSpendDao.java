package com.amazon.ata.advertising.service.dao;

import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.customerservice.GetCustomerSpendCategoriesRequest;
import com.amazon.ata.customerservice.GetCustomerSpendCategoriesResponse;
import com.amazon.ata.customerservice.Spend;

import com.amazon.atacustomerservicelambda.service.ATACustomerService;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Get information on a customer's spending habits in different categories.
 */
public class CustomerSpendDao implements ReadableDao<RequestContext, Map<String, Spend>> {

    private final ATACustomerService customerClient;

    /**
     * Access customer spend data.
     * @param customerClient Client for the CustomerService.
     */
    public CustomerSpendDao(ATACustomerService customerClient) {
        this.customerClient = customerClient;
    }

    /**
     * Get the amount a customer has spent in different categories on Amazon.
     *
     * @param requestContext The marketplaceId the customerId has spent in.
     * @return SpendCategories
     */
    @Override
    public Map<String, Spend> get(RequestContext requestContext) {
        final GetCustomerSpendCategoriesRequest request = GetCustomerSpendCategoriesRequest.builder()
                .withCustomerId(requestContext.getCustomerId())
                .withMarketplaceId(requestContext.getMarketplaceId())
                .build();
        final GetCustomerSpendCategoriesResponse result = customerClient.getCustomerSpendCategories(request);
        return result.getCustomerSpendCategories()
                .getSpendCategories()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
