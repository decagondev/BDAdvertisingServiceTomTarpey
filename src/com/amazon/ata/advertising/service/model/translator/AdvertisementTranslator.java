package com.amazon.ata.advertising.service.model.translator;

import com.amazon.ata.advertising.service.model.Advertisement;
import com.amazon.ata.advertising.service.model.GeneratedAdvertisement;

/**
 * Class used to perform translations from the service's internal Advertisement representation.
 */
public class AdvertisementTranslator {

    private AdvertisementTranslator() {}

    /** Translates an internal GeneratedAdvertisement to the coral model's Advertisement shape.
     * @param generatedAd - the object containing information to be converted to the coral model's shape
     * @return the Advertisement object filled in with the translated data
     */
    public static Advertisement toCoral(final GeneratedAdvertisement generatedAd) {
        return Advertisement.builder()
                .withContent(generatedAd.getContent().getRenderableContent())
                .withId(generatedAd.getId())
                .build();
    }
}
