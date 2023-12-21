package com.amazon.ata.advertising.service.activity;

import com.amazon.ata.advertising.service.model.requests.DeleteContentRequest;
import com.amazon.ata.advertising.service.model.responses.DeleteContentResponse;
import com.amazon.ata.advertising.service.dao.ContentDao;
import com.amazon.ata.advertising.service.dao.TargetingGroupDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteContentActivity {
    private static final Logger LOG = LogManager.getLogger(DeleteContentActivity.class);

    private final ContentDao contentDao;
    private final TargetingGroupDao targetingGroupDao;

    /**
     * The activity for the DeleteContent API.
     * @param contentDao contains the advertisement content to delete
     * @param targetingGroupDao contains the targeting groups to delete
     */
    @Inject
    public DeleteContentActivity(ContentDao contentDao, TargetingGroupDao targetingGroupDao) {
        this.contentDao = contentDao;
        this.targetingGroupDao = targetingGroupDao;
    }

    /**
     * Deletes a piece of advertising content. The targeting groups associated with the content will also be deleted.
     * If the provided contentId does not match any existing content an AdvertisementClientException will be thrown.
     *
     * @param request A piece of advertising content to delete.
     * @return an empty DeleteContentResponse object
     */
    public DeleteContentResponse deleteContent(DeleteContentRequest request) {
        String contentId = request.getContentId();
        LOG.info(String.format("Deleting content with id: %s.", contentId));

        targetingGroupDao.delete(contentId);
        contentDao.delete(contentId);

        return DeleteContentResponse.builder().build();
    }

}
