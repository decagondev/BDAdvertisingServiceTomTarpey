package com.amazon.ata.advertising.service.model.translator;

import com.amazon.ata.advertising.service.model.AdvertisingContent;
import com.amazon.ata.advertising.service.model.AdvertisementContent;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdvertisementContentTranslatorTest {

    @Test
    public void toCoral_internalAdvertisementContent_correctCoralShape() {
        // GIVEN
        String marketplaceId = "1";
        String contentId = UUID.randomUUID().toString();
        String content = "Hello world";
        AdvertisementContent advertisementContent = AdvertisementContent.builder()
                .withContentId(contentId)
                .withRenderableContent(content)
                .build();

        // WHEN
        AdvertisingContent coralShape = AdvertisementContentTranslator.toCoral(advertisementContent, marketplaceId);

        // THEN
        assertEquals(contentId, coralShape.getId());
        assertEquals(marketplaceId, coralShape.getMarketplaceId());
        assertEquals(content, coralShape.getContent());
    }

    @Test
    public void fromCoral_coralShape_correctAdvertisementContent() {
        // GIVEN
        String marketplaceId = UUID.randomUUID().toString();
        String contentId = UUID.randomUUID().toString();
        String content = "Goodbye moon.";
        AdvertisingContent coralShape = AdvertisingContent.builder()
                .withMarketplaceId(marketplaceId)
                .withId(contentId)
                .withContent(content)
                .build();

        // WHEN
        AdvertisementContent advertisementContent = AdvertisementContentTranslator.fromCoral(coralShape);

        // THEN
        assertEquals(contentId, advertisementContent.getContentId());
        assertEquals(content, advertisementContent.getRenderableContent());
    }

}
