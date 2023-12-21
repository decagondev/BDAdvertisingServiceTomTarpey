package com.amazon.ata.advertising.service.dao;

import com.amazon.ata.advertising.service.exceptions.AdvertisementClientException;
import com.amazon.ata.advertising.service.model.AdvertisementContent;
import com.amazon.ata.advertising.service.util.EncryptionUtil;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
import java.util.UUID;
import javax.inject.Inject;

/**
 * Gets ATA Content based on Marketplace.
 */
public class ContentDao implements ReadableDao<String, List<AdvertisementContent>> {
    private final DynamoDBMapper mapper;

    /**
     * Constructs a ContentDao.
     * @param mapper Connection to dynamo
     */
    @Inject
    public ContentDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Gets content for ATA based on the Marketplace of the request.
     * @param marketplaceId The marketplace to get content for.
     * @return A list of all advertisement content that could be shown for ATA in this marketplace.
     */
    @Override
    public List<AdvertisementContent> get(String marketplaceId) {
        String encryptedMarketplace = EncryptionUtil.encryptMarketplaceId(marketplaceId);
        AdvertisementContent indexHashKey = AdvertisementContent.builder()
                .withMarketplaceId(encryptedMarketplace)
                .build();
        DynamoDBQueryExpression<AdvertisementContent> expression = new DynamoDBQueryExpression<AdvertisementContent>()
                .withIndexName(AdvertisementContent.MARKETPLACE_ID_INDEX)
                .withConsistentRead(false)
                .withHashKeyValues(indexHashKey);
        return mapper.query(AdvertisementContent.class, expression);
    }

    /**
     * Create a new advertisement content and persist it.
     * @param marketplaceId The marketplace to save the new advertisement content in
     * @param content The content to save
     * @return The newly created advertisement content
     */
    public AdvertisementContent create(String marketplaceId, String content) {
        String encryptedMarketplace = EncryptionUtil.encryptMarketplaceId(marketplaceId);
        String id = UUID.randomUUID().toString();
        AdvertisementContent advertisementContent = AdvertisementContent.builder()
                .withContentId(id)
                .withMarketplaceId(encryptedMarketplace)
                .withRenderableContent(content)
                .build();
        mapper.save(advertisementContent);
        return advertisementContent;
    }

    /**
     * Update an AdvertisementContent's renderable content and it's marketplace.
     * @param marketplaceId the marketplace to move the content to.
     * @param advertisementContent The renderable content to be updated.
     * @return The updated AdvertisementContent.
     */
    public AdvertisementContent update(String marketplaceId, AdvertisementContent advertisementContent) {
        AdvertisementContent hashKey = AdvertisementContent.builder()
                .withContentId(advertisementContent.getContentId())
                .build();
        AdvertisementContent existingAdvertisementContent = mapper.load(hashKey);
        if (existingAdvertisementContent == null) {
            throw new AdvertisementClientException("No content exists with the ID " +
                    advertisementContent.getContentId());
        }

        String encryptedMarketplace = EncryptionUtil.encryptMarketplaceId(marketplaceId);
        advertisementContent.setMarketplaceId(encryptedMarketplace);
        mapper.save(advertisementContent);
        return advertisementContent;
    }

    /**
     * Deletes the AdvertisementContent corresponding to the provided contentId. If content cannot be found for the
     * provided contentId an AdvertisementClientException will be thrown.
     * @param contentId - the id of the content to delete
     * @return - the deleted content
     */
    public AdvertisementContent delete(String contentId) {
        AdvertisementContent advertisementContent = AdvertisementContent.builder()
            .withContentId(contentId).build();
        AdvertisementContent deletedContent = mapper.load(advertisementContent);

        if (deletedContent == null) {
            throw new AdvertisementClientException("Unable to find content to delete with contentId: " + contentId);
        }

        mapper.delete(deletedContent);
        return deletedContent;
    }
}
