package com.amazon.ata.advertising.service.activity;

import com.amazon.ata.advertising.service.model.requests.AddTargetingGroupRequest;
import com.amazon.ata.advertising.service.model.responses.AddTargetingGroupResponse;

import com.amazon.ata.advertising.service.dao.TargetingGroupDao;
import com.amazon.ata.advertising.service.model.translator.TargetingGroupTranslator;
import com.amazon.ata.advertising.service.model.translator.TargetingPredicateTranslator;
import com.amazon.ata.advertising.service.targeting.TargetingGroup;
import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 * Adds a new targeting group to an existing piece of advertising content based on the contentId specified. If a list of
 * targeting predicates is provided the initial targeting group will be created with those rules. Otherwise, the
 * targeting group will be created without any predicates, meaning it is viewable by any customer. Targeting groups are
 * given a click  through rate of 1 to start, so that they are guaranteed some initial impressions and a true
 * clickThroughRate can be learned.
 */
public class AddTargetingGroupActivity {
    public static final boolean IMPLEMENTED_STREAMS = true;
    private static final Logger LOG = LogManager.getLogger(AddTargetingGroupActivity.class);

    private final TargetingGroupDao targetingGroupDao;

    /**
     * Instantiate a AddTargetingGroupActivity.
     * @param targetingGroupDao source of targeting group data
     */
    @Inject
    public AddTargetingGroupActivity(TargetingGroupDao targetingGroupDao) {
        this.targetingGroupDao = targetingGroupDao;
    }

    /**
     * Adds a targeting group to an existing piece of content.
     * @param request The service request
     * @return The service response
     */
    public AddTargetingGroupResponse addTargetingGroup(AddTargetingGroupRequest request) {
        String contentId = request.getContentId();
        List<com.amazon.ata.advertising.service.model.TargetingPredicate> requestedTargetingPredicates =
            request.getTargetingPredicates();
        LOG.info(String.format("Adding targeting predicates [%s] to content with id: %s.",
            requestedTargetingPredicates,
            contentId));

        List<TargetingPredicate> targetingPredicates = Optional.ofNullable(requestedTargetingPredicates)
            .orElse(new ArrayList<>())
            .stream()
            .map(TargetingPredicateTranslator::fromCoral)
            .collect(Collectors.toList());

        TargetingGroup targetingGroup = targetingGroupDao.create(contentId, targetingPredicates);

        return AddTargetingGroupResponse.builder()
                .withTargetingGroup(TargetingGroupTranslator.toCoral(targetingGroup))
                .build();
    }

}
