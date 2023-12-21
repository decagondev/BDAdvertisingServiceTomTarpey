package com.amazon.ata.advertising.service.model;

/**
 * An EmptyGeneratedAdvertisement contains no renderable content, however does contain a unique id.
 */
public class EmptyGeneratedAdvertisement extends GeneratedAdvertisement {
    public static final String EMPTY_CONTENT = EmptyAdvertisementContent.EMPTY_CONTENT;

    /**
     * An EmptyGeneratedAdvertisement contains no renderable content, however does contain a unique id.
     */
    public EmptyGeneratedAdvertisement() {
        super(new EmptyAdvertisementContent());
    }
}
