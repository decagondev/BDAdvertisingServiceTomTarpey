package com.amazon.ata.advertising.service.activity;

import com.amazon.ata.advertising.service.model.requests.CreateContentRequest;
import com.amazon.ata.advertising.service.model.responses.CreateContentResponse;
import com.amazon.ata.advertising.service.dao.ContentDao;
import com.amazon.ata.advertising.service.dao.TargetingGroupDao;

import com.amazon.ata.advertising.service.model.AdvertisementContent;
import com.amazon.ata.advertising.service.model.translator.AdvertisementContentTranslator;
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

public class CreateContentActivity {
    private static final Logger LOG = LogManager.getLogger(CreateContentActivity.class);

    private final ContentDao contentDao;
    private final TargetingGroupDao targetingGroupDao;

    /**
     * The activity for the CreateContent API.
     * @param contentDao stores the new advertisement content
     * @param targetingGroupDao stores the new targeting group
     */
    @Inject
    public CreateContentActivity(ContentDao contentDao, TargetingGroupDao targetingGroupDao) {
        this.contentDao = contentDao;
        this.targetingGroupDao = targetingGroupDao;
    }

    /**
     * Creates a new piece of advertising content and a targeting group to go with it. The html/css content of the
     * advertisement and a marketplace to schedule the content in is required. If a list of targeting predicates is
     * provided the initial targeting group will be created with those rules. Otherwise, the targeting group will be
     * created without any predicates, meaning it is viewable by any customer. Targeting groups are given a click
     * through rate of 1 to start, so that they are guaranteed some initial impressions and a true clickThroughRate can
     * be learned.
     * @param request A piece of advertising content to create.
     * @return The newly created piece of advertising content.
     */
    public CreateContentResponse createContent(CreateContentRequest request) {
        String marketplaceId = request.getMarketplaceId();
        String requestedContent = request.getContent();
        List<com.amazon.ata.advertising.service.model.TargetingPredicate> requestedTargetingPredicates =
            request.getTargetingPredicates();
        LOG.info(String.format("Creating content in marketplace: %s. Content: %s. Targeting predicates: %s",
            marketplaceId, requestedContent, requestedTargetingPredicates));

        AdvertisementContent content = contentDao.create(marketplaceId, requestedContent);

        List<TargetingPredicate> targetingPredicates = Optional.ofNullable(requestedTargetingPredicates)
                .orElse(new ArrayList<>())
                .stream()
                .map(TargetingPredicateTranslator::fromCoral)
                .collect(Collectors.toList());
        TargetingGroup group = targetingGroupDao.create(content.getContentId(), targetingPredicates);

        return CreateContentResponse.builder()
                .withAdvertisingContent(AdvertisementContentTranslator.toCoral(content, request.getMarketplaceId()))
                .withTargetingGroup(TargetingGroupTranslator.toCoral(group))
                .build();
    }

}
