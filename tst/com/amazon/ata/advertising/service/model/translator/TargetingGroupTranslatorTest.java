package com.amazon.ata.advertising.service.model.translator;

import com.amazon.ata.advertising.service.model.TargetingPredicateType;
import com.amazon.ata.advertising.service.targeting.TargetingGroup;
import com.amazon.ata.advertising.service.targeting.predicate.ParentPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.RecognizedTargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TargetingGroupTranslatorTest {
    private static final String TARGETING_GROUP_ID = UUID.randomUUID().toString();
    private static final String CONTENT_ID = UUID.randomUUID().toString();
    private static final double CLICK_THROUGH_RATE = 0.2;

    @Test
    public void toCoral_targetingGroupWithPredicates_returnsCoralShape() {
        // GIVEN
        List<TargetingPredicate> targetingPredicates = Arrays.asList(new RecognizedTargetingPredicate(),
                                                                     new ParentPredicate(true));
        TargetingGroup group = new TargetingGroup(TARGETING_GROUP_ID, CONTENT_ID, CLICK_THROUGH_RATE, targetingPredicates);
        // WHEN
        com.amazon.ata.advertising.service.model.TargetingGroup coralShape = TargetingGroupTranslator.toCoral(group);

        // THEN
        assertCoralShape(coralShape);

        com.amazon.ata.advertising.service.model.TargetingPredicate recognizedPredicate = com.amazon.ata.advertising.service.model.TargetingPredicate.builder()
                .withNegate(false)
                .withAttributes(new HashMap<>())
                .withTargetingPredicateType(TargetingPredicateType.RECOGNIZED)
                .build();
        com.amazon.ata.advertising.service.model.TargetingPredicate parentPredicate = com.amazon.ata.advertising.service.model.TargetingPredicate.builder()
                .withNegate(true)
                .withAttributes(new HashMap<>())
                .withTargetingPredicateType(TargetingPredicateType.PARENT)
                .build();
        assertEquals(2, coralShape.getTargetingPredicates().size());
        System.out.println(coralShape.getTargetingPredicates());
        System.out.println(recognizedPredicate);
        assertTrue(coralShape.getTargetingPredicates().contains(recognizedPredicate));
        assertTrue(coralShape.getTargetingPredicates().contains(parentPredicate));
    }

    @Test
    public void toCoral_targetingGroupWithEmptyPredicateList_returnsCoralShape() {
        // GIVEN
        List<TargetingPredicate> targetingPredicates = new ArrayList<>();
        TargetingGroup group = new TargetingGroup(TARGETING_GROUP_ID, CONTENT_ID, CLICK_THROUGH_RATE, targetingPredicates);

        // WHEN
        com.amazon.ata.advertising.service.model.TargetingGroup coralShape = TargetingGroupTranslator.toCoral(group);

        // THEN
        assertCoralShape(coralShape);
        assertEquals(new ArrayList<>(), coralShape.getTargetingPredicates());
    }

    private void assertCoralShape(com.amazon.ata.advertising.service.model.TargetingGroup coralShape) {
        assertEquals(TARGETING_GROUP_ID, coralShape.getTargetingGroupId());
        assertEquals(CONTENT_ID, coralShape.getContentId());
        assertEquals(CLICK_THROUGH_RATE, coralShape.getClickThroughRate());
    }

}
