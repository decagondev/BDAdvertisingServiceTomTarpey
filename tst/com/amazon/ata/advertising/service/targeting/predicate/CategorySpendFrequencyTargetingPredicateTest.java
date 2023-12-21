package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.advertising.service.dao.ReadableDao;
import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.advertising.service.targeting.Comparison;
import com.amazon.ata.customerservice.Category;
import com.amazon.ata.customerservice.Spend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CategorySpendFrequencyTargetingPredicateTest {
    private static final String CUSTOMER_ID = "1";
    private static final String MARKETPLACE_ID = "2";
    private static final RequestContext REQUEST_CONTEXT = new RequestContext(CUSTOMER_ID, MARKETPLACE_ID);

    private static final String CATEGORY = Category.COMPUTERS;
    private static final int PURCHASES = 2;
    private static final Spend SPEND = Spend.builder().withNumberOfPurchases(PURCHASES).withUsdSpent(1000).build();

    @Mock
    private ReadableDao<RequestContext, Map<String, Spend>> spendDao;

    private CategorySpendFrequencyTargetingPredicate predicate;

    @BeforeEach
    public void setup() {
        initMocks(this);
        when(spendDao.get(REQUEST_CONTEXT)).thenReturn(Collections.singletonMap(CATEGORY, SPEND));
    }


    @Test
    public void predicatePass() {
        predicate = new CategorySpendFrequencyTargetingPredicate(CATEGORY, Comparison.LT, PURCHASES + 5);
        predicate.setSpendDao(spendDao);

        final TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.TRUE, result);
    }

    @Test
    public void predicateFail() {
        predicate = new CategorySpendFrequencyTargetingPredicate(CATEGORY, Comparison.GT, PURCHASES + 5);
        predicate.setSpendDao(spendDao);

        final TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.FALSE, result);
    }

    @Test
    public void predicatePass_inverse() {
        predicate = new CategorySpendFrequencyTargetingPredicate(CATEGORY, Comparison.GT, PURCHASES + 5, true);
        predicate.setSpendDao(spendDao);

        final TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.TRUE, result);

    }

    @Test
    public void predicateFail_inverse() {
        predicate = new CategorySpendFrequencyTargetingPredicate(CATEGORY, Comparison.LT, PURCHASES + 5, true);
        predicate.setSpendDao(spendDao);

        final TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.FALSE, result);

    }

    @Test
    public void categoryNotPresentInMap() {
        when(spendDao.get(REQUEST_CONTEXT)).thenReturn(Collections.singletonMap(Category.AMAZON_MUSIC, SPEND));
        predicate = new CategorySpendFrequencyTargetingPredicate(CATEGORY, Comparison.LT, PURCHASES + 5);
        predicate.setSpendDao(spendDao);

        final TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.TRUE, result);

    }

    @Test
    public void unrecognized() {
        predicate = new CategorySpendFrequencyTargetingPredicate(CATEGORY, Comparison.LT, PURCHASES + 5);
        predicate.setSpendDao(spendDao);
        final RequestContext unrecognizedContext = new RequestContext(null, MARKETPLACE_ID);

        final TargetingPredicateResult result = predicate.evaluate(unrecognizedContext);

        assertEquals(TargetingPredicateResult.INDETERMINATE, result);

    }

    @Test
    public void unrecognized_inverse() {
        predicate = new CategorySpendFrequencyTargetingPredicate(CATEGORY, Comparison.LT, PURCHASES + 5, true);
        predicate.setSpendDao(spendDao);
        final RequestContext unrecognizedContext = new RequestContext(null, MARKETPLACE_ID);

        final TargetingPredicateResult result = predicate.evaluate(unrecognizedContext);

        assertEquals(TargetingPredicateResult.INDETERMINATE, result);
    }

}
