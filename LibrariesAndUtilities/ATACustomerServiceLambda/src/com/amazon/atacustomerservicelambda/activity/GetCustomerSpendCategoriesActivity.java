package com.amazon.atacustomerservicelambda.activity;

import com.amazon.ata.customerservice.Category;
import com.amazon.ata.customerservice.CustomerSpendCategories;
import com.amazon.ata.customerservice.GetCustomerSpendCategoriesRequest;
import com.amazon.ata.customerservice.GetCustomerSpendCategoriesResponse;
import com.amazon.ata.customerservice.InvalidParameterException;
import com.amazon.ata.customerservice.Spend;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.Map;
import javax.inject.Inject;

public class GetCustomerSpendCategoriesActivity implements RequestHandler<GetCustomerSpendCategoriesRequest, GetCustomerSpendCategoriesResponse> {

    @VisibleForTesting
    protected static final long SLEEP_MS = 200;

    @VisibleForTesting
    public static final CustomerSpendCategories COMPUTERS = CustomerSpendCategories.builder()
        .withSpendCategories(Collections.singletonMap(
            Category.COMPUTERS,
            Spend.builder()
                .withNumberOfPurchases(1)
                .withUsdSpent(1000)
                .build()))
        .build();

    @VisibleForTesting
    public static final CustomerSpendCategories SMALL_SPEND_COMPUTERS = CustomerSpendCategories.builder()
        .withSpendCategories(Collections.singletonMap(
            Category.COMPUTERS,
            Spend.builder()
                .withNumberOfPurchases(5)
                .withUsdSpent(25)
                .build()))
        .build();

    @VisibleForTesting
    public static final CustomerSpendCategories PRIME_VIDEO_SPEND = CustomerSpendCategories.builder()
        .withSpendCategories(ImmutableMap.of(
            Category.AMAZON_MUSIC, Spend.builder().withNumberOfPurchases(48).withUsdSpent(132).build(),
            Category.PRIME_VIDEO, Spend.builder().withNumberOfPurchases(28).withUsdSpent(97).build()
        )).build();

    @VisibleForTesting
    public static final CustomerSpendCategories TECHNICAL_BOOKS_COMPUTERS = CustomerSpendCategories.builder()
        .withSpendCategories(ImmutableMap.of(
            Category.TECHNICAL_BOOKS, Spend.builder().withNumberOfPurchases(3).withUsdSpent(60).build(),
            Category.COMPUTERS, Spend.builder().withNumberOfPurchases(1).withUsdSpent(200).build(),
            Category.ELECTRONICS, Spend.builder().withNumberOfPurchases(13).withUsdSpent(92).build(),
            Category.FRESH, Spend.builder().withNumberOfPurchases(1).withUsdSpent(73).build()
        )).build();

    @VisibleForTesting
    public static final CustomerSpendCategories VIDEO_GAMES = CustomerSpendCategories.builder()
        .withSpendCategories(ImmutableMap.of(
            Category.VIDEO_GAMES, Spend.builder().withNumberOfPurchases(5).withUsdSpent(100).build()
        )).build();

    //TODO Remove
    private static final CustomerSpendCategories EMPTY = CustomerSpendCategories.builder().withSpendCategories(Collections.emptyMap()).build();

    private static final Map<Integer, CustomerSpendCategories> SPEND = ImmutableMap.<Integer, CustomerSpendCategories>builder()
        .put(0, COMPUTERS)
        .put(1, SMALL_SPEND_COMPUTERS)
        .put(2, PRIME_VIDEO_SPEND)
        .put(3, EMPTY)
        .put(4, EMPTY)
        .put(5, TECHNICAL_BOOKS_COMPUTERS)
        .put(6, VIDEO_GAMES)
        .build();

    @Inject
    public GetCustomerSpendCategoriesActivity() {

    }

    @Override
    public GetCustomerSpendCategoriesResponse handleRequest(GetCustomerSpendCategoriesRequest request, Context context) {

        try {
            Thread.sleep(SLEEP_MS);
        } catch (InterruptedException e)  {
            // Do nothing, just go ahead and send the results.
            Thread.currentThread().interrupt();
        }

        if (request.getCustomerId() == null) {
            throw new InvalidParameterException("CustomerId cannot be null.");
        }
        if (request.getMarketplaceId() == null) {
            throw new InvalidParameterException("MarketplaceId cannot be null.");
        }

        final int spendId = (request.getCustomerId().chars().sum() + request.getMarketplaceId().chars().sum()) % 7;

        return GetCustomerSpendCategoriesResponse.builder()
            .withCustomerSpendCategories(SPEND.get(spendId))
            .build();
    }
}
