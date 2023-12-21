package com.amazon.ataprimeclubservicelambda.activity;

import com.amazon.ata.primeclubservice.BenefitMetadata;
import com.amazon.ata.primeclubservice.DimSumBenefitMetadata;
import com.amazon.ata.primeclubservice.GetPrimeBenefitsRequest;
import com.amazon.ata.primeclubservice.GetPrimeBenefitsResponse;
import com.amazon.ata.primeclubservice.InvalidParameterException;
import com.amazon.ata.primeclubservice.PrimeBenefit;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class GetPrimeBenefitsActivity implements RequestHandler<GetPrimeBenefitsRequest, GetPrimeBenefitsResponse> {

    @VisibleForTesting
    protected static final long SLEEP_MS = 200;

    @VisibleForTesting
    public static final PrimeBenefit FREE_SHIPPING = PrimeBenefit.builder()
        .withBenefitType("FREE_EXPEDITED_SHIPPING")
        .withBenefitMetadata(BenefitMetadata.builder()
            .withBenefitLevel("STANDARD")
            .build())
        .build();

    @VisibleForTesting
    protected static final PrimeBenefit MOM = PrimeBenefit.builder()
        .withBenefitType("MOM_DISCOUNT")
        .build();

    @VisibleForTesting
    protected static final PrimeBenefit DIM_SUM = PrimeBenefit.builder()
        .withBenefitType("DIM_SUM")
        .withBenefitMetadata(DimSumBenefitMetadata.builder()
            .withAmountOfBooksAvailable(10)
            .withCheckoutsPerMonth(2)
            .withBenefitLevel("MINI")
            .build())
        .build();

    @VisibleForTesting
    protected static final PrimeBenefit KIDS = PrimeBenefit.builder()
        .withBenefitType("AMZN4KIDS")
        .build();

    private static final Map<Integer, List<PrimeBenefit>> BENEFITS = ImmutableMap.<Integer, List<PrimeBenefit>>builder()
        .put(0, Collections.emptyList())
        .put(1, Collections.emptyList())
        .put(2, Collections.emptyList())
        .put(3, Collections.singletonList(FREE_SHIPPING))
        .put(4, Collections.singletonList(FREE_SHIPPING))
        .put(5, Collections.singletonList(FREE_SHIPPING))
        .put(6, Arrays.asList(FREE_SHIPPING, MOM))
        .put(7, Arrays.asList(FREE_SHIPPING, DIM_SUM))
        .put(8, Arrays.asList(FREE_SHIPPING, MOM, DIM_SUM, KIDS))
        .put(9, Arrays.asList(MOM, KIDS))
        .build();

    @Inject
    public GetPrimeBenefitsActivity() {

    }

    /**
     * Handles GetPrimeBenefits requests.  Neither CustomerId nor MarketplaceId can be null.  For all successful calls
     * a list of benefits will be returned.  If the customer is not prime an empty list will be returned.
     *
     * @param request the customer in the marketplace to find benefits for.
     * @return a list of benefits.
     * @throws InvalidParameterException if customerId or marketplaceId is null
     */
    @Override
    public GetPrimeBenefitsResponse handleRequest(GetPrimeBenefitsRequest request, Context context) {
        try {
            Thread.sleep(SLEEP_MS);
        } catch (InterruptedException e)  {
            //Do nothing, just go ahead and send the results.
            Thread.currentThread().interrupt();
        }

        if (request.getCustomerId() == null) {
            throw new InvalidParameterException("CustomerId cannot be null.");
        }
        if (request.getMarketplaceId() == null) {
            throw new InvalidParameterException("MarketplaceId cannot be null.");
        }

        int benefitsId = request.getCustomerId().chars().sum() % 10;

        return new GetPrimeBenefitsResponse().builder()
            .withPrimeBenefits(BENEFITS.get(benefitsId))
            .build();
    }

}
