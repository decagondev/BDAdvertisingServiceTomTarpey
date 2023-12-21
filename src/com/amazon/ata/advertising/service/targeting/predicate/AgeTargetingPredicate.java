package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.advertising.service.dao.ReadableDao;
import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.customerservice.CustomerProfile;

import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Targets a customers age range.
 */
public class AgeTargetingPredicate extends TargetingPredicate {

    @Inject
    ReadableDao<String, CustomerProfile> customerProfileDao;

    private String targetedAgeRange;

    /**
     * Create a targeting predicate for a set AgeRange that can be inverted.
     * @param targetedAgeRange AgeRange customer should match.
     * @param inverse If true, return TRUE for every AgeRange other than the one specified.
     */
    public AgeTargetingPredicate(String targetedAgeRange, boolean inverse) {
        super(inverse);
        Validate.notNull(targetedAgeRange, "Targeted AgeRange cannot be null.");
        this.targetedAgeRange = targetedAgeRange;
    }

    /**
     * Predicate to target customers of a specific age.
     * @param targetedAgeRange The age customer's should be in to see the ad.
     */
    public AgeTargetingPredicate(String targetedAgeRange) {
        this(targetedAgeRange, false);
    }

    /**
     * Predicate to target customers of a specific age.
     */
    public AgeTargetingPredicate() {}

    @Override
    TargetingPredicateResult evaluateRecognizedCustomer(RequestContext context) {
        Validate.notNull(targetedAgeRange, "Targeted AgeRange cannot be null.");

        final CustomerProfile profile = customerProfileDao.get(context.getCustomerId());
        return targetedAgeRange.toString().equalsIgnoreCase(profile.getAgeRange()) ?
                TargetingPredicateResult.TRUE : TargetingPredicateResult.FALSE;
    }

    @VisibleForTesting
    void setCustomerProfileDao(ReadableDao<String, CustomerProfile> customerProfileDao) {
        this.customerProfileDao = customerProfileDao;
    }

    public String getTargetedAgeRange() {
        return targetedAgeRange;
    }

    public void setTargetedAgeRange(String targetedAgeRange) {
        this.targetedAgeRange = targetedAgeRange;
    }
}
