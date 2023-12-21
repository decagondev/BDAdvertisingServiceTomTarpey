package com.amazon.ata.advertising.service.model.translator;

import com.amazon.ata.advertising.service.model.AdvertisingContent;
import com.amazon.ata.advertising.service.model.AdvertisementContent;

/**
 * Class used to perform translations from the service's internal AdvertisementContent representation.
 */
public class AdvertisementContentTranslator {

    private AdvertisementContentTranslator() {}

    /**
     * Translates an internal AdvertisementContent to the coral model's AdvertisingContent shape.
     * @param content - the object containing information to be converted to the coral model's shape
     * @param marketplaceId The marketplaceId is not stored in the internal representation
     * @return the AdvertisingContent object filled in with the translated data
     */
    public static AdvertisingContent toCoral(final AdvertisementContent content, final String marketplaceId) {
        return AdvertisingContent.builder()
                .withId(content.getContentId())
                .withMarketplaceId(marketplaceId)
                .withContent(content.getRenderableContent())
                .build();
    }

    /**
     * Translates a coral shape into an internal AdvertisementContent.
     * @param advertisingContent coral shape
     * @return our internal representation of an AdvertisementContent
     */
    public static AdvertisementContent fromCoral(final AdvertisingContent advertisingContent) {
        return AdvertisementContent.builder()
                .withContentId(advertisingContent.getId())
                .withRenderableContent(advertisingContent.getContent())
                .build();
    }
}
