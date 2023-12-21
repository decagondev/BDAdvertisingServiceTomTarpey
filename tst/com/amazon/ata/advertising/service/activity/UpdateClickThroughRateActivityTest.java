package com.amazon.ata.advertising.service.activity;

import com.amazon.ata.advertising.service.model.requests.UpdateClickThroughRateRequest;
import com.amazon.ata.advertising.service.model.responses.UpdateClickThroughRateResponse;
import com.amazon.ata.advertising.service.dao.TargetingGroupDao;
import com.amazon.ata.advertising.service.targeting.TargetingGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UpdateClickThroughRateActivityTest {
    private static final String TARGETING_GROUP_ID = "12345";
    private static final double CLICK_THROUGH_RATE = 0.02;

    @Mock
    private TargetingGroupDao targetingGroupDao;

    @InjectMocks
    private UpdateClickThroughRateActivity updateClickThroughRateActivity;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void updateClickThroughRate_newClickThroughRate_targetingGroupUpdated() {
        // GIVEN
        TargetingGroup group = new TargetingGroup(TARGETING_GROUP_ID,
                UUID.randomUUID().toString(), CLICK_THROUGH_RATE, new ArrayList<>());
        when(targetingGroupDao.update(TARGETING_GROUP_ID, CLICK_THROUGH_RATE)).thenReturn(group);
        UpdateClickThroughRateRequest request = UpdateClickThroughRateRequest.builder()
                .withTargetingGroupId(TARGETING_GROUP_ID)
                .withClickThroughRate(CLICK_THROUGH_RATE)
                .build();

        // WHEN
        UpdateClickThroughRateResponse response = updateClickThroughRateActivity.updateClickThroughRate(request);

        // THEN
        assertEquals(response.getTargetingGroup().getTargetingGroupId(), TARGETING_GROUP_ID);
        assertEquals(response.getTargetingGroup().getClickThroughRate(), CLICK_THROUGH_RATE);
    }

}
