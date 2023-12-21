package com.tct.mastery.task3;

import com.amazon.ata.advertising.service.activity.dagger.CreateContentActivityDagger;
import com.amazon.ata.advertising.service.activity.dagger.DeleteContentActivityDagger;
import com.amazon.ata.advertising.service.activity.dagger.UpdateClickThroughRateActivityDagger;
import com.amazon.ata.advertising.service.model.requests.GenerateAdvertisementRequest;
import com.amazon.ata.advertising.service.model.Advertisement;
import com.amazon.ata.advertising.service.model.AdvertisingContent;
import com.amazon.ata.advertising.service.model.requests.CreateContentRequest;
import com.amazon.ata.advertising.service.model.responses.CreateContentResponse;
import com.amazon.ata.advertising.service.model.requests.DeleteContentRequest;
import com.amazon.ata.advertising.service.model.requests.GenerateAdvertisementRequest;
import com.amazon.ata.advertising.service.model.TargetingGroup;
import com.amazon.ata.advertising.service.model.requests.UpdateClickThroughRateRequest;
import com.amazon.ata.advertising.service.model.responses.GenerateAdvertisementResponse;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasteryTaskThreeHelper {
    public MasteryTaskThreeHelper() {
    }

    public static GenerateAdvertisementRequest createGenAdRequest(String marketplaceId) {
        return createGenAdRequest(marketplaceId, null);
    }

    public static GenerateAdvertisementRequest createGenAdRequest(String marketplaceId, String customerId) {
        return new GenerateAdvertisementRequest().builder()
                .withMarketplaceId(marketplaceId)
                .withCustomerId(customerId)
                .build();
    }

    public static void assertContent(String expectedContent, GenerateAdvertisementResponse result,
                                     List<Double> eligibleCTRs) {
        Advertisement advertisement = result.getAdvertisement();
        String actualContent = advertisement.getContent();
        assertEquals(expectedContent, actualContent, String.format("Did not select the advertisement with the highest CTR. " +
                "The CTRs of eligible ads available were: %s", eligibleCTRs));
    }

    public AdvertisingContent createContent(String marketplaceId, double clickThroughRate) {
        String content = UUID.randomUUID().toString();
        CreateContentRequest request = new CreateContentRequest().builder()
                .withMarketplaceId(marketplaceId)
                .withContent(content)
                .build();
        CreateContentResponse result = new CreateContentActivityDagger().handleRequest(request, null);

        updateCTR(result.getTargetingGroup(), clickThroughRate);

        return result.getAdvertisingContent();
    }

    public void updateCTR(TargetingGroup targetingGroup, double clickThroughRate) {
        UpdateClickThroughRateRequest updateClickThroughRateRequest = new UpdateClickThroughRateRequest().builder()
                .withTargetingGroupId(targetingGroup.getTargetingGroupId())
                .withClickThroughRate(clickThroughRate)
                .build();
        new UpdateClickThroughRateActivityDagger().handleRequest(updateClickThroughRateRequest, null);
    }

    public void deleteContent(String contentId) {
        DeleteContentRequest request = new DeleteContentRequest().builder()
                .withContentId(contentId)
                .build();
        new DeleteContentActivityDagger().handleRequest(request, null);
    }
}
