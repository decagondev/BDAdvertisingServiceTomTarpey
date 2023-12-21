package com.amazon.ata.advertising.service.dao;

import com.amazon.ata.advertising.service.exceptions.AdvertisementClientException;
import com.amazon.ata.advertising.service.dependency.TargetingPredicateInjector;
import com.amazon.ata.advertising.service.targeting.TargetingGroup;
import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicate;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
import java.util.UUID;
import javax.inject.Inject;

/**
 * Gets the TargetingGroups for a piece of ATA ad content.
 */
public class TargetingGroupDao implements ReadableDao<String, List<TargetingGroup>> {
    private final TargetingPredicateInjector targetingPredicateInjector;
    private final DynamoDBMapper mapper;

    /**
     * Constructs a new TargetingGroupDao.
     * @param targetingPredicateInjector injects the dependencies into the predicates
     * @param mapper connection to DynamoDB
     */
    @Inject
    public TargetingGroupDao(TargetingPredicateInjector targetingPredicateInjector, DynamoDBMapper mapper) {
        this.targetingPredicateInjector = targetingPredicateInjector;
        this.mapper = mapper;
    }

    /**
     * Retrieves a metadata object for a piece of ATA ad content. The return is wrapped in an optional, which will be
     * empty if no metatdata could be retrieved.
     * @param contentId The id of the content to get metadata for
     * @return the Advertisement metadata for the piece of content. If there is no metadata the Optional will be empty.
     */
    @Override
    public List<TargetingGroup> get(String contentId) {
        TargetingGroup indexHashKey = new TargetingGroup(null, contentId, 0,  null);
        DynamoDBQueryExpression<TargetingGroup> queryExpression = new DynamoDBQueryExpression<TargetingGroup>()
                .withIndexName(TargetingGroup.CONTENT_ID_INDEX)
                .withConsistentRead(false)
                .withHashKeyValues(indexHashKey);
        return mapper.query(TargetingGroup.class, queryExpression);
    }

    /**
     * Create a new targeting group and persist it.
     * @param contentId The content to associate with the new targeting group.
     * @param targetingPredicates The targeting predicate list to create the new group from.
     * @return The newly created group
     */
    public TargetingGroup create(String contentId, List<TargetingPredicate> targetingPredicates) {
        TargetingGroup group = new TargetingGroup(UUID.randomUUID().toString(), contentId, 1.0, targetingPredicates);
        mapper.save(group);
        return group;
    }

    /**
     * Update the click through rate for a targeting group.
     * @param targetingGroupId The ID of the targeting group to update
     * @param clickThroughRate The new clickThroughRate for the targeting group
     * @return The updated TargetingGroup
     */
    public TargetingGroup update(String targetingGroupId, double clickThroughRate) {
        TargetingGroup hashKey = new TargetingGroup(targetingGroupId, null, 0, null);
        TargetingGroup targetingGroup = mapper.load(hashKey);
        if (targetingGroup == null) {
            throw new AdvertisementClientException("No targeting group exists with the ID " + targetingGroupId);
        }
        targetingGroup.setClickThroughRate(clickThroughRate);
        mapper.save(targetingGroup);
        return targetingGroup;
    }

    /**
     * Deletes the List of TargetingGroups associated with the provided contentId.
     * @param contentId - the id of the content to delete TargetingGroups for
     * @return the deleted list of TargetingGroups
     */
    public List<TargetingGroup> delete(String contentId) {
        List<TargetingGroup> targetingGroups = get(contentId);
        mapper.batchDelete(targetingGroups);
        return targetingGroups;
    }
}
