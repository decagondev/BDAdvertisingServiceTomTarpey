package com.amazon.ata.advertising.service.activity;

import com.amazon.ata.advertising.service.model.requests.GenerateAdvertisementRequest;
import com.amazon.ata.advertising.service.model.responses.GenerateAdvertisementResponse;
import com.amazon.ata.advertising.service.businesslogic.AdvertisementSelectionLogic;
import com.amazon.ata.advertising.service.model.AdvertisementContent;
import com.amazon.ata.advertising.service.model.EmptyGeneratedAdvertisement;
import com.amazon.ata.advertising.service.model.GeneratedAdvertisement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GenerateAdActivityTest {

    private static final String CUSTOMER_ID = "A123B456";
    private static final String MARKETPLACE_ID = "1";
    private static final String CONTENT_ID = UUID.randomUUID().toString();
    private static final String RENDERABLE_CONTENT = "<div class=\"ata-ad\"> Click here! </div>";

    private static final GenerateAdvertisementRequest REQUEST = GenerateAdvertisementRequest.builder()
            .withCustomerId(CUSTOMER_ID)
            .withMarketplaceId(MARKETPLACE_ID)
            .build();

    private static final AdvertisementContent CONTENT = AdvertisementContent.builder()
            .withRenderableContent(RENDERABLE_CONTENT)
            .withContentId(CONTENT_ID)
            .build();

    private static final GeneratedAdvertisement EMPTY_GENERATED_ADVERTISEMENT = new EmptyGeneratedAdvertisement();
    private static final GeneratedAdvertisement GENERATED_ADVERTISEMENT = new GeneratedAdvertisement(CONTENT);

    @Mock
    private AdvertisementSelectionLogic adSelectionService;

    @InjectMocks
    private GenerateAdActivity activity;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void testGenerateAd_advertisementReturned() {
        when(adSelectionService.selectAdvertisement(CUSTOMER_ID, MARKETPLACE_ID)).thenReturn(GENERATED_ADVERTISEMENT);
        final GenerateAdvertisementResponse response = activity.generateAd(REQUEST);

        assertNotNull(response.getAdvertisement());
        assertEquals(GENERATED_ADVERTISEMENT.getId(), response.getAdvertisement().getId());
        assertEquals(GENERATED_ADVERTISEMENT.getContent().getRenderableContent(), response.getAdvertisement().getContent());
    }

    @Test
    public void testGenerateAd_emptyAdvertisementReturned() {
        when(adSelectionService.selectAdvertisement(CUSTOMER_ID, MARKETPLACE_ID)).thenReturn(EMPTY_GENERATED_ADVERTISEMENT);
        final GenerateAdvertisementResponse response = activity.generateAd(REQUEST);

        assertNotNull(response.getAdvertisement());
        assertEquals(EMPTY_GENERATED_ADVERTISEMENT.getId(), response.getAdvertisement().getId());
        assertEquals("", response.getAdvertisement().getContent());
    }

    @Test
    public void whenExceptionThrown_emptyAdvertisementReturned() {
        when(adSelectionService.selectAdvertisement(CUSTOMER_ID, MARKETPLACE_ID)).thenThrow(new RuntimeException());
        final GenerateAdvertisementResponse response = activity.generateAd(REQUEST);

        assertNotNull(response.getAdvertisement());
        assertEquals("", response.getAdvertisement().getContent());
    }
}
