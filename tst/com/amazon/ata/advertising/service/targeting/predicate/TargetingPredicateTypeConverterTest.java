package com.amazon.ata.advertising.service.targeting.predicate;

import com.amazon.ata.primeclubservice.Benefit;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TargetingPredicateTypeConverterTest {

    private TargetingPredicateTypeConverter converter;

    private static final TargetingPredicate PREDICATE1 = new PrimeBenefitTargetingPredicate(Benefit.FREE_EXPEDITED_SHIPPING);
    private static final TargetingPredicate PREDICATE2 = new PrimeBenefitTargetingPredicate(Benefit.MOM_DISCOUNT, true);

    private static final List<TargetingPredicate> EMPTY_PREDICATE_LIST = Collections.EMPTY_LIST;
    private static final List<TargetingPredicate> PREDICATE_LIST_1 = Lists.newArrayList(PREDICATE1);
    private static final List<TargetingPredicate> PREDICATE_LIST_2 = Lists.newArrayList(PREDICATE1, PREDICATE2);

    private static final String PREDICATE1_STRING = "{\"@class\":\"com.amazon.ata.advertising.service.targeting.predicate.PrimeBenefitTargetingPredicate\",\"inverse\":false"
            + ",\"benefitToHave\":\"FREE_EXPEDITED_SHIPPING\"}";
    private static final String PREDICATE2_STRING = "{\"@class\":\"com.amazon.ata.advertising.service.targeting.predicate.PrimeBenefitTargetingPredicate\",\"inverse\":true"
            + ",\"benefitToHave\":\"MOM_DISCOUNT\"}";

    private static final String EMPTY_LIST_STRING = "[]";
    private static final String PREDICATE_LIST_1_STRING = "[" + PREDICATE1_STRING + "]";
    private static final String PREDICATE_LIST_2_STRING = "[" + PREDICATE1_STRING + "," + PREDICATE2_STRING + "]";

    @BeforeEach
    public void setup() {
        converter = new TargetingPredicateTypeConverter();
    }

    @Test
    public void convertList_emptyList() {
        String convertedString = converter.convert(EMPTY_PREDICATE_LIST);
        assertEquals(EMPTY_LIST_STRING, convertedString);
    }

    @Test
    public void convertList_sizeOne() {
        String convertedString = converter.convert(PREDICATE_LIST_1);
        assertEquals(PREDICATE_LIST_1_STRING, convertedString);
    }

    @Test
    public void convertList_sizeTwo() {
        String convertedString = converter.convert(PREDICATE_LIST_2);

        assertEquals(PREDICATE_LIST_2_STRING, convertedString);
    }

    @Test
    public void unconvert_emptyList() {
        List<TargetingPredicate> predicates = converter.unconvert(EMPTY_LIST_STRING);

        assertEquals(EMPTY_PREDICATE_LIST, predicates);
    }

    @Test
    public void unconvert_sizeOne() {
        List<TargetingPredicate> predicates = converter.unconvert(PREDICATE_LIST_1_STRING);

        assertEquals(1, predicates.size());
        assertPrimeBenefit(Benefit.FREE_EXPEDITED_SHIPPING, false, predicates.get(0));
    }

    @Test
    public void unconvert_sizeTwo() {
        List<TargetingPredicate> predicates = converter.unconvert(PREDICATE_LIST_2_STRING);

        assertEquals(2, predicates.size());
        assertPrimeBenefit(Benefit.FREE_EXPEDITED_SHIPPING, false, predicates.get(0));
        assertPrimeBenefit(Benefit.MOM_DISCOUNT, true, predicates.get(1));
    }

    private void assertPrimeBenefit(String benefit, boolean isInverse, TargetingPredicate targetingPredicate) {
        assertTrue(targetingPredicate instanceof PrimeBenefitTargetingPredicate);
        assertEquals(isInverse, targetingPredicate.isInverse());
        PrimeBenefitTargetingPredicate primeBenefitTargetingPredicate = (PrimeBenefitTargetingPredicate) targetingPredicate;
        assertEquals(benefit, primeBenefitTargetingPredicate.getBenefitToHave());
    }
}
