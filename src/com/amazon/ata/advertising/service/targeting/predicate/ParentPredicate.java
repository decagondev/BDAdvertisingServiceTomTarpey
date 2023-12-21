package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.advertising.service.dao.ReadableDao;
import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.customerservice.CustomerProfile;

import com.google.common.annotations.VisibleForTesting;

import javax.inject.Inject;

/**
 * Evaluates to true if a customer is a parent.
 */
public class ParentPredicate extends TargetingPredicate {

    @Inject
    ReadableDao<String, CustomerProfile> customerProfileDao;

    /**
     * Evaluates to true if a customer is a parent.
     * @param inverse Can force the predicate to evaluate to true only if a customer isn't a parent.
     */
    public ParentPredicate(boolean inverse) {
        super(inverse);
    }

    /**
     * Evaluates to true if a customer is a parent.
     */
    public ParentPredicate() {}

    @Override
    TargetingPredicateResult evaluateRecognizedCustomer(RequestContext context) {
        final CustomerProfile profile = customerProfileDao.get(context.getCustomerId());

        return profile.isParent() == null ? TargetingPredicateResult.INDETERMINATE : profile.isParent() ?
                TargetingPredicateResult.TRUE : TargetingPredicateResult.FALSE;
    }

    @VisibleForTesting
    void setCustomerProfileDao(ReadableDao<String, CustomerProfile> customerProfileDao) {
        this.customerProfileDao = customerProfileDao;
    }
}
