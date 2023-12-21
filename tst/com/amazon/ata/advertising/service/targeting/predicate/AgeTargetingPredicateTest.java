package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.advertising.service.dao.ReadableDao;
import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.customerservice.AgeRange;
import com.amazon.ata.customerservice.CustomerProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AgeTargetingPredicateTest {
    private static final String CUSTOMER_ID = "1";
    private static final String MARKETPLACE_ID = "2";
    private static final RequestContext REQUEST_CONTEXT = new RequestContext(CUSTOMER_ID, MARKETPLACE_ID);

    @Mock
    private ReadableDao<String, CustomerProfile> customerProfileDao;

    private AgeTargetingPredicate predicate;

    @BeforeEach
    public void setup() {
        initMocks(this);
        predicate = new AgeTargetingPredicate(AgeRange.AGE_18_TO_21);
        predicate.setCustomerProfileDao(customerProfileDao);
    }

    @Test
    public void matchesAgeRange() {
        when(customerProfileDao.get(CUSTOMER_ID)).thenReturn(CustomerProfile.builder()
                .withAgeRange(AgeRange.AGE_18_TO_21)
                .build());

        TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.TRUE, result);
    }

    @Test
    public void matchesAgeRange_inverse() {
        when(customerProfileDao.get(CUSTOMER_ID)).thenReturn(CustomerProfile.builder()
                .withAgeRange(AgeRange.AGE_18_TO_21)
                .build());
        predicate.setInverse(true);

        TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.FALSE, result);
    }

    @Test
    public void doesNotMatchAgeRange() {
        when(customerProfileDao.get(CUSTOMER_ID)).thenReturn(CustomerProfile.builder()
                .withAgeRange(AgeRange.UNDER_18)
                .build());

        TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.FALSE, result);
    }

    @Test
    public void doesNotMatchAgeRange_inverse() {
        when(customerProfileDao.get(CUSTOMER_ID)).thenReturn(CustomerProfile.builder()
                .withAgeRange(AgeRange.UNDER_18)
                .build());
        predicate.setInverse(true);

        TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.TRUE, result);
    }

    @Test
    public void unrecognized() {
        TargetingPredicateResult result = predicate.evaluate(new RequestContext(null, MARKETPLACE_ID));

        assertEquals(TargetingPredicateResult.INDETERMINATE, result);
    }

}
