package com.amazon.ata.advertising.service.activity;

import com.amazon.ata.advertising.service.model.AdvertisingContent;
import com.amazon.ata.advertising.service.model.requests.UpdateContentRequest;
import com.amazon.ata.advertising.service.model.responses.UpdateContentResponse;
import com.amazon.ata.advertising.service.dao.ContentDao;
import com.amazon.ata.advertising.service.dao.TargetingGroupDao;
import com.amazon.ata.advertising.service.model.AdvertisementContent;
import com.amazon.ata.advertising.service.model.translator.AdvertisementContentTranslator;
import com.amazon.ata.advertising.service.model.translator.TargetingGroupTranslator;
import com.amazon.ata.advertising.service.targeting.TargetingGroup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class UpdateContentActivity {
    private static final Logger LOG = LogManager.getLogger(UpdateContentActivity.class);

    private final ContentDao contentDao;
    private final TargetingGroupDao targetingGroupDao;

    /**
     * Instantiates an UpdateContentActivity.
     * @param contentDao The source of data for content
     * @param targetingGroupDao The source of data for targeting groups
     */
    @Inject
    public UpdateContentActivity(ContentDao contentDao, TargetingGroupDao targetingGroupDao) {
        this.contentDao = contentDao;
        this.targetingGroupDao = targetingGroupDao;
    }

    /**
     * Updates a piece of advertising content. You can either update the content of the advertisement itself or the
     * marketplace in which it is scheduled to be displayed.
     * @param request The service request to update
     * @return the updated content
     */
    public UpdateContentResponse updateContent(UpdateContentRequest request) {
        String marketplaceId = request.getAdvertisingContent().getMarketplaceId();
        AdvertisingContent requestedContent = request.getAdvertisingContent();
        LOG.info(String.format("Updating content with id: %s. Updated content: %s",
            requestedContent.getId(), requestedContent));

        AdvertisementContent updatedContent = contentDao.update(marketplaceId,
            AdvertisementContentTranslator.fromCoral(requestedContent));

        List<TargetingGroup> targetingGroups = targetingGroupDao.get(updatedContent.getContentId());
        List<com.amazon.ata.advertising.service.model.TargetingGroup> coralTargetingGroup = targetingGroups.stream()
                .map(TargetingGroupTranslator::toCoral)
                .collect(Collectors.toList());

        return UpdateContentResponse.builder()
                .withAdvertisingContent(AdvertisementContentTranslator.toCoral(updatedContent, marketplaceId))
                .withTargetingGroups(coralTargetingGroup)
                .build();
    }
}
