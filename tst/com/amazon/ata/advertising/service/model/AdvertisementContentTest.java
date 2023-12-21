package com.amazon.ata.advertising.service.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdvertisementContentTest {
    private static final String CONTENT_ID = UUID.randomUUID().toString();
    private static final String CONTENT = "Hello World";
    private static final AdvertisementContent ADVERTISEMENT_CONTENT = AdvertisementContent.builder()
            .withContentId(CONTENT_ID)
            .withRenderableContent(CONTENT)
            .build();

    @Test
    public void equals_referentiallyEquals_returnsTrue() {
        // GIVEN
        Object other = ADVERTISEMENT_CONTENT;

        // WHEN
        boolean isEqual = ADVERTISEMENT_CONTENT.equals(other);

        // THEN
        assertTrue(isEqual);
    }

    @Test
    public void equals_null_returnsFalse() {
        // GIVEN
        Object other = null;

        // WHEN
        boolean isEqual = ADVERTISEMENT_CONTENT.equals(other);

        // THEN
        assertFalse(isEqual);
    }

    @Test
    public void equals_otherClass_returnsFalse() {
        // GIVEN
        Object other = "String";

        // WHEN
        boolean isEqual = ADVERTISEMENT_CONTENT.equals(other);

        // THEN
        assertFalse(isEqual);
    }

    @Test
    public void equals_equalContentId_returnsTrue() {
        // GIVEN
        Object other = AdvertisementContent.builder()
                .withContentId(CONTENT_ID)
                .withRenderableContent("Other content")
                .build();

        // WHEN
        boolean isEqual = ADVERTISEMENT_CONTENT.equals(other);

        // THEN
        assertTrue(isEqual);
    }

    @Test
    public void equals_unequalContentIds_returnsFalse() {
        // GIVEN
        Object other = AdvertisementContent.builder()
                .withContentId(UUID.randomUUID().toString())
                .withRenderableContent(CONTENT)
                .build();

        // WHEN
        boolean isEqual = ADVERTISEMENT_CONTENT.equals(other);

        // THEN
        assertFalse(isEqual);
    }

    @Test
    public void hashCode_equalObjects_equalHashCodes() {
        // GIVEN
        Object other = AdvertisementContent.builder()
                .withContentId(CONTENT_ID)
                .withRenderableContent("Other content")
                .build();

        // WHEN
        int hashCode = other.hashCode();

        // THEN
        assertEquals(ADVERTISEMENT_CONTENT.hashCode(), hashCode);
    }

    @Test
    public void hashCode_unequalObjects_unequalHashCodes() {
        // GIVEN
        Object other = AdvertisementContent.builder()
                .withContentId(UUID.randomUUID().toString())
                .withRenderableContent(CONTENT)
                .build();

        // WHEN
        int hashCode = other.hashCode();

        // THEN
        assertNotEquals(ADVERTISEMENT_CONTENT.hashCode(), hashCode);
    }

}
