package com.amazon.ata.advertising.service.dependency;

import com.amazon.ata.advertising.service.targeting.predicate.AgeTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.CategorySpendFrequencyTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.CategorySpendValueTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.ParentPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.PrimeBenefitTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.RecognizedTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicate;
import dagger.MembersInjector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TargetingPredicateInjectorTest {

    @Mock
    private MembersInjector<AgeTargetingPredicate> agePredicateInjector;

    @Mock
    private MembersInjector<CategorySpendFrequencyTargetingPredicate> spendFrequencyPredicateInjector;

    @Mock
    private MembersInjector<CategorySpendValueTargetingPredicate> spendValuePredicateInjector;

    @Mock
    private MembersInjector<PrimeBenefitTargetingPredicate> primePredicateInjector;

    @Mock
    private MembersInjector<ParentPredicate> parentPredicateInjector;

    @Mock
    private MembersInjector<RecognizedTargetingPredicate> recognizedPredicateInjector;

    private TargetingPredicateInjector targetingPredicateInjector;

    @BeforeEach
    public void setup() {
        initMocks(this);
        targetingPredicateInjector = new TargetingPredicateInjector(agePredicateInjector,
                spendFrequencyPredicateInjector,
                spendValuePredicateInjector,
                primePredicateInjector,
                parentPredicateInjector,
                recognizedPredicateInjector);
    }

    @Test
    public void inject_agePredicate_isInject() {
        // GIVEN
        TargetingPredicate predicate = new AgeTargetingPredicate();

        // WHEN
        targetingPredicateInjector.inject(predicate);

        // THEN
        verify(agePredicateInjector).injectMembers((AgeTargetingPredicate) predicate);
        verifyNoMoreInteractions(spendFrequencyPredicateInjector);
        verifyNoMoreInteractions(spendValuePredicateInjector);
        verifyNoMoreInteractions(primePredicateInjector);
        verifyNoMoreInteractions(parentPredicateInjector);
        verifyNoMoreInteractions(recognizedPredicateInjector);
    }

    @Test
    public void inject_spendFrequencyPredicate_isInject() {
        // GIVEN
        TargetingPredicate predicate = new CategorySpendFrequencyTargetingPredicate();

        // WHEN
        targetingPredicateInjector.inject(predicate);

        // THEN
        verify(spendFrequencyPredicateInjector).injectMembers((CategorySpendFrequencyTargetingPredicate) predicate);
        verifyNoMoreInteractions(agePredicateInjector);
        verifyNoMoreInteractions(spendValuePredicateInjector);
        verifyNoMoreInteractions(primePredicateInjector);
        verifyNoMoreInteractions(parentPredicateInjector);
        verifyNoMoreInteractions(recognizedPredicateInjector);
    }

    @Test
    public void inject_spendValuePredicate_isInject() {
        // GIVEN
        TargetingPredicate predicate = new CategorySpendValueTargetingPredicate();

        // WHEN
        targetingPredicateInjector.inject(predicate);

        // THEN
        verify(spendValuePredicateInjector).injectMembers((CategorySpendValueTargetingPredicate) predicate);
        verifyNoMoreInteractions(spendFrequencyPredicateInjector);
        verifyNoMoreInteractions(agePredicateInjector);
        verifyNoMoreInteractions(primePredicateInjector);
        verifyNoMoreInteractions(parentPredicateInjector);
        verifyNoMoreInteractions(recognizedPredicateInjector);
    }

    @Test
    public void inject_primePredicate_isInject() {
        // GIVEN
        TargetingPredicate predicate = new PrimeBenefitTargetingPredicate();

        // WHEN
        targetingPredicateInjector.inject(predicate);

        // THEN
        verify(primePredicateInjector).injectMembers((PrimeBenefitTargetingPredicate) predicate);
        verifyNoMoreInteractions(spendFrequencyPredicateInjector);
        verifyNoMoreInteractions(spendValuePredicateInjector);
        verifyNoMoreInteractions(agePredicateInjector);
        verifyNoMoreInteractions(parentPredicateInjector);
        verifyNoMoreInteractions(recognizedPredicateInjector);
    }

    @Test
    public void inject_parentPredicate_isInject() {
        // GIVEN
        TargetingPredicate predicate = new ParentPredicate();

        // WHEN
        targetingPredicateInjector.inject(predicate);

        // THEN
        verify(parentPredicateInjector).injectMembers((ParentPredicate) predicate);
        verifyNoMoreInteractions(spendFrequencyPredicateInjector);
        verifyNoMoreInteractions(spendValuePredicateInjector);
        verifyNoMoreInteractions(primePredicateInjector);
        verifyNoMoreInteractions(agePredicateInjector);
        verifyNoMoreInteractions(recognizedPredicateInjector);
    }

    @Test
    public void inject_recognizedPredicate_isInject() {
        // GIVEN
        TargetingPredicate predicate = new RecognizedTargetingPredicate();

        // WHEN
        targetingPredicateInjector.inject(predicate);

        // THEN
        verify(recognizedPredicateInjector).injectMembers((RecognizedTargetingPredicate) predicate);
        verifyNoMoreInteractions(spendFrequencyPredicateInjector);
        verifyNoMoreInteractions(spendValuePredicateInjector);
        verifyNoMoreInteractions(primePredicateInjector);
        verifyNoMoreInteractions(parentPredicateInjector);
        verifyNoMoreInteractions(agePredicateInjector);
    }

}
