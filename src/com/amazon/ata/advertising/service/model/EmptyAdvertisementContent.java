package com.amazon.ata.advertising.service.model;

/**
 *  An extension of AdvertisementContent that has empty renderable content and a null id. This class is used as the
 *  content for an EmptyGeneratedAdvertisement.
 */
public class EmptyAdvertisementContent extends AdvertisementContent {
    static final String EMPTY_CONTENT = "";

    /**
     * Creates an empty advertisement content, that is an empty string and a null ID.
     */
    public EmptyAdvertisementContent() {
        super(null, EMPTY_CONTENT, null);
    }
}
