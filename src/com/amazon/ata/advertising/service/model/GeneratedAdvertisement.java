package com.amazon.ata.advertising.service.model;

import org.apache.commons.lang3.Validate;

import java.util.UUID;

/**
 * The unique advertisement generated for a customer, containing an ID unique to this ad's impression and advertisement
 * content.
 */
public class GeneratedAdvertisement {

    private final String id;
    private final AdvertisementContent content;

    /**
     * Constructs GeneratedAdvertisements - generating a value for the id.
     * @param content - the content for the generated ad, cannot be null
     */
    public GeneratedAdvertisement(AdvertisementContent content) {
        Validate.notNull(content, "Advertisement Content may not be null");
        this.id = UUID.randomUUID().toString();
        this.content = content;
    }

    public AdvertisementContent getContent() {
        return this.content;
    }

    public String getId() {
        return this.id;
    }
}
