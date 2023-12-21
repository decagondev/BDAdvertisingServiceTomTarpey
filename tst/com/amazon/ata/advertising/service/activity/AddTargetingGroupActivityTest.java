package com.amazon.ata.advertising.service.activity;

import com.amazon.ata.advertising.service.model.requests.AddTargetingGroupRequest;
import com.amazon.ata.advertising.service.model.responses.AddTargetingGroupResponse;
import com.amazon.ata.advertising.service.model.TargetingGroup;
import com.amazon.ata.advertising.service.model.TargetingPredicate;
import com.amazon.ata.advertising.service.model.TargetingPredicateType;
import com.amazon.ata.advertising.service.dao.TargetingGroupDao;
import com.amazon.ata.advertising.service.targeting.predicate.AgeTargetingPredicate;
import com.amazon.ata.customerservice.AgeRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AddTargetingGroupActivityTest {
    private static final String CONTENT_ID = UUID.randomUUID().toString();
    private static final String TARGETING_GROUP_ID = UUID.randomUUID().toString();

    @Mock
    private TargetingGroupDao targetingGroupDao;

    @InjectMocks
    private AddTargetingGroupActivity addTargetingGroupActivity;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void addTargetingGroup_withoutPredicates_createTargetingGroupWithoutPredicates() {
        // GIVEN
        com.amazon.ata.advertising.service.targeting.TargetingGroup targetingGroup =
                new com.amazon.ata.advertising.service.targeting.TargetingGroup(TARGETING_GROUP_ID, CONTENT_ID,
                        1.0, new ArrayList<>());
        when(targetingGroupDao.create(CONTENT_ID, new ArrayList<>())).thenReturn(targetingGroup);

        AddTargetingGroupRequest request = AddTargetingGroupRequest.builder()
                .withContentId(CONTENT_ID)
                .build();

        // WHEN
        AddTargetingGroupResponse response = addTargetingGroupActivity.addTargetingGroup(request);

        // THEN
        assertTargetingGroup(response.getTargetingGroup());
        assertTrue(response.getTargetingGroup().getTargetingPredicates().isEmpty());

    }

    @Test
    public void addTargetingGroup_withPredicates_createTargetingGroupWithPredicates() {
        // GIVEN
        com.amazon.ata.advertising.service.targeting.TargetingGroup targetingGroup =
                new com.amazon.ata.advertising.service.targeting.TargetingGroup(TARGETING_GROUP_ID, CONTENT_ID,
                        1.0, Collections.singletonList(new AgeTargetingPredicate(AgeRange.AGE_26_TO_30)));
        ArgumentMatcher<List<com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicate>>
                containsAgePredicate = list -> !list.isEmpty() && list.get(0) instanceof AgeTargetingPredicate;
        when(targetingGroupDao.create(eq(CONTENT_ID), argThat(containsAgePredicate))).thenReturn(targetingGroup);

        Map<String, String> attributes = new HashMap<>();
        attributes.put("AgeRange", AgeRange.AGE_26_TO_30);
        AddTargetingGroupRequest request = AddTargetingGroupRequest.builder()
                .withContentId(CONTENT_ID)
                .withTargetingPredicates(Collections.singletonList(
                        TargetingPredicate.builder()
                                .withTargetingPredicateType(TargetingPredicateType.AGE)
                                .withAttributes(attributes)
                                .build())
                )
                .build();

        // WHEN
        AddTargetingGroupResponse response = addTargetingGroupActivity.addTargetingGroup(request);

        // THEN
        assertTargetingGroup(response.getTargetingGroup());
        assertEquals(1, response.getTargetingGroup().getTargetingPredicates().size());
        assertEquals(TargetingPredicateType.AGE, response.getTargetingGroup().getTargetingPredicates().get(0).getTargetingPredicateType());
    }

    private void assertTargetingGroup(TargetingGroup actual) {
        assertEquals(CONTENT_ID, actual.getContentId());
        assertEquals(TARGETING_GROUP_ID, actual.getTargetingGroupId());
        assertEquals(1.0, actual.getClickThroughRate());
    }

}
