package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.advertising.service.dao.ReadableDao;
import com.amazon.ata.advertising.service.model.RequestContext;

import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.lang3.Validate;

import java.util.List;
import javax.inject.Inject;

/**
 * This predicate can be used to ensure a customer has a specific PrimeBenefit, or ensure the absence of a prime
 * benefit.
 */
public class PrimeBenefitTargetingPredicate extends TargetingPredicate {

    @Inject
    ReadableDao<RequestContext, List<String>> primeDao;

    private String benefitToHave;

    /**
     * Constructor to create PrimeBenefitTargetingPredicate objects. A null prime benefit cannot be passed.
     * @param benefitToHave The benefit a customer must have for this predicate to evaluate to true.
     * @param inverse Will only evaluate to true is a customer doesn't have the specified benefit
     */
    public PrimeBenefitTargetingPredicate(String benefitToHave, boolean inverse) {
        super(inverse);

        Validate.notNull(benefitToHave, "Prime Benefit must be provided to the predicate.");
        this.benefitToHave = benefitToHave;
    }

    /**
     * Constructor to create PrimeBenefitTargetingPredicate objects.
     * @param benefitToHave The benefit a customer must have for this predicate to evaluate to true.
     */
    public PrimeBenefitTargetingPredicate(String benefitToHave) {
        this(benefitToHave, false);
    }

    /**
     * Constructor to create PrimeBenefitTargetingPredicate objects.
     */
    public PrimeBenefitTargetingPredicate() {}

    @Override
    TargetingPredicateResult evaluateRecognizedCustomer(RequestContext context) {
        Validate.notNull(benefitToHave, "Prime Benefit must be populated to evaluate the predicate.");

        return primeDao.get(context)
                .stream()
                .anyMatch(benefit -> benefitToHave.equals(benefit)) ?
                TargetingPredicateResult.TRUE : TargetingPredicateResult.FALSE;
    }

    public String getBenefitToHave() {
        return benefitToHave;
    }

    public void setBenefitToHave(String benefit) {
        this.benefitToHave = benefit;
    }

    @VisibleForTesting
    void setDao(ReadableDao<RequestContext, List<String>> dao) {
        this.primeDao = dao;
    }
}
