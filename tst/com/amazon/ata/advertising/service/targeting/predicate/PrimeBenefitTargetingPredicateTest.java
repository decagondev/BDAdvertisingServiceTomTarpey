package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.advertising.service.dao.PrimeDao;
import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.primeclubservice.Benefit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PrimeBenefitTargetingPredicateTest {

    private static final String MARKETPLACE_ID = "1";
    private static final String CUSTOMER_ID = "A123456";

    private static RequestContext RECOGNIZED_CUSTOMER_CONEXT = new RequestContext(CUSTOMER_ID, MARKETPLACE_ID);
    private static RequestContext UNRECOGNIZED_CUSTOMER_CONEXT = new RequestContext(null, MARKETPLACE_ID);
    private static String BENEFIT_TYPE = Benefit.FREE_EXPEDITED_SHIPPING;

    @Mock
    private PrimeDao primeDao;

    private PrimeBenefitTargetingPredicate predicate;

    @BeforeEach
    public void setup() {
        initMocks(this);
        predicate = new PrimeBenefitTargetingPredicate(BENEFIT_TYPE);
        predicate.setDao(primeDao);
    }

    @Test
    public void evaluate_customerNotRecognized_inverseFalse() {
        assertEquals(TargetingPredicateResult.INDETERMINATE, predicate.evaluate(UNRECOGNIZED_CUSTOMER_CONEXT));
    }

    @Test
    public void evaluate_customerNotRecognized_inverseTrue() {
        assertEquals(TargetingPredicateResult.INDETERMINATE, predicate.evaluate(UNRECOGNIZED_CUSTOMER_CONEXT));
    }

    @Test
    public void evaluate_customerHasBenefit_inverseFalse() {
        when(primeDao.get(any(RequestContext.class))).thenReturn(Collections.singletonList(BENEFIT_TYPE));

        assertEquals(TargetingPredicateResult.TRUE, predicate.evaluate(RECOGNIZED_CUSTOMER_CONEXT));
    }

    @Test
    public void evaluate_customerHasMultipleBenefits_inverseFalse() {
        when(primeDao.get(any(RequestContext.class))).thenReturn(Arrays.asList(Benefit.DIM_SUM, BENEFIT_TYPE));

        assertEquals(TargetingPredicateResult.TRUE, predicate.evaluate(RECOGNIZED_CUSTOMER_CONEXT));
    }

    @Test
    public void evaluate_customerHasBenefit_inverseTrue() {
        predicate.setInverse(true);

        when(primeDao.get(any(RequestContext.class))).thenReturn(Collections.singletonList(BENEFIT_TYPE));

        assertEquals(TargetingPredicateResult.FALSE, predicate.evaluate(RECOGNIZED_CUSTOMER_CONEXT));
    }

    @Test
    public void evaluate_customerDoesNotHaveBenefit_inverseFalse() {
        when(primeDao.get(any(RequestContext.class))).thenReturn(Collections.emptyList());

        assertEquals(TargetingPredicateResult.FALSE, predicate.evaluate(RECOGNIZED_CUSTOMER_CONEXT));
    }

    @Test
    public void evaluate_customerDoesNotHaveBenefit_inverseTrue() {
        predicate.setInverse(true);

        when(primeDao.get(any(RequestContext.class))).thenReturn(Collections.emptyList());

        assertEquals(TargetingPredicateResult.TRUE, predicate.evaluate(RECOGNIZED_CUSTOMER_CONEXT));
    }
}
