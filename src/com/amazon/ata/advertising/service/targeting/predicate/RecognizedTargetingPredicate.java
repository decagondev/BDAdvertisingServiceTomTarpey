package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.advertising.service.model.RequestContext;

/**
 * Evaluates to true if a customer is recognized.
 */
public class RecognizedTargetingPredicate extends TargetingPredicate {

    /**
     * Evalutates to true based on the customer being recognized and how the inverse flag is set.
     * @param inverse Can force the predicate to only evaluate to true if the customer isn't recognized.
     */
    public RecognizedTargetingPredicate(boolean inverse) {
        super(inverse);
    }

    /**
     * Evaluates to true if the customer is recognized.
     */
    public RecognizedTargetingPredicate() {}

    @Override
    TargetingPredicateResult evaluateRecognizedCustomer(RequestContext context) {
        return TargetingPredicateResult.TRUE;
    }

    @Override
    TargetingPredicateResult evaluateUnrecognizedCustomer(RequestContext context) {
        return TargetingPredicateResult.FALSE;
    }
}
