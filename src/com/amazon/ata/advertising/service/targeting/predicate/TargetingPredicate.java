package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.advertising.service.model.RequestContext;

import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicateResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base class for all TargetingPredicates. The evaluate method will call either a recognized or unrecognized evaluate
 * method based on whether not the customerId is available in the context. All classes extending TargetingPredicate must
 * implement the recognized evaluation method. Default implementation of the unrecognized evaluate is to return an
 * INDETERMINATE result.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class TargetingPredicate {

    protected boolean inverse;

    /**
     * Creates a TargetingPredicate that can be inverted.
     * @param inverse Whatever the normal result of this predicate should be, evaluate to the inverse.
     */
    public TargetingPredicate(boolean inverse) {
        this.inverse = inverse;
    }

    /**
     * Creates a TargetingPredicate.
     */
    public TargetingPredicate() {}

    /**
     * Evaluate whether the information available in the request context passes the targeting predicate.
     * @param context - information about the incoming request, such as requestor and location
     * @return TRUE/FALSE if the predicate passes, or INDETERMINATE if the evaluation cannot be made
     */
    public TargetingPredicateResult evaluate(RequestContext context) {
        final TargetingPredicateResult nonInvertedResult = evaluateWithoutInverse(context);
        return inverse ? nonInvertedResult.invert() : nonInvertedResult;
    }

    /**
     * Evaluate this targeting predicate ignoring whether or not it is set to inverse.
     * @param context The context of this request.
     * @return The result of evaluating the predicate.
     */
    private TargetingPredicateResult evaluateWithoutInverse(RequestContext context) {
        return context.isRecognizedCustomer() ?
                evaluateRecognizedCustomer(context) : evaluateUnrecognizedCustomer(context);
    }

    /**
     * Evaluates this targeting predicate for an unrecognized customer.
     * @param context The context of this request
     * @return An indeterminate result for unrecognized customers.
     */
    TargetingPredicateResult evaluateUnrecognizedCustomer(RequestContext context) {
        return TargetingPredicateResult.INDETERMINATE;
    }

    /**
     * Evaluates this targeting predicate for a recognized customer.
     * @param context The context of this request
     * @return The result of evaluating the predicate for a recognized customer.
     */
    abstract TargetingPredicateResult evaluateRecognizedCustomer(RequestContext context);

    public void setInverse(boolean inverse) {
        this.inverse = inverse;
    }

    public boolean isInverse() {
        return inverse;
    }
}
