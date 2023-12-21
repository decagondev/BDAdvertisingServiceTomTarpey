package com.amazon.ataprimeclubservicelambda.service;

import com.amazon.ata.primeclubservice.GetPrimeBenefitsRequest;
import com.amazon.ata.primeclubservice.GetPrimeBenefitsResponse;

import com.amazon.ataprimeclubservicelambda.activity.GetPrimeBenefitsActivity;

public class ATAPrimeClubService {

    private final GetPrimeBenefitsActivity getPrimeBenefitsActivity = new GetPrimeBenefitsActivity();

    /**
     * Determines what benefits a given customerID is receiving in a specific marketplaceID.
     * For all valid customerIds, it will always return a BenefitList. For customerIds that are not Prime,
     * this BenefitList will be empty.
     *
     * @param request the customer in the marketplace to find benefits for.
     * @return a list of benefits.
     */
    public GetPrimeBenefitsResponse getPrimeBenefits(GetPrimeBenefitsRequest request) {
        return getPrimeBenefitsActivity.handleRequest(request, null);
    }
}
