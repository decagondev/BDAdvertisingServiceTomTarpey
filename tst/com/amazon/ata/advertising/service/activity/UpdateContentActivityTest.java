package com.amazon.ata.advertising.service.activity;

import com.amazon.ata.advertising.service.model.AdvertisingContent;
import com.amazon.ata.advertising.service.model.requests.UpdateContentRequest;
import com.amazon.ata.advertising.service.model.responses.UpdateContentResponse;
import com.amazon.ata.advertising.service.dao.ContentDao;
import com.amazon.ata.advertising.service.dao.TargetingGroupDao;
import com.amazon.ata.advertising.service.model.AdvertisementContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UpdateContentActivityTest {
    private static final String CONTENT_ID = UUID.randomUUID().toString();
    private static final String CONTENT = "Something new!";
    private static final String MARKETPLACE_ID = UUID.randomUUID().toString();

    @Mock
    private ContentDao contentDao;

    @Mock
    private TargetingGroupDao targetingGroupDao;

    @InjectMocks
    private UpdateContentActivity updateContentActivity;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void updateContent_requestUpdates_resultsContainUpdates() {
        // GIVEN
        AdvertisingContent advertisingContent = AdvertisingContent.builder()
                .withId(CONTENT_ID)
                .withMarketplaceId(MARKETPLACE_ID)
                .withContent(CONTENT)
                .build();
        UpdateContentRequest request = UpdateContentRequest.builder()
                .withContentId(CONTENT_ID)
                .withAdvertisingContent(advertisingContent)
                .build();
        AdvertisementContent advertisementContent = AdvertisementContent.builder()
                .withContentId(CONTENT_ID)
                .withRenderableContent(CONTENT)
                .build();
        when(contentDao.update(eq(MARKETPLACE_ID), any(AdvertisementContent.class))).thenReturn(advertisementContent);
        when(targetingGroupDao.get(CONTENT_ID)).thenReturn(new ArrayList<>());

        // WHEN
        UpdateContentResponse response = updateContentActivity.updateContent(request);

        // THEN
        assertEquals(new ArrayList<>(), response.getTargetingGroups());
        assertEquals(CONTENT_ID, response.getAdvertisingContent().getId());
        assertEquals(MARKETPLACE_ID, response.getAdvertisingContent().getMarketplaceId());
        assertEquals(CONTENT, response.getAdvertisingContent().getContent());
    }

}
