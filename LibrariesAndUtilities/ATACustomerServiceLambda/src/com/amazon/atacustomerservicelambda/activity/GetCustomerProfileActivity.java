package com.amazon.atacustomerservicelambda.activity;

import com.amazon.ata.customerservice.AgeRange;
import com.amazon.ata.customerservice.CustomerProfile;
import com.amazon.ata.customerservice.GetCustomerProfileRequest;
import com.amazon.ata.customerservice.GetCustomerProfileResponse;
import com.amazon.ata.customerservice.InvalidParameterException;
import com.amazon.ata.customerservice.State;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import javax.inject.Inject;

public class GetCustomerProfileActivity implements RequestHandler<GetCustomerProfileRequest, GetCustomerProfileResponse> {

    @VisibleForTesting
    protected static final long SLEEP_MS = 200;

    @VisibleForTesting
    protected static final CustomerProfile UNDER_18 = CustomerProfile.builder()
        .withAgeRange(AgeRange.UNDER_18)
        .withHomeState(State.WA)
        .withParent(false)
        .build();

    @VisibleForTesting
    protected static final CustomerProfile PARENT = CustomerProfile.builder()
        .withAgeRange(AgeRange.AGE_36_TO_45)
        .withHomeState(State.WA)
        .withParent(true)
        .build();

    @VisibleForTesting
    protected static final CustomerProfile OVER_60 = CustomerProfile.builder()
        .withAgeRange(AgeRange.OVER_60)
        .withHomeState(State.WA)
        .withParent(true)
        .build();

    @VisibleForTesting
    public static final CustomerProfile UNDER_46 = CustomerProfile.builder()
        .withAgeRange(AgeRange.AGE_26_TO_30)
        .withHomeState(State.GA)
        .withParent(false)
        .build();

    @VisibleForTesting
    private static final CustomerProfile THIRTY_ONE = CustomerProfile.builder()
        .withAgeRange(AgeRange.AGE_31_TO_35)
        .withHomeState(State.AK)
        .withParent(false)
        .build();

    //TODO Remove
    private static final CustomerProfile EMPTY = CustomerProfile.builder().build();

    private static final Map<Integer, CustomerProfile> PROFILES = ImmutableMap.<Integer, CustomerProfile>builder()
        .put(0, UNDER_18)
        .put(1, PARENT)
        .put(2, EMPTY)
        .put(3, OVER_60)
        .put(4, UNDER_46)
        .put(5, THIRTY_ONE)
        .put(6, EMPTY)
        .put(7, EMPTY)
        .put(8, EMPTY)
        .put(9, EMPTY)
        .build();

    @Inject
    public GetCustomerProfileActivity() {

    }

    @Override
    public GetCustomerProfileResponse handleRequest(GetCustomerProfileRequest request, Context context) {
        try {
            Thread.sleep(SLEEP_MS);
        } catch (InterruptedException e)  {
            // Do nothing, just go ahead and send the results.
            Thread.currentThread().interrupt();
        }

        if (request.getCustomerId() == null) {
            throw new InvalidParameterException("CustomerId cannot be null.");
        }

        final int profileId = (request.getCustomerId().chars().sum() + request.getCustomerId().charAt(0)) % 10;

        return new GetCustomerProfileResponse().builder()
            .withCustomerProfile(PROFILES.get(profileId))
            .build();
    }
}
