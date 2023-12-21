package com.amazon.ata.advertising.service.activity;

import com.amazon.ata.advertising.service.model.requests.GenerateAdvertisementRequest;
import com.amazon.ata.advertising.service.model.responses.GenerateAdvertisementResponse;
import com.amazon.ata.advertising.service.businesslogic.AdvertisementSelectionLogic;
import com.amazon.ata.advertising.service.model.EmptyGeneratedAdvertisement;
import com.amazon.ata.advertising.service.model.GeneratedAdvertisement;
import com.amazon.ata.advertising.service.model.translator.AdvertisementTranslator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 *
 * Activity class for generate ad operation.
 *
 */
public class GenerateAdActivity {
    private static final Logger LOG = LogManager.getLogger(GenerateAdActivity.class);

    private final AdvertisementSelectionLogic adSelector;

    /**
     * A Coral activity for the GenerateAdvertisement API.
     * @param advertisementSelector The business logic to select an ad.
     */
    @Inject
    public GenerateAdActivity(AdvertisementSelectionLogic advertisementSelector) {
        this.adSelector = advertisementSelector;
    }

    /**
     * Decides on the ad most likely to be clicked on by the provided customer, from the group of ads a customer is
     * eligible to see.
     * @param request Contains the customerId to generate an advertisement for, and the marketplace id where the ad
     *                will be rendered
     * @return the response will contain the generated advertisement. It's content will be an empty String if no
     *      advertisement could be generated.
     */
    public GenerateAdvertisementResponse generateAd(GenerateAdvertisementRequest request) {
        String customerId = request.getCustomerId();
        String marketplaceId = request.getMarketplaceId();
        LOG.info(String.format("Generating ad for customerId: %s in marketplace: %s", customerId, marketplaceId));

        GenerateAdvertisementResponse response;
        try {
            LOG.debug("This is the adSelector:" + adSelector);
            final GeneratedAdvertisement generatedAd = adSelector.selectAdvertisement(customerId,
                    marketplaceId);
            response = GenerateAdvertisementResponse.builder()
                    .withAdvertisement(AdvertisementTranslator.toCoral(generatedAd))
                    .build();
        } catch (Exception e) {
            LOG.error(String.format(
                "Something unexpected happened when calling GenerateAdvertisement for customer, %s, in marketplace %s.",
                request.getCustomerId(),
                request.getMarketplaceId()), e);
            response = GenerateAdvertisementResponse.builder()
                    .withAdvertisement(AdvertisementTranslator.toCoral(new EmptyGeneratedAdvertisement()))
                    .build();
        }

        return response;
    }
}
