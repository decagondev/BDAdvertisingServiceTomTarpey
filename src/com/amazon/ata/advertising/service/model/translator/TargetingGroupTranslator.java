package com.amazon.ata.advertising.service.model.translator;

import com.amazon.ata.advertising.service.model.TargetingGroup;
import com.amazon.ata.advertising.service.model.TargetingPredicate;

import java.util.List;
import java.util.stream.Collectors;

public class TargetingGroupTranslator {
    private TargetingGroupTranslator() {}

    /**
     * Translate from an internal representation of a TargetingGroup to a coral shape.
     * @param group The internal representation
     * @return The coral shape
     */
    public static TargetingGroup toCoral(com.amazon.ata.advertising.service.targeting.TargetingGroup group) {
        List<TargetingPredicate> targetingPredicates = group.getTargetingPredicates().stream()
                .map(TargetingPredicateTranslator::toCoral)
                .collect(Collectors.toList());

        return TargetingGroup.builder()
                .withContentId(group.getContentId())
                .withTargetingGroupId(group.getTargetingGroupId())
                .withClickThroughRate(group.getClickThroughRate())
                .withTargetingPredicates(targetingPredicates)
                .build();
    }
}
