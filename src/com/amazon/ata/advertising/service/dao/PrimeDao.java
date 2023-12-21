package com.amazon.ata.advertising.service.dao;

import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.primeclubservice.GetPrimeBenefitsRequest;
import com.amazon.ata.primeclubservice.PrimeBenefit;

import com.amazon.ataprimeclubservicelambda.service.ATAPrimeClubService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Obtain prime benefits for a customer.
 */
public class PrimeDao implements ReadableDao<RequestContext, List<String>> {

    private final ATAPrimeClubService primeClubService;

    /**
     * Access Prime data.
     * @param primeClubService Client for the PrimeClubService
     */
    public PrimeDao(ATAPrimeClubService primeClubService) {
        this.primeClubService = primeClubService;
    }

    /**
     * Get a list of PrimeBenefit types for a customer in a particular marketplace.
     * @param requestContext The marketplaceId and customerId to get benefits for.
     * @return A list of benefit types for a customer.  If the list is empty, the customer is not prime.  If a null
     *     object is returned an error has occurred.
     */
    public List<String> get(RequestContext requestContext) {
        final GetPrimeBenefitsRequest request = GetPrimeBenefitsRequest.builder()
                .withMarketplaceId(requestContext.getMarketplaceId())
                .withCustomerId(requestContext.getCustomerId())
                .build();

        return primeClubService.getPrimeBenefits(request)
                .getPrimeBenefits()
                .stream()
                .map(PrimeBenefit::getBenefitType)
                .collect(Collectors.toList());
    }
}
