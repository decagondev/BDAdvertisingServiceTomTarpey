package com.amazon.ata.advertising.service.targeting;

import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicateResult;
import com.amazon.ata.advertising.service.util.Futures;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Evaluates TargetingPredicates for a given RequestContext.
 */
public class TargetingEvaluator {
    public static final boolean IMPLEMENTED_STREAMS = true;
    public static final boolean IMPLEMENTED_CONCURRENCY = true;
    private final RequestContext requestContext;

    /**
     * Creates an evaluator for targeting predicates.
     * @param requestContext Context that can be used to evaluate the predicates.
     */
    public TargetingEvaluator(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    /**
     * Evaluate a TargetingGroup to determine if all of its TargetingPredicates are TRUE or not for the given
     * RequestContext.
     * @param targetingGroup Targeting group for an advertisement, including TargetingPredicates.
     * @return TRUE if all of the TargetingPredicates evaluate to TRUE against the RequestContext, FALSE otherwise.
     */
    public TargetingPredicateResult evaluate(TargetingGroup targetingGroup) {
        // Evaluate all the predicates concurrently in a new ExecutorService
        ExecutorService evaluation = Executors.newCachedThreadPool();

        List<TargetingPredicate> targetingPredicates = targetingGroup.getTargetingPredicates();
        List<Future<TargetingPredicateResult>> results = targetingPredicates
            .stream()
            .map(predicate -> evaluation.submit(() -> predicate.evaluate(requestContext)))
            .collect(Collectors.toList());

        // If all the predicates evaluate to TRUE, the TargetingGroup is TRUE!
        TargetingPredicateResult result = results
            .stream()
            .map(Futures::getUnchecked)
            .allMatch(TargetingPredicateResult::isTrue) ?
            TargetingPredicateResult.TRUE : TargetingPredicateResult.FALSE;
        evaluation.shutdown();

        return result;
    }
}
