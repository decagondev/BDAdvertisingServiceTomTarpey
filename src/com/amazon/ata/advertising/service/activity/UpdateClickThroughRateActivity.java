package com.amazon.ata.advertising.service.activity;

import com.amazon.ata.advertising.service.model.requests.UpdateClickThroughRateRequest;
import com.amazon.ata.advertising.service.model.responses.UpdateClickThroughRateResponse;
import com.amazon.ata.advertising.service.dao.TargetingGroupDao;
import com.amazon.ata.advertising.service.model.translator.TargetingGroupTranslator;
import com.amazon.ata.advertising.service.targeting.TargetingGroup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateClickThroughRateActivity {
    private static final Logger LOG = LogManager.getLogger(UpdateClickThroughRateActivity.class);

    private final TargetingGroupDao targetingGroupDao;

    /**
     * Instantiates an UpdateClickThroughRateActivity.
     * @param targetingGroupDao The source of data for targeting groups
     */
    @Inject
    public UpdateClickThroughRateActivity(TargetingGroupDao targetingGroupDao) {
        this.targetingGroupDao = targetingGroupDao;
    }

    /**
     * Updates the click through rate that has been calculated for a targeting group based on the targetingGroupId.
     * @param request The service request to update the CTR
     * @return The updated targeting group
     */
    public UpdateClickThroughRateResponse updateClickThroughRate(UpdateClickThroughRateRequest request) {
        double ctr = request.getClickThroughRate();
        String targetingGroupId = request.getTargetingGroupId();
        LOG.info(String.format("Updating CTR for targeting group with id: %s to %.3f", targetingGroupId, ctr));

        TargetingGroup group = targetingGroupDao.update(targetingGroupId, ctr);

        return UpdateClickThroughRateResponse.builder()
                .withTargetingGroup(TargetingGroupTranslator.toCoral(group))
                .build();
    }
}
