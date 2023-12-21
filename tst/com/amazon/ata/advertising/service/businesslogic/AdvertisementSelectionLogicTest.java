package com.amazon.ata.advertising.service.businesslogic;

import com.amazon.ata.advertising.service.dao.ReadableDao;
import com.amazon.ata.advertising.service.model.AdvertisementContent;
import com.amazon.ata.advertising.service.model.EmptyGeneratedAdvertisement;
import com.amazon.ata.advertising.service.model.GeneratedAdvertisement;
import com.amazon.ata.advertising.service.targeting.TargetingGroup;
import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicate;
import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicateResult;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdvertisementSelectionLogicTest {

    private static final String CUSTOMER_ID = "A123B456";
    private static final String MARKETPLACE_ID = "1";

    private static final String CONTENT_ID1 = "contentId1";
    private static final AdvertisementContent CONTENT1 = AdvertisementContent.builder().withContentId(CONTENT_ID1).build();
    private static final String CONTENT_ID2 = "contentId2";
    private static final AdvertisementContent CONTENT2 = AdvertisementContent.builder().withContentId(CONTENT_ID2).build();
    private static final String CONTENT_ID3 = "contentId3";
    private static final AdvertisementContent CONTENT3 = AdvertisementContent.builder().withContentId(CONTENT_ID3).build();
    private static final String CONTENT_ID4 = "contentId4";
    private static final AdvertisementContent CONTENT4 = AdvertisementContent.builder().withContentId(CONTENT_ID4).build();

    @Mock
    private TargetingPredicate PREDICATE1;

    @Mock
    private TargetingPredicate PREDICATE2;

    @Mock
    private TargetingPredicate PREDICATE3;

    @Mock
    private TargetingPredicate PREDICATE4;

    @Mock
    private ReadableDao<String, List<AdvertisementContent>> contentDao;

    @Mock
    private ReadableDao<String, List<TargetingGroup>> targetingGroupDao;

    private AdvertisementSelectionLogic adSelectionService;

    private TargetingGroup targetingGroup1;
    private TargetingGroup targetingGroup2;
    private TargetingGroup targetingGroup3;
    private TargetingGroup targetingGroup4;


    @BeforeEach
    public void setup() {
        initMocks(this);
        adSelectionService = new AdvertisementSelectionLogic(contentDao, targetingGroupDao);
        targetingGroup1 = new TargetingGroup(UUID.randomUUID().toString(), CONTENT_ID1, 0.25, Collections.singletonList(PREDICATE1));
        targetingGroup2 = new TargetingGroup(UUID.randomUUID().toString(), CONTENT_ID2, 1, Collections.singletonList(PREDICATE2));
        targetingGroup3 = new TargetingGroup(UUID.randomUUID().toString(), CONTENT_ID2, 0.5, Collections.singletonList(PREDICATE3));
        targetingGroup4 = new TargetingGroup(UUID.randomUUID().toString(), CONTENT_ID3, 0.4, Collections.singletonList(PREDICATE4));
        when(targetingGroupDao.get(CONTENT_ID1)).thenReturn(Collections.singletonList(targetingGroup1));
        when(targetingGroupDao.get(CONTENT_ID2)).thenReturn(Arrays.asList(targetingGroup2, targetingGroup3));
        when(targetingGroupDao.get(CONTENT_ID3)).thenReturn(Arrays.asList(targetingGroup4));
        when(targetingGroupDao.get(CONTENT_ID4)).thenReturn(Collections.emptyList());
    }

    @Test
    public void selectAdvertisement_nullMarketplaceId_EmptyAdReturned() {
        // GIVEN / WHEN
        GeneratedAdvertisement ad = adSelectionService.selectAdvertisement(CUSTOMER_ID, null);

        // THEN
        assertTrue(ad instanceof EmptyGeneratedAdvertisement);
    }

    @Test
    public void selectAdvertisement_emptyMarketplaceId_EmptyAdReturned() {
        // GIVEN / WHEN
        GeneratedAdvertisement ad = adSelectionService.selectAdvertisement(CUSTOMER_ID, "");

        // THEN
        assertTrue(ad instanceof EmptyGeneratedAdvertisement);
    }

    @Test
    public void selectAdvertisement_noContentForMarketplace_emptyAdReturned() throws InterruptedException {
        // GIVEN
        when(contentDao.get(MARKETPLACE_ID)).thenReturn(Collections.emptyList());

        // WHEN
        GeneratedAdvertisement ad = adSelectionService.selectAdvertisement(CUSTOMER_ID, MARKETPLACE_ID);

        // THEN
        assertTrue(ad instanceof EmptyGeneratedAdvertisement);
    }

    @Test
    public void selectAdvertisement_allAdsIneligible_emptyAdReturned() {
        // GIVEN
        when(contentDao.get(MARKETPLACE_ID)).thenReturn(Arrays.asList(CONTENT1, CONTENT2));
        when(PREDICATE1.evaluate(any())).thenReturn(TargetingPredicateResult.FALSE);
        when(PREDICATE2.evaluate(any())).thenReturn(TargetingPredicateResult.FALSE);
        when(PREDICATE3.evaluate(any())).thenReturn(TargetingPredicateResult.FALSE);

        // WHEN
        GeneratedAdvertisement ad = adSelectionService.selectAdvertisement(CUSTOMER_ID, MARKETPLACE_ID);

        // THEN
        assertTrue(ad instanceof EmptyGeneratedAdvertisement);
    }

    @Test
    public void selectAdvertisement_oneEligibleAdWithLowCTR_returnsEligibleAd() {
        // GIVEN
        when(contentDao.get(MARKETPLACE_ID)).thenReturn(Arrays.asList(CONTENT1, CONTENT2));
        when(PREDICATE1.evaluate(any())).thenReturn(TargetingPredicateResult.TRUE);
        when(PREDICATE2.evaluate(any())).thenReturn(TargetingPredicateResult.FALSE);
        when(PREDICATE3.evaluate(any())).thenReturn(TargetingPredicateResult.FALSE);

        // WHEN
        GeneratedAdvertisement ad = adSelectionService.selectAdvertisement(CUSTOMER_ID, MARKETPLACE_ID);

        // THEN
        assertEquals(CONTENT_ID1, ad.getContent().getContentId());
    }

    @Test
    public void selectAdvertisement_multipleEligibleAds_selectOneWithHighestCTR() {
        // GIVEN
        when(contentDao.get(MARKETPLACE_ID)).thenReturn(Arrays.asList(CONTENT1, CONTENT2, CONTENT3));
        when(PREDICATE1.evaluate(any())).thenReturn(TargetingPredicateResult.TRUE);
        when(PREDICATE2.evaluate(any())).thenReturn(TargetingPredicateResult.TRUE);
        when(PREDICATE4.evaluate(any())).thenReturn(TargetingPredicateResult.TRUE);

        // WHEN
        GeneratedAdvertisement ad = adSelectionService.selectAdvertisement(CUSTOMER_ID, MARKETPLACE_ID);

        // THEN
        assertEquals(CONTENT_ID2, ad.getContent().getContentId());
    }

    @Test
    public void selectAdvertisement_oneAdWithoutTargetingGroups_selectOneWithHighestCTR() {
        // GIVEN
        when(contentDao.get(MARKETPLACE_ID)).thenReturn(Arrays.asList(CONTENT1, CONTENT2, CONTENT3, CONTENT4));
        when(PREDICATE1.evaluate(any())).thenReturn(TargetingPredicateResult.FALSE);
        when(PREDICATE2.evaluate(any())).thenReturn(TargetingPredicateResult.FALSE);
        when(PREDICATE3.evaluate(any())).thenReturn(TargetingPredicateResult.TRUE);
        when(PREDICATE4.evaluate(any())).thenReturn(TargetingPredicateResult.FALSE);

        // WHEN
        GeneratedAdvertisement ad = adSelectionService.selectAdvertisement(CUSTOMER_ID, MARKETPLACE_ID);

        // THEN
        assertEquals(CONTENT_ID2, ad.getContent().getContentId());
    }
}
