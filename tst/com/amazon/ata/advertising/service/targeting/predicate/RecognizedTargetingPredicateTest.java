package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.advertising.service.model.RequestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class RecognizedTargetingPredicateTest {

    private RecognizedTargetingPredicate recognizedTargetingPredicate;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void recognizedCustomer() {
        recognizedTargetingPredicate = new RecognizedTargetingPredicate();
        RequestContext context = new RequestContext("1", "1");

        TargetingPredicateResult result = recognizedTargetingPredicate.evaluate(context);

        assertEquals(TargetingPredicateResult.TRUE, result);
    }

    @Test
    public void unrecognizedCustomer() {
        recognizedTargetingPredicate = new RecognizedTargetingPredicate();
        RequestContext context = new RequestContext(null, "1");

        TargetingPredicateResult result = recognizedTargetingPredicate.evaluate(context);

        assertEquals(TargetingPredicateResult.FALSE, result);
    }

    @Test
    public void recognizedCustomer_inverse() {
        recognizedTargetingPredicate = new RecognizedTargetingPredicate(true);
        RequestContext context = new RequestContext("1", "1");

        TargetingPredicateResult result = recognizedTargetingPredicate.evaluate(context);

        assertEquals(TargetingPredicateResult.FALSE, result);
    }

    @Test
    public void unrecognizedCustomer_inverse() {
        recognizedTargetingPredicate = new RecognizedTargetingPredicate(true);
        RequestContext context = new RequestContext(null, "1");

        TargetingPredicateResult result = recognizedTargetingPredicate.evaluate(context);

        assertEquals(TargetingPredicateResult.TRUE, result);
    }

}
