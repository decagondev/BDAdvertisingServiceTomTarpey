package com.amazon.ata.advertising.service.dao;

import com.amazon.ata.advertising.service.exceptions.AdvertisementClientException;
import com.amazon.ata.advertising.service.model.AdvertisementContent;
import com.amazon.ata.advertising.service.util.EncryptionUtil;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ContentDaoTest {
    private static final String MARKETPLACE_ID = UUID.randomUUID().toString();
    private static final String CONTENT_ID = UUID.randomUUID().toString();
    private static final String RENDERABLE_CONTENT = "Buy this now!";
    private static final AdvertisementContent ADVERTISEMENT_CONTENT = AdvertisementContent.builder()
            .withMarketplaceId(MARKETPLACE_ID)
            .withContentId(CONTENT_ID)
            .withRenderableContent(RENDERABLE_CONTENT)
            .build();

    private ArgumentCaptor<DynamoDBQueryExpression<AdvertisementContent>> captor;

    @Mock
    private DynamoDBMapper mapper;

    @Mock
    private PaginatedQueryList<AdvertisementContent> paginatedQueryList;

    @InjectMocks
    private ContentDao contentDao;

    @BeforeEach
    public void setup() {
        initMocks(this);

        captor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        when(mapper.query(eq(AdvertisementContent.class), captor.capture())).thenReturn(paginatedQueryList);
        when(mapper.load(ADVERTISEMENT_CONTENT)).thenReturn(ADVERTISEMENT_CONTENT);
    }

    @Test
    public void get_encryptedMarketplaceWithContent_returnsListOfContent() {
        // WHEN
        List<AdvertisementContent> content = contentDao.get(MARKETPLACE_ID);

        // THEN
        assertEquals(paginatedQueryList, content);
        DynamoDBQueryExpression<AdvertisementContent> queryExpression = captor.getValue();
        assertEquals(AdvertisementContent.MARKETPLACE_ID_INDEX, queryExpression.getIndexName());
        assertEquals(MARKETPLACE_ID, queryExpression.getHashKeyValues().getMarketplaceId());
    }

    @Test
    public void get_unencryptedMarketplaceWithContent_returnsListOfContent() {
        // GIVEN
        String marketplaceId = "1";

        // WHEN
        List<AdvertisementContent> content = contentDao.get(marketplaceId);

        // THEN
        assertEquals(paginatedQueryList, content);
        DynamoDBQueryExpression<AdvertisementContent> queryExpression = captor.getValue();
        assertEquals(AdvertisementContent.MARKETPLACE_ID_INDEX, queryExpression.getIndexName());
        assertEquals(EncryptionUtil.encryptMarketplaceId(marketplaceId), queryExpression.getHashKeyValues().getMarketplaceId());
    }

    @Test
    public void create_givenMarketplaceIdAndContent_contentSaved() {
        // WHEN
        AdvertisementContent content = contentDao.create(MARKETPLACE_ID, RENDERABLE_CONTENT);

        // THEN
        assertEquals(MARKETPLACE_ID, content.getMarketplaceId());
        assertEquals(RENDERABLE_CONTENT, content.getRenderableContent());
        verify(mapper).save(AdvertisementContent.builder().withContentId(content.getContentId()).build());
    }

    @Test
    public void create_givenUnEncryptedMarketplaceId_contentSaved() {
        // GIVEN
        String marketplaceId = "1";

        // WHEN
        AdvertisementContent content = contentDao.create(marketplaceId, RENDERABLE_CONTENT);

        // THEN
        assertEquals(EncryptionUtil.encryptMarketplaceId(marketplaceId), content.getMarketplaceId());
        assertEquals(RENDERABLE_CONTENT, content.getRenderableContent());
        verify(mapper).save(AdvertisementContent.builder().withContentId(content.getContentId()).build());
    }

    @Test
    public void update_existingContent_updatesValues() {
        // WHEN
        AdvertisementContent content = contentDao.update(MARKETPLACE_ID, ADVERTISEMENT_CONTENT);

        // THEN
        assertEquals(ADVERTISEMENT_CONTENT, content);
        verify(mapper).save(ADVERTISEMENT_CONTENT);
    }

    @Test
    public void update_unEncryptedMarketplaceId_updatesValues() {
        // GIVEN
        String marketplaceId = "1";

        // WHEN
        AdvertisementContent content = contentDao.update(marketplaceId, ADVERTISEMENT_CONTENT);

        // THEN
        assertEquals(EncryptionUtil.encryptMarketplaceId(marketplaceId), content.getMarketplaceId());
        verify(mapper).save(ADVERTISEMENT_CONTENT);
    }

    @Test
    public void update_contentDoesNotExist_throwsException() {
        // GIVEN
        String marketplaceId = "1";
        when(mapper.load(ADVERTISEMENT_CONTENT)).thenReturn(null);

        // WHEN && THEN
        assertThrows(AdvertisementClientException.class, () -> contentDao.update(marketplaceId, ADVERTISEMENT_CONTENT),
                "Expected an AdvertisementClientException to be thrown when no content can be found for" +
                        "provided contentId: " + CONTENT_ID);
    }

    @Test
    public void delete_contentExists_contentDeletedAndReturned() {
        // GIVEN

        // WHEN
        AdvertisementContent content = contentDao.delete(CONTENT_ID);

        // THEN
        assertEquals(ADVERTISEMENT_CONTENT, content);
        verify(mapper).delete(ADVERTISEMENT_CONTENT);
    }

    @Test
    public void delete_contentDoesNotExist_throwsException() {
        // GIVEN
        when(mapper.load(ADVERTISEMENT_CONTENT)).thenReturn(null);

        // WHEN && THEN
        assertThrows(AdvertisementClientException.class, () -> contentDao.delete(CONTENT_ID),
            "Expected an AdvertisementClientException to be thrown when no content can be found for" +
                "provided contentId: " + CONTENT_ID);
    }
}
