package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.advertising.service.dao.ReadableDao;
import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.customerservice.CustomerProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ParentPredicateTest {
    private static final String CUSTOMER_ID = "1";
    private static final String MARKETPLACE_ID = "2";
    private static final RequestContext REQUEST_CONTEXT = new RequestContext(CUSTOMER_ID, MARKETPLACE_ID);

    @Mock
    private ReadableDao<String, CustomerProfile> customerProfileDao;

    private ParentPredicate predicate;

    @BeforeEach
    public void setup() {
        initMocks(this);
        predicate = new ParentPredicate();
        predicate.setCustomerProfileDao(customerProfileDao);
    }

    @Test
    public void matchesParentStatus() {
        when(customerProfileDao.get(CUSTOMER_ID)).thenReturn(CustomerProfile.builder().withParent(true).build());

        TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.TRUE, result);
    }

    @Test
    public void nullParentStatus() {
        when(customerProfileDao.get(CUSTOMER_ID)).thenReturn(CustomerProfile.builder().withParent(null).build());

        TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.INDETERMINATE, result);
    }

    @Test
    public void matchesParentStatus_inverse() {
        predicate = new ParentPredicate(true);
        predicate.setCustomerProfileDao(customerProfileDao);

        when(customerProfileDao.get(CUSTOMER_ID)).thenReturn(CustomerProfile.builder().withParent(true).build());

        TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.FALSE, result);
    }

    @Test
    public void doesNotMatchParentStatus() {
        when(customerProfileDao.get(CUSTOMER_ID)).thenReturn(CustomerProfile.builder().withParent(false).build());

        TargetingPredicateResult result = predicate.evaluate(REQUEST_CONTEXT);

        assertEquals(TargetingPredicateResult.FALSE, result);
    }

    @Test
    public void doesNotMatchParentStatus_inverse() {
        when(customerProfileDao.get(CUSTOMER_ID)).thenReturn(CustomerProfile.builder().withParent(false).build());
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
