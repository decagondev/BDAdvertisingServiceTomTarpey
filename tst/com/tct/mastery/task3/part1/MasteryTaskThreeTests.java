package com.tct.mastery.task3.part1;

import com.amazon.ata.advertising.service.activity.dagger.GenerateAdActivityDagger;
import com.amazon.ata.advertising.service.model.AdvertisingContent;
import com.amazon.ata.advertising.service.model.requests.GenerateAdvertisementRequest;
import com.amazon.ata.advertising.service.model.responses.GenerateAdvertisementResponse;
import com.tct.mastery.task3.MasteryTaskThreeHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.DoubleStream;

public class MasteryTaskThreeTests {
    private MasteryTaskThreeHelper helper;
    private List<String> contentToCleanup;

    /**
     * Ensure the test infra is ready for test run, including creating the client.
     */
    @BeforeEach
    public void setup() throws Exception {
        helper = new MasteryTaskThreeHelper();
        contentToCleanup = new ArrayList<>();
    }

    @AfterEach
    public void cleanup() {
        contentToCleanup.forEach(helper::deleteContent);
    }

    @Test
    public void generateAdvertisement_multipleContentEachSingleTargetingGroups_alwaysReturnsHighestCTR() {
        // GIVEN
        String marketplaceId = UUID.randomUUID().toString();
        String customerId = "123";
        DoubleStream.iterate(0, x -> x + 0.3)
                .limit(3)
                .mapToObj(ctr -> helper.createContent(marketplaceId, ctr))
                .map(AdvertisingContent::getId)
                .forEach(contentToCleanup::add);
        AdvertisingContent expectedContent = helper.createContent(marketplaceId, 1.0);
        contentToCleanup.add(expectedContent.getId());
        GenerateAdvertisementRequest request = MasteryTaskThreeHelper.createGenAdRequest(marketplaceId, customerId);

        // WHEN
        List<GenerateAdvertisementResponse> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(new GenerateAdActivityDagger().handleRequest(request, null));
        }

        // THEN
        results.forEach(result ->
            MasteryTaskThreeHelper.assertContent(expectedContent.getContent(), result, Arrays.asList(0.0, 0.3, 0.6, 1.0)));
    }
}
