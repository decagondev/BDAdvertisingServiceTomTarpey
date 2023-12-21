package com.amazon.ata.advertising.service.model.translator;

import com.amazon.ata.advertising.service.model.Advertisement;
import com.amazon.ata.advertising.service.model.AdvertisementContent;
import com.amazon.ata.advertising.service.model.GeneratedAdvertisement;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AdvertisementTranslatorTest {

    @Test
    public void toCoral_internalAdvertisementRepresentation_returnsCoralShape() {
        // GIVEN
        String contentId = UUID.randomUUID().toString();
        String content = "Hello World";
        AdvertisementContent advertisementContent = AdvertisementContent.builder()
                .withContentId(contentId)
                .withRenderableContent(content)
                .build();
        GeneratedAdvertisement generatedAdvertisement = new GeneratedAdvertisement(advertisementContent);

        // WHEN
        Advertisement coralShape = AdvertisementTranslator.toCoral(generatedAdvertisement);

        // THEN
        assertNotNull(coralShape.getId());
        assertEquals(content, coralShape.getContent());
    }

}