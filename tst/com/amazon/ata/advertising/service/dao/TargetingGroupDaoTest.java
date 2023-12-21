package com.amazon.ata.advertising.service.dao;

import com.amazon.ata.advertising.service.exceptions.AdvertisementClientException;
import com.amazon.ata.advertising.service.dependency.TargetingPredicateInjector;
import com.amazon.ata.advertising.service.targeting.TargetingGroup;
import com.amazon.ata.advertising.service.targeting.predicate.ParentPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicate;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class TargetingGroupDaoTest {
    private ArgumentCaptor<DynamoDBQueryExpression<TargetingGroup>> argumentCaptor;

    @Mock
    private TargetingPredicateInjector injector;

    @Mock
    private DynamoDBMapper mapper;

    @Mock
    private PaginatedQueryList<TargetingGroup> paginatedQueryList;

    @InjectMocks
    private TargetingGroupDao targetingGroupDao;

    @BeforeEach
    public void setup() {
        initMocks(this);

        argumentCaptor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        when(mapper.query(eq(TargetingGroup.class), argumentCaptor.capture())).thenReturn(paginatedQueryList);
    }

    @Test
    public void get_existingContentId_returnsTargetingGroup() {
        // GIVEN
        String contentId = "0859b575-7e33-401c-9dcb-401e5f695d64";

        // WHEN
        List<TargetingGroup> targetingGroups = targetingGroupDao.get(contentId);

        // THEN
        DynamoDBQueryExpression<TargetingGroup> dynamoDBQueryExpression = argumentCaptor.getValue();
        assertEquals(TargetingGroup.CONTENT_ID_INDEX, dynamoDBQueryExpression.getIndexName());
        assertEquals(contentId, dynamoDBQueryExpression.getHashKeyValues().getContentId());
        assertEquals(paginatedQueryList, targetingGroups);
    }

    @Test
    public void create_newTargetingGroup_saves() {
        // GIVEN
        String contentId = UUID.randomUUID().toString();
        List<TargetingPredicate> targetingPredicates = Collections.singletonList(new ParentPredicate());

        // WHEN
        TargetingGroup targetingGroup = targetingGroupDao.create(contentId, targetingPredicates);

        // THEN
        verify(mapper).save(targetingGroup);
        assertEquals(contentId, targetingGroup.getContentId());
        assertEquals(targetingPredicates, targetingGroup.getTargetingPredicates());
        assertNotNull(targetingGroup.getTargetingGroupId());
        assertEquals(1.0, targetingGroup.getClickThroughRate());
    }

    @Test
    public void update_targetingGroupDoesNotExist_throwsClientException() {
        // GIVEN
        String targetingGroupId = UUID.randomUUID().toString();
        double clickThroughRate = 0.314;
        when(mapper.load(any(TargetingGroup.class))).thenReturn(null);

        // WHEN + THEN
        assertThrows(AdvertisementClientException.class, () -> targetingGroupDao.update(targetingGroupId, clickThroughRate));
    }

    @Test
    public void update_targetingGroupExists_saveChanges() {
        // GIVEN
        String targetingGroupId = UUID.randomUUID().toString();
        double clickThroughRate = 0.314;
        TargetingGroup targetingGroup = new TargetingGroup(targetingGroupId, "1", 1.0, Collections.emptyList());
        when(mapper.load(any(TargetingGroup.class))).thenReturn(targetingGroup);

        // WHEN
        TargetingGroup actualTargetingGroup = targetingGroupDao.update(targetingGroupId, clickThroughRate);

        // THEN
        verify(mapper).save(actualTargetingGroup);
        assertEquals(clickThroughRate, actualTargetingGroup.getClickThroughRate());
    }

    @Test
    public void delete_contentIdExists_deletesAndReturnsTargetingGroups() {
        // GIVEN
        String contentId = "0b63284a-9c16-11e8-98d0-529269fb1459";

        // WHEN
        List<TargetingGroup> targetingGroups = targetingGroupDao.delete(contentId);

        // THEN
        assertEquals(paginatedQueryList, targetingGroups);
        verify(mapper).batchDelete(targetingGroups);
    }

}
