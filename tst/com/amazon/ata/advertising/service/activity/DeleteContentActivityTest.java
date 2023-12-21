package com.amazon.ata.advertising.service.activity;

import com.amazon.ata.advertising.service.exceptions.AdvertisementClientException;
import com.amazon.ata.advertising.service.model.requests.DeleteContentRequest;
import com.amazon.ata.advertising.service.model.responses.DeleteContentResponse;
import com.amazon.ata.advertising.service.dao.ContentDao;
import com.amazon.ata.advertising.service.dao.TargetingGroupDao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeleteContentActivityTest {
    private static final String CONTENT_ID = UUID.randomUUID().toString();

    @Mock
    private ContentDao contentDao;

    @Mock
    private TargetingGroupDao targetingGroupDao;

    @InjectMocks
    private DeleteContentActivity deleteContentActivity;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void deleteContent_contentIdDoesNotExist_throwsException() {
        // GIVEN
        DeleteContentRequest request = DeleteContentRequest.builder()
                .withContentId(CONTENT_ID)
                .build();
        when(contentDao.delete(CONTENT_ID)).thenThrow(new AdvertisementClientException("FAIL"));

        // WHEN && THEN
        assertThrows(AdvertisementClientException.class, () -> deleteContentActivity.deleteContent(request),
            "Expected an AdvertisementClientException to be thrown when no content can be found for" +
                "provided contentId: " + CONTENT_ID);
    }

    @Test
    public void deleteContent_contentIdExists_contentAndTargetingDeleted() {
        // GIVEN
        DeleteContentRequest request = DeleteContentRequest.builder()
            .withContentId(CONTENT_ID)
            .build();

        // WHEN
        DeleteContentResponse response = deleteContentActivity.deleteContent(request);

        // THEN
        assertNotNull(response, "Expected a non-null response from the api.");
        verify(targetingGroupDao).delete(CONTENT_ID);
        verify(contentDao).delete(CONTENT_ID);
    }
}
