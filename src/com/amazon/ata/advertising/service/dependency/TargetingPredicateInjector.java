package com.amazon.ata.advertising.service.dependency;

import com.amazon.ata.advertising.service.targeting.predicate.AgeTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.CategorySpendFrequencyTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.CategorySpendValueTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.ParentPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.PrimeBenefitTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.RecognizedTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicate;

import dagger.MembersInjector;

import javax.inject.Inject;

public class TargetingPredicateInjector {
    private final MembersInjector<AgeTargetingPredicate> agePredicateInjector;
    private final MembersInjector<CategorySpendFrequencyTargetingPredicate> spendFrequencyPredicateInjector;
    private final MembersInjector<CategorySpendValueTargetingPredicate> spendValuePredicateInjector;
    private final MembersInjector<PrimeBenefitTargetingPredicate> primePredicateInjector;
    private final MembersInjector<ParentPredicate> parentPredicateInjector;
    private final MembersInjector<RecognizedTargetingPredicate> recognizedPredicateInjector;

    /**
     * Constructs a targeting predicate injector.
     * @param agePredicateInjector injects dependencies in AgeTargetingPredicates
     * @param spendFrequencyPredicateInjector injects dependencies in CategorySpendFrequencyTargetingPredicate
     * @param spendValuePredicateInjector injects dependencies in CategorySpendValueTargetingPredicate
     * @param primePredicateInjector injects dependencies in PrimeBenefitTargetingPredicate
     * @param parentPredicateInjector injects dependencies in ParentPredicate
     * @param recognizedPredicateInjector injects dependencies in RecognizedTargetingPredicate
     */
    @Inject
    public TargetingPredicateInjector(MembersInjector<AgeTargetingPredicate> agePredicateInjector,
                                      MembersInjector<CategorySpendFrequencyTargetingPredicate>
                                              spendFrequencyPredicateInjector,
                                      MembersInjector<CategorySpendValueTargetingPredicate> spendValuePredicateInjector,
                                      MembersInjector<PrimeBenefitTargetingPredicate> primePredicateInjector,
                                      MembersInjector<ParentPredicate> parentPredicateInjector,
                                      MembersInjector<RecognizedTargetingPredicate> recognizedPredicateInjector) {
        this.agePredicateInjector = agePredicateInjector;
        this.spendFrequencyPredicateInjector = spendFrequencyPredicateInjector;
        this.spendValuePredicateInjector = spendValuePredicateInjector;
        this.primePredicateInjector = primePredicateInjector;
        this.parentPredicateInjector = parentPredicateInjector;
        this.recognizedPredicateInjector = recognizedPredicateInjector;
    }

    /**
     * Inject's any member variables or method's marked with @Inject. This is how we can serialize the targeting
     * predicates without the Daos and then inject the dependencies later.
     * @param targetingPredicate predicate to be injected
     */
    public void inject(TargetingPredicate targetingPredicate) {
        if (targetingPredicate instanceof AgeTargetingPredicate) {
            agePredicateInjector.injectMembers((AgeTargetingPredicate) targetingPredicate);
        } else if (targetingPredicate instanceof CategorySpendFrequencyTargetingPredicate) {
            spendFrequencyPredicateInjector.injectMembers(
                    (CategorySpendFrequencyTargetingPredicate) targetingPredicate);
        } else if (targetingPredicate instanceof CategorySpendValueTargetingPredicate) {
            spendValuePredicateInjector.injectMembers((CategorySpendValueTargetingPredicate) targetingPredicate);
        } else if (targetingPredicate instanceof PrimeBenefitTargetingPredicate) {
            primePredicateInjector.injectMembers((PrimeBenefitTargetingPredicate) targetingPredicate);
        } else if (targetingPredicate instanceof ParentPredicate) {
            parentPredicateInjector.injectMembers((ParentPredicate) targetingPredicate);
        } else if (targetingPredicate instanceof RecognizedTargetingPredicate) {
            recognizedPredicateInjector.injectMembers((RecognizedTargetingPredicate) targetingPredicate);
        }
    }
}
