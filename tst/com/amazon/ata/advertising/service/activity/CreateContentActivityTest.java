package com.amazon.ata.advertising.service.activity;

import com.amazon.ata.advertising.service.model.requests.CreateContentRequest;
import com.amazon.ata.advertising.service.model.responses.CreateContentResponse;
import com.amazon.ata.advertising.service.model.TargetingPredicate;
import com.amazon.ata.advertising.service.model.TargetingPredicateType;
import com.amazon.ata.advertising.service.dao.ContentDao;
import com.amazon.ata.advertising.service.dao.TargetingGroupDao;
import com.amazon.ata.advertising.service.model.AdvertisementContent;
import com.amazon.ata.advertising.service.targeting.TargetingGroup;
import com.amazon.ata.advertising.service.targeting.predicate.RecognizedTargetingPredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreateContentActivityTest {
    private static final String MARKETPLACE_ID = "1";
    private static final String CONTENT = "Hello World";
    private static final String CONTENT_ID = UUID.randomUUID().toString();

    @Mock
    private ContentDao contentDao;

    @Mock
    private TargetingGroupDao targetingGroupDao;

    @InjectMocks
    private CreateContentActivity activity;

    @BeforeEach
    public void setup() {
        initMocks(this);
        AdvertisementContent content = AdvertisementContent.builder()
                .withContentId(CONTENT_ID)
                .withRenderableContent(CONTENT)
                .build();
        when(contentDao.create(MARKETPLACE_ID, CONTENT)).thenReturn(content);
    }

    @Test
    public void createContent_contentRequested_contentSaved() {
        // GIVEN
        TargetingPredicate recognizedPredicate = TargetingPredicate.builder()
                .withTargetingPredicateType(TargetingPredicateType.RECOGNIZED)
                .withAttributes(new HashMap<>())
                .build();
        CreateContentRequest request = CreateContentRequest.builder()
                .withMarketplaceId(MARKETPLACE_ID)
                .withContent(CONTENT)
                .withTargetingPredicates(Collections.singletonList(recognizedPredicate))
                .build();
        TargetingGroup targetingGroup = new TargetingGroup(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1, Arrays.asList(new RecognizedTargetingPredicate()));
        when(targetingGroupDao.create(eq(CONTENT_ID), argThat(list -> list.size() == 1 && list.get(0) instanceof RecognizedTargetingPredicate))).thenReturn(targetingGroup);

        // WHEN
        CreateContentResponse response = activity.createContent(request);

        // THEN
        assertCreateContentResponse(response);
        assertEquals(Collections.singletonList(recognizedPredicate), response.getTargetingGroup().getTargetingPredicates());
    }

    @Test
    public void createContent_nullTargetingPredicates_savesEmptyListOfTargetingPredicates() {
        // GIVEN
        CreateContentRequest request = CreateContentRequest.builder()
                .withMarketplaceId(MARKETPLACE_ID)
                .withContent(CONTENT)
                .build();
        TargetingGroup targetingGroup = new TargetingGroup(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1, new ArrayList<>());
        when(targetingGroupDao.create(CONTENT_ID, new ArrayList<>())).thenReturn(targetingGroup);

        // WHEN
        CreateContentResponse response = activity.createContent(request);

        // THEN
        assertCreateContentResponse(response);
        assertEquals(new ArrayList<>(), response.getTargetingGroup().getTargetingPredicates());
    }

    private void assertCreateContentResponse(CreateContentResponse response) {
        assertNotNull(response.getAdvertisingContent().getId());
        assertEquals(MARKETPLACE_ID, response.getAdvertisingContent().getMarketplaceId());
        assertEquals(CONTENT, response.getAdvertisingContent().getContent());
        assertNotNull(response.getTargetingGroup().getTargetingGroupId());
        assertNotNull(response.getTargetingGroup().getContentId());
        assertEquals(1, response.getTargetingGroup().getClickThroughRate());
    }

}
